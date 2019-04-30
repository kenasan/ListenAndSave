package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.Util.MessageGeneratorUtil;
import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;

/**
 * todo annotation
 */
public class Scheduler implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                MessageGeneratorUtil messageGenerator = new MessageGeneratorUtil();
                AmqpSender amqpSender = new AmqpSender();
                amqpSender.send( RabbitConstants.EXCHANGE, messageGenerator.getBindingKey(), messageGenerator.getMessage() );
                Thread.sleep( RabbitConstants.SECONDS_DELAY * 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
