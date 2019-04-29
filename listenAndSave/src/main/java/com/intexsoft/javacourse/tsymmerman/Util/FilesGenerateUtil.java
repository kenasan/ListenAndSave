package com.intexsoft.javacourse.tsymmerman.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FilesGenerateUtil {

    public FilesGenerateUtil(ArrayList<String> messagesFromQueueFirst, ArrayList<String> messagesFromQueueSecond) throws IOException {
        String name1 = getNameQueue( messagesFromQueueFirst );
        String name2 = getNameQueue( messagesFromQueueSecond );
        saveInTxtFile( messagesFromQueueFirst, name1 );
        saveInTxtFile( messagesFromQueueSecond, name2 );
    }

    private String getNameQueue(ArrayList<String> queue) {
        return queue.get( 0 );
    }

    private void saveInTxtFile(ArrayList<String> queue, String nameFile) throws IOException {
        try (FileWriter writer = new FileWriter( nameFile )) {
            writer.write( "Messages at the queue" + "\r\n" );
            for (String message : queue) {
                writer.write( message + "\r\n" );
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println( "ERROR: File " + nameFile + " missing" );
        }
        System.out.println( "MESSAGE: File " + nameFile + " saved" );
    }
}
