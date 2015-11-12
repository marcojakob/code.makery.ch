---
layout: article
title: "JavaFX 8 Tutorial - Part 5: Storing Data as XML"
date: 2014-04-25 01:00
updated: 2015-03-12 00:00
slug: javafx-8-tutorial/it/part5
canonical: /library/javafx-8-tutorial/part5/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-it-part5.md
description: "Save data as XML with JAXB. Learn how to use the JavaFX FileChooser and the JavaFX Menu."
image: /assets/library/javafx-8-tutorial/part5/addressapp-part5.png
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
    active: true
  - text: "Parte 6: Grafico delle statistiche"
    link: /library/javafx-8-tutorial/it/part6/
    paging: 6
  - text: "Parte 7: Deployment"
    link: /library/javafx-8-tutorial/it/part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: Part 5 as Eclipse Project <em>(requires at least JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-5.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Languages
  collection: library
  item: javafx-8-tutorial
  part: part5
  active: it
---

![Screenshot AddressApp Part 5](/assets/library/javafx-8-tutorial/part5/addressapp-part5.png)


## Argomenti nella parte 5

* **Persistenza dei dati su file XML**
* Utilizzo del **FileChooser** di JavaFX
* Utilizzo del **Menu** di JavaFX
* Salvare l'ultimo file aperto nelle **preferenze utente**



*****

Attualmente nella nostra applicazione rubrica i dati riesiedono solo sulla memoria volatile. Ogni volta che chiudiamo l'applicazione, i dati vengono persi. E' quindi giunto il momento di pensare ad un sistema di persistenza dei dati.


## Salvare le prferenze utente

Java ci permette di salvare alcuni stati dell'applicazione con la classe chiamata `Preferences`. In base al sistema operativo utilizzato, le `Preferenze` sono salvate in posti diversi (es. il file di registro in windows).

Non saremo in grado di utilizzare `Preferences` per salvare tutto il contenuto della rubrica. Ma la classe ci permette di **salvare alcuni semplici stati dell'applicazione**. Una di queste cose è il **path dell'ultimo file aperto**. Con questa informazione possiamo caricare l'ultimo stato dell'applicazione ogni volta che l'utente esegue un avvio della stessa.

I due metodi seguenti si occupano di salvare e recuperare le preferenze. Aggingili alla fine della tua classe `MainApp`:


##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * Returns the person file preference, i.e. the file that was last opened.
 * The preference is read from the OS specific registry. If no such
 * preference can be found, null is returned.
 * 
 * @return
 */
public File getPersonFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    String filePath = prefs.get("filePath", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

/**
 * Sets the file path of the currently loaded file. The path is persisted in
 * the OS specific registry.
 * 
 * @param file the file or null to remove the path
 */
public void setPersonFilePath(File file) {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    if (file != null) {
        prefs.put("filePath", file.getPath());

        // Update the stage title.
        primaryStage.setTitle("AddressApp - " + file.getName());
    } else {
        prefs.remove("filePath");

        // Update the stage title.
        primaryStage.setTitle("AddressApp");
    }
}
</pre>


## Persistenza dei dati su file XML

### Perché l'XML?

Uno dei modi più comuni per realizzare una persistenza dati è quella di utilizzare un database. I database generalmente contengono dati relazionali (come le tabelle) mentre i dati che dobbiamo salvare sono fondamentalmente oggetti. Questo prende il nome di [object-relational impedance mismatch](http://wikipedia.org/wiki/Object-relational_impedance_mismatch). E' necessario un pò di lavoro per realizzare delle relazioni tra oggetti fra tabelle relazionali in un database. Ci sono diversi frameeorks che possono aituare a creare queste relazioni (es. [Hibernate](http://www.hibernate.org/), uno dei più popolari) ma richiede comunque un pò di lavoro prima di poter essere utilizzato.

Per il nostro semplice modello di dati è più semplice utilizzare un file XML. Useremo una libreria chiamata [JAXB](https://jaxb.java.net/) (**J**ava **A**rchitecture for **X**ML **B**inding). Con poche linee di codice JAXM ci permette di generare un XML in output come il seguente:

##### Esempio di file xml in output

<pre class="prettyprint lang-xml">
&lt;persons&gt;
    &lt;person&gt;
        &lt;birthday&gt;1999-02-21&lt;/birthday&gt;
        &lt;city&gt;some city&lt;/city&gt;
        &lt;firstName&gt;Hans&lt;/firstName&gt;
        &lt;lastName&gt;Muster&lt;/lastName&gt;
        &lt;postalCode&gt;1234&lt;/postalCode&gt;
        &lt;street&gt;some street&lt;/street&gt;
    &lt;/person&gt;
    &lt;person&gt;
        &lt;birthday&gt;1999-02-21&lt;/birthday&gt;
        &lt;city&gt;some city&lt;/city&gt;
        &lt;firstName&gt;Anna&lt;/firstName&gt;
        &lt;lastName&gt;Best&lt;/lastName&gt;
        &lt;postalCode&gt;1234&lt;/postalCode&gt;
        &lt;street&gt;some street&lt;/street&gt;
    &lt;/person&gt;
&lt;/persons&gt;
</pre>




### Utilizzare la JAXB

JAXB è già presente all'interno del JDK. Questo significa che non dobbiamo includere nessuna libreria aggiuntiva.

JAXB mette a disposizione due funzionalità principali: la possibilità di eseguire un **marshal (serializzazione)** di un oggetto Java in XML e l'**unmarshal (deserializzazione)** da XML in un oggetto Java.

Affinché JAXB sia in grado di eseguire la conversione, è necessario preparare un modello.


#### Preparare la Classe Modello per JAXB 

I dati che vogliamo salvare sono contenuti nella variabile `personData` nella classe `MainApp`. JAXB richiede che nella parte in alto della classe sia presente l'annotazione `@XmlRootElement`. `personData` è un oggetto `ObservableList` e non possiamo mettere nessuna annotazione ad un oggetto `ObservableList`. Quindi dobbiamo creare un'altra classe che è usata solo per contenere la nostra lista di `Persons` per il salvataggio su file XML.

La nuova classe che creiamo si chiama `PersonListWrapper` ed è inserita nel package `ch.makery.address.model`


##### PersonListWrapper.java

<pre class="prettyprint lang-java">
package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

	private List&lt;Person> persons;

	@XmlElement(name = "person")
	public List&lt;Person> getPersons() {
		return persons;
	}

	public void setPersons(List&lt;Person> persons) {
		this.persons = persons;
	}
}
</pre>

Notare le due annotazioni: 

* `@XmlRootElement` definisce il nome dell'elemento radice.
* `@XmlElement` è un nome opzionale che possiamo definire per l'elmento.


#### Leggere e Scrivere dati con JAXB

Daremo il compito di leggere e scrivere i dati delle persone alla nostra classe `MainApp`. Aggiungi i due seguenti metodi alla fine di `MainApp.java`:


<pre class="prettyprint lang-java">
/**
 * Loads person data from the specified file. The current person data will
 * be replaced.
 * 
 * @param file
 */
public void loadPersonDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

        personData.clear();
        personData.addAll(wrapper.getPersons());

        // Save the file path to the registry.
        setPersonFilePath(file);

    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());

        alert.showAndWait();
    }
}

/**
 * Saves the current person data to the specified file.
 * 
 * @param file
 */
public void savePersonDataToFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our person data.
        PersonListWrapper wrapper = new PersonListWrapper();
        wrapper.setPersons(personData);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

        // Save the file path to the registry.
        setPersonFilePath(file);
    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not save data");
        alert.setContentText("Could not save data to file:\n" + file.getPath());

        alert.showAndWait();
    }
}
</pre>

The `marshalling`/`unmarshalling` è pronto, andiamo a creare il menu save/load per poterno utilizzare.


## Catturare gli eventi del Menu

In our `RootLayout.fxml` there is already a menu, but we haven't used it yet. Before we add action to the menu we'll first create all menu items.

Open the `RootLayout.fxml` file in Scene Builder and drag the necessary menu items from the *library* group to the `MenuBar` bar in the *hierarchy* group. Create a **New**, **Open...**, **Save**, **Save As...**, and **Exit** menu item.

![Add Menu Items](/assets/library/javafx-8-tutorial/part5/add-menu-items.png)

Hint: Using the *Accelerator* setting under the *Properties* group you can set shortcut keys to menu items.


### The RootLayoutController

Per catturare gli eventi del menu avremo bisogno di una nuova classe controllore. Creiamo la classe `RootLayoutController` all'interno del package `ch.makery.address.view`. 

Aggiungiamo il seguente codice all'interno del controllore:


##### RootLayoutController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import ch.makery.address.MainApp;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.savePersonDataToFile(file);
		}
	}

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("AddressApp");
    	alert.setHeaderText("About");
    	alert.setContentText("Author: Marco Jakob\nWebsite: http://code.makery.ch");

    	alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
</pre>


#### FileChooser

Prendete nota dei metodi che utilizzano il `FileChooser` all'interno della classe `RootLayoutController` qui sopra. Primo, si è creato un nuovo oggetto della classe `FileChooser`. Successivamente, un filtro per l'estensione è stato aggiunto, cosicché siano visualizzati solo i file che terminano con `.xml`. Infine, il `file chooser` è mostrato sopra allo stage primario.

Se l'utente chiude il dialog senza aver scelto un file, viene ritornato il valore `null`. Altrimenti, abbiamo il file selezionato e lo possiamo passare al metodo `loadPersonDataFromFile(...)` o `savePersonDataToFile(...)` di `MainApp`.


### Connettere il file fxml al controllore

1. Apri `RootLayout.fxml` in Scene Builder. Nel gruppo *Controller* seleziona `RootLayoutController` come controllore della classe.

2. Torna al gruppo *Hierarchy* e seleziona una item del menu. Nel gruppo *Code* sotto **On Action** dovresti vedere una scelta di tutti i metodi disponibili nel controllore. Scegli il corrispondente metodo per ogni item del monu.
![Menu Actions](/assets/library/javafx-8-tutorial/part5/menu-actions.png)

3. Ripeti i passi per ogni item del menu.

4. Chiudi Scene Builder e premi **Refresh (F5)** sulla cartella principale del tuo progetto. Questo farà si che Ecplisce sia al corrente degli aggiornamenti che hai effettuato sullo Scene Builder.


### Connettere MainApp e RootLayoutController

In diversi posti, `RootLayoutController` necessita di un riferimento al retrostante `MainApp`. Non abbiamo ancora passato la reference a `RootLayoutController`.

Apri la classe `MainApp` e sostituisci il metodo `initRootLayout()` con il seguente codice:

<pre class="prettyprint lang-java">
/**
 * Initializes the root layout and tries to load the last opened
 * person file.
 */
public void initRootLayout() {
    try {
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class
                .getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // Give the controller access to the main app.
        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Try to load last opened person file.
    File file = getPersonFilePath();
    if (file != null) {
        loadPersonDataFromFile(file);
    }
}
</pre>

Nota i due cambiamenti: Le righe che *danno l'accesso al controllore all'applicazione principale* e le ultime tre linee per *caricare l'ultimo file persone aperto*.


### Test

Testando la tua applicazione dovresti essere in grado di usare il menu per salvare i dati delle persone su un file.

Quando apri il file `xml` in un editor noterari che il compleanno non è stato salvato correttamente, il tag `<birthday/>` risulta vuoto. La ragione di questo fatto è che JAXB non sa come convertire l'oggetto `LocalDate` in xml. Dobbiamo inserire un nostro `LocalDateAdapter` per definire questa conversione.

Create una nuova classe all'interno del package `ch.makery.address.util` chiamata `LocalDateAdapter` con il seguente contenuto:

##### LocalDateAdapter.java

<pre class="prettyprint lang-java">
package ch.makery.address.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter (for JAXB) to convert between the LocalDate and the ISO 8601 
 * String representation of the date such as '2012-12-03'.
 * 
 * @author Marco Jakob
 */
public class LocalDateAdapter extends XmlAdapter&lt;String, LocalDate> {

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}
}
</pre>

Quindi aprite `Person.java` ed aggiungete la seguente annotazione al metodo `getBirthday()`:

<pre class="prettyprint lang-java">
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public LocalDate getBirthday() {
    return birthday.get();
}
</pre>

Adesso, rieseguite il test. Provate a salvare e caricare il file xml. Dopo un riavvio, dovrebbe caricare automaticamente l'ultimo file usato.



## Come funziona


Guardiamo aadesso come funziona il meccanismo:

1. L'applicaizone è avviata usando il metodo `main(...)` all'interno di `MainApp`.
2. Il costruttore `public MainApp()` viene chiamato ed aggiunge alcuni dati campione.
3. Il metodo `start(...)` di `MainApp` viene chiamato ed a sua volta chiama `initRootLayout()` per inizializzare il root layout dal file `RootLayout.fxml`. Il file fxml possiede le informazioni riguardo quale controllore utilizzare e collega la vista al suo `RootLayoutController`. 
4. La `MainApp` prende il `RootLayoutController` dal loader fxml e passa una referenza di se stessa al controllore. Con questa referenza il controllore può successivamente accedere ai metodi pubblici di `MainApp`.
5. Alla fine del metodo `initRootLayout()` proviamo a prendere l'*l'ultimo file person che è stato aperto* da `Preferences`. Se `Preferences` è a conoscenza di un fule XML, caricheremo i dati da questo file. Questo evidentemente sovrascriverà i cati campione introdotti nel costruttore. 


### Qual'è il prossimo?

Nella [parte 6 del tutorial](/library/javafx-8-tutorial/part6/) aggiungeremo un grafico per la statistica del compleanno.


##### Alcuni articoli che potresti trovare interessanti

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
