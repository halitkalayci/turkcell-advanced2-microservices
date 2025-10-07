package com.turkcell.order_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.order_service.client.CatalogClient;
import com.turkcell.order_service.contract.CatalogGetByIdProductContract;
import com.turkcell.order_service.dto.CreateOrderRequest;
import com.turkcell.order_service.dto.CreatedOrderResponse;
import com.turkcell.order_service.entity.Order;
import com.turkcell.order_service.entity.OrderItem;
import com.turkcell.order_service.event.OrderCreatedEvent;
import com.turkcell.order_service.messaging.outbox.OutboxMessage;
import com.turkcell.order_service.repository.OrderItemRepository;
import com.turkcell.order_service.repository.OrderRepository;
import com.turkcell.order_service.repository.OutboxRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final CatalogClient catalogClient;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;

    public OrderController(CatalogClient catalogClient, OrderRepository orderRepository, OrderItemRepository orderItemRepository, ObjectMapper objectMapper, OutboxRepository outboxRepository) {
        this.catalogClient = catalogClient;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.objectMapper = objectMapper;
        this.outboxRepository = outboxRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CreatedOrderResponse> create(@RequestBody @Valid List<CreateOrderRequest> createOrderRequests)
            throws JsonProcessingException {
        Order order = new Order();
        order.setCustomerId(UUID.randomUUID()); // TODO: Identity
        order.setOrderItems(new ArrayList<>());
        order = orderRepository.save(order);
        for (CreateOrderRequest createOrderRequest : createOrderRequests) {
            CatalogGetByIdProductContract product = catalogClient.get(createOrderRequest.getProductId());
            if(createOrderRequest.getQuantity() > product.stock())
                throw new IllegalArgumentException("Quantity exceeds stock");

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.id());
            orderItem.setQuantity(createOrderRequest.getQuantity());
            orderItem.setUnitPrice(product.price());
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
            order.orderItems().add(orderItem);
        }
        order.setTotalPrice(order
                .orderItems()
                .stream()
                .map(i -> i.unitPrice().multiply(BigDecimal.valueOf(i.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        order = orderRepository.save(order);
        CreatedOrderResponse createdOrderResponse = new CreatedOrderResponse();
        createdOrderResponse.setId(order.id());
        createdOrderResponse.setTotalAmount(order.totalPrice());

        // Kafka event fırlatılması. ❌
        // Outbox patterna kafkaya gönderilecek yeni bir event oldugu bilgisi. ✅

        OrderCreatedEvent event = new OrderCreatedEvent(
                order.id(),
                order.customerId(),
                order.totalPrice()
        );

        OutboxMessage outboxMessage = new OutboxMessage();
        outboxMessage.setAggregateType("ORDER");
        outboxMessage.setAggregateId(order.id());
        outboxMessage.setType("ORDER_CREATED");
        outboxMessage.setPayloadJson(objectMapper.writeValueAsString(event));

        outboxRepository.save(outboxMessage);

        return ResponseEntity.ok(createdOrderResponse);
    }
}
