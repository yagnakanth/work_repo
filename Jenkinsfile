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
         sh 'mvn clean compile'
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
        script{
        	withEnv(['JENKINS_NODE_COOKIE=dontKill']){
        		
        		sh 'nohup mvn tomcat7:run-war &'
        		sh 'sleep 10'
        	}
        }
      }
    }
    stage('smoke test') {
      steps {
        sh 'rm -rf ecommerce-smoke-uitests'
        sh 'git clone https://github.com/vishnunc/ecommerce-uitests.git ecommerce-smoke-uitests'
        sh 'cd ecommerce-smoke-uitests && ./gradlew cucumber -Pfeatures=src/test/resources/gradle/cucumber/smoke'
        step([$class: 'CucumberReportPublisher', jsonReportDirectory: 'target', fileIncludePattern: 'cucumber.json'])
      }
    }
    stage('qa-deploy') {
      steps {
        
        sh 'mvn package'
      }
    }
    stage('ui tests') {
      steps {
        sh 'rm -rf ecommerce-uitests'
        sh 'git clone https://github.com/vishnunc/ecommerce-uitests.git'
       
        sh 'cd ecommerce-uitests && ./gradlew cucumber -Pfeatures=src/test/resources/gradle/cucumber'
        step([$class: 'CucumberReportPublisher', jsonReportDirectory: 'target', fileIncludePattern: 'cucumber.json'])
      }
    }
  }
}