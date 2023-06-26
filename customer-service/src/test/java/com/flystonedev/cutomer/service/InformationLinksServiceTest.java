package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.SampleData;
import com.flystonedev.cutomer.config.JwtConverter;
import com.flystonedev.cutomer.mapper.InformationLinksMapper;
import com.flystonedev.cutomer.repository.InformationLinksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class InformationLinksServiceTest implements SampleData {

    @InjectMocks
    InformationLinksService informationLinksService;

    @Mock
    InformationLinksRepository informationLinksRepository;

    @Mock
    JwtConverter jwtConverter;

    private final InformationLinksMapper informationLinksMapper = Mappers.getMapper(InformationLinksMapper.class);


    @Test
    void canUserAddUserLinks() {
        var toSave = getSampleOFInformationLinksRequest();
        var repository = getSampleOFInformationLink();
        when(jwtConverter.getKeycloakUserID()).thenReturn(repository.getAuthId());

        informationLinksService.addUserLinks(toSave);

        verify(informationLinksRepository, times(1)).save(repository);
        verifyNoMoreInteractions(informationLinksRepository);

    }

    @Test
    void getUserLink() {
    }

    @Test
    void informationLinksUserResponseList() {
    }

    @Test
    void updateUsersLink() {
    }

    @Test
    void deleteUserLink() {
    }

    @Test
    void addAdminLinks() {
    }

    @Test
    void getAdminLink() {
    }

    @Test
    void informationLinksAdminResponseList() {
    }

    @Test
    void updateLinkAdmin() {
    }

    @Test
    void deleteAdminLink() {
    }
}