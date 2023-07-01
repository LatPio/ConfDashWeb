package com.flystonedev.customer.config;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KeycloakProperties {

    @Value("${app.config.keycloak.server-url}")
    private String serverURL;
    @Value("${app.config.keycloak.realm}")
    private String realm;
    @Value("${app.config.keycloak.client-id}")
    private String clientId;
    @Value("${app.config.keycloak.client-secret}")
    private String clientSecret;

    private Keycloak keycloakInstance = null;

    public Keycloak getKeycloakInstance(){
        keycloakInstance = KeycloakBuilder
                .builder()
                .serverUrl(serverURL)
                .realm(realm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
        return keycloakInstance;
    }

    public String getRealm() {
        return this.realm;
    }
}
