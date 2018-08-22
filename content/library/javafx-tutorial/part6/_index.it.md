---
layout: article
title: "JavaFX 8 Tutorial - Parte 6: Grafico delle Statistiche"
date: 2014-05-09
updated: 2015-03-12
slug: javafx-tutorial/it/part6
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-it-part6.md
description: "Learn how to create a JavaFX Bar Chart."
image: /assets/library/javafx-tutorial/part6/addressapp-part6.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articoli in questa serie"
  body:
  - text: "Introduzione"
    link: /library/javafx-tutorial/it/
    paging: Intro
  - text: "Parte 1: Scene Builder"
    link: /library/javafx-tutorial/it/part1/
    paging: 1
  - text: "Parte 2: Model and TableView"
    link: /library/javafx-tutorial/it/part2/
    paging: 2
  - text: "Parte 3: Interazione con l'utente"
    link: /library/javafx-tutorial/it/part3/
    paging: 3
  - text: "Parte 4: CSS Styling"
    link: /library/javafx-tutorial/it/part4/
    paging: 4
  - text: "Parte 5: Conservazione dati come XML"
    link: /library/javafx-tutorial/it/part5/
    paging: 5
  - text: "Parte 6: Grafico delle statistiche"
    link: /library/javafx-tutorial/it/part6/
    paging: 6
    active: true
  - text: "Parte 7: Deployment"
    link: /library/javafx-tutorial/it/part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: Part 6 as Eclipse Project <em>(requires at least JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-6.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Languages
  collection: library
  item: javafx-tutorial
  part: part6
  active: it
---


![Screenshot AddressApp Part 6](/assets/library/javafx-tutorial/part6/addressapp-part6.png)


## Punti nella Parte 6

* Creare un **Grafico Statistiche** per visualizzare la distribuzione dei compleanni.


*****

## Statistiche di Compleanno

Tutte le persone nell'applicazione hanno una data di nascita. Sarebbe carino avere qualche statistica riguardo a quando le persone celebrano il compleanno.

Useremo un **Bar Chart** contenente una barra per ogni mese. Ogni barra mostra quante persone hanno il loro compleanno in un determinato mese.


## La Vista FXML Statistiche

1. Iniziamo creando il file `BirthdayStatistics.fxml` all'interno del package `ch.makery.address.view` (*Click destro su package | New | other... | New FXML Document*).   
![Birthday Statistics FXML](/assets/library/javafx-tutorial/part6/birthday-statistics-fxml.png)

2. Apriamo il file `BirthdayStatistics.fxml` con Scene Builder.

3. Selezioniamo la radice `AnchorPane`. Nel gruppo *Layout* settare *Pref Width* a 620 e *Pref Height* a 450.

4. Aggiungiamo `BarChart` all'`AnchorPane`.

5. Click destro su `BarChart` e selezionare *Fit to Parent*.

6. Salva il file fxml, vai su Eclipse e aggiorna il progetto.

Prima di tornare indietro a Scene Builder creeremo prima il controller e connetteremo tutto nella nostra`MainApp`.


## Il Controller Statistiche

Nel package vista `ch.makery.address.view` creare la classe java chiamata `BirthdayStatisticsController.java`.

Diamo prima un'occhiata all'intera classe controller prima di iniziare le spiegazioni:


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


#### Come funziona il controller

1. Il controller avrà bisogno dell'accesso a due elementi dal nostro file FXML:
   * Il `barChar`: Di tipo `String` e `Integer`. `String` è usato per il mese sull'asse X mentre `Integer` è usato per il numero di persone in uno specifico mese. 
   * Il `xAxis`: useremo questo per aggiungere le Strings mese.   

2. Il metodo `initialize()` riempie l'asse X con una lista di tutti i mesi.

3. Il metodo `setPersonData(...)` verrà utilizzato dalla classe `MainApp` per settare i dati personali. Cicla attraverso tutte le persone e conta i compleanni per mese. Quindi aggiunge `XYChart.Data` per ogni mese alla serie di dati. Ogni oggetto `XYChart.Data` rappresenta una barra nel grafico.


*****

## Connettere la Vista al Controller

1. Apri `BirthdayStatistics.fxml` in Scene Builder.

2. Nel gruppo *Controller* setta `BirthdayStatisticsController` come controller.

3. Seleziona `BarChart` e scegli `barChart` come fx:id Property (nel gruppo *Code*).

4. Seleziona `CategoryAxis` e scegli `xAxis` come fx:id Property.   
![Category Axis](/assets/library/javafx-tutorial/part6/category-axis.png)

5. Puoi aggiungere un titolo a `BarChart` (nel gruppo *Properties*) per personalizzare lo stile.



*****


## Connettere la Vista/Controller con MainApp

Useremo lo stesso meccanismo per la nostra *birthday statistics* che abbiamo usato per *edit person dialog*, un semplice dialog popup.

Aggiungi il seguente metodo alla classe `MainApp`:


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

Ok, abbiamo settato tutto, ma non abbiamo ancora nulla che chiami il nuovo metodo `showBirthdayStatistics()`. Fortunatamente abbiamo già un menù in `RootLayout.fxml` che può essere usato per questo scopo.


### Mostra il Menù Statistiche di Compleanno

Nel tuo `RootLayoutController` aggiungi il seguente metodo che si occuperà dei click dell'utente nell'oggetto di menù *show birthday statistics*: 

<pre class="prettyprint lang-java">
/**
 * Opens the birthday statistics.
 */
@FXML
private void handleShowBirthdayStatistics() {
  mainApp.showBirthdayStatistics();
}
</pre>

Ora, apri il file `RootLayout.fxml` con Scene Builder. Crea il `Menu` *Statistics* con `MenuItem` *Show Statistics*:

![Show Statistics Menu](/assets/library/javafx-tutorial/part6/show-statistics-menu.png)

Seleziona *Show Statistics* `MenuItem` e scegli `handleShowBirthdayStatistics` per `On Action` (nel gruppo *Code*)   

![Show Statistics On Action](/assets/library/javafx-tutorial/part6/show-statistics-on-action.png)

Vai a Eclipse, aggiorna il progetto e **testalo**.


*****

## Altre info su JavaFX Charts

Un buon posto per ulteriori informazioni è il tutorial ufficiale della Oracle a [Working with JavaFX Charts](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/charts.htm).


### Cosa viene dopo?

Nell'ultimo tutorial [Parte 7](/library/javafx-tutorial/it/part7/) finalmente ci occuperemo della distribuzione (i.e. pacchettizzare e distribuire la nostra app agli utenti).


##### Qualche altro articolo che potresti trovare interessante

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

