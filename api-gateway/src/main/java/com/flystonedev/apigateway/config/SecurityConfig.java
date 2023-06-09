package com.flystonedev.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){

        serverHttpSecurity
                .csrf()
                .disable()
                .authorizeExchange(
                        authorizeExchangeSpec -> authorizeExchangeSpec
                                .pathMatchers("/eureka/**")
                                .permitAll()
                                .pathMatchers("/api/v1/customer/new").permitAll()
                                .anyExchange()
                                .authenticated()
                )
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt)
                ;

        serverHttpSecurity
                .oauth2Login();

        return serverHttpSecurity.build();
    }

}
