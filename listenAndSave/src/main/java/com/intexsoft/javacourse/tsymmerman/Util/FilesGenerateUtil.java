package com.intexsoft.javacourse.tsymmerman.Util;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FilesGenerateUtil {
    public FilesGenerateUtil(ArrayList<String> arrayList, String nameQueue) {
        try (FileWriter writer = new FileWriter(nameQueue, false)) {
            writer.write("Auto generated");
            for (String string : arrayList) {
                writer.write(string);
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println("ERROR: file is not saving");
        }
        System.out.println("ok");
    }
}
