package com.turkcell.order_service.consumer;

import com.turkcell.order_service.event.PaymentCompletedEvent;
import com.turkcell.order_service.event.PaymentFailedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PaymentEventConsumer
{
    @Bean
    public Consumer<PaymentCompletedEvent> paymentCompleted() {
        return paymentCompletedEvent -> {
            System.out.println("Payment başarılı: "+paymentCompletedEvent.toString());
        };
    }
    @Bean
    public Consumer<PaymentFailedEvent> paymentFailed() {
        return paymentFailedEvent -> {
            System.out.println("Payment başarısız: "+paymentFailedEvent.toString());
        };
    }
}
