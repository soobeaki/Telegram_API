package com.telegram.api.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

/**
 * WebConfig
 */
@Configuration
public class WebConfig implements WebFluxConfigurer {

    @Bean
    WebFilter redirectFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            String path = exchange.getRequest().getURI().getPath();
            if (path.startsWith("/swagger-ui/index.html")) {
                return Mono.defer(() -> {
                    exchange.getResponse().setStatusCode(HttpStatus.FOUND);
                    exchange.getResponse().getHeaders().setLocation(URI.create("/webjars/swagger-ui/index.html"));
                    return exchange.getResponse().setComplete();
                });
            }
            return chain.filter(exchange);
        };
    }
}