# Dockerfile
#
# This is used to set up a (Linux) docker container for the 
# back-end server.
#
# @author   Allan DeBoe (allan.m.deboe@gmail.com)
# @date     March 16th, 2024

###########################################################
# DOCKER IMAGE SET-UP
# (update image version at your own risk)
FROM debian:latest

# DEPENDENCY INSTALLATION
RUN apt-get update --fix-missing && \
    apt-get upgrade && \
    apt-get install openjdk-17-jdk openjdk-17-jre maven -y

# COPY PROJECT
WORKDIR /usr/src/focust-spring-app
COPY . .

# BUILD TARGET & RUN
CMD ["mvn", "spring-boot:run"]
EXPOSE 8080