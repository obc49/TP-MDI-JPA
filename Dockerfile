FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD password
ENV MYSQL_DATABASE mydatabases
ENV MYSQL_USER root
ENV MYSQL_PASSWORD: password
ADD script.sql /docker-entrypoint-initdb.d
EXPOSE 3306  

FROM maven:3.5-jdk-8
ADD /target/jpaResApplication.jar jpaResApplication.jar
ENTRYPOINT [ "java","-jar","jpaRestApplication-0.0.1-SNAPSHOT.jar" ]
