package com.example.MatrimonyProject.utilits;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class PageableConfig {

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer pagerCustomizer() {
        return resolver -> {
            resolver.setMaxPageSize(50); // hard cap
            resolver.setOneIndexedParameters(false); // keep 0-based
        };
    }



}
