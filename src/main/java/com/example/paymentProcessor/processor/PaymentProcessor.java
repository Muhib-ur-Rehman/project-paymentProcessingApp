package com.example.paymentProcessor.processor;

import com.example.paymentProcessor.config.PaymentConfig;
import com.example.paymentProcessor.model.OrderInfo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {

    @Autowired
    private RabbitTemplate template;
//
//    public PaymentProcessor(RabbitTemplate template){
//        this.template=template;
//    }

    public OrderInfo processPayment(OrderInfo order){
        String accNum = order.getAccountNum();
        if (accNum.charAt(0)=='1' && accNum.charAt(accNum.length()-1)=='5') {
            order.setPaymentStatus("ACCEPTED");
            order.setOrderStatus("ACCEPTED");
            return order;
        }
        else {
            order.setPaymentStatus("REJECTED");
            order.setOrderStatus("REJECTED");
        }
        return order;
    }
}
