package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.Util.AmqpUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * todo javadoc
 */
public class AmqpSender {
    private static Channel channel = AmqpUtils.getChannel();

    /**
     * todo java doc
     */

    public void send(String exchange, String bindingKey, String message) {
        try {
            channel.basicPublish( exchange, bindingKey, null, message.getBytes( "UTF-8" ) );
            System.out.println( " [x] Sent '" + message + "'" );
        } catch (IOException e) {
            e.printStackTrace(); // todo logger
        }
    }
}
