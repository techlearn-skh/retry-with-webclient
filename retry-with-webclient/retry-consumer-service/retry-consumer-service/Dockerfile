FROM ubuntu:20.04
RUN apt update -y
RUN apt install -y openjdk-21-jdk
RUN mkdir -p u01/applications/
EXPOSE 9001
COPY build/libs/retry-producer-service-0.0.1-SNAPSHOT.jar /u01/applications/
CMD java -jar /u01/applications/retry-producer-service-0.0.1-SNAPSHOT.jar