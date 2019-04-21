package com.intexsoft.javacourse.tsymmerman;


import com.rabbitmq.client.*;

public class MessageGenerator {

    public MessageGenerator(String nameQueue) throws Exception {
        String routingKey = nameQueue;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(Main.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
           // channel.queueBind(nameQueue, Main.EXCHANGE_NAME, "");
            for (int i = 0; i < 20; i++) {
                Thread.sleep(1000);
                String message = i + "." + nameQueue;
                channel.basicPublish(Main.EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }
}
