FROM maven:3.5-jdk-8 AS build  

#Copy root

COPY src /usr/src/app/src    
COPY pom.xml /usr/src/app  

#Copy Modules

COPY 0-gameserver-backend-plugin/src /usr/src/app/0-gameserver-backend-plugin/src
COPY 0-gameserver-backend-plugin/pom.xml /usr/src/app/0-gameserver-backend-plugin

COPY 1-gameserver-backend-adapters/src /usr/src/app/1-gameserver-backend-adapters/src    
COPY 1-gameserver-backend-adapters/pom.xml /usr/src/app/1-gameserver-backend-adapters 

COPY 2-gameserver-backend-application/src /usr/src/app/2-gameserver-backend-application/src    
COPY 2-gameserver-backend-application/pom.xml /usr/src/app/2-gameserver-backend-application 

COPY 3-gameserver-backend-domain/src /usr/src/app/3-gameserver-backend-domain/src    
COPY 3-gameserver-backend-domain/pom.xml /usr/src/app/3-gameserver-backend-domain 

# Build root

RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java
COPY --from=build /usr/src/app/0-gameserver-backend-plugin/target/0-gameserver-backend-plugin-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/app/gameserver-backend-1.0-SNAPSHOT.jar
EXPOSE 50051  
ENTRYPOINT ["java","-jar","/usr/app/gameserver-backend-1.0-SNAPSHOT.jar"]