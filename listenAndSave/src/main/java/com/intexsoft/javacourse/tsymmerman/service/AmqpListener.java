package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import com.intexsoft.javacourse.tsymmerman.model.AmqpMessage;
import com.intexsoft.javacourse.tsymmerman.util.AmqpUtils;
import com.intexsoft.javacourse.tsymmerman.util.SaveFileUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants.*;

/**
 * Active and listen, when calling consumer take a messagesList and add in map.
 * After some amount of messagesList write it to file.
 */
@Log4j
public class AmqpListener {
    @Getter
    private static DeliverCallback deliverCallback;
    private static Map<String, List<String>> messagesQueuesMap;
    private static List<AmqpMessage> messagesList;
    private Channel channel;
    static AmqpMessage amqpMessage;

    /**
     * Constructor start listener and before declare consumers.
     */
    public AmqpListener() {
        deliverCallback = getCallback();
        messagesQueuesMap = new LinkedHashMap<>();
        messagesList = new ArrayList<>();
        channel = AmqpUtils.getChannel();
        getConsumers(FIRST_QUEUE_NAME, SECOND_QUEUE_NAME);
    }

    private static DeliverCallback getCallback() {
        return (consumerTag, delivery) -> {
            final String routingKey = getRoutingKey(delivery);
            final String message = getMessage(delivery);
            log.info(String.format("Got message from queue with RK %s: %s", routingKey, message));
            amqpMessage = AmqpMessage.builder().routingKey(routingKey).message(message).build();
            messagesList.add(amqpMessage);
            checkLimitAndSave();
        };
    }

    private static String getRoutingKey(Delivery delivery) {
        return delivery.getEnvelope().getRoutingKey();
    }

    private static String getMessage(Delivery delivery) throws UnsupportedEncodingException {
        return new String(delivery.getBody(), StandardCharsets.UTF_8);
    }

    private static void checkLimitAndSave() {

        if (isMessageLimit()) {
            messagesQueuesMap = messagesList.stream().collect(Collectors.groupingBy(AmqpMessage::getRoutingKey,
                    Collectors.mapping(AmqpMessage::getMessage, Collectors.toList())));
            saveFileMessagesByQueue(messagesQueuesMap);
        }
    }

    private static boolean isMessageLimit() {
        return messagesList.size() == FILE_NUMBER_MESSAGES;
    }

    private static void saveFileMessagesByQueue(Map<String, List<String>> messagesQueuesMap) {
        SaveFileUtil.saveFileMessagesByQueue(messagesQueuesMap);
    }

    private void getConsumers(String nameFirstQueue, String nameSecondQueue) {
        declareConsumer(nameFirstQueue, RabbitConstants.FIRST_ROUTING_KEY);
        declareConsumer(nameSecondQueue, RabbitConstants.SECOND_ROUTING_KEY);
    }

    // todo рекомендация: передавать в метод конкретный callBack   ?
    private void declareConsumer(String nameQueue, String routingKey) {
        try {
            channel.queueDeclare(nameQueue, false, false, true, null);
            channel.queueBind(nameQueue, EXCHANGE, routingKey);
            channel.basicConsume(nameQueue, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException e) {
            log.error("Consumer was not declared. Exception:  ", e);
        }
    }
}