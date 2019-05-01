package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.Util.AmqpUtils;
import com.rabbitmq.client.Channel;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Sending generated message to exchange.
 */
public class AmqpSender {
    private static Logger log = Logger.getLogger( "Send" );
    private static Channel channel = AmqpUtils.getChannel();

    /**
     * When called send message.
     */
    public void send(String exchange, String bindingKey, String message) {
        try {
            channel.basicPublish( exchange, bindingKey, null, message.getBytes( "UTF-8" ) );
            log.info( message );
        } catch (IOException e) {
            e.printStackTrace();
            log.log( Level.ERROR, "Exception: ", e );
        }
    }
}
