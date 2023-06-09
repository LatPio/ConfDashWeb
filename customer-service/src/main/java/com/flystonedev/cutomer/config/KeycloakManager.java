package com.flystonedev.cutomer.config;

import com.flystonedev.cutomer.config.KeycloakProperties;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakManager {

    private final KeycloakProperties keycloakProperties;

    public RealmResource getKeyCloakInstanceWithRealm(){
        return keycloakProperties.getKeycloakInstance().realm(keycloakProperties.getRealm());
    }
}
