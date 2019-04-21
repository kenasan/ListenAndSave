package com.intexsoft.javacourse.tsymmerman;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Random;

public class MessageGenerator {

    public MessageGenerator(String[] arrayOfNameQueue) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(Main.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            for (int i = 0; i < 20; i++) {
                String nameQueRandom = getRandom(arrayOfNameQueue);
                String message = i + "." + nameQueRandom;
                channel.basicPublish(Main.EXCHANGE_NAME, nameQueRandom , null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
                Thread.sleep(1000);
            }
        }
    }

    public static String getRandom(String[] array) {
        int randomNumber = new Random().nextInt(array.length);
        return array[randomNumber];
    }
}

