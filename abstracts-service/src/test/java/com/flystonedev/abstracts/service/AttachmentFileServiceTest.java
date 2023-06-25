package com.flystonedev.abstracts.service;

import com.flystonedev.abstracts.SampleData;
import com.flystonedev.abstracts.config.JwtConverter;
import com.flystonedev.abstracts.exeption.AttachmentFileEditionBlockedException;
import com.flystonedev.abstracts.exeption.EntityNotFoundException;
import com.flystonedev.abstracts.mapper.AttachmentFileMapper;
import com.flystonedev.abstracts.model.AbstractsEntity;
import com.flystonedev.abstracts.model.AttachmentFile;
import com.flystonedev.abstracts.repository.AbstractRepository;
import com.flystonedev.abstracts.repository.AttachmentFileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttachmentFileServiceTest implements SampleData {

    @InjectMocks
    private AttachmentFileService attachmentFileService;

    @Mock
    private AttachmentFileRepository attachmentFileRepository;

    @Mock
    private AbstractRepository abstractRepository;

    @Mock
    private JwtConverter jwtConverter;

    private final AttachmentFileMapper attachmentFileMapper = Mappers.getMapper(AttachmentFileMapper.class);


//    @SneakyThrows
    @Test
    void canSaveNewFileCreatedByUser() throws Exception {
        //given
        AttachmentFile expected = getSampleOfOneAttachmentFile();
        expected.setId(null);
        Optional<AbstractsEntity> abstractsEntity = Optional.of(getSampleOfOneAbstractEntity());
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        when(abstractRepository.findById(anyInt())).thenReturn(abstractsEntity);

        //when
        attachmentFileService.saveUsersFile(getSampleMultipart(),getSampleOfOneAttachmentFileRequest());
        //then
        ArgumentCaptor<AttachmentFile> attachmentFileArgumentCaptor = ArgumentCaptor.forClass(AttachmentFile.class);
        verify(attachmentFileRepository).save(attachmentFileArgumentCaptor.capture());

        AttachmentFile attachmentFileArgumentCaptorValue = attachmentFileArgumentCaptor.getValue();
        assertThat(attachmentFileArgumentCaptorValue).isEqualTo(expected);

    }

    @Test
    void canUserGetAttachmentFileDTOS() {
        //given
        String authId = "vava-dddd";
        when(jwtConverter.getKeycloakUserID()).thenReturn(authId);

        //when
        attachmentFileService.attachmentUsersFileDTOS();
        //then
        verify(attachmentFileRepository, times(1)).findAttachmentFileByAuthId(authId);
        verifyNoMoreInteractions(attachmentFileRepository);
    }

    @Test
    void canUserGetOwnUserFile() {
        //given
        final var expected = getSampleOfOneAttachmentFile();
        when(attachmentFileRepository.findAttachmentFileByIdAndAuthId(expected.getId(), expected.getAuthId())).thenReturn(Optional.ofNullable(expected));
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        //when
        final var actual = attachmentFileService.getUserFile(expected.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(attachmentFileRepository, times(1)).findAttachmentFileByIdAndAuthId(expected.getId(),expected.getAuthId());
        verifyNoMoreInteractions(attachmentFileRepository);

    }
    @Test
    void willThrowErrorWhenUserGetOwnUserFileAndProvideWrongId(){
        //given
        final var expected = getSampleOfOneAttachmentFile();
        when(attachmentFileRepository.findAttachmentFileByIdAndAuthId(expected.getId(), expected.getAuthId())).thenReturn(Optional.ofNullable(null));
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        //when

        //then
        assertThatThrownBy(() -> attachmentFileService.getUserFile(expected.getId()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Exception Entity not found");

    }

    @Test
    void canUserUpdateOwnUsersFile() {
        //given
        final var expected = getSampleOfOneAttachmentFile();
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        when(attachmentFileRepository.save(any(AttachmentFile.class))).thenReturn(expected);
        when(attachmentFileRepository.findAttachmentFileByIdAndAuthId(expected.getId(), expected.getAuthId())).thenReturn(Optional.ofNullable(expected));
        //when
        final var actual = attachmentFileService.updateUsersFile(getSampleOfOneAttachmentFileDTO());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(attachmentFileRepository, times(1)).save(any(AttachmentFile.class));
        verifyNoMoreInteractions(attachmentFileRepository);
    }

    @Test
    void willThrowErrorWhenUserUpdateOwnFileAndFileProvidedWrongId(){
        //given
        final var expected = getSampleOfOneAttachmentFile();
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        when(attachmentFileRepository.findAttachmentFileByIdAndAuthId(expected.getId(), expected.getAuthId())).thenReturn(Optional.ofNullable(null));
        //when
        assertThatThrownBy(() -> attachmentFileService.updateUsersFile(getSampleOfOneAttachmentFileDTO()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Exception Entity not found");

    }
    @Test
    void willThrowErrorWhenUserUpdateOwnFileAndFileIsBlocked(){
        //given
        final var expected = getSampleOfOneAttachmentFile();
        expected.setAccepted(true);
        when(jwtConverter.getKeycloakUserID()).thenReturn(expected.getAuthId());
        when(attachmentFileRepository.findAttachmentFileByIdAndAuthId(expected.getId(), expected.getAuthId())).thenReturn(Optional.ofNullable(expected));
        //when
        assertThatThrownBy(() -> attachmentFileService.updateUsersFile(getSampleOfOneAttachmentFileDTO()))
                .isInstanceOf(AttachmentFileEditionBlockedException.class)
                .hasMessageContaining("File edition blocked");
    }
    @Test
    void canUserDeleteOwnUsersFile() {
        //given
        var attachmentFileDTO = getSampleOfOneAttachmentFileDTO();
        when(jwtConverter.getKeycloakUserID()).thenReturn(attachmentFileDTO.getAuthId());
        when(attachmentFileRepository.findAttachmentFileByIdAndAuthId(attachmentFileDTO.getId(), attachmentFileDTO.getAuthId())).thenReturn(Optional.ofNullable(attachmentFileMapper.map(attachmentFileDTO)));
        //when
        attachmentFileService.deleteUsersFile(attachmentFileDTO.getId());
        //then
        verify(attachmentFileRepository, times(1)).deleteAttachmentFileByIdAndAuthId(anyInt(),anyString());
        verifyNoMoreInteractions(attachmentFileRepository);
    }


    @Test
    void willThrowErrorWhenUserDeleteOwnFileAndFileProvidedWrongId(){
        //given
        var attachmentFileDTO = getSampleOfOneAttachmentFileDTO();

        when(jwtConverter.getKeycloakUserID()).thenReturn(attachmentFileDTO.getAuthId());
        when(attachmentFileRepository.findAttachmentFileByIdAndAuthId(attachmentFileDTO.getId(), attachmentFileDTO.getAuthId())).thenReturn(Optional.ofNullable(null));
        //when
        //then
        assertThatThrownBy(() -> attachmentFileService.deleteUsersFile(attachmentFileDTO.getId()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Exception Entity not found");
    }
    @Test
    void willThrowErrorWhenUserDeleteOwnFileAndFileIsBlocked(){
        //given
        var attachmentFileDTO = getSampleOfOneAttachmentFileDTO();
        attachmentFileDTO.setAccepted(true);
        when(jwtConverter.getKeycloakUserID()).thenReturn(attachmentFileDTO.getAuthId());
        when(attachmentFileRepository.findAttachmentFileByIdAndAuthId(attachmentFileDTO.getId(), attachmentFileDTO.getAuthId())).thenReturn(Optional.ofNullable(attachmentFileMapper.map(attachmentFileDTO)));
        //when
        assertThatThrownBy(() -> attachmentFileService.deleteUsersFile(attachmentFileDTO.getId()))
                .isInstanceOf(AttachmentFileEditionBlockedException.class)
                .hasMessageContaining("File edition blocked");

    }

    @Test
    void canAdminSaveFile() throws Exception{
        //given
        AttachmentFile expected = getSampleOfOneAttachmentFile();
        expected.setId(null);
        Optional<AbstractsEntity> abstractsEntity = Optional.of(getSampleOfOneAbstractEntity());
        when(abstractRepository.findById(anyInt())).thenReturn(abstractsEntity);

        //when
        attachmentFileService.saveAdminFile(getSampleMultipart(),getSampleOfOneAttachmentFileAdminRequest());
        //then
        ArgumentCaptor<AttachmentFile> attachmentFileArgumentCaptor = ArgumentCaptor.forClass(AttachmentFile.class);
        verify(attachmentFileRepository).save(attachmentFileArgumentCaptor.capture());

        AttachmentFile attachmentFileArgumentCaptorValue = attachmentFileArgumentCaptor.getValue();
        assertThat(attachmentFileArgumentCaptorValue).isEqualTo(expected);
    }

    @Test
    void canAdminFindAttachmentAdminFileDTOS() {
        //given
        String authId = "vava-dddd";
        //when
        attachmentFileService.attachmentAdminFileDTOS();
        //then
        verify(attachmentFileRepository, times(1)).findAll();
        verifyNoMoreInteractions(attachmentFileRepository);
    }

    @Test
    void canAdminGetFile() {
        //given
        final var expected = getSampleOfOneAttachmentFile();
        when(attachmentFileRepository.findById(expected.getId())).thenReturn(Optional.ofNullable(expected));
        //when
        final var actual = attachmentFileService.getAdminFile(expected.getId());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(attachmentFileRepository, times(1)).findById(expected.getId());
        verifyNoMoreInteractions(attachmentFileRepository);
    }

    @Test
    void willThrowErrorWhenAdminGetFile(){
        //given
        final var excepted = getSampleOfOneAttachmentFileDTO();
        //when
        //then
        assertThatThrownBy(() -> attachmentFileService.getAdminFile(excepted.getId()))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Exception Entity not found");
    }

    @Test
    void canAdminUpdateFile() throws Exception{
        //given
        final var expected = getSampleOfOneAttachmentFile();
        when(attachmentFileRepository.save(any(AttachmentFile.class))).thenReturn(expected);
        when(attachmentFileRepository.findById(expected.getId())).thenReturn(Optional.ofNullable(expected));
        //when
        final var actual = attachmentFileService.updateAdmin(getSampleMultipart(), getSampleOfAttachmentFileUpdateRequest());
        //then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
        verify(attachmentFileRepository, times(1)).save(any(AttachmentFile.class));
        verifyNoMoreInteractions(attachmentFileRepository);


        //then
//        ArgumentCaptor<AttachmentFile> attachmentFileArgumentCaptor = ArgumentCaptor.forClass(AttachmentFile.class);
//        verify(attachmentFileRepository).save(attachmentFileArgumentCaptor.capture());
//
//        AttachmentFile attachmentFileArgumentCaptorValue = attachmentFileArgumentCaptor.getValue();
//        assertThat(attachmentFileArgumentCaptorValue).isEqualTo(expected);
    }
    @Test
    void willThrowErrorWhenAdminUpdateAttachmentFile(){
        //given
        final var toSave = getSampleOfOneAttachmentFileDTO();
        //when
        //then
        assertThatThrownBy(() -> attachmentFileService.updateAdmin(getSampleMultipart(),getSampleOfAttachmentFileUpdateRequest() ))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Exception Entity not found");

    }

    @Test
    void deleteAdmin() {
        //given
        var attachmentFileDTO = getSampleOfOneAttachmentFileDTO();
        doNothing().when(attachmentFileRepository).deleteById(anyInt());

        //when
        attachmentFileService.deleteAdmin(attachmentFileDTO.getId());
        //then
        verify(attachmentFileRepository, times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(attachmentFileRepository);

    }
}