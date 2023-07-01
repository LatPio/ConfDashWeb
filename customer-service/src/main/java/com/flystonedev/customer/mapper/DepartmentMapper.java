package com.flystonedev.customer.mapper;

import com.flystonedev.customer.DTO.DepartmentDTO;
import com.flystonedev.customer.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

  DepartmentDTO map(Department department);

  Department map(DepartmentDTO departmentResponse);
}
