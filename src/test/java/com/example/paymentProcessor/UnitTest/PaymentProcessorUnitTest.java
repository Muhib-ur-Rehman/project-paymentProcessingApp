package com.example.paymentProcessor.UnitTest;

import com.example.paymentProcessor.model.OrderInfo;
import com.example.paymentProcessor.processor.PaymentProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PaymentProcessorUnitTest {

    @Test
    public void processPaymentTest(){
        OrderInfo order = new OrderInfo();
        order.setAccountNum("12345");
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        Assertions.assertEquals("ACCEPTED",paymentProcessor.processPayment(order).getPaymentStatus());
        Assertions.assertEquals("ACCEPTED",paymentProcessor.processPayment(order).getOrderStatus());
        order.setAccountNum("1234");
        Assertions.assertEquals("REJECTED",paymentProcessor.processPayment(order).getPaymentStatus());
        Assertions.assertEquals("REJECTED",paymentProcessor.processPayment(order).getOrderStatus());
    }
}
