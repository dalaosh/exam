package com.mc.esfiles.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author machao
 * * @datetime 2025/1/14 11:14
 * * inform
 */

@RestController
@RequestMapping("/files/wang")
@Tag(name = "富文本文件接口",description = "对富文本中的图片进行存储")
public class WangEditorFileController {
    // 文件上传存储路径
    private static final String filePath = System.getProperty("user.dir") + "/files/wang/";
    private final String port="9998";

    @Value("${ip:localhost}")
    private String ip;


    @Operation(summary = "wang-editor编辑器文件上传接口", description = "对富文本中的图片进行上传")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "上传成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "file", description = "图片", required = true),
    })
    @PostMapping("/upload")//localhost:9998/files/wang/1736829934909-43989342-20213202317.jpg
    public Map<String, Object> wangEditorUpload(MultipartFile file) {
        String flag = System.currentTimeMillis() + "";
        String random=RandomUtil.randomNumbers(8);
        String fileName = file.getOriginalFilename();
        try {
            // 文件存储形式：时间戳-随机数-文件名  1736829934909-43989342-20213202317.jpg
            FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + random + "-" + fileName);
            System.out.println(fileName + "--上传成功");
            Thread.sleep(1L);
        } catch (Exception e) {
            System.err.println(fileName + "--文件上传失败");
        }
        String http = "http://" + ip + ":" + port + "/files/wang/";
        Map<String, Object> resMap = new HashMap<>();
        // wangEditor上传图片成功后， 需要返回的参数
        resMap.put("errno", 0);
        resMap.put("data", CollUtil.newArrayList(Dict.create().set("url", http + flag + "-" + random + "-" + fileName)));
        return resMap;
    }

    @Operation(summary = "富文本图片获取", description = "对富文本中的图片进行获取")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "flag", description = "图片地址", required = true),
    })
    @GetMapping("/{flag}")   //  1736829934909-43989342-20213202317.jpg
    public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;
        try {
            if (StrUtil.isNotEmpty(flag)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(flag, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + flag);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }
}
