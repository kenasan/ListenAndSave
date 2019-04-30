package com.intexsoft.javacourse.tsymmerman.Util;

import lombok.Getter;

import java.util.LinkedHashMap;

/**
 * TODO javadoc
 */


public class MapUtil {
    @Getter
    private static LinkedHashMap<String, String> queueMessage = new LinkedHashMap();

    public static void addMessage(String message, String bindingKey) {
        queueMessage.put( bindingKey, message );
    }
}
