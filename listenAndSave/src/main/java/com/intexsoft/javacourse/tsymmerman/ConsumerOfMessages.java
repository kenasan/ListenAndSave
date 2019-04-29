package com.intexsoft.javacourse.tsymmerman;

// TODO rename class, add javadoc
public class ConsumerOfMessages {
    private int numberOfMessages = 0;
    static String nameFirst = "qwe";

   //

//    private Channel getChannel() throws IOException, TimeoutException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost( "localhost" );
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.exchangeDeclare( ConsoleUtil.EXCHANGE_NAME, BuiltinExchangeType.DIRECT );
//        return channel;
//    }
//
//    private void getQueueDeclare(String nameQueue, Channel channel) throws Exception {
//        channel.queueDeclare( nameQueue, false, false, true, null );
//        String routingKey = nameQueue;
//        channel.queueBind( nameQueue, ConsoleUtil.EXCHANGE_NAME, routingKey );
//    }
//
//    private void getDeliverCallback(String nameQueueFirst, String nameQueueSecond, Channel channel) throws IOException {
//        DeliverCallback deliverCallback = getDeliverCallback();
//        channel.basicConsume( nameQueueFirst, true, deliverCallback, consumerTag -> {
//        } );
//        channel.basicConsume( nameQueueSecond, true, deliverCallback, consumerTag -> {
//        } );
//    }
//
//    private DeliverCallback getDeliverCallback() {
//        return (consumerTag, delivery) -> {
//            String message = new String( delivery.getBody(), "UTF-8" );
//            System.out.println( " [x] Received '" + message + "'" );
//            String routingKeyFromMessage = delivery.getEnvelope().getRoutingKey();
//            addMessageInArrayForRoutingKey( message, routingKeyFromMessage );
//            if (numberOfMessages == ConsoleUtil.NUMBER_OF_GENERATION_MESSAGES) {
//                new FilesGenerateUtil( MapUtil.class.);
//            }
//        };
//    }
//
//    private void addMessageInArrayForRoutingKey(String message, String routingKey) {
//        numberOfMessages++;
//        if (routingKey.equals( nameFirst )) {
//            MapUtil.queueFirst.add( numberOfMessages, message );
//        }
//        MapUtil.queueSecond.add( numberOfMessages, message );
//    }
}