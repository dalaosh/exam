package com.mc.esuser.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.user.CaInstitution;
import com.mc.escommon.entity.user.Params;

import java.util.List;

public interface CaInstitutionService {
    /**
     * 新增
     */
    void add(CaInstitution caInstitution);

    /**
     * 删除
     */
    void deleteById(Integer id);

    /**
     * 批量删除
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 修改
     */
    void updateById(CaInstitution caInstitution);

    /**
     * 根据ID查询
     */
    CaInstitution selectById(Integer id);

    /**
     * 查询所有
     */
    List<CaInstitution> selectAll(CaInstitution caInstitution);

    /**
     * 分页查询
     */
    PageInfo<CaInstitution> selectPage(Params params);

    /**
     * 登录
     */
    CaInstitution login(CaInstitution caInstitution);

//    /**
//     * 注册
//     */
//    void register(CaInstitution CaInstitution);

    /**
     * 修改密码
     */
    void updatePassword(CaInstitution caInstitution);

    CaInstitution selectByAccountAndPhone(String account, String phone);

    /**
     * 修改密码
     */
    void updatePd(CaInstitution caInstitution);

    /**
     * 修改密钥
     */
    void updateKey(CaInstitution caInstitution);

    /**
     * 修改信息
     */
    void updateInform(CaInstitution caInstitution);
}
