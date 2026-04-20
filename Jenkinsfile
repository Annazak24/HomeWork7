pipeline {
    agent any

    tools {
        allure 'Allure 2.30'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Prepare config') {
            steps {
                writeFile file: 'config.yaml', text: params.CONFIG

                script {
                    env.APPIUM_URL = sh(
                        script: "grep '^appiumUrl:' config.yaml | cut -d':' -f2- | xargs",
                        returnStdout: true
                    ).trim()

                    env.APP_URL = sh(
                        script: "grep '^app:' config.yaml | cut -d':' -f2- | xargs",
                        returnStdout: true
                    ).trim()

                    env.ENVIRONMENT = sh(
                        script: "grep '^environment:' config.yaml | cut -d':' -f2- | xargs",
                        returnStdout: true
                    ).trim()

                    echo "Appium URL: ${env.APPIUM_URL}"
                    echo "App URL: ${env.APP_URL}"
                    echo "Environment: ${env.ENVIRONMENT}"
                }
            }
        }

        stage('Build Docker Image Once If Needed') {
            steps {
                sh '''
                    docker image inspect mobile-tests >/dev/null 2>&1 || docker build -t mobile-tests .
                '''
            }
        }

        stage('Run Mobile Tests in Docker') {
            steps {
                sh '''
                    rm -rf allure-results
                    mkdir -p allure-results

                    docker run --rm \
                      --volumes-from jenkins \
                      -w "$WORKSPACE" \
                      --network selenoid1 \
                      mobile-tests \
                      mvn test \
                        -DappiumUrl="${APPIUM_URL}" \
                        -Dapp="${APP_URL}" \
                        -Denvironment="${ENVIRONMENT}" \
                        -DdatabaseUsername=student \
                        -DdatabasePassword=student \
                        -Dmaven.test.failure.ignore=true \
                        -Dallure.results.directory=allure-results
                '''
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