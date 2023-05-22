package com.flystonedev.cutomer.mapper;

import com.flystonedev.cutomer.DTO.ProfilePhotoDTO;
import com.flystonedev.cutomer.model.ProfilePhoto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfilePhotoMapper {

    ProfilePhotoDTO map(ProfilePhoto profilePhoto);
    ProfilePhoto map(ProfilePhotoDTO profilePhotoDTO);
}
