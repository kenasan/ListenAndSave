package com.intexsoft.javacourse.tsymmerman.Util;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * TODO javadoc
 */
@Getter
@Setter
public class MapUtil {

    private static LinkedHashMap<String, String> queueMessage = new LinkedHashMap();

    public static void setQueueMessage(String message, String bindingKey) {
        queueMessage.put( bindingKey, message );
    }
}
