package com.flystonedev.localization.service;

import com.flystonedev.localization.DTO.LocalizationDTO;
import com.flystonedev.localization.DTO.LocalizationOutResponse;
import com.flystonedev.localization.exeption.EntityNotFoundException;
import com.flystonedev.localization.mapper.LocalizationMapper;
import com.flystonedev.localization.mapper.LocalizationOutResponseMapper;
import com.flystonedev.localization.model.Localization;
import com.flystonedev.localization.repository.LocalizationRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocalizationService {

    private final LocalizationRepository localizationRepository;
    private final LocalizationMapper localizationMapper = Mappers.getMapper(LocalizationMapper.class);
    private final LocalizationOutResponseMapper localizationOutResponseMapper = Mappers.getMapper(LocalizationOutResponseMapper.class);


    public void createLocalization(LocalizationDTO localizationDTO){
        Localization localization = Localization.builder()
                .room(localizationDTO.getRoom())
                .mapImage(localizationDTO.getMapImage())
                .coordinateX(localizationDTO.getCoordinateX())
                .coordinateY(localizationDTO.getCoordinateY())
                .build();
        localizationRepository.save(localization);
    }

    public List<LocalizationDTO> localizationDTOList(){
        List<Localization> localizationList = localizationRepository.findAll();
        return localizationList.stream().map(localizationMapper::map).collect(Collectors.toList());
    }

    public LocalizationDTO get(Integer id){
        return localizationRepository.findById(id).map(localizationMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public LocalizationDTO update(LocalizationDTO localizationDTO){
        LocalizationDTO exist = get(localizationDTO.getId());
        if(exist == null){
            return null;
        }
        Localization updated = localizationRepository.save(localizationMapper.map(localizationDTO));
        return localizationMapper.map(updated);

    }

    public void delete(Integer id){
        localizationRepository.deleteById(id)
        ;
    }

    public LocalizationOutResponse getSimple(Integer id) {
       return localizationRepository.findById(id).map(localizationOutResponseMapper::map).orElseThrow(EntityNotFoundException::new);
    }
}
