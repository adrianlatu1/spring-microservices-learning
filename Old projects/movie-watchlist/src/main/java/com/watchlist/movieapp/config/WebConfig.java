package com.watchlist.movieapp.config;

import com.watchlist.movieapp.config.LoggingInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // configurations class
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {


    private final LoggingInterceptor loggingInterceptor;
    private final WatchlistStatsInterceptor watchlistStatsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //logging  interceptor pentru toate API-urile
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/api/**");

        // watchlist stats interceptor
        registry.addInterceptor(watchlistStatsInterceptor)
                .addPathPatterns("/api/watchlist-items/**"); //
                //.excludePathPatterns("/api/atchlist-items/user/2");   you can also exclude certain paths if needed
    }
}