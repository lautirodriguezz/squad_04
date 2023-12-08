FROM gradle:jdk17-jammy AS BUILD_STAGE
COPY --chown=gradle:gradle TPG /home/gradle
RUN gradle build || return 1

#
# Package stage
#
FROM eclipse-temurin:17-jdk-jammy
VOLUME /tmp
COPY --from=BUILD_STAGE /home/gradle/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8081