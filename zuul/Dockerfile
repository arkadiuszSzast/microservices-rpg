FROM openjdk:11-jre
ADD build/libs/zuul-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app.jar"]