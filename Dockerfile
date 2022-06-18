#FROM openjdk:11
#MAINTAINER lsrodrigues
#COPY target/builders-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM openjdk:11
#
#WORKDIR /opt/app
#
#COPY /target/builders-0.0.1-SNAPSHOT.jar app.jar
#
#ENTRYPOINT ["java","-jar","app.jar"]

#FROM openjdk:11
##EXPOSE 8080
#ARG JAR_FILE=target/builders-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM adoptopenjdk/openjdk11:latest

RUN mkdir -p /software

ADD target/builders.jar /software/app.jar

CMD java -Dserver.port=$PORT $JAVA_OPTS -jar /software/app.jar