package com.turkcell.order_service.event;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentCompletedEvent(
        UUID paymentId,
        UUID orderId,
        UUID customerId,
        BigDecimal amount
) {
}
