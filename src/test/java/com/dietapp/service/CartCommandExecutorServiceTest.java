package com.dietapp.service;

import com.dietapp.model.CartCommandRequest;
import com.dietapp.model.CartCommandResponse;
import com.dietapp.model.CartCommandType;
import com.dietapp.model.CartUndoResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartCommandExecutorServiceTest {

    @Test
    void ignoresDuplicateCommandWithSameIdempotencyKey() {
        CartCommandExecutorService service = new CartCommandExecutorService();
        CartCommandRequest request = new CartCommandRequest(
                "CART-101",
                CartCommandType.ADD_MEAL,
                "CMD-ADD-PANEER-1",
                "Paneer Bowl",
                null,
                null
        );

        CartCommandResponse firstResponse = service.execute(request);
        CartCommandResponse duplicateResponse = service.execute(request);

        assertThat(firstResponse.meals()).containsExactly("Paneer Bowl");
        assertThat(duplicateResponse.meals()).containsExactly("Paneer Bowl");
        assertThat(duplicateResponse.message()).isEqualTo(firstResponse.message());
    }

    @Test
    void undoRestoresPreviousCartState() {
        CartCommandExecutorService service = new CartCommandExecutorService();
        service.execute(new CartCommandRequest(
                "CART-102",
                CartCommandType.ADD_MEAL,
                "CMD-ADD-IDLI-1",
                "Millet Idli",
                null,
                null
        ));

        CartUndoResponse undoResponse = service.undoLast("CART-102");

        assertThat(undoResponse.undoneCommand()).isEqualTo("AddMealCommand");
        assertThat(undoResponse.meals()).isEmpty();
    }
}
