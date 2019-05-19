FROM openjdk:8-jre
MAINTAINER Bala <bala@email.com>

#RUN apk update && apk add bash curl && apk --update add openjdk8-jre

ARG JAR_FILE
ADD ./cart-service/target/cart-service.jar target/cart-service.jar

ADD ./cart-service/start_cart_service.sh /usr/local/bin/start_cart_service.sh
RUN chmod u+x /usr/local/bin/start_cart_service.sh
ENTRYPOINT /usr/local/bin/start_cart_service.sh


