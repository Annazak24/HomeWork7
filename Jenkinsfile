pipeline {
    agent {
        docker {
            image 'maven:3.9.4-eclipse-temurin-21'
            args '--network selenoid1'
        }
    }

    tools {
        allure 'Allure 2.30'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                sh 'pwd'
                sh 'git branch --show-current || true'
                sh 'grep -R "http://android:4723" -n src || true'
                sh 'curl http://android:4723/status || true'
                sh 'curl -I http://wiremock:8080/wishlist.apk || true'

                sh 'mvn clean test -Dmaven.test.failure.ignore=true'

                sh 'echo "========================="'
                sh 'echo "ALLURE DEBUG START"'
                sh 'echo "========================="'
                sh 'ls -la || true'
                sh 'ls -la allure-results || true'
                sh 'find allure-results -type f || true'
                sh 'ls -la target || true'
                sh 'ls -la target/allure-results || true'
                sh 'find target/allure-results -type f || true'
                sh 'echo "========================="'
                sh 'echo "ALLURE DEBUG END"'
                sh 'echo "========================="'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'allure-results/**', allowEmptyArchive: true

            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']]
            ])
        }
    }
}