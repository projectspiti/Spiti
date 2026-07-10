package com.dietapp.service.cart.command;

import com.dietapp.model.CartCommandResponse;
import com.dietapp.model.CartCommandType;
import com.dietapp.service.cart.Cart;

public class RemoveMealCommand implements CartCommand {

    private final Cart cart;
    private final String mealName;
    private final String idempotencyKey;

    public RemoveMealCommand(Cart cart, String mealName, String idempotencyKey) {
        this.cart = cart;
        this.mealName = mealName;
        this.idempotencyKey = idempotencyKey;
    }

    @Override
    public String idempotencyKey() {
        return idempotencyKey;
    }

    @Override
    public String commandName() {
        return "RemoveMealCommand";
    }

    @Override
    public CartCommandResponse execute() {
        cart.removeMeal(mealName);
        return response("Meal removed from cart: " + mealName);
    }

    @Override
    public CartCommandResponse undo() {
        cart.addMeal(mealName);
        return response("Undo completed. Meal restored to cart: " + mealName);
    }

    private CartCommandResponse response(String message) {
        return new CartCommandResponse(
                cart.cartId(),
                CartCommandType.REMOVE_MEAL,
                idempotencyKey,
                "SUCCESS",
                cart.meals(),
                cart.couponCode(),
                message
        );
    }
}
