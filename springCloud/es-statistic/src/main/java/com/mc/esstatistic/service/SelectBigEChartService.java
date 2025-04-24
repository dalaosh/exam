package com.mc.esstatistic.service;

import com.mc.escommon.entity.exam.ExamManage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * @author machao
 * * @datetime 2025/3/19 19:53
 * * inform
 */
public interface SelectBigEChartService {
    Map<String,Integer> selectBasicNum();

    Map<String,Map<String, Integer>> selectQuestionNum();

    List<ExamManage> selectExamAll();

    Map<String, Integer> selectStrongAll();
}
