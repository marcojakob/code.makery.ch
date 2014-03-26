---
layout: post
title: JavaFX 2 Date Picker
date: 2013-01-07 00:00
slug: javafx-2-date-picker
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2013/01/07/javafx-date-picker/
tags:
- Java
- JavaFX
---

As JavaFX 2.2 doesn't contain a Calendar control we would have to create our own to provide a convenient way to enter a date. Fortunately, [Christian Schudt](http://myjavafx.blogspot.ch/2012/01/javafx-calendar-control.html) has created a very nice DatePicker that we can use:

![TableView Cell Renderer](/assets/blog/13-01-07-javafx-2-date-picker/javafx-date-picker.png)

I modified [Christian Schudt's](http://myjavafx.blogspot.ch/2012/01/javafx-calendar-control.html) `DatePicker` class a little with a few additional lines at the end of the constructor. One change is to let the `DatePicker` grow horizontally with its parent and the other change passes the style sheet to the popup.

You can **download** a `jar` with my modified version and the stylesheet that goes with it. *Harshad RJ* has created a GitHub repository to host it:

* Download [JavaFX Date Picker Release](https://github.com/hrj/javafxDatePicker/releases) on GitHub.
* Download [DatePicker.css](/assets/blog/13-01-07-javafx-2-date-picker/DatePicker.css)


## Integrating the Date Picker with AddressApp

I'll show how you can use the `DatePicker` by integrating it with our **AddressApp** example. You can download the full AddressApp example from the [AddressApp Tutorial Part 7](/java/javafx-2-tutorial-part7).


### Add the Library and CSS

Download and add the `javafx-datepicker-x-x-x.jar` file from above to the `lib` folder. Add it to the classpath with *right-click | Build Path | Add to Build Path*.

Add the `DatePicker.css` file to the `ch.makery.address.view` package.


### Prepare the View for the DatePicker

In our `PersonEditDialog` we have a simple text field for the Date. This is not very convenient and we'd like to replace this with the new `DatePicker`. 

First, delete the birthday text field from `PersonEditDialog.fxml` (using SceneBuilder). 

We won't be able to add the custom `DatePicker` directly in SceneBuilder (at least not with SceneBuilder 1.1). We'll need to add it manually in the Java code. Delete the `birthdayField` from the `PersonEditDialogController` and add the following fields instead:


#### PersonEditDialogController.java

<pre class="prettyprint lang-java">
@FXML
private GridPane gridPane;

private DatePicker birthdayDatePicker;
</pre>

Now, open the `PersonEditDialog.fxml` in SceneBuilder. Select the GridPane and choose `gridPane` as `fx:id`.


### Initialize the DatePicker

Create the `DatePicker` in the `initialize()` method as follows:

<pre class="prettyprint lang-java">
@FXML
private void initialize() {
  // Initialize the DatePicker for birthday
  birthdayDatePicker = new DatePicker(Locale.ENGLISH);
  birthdayDatePicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
  birthdayDatePicker.getCalendarView().todayButtonTextProperty().set("Today");
  birthdayDatePicker.getCalendarView().setShowWeeks(false);
  birthdayDatePicker.getStylesheets().add("ch/makery/address/view/DatePicker.css");
  
  // Add DatePicker to grid
  gridPane.add(birthdayDatePicker, 1, 5);
}
</pre>


### Some Further Adjustments

Change in `setPerson(...)`:

#### PersonEditDialogController.java

<pre class="prettyprint lang-java">
public void setPerson(Person person) {
  this.person = person;
  
  firstNameField.setText(person.getFirstName());
  lastNameField.setText(person.getLastName());
  streetField.setText(person.getStreet());
  postalCodeField.setText(Integer.toString(person.getPostalCode()));
  cityField.setText(person.getCity());
  
  if (person.getBirthday() != null) {
    birthdayDatePicker.setSelectedDate(person.getBirthday().getTime());
  } else {
    birthdayDatePicker.setSelectedDate(null);
  }
}
</pre>

Change in `handleOk()`:

<pre class="prettyprint lang-java">
@FXML
private void handleOk() {
  if (isInputValid()) {
    person.setFirstName(firstNameField.getText());
    person.setLastName(lastNameField.getText());
    person.setStreet(streetField.getText());
    person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
    person.setCity(cityField.getText());
    
    Calendar c = Calendar.getInstance();
    c.setTime(birthdayDatePicker.getSelectedDate());
    person.setBirthday(c);
    
    okClicked = true;
    dialogStage.close();
  }
}
</pre>

Change in `isInputValid()`:

<pre class="prettyprint lang-java">
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
  
  ////////////////////////////////////////
  // CHANGE STARTS HERE
  ////////////////////////////////////////
  if (birthdayDatePicker.getSelectedDate() == null) {
    errorMessage += "No valid birthday!\n";
  } else {
    if (birthdayDatePicker.invalidProperty().get()) {
      errorMessage += "No valid birthday. Use the format yyyy-mm-dd!\n";
    }
  }
  ////////////////////////////////////////
  // CHANGE ENDS HERE
  ////////////////////////////////////////
  
  if (errorMessage.length() == 0) {
    return true;
  } else {
    // Show the error message
    Dialogs.showErrorDialog(dialogStage, errorMessage,
        "Please correct invalid fields", "Invalid Fields");
    return false;
  }
}
</pre>



## Tweaking the DatePicker

For further details about how to tweak the `DatePicker` see [JavaFX Calendar Control](http://myjavafx.blogspot.ch/2012/01/javafx-calendar-control.html) on Christian Schudt's Blog.

