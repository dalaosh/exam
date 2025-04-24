package com.mc.esexam.controller;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.ExamAnswer;
import com.mc.escommon.entity.exam.ExamTeacherGrading;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.result.Result;
import com.mc.esexam.service.ExamAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/1 0:50
 * @inform 考试答案相关操作的控制器
 */
@Slf4j
@RestController
@RequestMapping("exam/examAnswer")
@Tag(name = "考试答案接口", description = "该接口提供了一系列关于考试答案的增删改查以及判分等操作，方便对考试答案数据进行管理。")
public class ExamAnswerController {

    @Autowired
    private ExamAnswerService examAnswerService;

    @Operation(summary = "添加考试答案", description = "向系统中添加一条新的考试答案记录。通常用于教师录入答案或者学生提交答案等场景。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "考试答案添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examAnswer", description = "考试答案信息，包含答案的具体内容、所属考试、学生信息等。", required = true, schema = @Schema(implementation = ExamAnswer.class))
    })
    @PostMapping("/add")
    public Result add(@RequestBody ExamAnswer examAnswer) {
        examAnswerService.add(examAnswer);
        return Result.success();
    }

    @Operation(summary = "删除单个考试答案", description = "根据考试答案的唯一标识 ID，从系统中删除对应的考试答案记录。常用于清理无效或错误的答案数据。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "考试答案删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除的考试答案的唯一标识 ID，为正整数。", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        examAnswerService.deleteById(id);
        return Result.success();
    }

    @Operation(summary = "批量删除考试答案", description = "根据多个考试答案的 ID 列表，一次性从系统中删除对应的多条考试答案记录。适用于需要批量清理数据的场景。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "批量删除考试答案成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除的考试答案的 ID 列表，列表中的元素为正整数。", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        examAnswerService.deleteBatch(ids);
        return Result.success();
    }

    @Operation(summary = "修改考试答案", description = "根据提供的考试答案信息，对系统中已有的考试答案记录进行更新。可用于教师修改答案或者学生修改提交的答案等场景。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "考试答案修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examAnswer", description = "需要修改的考试答案信息，包含要更新的答案内容、所属考试、学生信息等，ID 用于定位要修改的记录。", required = true, schema = @Schema(implementation = ExamAnswer.class))
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody ExamAnswer examAnswer) {
        examAnswerService.updateById(examAnswer);
        return Result.success();
    }

    @Operation(summary = "根据 ID 查询考试答案", description = "通过考试答案的唯一标识 ID，从系统中查询对应的考试答案记录。用于获取特定答案的详细信息。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "考试答案查询成功",
                    content = @Content(schema = @Schema(implementation = ExamAnswer.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询的考试答案的唯一标识 ID，为正整数。", required = true)
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ExamAnswer examAnswer = examAnswerService.selectById(id);
        return Result.success(examAnswer);
    }

    @Operation(summary = "条件查询考试答案", description = "根据提供的考试答案条件，从系统中筛选出符合条件的考试答案记录。可用于根据多种条件组合查询答案。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "考试答案条件查询成功",
                    content = @Content(schema = @Schema(implementation = List.class)))
    })
    @Parameters({
            @Parameter(name = "examAnswer", description = "要查询考试答案的条件，可包含答案内容、所属考试、学生信息等，根据这些条件筛选答案。", required = true, schema = @Schema(implementation = ExamAnswer.class))
    })
    @GetMapping("/selectAll")
    public Result selectAll(ExamAnswer examAnswer) {
        List<ExamAnswer> list = examAnswerService.selectAll(examAnswer);
        return Result.success(list);
    }

    @Operation(summary = "分页查询考试答案", description = "根据提供的分页条件，从系统中分页查询考试答案记录。适用于数据量较大时，分页展示答案信息。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "考试答案分页查询成功",
                    content = @Content(schema = @Schema(implementation = PageInfo.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询考试答案的分页条件，包含页码、每页记录数等信息。", required = true, schema = @Schema(implementation = Params.class))
    })
    @GetMapping("/selectPage")
    public Result selectPage(Params params) {
        PageInfo<ExamAnswer> page = examAnswerService.selectPage(params);
        return Result.success(page);
    }

    @Operation(summary = "阅卷判分", description = "教师根据考试答案进行人工判分，更新考试答案的分数信息。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "阅卷判分成功，考试答案分数更新成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "需要修改的考试答案信息，包含答案 ID、判分结果等。", required = true, schema = @Schema(implementation = Params.class))
    })
    @PutMapping("/updateByScore")
    public Result updateByScore(@RequestBody Params params) {
        examAnswerService.updateByScore(params);
        return Result.success();
    }

    @Operation(summary = "自动判分（选择题等）", description = "系统根据预设的规则对考试答案中的选择题等进行自动判分，更新相应的分数信息。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "自动判分（选择题等）成功，考试答案分数更新成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examTeacherGradinglist", description = "需要修改的考试答案信息列表，包含答案 ID、题目类型等，用于系统自动判分。", required = true, schema = @Schema(implementation = List.class))
    })
    @PutMapping("/updateByMultiAll")
    public Result updateByMultiAll(@RequestBody List<ExamTeacherGrading> examTeacherGradinglist) {
        examAnswerService.updateByMultiAll(examTeacherGradinglist);
        return Result.success();
    }

    @Operation(summary = "自动判分（判断题等）", description = "系统根据预设的规则对考试答案中的判断题等进行自动判分，更新相应的分数信息。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "自动判分（判断题等）成功，考试答案分数更新成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examTeacherGradinglist", description = "需要修改的考试答案信息列表，包含答案 ID、题目类型等，用于系统自动判分。", required = true, schema = @Schema(implementation = List.class))
    })
    @PutMapping("/updateByJudgeAll")
    public Result updateByJudgeAll(@RequestBody List<ExamTeacherGrading> examTeacherGradinglist) {
        examAnswerService.updateByJudgeAll(examTeacherGradinglist);
        return Result.success();
    }

    @Operation(summary = "审批自动判分（选择题等）", description = "系统根据预设的规则对考试答案中的选择题等进行自动判分，更新相应的分数信息。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "自动判分（选择题等）成功，考试答案分数更新成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examTeacherGradinglist", description = "需要修改的考试答案信息列表，包含答案 ID、题目类型等，用于系统自动判分。", required = true, schema = @Schema(implementation = List.class))
    })
    @PutMapping("/updateByMultiAllAnswer")
    public Result updateByMultiAllAnswer(@RequestBody List<ExamAnswer> examAnswerList) {
        examAnswerService.updateByMultiAllAnswer(examAnswerList);
        return Result.success();
    }

    @Operation(summary = "审批自动判分（判断题等）", description = "系统根据预设的规则对考试答案中的判断题等进行自动判分，更新相应的分数信息。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "自动判分（判断题等）成功，考试答案分数更新成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "examTeacherGradinglist", description = "需要修改的考试答案信息列表，包含答案 ID、题目类型等，用于系统自动判分。", required = true, schema = @Schema(implementation = List.class))
    })
    @PutMapping("/updateByJudgeAllAnswer")
    public Result updateByJudgeAllAnswer(@RequestBody List<ExamAnswer> examAnswerList) {
        examAnswerService.updateByJudgeAllAnswer(examAnswerList);
        return Result.success();
    }

    @Operation(summary = "查询已批阅的考试答案", description = "根据提供的条件，从系统中筛选出已经批阅的考试答案记录。用于查看已批阅的答案情况。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询已批阅的考试答案成功",
                    content = @Content(schema = @Schema(implementation = List.class)))
    })
    @Parameters({
            @Parameter(name = "examAnswer", description = "要查询考试答案的条件，可包含答案内容、所属考试、学生信息等，根据这些条件筛选已批阅的答案。", required = true, schema = @Schema(implementation = ExamAnswer.class))
    })
    @GetMapping("/selectIsMark")
    public Result selectIsMark(ExamAnswer examAnswer) {
        List<ExamAnswer> list = examAnswerService.selectIsMark(examAnswer);
        return Result.success(list);
    }

    @Operation(summary = "查询未批阅的考试答案", description = "根据提供的条件，从系统中筛选出尚未批阅的考试答案记录。用于查看待批阅的答案情况。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询未批阅的考试答案成功",
                    content = @Content(schema = @Schema(implementation = List.class)))
    })
    @Parameters({
            @Parameter(name = "examAnswer", description = "要查询考试答案的条件，可包含答案内容、所属考试、学生信息等，根据这些条件筛选未批阅的答案。", required = true, schema = @Schema(implementation = ExamAnswer.class))
    })
    @GetMapping("/selectNoMark")
    public Result selectNoMark(ExamAnswer examAnswer) {
        List<ExamAnswer> list = examAnswerService.selectNoMark(examAnswer);
        return Result.success(list);
    }

    @Operation(summary = "查询已标记正确的考试答案", description = "根据提供的条件，从系统中筛选出已经标记为正确的考试答案记录。用于查看正确答案情况。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询已标记正确的考试答案成功",
                    content = @Content(schema = @Schema(implementation = List.class)))
    })
    @Parameters({
            @Parameter(name = "examAnswer", description = "要查询考试答案的条件，可包含答案内容、所属考试、学生信息等，根据这些条件筛选已标记正确的答案。", required = true, schema = @Schema(implementation = ExamAnswer.class))
    })
    @GetMapping("/selectIsRightSign")
    public Result selectIsRightSign(ExamAnswer examAnswer) {
        List<ExamAnswer> list = examAnswerService.selectIsRightSign(examAnswer);
        return Result.success(list);
    }

    @Operation(summary = "查询未标记正确的考试答案", description = "根据提供的条件，从系统中筛选出尚未标记为正确的考试答案记录。用于查看待确认的答案情况。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询未标记正确的考试答案成功",
                    content = @Content(schema = @Schema(implementation = List.class)))
    })
    @Parameters({
            @Parameter(name = "examAnswer", description = "要查询考试答案的条件，可包含答案内容、所属考试、学生信息等，根据这些条件筛选未标记正确的答案。", required = true, schema = @Schema(implementation = ExamAnswer.class))
    })
    @GetMapping("/selectNoRightSign")
    public Result selectNoRightSign(ExamAnswer examAnswer) {
        List<ExamAnswer> list = examAnswerService.selectNoRightSign(examAnswer);
        return Result.success(list);
    }

    @Operation(summary = "批量验签并标记正确", description = "对提供的考试答案列表进行批量验签，验签成功后将其标记为正确状态。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "批量验签成功",
                    content = @Content(schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "400", description = "无效的请求参数")
    })
    @Parameters({
            @Parameter(name = "examAnswerList", description = "需要验签的考试答案列表，包含答案内容、所属考试等信息，系统将对这些答案进行批量验签操作。",
                    required = true)
    })
    @PutMapping("/batchSetRightSign")
    public Result batchSetRightSign(@RequestBody List<ExamAnswer> examAnswerList) {
        examAnswerService.selectSetRightSignAll(examAnswerList);
        return Result.success();
    }

    @Operation(summary = "单个验签并标记正确", description = "对单个考试答案进行验签，验签成功后将其标记为正确状态。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "单个验签成功",
                    content = @Content(schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "400", description = "无效的请求参数")
    })
    @Parameters({
            @Parameter(name = "examAnswer", description = "需要验签的单个考试答案，包含答案内容、所属考试等信息。",
                    required = true)
    })
    @PutMapping("/setRightSign")
    public Result setRightSign(@RequestBody ExamAnswer examAnswer) {
        examAnswerService.selectSetRightSign(examAnswer);
        return Result.success();
    }
}