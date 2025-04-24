package com.mc.esexam.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionCode;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.esexam.exception.CustomException;
import com.mc.esexam.service.QuestionCodeService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author machao
 * *@datetime 2025/2/20 16:26
 * *@inform 代码题接口
 */
@Slf4j
@RestController
@RequestMapping("exam/questionCode")
@Tag(name = "代码题接口", description = "关于代码题的操作")
public class QuestionCodeController {

    @Autowired
    private QuestionCodeService questionCodeService;

    @Operation(summary = "代码题的添加", description = "对代码题进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionCode", description = "代码题信息", required = true)
    })
    @PostMapping("/add")
    public Result add(@RequestBody QuestionCode questionCode) {
        if (ObjectUtil.isEmpty(questionCode.getCourseId())
                || ObjectUtil.isEmpty(questionCode.getQuestion())
                || ObjectUtil.isEmpty(questionCode.getAnswer())
                || ObjectUtil.isEmpty(questionCode.getAnalysis())
                || ObjectUtil.isEmpty(questionCode.getScore())
                || ObjectUtil.isEmpty(questionCode.getLevel())
                || ObjectUtil.isEmpty(questionCode.getSection())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        questionCodeService.add(questionCode);
        return Result.success();
    }
    @Operation(summary = "代码题的单体删除", description = "通过id对代码题进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除代码题的id", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        questionCodeService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "代码题的批量删除", description = "通过id列表对代码题进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除代码题的id列表", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        questionCodeService.deleteBatch(ids);
        return Result.success();
    }

    @Operation(summary = "代码题的修改", description = "对代码题进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionCode", description = "需要修改的代码题信息", required = true)
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody QuestionCode questionCode) {
        questionCodeService.updateById(questionCode);
        return Result.success();
    }

    @Operation(summary = "代码题的id查询", description = "对代码题进行id查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询代码题的id", required = true)
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        QuestionCode questionCode = questionCodeService.selectById(id);
        return Result.success(questionCode);
    }

    @Operation(summary = "代码题的条件查询", description = "对代码题进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionCode", description = "要查询代码题的条件", required = true)
    })
    @GetMapping("/selectAll")
    public Result selectAll(QuestionCode questionCode) {
        List<QuestionCode> list = questionCodeService.selectAll(questionCode);
        return Result.success(list);
    }

    @Operation(summary = "代码题的分页查询", description = "对代码题进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询代码题的分页条件", required = true)
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<QuestionCode> page = questionCodeService.selectPage(params);
        return Result.success(page);
    }

    @Operation(summary = "编程题题库的excel下载", description = "将编程题题库数据进行打包，封装到excel中进行发送")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "excel下载成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "response", description = "请求体", required = true),
    })
    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        // 1. 从数据库中查询出所有数据
        QuestionCode questionCode = new QuestionCode();
        List<QuestionCode> all = questionCodeService.selectAll(questionCode);
        if (CollectionUtil.isEmpty(all)) {
            throw new CustomException(ResultCodeEnum.EXCEL_DATA_NOFOUND);
        }
        // 2. 定义一个 List，存储处理之后的数据，用于塞到 list 里
        List<Map<String, Object>> list = new ArrayList<>(all.size());

        // 3. 定义Map<key,value> 出来，遍历每一条数据，然后封装到 Map<key,value> 里，把这个 map 塞到 list 里
        for (QuestionCode code : all) {
            Map<String, Object> row = new HashMap<>();
            row.put("试题编号", code.getId());
            row.put("考试科目Id", code.getCourseId());
            row.put("试题内容", code.getQuestion());
            row.put("正确答案", code.getAnswer());
            row.put("题目解析", code.getAnalysis());
            row.put("分值", code.getScore());
            row.put("难度等级", code.getLevel());
            row.put("所属章节", code.getSection());
            if (code.getCourse() != null) {
                row.put("课程名称", code.getCourse().getName()); // 假设Course类有getName方法
            }
            if (code.getTeacher() != null) {
                row.put("教师姓名", code.getTeacher().getName()); // 假设Teacher类有getName方法
            }
            list.add(row);
        }
        // 4. 创建一个 ExcelWriter，把 list 数据用这个writer写出来（生成出来）
        ExcelWriter wr = ExcelUtil.getWriter(true);
        wr.write(list, true);

        // 5. 把这个 excel 下载下来
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=questionCodeInformation.xlsx");
        ServletOutputStream out = response.getOutputStream();
        wr.flush(out, true);
        wr.close();
        return Result.success();
    }

    @Operation(summary = "编程题题库的excel添加", description = "接受excel，映射到实体类，进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "编程题题库添加成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "file", description = "excel文件", required = true),
    })
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        List<QuestionCode> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(QuestionCode.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            for (QuestionCode questionCode : infoList) {
                try {
                    questionCodeService.add(questionCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.success();
    }
}