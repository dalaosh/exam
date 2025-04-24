package com.mc.escommon.result;

/**
 * @author machao
 * * @datetime 2025/3/15 12:40
 * * inform
 */
public enum QuestionTypeEnum {
    QUESTION_CODE("编程题","code"),
    QUESTION_FILL("填空题","fill"),
    QUESTION_JUDGE("判断题","judge"),
    QUESTION_MULTI("选择题","multi"),
    QUESTION_SHORT_ANS("简答题","shortAns");
    public final String name;
    public final String value;

    QuestionTypeEnum(String name, String value) {
        this.value = value;
        this.name = name;
    }
}
