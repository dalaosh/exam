package com.mc.estrustSeal.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.statistic.Params;
import com.mc.escommon.entity.statistic.SchoolsColleges;
import com.mc.escommon.entity.statistic.SchoolsMajor;
import com.mc.escommon.entity.statistic.SchoolsUniversity;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/2/16 20:02
 * @inform 该接口定义了学校、学院和专业相关的业务逻辑方法，涵盖了学校、学院和专业的增删改查操作以及分页查询功能。
 * 这些方法将由具体的服务实现类来完成具体的业务逻辑处理，主要用于与数据库进行交互，实现对学校、学院和专业信息的管理。
 */
public interface UniversityCollegeMajorService {

    /**
     * 新增学校信息
     *
     * @param schoolsUniversity 包含要新增的学校信息的实体对象，该对象包含了学校的各种属性，如学校名称、地址等。
     *                          此方法会将该对象的信息持久化到数据库中，实现学校信息的添加。
     */
    void insertUniversity(SchoolsUniversity schoolsUniversity);

    /**
     * 新增学院信息
     *
     * @param schoolsColleges 包含要新增的学院信息的实体对象，该对象包含了学院的各种属性，如学院名称、所属学校等。
     *                        此方法会将该对象的信息持久化到数据库中，实现学院信息的添加。
     */
    void insertCollege(SchoolsColleges schoolsColleges);

    /**
     * 新增专业信息
     *
     * @param schoolsMajor 包含要新增的专业信息的实体对象，该对象包含了专业的各种属性，如专业名称、所属学院等。
     *                     此方法会将该对象的信息持久化到数据库中，实现专业信息的添加。
     */
    void insertMajor(SchoolsMajor schoolsMajor);

    /**
     * 根据学校 ID 删除学校信息
     *
     * @param id 要删除的学校的唯一标识 ID。此方法会根据该 ID 从数据库中查找对应的学校记录，并将其删除。
     */
    void deleteUniversityById(Integer id);

    /**
     * 批量删除学校信息
     *
     * @param ids 包含要删除的学校 ID 的数组。此方法会根据数组中的每个 ID 从数据库中查找对应的学校记录，并将这些记录批量删除。
     */
    void deleteUniversityByIds(Integer[] ids);

    /**
     * 根据学院 ID 删除学院信息
     *
     * @param id 要删除的学院的唯一标识 ID。此方法会根据该 ID 从数据库中查找对应的学院记录，并将其删除。
     */
    void deleteCollegeById(Integer id);

    /**
     * 批量删除学院信息
     *
     * @param ids 包含要删除的学院 ID 的数组。此方法会根据数组中的每个 ID 从数据库中查找对应的学院记录，并将这些记录批量删除。
     */
    void deleteCollegesByIds(Integer[] ids);

    /**
     * 根据专业 ID 删除专业信息
     *
     * @param id 要删除的专业的唯一标识 ID。此方法会根据该 ID 从数据库中查找对应的专业记录，并将其删除。
     */
    void deleteMajorById(Integer id);

    /**
     * 批量删除专业信息
     *
     * @param ids 包含要删除的专业 ID 的数组。此方法会根据数组中的每个 ID 从数据库中查找对应的专业记录，并将这些记录批量删除。
     */
    void deleteMajorByIds(Integer[] ids);

    /**
     * 根据学校 ID 修改学校信息
     *
     * @param schoolsUniversity 包含要修改的学校信息的实体对象，该对象的 ID 属性应指向要修改的学校记录，其他属性为要更新的值。
     *                          此方法会根据该对象的 ID 从数据库中查找对应的学校记录，并将该对象的其他属性值更新到该记录中。
     */
    void updateUniversityById(SchoolsUniversity schoolsUniversity);

    /**
     * 根据学院 ID 修改学院信息
     *
     * @param schoolsColleges 包含要修改的学院信息的实体对象，该对象的 ID 属性应指向要修改的学院记录，其他属性为要更新的值。
     *                        此方法会根据该对象的 ID 从数据库中查找对应的学院记录，并将该对象的其他属性值更新到该记录中。
     */
    void updateCollegeById(SchoolsColleges schoolsColleges);

    /**
     * 根据专业 ID 修改专业信息
     *
     * @param schoolsMajor 包含要修改的专业信息的实体对象，该对象的 ID 属性应指向要修改的专业记录，其他属性为要更新的值。
     *                     此方法会根据该对象的 ID 从数据库中查找对应的专业记录，并将该对象的其他属性值更新到该记录中。
     */
    void updateMajorById(SchoolsMajor schoolsMajor);

    /**
     * 根据学校 ID 查询学校信息
     *
     * @param id 要查询的学校的唯一标识 ID。此方法会根据该 ID 从数据库中查找对应的学校记录，并将该记录封装为学校实体对象返回。
     * @return 返回包含指定 ID 的学校信息的实体对象，如果未找到对应记录，则返回 null。
     */
    SchoolsUniversity selectUniversityById(Integer id);

    /**
     * 根据学院 ID 查询学院信息
     *
     * @param id 要查询的学院的唯一标识 ID。此方法会根据该 ID 从数据库中查找对应的学院记录，并将该记录封装为学院实体对象返回。
     * @return 返回包含指定 ID 的学院信息的实体对象，如果未找到对应记录，则返回 null。
     */
    SchoolsColleges selectCollegeById(Integer id);

    /**
     * 根据专业 ID 查询专业信息
     *
     * @param id 要查询的专业的唯一标识 ID。此方法会根据该 ID 从数据库中查找对应的专业记录，并将该记录封装为专业实体对象返回。
     * @return 返回包含指定 ID 的专业信息的实体对象，如果未找到对应记录，则返回 null。
     */
    SchoolsMajor selectMajorById(Integer id);

    /**
     * 查询所有符合条件的学校信息
     *
     * @param schoolsUniversity 包含查询条件的学校信息实体对象，根据该对象的属性值进行条件查询，可为空表示查询所有学校记录。
     *                          此方法会根据该对象的属性值从数据库中筛选出符合条件的学校记录，并将这些记录封装为学校实体对象列表返回。
     * @return 返回符合条件的学校信息实体对象列表，如果没有符合条件的记录，则返回空列表。
     */
    List<SchoolsUniversity> selectUniversity(SchoolsUniversity schoolsUniversity);

    /**
     * 查询所有符合条件的学院信息
     *
     * @param schoolsColleges 包含查询条件的学院信息实体对象，根据该对象的属性值进行条件查询，可为空表示查询所有学院记录。
     *                        此方法会根据该对象的属性值从数据库中筛选出符合条件的学院记录，并将这些记录封装为学院实体对象列表返回。
     * @return 返回符合条件的学院信息实体对象列表，如果没有符合条件的记录，则返回空列表。
     */
    List<SchoolsColleges> selectColleges(SchoolsColleges schoolsColleges);

    /**
     * 查询所有符合条件的专业信息
     *
     * @param schoolsMajor 包含查询条件的专业信息实体对象，根据该对象的属性值进行条件查询，可为空表示查询所有专业记录。
     *                     此方法会根据该对象的属性值从数据库中筛选出符合条件的专业记录，并将这些记录封装为专业实体对象列表返回。
     * @return 返回符合条件的专业信息实体对象列表，如果没有符合条件的记录，则返回空列表。
     */
    List<SchoolsMajor> selectMajor(SchoolsMajor schoolsMajor);

    /**
     * 分页查询学校信息
     *
     * @param params 包含分页及查询参数的实体对象，该对象包含了当前页码、每页显示记录数以及其他查询条件。
     *               此方法会根据该对象的分页参数和查询条件从数据库中分页查询符合条件的学校记录，并将这些记录封装为分页信息对象返回。
     * @return 返回包含分页信息和查询结果的 PageInfo 对象，其中包含了当前页的学校记录列表、总记录数、总页数等信息。
     */
    PageInfo<SchoolsUniversity> selectUniversityPage(Params params);

    /**
     * 分页查询学院信息
     *
     * @param params 包含分页及查询参数的实体对象，该对象包含了当前页码、每页显示记录数以及其他查询条件。
     *               此方法会根据该对象的分页参数和查询条件从数据库中分页查询符合条件的学院记录，并将这些记录封装为分页信息对象返回。
     * @return 返回包含分页信息和查询结果的 PageInfo 对象，其中包含了当前页的学院记录列表、总记录数、总页数等信息。
     */
    PageInfo<SchoolsColleges> selectCollegesPage(Params params);

    /**
     * 分页查询专业信息
     *
     * @param params 包含分页及查询参数的实体对象，该对象包含了当前页码、每页显示记录数以及其他查询条件。
     *               此方法会根据该对象的分页参数和查询条件从数据库中分页查询符合条件的专业记录，并将这些记录封装为分页信息对象返回。
     * @return 返回包含分页信息和查询结果的 PageInfo 对象，其中包含了当前页的专业记录列表、总记录数、总页数等信息。
     */
    PageInfo<SchoolsMajor> selectMajorPage(Params params);
}