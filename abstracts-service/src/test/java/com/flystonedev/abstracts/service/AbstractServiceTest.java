package com.flystonedev.abstracts.service;

import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.config.JwtConverter;
import com.flystonedev.abstracts.config.KeycloakTestContainers;
import com.flystonedev.abstracts.mapper.AbstractMapper;
import com.flystonedev.abstracts.mapper.AbstractSimpleMapper;
import com.flystonedev.abstracts.model.AbstractsEntity;
import com.flystonedev.abstracts.repository.AbstractRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.field.BaseDurationField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import java.security.Principal;
import java.util.*;

import static com.flystonedev.abstracts.config.JwtConverter.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbstractServiceTest   implements SampleData {   //extends KeycloakTestContainers

    @InjectMocks
    private AbstractService underTest;
    @Mock
    private  AbstractRepository abstractRepository;

    @Mock
    private JwtConverter jwtConverter;

    private final AbstractMapper abstractMapper = Mappers.getMapper(AbstractMapper.class);
    private final AbstractSimpleMapper abstractSimpleMapper = Mappers.getMapper(AbstractSimpleMapper.class);


    @BeforeEach
    void setUp() {

        }

    @AfterEach
    void tearDown() {

    }

    @Test
    void canCreateNewAbstractCreatedByUser() {
        //given
        AbstractDTO expected = getSampleOfOneAbstractDTO();
        expected.setId(null);
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());

        underTest.createUserAbstract(expected);
        //then
        ArgumentCaptor<AbstractsEntity> abstractsEntityArgumentCaptor = ArgumentCaptor.forClass(AbstractsEntity.class);
        verify(abstractRepository).save(abstractsEntityArgumentCaptor.capture());

        AbstractsEntity abstractsEntityArgumentCaptorValue = abstractsEntityArgumentCaptor.getValue();
        assertThat(abstractsEntityArgumentCaptorValue).isEqualTo(abstractMapper.map(expected));
    }

    @Test
    void userCanGetAllAbstractsCreatedByYourself() {
        //given
        String authId = "vava-dddd";
        when(jwtConverter.getKeycloakUserID()).thenReturn(authId);

        //when
        underTest.abstractUsersDTOList();
        //then
        verify(abstractRepository, times(1)).findAbstractsEntitiesByAuthId(authId);
        verifyNoMoreInteractions(abstractRepository);
    }

    @Test
    void userCanGetAllAcceptedAbstractToList() {
        //given

        //when
        underTest.abstractUserAcceptedDTOList();
        //then
        verify(abstractRepository, times(1)).findAbstractsEntitiesByAccepted(true);
        verifyNoMoreInteractions(abstractRepository);
    }

    @Test
    @WithAnonymousUser
    void userCanGetYourselfAbstract() {

        final var expected = getSampleOfOneAbstractEntity();
        when(abstractRepository.findByIdAndAuthId(expected.getId(), expected.getAuthId())).thenReturn(Optional.ofNullable(expected));
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        //when

        final var actual = underTest.getUsersAbstract(expected.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(abstractRepository, times(1)).findByIdAndAuthId(expected.getId(), expected.getAuthId());
        verifyNoMoreInteractions(abstractRepository);
    }

    @Test
    void userCanUpdateOwnAbstract() {
        //given
        final var toSave = getSampleOfOneAbstractDTO();
        when(jwtConverter.getKeycloakUserID()).thenReturn(toSave.getAuthId());
        when(abstractRepository.save(any(AbstractsEntity.class))).thenReturn(abstractMapper.map(toSave));
        when(abstractRepository.findByIdAndAuthId(toSave.getId(), toSave.getAuthId())).thenReturn(Optional.ofNullable(abstractMapper.map(toSave)));
        //when
        final var actual = underTest.updateUsersAbstract(toSave);
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(toSave);
        verify(abstractRepository, times(1)).save(any(AbstractsEntity.class));
        verifyNoMoreInteractions(abstractRepository);
    }

    @Test
    void userCanDeleteOwnAbstract() {
        //given
        var abstractDTO = getSampleOfOneAbstractDTO();
        when(jwtConverter.getKeycloakUserID()).thenReturn(abstractDTO.getAuthId());
        when(abstractRepository.findByIdAndAuthId(abstractDTO.getId(), abstractDTO.getAuthId())).thenReturn(Optional.ofNullable(abstractMapper.map(abstractDTO)));//        when(abstractRepository.deleteByIdAndAuthId(anyInt(),anyString())).then(invocationOnMock -> doNothing());
//        doNothing().when(abstractRepository).deleteByIdAndAuthId(anyInt(),anyString());
        //when
        //then
        underTest.deleteUsersAbstract(1);
        verify(abstractRepository, times(1)).deleteByIdAndAuthId(anyInt(),anyString());
        verifyNoMoreInteractions(abstractRepository);

    }

    @Test
    void adminCanCreateAbstract() {
        //when
        AbstractDTO toTest = getSampleOfOneAbstractDTO();
        toTest.setId(null);
        underTest.createAdminAbstract(toTest);
        //then
        ArgumentCaptor<AbstractsEntity> abstractsEntityArgumentCaptor = ArgumentCaptor.forClass(AbstractsEntity.class);
        verify(abstractRepository).save(abstractsEntityArgumentCaptor.capture());

        AbstractsEntity abstractsEntityArgumentCaptorValue = abstractsEntityArgumentCaptor.getValue();
        assertThat(abstractsEntityArgumentCaptorValue).isEqualTo(abstractMapper.map(toTest));
    }

    @Test
    void adminCanGetAbstractDTOList() {
        //when
        underTest.abstractAdminDTOList();
        //then
        verify(abstractRepository).findAll();
    }

    @Test
    void adminCanGetAbstract(){
        //given
        final var expected = getSampleOfOneAbstractEntity();
        when(abstractRepository.findById(expected.getId())).thenReturn(Optional.ofNullable(expected));
        //when

        final var actual = underTest.getAbstractByAdmin(expected.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(abstractRepository, times(1)).findById(expected.getId());
        verifyNoMoreInteractions(abstractRepository);
    }

    @Test
    void getSimple() {
        //given
        final var expected = getSampleOfOneAbstractOutResponse();
        when(abstractRepository.findById(expected.getId())).thenReturn(Optional.ofNullable(abstractSimpleMapper.map(expected)));
        //when

        final var actual = underTest.getSimple(expected.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(abstractRepository, times(1)).findById(expected.getId());
        verifyNoMoreInteractions(abstractRepository);
    }

    @Test
    void adminCanUpdateAbstract() {
        //given
        final var toSave = getSampleOfOneAbstractDTO();
        when(abstractRepository.save(any(AbstractsEntity.class))).thenReturn(abstractMapper.map(toSave));
        when(abstractRepository.findById(toSave.getId())).thenReturn(Optional.ofNullable(abstractMapper.map(toSave)));
        //when
        final var actual = underTest.updateAbstractByAdmin(toSave);
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(toSave);
        verify(abstractRepository, times(1)).save(any(AbstractsEntity.class));
        verifyNoMoreInteractions(abstractRepository);
    }

    @Test
    void adminCanUpdateAbstractByBlockEdit() {
        //given
        final var toSave = getSampleOfOneAbstractBlockDTO();
        final var expected = getSampleOfOneAbstractDTO();
        expected.setAccepted(true);
        when(abstractRepository.save(any(AbstractsEntity.class))).thenReturn(abstractMapper.map(expected));
        when(abstractRepository.findById(toSave.getId())).thenReturn(Optional.ofNullable(abstractMapper.map(getSampleOfOneAbstractDTO())));
        //when
        final var actual = underTest.updateAbstractAdminBlockEdit(toSave);
        //then
        assertThat(actual.isAccepted()).isEqualTo(toSave.isAccepted());
        verify(abstractRepository, times(1)).save(any(AbstractsEntity.class));
        verifyNoMoreInteractions(abstractRepository);
    }

    @Test
    void adminCanDeleteAbstract() {
        //given
        doNothing().when(abstractRepository).deleteById(anyInt());
        //when
        //then
        underTest.deleteAdminAbstract(1);
        verify(abstractRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(abstractRepository);
    }
}