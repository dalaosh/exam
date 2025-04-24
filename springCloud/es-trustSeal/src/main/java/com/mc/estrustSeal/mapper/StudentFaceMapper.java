package com.mc.estrustSeal.mapper;

import com.mc.escommon.entity.statistic.StudentFace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 该 Mapper 接口用于对 StudentFace 实体进行数据库操作，提供了插入、删除、查询和更新等方法。
 *
 * @author machao
 * @datetime 2025/3/16 16:16
 */
@Mapper
public interface StudentFaceMapper {

    /**
     * 向数据库中插入一条学生人脸认证信息记录。
     *
     * @param studentFace 包含要插入的学生人脸认证信息的实体对象，该对象包含了学生人脸的相关属性，如人脸特征、学生姓名等。
     * @return 返回插入操作影响的记录数，若插入成功则返回 1，失败则返回 0。
     */
    int insert(StudentFace studentFace);

    /**
     * 根据给定的学生人脸认证记录的 ID，从数据库中删除对应的记录。
     *
     * @param id 要删除的学生人脸认证记录的唯一标识 ID。
     * @return 返回删除操作影响的记录数，若删除成功则返回 1，未找到对应记录则返回 0。
     */
    int deleteById(Integer id);

    /**
     * 根据给定的学生人脸认证记录的 ID 数组，从数据库中批量删除对应的记录。
     *
     * @param ids 包含要删除的学生人脸认证记录 ID 的数组。
     * @return 返回删除操作影响的记录数，即成功删除的记录数量。
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据给定的查询条件，从数据库中查询符合条件的学生人脸认证信息列表。
     *
     * @param studentFace 包含查询条件的学生人脸认证信息实体对象，可以根据该对象的属性值进行条件查询，
     *                    若该对象为空，则查询所有学生人脸认证信息记录。
     * @return 返回符合查询条件的学生人脸认证信息列表，如果没有符合条件的记录，则返回一个空列表。
     */
    List<StudentFace> selectAll(StudentFace studentFace);

    /**
     * 根据给定的学生人脸认证记录的 ID，从数据库中查询对应的学生人脸认证信息。
     *
     * @param id 要查询的学生人脸认证记录的唯一标识 ID。
     * @return 返回包含指定 ID 的学生人脸认证信息的实体对象，如果未找到对应记录，则返回 null。
     */
    StudentFace selectById(Integer id);

    /**
     * 根据学生人脸认证记录的 ID，修改数据库中对应的学生人脸认证信息。
     *
     * @param studentFace 包含要修改的学生人脸认证信息的实体对象，该对象的 ID 属性应指向要修改的记录，其他属性为要更新的值。
     * @return 返回修改操作影响的记录数，若修改成功则返回 1，未找到对应记录则返回 0。
     */
    int updateById(StudentFace studentFace);

    /**
     * 根据给定的学生用户 ID，从数据库中查询对应的学生人脸认证信息。
     *
     * @param userId 要查询的学生用户的唯一标识 ID。
     * @return 返回包含指定用户 ID 的学生人脸认证信息的实体对象，如果未找到对应记录，则返回 null。
     */
    StudentFace selectByUserId(Integer userId);

    /**
     * 根据给定的学生人脸 ID，从数据库中查询对应的学生人脸认证信息。
     *
     * @param faceId 要查询的学生人脸的唯一标识 ID。
     * @return 返回包含指定人脸 ID 的学生人脸认证信息的实体对象，如果未找到对应记录，则返回 null。
     */
    StudentFace selectByFaceId(@Param("faceId") String faceId);
}