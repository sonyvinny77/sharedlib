def call(imageName) {
    stage('Build Docker Image') {
        sh """
cat <<EOF > Dockerfile
FROM tomcat:9
COPY webapp/target/webapp.war /usr/local/tomcat/webapps/
EXPOSE 8080
EOF

docker build -t ${imageName}:${env.BUILD_NUMBER} .
docker tag ${imageName}:${env.BUILD_NUMBER} ${imageName}:latest
"""
    }

    stage('Push Docker Image') {
        sh """
docker push ${imageName}:${env.BUILD_NUMBER}
docker push ${imageName}:latest
"""
    }
}
