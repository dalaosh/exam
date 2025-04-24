package com.mc.esuser.mapper;

import com.mc.escommon.entity.user.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/1/5 21:45
 * * inform
 */

@Mapper
public interface TeacherMapper {
    /**
     * 插入操作
     * @param teacher ac教师数据
     * @return 返回是否插入成功
     */
    int insert(Teacher teacher);

    /**
     * 根据id单个删除
     * @param id 教师id
     * @return 返回是否删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据id多个删除
     * @param ids 教师id数组
     * @return 返回是否删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param teacher 查询条件
     * @return 返回多个教师成员
     */
    List<Teacher> selectAll(Teacher teacher);

    /**
     * 根据id单个查询
     * @param id 教师id
     * @return 返回教师全部信息
     */
    Teacher selectById(Integer id);

    /**
     * 修改教师数据
     * @param teacher 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(Teacher teacher);

    Teacher findByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    Teacher selectByAccountAndPhone(@Param("account") String account,@Param("phone") String phone);

    Teacher findByAccountAndSM3(@Param("account") String account,@Param("keySm3") String keySm3);
}
