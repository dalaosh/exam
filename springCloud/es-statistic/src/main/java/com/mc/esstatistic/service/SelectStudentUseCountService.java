package com.mc.esstatistic.service;

import java.util.Map;

/**
 * @author machao
 * * @datetime 2025/3/17 13:54
 * * inform
 */
public interface SelectStudentUseCountService {
    Map<String, Map<String, Integer>> selectQuestionType(Integer studentId);

    Map<String, Map<String, Integer>> selectStudentStrong(Integer studentId);

    Map<String, Integer> selectBasicNum(Integer studentId);
}
