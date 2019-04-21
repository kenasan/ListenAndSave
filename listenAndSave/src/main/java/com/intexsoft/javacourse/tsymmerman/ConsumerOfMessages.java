package com.intexsoft.javacourse.tsymmerman;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerOfMessages {

    protected ConsumerOfMessages(String nameQueueFirst, String nameQueueSecond) throws Exception {

        Channel channel = getChannel();
        getQueueDeclare(nameQueueFirst, channel);
        getQueueDeclare(nameQueueSecond, channel);
        getDeliverCallback(nameQueueFirst, nameQueueSecond, channel);
    }

    private Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Main.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        return channel;
    }

    private void getQueueDeclare(String nameQueue, Channel channel) throws Exception {
        channel.queueDeclare(nameQueue, true, false, false, null);
        channel.queueBind(nameQueue, Main.EXCHANGE_NAME, nameQueue);
    }

    private void getDeliverCallback(String nameQueueFirst, String nameQueueSecond, Channel channel) throws IOException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");

        };
        channel.basicConsume(nameQueueFirst, true, deliverCallback, consumerTag -> {
        });
        channel.basicConsume(nameQueueSecond, true, deliverCallback, consumerTag -> {
        });
    }

}