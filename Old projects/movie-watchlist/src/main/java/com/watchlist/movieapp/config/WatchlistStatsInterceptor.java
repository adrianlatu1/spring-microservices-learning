package com.watchlist.movieapp.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.watchlist.movieapp.service.UserService;

@RequiredArgsConstructor
@Component
@Slf4j
public class WatchlistStatsInterceptor implements HandlerInterceptor {


    private final UserService userService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        log.info(" WatchlistStatsInterceptor - URI: {}, Status: {}",
                request.getRequestURI(), response.getStatus());
        if (request.getRequestURI().contains("/watchlist-items") && response.getStatus() == 200) {
            String userId = request.getHeader("User-Id");
            if (userId != null) {
                userService.incrementViewCount(userId);
                log.info("User {} accessed watchlist", userId);
            }
        }
    }

}
