---
layout: article
title: "Tutoriel JavaFX 8 - partie 6 : statistiques graphiques"
date: 2014-05-09 00:00
updated: 2015-04-15 00:00
slug: javafx-8-tutorial/fr/part6
canonical: /library/javafx-8-tutorial/part6/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-fr-part6.md
description: "Learn how to create a JavaFX Bar Chart."
image: /assets/library/javafx-8-tutorial/part6/addressapp-part6.png
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
  - text: "Partie 5 : stockage de données en XML"
    link: /library/javafx-8-tutorial/fr/part5/
    paging: 5
  - text: "Partie 6 : statistiques graphiques"
    link: /library/javafx-8-tutorial/fr/part6/
    paging: 6
    active: true
  - text: "Partie 7 : déploiement"
    link: /library/javafx-8-tutorial/fr/part7/
    paging: 7
- header: "Téléchargez les sources"
  body:
  - text: Projet Eclipse relatif à la partie 6 <em>(JDK 8u40 requis au minimum)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-6.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Langues
  collection: library
  item: javafx-8-tutorial
  part: part6
  active: fr
---

![Screenshot AddressApp Part 6](/assets/library/javafx-8-tutorial/part6/addressapp-part6.png)


## Sujet dans la partie 6

* Création d'un **graphique** pour afficher la distribution des anniversaires.


*****

## Statistiques des anniversaires

Toutes les personnes dans AddressApp ont un anniversaire. Ne serait-il pas génial d'avoir quelques statistiques concernant le moment où ces personnes célèbrent leur anniversaire.

Nous allons utiliser un **Bar Chart** (histogramme) contenant une barre pour chaque mois. Chacune des barres montre combien de personnes fêtent leur anniversaire pour ce mois.


## La vue statistiques FXML

1. Nous commençons pas créer un fichier `BirthdayStatistics.fxml` à l'intérieur de notre package `ch.makery.address.view` (*Clique droit sur package | New | other... | New FXML Document*).
![Birthday Statistics FXML](/assets/library/javafx-8-tutorial/part6/birthday-statistics-fxml.png)

2. Ouvrez le fichier `BirthdayStatistics.fxml` dans Scene Builder.

3. Sélectionnez le tout premier `AnchorPane`. Dans la catégorie *Layout* définissez *Pref Width* à 620 et *Pref Height* à 450.

4. Ajoutez un `BarChart` à l'`AnchorPane`.

5. Clique droit sur le `BarChart` et sélectionner *Fit to Parent*.

6. Sauvegardez le fichier fxml et allez sur Eclipse pour rafraîchir le projet. 

Avant de revenir sur Scene Builder nous devons tout d'abord créer le contrôleur et connecter le tout dans `MainApp`.


## Le contrôleur de la vue sur les statistiques

Dans le package view `ch.makery.address.view` créez une classe Java appelée `BirthdayStatisticsController.java`.

Jetons d'abord un coup d'oeil à l'ensemble de la classe contrôleur avant que je commence à expliquer:


##### BirthdayStatisticsController.java

<pre class="prettyprint lang-java">
package ch.makery.address.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import ch.makery.address.model.Person;

/**
 * The controller for the birthday statistics view.
 * 
 * @author Marco Jakob
 */
public class BirthdayStatisticsController {

    @FXML
    private BarChart&lt;String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList&lt;String> monthNames = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
    }

    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    public void setPersonData(List&lt;Person> persons) {
    	// Count the number of people having their birthday in a specific month.
        int[] monthCounter = new int[12];
        for (Person p : persons) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series&lt;String, Integer> series = new XYChart.Series&lt;>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i &lt; monthCounter.length; i++) {
        	series.getData().add(new XYChart.Data&lt;>(monthNames.get(i), monthCounter[i]));
        }
        
        barChart.getData().add(series);
    }
}
</pre>


#### Comment le contrôleur fonctionne

1. Le contrôleur a besoin d'un accès a deux éléments de notre fichier FXML:
  * Le `barChart`: Il possède les types `String` et `Integer`. Le `String` est utilisé pour les mois dans l'axe des abscisses et l'`Integer` pour le nombre de personnes célébrant leur anniversaire pour les mois respectifs.
  * `xAxis`: Nous allons l'utiliser pour ajouter les Strings mois.

2. La méthode `initialize()` remplit l'axe des abscisses avec la liste de tous les mois.

3. La méthode `setPersonData(...)` sera accessible par la classe `MainApp` pour définir les données des personnes. Elle boucle à travers la liste des personnes et compte les anniversaires par mois. Ensuite, elle ajoute `XYChart.Data` pour tous les mois à la série de données. Chaque objet `XYChart.Data` représente une barre du graphique.


*****

## Connexion entre la vue et le contrôleur

1. Ouvrez `BirthdayStatistics.fxml` dans Scene Builder.

2. Dans le groupe *Controller* définissez `BirthdayStatisticsController` comment étant le contrôleur.

3. Sélectionnez le `BarChart` et choisissez `barChart` comme propriété fx:id (dans le groupe *Code*).

4. Sélectionnez `CategoryAxis` et choisissez `xAxis` comme propriété fx:id.
![Category Axis](/assets/library/javafx-8-tutorial/part6/category-axis.png)

5. Vous pouvez ajouter un titre au `BarChart` (dans le groupe *Properties*) pour plus de style.



*****


## Connexion Vue/Contrôleur avec MainApp

Nous allons utiliser le même mécanisme pour la partie *Statistque des anniversaires* que celui utilisé pour la partie *Dialogue d'édition de personne*, une boîte de dialogue popup simple.

Ajoutez la méthode suivante à votre classe `MainApp`:


<pre class="prettyprint lang-java">
/**
 * Opens a dialog to show birthday statistics.
 */
public void showBirthdayStatistics() {
    try {
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Birthday Statistics");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller.
        BirthdayStatisticsController controller = loader.getController();
        controller.setPersonData(personData);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>

Tout est prêt, seulement nous n'avons rien qui appelle la nouvelle méthode `showBirthdayStatistics()`. Heureusement, nous avons déjà un menu dans le fichier `RootLayout.fxml` qui peut être utilisé à cette fin.


### Affichage des statistiques d'anniversaires dans le menu

Dans votre `RootLayoutController` ajouter la méthode suivante qui va gérer le clique d'un utilisateur sur le menu item *Show Statistics*:

<pre class="prettyprint lang-java">
/**
 * Opens the birthday statistics.
 */
@FXML
private void handleShowBirthdayStatistics() {
  mainApp.showBirthdayStatistics();
}
</pre>

Maintenant, ouvrez le fichier `RootLayout.fxml` avec Scene Builder. Créer un menu *Statistics* avec le `MenuItem` *Show Statistics*:

![Show Statistics Menu](/assets/library/javafx-8-tutorial/part6/show-statistics-menu.png)

Sélectionnez le `MenuItem` *Show Statistics* et choisissez `handleShowBirthdayStatistics` dans la propriété `On Action` (Dans le groupe *Code*)

![Show Statistics On Action](/assets/library/javafx-8-tutorial/part6/show-statistics-on-action.png)

Allez sur Eclipse, rafraîchissez le projet et **testez le**.


*****

## Plus d'informations sur les graphiques JavaFX

Un bon endroit pour obtenir plus d'informations est le tutoriel officiel d'Oracle: [Working with JavaFX Charts](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/charts.htm).


### Et après?

Dans le dernier tutoriel [Partie 7](/library/javafx-8-tutorial/fr/part7/) nous allons enfin déployer notre application (c'est à dire empaqueter et délivrer l'application à l'utilisateur).


##### Voici quelques articles qui pourraient aussi vous intéresser :

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

