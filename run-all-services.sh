#!/bin/bash

mvn -pl api-gateway spring-boot:run &
mvn -pl discovery-server spring-boot:run &
mvn -pl abstracts-service spring-boot:run &
mvn -pl customer-service spring-boot:run &
mvn -pl localization-service spring-boot:run &
mvn -pl event-service spring-boot:run &
mvn -pl basket-service spring-boot:run &
mvn -pl site_management-service spring-boot:run &
wait