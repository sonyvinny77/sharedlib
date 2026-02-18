def call(imageName, devServer) {
    stage('Deploy to DEV') {
        sh """
ssh -o StrictHostKeyChecking=no ${devServer} '
docker pull ${imageName}:latest
docker stop dev-container || true
docker rm dev-container || true
docker run -d -p 8081:8080 --name dev-container ${imageName}:latest
'
"""
    }
}
