package com.flystonedev.cutomer.mapper;

import com.flystonedev.cutomer.model.Department;
import com.flystonedev.cutomer.records.DepartmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentResponse map(Department department);

    Department map(DepartmentResponse departmentResponse);
}
