pipeline {
  agent any
  stages {
    stage('checkout') {
      steps {
        echo 'Git checkout complete'
      }
    }
    stage('build') {
      steps {
        echo 'b'
      }
    }
    stage('test') {
      steps {
        parallel(
          "code analyze": {
            echo 'd'
            
          },
          "unit tests": {
            echo 'a'
            
          }
        )
      }
    }
    stage('package') {
      steps {
        echo 'e'
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