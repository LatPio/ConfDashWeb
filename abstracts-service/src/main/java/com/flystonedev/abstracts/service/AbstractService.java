package com.flystonedev.abstracts.service;


import com.flystonedev.abstracts.DTO.AbstractBlockDTO;
import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.abstracts.config.JwtConverter;
import com.flystonedev.abstracts.exeption.AbstractEditionBlockedException;
import com.flystonedev.abstracts.exeption.EntityNotFoundException;
import com.flystonedev.abstracts.exeption.config.GlobalErrorCode;
import com.flystonedev.abstracts.mapper.AbstractBlockMapper;
import com.flystonedev.abstracts.mapper.AbstractMapper;
import com.flystonedev.abstracts.mapper.AbstractSimpleMapper;
import com.flystonedev.abstracts.model.AbstractsEntity;
import com.flystonedev.abstracts.repository.AbstractRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AbstractService {

    private final AbstractRepository abstractRepository;

    private final JwtConverter jwtConverter;
    private final AbstractMapper abstractMapper = Mappers.getMapper(AbstractMapper.class);
    private final AbstractBlockMapper abstractBlockMapper = Mappers.getMapper(AbstractBlockMapper.class);
    private final AbstractSimpleMapper abstractSimpleMapper = Mappers.getMapper(AbstractSimpleMapper.class);

    /*
    *
    * !!!!!!!!! USER SERVICE METHODS !!!!!!!!
    *
    * */

    public void createUserAbstract(AbstractDTO abstractDTO){
        AbstractsEntity abstracts = AbstractsEntity.builder()
                .abstractTitle(abstractDTO.getAbstractTitle())
                .body(abstractDTO.getBody())
                .authors(abstractDTO.getAuthors())
                .ownerId(abstractDTO.getOwnerId()) //todo from customer get user id
                .authId(jwtConverter.getKeycloakUserID())
                .build();
        abstractRepository.save(abstracts);
        //todo files?
    }
    @Transactional
    public List<AbstractDTO> abstractUsersDTOList(){
        List<AbstractsEntity> abstractsEntityList = abstractRepository.findAbstractsEntitiesByAuthId(jwtConverter.getKeycloakUserID().toString());
        return abstractsEntityList.stream().map(abstractMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public List<AbstractDTO> abstractUserAceptedDTOList(){
        List<AbstractsEntity> abstractsEntityList = abstractRepository.findAbstractsEntitiesByAccepted(true);
        return abstractsEntityList.stream().map(abstractMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public AbstractDTO getUsersAbstract(Integer id){
        return abstractRepository.findByIdAndAuthId(id, jwtConverter.getKeycloakUserID().toString()).map(abstractMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public AbstractDTO updateUsers(AbstractDTO abstractDTO){
        AbstractDTO exist = getUsersAbstract(abstractDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException();
        } else
        if (exist.isAccepted()) {
            throw new AbstractEditionBlockedException();
        } else
        if (exist.getAuthId() != JwtConverter.getKeycloakUserID()){
            throw new AbstractEditionBlockedException("You can edit only yours Abstract!", GlobalErrorCode.ERROR_ABSTRACT_ACCESS_BLOCKED);
        }
        else {
            AbstractsEntity updated = abstractRepository.save(abstractMapper.map(abstractDTO));
            return abstractMapper.map(updated);
        }
    }

    public void deleteUsers(Integer id) {
        AbstractDTO exist = getUsersAbstract(id);
        if (exist == null) {
            throw new EntityNotFoundException();
        } else
        if (exist.isAccepted()) {
            throw new AbstractEditionBlockedException();
        } else {
            abstractRepository.deleteByIdAndAuthId(id, jwtConverter.getKeycloakUserID().toString());
        }

    }

    /*
     *
     * !!!!!!!!! ADMIN SERVICE METHODS !!!!!!!!
     *
     * */


    public void createAdminAbstract(AbstractDTO abstractDTO){
        AbstractsEntity abstracts = AbstractsEntity.builder()
                .abstractTitle(abstractDTO.getAbstractTitle())
                .body(abstractDTO.getBody())
                .authors(abstractDTO.getAuthors())
                .ownerId(abstractDTO.getOwnerId())
                .authId(abstractDTO.getAuthId())
                .build();
        abstractRepository.save(abstracts);
    }
    @Transactional
    public List<AbstractDTO> abstractAdminDTOList(){
        List<AbstractsEntity> abstractsEntityList = abstractRepository.findAll();
        return abstractsEntityList.stream().map(abstractMapper::map).collect(Collectors.toList());
    }
    @Transactional
    public AbstractDTO getAdmin(Integer id){
        return abstractRepository.findById(id).map(abstractMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public AbstractOutResponse getSimple(Integer id){
        return abstractRepository.findById(id).map(abstractSimpleMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public AbstractDTO updateAdmin(AbstractDTO abstractDTO){
        AbstractDTO exist = getAdmin(abstractDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException();
        }

        AbstractsEntity updated = abstractRepository.save(abstractMapper.map(abstractDTO));
        return abstractMapper.map(updated);
    }

    @Transactional
    public AbstractBlockDTO updateAdminBlockEdit(AbstractBlockDTO abstractBlockDTO){
        AbstractDTO exist = getAdmin(abstractBlockDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException();
        }
        exist.setAccepted(abstractBlockDTO.isAccepted());
        AbstractsEntity updated = abstractRepository.save(abstractMapper.map(exist));
        return abstractBlockMapper.map(updated);
    }

    public void deleteAdmin(Integer id) {
        abstractRepository.deleteById(id);
    }

}
