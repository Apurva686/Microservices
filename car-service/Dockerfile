FROM maven:3.6.3-jdk-8
COPY . /car-service
WORKDIR /car-service
RUN mvn clean install
EXPOSE 8090
WORKDIR /car-service/target
ENTRYPOINT ["java","-jar", "car-service-0.0.1-SNAPSHOT.jar"]