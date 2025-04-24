package com.mc.esstatistic.mapper;

import com.mc.escommon.entity.info.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 该 Mapper 接口用于统计不同类型用户的数量，提供了查询 CA 机构中心人数、管理员人数、教师人数和学生人数的方法。
 *
 * @author machao
 * @datetime 2025/3/16 16:16
 */
@Mapper
public interface SelectUserCountMapper {

    /**
     * 查询 CA 机构中心的人数。
     * 此方法会执行相应的 SQL 查询语句，从数据库中获取 CA 机构中心的用户数量。
     *
     * @return 返回 CA 机构中心的人数，如果没有记录则返回 0。
     * @throws RuntimeException 当数据库查询出现异常时，会抛出此异常。
     */
    int selectCaNum();

    /**
     * 查询管理员的人数。
     * 该方法会执行对应的 SQL 查询，从数据库里统计管理员的用户数量。
     *
     * @return 返回管理员的人数，如果没有记录则返回 0。
     * @throws RuntimeException 若数据库查询过程中出现异常，将抛出此异常。
     */
    int selectAdmin();

    /**
     * 查询教师的人数。
     * 此方法会在数据库中执行相应的 SQL 查询，以统计教师的用户数量。
     *
     * @return 返回教师的人数，如果没有记录则返回 0。
     * @throws RuntimeException 当数据库查询发生异常时，会抛出该异常。
     */
    int selectTeacher();

    /**
     * 查询学生的人数。
     * 该方法会执行对应的 SQL 查询，从数据库中获取学生的用户数量。
     *
     * @return 返回学生的人数，如果没有记录则返回 0。
     * @throws RuntimeException 若数据库查询出现异常，将抛出此异常。
     */
    int selectStudent();

    /**
     * 查询四个登录
     */
    List<Log> selectUserList(@Param("role") String role);


}