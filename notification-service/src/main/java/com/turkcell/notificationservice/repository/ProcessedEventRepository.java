package com.turkcell.notificationservice.repository;

import com.turkcell.notificationservice.entity.ProcessedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, UUID> {
    Optional<ProcessedEvent> findByEventId(UUID eventId);
}
