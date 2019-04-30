package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.Util.AmqpUtils;
import com.intexsoft.javacourse.tsymmerman.Util.MapUtil;
import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * todo javadoc
 */
public class AmqpListener {
    private static Channel channel = AmqpUtils.getChannel();
    private DeliverCallback deliverCallback = getDeliverCallback();

    public AmqpListener(String nameFirstQueue, String nameSecondQueue) {
        try {
            getQueuesDeclare( nameFirstQueue, nameSecondQueue );
            getQueueBinds( nameFirstQueue, nameSecondQueue );
            getBasicConsume( nameFirstQueue, nameSecondQueue );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DeliverCallback getDeliverCallback() {
        return (consumerTag, delivery) -> {
            String message = new String( delivery.getBody(), "UTF-8" );
            System.out.println( " [x] Received '" + message + "'" );
            String routingKey = delivery.getEnvelope().getRoutingKey();
            MapUtil.addMessage( routingKey, message );
            System.out.println( MapUtil.getQueueMessage().toString() );
        };
    }

    private void getBasicConsume(String nameFirstQueue, String nameSecondQueue) throws IOException {
        channel.basicConsume( nameFirstQueue, true, deliverCallback, consumerTag -> {
        } );
        channel.basicConsume( nameSecondQueue, true, deliverCallback, consumerTag -> {
        } );
    }

    private void getQueueBinds(String nameFirstQueue, String nameSecondQueue) throws IOException {
        channel.queueBind( nameFirstQueue, RabbitConstants.EXCHANGE, RabbitConstants.FIRST_ROUTING_KEY );
        channel.queueBind( nameSecondQueue, RabbitConstants.EXCHANGE, RabbitConstants.SECOND_ROUTING_KEY );
    }

    private void getQueuesDeclare(String nameFirstQueue, String nameSecondQueue) throws IOException {
        channel.queueDeclare( nameFirstQueue, false, false, true, null );
        channel.queueDeclare( nameSecondQueue, false, false, true, null );
    }
}
