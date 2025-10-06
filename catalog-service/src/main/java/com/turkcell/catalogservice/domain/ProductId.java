package com.turkcell.catalogservice.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

// VO
// Serializable -> JPA,Caching,Network,MQ, -> Kaydedilebilir/Yüklenebilir.
public record ProductId(UUID value) implements Serializable {
    public ProductId {
        Objects.requireNonNull(value, "Value cannot be null");
    }

    public static ProductId newId() { return new ProductId(UUID.randomUUID()); }
}

// TODO: Anlat.
// Primitive Obsession => Code Smell