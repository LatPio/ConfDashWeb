//package com.flystonedev.cutomer.mapper;
//
//import com.flystonedev.cutomer.model.Customer;
//import com.flystonedev.cutomer.model.Department;
//import com.flystonedev.cutomer.model.InformationLinks;
//import com.flystonedev.cutomer.records.CustomerResponse;
//import com.flystonedev.cutomer.records.DepartmentResponse;
//import com.flystonedev.cutomer.records.InstitutionRecord;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.processing.Generated;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Generated(
//    value = "org.mapstruct.ap.MappingProcessor",
//    date = "2023-05-16T21:10:04+0200",
//    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
//)
//@Component
//public class CustomerMapper1  {
//
//    public CustomerResponse map(Customer customer) {
//        if ( customer == null ) {
//            return null;
//        }
//
//        Integer id = null;
//        String firstName = null;
//        String lastName = null;
//        String email = null;
//        String degree = null;
//        String phoneNumber = null;
//        List<InformationLinks> links = null;
//        DepartmentResponse department = null;
//        byte[] photo = null;
//
//        id = customer.getId();
//        firstName = customer.getFirstName();
//        lastName = customer.getLastName();
//        email = customer.getEmail();
//        degree = customer.getDegree();
//        phoneNumber = customer.getPhoneNumber();
//        List<InformationLinks> list = customer.getLinks();
//        if ( list != null ) {
//            links = new ArrayList<InformationLinks>( list );
//        }
//        department = departmentToDepartmentResponse( customer.getDepartment() );
//        byte[] photo1 = customer.getPhoto();
//        if ( photo1 != null ) {
//            photo = Arrays.copyOf( photo1, photo1.length );
//        }
//
//        CustomerResponse customerResponse = new CustomerResponse( id, firstName, lastName, email, degree, phoneNumber, links, department, photo );
//
//        return customerResponse;
//    }
//
//    public Customer map(CustomerResponse customerResponse) {
//        if ( customerResponse == null ) {
//            return null;
//        }
//
//        Customer.CustomerBuilder customer = Customer.builder();
//
//        customer.id( customerResponse.id() );
//        customer.email( customerResponse.email() );
//        customer.degree( customerResponse.degree() );
//        customer.firstName( customerResponse.firstName() );
//        customer.lastName( customerResponse.lastName() );
//        customer.phoneNumber( customerResponse.phoneNumber() );
//        List<InformationLinks> list = customerResponse.links();
//        if ( list != null ) {
//            customer.links( new ArrayList<InformationLinks>( list ) );
//        }
//        customer.department( departmentResponseToDepartment( customerResponse.department() ) );
//        byte[] photo = customerResponse.photo();
//        if ( photo != null ) {
//            customer.photo( Arrays.copyOf( photo, photo.length ) );
//        }
//
//        return customer.build();
//    }
//
//    public List<CustomerResponse> mapToRecord(List<Customer> customerList) {
//        if ( customerList == null ) {
//            return null;
//        }
//
//        List<CustomerResponse> list = new ArrayList<CustomerResponse>( customerList.size() );
//        for ( Customer customer : customerList ) {
//            list.add( map( customer ) );
//        }
//
//        return list;
//    }
//
//    protected DepartmentResponse departmentToDepartmentResponse(Department department) {
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
//
//        id = department.getId();
//        name = department.getName();
//        street = department.getStreet();
//        buildingNumber = department.getBuildingNumber();
//        flatNumber = department.getFlatNumber();
//        city = department.getCity();
//        postalCode = department.getPostalCode();
//        country = department.getCountry();
//
//        InstitutionRecord institutionRecord = null;
//
//        DepartmentResponse departmentResponse = new DepartmentResponse( id, name, street, buildingNumber, flatNumber, city, postalCode, country, institutionRecord );
//
//        return departmentResponse;
//    }
//
//    protected Department departmentResponseToDepartment(DepartmentResponse departmentResponse) {
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
