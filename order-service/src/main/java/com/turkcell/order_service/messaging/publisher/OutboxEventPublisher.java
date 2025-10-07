package com.turkcell.order_service.messaging.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.order_service.event.OrderCreatedEvent;
import com.turkcell.order_service.messaging.outbox.OutboxMessage;
import com.turkcell.order_service.messaging.outbox.OutboxStatus;
import com.turkcell.order_service.repository.OutboxRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OutboxEventPublisher {
    private final OutboxRepository outboxRepository;
    private final StreamBridge streamBridge; // Spring Cloud Stream -> Kafka-Rabbitmq
    private final ObjectMapper objectMapper;

    public OutboxEventPublisher(OutboxRepository outboxRepository, StreamBridge streamBridge, ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.streamBridge = streamBridge;
        this.objectMapper = objectMapper;
    }
    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void publishPendingEvents() throws JsonProcessingException {
        List<OutboxMessage> pendingEvents = outboxRepository.findByStatusOrderByCreatedAtAsc(OutboxStatus.PENDING);

        for (OutboxMessage pendingEvent : pendingEvents) {
            OrderCreatedEvent event = objectMapper.readValue(pendingEvent.payloadJson(), OrderCreatedEvent.class);

            boolean sent = streamBridge.send("orders", event);

            if(sent)
            {
                pendingEvent.setStatus(OutboxStatus.SENT);
                pendingEvent.setProcessedAt(OffsetDateTime.now());
                outboxRepository.save(pendingEvent);
            }else{
                pendingEvent.setRetryCount(pendingEvent.retryCount() + 1);
                if(pendingEvent.retryCount() > 5)
                {
                    pendingEvent.setStatus(OutboxStatus.FAILED);
                    pendingEvent.setProcessedAt(OffsetDateTime.now());
                    outboxRepository.save(pendingEvent);
                }
            }
        }
    }
}
