package com.dietapp.service.order.visitor;

public interface OrderVisitable {

    void accept(OrderVisitor visitor);
}
