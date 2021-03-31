FROM java:openjdk-8-jdk-alpine

RUN apk add --update redis
RUN apk add --update mysql



COPY Rates/target/Rates-1.0.jar  /home/Rates-1.0.jar
RUN chmod 777 /home/Rates-1.0.jar


EXPOSE 8086

CMD ["java" , "-jar" , "/home/Rates-1.0.jar"]

