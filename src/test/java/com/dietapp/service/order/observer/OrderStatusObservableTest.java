package com.dietapp.service.order.observer;

import com.dietapp.model.OrderObserverNotification;
import com.dietapp.model.OrderStatus;
import com.dietapp.model.OrderStatusChangedEvent;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderStatusObservableTest {

    @Test
    void notifiesEveryRegisteredObserverWhenOrderStatusChanges() {
        OrderStatusObservable observable = new OrderStatusObservable(List.of(
                new RecordingObserver("KitchenDashboard"),
                new RecordingObserver("DeliveryPartnerService"),
                new RecordingObserver("UserPushNotification")
        ));

        List<OrderObserverNotification> notifications = observable.notifyObservers(
                new OrderStatusChangedEvent(
                        "ORDER-101",
                        OrderStatus.CONFIRMED,
                        OrderStatus.PREPARING,
                        Instant.parse("2026-07-09T10:00:00Z")
                )
        );

        assertThat(notifications)
                .extracting(OrderObserverNotification::observerName)
                .containsExactly("KitchenDashboard", "DeliveryPartnerService", "UserPushNotification");
    }

    private record RecordingObserver(String name) implements OrderStatusObserver {

        @Override
        public OrderObserverNotification onStatusChanged(OrderStatusChangedEvent event) {
            return new OrderObserverNotification(name, name + " received " + event.currentStatus());
        }
    }
}
