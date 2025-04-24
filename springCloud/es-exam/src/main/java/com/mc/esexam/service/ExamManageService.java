package com.mc.esexam.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.exam.ExamManage;
import com.mc.escommon.entity.exam.Params;
import com.mc.escommon.entity.user.Teacher;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/3/1 0:31
 * @inform 考试管理服务接口
 */
public interface ExamManageService {

    /**
     * 新增考试管理信息
     * @param examManage 考试管理实体对象
     */
    void add(ExamManage examManage);

    /**
     * 根据 ID 删除考试管理信息
     * @param id 考试管理记录的 ID
     */
    void deleteById(Integer id);

    /**
     * 批量删除考试管理信息
     * @param ids 考试管理记录的 ID 列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据 ID 更新考试管理信息
     * @param examManage 包含要更新信息的考试管理实体对象
     */
    void updateById(ExamManage examManage);

    /**
     * 根据 ID 查询考试管理信息
     * @param id 考试管理记录的 ID
     * @return 对应的考试管理实体对象，若未找到则返回 null
     */
    ExamManage selectById(Integer id);

    /**
     * 查询所有考试管理信息
     * @param examManage 包含查询条件的考试管理实体对象
     * @return 符合条件的考试管理信息列表
     */
    List<ExamManage> selectAll(ExamManage examManage);

    /**
     * 分页查询考试管理信息
     * @param params 包含分页和查询条件的参数对象
     * @return 分页后的考试管理信息列表
     */
    PageInfo<ExamManage> selectPage(Params params);

    /**
     * 根据 ID 查询考试管理信息环公钥
     * @param id 考试管理记录的 ID
     */
    List<Teacher> getPublicKeys(Integer id);
}