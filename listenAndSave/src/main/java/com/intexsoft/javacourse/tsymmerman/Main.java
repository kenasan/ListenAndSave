package com.intexsoft.javacourse.tsymmerman;


import java.util.Scanner;

public class Main {
    public static final String EXCHANGE_NAME = "exchange";

    public static void main(String[] argv) throws Exception {
       String[] arrayOfNameQueue = new String[2];
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        for (String nameQueue :arrayOfNameQueue) {
            System.out.print("Set name of " + (index+1) + " queue : ");
            nameQueue = scanner.nextLine();
            arrayOfNameQueue[index]=nameQueue;
            index++;
        }
        Consumers.main(arrayOfNameQueue);
        new MessageGenerator(arrayOfNameQueue);
    }
}


