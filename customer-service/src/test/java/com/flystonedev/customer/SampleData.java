package com.flystonedev.customer;

import com.flystonedev.customer.DTO.*;
import com.flystonedev.customer.model.*;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface SampleData {

    default List<Customer> getSampleOfCustomers(){
        Customer customer1 = Customer.builder()
                .id(2)
                .email("admin@email.com")
                .degree("PhD")
                .firstName("Admin")
                .lastName("Master")
                .phoneNumber("1234567890")
                .links(new ArrayList<>())
                .invoiceData(null)
//                .photo(ProfilePhoto.builder().id(1).build())
                .authID("aaaa")
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();
        Customer customer2 = Customer.builder()
                .id(1)
                .email("user@email.com")
                .degree("bachelor")
                .firstName("User")
                .lastName("Typical")
                .phoneNumber("1234567890")
                .links(new ArrayList<>())
                .authID("bbbb")
                .invoiceData(null)
//                .photo(ProfilePhoto.builder().id(2).build())
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();

        return Arrays.asList(customer2,customer1);
    }

    default Customer getSampleOfCustomerAtRegistration(){
        Customer customer1 = Customer.builder()
                .email("user@email.com")
                .firstName("User")
                .lastName("Typical")
                .authID("authId")
                .build();
        return customer1;
    }

    default CustomerCardDTO getSampleOfCustomerCardDTO(){
        CustomerCardDTO customerCardDTO = CustomerCardDTO.builder()
                .id(1)
                .firstName("User")
                .lastName("Typical")
                .degree("bachelor")
                .links(new ArrayList<>())
                .invoiceData(null)
                .photo(null)
                .build();
        return customerCardDTO;
    }
    default CustomerDTO getSampleOfCustomerDTO(){
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(1)
                .email("user@email.com")
                .degree("bachelor")
                .firstName("User")
                .lastName("Typical")
                .authID("bbbb")
                .phoneNumber("1234567890")
                .links(new ArrayList<>())
                .invoiceData(null)
                .photo(null)
                .build();
        return customerDTO;
    }

    default CustomerAdminDTO getSampleOfCustomerAdminDTO(){
        CustomerAdminDTO customerAdminDTO = CustomerAdminDTO.builder()
                .id(1)
                .email("user@email.com")
                .degree("bachelor")
                .firstName("User")
                .lastName("Typical")
                .authID("bbbb")
                .phoneNumber("1234567890")
                .links(new ArrayList<>())
                .invoiceData(null)
                .photo(null)
                .build();
        return customerAdminDTO;
    }

    default List<InvoiceData> getSampleOfDepartment(){
        InvoiceData invoiceData1 = InvoiceData.builder()
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
        InvoiceData invoiceData2 = InvoiceData.builder()
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

        return Arrays.asList(invoiceData1, invoiceData2);
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


//    default List<Institution> getSampleOfInstitution(){
//        Institution institution1 = Institution.builder()
//                .id(1)
//                .name("University")
//                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
//                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
//                .build();
//        Institution institution2 = Institution.builder()
//                .id(2)
//                .name("Company")
//                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
//                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
//                .build();
//
//        return Arrays.asList(institution1,institution2);
//
//    }

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

    default CustomerRegistrationRequest getSampleOfCustomerRegistrationRequest(){
        return new CustomerRegistrationRequest(
                "User",
                "Typical",
                "user@email.com",
                "password");
    }

    default UserRepresentation getSampleOfCustomerRepresentationWithParams(CustomerRegistrationRequest request){
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEmail(request.email());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(request.email());
        userRepresentation.setFirstName(request.firstName());
        userRepresentation.setLastName(request.lastName());

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(request.password());
        credentialRepresentation.setTemporary(false);
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
        return userRepresentation;
    }
    default UserRepresentation getSampleOfCustomerRepresentation(){
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEmail("user@email.com");
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername("user@email.com");
        userRepresentation.setFirstName("User");
        userRepresentation.setLastName("Typical");

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue("password");
        credentialRepresentation.setTemporary(false);
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
        return userRepresentation;
    }

    default InvoiceDataDTO getSampleOfDepartmentDTO(){
        InvoiceDataDTO invoiceDataDTO = InvoiceDataDTO.builder()
                .id(1)
                .name("Name")
                .street("Street")
                .buildingNumber("10")
                .flatNumber("a22")
                .city("New Your")
                .postalCode("1234-12")
                .country("USA")
                .institution("MIT")
                .build();
        return invoiceDataDTO;
    }

default InformationLinksRequest getSampleOFInformationLinksRequest (){
        InformationLinksRequest informationLinksRequest =
                new InformationLinksRequest(
                        "LinkedIn",
                        "http....",
                        CustomerDTO.builder().id(1).build());
        return informationLinksRequest;
}
    default InformationLinks getSampleOFInformationLink (){
        InformationLinks informationLinks = InformationLinks.builder()
                .id(1)
                .name("LinkedIn")
                .urlLink("http....")
                .authId("bbbb")
                .customer(getSampleOfCustomers().get(1))
                .build();
        return informationLinks;
    }
    default InformationLinksDTO getSampleOfInformationLinksDTO(){
        InformationLinksDTO informationLinksDTO = InformationLinksDTO.builder()
                .id(1)
                .name("LinkedIn")
                .urlLink("http....")
                .build();
        return informationLinksDTO;
    }
    default InformationLinksAdminRequest getSampleOfInformationLinksAdminRequest(){
        InformationLinksAdminRequest informationLinksAdminRequest =
                new InformationLinksAdminRequest(
                        "LinkedIn",
                        "http....",
                        getSampleOfCustomerDTO(),
                        "bbbb");
        return informationLinksAdminRequest;
    }

    default InformationLinksAdminDTO getSampleOfInformationLinksAdminDTO(){
        InformationLinksAdminDTO informationLinksAdminDTO = InformationLinksAdminDTO.builder()
                .id(1)
                .name("LinkedIn")
                .urlLink("http....")
                .authId("bbbb")
                .build();
        return informationLinksAdminDTO;
    }

//    default InstitutionDTO getSampleOfInstitutionDTO(){
//        InstitutionDTO institutionDTO = InstitutionDTO.builder()
//                .id(1)
//                .name("Institution")
//                .build();
//        return institutionDTO;
//    }

    default ProfilePhoto getSampleOfProfilePhoto(){
        ProfilePhoto profilePhoto = ProfilePhoto.builder()
                .id(1)
                .name("name")
                .type("text/plain")
                .data("Hello, World!".getBytes())
                .customer(getSampleOfCustomers().get(1))
                .build();
        return profilePhoto;
    }
    default MultipartFile getSampleMultipartPhotoProfile(){
        return new MockMultipartFile(
                "file",
                "name",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
    }

    default ProfilePhotoDTO getSampleOfProfilePhotoDTO(){
        ProfilePhotoDTO profilePhotoDTO = ProfilePhotoDTO.builder()
                .id(1)
                .name("name")
                .data("Hello, World!".getBytes())
                .authId("bbbb")
                .type("text/plain")
                .build();
        return profilePhotoDTO;
    }

    default Keycloak getSampleOfKeycloakRealInstance(){
        Keycloak keycloakInstance = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8181")
                .realm("confdashweb")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("spring-cloud-client")
                .clientSecret("eIEAsftRLNv8hk9FX1L9OR3jtaSqkIqe")
                .build();
        return keycloakInstance;

    }

    default Keycloak getSampleOfKeycloakTestInstance(String url){
        Keycloak keycloakInstance = KeycloakBuilder.builder()
                .serverUrl(url)
                .realm("confdashweb")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("spring-cloud-client")
                .clientSecret("secret")
                .build();
        return keycloakInstance;

    }

}
