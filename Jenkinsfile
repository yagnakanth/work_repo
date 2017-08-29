pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        echo 'a'
      }
    }
    stage('code analyze') {
      steps {
        parallel(
          "code analyze": {
            echo 'b'
            
          },
          "unit tests": {
            echo 'c'
            
          }
        )
      }
    }
    stage('package') {
      steps {
        echo 'd'
      }
    }
    stage('test') {
      steps {
        echo 'e'
      }
    }
    stage('qa-deploy') {
      steps {
        echo 'f'
      }
    }
  }
}