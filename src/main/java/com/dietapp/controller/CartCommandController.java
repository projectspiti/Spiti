package com.dietapp.controller;

import com.dietapp.model.CartCommandRequest;
import com.dietapp.model.CartCommandResponse;
import com.dietapp.model.CartUndoResponse;
import com.dietapp.service.CartCommandExecutorService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
public class CartCommandController {

    private final CartCommandExecutorService cartCommandExecutorService;

    public CartCommandController(CartCommandExecutorService cartCommandExecutorService) {
        this.cartCommandExecutorService = cartCommandExecutorService;
    }

    @PostMapping("/commands")
    public CartCommandResponse execute(@RequestBody CartCommandRequest request) {
        return cartCommandExecutorService.execute(request);
    }

    @PostMapping("/{cartId}/undo")
    public CartUndoResponse undoLast(@PathVariable String cartId) {
        return cartCommandExecutorService.undoLast(cartId);
    }
}
