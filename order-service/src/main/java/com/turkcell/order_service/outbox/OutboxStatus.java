package com.turkcell.order_service.outbox;

public enum OutboxStatus {
    PENDING,
    SENT,
    FAILED
}
