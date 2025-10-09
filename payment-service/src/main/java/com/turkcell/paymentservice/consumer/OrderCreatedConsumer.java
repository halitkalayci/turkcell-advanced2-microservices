package com.turkcell.paymentservice.consumer;

import com.turkcell.paymentservice.event.OrderCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;

@Configuration
public class OrderCreatedConsumer {
    @Bean
    public Consumer<Message<OrderCreatedEvent>> orderCreated() {
        return message ->{
            System.out.println("Event yakalandı:" +  message.getPayload());
            Random random = new Random();
            if(random.nextBoolean()) {
                System.out.println("Ödeme başarılı");
            }else{
                System.out.println("Ödeme başarısız");
            }
            System.out.println("Event işlendi.");
        };
    }
}