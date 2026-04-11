package com.mc.esuser.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.JwtUtils.JwtAPI;
import com.mc.escommon.SmUtils.api.FileWithKeyReadAndWriteAPI;
import com.mc.escommon.SmUtils.api.SM2JwtAPI;
import com.mc.escommon.SmUtils.api.SM3API;
import com.mc.escommon.SmUtils.api.Sm2API;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.escommon.result.RoleEnum;
import com.mc.escommon.result.TimeEnum;
import com.mc.escommon.entity.user.Admin;
import com.mc.escommon.entity.user.Params;
import com.mc.escommon.user.Utils;
import com.mc.esuser.exception.CustomException;
import com.mc.esuser.mapper.AdminMapper;
import com.mc.esuser.service.AdminService;
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
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增
     */
    @Override
    public void add(Admin admin) {
        //1.生成账号
        String account= Utils.getAccount();
        admin.setAccount(account);
        //2.密码初始化
        String password="123456";

        //3.生成密钥 两对，考试一对/个人加密一对
        String url1=RoleEnum.admin.name()+"/"+account+"/user";
        String url2=RoleEnum.admin.name()+"/"+account+"/exam";
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
        String pubKey=Sm2API.pub2str(publicKey);
        //5.对密码进行加密
        String key;
        try {
            key = Sm2API.encryption(pubKey, password);
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new CustomException(ResultCodeEnum.KEY_ENCRY_ERROR);
        }
        admin.setPassword(key);
        //6.对密码做数字摘要
        String sm3 = SM3API.hashS(password);
        admin.setKeySm3(sm3);
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
        //7.将信息存入数据库
        admin.setRole(RoleEnum.admin.name());
        adminMapper.insert(admin);
    }

    /**
     * 删除
     */
    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids){
            adminMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    @Override
    public void updateById(Admin admin) {
        adminMapper.updateById(admin);
    }

    /**
     * 根据ID查询
     */
    @Override
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    @Override
    public List<Admin> selectAll(Admin admin) {
        return adminMapper.selectAll(admin);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Admin> selectPage(Params params) {
        //进行数据转移
        Admin admin=new Admin();
        if(!Objects.equals(params.getAccount(), "") && params.getAccount()!=null){
            admin.setAccount(params.getAccount());//账号
        }
        if(!Objects.equals(params.getName(), "") &&params.getName()!=null){
            admin.setName(params.getName());//姓名
        }
        if(!Objects.equals(params.getSex(), "") &&params.getSex()!=null){
            admin.setSex(params.getSex());//性别
        }
        if(!Objects.equals(params.getPhone(), "") &&params.getPhone()!=null){
            admin.setPhone(params.getPhone());//电话
        }
        if(!Objects.equals(params.getEmail(), "") &&params.getEmail()!=null){
            admin.setEmail(params.getEmail());//电子邮件
        }
        //开启分页查询
        if(params.getPageNum()==null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());

        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Admin> list= adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }

    /**
     * 登录
     */
    @Override
    public Admin login(Admin admin) {
        //1.进行非空判断
        if(admin.getAccount()==null|| admin.getAccount().isEmpty()){
            throw new CustomException(ResultCodeEnum.USER_NAME_ERROR);
        }
        if(admin.getPassword()==null || admin.getPassword().isEmpty()){
            throw new CustomException(ResultCodeEnum.USER_PASSWORD_ERROR);
        }
        //2.做数字摘要
        admin.setAccount(admin.getAccount().trim());
        String sm3 = SM3API.hashS(admin.getPassword());
        //3.进行密码判断
        Admin user=adminMapper.findByAccountAndSM3(admin.getAccount(),sm3);
        if(user==null){
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        String url=RoleEnum.admin.name()+"/"+admin.getAccount()+"/user";
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
        if(!key.equals(admin.getPassword())){
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
//    public void register(Admin admin) {
//
//    }


    /**
     * 根据账号和手机号查询
     */
    @Override
    public Admin selectByAccountAndPhone(String account, String phone) {
        return adminMapper.selectByAccountAndPhone(account,phone);
    }

    /**
     * 忘记密码修改密码
     */
    @Override
    public void updatePassword(Admin admin) {
        Admin user=new Admin();
        //1.查询密钥
        String url=RoleEnum.admin.name()+"/"+admin.getAccount()+"/user";
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
            key = Sm2API.encryption(pubKey, admin.getPassword());
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new CustomException(ResultCodeEnum.KEY_ENCRY_ERROR);
        }
        //3.做摘要
        String sm3 = SM3API.hashS(admin.getPassword());

        user.setId(admin.getId());
        user.setPassword(key);
        user.setKeySm3(sm3);
        adminMapper.updateById(user);
    }

    /**
     * 修改密码
     */
    @Override
    public void updatePd(Admin admin) {
        Admin user=new Admin();
        String password="123456";
        //1.查询密钥
        String url=RoleEnum.admin.name()+"/"+admin.getAccount()+"/user";
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

        user.setId(admin.getId());
        user.setPassword(key);
        user.setKeySm3(sm3);
        adminMapper.updateById(user);
    }

    /**
     * 修改密钥
     */
    @Override
    public void updateKey(Admin admin) {
        //1.账号
        Admin user =new Admin();
        String account=admin.getAccount();
        //2.密码初始化
        String url=RoleEnum.admin.name()+"/"+account+"/user";
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
            password = Sm2API.decrypt(priKey, admin.getPassword());
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
        user.setId(admin.getId());
        //6.对密码做数字摘要
        adminMapper.updateById(user);
    }

    /**
     * 修改信息
     */
    @Override
    public void updateInform(Admin admin) {
        Admin user=new Admin();
        if(!Objects.equals(admin.getName(), "") &&admin.getName()!=null){
            user.setName(admin.getName());//姓名
        }
        if(!Objects.equals(admin.getSex(), "") &&admin.getSex()!=null){
            user.setSex(admin.getSex());//性别
        }
        if(!Objects.equals(admin.getPhoto(), "") &&admin.getPhoto()!=null){
            user.setPhoto(admin.getPhoto());//照片
        }
        if(!Objects.equals(admin.getPhone(), "") &&admin.getPhone()!=null){
            user.setPhone(admin.getPhone());//电话
        }
        if(!Objects.equals(admin.getEmail(), "") &&admin.getEmail()!=null){
            user.setEmail(admin.getEmail());//电子邮件
        }
        user.setId(admin.getId());
        adminMapper.updateById(user);
    }
}
