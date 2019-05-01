package com.intexsoft.javacourse.tsymmerman.Util;

import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class take a map, and save all message with equal binding key from one queue.
 */
public class FileGenerateUtil {
    private static Logger log = Logger.getLogger( "Save file" );

    private static int count;

    /**
     * When method is calling with parameters start write messages at file.
     */
    public static void saveFile(LinkedHashMap<String, String> messageMap, String nameQueue, String bindingKey) {
        Iterator iterator = messageMap.entrySet().iterator();

        try (FileWriter writer = new FileWriter( (nameQueue + "_" + count + ".txt") )) {
            writer.write( "Messages at the queue : " + nameQueue + "\n" );
            writeMessage( bindingKey, iterator, writer );
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info( nameQueue + "_" + count + ".txt" );
        count++;
    }

    private static void writeMessage(String bindingKey, Iterator iterator, FileWriter writer) throws IOException {
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            if (entry.getValue().equals( bindingKey )) {
                writer.write( entry.getKey() + "\n" );
            }
        }
    }
}
