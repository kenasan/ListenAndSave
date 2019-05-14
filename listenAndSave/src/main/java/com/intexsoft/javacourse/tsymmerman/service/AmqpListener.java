package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import com.intexsoft.javacourse.tsymmerman.util.ConsumerUtil;
import com.intexsoft.javacourse.tsymmerman.util.SaveFileUtil;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

/**
 * Active and listen, when calling consumer take a messages and add in map.
 * After some amount of messages write it to file.
 */
@Log4j
public class AmqpListener {
    private static int messageNumber = 1;
    private static LinkedHashMap<String, String> queueMessage = new LinkedHashMap<>();
    @Getter
    private static DeliverCallback deliverCallback = getCallback();

    /**
     * Constructor start listener and before declare consumers.
     */
    public AmqpListener() {
        new ConsumerUtil(RabbitConstants.FIRST_QUEUE_NAME, RabbitConstants.SECOND_QUEUE_NAME);
    }

    /**
     * Called when message delivered to consumer.
     */
    public static DeliverCallback getCallback() {
        return (consumerTag, delivery) -> {
            queueMessage.put(getMessage(delivery), getRoutingKey(delivery));
            log.info(getMessage(delivery));
            ifReadyWrite();
        };
    }

    private static String getRoutingKey(Delivery delivery) {
        return delivery.getEnvelope().getRoutingKey();
    }

    private static String getMessage(Delivery delivery) throws UnsupportedEncodingException {
        return new String(delivery.getBody(), "UTF-8");
    }

    private static void ifReadyWrite() {
        if (messageNumber == RabbitConstants.FILE_NUMBER_MESSAGES) {
            save(queueMessage);
            queueMessage.clear();
        } else {
            messageNumber++;
        }
    }

    private static void save(LinkedHashMap<String, String> map) {
        SaveFileUtil.saveFile(map, RabbitConstants.FIRST_QUEUE_NAME, RabbitConstants.FIRST_ROUTING_KEY);
        SaveFileUtil.saveFile(map, RabbitConstants.SECOND_QUEUE_NAME, RabbitConstants.SECOND_ROUTING_KEY);
        messageNumber = 1;
    }
}

