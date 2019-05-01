package com.intexsoft.javacourse.tsymmerman.Util;

import lombok.Getter;

import java.util.LinkedHashMap;

/**
 * Util class that create LinkedHashMap and worked with it
 */
public class MapUtil {

    @Getter
    private static LinkedHashMap<String, String> queueMessage = new LinkedHashMap<>();

    /**
     * Method which add element (value - key) from map, where value is the binding key, and key it's a message.
     */
    public static void addMessage(String message, String bindingKey) {
        queueMessage.put( message, bindingKey );
    }

    /**
     * Method which clear map, and called after writing map in file.
     */
    public static void clearMap() {
        queueMessage.clear();
    }
}
