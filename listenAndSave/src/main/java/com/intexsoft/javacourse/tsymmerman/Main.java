package com.intexsoft.javacourse.tsymmerman;

import com.intexsoft.javacourse.tsymmerman.Util.ConsoleUtil;

public class Main {
    public static void main(String[] argv) throws Exception {
        System.out.print(ConsoleUtil.MESSAGE_SET_NAME_QUEUE1);
        String nameQueueFirst = ConsoleUtil.getInputString();
        System.out.print(ConsoleUtil.MESSAGE_SET_NAME_QUEUE2);
        String nameQueueSecond = ConsoleUtil.getInputString();
        System.out.print(ConsoleUtil.MESSAGE_SET_SECONDS_DELAY);
        int secondsDelay = ConsoleUtil.getInputInteger();

        new ConsumerOfMessages(nameQueueFirst, nameQueueSecond);
        new ProducerOfMessages(nameQueueFirst, nameQueueSecond, secondsDelay);
    }
}


