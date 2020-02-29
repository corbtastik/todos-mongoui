FROM openjdk:8-jdk-alpine
VOLUME /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} todos-mongoui.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /todos-mongoui.jar ${0} ${@}"]