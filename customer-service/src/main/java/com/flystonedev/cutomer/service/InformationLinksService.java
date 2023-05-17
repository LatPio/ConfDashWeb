package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.DTO.InformationLinksDTO;
import com.flystonedev.cutomer.mapper.InformationLinksMapper;
import com.flystonedev.cutomer.model.InformationLinks;
import com.flystonedev.cutomer.repository.InformationLinksRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InformationLinksService {

    private final InformationLinksRepository informationLinksRepository;

    private final InformationLinksMapper informationLinksMapper = Mappers.getMapper(InformationLinksMapper.class);

    public void addLinks(InformationLinksDTO informationLinksDTO){
        InformationLinks informationLinks = InformationLinks.builder()
                .name(informationLinksDTO.getName())
                .urlLink(informationLinksDTO.getUrlLink())
//                .customer(informationLinksDTO.)
                .build();
    informationLinksRepository.save(informationLinks);
    }

    public List<InformationLinksDTO> informationLinksResponseList(){
        List<InformationLinks> linksList = informationLinksRepository.findAll();
        return linksList.stream().map(informationLinksMapper::map).collect(Collectors.toList());
    }

    public InformationLinksDTO get(Integer id){
        return informationLinksRepository.findById(id).map(informationLinksMapper::map).orElse(null);
    }

    public InformationLinksDTO update(InformationLinksDTO informationLinksDTO){
        InformationLinksDTO exist = get(informationLinksDTO.getId());
        if (exist == null) {
            return null;
        }
        InformationLinks updated = informationLinksRepository.save(informationLinksMapper.map(informationLinksDTO));
        return informationLinksMapper.map(updated);
    }

    public void delete(Integer id){informationLinksRepository.deleteById(id);}

}
