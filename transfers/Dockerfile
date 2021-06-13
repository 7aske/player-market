FROM maven:3.8.1-jdk-8 as BUILD
COPY . /usr/src/transfers
WORKDIR /usr/src/transfers
RUN mvn -Dmaven.test.skip=true clean install
FROM openjdk:8-jre
EXPOSE 8080
COPY --from=BUILD /usr/src/transfers/target/*.war /opt/target/transfers.war
WORKDIR /opt/target
ENTRYPOINT ["java","-jar", "transfers.war"]