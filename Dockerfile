# Stage 1: Build with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

# Stage 2: Run with Tomcat
FROM tomcat:9.0-jdk17
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
