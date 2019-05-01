package com.intexsoft.javacourse.tsymmerman.Util;

import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import com.intexsoft.javacourse.tsymmerman.service.AmqpListener;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * Created by kenasan on 01.05.2019.
 */
public class ConsumerUtil {

    private Channel channel = AmqpUtils.getChannel();
    DeliverCallback deliverCallback = AmqpListener.getDeliverCallback();

    public ConsumerUtil(String nameFirstQueue, String nameSecondQueue) {
        getDeclareConsumer( nameFirstQueue,RabbitConstants.FIRST_ROUTING_KEY  );
        getDeclareConsumer( nameSecondQueue, RabbitConstants.SECOND_ROUTING_KEY );
    }

    private void getDeclareConsumer(String nameQueue, String routingKey) {
        try {
            channel.queueDeclare( nameQueue, false, false, true, null );
            channel.queueBind( nameQueue, RabbitConstants.EXCHANGE, routingKey);
            channel.basicConsume( nameQueue, true, deliverCallback, consumerTag -> {
            } );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
