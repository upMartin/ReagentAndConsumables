package com.huijian.rac.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {
    final static String message = "order";

    @Bean
    public Queue queueMessage(){
        return new Queue(TopicRabbitConfig.message);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    public Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("order");
    }


}
