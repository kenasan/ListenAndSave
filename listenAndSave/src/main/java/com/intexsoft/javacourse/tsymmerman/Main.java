package com.intexsoft.javacourse.tsymmerman;


import com.intexsoft.javacourse.tsymmerman.Util.ConsoleUtil;

public class Main {
    public static final String EXCHANGE_NAME = "exchange";


    public static void main(String[] argv) throws Exception {
        System.out.print(ConsoleUtil.MESSAGE_SET_QUEUE1);
        String nameQueueFirst = ConsoleUtil.getInputString();
        System.out.print(ConsoleUtil.MESSAGE_SET_QUEUE2);
        String nameQueueSecond = ConsoleUtil.getInputString();
        System.out.print(ConsoleUtil.MESSAGE_SET_SECONDS_DALAY);
        int secondsDelay = ConsoleUtil.getInputInteger();

        new Consumers(nameQueueFirst, nameQueueSecond);
        new MessageGenerator(nameQueueFirst, nameQueueSecond, secondsDelay);
    }


}


