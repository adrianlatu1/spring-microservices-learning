package com.watchlist.movieapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Movie WatchList API")
                        .version("1.0")
                        .description("API for managing movies and series in a watchlist")
                        .contact(new Contact()
                                .name("Adrian Latu")
                                .email("adrian.latu@selir.com")
                                .url("https://https://www.mcdonalds.it")
                        )
                );
    }
}