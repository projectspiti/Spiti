package com.dietapp.service.order.state;

import com.dietapp.model.OrderStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderStateTest {

    @Test
    void allowsOnlyValidStateTransitions() {
        OrderState confirmed = new ConfirmedOrderState();
        OrderState preparing = new PreparingOrderState();
        OrderState outForDelivery = new OutForDeliveryOrderState();

        assertThat(confirmed.startPreparing()).isEqualTo(OrderStatus.PREPARING);
        assertThat(preparing.markOutForDelivery()).isEqualTo(OrderStatus.OUT_FOR_DELIVERY);
        assertThat(outForDelivery.markDelivered()).isEqualTo(OrderStatus.DELIVERED);
    }

    @Test
    void rejectsInvalidStateTransitions() {
        OrderState confirmed = new ConfirmedOrderState();
        OrderState delivered = new DeliveredOrderState();

        assertThatThrownBy(confirmed::markDelivered)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot mark delivered from CONFIRMED state");

        assertThatThrownBy(delivered::startPreparing)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot start preparing from DELIVERED state");
    }
}
