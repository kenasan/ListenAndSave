package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.Util.FileGenerateUtil;
import com.intexsoft.javacourse.tsymmerman.Util.MapUtil;
import com.intexsoft.javacourse.tsymmerman.Util.ConsumerUtil;
import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import com.rabbitmq.client.DeliverCallback;
import lombok.Getter;

import java.util.LinkedHashMap;

/**
 * todo javadoc
 */
public class AmqpListener {
    private static int messageNumber = 1;
    @Getter
    private static DeliverCallback deliverCallback = getCallback();

    public AmqpListener() {
        new ConsumerUtil( RabbitConstants.FIRST_QUEUE_NAME, RabbitConstants.SECOND_QUEUE_NAME );
    }

    public static DeliverCallback getCallback() {
        return (consumerTag, delivery) -> {
            String message = new String( delivery.getBody(), "UTF-8" );
            System.out.println( " [x] Received '" + message + "'" );
            String routingKey = delivery.getEnvelope().getRoutingKey();
            MapUtil.addMessage( message, routingKey );
            if (messageNumber == RabbitConstants.FILE_NUMBER_MESSAGES) {
                save( MapUtil.getQueueMessage() );
            } else {
                messageNumber++;
            }
        };
    }

    private static void save(LinkedHashMap<String, String> map) {
        FileGenerateUtil.saveFile( map, RabbitConstants.FIRST_QUEUE_NAME, RabbitConstants.FIRST_ROUTING_KEY );
        FileGenerateUtil.saveFile( map, RabbitConstants.SECOND_QUEUE_NAME, RabbitConstants.SECOND_ROUTING_KEY );
        messageNumber = 1;
        //MapUtil.clearMap();
    }
}

