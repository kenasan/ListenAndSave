package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.Util.MessageGeneratorUtil;
import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Be active all time, and send a message with some delay
 */
public class AmqpScheduler implements Runnable {
    private static Logger log = Logger.getLogger( AmqpListener.class );

    /**
     * Override method from interface Runnable, and mean that body always be active, and send message with delay.
     */
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
                log.log( Level.ERROR, "Exception: ", e );
            }
        }
    }
}
