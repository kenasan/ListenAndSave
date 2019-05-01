package com.intexsoft.javacourse.tsymmerman;

import com.intexsoft.javacourse.tsymmerman.Util.AmqpUtils;
import com.intexsoft.javacourse.tsymmerman.service.AmqpListener;
import com.intexsoft.javacourse.tsymmerman.service.AmqpScheduler;
import org.apache.log4j.BasicConfigurator;

/**
 * The Main class of "Listen and Save" program.
 */
public class Main {

    /**
     * The main method of Main class entry point of program.
     * Create Listener to save received message to map and write to file
     * and Scheduler which send a message with delay
     *
     * @param args
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        AmqpUtils.createConnection();
        new AmqpListener();
        new AmqpScheduler().run();
    }
}


