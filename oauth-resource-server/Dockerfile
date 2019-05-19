FROM openjdk:8-jre
MAINTAINER Bala <bala@email.com>

#RUN apk update && apk add bash curl && apk --update add openjdk8-jre

ARG JAR_FILE
ADD ./oauth-resource-server/target/oauth-resource-server.jar target/oauth-resource-server.jar

ADD ./oauth-resource-server/start_resource_server_service.sh /usr/local/bin/start_resource_server_service.sh
RUN chmod u+x /usr/local/bin/start_resource_server_service.sh
ENTRYPOINT /usr/local/bin/start_resource_server_service.sh


