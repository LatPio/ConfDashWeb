package com.flystonedev.customer.service;

import com.flystonedev.customer.DTO.CustomerAdminDTO;
import com.flystonedev.customer.DTO.ProfilePhotoDTO;
import com.flystonedev.customer.config.JwtConverter;
import com.flystonedev.customer.exeption.CustomerUpdateException;
import com.flystonedev.customer.exeption.EntityNotFoundException;
import com.flystonedev.customer.mapper.ProfilePhotoMapper;
import com.flystonedev.customer.model.ProfilePhoto;
import com.flystonedev.customer.repository.CustomerRepository;
import com.flystonedev.customer.repository.ProfilePhotoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfilePhotoService {

    private final ProfilePhotoRepository profilePhotoRepository;
    private final ProfilePhotoMapper profilePhotoMapper = Mappers.getMapper(ProfilePhotoMapper.class);

    private final CustomerRepository customerRepository;
    private final JwtConverter jwtConverter;

    private final CustomerService customerService;


    @Transactional
    public void savePhoto(MultipartFile file, Integer id) throws IOException{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        ProfilePhoto profilePhoto = ProfilePhoto.builder()
                .customer(customerRepository.findById(id).orElseThrow(EntityNotFoundException::new))
                .name(filename)
                .authId(jwtConverter.getKeycloakUserID())
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
    public ProfilePhotoDTO getUserPhoto(Integer id){
        return profilePhotoRepository.findProfilePhotoByIdAndAuthId(id, jwtConverter.getKeycloakUserID()).map(profilePhotoMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public ProfilePhotoDTO updateUser(ProfilePhotoDTO profilePhotoDTO){
        ProfilePhotoDTO exist = getUserPhoto(profilePhotoDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException();
        } else if (!Objects.equals(exist.getAuthId(), jwtConverter.getKeycloakUserID())){
            throw new CustomerUpdateException();
        } else {
            ProfilePhoto updated = profilePhotoRepository.save(profilePhotoMapper.map(profilePhotoDTO));
            return profilePhotoMapper.map(updated);
        }
    }


    public void deleteUser (Integer id) {
        ProfilePhotoDTO exist = getUserPhoto(id);
        if (exist == null) {
            throw new EntityNotFoundException();
        } else if (exist.getAuthId() != jwtConverter.getKeycloakUserID()) {
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
        ProfilePhotoDTO exist = getAdmin(profilePhotoDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException();
        }
        ProfilePhoto updated = profilePhotoRepository.save(profilePhotoMapper.map(profilePhotoDTO));
        return profilePhotoMapper.map(updated);
    }

    public void deleteAdmin (Integer id) {
        CustomerAdminDTO customerDTO = customerService.getAdmin(id);
        customerDTO.setPhoto(null);
        customerService.updateAdmin(customerDTO);
        profilePhotoRepository.deleteById(id);
    }


}
