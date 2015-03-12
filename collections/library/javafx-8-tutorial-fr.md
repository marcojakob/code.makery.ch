---
layout: article
title: "JavaFX 8 Tutorial (Français)"
date: 2014-04-19 00:00
updated: 2015-02-18 00:00
slug: javafx-8-tutorial/fr
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-fr.md
description: "Les sept étapes de ce tutorial vous amèneront à découvrir tout à tour, le design, la programmation et enfin le déploiement d'une application de répertoire d'adresses."
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /library/javafx-8-tutorial/fr/
    paging: Intro
    active: true
  - text: "Part 1: Scene Builder"
    link: /library/javafx-8-tutorial/fr/part1/
    paging: 1
  - text: "Part 2: Model and TableView"
    link: /library/javafx-8-tutorial/fr/part2/
    paging: 2
  - text: "Part 3: Interacting with the User"
    link: /library/javafx-8-tutorial/fr/part3/
    paging: 3
  - text: "Part 4: CSS Styling"
    link: /library/javafx-8-tutorial/fr/part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /library/javafx-8-tutorial/fr/part5/
    paging: 5
  - text: "Part 6: Statistics Chart"
    link: /library/javafx-8-tutorial/fr/part6/
    paging: 6
  - text: "Part 7: Deployment"
    link: /library/javafx-8-tutorial/fr/part7/
    paging: 7
- header: Languages
  languages: true
  body:
  - text: English
    link: /library/javafx-8-tutorial/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/
    icon-css: fa fa-fw fa-globe
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/javafx-8-tutorial/fr/
    icon-css: fa fa-fw fa-globe
    active: true
---

<div class="alert alert-warning">
  <i class="fa fa-language"></i> Some parts of this tutorial still need a French translation. If you'd like to help out please read <a href="/library/how-to-contribute/" class="alert-link">how to contribute</a>.
</div>

En 2012, J'ai écrit une [série de tutoriaux JavaFX 2 très détaillés](/java/javafx-2-tutorial-intro/) pour mes étudiants. De nombreuses personnes du monde entier les ont lus et les retours ont été très positifs. J'ai donc décidé de **réécrire le tutoriel JavaFX 2 pour JavaFX 8** (pour savoir ce qui a changé : [Mise à jour vers JavaFX 8 - Quoi de neuf](/blog/update-to-javafx-8-whats-new/)).

Ce tutoriel vous guide pour les phases de design, de programmation et de déploiement d'une application de répertoire d'adresse. Voici à quoi ressemblera l'application :

![Screenshot AddressApp](/assets/library/javafx-8-tutorial/addressapp.png)


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

Maintenant, j'espère que vous allez vous amuser ! Commencez avec la [Partie 1: le Scene Builder](/library/javafx-8-tutorial/fr/part1/).

<div class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> The French translation has been contributed by 
  <ul>
    <li><a href="https://github.com/chakram" class="alert-link">Chakram</a></li> 
  </ul>
  Thank you very much!
</div>
