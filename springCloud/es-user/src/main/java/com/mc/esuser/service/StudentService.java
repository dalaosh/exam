package com.mc.esuser.service;

import com.github.pagehelper.PageInfo;
import com.mc.escommon.entity.user.Params;
import com.mc.escommon.entity.user.Student;

import java.util.List;

public interface StudentService {
    /**
     * 新增
     */
    void add(Student student);

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
    void updateById(Student student);

    /**
     * 根据ID查询
     */
    Student selectById(Integer id);

    /**
     * 查询所有
     */
    List<Student> selectAll(Student student);

    /**
     * 分页查询
     */
    PageInfo<Student> selectPage(Params params);

    /**
     * 登录
     */
    Student login(Student student);

//    /**
//     * 注册
//     */
//    void register(Student student);

    /**
     * 修改密码
     */
    void updatePassword(Student student);

    /**
     * 根据账号和手机号查询
     */
    Student selectByAccountAndPhone(String account,String phone);

    /**
     * 修改密码
     */
    void updatePd(Student student);

    /**
     * 修改密钥
     */
    void updateKey(Student student);

    /**
     * 修改信息
     */
    void updateInform(Student student);
}
