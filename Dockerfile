# Utilisation de l'image de base d'OpenJDK 17
FROM openjdk:17-jdk-slim

# Création du répertoire d'application
WORKDIR /usr/src/app

# Copie du jar Spring Boot dans le répertoire d'application
COPY target/shop-back.jar shop-back.jar

# Commande pour lancer le jar Spring Boot
CMD ["java","-jar","shop-back.jar"]

EXPOSE 8090
