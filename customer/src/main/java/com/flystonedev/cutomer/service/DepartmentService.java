package com.flystonedev.cutomer.service;

import com.flystonedev.cutomer.mapper.DepartmentMapper;
import com.flystonedev.cutomer.model.Department;
import com.flystonedev.cutomer.records.DepartmentResponse;
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

    public void addDepartment(DepartmentResponse departmentResponse){
        Department department = Department.builder()
                .name(departmentResponse.name())
                .street(departmentResponse.street())
                .buildingNumber(departmentResponse.buildingNumber())
                .flatNumber(departmentResponse.flatNumber())
                .city(departmentResponse.city())
                .country(departmentResponse.country())
                .build();
        departmentRepository.save(department);
    }
    public List<DepartmentResponse> departmentResponseList(){
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList.stream().map(departmentMapper::map).collect(Collectors.toList());
    }

    public DepartmentResponse get(Integer id){
        return departmentRepository.findById(id).map(department -> departmentMapper.map(department)).orElse(null);
    }
    public DepartmentResponse update(DepartmentResponse departmentResponse){
        DepartmentResponse exist = get(departmentResponse.id());
        if(exist == null) {
            return null;
        }

        Department updated = departmentRepository.save(departmentMapper.map(departmentResponse));

        return departmentMapper.map(updated);
    }

    public void delete(Integer id){
        departmentRepository.deleteById(id);
    }
}
