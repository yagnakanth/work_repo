pipeline {
  agent any
  tools { 
        maven 'maven' 
        sonar 'sonar'
    }
  environment{
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
                ''' 
         sh 'mvn clean package'
      }
    }
    stage('test') {
      steps {
        parallel(
          "code analyze": {
            sh "${sonar}/bin/sonar-runner -Dsonar.projectName=ecommerce -Dsonar.projectVersion=1.0 -Dsonar.projectKey=ecommerce -Dsonar.sources=."
            
          },
          "unit tests": {
           sh 'mvn test'
            
          }
        )
      }
    }
    stage('dev-deploy') {
      steps {
        sh 'mvn tomcat7:run-war &'
      }
    }
    stage('regression test') {
      steps {
        echo 'f'
      }
    }
    stage('qa-deploy') {
      steps {
        echo 'yo'
      }
    }
  }
}