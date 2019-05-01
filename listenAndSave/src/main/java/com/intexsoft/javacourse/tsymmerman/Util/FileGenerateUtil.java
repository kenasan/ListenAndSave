package com.intexsoft.javacourse.tsymmerman.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileGenerateUtil {
    private static int count;

    public static void saveFile(LinkedHashMap<String, String> messageMap, String nameQueue, String bindingKey) {
        Iterator iterator = messageMap.entrySet().iterator();

        try (FileWriter writer = new FileWriter( (nameQueue + "_" + count + ".txt"))) {
            writer.write( "Messages at the queue : " + nameQueue + "\n" );
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
                if (entry.getValue().equals( bindingKey )) {
                    writer.write( entry.getKey() + "\n" );
                }
            }
              writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "saved - " + nameQueue + "_" + count );
        count++;
    }
}
