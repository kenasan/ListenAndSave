package com.intexsoft.javacourse.tsymmerman;

import com.intexsoft.javacourse.tsymmerman.Util.AmqpUtils;
import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import com.intexsoft.javacourse.tsymmerman.service.AmqpListener;
import com.intexsoft.javacourse.tsymmerman.service.Scheduler;

public class Main {
    public static void main(String[] argv) throws Exception {

        AmqpUtils.createConnection();
        new AmqpListener( RabbitConstants.FIRST_QUEUE_NAME, RabbitConstants.SECOND_QUEUE_NAME );
        Scheduler scheduler = new Scheduler();
        scheduler.run();
    }
}


