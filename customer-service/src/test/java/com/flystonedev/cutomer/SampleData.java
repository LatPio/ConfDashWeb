package com.flystonedev.cutomer;

import com.flystonedev.cutomer.model.*;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface SampleData {

    default List<Customer> getSampleOfCustomers(){
        Customer customer1 = Customer.builder()
                .id(1)
                .email("admin@email.com")
                .degree("PhD")
                .firstName("Admin")
                .lastName("Master")
                .phoneNumber("1234567890")
                .links(new ArrayList<>())
                .department(null)
//                .photo(ProfilePhoto.builder().id(1).build())
                .authID("aaaa")
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();
        Customer customer2 = Customer.builder()
                .id(2)
                .email("user@email.com")
                .degree("bachelor")
                .firstName("User")
                .lastName("Typical")
                .phoneNumber("1234567890")
                .links(new ArrayList<>())
                .authID("bbbb")
                .department(null)
//                .photo(ProfilePhoto.builder().id(2).build())
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();

        return Arrays.asList(customer1,customer2);
    }

    default List<Department> getSampleOfDepartment(){
        Department department1= Department.builder()
                .id(1)
                .name("Test Department")
                .street("street1")
                .buildingNumber("1A")
                .flatNumber("35F")
                .city("New York")
                .postalCode("1234-123")
                .country("USA")
                .institution(null)
                .customers(new ArrayList<>())
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();
        Department department2= Department.builder()
                .id(2)
                .name("Test2 Department")
                .street("street2")
                .buildingNumber("2A")
                .flatNumber("235F")
                .city("Hollywood")
                .postalCode("1234-123")
                .country("USA")
                .institution(null)
                .customers(new ArrayList<>())
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();

        return Arrays.asList(department1,department2);
    }

    default List<InformationLinks> getSampleOfInformationLinks(){
        InformationLinks link1 = InformationLinks.builder()
                .id(1)
                .name("LinkedIn")
                .urlLink("htttp.....")
                .authId("aaaa")
                .customer(getSampleOfCustomers().get(0))
                .build();
        InformationLinks link2 = InformationLinks.builder()
                .id(2)
                .name("Company site")
                .urlLink("ttp.....")
                .authId("aaaa")
                .customer(getSampleOfCustomers().get(0))
                .build();
        return Arrays.asList(link1,link2);

    }


    default List<Institution> getSampleOfInstitution(){
        Institution institution1 = Institution.builder()
                .id(1)
                .name("University")
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();
        Institution institution2 = Institution.builder()
                .id(2)
                .name("Company")
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();

        return Arrays.asList(institution1,institution2);

    }

    default List<ProfilePhoto> getSampleOfProfilePhotos(){
        ProfilePhoto photo1 = ProfilePhoto.builder()
                .id(1)
                .name("photo1")
                .type("jpg")
                .authId("aaaa")
                .data("Hello, World!".getBytes())
                .customer(Customer.builder().id(1).build())
                .build();
        ProfilePhoto photo2 = ProfilePhoto.builder()
                .id(2)
                .name("photo2")
                .type("jpg")
                .authId("bbbb")
                .data("Hello, World!".getBytes())
                .customer(Customer.builder().id(2).build())
                .build();

        return Arrays.asList(photo1,photo2);

    }

    default MultipartFile getSampleMultipart(){
        return new MockMultipartFile(
                "file",
                "Hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );}
}
