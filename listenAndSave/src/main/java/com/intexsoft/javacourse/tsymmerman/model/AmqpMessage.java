package com.intexsoft.javacourse.tsymmerman.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Class which present a message from queue.
 */
@Setter
@Getter
@Builder
public class AmqpMessage {
    private String routingKey;
    private String message;

}