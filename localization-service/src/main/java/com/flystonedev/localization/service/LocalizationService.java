package com.flystonedev.localization.service;

import com.flystonedev.localization.DTO.LocalizationDTO;
import com.flystonedev.localization.DTO.LocalizationOutResponse;
import com.flystonedev.localization.DTO.StatsLocationResponse;
import com.flystonedev.localization.exeption.EntityNotFoundException;
import com.flystonedev.localization.mapper.BookingMapper;
import com.flystonedev.localization.mapper.LocalizationMapper;
import com.flystonedev.localization.mapper.LocalizationOutResponseMapper;
import com.flystonedev.localization.mapper.MapImageMapper;
import com.flystonedev.localization.model.Localization;
import com.flystonedev.localization.repository.BookingsRepository;
import com.flystonedev.localization.repository.LocalizationRepository;
import com.flystonedev.localization.repository.MapImageRepository;
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
    private final MapImageRepository mapImageRepository;
    private final BookingsRepository bookingsRepository;
    private final LocalizationMapper localizationMapper = Mappers.getMapper(LocalizationMapper.class);
    private final MapImageMapper mapImageMapper = Mappers.getMapper(MapImageMapper.class);
    private final BookingMapper bookingMapper = Mappers.getMapper(BookingMapper.class);
    private final LocalizationOutResponseMapper localizationOutResponseMapper = Mappers.getMapper(LocalizationOutResponseMapper.class);


    public void createLocalization(LocalizationDTO localizationDTO){
        Localization localization = Localization.builder()
                .room(localizationDTO.getRoom())
                .mapImage(mapImageRepository.findById(localizationDTO.getMapImage().getId()).orElseThrow(EntityNotFoundException::new))
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

    public StatsLocationResponse stats(){
        return new StatsLocationResponse(
                mapImageRepository.count(),
                localizationRepository.count(),
                bookingsRepository.count()
        );
//        return new StatsLocationResponse(
//                1L,1L,1L
//        );
    }

    public LocalizationOutResponse getSimple(Integer id) {
       return localizationRepository.findById(id).map(localizationOutResponseMapper::map).orElseThrow(EntityNotFoundException::new);
    }
}
