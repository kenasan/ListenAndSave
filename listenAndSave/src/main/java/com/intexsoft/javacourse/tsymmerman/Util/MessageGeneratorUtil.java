package com.intexsoft.javacourse.tsymmerman.util;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants.*;

/**
 * Generate message with random binding key.
 */
@Getter
public class MessageGeneratorUtil {
    private static int messageNumber = 1;
    private String queueName = FIRST_QUEUE_NAME;
    private SimpleDateFormat dateFormat = new SimpleDateFormat( "hh:mm:ss a" );
    private String bindingKey = generateBindingKey();
    private String message = generateMessage();

    private String generateMessage() {
        String message = getText();
        messageNumber++;
        return message;
    }

    private String getText() {
        return String.format("%s) queue: %s; time: %s; binding_key: %s.", messageNumber, queueName, getTime(), bindingKey);
    }

    private String generateBindingKey() {
        int randomNumber = (int) (Math.random() * 2);
        return getBindingKey( randomNumber );
    }

    private String getBindingKey(int randomNumber) {
        if (randomNumber == 0) {
            queueName = FIRST_QUEUE_NAME;
            return FIRST_ROUTING_KEY;
        } else {
            queueName = SECOND_QUEUE_NAME;
            return SECOND_ROUTING_KEY;
        }
    }

    private String getTime() {
        Date date = new Date();
        return dateFormat.format(date);
    }
}
