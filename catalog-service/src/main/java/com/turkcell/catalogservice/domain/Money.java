package com.turkcell.catalogservice.domain;

import java.math.BigDecimal;
import java.util.Objects;

// Java 16+
// VO => Immutable (değişmez) olmalıdır.
public record Money(BigDecimal amount, String currency) {
    public Money {
        Objects.requireNonNull(currency, "Currency cannot be null");
        Objects.requireNonNull(amount, "Amount cannot be null");
        if(amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
    }
}
