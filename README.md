# Projet de Communication Distribuée en Java

Ce projet consiste à développer des serveurs et des clients en utilisant trois technologies de communication distribuée en Java : Java RMI, gRPC et les sockets. L'objectif est de comprendre le fonctionnement et les différences entre ces technologies en mettant en œuvre des fonctionnalités spécifiques dans chaque système.

## Java RMI

### Déploiement

1. Compilez les fichiers Java :
    javac RMI/*.java
    
2. Lancez le registre RMI :
    rmiregistry &
    
3. Lancez le serveur RMI :
    java RMI.Server
    
4. Exécutez le client RMI :
    java RMI.Client
  

### Tests

Une fois que le client est lancé, vous pouvez ajouter, supprimer et récupérer des tâches à partir du service de liste de tâches.

## gRPC

### Déploiement

1. Naviguez vers le répertoire gRPC :
    cd GrpcProject
    
2. Compilez et exécutez le serveur gRPC :
    mvn compile
    mvn exec:java -Dexec.mainClass="ChatServer"
  
3. Exécutez le client gRPC :
    mvn exec:java -Dexec.mainClass="ChatClient"

## Sockets

### Déploiement

1. Naviguez vers le répertoire Sockets :
    cd Sockets
   
2. Compilez et exécutez le serveur de chat :
    javac ChatServer.java
    java ChatServer
    
3. Pour chaque client, ouvrez une nouvelle fenêtre de terminal et exécutez le client de chat :
    javac ChatClient.java
    java ChatClient

### Tests

Une fois connecté, vous pouvez envoyer des messages qui seront diffusés à tous les autres clients connectés au serveur.




### Tests
