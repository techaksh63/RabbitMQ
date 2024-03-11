package com.rabbitmq.firstProject.Publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.second.routing.key}")
    private String secondRoutingKey;
    @Value("${rabbitmq.third.routing.key}")
    private String thirdRoutingKey;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message send -> %s",message));
          rabbitTemplate.convertAndSend(message);
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
        rabbitTemplate.convertAndSend(exchange,secondRoutingKey,message);
//        rabbitTemplate.convertAndSend(exchange,thirdRoutingKey,message);
    }
}
