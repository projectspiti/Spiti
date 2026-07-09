package com.dietapp.service.menu;

import com.dietapp.model.MenuImageResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuCDNProxyTest {

    @Test
    void returnsCachedImageAfterFirstBackendCall() {
        CountingBackendMenuImageService backend = new CountingBackendMenuImageService();
        MenuCDNProxy proxy = new MenuCDNProxy(backend);

        MenuImageResponse firstResponse = proxy.getImage("paneer_bowl");
        MenuImageResponse secondResponse = proxy.getImage("PANEER_BOWL");

        assertThat(firstResponse.source()).isEqualTo("BACKEND");
        assertThat(secondResponse.source()).isEqualTo("CACHE");
        assertThat(secondResponse.imageUrl()).isEqualTo(firstResponse.imageUrl());
        assertThat(backend.callCount).isEqualTo(1);
    }

    private static class CountingBackendMenuImageService implements MenuImageService {
        private int callCount;

        @Override
        public MenuImageResponse getImage(String dishCode) {
            callCount++;
            return new MenuImageResponse(
                    dishCode,
                    "https://images.dietapp.local/menu/" + dishCode.toLowerCase() + ".jpg",
                    "BACKEND"
            );
        }
    }
}
