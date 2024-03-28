# Dockerfile
#
# This is used to set up a (Linux) docker container for the 
# back-end server.
#
# @author   Allan DeBoe (allan.m.deboe@gmail.com)
# @date     March 18th, 2024

###########################################################
# DOCKER IMAGE SET-UP
# (update image version at your own risk)
FROM debian:latest

# DEPENDENCY INSTALLATION
RUN apt-get update --fix-missing && \
    apt-get upgrade && \
    apt-get install nodejs -y

# This command is unusually long in cases where
# there are problems with the installation of
# npm due to issues with deb.debian.org:80
RUN apt-get install npm -y || \
    apt-get update --fix-missing && \
    apt-get install npm -y

# COPY PROJECT
WORKDIR /usr/src/focust-react-app
COPY . .

# UPDATE NPM IF NEEDED
RUN npm update

# BUILD PROJECT
RUN npm cache clean --force && \
    npm install && \
    npm run build

# RUN
CMD ["npm", "start"]
EXPOSE 3000