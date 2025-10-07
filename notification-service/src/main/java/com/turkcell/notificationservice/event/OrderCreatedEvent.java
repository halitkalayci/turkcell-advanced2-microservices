package com.turkcell.notificationservice.event;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderId,
        UUID customerId,
        BigDecimal totalAmount
) {}
