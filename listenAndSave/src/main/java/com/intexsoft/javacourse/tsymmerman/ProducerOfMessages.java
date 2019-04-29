package com.intexsoft.javacourse.tsymmerman;

public class ProducerOfMessages {

//    public ProducerOfMessages(String nameQueueFirst, String nameQueueSecond, int secondsDelay) throws Exception {
//        String[] arrayOfNameQueue = {nameQueueFirst, nameQueueSecond};
//        Channel channel = getChannel();
//        channel.exchangeDeclare( ConsoleUtil.EXCHANGE_NAME, BuiltinExchangeType.DIRECT );
//        getGeneratedMessage( secondsDelay, arrayOfNameQueue, channel );
//    }
//
//    private void getGeneratedMessage(int secondsDelay, String[] arrayOfNameQueue, Channel channel) throws IOException, InterruptedException {
//        for (int messageNumber = 1; messageNumber <= ConsoleUtil.NUMBER_OF_GENERATION_MESSAGES; messageNumber++) {
//            String nameQueueRandom = getRandomName( arrayOfNameQueue );
//            String timeSendingMessage = getTime();
//            String message = getMessage( messageNumber, nameQueueRandom, timeSendingMessage );
//            channel.basicPublish( ConsoleUtil.EXCHANGE_NAME, nameQueueRandom, null, message.getBytes( "UTF-8" ) );
//            System.out.println( " [x] Sent '" + message + "'" );
//            Thread.sleep( secondsDelay * 1000 );
//        }
//    }
//
//    private String getTime() {
//        Date date = new Date();
//        SimpleDateFormat formatForDateNow = new SimpleDateFormat( "hh:mm:ss a" );
//        return formatForDateNow.format( date );
//    }
//
//    private Channel getChannel() throws IOException, TimeoutException {
//
//    }
//
//    private String getMessage(int messageNumber, String nameQueueRandom, String timeSendingMessage) {
//        return "number: " + messageNumber + ", sending time: " + timeSendingMessage + ", name_queue: " + nameQueueRandom;
//    }
//
//    private static String getRandomName(String[] array) {
//        int randomIndex = new Random().nextInt( array.length );
//        return array[randomIndex];
//    }
}

