package com.intexsoft.javacourse.tsymmerman.service;

import com.intexsoft.javacourse.tsymmerman.Util.AmqpUtils;
import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * todo javadoc
 */
public class AmqpConsumer {
    private static Channel channel = AmqpUtils.getChannel();

    public void setDeclareQueue (String nameFirstQueue, String nameSecondQueue){

        try {
            channel.queueDeclare( nameFirstQueue, false, false, true, null );
            channel.queueDeclare( nameSecondQueue, false, false, true, null );
            channel.queueBind( nameFirstQueue, RabbitConstants.EXCHANGE, RabbitConstants.FIRST_ROUTING_KEY );
            channel.queueBind( nameSecondQueue, RabbitConstants.EXCHANGE, RabbitConstants.SECOND_ROUTING_KEY );


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
