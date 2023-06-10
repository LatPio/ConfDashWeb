package com.flystonedev.site.service;

import com.flystonedev.site.DTO.FilesDTO;
import com.flystonedev.site.exeption.EntityNotFoundException;
import com.flystonedev.site.mapper.FilesMapper;
import com.flystonedev.site.model.Files;
import com.flystonedev.site.repository.FilesRepository;
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
public class FilesService {

    private final FilesRepository filesRepository;

    private final FilesMapper filesMapper = Mappers.getMapper(FilesMapper.class);


    public void saveFile(MultipartFile multipartFile) throws IOException {
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Files files = Files.builder()
                .name(filename)
                .type(multipartFile.getContentType())
                .data(multipartFile.getBytes())
                .build();
        filesRepository.save(files);
    }

    public List<FilesDTO> filesDTOList(){
        List<Files> filesList = filesRepository.findAll();
        return filesList.stream().map(filesMapper::map).collect(Collectors.toList());
    }
    public FilesDTO get(Integer id){
        return filesRepository.findById(id).map(filesMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    public FilesDTO update(FilesDTO filesDTO){
        FilesDTO exist = get(filesDTO.getId());
        if(exist == null){
            return  null;
        }
        Files updated = filesRepository.save(filesMapper.map(filesDTO));
        return filesMapper.map(updated);

    }

    public void delete (Integer id){
        filesRepository.deleteById(id);
    }
}
