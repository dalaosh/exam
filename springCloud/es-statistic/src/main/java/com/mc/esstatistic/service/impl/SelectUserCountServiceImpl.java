package com.mc.esstatistic.service.impl;


import com.mc.escommon.entity.info.Log;
import com.mc.esstatistic.mapper.SelectUserCountMapper;
import com.mc.esstatistic.service.SelectUserCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author machao
 * * @datetime 2025/3/16 16:24
 * * inform
 */
@Service
public class SelectUserCountServiceImpl implements SelectUserCountService {
    @Autowired
    private SelectUserCountMapper selectUserCountMapper;
    /**
     * 查询用户统计信息。
     * 此方法会返回一个包含用户统计数据的 Map，
     * 其中键为统计项的名称，值为对应的统计数值。
     *
     * @return 包含用户统计信息的 Map，键为统计项名称，值为对应统计数值
     * @throws RuntimeException 如果在查询过程中出现异常，抛出此异常
     */
    @Override
    public Map<String, Integer> selectUserCount() {
        int caCount=selectUserCountMapper.selectCaNum();
        int adminCount=selectUserCountMapper.selectAdmin();
        int teacherCount=selectUserCountMapper.selectTeacher();
        int studentCount=selectUserCountMapper.selectStudent();
        Map<String, Integer> userNum = new HashMap<>();
        userNum.put("caCount",caCount);
        userNum.put("adminCount",adminCount);
        userNum.put("teacherCount",teacherCount);
        userNum.put("studentCount",studentCount);
        return userNum;
    }

    /**
     * @return
     */
    @Override
    public Map<String, Map<String, Integer>> selectUserLogin() {
        // 获取当前时间
        Date currentDate = new Date();
        // 计算两周前的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.WEEK_OF_YEAR, -2);
        Date twoWeeksAgo = calendar.getTime();

        // 定义日期格式化器
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 查询日志，分别查询
        List<Log> caInstitutionList = selectUserCountMapper.selectUserList("caInstitution");
        List<Log> adminList = selectUserCountMapper.selectUserList("admin");
        List<Log> teacherList = selectUserCountMapper.selectUserList("teacher");
        List<Log> studentList = selectUserCountMapper.selectUserList("student");

        // 分别统计每个角色每天的登录次数
        Map<String, Map<String, Integer>> result = new HashMap<>();

        // 统计 CA 机构中心每天的登录次数
        Map<String, Integer> caInstitutionCountMap = countLoginByDay(caInstitutionList, sdf, twoWeeksAgo, currentDate);
        result.put("caInstitution", caInstitutionCountMap);

        // 统计管理员每天的登录次数
        Map<String, Integer> adminCountMap = countLoginByDay(adminList, sdf, twoWeeksAgo, currentDate);
        result.put("admin", adminCountMap);

        // 统计教师每天的登录次数
        Map<String, Integer> teacherCountMap = countLoginByDay(teacherList, sdf, twoWeeksAgo, currentDate);
        result.put("teacher", teacherCountMap);

        // 统计学生每天的登录次数
        Map<String, Integer> studentCountMap = countLoginByDay(studentList, sdf, twoWeeksAgo, currentDate);
        result.put("student", studentCountMap);

        return result;
    }

    private Map<String, Integer> countLoginByDay(List<Log> logList, SimpleDateFormat sdf, Date startDate, Date endDate) {
        Map<String, Integer> countMap = new HashMap<>();
        // 假设时间字符串的格式，根据实际情况修改
        SimpleDateFormat inputSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Log log : logList) {
            String timeStr = log.getTime();
            if (timeStr != null) {
                try {
                    // 将字符串解析为 Date 对象
                    Date loginTime = inputSdf.parse(timeStr);
                    if (loginTime.after(startDate) && loginTime.before(endDate)) {
                        String dateStr = sdf.format(loginTime);
                        countMap.put(dateStr, countMap.getOrDefault(dateStr, 0) + 1);
                    }
                } catch (ParseException ignored) {

                }
            }
        }
        return countMap;
    }
}
