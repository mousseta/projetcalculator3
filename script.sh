# Assurez-vous que le réseau Docker existe
    docker network ls | grep example.com || docker network create --subnet=172.40.0.0/16 example.com
# Démarrer le conteneur Docker
    docker run -itd --name test --net example.com --ip 172.40.0.25 -p8082:8081 registry.example.com:5000/projetmaven
# Attendre que le service soit prêt
sleep 10
# Tester le service avec l'adresse IP
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

