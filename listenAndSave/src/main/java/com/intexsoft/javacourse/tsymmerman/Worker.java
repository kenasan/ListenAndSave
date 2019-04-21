package com.intexsoft.javacourse.tsymmerman;


import com.rabbitmq.client.*;

public class Worker {

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(Main.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String nameQueue = channel.queueDeclare().getQueue();
        String routingName = nameQueue;
        channel.queueBind(nameQueue, Main.EXCHANGE_NAME, routingName);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(nameQueue, true, deliverCallback, consumerTag -> {});
    }}