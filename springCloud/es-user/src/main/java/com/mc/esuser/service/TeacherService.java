package com.mc.esuser.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.user.Params;

import com.mc.escommon.entity.user.Teacher;

import java.util.List;

public interface TeacherService {
    /**
     * 新增
     */
    void add(Teacher teacher);

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
    void updateById(Teacher teacher);

    /**
     * 根据ID查询
     */
    Teacher selectById(Integer id);

    /**
     * 查询所有
     */
    List<Teacher> selectAll(Teacher teacher);

    /**
     * 分页查询
     */
    PageInfo<Teacher> selectPage(Params params);

    /**
     * 登录
     */
    Teacher login(Teacher teacher);

//    /**
//     * 注册
//     */
//    void register(Teacher teacher);

    /**
     * 修改密码
     */
    void updatePassword(Teacher teacher);

    /**
     * 根据账号和手机号查询
     */
    Teacher selectByAccountAndPhone(String account,String phone);

    /**
     * 修改密码
     */
    void updatePd(Teacher teacher);

    /**
     * 修改密钥
     */
    void updateKey(Teacher teacher);

    /**
     * 修改信息
     */
    void updateInform(Teacher teacher);


}
