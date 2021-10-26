package com.example.paymentProcessor.integration_test;

import com.example.paymentProcessor.model.OrderInfo;
import com.example.paymentProcessor.processor.PaymentProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@SpringBootTest
public class PaymentProcessorIntegrationTest {

    @Autowired
    PaymentProcessor paymentProcessor;

    @Test
    public void processPaymentTest(){
        OrderInfo order = new OrderInfo();
        order.setAccountNum("12345");
        Assertions.assertEquals("ACCEPTED",this.paymentProcessor.processPayment(order).getPaymentStatus());
        Assertions.assertEquals("ACCEPTED",this.paymentProcessor.processPayment(order).getOrderStatus());
        order.setAccountNum("1234");
        Assertions.assertEquals("REJECTED",this.paymentProcessor.processPayment(order).getPaymentStatus());
        Assertions.assertEquals("REJECTED",this.paymentProcessor.processPayment(order).getOrderStatus());
    }
}
