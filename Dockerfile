FROM maven:3.8.2-jdk-8

WORKDIR /student-service
COPY . .
#RUN mvn clean package install -DskipTests

CMD mvn spring-boot:run