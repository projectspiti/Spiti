package com.dietapp.service.menu;

import com.dietapp.model.MenuImageResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MenuCDNProxy implements MenuImageService {

    private static final Logger log = LoggerFactory.getLogger(MenuCDNProxy.class);

    private final MenuImageService backendMenuImageService;
    private final Map<String, MenuImageResponse> cache = new ConcurrentHashMap<>();

    public MenuCDNProxy(@Qualifier("backendMenuImageService") MenuImageService backendMenuImageService) {
        this.backendMenuImageService = backendMenuImageService;
    }

    @Override
    public MenuImageResponse getImage(String dishCode) {
        String normalizedDishCode = dishCode.toUpperCase();
        MenuImageResponse cachedImage = cache.get(normalizedDishCode);
        if (cachedImage != null) {
            log.info("Menu image cache hit dishCode={}", normalizedDishCode);
            return new MenuImageResponse(cachedImage.dishCode(), cachedImage.imageUrl(), "CACHE");
        }

        log.info("Menu image cache miss dishCode={}, fetching from backend", normalizedDishCode);
        MenuImageResponse backendImage = backendMenuImageService.getImage(normalizedDishCode);
        cache.put(normalizedDishCode, backendImage);
        log.info("Menu image cached dishCode={}", normalizedDishCode);
        return backendImage;
    }
}
