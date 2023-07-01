package com.example.myseckill.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CanalConfig {

    @Bean
    Queue queue(){
        return  new Queue("canal_queue");
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("canal_exchange01");
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with("canal_key");
    }
}