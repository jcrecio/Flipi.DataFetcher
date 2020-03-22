FROM maven:3.5.2-jdk-8-alpine AS build
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml package
FROM java:8-jre 
COPY --from=build /usr/src/app/target/fetcher-1.0-SNAPSHOT.jar /usr/app/fetcher-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/fetcher-1.0-SNAPSHOT.jar"]
