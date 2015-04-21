---
layout: article
title: "Tutoriel JavaFX 8 - partie 1 : le Scene Builder"
date: 2014-04-19 01:00
updated: 2015-02-18 00:00
slug: javafx-8-tutorial/fr/part1
canonical: /library/javafx-8-tutorial/part1/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-fr-part1.md
description: "Apprenez comment créer un projet JavaFX. Cette page fait partie d'un tutoriel de sept pages relatif à la conception, la programmation et le déploiment d'une application type carnet d'adresses avec JavaFX. "
image: /assets/library/javafx-8-tutorial/part1/addressapp-part1.png
published: true
prettify: true
comments: true
sidebars:
- header: "Les Articles de ce tutoriel"
  body:
  - text: "Introduction"
    link: /library/javafx-8-tutorial/fr/
    paging: Intro
  - text: "Partie 1: Le Scene Builder"
    link: /library/javafx-8-tutorial/fr/part1/
    paging: 1
    active: true
  - text: "Partie 2: Modèle et TableView"
    link: /library/javafx-8-tutorial/fr/part2/
    paging: 2
  - text: "Partie 3: Interaction avec l'utilisateur"
    link: /library/javafx-8-tutorial/fr/part3/
    paging: 3
  - text: "Partie 4: Style CSS"
    link: /library/javafx-8-tutorial/fr/part4/
    paging: 4
  - text: "Partie 5: Stockage de données en XML"
    link: /library/javafx-8-tutorial/fr/part5/
    paging: 5
  - text: "Partie 6: Graphiques Statistiques"
    link: /library/javafx-8-tutorial/fr/part6/
    paging: 6
  - text: "Partie 7: Deploiement"
    link: /library/javafx-8-tutorial/fr/part7/
    paging: 7
- header: "Téléchargez les sources"
  body:
  - text: Projet Eclipse relatif à la partie 1 <em>(JDK 8u40 requis au minimum)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-1.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Langues
  collection: library
  item: javafx-8-tutorial
  part: part1
  active: fr
---

<div class="alert alert-warning">
  <i class="fa fa-language"></i> This page still needs a French translation. If you'd like to help out please read <a href="/library/how-to-contribute/" class="alert-link">how to contribute</a>.
</div>

![Screenshot AddressApp Part 1](/assets/library/javafx-8-tutorial/part1/addressapp-part1.png)

### Sujets dans la partie 1

* Faire connaissance avec JavaFX
* Créer et démarrer un projet JavaFX
* Utiliser le Scene Builder pour concevoir l'interface utilisateur 
* Structurer simplement une application en utilisant le modèle de conception modèle vue contrôleur


*****


### Prérequis

* le dernier [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (qui inclu **JavaFX 8**).
* Eclipse 4.4 ou supérieur avec le plugin e(fx)clipse. Le plus simple est de télécharger la distribution préconfigurée depuis le site [e(fx)clipse](http://efxclipse.bestsolution.at/install.html#all-in-one). Une autre façon de faire consiste à utiliser le [site de mise à jour](http://www.eclipse.org/efxclipse/install.html) pour votre installation d'Eclipse. 
* [Scene Builder 8.0](http://gluonhq.com/products/downloads/) (fourni par Gluon car [Oracle le distribue seulement sous la forme de code source](http://www.oracle.com/technetwork/java/javase/downloads/sb2download-2177776.html)).


### Configuration d'Eclipse 

Nous devons paramètrer Eclipse pour qu'il utilise le JDK 8 et qu'il sache où trouver le le Scene Builder : 

1. Ouvrez les préférences et sélectionnez la partie *Java | Installed JREs*.

2. Cliquez sur le bouton *Add...* pour ajoutez le JDK 8 puis sur *Standard VM* et sélectionnez le *dossier* contenant le JDK 8. 

3. Supprimez les autres JREs et JDKs afin que le **JDK 8 devienne le JDK par défaut (default)** !    
![Preferences JDK](/assets/library/javafx-8-tutorial/part1/preferences-jdk.png)

4. Sélectionnez la partie *Java | Compiler*. Définissez la **Compiler compliance level à 1.8** !    
![Preferences Compliance](/assets/library/javafx-8-tutorial/part1/preferences-compliance.png)

5. Sélectionnez la partie *JavaFX* puis spécifiez le chemin de votre exécutable Scene Builder !   
![Preferences JavaFX](/assets/library/javafx-8-tutorial/part1/preferences-javafx.png)


### Liens utiles

Peut-être voudrez-vous mettre les liens suivants dans vos favoris : 

* [Java 8 API](http://docs.oracle.com/javase/8/docs/api/) - JavaDoc pour les classes Java standard
* [JavaFX 8 API](http://docs.oracle.com/javase/8/javafx/api/) - JavaDoc pour les classes JavaFX
* [ControlsFX API](http://controlsfx.bitbucket.org/) - JavaDoc pour les projets [projets ControlsFX](http://fxexperience.com/controlsfx/) et les contrôles supplémentaires JavaFX
* [Oracle's JavaFX Tutorials](http://docs.oracle.com/javase/8/javafx/get-started-tutorial/get_start_apps.htm) - Tutoriels officiels d'Oracle

Maintenant, nous pouvons commencer !


*****


## Créer un nouveau projet JavaFX

Dans votre IDE Eclipse (avec e(fx)clipse installé), cliquez sur *File | New | Other...* et sélectionnez *JavaFX Project*.   
Spécifiez le nom du projet (par exemple *AddressApp*) et cliquez sur *Finish* ! 

Supprimez le package *application* et son contenu s'il a été automatiquement créé ! 


### Créer le packages

Dès maintenant nous allons suivre les bonnes pratiques de programmation. Un principe très important est le [**Model-View-Controller** (MVC)](http://en.wikipedia.org/wiki/Model_View_Controller). En accord avec ceci, nous divisons notre code en trois unités. Nous allons créer un package pour chaques parties : (clic droit sur le dossier src, *New... | Package*):

* `ch.makery.address` - contient *la plupart* des classes contrôleur ( = la logique métier)
* `ch.makery.address.model` - contient les classe modèle 
* `ch.makery.address.view` - contient l'interface graphique 

**Note:** Notre package view contiendra aussi quelques contrôleurs dépendant directement d'une interface graphique unique. Appelons-les **view-controllers** ! 


*****


## Créer le fichier de disposition FXML

Il y a deux manières de créer une interface utilisateur. L'une consiste à utiliser un fichier XML et l'autre est de tout programmer en Java. En cherchant sur le web, vous trouverez les deux. Nous utiliserons ici le fichier XML (extension .fxml) pour presque toutes les interfaces. Je trouve cette façon de faire plus propre car cela permet de mieux séparer les contrôleurs des interfaces. De plus, nous pouvons utiliser l'outil graphique Scene Builder pour modifier le XML. Cela signifie que nous n'aurons pas à travailler directement en XML.

Clic droit sur le package view et new *FXML Document* ! Nommez-le `PersonOverview` !    

![New FXML Document](/assets/library/javafx-8-tutorial/part1/new-fxml-document.png)

![New PersonOverview](/assets/library/javafx-8-tutorial/part1/new-person-overview.png)



*****


## Conception de la GUI avec Scene Builder

<div class="alert alert-warning">
  **Note :** si vous ne pouvez pas faire fonctionner Scene Builder, téléchargez la source de cette partie du tutoriel et essayez avec le fichier .fxml inclu ! 
</div>

Faites un clic droit sur `PersonOverview.fxml` et puis cliquez sur *Open with Scene Builder*. Maintenant vous devriez voir s'afficher le programme Scene Builder avec un *AnchorPane* (visible dans la partie hiérarchie sur la gauche). 

(Si Scene Builder ne démarre pas, cliquez sur *Window | Preferences | JavaFX* et définissez le chemin correct relatif à l'installation de votre Scene Builder). 

1. Dans la partie Hierarchy, sélectionnez l'*Anchor Pane* et ajustez la taille sous Layout (dans la partie de droite):   
![Anchor Pane Size](/assets/library/javafx-8-tutorial/part1/anchor-pane-size.png)

2. Ajoutez un *Split Pane (Horizontal Flow)* en utilisant un drag et drop depuis la librairie sur gauche jusque dans la partie centrale ! Faites un clic droit dans la partie *Hierarchy* à gauche sur *Split Pane* et sélectionnez *Fit to Parent* (ajuster au contrôle parent) !    
![Fit to Parent](/assets/library/javafx-8-tutorial/part1/fit-to-parent.png)

3. Faites glisser un *TableView* (depuis la liste *Controls* sur la gauche) et amenez-le dans la partie de gauche du *SplitPane*. Sélectionnez la TableView (pas la colonne mais le tableau) et définissez la contrainte du TableView comme suit. Dans un *AnchorPane*, vous pouvez toujours définir les ancres sur les quatres bords ([plus d'information dans la partie relative aux Layouts](http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm)).   
![TableView Anchors](/assets/library/javafx-8-tutorial/part1/table-view-anchors.png)

4. Allez dans le menu *Preview | Show Preview in Window* pour voir si la fenêtre s'affiche comme vous le voulez. Essayez de la rediensionner ! Le TableView devrait être redimensionné en même temps que la fenêtre parce qu'il est ancré aux bordures. 

5. Modifiez l'entête des colonnes (dans les propriétés) ! Nommez-les "First Name" et "Last Name" !  
![Column Texts](/assets/library/javafx-8-tutorial/part1/column-texts.png)

6. Sélectionnez le *TableView* et définissez *constrained-resize* sous *Column Resize Policy* (dans les propriétés). Ceci spécifie que la colonne prendra tout l'espace disponible.    
![Column Resize Policy](/assets/library/javafx-8-tutorial/part1/column-resize-policy.png)

7. Ajoutez un *Label* sur le côté droit dont le texte sera "Person Details" (suggestion : utilisez la recherche pour retrouver le *Label*). Ajustez le layout en utilisant les ancres. 
![Person Details Label](/assets/library/javafx-8-tutorial/part1/person-details-label.png)

8. Ajoutez un *GridPane* sur le côté droit, sélectionnez-le puis ajuster son layout en utilisant les ancres (top, right et left) !     
![GridPane Layout](/assets/library/javafx-8-tutorial/part1/grid-pane-layout.png)

9. Ajoutez le étiquettes suivantes dans les cellules !   
*Note : pour ajouter une ligne dans le GridPane, sélectionnez l'index d'un ligne existante (elle deviendra jaune) puis faites un clic droit sur l'index et sélectionnez "Add Row".*   
![Add labels](/assets/library/javafx-8-tutorial/part1/add-labels.png)

10. Ajoutez une *ButtonBar* en bas ! Ajoutez trois boutons à la barre ! Enfin, définissez les ancres pour qu'ils restent à leur place soit en bas et à droite.   
![Button Group](/assets/library/javafx-8-tutorial/part1/button-group.png)

11. Maintenant, vous devriez voir quelque chose ressemblant à l'image ci-dessous. Utilisez le menu *Preview* pour tester le comportement après redimensionnement.  
![Preview](/assets/library/javafx-8-tutorial/part1/scene-builder-preview.png)



*****


## Créer l'application principale

Vous avez besoin d'un autre fichier FXML pour la disposition de base. Celle-ci contiendra une barre de menu et encapsulera le `PersonOverview.fxml` que nous venons de créer. 

1. Créez un autre *FXML Document* dans le package view appelé `RootLayout.fxml`. Cette fois, choisissez *BorderPane* comme élément racine.   
![New RootLayout](/assets/library/javafx-8-tutorial/part1/new-root-layout.png)

2. Ouvrez le fichier `RootLayout.fxml` dans l'application Scene Builder ! 

3. Redimensionnez le *BorderPane* de sorte que la largeur préférée (*Pref Width*) soit à 600 et la hauteur préférée (*Pref Height*) à 400.   
![RootLayout Size](/assets/library/javafx-8-tutorial/part1/root-layout-size.png)

4. Ajoutez une *MenuBar* dans le slot supérieure (TOP). Nous n'implémenterons pas les fonctionnalités du menu pour l'instant.    
![MenuBar](/assets/library/javafx-8-tutorial/part1/menu-bar.png)


### La classe principale de JavaFX 

Maintenant vous devez créer la classe principale (**main java class**) qui démarrera notre application avec le fichier `RootLayout.fxml` et ajoutera la `PersonOverview.fxml` au centre. 

1. Faites un clic droit dans votre projet puis cliquez sur *New | Other...* et enfin sur *JavaFX Main Class*.   
![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class.png)

2. Nous nommerons la classe `MainApp` et nous la placerons dans le package contrôleur `ch.makery.address` (note : c'est le package parent des sous-paquets `view` et `model`).   
![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class2.png)


La classe `MainApp.java` générée hérite de la classe `Application` et contient deux méthodes. C'est la structure basique dont nous avons besoin pour démarrer une application JavaFX. La partie la plus importante pour nous est la méthode `start(Stage primaryStage)`. Cette méthode est automatiquement appelée lors du lancement de l'application (`launch`) avec la méthode `main`.

Comme vous le voyez, la méthode `start(...)` reçoit un objet `Stage` aen paramètre. Le diagramme suivant montre la structure de toutes les applications JavaFX : 

![New FXML Document](/assets/library/javafx-8-tutorial/part1/javafx-hierarchy.png)   
*Image provenant de : http://www.oracle.com*

**C'est comme une pièce de théâtre** : l'objet `Stage` est le conteneur principal qui est habituellement une `fenêtre` avec des bordures et qui a les boutons habituels pour minimiser, maximiser et fermer. Dans le `Stage`, vous ajoutez une `Scene` qui peut, bien sûr, être échangée avec un autre object `Scene`. Sont ajoutés dans la `Scene` les noeuds JavaFX actuels comme `AnchorPane`, `TextBox`, etc. 

Pour plus d'informations à ce sujet, voir [Working with the JavaFX Scene Graph](http://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm).


*****

Ouvrez `MainApp.java` et remplacez le code par celui-ci : 

<pre class="prettyprint lang-java">
package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}
</pre>

Les commentaires épars devraient vous donner quelques indices concernant ce qu'il se passe. 

Si vous exécutez l'application maintenant, vous devriez voir quelque chose ressemblant à la capture d'écran figurant au début de ce post. 

### Problèmes fréquents 

Lorsque JavaFX ne peut pas trouver le fichier `fxml` que vous avez spécifié, vous devriez voir le message d'erreur suivant : 

`java.lang.IllegalStateException: Location is not set.`

Pour résoudre ce problème, vérifiez une seconde fois le chemin du fichier `fxml` ! 

<div class="alert alert-warning">
  Si cela ne fonctionne toujours pas, téléchargez la source de cette partie du tutoriel et essayez avec le fichier fxml inclu ! 
</div>


*****

### Qu'est-ce qui suit ? 

Dans la  [partie 2 du tutoriel](/library/javafx-8-tutorial/fr/part2/), vous allez ajouter des données et des fonctionnalités à notre AddressApp.


##### Voici quelques autres articles qui pourraient vous intéresser : 

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
