package com.flystonedev.site.service;

import com.flystonedev.site.DTO.PhotosAndFilesDTO;
import com.flystonedev.site.mapper.PhotoAndFileMapper;
import com.flystonedev.site.model.PhotosAndFiles;
import com.flystonedev.site.repository.PhotosAndFilesRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhotosAndFilesService {

    private final PhotosAndFilesRepository photosAndFilesRepository;

    private final PhotoAndFileMapper photoAndFileMapper = Mappers.getMapper(PhotoAndFileMapper.class);


    public void saveFile(PhotosAndFilesDTO photosAndFilesDTO){
        PhotosAndFiles photosAndFiles = PhotosAndFiles.builder()
                .name(photosAndFilesDTO.getName())
                .file(photosAndFilesDTO.getFile())
                .build();
        photosAndFilesRepository.save(photosAndFiles);
    }

    public List<PhotosAndFilesDTO> photosAndFilesDTOList(){
        List<PhotosAndFiles> photosAndFilesList = photosAndFilesRepository.findAll();
        return photosAndFilesList.stream().map(photoAndFileMapper::map).collect(Collectors.toList());
    }
    public PhotosAndFilesDTO get(Integer id){
        return photosAndFilesRepository.findById(id).map(photoAndFileMapper::map).orElse(null);
    }

    public PhotosAndFilesDTO update(PhotosAndFilesDTO photosAndFilesDTO){
        PhotosAndFilesDTO exist = get(photosAndFilesDTO.getId());
        if(exist == null){
            return  null;
        }
        PhotosAndFiles updated = photosAndFilesRepository.save(photoAndFileMapper.map(photosAndFilesDTO));
        return photoAndFileMapper.map(updated);

    }

    public void delete (Integer id){
        photosAndFilesRepository.deleteById(id);
    }
}
