package com.intexsoft.javacourse.tsymmerman;

import com.intexsoft.javacourse.tsymmerman.Util.ConsoleUtil;
import com.intexsoft.javacourse.tsymmerman.Util.FilesGenerateUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class ConsumerOfMessages {
    int i = 0;
    ArrayList<String> queueFirst = new ArrayList<>();

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
        channel.exchangeDeclare(ConsoleUtil.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        return channel;
    }

    private void getQueueDeclare(String nameQueue, Channel channel) throws Exception {
        channel.queueDeclare(nameQueue, false, false, true, null);
        String routingKey = nameQueue;
        channel.queueBind(nameQueue, ConsoleUtil.EXCHANGE_NAME, routingKey);
    }

    private void getDeliverCallback(String nameQueueFirst, String nameQueueSecond, Channel channel) throws IOException {
        DeliverCallback deliverCallback = getDeliverCallback();
        channel.basicConsume(nameQueueFirst, true, deliverCallback, consumerTag -> {
        });
        channel.basicConsume(nameQueueSecond, true, deliverCallback, consumerTag -> {
        });
    }

    private DeliverCallback getDeliverCallback() {
        return (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            queueFirst.add(i++, message);
            if (queueFirst.size() == ConsoleUtil.NUMBER_OF_GENERATION_MESSAGES) {
                new FilesGenerateUtil(queueFirst, delivery.getEnvelope().getRoutingKey());
            }
        };
    }

}