package com.dietapp.service.cart.command;

import com.dietapp.model.CartCommandResponse;

public interface CartCommand {

    String idempotencyKey();

    String commandName();

    CartCommandResponse execute();

    CartCommandResponse undo();
}
