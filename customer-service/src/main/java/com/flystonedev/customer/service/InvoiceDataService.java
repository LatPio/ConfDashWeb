package com.flystonedev.customer.service;

import com.flystonedev.customer.DTO.InvoiceDataDTO;
import com.flystonedev.customer.exeption.EntityNotFoundException;
import com.flystonedev.customer.mapper.InvoiceDataMapper;
import com.flystonedev.customer.model.InvoiceData;
import com.flystonedev.customer.repository.InvoiceDataRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceDataService {

    private final InvoiceDataRepository departmentRepository;

    private final InvoiceDataMapper departmentMapper = Mappers.getMapper(InvoiceDataMapper.class);

    @Transactional
    public InvoiceDataDTO addDepartment(InvoiceDataDTO invoiceDataDTO){

        InvoiceData invoiceData = InvoiceData.builder()
                .name(invoiceDataDTO.getName())
                .street(invoiceDataDTO.getStreet())
                .buildingNumber(invoiceDataDTO.getBuildingNumber())
                .flatNumber(invoiceDataDTO.getFlatNumber())
                .city(invoiceDataDTO.getCity())
                .postalCode(invoiceDataDTO.getPostalCode())
                .country(invoiceDataDTO.getCountry())
                .institution(invoiceDataDTO.getInstitution())
                .institutionShortName(invoiceDataDTO.getInstitutionShortName())
                .taxIdentificationNumber(invoiceDataDTO.getTaxIdentificationNumber())
                .build();
        InvoiceData saved = departmentRepository.save(invoiceData);
        return departmentMapper.map(saved);
    }
    public List<InvoiceDataDTO> departmentResponseList(){
        List<InvoiceData> invoiceDataList = departmentRepository.findAll();
        return invoiceDataList.stream().map(departmentMapper::map).collect(Collectors.toList());
    }

    public InvoiceDataDTO get(Integer id){
        return departmentRepository.findById(id).map(departmentMapper::map).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public InvoiceDataDTO update(InvoiceDataDTO invoiceDataDTO){
        InvoiceDataDTO exist = get(invoiceDataDTO.getId());
        if(exist == null) {
            throw new EntityNotFoundException();
        }
        InvoiceData updated = departmentRepository.save(departmentMapper.map(invoiceDataDTO));
        return departmentMapper.map(updated);
    }

    public void delete(Integer id){
        departmentRepository.deleteById(id);
    }
}
