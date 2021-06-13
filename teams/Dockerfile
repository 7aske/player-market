FROM maven:3.8.1-jdk-8 as BUILD
COPY . /usr/src/teams
WORKDIR /usr/src/teams
RUN mvn -Dmaven.test.skip=true clean install
FROM openjdk:8-jre
EXPOSE 8080
COPY --from=BUILD /usr/src/teams/target/*.war /opt/target/teams.war
WORKDIR /opt/target
ENTRYPOINT ["java","-jar", "teams.war"]