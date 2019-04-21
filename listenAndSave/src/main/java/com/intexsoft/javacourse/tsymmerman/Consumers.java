package com.intexsoft.javacourse.tsymmerman;


import com.rabbitmq.client.*;

public class Consumers {

    public static void main(String[] arrayOfNameQueue) throws Exception {


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Main.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        for (String nameQueue:arrayOfNameQueue){
            channel.queueDeclare(nameQueue,true,false,false,null);
            channel.queueBind(nameQueue, Main.EXCHANGE_NAME, nameQueue);
        }

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(arrayOfNameQueue[0], true, deliverCallback, consumerTag -> {
        });
        channel.basicConsume(arrayOfNameQueue[1], true, deliverCallback, consumerTag -> {
        });
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    }
}