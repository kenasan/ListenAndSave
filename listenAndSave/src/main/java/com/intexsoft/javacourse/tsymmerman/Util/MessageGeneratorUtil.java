package com.intexsoft.javacourse.tsymmerman.Util;

import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import lombok.Getter;

/**
 * todo javadoc
 */
public class MessageGeneratorUtil {
    @Getter
    private String bindingKey = generatedBindingKey();
    @Getter
    private String message = generatedMessage();
    private static int messageNumber = 1;
    private String queueName;

    private String generatedMessage() {
        String message = (messageNumber + ") queue: " + queueName + "; time: " + "; binding_key: " + bindingKey);
        messageNumber++;
        return message;
    }

    private String generatedBindingKey() {
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
}
