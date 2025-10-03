FROM tomcat:9.0
COPY target/app.war /usr/local/tomcat/webapps/
EXPOSE 8080