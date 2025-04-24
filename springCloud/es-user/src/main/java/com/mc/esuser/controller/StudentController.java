package com.mc.esuser.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.SmUtils.api.FileWithKeyReadAndWriteAPI;
import com.mc.escommon.SmUtils.api.KeyToPemAPI;
import com.mc.escommon.SmUtils.api.Sm2API;
import com.mc.escommon.entity.user.Student;
import com.mc.escommon.entity.user.Params;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.escommon.result.RoleEnum;
import com.mc.esuser.exception.CustomException;
import com.mc.esuser.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author machao
 * * @datetime 2025/1/13 10:59
 * * inform
 */

@Slf4j
@RestController
@RequestMapping("user/student")
@Tag(name = "学生接口",description = "关于学生的相关操作")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Operation(summary = "学生的添加", description = "对学生进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "student", description = "学生信息", required = true),
    })
    @PostMapping("/add")
    public Result add(@RequestBody Student student) {
        studentService.add(student);
        return Result.success();
    }

    @Operation(summary = "学生的单个删除", description = "对学生进行单个删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除学生的id", required = true),
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        studentService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "学生的批量删除", description = "对学生进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除学生的id列表", required = true),
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        studentService.deleteBatch(ids);
        return Result.success();
    }

//    @Operation(summary = "学生的修改", description = "对学生进行修改")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "修改成功",
//                    content = @Content(schema = @Schema(implementation = String.class))),
//    })
//    @Parameters({
//            @Parameter(name = "student", description = "需要修改的学生信息", required = true),
//    })
//    @PutMapping("/update")
//    public Result updateById(@RequestBody Student student) {
//        studentService.updateById(student);
//        return Result.success();
//    }

    @Operation(summary = "学生的id查询", description = "对学生进行id查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询学生的id", required = true),
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Student student = studentService.selectById(id);
        return Result.success(student);
    }

    @Operation(summary = "学生的条件查询", description = "对学生进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "student", description = "要查询学生的条件", required = true),
    })
    @GetMapping("/selectAll")
    public Result selectAll(Student student ) {
        List<Student> list = studentService.selectAll(student);
        return Result.success(list);
    }

    @Operation(summary = "学生的分页查询", description = "对学生进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询学生的分页条件", required = true),
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<Student> page = studentService.selectPage(params);
        return Result.success(page);
    }

    @Operation(summary = "学生的excel下载", description = "将学生进行打包，封装到excel中进行发送")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "excel下载成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "response", description = "请求体", required = true),
    })
    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        // 思考：
        // 要一行一行的组装数据，塞到一个list里面
        // 每一行数据，其实就对应数据库表中的一行数据，也就是对应Java的一个实体类Type
        // 我们怎么知道它某一列就是对应某个表头呢？？ 需要映射数据，我们需要一个Map<key,value>，把这个map塞到list里

        // 干！
        // 1. 从数据库中查询出所有数据
        Student user =new Student();
        List<Student> all = studentService.selectAll(user);
        if (CollectionUtil.isEmpty(all)) {
            throw new CustomException(ResultCodeEnum.EXCEL_DATA_NOFOUND);
        }
        // 2. 定义一个 List，存储处理之后的数据，用于塞到 list 里
        List<Map<String, Object>> list = new ArrayList<>(all.size());

        // 3. 定义Map<key,value> 出来，遍历每一条数据，然后封装到 Map<key,value> 里，把这个 map 塞到 list 里
        for (Student student : all) {
            Map<String, Object> row = new HashMap<>();
            row.put("角色", student.getRole() );
            row.put("姓名", student.getName());
            row.put("性别", student.getSex());
            row.put("电子邮箱", student.getEmail());
            row.put("电话", student.getPhone());
            row.put("账号", student.getAccount());
            row.put("密码", student.getPassword());
            row.put("密码摘要", student.getKeySm3());
            list.add(row);
        }
        // 4. 创建一个 ExcelWriter，把 list 数据用这个writer写出来（生成出来）
        ExcelWriter wr = ExcelUtil.getWriter(true);
        wr.write(list, true);

        // 5. 把这个 excel 下载下来
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=StudentInformation.xlsx");
        ServletOutputStream out = response.getOutputStream();
        wr.flush(out, true);
        wr.close();
        IoUtil.close(System.out);
        return Result.success();
    }

    @Operation(summary = "学生的excel添加", description = "接受excel，映射到实体类，进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "学生添加成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "file", description = "excel文件", required = true),
    })
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        List<Student> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Student.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            for (Student student : infoList) {
                try {
                    studentService.add(student);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.success();
    }

    @Operation(summary = "学生的单个重置密码", description = "通过id对学生进行重置密码，密码重置为123456")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "密码重置成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要重置密码的学生的id", required = true),
    })
    @PutMapping("/update/password/{id}")
    public Result updatePassword(@PathVariable Integer id){
        Student student=studentService.selectById(id);
        studentService.updatePd(student);
        return Result.success();
    }
    @Operation(summary = "学生的批量重置密码", description = "通过id列表对学生进行重置密码，密码重置为123456")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "密码重置成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "ids", description = "要重置密码的学生的id列表", required = true),
    })
    @PutMapping("/update/password/batch")
    public Result updatePdBatch(@RequestBody List<Integer> ids){
        for (Integer id :ids){
            Student student=studentService.selectById(id);
            studentService.updatePd(student);
        }
        return Result.success();
    }
    @Operation(summary = "学生的单个重置用户密钥", description = "通过id对学生进行重置密钥")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "密钥重置成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "id", description = "要重置密钥的学生的id", required = true),
    })
    @PutMapping("/update/key/{id}")
    public Result updateKey(@PathVariable Integer id){
        Student student=studentService.selectById(id);
        studentService.updateKey(student);
        return Result.success();
    }
    @Operation(summary = "学生的批量重置密钥", description = "通过id列表对学生进行重置密钥")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "密钥重置成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "ids", description = "要重置密钥的学生的id列表", required = true),
    })
    @PutMapping("/update/key/batch")
    public Result updateKeyBatch(@RequestBody List<Integer> ids){
        for (Integer id :ids){
            Student student=studentService.selectById(id);
            studentService.updateKey(student);
        }
        return Result.success();
    }
    @Operation(summary = "学生进行个人信息的修改", description = "用户对自己的基本信息进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "信息修改成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "student", description = "需要修改的用户的信息", required = true),
    })
    @PutMapping("/update/inform")
    public Result updateKey(@RequestBody Student student){
        studentService.updateInform(student);
        return Result.success();
    }

    @Operation(summary = "学生进行密钥显示", description = "对用户的公钥和私钥进行显示")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "信息修改成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "params", description = "查询公钥和私钥的条件", required = true),
    })
    @PostMapping("/getKey")
    public Result getKey(@RequestBody Params params){
        Map<String, String> map = new HashMap<>();
        //查找两对公钥和私钥
        String url1= RoleEnum.student.name()+"/"+params.getAccount()+"/user";
        String url2=RoleEnum.student.name()+"/"+params.getAccount()+"/exam";
        KeyToPemAPI keyToPemAPI1;
        try {
            keyToPemAPI1=new KeyToPemAPI(url1);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        List<String> pem1;
        try {
            pem1=keyToPemAPI1.pem();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String pubKey1=pem1.get(0);
        String priKey1=pem1.get(1);

        KeyToPemAPI keyToPemAPI2;
        try {
            keyToPemAPI2=new KeyToPemAPI(url2);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
        List<String> pem2;
        try {
            pem2=keyToPemAPI2.pem();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String pubKey2=pem2.get(0);
        String priKey2=pem2.get(1);
        //对私钥进行加密
        //获取公钥
        try {
            FileWithKeyReadAndWriteAPI.readKey(url1);
        } catch (CertificateException | NoSuchAlgorithmException | InvalidKeySpecException | IOException |
                 NoSuchProviderException e) {
            throw new CustomException(ResultCodeEnum.KEY_READ_ERROR);
        }
        BCECPublicKey publicKey=FileWithKeyReadAndWriteAPI.getPublicKey();
        //将公钥转为字符串
        String pubKey= Sm2API.pub2str(publicKey);
        try {
            priKey1 = Sm2API.encryption(pubKey, priKey1);
            priKey2 = Sm2API.encryption(pubKey, priKey2);
        } catch (InvalidCipherTextException | NoSuchProviderException | InvalidKeySpecException |
                 NoSuchAlgorithmException e) {
            throw new CustomException(ResultCodeEnum.KEY_ENCRY_ERROR);
        }
        map.put("selfPri",priKey1);
        map.put("selfPub",pubKey1);
        map.put("examPri",priKey2);
        map.put("examPub",pubKey2);
        //进行传输
        return Result.success(map);
    }
}
