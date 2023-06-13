package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.DTO.DepartmentDTO;
import com.flystonedev.cutomer.exeption.EntityNotFoundException;
import com.flystonedev.cutomer.exeption.config.EntityAlreadyInDatabaseException;
import com.flystonedev.cutomer.mapper.DepartmentMapper;
import com.flystonedev.cutomer.model.Department;
import com.flystonedev.cutomer.model.Institution;
import com.flystonedev.cutomer.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);

    public void addDepartment(DepartmentDTO departmentDTO){
        DepartmentDTO nameExist = departmentMapper.map(departmentRepository.findDepartmentByName(departmentDTO.getName()));
        if (nameExist != null) {
            throw new EntityAlreadyInDatabaseException();
        } else {
            Department department = Department.builder()
                    .name(departmentDTO.getName())
                    .street(departmentDTO.getStreet())
                    .buildingNumber(departmentDTO.getBuildingNumber())
                    .flatNumber(departmentDTO.getFlatNumber())
                    .city(departmentDTO.getCity())
                    .postalCode(departmentDTO.getPostalCode())
                    .country(departmentDTO.getCountry())
                    .institution(Institution.builder().id(departmentDTO.getInstitution().getId()).build())
                    .build();
            departmentRepository.save(department);
        }

    }
    public List<DepartmentDTO> departmentResponseList(){
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList.stream().map(departmentMapper::map).collect(Collectors.toList());
    }

    public DepartmentDTO get(Integer id){
        return departmentRepository.findById(id).map(departmentMapper::map).orElseThrow(EntityNotFoundException::new);
    }
    public DepartmentDTO update(DepartmentDTO departmentDTO){
        DepartmentDTO exist = get(departmentDTO.getId());
        if(exist == null) {
            throw new EntityNotFoundException();
        }
        Department updated = departmentRepository.save(departmentMapper.map(departmentDTO));
        return departmentMapper.map(updated);
    }

    public void delete(Integer id){
        departmentRepository.deleteById(id);
    }
}
