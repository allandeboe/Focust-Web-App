/**
 * Jenkinsfile - used for Focust's CI/CD pipeline
 *
 * @author  Allan DeBoe <allan.m.deboe@gmail.com>
 * @date    September 17th, 2024
 */
pipeline {
    agent none
    environment {
        DATABASE_VOLUME_NAME = 'mysql-data'
        BACK_END_DATABASE_NETWORK_NAME = 'spring-mysql'
        FRONT_END_BACK_END_NETWORK_NAME = 'react-spring'

        BACK_END_HOST_PORT = '8080'
        BACK_END_CONTAINER_PORT = '8080'
        FRONT_END_HOST_PORT = '3000'
        FRONT_END_CONTAINER_PORT = '3000'
    }
    stages {

        stage("Create Networks & Volumes") {
            agent any
            steps {
                echo 'Removing Networks & Volumes...'
                sh 'docker network rm ${BACK_END_DATABASE_NETWORK_NAME} --force'
                sh 'docker network rm ${FRONT_END_BACK_END_NETWORK_NAME} --force'
                sh 'docker volume rm ${DATABASE_VOLUME_NAME} --force'
                echo 'Removed!'

                echo 'Creating Networks & Volumes...'
                sh 'docker network create ${BACK_END_DATABASE_NETWORK_NAME}'
                sh 'docker network create ${FRONT_END_BACK_END_NETWORK_NAME}'
                sh 'docker volume create ${DATABASE_VOLUME_NAME}'
            }
        }

        stage("Build MySQL Database") {
            agent any
            environment {
                MYSQL_DATABASE_CREDENTIALS = credentials('focust-mysql-database')

                DATABASE_HOST_PORT = '3307'
                DATABASE_CONTAINER_PORT = '3306'
            }
            steps {
                sh '''
                    docker run -d --name focust-mysql \
                    -e MYSQL_DATABASE=focust_db \
                    -e MYSQL_ROOT_PASSWORD=$MYSQL_DATABASE_CREDENTIALS_PSW \
                    --network ${BACK_END_DATABASE_NETWORK_NAME} \
                    --restart=always \
                    -p ${DATABASE_HOST_PORT}:${DATABASE_CONTAINER_PORT} \
                    -v ${DATABASE_VOLUME_NAME} \
                    mysql:latest
                '''
                sh 'docker ps'
            }
        }

        stage("Build Back-end Image") {
            agent any
            environment {
                MYSQL_DATABASE_CREDENTIALS = credentials('focust-mysql-database')
                SPRING_SECURITY_CREDENTIALS = credentials('focust-spring-security')

                BACK_END_SERVER_MODE = 'prod'
            }
            steps {
                sh 'ls'
                dir ('./focust-back-end') {
                    sh 'ls'
                    dir ('./src/main/resources') {
                        sh 'echo "focust.server-mode=${BACK_END_SERVER_MODE}" > application.properties'
                        sh 'echo "server.port=${BACK_END_HOST_PORT}" > application.properties'
                        sh 'echo "spring.jpa.hibernate.ddl-auto=update" > application.properties'
                        sh 'echo "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver" > application.properties'
                        sh 'echo "spring.datasource.url=jdbc:mysql://mysql/focust_db" > application.properties'
                        sh 'echo "spring.datasource.username=$MYSQL_DATABASE_CREDENTIALS_USR" > application.properties'
                        sh 'echo "spring.datasource.password=$MYSQL_DATABASE_CREDENTIALS_PSW" > application.properties'
                        sh 'echo "spring.security.user.name=$SPRING_SECURITY_CREDENTIALS_USR" > application.properties'
                        sh 'echo "spring.security.user.password=$SPRING_SECURITY_CREDENTIALS_PSW" > application.properties'
                        sh 'echo "management.endpoints.enabled-by-default=false" > application.properties'
                        sh 'echo "management.endpoint.health.enabled=true" > application.properties'
                        sh 'echo "management.endpoints.web.exposure.include=health" > application.properties'
                    }
                    sh 'docker build -t allandeboe/focust-back-end:latest .'
                }
                sh 'docker ps'
            }
        }

        stage("Build Front-end Image") {
            agent any 
            steps {
                sh 'apt-get install jq -y'
                sh 'jq --version'
                sh 'ls'
                dir ('./focust-front-end') {
                    sh 'ls'
                    sh 'mv package.json temp.json'
                    sh '''
                        jq '.proxy |= "http://focust-spring-app:${BACK_END_HOST_PORT}"' temp.json > package.json
                    '''
                    sh 'rm temp.json'
                    sh 'docker build -t allandeboe/focust-front-end:latest .'
                }
                sh 'docker ps'
            }
        }

        stage("Run Back-End Image") {
            agent any 
            steps {
                sh '''
                    docker run -d --name focust-spring-app \
                    --network ${BACK_END_DATABASE_NETWORK_NAME} \
                    --network ${FRONT_END_BACK_END_NETWORK_NAME} \
                    --restart=always \
                    -p ${BACK_END_HOST_PORT}:${BACK_END_CONTAINER_PORT} \
                    allandeboe/focust-back-end:latest
                '''
            }
        }

        stage("Run Front-End Image") {
            agent any 
            steps {
                sh '''
                    docker run -d --name focust-react-app \
                    --network ${FRONT_END_BACK_END_NETWORK_NAME} \
                    --restart=always \
                    -p ${FRONT_END_HOST_PORT}:${FRONT_END_CONTAINER_PORT} \
                    allandeboe/focust-front-end:latest
                '''
            }
        }

    }
    post {
        success {
            echo 'Pipeline completed!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}