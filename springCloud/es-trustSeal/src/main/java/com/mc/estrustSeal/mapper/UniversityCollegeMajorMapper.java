package com.mc.estrustSeal.mapper;

import com.mc.escommon.entity.statistic.SchoolsColleges;
import com.mc.escommon.entity.statistic.SchoolsMajor;
import com.mc.escommon.entity.statistic.SchoolsUniversity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/2/16 20:02
 * @inform 该 Mapper 接口用于对学校、学院和专业相关实体进行数据库操作，
 * 提供了插入、删除、查询和更新等方法，方便与数据库进行交互。
 */
@Mapper
public interface UniversityCollegeMajorMapper {

    /**
     * 向数据库中插入一条学校信息记录。
     *
     * @param schoolsUniversity 包含要插入的学校信息的实体对象，该对象包含了学校的各种属性，如学校名称、地址等。
     * @return 返回插入操作影响的记录数，若插入成功则返回 1，失败则返回 0。
     */
    int insertUniversity(SchoolsUniversity schoolsUniversity);

    /**
     * 向数据库中插入一条学院信息记录。
     *
     * @param schoolsColleges 包含要插入的学院信息的实体对象，该对象包含了学院的各种属性，如学院名称、所属学校等。
     * @return 返回插入操作影响的记录数，若插入成功则返回 1，失败则返回 0。
     */
    int insertCollege(SchoolsColleges schoolsColleges);

    /**
     * 向数据库中插入一条专业信息记录。
     *
     * @param schoolsMajor 包含要插入的专业信息的实体对象，该对象包含了专业的各种属性，如专业名称、所属学院等。
     * @return 返回插入操作影响的记录数，若插入成功则返回 1，失败则返回 0。
     */
    int insertMajor(SchoolsMajor schoolsMajor);

    /**
     * 根据给定的学校 ID，从数据库中删除对应的学校信息记录。
     *
     * @param id 要删除的学校的唯一标识 ID。
     * @return 返回删除操作影响的记录数，若删除成功则返回 1，未找到对应记录则返回 0。
     */
    int deleteUniversityById(Integer id);

    /**
     * 根据给定的学校 ID 数组，从数据库中批量删除对应的学校信息记录。
     *
     * @param ids 包含要删除的学校 ID 的数组。
     * @return 返回删除操作影响的记录数，即成功删除的记录数量。
     */
    int deleteUniversityByIds(Integer[] ids);

    /**
     * 根据给定的学院 ID，从数据库中删除对应的学院信息记录。
     *
     * @param id 要删除的学院的唯一标识 ID。
     * @return 返回删除操作影响的记录数，若删除成功则返回 1，未找到对应记录则返回 0。
     */
    int deleteCollegeById(Integer id);

    /**
     * 根据给定的学院 ID 数组，从数据库中批量删除对应的学院信息记录。
     *
     * @param ids 包含要删除的学院 ID 的数组。
     * @return 返回删除操作影响的记录数，即成功删除的记录数量。
     */
    int deleteCollegesByIds(Integer[] ids);

    /**
     * 根据给定的专业 ID，从数据库中删除对应的专业信息记录。
     *
     * @param id 要删除的专业的唯一标识 ID。
     * @return 返回删除操作影响的记录数，若删除成功则返回 1，未找到对应记录则返回 0。
     */
    int deleteMajorById(Integer id);

    /**
     * 根据给定的专业 ID 数组，从数据库中批量删除对应的专业信息记录。
     *
     * @param ids 包含要删除的专业 ID 的数组。
     * @return 返回删除操作影响的记录数，即成功删除的记录数量。
     */
    int deleteMajorByIds(Integer[] ids);

    /**
     * 根据学校 ID，修改数据库中对应的学校信息。
     *
     * @param schoolsUniversity 包含要修改的学校信息的实体对象，该对象的 ID 属性应指向要修改的记录，其他属性为要更新的值。
     * @return 返回修改操作影响的记录数，若修改成功则返回 1，未找到对应记录则返回 0。
     */
    int updateUniversityById(SchoolsUniversity schoolsUniversity);

    /**
     * 根据学院 ID，修改数据库中对应的学院信息。
     *
     * @param schoolsColleges 包含要修改的学院信息的实体对象，该对象的 ID 属性应指向要修改的记录，其他属性为要更新的值。
     * @return 返回修改操作影响的记录数，若修改成功则返回 1，未找到对应记录则返回 0。
     */
    int updateCollegeById(SchoolsColleges schoolsColleges);

    /**
     * 根据专业 ID，修改数据库中对应的专业信息。
     *
     * @param schoolsMajor 包含要修改的专业信息的实体对象，该对象的 ID 属性应指向要修改的记录，其他属性为要更新的值。
     * @return 返回修改操作影响的记录数，若修改成功则返回 1，未找到对应记录则返回 0。
     */
    int updateMajorById(SchoolsMajor schoolsMajor);

    /**
     * 根据给定的学校 ID，从数据库中查询对应的学校信息。
     *
     * @param id 要查询的学校的唯一标识 ID。
     * @return 返回包含指定 ID 的学校信息的实体对象，如果未找到对应记录，则返回 null。
     */
    SchoolsUniversity selectUniversityById(Integer id);

    /**
     * 根据给定的学院 ID，从数据库中查询对应的学院信息。
     *
     * @param id 要查询的学院的唯一标识 ID。
     * @return 返回包含指定 ID 的学院信息的实体对象，如果未找到对应记录，则返回 null。
     */
    SchoolsColleges selectCollegeById(Integer id);

    /**
     * 根据给定的专业 ID，从数据库中查询对应的专业信息。
     *
     * @param id 要查询的专业的唯一标识 ID。
     * @return 返回包含指定 ID 的专业信息的实体对象，如果未找到对应记录，则返回 null。
     */
    SchoolsMajor selectMajorById(Integer id);

    /**
     * 根据给定的查询条件，从数据库中查询所有符合条件的学校信息。
     *
     * @param schoolsUniversity 包含查询条件的学校信息实体对象，可以根据该对象的属性值进行条件查询，
     *                          若该对象为空，则查询所有学校信息记录。
     * @return 返回符合查询条件的学校信息列表，如果没有符合条件的记录，则返回一个空列表。
     */
    List<SchoolsUniversity> selectUniversity(SchoolsUniversity schoolsUniversity);

    /**
     * 根据给定的查询条件，从数据库中查询所有符合条件的学院信息。
     *
     * @param schoolsColleges 包含查询条件的学院信息实体对象，可以根据该对象的属性值进行条件查询，
     *                        若该对象为空，则查询所有学院信息记录。
     * @return 返回符合查询条件的学院信息列表，如果没有符合条件的记录，则返回一个空列表。
     */
    List<SchoolsColleges> selectColleges(SchoolsColleges schoolsColleges);

    /**
     * 根据给定的查询条件，从数据库中查询所有符合条件的专业信息。
     *
     * @param schoolsMajor 包含查询条件的专业信息实体对象，可以根据该对象的属性值进行条件查询，
     *                     若该对象为空，则查询所有专业信息记录。
     * @return 返回符合查询条件的专业信息列表，如果没有符合条件的记录，则返回一个空列表。
     */
    List<SchoolsMajor> selectMajor(SchoolsMajor schoolsMajor);
}