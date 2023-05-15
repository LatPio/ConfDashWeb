package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.mapper.InstitutionMapper;
import com.flystonedev.cutomer.model.Institution;
import com.flystonedev.cutomer.records.InstitutionRecord;
import com.flystonedev.cutomer.repository.InstitutionRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    private final InstitutionMapper institutionMapper = Mappers.getMapper(InstitutionMapper.class);

    public void addInstitution(InstitutionRecord institutionRecord){
        Institution institution = Institution.builder()
                .name(institutionRecord.name())
                .build();
        institutionRepository.save(institution);
    }

    public List<InstitutionRecord> institutionRecordList(){
        List<Institution> institutionList = institutionRepository.findAll();
        return institutionList.stream().map(institutionMapper::map).collect(Collectors.toList());
    }
    public InstitutionRecord get(Integer id){
        return institutionRepository.findById(id).map(institution -> institutionMapper.map(institution)).orElse(null);
    }
    public InstitutionRecord update(InstitutionRecord institutionRecord){
        InstitutionRecord exist = get(institutionRecord.id());
        if (exist == null) {
            return null;
        }
        Institution updated = institutionRepository.save(institutionMapper.map(institutionRecord));
        return institutionMapper.map(updated);

    }
    public void delete(Integer id){institutionRepository.deleteById(id);}
}
