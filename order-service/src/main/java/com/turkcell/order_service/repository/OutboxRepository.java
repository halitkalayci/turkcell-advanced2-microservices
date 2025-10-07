package com.turkcell.order_service.repository;

import com.turkcell.order_service.outbox.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutboxRepository extends JpaRepository<OutboxMessage, UUID> {
}
