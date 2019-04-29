package com.intexsoft.javacourse.tsymmerman;

import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import lombok.Getter;

/**
 * todo javadoc
 */

public class MessageGenerator {
    @Getter
    private String bindingKey = generatedBindingKey();
    @Getter
    private String message = generatedMessage();
    private static int messageNumber = 0;
    private String queueName;

    private String generatedMessage() {
        String message = (messageNumber + " " + bindingKey + " time" + queueName);
        messageNumber++;
        return message;
    }

    private String generatedBindingKey() {
        int randomNumber = (int) (Math.random() * 2);
        return getBindingKey( randomNumber );
    }

    private String getBindingKey(int randomNumber) {
        if (randomNumber == 0) {
            this.queueName = RabbitConstants.FIRST_QUEUE_NQME;
            return RabbitConstants.FIRST_ROUTING_KEY;
        } else {
            this.queueName = RabbitConstants.SECOND_QUEUE_NQME;
            return RabbitConstants.SECOND_ROUTING_KEY;
        }
    }
}
