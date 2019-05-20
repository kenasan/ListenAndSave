package com.intexsoft.javacourse.tsymmerman;

import com.intexsoft.javacourse.tsymmerman.service.AmqpListener;
import com.intexsoft.javacourse.tsymmerman.service.AmqpScheduler;
import com.intexsoft.javacourse.tsymmerman.util.AmqpUtils;
import org.apache.log4j.BasicConfigurator;

/**
 * The Main class of "Listen and Save" program.
 */
public class Main {
    /**
     * Create Listener to save received message to map and write to file
     * and Scheduler which send a message with delay.
     *
     * @param args parameters of main method.
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        AmqpUtils.createConnection();
        new AmqpListener();
        new AmqpScheduler().run();
    }
}

