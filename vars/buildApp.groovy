def call() 
{
    stage('Checkout') 
  {
        git 'https://github.com/sonyvinny77/maven.git'
  }

    stage('Build Maven') 
  {
        sh 'mvn clean package'
  }
}
