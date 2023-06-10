package com.flystonedev.abstracts.service;


import com.flystonedev.abstracts.DTO.AbstractBlockDTO;
import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.DTO.AbstractOutResponse;
import com.flystonedev.abstracts.exeption.EntityNotFoundException;
import com.flystonedev.abstracts.mapper.AbstractBlockMapper;
import com.flystonedev.abstracts.mapper.AbstractMapper;
import com.flystonedev.abstracts.mapper.AbstractSimpleMapper;
import com.flystonedev.abstracts.model.AbstractsEntity;
import com.flystonedev.abstracts.repository.AbstractRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AbstractService {

    private final AbstractRepository abstractRepository;

    private final AbstractMapper abstractMapper = Mappers.getMapper(AbstractMapper.class);
    private final AbstractBlockMapper abstractBlockMapper = Mappers.getMapper(AbstractBlockMapper.class);
    private final AbstractSimpleMapper abstractSimpleMapper = Mappers.getMapper(AbstractSimpleMapper.class);


    public void createAbstract(AbstractDTO abstractDTO){
        AbstractsEntity abstracts = AbstractsEntity.builder()
                .abstractTitle(abstractDTO.getAbstractTitle())
                .ownerId(abstractDTO.getOwnerId())
                .build();
        abstractRepository.save(abstracts);
    }
    @Transactional
    public List<AbstractDTO> abstractDTOList(){
        List<AbstractsEntity> abstractsEntityList = abstractRepository.findAll();
        return abstractsEntityList.stream().map(abstractMapper::map).collect(Collectors.toList());
    }
    @Transactional
    public AbstractDTO get(Integer id){
        return abstractRepository.findById(id).map(abstractMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public AbstractOutResponse getSimple(Integer id){
        return abstractRepository.findById(id).map(abstractSimpleMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public AbstractDTO update(AbstractDTO abstractDTO){
        AbstractDTO exist = get(abstractDTO.getId());
        if (exist == null) {
            return null;
        }
        AbstractsEntity updated = abstractRepository.save(abstractMapper.map(abstractDTO));
        return abstractMapper.map(updated);
    }

    @Transactional
    public AbstractBlockDTO updateBlockEdit(AbstractBlockDTO abstractBlockDTO){
        AbstractDTO exist = get(abstractBlockDTO.getId());
        if (exist == null) {
            return null;
        }
        exist.setAccepted(abstractBlockDTO.isAccepted());
        AbstractsEntity updated = abstractRepository.save(abstractMapper.map(exist));
        return abstractBlockMapper.map(updated);
    }

    public void delete(Integer id) {
        abstractRepository.deleteById(id);
    }

}
