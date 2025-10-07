package com.turkcell.order_service.repository;

import com.turkcell.order_service.messaging.outbox.OutboxMessage;
import com.turkcell.order_service.messaging.outbox.OutboxStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutboxRepository extends JpaRepository<OutboxMessage, UUID> {
    List<OutboxMessage> findByStatusOrderByCreatedAtAsc(OutboxStatus status);
}
