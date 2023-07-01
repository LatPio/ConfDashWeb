package com.flystonedev.customer.mapper;

import com.flystonedev.customer.DTO.ProfilePhotoDTO;
import com.flystonedev.customer.model.ProfilePhoto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfilePhotoMapper {

    ProfilePhotoDTO map(ProfilePhoto profilePhoto);
    ProfilePhoto map(ProfilePhotoDTO profilePhotoDTO);
}
