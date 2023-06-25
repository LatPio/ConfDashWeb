package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.DTO.ProfilePhotoDTO;
import com.flystonedev.cutomer.config.JwtConverter;
import com.flystonedev.cutomer.exeption.CustomerUpdateException;
import com.flystonedev.cutomer.exeption.EntityNotFoundException;
import com.flystonedev.cutomer.mapper.ProfilePhotoMapper;
import com.flystonedev.cutomer.model.ProfilePhoto;
import com.flystonedev.cutomer.repository.CustomerRepository;
import com.flystonedev.cutomer.repository.ProfilePhotoRepository;
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
                .customer(customerRepository.findById(id).orElseThrow(EntityNotFoundException::new))
                .name(filename)
                .authId(JwtConverter.getKeycloakUserID())
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

    /*
     *
     * !!!!!!!!! USER SERVICE METHODS !!!!!!!!
     *
     * */
    public ProfilePhotoDTO get(Integer id){
        return profilePhotoRepository.findProfilePhotoByIdAndAuthId(id, JwtConverter.getKeycloakUserID()).map(profilePhotoMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public ProfilePhotoDTO updateUser(ProfilePhotoDTO profilePhotoDTO){
        ProfilePhotoDTO exist = get(profilePhotoDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException();
        } else if (exist.getAuthId() != JwtConverter.getKeycloakUserID()){
            throw new CustomerUpdateException();
        } else {
            ProfilePhoto updated = profilePhotoRepository.save(profilePhotoMapper.map(profilePhotoDTO));
            return profilePhotoMapper.map(updated);
        }
    }


    public void deleteUser (Integer id) {
        ProfilePhotoDTO exist = get(id);
        if (exist == null) {
            throw new EntityNotFoundException();
        } else if (exist.getAuthId() != JwtConverter.getKeycloakUserID()) {
            throw new CustomerUpdateException();
        } else {
            profilePhotoRepository.deleteById(id);

        }
    }

    /*
     *
     * !!!!!!!!! ADMIN SERVICE METHODS !!!!!!!!
     *
     * */
    public ProfilePhotoDTO getAdmin(Integer id){
        return profilePhotoRepository.findById(id).map(profilePhotoMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public ProfilePhotoDTO updateAdmin(ProfilePhotoDTO profilePhotoDTO){
        ProfilePhotoDTO exist = get(profilePhotoDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException();
        }
        ProfilePhoto updated = profilePhotoRepository.save(profilePhotoMapper.map(profilePhotoDTO));
        return profilePhotoMapper.map(updated);
    }

    public void deleteAdmin (Integer id) {
        profilePhotoRepository.deleteById(id);
    }


}
