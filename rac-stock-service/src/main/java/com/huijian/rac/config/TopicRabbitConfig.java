package com.huijian.rac.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {
    final static String message = "goodsDictionary";

    @Bean
    public Queue queueMessage(){
        return new Queue(TopicRabbitConfig.message);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    public Binding bindingExchangeMessage(Queue queueMessage,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("goodsDictionary");
    }
}
