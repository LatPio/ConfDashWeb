package com.flystonedev.customer.service;

import com.flystonedev.customer.DTO.ProfilePhotoDTO;
import com.flystonedev.customer.SampleData;
import com.flystonedev.customer.config.JwtConverter;
import com.flystonedev.customer.mapper.ProfilePhotoMapper;
import com.flystonedev.customer.model.ProfilePhoto;
import com.flystonedev.customer.repository.CustomerRepository;
import com.flystonedev.customer.repository.ProfilePhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfilePhotoServiceTest implements SampleData {
    @InjectMocks
    private ProfilePhotoService profilePhotoService;
    @Mock
    private ProfilePhotoRepository profilePhotoRepository;
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private JwtConverter jwtConverter;

    private final ProfilePhotoMapper profilePhotoMapper = Mappers.getMapper(ProfilePhotoMapper.class);

    @Test
    void canSavePhoto() throws Exception{
        //given
        ProfilePhoto sampleOfProfilePhoto = getSampleOfProfilePhoto();
        sampleOfProfilePhoto.setId(null);
        Integer idOfCustomer = 1;
        when(jwtConverter.getKeycloakUserID()).thenReturn(sampleOfProfilePhoto.getAuthId());
        when(customerRepository.findById(idOfCustomer)).thenReturn(Optional.ofNullable(getSampleOfCustomers().get(1)));

        //when
        profilePhotoService.savePhoto(getSampleMultipartPhotoProfile(),idOfCustomer);
        //then
        ArgumentCaptor<ProfilePhoto> argumentCaptor = ArgumentCaptor.forClass(ProfilePhoto.class);
        verify(profilePhotoRepository).save(argumentCaptor.capture());

        ProfilePhoto profilePhotoArgumentCaptor = argumentCaptor.getValue();
        assertThat(profilePhotoArgumentCaptor).isEqualTo(sampleOfProfilePhoto);



    }

    @Test
    void canGetProfilePhotoDTOList() {
        //given

        //when
        profilePhotoService.profilePhotoDTOList();
        //then
        verify(profilePhotoRepository,times(1)).findAll();
        verifyNoMoreInteractions(profilePhotoRepository);
    }

    @Test
    void canUserGetOwnPhoto() {
        //given
        ProfilePhotoDTO sample = getSampleOfProfilePhotoDTO();
        when(jwtConverter.getKeycloakUserID()).thenReturn(sample.getAuthId());
        when(profilePhotoRepository.findProfilePhotoByIdAndAuthId(sample.getId(),sample.getAuthId()))
                .thenReturn(Optional.ofNullable(profilePhotoMapper.map(sample)));
        //when
        ProfilePhotoDTO userPhoto = profilePhotoService.getUserPhoto(sample.getId());
        //then
        assertThat(userPhoto).usingRecursiveComparison().isEqualTo(sample);
        verify(profilePhotoRepository,times(1)).findProfilePhotoByIdAndAuthId(anyInt(),anyString());
        verifyNoMoreInteractions(profilePhotoRepository);

    }

    @Test
    void updateUser() {
        ProfilePhotoDTO sample = getSampleOfProfilePhotoDTO();
        when(jwtConverter.getKeycloakUserID()).thenReturn(sample.getAuthId());
        when(profilePhotoRepository.findProfilePhotoByIdAndAuthId(sample.getId(),sample.getAuthId()))
                .thenReturn(Optional.ofNullable(profilePhotoMapper.map(sample)));
        when(profilePhotoRepository.save(any(ProfilePhoto.class))).thenReturn(profilePhotoMapper.map(sample));
        //when
        ProfilePhotoDTO userPhoto = profilePhotoService.updateUser(sample);
        //then
        assertThat(userPhoto).usingRecursiveComparison().isEqualTo(sample);
        verify(profilePhotoRepository,times(1)).save(profilePhotoMapper.map(sample));
        verifyNoMoreInteractions(profilePhotoRepository);
    }

    @Test
    void deleteUser() {
        //given
        ProfilePhotoDTO sample = getSampleOfProfilePhotoDTO();
        when(jwtConverter.getKeycloakUserID()).thenReturn(sample.getAuthId());
        when(profilePhotoRepository.findProfilePhotoByIdAndAuthId(sample.getId(),sample.getAuthId()))
                .thenReturn(Optional.ofNullable(profilePhotoMapper.map(sample)));
        //when
        profilePhotoService.deleteUser(sample.getId());
        //then
        verify(profilePhotoRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(profilePhotoRepository);
    }

    @Test
    void canAdminGetProfilePhoto() {
        //given
        ProfilePhotoDTO sample = getSampleOfProfilePhotoDTO();
        when(profilePhotoRepository.findById(anyInt()))
                .thenReturn(Optional.ofNullable(profilePhotoMapper.map(sample)));
        //when
        ProfilePhotoDTO userPhoto = profilePhotoService.getAdmin(sample.getId());
        //then
        assertThat(userPhoto).usingRecursiveComparison().isEqualTo(sample);
        verify(profilePhotoRepository,times(1)).findById(anyInt());
        verifyNoMoreInteractions(profilePhotoRepository);
    }

    @Test
    void updateAdmin() {
        ProfilePhotoDTO sample = getSampleOfProfilePhotoDTO();
        when(profilePhotoRepository.findById(anyInt()))
                .thenReturn(Optional.ofNullable(profilePhotoMapper.map(sample)));
        when(profilePhotoRepository.save(any(ProfilePhoto.class))).thenReturn(profilePhotoMapper.map(sample));
        //when
        ProfilePhotoDTO userPhoto = profilePhotoService.updateAdmin(sample);
        //then
        assertThat(userPhoto).usingRecursiveComparison().isEqualTo(sample);
        verify(profilePhotoRepository,times(1)).save(profilePhotoMapper.map(sample));
        verifyNoMoreInteractions(profilePhotoRepository);
    }

    @Test
    void deleteAdmin() {
        //given
        ProfilePhotoDTO sample = getSampleOfProfilePhotoDTO();
        //when
        profilePhotoService.deleteAdmin(sample.getId());
        //then
        verify(profilePhotoRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(profilePhotoRepository);
    }
}