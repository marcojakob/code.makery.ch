+++
title = "Part 4: CSS Styling"
date = 2014-04-25
updated = 2014-08-27
description = "In JavaFX you can style your user interface using CSS. We'll also add an application icon in this tutorial part."
image = "addressapp-part4.png"
prettify = true
comments = true 
commentsIdentifier = "/library/javafx-8-tutorial/it/part4/"
aliases = [ 
  "/library/javafx-8-tutorial/it/part4/"
]
weight = 4

[[sidebars]]
header = "Scaricare Fonti"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Parte 4 come progetto Eclipse <em>(requires at least JDK 8u40)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-4.zip"
+++

![Screenshot AddressApp Part 4](addressapp-part4.png)


## Argomenti nella parte 4

* **Stili con i CSS**
* Aggiungere un' **Icona per l'applicazione**



*****


## Stile con i CSS

Con le JavaFX puoi impostare lo stile della tua interfaccia utente usando i **Cascading Style Sheets (CSS)**. Una cosa fantastica! Non è mai stato così facile personalizzare l'aspetto di una applicazione Java.

In questo tutorial creeremo un *DarkTheme* ispirato dal design metro di windows 8. Il css per il pulsante è basato sul post [JMetro - Windows 8 Metro controls on Java](http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/) di Pedro Duque Vieira.


### Prendere familiarità con i CSS

Se vuoi personalizzare lo stule della tua applicazione JavaFX dovresti possedere almeno una conoscenza basilare dei CSS. Un buon posto per incominciare è questo [CSS tutorial](http://www.csstutorial.net/).

Per informazioni più specifiche su JavaFX riguardo i CSS:

* [Skinning JavaFX Applications with CSS](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/css_tutorial.htm) - Tutorial da Oracle
* [JavaFX CSS Reference](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html) - Documentazione ufficiale


### I CSS di default in JavaFX

Il sorgente di default per i css in JavaFX 8 è il file chiamato **`modena.css`**. Questo file può essere trovato all'inteno del file Jar delle Java FX, `jfxrt.jar` che puoi trovare all'interno della cartella Java in `/jdk1.8.x/jre/lib/ext/jfxrt.jar`.

Scoppatta il file `jfxrt.jar`. Dovresti poter trovare il file `modena.css` sotto `com/sun/javafx/scene/control/skin/modena/`

Questo file css è sempre apllicato di default alle applicazioni Java FX. Aggiungendo un css personalizzato possiamo sovrascrivere quello di default `modena.css`.   

<div class="alert alert-info">
<strong>Hint:</strong> Può essere di aiuto guardare il file css di default per vedere quali parametri hai la necessità di modificare.
</div>


### Utilizzare il file CSS

Aggiungi il seguente file CSS chiamato `DarkTheme.css` nel package *view*.


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

Adesso dobbiamo collegare il CSS all nostro oggetto Scene. Potremmo farlo direttamente dal codice Java, useremo invece Scene Builder per aggiungerlo al nostro file fxml: 


#### Collegare il CSS a RootLayout.fxml

1. Apri il file `RootLayout.fxml` nel Scene Builder. 

2. Seleziona la root `BorderPane` nella vista gerarchica. Sotto il gruppo *Properties* aggiugni il file `DarkTheme.css` come stylesheet.   
![DarkTheme for RootLayout](darktheme-rootlayout.png)


#### Collegare il CSS a PersonEditDialog.fxml

1. Apri il file `PersonEditDialog.fxml` in Scene Builder. Seleziona la radice `AnchorPane` e scegli `DarkTheme.css` nel gruppo *Properties* come stylesheet.

2. Lo sfondo rimane bianco, quindi aggiungi la Style Class `background` alla radice `AnchorPane`.   
![Add Style Class](darktheme-personeditdialog.png)

3. Seleziona il pulsante OK e scegli *Default Button* nella vista Properties. Questo cambierà il suo colore e lo setterà come pulsante di default quando la key *invio* sulla tastiera viene premuta dall'utente.


#### Collegare il CSS a PersonOverview.fxml

1. Apri il file `PersonOverview.fxml` in Scene Builder. Seleziona la radice `AnchorPane` nel gruppo *Hierarchy*. Sotto `properties` aggiungi il file `DarkTheme.css` come stylesheet.

2. A questo punto dovresti vedere qualche cambiamento: La tabella ed i pulsanti sono neri. Tutte le classi di stile `.table-view` e`.button` dal file `modena.css` applicate alla tabella ed ai pulsanti. Dal momento che abbiamo ridefinito (e quindi sovrascritto) alcuni di questi stili nel nostro CSS personalizzato, i nuovi stili sono applicati automaticamente.

3. Potresti dover aggiustare la dimensione dei pulsanti affinché tutti i testi tornino ad essere visibili.

4. Seleziona sulla destra l'`AnchorPane` che si trova dentro allo `SplitPane`.   
![Background Style Select](background-style-select.png)   

5. Vai nel gruppo *Properties* e seleziona `background` come style class. Lo sfondo dovrebbe diventare nero.   
![Background Style](background-style.png)


#### Label con Style diversi

Ora, tutti i labels sul lato destro hanno la stessa dimensione. Nel file css ci sono già alcuni stili definiti come `.label-header` e `.label-bright` che useremo per un ulteriore personalizzaizone dei label.

1. Select the *Person Details* label and add `label-header` as a Style Class.   
![Label Header Style](label-header-style.png)

2. Per ogni label nella colonna di destra (dove i dettagli della persona attuale sono visualizzati), aggiungi lo Style Class del css `label-bright`.   
![Label Bright Style](label-bright-style.png)


*****


## Aggiungere un'Icona per l'applicazione

Adesso la nostra applicazione ha l'icona di default nella title bar e nella task bar:

![Default Icon](default-app-icon.png)

Apparirebbe molto migliore con un'icona personalizzata:

![Custom Icon](custom-app-icon.png)


### Il file Icon

Un possibile posto in cui reperire icone gratuitamente è [Icon Finder](http://www.iconfinder.com). Ho scaricato una semplice [icona rubrica](https://www.iconfinder.com/icons/86957/address_book_icon#size=32).

Creare una cartella (normale) dentro alla progetto AddressApp chiamata **resources** ed una sottocartella chiamata **images** al suo interno. Mettete l'icona che avete scelto all'interno della sottocartella. La tua struttura di cartelle dovrebbe apparire in questo modo adesso:

![Custom Icon File](custom-icon-file.png)


### Impostare l'icona nell'oggetto Scene

Per impostare l'icona nel nostro oggetto scene aggiungi la seguente linea nel metodo `start(...)` in `MainApp.java`


##### MainApp.java

<pre class="prettyprint lang-java">
this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
</pre>

L'intero metodo `start(...)` dovrebbe apparire in questo modo adesso:

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

Puoi anche aggiungere un'icona all'oggetto stage del Dialog di modifica della persona se lo desideri.


### Qual'è il prossimo?

Nella [parte 5 del tutorial](/it/library/javafx-tutorial/part5/) aggiungeremo una memorizzazione su file XML per i nostri dati.


##### Altri articoli che potrebbero insteressarti

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

