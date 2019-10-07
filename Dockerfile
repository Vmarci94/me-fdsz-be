FROM ubuntu:latest
LABEL maintainer="vmarci94@gmail.com"
RUN apt-get update && apt-get install -y \
    openjdk-11-jdk \
    git \
    maven
RUN git clone https://github.com/Vmarci94/me-fdsz-be && cd me-fdsz-be && git checkout develop && mvn clean package
#EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/me-fdsz-be/target/me-fdsz-1.0-SNAPSHOT.jar"]