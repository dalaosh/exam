package com.mc.esuser.mapper;


import com.mc.escommon.entity.user.CaInstitution;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author machao
 * * @datetime 2025/1/5 21:44
 * * inform
 */

@Mapper
public interface CaInstitutionMapper {
    /**
     * 插入操作
     * @param caInstitution ca机构数据
     * @return 返回是否插入成功
     */
    int insert(CaInstitution caInstitution);

    /**
     * 根据id单个删除
     * @param id 机构id
     * @return 返回是否删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据id多个删除
     * @param ids 机构id数组
     * @return 返回是否删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param caInstitution 查询条件
     * @return 返回多个机构成员
     */
    List<CaInstitution> selectAll(CaInstitution caInstitution);

    /**
     * 根据id单个查询
     * @param id 机构id
     * @return 返回机构全部信息
     */
    CaInstitution selectById(Integer id);

    /**
     * 修改机构数据
     * @param caInstitution 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(CaInstitution caInstitution);

    CaInstitution findByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    CaInstitution selectByAccountAndPhone(@Param("account") String account, @Param("phone") String phone);
    CaInstitution findByAccountAndSM3(@Param("account") String account, @Param("keySm3") String keySm3);
}
