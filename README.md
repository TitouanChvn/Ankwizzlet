## Coding Week 27

Membres du groupe : Océane AVILA - Julia BERNON - Tanguy BOURA - Titouan CHAUVINEAU

vidéo explicative : https://www.youtube.com/watch?v=7oyWYW579jA&feature=youtu.be
file sender : https://filesender.renater.fr/?s=download&token=d4af9c0e-a04c-4182-ac7d-46af1096d009

<!-- ## How to launch the app : 

In order to launch the app you first have to configure the path to the javafx library using the command "export JAVAFX_HOME=/path/to/javafx-sdk-19/lib/" (you need to write the real path to the library javafx instead of "/path/to")

Then you have to type "make jc" so you can compile all the .java

Finally you have to type "make j" to launch the app -->

# Installation

## Récupération du projet
Pour récupérer le projet :

```sh
# en SSH
git clone git@gitlab.telecomnancy.univ-lorraine.fr:pcd2k23/codingweek-27.git
# ou en HTTPS
git clone https://gitlab.telecomnancy.univ-lorraine.fr/pcd2k23/codingweek-27.git
```

Ouvrir ce répertoire. La commande suivante peut être utilisée :

```bash
cd codingweek-27
code .
```

## Utilisation de Gradle

En utilisant Gradle, il faut lancer la commande `./gradlew run` dans un terminal. 

Problèmes possibles : 
- S'il y a des problèmes d'accès, une solution est de lancer la commande `chmod a+x ./gradlew`
- Gradle ne supportant pas encore Java 19, il faut utiliser une version 17 ou 18 du Java Development Kit (JDK) (utiliser la commande `java --version` ou un `./gradlew --version` pour vérifier votre version actuelle du JDK.)

## Pour le FatJar

Un jar exécutable est déja présent à la racine du projet

Pour les exécuter, il suffit soit :
- de double-cliquer sur l'exécutable depuis l'interface graphique
- de lancer la commande `java -jar ./Ankwizzlet.jar` depuis un terminal

Si vous rencontrez un problème lors de l'exécution il est peut-être possible que le .jar n'ait pas les permissions nécessaires pour être exécuté. Vous pouvez faire un clid droit, propriétés, aller dans l'onglet permission et cocher la case "Autoriser l'exécution du fichier comme un programme".

Si votre problème n'est pas résolu alors vous pouvez supprimer le .jar, ouvrir le terminal et suivre les indication suivantes : 

On se place dans le répertoire, à la racine du projet. Puis, dans un terminal, on lance les commandes suivantes :

```bash
./gradlew clean
./gradlew CreateFatJar # génère le FatJar à la racine du projet
```

A la racine du projet, il y a désormais un dossier `dataAnkwizzlet` ainsi qu'un exécutable `Ankwizzlet.jar`.

**Remarque**

Tant que `dataAnkwizzlet` et `Ankwizzlet.jar` sont dans le même dossier et au même niveau, la sauvegarde des actions effectuées lors de l'utilisation de l'application se fera dans ce même dossier.

# Historique

## Release Day 1 :
    1. Implémentation des vues principales
    2. Implémentation des classes principales
    3. Application exécutable avec les premières pages et possibilité de passer d'une vue à une autre

## Release Day 2 :
    1. Implémentation des contrôleurs pour le choix des decks et des cartes à étudier
    2. Implémentation des contrôleurs pour la création des decks et des cartes
    3. Début fichier .json pour la sauvegarde des données et leur accessibilité
    4. Mise en place des statistiques avec graphiques
    5. Implémentation des paramètres d'apprentissage

## Release Day 3 :
    1. Corrections de bug
    2. Création de decks de test
    3. Début de récupération des données pour le calcul des statistiques
    4. Modes d'apprentissage fonctionnels
    5. Début de mise en forme de l'application

## Release Day 4 - Release Final :
    1. Finalisation des méthodes importer/exporter
    2. Affichage et calcul des statistiques 
    3. Améliorations / corrections sur la sélection des decks à apprendre
    4. Mise en page de l'application
    5. Corrections globales et amélioration de la présentation du code

# Roadmap

La Roadmap correspond à l'organisation de la semaine tel que nous l'avions envisagée le premier jour. Cette Roadmap a évolué, comme on peut le constater dans le suivi des release quotidiens.

Se référer au WBS et au planning pour davantage de détails sur la plannification du projet.

## Day 1 
    1. Conception de l'application : envisager les vues, modèles, contrôleurs / réaliser un premier diagramme de classes
    2. Gestion de projet : création d'un WBS liminaire
    3. Utilisation de Gradle : se familiariser avec l'outil Gradle
    4. Création des modèles principaux
    5. Créations des vues principales
    6. Créations des contrôleurs principaux
    7. Obtenir une première application exécutable qui permet de passer d'une vue à une autre

## Day 2
    1. Implémentation des contrôleurs : choix des decks et des cartes à étudier
    2. Implémentation des contrôleurs : création des decks et des cartes
    3. Possibilité de voir une carte (avec question, réponse, indice, explication)
    4. Interface générale mise en place : il doit y avoir toutes les vues et elles doivent être fonctionnelles
    5. JSON : prise en main de la sauvegarde et le chargement au format .json
    6. Statistiques : affichage des graphiques, méthodes prêtes à être utilisées

## Day 3
    1. JSON : sauvegarder un deck
    2. JSON : importer/exporter un fichier
    3. Création de decks de test
    4. Mise en place des différents modes d'apprentissage
    5. Statistiques : récupération des données
    6. Correction de bugs
    7. L'application doit être jouable

## Day 4
    1. Statistiques : ajout
    2. Tests : ajout de decks de test, mise en place de différents tests
    3. Correction : corrections de bugs, amélioration d'affichage et de l'écriture du code
    4. Ecrire la documentation
    5. FATJar : l'application doit être exécutable

## Day 5 
    1. Vidéo de présentation