---
layout: article
title: "Tutorial JavaFX 8 - Parte 5: Persistencia de datos con XML"
date: 2014-09-17 00:00
slug: javafx-8-tutorial/es/part5
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-es-part5.md
description: "Almacena datos en XML con JAXB. Aprender a utilizar FileChooser y los menús de JavaFX."
image: /assets/library/javafx-8-tutorial/part5/addressapp-part5.png
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
  - text: "Parte 3: Interacción con el usuario"
    link: /library/javafx-8-tutorial/es/part3/
    paging: 3
  - text: "Parte 4: Hojas de estilo CSS"
    link: /library/javafx-8-tutorial/es/part4/
    paging: 4
  - text: "Parte 5: Persistencia de datos con XML"
    link: /library/javafx-8-tutorial/es/part5/
    paging: 5
    active: true
  - text: "Parte 6: Gráficos estadísticos"
    link: /library/javafx-8-tutorial/es/part6/
    paging: 6
  - text: "Parte 7: Despliegue"
    link: /library/javafx-8-tutorial/es/part7/
    paging: 7
- header: "Código fuente"
  body:
  - text: Parte 5 proyecto Eclipse <em>(requiere al menos JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-5.zip
    icon-css: fa fa-fw fa-download
- header: Lenguajes
  languages: true
  body:
  - text: English
    link: /library/javafx-8-tutorial/part5/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part5/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part5/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part5/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/part5/
    icon-css: fa fa-fw fa-globe
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/part5/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/javafx-8-tutorial/fr/part5/
    icon-css: fa fa-fw fa-globe
---

![Screenshot AddressApp Part 5](/assets/library/javafx-8-tutorial/part5/addressapp-part5.png)


## Contenidos en Parte 5

* **Persistencia de datos en XML**
* Utilización de **FileChooser**
* Utilización de **Menu**
* Guardando la ruta al último archivo abierto en las **preferencias de usuario**



*****

Actualmente, los datos de nuestra aplicación de libreta de direcciones reside únicamente en memoria. Cada vez que cerramos la aplicación los datos se pierden. Así pues, ha llegado la hora de pensar en como guardar los datos de forma persistente.


## Guardando preferencias del usuario

Java nos permite guardar cierta información mediante una clase llamada `Preferences`, pensada para guardar las preferencias de usuario de una aplicación. Dependiendo del sistema operativo, estas preferencias son guardadas en un sitio u otro (por ejemplo el registro de Windows).

No podemos usar un archivo de `Preferences` para guardar nuestra libreta de direcciones completa, pero nos sirve para guardar **información de estado muy simple**. Un ejemplo del tipo de cosas que podemos guardar en estas preferencias es **la ruta al último archivo abierto**. Con esta información podemos recuperar el último estado de la aplicación cuando el usuario vuelva a ejecutar la aplicación.

Los siguientes dos métodos se encargan de guardar y recuperar las `Preferences`. Añádelos al final de la clase `MainApp`:


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


## Persistencia de datos mediante XML

### ¿Por qué XML?

Una de las formas más habituales de almacenar datos es mediante una de base de datos. Las bases de datos típicamente contienen algún tipo de datos relacionales (tablas relacionadas mediante índices), mientras que los datos que tenemos que guardar. A este problema se le denomina *desadaptación de impedancias objeto-relacional* ([object-relational impedance mismatch](http://wikipedia.org/wiki/Object-relational_impedance_mismatch)). Cuesta bastante trabajo adaptar objetos a tablas de una base de datos relacional. Aunque existen algunas soluciones para ayudarnos a realizar esta adaptación (ej. [Hibernate](http://www.hibernate.org/), la más popular), todavía cuesta bastante trabajo de configuración. 

Para  nuestro sencillo modelo de datos es mucho más fácil usar XML. Usaremos una librería llamada [JAXB](https://jaxb.java.net/) (**J**ava **A**rchitecture for **X**ML **B**inding). Con apenas unas pocas líneas de código JAXB nos permitirá generar una salida en XML como esta:

##### Ejemplo de salid en XML

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




### Utilización de JAXB

JAXB viene incluido en el JDKm. Eso significa que no necesitamos añadir ninguna librería adicional.

JAXB proporciona dos funcionalidades principales: la capacidad de convertir objectos Java en XML (**marshalling**), y a la inversa, la capacidad de convertir XML en objetos Java (**unmarshalling**).

Para que JAXB sea capaz de hacer la conversión, necesitamos preparar nuestro modelo.


#### Preparando el modelo para JAXB 

Los datos que queremos guardar se hallan en la variable `personData` dentro de la clase `MainApp`. JAXB requiere que la clase raíz (la que contenga a todo el árbol XML) sea anotada anotada con `@XmlRootElement`. Sin embargo `personData` es de clase `ObservableList`, que no se puede utilizar en JAXB. De ahí que necesitemos crear otra clase para contener nuestra lista de personas (`Person`) de cara a ser adaptada a XML por JAXB 

La nueva clase que creamos se llama `PersonListWrapper` y la ponemos en el paquete `ch.makery.address.model`.


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

Fíjate en las dos anotaciones. 

* `@XmlRootElement` define el nombre del elemento raíz del XML.
* `@XmlElement` es un nombre opcional que podemos especificar para el elemento (usado en su representación XML).


#### Leyendo y escribiendo datos con JAXB

Haremos a nuestra clase `MainApp` responsable de leer y escribir los datos XML. Añade la siguiente pareja de métodos al final de la clase `MainApp.java`:


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
        Dialogs.create()
                .title("Error")
                .masthead("Could not load data from file:\n" + file.getPath())
                .showException(e);
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
        Dialogs.create().title("Error")
                .masthead("Could not save data to file:\n" + file.getPath())
                .showException(e);
    }
}
</pre>

Los métodos de escritura (marshalling) y lectura (unmarshalling) ya están listos. Ahora crearemos unas opciones de menú para poder utilizar esos métodos.


## Gestión de acciones de menú

En nuestro `RootLayout.fxml` ya hay un menú, pero todavía no lo hemos utilizado. Antes de añadir acciones al menú crearemos todos los ítems del menú.

Abre ela rchivo `RootLayout.fxml` en Scene Builder y arrastra los ítems de menú necesarios desde la sección *library* a la barra de menús (componente `MenuBar` en la *hierarchy*). Crea los siguientes ítems: **New**, **Open...**, **Save**, **Save As...**, y **Exit**.

![Add Menu Items](/assets/library/javafx-8-tutorial/part5/add-menu-items.png)

Truco: Mediante el uso de la opción *Accelerator* en la vista *Properties* se pueden establecer atajos de teclado para lanzar las acciones asociadas a los ítems del menú.


### Controlador para las acciones de menú: RootLayoutController

Para implementar las acciones del menú necesitaremos una nueva clase de control. Crea una nueva clase `RootLayoutController` dentro de `ch.makery.address.view`. 

Añade el siguiente contenido al controlador recién creado.


##### RootLayoutController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import org.controlsfx.dialog.Dialogs;

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
		Dialogs.create()
	        .title("AddressApp")
	        .masthead("About")
	        .message("Author: Marco Jakob\nWebsite: http://code.makery.ch")
	        .showInformation();
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

Fíjate en los métodos que usan la clase `FileChooser` dentro de `RootLayoutController`. Primero, se crea una nueva instancia de la clase `FileChooser`. A continuación, se le añade un filtro de extensión para que sólo se muestren los archivos terminados en `.xml`. Finalmente, el objeto `FileChooser` se muestra justo encima de la escena principal.

Si el usuario cierra la ventana del `FileChooser`sin escoger un archivo, se devuelve `null`. En otro caso, se obtiene el archivo seleccionado, y se lo podemos pasar al método `loadPersonDataFromFile(...)` o al método `savePersonDataToFile(...)` de la clase `MainApp`. 


### Conectando el FXML con el controlador

1. Abre `RootLayout.fxml` en Scene Builder. En la sección *Controller* selecciona `RootLayoutController` como controlador. 

2. Vuelve a la sección *Hierarchy* y elige un ítem del menú. En el campo **On Action** de la sección *Code* debes tener como opciones todos los métodos disponibles en la clase de control. Elije el que corresponda a cada uno de los ítems del menú.   
![Menu Actions](/assets/library/javafx-8-tutorial/part5/menu-actions.png)

3. Repite el paso 2 para todos y cada uno de ls ítems del menú.

4. Cierra Scene Builder y refresca el proyecto (pulsa **Refresh (F5)** sobre la carpeta raíz de tu proyecto). Esto hará que Eclipse se entere de los cambios realizados en Scene Builder.


### Conectando la clase MainApp y el controlador RootLayoutController

En varios sitios, el controlador `RootLayoutController` necesita una referencia a la clase `MainApp`. Todavía no hemos pasado esa referencia al `RootLayoutController`.

Abre la clase `MainApp` y sustituye el método `initRootLayout()` por el código siguiente:

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

Fíjate en los 2 cambios introducidos: Las líneas que *dan acceso a MainApp" y las últimas tres líneas para *cargar el último archivo abierto*.


### Pruebas

Si pruebas ahora tu aplicación deberías ser capaz de usar los menús para grabar los datos de los contactos en un archivo XML. 

Si abres el archivo XML resultante en un editor, notarás que la fecha de nacimiento no se guarda correctamente, aparece una etiqueta `<birthday/>` vacía. La razón es que JAXB no sabe como convertir `LocalDate` a XML. Debemos proporcionar un adaptador a medida para realizar esta conversión.

Dentro de `ch.makery.address.util` crea una nueva clase denominada `LocalDateAdapter` con el contenido siguiente:

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

A continuación abre la clase `Person` y añade la siguiente anotación al método `getBirthday()`:

<pre class="prettyprint lang-java">
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public LocalDate getBirthday() {
    return birthday.get();
}
</pre>

Ahora prueba a guardar los datos de nuevo y abre el archivo XML otra vez. Debería abrir automáticamente el último archivo abierto durante la ejecución previa.



## Como funciona

Ahora veamos como funciona todo junto

1. La aplicación se inicia con la ejecución del método `main(...)` de la clase `MainApp`.
2. El constructor `public MainApp()` es invocado y añade algunos datos de ejemplo.
3. El método `start(...)` de la clase `MainApp` es invocado, el cual a su vez invoca a `initRootLayout()` para inicializar la vista principal utilizando el archivo `RootLayout.fxml`. El archivo FSML tiene información sobre qué controlador utilizar y enlaza la vista con su controlador `RootLayoutController`. 
4. `MainApp` obtiene el controlador `RootLayoutController` del cargador FXML y le pasa a ese controlador una referencia a sí mismo. con esta referencia el controlador podrá después acceder a los métodos (públicos) de `MainApp`.
5. Al final del método `initRootLayout()` se intenta obtener el *último archivo de direcciones abierto* desde las `Preferences`. Si existe esa información en `Preferences` entonces se leen los datos del XML. Estos datos sobreescribirán los datos de ejemplo generados en el constructor. 


### ¿Qué es lo siguiente?

En [Tutorial Parte 6](/library/javafx-8-tutorial/es/part6/) añadiremos un gráfico con estadísticas de las fechas de nacimiento de la lista de personas.


##### Otros artículos que podrían resultarte de interés

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
