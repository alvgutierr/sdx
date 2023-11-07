FROM maven:3.8.6-jdk-11-slim AS mbuild
WORKDIR /opt/app
COPY pom.xml .
COPY src ./src
#RUN mvn -e -B -f /opt/app/pom.xml -Dmaven.test.skip=true package
RUN mvn -e -B -f /opt/app/pom.xml package
 
FROM alpine:3.18.0
RUN apk update && apk upgrade -U -a
RUN apk add --no-cache ca-certificates && apk add --upgrade openjdk11-jre
RUN apk add -U tzdata
ENV TZ=America/Santiago
RUN cp /usr/share/zoneinfo/America/Santiago /etc/localtime
RUN echo "America/Santiago" > /etc/timezone

COPY --from=mbuild /opt/app/target/*.jar /opt/app/api.jar

EXPOSE 8089
CMD [ "java", "-jar", "/opt/app/api.jar" ]

#docker build -t api-fav .
#docker run -d --name api-favorites -it -p 8089:8089 api-fav
