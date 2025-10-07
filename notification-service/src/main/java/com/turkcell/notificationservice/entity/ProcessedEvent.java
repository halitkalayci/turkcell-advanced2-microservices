package com.turkcell.notificationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="processed_events")
public class ProcessedEvent {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    private UUID eventId;
    @Column(nullable = false)
    private String eventType;
    @Column(nullable = false)
    private OffsetDateTime processedAt;

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID eventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String eventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public OffsetDateTime processedAt() {
        return processedAt;
    }

    public void setProcessedAt(OffsetDateTime processedAt) {
        this.processedAt = processedAt;
    }
}
