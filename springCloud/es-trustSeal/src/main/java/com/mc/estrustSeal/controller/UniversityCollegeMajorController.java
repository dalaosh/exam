package com.mc.estrustSeal.controller;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.statistic.Params;
import com.mc.escommon.entity.statistic.SchoolsColleges;
import com.mc.escommon.entity.statistic.SchoolsMajor;
import com.mc.escommon.entity.statistic.SchoolsUniversity;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.estrustSeal.service.UniversityCollegeMajorService;
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
 * 学校、学院和专业相关的控制器，负责处理与学校、学院和专业信息的增删改查以及分页查询相关的 HTTP 请求。
 * 该控制器依赖于 UniversityCollegeMajorService 来执行具体的业务逻辑。
 */
@Slf4j
@RestController
@RequestMapping("trustSeal/universityCollegeMajor")
@Tag(name = "学校、学院和专业接口", description = "关于学校、学院和专业的操作")
public class UniversityCollegeMajorController {

    @Autowired
    private UniversityCollegeMajorService universityCollegeMajorService;

    /**
     * 新增学校信息。
     *
     * @param schoolsUniversity 包含学校详细信息的对象，如学校名称、省份 ID、地区 ID、城市 ID 和地址等。
     * @return 若新增成功，返回包含成功信息的 Result 对象；若必要参数缺失，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "新增学校", description = "对学校进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "schoolsUniversity", description = "学校信息", required = true)
    })
    @PostMapping("/university/add")
    public Result insertUniversity(@RequestBody SchoolsUniversity schoolsUniversity) {
        if (ObjectUtil.isEmpty(schoolsUniversity.getUniversityName())
                || ObjectUtil.isEmpty(schoolsUniversity.getProvinceId())
                || ObjectUtil.isEmpty(schoolsUniversity.getAreaId())
                || ObjectUtil.isEmpty(schoolsUniversity.getCityId())
                || ObjectUtil.isEmpty(schoolsUniversity.getAddress())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.insertUniversity(schoolsUniversity);
        return Result.success();
    }

    /**
     * 新增学院信息。
     *
     * @param schoolsColleges 包含学院详细信息的对象，如所属学校 ID 和学院名称等。
     * @return 若新增成功，返回包含成功信息的 Result 对象；若必要参数缺失，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "新增学院", description = "对学院进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "schoolsColleges", description = "学院信息", required = true)
    })
    @PostMapping("/college/add")
    public Result insertCollege(@RequestBody SchoolsColleges schoolsColleges) {
        if (ObjectUtil.isEmpty(schoolsColleges.getUniversityId())
                || ObjectUtil.isEmpty(schoolsColleges.getCollegesName())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.insertCollege(schoolsColleges);
        return Result.success();
    }

    /**
     * 新增专业信息。
     *
     * @param schoolsMajor 包含专业详细信息的对象，如所属学校 ID、所属学院 ID 和专业名称等。
     * @return 若新增成功，返回包含成功信息的 Result 对象；若必要参数缺失，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "新增专业", description = "对专业进行添加")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "schoolsMajor", description = "专业信息", required = true)
    })
    @PostMapping("/major/add")
    public Result insertMajor(@RequestBody SchoolsMajor schoolsMajor) {
        if (ObjectUtil.isEmpty(schoolsMajor.getUniversityId())
                || ObjectUtil.isEmpty(schoolsMajor.getCollegesId())
                || ObjectUtil.isEmpty(schoolsMajor.getMajorName())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.insertMajor(schoolsMajor);
        return Result.success();
    }

    /**
     * 根据 ID 删除学校信息。
     *
     * @param id 要删除的学校的 ID。
     * @return 若删除成功，返回包含成功信息的 Result 对象；若 ID 为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "删除学校", description = "通过 id 对学校进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除学校的 id", required = true)
    })
    @DeleteMapping("/university/delete/{id}")
    public Result deleteUniversityById(@PathVariable Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.deleteUniversityById(id);
        return Result.success();
    }

    /**
     * 批量删除学校信息。
     *
     * @param ids 要删除的学校的 ID 列表。
     * @return 若删除成功，返回包含成功信息的 Result 对象；若 ID 列表为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "批量删除学校", description = "通过 id 列表对学校进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除学校的 id 列表", required = true)
    })
    @DeleteMapping("/university/delete/batch")
    public Result deleteUniversityByIds(@RequestBody List<Integer> ids) {
        if (ObjectUtil.isEmpty(ids)) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.deleteUniversityByIds(ids.toArray(new Integer[0]));
        return Result.success();
    }

    /**
     * 根据 ID 删除学院信息。
     *
     * @param id 要删除的学院的 ID。
     * @return 若删除成功，返回包含成功信息的 Result 对象；若 ID 为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "删除学院", description = "通过 id 对学院进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除学院的 id", required = true)
    })
    @DeleteMapping("/college/delete/{id}")
    public Result deleteCollegeById(@PathVariable Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.deleteCollegeById(id);
        return Result.success();
    }

    /**
     * 批量删除学院信息。
     *
     * @param ids 要删除的学院的 ID 列表。
     * @return 若删除成功，返回包含成功信息的 Result 对象；若 ID 列表为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "批量删除学院", description = "通过 id 列表对学院进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除学院的 id 列表", required = true)
    })
    @DeleteMapping("/college/delete/batch")
    public Result deleteCollegesByIds(@RequestBody List<Integer> ids) {
        if (ObjectUtil.isEmpty(ids)) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.deleteCollegesByIds(ids.toArray(new Integer[0]));
        return Result.success();
    }

    /**
     * 根据 ID 删除专业信息。
     *
     * @param id 要删除的专业的 ID。
     * @return 若删除成功，返回包含成功信息的 Result 对象；若 ID 为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "删除专业", description = "通过 id 对专业进行删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除专业的 id", required = true)
    })
    @DeleteMapping("/major/delete/{id}")
    public Result deleteMajorById(@PathVariable Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.deleteMajorById(id);
        return Result.success();
    }

    /**
     * 批量删除专业信息。
     *
     * @param ids 要删除的专业的 ID 列表。
     * @return 若删除成功，返回包含成功信息的 Result 对象；若 ID 列表为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "批量删除专业", description = "通过 id 列表对专业进行批量删除")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除专业的 id 列表", required = true)
    })
    @DeleteMapping("/major/delete/batch")
    public Result deleteMajorByIds(@RequestBody List<Integer> ids) {
        if (ObjectUtil.isEmpty(ids)) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.deleteMajorByIds(ids.toArray(new Integer[0]));
        return Result.success();
    }

    /**
     * 修改学校信息。
     *
     * @param schoolsUniversity 包含要修改的学校信息的对象，需包含学校 ID。
     * @return 若修改成功，返回包含成功信息的 Result 对象；若 ID 为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "修改学校信息", description = "对学校信息进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "schoolsUniversity", description = "需要修改的学校信息", required = true)
    })
    @PutMapping("/university/update")
    public Result updateUniversityById(@RequestBody SchoolsUniversity schoolsUniversity) {
        if (ObjectUtil.isEmpty(schoolsUniversity.getId())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.updateUniversityById(schoolsUniversity);
        return Result.success();
    }

    /**
     * 修改学院信息。
     *
     * @param schoolsColleges 包含要修改的学院信息的对象，需包含学院 ID。
     * @return 若修改成功，返回包含成功信息的 Result 对象；若 ID 为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "修改学院信息", description = "对学院信息进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "schoolsColleges", description = "需要修改的学院信息", required = true)
    })
    @PutMapping("/college/update")
    public Result updateCollegeById(@RequestBody SchoolsColleges schoolsColleges) {
        if (ObjectUtil.isEmpty(schoolsColleges.getId())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.updateCollegeById(schoolsColleges);
        return Result.success();
    }

    /**
     * 修改专业信息。
     *
     * @param schoolsMajor 包含要修改的专业信息的对象，需包含专业 ID。
     * @return 若修改成功，返回包含成功信息的 Result 对象；若 ID 为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "修改专业信息", description = "对专业信息进行修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "schoolsMajor", description = "需要修改的专业信息", required = true)
    })
    @PutMapping("/major/update")
    public Result updateMajorById(@RequestBody SchoolsMajor schoolsMajor) {
        if (ObjectUtil.isEmpty(schoolsMajor.getId())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        universityCollegeMajorService.updateMajorById(schoolsMajor);
        return Result.success();
    }

    /**
     * 根据 ID 查询学校信息。
     *
     * @param id 要查询的学校的 ID。
     * @return 若查询成功，返回包含学校信息的 Result 对象；若 ID 为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "根据 ID 查询学校", description = "对学校进行 id 查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询学校的 id", required = true)
    })
    @GetMapping("/university/selectById/{id}")
    public Result selectUniversityById(@PathVariable Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        SchoolsUniversity schoolsUniversity = universityCollegeMajorService.selectUniversityById(id);
        return Result.success(schoolsUniversity);
    }

    /**
     * 根据 ID 查询学院信息。
     *
     * @param id 要查询的学院的 ID。
     * @return 若查询成功，返回包含学院信息的 Result 对象；若 ID 为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "根据 ID 查询学院", description = "对学院进行 id 查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询学院的 id", required = true)
    })
    @GetMapping("/college/selectById/{id}")
    public Result selectCollegeById(@PathVariable Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        SchoolsColleges schoolsColleges = universityCollegeMajorService.selectCollegeById(id);
        return Result.success(schoolsColleges);
    }

    /**
     * 根据 ID 查询专业信息。
     *
     * @param id 要查询的专业的 ID。
     * @return 若查询成功，返回包含专业信息的 Result 对象；若 ID 为空，返回包含错误信息的 Result 对象。
     */
    @Operation(summary = "根据 ID 查询专业", description = "对专业进行 id 查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询专业的 id", required = true)
    })
    @GetMapping("/major/selectById/{id}")
    public Result selectMajorById(@PathVariable Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        SchoolsMajor schoolsMajor = universityCollegeMajorService.selectMajorById(id);
        return Result.success(schoolsMajor);
    }

    /**
     * 根据条件查询所有学校信息。
     *
     * @param schoolsUniversity 包含查询条件的学校信息对象。
     * @return 返回包含查询到的学校信息列表的 Result 对象。
     */
    @Operation(summary = "查询所有学校", description = "对学校进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "schoolsUniversity", description = "要查询学校的条件", required = true)
    })
    @GetMapping("/university/selectAll")
    public Result selectUniversity(SchoolsUniversity schoolsUniversity) {
        List<SchoolsUniversity> list = universityCollegeMajorService.selectUniversity(schoolsUniversity);
        return Result.success(list);
    }

    /**
     * 根据条件查询所有学院信息。
     *
     * @param schoolsColleges 包含查询条件的学院信息对象。
     * @return 返回包含查询到的学院信息列表的 Result 对象。
     */
    @Operation(summary = "查询所有学院", description = "对学院进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "schoolsColleges", description = "要查询学院的条件", required = true)
    })
    @GetMapping("/college/selectAll")
    public Result selectColleges(SchoolsColleges schoolsColleges) {
        List<SchoolsColleges> list = universityCollegeMajorService.selectColleges(schoolsColleges);
        return Result.success(list);
    }

    /**
     * 根据条件查询所有专业信息。
     *
     * @param schoolsMajor 包含查询条件的专业信息对象。
     * @return 返回包含查询到的专业信息列表的 Result 对象。
     */
    @Operation(summary = "查询所有专业", description = "对专业进行条件查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "schoolsMajor", description = "要查询专业的条件", required = true)
    })
    @GetMapping("/major/selectAll")
    public Result selectMajor(SchoolsMajor schoolsMajor) {
        List<SchoolsMajor> list = universityCollegeMajorService.selectMajor(schoolsMajor);
        return Result.success(list);
    }

    /**
     * 分页查询学校信息。
     *
     * @param params 包含分页查询条件的对象。
     * @return 返回包含分页信息和查询到的学校信息的 Result 对象。
     */
    @Operation(summary = "分页查询学校", description = "对学校进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询学校的分页条件", required = true)
    })
    @GetMapping("/university/selectPage")
    public Result selectUniversityPage(Params params) {
        PageInfo<SchoolsUniversity> page = universityCollegeMajorService.selectUniversityPage(params);
        return Result.success(page);
    }

    /**
     * 分页查询学院信息。
     *
     * @param params 包含分页查询条件的对象。
     * @return 返回包含分页信息和查询到的学院信息的 Result 对象。
     */
    @Operation(summary = "分页查询学院", description = "对学院进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询学院的分页条件", required = true)
    })
    @GetMapping("/college/selectPage")
    public Result selectCollegesPage(Params params) {
        PageInfo<SchoolsColleges> page = universityCollegeMajorService.selectCollegesPage(params);
        return Result.success(page);
    }

    /**
     * 分页查询专业信息。
     *
     * @param params 包含分页查询条件的对象。
     * @return 返回包含分页信息和查询到的专业信息的 Result 对象。
     */
    @Operation(summary = "分页查询专业", description = "对专业进行分页查询")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "params", description = "要查询专业的分页条件", required = true)
    })
    @GetMapping("/major/selectPage")
    public Result selectMajorPage(Params params) {
        PageInfo<SchoolsMajor> page = universityCollegeMajorService.selectMajorPage(params);
        return Result.success(page);
    }
}