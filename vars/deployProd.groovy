def call(imageName, prodServer) {
    stage('Deploy to PROD') {
        timeout(time: 2, unit: 'MINUTES') {
            sh """
ssh -o StrictHostKeyChecking=no ${prodServer} '
docker pull ${imageName}:latest
docker stop prod-container || true
docker rm prod-container || true
docker run -d -p 8082:8080 --name prod-container ${imageName}:latest
'
"""
        }
    }
}
