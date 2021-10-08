package com.example.paymentProcessor.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {
    public static final String QUEUE = "order_queue";
    public static final String PAYMENT_QUEUE = "payment_queue";
    public static final String EXCHANGE= "order_exchange";
    public static final String ROUTING_KEY= "order_routingKey";

    @Bean
    public Queue paymentQueue (){
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange paymentExchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding paymentBinding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter paymentConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate paymentTemplate (ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(paymentConverter());
        return rabbitTemplate;
    }
}
