package com.mc.esexam.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionShortAns;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.esexam.exception.CustomException;
import com.mc.esexam.service.QuestionShortAnsService;
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
 * @datetime 2025/3/3 17:54
 * @inform 简答题接口
 */
@Slf4j
@RestController
@RequestMapping("exam/questionShortAns")
@Tag(name = "简答题接口", description = "关于简答题的操作")
public class QuestionShortAnsController {

    @Autowired
    private QuestionShortAnsService questionShortAnsService;

    @Operation(summary = "简答题的添加", description = "对简答题进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionShortAns", description = "简答题信息", required = true)
    })
    @PostMapping("/add")
    public Result add(@RequestBody QuestionShortAns questionShortAns) {
        if (ObjectUtil.isEmpty(questionShortAns.getCourseId())
                || ObjectUtil.isEmpty(questionShortAns.getQuestion())
                || ObjectUtil.isEmpty(questionShortAns.getAnswer())
                || ObjectUtil.isEmpty(questionShortAns.getAnalysis())
                || ObjectUtil.isEmpty(questionShortAns.getScore())
                || ObjectUtil.isEmpty(questionShortAns.getLevel())
                || ObjectUtil.isEmpty(questionShortAns.getSection())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        questionShortAnsService.add(questionShortAns);
        return Result.success();
    }

    @Operation(summary = "简答题的单体删除", description = "通过id对简答题进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除简答题的id", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        questionShortAnsService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "简答题的批量删除", description = "通过id列表对简答题进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除简答题的id列表", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        questionShortAnsService.deleteBatch(ids);
        return Result.success();
    }

    @Operation(summary = "简答题的修改", description = "对简答题进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionShortAns", description = "需要修改的简答题信息", required = true)
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody QuestionShortAns questionShortAns) {
        questionShortAnsService.updateById(questionShortAns);
        return Result.success();
    }

    @Operation(summary = "简答题的id查询", description = "对简答题进行id查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询简答题的id", required = true)
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        QuestionShortAns questionShortAns = questionShortAnsService.selectById(id);
        return Result.success(questionShortAns);
    }

    @Operation(summary = "简答题的条件查询", description = "对简答题进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionShortAns", description = "要查询简答题的条件", required = true)
    })
    @GetMapping("/selectAll")
    public Result selectAll(QuestionShortAns questionShortAns) {
        List<QuestionShortAns> list = questionShortAnsService.selectAll(questionShortAns);
        return Result.success(list);
    }

    @Operation(summary = "简答题的分页查询", description = "对简答题进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询简答题的分页条件", required = true)
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<QuestionShortAns> page = questionShortAnsService.selectPage(params);
        return Result.success(page);
    }

    @Operation(summary = "简答题题库的excel下载", description = "将简答题题库数据进行打包，封装到excel中进行发送")
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
        QuestionShortAns questionShortAns = new QuestionShortAns();
        List<QuestionShortAns> all = questionShortAnsService.selectAll(questionShortAns);
        if (CollectionUtil.isEmpty(all)) {
            throw new CustomException(ResultCodeEnum.EXCEL_DATA_NOFOUND);
        }
        // 2. 定义一个 List，存储处理之后的数据，用于塞到 list 里
        List<Map<String, Object>> list = new ArrayList<>(all.size());

        // 3. 定义Map<key,value> 出来，遍历每一条数据，然后封装到 Map<key,value> 里，把这个 map 塞到 list 里
        for (QuestionShortAns ans : all) {
            Map<String, Object> row = new HashMap<>();
            row.put("试题编号", ans.getId());
            row.put("考试科目Id", ans.getCourseId());
            row.put("试题内容", ans.getQuestion());
            row.put("正确答案", ans.getAnswer());
            row.put("题目解析", ans.getAnalysis());
            row.put("分值", ans.getScore());
            row.put("难度等级", ans.getLevel());
            row.put("所属章节", ans.getSection());
            if (ans.getCourse() != null) {
                row.put("课程名称", ans.getCourse().getName());
            }
            if (ans.getTeacher() != null) {
                row.put("教师姓名", ans.getTeacher().getName());
            }
            list.add(row);
        }
        // 4. 创建一个 ExcelWriter，把 list 数据用这个writer写出来（生成出来）
        ExcelWriter wr = ExcelUtil.getWriter(true);
        wr.write(list, true);

        // 5. 把这个 excel 下载下来
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=questionShortAnsInformation.xlsx");
        ServletOutputStream out = response.getOutputStream();
        wr.flush(out, true);
        wr.close();
        return Result.success();
    }

    @Operation(summary = "简答题题库的excel添加", description = "接受excel，映射到实体类，进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "简答题题库添加成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "file", description = "excel文件", required = true),
    })
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        List<QuestionShortAns> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(QuestionShortAns.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            for (QuestionShortAns questionShortAns : infoList) {
                try {
                    questionShortAnsService.add(questionShortAns);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.success();
    }
}