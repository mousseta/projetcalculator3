pipeline {
    agent any
//test
    stages {
        stage("Checkout") {
            steps {
                git branch: 'main',
                    credentialsId: 'jenkinsgithubssh',
                    url: 'git@github.com:mousseta/projetcalculator3.git'
            }
        }
        stage("Compile") {
            steps {
                sh './mvnw compile'
            }
        }
        stage("Test") {
            steps {
                sh './mvnw test'
            }
        }
        stage("Package") {
            steps {
                sh './mvnw package'
            }
        }
        stage("Docker Build") {
            steps {
                sh 'docker build -t registry.example.com:5000/projetmaven .'
            }
        }
        stage("Docker Push") {
            steps {
                sh 'docker push registry.example.com:5000/projetmaven'
            }
        }
        stage("Run and Test") {
            steps {
               sh 'chmod +x script.sh'
               sh './script.sh'
            }
        }
       stage('SonarQube analysis') {
          steps {
             withSonarQubeEnv('sonarqube') {
             sh "./gradlew sonarqube"
            }
          }
      }
      stage("Quality gate") {
         steps {
            waitForQualityGate abortPipeline: true
         }
     }
    }
    post {
        always {
            // Arrêter et supprimer le conteneur
            sh 'docker stop test || true'
            sh 'docker rm test || true'
        }
    }
}
