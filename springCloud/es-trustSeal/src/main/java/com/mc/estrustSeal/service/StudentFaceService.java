package com.mc.estrustSeal.service;

import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.dto.FaceUserInfo;
import com.mc.escommon.entity.dto.ProcessInfo;
import com.mc.escommon.entity.statistic.StudentFace;
import com.mc.escommon.entity.statistic.AddressAreas;
import com.mc.escommon.entity.statistic.AddressCities;
import com.mc.escommon.entity.statistic.AddressProvinces;
import com.mc.escommon.entity.statistic.SchoolsColleges;
import com.mc.escommon.entity.statistic.SchoolsMajor;
import com.mc.escommon.entity.statistic.SchoolsUniversity;
import com.mc.escommon.entity.user.Student;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 该接口定义了与学生人脸认证信息相关的服务方法，包括增删改查操作、人脸检测、处理、特征提取、比对等功能。
 */
public interface StudentFaceService {

    /**
     * 新增人脸认证信息
     *
     * @param studentFace 包含要新增的人脸认证信息的对象，包含了学生人脸的相关属性，如人脸特征、关联的用户信息等。
     */
    void add(StudentFace studentFace);

    /**
     * 根据id删除人脸认证信息
     *
     * @param id 要删除的人脸认证信息的唯一标识。
     */
    void deleteById(Integer id);

    /**
     * 批量删除人脸认证信息
     *
     * @param ids 要删除的人脸认证信息的ID列表，包含多个要删除记录的唯一标识。
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改人脸认证信息
     *
     * @param studentFace 包含要修改的人脸认证信息的对象，该对象的ID属性应指向要修改的记录，其他属性为要更新的值。
     */
    void updateById(StudentFace studentFace);

    /**
     * 根据ID查询人脸认证信息
     *
     * @param id 要查询的人脸认证信息的唯一标识。
     * @return 返回包含指定ID的人脸认证信息的对象，如果未找到则返回null。
     */
    StudentFace selectById(Integer id);

    /**
     * 查询所有符合条件的人脸认证信息
     *
     * @param studentFace 包含查询条件的人脸认证信息对象，根据该对象的属性值进行条件查询，可为空表示查询所有记录。
     * @return 返回符合条件的人脸认证信息列表，如果没有符合条件的记录则返回空列表。
     */
    List<StudentFace> selectAll(StudentFace studentFace);

    /**
     * 分页查询人脸认证信息
     *
     * @param pageNum 当前页码，从1开始。
     * @param pageSize 每页显示的记录数。
     * @param studentFace 包含查询条件的人脸认证信息对象，根据该对象的属性值进行条件查询，可为空表示查询所有记录。
     * @return 返回包含分页信息和查询结果的PageInfo对象，其中包含了当前页的记录列表、总记录数、总页数等信息。
     */
    PageInfo<StudentFace> selectPage(int pageNum, int pageSize, StudentFace studentFace);

    /**
     * 检测图像中的人脸
     *
     * @param imageInfo 包含图像信息的对象，如图像的像素数据、宽度、高度等。
     * @return 返回检测到的人脸信息列表，每个 FaceInfo 对象包含了人脸的位置、角度等信息。
     */
    List<FaceInfo> detectFaces(ImageInfo imageInfo);

    /**
     * 对图像中的人脸进行处理
     *
     * @param imageInfo 包含图像信息的对象，如图像的像素数据、宽度、高度等。
     * @return 返回处理后的人脸信息列表，每个 ProcessInfo 对象包含了处理后的人脸相关信息。
     */
    List<ProcessInfo> process(ImageInfo imageInfo);

    /**
     * 提取图像中人脸的特征
     *
     * @param imageInfo 包含图像信息的对象，如图像的像素数据、宽度、高度等。
     * @return 返回提取到的人脸特征的字节数组，如果未检测到人脸或提取失败则返回null。
     * @throws InterruptedException 如果在提取过程中线程被中断。
     */
    byte[] extractFaceFeature(ImageInfo imageInfo) throws InterruptedException;

    /**
     * 进行人脸比对
     *
     * @param faceFeature 要比对的人脸特征的字节数组。
     * @return 返回与输入人脸特征比对后匹配的人脸用户信息列表，每个 FaceUserInfo 对象包含了匹配用户的相关信息。
     * @throws InterruptedException 如果在比对过程中线程被中断。
     * @throws ExecutionException 如果在异步执行比对任务时发生异常。
     */
    List<FaceUserInfo> compareFaceFeature(byte[] faceFeature) throws InterruptedException, ExecutionException;

    /**
     * 根据用户ID查询人脸认证信息
     *
     * @param userId 关联的用户的唯一标识。
     * @return 返回包含指定用户ID的人脸认证信息的对象，如果未找到则返回null。
     */
    StudentFace selectByUserId(Integer userId);

    /**
     * 批量更新人脸认证信息
     *
     * @param ids 要更新的人脸认证信息的ID列表，包含多个要更新记录的唯一标识。
     * @param submit 更新的提交信息，具体含义根据业务需求而定。
     */
    void updateBatch(List<Integer> ids, String submit);

    /**
     * 根据人脸ID查询人脸认证信息
     *
     * @param faceId 人脸的唯一标识。
     * @return 返回包含指定人脸ID的人脸认证信息的对象，如果未找到则返回null。
     */
    StudentFace selectByFaceId(String faceId);
}