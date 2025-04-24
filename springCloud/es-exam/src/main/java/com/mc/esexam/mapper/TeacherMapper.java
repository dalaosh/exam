package com.mc.esexam.mapper;

import com.mc.escommon.entity.user.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/3/2 15:24
 * * inform
 */
@Mapper
public interface TeacherMapper {
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
}
