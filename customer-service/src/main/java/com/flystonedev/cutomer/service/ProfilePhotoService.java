package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.DTO.CustomerDTO;
import com.flystonedev.cutomer.DTO.ProfilePhotoDTO;
import com.flystonedev.cutomer.exeption.EntityNotFoundException;
import com.flystonedev.cutomer.mapper.ProfilePhotoMapper;
import com.flystonedev.cutomer.model.Customer;
import com.flystonedev.cutomer.model.ProfilePhoto;
import com.flystonedev.cutomer.repository.CustomerRepository;
import com.flystonedev.cutomer.repository.ProfilePhotoRepository;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfilePhotoService {

    private final ProfilePhotoRepository profilePhotoRepository;
    private final ProfilePhotoMapper profilePhotoMapper = Mappers.getMapper(ProfilePhotoMapper.class);

    private final CustomerRepository customerRepository;


    public void savePhoto(MultipartFile file, Integer id) throws IOException{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        ProfilePhoto profilePhoto = ProfilePhoto.builder()
                .customer(customerRepository.findById(id).orElse(null))
                .name(filename)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
        profilePhotoRepository.save(profilePhoto);
    }
    //todo implementation for admin
    public List<ProfilePhotoDTO> profilePhotoDTOList(){
        List<ProfilePhoto> profilePhotoList = profilePhotoRepository.findAll();
        return profilePhotoList.stream().map(profilePhotoMapper::map).collect(Collectors.toList());
    }

    public ProfilePhotoDTO get(Integer id){
        return profilePhotoRepository.findById(id).map(profilePhotoMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public ProfilePhotoDTO update(ProfilePhotoDTO profilePhotoDTO){
        ProfilePhotoDTO exist = get(profilePhotoDTO.getId());
        if (exist == null) {
            return null;
        }
        ProfilePhoto updated = profilePhotoRepository.save(profilePhotoMapper.map(profilePhotoDTO));
        return profilePhotoMapper.map(updated);
    }

    public void delete (Integer id) {
        profilePhotoRepository.deleteById(id);
    }



}
