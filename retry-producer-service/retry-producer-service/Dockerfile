FROM ubuntu:20.04
RUN apt update -y
RUN apt install -y openjdk-21-jdk
RUN mkdir -p /u01/applications/
WORKDIR /u01/applications
COPY build/libs/retry-producer-service-0.0.1-SNAPSHOT.jar .
EXPOSE 9001
CMD ["java", "-jar", "retry-producer-service-0.0.1-SNAPSHOT.jar"]