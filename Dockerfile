FROM maven:3.5-jdk-8
ADD /target/jpaResApplication.jar jpaResApplication.jar
ENTRYPOINT [ "java","-jar","jpaRestApplication-0.0.1-SNAPSHOT.jar" ]
