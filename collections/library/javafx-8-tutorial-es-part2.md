---
layout: article
title: "Tutorial JavaFX 8 - Part 2: Modelo y TableView"
date: 2014-09-17 00:00
updated: 2014-09-17 00:00
slug: javafx-8-tutorial/es/part2
canonical: /java/javafx-8-tutorial-part2/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-es-part2.md
description: "Uso de un componente TableView para mostrar una lista (ObsevableList) de personas (Person)"
image: /assets/library/javafx-8-tutorial/part2/addressapp-part2.png
published: true
prettify: true
comments: true
sidebars:
- header: "Artículos en esta serie"
  body:
  - text: "Introducción"
    link: /library/javafx-8-tutorial/es/
    paging: Intro
  - text: "Parte 1: Scene Builder"
    link: /library/javafx-8-tutorial/es/part1/
    paging: 1
  - text: "Parte 2: Modelo y TableView"
    link: /library/javafx-8-tutorial/es/part2/
    paging: 2
    active: true
  - text: "Parte 3: Interacción con el usuario"
    link: /library/javafx-8-tutorial/es/part3/
    paging: 3
  - text: "Parte 4: Hojas de estilo CSS"
    link: /library/javafx-8-tutorial/es/part4/
    paging: 4
  - text: "Parte 5: Persistencia de datos con XML"
    link: /library/javafx-8-tutorial/es/part5/
    paging: 5
  - text: "Parte 6: Gráficos estadísticos"
    link: /library/javafx-8-tutorial/es/part6/
    paging: 6
  - text: "Parte 7: Publicación con e(fx)clipse"
    link: /library/javafx-8-tutorial/es/part7/
    paging: 7
- header: Lenguajes
  body:
  - text: English
    link: /java/javafx-8-tutorial-part2/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part2/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part2/
    icon-css: fa fa-fw fa-globe
    active: true
---

<div class="alert alert-warning">
  <i class="fa fa-language"></i> This page is beeing translated to Spanish. If you'd like to help out please read <a href="/library/how-to-contribute/" class="alert-link">how to contribute</a>.
</div>

![Screenshot AddressApp Part 2](/assets/library/javafx-8-tutorial/part2/addressapp-part2.png)


## Contenidos en Parte 2

* Creación de una clase para el **modelo**
* Uso del modelo en una **ObservableList**
* Visualización del modelo mediante **TableView** y **Controladores**


*****

## Crea la clase para el Modelo

Neceistamos un modelo para contener la información sobre los contactos de nuestra agenda. Añade una nueva clase al paquete encargado de contener los modelos (`ch.makery.address.model`) denominada `Person`. La clase `Person` tendrá atributos (instancias de clase) para el nombre, la dirección y la fecha de nacimiento. Añade el código siguiente a la clase. Explicaré detalles de JavaFX después del código.


#### Person.java

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


### Explicación del código

* Con JavaFX es habitual usar [`Propiedades`](http://docs.oracle.com/javase/8/javafx/api/javafx/beans/property/Property.html) para todos los atributos de un clase usada como modelo. Una `Propiedad` permite, entre otras cosas, recibir notificaciones automáticamente cuando el valor de una variable cambia (por ejemplo si cambia `lastName`. Esto ayuda a mantener sincronizados la vista y los datos. Para aprender más sobre `Propiedades` lee [Using JavaFX Properties and Binding](http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm).
* [`LocalDate`](http://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html), el tipo que usamos para especificar la fecha de nacimiento (`birthday`) es parte de la nueva [API de JDK 8 para la fecha y la hora](http://docs.oracle.com/javase/tutorial/datetime/iso/).


*****

## Una lista de personas

Los principales datos que maneja nuestra aplicación son una colección de personas. Vamos a crear una lista de objetos de tipo `Person` dentro de la clase principal `MainApp`. El resto de controladores obtendrá luego acceso a esa lista central dentro de `MainApp`. 


### Lista observable (ObservableList)

Estamos clases gráficas de JavaFX que necesitan ser informadas sobre los cambios en la lista de personas. Esto es importante, pues de otro modo la vista no estará sincronizada con los datos. Para estos fines, JavaFX ha introducido nuevas [clases de colección](http://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm). 

De esas colecciones, necesitamos la denominada `ObservableList`. Para crear una nueva `ObservableList`, añade el código siguiente al principio de la clase `MainApp`. También añadiremos un constructor para crear datos de ejemplo y un método de consulta (get) público:


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

## PersonOverviewController ##

Finalmente vamos a añadir datos a nuestra table. Para ello necesitaremos un controlador específico para la vista `PersonOverview.fxml`.

1. Crea una clase normal dentro del paquete **view** denominado `PersonOverviewController.java`. (Debemos ponerlo en el mismo paquete que `PersonOverview.fxml` o el Scene Builder no lo encontrará, al menos no en la versión actual).
2. Añadiremos algunos atributos para acceder a la tabla y las etiquetas de la vista. Estos atributos irán precedidos por la anotación `@FXML`. Esto es necesario para que la vista tenga acceso a los atributos y métodos del controlador, incluso aunque sean privados. Una vez definida la vista en fxml, la aplicación se encargará de rellenar automáticamente estos atributos al cargar el fxml. Así pues, añade el código siguiente:

<div class="alert alert-info">
**Nota:** acuérdate siempre de importar **javafx**, NOT AWT o Swing!
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


Este código necesita cierta explicación:

* Los campos y métodos donde el archivo fxml necesita acceso deben ser anotados con `@FXML`. En realidad, sólo si son privados, pero es mejor tenerlos privados y marcarlos con la anotación.
* El método `initialize()` es invocado automáticamente tras cargar el fxml. En ese momento, todos los atributos FXML deberían ya haber sido inicializados..
* El método `setCellValueFactory(...)` que aplicamos sobre las columnas de la tabla se usa para determinar qué atributos de la clase `Person` deben ser usados para cada columna particular. La flecha `->` indica que estamos usando una característica de Java 8 denominada *Lambdas*. Otra opción sería utilizar un [PropertyValueFactory](http://docs.oracle.com/javase/8/javafx/api/), pero entonces no ofrecería seguridad de tipo (type-safe).


### Conexión de MainApp con PersonOverviewController

El método `setMainApp(...)` debe ser invocado desde la clase `MainApp`. Esto nos da la oportunidad de acceder al objeto `MainApp` para obtener la lista de `Person` y otras cosas. Sustituye el método `showPersonOverview()` con el código siguiente, el cual contiene dos líneas adicionales:


##### MainApp.java - nuevo método showPersonOverview()s

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

        // Give the controller access to the main app.
        PersonOverviewController controller = loader.getController();
        controller.setMainApp(this);

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>



*****


## Vincular la vista al controlador

¡Ya casi lo tenemos! Pero falta un detalle: no le hemos indicado a la vista declarada en `PersonOverview.fxml` cuál es su controlador y que elemento hacer corresponder to cada uno de los atributos en el controlador.

1. Abre `PersonOverview.fxml` en *SceneBuilder*.

2. Abre la sección *Controller* en el lado izquierdo y selecciona `PersonOverviewController` como **controlador**.   
![Set Controller Class](/assets/library/javafx-8-tutorial/part2/set-controller-class.png)

3. Selecciona `TableView` en la sección *Hierarchy* y en el apartado *Code* escribe  `personTable` en la propiedad **fx:id**.   
![Set TableView fx:id](/assets/library/javafx-8-tutorial/part2/set-tableview-fx-id.png)

4. Haz lo mismo para las columnas, poniendo `firstNameColumn` y `lastNameColumn` como sus **fx:id** respectivamente.

5. Para **cada etiqueta** en la segunda columna, introduce el **fx:id** que corresponda.   
![Set Label fx:id](/assets/library/javafx-8-tutorial/part2/set-label-fx-id.png)

6. Importante: En Eclipse **refresca el projecto AddressApp** (tecla F5). Esto es necesario porque a veces Eclipse no se da cuenta de los cambios realizados desde el Scene Builder.


*****

## Inicia la aplicación

Ahora, cuando ejecutes la aplicación, deberías obtener algo parecido a la captura de pantalla incluida al principio de este artículo.   

Enhorabuena!


### ¿Qué es lo siguiente?

En [Tutorial Parte 3](/java/javafx-8-tutorial-part3/) añadiremos la funcionalidad requerida para añadir, borrar y editar contactos.


##### Otros artículos que podrías encontrar de interés

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)