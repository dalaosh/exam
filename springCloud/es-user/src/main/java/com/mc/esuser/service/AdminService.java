package com.mc.esuser.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.user.Admin;
import com.mc.escommon.entity.user.Params;

import java.util.List;

public interface AdminService {
    /**
     * 新增
     */
    void add(Admin admin);

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
    void updateById(Admin admin);

    /**
     * 根据ID查询
     */
    Admin selectById(Integer id);

    /**
     * 查询所有
     */
    List<Admin> selectAll(Admin admin);

    /**
     * 分页查询
     */
    PageInfo<Admin> selectPage(Params params);

    /**
     * 登录
     */
    Admin login(Admin admin);

//    /**
//     * 注册
//     */
//    void register(Admin admin);

    /**
     * 修改密码
     */
    void updatePassword(Admin admin);

    /**
     * 根据账号和手机号查询
     */
    Admin selectByAccountAndPhone(String account,String phone);

    /**
     * 修改密码
     */
    void updatePd(Admin admin);

    /**
     * 修改密钥
     */
    void updateKey(Admin admin);

    /**
     * 修改信息
     */
    void updateInform(Admin admin);
}
