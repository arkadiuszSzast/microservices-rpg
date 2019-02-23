FROM openjdk:11-jre
ADD build/libs/eureka-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app.jar"]