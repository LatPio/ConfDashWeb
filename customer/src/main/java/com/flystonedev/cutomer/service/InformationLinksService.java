package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.mapper.InformationLinksMapper;
import com.flystonedev.cutomer.model.InformationLinks;
import com.flystonedev.cutomer.records.InformationLinksResponse;
import com.flystonedev.cutomer.repository.InformationLinksRepository;
import com.flystonedev.cutomer.repository.InstitutionRepository;
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

    public void addLinks(InformationLinksResponse informationLinksResponse){
        InformationLinks informationLinks = InformationLinks.builder()
                .name(informationLinksResponse.name())
                .urlLink(informationLinksResponse.urlLink())
//                .customer(informationLinksResponse.customer())
                .build();
    informationLinksRepository.save(informationLinks);
    }

    public List<InformationLinksResponse> informationLinksResponseList(){
        List<InformationLinks> linksList = informationLinksRepository.findAll();
        return linksList.stream().map(informationLinksMapper::map).collect(Collectors.toList());
    }

    public InformationLinksResponse get(Integer id){
        return informationLinksRepository.findById(id).map(informationLinks -> informationLinksMapper.map(informationLinks)).orElse(null);
    }

    public InformationLinksResponse update(InformationLinksResponse informationLinksResponse){
        InformationLinksResponse exist = get(informationLinksResponse.id());
        if (exist == null) {
            return null;
        }
        InformationLinks updated = informationLinksRepository.save(informationLinksMapper.map(informationLinksResponse));
        return informationLinksMapper.map(updated);
    }

    public void delete(Integer id){informationLinksRepository.deleteById(id);}

}
