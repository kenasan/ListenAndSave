package com.intexsoft.javacourse.tsymmerman.constant;

/**
 * Class of constants which used in program.
 */
public abstract class RabbitConstants {
    public static final String HOST_NAME = "localhost";
    public static final String EXCHANGE = "exchange";
    public static final String FIRST_ROUTING_KEY = "AMQP_key_first";
    public static final String SECOND_ROUTING_KEY = "AMQP_key_second";
    public static final int SECONDS_DELAY = 1;
    public static final String FIRST_QUEUE_NAME = "AMQP_queue_first";
    public static final String SECOND_QUEUE_NAME = "AMQP_queue_second";
    public static final int FILE_NUMBER_MESSAGES = 10;
    public static final String FILE_EXTENSION = ".txt";
}

