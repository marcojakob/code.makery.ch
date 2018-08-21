---
layout: article
title: "Tutoriel JavaFX 8 - partie 4 : style CSS"
date: 2014-04-25
updated: 2015-04-15
slug: javafx-8-tutorial/fr/part4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-fr-part4.md
description: "Avec JavaFX vous pouvez appliquer un style CSS à votre interface utilisateur. Nous allons aussi ajouter une icône à l'application dans cette partie du tutoriel."
image: /assets/library/javafx-8-tutorial/part4/addressapp-part4.png
published: true
prettify: true
comments: true
sidebars:
- header: "Les articles dans ce tutoriel"
  body:
  - text: "Introduction"
    link: /library/javafx-8-tutorial/fr/
    paging: Intro
  - text: "Partie 1 : le Scene Builder"
    link: /library/javafx-8-tutorial/fr/part1/
    paging: 1
  - text: "Partie 2 : modèle et TableView"
    link: /library/javafx-8-tutorial/fr/part2/
    paging: 2
  - text: "Partie 3 : interaction avec l'utilisateur"
    link: /library/javafx-8-tutorial/fr/part3/
    paging: 3
  - text: "Partie 4 : style CSS"
    link: /library/javafx-8-tutorial/fr/part4/
    paging: 4
    active: true
  - text: "Partie 5 : stockage de données en XML"
    link: /library/javafx-8-tutorial/fr/part5/
    paging: 5
  - text: "Partie 6 : statistiques graphiques"
    link: /library/javafx-8-tutorial/fr/part6/
    paging: 6
  - text: "Partie 7 : déploiement"
    link: /library/javafx-8-tutorial/fr/part7/
    paging: 7
- header: "Téléchargez les sources"
  body:
  - text: Projet Eclipse relatif à la partie 4 <em>(JDK 8u40 requis au minimum)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-4.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Langues
  collection: library
  item: javafx-8-tutorial
  part: part4
  active: fr
---

![Screenshot AddressApp Part 4](/assets/library/javafx-8-tutorial/part4/addressapp-part4.png)


## Sujets dans la parties 4

* Appliquer un **style CSS**
* Ajouter une **icône à l'application**



*****


## Appliquer un style CSS 

Avec JavaFX vous pouvez appliquer un style en utilisant les Cascading Style Sheets (CSS). C'est grandiose ! Cela n'a jamais été aussi simple de personnaliser l'apparence d'une application Java. 

Dans ce tutoriel, nous allons créer un *DarkTheme* (thème sombre) inspiré par le design de Windows 8 Metro. Le style CSS des boutons est basé sur le post du blog [JMetro - Windows 8 Metro controls on Java](http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/) de Pedro Duque Vieira.


### Faire connaissance avec CSS

Si vous voulez styliser votre application JavaFX vous devriez avoir une compréhension basique de CSS en général. Il y a un bon [tutoriel CSS](http://www.csstutorial.net/) ici pour débuter. 

Pour plus d'informations plus spécifiques au sujet de CSS: 

* [Changer l'apparence des applications JavaFX avec CSS](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/css_tutorial.htm) - Tutoriel d'Oracle
* [Références CSS pour JavaFX](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html) - Réference officielle


### Style CSS par défaut de JavaFX 

La source du style CSS appliqué par défaut dans JavaFX 8 est appellée **`modena.css`**. Ce fichier CSS peut être trouvé dans le fichier `jfxrt.jar`situé dans le dossier Java sous `/jdk1.8.x/jre/lib/ext/jfxrt.jar`. 

Dézippez le `jfxrt.jar` ! vous devriez trouver le fichier `modena.css` dans `com/sun/javafx/scene/control/skin/modena/`

Cette feuille de style est toujours appliquée par défaut à l'application JavaFX. En ajoutant une feuille de style personnalisée nous pouvons surcharger les styles `modena.css` appliqués par défaut. 

<div class="alert alert-info">
**Astuce:** Consulter le fichier des styles par défaut vous aide à identifier les styles que vous devriez peut-être surcharger. 
</div>


### Appliquer des feuilles de style CSS

Ajoutez la feuille de style CSS suivante nommée `DarkTheme.css` dans le package *view* !


##### DarkTheme.css

<pre class="prettyprint lang-css pre-scrollable">
.background {
    -fx-background-color: #1d1d1d;
}

.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 0.6;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.table-view {
    -fx-base: #1d1d1d;
    -fx-control-inner-background: #1d1d1d;
    -fx-background-color: #1d1d1d;
    -fx-table-cell-border-color: transparent;
    -fx-table-header-border-color: transparent;
    -fx-padding: 5;
}

.table-view .column-header-background {
    -fx-background-color: transparent;
}

.table-view .column-header, .table-view .filler {
    -fx-size: 35;
    -fx-border-width: 0 0 1 0;
    -fx-background-color: transparent;
    -fx-border-color: 
        transparent
        transparent
        derive(-fx-base, 80%) 
        transparent;
    -fx-border-insets: 0 10 1 0;
}

.table-view .column-header .label {
    -fx-font-size: 20pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-alignment: center-left;
    -fx-opacity: 1;
}

.table-view:focused .table-row-cell:filled:focused:selected {
    -fx-background-color: -fx-focus-color;
}

.split-pane:horizontal > .split-pane-divider {
    -fx-border-color: transparent #1d1d1d transparent #1d1d1d;
    -fx-background-color: transparent, derive(#1d1d1d,20%);
}

.split-pane {
    -fx-padding: 1 0 0 0;
}

.menu-bar {
    -fx-background-color: derive(#1d1d1d,20%);
}

.context-menu {
    -fx-background-color: derive(#1d1d1d,50%);
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 0.9;
}

.menu .left-container {
	-fx-background-color: black;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}

/* 
 * Metro style Push Button
 * Author: Pedro Duque Vieira
 * http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/
 */
.button {
    -fx-padding: 5 22 5 22;   
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #1d1d1d;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
  -fx-background-color: white;
  -fx-text-fill: #1d1d1d;
}

.button:focused {
    -fx-border-color: white, white;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #1d1d1d;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color,30%);
}
</pre>

Nous devons maintenant appliquer le CSS à notre Scene. Nous aurions pu le faire en programmant du code Java mais nous allons utiliser le Scene Builder pour l'ajouter à nos fichiers *.fxml : 


#### Appliquer le CSS à RootLayout.fxml

1. Ouvrez le fichier `RootLayout.fxml` avec Scene Builder ! 

2. Sélectionnez la racine `BorderPane` dans l'affichage Hierarchy ! Puis ajoutez le fichier `DarkTheme.css` dans le groupe *Properties* sous Stylesheets (feuilles de styles) !    
![DarkTheme for RootLayout](/assets/library/javafx-8-tutorial/part4/darktheme-rootlayout.png)


#### Appliquer le CSS à PersonEditDialog.fxml

1. Ouvrez le fichier `PersonEditDialog.fxml` avec Scene Builder ! Sélectionnez la racine `AnchorPane` et définissez `DarkTheme.css` dans le groupe *Properties* en tant que feuille de style. 

2. Le fond est encore blanc, ajoutez encore la classe de style `background` à la racine `AnchorPane` !    
![Add Style Class](/assets/library/javafx-8-tutorial/part4/darktheme-personeditdialog.png)

3. Sélectionnez le bouton OK et définissez *Default Button* dans la partie des Properties ! Ceci modifiera sa couleur et le définira en tant que bouton par défaut (le bouton actionné lorsque l'utilisateur clique sur *ENTRÉE*). 


#### Appliquer le CSS à PersonOverview.fxml

1. Ouvrez le fichier `PersonOverview.fxml` avec Scene Builder ! Sélectionnez la racine `AnchorPane` dans le groupe *Hierarchy*. Dans les propriétés, ajoutez la feuille de style `DarkTheme.css` ! 

2. Vous devriez déjà voir les changements à présent : Le tableau et les boutons sont noirs. Toutes les classes de styles `.table-view` et `.button` de `modena.css` s'appliquent au tableau et aux boutons. Depuis que nous avons défini (et ainsi surchargé) quelques-uns des styles dans notre CSS, les nouveaux styles sont appliqués automatiquement. 

3. Vous pourriez avoir besoin d'ajuster la taille des boutons afin d'afficher le texte en entier. 

4. Sélectionnez l'`AnchorPane` sur la droite, celui qui est dans le `SplitPane` !    
![Background Style Select](/assets/library/javafx-8-tutorial/part4/background-style-select.png)   

5. Regardez ce qu'il y a dans le groupe *Properties*. Sélectionnez `background` comme classe de style. Le fond devrait maintenant devenir noir. 
![Background Style](/assets/library/javafx-8-tutorial/part4/background-style.png)


#### Étiquettes avec un style différent 

À cette étape, tous les labels sur le côté droit ont la même taille. Il y a déjà quelques styles définis dans le fichier css nommés `.label-header` et `.label-bright` que nous utiliserons plus tard pour styliser les étiquettes. 

1. Sélectionnez l'étiquette *Person Details* et ajoutez `label-header` comme classe de style.   
![Label Header Style](/assets/library/javafx-8-tutorial/part4/label-header-style.png)

2. Pour chaque label dans la colonne de droite (où sont affichés les détails de la personnes actuelle), ajoutez la classe de style `label-bright` !    
![Label Bright Style](/assets/library/javafx-8-tutorial/part4/label-bright-style.png)


*****


## Ajouter une icône à l'application 

Pour le moment notre application a seulement l'icône par défaut dans la barre de titre et la barre des tâches : 

![Default Icon](/assets/library/javafx-8-tutorial/part4/default-app-icon.png)

C'est bien plus beau avec une icône personnalisée : 

![Custom Icon](/assets/library/javafx-8-tutorial/part4/custom-app-icon.png)


### Le fichier icône

Un endroit parmi d'autres pour trouver des icônes libre de droit est [Icon Finder](http://www.iconfinder.com). J'ai téléchargé une petite [icône de carnet d'adresses](https://www.iconfinder.com/icons/86957/address_book_icon#size=32).

Créez un dossier (normal) dans votre projet AddressApp nommé **resources** et un sous-dossier appelé **images** dans celui-ci ! Insérez l'icône de votre choix dans le dossier images. La structure de dossiers devrait ressembler à cela :

![Custom Icon File](/assets/library/javafx-8-tutorial/part4/custom-icon-file.png)


### Définir l'icône pour la Scene

Pour définir l'icône de notre scène, ajoutez la ligne suivante à la méthode `start(...)` dans `MainApp.java` ! 


##### MainApp.java

<pre class="prettyprint lang-java">
this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
</pre>

La méthode `start(...)` en entier devrait maintenant ressembler à ça :

<pre class="prettyprint lang-java">
public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("AddressApp");

    // Set the application icon.
    this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

    initRootLayout();

    showPersonOverview();
}
</pre>

Vous pouvez aussi ajouter une icône au stage de la boîte de dialogue person edit bien sûr. 


### Qu'y a-t-il ensuite ?

Dans la [partie 5 du tutorial](/library/javafx-8-tutorial/fr/part5/) nous allons ajouter l'enregistrement de nos données dans un fichier XML. 


##### Voici quelques autres articles que vous pourriez trouver intéressant : 

* [JavaFX - Boîtes de dialogue (officiel)](/blog/javafx-dialogs-official/)
* [JavaFX - Contrôle Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX - Exemples de gestion d'événements](/blog/javafx-8-event-handling-examples/)
* [JavaFX - Trier et filtrer des TableView](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX - TableView Cell Renderer (rendu des cellules)](/blog/javafx-8-tableview-cell-renderer/)

