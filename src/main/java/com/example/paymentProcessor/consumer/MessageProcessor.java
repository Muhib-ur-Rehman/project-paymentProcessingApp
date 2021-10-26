package com.example.paymentProcessor.consumer;

import com.example.paymentProcessor.config.PaymentConfig;
import com.example.paymentProcessor.model.OrderInfo;
import com.example.paymentProcessor.processor.PaymentProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private PaymentProcessor paymentProcessor;

    @RabbitListener(queues = PaymentConfig.PAYMENT_QUEUE)
    public void consumeMessageFromQueue(OrderInfo order){
        System.out.println("Message received from queue : " + order);
        order = this.paymentProcessor.processPayment(order);
        template.convertAndSend(PaymentConfig.EXCHANGE,PaymentConfig.ROUTING_KEY,order);
    }
}
