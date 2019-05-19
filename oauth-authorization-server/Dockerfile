FROM openjdk:8-jre
MAINTAINER Bala <bala@email.com>

#RUN apk update && apk add bash curl && apk --update add openjdk8-jre

ARG JAR_FILE
ADD ./oauth-authorization-server/target/oauth-authorization-server.jar target/oauth-authorization-server.jar

ADD ./oauth-authorization-server/start_authorization_service.sh /usr/local/bin/start_authorization_service.sh
RUN chmod u+x /usr/local/bin/start_authorization_service.sh
ENTRYPOINT /usr/local/bin/start_authorization_service.sh


