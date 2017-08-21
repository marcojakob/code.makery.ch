---
layout: article
title: "JavaFX 8 Tutorial - Parte 2: Model and TableView"
date: 2014-04-23 00:00
updated: 2015-03-12 00:00
slug: javafx-8-tutorial/it/part2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-it-part2.md
description: "Uso della TabletView JavaFX per visualizzare una  ObservableList di Persons."
image: /assets/library/javafx-8-tutorial/part2/addressapp-part2.png
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
    active: true
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
  - text: Parte 2 come progetto Eclipse <em>(requires at least JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-2.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Languages
  collection: library
  item: javafx-8-tutorial
  part: part2
  active: it
---

![Screenshot AddressApp Part 2](/assets/library/javafx-8-tutorial/part2/addressapp-part2.png)


## Argomenti della Parte 2

* Creazione di una **model** class
* Uso della model class in una **ObservableList**
* Visualizzazione dei dati nella **TableView** usando un **Controllers**


*****

## Creare la Model Class

Abbiamo bisogno della model class per tenere le informazioni sulle persone nella nostra rubrica. Aggiungere una nuova classe al model package (`ch.makery.address.model`) chiamata `Person`. La classe `Person` avrà alcune variabili d'istanza per il nome, l'indirizzo e il compleanno. Aggiungere il seguente codice alla classe. Spiegerò alcune cose specifiche di JavaFX dopo il codice.


##### Person.java

<pre class="prettyprint lang-java">
package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty street;
	private final IntegerProperty postalCode;
	private final StringProperty city;
	private final ObjectProperty&lt;LocalDate&gt; birthday;

	/**
	 * Default constructor.
	 */
	public Person() {
		this(null, null);
	}
	
	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
		// Some initial dummy data, just for convenient testing.
		this.street = new SimpleStringProperty("some street");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.city = new SimpleStringProperty("some city");
		this.birthday = new SimpleObjectProperty&lt;LocalDate&gt;(LocalDate.of(1999, 2, 21));
	}
	
	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}
	
	public StringProperty streetProperty() {
		return street;
	}

	public int getPostalCode() {
		return postalCode.get();
	}

	public void setPostalCode(int postalCode) {
		this.postalCode.set(postalCode);
	}
	
	public IntegerProperty postalCodeProperty() {
		return postalCode;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}
	
	public StringProperty cityProperty() {
		return city;
	}

	public LocalDate getBirthday() {
		return birthday.get();
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}
	
	public ObjectProperty&lt;LocalDate&gt; birthdayProperty() {
		return birthday;
	}
}
</pre>


### Spiegazioni

* Con JavaFX è usuale usare le [`Properties`](http://docs.oracle.com/javase/8/javafx/api/javafx/beans/property/Property.html) per tutti i campi di una model class. Le `Property` ci permettono, per esempio, di avere una notifica automatica quando `lastName` o qualunque altra variabile vengono modificate. Questo ci aiuta a tenere sincronizzato ciò che è visualizzato con i dati. Per saperne di più sulle `Properties` leggere [Using JavaFX Properties and Binding](http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm).
* [`LocalDate`](http://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html), il tipo che usiamo per `birthday`, è parte delle nuove [Date and Time API for JDK 8](http://docs.oracle.com/javase/tutorial/datetime/iso/).


*****

## Una lista di Persons

I principali dati che la nostra applicazione gestisce sono un gruppo di persone. Creiamo una lista di oggetti `Person` all'interno della classe `MainApp`. Tutti agli altri controller della classi avranno in seguito accesso alla lista centrale all'interno della classe `MainApp`. 


### ObservableList

Stiamo lavorando con le classi di visualizzazione JavaFX che hanno bisogno di essere informate su eventuali modifiche apportate alla lista delle persone. Questo è importante, poiché altrimenti la vista non sarebbe sincronizzata con i dati. Per questo scopo, JavaFX ha introdotto alcune nuove [Collection classes](http://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm). 

Di quelle "Collections", utiliziamo una `ObservableList`. Per creare una nuova `ObservableList`, aggiungere il seguente codice all'inizio della classe `MainApp`. Dobbiamo inoltre aggiungere un costruttore che crei alcuni dati di esempio e un metodo getter pubblico:


##### MainApp.java

<pre class="prettyprint lang-java">

    // ... AFTER THE OTHER VARIABLES ...

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList&lt;Person&gt; personData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {
		// Add some sample data
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Mueller"));
	}
  
	/**
	 * Returns the data as an observable list of Persons. 
	 * @return
	 */
	public ObservableList&lt;Person&gt; getPersonData() {
		return personData;
	}
  
    // ... THE REST OF THE CLASS ...
</pre>


*****

## Il PersonOverviewController ##

Abbiamo bisogno di un controller per il nostro `PersonOverview.fxml`.

1. Creare una normale classe dentro il **view** package chiamata `PersonOverviewController.java`. (Dobbiamo metterla nello stesso package di `PersonOverview.fxml`, altrimenti SceneBuilder non la troverà.
2. Aggiungeremo alcune variabili d'istanza che ci daranno accesso alla tabella e alle labels all'interno della vista. I campi e alcuni metodi hanno una speciale annotazione `@FXML`. Questa è necessaria per il file fxml per avere accesso ai campi e metodi privati. Dopo aver impostato tutto nel file fxml, l'applicazione popolerà automaticamente le variabili quando il file fxml sarà caricato. Aggiungiamo il seguente codice:

<div class="alert alert-info">
**Note:** Ricordarsi di usare sempre **javafx imports**, NON awt o swing!
</div>


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;

public class PersonOverviewController {
    @FXML
    private TableView&lt;Person&gt; personTable;
    @FXML
    private TableColumn&lt;Person, String&gt; firstNameColumn;
    @FXML
    private TableColumn&lt;Person, String&gt; lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
}
</pre>


Adesso questo codice ha probabilmente bisogno di alune spiegazioni:

* Tutti i campi e i metodi a cui il file fxml ha bisogno di accedere devono essere annotati con `@FXML`. Attualmente, solo se sono privati, ma è meglio averli privati e comunque marcarli con l'annotazione!
* Il metodo `initialize()` è automaticamente chiamato dopo che il file fxml è stato caricato. In questo momento, tutti i campi FXML dovrebbero essere già inizializzati.
* `setCellValueFactory(...)` che impostiamo sulle colonne della tabella è usato per determinare quale campo all'interno dell'oggetto `Person` dovrebbe essere usato per quella particolare colonna. La freccia `->` indica che stiamo usando una caratteristica di Java 8 chiamata *Lambdas*. (Un'altra opzione potrebbe essere quella di usare una [PropertyValueFactory](http://docs.oracle.com/javase/8/javafx/api/), ma non è un tipo sicuro).


<div class="alert alert-info">
  <p>
    In questo esempio stiamo usando solo un valore `StringProperty` per le colonne. Quando si vuole usare un `IntegerProperty` o un `DoubleProperty`, `setCellValueFactory(...)` deve avere anche un `asObject()`:
  </p>
  <p>
  <pre>myIntegerColumn.setCellValueFactory(cellData -> 
      cellData.getValue().myIntegerProperty().<mark>asObject()</mark>);</pre>
  </p>
  <p>
    Questo è necessario per una cattiva scelta di design di JavaFX (vedi <a href="https://community.oracle.com/thread/2575601">questa discussione</a>).
  </p>
</div>


### Connessione di MainApp con PersonOverviewController

Il metodo `setMainApp(...)` deve essere chiamato dalla classe `MainApp`. Questo ci dà modo di accedere ad un oggeto `MainApp` e di prendere una lista di `Persons` e altre cose. Sostituire il metodo `showPersonOverview()` con il seguente. Questo contiene due linee aggiuntive:


##### MainApp.java - nuovo metodo showPersonOverview() 

<pre class="prettyprint lang-java">
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

<mark>        // Give the controller access to the main app.
        PersonOverviewController controller = loader.getController();
        controller.setMainApp(this);</mark>

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>



*****


## Agganciare la vista al Controller

Ci siamo quasi! Ma manca una piccola cosa: Non abbiamo ancora detto al nostro file `PersonOverview.fxml` quale controller usare e come accoppiare elemento e campo all'interno del controller .

1. Aprire `PersonOverview.fxml` con *SceneBuilder*.

2. Aprire il gruppo *Controller* sul lato sinistro e selezionare `PersonOverviewController` come **controller class**.   
![Set Controller Class](/assets/library/javafx-8-tutorial/part2/set-controller-class.png)

3. Selezionare la `TableView` nel gruppo *Hierarchy* e selezionare `personTable` nel campo **fx:id**.   
![Set TableView fx:id](/assets/library/javafx-8-tutorial/part2/set-tableview-fx-id.png)

4. Fare lo stesso per le colonne selezionando rispettivamente `firstNameColumn` e `lastNameColumn` nel campo **fx:id**.

5. **Per ogni label** nella seconda colonna, scegliere il corrispondente **fx:id**.   
![Set Label fx:id](/assets/library/javafx-8-tutorial/part2/set-label-fx-id.png)

6. Importante: Tornare su Eclipse e **e aggiornare l'intero progetto** (F5). Questo è necessario perchè alcune volte Eclipse non rileva i cambiamenti apportati in Scene Builder.


*****

## Avviare dell'applicazione

Se avvii l'applicazione adesso dovresti vedere qualcosa di simile allo screenshot all'inizio del post. 

Congratulazioni!

*Nota: Le label non si aggiornano ancora quando viene selezionata una persona. Gestiremo l'interazione con l'utente nella prossima parte del tutorial*


### What's Next?

Nella [Parte 3 del tutorial](http://code.makery.ch/library/javafx-8-tutorial/it/part3/) aggiungeremo nuove funzionalità per aggiungere, cancellare e modificare una persona.


##### Alcuni articoli che potresti trovare interessanti

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
