FROM maven:3.6.3-jdk-8
COPY . /api-gateway
WORKDIR /api-gateway
RUN mvn clean install
EXPOSE 8090
WORKDIR /api-gateway/target
ENTRYPOINT ["java","-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]