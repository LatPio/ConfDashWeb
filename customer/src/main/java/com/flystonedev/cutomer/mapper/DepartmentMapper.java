package com.flystonedev.cutomer.mapper;

import com.flystonedev.cutomer.DTO.DepartmentDTO;
import com.flystonedev.cutomer.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

  DepartmentDTO map(Department department);

  Department map(DepartmentDTO departmentResponse);
}
