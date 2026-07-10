package com.dietapp.service;

import com.dietapp.model.CartCommandRequest;
import com.dietapp.model.CartCommandResponse;
import com.dietapp.model.CartUndoResponse;
import com.dietapp.service.cart.Cart;
import com.dietapp.service.cart.command.AddMealCommand;
import com.dietapp.service.cart.command.ApplyCouponCommand;
import com.dietapp.service.cart.command.CartCommand;
import com.dietapp.service.cart.command.RemoveMealCommand;
import com.dietapp.service.cart.command.SwapMealCommand;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CartCommandExecutorService {

    private static final Logger log = LoggerFactory.getLogger(CartCommandExecutorService.class);

    private final Map<String, Cart> carts = new ConcurrentHashMap<>();
    private final Map<String, CartCommandResponse> processedCommands = new ConcurrentHashMap<>();
    private final Map<String, Deque<CartCommand>> commandHistoryByCart = new ConcurrentHashMap<>();

    public CartCommandResponse execute(CartCommandRequest request) {
        validate(request);
        if (processedCommands.containsKey(request.idempotencyKey())) {
            log.info("Cart command duplicate ignored cartId={}, commandType={}, idempotencyKey={}",
                    request.cartId(), request.commandType(), request.idempotencyKey());
            return processedCommands.get(request.idempotencyKey());
        }

        Cart cart = carts.computeIfAbsent(request.cartId(), Cart::new);
        CartCommand command = createCommand(cart, request);
        log.info("Executing cart command cartId={}, command={}, idempotencyKey={}",
                request.cartId(), command.commandName(), command.idempotencyKey());
        CartCommandResponse response = command.execute();
        processedCommands.put(command.idempotencyKey(), response);
        commandHistoryByCart.computeIfAbsent(request.cartId(), key -> new ArrayDeque<>()).push(command);
        return response;
    }

    public CartUndoResponse undoLast(String cartId) {
        if (cartId == null || cartId.isBlank()) {
            throw new IllegalArgumentException("cartId is required");
        }
        Deque<CartCommand> history = commandHistoryByCart.get(cartId);
        if (history == null || history.isEmpty()) {
            throw new IllegalStateException("No cart command available to undo for cartId " + cartId);
        }

        CartCommand command = history.pop();
        log.info("Undoing cart command cartId={}, command={}", cartId, command.commandName());
        CartCommandResponse response = command.undo();
        return new CartUndoResponse(
                response.cartId(),
                command.commandName(),
                response.meals(),
                response.couponCode(),
                response.message()
        );
    }

    private CartCommand createCommand(Cart cart, CartCommandRequest request) {
        return switch (request.commandType()) {
            case ADD_MEAL -> new AddMealCommand(cart, requireValue(request.mealName(), "mealName"), request.idempotencyKey());
            case REMOVE_MEAL -> new RemoveMealCommand(cart, requireValue(request.mealName(), "mealName"), request.idempotencyKey());
            case SWAP_MEAL -> new SwapMealCommand(
                    cart,
                    requireValue(request.mealName(), "mealName"),
                    requireValue(request.replacementMealName(), "replacementMealName"),
                    request.idempotencyKey()
            );
            case APPLY_COUPON -> new ApplyCouponCommand(cart, requireValue(request.couponCode(), "couponCode"), request.idempotencyKey());
        };
    }

    private void validate(CartCommandRequest request) {
        if (request.cartId() == null || request.cartId().isBlank()) {
            throw new IllegalArgumentException("cartId is required");
        }
        if (request.commandType() == null) {
            throw new IllegalArgumentException("commandType is required");
        }
        if (request.idempotencyKey() == null || request.idempotencyKey().isBlank()) {
            throw new IllegalArgumentException("idempotencyKey is required");
        }
    }

    private String requireValue(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        return value;
    }
}
