package com.intexsoft.javacourse.tsymmerman.util;

import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Generate message with random binding key.
 */
public class MessageGeneratorUtil {

    private static int messageNumber = 1;
    private String queueName;
    private SimpleDateFormat dateFormat = new SimpleDateFormat( "hh:mm:ss a" );
    @Getter
    private String bindingKey = generateBindingKey();
    @Getter
    private String message = generateMessage();

    private String generateMessage() {
        String message = getText();
        messageNumber++;
        return message;
    }

    private String getText() {
        return String.format( "%s) queue: %s; time: %s; binding_key: %s.", messageNumber, queueName, getTime(), bindingKey );
    }

    private String generateBindingKey() {
        int randomNumber = (int) (Math.random() * 2);
        return getBindingKey( randomNumber );
    }

    private String getBindingKey(int randomNumber) {
        if (randomNumber == 0) {
            queueName = RabbitConstants.FIRST_QUEUE_NAME;
            return RabbitConstants.FIRST_ROUTING_KEY;
        } else {
            queueName = RabbitConstants.SECOND_QUEUE_NAME;
            return RabbitConstants.SECOND_ROUTING_KEY;
        }
    }

    private String getTime() {
        Date date = new Date();
        return dateFormat.format( date );
    }
}
