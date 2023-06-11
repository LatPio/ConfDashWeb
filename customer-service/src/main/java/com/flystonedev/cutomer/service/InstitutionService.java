package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.DTO.InstitutionDTO;
import com.flystonedev.cutomer.exeption.EntityNotFoundException;
import com.flystonedev.cutomer.exeption.config.EntityAlreadyInDatabaseException;
import com.flystonedev.cutomer.mapper.InstitutionMapper;
import com.flystonedev.cutomer.model.Institution;
import com.flystonedev.cutomer.repository.InstitutionRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    private final InstitutionMapper institutionMapper = Mappers.getMapper(InstitutionMapper.class);

    public void addInstitution(InstitutionDTO institutionDTO){

        InstitutionDTO nameExist = institutionMapper.map(institutionRepository.findInstitutionByName(institutionDTO.getName()));
        if (nameExist != null) {
            throw new EntityAlreadyInDatabaseException();
        } else {
            Institution institution = Institution.builder()
                    .name(institutionDTO.getName())
                    .build();
            institutionRepository.save(institution);
        }
    }

    public List<InstitutionDTO> institutionRecordList(){
        List<Institution> institutionList = institutionRepository.findAll();
        return institutionList.stream().map(institutionMapper::map).collect(Collectors.toList());
    }
    public InstitutionDTO get(Integer id){
        return institutionRepository.findById(id).map(institutionMapper::map).orElseThrow(EntityNotFoundException::new);
    }
    public InstitutionDTO update(InstitutionDTO institutionDTO){
        InstitutionDTO exist = get(institutionDTO.getId());
        if (exist == null) {
            throw new EntityNotFoundException();
        }
        Institution updated = institutionRepository.save(institutionMapper.map(institutionDTO));
        return institutionMapper.map(updated);

    }
    public void delete(Integer id){institutionRepository.deleteById(id);}
}
