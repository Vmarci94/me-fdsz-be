FROM maven:3.6.2-jdk-11
LABEL maintainer="vmarci94@gmail.com"
RUN apt-get update && apt-get install -y git
RUN git clone https://github.com/Vmarci94/me-fdsz-be && cd me-fdsz-be && git checkout develop && mvn clean package
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/me-fdsz-be/target/me-fdsz-1.0-SNAPSHOT.jar"]