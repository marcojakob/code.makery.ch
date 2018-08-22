---
layout: article
title: "JavaFX 8 Tutorial - Part 5: Storing Data as XML"
date: 2014-04-25 01:00
updated: 2015-05-16
slug: javafx-tutorial/tr/part5
canonical: /library/javafx-tutorial/part5/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-tr-part5.md
description: "Save data as XML with JAXB. Learn how to use the JavaFX FileChooser and the JavaFX Menu."
image: /assets/library/javafx-tutorial/part5/addressapp-part5.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /library/javafx-tutorial/tr/
    paging: Intro
  - text: "Part 1: Scene Builder"
    link: /library/javafx-tutorial/tr/part1/
    paging: 1
  - text: "Part 2: Model and TableView"
    link: /library/javafx-tutorial/tr/part2/
    paging: 2
  - text: "Part 3: Interacting with the User"
    link: /library/javafx-tutorial/tr/part3/
    paging: 3
  - text: "Part 4: CSS Styling"
    link: /library/javafx-tutorial/tr/part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /library/javafx-tutorial/tr/part5/
    paging: 5
    active: true
  - text: "Part 6: Statistics Chart"
    link: /library/javafx-tutorial/tr/part6/
    paging: 6
  - text: "Part 7: Deployment"
    link: /library/javafx-tutorial/tr/part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: Part 5 as Eclipse Project <em>(requires at least JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-5.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Languages
  collection: library
  item: javafx-tutorial
  part: part5
  active: tr
---

<div class="alert alert-warning">
  <i class="fa fa-language"></i> This page needs a Turkish translation. If you'd like to help out please read <a href="/library/how-to-contribute/" class="alert-link">how to contribute</a>.
</div>

![Screenshot AddressApp Part 5](/assets/library/javafx-tutorial/part5/addressapp-part5.png)


## Topics in Part 5

* **Persisting data as XML**
* Using the JavaFX **FileChooser**
* Using the JavaFX **Menu**
* Saving the last opened file path in **user preferences**



*****

At the moment our address application's data only resides in memory. Every time we close the application, the data is lost. So it's about time to start thinking about persistently storing data.


## Saving User Preferences

Java allows us to save some application state using a class called `Preferences`. Depending on the operating system, the `Preferences` are saved in different places (e.g. the registry file in Windows).

We won't be able to use `Preferences` to store our entire address book. But it allows us to **save some simple application state**. One such thing is the **path to the last opened file**. With this information we could load the last application state whenever the user restarts the application.

The following two methods take care of saving and retrieving Preferences. Add them to the end of your `MainApp` class:


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


## Persisting Data as XML

### Why XML?

One of the most common ways to persist data is using a database. Databases usually contain some kind of relational data (like tables) while the data we need to save are objects. This is called the [object-relational impedance mismatch](http://wikipedia.org/wiki/Object-relational_impedance_mismatch). It is quite some work to match objects to relational database tables. There are some frameworks that help with the matching (e.g. [Hibernate](http://www.hibernate.org/), the most popular one) but it still requires quite some work to set up.

For our simple data model it's much easier to use XML. We'll use a library called [JAXB](https://jaxb.java.net/) (**J**ava **A**rchitecture for **X**ML **B**inding). With just a few lines of code JAXB will allow us to generate XML output like this:

##### Example xml output

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




### Using JAXB

JAXB is already included in the JDK. That means we don't need to include any additional library.

JAXB provides two main features: the ability to **marshal** Java objects into XML and to **unmarshal** XML back into Java objects.

For JAXB to be able to do the conversion, we need to prepare our model.


#### Preparing the Model Class for JAXB 

Our data that we want to save resides in the `personData` variable inside our `MainApp` class. JAXB requires the top most class to be annotated with `@XmlRootElement`. `personData` is of class `ObservableList` and we can't put any annotations to `ObservableList`. So we need to create another class that is only used to hold our list of `Persons` for saving to XML. 

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

![Add Menu Items](/assets/library/javafx-tutorial/part5/add-menu-items.png)

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
![Menu Actions](/assets/library/javafx-tutorial/part5/menu-actions.png)

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

In Tutorial [Part 6](/library/javafx-tutorial/tr/part6/) we'll add a birthday statistics chart.


##### Some other articles you might find interesting

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
