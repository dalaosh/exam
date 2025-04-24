package com.mc.esstatistic.service;

import java.util.Map;

/**
 * 该服务接口用于处理与用户统计相关的业务逻辑，
 * 提供了查询用户统计信息的方法。
 *
 * @author machao
 * *@datetime 2025/3/16 16:23
 */
public interface SelectUserCountService {
    /**
     * 查询用户统计信息。
     * 此方法会返回一个包含用户统计数据的 Map，
     * 其中键为统计项的名称，值为对应的统计数值。
     *
     * @return 包含用户统计信息的 Map，键为统计项名称，值为对应统计数值
     * @throws RuntimeException 如果在查询过程中出现异常，抛出此异常
     */
    Map<String, Integer> selectUserCount();

    Map<String, Map<String, Integer>> selectUserLogin();
}