def call(String envName) {
        stages {

            stage('Download Code') {
                steps {
                    git 'https://github.com/IntelliqDevops/maven.git'
                }
            }

            stage('Build Maven Project') {
                steps {
                    sh 'mvn clean package'
                }
            }

            stage('Prepare Docker Context') {
                steps {
                    sh '''
                        rm -rf docker
                        mkdir docker
                        cp webapp/target/webapp.war docker/webapp.war
                        cp Dockerfile docker/
                    '''
                }
            }

            stage('Build Docker Image') {
                steps {
                    script {
                        if (envName == "dev") {
                            sh 'docker build -t regapp:dev docker'
                        } else if (envName == "prod") {
                            sh 'docker build -t regapp:prod docker'
                        }
                    }
                }
            }

            stage('Deploy') {
                steps {
                    script {
                        if (envName == "dev") {
                            sh '''
                                docker rm -f regapp-dev || true
                                docker run -d -p 8081:8080 --name regapp-dev regapp:dev
                            '''
                        } else if (envName == "prod") {
                            sh '''
                                docker rm -f regapp-prod || true
                                docker run -d -p 8082:8080 --name regapp-prod regapp:prod
                            '''
                        }
                    }
                }
            }
        }
    }
}
