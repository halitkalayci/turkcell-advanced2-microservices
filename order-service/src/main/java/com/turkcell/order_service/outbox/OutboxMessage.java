package com.turkcell.order_service.outbox;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="outbox", indexes = {
        @Index(name="ix_outbox_event_id", columnList = "eventId", unique = true),
        @Index(name="ix_outbox_status_created", columnList = "status, createdAt")
})
public class OutboxMessage {
    @Id
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID eventID = UUID.randomUUID(); // Idempotency
    @Column(nullable = false)
    private String aggregateType; // Order
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID aggregateId;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String payloadJson;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OutboxStatus status = OutboxStatus.PENDING;
    private int retryCount = 0;
    private OffsetDateTime createdAt = OffsetDateTime.now();
    private OffsetDateTime processedAt;


    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID eventID() {
        return eventID;
    }

    public void setEventID(UUID eventID) {
        this.eventID = eventID;
    }

    public String aggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public UUID aggregateId() {
        return aggregateId;
    }

    public void setAggregateId(UUID aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String type() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String payloadJson() {
        return payloadJson;
    }

    public void setPayloadJson(String payloadJson) {
        this.payloadJson = payloadJson;
    }

    public OutboxStatus status() {
        return status;
    }

    public void setStatus(OutboxStatus outboxStatus) {
        this.status = outboxStatus;
    }

    public int retryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public OffsetDateTime createdAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime processedAt() {
        return processedAt;
    }

    public void setProcessedAt(OffsetDateTime processedAt) {
        this.processedAt = processedAt;
    }
}

