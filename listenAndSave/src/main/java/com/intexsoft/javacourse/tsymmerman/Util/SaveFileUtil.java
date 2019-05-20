package com.intexsoft.javacourse.tsymmerman.util;

import lombok.extern.log4j.Log4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants.FILE_EXTENSION;

/**
 * Class take a map, and save all message with equal binding key from one queue.
 */
@Log4j
public class SaveFileUtil {
    private static int fileNumber;

    /**
     * When method is calling with parameters start write messages in file.
     *
     * @param messageMap - map of massages.
     * @param nameQueue  - name of queue, which will be saved.
     */
    public static void saveFile(Map<String, java.util.List<String>> messageMap, String nameQueue) {
        String fileName = nameQueue + "_" + fileNumber + FILE_EXTENSION;
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String key : messageMap.keySet()) {
                messageMap.get(key).forEach(s -> writeMessage(key, s, writer));
            }
            writer.flush();
            fileNumber++;
            messageMap.clear();
            log.info(String.format("Save %s in %s", nameQueue, fileName));
        } catch (IOException e) {
            log.error("File is not saving. Exception: ", e);
        }
    }

    private static void writeMessage(String bindingKey, String message, FileWriter writer) {
        if (message.equals(bindingKey)) {
            try {
                writer.write(bindingKey + "\n");
            } catch (IOException e) {
                log.error("Message from map was not writing at the file. Exception:  ", e);
            }
        }
    }
}