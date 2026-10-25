package com.watchlist.movieapp.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {


    //interceptor - code that executes before the controller method is called, before HTTP requests.  It can be used for logging, authentication, etc.

    //before controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Request with prehandle: {} {}", request.getMethod(), request.getRequestURI());
        return true;  // true = continue, false = stop
    }

    // after controller, but before response
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("Response status with posthandle: {}", response.getStatus());
    }

    // after response/ even with exception
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex != null) {
            log.error("Request failed with afterCompletion", ex);
        }
    }
}
