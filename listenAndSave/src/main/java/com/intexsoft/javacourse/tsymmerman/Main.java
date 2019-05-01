package com.intexsoft.javacourse.tsymmerman;

import com.intexsoft.javacourse.tsymmerman.service.AmqpListener;
import com.intexsoft.javacourse.tsymmerman.service.AmqpScheduler;
import com.intexsoft.javacourse.tsymmerman.Util.AmqpUtils;

public class Main {
    public static void main(String[] argv) throws Exception {

        AmqpUtils.createConnection();
        new AmqpListener();
        new AmqpScheduler().run();
    }
}


