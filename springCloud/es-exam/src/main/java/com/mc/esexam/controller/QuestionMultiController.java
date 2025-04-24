package com.mc.esexam.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionMulti;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.esexam.exception.CustomException;
import com.mc.esexam.service.QuestionMultiService;
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
 * *@datetime 2025/2/20 16:28
 * *@inform 选择题接口
 */
@Slf4j
@RestController
@RequestMapping("exam/questionMulti")
@Tag(name = "选择题接口", description = "关于选择题的操作")
public class QuestionMultiController {

    @Autowired
    private QuestionMultiService questionMultiService;

    @Operation(summary = "选择题的添加", description = "对选择题进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionMulti", description = "选择题信息", required = true)
    })
    @PostMapping("/add")
    public Result add(@RequestBody QuestionMulti questionMulti) {
        if (ObjectUtil.isEmpty(questionMulti.getCourseId())
                || ObjectUtil.isEmpty(questionMulti.getQuestionType())
                || ObjectUtil.isEmpty(questionMulti.getQuestion())
                || ObjectUtil.isEmpty(questionMulti.getAnswerA())
                || ObjectUtil.isEmpty(questionMulti.getAnswerB())
                || ObjectUtil.isEmpty(questionMulti.getAnswerC())
                || ObjectUtil.isEmpty(questionMulti.getAnswerD())
                || ObjectUtil.isEmpty(questionMulti.getRightAnswer())
                || ObjectUtil.isEmpty(questionMulti.getAnalysis())
                || ObjectUtil.isEmpty(questionMulti.getScore())
                || ObjectUtil.isEmpty(questionMulti.getSection())
                || ObjectUtil.isEmpty(questionMulti.getLevel())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        questionMultiService.add(questionMulti);
        return Result.success();
    }

    @Operation(summary = "选择题的单体删除", description = "通过id对选择题进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除选择题的id", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        questionMultiService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "选择题的批量删除", description = "通过id列表对选择题进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除选择题的id列表", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        questionMultiService.deleteBatch(ids);
        return Result.success();
    }

    @Operation(summary = "选择题的修改", description = "对选择题进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionMulti", description = "需要修改的选择题信息", required = true)
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody QuestionMulti questionMulti) {
        questionMultiService.updateById(questionMulti);
        return Result.success();
    }

    @Operation(summary = "选择题的id查询", description = "对选择题进行id查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询选择题的id", required = true)
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        QuestionMulti questionMulti = questionMultiService.selectById(id);
        return Result.success(questionMulti);
    }

    @Operation(summary = "选择题的条件查询", description = "对选择题进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionMulti", description = "要查询选择题的条件", required = true)
    })
    @GetMapping("/selectAll")
    public Result selectAll(QuestionMulti questionMulti) {
        List<QuestionMulti> list = questionMultiService.selectAll(questionMulti);
        return Result.success(list);
    }

    @Operation(summary = "选择题的分页查询", description = "对选择题进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询选择题的分页条件", required = true)
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<QuestionMulti> page = questionMultiService.selectPage(params);
        return Result.success(page);
    }

    @Operation(summary = "选择题题库的 Excel 下载", description = "将选择题题库数据进行打包，封装到 Excel 中进行发送")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Excel 下载成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "response", description = "请求体", required = true)
    })
    @GetMapping("/export")
    public Result export(HttpServletResponse response) throws IOException {
        // 1. 从数据库中查询出所有数据
        QuestionMulti questionMulti = new QuestionMulti();
        List<QuestionMulti> all = questionMultiService.selectAll(questionMulti);
        if (CollectionUtil.isEmpty(all)) {
            throw new CustomException(ResultCodeEnum.EXCEL_DATA_NOFOUND);
        }
        // 2. 定义一个 List，存储处理之后的数据，用于塞到 list 里
        List<Map<String, Object>> list = new ArrayList<>(all.size());

        // 3. 定义 Map<key, value> 出来，遍历每一条数据，然后封装到 Map<key, value> 里，把这个 map 塞到 list 里
        for (QuestionMulti multi : all) {
            Map<String, Object> row = new HashMap<>();
            row.put("试题编号", multi.getId());
            row.put("考试科目 Id", multi.getCourseId());
            row.put("选择题类型", multi.getQuestionType());
            row.put("试题内容", multi.getQuestion());
            row.put("选项A", multi.getAnswerA());
            row.put("选项B", multi.getAnswerB());
            row.put("选项C", multi.getAnswerC());
            row.put("选项D", multi.getAnswerD());
            row.put("正确答案", multi.getRightAnswer());
            row.put("题目解析", multi.getAnalysis());
            row.put("分值", multi.getScore());
            row.put("难度等级", multi.getLevel());
            row.put("所属章节", multi.getSection());
            if (multi.getCourse() != null) {
                row.put("课程名称", multi.getCourse().getName());
            }
            if (multi.getTeacher() != null) {
                row.put("教师姓名", multi.getTeacher().getName());
            }
            list.add(row);
        }
        // 4. 创建一个 ExcelWriter，把 list 数据用这个 writer 写出来（生成出来）
        ExcelWriter wr = ExcelUtil.getWriter(true);
        wr.write(list, true);

        // 5. 把这个 Excel 下载下来
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=questionMultiInformation.xlsx");
        ServletOutputStream out = response.getOutputStream();
        wr.flush(out, true);
        wr.close();
        return Result.success();
    }

    @Operation(summary = "选择题题库的 Excel 添加", description = "接受 Excel，映射到实体类，进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "选择题题库添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true)
    })
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        List<QuestionMulti> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(QuestionMulti.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            for (QuestionMulti questionMulti : infoList) {
                try {
                    questionMultiService.add(questionMulti);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.success();
    }
}