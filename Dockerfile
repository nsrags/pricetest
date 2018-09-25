FROM centos:latest

MAINTAINER Uday Shankar <uday.thirumal@ascenareatil.com>

ENV PRICE_DEPLOY_DIR=/opt/price
ENV PRICE_LOG_DIR=/opt/price/logs


RUN yum -y update && \
    yum clean all && \
    yum -y install curl && \
    yum -y install wget tar &&\
    mkdir -p ${PRICE_DEPLOY_DIR} && \
    mkdir -p ${PRICE_LOG_DIR} && \
    yum -y install java-1.8.0-openjdk 

WORKDIR .
# This copies to local fat jar inside the image
ADD ./target/priceservice-0.0.1-SNAPSHOT.jar ${PRICE_DEPLOY_DIR}/priceservice.jar
RUN chmod 755 ${PRICE_DEPLOY_DIR}/priceservice.jar -R

# Setting up environment variable
ENV GOOGLE_APPLICATION_CREDENTIALS=""
ENV SPRING_PROFILE="prod"

ENTRYPOINT exec java -jar -Dserver.port=8080 -Dspring.profiles.active=$SPRING_PROFILE /opt/price/priceservice.jar

EXPOSE 8080
