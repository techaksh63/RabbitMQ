package com.rabbitmq.firstProject.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.queue.json.name}")
    private String JsonQueue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.second.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.second.json.routing.key}")
    private String jsonRoutingKey;
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    @Bean
    public Queue JsonQueue(){
        return new Queue(JsonQueue);
    }


//    @Bean
//    public TopicExchange exchange(){
//        return new TopicExchange(exchange);
//    }
//
//    @Bean
//    public Binding binding(){
//        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
//    }
//
//    @Bean
//    public Binding jsonBinding(){
//        return BindingBuilder.bind(JsonQueue()).to(exchange()).with(jsonRoutingKey);
//    }




    @Bean
    public FanoutExchange exchange(){
        return new FanoutExchange(exchange);
    }
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange());
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(JsonQueue()).to(exchange());
    }


//    @Bean
//    public DirectExchange exchange(){
//        return new DirectExchange(exchange);
//    }
//    @Bean
//    public Binding binding(){
//        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
//    }
//    @Bean
//    public Binding jsonBinding(){
//        return BindingBuilder.bind(JsonQueue()).to(exchange()).with(jsonRoutingKey);
//    }


    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
