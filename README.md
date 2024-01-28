
<h1 >ConfDashWeb</h1>
<h1 align='center'>BackEnd Spring-boot App. for Conference Management and Service</h1>

<div align='center'>
<br>
<img src="https://img.shields.io/badge/Java-ED8B00?style=plactic&logo=openjdk&logoColor=white" alt="">
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=plastic&logo=spring&logoColor=white" alt="">
<img src="https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white" alt="">

</div>

<div align='center'>
<br>

<img src="https://badges.pufler.dev/visits/LatPio/confdashweb" alt="">
</div>


## How to Run:

### Requirements
For building and running the application you need:

 - JDK 1.17
 - Maven 3
 - Docker
   
### Running the application locally
###  1. Backend services

Backend services consist of local services such as gateway, discovery and other dedicated services, and third-party such as keycloak, zpikin and postgres. To run them:

1. First in command prompt start containers with keycloak, postgres database: run command `docker compose -f docker-compose-local-services.yml up -d`
2. Run `$ mvn install  -f pom.xml` command to ensure every dependency is downloaded
3. Next in terminal at root directory of the project execute `/bin/bash ./run-all-services.sh`, this will run all from command prompt

You can also run every service in separate terminal window by executing jar file or executing run command on specific path to service. 



### 2. Frontend

In Cloned repository, frontend code is located in suborder `front-end`. To use frontend service:

1. In terminal open directory `..\front-end`
2. Run `npm install` to get the dependencies
3. Run `npm run start` to get it running on http://localhost:4200
4. Register new user. For simplicity registration don't consist of email validation or confirmation.
5. At running `keycloak` service open http://localhost:8181
6. Login with login as `admin` and password as `admin`
7. Under `confdahweb` realm find user and add to chosen registered user admin privilege.
8. Now you can see all pages in client, as well as for every registered users and for admin.

## About:



ConfDasWeb is a powerful web application designed to streamline the organization and management of scientific conferences. With its comprehensive set of features, ConfDasWeb simplifies the collection of abstracts from conference participants and facilitates the seamless sharing of essential information with attendees.

One of the key functionalities of ConfDasWeb is its abstract submission system. It provides an intuitive interface for participants to submit their abstracts online, ensuring a smooth and efficient submission process. This feature eliminates the need for manual handling of abstracts, reducing administrative overhead and potential errors.

Moreover, ConfDasWeb serves as a centralized hub for conference-related information. It allows organizers to effortlessly disseminate crucial details about the event, such as the conference schedule, keynote speakers, session topics, and important updates. Attendees can access this information conveniently through the web application, ensuring they stay informed and can plan their participation accordingly.

Application still in development and such is not ready for use. Needed to add things such as email-password-validation, sending emails to users, front page management, configuration of proxy in docker containers.

## Main features:

- Microservice architecture(ApiGateway, Eureka-Discovery, Zipkin, Keycloak, Postgres)
- Login Access with Admin and Users Management
- User adding and management Abstracts (Admin accepts abstracts for presentation on website)
- User create own profile for easy lookup (Finding Lecturer his abstract and contact info)
- At the Time of Conference accepted abstract are accessible at website with necessary information (similar to book of abstracts) 
- Admin create evens such as lecture(can be seperated for example poster, short communication,plenary lecture and others ), coffee-break, dinner, social evens and others
- Admin create place such as hall, rooms, lecture hall connected with map that can be easily navigate between lectures at venue
- User can add to his account interesting lectures which next application help to schedule them 

<br/>

# Technologies used across the project:
- Java 17 <img width="23px" src="https://cdn-icons-png.flaticon.com/512/226/226777.png"/>
- Springboot (web, security, cloud, netflix-eureka, and few more) <img width="29px" src="https://user-images.githubusercontent.com/33158051/103466606-760a4000-4d14-11eb-9941-2f3d00371471.png"/>
- Angular <img height="20px" src="https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white">
- Zipkin <img width="23px" src="https://zipkin.io/public/img/logo_png/zipkin_vertical_grey_gb.png"/>
- Keycloak <img width="80px" src="https://www.keycloak.org/resources/images/logo.svg"/>
- Maven <img width="120px" src="https://maven.apache.org/images/apache-maven-project.png"/>
- Postgresql <img width="20px" src="https://www.postgresql.org/media/img/about/press/elephant.png"/>
- H2 <img width="30px" src="https://www.h2database.com/html/images/h2-logo-2.png"/>
- Lombok <img width="36px" src="https://kodejava.org/wp-content/uploads/2018/12/lombok.png"/>
- Testcontainers <img width="80px" src="https://testcontainers.com/images/testcontainers-logo.svg"/>
- RestAssured <img width="18px" src="https://avatars.githubusercontent.com/u/19369327?s=280&v=4"/>
- Mockito <img width="50px" src="https://github.com/mockito/mockito.github.io/raw/master/img/logo%402x.png"/>
- Junit 5 <img width="50px" src="https://upload.wikimedia.org/wikipedia/commons/5/59/JUnit_5_Banner.png"/>

- Docker <img width="25px" src="https://www.docker.com/wp-content/uploads/2022/03/Moby-logo.png"/> / Docker compose <img width="40px" src="https://miro.medium.com/max/453/1*_5tOkcXb7RaVvjYpSqZXpg.png"/>
- Git <img width="20px" src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/Git_icon.svg/1024px-Git_icon.svg.png"/> / Github <img width="25px" height="23" src="https://cdn-icons-png.flaticon.com/512/25/25231.png"/>

