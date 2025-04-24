package com.mc.escommon.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author machao
 * * @datetime 2025/1/9 20:45
 * * inform
 */

@Component
public class EmailConfig {
    public static final Map<String,String> EMAIL_MAP = new HashMap<>();
    public static final Map<String, Long> TIME_MAP = new HashMap<>();
}
