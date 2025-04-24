package com.mc.esuser.mapper;


import com.mc.escommon.entity.user.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/1/5 21:45
 * * inform
 */
@Mapper
public interface StudentMapper {
    /**
     * 插入操作
     * @param student ac学生数据
     * @return 返回是否插入成功
     */
    int insert(Student student);

    /**
     * 根据id单个删除
     * @param id 学生id
     * @return 返回是否删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据id多个删除
     * @param ids 学生id数组
     * @return 返回是否删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param student 查询条件
     * @return 返回多个学生成员
     */
    List<Student> selectAll(Student student);

    /**
     * 根据id单个查询
     * @param id 学生id
     * @return 返回学生全部信息
     */
    Student selectById(Integer id);

    /**
     * 修改学生数据
     * @param student 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(Student student);

    Student findByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    Student selectByAccountAndPhone(@Param("account") String account,@Param("phone") String phone);

    Student findByAccountAndSM3(@Param("account") String account,@Param("keySm3") String keySm3);
}
