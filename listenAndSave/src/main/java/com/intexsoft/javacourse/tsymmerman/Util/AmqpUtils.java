package com.intexsoft.javacourse.tsymmerman.util;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants.EXCHANGE;
import static com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants.HOST_NAME;

/**
 * Util class that create connection to rabbit mq.
 */
@Log4j
public abstract class AmqpUtils {
    @Getter
    private static Channel channel;

    /**
     * After colling create connection.
     */
    public static void createConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT);
        } catch (IOException | TimeoutException e) {
            log.error("Exception: ", e);
        }
    }
}
