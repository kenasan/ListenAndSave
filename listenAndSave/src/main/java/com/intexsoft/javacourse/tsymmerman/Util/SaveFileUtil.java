package com.intexsoft.javacourse.tsymmerman.util;

import lombok.extern.log4j.Log4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

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
     * @param bindingKey - name of key, by which will be sorting map.
     */
    public static void saveFile(Map<String, String> messageMap, String nameQueue, String bindingKey) {
        Iterator iterator = messageMap.entrySet().iterator();
        String fileName = nameQueue + "_" + fileNumber + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writeMessage(bindingKey, iterator, writer);
            writer.flush();
            fileNumber++;
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
        log.info(String.format("Save %s in %s", nameQueue, fileName));
    }

    private static void writeMessage(String bindingKey, Iterator iterator, FileWriter writer) throws IOException {
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            if (entry.getValue().equals(bindingKey)) {
                writer.write(entry.getKey() + "\n");
            }
        }
    }
}
