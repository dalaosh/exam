package com.mc.estrustSeal.service.impl;

import com.mc.escommon.entity.statistic.AddressAreas;
import com.mc.escommon.entity.statistic.AddressCities;
import com.mc.escommon.entity.statistic.AddressProvinces;
import com.mc.estrustSeal.mapper.AddressMapper;
import com.mc.estrustSeal.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author machao
 * *@datetime 2025/2/10 14:03
 * *@inform 该类是 AddressService 接口的实现类，负责具体实现与地址信息（省、市、县）查询相关的业务逻辑。
 * 它通过依赖注入的方式获取 AddressMapper 对象，进而调用数据访问层的方法来查询地址数据。
 */
@Service
public class AddressServiceImpl implements AddressService {

    // 通过依赖注入获取 AddressMapper 对象，用于执行数据库的地址信息查询操作
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询省的信息
     *
     * @param addressProvinces 包含查询条件的省信息对象，可为空表示查询所有省的信息。
     *                         若不为空，则根据对象中的属性（如省名称等）进行条件查询。
     * @return 返回一个包含符合条件的省信息的列表。若没有符合条件的记录，返回一个空列表。
     * 列表中的每个元素都是一个 AddressProvinces 对象，包含了省的相关属性信息。
     */
    @Override
    public List<AddressProvinces> selectProvinces(AddressProvinces addressProvinces) {
        // 调用 AddressMapper 的 selectProvinces 方法，传入查询条件对象，获取省信息列表
        List<AddressProvinces> addressProvincesList = addressMapper.selectProvinces(addressProvinces);
        return addressProvincesList;
    }

    /**
     * 查询市的信息
     *
     * @param addressCities 包含查询条件的市信息对象，可为空表示查询所有市的信息。
     *                      若不为空，则根据对象中的属性（如市名称、所属省 ID 等）进行条件查询。
     * @return 返回一个包含符合条件的市信息的列表。若没有符合条件的记录，返回一个空列表。
     * 列表中的每个元素都是一个 AddressCities 对象，包含了市的相关属性信息。
     */
    @Override
    public List<AddressCities> selectCities(AddressCities addressCities) {
        // 调用 AddressMapper 的 selectCities 方法，传入查询条件对象，获取市信息列表
        List<AddressCities> addressCitiesList = addressMapper.selectCities(addressCities);
        return addressCitiesList;
    }

    /**
     * 查询县（区）的信息
     *
     * @param addressAreas 包含查询条件的县（区）信息对象，可为空表示查询所有县（区）的信息。
     *                     若不为空，则根据对象中的属性（如县（区）名称、所属市 ID 等）进行条件查询。
     * @return 返回一个包含符合条件的县（区）信息的列表。若没有符合条件的记录，返回一个空列表。
     * 列表中的每个元素都是一个 AddressAreas 对象，包含了县（区）的相关属性信息。
     */
    @Override
    public List<AddressAreas> selectAreas(AddressAreas addressAreas) {
        // 调用 AddressMapper 的 selectAreas 方法，传入查询条件对象，获取县（区）信息列表
        List<AddressAreas> addressAreasList = addressMapper.selectAreas(addressAreas);
        return addressAreasList;
    }
}