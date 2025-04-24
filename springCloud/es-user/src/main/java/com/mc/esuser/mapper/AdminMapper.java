package com.mc.esuser.mapper;

import com.mc.escommon.entity.user.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AdminMapper {
    /**
     * 插入操作
     * @param admin 管理员数据
     * @return 返回是否插入成功
     */
    int insert(Admin admin);

    /**
     * 根据id单个删除
     * @param id 管理员id
     * @return 返回是否删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据id多个删除
     * @param ids 管理员id数组
     * @return 返回是否删除成功
     */
    int deleteByIds(Integer[] ids);

    /**
     * 根据条件查询
     * @param admin 查询条件
     * @return 返回多个管理员
     */
    List<Admin> selectAll(Admin admin);

    /**
     * 根据id单个查询
     * @param id 管理员id
     * @return 返回管理员全部信息
     */
    Admin selectById(Integer id);

    /**
     * 修改管理员数据
     * @param admin 要修改的数据
     * @return 返回是否修改成功
     */
    int updateById(Admin admin);

    Admin findByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    Admin selectByAccountAndPhone(@Param("account") String account,@Param("phone") String phone);

    Admin findByAccountAndSM3(@Param("account") String account,@Param("keySm3") String keySm3);
}
