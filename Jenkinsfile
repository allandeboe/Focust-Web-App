pipeline {
    agent any
    stages {
        stage("Build Docker Containers") {
            steps {
                sh 'docker --version'
                sh 'docker-compose build'
            }
        }
        stage("Run Docker Containers") {
            steps {
                sh 'docker compose up'
            }
        }
    }
    post {
        success {
            echo 'Successfully an containers!'
        }
        failure {
            echo 'Unable to run containers'
        }
        always {
            sh 'docker compose down --remove-orphans'
        }
    }
}