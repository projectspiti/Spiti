package com.dietapp.service.cart.command;

import com.dietapp.model.CartCommandResponse;
import com.dietapp.model.CartCommandType;
import com.dietapp.service.cart.Cart;

public class ApplyCouponCommand implements CartCommand {

    private final Cart cart;
    private final String couponCode;
    private final String idempotencyKey;
    private String previousCouponCode;

    public ApplyCouponCommand(Cart cart, String couponCode, String idempotencyKey) {
        this.cart = cart;
        this.couponCode = couponCode;
        this.idempotencyKey = idempotencyKey;
    }

    @Override
    public String idempotencyKey() {
        return idempotencyKey;
    }

    @Override
    public String commandName() {
        return "ApplyCouponCommand";
    }

    @Override
    public CartCommandResponse execute() {
        previousCouponCode = cart.couponCode();
        cart.applyCoupon(couponCode);
        return response("Coupon applied to cart: " + couponCode);
    }

    @Override
    public CartCommandResponse undo() {
        cart.restoreCoupon(previousCouponCode);
        return response("Undo completed. Previous coupon restored");
    }

    private CartCommandResponse response(String message) {
        return new CartCommandResponse(
                cart.cartId(),
                CartCommandType.APPLY_COUPON,
                idempotencyKey,
                "SUCCESS",
                cart.meals(),
                cart.couponCode(),
                message
        );
    }
}
