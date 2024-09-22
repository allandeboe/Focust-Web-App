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
    }
    stages {

        stage("Build MySQL Database") {
            agent any
            environment {
                MYSQL_DATABASE_CREDENTIALS = credentials('focust-mysql-database')

                DATABASE_HOST_PORT = '3307'
                DATABASE_CONTAINER_PORT = '3306'
            }
            steps {
                sh 'docker network create ${BACK_END_DATABASE_NETWORK_NAME} --force'
                sh 'docker volume create ${DATABASE_VOLUME_NAME} --force'
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
                sh 'docker ls'
            }
        }

        stage("Build Back-end") {
            agent {
                docker {
                    image 'maven:3.9.9-ibm-semeru-21-jammy'
                }
            }
            environment {
                MYSQL_DATABASE_CREDENTIALS = credentials('focust-mysql-database')
                SPRING_SECURITY_CREDENTIALS = credentials('focust-spring-security')

                BACK_END_SERVER_MODE = 'dev'
                BACK_END_HOST_PORT = '8080'
            }
            steps {
                sh 'ls'
                sh 'mkdir ./focust-back-end/src/main/resources'
                sh 'cd ./focust-back-end/src/main/resources'
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

                sh 'cd ../../../'
                sh 'mvn clean install'
                sh 'cd ../'
            }
        }

        stage("Build Images") {
            agent any
            steps {
                sh 'docker build -t allandeboe/focust-back-end:latest ./focust-back-end'
                sh 'docker ls'
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