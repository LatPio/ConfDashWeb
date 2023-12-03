package com.flystonedev.localization.service;

import com.flystonedev.localization.DTO.MapImageDTO;
import com.flystonedev.localization.DTO.MapImageRequest;
import com.flystonedev.localization.DTO.MapImageResponse;
import com.flystonedev.localization.DTO.MapImageWithRoomsDTO;
import com.flystonedev.localization.exeption.EntityNotFoundException;
import com.flystonedev.localization.mapper.MapImageMapper;
import com.flystonedev.localization.mapper.MapImageWithRoomsMapper;
import com.flystonedev.localization.model.MapImage;
import com.flystonedev.localization.repository.MapImageRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MapImageService {

    private final MapImageRepository mapImageRepository;
    private final MapImageMapper mapImageMapper = Mappers.getMapper(MapImageMapper.class);

    private final MapImageWithRoomsMapper mapImageWithRoomsMapper = Mappers.getMapper(MapImageWithRoomsMapper.class);

    public MapImageDTO saveMapImage(MultipartFile file, MapImageRequest mapImageRequest) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        MapImage mapImage = MapImage.builder()
                .fileName(filename)
                .name(mapImageRequest.getName())
                .data(file.getBytes())
                .build();
        mapImageRepository.save(mapImage);
        return mapImageMapper.map(mapImage);
    }

    public MapImageDTO getMapImageDTO(Integer id){
        return mapImageRepository
                .findById(id)
                .map(mapImageMapper::map)
                .orElseThrow(EntityNotFoundException::new);
    }

    public MapImageWithRoomsDTO getMapImageWithRoomsDTO(Integer id){
        return mapImageRepository
                .findById(id)
                .map(mapImageWithRoomsMapper::map)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<MapImageResponse> mapImageDTOSimpleList(){
        List<MapImage> mapImageDTOList = mapImageRepository.findAll();
        return mapImageDTOList.stream().map(mapImageMapper::mapResponse).collect(Collectors.toList());
    }
    public List<MapImageDTO> mapImageDTOList(){
        List<MapImage> mapImageDTOList = mapImageRepository.findAll();
        return mapImageDTOList.stream().map(mapImageMapper::map).collect(Collectors.toList());
    }

    public MapImageDTO updateMapImage(MultipartFile file, MapImageDTO mapImageDTO) throws IOException{
        MapImage exist = mapImageRepository.findById(mapImageDTO.getId()).orElseThrow(EntityNotFoundException::new);

        if (file.getBytes().length !=0){
            exist.setData(file.getBytes());
            exist.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
            exist.setName(mapImageDTO.getName());

            MapImage updated = mapImageRepository.save(exist);
            return mapImageMapper.map(updated);
        } else {

            MapImage updated = mapImageRepository.save(exist);
            return mapImageMapper.map(updated);

        }
    }
    public void deleteMApImage(Integer id){
        MapImageDTO exist = getMapImageDTO(id);
        if(exist == null){
            throw new EntityNotFoundException();
        } else {
            mapImageRepository.deleteById(id);
        }
    }
}
