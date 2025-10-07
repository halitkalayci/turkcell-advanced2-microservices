package com.turkcell.order_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreatedOrderResponse {
    private UUID id;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public BigDecimal totalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    public LocalDateTime createdAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
