---
layout: post
title: JavaFX 2 TableView Filter
date: 2012-12-18 00:00
slug: javafx-2-tableview-filter
description: "The JavaFX 2 TableView lacks the ability for filtering. In this post I will explain how we can manually do row filtering in JavaFX 2." 
image: /assets/blog/12-12-18-javafx-2-tableview-filter/tableview-filter.png
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2012/12/18/javafx-tableview-filter/
tags:
- Java
- JavaFX
---

<div class="alert alert-danger">
  &rarr; UPDATED VERSION of this article: <a href="/blog/javafx-8-tableview-sorting-filtering/" class="alert-link">JavaFX 8 TableView Sorting and Filtering</a>
</div>

The JavaFX 2 TableView lacks the ability for filtering. The intention before JavaFX 2.0 shipped was to include a `FilteredList` that would wrap an `ObservableList` (see Oracle forum [Filter rows on TableView](https://forums.oracle.com/forums/thread.jspa?threadID=2350647)). Unfortunately, the filtering was removed again. It will appear in JavaFX 8 which won't be released before late 2013.

In this post I will explain how we can manually do row filtering in JavaFX 2.


## Example Set Up

As an example we'll create a simple table that displays `Person`s. The table should be filtered whenever the user enters something in the text field. 

![TableView Filter](/assets/blog/12-12-18-javafx-2-tableview-filter/tableview-filter.png)

I prefer to define the user interface in `fxml` (with Scene Builder). After creating the example in Scene Builder the `fxml` looks like this:


#### PersonTable.fxml

<pre class="prettyprint lang-xml">
&lt;?xml version="1.0" encoding="UTF-8"?&gt;

&lt;?import java.lang.*?&gt;
&lt;?import javafx.scene.control.*?&gt;
&lt;?import javafx.scene.layout.*?&gt;
&lt;?import javafx.scene.layout.AnchorPane?&gt;
&lt;?import jfxtras.labs.scene.control.*?&gt;

&lt;AnchorPane minWidth="315.0" prefHeight="300.0" prefWidth="315.0" xmlns:fx="http://javafx.com/fxml" fx:controller="PersonTableController"&gt;
  &lt;children&gt;
    &lt;TableView fx:id="personTable" prefHeight="-1.0" prefWidth="-1.0" tableMenuButtonVisible="false" AnchorPane.bottomAnchor="10.0" AnchorPane.leftAnchor="10.0" AnchorPane.rightAnchor="10.0" AnchorPane.topAnchor="40.0"&gt;
      &lt;columns&gt;
        &lt;TableColumn maxWidth="5000.0" minWidth="10.0" prefWidth="120.0" text="First Name" fx:id="firstNameColumn" /&gt;
        &lt;TableColumn maxWidth="5000.0" minWidth="10.0" prefWidth="120.0" text="Last Name" fx:id="lastNameColumn" /&gt;
      &lt;/columns&gt;
    &lt;/TableView&gt;
    &lt;HBox id="HBox" alignment="CENTER" spacing="5.0" AnchorPane.leftAnchor="10.0" AnchorPane.rightAnchor="10.0" AnchorPane.topAnchor="10.0"&gt;
      &lt;children&gt;
        &lt;Label text="Filter Table:" /&gt;
        &lt;TextField fx:id="filterField" prefWidth="-1.0" HBox.hgrow="ALWAYS" /&gt;
      &lt;/children&gt;
    &lt;/HBox&gt;
  &lt;/children&gt;
&lt;/AnchorPane&gt;
</pre>

We'll need a class `Person` for the model:


#### Person.java

<pre class="prettyprint lang-java">
public class Person {

	private String firstName;
	private String lastName;

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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
}
</pre>

We'll need a `MainApp` to load everything:


#### MainApp.java

<pre class="prettyprint lang-java">
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Table Filtering");
		
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("PersonTable.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			System.err.println("Error loading PersonTable.fxml!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
</pre>

The most interesting part is the `PersonTableController` which I'll discuss a bit more now.


* * *

## Filtering

For the filtering to work, we need **two** `ObservableList`s. One list contains the original **master data** while the other contains the **filtered data** that will be displayed in the table.

The constructor puts the same sample data in both the `masterData` and `filteredData` lists. In the beginning nothing is filtered and the two lists contain the same data.

We'll also add a `ListChangeListener` to the `masterData`. Whenever something changes in `masterData` we'll also have to update the `filteredData`.

Now let's take a look at the code: 


#### PersonTableController.java

<pre class="prettyprint lang-java">
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PersonTableController {
	@FXML
	private TextField filterField;
	@FXML
	private TableView&lt;Person> personTable;
	@FXML
	private TableColumn&lt;Person, String> firstNameColumn;
	@FXML
	private TableColumn&lt;Person, String> lastNameColumn;
	
	private ObservableList&lt;Person> masterData = FXCollections.observableArrayList();
	private ObservableList&lt;Person> filteredData = FXCollections.observableArrayList();
	
	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public PersonTableController() {
		// Add some sample data to the master data
		masterData.add(new Person("Hans", "Muster"));
		masterData.add(new Person("Ruth", "Mueller"));
		masterData.add(new Person("Heinz", "Kurz"));
		masterData.add(new Person("Cornelia", "Meier"));
		masterData.add(new Person("Werner", "Meyer"));
		masterData.add(new Person("Lydia", "Kunz"));
		masterData.add(new Person("Anna", "Best"));
		masterData.add(new Person("Stefan", "Meier"));
		masterData.add(new Person("Martin", "Mueller"));
		
		// Initially add all data to filtered data
		filteredData.addAll(masterData);
		
		// Listen for changes in master data.
		// Whenever the master data changes we must also update the filtered data.
		masterData.addListener(new ListChangeListener&lt;Person>() {
			@Override
			public void onChanged(ListChangeListener.Change&lt;? extends Person> change) {
				updateFilteredData();
			}
		});
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table
		firstNameColumn.setCellValueFactory(
				new PropertyValueFactory&lt;Person, String>("firstName"));
		lastNameColumn.setCellValueFactory(
				new PropertyValueFactory&lt;Person, String>("lastName"));
		
		// Add filtered data to the table
		personTable.setItems(filteredData);

		// Listen for text changes in the filter text field
		filterField.textProperty().addListener(new ChangeListener&lt;String>() {
			@Override
			public void changed(ObservableValue&lt;? extends String> observable,
					String oldValue, String newValue) {
				
				updateFilteredData();
			}
		});
	}
	
	/**
	 * Updates the filteredData to contain all data from the masterData that
	 * matches the current filter.
	 */
	private void updateFilteredData() {
		filteredData.clear();
			
		for (Person p : masterData) {
			if (matchesFilter(p)) {
				filteredData.add(p);
			}
		}
		
		// Must re-sort table after items changed
		reapplyTableSortOrder();
	}

	/**
	 * Returns true if the person matches the current filter. Lower/Upper case
	 * is ignored.
	 * 
	 * @param person
	 * @return
	 */
	private boolean matchesFilter(Person person) {
		String filterString = filterField.getText();
		if (filterString == null || filterString.isEmpty()) {
			// No filter --> Add all.
			return true;
		}
		
		String lowerCaseFilterString = filterString.toLowerCase();
		
		if (person.getFirstName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (person.getLastName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}
		
		return false; // Does not match
	}
	
	private void reapplyTableSortOrder() {
		ArrayList&lt;TableColumn&lt;Person, ?>> sortOrder = new ArrayList&lt;>(personTable.getSortOrder());
		personTable.getSortOrder().clear();
		personTable.getSortOrder().addAll(sortOrder);
	}
}
</pre>


### Reacting to User Entering a Filter String

At the end of the method `initialize()` we add a `ChangeListener` to the text property of the `TextField`. Whenever the user changes the text, the `updateFilteredData()` method is called.

In `updateFilteredData()` we remove all items in `filteredData` and only add the data matching the current filter. 


### Changing Filter Behaviour

The method `matchesFilter(...)` determines which `Person`s will be displayed. I chose to look both in the `firstName` and `lastName` fields for a match of the String while ignoring the case.

You could a different kind of filter behaviour in this method like Regular Expressions.


### Reapply Table Sort Order

Whenever we change the filtering, the table must be resorted. The method `reapplyTableSortOrder()` is responsible to remove and set the sort order again.


* * *

## Conclusion

Even though this might not be the fastest and most generic filtering approach, it's still sufficient for many cases. For a more comfortable filtering we'll have to wait for JDK 8.