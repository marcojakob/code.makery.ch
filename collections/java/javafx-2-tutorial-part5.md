---
layout: article
title: "JavaFX 2 Tutorial - Part 5: Storing Data as XML"
date: 2012-11-27 22:00
updated: 2013-02-08 00:00
github: https://github.com/marcojakob/code.makery.ch/blob/master/collections/java/javafx-2-tutorial-part5.md
description: "Save data as XML with XStream. Learn how to use the JavaFX FileChooser and the JavaFX Menu."
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2012/11/27/javafx-tutorial-addressapp-5/
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /java/javafx-2-tutorial-intro
    paging: Intro
  - text: "Part 1: Scene Builder"
    link: /java/javafx-2-tutorial-part1/
    paging: 1
  - text: "Part 2: Model and TableView"
    link: /java/javafx-2-tutorial-part2/
    paging: 2
  - text: "Part 3: Interacting with the User"
    link: /java/javafx-2-tutorial-part3/
    paging: 3
  - text: "Part 4: CSS Styling"
    link: /java/javafx-2-tutorial-part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /java/javafx-2-tutorial-part5/
    paging: 5
    active: true
  - text: "Part 6: Statistics Chart"
    link: /java/javafx-2-tutorial-part6/
    paging: 6
  - text: "Part 7: Deployment with e(fx)clipse"
    link: /java/javafx-2-tutorial-part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: Source of Part 5 (Eclipse Project)
    link: /assets/java/javafx-2-tutorial-part5/addressapp-part-5.zip
    icon-css: fa fa-fw fa-download
---

<div class="alert alert-danger">
  &rarr; UPDATED VERSION for JDK 8 available: <a href="/java/javafx-8-tutorial-part5/" class="alert-link">JavaFX 8 Tutorial</a>
</div>

![Screenshot AddressApp Part 5](/assets/java/javafx-2-tutorial-part5/addressapp01.png)


## Topics in Part 5

* **Persisting data as XML**
* Using the JavaFX **FileChooser**
* Using the JavaFX **Menu**
* Saving the last opened file path in **user preferences**

* * *

## Saving User Preferences

Java allows us to save some application state using a class called `Preferences`. Depending on the operating system, the `Preferences` are saved in different places (e.g. the registry file in Windows).

We won't be able to use `Preferences` to store our entire address book. But it allows us to save some simple application state. One such thing is the path to the *last opened file*. With this information we could load the last application state whenever the user restarts the application.

The following two methods take care of saving and retrieving Preferences. Add them to the end of your `MainApp` class:

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
 * Sets the file path of the currently loaded file.
 * The path is persisted in the OS specific registry.
 * 
 * @param file the file or null to remove the path
 */
public void setPersonFilePath(File file) {
  Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
  if (file != null) {
    prefs.put("filePath", file.getPath());
    
    // Update the stage title
    primaryStage.setTitle("AddressApp - " + file.getName());
  } else {
    prefs.remove("filePath");
    
    // Update the stage title
    primaryStage.setTitle("AddressApp");
  }
}
</pre>

* * *

## Persisting Data as XML

At the moment our address application's data only resides in memory. Every time we close the application, the data is lost. So it's about time to start thinking persistently storing data.


### Why XML?

One of the most common ways to persist data is using a database. Databases usually contain some kind of relational data (like tables) while the data we need to save are objects. This is called the [object-relational impedance mismatch](http://wikipedia.org/wiki/Object-relational_impedance_mismatch). It is quite some work to match objects to relational database tables. There are some of frameworks that help with the matching (e.g. [Hibernate](http://www.hibernate.org/), the most popular one) but it still requires quite some work to set up.

For our simple data model it's much easier to use XML. We'll use a library called [XStream](http://xstream.codehaus.org/). With just a few lines of code this will allow us to generate XML output like this:

##### sample.xml

<pre class="prettyprint lang-xml">
&lt;list&gt;
  &lt;person&gt;
    &lt;firstName&gt;Hans&lt;/firstName&gt;
    &lt;lastName&gt;Muster&lt;/lastName&gt;
    &lt;street&gt;some street&lt;/street&gt;
    &lt;postalCode&gt;1234&lt;/postalCode&gt;
    &lt;city&gt;some city&lt;/city&gt;
    &lt;birthday&gt;
      &lt;time&gt;1354035227734&lt;/time&gt;
      &lt;timezone&gt;Europe/Berlin&lt;/timezone&gt;
    &lt;/birthday&gt;
  &lt;/person&gt;
  &lt;person&gt;
    &lt;firstName&gt;Anna&lt;/firstName&gt;
    &lt;lastName&gt;Best&lt;/lastName&gt;
    &lt;street&gt;some street&lt;/street&gt;
    &lt;postalCode&gt;1234&lt;/postalCode&gt;
    &lt;city&gt;some city&lt;/city&gt;
    &lt;birthday&gt;
      &lt;time&gt;1354035227734&lt;/time&gt;
      &lt;timezone&gt;Europe/Berlin&lt;/timezone&gt;
    &lt;/birthday&gt;
  &lt;/person&gt;
&lt;/list&gt;
</pre>


### Reading and Writing Files

Since Java 7 there are some convenient classes to deal with reading and writing files. For a detailed tutorial see Oracle's [File I/O Tutorial](http://docs.oracle.com/javase/tutorial/essential/io/fileio.html).

Since we might need to read/write files in different places of our application we'll create a handy `FileUtil` helper class. This class provides one static method for reading from a file and one for writing to a file. Copy the following file into the `ch.makery.address.util` package:


##### FileUtil.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * Helper class for reading and writing files.
 */
public class FileUtil {

	/**
	 * The character set. UTF-8 works good for windows, mac and Umlaute.
	 */
	private static final Charset CHARSET = Charset.forName("UTF-8");
	
	/**
	 * Reads the specified file and returns the content as a String.
	 * 
	 * @param file
	 * @return
	 * @throws IOException thrown if an I/O error occurs opening the file
	 */
	public static String readFile(File file) throws IOException {
		StringBuffer stringBuffer = new StringBuffer();
		
		BufferedReader reader = Files.newBufferedReader(file.toPath(), CHARSET);
		
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	        stringBuffer.append(line);
	    }
	    
	    reader.close();
	    
	    return stringBuffer.toString();
	}
	
	/**
	 * Saves the content String to the specified file.
	 * 
	 * @param content
	 * @param file
	 * @throws IOException thrown if an I/O error occurs opening or creating the file
	 */
	public static void saveFile(String content, File file) throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(file.toPath(), CHARSET);
		writer.write(content, 0, content.length());
		writer.close();
	}
}
</pre>


### Using XStream

To use XStream we need three libraries. Add the following libraries to the project's *lib* folder and add them to the build path (right click on libraries).

* [xstream-1.4.3.jar](/assets/java/javafx-2-tutorial-part5/xstream-1.4.3.jar) - XStream main library
* [xmlpull-1.1.3.1.jar](/assets/java/javafx-2-tutorial-part5/xmlpull-1.1.3.1.jar) - XmlPull to detect available parsers 
* [xpp3_min-1.1.4c.jar](/assets/java/javafx-2-tutorial-part5/xpp3_min-1.1.4c.jar) - Xpp3, a fast pull parser

You can also download the three libraries from the [XStream download page](http://xstream.codehaus.org/download.html).

We'll make our `MainApp` class responsible for reading and writing the person data. Add the following two methods to the end of `MainApp.java`:


<pre class="prettyprint lang-java">
/**
 * Loads person data from the specified file. The current person data will
 * be replaced.
 * 
 * @param file
 */
@SuppressWarnings("unchecked")
public void loadPersonDataFromFile(File file) {
  XStream xstream = new XStream();
  xstream.alias("person", Person.class);
  
  try {
    String xml = FileUtil.readFile(file);
    
    ArrayList&lt;Person&gt; personList = (ArrayList&lt;Person&gt;) xstream.fromXML(xml);
    
    personData.clear();
    personData.addAll(personList);
    
    setPersonFilePath(file);
  } catch (Exception e) { // catches ANY exception
    Dialogs.showErrorDialog(primaryStage,
        "Could not load data from file:\n" + file.getPath(),
        "Could not load data", "Error", e);
  }
}

/**
 * Saves the current person data to the specified file.
 * 
 * @param file
 */
public void savePersonDataToFile(File file) {
  XStream xstream = new XStream();
  xstream.alias("person", Person.class);

  // Convert ObservableList to a normal ArrayList
  ArrayList&lt;Person&gt; personList = new ArrayList&lt;&gt;(personData);
  
  String xml = xstream.toXML(personList);
  try {
    FileUtil.saveFile(xml, file);
    
    setPersonFilePath(file);
  } catch (Exception e) { // catches ANY exception
    Dialogs.showErrorDialog(primaryStage,
        "Could not save data to file:\n" + file.getPath(),
        "Could not save data", "Error", e);
  }
}
</pre>

The save method uses `xstream.toXML(...)` to convert the list of `Person` objects into an XML representation. The load method uses `xstream.fromXML(...)` to convert the xml data back to a list of `Person`s. 

If anything goes wrong, an error dialog is presented to the user.

* * *

## Handling Menu Actions

In our `RootLayout.fxml` there is already a menu, but we haven't used it yet. Before we add action to the menu we'll first create all menu items.

Open the `RootLayout.fxml` file in Scene Builder and drag the necessary menu items from the *library view* to the menu bar in the *hierarchy view*. Create a **New**, **Open...**, **Save**, **Save As...**, and **Exit** menu item. You may also use separators between some items.

![RootLayout](/assets/java/javafx-2-tutorial-part5/addressapp02.png)

Hint: Using the *Accelerator* setting under properties you can set shortcut keys to menu items.


### The RootLayoutController

For handling menu actions we'll need a new controller class. Create a class `RootLayoutController` inside the controller package `ch.makery.address`. 

Add the following content to the controller:

##### RootLayoutController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.stage.FileChooser;

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
		Dialogs.showInformationDialog(mainApp.getPrimaryStage(), "Author: Marco Jakob\nWebsite: http://code.makery.ch", "AddressApp", "About");
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

The controller contains an `@FXML` method for each menu item.


#### FileChooser

Take note of the methods that use the `FileChooser` class inside `RootLayoutController` above. First, a new object of the class `FileChooser` is created. Then, an extension filter is added so that only files ending in `.xml` are displayed. Finally, the file chooser is displayed on top of the primary stage.

If the user closes the dialog without choosing a file, `null` is returned. Otherwise, we get the selected file and we can pass it to the `loadPersonDataFromFile(...)` or `savePersonDataToFile(...)` method of `MainApp`. 


### Connecting the fxml View to the Controller

1. Open `RootLayout.fxml` in Scene Builder. Select the root `BorderPane`. In the Code view select the `RootLayoutController` as Controller class. 

2. Select each menu item in the Hierarchy view. In the Code view under *On Action* you should see a choice of all the `@FXML` methods of the controller. Choose the corresponding method for each menu item.   
![On Action](/assets/java/javafx-2-tutorial-part5/addressapp03.png)

3. Close Scene Builder and hit **Refresh (F5)** on your project's root folder. This will make Eclipse aware of the changes you made in Scene Builder.


### Connecting the MainApp and RootLayoutController

In several places, the `RootLayoutController` needs a reference back to the `MainApp`. We haven't passed the reference to the `RootLayoutController` yet.

So, open the `MainApp` class and replace the `start(...)` method with the following code:

<pre class="prettyprint lang-java">
@Override
public void start(Stage primaryStage) {
  this.primaryStage = primaryStage;
  this.primaryStage.setTitle("AddressApp");
  this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
  
  try {
    // Load the root layout from the fxml file
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/RootLayout.fxml"));
    rootLayout = (BorderPane) loader.load();
    Scene scene = new Scene(rootLayout);
    primaryStage.setScene(scene);
    
    // Give the controller access to the main app
    RootLayoutController controller = loader.getController();
    controller.setMainApp(this);
    
    primaryStage.show();
  } catch (IOException e) {
    // Exception gets thrown if the fxml file could not be loaded
    e.printStackTrace();
  }
  
  showPersonOverview();
  
  // Try to load last opened person file
  File file = getPersonFilePath();
  if (file != null) {
    loadPersonDataFromFile(file);
  }
}
</pre>

Notice the two changes: The lines that *give the controller access to the main app* and the last three lines to *load the last opened person file*.

* * *

## How It Works

Doing a test drive of your application you should be able to use the menus to save the person data to a file and load it again. After a restart, it should automatically load the last used file.

Let's see how it all works together:

1. The application is started using the `main(...)` method inside `MainApp`.
2. The constructor `public MainApp()` is called and adds some sample data.
3. `MainApp`s `start(...)` method is called and initializes the root layout from `RootLayout.fxml`. The fxml file has the information about which controller to use and links the view to its `RootLayoutController`. 
4. The `MainApp` gets the `RootLayoutController` from the fxml loader and passes a reference to itself to the controller. With this reference the controller can later access the (public) methods of `MainApp`.
5. At the end of the `start(...)` method we try to get the *last opened person file* from `Preferences`. If the `Preferences` know about such an XML file, we'll load the data from this XML file. This will apparently overwrite the sample data from the constructor. 


### What's Next?

In Tutorial [Part 6](/java/javafx-2-tutorial-part6/) we'll add a birthday statistics chart.

