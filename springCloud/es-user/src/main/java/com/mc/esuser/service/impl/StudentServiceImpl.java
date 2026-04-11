package com.mc.esuser.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.JwtUtils.JwtAPI;
import com.mc.escommon.SmUtils.api.FileWithKeyReadAndWriteAPI;
import com.mc.escommon.SmUtils.api.SM2JwtAPI;
import com.mc.escommon.SmUtils.api.SM3API;
import com.mc.escommon.SmUtils.api.Sm2API;
import com.mc.escommon.user.Utils;
import com.mc.esuser.exception.CustomException;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.escommon.result.RoleEnum;
import com.mc.escommon.result.TimeEnum;
import com.mc.escommon.entity.user.Params;
import com.mc.escommon.entity.user.Student;
import com.mc.esuser.mapper.StudentMapper;
import com.mc.esuser.service.StudentService;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增
     */
    @Override
    public void add(Student student) {
        //1.生成账号
        String account= Utils.getAccount();
        student.setAccount(account);
        //2.密码初始化
        String password="123456";

        //3.生成密钥 两对，考试一对/个人加密一对
        String url1=RoleEnum.student.name()+"/"+account+"/user";
        String url2=RoleEnum.student.name()+"/"+account+"/exam";
        //将密钥存入文件
        boolean result1 = FileWithKeyReadAndWriteAPI.WriteKeyToFile(url1);
        if(!result1){
            throw new CustomException(ResultCodeEnum.KEY_CREAT_ERROR);
        }
        //4.读取个人密钥公钥
        //从文件读密钥
        try {
            FileWithKeyReadAndWriteAPI.readKey(url1);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new CustomException(ResultCodeEnum.KEY_READ_ERROR);
        }
        BCECPublicKey publicKey=FileWithKeyReadAndWriteAPI.getPublicKey();
        //将公钥转为字符串
        String pubKey= Sm2API.pub2str(publicKey);
        //5.对密码进行加密
        String key;
        try {
            key = Sm2API.encryption(pubKey, password);
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new CustomException(ResultCodeEnum.KEY_ENCRY_ERROR);
        }
        student.setPassword(key);
        //6.对密码做数字摘要
        String sm3 = SM3API.hashS(password);
        student.setKeySm3(sm3);
        //读取考试密钥
        boolean result2 = FileWithKeyReadAndWriteAPI.WriteKeyToFile(url2);
        if(!result2){
            throw new CustomException(ResultCodeEnum.KEY_CREAT_ERROR);
        }
        try {
            FileWithKeyReadAndWriteAPI.readKey(url2);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new CustomException(ResultCodeEnum.KEY_READ_ERROR);
        }
        BCECPublicKey exkey=FileWithKeyReadAndWriteAPI.getPublicKey();
        //将公钥转为字符串
        String pKey= Sm2API.pub2str(exkey);
        student.setPublicKey(pKey);
        //7.将信息存入数据库
        student.setRole(RoleEnum.student.name());
        studentMapper.insert(student);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids){
            studentMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateById(Student student) {
        studentMapper.updateById(student);
    }

    /**
     * 根据ID查询
     */
    @Override
    public Student selectById(Integer id) {
        return studentMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<Student> selectAll(Student student) {
        return studentMapper.selectAll(student);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Student> selectPage(Params params) {
        //进行数据转移
        Student student=new Student();
        if(!Objects.equals(params.getAccount(), "") && params.getAccount()!=null){
            student.setAccount(params.getAccount());//账号
        }
        if(!Objects.equals(params.getName(), "") &&params.getName()!=null){
            student.setName(params.getName());//姓名
        }
        if(!Objects.equals(params.getSex(), "") &&params.getSex()!=null){
            student.setSex(params.getSex());//性别
        }
        if(!Objects.equals(params.getPhone(), "") &&params.getPhone()!=null){
            student.setPhone(params.getPhone());//电话
        }
        if(!Objects.equals(params.getEmail(), "") &&params.getEmail()!=null){
            student.setEmail(params.getEmail());//电子邮件
        }
        //开启分页查询
        if(params.getPageNum()==null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Student> list= studentMapper.selectAll(student);
        return PageInfo.of(list);
    }

    /**
     * 登录
     */
    @Override
    public Student login(Student student) {
        //1.进行非空判断
        if(student.getAccount()==null|| student.getAccount().isEmpty()){
            throw new CustomException(ResultCodeEnum.USER_NAME_ERROR);
        }
        if(student.getPassword()==null || student.getPassword().isEmpty()){
            throw new CustomException(ResultCodeEnum.USER_PASSWORD_ERROR);
        }
        //2.做数字摘要
        student.setAccount(student.getAccount().trim());
        String sm3 = SM3API.hashS(student.getPassword());
        //3.进行密码判断
        Student user=studentMapper.findByAccountAndSM3(student.getAccount(),sm3);
        if(user==null){
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        String url=RoleEnum.student.name()+"/"+student.getAccount()+"/user";
        //读取私钥
        try {
            FileWithKeyReadAndWriteAPI.readKey(url);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new CustomException(ResultCodeEnum.KEY_READ_ERROR);
        }
        BCECPrivateKey privateKey=FileWithKeyReadAndWriteAPI.getPrivateKey();
        //将公钥转为字符串
        String priKey=Sm2API.pri2str(privateKey);
        //对密码进行解密
        String key;
        try {
            key = Sm2API.decrypt(priKey, user.getPassword());
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new CustomException(ResultCodeEnum.KEY_DECRY_ERROR);
        }
        //判断
        if(!key.equals(student.getPassword())){
            throw new CustomException(ResultCodeEnum.USER_KEY_ERROR);
        }
        //4.生成该登录用户对应的token    然后跟着user一起返回前台
        String token1 = JwtAPI.getToken(String.valueOf(user.getId()),user.getName(),user.getPassword(), TimeEnum.DAYS.time);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(user.getId()));
        SM2JwtAPI sm2JwtAPI=new SM2JwtAPI(url);
        String token2 = sm2JwtAPI.genToken(map, 1);
        String token=token1+","+token2;
        user.setToken(token);
        //5.将token存入redis
        redisTemplate.opsForValue().set(token1,user.getId(),TimeEnum.DAYS.time, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(token2,url,TimeEnum.DAYS.time, TimeUnit.SECONDS);
        //如果查出，则登录
        return user;
    }

//    /**
//     * 注册
//     */
//    @Override
//    public void register(Student student) {
//
//    }

    @Override
    public Student selectByAccountAndPhone(String account, String phone) {
        return studentMapper.selectByAccountAndPhone(account,phone);
    }

    /**
     * 忘记密码修改密码
     */
    @Override
    public void updatePassword(Student student) {
        Student user=new Student();
        //1.查询密钥
        String url=RoleEnum.student.name()+"/"+student.getAccount()+"/user";
        //从文件读密钥
        try {
            FileWithKeyReadAndWriteAPI.readKey(url);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new CustomException(ResultCodeEnum.KEY_READ_ERROR);
        }
        BCECPublicKey publicKey=FileWithKeyReadAndWriteAPI.getPublicKey();
        //将公钥转为字符串
        String pubKey=Sm2API.pub2str(publicKey);
        //2.对密码加密
        String key;
        try {
            key = Sm2API.encryption(pubKey, student.getPassword());
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new CustomException(ResultCodeEnum.KEY_ENCRY_ERROR);
        }
        //3.做摘要
        String sm3 = SM3API.hashS(student.getPassword());

        user.setId(student.getId());
        user.setPassword(key);
        user.setKeySm3(sm3);
        studentMapper.updateById(user);
    }

    /**
     * 修改密码
     */
    @Override
    public void updatePd(Student student) {
        Student user=new Student();
        String password="123456";
        //1.查询密钥
        String url=RoleEnum.student.name()+"/"+student.getAccount()+"/user";
        //从文件读密钥
        try {
            FileWithKeyReadAndWriteAPI.readKey(url);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new CustomException(ResultCodeEnum.KEY_READ_ERROR);
        }
        BCECPublicKey publicKey=FileWithKeyReadAndWriteAPI.getPublicKey();
        //将公钥转为字符串
        String pubKey=Sm2API.pub2str(publicKey);
        //2.对密码加密
        String key;
        try {
            key = Sm2API.encryption(pubKey, password);
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new CustomException(ResultCodeEnum.KEY_ENCRY_ERROR);
        }
        //3.做摘要
        String sm3 = SM3API.hashS(password);

        user.setId(student.getId());
        user.setPassword(key);
        user.setKeySm3(sm3);
        studentMapper.updateById(user);
    }

    /**
     * 修改密钥
     */
    @Override
    public void updateKey(Student student) {
        //1.账号
        Student user =new Student();
        String account=student.getAccount();
        //2.密码初始化
        String url=RoleEnum.student.name()+"/"+account+"/user";
        try {
            FileWithKeyReadAndWriteAPI.readKey(url);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new CustomException(ResultCodeEnum.KEY_READ_ERROR);
        }
        BCECPrivateKey privateKey=FileWithKeyReadAndWriteAPI.getPrivateKey();
        //将公钥转为字符串
        String priKey=Sm2API.pri2str(privateKey);
        //对密码进行解密
        String password;
        try {
            password = Sm2API.decrypt(priKey, student.getPassword());
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new CustomException(ResultCodeEnum.KEY_DECRY_ERROR);
        }
        //3.生成密钥
        //将密钥存入文件
        boolean result = FileWithKeyReadAndWriteAPI.WriteKeyToFile(url);
        if(!result){
            throw new CustomException(ResultCodeEnum.KEY_CREAT_ERROR);
        }
        //4.读取个人密钥公钥
        //从文件读密钥
        try {
            FileWithKeyReadAndWriteAPI.readKey(url);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new CustomException(ResultCodeEnum.KEY_READ_ERROR);
        }
        BCECPublicKey publicKey=FileWithKeyReadAndWriteAPI.getPublicKey();
        //将公钥转为字符串
        String pubKey=Sm2API.pub2str(publicKey);
        //5.对密码进行加密
        String key;
        try {
            key = Sm2API.encryption(pubKey, password);
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new CustomException(ResultCodeEnum.KEY_ENCRY_ERROR);
        }
        user.setPassword(key);
        user.setId(student.getId());
        //6.对密码做数字摘要
        studentMapper.updateById(user);
    }

    /**
     * 修改信息
     */
    @Override
    public void updateInform(Student student) {
        Student user=new Student();
        if(!Objects.equals(student.getName(), "") &&student.getName()!=null){
            user.setName(student.getName());//姓名
        }
        if(!Objects.equals(student.getSex(), "") &&student.getSex()!=null){
            user.setSex(student.getSex());//性别
        }
        if(!Objects.equals(student.getPhoto(), "") &&student.getPhoto()!=null){
            user.setPhoto(student.getPhoto());//照片
        }
        if(!Objects.equals(student.getPhone(), "") &&student.getPhone()!=null){
            user.setPhone(student.getPhone());//电话
        }
        if(!Objects.equals(student.getEmail(), "") &&student.getEmail()!=null){
            user.setEmail(student.getEmail());//电子邮件
        }
        student.setId(student.getId());
        studentMapper.updateById(user);
    }
}
