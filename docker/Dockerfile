FROM maven:3.3-jdk-8
MAINTAINER Edney Roldao
COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN mvn clean install
ENTRYPOINT java -jar target/*.jar
EXPOSE 8080
