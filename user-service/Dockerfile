FROM openjdk:8-jre
MAINTAINER Bala <bala@email.com>

#RUN apk update && apk add bash curl && apk --update add openjdk8-jre

ARG JAR_FILE
ADD ./user-service/target/user-service.jar target/user-service.jar

ADD ./user-service/start_user_service.sh /usr/local/bin/start_user_service.sh
RUN chmod u+x /usr/local/bin/start_user_service.sh
ENTRYPOINT /usr/local/bin/start_user_service.sh

