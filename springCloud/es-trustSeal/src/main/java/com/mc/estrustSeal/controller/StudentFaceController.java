package com.mc.estrustSeal.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.dto.FaceSearchResDto;
import com.mc.escommon.entity.dto.FaceUserInfo;
import com.mc.escommon.entity.dto.ProcessInfo;
import com.mc.escommon.entity.statistic.StudentFace;
import com.mc.escommon.realFile.RealFaceImageUtils;
import com.mc.escommon.result.Result;
import com.mc.escommon.result.ResultCodeEnum;
import com.mc.estrustSeal.service.StudentFaceService;
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
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 人脸认证信息前端操作接口，提供对人脸认证信息的增删改查、人脸添加、识别、检测等功能
 */
@Slf4j
@RestController
@RequestMapping("trustSeal/studentFace")
@Tag(name = "人脸认证接口", description = "关于人脸认证信息的相关操作")
public class StudentFaceController {

    @Autowired
    private StudentFaceService studentFaceService;

    /**
     * 添加人脸认证信息
     *
     * @param studentFace 要添加的人脸认证信息对象，包含人脸相关信息
     * @return 返回操作结果，添加成功返回包含成功信息的 Result 对象
     */
    @Operation(summary = "添加人脸认证信息", description = "添加一条新的人脸认证信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "studentFace", description = "人脸认证信息", required = true)
    })
    @PostMapping("/add")
    public Result add(@RequestBody StudentFace studentFace) {
        studentFaceService.add(studentFace);
        return Result.success();
    }

    /**
     * 删除单个人脸认证信息
     *
     * @param id 要删除的人脸认证信息的唯一标识 ID
     * @return 返回操作结果，删除成功返回包含成功信息的 Result 对象
     */
    @Operation(summary = "删除单个人脸认证信息", description = "根据 ID 删除一条人脸认证信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要删除的人脸认证信息的 ID", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        studentFaceService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除人脸认证信息
     *
     * @param ids 要删除的人脸认证信息的 ID 列表
     * @return 返回操作结果，删除成功返回包含成功信息的 Result 对象
     */
    @Operation(summary = "批量删除人脸认证信息", description = "根据 ID 列表批量删除人脸认证信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "删除成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要删除的人脸认证信息的 ID 列表", required = true)
    })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        studentFaceService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改人脸认证信息
     *
     * @param studentFace 包含修改信息的人脸认证信息对象，需携带要修改的 ID 和新的信息
     * @return 返回操作结果，修改成功返回包含成功信息的 Result 对象
     */
    @Operation(summary = "修改人脸认证信息", description = "根据 ID 修改人脸认证信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "studentFace", description = "包含修改信息的人脸认证信息对象", required = true)
    })
    @PutMapping("/update")
    public Result updateById(@RequestBody StudentFace studentFace) {
        studentFaceService.updateById(studentFace);
        return Result.success();
    }

    /**
     * 批量将人脸认证信息状态修改为同意
     *
     * @param ids 要修改状态的人脸认证信息的 ID 列表
     * @return 返回操作结果，修改成功返回包含成功信息的 Result 对象
     */
    @Operation(summary = "批量将人脸认证信息状态修改为同意", description = "根据 ID 列表批量将人脸认证信息状态修改为同意")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要修改的人脸认证信息的 ID 列表", required = true)
    })
    @PutMapping("/update/batchSuccess")
    public Result updateBatchSuccess(@RequestBody List<Integer> ids) {
        studentFaceService.updateBatch(ids, "同意");
        return Result.success();
    }

    /**
     * 批量将人脸认证信息状态修改为拒绝
     *
     * @param ids 要修改状态的人脸认证信息的 ID 列表
     * @return 返回操作结果，修改成功返回包含成功信息的 Result 对象
     */
    @Operation(summary = "批量将人脸认证信息状态修改为拒绝", description = "根据 ID 列表批量将人脸认证信息状态修改为拒绝")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "修改成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "ids", description = "要修改的人脸认证信息的 ID 列表", required = true)
    })
    @PutMapping("/update/batchError")
    public Result updateBatchError(@RequestBody List<Integer> ids) {
        studentFaceService.updateBatch(ids, "拒绝");
        return Result.success();
    }

    /**
     * 根据 ID 查询人脸认证信息
     *
     * @param id 要查询的人脸认证信息的唯一标识 ID
     * @return 返回操作结果，查询成功返回包含查询到的人脸认证信息的 Result 对象
     */
    @Operation(summary = "根据 ID 查询人脸认证信息", description = "根据 ID 查询一条人脸认证信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "id", description = "要查询的人脸认证信息的 ID", required = true)
    })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        StudentFace studentFace = studentFaceService.selectById(id);
        return Result.success(studentFace);
    }

    /**
     * 根据用户 ID 查询人脸认证信息
     *
     * @param userId 要查询的用户的唯一标识 ID
     * @return 返回操作结果，查询成功返回包含查询到的人脸认证信息的 Result 对象
     */
    @Operation(summary = "根据用户 ID 查询人脸认证信息", description = "根据用户 ID 查询一条人脸认证信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "userId", description = "要查询的用户 ID", required = true)
    })
    @GetMapping("/selectByUserId/{userId}")
    public Result selectByUserId(@PathVariable Integer userId) {
        StudentFace studentFace = studentFaceService.selectByUserId(userId);
        return Result.success(studentFace);
    }

    /**
     * 根据条件查询人脸认证信息
     *
     * @param studentFace 包含查询条件的人脸认证信息对象，根据对象中的属性进行筛选查询
     * @return 返回操作结果，查询成功返回包含查询到的人脸认证信息列表的 Result 对象
     */
    @Operation(summary = "根据条件查询人脸认证信息", description = "根据条件查询人脸认证信息列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "studentFace", description = "查询条件对象", required = true)
    })
    @GetMapping("/selectAll")
    public Result selectAll(StudentFace studentFace) {
        List<StudentFace> list = studentFaceService.selectAll(studentFace);
        return Result.success(list);
    }

    /**
     * 分页查询人脸认证信息
     *
     * @param pageNum  当前页码，从 1 开始计数
     * @param pageSize 每页显示的记录数量
     * @param studentFace 包含查询条件的人脸认证信息对象，根据对象中的属性进行筛选查询
     * @return 返回操作结果，查询成功返回包含分页信息和查询到的人脸认证信息列表的 Result 对象
     */
    @Operation(summary = "分页查询人脸认证信息", description = "分页查询人脸认证信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "pageNum", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页数量", required = true),
            @Parameter(name = "studentFace", description = "查询条件对象", required = true)
    })
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, StudentFace studentFace) {
        PageInfo<StudentFace> page = studentFaceService.selectPage(pageNum, pageSize, studentFace);
        return Result.success(page);
    }

    /**
     * 人脸添加接口
     * 此方法用于处理人脸认证信息的添加操作，包含人脸特征提取、图片保存、属性设置等步骤
     *
     * @param studentFace 包含人脸相关信息的对象，其中的 file 属性应为人脸图片的 Base64 字符串
     * @return 返回操作结果，添加成功返回包含成功信息的 Result 对象；若未检测到人脸返回相应错误信息；若出现未知异常返回未知错误信息
     */
    @Operation(summary = "人脸添加", description = "处理人脸认证信息添加，包括提取人脸特征、保存图片等操作")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "添加成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "studentFace", description = "包含人脸相关信息的对象，包括人脸图片的 Base64 字符串等", required = true)
    })
    @PostMapping(value = "/faceAdd")
    @ResponseBody
    public Result faceAdd(@RequestBody StudentFace studentFace) {
        try {
            byte[] decode = Base64.decode(base64Process(studentFace.getFile()));
            ImageInfo imageInfo = ImageFactory.getRGBData(decode);

            // 人脸特征获取
            byte[] bytes = studentFaceService.extractFaceFeature(imageInfo);
            if (bytes == null) {
                return Result.error(ResultCodeEnum.NO_FACE_DETECTED);
            }
            studentFace.setFaceId(RandomUtil.randomString(30));
            studentFace.setFaceFeature(bytes);
            RealFaceImageUtils imageUtils = new RealFaceImageUtils();
            String imageUrl = imageUtils.saveBase64Image(studentFace.getFile());
            System.out.println(imageUrl);
            studentFace.setFacePath(imageUrl);
            studentFace.setFile(null);
            // 人脸特征插入到数据库
        } catch (Exception e) {
            return Result.error(ResultCodeEnum.UNKNOWN);
        }
        if (studentFace.getId() == 0) {
            studentFaceService.add(studentFace);
        } else {
            studentFaceService.updateById(studentFace);
        }
        return Result.success("");
    }

    /**
     * 人脸识别接口
     * 此方法用于处理人脸识别操作，包含人脸特征提取、特征比对、人脸检测与标注等步骤
     *
     * @param file 包含待识别的人脸图片的 Base64 字符串的对象
     * @return 返回操作结果，识别成功返回包含相关人脸认证信息的 Result 对象；若未检测到人脸返回相应错误信息；若未匹配到人脸返回未匹配错误信息；若出现异常抛出相应异常
     * @throws Exception 处理过程中可能出现的异常
     */
    @Operation(summary = "人脸识别", description = "处理人脸识别，包括提取人脸特征、与数据库中人脸特征比对等操作")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "识别成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "file", description = "包含待识别的人脸图片的 Base64 字符串的对象", required = true)
    })
    @PostMapping(value = "/faceSearch")
    @ResponseBody
    public Result faceSearch(@RequestBody StudentFace file) throws Exception {
        byte[] decode = Base64.decode(base64Process(file.getFile()));
        BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(decode));
        ImageInfo imageInfo = ImageFactory.bufferedImage2ImageInfo(bufImage);

        // 人脸特征获取
        byte[] bytes = studentFaceService.extractFaceFeature(imageInfo);
        if (bytes == null) {
            return Result.error(ResultCodeEnum.NO_FACE_DETECTED);
        }
        // 人脸比对，获取比对结果
        List<FaceUserInfo> userFaceInfoList = studentFaceService.compareFaceFeature(bytes);

        if (CollectionUtil.isNotEmpty(userFaceInfoList)) {
            FaceUserInfo faceUserInfo = userFaceInfoList.get(0);
            FaceSearchResDto faceSearchResDto = new FaceSearchResDto();
            BeanUtil.copyProperties(faceUserInfo, faceSearchResDto);
            List<ProcessInfo> processInfoList = studentFaceService.process(imageInfo);
            if (CollectionUtil.isNotEmpty(processInfoList)) {
                // 人脸检测
                List<FaceInfo> faceInfoList = studentFaceService.detectFaces(imageInfo);
                int left = faceInfoList.get(0).getRect().getLeft();
                int top = faceInfoList.get(0).getRect().getTop();
                int width = faceInfoList.get(0).getRect().getRight() - left;
                int height = faceInfoList.get(0).getRect().getBottom() - top;

                Graphics2D graphics2D = bufImage.createGraphics();
                graphics2D.setColor(Color.RED); // 红色
                BasicStroke stroke = new BasicStroke(5f);
                graphics2D.setStroke(stroke);
                graphics2D.drawRect(left, top, width, height);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufImage, "jpg", outputStream);
                byte[] bytes1 = outputStream.toByteArray();
                faceSearchResDto.setImage("data:image/jpeg;base64," + Base64Utils.encodeToString(bytes1));
                faceSearchResDto.setAge(processInfoList.get(0).getAge());
                faceSearchResDto.setGender(processInfoList.get(0).getGender().equals(1)? "女" : "男");
            }
            StudentFace studentFace = studentFaceService.selectByFaceId(faceSearchResDto.getFaceId());
            studentFace.setSimilarValue(faceSearchResDto.getSimilarValue());
            return Result.success(studentFace);
        }
        return Result.error(ResultCodeEnum.FACE_DOES_NOT_MATCH);
    }

    /**
     * 人脸检测接口
     * 此方法用于根据传入的图片 Base64 字符串进行人脸检测
     *
     * @param image 图片的 Base64 字符串
     * @return 返回检测到的人脸信息列表，若未检测到人脸列表为空
     * @throws IOException 处理图片读取等操作时可能出现的 IO 异常
     */
    @Operation(summary = "人脸检测", description = "根据传入的图片 Base64 字符串进行人脸检测")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "检测成功",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Parameters({
            @Parameter(name = "image", description = "图片的 Base64 字符串", required = true)
    })
    @RequestMapping(value = "/detectFaces", method = RequestMethod.POST)
    @ResponseBody
    public List<FaceInfo> detectFaces(String image) throws IOException {
        byte[] decode = Base64.decode(image);
        InputStream inputStream = new ByteArrayInputStream(decode);
        ImageInfo imageInfo = ImageFactory.getRGBData(inputStream);

        if (inputStream != null) {
            inputStream.close();
        }
        List<FaceInfo> faceInfoList = studentFaceService.detectFaces(imageInfo);

        return faceInfoList;
    }

    /**
     * 处理 Base64 字符串
     * 此方法用于从传入的 Base64 字符串中提取有效的编码部分，去除可能存在的前缀
     *
     * @param base64Str 传入的 Base64 字符串
     * @return 返回处理后的有效 Base64 字符串，若传入字符串为空则返回空字符串
     */
    private String base64Process(String base64Str) {
        if (!StringUtils.isEmpty(base64Str)) {
            String photoBase64 = base64Str.substring(0, 30).toLowerCase();
            int indexOf = photoBase64.indexOf("base64,");
            if (indexOf > 0) {
                base64Str = base64Str.substring(indexOf + 7);
            }
            return base64Str;
        } else {
            return "";
        }
    }
}