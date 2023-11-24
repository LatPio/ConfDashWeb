package com.flystonedev.abstracts.service;


import com.flystonedev.abstracts.DTO.AbstractBlockDTO;
import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.DTO.AbstractLightDTO;
import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.abstracts.clients.CustomerClient;
import com.flystonedev.abstracts.config.JwtConverter;
import com.flystonedev.abstracts.exeption.AbstractEditionBlockedException;
import com.flystonedev.abstracts.exeption.EntityNotFoundException;
import com.flystonedev.abstracts.exeption.config.GlobalErrorCode;
import com.flystonedev.abstracts.mapper.AbstractBlockMapper;
import com.flystonedev.abstracts.mapper.AbstractMapper;
import com.flystonedev.abstracts.mapper.AbstractSimpleMapper;
import com.flystonedev.abstracts.model.AbstractsEntity;
import com.flystonedev.abstracts.model.StatsResponse;
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

    private final AttachmentFileService attachmentFileService;

    private final JwtConverter jwtConverter;
    private final AbstractMapper abstractMapper = Mappers.getMapper(AbstractMapper.class);
    private final AbstractBlockMapper abstractBlockMapper = Mappers.getMapper(AbstractBlockMapper.class);
    private final AbstractSimpleMapper abstractSimpleMapper = Mappers.getMapper(AbstractSimpleMapper.class);

    private final CustomerClient customerClient;
    /*
    *
    * !!!!!!!!! USER SERVICE METHODS !!!!!!!!
    *
    * */
    @Transactional
    public AbstractDTO createUserAbstract(AbstractDTO abstractDTO){
        AbstractsEntity abstracts = AbstractsEntity.builder()
                .abstractTitle(abstractDTO.getAbstractTitle())
                .body(abstractDTO.getBody())
                .affiliation(abstractDTO.getAffiliation())
                .authors(abstractDTO.getAuthors())
                .ownerId(customerClient.getCustomer().getId()) //todo from customer get user id
                .authId(jwtConverter.getKeycloakUserID())
                .accepted(false)
                .build();
        abstractRepository.save(abstracts);
        //todo files?
        return abstractMapper.map(abstracts);
    }
    @Transactional
    public List<AbstractDTO> abstractUsersDTOList(){
        List<AbstractsEntity> abstractsEntityList = abstractRepository.findAbstractsEntitiesByAuthId(jwtConverter.getKeycloakUserID().toString());
        return abstractsEntityList.stream().map(abstractMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public List<AbstractDTO> abstractUserAcceptedDTOList(){
        List<AbstractsEntity> abstractsEntityList = abstractRepository.findAbstractsEntitiesByAccepted(true);
        return abstractsEntityList.stream().map(abstractMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public AbstractDTO getUsersAbstract(Integer id){
        return abstractRepository.findByIdAndAuthId(id, jwtConverter.getKeycloakUserID()).map(abstractMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public AbstractDTO updateUsersAbstract(AbstractDTO abstractDTO){
        AbstractsEntity exist = abstractMapper.map(getUsersAbstract(abstractDTO.getId()));
        if (exist == null) {
            throw new EntityNotFoundException();
        } else
        if (exist.isAccepted()) {
            throw new AbstractEditionBlockedException();
        }
        else if (!exist.getAuthId().equals(jwtConverter.getKeycloakUserID())){
            throw new AbstractEditionBlockedException("You can edit only yours Abstract!", GlobalErrorCode.ERROR_ABSTRACT_ACCESS_BLOCKED);
        }
        else {
            AbstractsEntity toSave = abstractMapper.map(abstractDTO);
            toSave.setAuthId(exist.getAuthId());
            toSave.setAccepted(exist.isAccepted());
            AbstractsEntity updated = abstractRepository.save(toSave);
            return abstractMapper.map(updated);
        }
    }
    @Transactional
    public void deleteUsersAbstract(Integer id) {
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


    public AbstractDTO createAdminAbstract(AbstractDTO abstractDTO){
        AbstractsEntity abstracts = AbstractsEntity.builder()
                .abstractTitle(abstractDTO.getAbstractTitle())
                .body(abstractDTO.getBody())
                .affiliation(abstractDTO.getAffiliation())
                .authors(abstractDTO.getAuthors())
                .ownerId(abstractDTO.getOwnerId())
                .authId(abstractDTO.getAuthId())
                .build();
        abstractRepository.save(abstracts);
        return abstractMapper.map(abstracts);
    }
    @Transactional
    public List<AbstractDTO> abstractAdminDTOList(){
        List<AbstractsEntity> abstractsEntityList = abstractRepository.findAll();
        return abstractsEntityList.stream().map(abstractMapper::map).collect(Collectors.toList());
    }
    @Transactional
    public List<AbstractDTO> abstractAdminDTOListAccepted(Boolean accepted){
        List<AbstractsEntity> abstractsEntityList = abstractRepository.findAll();
        return abstractsEntityList.stream().filter(f->f.isAccepted() == accepted).map(abstractMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public List<AbstractLightDTO> abstractAdminDTOLightListAccepted(Boolean accepted){
        List<AbstractsEntity> abstractsEntityList = abstractRepository.findAll();
        return abstractsEntityList.stream().filter(f->f.isAccepted() == accepted).map(abstractMapper::mapLight).collect(Collectors.toList());
    }
    @Transactional
    public AbstractDTO getAbstractByAdmin(Integer id){
        return abstractRepository.findById(id).map(abstractMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public AbstractOutResponse getSimple(Integer id){
        return abstractRepository.findById(id).map(abstractSimpleMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public AbstractDTO updateAbstractByAdmin(AbstractDTO abstractDTO){
        AbstractDTO exist = getAbstractByAdmin(abstractDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException();
        }

        AbstractsEntity updated = abstractRepository.save(abstractMapper.map(abstractDTO));
        return abstractMapper.map(updated);
    }

    @Transactional
    public AbstractBlockDTO updateAbstractAdminBlockEdit(AbstractBlockDTO abstractBlockDTO){
        AbstractDTO exist = getAbstractByAdmin(abstractBlockDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException();
        }
        attachmentFileService.updateAdnBlockAttachmentFilesByAdmin(abstractBlockDTO.getId(), abstractBlockDTO.isAccepted());

        exist.setAccepted(abstractBlockDTO.isAccepted());
        AbstractsEntity updated = abstractRepository.save(abstractMapper.map(exist));
        return abstractBlockMapper.map(updated);
    }
    @Transactional
    public void deleteAdminAbstract(Integer id) {
        abstractRepository.deleteById(id);
    }

    public StatsResponse  stats(){
        return new StatsResponse(
                abstractRepository.count(),
                abstractRepository.countAbstractsEntitiesByAccepted(true),
                abstractRepository.countAbstractsEntitiesByAccepted(false)
        );
    }

}
