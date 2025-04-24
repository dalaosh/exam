package com.mc.estrustSeal.service;

import com.mc.escommon.entity.statistic.AddressAreas;
import com.mc.escommon.entity.statistic.AddressCities;
import com.mc.escommon.entity.statistic.AddressProvinces;

import java.util.List;

/**
 * @author machao
 * @datetime 2025/2/10 14:03
 * @inform 该接口定义了与地址信息（省、市、县）查询相关的业务方法，用于获取不同行政级别的地址数据。
 * 此接口的实现类应具体实现这些方法，以完成从数据存储源（如数据库）中检索地址信息的功能。
 */
public interface AddressService {

    /**
     * 查询省的信息
     *
     * @param addressProvinces 包含查询条件的省信息对象，可为空表示查询所有省的信息。
     *                         若不为空，则根据对象中的属性（如省名称等）进行条件查询。
     * @return 返回一个包含符合条件的省信息的列表。若没有符合条件的记录，返回一个空列表。
     * 列表中的每个元素都是一个 AddressProvinces 对象，包含了省的相关属性信息。
     */
    List<AddressProvinces> selectProvinces(AddressProvinces addressProvinces);

    /**
     * 查询市的信息
     *
     * @param addressCities 包含查询条件的市信息对象，可为空表示查询所有市的信息。
     *                      若不为空，则根据对象中的属性（如市名称、所属省 ID 等）进行条件查询。
     * @return 返回一个包含符合条件的市信息的列表。若没有符合条件的记录，返回一个空列表。
     * 列表中的每个元素都是一个 AddressCities 对象，包含了市的相关属性信息。
     */
    List<AddressCities> selectCities(AddressCities addressCities);

    /**
     * 查询县（区）的信息
     *
     * @param addressAreas 包含查询条件的县（区）信息对象，可为空表示查询所有县（区）的信息。
     *                     若不为空，则根据对象中的属性（如县（区）名称、所属市 ID 等）进行条件查询。
     * @return 返回一个包含符合条件的县（区）信息的列表。若没有符合条件的记录，返回一个空列表。
     * 列表中的每个元素都是一个 AddressAreas 对象，包含了县（区）的相关属性信息。
     */
    List<AddressAreas> selectAreas(AddressAreas addressAreas);
}