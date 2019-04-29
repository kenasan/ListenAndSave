package com.intexsoft.javacourse.tsymmerman;

import com.intexsoft.javacourse.tsymmerman.Util.AmqpUtils;
import com.intexsoft.javacourse.tsymmerman.service.Scheduler;

public class Main {
    public static void main(String[] argv) throws Exception {

        AmqpUtils.createConnection();
        Scheduler scheduler = new Scheduler();
        scheduler.run();
    }
}


