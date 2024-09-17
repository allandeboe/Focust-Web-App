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
    apt-get upgrade -y

# COPY PROJECT
COPY . /usr/src/focust-spring-app/
WORKDIR /usr/src/focust-spring-app/

# This command is unusually long in cases where
# there are problems with the installation of
# certain packages due to issues with deb.debian.org:80
RUN chmod +x ./dependencies.sh
RUN ["bash", "./dependencies.sh"]

# BUILD TARGET & RUN
CMD ["mvn", "spring-boot:run"]
EXPOSE 8080