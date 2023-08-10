FROM openjdk:19
COPY ./target/order-service-1.0-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java","-jar","/tmp/order-service-1.0-SNAPSHOT.jar"]