package com.mc.estrustSeal.mapper;

import com.mc.escommon.entity.statistic.AddressAreas;
import com.mc.escommon.entity.statistic.AddressCities;
import com.mc.escommon.entity.statistic.AddressProvinces;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/2/10 13:46
 * @inform 该接口是一个 MyBatis 映射器接口，用于定义与地址信息相关的数据库查询方法。
 * 它提供了查询省、市、县地址信息的功能，通过 MyBatis 框架与数据库进行交互。
 */
@Mapper
public interface AddressMapper {

    /**
     * 根据给定的查询条件查询省份信息。
     *
     * @param addressProvinces 包含查询条件的省份信息实体对象。可以根据该对象的属性值进行条件查询，
     *                         若该对象为空，则查询所有省份信息。
     * @return 返回符合查询条件的省份信息列表。如果没有符合条件的记录，则返回一个空列表。
     */
    List<AddressProvinces> selectProvinces(AddressProvinces addressProvinces);

    /**
     * 根据给定的查询条件查询城市信息。
     *
     * @param addressCities 包含查询条件的城市信息实体对象。可以根据该对象的属性值进行条件查询，
     *                      若该对象为空，则查询所有城市信息。
     * @return 返回符合查询条件的城市信息列表。如果没有符合条件的记录，则返回一个空列表。
     */
    List<AddressCities> selectCities(AddressCities addressCities);

    /**
     * 根据给定的查询条件查询县区信息。
     *
     * @param addressAreas 包含查询条件的县区信息实体对象。可以根据该对象的属性值进行条件查询，
     *                     若该对象为空，则查询所有县区信息。
     * @return 返回符合查询条件的县区信息列表。如果没有符合条件的记录，则返回一个空列表。
     */
    List<AddressAreas> selectAreas(AddressAreas addressAreas);
}