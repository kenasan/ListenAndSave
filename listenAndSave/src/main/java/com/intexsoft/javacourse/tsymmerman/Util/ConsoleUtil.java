package com.intexsoft.javacourse.tsymmerman.Util;

import java.util.Scanner;

public class ConsoleUtil {
    public static final String MESSAGE_SET_NAME_QUEUE1 = "Set name of first queue: ";
    public static final String MESSAGE_SET_NAME_QUEUE2 = "Set name of second queue: ";
    public static final String MESSAGE_SET_SECONDS_DELAY = "Set delay seconds between message sanding : ";
    public static final String EXCHANGE_NAME = "exchange";
    public static final int NUMBER_OF_GENERATION_MESSAGES = 10;

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
            System.out.print(ConsoleUtil.MESSAGE_SET_SECONDS_DELAY);
            getInputInteger();
        }
        return Math.abs(inputInteger);
    }
}

