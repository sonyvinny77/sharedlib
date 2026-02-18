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
    sh 'cp target/*.war docker/webapp.war'
}
