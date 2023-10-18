FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080

COPY target/Tranfe-0.0.1-SNAPSHOT.jar tranferMoney.jar

CMD ["java", "-jar","tranferMoney.jar"]