package com.intexsoft.javacourse.tsymmerman.Util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * don't used
 */
public class ConsoleUtil {
    public static final String MESSAGE_SET_SECONDS_DELAY = "Set delay seconds between message sanding : ";

    public static String getInputString() {
        Scanner scanner = new Scanner( System.in );
        String nameQueue = scanner.nextLine();

        if (isNotTrueValidation( nameQueue )) {
            System.out.println( "ERROR: Name queue is not validate" );
            System.out.print( "Set name of queue: " );
            getInputString();
        }
        return nameQueue;
    }

    private static boolean isNotTrueValidation(String nameQueue) {
        Pattern pattern = Pattern.compile( "^(\\w){1,8}" );
        Matcher matcher = pattern.matcher( nameQueue );
        if (matcher.matches()) {
            return false;
        }
        return true;
    }

    public static int getInputInteger() {

        int inputInteger = 0;
        try {
            inputInteger = Integer.parseInt( getInputString() );
        } catch (NumberFormatException e) {
            System.out.println( "ERROR : Number is not correct." );
            System.out.print( ConsoleUtil.MESSAGE_SET_SECONDS_DELAY );
            getInputInteger();
        }
        return Math.abs( inputInteger );
    }
}

