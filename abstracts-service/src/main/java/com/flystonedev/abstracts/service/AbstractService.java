package com.flystonedev.abstracts.service;


import com.flystonedev.abstracts.DTO.AbstractDTO;
import com.flystonedev.abstracts.mapper.AbstractMapper;
import com.flystonedev.abstracts.model.AbstractsEntity;
import com.flystonedev.abstracts.repository.AbstractRepository;
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

    public void createAbstract(AbstractDTO abstractDTO){
        AbstractsEntity abstracts = AbstractsEntity.builder()
                .abstractTitle(abstractDTO.getAbstractTitle())
                .ownerId(abstractDTO.getOwnerId())
                .build();
        abstractRepository.save(abstracts);
    }

    public List<AbstractDTO> abstractDTOList(){
        List<AbstractsEntity> abstractsEntityList = abstractRepository.findAll();
        return abstractsEntityList.stream().map(abstractMapper::map).collect(Collectors.toList());
    }
    public AbstractDTO get(Integer id){
        return abstractRepository.findById(id).map(abstractMapper::map).orElse(null);
    }

    public AbstractDTO update(AbstractDTO abstractDTO){
        AbstractDTO exist = get(abstractDTO.getId());
        if (exist == null) {
            return null;
        }
        AbstractsEntity updated = abstractRepository.save(abstractMapper.map(abstractDTO));
        return abstractMapper.map(updated);
    }

    public void delete(Integer id) {
        abstractRepository.deleteById(id);
    }

}
