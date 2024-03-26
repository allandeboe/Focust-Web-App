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
FROM debian:bookworm-20240311

# DEPENDENCY INSTALLATION
RUN apt-get update && \
    apt-get upgrade && \
    apt-get install nodejs -y

RUN apt-get install npm -y

# COPY PROJECT
WORKDIR /usr/src/focust-react-app
COPY . .

RUN npm install && \
    npm run build

# BUILD TARGET & RUN
CMD ["npm", "start"]
EXPOSE 3000