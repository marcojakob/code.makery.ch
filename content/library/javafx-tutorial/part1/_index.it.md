---
layout: article
title: "JavaFX 8 Tutorial - Parte 1: Scene Builder"
date: 2014-04-19 01:00
updated: 2015-03-12
slug: javafx-8-tutorial/it/part1
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-it-part1.md
description: "Impariamo come impostare un progetto JavaFX. Questo tutorial in sette parti, guida nella progettazione, nella programmazione e nel deploying di una applicazione rubrica con JavaFX."
image: /assets/library/javafx-8-tutorial/part1/addressapp-part1.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articoli in questa serie"
  body:
  - text: "Introduzione"
    link: /library/javafx-8-tutorial/it/
    paging: Intro
  - text: "Parte 1: Scene Builder"
    link: /library/javafx-8-tutorial/it/part1/
    paging: 1
    active: true
  - text: "Parte 2: Model and TableView"
    link: /library/javafx-8-tutorial/it/part2/
    paging: 2
  - text: "Parte 3: Interazione con l'utente"
    link: /library/javafx-8-tutorial/it/part3/
    paging: 3
  - text: "Parte 4: CSS Styling"
    link: /library/javafx-8-tutorial/it/part4/
    paging: 4
  - text: "Parte 5: Conservazione dati come XML"
    link: /library/javafx-8-tutorial/it/part5/
    paging: 5
  - text: "Parte 6: Grafico delle statistiche"
    link: /library/javafx-8-tutorial/it/part6/
    paging: 6
  - text: "Parte 7: Deployment"
    link: /library/javafx-8-tutorial/it/part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: Parte 1 come progetto Eclipse <em>(requires at least JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-1.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Languages
  collection: library
  item: javafx-8-tutorial
  part: part1
  active: it
---

![Screenshot AddressApp Part 1](/assets/library/javafx-8-tutorial/part1/addressapp-part1.png)

### Argomenti nella parte 1
 
* Conoscere JavaFX
* Creare ed eseguire un progetto JavaFX 
* Usare Scene Builder per progettare l'interfaccia utente
* Struttura base dell'applicazione usando il pattern Model-View-Controller (MVC) 


*****


### Prerequisiti

* Ultima versione di [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (incluso **JavaFX 8**).
* Eclipse 4.4 o superiore con il plugin e(fx)clipse. La via più facile è scaricare una distro preconfigurata dal  [sito di e(fx)clipse](http://efxclipse.bestsolution.at/install.html#all-in-one). Come alternativa puoi usare un [sito di aggiornamento](http://www.eclipse.org/efxclipse/install.html) per la tua installazione di Eclipse.
* [Scene Builder 8.0](http://gluonhq.com/products/scene-builder/) (fornito da Gluon perchè [Oracle lo rende disponibile solo sotto forma di codice sorgente](http://www.oracle.com/technetwork/java/javase/downloads/sb2download-2177776.html)).

### Configurazione di Eclipse 

Abbiamo bisogno che Eclipse usi JDK 8 e dovremo specificargli dove trovare Scene Builder:

1. Aprire le preferenze di Eclipse e andare su *Java | Installed JREs*.

2. Cliccare *Add...*, selezionare *Standard VM* e scegliere la *Directory* di installazione del tuo JDK 8.

3. Rimuovere gli altri JREs o JDKs così che **JDK 8 vada di default**.  
![Preferences JDK](/assets/library/javafx-8-tutorial/part1/preferences-jdk.png)

4. Andare su *Java | Compiler*. Imposta **Compiler compliance level to 1.8**. 
![Preferences Compliance](/assets/library/javafx-8-tutorial/part1/preferences-compliance.png)

5. Andare sulle preferenze di *JavaFX*. Specificare il percorso dell'eseguibile di Scene Builder.  
![Preferences JavaFX](/assets/library/javafx-8-tutorial/part1/preferences-javafx.png)


### Link di supporto

Potresti voler aggiungere ai tuoi preferiti i seguenti link:

* [Java 8 API](http://docs.oracle.com/javase/8/docs/api/) - JavaDoc for the standard Java classes
* [JavaFX 8 API](http://docs.oracle.com/javase/8/javafx/api/) - JavaDoc for JavaFX classes
* [ControlsFX API](https://controlsfx.bitbucket.io/) - JavaDoc for the [ControlsFX project](http://fxexperience.com/controlsfx/) for additional JavaFX controls
* [Oracle's JavaFX Tutorials](http://docs.oracle.com/javase/8/javafx/get-started-tutorial/get_start_apps.htm) - Official JavaFX Tutorials by Oracle

Adesso, cominciamo!


*****


## Creare un nuovo progetto JavaFX

In Eclipse (con e(fx)clipse installato) andare su *File | New | Other...* e scegliere *JavaFX Project*.   
Specificare il nome del progetto (e.g. *AddressApp*) e cliccare su *Finish*.

Rimuovere il package *application*  e il suo contenuto se questo viene automaticamente creato.


###  Creare i Packages

Fin dall'inizio seguiremo i buoni principi della progettazione software. Un principio molto importante è quello del [**Model-View-Controller** (MVC)](http://en.wikipedia.org/wiki/Model_View_Controller). Segeuendo questo, dividiamo il nostro codice in tre unità e creiamo un package per ognuna di queste (Tasto destro su src-folder, *New... | Package*):

* `ch.makery.address` - contenente *la maggior parte* delle classi controller (=business logic)
* `ch.makery.address.model` - contenente le classi model
* `ch.makery.address.view` - contenente le view 

**Nota:** Il nostro package view conterrà inoltre alcuni controller che sono relativi ad una singola view. Gli chiameremo **view-controllers**.


*****


## Creare il file di layout FXML 

Ci sono due modi per creare l'interfaccia utente. O usare un file XML o programmarla tutta in Java. Gurdandovi intorno su internet incontrerete entrambe. Useremo XML (.fxml) per molte parti. Io trovo che questa sia la via più pulita per tenere controller e view saparati gli uni dagli altri. Inoltre, possiamo usare Scene Builder per editare i nostri XML. Questo significa che non lavoreremo direttamente con XML.

Tasto destro sul package view e creiamo un nuovo *FXML Document* chiamato `PersonOverview`.   

![New FXML Document](/assets/library/javafx-8-tutorial/part1/new-fxml-document.png)

![New PersonOverview](/assets/library/javafx-8-tutorial/part1/new-person-overview.png)



*****


## Progettare con Scene Builder

<div class="alert alert-warning">
  **Nota:** Se non riuscite a farlo funzionare, scaricate il sorgente di questa parte del tutorial e provate con il file fxml                incluso.
</div>

Tasto destro su `PersonOverview.fxml` e click su *Open with Scene Builder*. Adesso dovreste vedere Scene Builder con solo un *AncherPane* (visibile sotto Hierarchy sulla sinistra).

(Se Scene Builder non si apre, andare su *Window | Preferences | JavaFX* e settare il giusto percorso dell'installazione di Scene Builder).

1. Selezionare *Anchor Pane* nella Hierarchy e modificare le dimensioni sotto Layout (lato destro): 
![Anchor Pane Size](/assets/library/javafx-8-tutorial/part1/anchor-pane-size.png)

2. Aggiungere un *Split Pane (Horizontal Flow)* trascinandolo dalla Library nella area principale. Tasto destro su *Split Pane* nella vista *Hierarchy* e selezionare *Fit to Parent*.  
![Fit to Parent](/assets/library/javafx-8-tutorial/part1/fit-to-parent.png)

3. Trascinare una *TableView* (sotto *Controls*) nella parte sinistra dello *SplitPane*. Selezionare la TableView (non la colonna) e impostare il seguente "layout constraints" per la TableView. All'interno di un *AnchorPane* è sempre possibile impostare gli  anchors per i quattro bordi ([Più informazioni sui Layouts](http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm)).   
![TableView Anchors](/assets/library/javafx-8-tutorial/part1/table-view-anchors.png)

4. Andare al menù *Preview | Show Preview in Window* per vedere come si comporta. Provare a ridemensionare la finestra. La TableView dovrebbe ridimensionarsi insieme alla finestra come fosse ancorata ai bordi.

5. Cambiare il testo delle colonne (sotto Properties) in "First Name" e "Last Name".   
![Column Texts](/assets/library/javafx-8-tutorial/part1/column-texts.png)

6. Selezionare la *TableView* e scegliere *constrained-resize* per la *Column Resize Policy* (sotto Properties). Questo assicurerà che la colonna occuperà tutto lo spazio disponibile.   
![Column Resize Policy](/assets/library/javafx-8-tutorial/part1/column-resize-policy.png)

7. Aggiungere una *Label* sul lato destro con il testo "Person Details" (suggerimento: usare la barra di ricerca per trovare *Label*). Regolare il suo layout usando gli anchors.   
![Person Details Label](/assets/library/javafx-8-tutorial/part1/person-details-label.png)

8. Aggiungere un *GridPane* sul lato destro, selezionarlo e regolare il suo layout con gli anchors (superiore, destro e sinistro).    
![GridPane Layout](/assets/library/javafx-8-tutorial/part1/grid-pane-layout.png)

9. Aggiungere le seguenti labels alle celle.   
*Nota: Per aggiungere una riga al GridPane selezionare un esistente numero di riga (diventerà giallo), tasto destro sul numero di riga e scegliere "Add Row".*   
![Add labels](/assets/library/javafx-8-tutorial/part1/add-labels.png)

10. Aggiungere una *ButtonBar* in basso. Aggiungere tre bottoni alla barra. Adesso, impostare gli anchors (destro e inferiore) della *ButtonBar* così da posizionarla nel lato destro.   
![Button Group](/assets/library/javafx-8-tutorial/part1/button-group.png)

11. Adesso dovresti vedere qualcosa simile alla figura. Usa il menù *Preview* per testare il comportamento quando viene ridimensionata.   
![Preview](/assets/library/javafx-8-tutorial/part1/scene-builder-preview.png)



*****


## Creare l'applicazione "Main"

Abbiamo bisogno di un altro FXML per il nostro layout radice che conterrà una barra dei menù e si aggancerà all'appena creato  `PersonOverview.fxml`.

1. Creare un'altro *FXML Document* all'interno del "view package" chiamandolo `RootLayout.fxml`. Questa volta sceglieremo *BorderPane* come elemento radice.   
![New RootLayout](/assets/library/javafx-8-tutorial/part1/new-root-layout.png)

2. Aprire `RootLayout.fxml` con Scene Builder.

3. Ridimensionare *BorderPane* con *Pref Width* impostato a 600 e *Pref Height* impostato a 400.   
![RootLayout Size](/assets/library/javafx-8-tutorial/part1/root-layout-size.png)

4. Implementeremo le funzionalità dei menù in un secondo momento.   
![MenuBar](/assets/library/javafx-8-tutorial/part1/menu-bar.png)


### La Classe Main JavaFX 

Adesso abbiamo bisogno di creare la **main java class** che avvia la nostra applicazione con il `RootLayout.fxml` e aggiunge la `PersonOverview.fxml` al centro. 

1. Tasto destro sul progetto, selezionare *New | Other...* e scegliere *JavaFX Main Class*.   
![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class.png)

2. Chiameremo la classe `MainApp` e la metteremo nel controller package `ch.makery.address` (nota: questo è un package parente dei `view` e `model` subpackages).   
![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class2.png)


La classe `MainApp.java` generata è estesa da `Application` e contiene due metodi. Questa è la struttura base di cui abbiamo bisogno per avviare una applicazione JavaFX. La parte più importante è il metodo `start(Stage primaryStage)`. Questo è automaticamente chiamato quando l'applicazione viene `lanciata` dall'interno del metodo `main`.
Come puoi vedere, il metodo `start(...)` riceve uno `Stage` come parametro. Il seguente grafico illustra la struttura di ogni applicazione JavaFX:

![New FXML Document](/assets/library/javafx-8-tutorial/part1/javafx-hierarchy.png)   
*Image Source: http://www.oracle.com*

**E' come uno spettacolo teatrale**:`Stage` è un il contenitore principale, di solito un finistra `Window` con dei bordi e i tipici pulsanti per minimizzare, massimizzare e chiudere. All'interno di `Stage` si aggiunge una `Scene` che può, naturalmente, avvicendarsi ad un'altra. All'interno della `Scene` gli effettivi nodi JavaFX come `AnchorPane`, `TextBox`, etc. vengono aggiunti.

Per ulteriori informazioni [Working with the JavaFX Scene Graph](http://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm).


*****

Aprire `MainApp.java` e stostituire il codice con il seguente:

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

I vari commenti ti daranno dei suggerimenti sul funzionamento.

Se avvii l'applicazione adesso dovresti vedere qualcosa di simile allo screenshot all'inizio del post.


### Problemi frequenti

Se JavaFX non trova il file `fxml` potresti vedere il seguente messaggio: 

`java.lang.IllegalStateException: Location is not set.`

Per risolvere controlla di non aver sbagliato il nome del tuo file `fxml`!

<div class="alert alert-warning">
  Se continui ad avere problemi, scarica il sorgente di questa parte del tutorial e prova con il file fxml incluso.
</div>


*****

### What's Next?

Nella [Parte 2 del tutorial](http://code.makery.ch/library/javafx-8-tutorial/it/part2/) aggiungeremo alcuni dati e alcune funzionalità alla nostra rubrica.


##### Alcuni articoli che potresti trovare interessanti

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
