//package com.flystonedev.cutomer.mapper;
//
//import com.flystonedev.cutomer.model.Institution;
//import com.flystonedev.cutomer.records.InstitutionRecord;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.processing.Generated;
//
//@Generated(
//    value = "org.mapstruct.ap.MappingProcessor",
//    date = "2023-05-16T21:10:05+0200",
//    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
//)
//@Component
//public class InstitutionMapperImpl1 {
//
//    public InstitutionRecord map(Institution institution) {
//        if ( institution == null ) {
//            return null;
//        }
//
//        Integer id = null;
//        String name = null;
//
//        id = institution.getId();
//        name = institution.getName();
//
//        InstitutionRecord institutionRecord = new InstitutionRecord( id, name );
//
//        return institutionRecord;
//    }
//
//    public Institution map(InstitutionRecord institutionRecord) {
//        if ( institutionRecord == null ) {
//            return null;
//        }
//
//        Institution.InstitutionBuilder institution = Institution.builder();
//
//        institution.id( institutionRecord.id() );
//        institution.name( institutionRecord.name() );
//
//        return institution.build();
//    }
//}
