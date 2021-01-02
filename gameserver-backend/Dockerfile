FROM maven:3.5-jdk-11 AS build

#Copy root

COPY src /usr/src/app/src    
COPY pom.xml /usr/src/app  

#Copy Modules

COPY 0-gameserver-backend-plugin-api/src /usr/src/app/0-gameserver-backend-plugin-api/src
COPY 0-gameserver-backend-plugin-api/pom.xml /usr/src/app/0-gameserver-backend-plugin-api

COPY 0-gameserver-backend-plugin-database/src /usr/src/app/0-gameserver-backend-plugin-database/src
COPY 0-gameserver-backend-plugin-database/pom.xml /usr/src/app/0-gameserver-backend-plugin-database

COPY 1-gameserver-backend-adapter-api/src /usr/src/app/1-gameserver-backend-adapter-api/src
COPY 1-gameserver-backend-adapter-api/pom.xml /usr/src/app/1-gameserver-backend-adapter-api 

COPY 1-gameserver-backend-adapter-database/src /usr/src/app/1-gameserver-backend-adapter-database/src
COPY 1-gameserver-backend-adapter-database/pom.xml /usr/src/app/1-gameserver-backend-adapter-database 

COPY 2-gameserver-backend-application/src /usr/src/app/2-gameserver-backend-application/src
COPY 2-gameserver-backend-application/pom.xml /usr/src/app/2-gameserver-backend-application 

COPY 3-gameserver-backend-domain/src /usr/src/app/3-gameserver-backend-domain/src
COPY 3-gameserver-backend-domain/pom.xml /usr/src/app/3-gameserver-backend-domain 

# Build root

RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java
COPY --from=build /usr/src/app/0-gameserver-backend-plugin-api/target/0-gameserver-backend-plugin-api-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/app/gameserver-backend-1.0-SNAPSHOT.jar
EXPOSE 7001
ENTRYPOINT ["java","-jar","/usr/app/gameserver-backend-1.0-SNAPSHOT.jar"]