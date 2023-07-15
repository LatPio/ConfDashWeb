//package com.flystonedev.cutomer.mapper;
//
//import com.flystonedev.cutomer.model.Customer;
//import com.flystonedev.cutomer.model.InformationLinks;
//import com.flystonedev.cutomer.records.InformationLinksResponse;
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
//public class InformationLinksMapperImpl1  {
//
//    public InformationLinksResponse map(InformationLinks informationLinks) {
//        if ( informationLinks == null ) {
//            return null;
//        }
//
//        Integer id = null;
//        String name = null;
//        String urlLink = null;
//        Customer customer = null;
//
//        id = informationLinks.getId();
//        name = informationLinks.getName();
//        urlLink = informationLinks.getUrlLink();
//        customer = informationLinks.getCustomer();
//
//        InformationLinksResponse informationLinksResponse = new InformationLinksResponse( id, name, urlLink, customer );
//
//        return informationLinksResponse;
//    }
//
//    public InformationLinks map(InformationLinksResponse informationLinksResponse) {
//        if ( informationLinksResponse == null ) {
//            return null;
//        }
//
//        InformationLinks.InformationLinksBuilder informationLinks = InformationLinks.builder();
//
//        informationLinks.id( informationLinksResponse.id() );
//        informationLinks.name( informationLinksResponse.name() );
//        informationLinks.urlLink( informationLinksResponse.urlLink() );
//        informationLinks.customer( informationLinksResponse.customer() );
//
//        return informationLinks.build();
//    }
//}
