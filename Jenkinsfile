/**
 * Jenkinsfile - used for Focust's CI/CD pipeline
 *
 * @author  Allan DeBoe <allan.m.deboe@gmail.com>
 * @date    September 17th, 2024
 */
pipeline {
    agent {
        label 'linux'
    }
    environment {

        // Jenkins credentials are used since we are creating the project from repo
        // and we need to ensure that sensitive information is not leaked when commiting
        // to the public repository.
        MYSQL_DATABASE_CREDENTIALS = credentials('focust-mysql-database')
        SPRING_SECURITY_CREDENTIALS = credentials('focust-spring-security')

        DATABASE_VOLUME_NAME = 'mysql-data'
        BACK_END_DATABASE_NETWORK_NAME = 'spring-mysql'
        FRONT_END_BACK_END_NETWORK_NAME = 'react-spring'

        DATABASE_DOCKER_NAME = "focust-mysql"
        DATABASE_HOST_PORT = '3307'
        DATABASE_CONTAINER_PORT = '3306'

        BACK_END_SERVER_DOCKER_NAME = 'focust-spring-app'
        BACK_END_SERVER_DOCKER_TAG = "spring-app"
        BACK_END_HOST_PORT = '8080'
        BACK_END_CONTAINER_PORT = '8080'
        BACK_END_SERVER_MODE = 'dev' // for ./focust-back-end/src/main/resources/application.properties

        FRONT_END_SERVER_DOCKER_NAME = 'focust-react-app'
        FRONT_END_SERVER_DOCKER_TAG = "react-app"
        FRONT_END_HOST_PORT = '3000'
        FRONT_END_CONTAINER_PORT = '3000'

    }

    stages {

        // Ensure that we do have Docker, as this pipeline's job is similar to that of
        // docker-compose.yml file; Build & Run the needed docker containers
        stage("Stage 0: Check Tooling") {
            steps {
                sh 'apt-get install docker jq -y'

                // "jq" is used to modify ./focust-front-end/package.json
                sh 'jq --version'
                
                sh 'docker version'
                sh 'docker info'
                sh 'docker compose version'
            }
        }

        stage("Stage 1a: Set up Back-end Server Image") {
            steps {
                sh 'ls'
                sh 'cd ./focust-back-end'

                // Since the application.properties file contains sensitive information, and this file
                // is needed to run the back-end server, we need to create it ourselves
                sh 'mkdir ./src/main/resources'
                sh 'cd ./src/main/resources'
                sh 'echo "focust.server-mode=${BACK_END_SERVER_MODE}" > application.properties'
                sh 'echo "server.port=${BACK_END_HOST_PORT}" > application.properties'
                sh 'echo "spring.jpa.hibernate.ddl-auto=update" > application.properties'
                sh 'echo "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver" > application.properties'
                sh 'echo "spring.datasource.username=$MYSQL_DATABASE_CREDENTIALS_USR" > application.properties'
                sh 'echo "spring.datasource.password=$MYSQL_DATABASE_CREDENTIALS_PSW" > application.properties'
                sh 'echo "spring.security.user.name=$SPRING_SECURITY_CREDENTIALS_USR" > application.properties'
                sh 'echo "spring.security.user.password=$SPRING_SECURITY_CREDENTIALS_PSW" > application.properties'
                sh 'echo "management.endpoints.enabled-by-default=false" > application.properties'
                sh 'echo "management.endpoint.health.enabled=true" > application.properties'
                sh 'echo "management.endpoints.web.exposure.include=health" > application.properties'

                // Since we just need the application.properties file to have the back-end server working,
                // we can go ahead and create the Docker image for the back-end server, which will run the 
                // Dockerfile.
                sh 'cd ../../../'
                sh 'ls'
                sh "docker buildx --tag ${BACK_END_SERVER_DOCKER_TAG}"
                sh 'cd ../'
            }
        }

        stage("Stage 1b: Set up Front-end Server Image") {
            steps {
                sh 'ls'
                sh 'cd ./focust-front-end' // assuming we are back at root directory...

                // We will need to modify the package.json to change the proxy to match that with the
                // environment variables used in this Jenkinsfile. 
                sh 'mv package.json package-temp.json'
                sh "jq -r '.proxy |= \"http://${BACK_END_SERVER_DOCKER_TAG}:${BACK_END_HOST_PORT}\"' package-temp.json > package.json"
                sh 'rm package-temp.json'

                sh "docker buildx --tag ${FRONT_END_SERVER_DOCKER_TAG}"
                sh 'cd ../'
            }
        }

        stage("Stage 2: Set up Networks & Volumes") {
            steps {
                sh "docker volume create ${DATABASE_VOLUME_NAME} --force"
                sh "docker network create ${BACK_END_DATABASE_NETWORK_NAME} --force"
                sh "docker network create ${FRONT_END_BACK_END_NETWORK_NAME} --force"
            }
        }

        stage("Stage 3a: Run Database Docker Container") {
            steps {
                sh '''
                    docker run --name ${DATABASE_DOCKER_NAME} \
                    -e MYSQL_DATABASE=focust-db \
                    -e MYSQL_ROOT_PASSWORD=$MYSQL_DATABASE_CREDENTIALS_PSW \
                    --network ${BACK_END_DATABASE_NETWORK_NAME} \
                    --restart=always \
                    -p ${DATABASE_HOST_PORT}:${DATABASE_CONTAINER_PORT} \
                    -d mysql:latest
                '''
            }
        }

        stage("Stage 3b: Run Back-end Server Container") {
            steps {
                sh '''
                    docker run --name ${BACK_END_SERVER_DOCKER_NAME} \
                    --restart=always \
                    -p ${BACK_END_HOST_PORT}:${BACK_END_CONTAINER_PORT} \
                    -d ${BACK_END_SERVER_DOCKER_TAG}
                '''
                sh "docker network connect ${BACK_END_DATABASE_NETWORK_NAME} ${BACK_END_SERVER_DOCKER_NAME}"
                sh "docker network connect ${FRONT_END_BACK_END_NETWORK_NAME} ${BACK_END_SERVER_DOCKER_NAME}"
            }
        }

        stage("Stage 3c: Run Front-end Server Container") {
            steps {
                sh '''
                    docker run --name ${FRONT_END_SERVER_DOCKER_NAME} \
                    -p ${FRONT_END_HOST_PORT}:${FRONT_END_CONTAINER_PORT} \
                    -d ${FRONT_END_SERVER_DOCKER_TAG}
                '''
                sh "docker network connect ${FRONT_END_BACK_END_NETWORK_NAME} ${FRONT_END_SERVER_DOCKER_NAME}"
            }
        }

    }
    post {
        always {
            script {
                if (getContext(hudson.FilePath)) {
                    sh 'docker system prune --all --force'
                }
                echo 'Finished pipeline!'
            }
        }
    }
}