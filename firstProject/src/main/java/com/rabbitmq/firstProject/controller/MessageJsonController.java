package com.rabbitmq.firstProject.controller;

import com.rabbitmq.firstProject.Consumer.RabbitMQJsonConsumer;
import com.rabbitmq.firstProject.Publisher.RabbitMQJsonProducer;
import com.rabbitmq.firstProject.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
    private final RabbitMQJsonProducer jsonProducer;
    private final RabbitMQJsonConsumer rabbitMQJsonConsumer;
    public MessageJsonController(RabbitMQJsonProducer jsonProducer, RabbitMQJsonConsumer rabbitMQJsonConsumer){
        this.jsonProducer = jsonProducer;
        this.rabbitMQJsonConsumer = rabbitMQJsonConsumer;
    }
    @PostMapping("/publish")
    public ResponseEntity<User> sendJsonMessage(@RequestBody User user){
        jsonProducer.sendJsonMessage(user);
//        rabbitMQJsonConsumer.consumerJsonMessage(user);
        return ResponseEntity.ok(user);
    }
}
