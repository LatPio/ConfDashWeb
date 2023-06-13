package com.flystonedev.abstracts;

import com.flystonedev.abstracts.model.AbstractsEntity;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface SampleData {

    default List<AbstractsEntity> getSampleAbstract(){
        AbstractsEntity abstract1 = new AbstractsEntity(1,"Title 1", "Body of abstract 1", "John Doe", "walmart",
                1,1,"aaaa-bbbb", false, new ArrayList<>(),
                LocalDate.of(2022,5,5).atTime(12,22,12).atZone(ZoneOffset.UTC).toInstant(),
                LocalDate.of(2022,5,5).atTime(12,22,12).atZone(ZoneOffset.UTC).toInstant());
        AbstractsEntity abstract2= AbstractsEntity.builder()
                .id(2)
                .abstractTitle("Title 2")
                .body("Body of abstract 2")
                .authors("Bush Junior")
                .affiliation("Capitol")
                .presenterId(2)
                .ownerId(2)
                .authId("vava-dddd")
                .accepted(true)
                .files(new ArrayList<>())
                .createdAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .updatedAt(LocalDate.of(2022,5,1).atTime(10,35,44).atZone(ZoneOffset.UTC).toInstant())
                .build();

        return Arrays.asList(abstract1,abstract2);
    }
}
