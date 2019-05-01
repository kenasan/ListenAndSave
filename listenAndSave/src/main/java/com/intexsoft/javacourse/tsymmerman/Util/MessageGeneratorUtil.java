package com.intexsoft.javacourse.tsymmerman.Util;

import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Generate message with random binding key
 */
public class MessageGeneratorUtil {

    @Getter
    private String bindingKey = generateBindingKey();
    @Getter
    private String message = generateMessage();
    private static int messageNumber = 1;
    private String queueName;

    private String generateMessage() {
        String message = (messageNumber + ") queue: " + queueName + "; time: " + getTime() + "; binding_key: " + bindingKey);
        messageNumber++;
        return message;
    }

    private String generateBindingKey() {
        int randomNumber = (int) (Math.random() * 2);
        return getBindingKey( randomNumber );
    }

    private String getBindingKey(int randomNumber) {
        if (randomNumber == 0) {
            this.queueName = RabbitConstants.FIRST_QUEUE_NAME;
            return RabbitConstants.FIRST_ROUTING_KEY;
        } else {
            this.queueName = RabbitConstants.SECOND_QUEUE_NAME;
            return RabbitConstants.SECOND_ROUTING_KEY;
        }
    }

    private String getTime() {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat( "hh:mm:ss a" );
        return formatForDateNow.format( date );
    }
}
