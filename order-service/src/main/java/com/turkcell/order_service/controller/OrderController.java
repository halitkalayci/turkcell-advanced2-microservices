package com.turkcell.order_service.controller;

import com.turkcell.order_service.client.CatalogClient;
import com.turkcell.order_service.contract.CatalogGetByIdProductContract;
import com.turkcell.order_service.dto.CreateOrderRequest;
import com.turkcell.order_service.dto.CreatedOrderResponse;
import com.turkcell.order_service.entity.Order;
import com.turkcell.order_service.entity.OrderItem;
import com.turkcell.order_service.repository.OrderItemRepository;
import com.turkcell.order_service.repository.OrderRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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

    public OrderController(CatalogClient catalogClient, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.catalogClient = catalogClient;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CreatedOrderResponse> create(@RequestBody @Valid List<CreateOrderRequest> createOrderRequests) {
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
        return ResponseEntity.ok(createdOrderResponse);
    }
}
