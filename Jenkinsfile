pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        parallel(
          "build dev": {
            echo 'something'
            
          },
          "build master": {
            echo 'somehting'
            
          },
          "build qa": {
            echo 'something'
            
          }
        )
      }
    }
    stage('integrate') {
      steps {
        echo 'something'
      }
    }
    stage('test') {
      steps {
        echo 'something'
      }
    }
    stage('qa-deploy') {
      steps {
        echo 'something'
      }
    }
  }
}