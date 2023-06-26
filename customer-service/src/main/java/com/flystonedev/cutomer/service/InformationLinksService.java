package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.DTO.InformationLinksAdminRequest;
import com.flystonedev.cutomer.DTO.InformationLinksDTO;
import com.flystonedev.cutomer.DTO.InformationLinksRequest;
import com.flystonedev.cutomer.config.JwtConverter;
import com.flystonedev.cutomer.exeption.CustomerUpdateException;
import com.flystonedev.cutomer.exeption.EntityNotFoundException;
import com.flystonedev.cutomer.mapper.InformationLinksMapper;
import com.flystonedev.cutomer.model.Customer;
import com.flystonedev.cutomer.model.InformationLinks;
import com.flystonedev.cutomer.repository.InformationLinksRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InformationLinksService {

    private final InformationLinksRepository informationLinksRepository;

    private final InformationLinksMapper informationLinksMapper = Mappers.getMapper(InformationLinksMapper.class);
    private final JwtConverter jwtConverter;

    /*
     *
     * !!!!!!!!! USER SERVICE METHODS !!!!!!!!
     *
     * */

    public void addUserLinks(InformationLinksRequest informationLinksRequest){
        InformationLinks informationLinks = InformationLinks.builder()
                .name(informationLinksRequest.name())
                .urlLink(informationLinksRequest.urlLink())
                .authId(jwtConverter.getKeycloakUserID())
                .customer(Customer.builder().id(informationLinksRequest.customer().getId()).build())
                .build();
        informationLinksRepository.save(informationLinks);
    }

    public InformationLinksDTO getUserLink(Integer id){
        return informationLinksRepository.findInformationLinksByIdAndAuthId(id, jwtConverter.getKeycloakUserID()).map(informationLinksMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public List<InformationLinksDTO> informationLinksUserResponseList(){
        List<InformationLinks> linksList = informationLinksRepository.findInformationLinksByAuthId(jwtConverter.getKeycloakUserID());
        return linksList.stream().map(informationLinksMapper::map).collect(Collectors.toList());
    }

    public InformationLinksDTO updateUsersLink(InformationLinksDTO informationLinksDTO){
        InformationLinks exist = informationLinksMapper.map(getUserLink(informationLinksDTO.getId()));
        if (exist == null) {
            throw new EntityNotFoundException();
        } else if (exist.getAuthId() != jwtConverter.getKeycloakUserID()){
            throw new CustomerUpdateException();
        } else {
            InformationLinks updated = informationLinksRepository.save(informationLinksMapper.map(informationLinksDTO));
            return informationLinksMapper.map(updated);
        }
    }

    public void deleteUserLink(Integer id){
        InformationLinks exist = informationLinksMapper.map(getUserLink(id));
        if (exist == null) {
            throw new EntityNotFoundException();
        } else if (exist.getAuthId() != jwtConverter.getKeycloakUserID()){
            throw new CustomerUpdateException();
        } else {
        informationLinksRepository.deleteById(id);}}

    /*
     *
     * !!!!!!!!! ADMIN SERVICE METHODS !!!!!!!!
     *
     * */

    public void addAdminLinks(InformationLinksAdminRequest informationLinksAdminRequest){
        InformationLinks informationLinks = InformationLinks.builder()
                .name(informationLinksAdminRequest.name())
                .urlLink(informationLinksAdminRequest.urlLink())
                .authId(informationLinksAdminRequest.authId())
                .customer(Customer.builder().id(informationLinksAdminRequest.customer().getId()).build())
                .build();
        informationLinksRepository.save(informationLinks);
    }

    public InformationLinksDTO getAdminLink(Integer id){
        return informationLinksRepository.findById(id).map(informationLinksMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public List<InformationLinksDTO> informationLinksAdminResponseList(){
        List<InformationLinks> linksList = informationLinksRepository.findAll();
        return linksList.stream().map(informationLinksMapper::map).collect(Collectors.toList());
    }


    public InformationLinksDTO updateLinkAdmin(InformationLinksDTO informationLinksDTO){
        InformationLinksDTO exist = getAdminLink(informationLinksDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException();
        }
        InformationLinks updated = informationLinksRepository.save(informationLinksMapper.map(informationLinksDTO));
        return informationLinksMapper.map(updated);
    }

    public void deleteAdminLink(Integer id){informationLinksRepository.deleteById(id);}


}
