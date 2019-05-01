package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.Util.FileGenerateUtil;
import com.intexsoft.javacourse.tsymmerman.Util.MapUtil;
import com.intexsoft.javacourse.tsymmerman.Util.ConsumerUtil;
import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

/**
 * Active and listen, when calling consumer take a messages and add in map. After some amount of messages write it to file
 */
public class AmqpListener {
    private static Logger log = Logger.getLogger("Receive message");
    private static int messageNumber = 1;
    @Getter
    private static DeliverCallback deliverCallback = getCallback();

    /**
     * Constructor start listener and before declare consumers.
     */
    public AmqpListener() {
        new ConsumerUtil( RabbitConstants.FIRST_QUEUE_NAME, RabbitConstants.SECOND_QUEUE_NAME );
    }

    /**
     * Called when message delivered to consumer
     */
    public static DeliverCallback getCallback() {
        return (consumerTag, delivery) -> {
            MapUtil.addMessage( getMessage( delivery ), getRoutingKey( delivery ) );
            log.info( getMessage( delivery ) );
            ifReadyWrite();
        };
    }

    private static String getRoutingKey(Delivery delivery) {
        return delivery.getEnvelope().getRoutingKey();
    }

    @NotNull
    private static String getMessage(Delivery delivery) throws UnsupportedEncodingException {
        return new String( delivery.getBody(), "UTF-8" );
    }

    private static void ifReadyWrite() {
        if (messageNumber == RabbitConstants.FILE_NUMBER_MESSAGES) {
            save( MapUtil.getQueueMessage() );
        } else {
            messageNumber++;
        }
    }

    private static void save(LinkedHashMap<String, String> map) {
        FileGenerateUtil.saveFile( map, RabbitConstants.FIRST_QUEUE_NAME, RabbitConstants.FIRST_ROUTING_KEY );
        FileGenerateUtil.saveFile( map, RabbitConstants.SECOND_QUEUE_NAME, RabbitConstants.SECOND_ROUTING_KEY );
        messageNumber = 1;
        MapUtil.clearMap();
    }
}

