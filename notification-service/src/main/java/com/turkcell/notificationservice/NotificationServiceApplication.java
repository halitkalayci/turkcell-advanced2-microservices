package com.turkcell.notificationservice;

import com.turkcell.notificationservice.event.OrderCreatedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @Bean
    public Consumer<Message<OrderCreatedEvent>> orderCreated() {
        return message ->{
            System.out.println("Event yakalandı:" +  message.getPayload());
            var a = 1/0;
            System.out.println("Event işlendi.");
        };
    }
}
