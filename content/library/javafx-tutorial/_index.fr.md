---
layout: article
title: "Tutoriel JavaFX 8 (Français)"
date: 2014-04-19
updated: 2015-04-15
slug: javafx-tutorial/fr
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-fr.md
description: "Les sept étapes de ce tutorial vous amèneront à découvrir tout à tour, le design, la programmation et enfin le déploiement d'une application de répertoire d'adresses."
image: /assets/library/javafx-tutorial/addressapp.png
published: true
prettify: true
comments: true
sidebars:
- header: "Les articles dans ce tutoriel"
  body:
  - text: "Introduction"
    link: /library/javafx-tutorial/fr/
    paging: Intro
    active: true
  - text: "Partie 1 : le Scene Builder"
    link: /library/javafx-tutorial/fr/part1/
    paging: 1
  - text: "Partie 2 : modèle et TableView"
    link: /library/javafx-tutorial/fr/part2/
    paging: 2
  - text: "Partie 3 : interaction avec l'utilisateur"
    link: /library/javafx-tutorial/fr/part3/
    paging: 3
  - text: "Partie 4 : style CSS"
    link: /library/javafx-tutorial/fr/part4/
    paging: 4
  - text: "Partie 5 : stockage de données en XML"
    link: /library/javafx-tutorial/fr/part5/
    paging: 5
  - text: "Partie 6 : statistiques graphiques"
    link: /library/javafx-tutorial/fr/part6/
    paging: 6
  - text: "Partie 7 : déploiement"
    link: /library/javafx-tutorial/fr/part7/
    paging: 7
languages: 
  header: Langues
  collection: library
  item: javafx-tutorial
  part:
  active: fr
---

En 2012, J'ai écrit une [série de tutoriaux JavaFX 2 très détaillés](/library/javafx-2-tutorial/) pour mes étudiants. De nombreuses personnes du monde entier les ont lus et les retours ont été très positifs. J'ai donc décidé de **réécrire le tutoriel JavaFX 2 pour JavaFX 8** (pour savoir ce qui a changé : [Mise à jour vers JavaFX 8 - Quoi de neuf](/blog/update-to-javafx-8-whats-new/)).

Ce tutoriel vous guide pour les phases de design, de programmation et de déploiement d'une application de répertoire d'adresse. Voici à quoi ressemblera l'application :

![Screenshot AddressApp](/assets/library/javafx-tutorial/addressapp.png)


## Ce que vous allez apprendre

* Créer et lancer un projet JavaFX
* Utiliser le Scene Builder pour dessiner l'interface utilisateur
* Structurer une application suivant le schéma Modèle Vue Contrôleur (MVC)
* Utiliser des `ObservableLists` pour mettre à jour automatiquement l'interface utilisateur
* Utiliser une `TableView` et réagir au changements de sélection dans la table
* Créer une fenêtre de dialogue popup pour éditer les données de personnes
* Valider les entrées utilisateur
* Personnaliser votre application JavaFX avec CSS
* Gérer des données persistantes en XML
* Sauvegarder le dernier répertoire ouvert dans les préférences utilisateurs
* Créer un graphique JavaFX pour afficher des statistiques
* Deployer une application JavaFX via un package natif

**C'est déjà beaucoup!** Donc, après être allé au bout de cette série de tutoriaux, vous devriez être prêt à coder des applications sophistiquées avec JavaFX.


## Comment utiliser ce tutorial ?

Il y a deux façons de l'utiliser :

* **la version apprendre-un-max:** Créez votre propre projet JavaFX en partant de zéro.
* **la version rapide:** Importez le code source dans votre IDE (c'est un projet Eclipse, mais vous devriez pouvoir utiliser d'autres IDEs comme NetBeans avec quelques petites modifications). Ensuite suivez le tutorial pour comprendre le code.

Maintenant, j'espère que vous allez vous amuser ! Commencez avec la [Partie 1: le Scene Builder](/library/javafx-tutorial/fr/part1/).

<div class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> The French translation has been contributed by 
  <ul>
    <li><a href="https://github.com/chakram" class="alert-link">Chakram</a></li> 
    <li><a href="https://github.com/Davidmue" class="alert-link">David Müller</a></li> 
    <li><a href="https://github.com/mdejeans" class="alert-link">Mathieu Dejean-Servieres</a></li> 
    <li><a href="https://github.com/Kynarth" class="alert-link">Nicolas Belouin</a></li> 
  </ul>
  Thank you very much!
</div>
