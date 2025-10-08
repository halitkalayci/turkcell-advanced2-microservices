package com.turkcell.notificationservice.consumer;

import com.turkcell.notificationservice.entity.ProcessedEvent;
import com.turkcell.notificationservice.event.OrderCreatedEvent;
import com.turkcell.notificationservice.repository.ProcessedEventRepository;
import io.micrometer.observation.annotation.Observed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Configuration
public class OrderCreatedConsumer {
    private final ProcessedEventRepository processedEventRepository;

    public OrderCreatedConsumer(ProcessedEventRepository processedEventRepository) {
        this.processedEventRepository = processedEventRepository;
    }

    @Bean
    //@Observed(name="order-created")
    public Consumer<Message<OrderCreatedEvent>> orderCreated() {
        return message ->{
            Optional<ProcessedEvent> alreadyProcessedEvent = processedEventRepository.findByEventId(
                    UUID.fromString(message.getHeaders().get("EventID").toString())
            );
            if(alreadyProcessedEvent.isPresent()) {
                return;
            }

            System.out.println("Event yakalandı:" +  message.getPayload());

            ProcessedEvent processedEvent = new ProcessedEvent();
            processedEvent.setEventId(UUID.fromString(message.getHeaders().get("EventID").toString()));
            processedEvent.setEventType(message.getHeaders().get("EventType").toString());
            //processedEvent.setEventType("OrderCreatedEvent");
            processedEvent.setProcessedAt(OffsetDateTime.now());
            processedEventRepository.save(processedEvent);

            System.out.println("Event işlendi.");
        };
    }
}
