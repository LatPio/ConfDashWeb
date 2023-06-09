package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.config.KeycloakManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakUserManagementService {

    private final KeycloakManager keycloakManager;

    public Integer createUser(UserRepresentation userRepresentation){
        Response response = keycloakManager.getKeyCloakInstanceWithRealm().users().create(userRepresentation);
        return response.getStatus();
    }

    public void updateUser(UserRepresentation userRepresentation){
        keycloakManager.getKeyCloakInstanceWithRealm().users().get(userRepresentation.getId()).update(userRepresentation);
    }

    public List<UserRepresentation> readUserByEmail(String email){
        return keycloakManager.getKeyCloakInstanceWithRealm().users().search(email);
    }

    public UserRepresentation readUser(String authId){
        try {
            UserResource userResource = keycloakManager.getKeyCloakInstanceWithRealm().users().get(authId);
            return userResource.toRepresentation();
        } catch (Exception e){
            throw new EntityNotFoundException("User not found by provided ID");
        }
    }





}
