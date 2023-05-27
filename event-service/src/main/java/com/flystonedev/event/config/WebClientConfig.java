package com.flystonedev.event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientConfig {

    @Bean
    public WebClient webClient (){
        return WebClient.builder().build();
    }
}
