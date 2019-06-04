package com.intexsoft.javacourse.tsymmerman.util;

import lombok.extern.log4j.Log4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import static com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants.*;

/**
 * Class take a map, where key it's binding key from queue, and value it's a list of messages from the same queue,
 * and save each queue to single file.
 */
@Log4j
public class SaveFileUtil {
    private static int fileNumber;

    /**
     * When method is calling with parameters start write messages in file.
     *
     * @param messagesQueuesMap - map of massages grouping by routing_key.
     */
    public static void saveFileMessagesByQueue(Map<String, List<String>> messagesQueuesMap) {
        writeMapMessagesByKey(messagesQueuesMap, FIRST_ROUTING_KEY, FIRST_QUEUE_NAME);
        writeMapMessagesByKey(messagesQueuesMap, SECOND_ROUTING_KEY, SECOND_QUEUE_NAME);
        messagesQueuesMap.clear();
    }

    private static void writeMapMessagesByKey(Map<String, List<String>> messagesQueuesMap, String key, String nameQueue) {
        String fileName = nameQueue + "_" + fileNumber + FILE_EXTENSION;
        try (FileWriter writer = new FileWriter(fileName)) {
            messagesQueuesMap.get(key).forEach(message -> writeFileMessage(message, writer));
            writer.flush();
            fileNumber++;
            log.info(String.format("Save %s in %s", nameQueue, fileName));
        } catch (IOException e) {
            log.error("File: " + fileName + " -- is not saving. Exception: ", e);
        }
    }

    private static void writeFileMessage(String message, Writer writer) {
        try {
            writer.write(message + "\n");
        } catch (IOException e) {
            log.error("Message: " + message + " -- was not writing at the file. Exception:  ", e);
        }
    }
}