pipeline {
  agent any
  tools { 
        maven 'maven' 
        
    }
  triggers{
  	pollSCM '* * * * *'
  }
  stages {
    stage('code pull') {
      steps {
        checkout scm
        echo 'Git checkout complete'
      }
    }
    stage('build') {
      steps {
         sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    echo ${SONAR_RUNNER_HOME}
                ''' 
         sh 'mvn clean package'
      }
    }
    stage('test') {
      steps {
        parallel(
          "code analyze": {
            tool name: 'Sonar', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
            withSonarQubeEnv('Sonar') { // from SonarQube servers > name
    		sh "${HOME}/.jenkins/tools/hudson.plugins.sonar.SonarRunnerInstallation/Sonar/bin/sonar-runner -Dsonar.projectName=ecommerce -Dsonar.projectVersion=1.0 -Dsonar.projectKey=ecommerce -Dsonar.sources=src/main/java -Dsonar.java.binaries=target/classes"
            }
          },
          "unit tests": {
           sh 'mvn test'
            
          }
        )
      }
    }
    stage('dev-deploy') {
      steps {
        sh "BUILD_ID=dontKillMe nohup mvn tomcat7:run-war &"
      }
    }
    stage('regression test') {
      steps {
        sh 'mvn test'
      }
    }
    stage('qa-deploy') {
      steps {
        echo 'yo'
      }
    }
  }
}