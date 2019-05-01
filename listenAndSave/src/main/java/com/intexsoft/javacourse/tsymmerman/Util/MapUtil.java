package com.intexsoft.javacourse.tsymmerman.Util;

import lombok.Getter;

import java.util.LinkedHashMap;

/**
 * TODO javadoc
 */


public class MapUtil {
    @Getter
    private static LinkedHashMap<String, String> queueMessage = new LinkedHashMap<>();

    public static void addMessage(String message, String bindingKey) {
        queueMessage.put( message, bindingKey );
    }
//
//    public static LinkedHashMap<String, String> copyMap(LinkedHashMap<String, String> map) {
//        return (LinkedHashMap<String, String>) map.clone();
//    }
//    public static void clearMap() {
//        queueMessage.clear();
//    }
}
