FROM openjdk:11
WORKDIR /app
ADD target/ReadingIsGood-0.0.1-SNAPSHOT.jar ReadingIsGood-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "ReadingIsGood-0.0.1-SNAPSHOT.jar"]