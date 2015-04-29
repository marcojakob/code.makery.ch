---
layout: article
title: "Tutoriel JavaFX 8 - partie 5 : stockage de données en XML"
date: 2014-04-25 01:00
updated: 2015-04-15 00:00
slug: javafx-8-tutorial/fr/part5
canonical: /library/javafx-8-tutorial/part5/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-fr-part5.md
description: "Enregistrer les données en XML avec JAXB. Apprenez comment utiliser le FileChooser et le menu de JavaFX."
image: /assets/library/javafx-8-tutorial/part5/addressapp-part5.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articles dans ces séries"
  body:
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
    active: true
  - text: "Partie 6 : statistiques graphiques"
    link: /library/javafx-8-tutorial/fr/part6/
    paging: 6
  - text: "Partie 7 : déploiement"
    link: /library/javafx-8-tutorial/fr/part7/
    paging: 7
- header: "Téléchargez les sources"
  body:
  - text: Projet Eclipse relatif à la partie 5 <em>(JDK 8u40 requis au minimum)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-5.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Langues
  collection: library
  item: javafx-8-tutorial
  part: part5
  active: fr
---

<div class="alert alert-warning">
  <i class="fa fa-language"></i> This page needs a French translation. If you'd like to help out please read <a href="/library/how-to-contribute/" class="alert-link">how to contribute</a>.
</div>

![Screenshot AddressApp Part 5](/assets/library/javafx-8-tutorial/part5/addressapp-part5.png)


## Sujets dans la partie 5

* **Données persistantes en XML**
* Utiliser le **FileChooser** de JavaFX
* Utiliser le **Menu** de JavaFX
* Enregistrer le chemin du dernier fichier utilisé dans les **préférences utilisateur**



*****

Pour l'instant, les données de notre application carnet d'adresses résident seulement dans la mémoire vive. Les données sont perdues à chaque fois que nous refermons l'application ! Il est temps de penser à sauvegarder de manière persistante. 


## Enregistrer les préférences utilisateur

Java nous permet d'enregistrer l'état de l'application par le biais d'une classe nommée `Preferences`. Les `Préférences` sont enregistrées dans des endroits différents selon l'OS (p. ex. la base de registre sous Windows). 

Nous ne pourrons pas utiliser les `Préférences` pour sauvegarder tout le carnet d'adresses. Mais elles vont nous permettre d'**enregistrer des états simples de l'application**. Le **chemin du dernier fichier ouvert** fait partie de tout cela. Avec cette information nous pourrons recharger le dernier état de l'application quand l'utilisateur redémarrera l'applicaton. 

Les deux méthodes ci-dessous s'occupent d'enregistrer et de lire les préférences. Ajoutez-les à la fin de votre classe `MainApp` ! 


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


## Données persistantes en XML

### Pourquoi XML?

Une des façons habituelles de rendre les données persistantes consiste à utiliser une base de données. Les bases de données contiennent des données typées relationnel (dans les tables) or les données que nous voulons enregistrer sont des objets. C'est appelé l'[object-relational impedance mismatch](http://wikipedia.org/wiki/Object-relational_impedance_mismatch). C'est beaucoup de travail de faire correspondre les objets aux tables des bases de données relationelles. Il existe des frameworks qui facilitent la mise en correspondance (p. ex. [Hibernate](http://www.hibernate.org/), le plus populaire) mais il requiert tout de même du travail de mise en place. 

Pour notre modèle de données basique, c'est plus facile d'utiliser le XML. Nous utiliserons une librairie nommée [JAXB](https://jaxb.java.net/) (**J**ava **A**rchitecture for **X**ML **B**inding). JAXB va nous permettre de générer du XML avec quelques lignes de code seulement comme ceci :   

##### Exemple de texte formatté en xml

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




### Utiliser JAXB

JAXB est déjà inclu dans le JDK. Cela signifie que nous n'avons pas besoin d'inclure une librairie supplémentaire. 

JAXB fournit deux fonctionalités principales : la capacité de faire correspondre (**marshal**) des objets Java en code XML et vice et versa (**unmarshal**). 

Pour que JAXB soit capable de faire la conversion, nous devons préparer notre modèle. 


#### Préparer la classe modèle pour JAXB 

Nos données que nous voulons enregistrer résident dans la variable `personData` dans notre classe `MainApp`. JAXB requiert que nous annotions la classe racine avec `@XmlRootElement`. `personData` est une classe de type `ObservableList` et nous ne pouvons pas ajouter d'annotations à `ObservableList`. Nous devons donc créer une autre classe qui est utilisée seulement pour contenir notre liste de `Persons` en vue de l'enregistrer en XML. 

The new class we create is called `PersonListWrapper` and is put into the `ch.makery.address.model` package.


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

Notice the two annotations: 

* `@XmlRootElement` defines the name of the root element.
* `@XmlElement` is an optional name we can specify for the element.


#### Reading and Writing Data with JAXB

We'll make our `MainApp` class responsible for reading and writing the person data. Add the following two methods to the end of `MainApp.java`:


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

The marshalling/unmarshalling is ready. Let's create the save/load menu to actually be able to use it.


## Handling Menu Actions

In our `RootLayout.fxml` there is already a menu, but we haven't used it yet. Before we add action to the menu we'll first create all menu items.

Open the `RootLayout.fxml` file in Scene Builder and drag the necessary menu items from the *library* group to the `MenuBar` bar in the *hierarchy* group. Create a **New**, **Open...**, **Save**, **Save As...**, and **Exit** menu item.

![Add Menu Items](/assets/library/javafx-8-tutorial/part5/add-menu-items.png)

Hint: Using the *Accelerator* setting under the *Properties* group you can set shortcut keys to menu items.


### The RootLayoutController

For handling menu actions we'll need a new controller class. Create a class `RootLayoutController` inside the controller package `ch.makery.address.view`. 

Add the following content to the controller:


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

Take note of the methods that use the `FileChooser` class inside `RootLayoutController` above. First, a new object of the class `FileChooser` is created. Then, an extension filter is added so that only files ending in `.xml` are displayed. Finally, the file chooser is displayed on top of the primary stage.

If the user closes the dialog without choosing a file, `null` is returned. Otherwise, we get the selected file and we can pass it to the `loadPersonDataFromFile(...)` or `savePersonDataToFile(...)` method of `MainApp`. 


### Connecting the fxml View to the Controller

1. Open `RootLayout.fxml` in Scene Builder. In the *Controller* group select the `RootLayoutController` as Controller class. 

2. Go back to the *Hierarchy* group and select a menu item. In the *Code* group under **On Action** you should see a choice of all the available controller methods. Choose the corresponding method for each menu item.   
![Menu Actions](/assets/library/javafx-8-tutorial/part5/menu-actions.png)

3. Repeat the steps for every menu item.

4. Close Scene Builder and hit **Refresh (F5)** on your project's root folder. This will make Eclipse aware of the changes you made in Scene Builder.


### Connecting the MainApp and RootLayoutController

In several places, the `RootLayoutController` needs a reference back to the `MainApp`. We haven't passed the reference to the `RootLayoutController` yet.

Open the `MainApp` class and replace the `initRootLayout()` method with the following code:

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

Notice the two changes: The lines that *give the controller access to the main app* and the last three lines to *load the last opened person file*.


### Testing

Doing a test drive of your application you should be able to use the menus to save the person data to a file. 

When you open the `xml` file in an editor you will notice that the birthday is not saved correctly, it's an empty `<birthday/>` tag. The reason is that JAXB does not know how to convert the `LocalDate` to XML. We must provide a custom `LocalDateAdapter` to define this conversion.

Create a new class inside `ch.makery.address.util` called `LocalDateAdapter` with the following content:

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

Then open `Person.java` and add the following annotation to the `getBirthday()` method:

<pre class="prettyprint lang-java">
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public LocalDate getBirthday() {
    return birthday.get();
}
</pre>

Now, test again. Try saving and loading the xml file. After a restart, it should automatically load the last used file.



## How It Works


Let's see how it all works together:

1. The application is started using the `main(...)` method inside `MainApp`.
2. The constructor `public MainApp()` is called and adds some sample data.
3. `MainApp`s `start(...)` method is called and calls `initRootLayout()` to initialize the root layout from `RootLayout.fxml`. The fxml file has the information about which controller to use and links the view to its `RootLayoutController`. 
4. The `MainApp` gets the `RootLayoutController` from the fxml loader and passes a reference to itself to the controller. With this reference the controller can later access the (public) methods of `MainApp`.
5. At the end of the `initRootLayout()` method we try to get the *last opened person file* from `Preferences`. If the `Preferences` know about such an XML file, we'll load the data from this XML file. This will apparently overwrite the sample data from the constructor. 


### What's Next?

In Tutorial [Part 6](/library/javafx-8-tutorial/fr/part6/) we'll add a birthday statistics chart.


##### Some other articles you might find interesting

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
