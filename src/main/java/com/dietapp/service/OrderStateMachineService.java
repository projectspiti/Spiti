package com.dietapp.service;

import com.dietapp.model.OrderObserverNotification;
import com.dietapp.model.OrderStateTransitionResponse;
import com.dietapp.model.OrderStatus;
import com.dietapp.model.OrderStatusChangedEvent;
import com.dietapp.service.order.observer.OrderStatusObservable;
import com.dietapp.service.order.state.ConfirmedOrderState;
import com.dietapp.service.order.state.DeliveredOrderState;
import com.dietapp.service.order.state.OrderState;
import com.dietapp.service.order.state.OutForDeliveryOrderState;
import com.dietapp.service.order.state.PreparingOrderState;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderStateMachineService {

    private static final Logger log = LoggerFactory.getLogger(OrderStateMachineService.class);

    private final Map<String, OrderStatus> orderStatuses = new ConcurrentHashMap<>();
    private final OrderStatusObservable orderStatusObservable;

    public OrderStateMachineService(OrderStatusObservable orderStatusObservable) {
        this.orderStatusObservable = orderStatusObservable;
    }

    public OrderStateTransitionResponse confirmOrder(String orderId) {
        validateOrderId(orderId);
        OrderStatus previousStatus = orderStatuses.putIfAbsent(orderId, OrderStatus.CONFIRMED);
        if (previousStatus != null) {
            throw new IllegalStateException("Order already exists with state " + previousStatus);
        }
        log.info("Order confirmed orderId={}, currentStatus={}", orderId, OrderStatus.CONFIRMED);
        List<OrderObserverNotification> observerNotifications = orderStatusObservable.notifyObservers(
                new OrderStatusChangedEvent(orderId, null, OrderStatus.CONFIRMED, Instant.now())
        );
        return new OrderStateTransitionResponse(
                orderId,
                null,
                OrderStatus.CONFIRMED,
                "Order confirmed",
                observerNotifications
        );
    }

    public OrderStateTransitionResponse startPreparing(String orderId) {
        return transition(orderId, OrderState::startPreparing, "Order moved to preparing");
    }

    public OrderStateTransitionResponse markOutForDelivery(String orderId) {
        return transition(orderId, OrderState::markOutForDelivery, "Order moved out for delivery");
    }

    public OrderStateTransitionResponse markDelivered(String orderId) {
        return transition(orderId, OrderState::markDelivered, "Order delivered");
    }

    public OrderStatus currentStatus(String orderId) {
        validateOrderId(orderId);
        return orderStatuses.getOrDefault(orderId, OrderStatus.CONFIRMED);
    }

    private OrderStateTransitionResponse transition(
            String orderId,
            Function<OrderState, OrderStatus> transition,
            String message
    ) {
        validateOrderId(orderId);
        OrderStatus previousStatus = currentStatus(orderId);
        OrderState state = toState(previousStatus);
        OrderStatus nextStatus = transition.apply(state);
        orderStatuses.put(orderId, nextStatus);
        log.info("Order state changed orderId={}, previousStatus={}, currentStatus={}",
                orderId, previousStatus, nextStatus);
        List<OrderObserverNotification> observerNotifications = orderStatusObservable.notifyObservers(
                new OrderStatusChangedEvent(orderId, previousStatus, nextStatus, Instant.now())
        );
        return new OrderStateTransitionResponse(orderId, previousStatus, nextStatus, message, observerNotifications);
    }

    private OrderState toState(OrderStatus status) {
        return switch (status) {
            case CONFIRMED -> new ConfirmedOrderState();
            case PREPARING -> new PreparingOrderState();
            case OUT_FOR_DELIVERY -> new OutForDeliveryOrderState();
            case DELIVERED -> new DeliveredOrderState();
        };
    }

    private void validateOrderId(String orderId) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("orderId is required");
        }
    }
}
