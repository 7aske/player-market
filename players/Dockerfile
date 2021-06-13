FROM maven:3.8.1-jdk-8 as BUILD
COPY . /usr/src/players
WORKDIR /usr/src/players
RUN mvn -Dmaven.test.skip=true clean install
FROM openjdk:8-jre
EXPOSE 8080
COPY --from=BUILD /usr/src/players/target/*.war /opt/target/players.war
WORKDIR /opt/target
ENTRYPOINT ["java","-jar", "players.war"]