package com.mc.esuser.mapper;

import com.mc.escommon.entity.markdown.Markdown;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/1/14 20:57
 * * inform
 */
@Mapper
public interface MarkdownMapper {
    /**
     * 插入操作
     * @param markdown markdown数据
     * @return 返回是否插入成功
     */
    int insert(Markdown markdown);

    /**
     * 根据id单个删除
     * @param id markdownid
     * @return 返回是否删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据id多个删除
     * @param ids markdownid数组
     * @return 返回是否删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param markdown 查询条件
     * @return 返回多个markdown
     */
    List<Markdown> selectAdminAll(Markdown markdown);
    List<Markdown> selectStudentAll(Markdown markdown);
    List<Markdown> selectTeacherAll(Markdown markdown);

    Markdown selectAdminById(Integer id);
    Markdown selectStudentById(Integer id);
    Markdown selectTeacherById(Integer id);
    /**
     * 修改markdown数据
     * @param markdown 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(Markdown markdown);
}
