package com.winter.market.core.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public OpenAPI api() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("GeekBrains - Winter Market - Продуктовый сервис")
                                .version("1")
                );
    }
}
