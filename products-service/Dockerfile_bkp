FROM openjdk:8-jre
MAINTAINER Bala <bala@email.com>

#RUN apk update && apk add bash curl && apk --update add openjdk8-jre

ARG JAR_FILE
ADD ./products-service/target/products-service.jar target/products-service.jar

ADD ./products-service/start_products_service.sh /usr/local/bin/start_products_service.sh
RUN chmod u+x /usr/local/bin/start_products_service.sh
ENTRYPOINT /usr/local/bin/start_products_service.sh

