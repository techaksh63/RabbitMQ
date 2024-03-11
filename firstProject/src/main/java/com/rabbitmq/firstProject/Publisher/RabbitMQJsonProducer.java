package com.rabbitmq.firstProject.Publisher;

import com.rabbitmq.firstProject.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.json.routing.key}")
    private String jsonRoutingKey;
    @Value("${rabbitmq.second.json.routing.key}")
    private String secondJsonRoutingKey;
    @Value("${rabbitmq.third.json.routing.key}")
    private String thirdJsonRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
      LOGGER.info(String.format("Json message sent -> %s",user.toString()));
      rabbitTemplate.convertAndSend(user);
      rabbitTemplate.convertAndSend(exchange,jsonRoutingKey,user);
      rabbitTemplate.convertAndSend(exchange,secondJsonRoutingKey,user);
//      rabbitTemplate.convertAndSend(exchange,thirdJsonRoutingKey,user);
    }
}
