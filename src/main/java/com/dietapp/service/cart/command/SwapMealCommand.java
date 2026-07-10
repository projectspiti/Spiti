package com.dietapp.service.cart.command;

import com.dietapp.model.CartCommandResponse;
import com.dietapp.model.CartCommandType;
import com.dietapp.service.cart.Cart;

public class SwapMealCommand implements CartCommand {

    private final Cart cart;
    private final String mealName;
    private final String replacementMealName;
    private final String idempotencyKey;

    public SwapMealCommand(Cart cart, String mealName, String replacementMealName, String idempotencyKey) {
        this.cart = cart;
        this.mealName = mealName;
        this.replacementMealName = replacementMealName;
        this.idempotencyKey = idempotencyKey;
    }

    @Override
    public String idempotencyKey() {
        return idempotencyKey;
    }

    @Override
    public String commandName() {
        return "SwapMealCommand";
    }

    @Override
    public CartCommandResponse execute() {
        cart.swapMeal(mealName, replacementMealName);
        return response("Meal swapped from " + mealName + " to " + replacementMealName);
    }

    @Override
    public CartCommandResponse undo() {
        cart.swapMeal(replacementMealName, mealName);
        return response("Undo completed. Meal swapped back to " + mealName);
    }

    private CartCommandResponse response(String message) {
        return new CartCommandResponse(
                cart.cartId(),
                CartCommandType.SWAP_MEAL,
                idempotencyKey,
                "SUCCESS",
                cart.meals(),
                cart.couponCode(),
                message
        );
    }
}
