//package com.flystonedev.cutomer.mapper;
//
//import com.flystonedev.cutomer.model.Department;
//import com.flystonedev.cutomer.records.DepartmentResponse;
//import com.flystonedev.cutomer.records.InstitutionRecord;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.processing.Generated;
//
//@Generated(
//    value = "org.mapstruct.ap.MappingProcessor",
//    date = "2023-05-16T21:10:04+0200",
//    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
//)
//@Component
//public class DepartmentMapperImpl1  {
//
//    public DepartmentResponse map(Department department) {
//        if ( department == null ) {
//            return null;
//        }
//
//        Integer id = null;
//        String name = null;
//        String street = null;
//        String buildingNumber = null;
//        String flatNumber = null;
//        String city = null;
//        String postalCode = null;
//        String country = null;
//        InstitutionRecord institutionRecord = null;
//
//        id = department.getId();
//        name = department.getName();
//        street = department.getStreet();
//        buildingNumber = department.getBuildingNumber();
//        flatNumber = department.getFlatNumber();
//        city = department.getCity();
//        postalCode = department.getPostalCode();
//        country = department.getCountry();
////        institutionRecord = department.getInstitution();
//
//        DepartmentResponse departmentResponse = new DepartmentResponse( id, name, street, buildingNumber, flatNumber, city, postalCode, country, institutionRecord );
//
//        return departmentResponse;
//    }
//
//    public Department map(DepartmentResponse departmentResponse) {
//        if ( departmentResponse == null ) {
//            return null;
//        }
//
//        Department.DepartmentBuilder department = Department.builder();
//
//        department.id( departmentResponse.id() );
//        department.name( departmentResponse.name() );
//        department.street( departmentResponse.street() );
//        department.buildingNumber( departmentResponse.buildingNumber() );
//        department.flatNumber( departmentResponse.flatNumber() );
//        department.city( departmentResponse.city() );
//        department.postalCode( departmentResponse.postalCode() );
//        department.country( departmentResponse.country() );
//
//        return department.build();
//    }
//}
