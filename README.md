# bravi-contact-list

There are two ways to startup this application:

First way (JDK-8, Maven 3.3 and mongodb must be installed on your machine):

1 - clone or download this project <br>
2 - open up a terminal and startup mongo with the command: mongod (make sure mongo is listening to port 27017)<br>
3 - walk your terminal through where pom.xml file is placed in.<br>
4 - install project with maven using the following instruction: mvn clean install<br>
4 - startup the app using dev profile with the command below:<br>
    java -Dspring.profiles.active=dev -jar target/*.jar<br>
<br>

Second way will be via docker-compose:

1 - clone or download this project <br>
2 - open up a terminal and go to where docker-compose.yml is placed in.<br>
3 - build services with the command: docker-compose build<br>
(Maybe an exception should be thrown, don't worry not a big deal)<br>
4 - Startup all services with the command: docker-compose up<br>
<br>

This application is a Rest API where you can check its endpoints and documentation via swagger. Go to the following link:
<br>
http://localhost:8080/swagger-ui.html

<br>
Note: this application is running on http://localhost:8080