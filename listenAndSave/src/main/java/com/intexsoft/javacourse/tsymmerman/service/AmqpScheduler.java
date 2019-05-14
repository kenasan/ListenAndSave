package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import com.intexsoft.javacourse.tsymmerman.util.MessageGeneratorUtil;
import lombok.extern.log4j.Log4j;

/**
 * Be active all time, and send a message with some delay
 */
@Log4j
public class AmqpScheduler implements Runnable {
    /**
     * Override method from interface Runnable, and mean that body always be active,
     * and send message with delay.
     */
    @Override
    public void run() {
        while (true) {
            try {
                MessageGeneratorUtil messageGenerator = new MessageGeneratorUtil();
                AmqpSender amqpSender = new AmqpSender();
                amqpSender.send(RabbitConstants.EXCHANGE, messageGenerator.getBindingKey(), messageGenerator.getMessage());
                Thread.sleep(RabbitConstants.SECONDS_DELAY * 1000);
            } catch (InterruptedException e) {
                log.error("Exception: ", e);
            }
        }
    }
}
