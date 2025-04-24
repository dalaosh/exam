package com.mc.estrustSeal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.statistic.SchoolsColleges;
import com.mc.escommon.entity.statistic.SchoolsMajor;
import com.mc.escommon.entity.statistic.SchoolsUniversity;
import com.mc.escommon.entity.statistic.Params; // 假设存在 Params 类用于分页参数
import com.mc.estrustSeal.mapper.UniversityCollegeMajorMapper;
import com.mc.estrustSeal.service.UniversityCollegeMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author machao
 * @datetime 2025/2/16 20:02
 * @inform 该类是学校、学院和专业相关业务逻辑的实现类，实现了 UniversityCollegeMajorService 接口中定义的各种方法。
 * 这些方法主要用于与数据库进行交互，实现对学校、学院和专业信息的增删改查以及分页查询功能。
 */
@Service
public class UniversityCollegeMajorServiceImpl implements UniversityCollegeMajorService {

    // 自动注入 UniversityCollegeMajorMapper，用于与数据库进行交互
    @Autowired
    private UniversityCollegeMajorMapper universityCollegeMajorMapper;

    /**
     * 新增学校信息
     *
     * @param schoolsUniversity 包含要新增的学校信息的实体对象，该对象包含了学校的各种属性，如学校名称、地址等。
     *                          此方法会调用 UniversityCollegeMajorMapper 的 insertUniversity 方法，将该对象的信息持久化到数据库中。
     */
    @Override
    public void insertUniversity(SchoolsUniversity schoolsUniversity) {
        universityCollegeMajorMapper.insertUniversity(schoolsUniversity);
    }

    /**
     * 新增学院信息
     *
     * @param schoolsColleges 包含要新增的学院信息的实体对象，该对象包含了学院的各种属性，如学院名称、所属学校等。
     *                        此方法会调用 UniversityCollegeMajorMapper 的 insertCollege 方法，将该对象的信息持久化到数据库中。
     */
    @Override
    public void insertCollege(SchoolsColleges schoolsColleges) {
        universityCollegeMajorMapper.insertCollege(schoolsColleges);
    }

    /**
     * 新增专业信息
     *
     * @param schoolsMajor 包含要新增的专业信息的实体对象，该对象包含了专业的各种属性，如专业名称、所属学院等。
     *                     此方法会调用 UniversityCollegeMajorMapper 的 insertMajor 方法，将该对象的信息持久化到数据库中。
     */
    @Override
    public void insertMajor(SchoolsMajor schoolsMajor) {
        universityCollegeMajorMapper.insertMajor(schoolsMajor);
    }

    /**
     * 根据学校 ID 删除学校信息
     *
     * @param id 要删除的学校的唯一标识 ID。此方法会调用 UniversityCollegeMajorMapper 的 deleteUniversityById 方法，
     *           根据该 ID 从数据库中查找对应的学校记录，并将其删除。
     */
    @Override
    public void deleteUniversityById(Integer id) {
        universityCollegeMajorMapper.deleteUniversityById(id);
    }

    /**
     * 批量删除学校信息
     *
     * @param ids 包含要删除的学校 ID 的数组。此方法会调用 UniversityCollegeMajorMapper 的 deleteUniversityByIds 方法，
     *            根据数组中的每个 ID 从数据库中查找对应的学校记录，并将这些记录批量删除。
     */
    @Override
    public void deleteUniversityByIds(Integer[] ids) {
        universityCollegeMajorMapper.deleteUniversityByIds(ids);
    }

    /**
     * 根据学院 ID 删除学院信息
     *
     * @param id 要删除的学院的唯一标识 ID。此方法会调用 UniversityCollegeMajorMapper 的 deleteCollegeById 方法，
     *           根据该 ID 从数据库中查找对应的学院记录，并将其删除。
     */
    @Override
    public void deleteCollegeById(Integer id) {
        universityCollegeMajorMapper.deleteCollegeById(id);
    }

    /**
     * 批量删除学院信息
     *
     * @param ids 包含要删除的学院 ID 的数组。此方法会调用 UniversityCollegeMajorMapper 的 deleteCollegesByIds 方法，
     *            根据数组中的每个 ID 从数据库中查找对应的学院记录，并将这些记录批量删除。
     */
    @Override
    public void deleteCollegesByIds(Integer[] ids) {
        universityCollegeMajorMapper.deleteCollegesByIds(ids);
    }

    /**
     * 根据专业 ID 删除专业信息
     *
     * @param id 要删除的专业的唯一标识 ID。此方法会调用 UniversityCollegeMajorMapper 的 deleteMajorById 方法，
     *           根据该 ID 从数据库中查找对应的专业记录，并将其删除。
     */
    @Override
    public void deleteMajorById(Integer id) {
        universityCollegeMajorMapper.deleteMajorById(id);
    }

    /**
     * 批量删除专业信息
     *
     * @param ids 包含要删除的专业 ID 的数组。此方法会调用 UniversityCollegeMajorMapper 的 deleteMajorByIds 方法，
     *            根据数组中的每个 ID 从数据库中查找对应的专业记录，并将这些记录批量删除。
     */
    @Override
    public void deleteMajorByIds(Integer[] ids) {
        universityCollegeMajorMapper.deleteMajorByIds(ids);
    }

    /**
     * 根据学校 ID 修改学校信息
     *
     * @param schoolsUniversity 包含要修改的学校信息的实体对象，该对象的 ID 属性应指向要修改的学校记录，其他属性为要更新的值。
     *                          此方法会调用 UniversityCollegeMajorMapper 的 updateUniversityById 方法，
     *                          根据该对象的 ID 从数据库中查找对应的学校记录，并将该对象的其他属性值更新到该记录中。
     */
    @Override
    public void updateUniversityById(SchoolsUniversity schoolsUniversity) {
        universityCollegeMajorMapper.updateUniversityById(schoolsUniversity);
    }

    /**
     * 根据学院 ID 修改学院信息
     *
     * @param schoolsColleges 包含要修改的学院信息的实体对象，该对象的 ID 属性应指向要修改的学院记录，其他属性为要更新的值。
     *                        此方法会调用 UniversityCollegeMajorMapper 的 updateCollegeById 方法，
     *                        根据该对象的 ID 从数据库中查找对应的学院记录，并将该对象的其他属性值更新到该记录中。
     */
    @Override
    public void updateCollegeById(SchoolsColleges schoolsColleges) {
        universityCollegeMajorMapper.updateCollegeById(schoolsColleges);
    }

    /**
     * 根据专业 ID 修改专业信息
     *
     * @param schoolsMajor 包含要修改的专业信息的实体对象，该对象的 ID 属性应指向要修改的专业记录，其他属性为要更新的值。
     *                     此方法会调用 UniversityCollegeMajorMapper 的 updateMajorById 方法，
     *                     根据该对象的 ID 从数据库中查找对应的专业记录，并将该对象的其他属性值更新到该记录中。
     */
    @Override
    public void updateMajorById(SchoolsMajor schoolsMajor) {
        universityCollegeMajorMapper.updateMajorById(schoolsMajor);
    }

    /**
     * 根据学校 ID 查询学校信息
     *
     * @param id 要查询的学校的唯一标识 ID。此方法会调用 UniversityCollegeMajorMapper 的 selectUniversityById 方法，
     *           根据该 ID 从数据库中查找对应的学校记录，并将该记录封装为学校实体对象返回。
     * @return 返回包含指定 ID 的学校信息的实体对象，如果未找到对应记录，则返回 null。
     */
    @Override
    public SchoolsUniversity selectUniversityById(Integer id) {
        return universityCollegeMajorMapper.selectUniversityById(id);
    }

    /**
     * 根据学院 ID 查询学院信息
     *
     * @param id 要查询的学院的唯一标识 ID。此方法会调用 UniversityCollegeMajorMapper 的 selectCollegeById 方法，
     *           根据该 ID 从数据库中查找对应的学院记录，并将该记录封装为学院实体对象返回。
     * @return 返回包含指定 ID 的学院信息的实体对象，如果未找到对应记录，则返回 null。
     */
    @Override
    public SchoolsColleges selectCollegeById(Integer id) {
        return universityCollegeMajorMapper.selectCollegeById(id);
    }

    /**
     * 根据专业 ID 查询专业信息
     *
     * @param id 要查询的专业的唯一标识 ID。此方法会调用 UniversityCollegeMajorMapper 的 selectMajorById 方法，
     *           根据该 ID 从数据库中查找对应的专业记录，并将该记录封装为专业实体对象返回。
     * @return 返回包含指定 ID 的专业信息的实体对象，如果未找到对应记录，则返回 null。
     */
    @Override
    public SchoolsMajor selectMajorById(Integer id) {
        return universityCollegeMajorMapper.selectMajorById(id);
    }

    /**
     * 查询所有符合条件的学校信息
     *
     * @param schoolsUniversity 包含查询条件的学校信息实体对象，根据该对象的属性值进行条件查询，可为空表示查询所有学校记录。
     *                          此方法会调用 UniversityCollegeMajorMapper 的 selectUniversity 方法，
     *                          根据该对象的属性值从数据库中筛选出符合条件的学校记录，并将这些记录封装为学校实体对象列表返回。
     * @return 返回符合条件的学校信息实体对象列表，如果没有符合条件的记录，则返回空列表。
     */
    @Override
    public List<SchoolsUniversity> selectUniversity(SchoolsUniversity schoolsUniversity) {
        return universityCollegeMajorMapper.selectUniversity(schoolsUniversity);
    }

    /**
     * 查询所有符合条件的学院信息
     *
     * @param schoolsColleges 包含查询条件的学院信息实体对象，根据该对象的属性值进行条件查询，可为空表示查询所有学院记录。
     *                        此方法会调用 UniversityCollegeMajorMapper 的 selectColleges 方法，
     *                        根据该对象的属性值从数据库中筛选出符合条件的学院记录，并将这些记录封装为学院实体对象列表返回。
     * @return 返回符合条件的学院信息实体对象列表，如果没有符合条件的记录，则返回空列表。
     */
    @Override
    public List<SchoolsColleges> selectColleges(SchoolsColleges schoolsColleges) {
        return universityCollegeMajorMapper.selectColleges(schoolsColleges);
    }

    /**
     * 查询所有符合条件的专业信息
     *
     * @param schoolsMajor 包含查询条件的专业信息实体对象，根据该对象的属性值进行条件查询，可为空表示查询所有专业记录。
     *                     此方法会调用 UniversityCollegeMajorMapper 的 selectMajor 方法，
     *                     根据该对象的属性值从数据库中筛选出符合条件的专业记录，并将这些记录封装为专业实体对象列表返回。
     * @return 返回符合条件的专业信息实体对象列表，如果没有符合条件的记录，则返回空列表。
     */
    @Override
    public List<SchoolsMajor> selectMajor(SchoolsMajor schoolsMajor) {
        return universityCollegeMajorMapper.selectMajor(schoolsMajor);
    }

    /**
     * 分页查询学校信息
     *
     * @param params 包含分页及查询参数的实体对象，该对象包含了当前页码、每页显示记录数以及其他查询条件。
     *               此方法会根据该对象的查询条件构建 SchoolsUniversity 对象，设置分页参数（若为空则设置默认值），
     *               然后调用 UniversityCollegeMajorMapper 的 selectUniversity 方法进行分页查询，
     *               最后将查询结果封装为 PageInfo 对象返回。
     * @return 返回包含分页信息和查询结果的 PageInfo 对象，其中包含了当前页的学校记录列表、总记录数、总页数等信息。
     */
    @Override
    public PageInfo<SchoolsUniversity> selectUniversityPage(Params params) {
        // 创建学校信息实体对象，用于存储查询条件
        SchoolsUniversity schoolsUniversity = new SchoolsUniversity();
        // 根据 Params 对象的属性设置学校信息实体对象的属性
        if (params.getId() != null) {
            schoolsUniversity.setId(params.getId());
        }
        if (params.getProvinceId() != null) {
            schoolsUniversity.setProvinceId(params.getProvinceId());
        }
        if (params.getCityId() != null) {
            schoolsUniversity.setCityId(params.getCityId());
        }
        if (params.getAreaId() != null) {
            schoolsUniversity.setAreaId(params.getAreaId());
        }
        if (params.getUniversityName() != null && !Objects.equals(params.getUniversityName(), "")) {
            schoolsUniversity.setUniversityName(params.getUniversityName());
        }

        // 若分页参数为空，则设置默认值
        if (params.getPageNum() == null || params.getPageSize() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 调用 mapper 方法进行查询
        List<SchoolsUniversity> list = universityCollegeMajorMapper.selectUniversity(schoolsUniversity);
        // 将查询结果封装为 PageInfo 对象返回
        return PageInfo.of(list);
    }

    /**
     * 分页查询学院信息
     *
     * @param params 包含分页及查询参数的实体对象，该对象包含了当前页码、每页显示记录数以及其他查询条件。
     *               此方法会根据该对象的查询条件构建 SchoolsColleges 对象，设置分页参数（若为空则设置默认值），
     *               然后调用 UniversityCollegeMajorMapper 的 selectColleges 方法进行分页查询，
     *               最后将查询结果封装为 PageInfo 对象返回。
     * @return 返回包含分页信息和查询结果的 PageInfo 对象，其中包含了当前页的学院记录列表、总记录数、总页数等信息。
     */
    @Override
    public PageInfo<SchoolsColleges> selectCollegesPage(Params params) {
        // 创建学院信息实体对象，用于存储查询条件
        SchoolsColleges schoolsColleges = new SchoolsColleges();
        // 根据 Params 对象的属性设置学院信息实体对象的属性
        if (params.getId() != null) {
            schoolsColleges.setId(params.getId());
        }
        if (params.getUniversityId() != null) {
            schoolsColleges.setUniversityId(params.getUniversityId());
        }
        if (params.getProvinceId() != null) {
            schoolsColleges.setProvinceId(params.getProvinceId());
        }
        if (params.getCityId() != null) {
            schoolsColleges.setCityId(params.getCityId());
        }
        if (params.getAreaId() != null) {
            schoolsColleges.setAreaId(params.getAreaId());
        }
        if (params.getCollegesName() != null && !Objects.equals(params.getCollegesName(), "")) {
            schoolsColleges.setCollegesName(params.getCollegesName());
        }
        // 若分页参数为空，则设置默认值
        if (params.getPageNum() == null || params.getPageSize() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 调用 mapper 方法进行查询
        List<SchoolsColleges> list = universityCollegeMajorMapper.selectColleges(schoolsColleges);
        // 将查询结果封装为 PageInfo 对象返回
        return PageInfo.of(list);
    }

    /**
     * 分页查询专业信息
     *
     * @param params 包含分页及查询参数的实体对象，该对象包含了当前页码、每页显示记录数以及其他查询条件。
     *               此方法会根据该对象的查询条件构建 SchoolsMajor 对象，设置分页参数（若为空则设置默认值），
     *               然后调用 UniversityCollegeMajorMapper 的 selectMajor 方法进行分页查询，
     *               最后将查询结果封装为 PageInfo 对象返回。
     * @return 返回包含分页信息和查询结果的 PageInfo 对象，其中包含了当前页的专业记录列表、总记录数、总页数等信息。
     */
    @Override
    public PageInfo<SchoolsMajor> selectMajorPage(Params params) {
        // 创建专业信息实体对象，用于存储查询条件
        SchoolsMajor schoolsMajor = new SchoolsMajor();
        // 根据 Params 对象的属性设置专业信息实体对象的属性
        if (params.getId() != null) {
            schoolsMajor.setId(params.getId());
        }
        if (params.getCollegesId() != null) {
            schoolsMajor.setCollegesId(params.getCollegesId());
        }
        if (params.getUniversityId() != null) {
            schoolsMajor.setUniversityId(params.getUniversityId());
        }
        if (params.getProvinceId() != null) {
            schoolsMajor.setProvinceId(params.getProvinceId());
        }
        if (params.getCityId() != null) {
            schoolsMajor.setCityId(params.getCityId());
        }
        if (params.getAreaId() != null) {
            schoolsMajor.setAreaId(params.getAreaId());
        }
        if (params.getMajorName() != null && !Objects.equals(params.getMajorName(), "")) {
            schoolsMajor.setMajorName(params.getMajorName());
        }
        // 若分页参数为空，则设置默认值
        if (params.getPageNum() == null || params.getPageSize() == null) {
            params.setPageNum(1);
            params.setPageSize(5);
        }
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 调用 mapper 方法进行查询
        List<SchoolsMajor> list = universityCollegeMajorMapper.selectMajor(schoolsMajor);
        // 将查询结果封装为 PageInfo 对象返回
        return PageInfo.of(list);
    }
}