package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.util.AmqpUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Sending generated message to exchange.
 */
@Log4j
public class AmqpSender {
    private static Channel channel;

    public AmqpSender() {
        channel = AmqpUtils.getChannel();
    }

    /**
     * When called send message.
     */
    public void send(String exchange, String bindingKey, String message) {
        try {
            channel.basicPublish(exchange, bindingKey, null, message.getBytes(UTF_8));
            log.info(message);
        } catch (IOException e) {
            log.error("Sender don't publish a message. Exception: ", e);
        }
    }
}
