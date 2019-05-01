package com.intexsoft.javacourse.tsymmerman.Util;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants.EXCHANGE;
import static com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants.HOST_NAME;

/**
 * Util class that create connection to rabbit mq
 */
public abstract class AmqpUtils {
    private static Logger log = Logger.getLogger( AmqpUtils.class );
    @Getter
    private static Channel channel;

    /**
     * after colling create connection
     */
    public static void createConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost( HOST_NAME );
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare( EXCHANGE, BuiltinExchangeType.DIRECT );
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
            log.log( Level.ERROR, "Exception: ", e );
        }
    }
}
