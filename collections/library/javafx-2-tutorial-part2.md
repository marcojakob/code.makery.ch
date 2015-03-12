---
layout: article
title: "JavaFX 2 Tutorial - Part 2: Model and TableView"
date: 2012-11-17 00:30
updated: 2013-02-08 00:00
slug: javafx-2-tutorial/part2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-2-tutorial-part2.md
description: "Use a JavaFX TableView to display an ObservableList of Persons."
prettify: true
published: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2012/11/17/javafx-tutorial-addressapp-2/
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /library/javafx-2-tutorial/
    paging: Intro
  - text: "Part 1: Scene Builder"
    link: /library/javafx-2-tutorial/part1/
    paging: 1
  - text: "Part 2: Model and TableView"
    link: /library/javafx-2-tutorial/part2/
    paging: 2
    active: true
  - text: "Part 3: Interacting with the User"
    link: /library/javafx-2-tutorial/part3/
    paging: 3
  - text: "Part 4: CSS Styling"
    link: /library/javafx-2-tutorial/part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /library/javafx-2-tutorial/part5/
    paging: 5
  - text: "Part 6: Statistics Chart"
    link: /library/javafx-2-tutorial/part6/
    paging: 6
  - text: "Part 7: Deployment with e(fx)clipse"
    link: /library/javafx-2-tutorial/part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: Source of Part 2 (Eclipse Project)
    link: https://github.com/marcojakob/tutorial-javafx-2/releases/download/v1.0/addressapp-part-2.zip
    icon-css: fa fa-fw fa-download
---

<div class="alert alert-danger">
  &rarr; UPDATED VERSION for Java 8 available: <a href="/library/javafx-8-tutorial/part2/" class="alert-link">JavaFX 8 Tutorial</a>
</div>

![Screenshot AddressApp Part 2](/assets/library/javafx-2-tutorial/part2/addressapp01.png)

## Topics in Part 2

* Creating a **model** class
* Using the model class in an **ObservableList**
* Show data in the **TableView** using **Controllers**

* * * 

## Create the Model Class

We need a model class to hold information about the people in our address book. Add a new class to the model package (`ch.makery.address.model`) called `Person`. This makes sense, since we want to manage people and their addresses. The `Person` class will have a few instance variables for the name, address and birthday. Add the following code to the class:


##### Person.java

<pre class="prettyprint lang-java">
package ch.makery.address.model;

import java.util.Calendar;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person {

	private String firstName;
	private String lastName;
	private String street;
	private int postalCode;
	private String city;
	private Calendar birthday;

	/**
	 * Default constructor.
	 */
	public Person() {
	}
	
	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
		// some initial dummy data
		this.street = "some street";
		this.postalCode = 1234;
		this.city = "some city";
		this.birthday = Calendar.getInstance();
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}
}
</pre>

* * *

## A List of Persons

The main Data that our application manages is a bunch of persons. Let's create a list for `Person` objects inside the `MainApp` class. All other controller classes will later get access to the list inside the `MainApp`. 


### ObservableList

We are working with JavaFX view classes that always need to be informed about any changes made to the list of persons. This is important, since otherwise the view would not be in sync with the data. For this purpose, JavaFX introduces some new [Collection classes](http://docs.oracle.com/javafx/2/collections/jfxpub-collections.htm). 

From those collections, we need the `ObservableList`. To create a new `ObservableList`, add the following code at the beginning of the `MainApp` class. We'll also add a constructor that creates some sample data and a public getter method:


##### MainApp.java

<pre class="prettyprint lang-java">
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

  // ...

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
  
// ...
</pre>

* * *

## The PersonOverviewController ##

Now let's finally view some data in our table.

1. Create a normal class inside the controller package called `PersonOverviewController.java`.
2. We'll add some instance variables that give us access to the table and the labels inside the view. The fields and some methods have a special `@FXML` annotation. This is necessary for the fxml file to have access to those variables. After we have everything set up in the fxml file, the application will automatically fill the variables when the fxml file is loaded. So let's add the following code:

**Note: Remember to always use the javafx imports (not awt or swing)!**


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
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
	
	// Reference to the main application
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
    // Initialize the person table
		firstNameColumn.setCellValueFactory(new PropertyValueFactory&lt;Person, String&gt;("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory&lt;Person, String&gt;("lastName"));
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

Now this code will need some explanation:

* All fields and methods where the fxml file needs access must be annotated with `@FXML`. Actually, only if they are private, but it's better to have them private and mark them with the annotation!.
* The `initialize()` method is automatically called after the fxml file has been loaded. At this time, all the FXML fields should have been initialized already.
* The `PropertyValueFactory` that we set on the table colums are used to determine which field inside the `Person` objects should be used for the particular column.
* The `setMainApp(...)` method must be called by the `MainApp` class. This gives us a way to access the `MainApp` object and get the list of data and other things. In fact, let's do that call right now. Replace the `showPersonOverview()` method with the following. It contains two additional lines:


##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * Shows the person overview scene.
 */
public void showPersonOverview() {
    try {
        // Load the fxml file and set into the center of the main layout
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/PersonOverview.fxml"));
        AnchorPane overviewPage = (AnchorPane) loader.load();
        rootLayout.setCenter(overviewPage);

        // Give the controller access to the main app
        PersonOverviewController controller = loader.getController();
        controller.setMainApp(this);

    } catch (IOException e) {
        // Exception gets thrown if the fxml file could not be loaded
        e.printStackTrace();
    }
}
</pre>

* * *

## Hook the View to the Controller

We're almost there! But one little thing is missing: We haven't told our `PersonOverview.fxml` file yet, which controller to use and which element should match to which field inside the controller.

1. Open `PersonOverview.fxml` with the Scene Builder.
2. Select the topmost *AnchorPane* in the Hierarchy.
3. Open *Code* on the right side and select the `PersonOverviewController` as **controller class**.   
![Set Controller Class](/assets/library/javafx-2-tutorial/part2/addressapp02.png)

4. Select the TableView and choose under Properties the `personTable` field as **fx:id**.   
![Set fx:id](/assets/library/javafx-2-tutorial/part2/addressapp03.png)

5. Do the same for the columns and select `firstNameColumn` and `lastNameColumn` respectively.
6. For each label in the second column, choose the corresponding **fx:id**.
7. Important: Go back to Eclipse and refresh the entire AddressApp project (F5). This is necessary because Eclipse sometimes doesn't know about changes that were made inside the Scene Builder.

* * *

## Start the Application

When you start your application now, you should see something like the screenshot at the beginning of this blog post.   

Congratulations!


### What's Next?

In [Tutorial Part 3](/library/javafx-2-tutorial/part3/) we will add more functionality like adding, deleting and editing Persons.