package com.mc.esstatistic.service;

import java.util.Map;

/**
 * @author machao
 * * @datetime 2025/3/17 13:55
 * * inform
 */
public interface SelectTeacherUseCountService {

    Map<String, Integer> selectQuestionType(Integer teacherId);

    Map<String, Map<String, Integer>> selectExamAnswerLevel(Integer teacherId);

    Map<String,Integer> selectBasicNum(Integer teacherId);
}
