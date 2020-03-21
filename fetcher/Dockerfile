FROM maven:3.5.2-jdk-8-alpine AS build
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml package
FROM java:8-jre 
COPY --from=build /usr/src/app/target/fetcher-1.0-SNAPSHOT.jar /usr/app/fetcher-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/fetcher-1.0-SNAPSHOT.jar"]


# FROM maven:3-jdk-8
# ENV HOME=/home/usr/app
# RUN mkdir -p $HOME
# WORKDIR $HOME
# ADD pom.xml $HOME
# RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
# ADD . $HOME
# RUN ["mvn", "package"]
# EXPOSE 8005
# CMD ["bash"]
# # CMD ["java", "-jar", "./target/fetcher-1.0-SNAPSHOT.jar"]


# FROM maven:3.5-jdk-8 as maven
# COPY ./pom.xml ./pom.xml
# COPY ./nbactions.xml ./nbactions.xml
# RUN mvn dependency:go-offline package -B
# COPY ./src ./src
# RUN mvn package
