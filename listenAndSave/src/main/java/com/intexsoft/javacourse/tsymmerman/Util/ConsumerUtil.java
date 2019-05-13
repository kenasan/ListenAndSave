package com.intexsoft.javacourse.tsymmerman.Util;

import com.intexsoft.javacourse.tsymmerman.constant.RabbitConstants;
import com.intexsoft.javacourse.tsymmerman.service.AmqpListener;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Util class that declare consumer of message.
 */
public class ConsumerUtil {
final private Logger log = Logger.getLogger( ConsumerUtil.class );
    private Channel channel = AmqpUtils.getChannel();
    private DeliverCallback deliverCallback = AmqpListener.getDeliverCallback();

    /**
     * Constructor take sending parameters and initialize object.
     * @param nameFirstQueue - name of first queue.
     * @param nameSecondQueue - name of second queue.
     */
    public ConsumerUtil(String nameFirstQueue, String nameSecondQueue) {
        getDeclareConsumer( nameFirstQueue,RabbitConstants.FIRST_ROUTING_KEY  );
        getDeclareConsumer( nameSecondQueue, RabbitConstants.SECOND_ROUTING_KEY );
    }

    private void getDeclareConsumer(String nameQueue, String routingKey) {
        try {
            channel.queueDeclare( nameQueue, false, false, true, null );
            channel.queueBind( nameQueue, RabbitConstants.EXCHANGE, routingKey);
            channel.basicConsume( nameQueue, true, deliverCallback, consumerTag -> {
            } );
        } catch (IOException e) {
            e.printStackTrace();
            log.log( Level.ERROR, "Exception: ", e);
        }
    }
}
