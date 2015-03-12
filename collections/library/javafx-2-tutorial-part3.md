---
layout: article
title: "JavaFX 2 Tutorial - Part 3: Interacting with the User"
date: 2012-11-20 17:30
updated: 2013-02-08 00:00
slug: javafx-2-tutorial/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-2-tutorial/part3.md
description: "React to selection changes in the JavaFX TableView. Add, edit and remove items from the table and validate user input."
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2012/11/20/javafx-tutorial-addressapp-3/
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
  - text: "Part 3: Interacting with the User"
    link: /library/javafx-2-tutorial/part3/
    paging: 3
    active: true
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
  - text: Source of Part 3 (Eclipse Project)
    link: https://github.com/marcojakob/tutorial-javafx-2/releases/download/v1.0/addressapp-part-3.zip
    icon-css: fa fa-fw fa-download
---

<div class="alert alert-danger">
  &rarr; UPDATED VERSION for Java 8 available: <a href="/library/javafx-8-tutorial/part3/" class="alert-link">JavaFX 8 Tutorial</a>
</div>

![Screenshot AddressApp Part 3](/assets/library/javafx-2-tutorial/part3/addressapp01.png)


## Topics in Part 3

* **React to selection changes** in the table.
* Add functionality to the **add**, **edit**, and **remove** buttons.
* Create a custom **popup dialog** to edit a person.
* **Validating user input**.

* * * 

## React to Table Selections

Obviousely, we haven't used the right side of our application, yet. The idea is when the user selects a person in the table, the details about that person should be displayed on the right side.

First, let's add a new method inside `PersonOverviewController` that helps us fill the labels with the data from a single `Person`.

Create a method called `showPersonDetails(Person person)`. Go trough all the labels and set the text using `setText(...)` with details from the person. If `null` is passed as parameter, all labels should be cleared.


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Fills all text fields to show details about the person.
 * If the specified person is null, all text fields are cleared.
 * 
 * @param person the person or null
 */
private void showPersonDetails(Person person) {

// use setText(...) on all labels with info from the person object
// use setText("") on all labels if the person is null
}
</pre>


### Convert the Birthday Date to a String

If you've implemented the method above, you will have noticed that we need a way to convert the `Calendar` from the birthday field to a String. In a `Label` we can only display Strings.

We will use the conversion from `Calendar` and `String` (and vice versa) in several places. It's good practice to create a helper class with `static` methods for this. We'll call it `CalendarUtil` and place it in a seperate package called `ch.makery.address.util`:


##### CalendarUtil.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Helper functions for handling dates.
 */
public class CalendarUtil {

	/**
	 * Default date format in the form 2013-03-18.
	 */
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Returns the given date as a well formatted string. The above defined date
	 * format is used.
	 * 
	 * @param calendar date to be returned as a string
	 * @return formatted string
	 */
	public static String format(Calendar calendar) {
		if (calendar == null) {
			return null;
		}
		return DATE_FORMAT.format(calendar.getTime());
	}

	/**
	 * Converts a String in the format "yyyy-MM-dd" to a Calendar object.
	 * 
	 * Returns null if the String could not be converted.
	 * 
	 * @param dateString the date as String
	 * @return the calendar object or null if it could not be converted
	 */
	public static Calendar parse(String dateString) {
		Calendar result = Calendar.getInstance();
		try {
			result.setTime(DATE_FORMAT.parse(dateString));
			return result;
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * Checks the String whether it is a valid date.
	 * 
	 * @param dateString
	 * @return true if the String is a valid date
	 */
	public static boolean validString(String dateString) {
		try {
			DATE_FORMAT.parse(dateString);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
</pre>


Note that you can change the format of the date by changing the constant `DATE_FORMAT`. For all possible formats see [`SimpleDateFormat`](http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html) in the Java API.


### Listen for Table Selection Changes

To get informed when the user selects a person in the person table, we need to *listen for changes*.

If you're not familiar with the concept of *anonymous classes* you might want to take a look at an [explanation in German](http://openbook.galileocomputing.de/javainsel9/javainsel_08_001.htm#mj58cf4fadac5cc0924b9451626df2228c) or [English](http://inheritingjava.blogspot.ch/2011/02/chapter-54-anonymous-inner-classes.html).

There is an interface in JavaFX called [`ChangeListener`](http://docs.oracle.com/javafx/2/api/javafx/beans/value/ChangeListener.html) with one method called `changed(...)`. We need an *anonymous class* that implements this interface and add it to our person table. That sounds quite complicated. I'll explain it, but first let's take a look at the new code, added to the `initialize()` method in `PersonOverviewController`:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
@FXML
private void initialize() {
  // Initialize the person table
  firstNameColumn.setCellValueFactory(new PropertyValueFactory&lt;Person, String&gt;("firstName"));
  lastNameColumn.setCellValueFactory(new PropertyValueFactory&lt;Person, String&gt;("lastName"));
  // Auto resize columns
  personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

  // clear person
  showPersonDetails(null);
  
  // Listen for selection changes
  personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener&lt;Person&gt;() {

    @Override
    public void changed(ObservableValue&lt;? extends Person&gt; observable,
        Person oldValue, Person newValue) {
      showPersonDetails(newValue);
    }
  });
}
</pre>

With `showPersonDetails(null);` we reset the person details. If you've implemented `showPersonDetails(...)` correctly this should set an empty String to all text fields.

With `personTable.getSelectionModel...` we get the *selectedItemProperty* of the person table and add a listener to it. The new `ChangeListener` is of type `Person` since we have `Person` objects in the table. Now, whenever the user selects a person in the table, the method `changed(...)` is called. We take the newly selected person and pass it to the `showPersonDetails(...)` method.

Try to **run your application** at this point. Verify that when you select a person in the table, details about that person are displayed on the right.

If something doesn't work, you can compare your `PersonOverviewController` class with [PersonOverviewController.java](/assets/library/javafx-2-tutorial/part3/PersonOverviewController.java).

* * * 

## The Delete Button

Our user interface already contains a delete button, but without any functionality. We can select the action for a button inside the *Scene Builder*. Any method inside our controller that is annotated with `@FXML` (or is public) is accessible by the *Scene Builder*. Thus, let's first create the delete method at the end of our `PersonOverviewController` class:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Called when the user clicks on the delete button.
 */
@FXML
private void handleDeletePerson() {
  int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
  personTable.getItems().remove(selectedIndex);
}
</pre>

Now, open the `PersonOverview.fxml` file in *SceneBuilder*. Select the *Delete* button, open the *Code* view and choose `#handleDeletePerson` in the dropdown of **On Action**.

![On Action](/assets/library/javafx-2-tutorial/part3/addressapp02.png)


### Error Handling

If you run the application at this point, you should be able to delete selected persons from the table. But what happenes, if you **click the delete button if no person is selected** in the table? 

There will be an `ArrayIndexOutOfBoundsException` because it could not remove a person item at index `-1`. The index `-1` was returned by `getSelectedIndex()` which means that there was no selection.

To ignore such an error is not very nice, of course. We should let the user know that he/she must select a person before deleting. Even better would be if we disabled the button so that the user doesn't even have the chance to do something wrong. I'll show how to do the first approach here.

We'll add a popup dialog to inform the user. You'll need to **add a library** for the Dialogs: 

1. Download the newest **javafx-dialogs-x.x.x.jar** file from my [GitHub Page](https://github.com/marcojakob/javafx-ui-sandbox/releases).
2. Create a **lib** subfolder in the project and add the jar file to this folder.
3. Add the jar file to the project's classpath: In Eclipse *right-click on the jar file | Build Path | Add to Build Path*.

With some changes made to the `handleDeletePerson()` method, we can show a popup dialog whenever the user pushes the delete button while no person is selected in the table:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Called when the user clicks on the delete button.
 */
@FXML
private void handleDeletePerson() {
  int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
  if (selectedIndex &gt;= 0) {
    personTable.getItems().remove(selectedIndex);
  } else {
    // Nothing selected
    Dialogs.showWarningDialog(mainApp.getPrimaryStage(),
        "Please select a person in the table.",
        "No Person Selected", "No Selection");
  }
}
</pre>

* * *

## The New and Edit Dialog

The new and edit actions are a bit more work: We'll need a new custom dialog (a.k.a. stage) with a form to ask the user for details about the person.


### Design the Dialog

1. Create a new fxml file called `PersonEditDialog.fxml` inside the view package.
2. Use a `GridPane`, `Label`s, `TextField`s and `Button`s to create a Dialog like the following:   
![Edit Dialog](/assets/library/javafx-2-tutorial/part3/addressapp03.png)   
If you don't to do the work, you can download this [PersonEditDialog.fxml](/assets/library/javafx-2-tutorial/part3/PersonEditDialog.fxml). 


### Create the Controller

Create the controller for the Dialog as `PersonEditDialogController.java`:

##### PersonEditDialogController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address;

import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Person;
import ch.makery.address.util.CalendarUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class PersonEditDialogController {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField birthdayField;
	
	
	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
	}
	
	/**
	 * Sets the stage of this dialog.
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/**
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
		
		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		postalCodeField.setText(Integer.toString(person.getPostalCode()));
		cityField.setText(person.getCity());
		birthdayField.setText(CalendarUtil.format(person.getBirthday()));
		birthdayField.setPromptText("yyyy-mm-dd");
	}
	
	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			person.setFirstName(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setStreet(streetField.getText());
			person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
			person.setCity(cityField.getText());
			person.setBirthday(CalendarUtil.parse(birthdayField.getText()));
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n"; 
		}
		if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n"; 
		}
		if (streetField.getText() == null || streetField.getText().length() == 0) {
			errorMessage += "No valid street!\n"; 
		}
		
		if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
			errorMessage += "No valid postal code!\n"; 
		} else {
			// try to parse the postal code into an int
			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n"; 
			}
		}
		
		if (cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "No valid city!\n"; 
		}
		
		if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
			errorMessage += "No valid birthday!\n";
		} else {
			if (!CalendarUtil.validString(birthdayField.getText())) {
				errorMessage += "No valid birthday. Use the format yyyy-mm-dd!\n";
			}
		}
		
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message
			Dialogs.showErrorDialog(dialogStage, errorMessage,
					"Please correct invalid fields", "Invalid Fields");
			return false;
		}
	}
}
</pre>

A few things to note about this controller:

* The `setPerson(...)` method can be called from another class to set the person that is to be edited.
* When the user clicks the OK butten, the `handleOk()` method is called. First, some validation is done by calling the `isInputValid()` method. Only if validation was successful, the person object is filled with the data that the user entered. Those changes will directly be applied to the person object that was passed to `setPerson(...)`!
* The boolean `okClicked` is used so that the caller can determine whether the user clicked the OK or Cancel button.


### Link View and Controller 

With the View (FXML) and the controller created we need to link them together:

1. Open the `PersonEditDialog.fxml` and select the topmost *AnchorPane* in the *Hierarchy*. 
2. Open *Code* on the right side and select the `PersonEditDialogController` as controller class.
3. Set the **fx:id** of all `TextField`s to the corresponding field of the controller.
4. Set the **onAction** of the two buttons to the corresponding handler method.



### Opening the Dialog

Add a method to load and display the edit person dialog inside our `MainApp`:   

##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * Opens a dialog to edit details for the specified person. If the user
 * clicks OK, the changes are saved into the provided person object and
 * true is returned.
 * 
 * @param person the person object to be edited
 * @return true if the user clicked OK, false otherwise.
 */
public boolean showPersonEditDialog(Person person) {
  try {
    // Load the fxml file and create a new stage for the popup
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/PersonEditDialog.fxml"));
    AnchorPane page = (AnchorPane) loader.load();
    Stage dialogStage = new Stage();
    dialogStage.setTitle("Edit Person");
    dialogStage.initModality(Modality.WINDOW_MODAL);
    dialogStage.initOwner(primaryStage);
    Scene scene = new Scene(page);
    dialogStage.setScene(scene);
    
    // Set the person into the controller
    PersonEditDialogController controller = loader.getController();
    controller.setDialogStage(dialogStage);
    controller.setPerson(person);
    
    // Show the dialog and wait until the user closes it
    dialogStage.showAndWait();
    
    return controller.isOkClicked();
    
  } catch (IOException e) {
    // Exception gets thrown if the fxml file could not be loaded
    e.printStackTrace();
    return false;
  }
}
</pre>

Add the following methods to the `PersonOverviewController`:   

##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Called when the user clicks the new button.
 * Opens a dialog to edit details for a new person.
 */
@FXML
private void handleNewPerson() {
  Person tempPerson = new Person();
  boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
  if (okClicked) {
    mainApp.getPersonData().add(tempPerson);
  }
}

/**
 * Called when the user clicks the edit button.
 * Opens a dialog to edit details for the selected person.
 */
@FXML
private void handleEditPerson() {
  Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
  if (selectedPerson != null) {
    boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
    if (okClicked) {
      refreshPersonTable();
      showPersonDetails(selectedPerson);
    }
    
  } else {
    // Nothing selected
    Dialogs.showWarningDialog(mainApp.getPrimaryStage(),
        "Please select a person in the table.",
        "No Person Selected", "No Selection");
  }
}

/**
 * Refreshes the table. This is only necessary if an item that is already in
 * the table is changed. New and deleted items are refreshed automatically.
 * 
 * This is a workaround because otherwise we would need to use property
 * bindings in the model class and add a *property() method for each
 * property. Maybe this will not be necessary in future versions of JavaFX
 * (see http://javafx-jira.kenai.com/browse/RT-22599)
 */
private void refreshPersonTable() {
  int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
  personTable.setItems(null);
  personTable.layout();
  personTable.setItems(mainApp.getPersonData());
  // Must set the selected index again (see http://javafx-jira.kenai.com/browse/RT-26291)
  personTable.getSelectionModel().select(selectedIndex);
}
</pre>

Open the `PersonOverview.fxml` file in Scene Builder. Choose the corresponding methods in *On Action* for the new and edit buttons.

* * *

## Done!
You should have a working *Address Application* now. The application is able to add, edit, and delete persons. There is even some validation for the text fields to avoid bad user entries.

I hope the concepts and structure of this application will get you started with writing your own JavaFX application! Have fun and stay tuned for possible future tutorials.


### What's Next? ###
In [Tutorial Part 4](/library/javafx-2-tutorial/part4/) we will add some CSS styling.

