package com.intexsoft.javacourse.tsymmerman.Util;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants.EXCHANGE;
import static com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants.HOST_NAME;

/**
 * todo javadoc
 */
public abstract class AmqpUtils {
    @Getter
    private static Channel channel;

    /**
     * todo javadoc
     */
    public static void createConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost( HOST_NAME );
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare( EXCHANGE, BuiltinExchangeType.DIRECT );
        } catch (IOException | TimeoutException e) {
            e.printStackTrace(); // todo logger
        }
    }
}
