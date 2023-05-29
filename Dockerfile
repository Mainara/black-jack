FROM gradle:8.1.1-jdk17 as builder

WORKDIR /backend
COPY . /backend/
RUN gradle build -x test

FROM openjdk:17-jdk-alpine

RUN mkdir /opt/app
COPY --from=builder /backend/build/libs/blackjack-0.0.1-SNAPSHOT.jar /opt/app/blackjack-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/opt/app/blackjack-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080