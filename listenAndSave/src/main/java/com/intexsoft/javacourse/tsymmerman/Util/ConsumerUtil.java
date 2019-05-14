package com.intexsoft.javacourse.tsymmerman.util;

import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import com.intexsoft.javacourse.tsymmerman.service.AmqpListener;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.log4j.Log4j;

import java.io.IOException;

/**
 * Util class that declare consumer of message.
 */
@Log4j
public class ConsumerUtil {
    private Channel channel = AmqpUtils.getChannel();
    private DeliverCallback deliverCallback = AmqpListener.getDeliverCallback();

    /**
     * Constructor take sending parameters and initialize object.
     *
     * @param nameFirstQueue  - name of first queue.
     * @param nameSecondQueue - name of second queue.
     */
    public ConsumerUtil(String nameFirstQueue, String nameSecondQueue) {
        declareConsumer(nameFirstQueue, RabbitConstants.FIRST_ROUTING_KEY);
        declareConsumer(nameSecondQueue, RabbitConstants.SECOND_ROUTING_KEY);
    }

    private void declareConsumer(String nameQueue, String routingKey) {
        try {
            channel.queueDeclare(nameQueue, false, false, true, null);
            channel.queueBind(nameQueue, RabbitConstants.EXCHANGE, routingKey);
            channel.basicConsume(nameQueue, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
    }
}
