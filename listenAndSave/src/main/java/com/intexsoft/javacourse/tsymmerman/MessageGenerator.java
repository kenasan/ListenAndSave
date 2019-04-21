package com.intexsoft.javacourse.tsymmerman;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class MessageGenerator {

    public MessageGenerator(String nameQueueFirst, String nameQueueSecond, int secondsDelay) throws Exception {
        String[] arrayOfNameQueue = {nameQueueFirst, nameQueueSecond};
        Channel channel = getChannel();
        channel.exchangeDeclare(Main.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        getGeneratedMessage(secondsDelay, arrayOfNameQueue, channel);
    }

    private void getGeneratedMessage(int secondsDelay, String[] arrayOfNameQueue, Channel channel) throws IOException, InterruptedException {
        for (int messageNumber = 0; messageNumber < 100; messageNumber++) {
            String nameQueueRandom = getRandom(arrayOfNameQueue);
            Date date = new Date();
            String message = getMessage(messageNumber, nameQueueRandom, date);
            channel.basicPublish(Main.EXCHANGE_NAME, nameQueueRandom, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
            Thread.sleep(secondsDelay * 1000);
        }
    }

    private Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        return connection.createChannel();
    }

    private String getMessage(int messageNumber, String nameQueueRandom, Date date) {
        return "number: " + messageNumber + ", time: " + date.getTime() + ", name_queue: " + nameQueueRandom;
    }

    private static String getRandom(String[] array) {
        int randomIndex = new Random().nextInt(array.length);
        return array[randomIndex];
    }

}

