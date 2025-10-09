package com.turkcell.paymentservice.consumer;

import com.turkcell.paymentservice.event.OrderCreatedEvent;
import com.turkcell.paymentservice.event.PaymentCompletedEvent;
import com.turkcell.paymentservice.event.PaymentFailedEvent;
import org.springframework.cloud.stream.function.StreamBridge;
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
    private final StreamBridge streamBridge;

    public OrderCreatedConsumer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Bean
    public Consumer<Message<OrderCreatedEvent>> orderCreated() {
        return message ->{
            System.out.println("Event yakalandı:" +  message.getPayload());
            Random random = new Random();
            if(random.nextBoolean()) {
                PaymentCompletedEvent event = new PaymentCompletedEvent(
                        UUID.randomUUID(),
                        message.getPayload().orderId(),
                        message.getPayload().customerId(),
                        message.getPayload().totalAmount()
                );

                boolean isSent = streamBridge.send("paymentCompleted", event);
                System.out.println("Ödeme başarılı");
            }else{
                PaymentFailedEvent event = new PaymentFailedEvent(
                        UUID.randomUUID(),
                        message.getPayload().orderId(),
                        message.getPayload().customerId(),
                        message.getPayload().totalAmount(),
                        "3D başarısız oldu"
                );

                boolean isSent =  streamBridge.send("paymentFailed", event);
                System.out.println("Ödeme başarısız");
            }
            System.out.println("Event işlendi.");
        };
    }
}