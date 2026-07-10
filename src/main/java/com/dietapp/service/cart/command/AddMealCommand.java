package com.dietapp.service.cart.command;

import com.dietapp.model.CartCommandResponse;
import com.dietapp.model.CartCommandType;
import com.dietapp.service.cart.Cart;

public class AddMealCommand implements CartCommand {

    private final Cart cart;
    private final String mealName;
    private final String idempotencyKey;

    public AddMealCommand(Cart cart, String mealName, String idempotencyKey) {
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
        return "AddMealCommand";
    }

    @Override
    public CartCommandResponse execute() {
        cart.addMeal(mealName);
        return response("Meal added to cart: " + mealName);
    }

    @Override
    public CartCommandResponse undo() {
        cart.removeMeal(mealName);
        return response("Undo completed. Meal removed from cart: " + mealName);
    }

    private CartCommandResponse response(String message) {
        return new CartCommandResponse(
                cart.cartId(),
                CartCommandType.ADD_MEAL,
                idempotencyKey,
                "SUCCESS",
                cart.meals(),
                cart.couponCode(),
                message
        );
    }
}
