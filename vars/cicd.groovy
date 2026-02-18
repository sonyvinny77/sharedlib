def gitDownload(repo)
{
  git "https://github.com/IntelliqDevops/${repo}.git"
} 
def buildArtifact()
{
  sh 'mvn package'
} 
def prepareDockerContext() 
{
    sh '''
        mkdir -p docker
        cp webapp/target/webapp.war docker/webapp.war
        cat <<EOF > docker/Dockerfile
FROM tomcat:10
COPY webapp.war /usr/local/tomcat/webapps/
EXPOSE 8080
EOF
    '''
}
def buildDockerImage(imageName) 
{
    sh "docker build -t ${imageName} docker"
}
def runDockerContainer(containerName, imageName) 
{
    sh """
        docker rm -f ${containerName} || true
        docker run -d -p 8081:8080 --name ${containerName} ${imageName}
    """
}
