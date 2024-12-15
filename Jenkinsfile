pipeline {
    agent any
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
                script {
                    // Assurez-vous que le réseau Docker existe
                    sh '''
                    docker network ls | grep example.com || docker network create --subnet=172.40.0.0/16 example.com
                    '''

                    // Démarrer le conteneur Docker
                    sh 'docker run -itd --name test --net example.com --ip 172.40.0.25 -p8082:8081 registry.example.com:5000/projetmaven'

                    // Attendre que le service soit prêt
                    sh 'sleep 10'

                    // Tester le service avec l'adresse IP
                    sh '''
                        response=$(curl -s "http://172.40.0.25:8081/sum?a=5&b=6")
                        echo "Réponse obtenue : $response"

                        if [ -z "$response" ]; then
                            echo "Erreur : le service n'a pas répondu ou la réponse est vide"
                            exit 1
                        fi

                        if [ "$response" -eq 11 ]; then
                            echo "Test réussi : réponse attendue 11, obtenue $response"
                        else
                            echo "Test échoué : réponse attendue 11, obtenue $response"
                            exit 1
                        fi
                    '''
                }
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
