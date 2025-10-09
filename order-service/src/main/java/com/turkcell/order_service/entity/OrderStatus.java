package com.turkcell.order_service.entity;

public enum OrderStatus {
    PENDING,
    PAYMENT_FAILED,
    WAITING_SHIPMENT,
    SHIPPED,
    COMPLETED,
}
