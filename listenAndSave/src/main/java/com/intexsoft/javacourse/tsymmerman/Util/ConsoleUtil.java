package com.intexsoft.javacourse.tsymmerman.Util;

import java.util.Scanner;

public class ConsoleUtil {
    public static final String MESSAGE_SET_QUEUE1 = "Set name of first queue: ";
    public static final String MESSAGE_SET_QUEUE2 = "Set name of second queue: ";
    public static final String MESSAGE_SET_SECONDS_DALAY = "Set delay seconds between message sanding : ";

    public static String getInputString() {
        Scanner scanner = new Scanner(System.in);
        String nameQueue = scanner.nextLine();
        return nameQueue;
    }

    public static int getInputInteger() {

        int inputInteger = 0;
        try {
            inputInteger = Integer.parseInt(getInputString());
        } catch (NumberFormatException e) {
            System.out.println("Error");
            System.out.print(ConsoleUtil.MESSAGE_SET_SECONDS_DALAY);
            getInputInteger();
        }
        return Math.abs(inputInteger);
    }
}

