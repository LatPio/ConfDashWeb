package com.flystonedev.customer.service;

import com.flystonedev.customer.DTO.InformationLinksAdminDTO;
import com.flystonedev.customer.DTO.InformationLinksDTO;
import com.flystonedev.customer.SampleData;
import com.flystonedev.customer.config.JwtConverter;
import com.flystonedev.customer.mapper.InformationLinksAdminMapper;
import com.flystonedev.customer.mapper.InformationLinksMapper;
import com.flystonedev.customer.model.InformationLinks;
import com.flystonedev.customer.repository.InformationLinksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class InformationLinksServiceTest implements SampleData {

    @InjectMocks
    InformationLinksService informationLinksService;

    @Mock
    InformationLinksRepository informationLinksRepository;

    @Mock
    JwtConverter jwtConverter;

    private final InformationLinksMapper informationLinksMapper = Mappers.getMapper(InformationLinksMapper.class);
    private final InformationLinksAdminMapper informationLinksAdminMapper = Mappers.getMapper(InformationLinksAdminMapper.class);



    @Test
    void canUserAddUserLinks() {
        var toSave = getSampleOFInformationLinksRequest();

        var authId = "bbbb";
        when(jwtConverter.getKeycloakUserID()).thenReturn(authId);

        informationLinksService.addUserLinks(toSave);

        ArgumentCaptor<InformationLinks> informationLinksArgumentCaptor = ArgumentCaptor.forClass(InformationLinks.class);
        verify(informationLinksRepository).save(informationLinksArgumentCaptor.capture());

        InformationLinks informationLinksCaptureValue = informationLinksArgumentCaptor.getValue();
        assertThat(informationLinksCaptureValue.getName()).isEqualTo(toSave.name());

//        verify(informationLinksRepository, times(1)).save(to);
//        verifyNoMoreInteractions(informationLinksRepository);

    }

    @Test
    void canUserGetUserLink() {
        var authId = "bbbb";
        var id = 1;
        var excepted = getSampleOfInformationLinksDTO();
        var repositoryCall= getSampleOFInformationLink();
        when(informationLinksRepository.findInformationLinksByIdAndAuthId(id, authId)).thenReturn(Optional.of(repositoryCall));
        when(jwtConverter.getKeycloakUserID()).thenReturn(authId);
        InformationLinksDTO userLink = informationLinksService.getUserLink(id);

        assertThat(userLink.getName()).isEqualTo(excepted.getName());

        verify(informationLinksRepository, times(1)).findInformationLinksByIdAndAuthId(id, authId);
        verifyNoMoreInteractions(informationLinksRepository);
    }

    @Test
    void canUserGetInformationLinksUserResponseList() {

        //given
        var authId = "bbbb";
        when(jwtConverter.getKeycloakUserID()).thenReturn(authId);

        //when
        informationLinksService.informationLinksUserResponseList();
        //then

        verify(informationLinksRepository, times(1)).findInformationLinksByAuthId(authId);
        verifyNoMoreInteractions(informationLinksRepository);

    }

    @Test
    void canUserUpdateUsersLink() {

        //given
        var toSave = getSampleOfInformationLinksDTO();
        var repositoryCall= getSampleOFInformationLink();
        when(informationLinksRepository.save(any(InformationLinks.class))).thenReturn(informationLinksMapper.map(toSave));
        when(jwtConverter.getKeycloakUserID()).thenReturn(repositoryCall.getAuthId());

        when(informationLinksRepository
                .findInformationLinksByIdAndAuthId(repositoryCall.getId(), repositoryCall.getAuthId())
                ).thenReturn(Optional.of(repositoryCall));
        //when
        InformationLinksDTO informationLinksDTO = informationLinksService.updateUsersLink(toSave);
        //then
        assertThat(informationLinksDTO).usingRecursiveComparison().isEqualTo(toSave);
        verify(informationLinksRepository,times(1)).save(any(InformationLinks.class));
        verifyNoMoreInteractions(informationLinksRepository);
    }

    @Test
    void deleteUserLink() {

        //given
        var sample = getSampleOFInformationLink();

        when(jwtConverter.getKeycloakUserID()).thenReturn(sample.getAuthId());
        when(informationLinksRepository
                .findInformationLinksByIdAndAuthId(sample.getId(), sample.getAuthId())
        ).thenReturn(Optional.of(sample));
        //when
        informationLinksService.deleteUserLink(sample.getId());
        //then
        verify(informationLinksRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(informationLinksRepository);


    }

    @Test
    void addAdminLinks() {
        //given
        var toSave = getSampleOfInformationLinksAdminRequest();
        //when
        informationLinksService.addAdminLinks(toSave);
        //then
        ArgumentCaptor<InformationLinks> informationLinksArgumentCaptor = ArgumentCaptor.forClass(InformationLinks.class);
        verify(informationLinksRepository).save(informationLinksArgumentCaptor.capture());

        InformationLinks informationLinksCaptureValue = informationLinksArgumentCaptor.getValue();
        assertThat(informationLinksCaptureValue.getName()).isEqualTo(toSave.name());
    }

    @Test
    void getAdminLink() {

        //given
        var repositoryCall= getSampleOFInformationLink();
        when(informationLinksRepository.findById(repositoryCall.getId())).thenReturn(Optional.of(repositoryCall));
        //when
        InformationLinksAdminDTO adminLink = informationLinksService.getAdminLink(repositoryCall.getId());
        //then
        assertThat(adminLink.getName()).isEqualTo(repositoryCall.getName());
        verify(informationLinksRepository, times(1)).findById(repositoryCall.getId());
        verifyNoMoreInteractions(informationLinksRepository);
    }

    @Test
    void informationLinksAdminResponseList() {

        //given

        //when
        informationLinksService.informationLinksAdminResponseList();
        //then
        verify(informationLinksRepository, times(1)).findAll();
        verifyNoMoreInteractions(informationLinksRepository);
    }

    @Test
    void updateLinkAdmin() {

        //given
        var toSave = getSampleOfInformationLinksAdminDTO();
        var repositoryCall= getSampleOFInformationLink();
        when(informationLinksRepository.save(any(InformationLinks.class))).thenReturn(informationLinksAdminMapper.map(toSave));
//        when(jwtConverter.getKeycloakUserID()).thenReturn(repositoryCall.getAuthId());

        when(informationLinksRepository
                .findById(repositoryCall.getId())
        ).thenReturn(Optional.of(repositoryCall));
        //when
        InformationLinksAdminDTO informationLinksAdminDTO = informationLinksService.updateLinkAdmin(toSave);
        //then
        assertThat(informationLinksAdminDTO).usingRecursiveComparison().isEqualTo(toSave);
        verify(informationLinksRepository,times(1)).save(any(InformationLinks.class));
        verifyNoMoreInteractions(informationLinksRepository);
    }

    @Test
    void deleteAdminLink() {

        //given
        var sample = getSampleOFInformationLink();

        //when
        informationLinksService.deleteAdminLink(sample.getId());
        //then
        verify(informationLinksRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(informationLinksRepository);
    }
}