FROM adoptopenjdk/openjdk11

ARG JAR_FILE=./target/tn-ycyd-listener-payment-result-0.0.1-SNAPSHOT.jar

VOLUME /tmp

RUN mkdir -p /cert
COPY Bangkok /etc/localtime
ADD helm/truststore /cert/
RUN echo Asia/Bangkok > /etc/timezone
RUN mkdir -p /data
RUN mkdir -p /home
ADD ${JAR_FILE} /home/app.jar

WORKDIR /home

EXPOSE 9700

ENTRYPOINT exec java -jar $JAVA_OPTS -Dlogback.timezone=Asia/Bangkok /home/app.jar --spring.config.location=/home/application.yml