# Stage 1: Build WAR with Maven
FROM maven:3.8.7 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Deploy to Tomcat
FROM tomcat:9.0
COPY --from=builder /app/target/app.war /usr/local/tomcat/webapps/
EXPOSE 8080
