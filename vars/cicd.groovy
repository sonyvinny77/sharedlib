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
    sh 'cp webapp/target/*.war docker/webapp.war'
}
