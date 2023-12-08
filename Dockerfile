FROM gradle:latest AS BUILD
WORKDIR /usr/app/
COPY . .
RUN gradle build

#
# Package stage
#
FROM eclipse-temurin:17-jdk-jammy
ENV JAR_NAME=app.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8081