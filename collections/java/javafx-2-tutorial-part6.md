---
layout: article
title: "JavaFX 2 Tutorial - Part 6: Statistics Chart"
date: 2012-12-04 12:00
updated: 2013-02-08 00:00
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2012/12/04/javafx-tutorial-addressapp-6/
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
  - text: "Part 6: Statistics Chart"
    link: /java/javafx-2-tutorial-part6/
    paging: 6
    active: true
  - text: "Part 7: Deployment with e(fx)clipse"
    link: /java/javafx-2-tutorial-part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: Source of Part 6 (Eclipse Project)
    link: /assets/java/javafx-2-tutorial-part6/addressapp-part-6.zip
    icon-css: fa fa-fw fa-download
---

<div class="alert alert-info">
  I'm currently rewriting the entire JavaFX 2 tutorial for JavaFX 8. <a href="https://tinyletter.com/code-makery" class="alert-link">Subscribe here</a> and I'll send you an email when all tutorial parts are ready!
</div>

![Screenshot AddressApp Part 6](/assets/java/javafx-2-tutorial-part6/addressapp01.png)

## Topics in Part 6

* Creating a **Statistics Chart** to display birthday distribution.

* * *

## Birthday Statistics

All our people in the AddressApp have a birthday. Wouldn't it be nice to have some statistics about when our people celebrate their birthday.

We'll use a **Bar Chart** containing a bar for each month. Each bar shows how many people have their birthday in that particular month.

* * *

## The Statistics FXML View

1. We start by creating a `BirthdayStatistics.fxml` file inside our `ch.makery.address.view` package (*Right-click on package | New | other... | New FXML Document*).
2. Open the `BirthdayStatistics.fxml` file in Scene Builder.
3. Select the root `AnchorPane` and set the *Pref Width* to 620 and the *Pref Height* to 450.
4. Add a `BarChart` to the `AnchorPane`.
5. Right-click on the `BarChart` and select *Fit to Parent*.
6. Save the fxml file, go to Eclipse and refresh the project.

Before we'll come back to Scene Builder, we'll first create the controller and wire everything up in our MainApp.

* * *

## The Statistics Controller

In the controller package `ch.makery.address` create a Java class called `BirthdayStatisticsController.java`.

Let's first take a look at the entire controller class before I start explaining:

##### BirthdayStatisticsController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import ch.makery.address.model.Person;

/**
 * The controller for the birthday statistics view.
 * 
 * @author Marco Jakob
 */
public class BirthdayStatisticsController {
	
	@FXML
	private BarChart&lt;String, Integer&gt; barChart;
	
	@FXML
	private CategoryAxis xAxis;
	
	private ObservableList&lt;String&gt; monthNames = FXCollections.observableArrayList();
	
	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Get an array with the English month names.
		String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
		// Convert it to a list and add it to our ObservableList of months.
		monthNames.addAll(Arrays.asList(months));
		
		xAxis.setCategories(monthNames);
	}
	
	/**
	 * Sets the persons to show the statistics for.
	 * 
	 * @param persons
	 */
	public void setPersonData(List&lt;Person&gt; persons) {
		int[] monthCounter = new int[12];
		for (Person p : persons) {
			int month = p.getBirthday().get(Calendar.MONTH);
			monthCounter[month]++;
		}
		
		XYChart.Series&lt;String, Integer&gt; series = createMonthDataSeries(monthCounter);
        barChart.getData().add(series);
	}
	
	/**
	 * Creates a XYChart.Data object for each month. All month data is then
	 * returned as a series.
	 * 
	 * @param monthCounter Array with a number for each month. Must be of length 12!
	 * @return
	 */
	private XYChart.Series&lt;String, Integer&gt; createMonthDataSeries(int[] monthCounter) {
		XYChart.Series&lt;String,Integer&gt; series = new XYChart.Series&lt;String,Integer&gt;();
		
		for (int i = 0; i &lt; monthCounter.length; i++) {
			XYChart.Data&lt;String, Integer&gt; monthData = new XYChart.Data&lt;String,Integer&gt;(monthNames.get(i), monthCounter[i]);
			series.getData().add(monthData);
		}
		
		return series;
	}
}
</pre>

### How the Controller Works

1. The controller will need access to two elements from our FXML file:
   * The `barChar`: It has the type `String` and `Integer`. The `String` is used for the month on the x-axis and the `Integer` is used for the number of people in a specific month.
   We'll use the reference to the `BarChart` to set our data.
   * The `xAxis`: We'll use this to add the month Strings.   

2. The `initialize()` method fills the x-axis with a list of all the months.

3. The `setPersonData(...)` method will be accessed by the `MainApp` class to set the person data. It loops through all persons and counts the birthdays per month.

4. The `createMonthDataSeries(...)` method takes the array with a number for each month and creates the chart data. For each month a new `XYChart.Data` object is created with the month name and the number of people having their birthday in this month. Each `XYChart.Data` object will represent one bar in the chart.

* * *

## Connecting View and Controller

1. Open `BirthdayStatistics.fxml` in Scene Builder.
2. Select the root `AncherPane` and add the `BirthdayStatisticsController` as controller (in Code View).
3. Select the `BarChart` and choose `barChart` as fx:id Property.
4. Select the `CategoryAxis` and choose `xAxis` as fx:id Property.
5. You may add a title to the chart, remove the legend, etc. for further styling the chart.

* * *

## Connecting the View/Controller with MainApp

We'll use the same mechanism for our *birthday statistics* that we used for the *edit person dialog*: A simple popup dialog containing.

Add the following method to your `MainApp` class:


<pre class="prettyprint lang-java">
/**
 * Opens a dialog to show birthday statistics. 
 */
public void showBirthdayStatistics() {
  try {
    // Load the fxml file and create a new stage for the popup
    FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
    AnchorPane page = (AnchorPane) loader.load();
    Stage dialogStage = new Stage();
    dialogStage.setTitle("Birthday Statistics");
    dialogStage.initModality(Modality.WINDOW_MODAL);
    dialogStage.initOwner(primaryStage);
    Scene scene = new Scene(page);
    dialogStage.setScene(scene);
    
    // Set the persons into the controller
    BirthdayStatisticsController controller = loader.getController();
    controller.setPersonData(personData);
    
    dialogStage.show();
    
  } catch (IOException e) {
    // Exception gets thrown if the fxml file could not be loaded
    e.printStackTrace();
  }
}
</pre>

Everything is set up, but we don't have anyone who actually calls the new `showBirthdayStatistics()` method. Luckily we already have a menu in `RootLayout.fxml` that can be used for this purpose.


### Show Birthday Statistics Menu

In your `RootLayoutController` add the following method which will handle user clicks on the *show birthday statistics* menu item: 


<pre class="prettyprint lang-java">
/**
 * Opens the birthday statistics.
 */
@FXML
private void handleShowBirthdayStatistics() {
  mainApp.showBirthdayStatistics();
}
</pre>

Now, open the `RootLayout.fxml` file with Scene Builder.

Select the *Show Statistics* `MenuItem` and choose `#handleShowBirthdayStatistics` for `On Action` (in Code view)   

![On Action](/assets/java/javafx-2-tutorial-part6/addressapp02.png)

Go to Eclipse, refresh the project and **test it**.

* * *

## More Information on JavaFX Charts

A good place for more information is the official Oracle tutorial on [Using JavaFX Charts](http://docs.oracle.com/javafx/2/charts/jfxpub-charts.htm).


### What's Next?

In the last tutorial [Part 7](/java/javafx-2-tutorial-part7/) we will finally deploy our application (i.e. package and deliver the app to our users).

