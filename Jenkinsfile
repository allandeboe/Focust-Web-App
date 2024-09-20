/**
 * Jenkinsfile - used for Focust's CI/CD pipeline
 *
 * @author  Allan DeBoe <allan.m.deboe@gmail.com>
 * @date    September 17th, 2024
 */
pipeline {
    agent none
    stages {

        stage("Build Back-end") {
            agent {
                docker {
                    image 'maven:3.5.0'
                }
            }
            environment {
                MYSQL_DATABASE_CREDENTIALS = credentials('focust-mysql-database')
                SPRING_SECURITY_CREDENTIALS = credentials('focust-spring-security')

                BACK_END_SERVER_MODE = 'dev'
                BACK_END_HOST_PORT = '8080'
            }
            steps {
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

                sh 'cd ../../../'
                sh 'mvn clean install'
            }
        }

        stage("Build Images") {
            agent any
            steps {
                sh 'docker build -t allandeboe/focust-back-end ./focust-back-end'
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