package com.dietapp.service.menu;

import com.dietapp.model.MenuImageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("backendMenuImageService")
public class BackendMenuImageService implements MenuImageService {

    private static final Logger log = LoggerFactory.getLogger(BackendMenuImageService.class);

    @Override
    public MenuImageResponse getImage(String dishCode) {
        log.info("Fetching menu image from backend image store dishCode={}", dishCode);
        return new MenuImageResponse(
                dishCode,
                "https://images.dietapp.local/menu/" + dishCode.toLowerCase() + ".jpg",
                "BACKEND"
        );
    }
}
