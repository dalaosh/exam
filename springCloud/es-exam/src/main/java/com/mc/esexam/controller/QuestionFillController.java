package com.mc.esexam.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.exam.QuestionFill;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.esexam.exception.CustomException;
import com.mc.esexam.service.QuestionFillService;
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
 * *@datetime 2025/2/20 16:27
 * *@inform 填空题接口
 */
@Slf4j
@RestController
@RequestMapping("exam/questionFill")
@Tag(name = "填空题接口", description = "关于填空题的操作")
public class QuestionFillController {

    @Autowired
    private QuestionFillService questionFillService;

    @Operation(summary = "填空题的添加", description = "对填空题进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionFill", description = "填空题信息", required = true)
    })
    @PostMapping("/add")
    public Result add(@RequestBody QuestionFill questionFill) {
        if (ObjectUtil.isEmpty(questionFill.getCourseId())
                || ObjectUtil.isEmpty(questionFill.getQuestion())
                || ObjectUtil.isEmpty(questionFill.getAnswer())
                || ObjectUtil.isEmpty(questionFill.getAnalysis())
                || ObjectUtil.isEmpty(questionFill.getScore())
                || ObjectUtil.isEmpty(questionFill.getLevel())
                || ObjectUtil.isEmpty(questionFill.getSection())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        questionFillService.add(questionFill);
        return Result.success();
    }

    @Operation(summary = "填空题的单体删除", description = "通过id对填空题进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除填空题的id", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        questionFillService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "填空题的批量删除", description = "通过id列表对填空题进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除填空题的id列表", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        questionFillService.deleteBatch(ids);
        return Result.success();
    }

    @Operation(summary = "填空题的修改", description = "对填空题进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionFill", description = "需要修改的填空题信息", required = true)
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody QuestionFill questionFill) {
        questionFillService.updateById(questionFill);
        return Result.success();
    }

    @Operation(summary = "填空题的id查询", description = "对填空题进行id查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询填空题的id", required = true)
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        QuestionFill questionFill = questionFillService.selectById(id);
        return Result.success(questionFill);
    }

    @Operation(summary = "填空题的条件查询", description = "对填空题进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "questionFill", description = "要查询填空题的条件", required = true)
    })
    @GetMapping("/selectAll")
    public Result selectAll(QuestionFill questionFill) {
        List<QuestionFill> list = questionFillService.selectAll(questionFill);
        return Result.success(list);
    }

    @Operation(summary = "填空题的分页查询", description = "对填空题进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询填空题的分页条件", required = true)
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<QuestionFill> page = questionFillService.selectPage(params);
        return Result.success(page);
    }

    @Operation(summary = "填空题题库的excel下载", description = "将填空题题库数据进行打包，封装到excel中进行发送")
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
        QuestionFill questionFill = new QuestionFill();
        List<QuestionFill> all = questionFillService.selectAll(questionFill);
        if (CollectionUtil.isEmpty(all)) {
            throw new CustomException(ResultCodeEnum.EXCEL_DATA_NOFOUND);
        }
        // 2. 定义一个 List，存储处理之后的数据，用于塞到 list 里
        List<Map<String, Object>> list = new ArrayList<>(all.size());

        // 3. 定义Map<key,value> 出来，遍历每一条数据，然后封装到 Map<key,value> 里，把这个 map 塞到 list 里
        for (QuestionFill fill : all) {
            Map<String, Object> row = new HashMap<>();
            row.put("试题编号", fill.getId());
            row.put("考试科目Id", fill.getCourseId());
            row.put("试题内容", fill.getQuestion());
            row.put("正确答案", fill.getAnswer());
            row.put("题目解析", fill.getAnalysis());
            row.put("分值", fill.getScore());
            row.put("难度等级", fill.getLevel());
            row.put("所属章节", fill.getSection());
            if (fill.getCourse() != null) {
                row.put("课程名称", fill.getCourse().getName());
            }
            if (fill.getTeacher() != null) {
                row.put("教师姓名", fill.getTeacher().getName());
            }
            list.add(row);
        }
        // 4. 创建一个 ExcelWriter，把 list 数据用这个writer写出来（生成出来）
        ExcelWriter wr = ExcelUtil.getWriter(true);
        wr.write(list, true);

        // 5. 把这个 excel 下载下来
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=questionFillInformation.xlsx");
        ServletOutputStream out = response.getOutputStream();
        wr.flush(out, true);
        wr.close();
        return Result.success();
    }

    @Operation(summary = "填空题题库的excel添加", description = "接受excel，映射到实体类，进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "填空题题库添加成功",
                    content = @Content(schema = @Schema(implementation = String.class))),
    })
    @Parameters({
            @Parameter(name = "file", description = "excel文件", required = true),
    })
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        List<QuestionFill> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(QuestionFill.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            for (QuestionFill questionFill : infoList) {
                try {
                    questionFillService.add(questionFill);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.success();
    }
}