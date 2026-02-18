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
        cp Dockerfile docker/
    '''
}

