---
layout: post
title: JavaFX 2 Event Handlers and Change Listeners
date: 2012-12-19 01:00
slug: javafx-2-event-handlers-and-change-listeners
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2012/12/19/javafx-event-handlers-and-change-listeners/
tags:
- Java
- JavaFX
---
<div class="alert alert-info">
  &rarr; UPDATED VERSION of this article available: <a href="/blog/javafx-8-event-handling-examples/" class="alert-link">JavaFX 8 Event Handling Examples</a>
</div>

Very often in JavaFX we have to react to user events: The user clicks a button, presses a key, moves the mouse, etc. This post describes how to handle such events. 

We'll try to do as much event handling with *Scene Builder* and `fxml` as possible. For some events we'll add event handling in the Java code of the controller.

Our example covers some of the most used events on common JavaFX 2 controls:

![TableView Filter](/assets/blog/12-12-19-javafx-2-event-handlers-and-change-listeners/screenshot-01.png)

Note: For background information refer to the official [JavaFX Events Tutorial](http://docs.oracle.com/javafx/2/events/jfxpub-events.htm) (does not cover `fxml`).


* * *

## Set Up

Set up a simple JavaFX project with an **fxml** file, a corresponding **controller** class and a main class to load the fxml. Alternatively, just download the source at the end of this post.


* * *

## Button Events

Handling `Button` events is very easy. Altough there are other events we'll likely only need `ActionEvent`s.

First, open the **controller** class. Add a new method to handle button action events:

<pre class="prettyprint lang-java">
@FXML
private void handleButtonAction() {
  outputTextArea.appendText("Button Action\n");
}
</pre>

Note: The `outputTextArea` is just a JavaFX `TextArea` to print some output. You could also do a `System.out.println(...)`.

Now, open the **fxml** file in *Scene Builder*, select a button and choose `#handleButtonAction` as *On Action*.

![TableView Filter](/assets/blog/12-12-19-javafx-2-event-handlers-and-change-listeners/screenshot-02.png)

This should already be enough for the button to work. If we need more information about the event that called our method in the controller, we could add a parameter like this:

<pre class="prettyprint lang-java">
@FXML
private void handleButtonAction(ActionEvent event) {
  outputTextArea.appendText(event.toString() + "\n");
}
</pre>


* * *

## CheckBox Events

Handling `CheckBox` events works almost the same as handling `Button` events. The only difference is that we might need to know the current state of the `CheckBox`, wheter it is selected or not. 

For such information we must have a reference to the `CheckBox`. So, we'll add an instance variable at the beginning of our **controller**.

<pre class="prettyprint lang-java">
@FXML
private CheckBox checkBox;
</pre>

Open *Scene Builder*, choose the `checkBox` variable as `fx:id` for the `CheckBox`.

![TableView Filter](/assets/blog/12-12-19-javafx-2-event-handlers-and-change-listeners/screenshot-03.png)

Now create a handler method and choose it as *On Action* in *Scene Builder*.

<pre class="prettyprint lang-java">
@FXML
private void handleCheckBoxAction() {
  outputTextArea.appendText("CheckBox Action (selected: " + checkBox.isSelected() + ")\n");
}
</pre>


* * *

## ComboBox Events

For the `ComboBox` (and later for the `ListView`) we'll use a simple model class called `Person` with a `firstName` and `lastName` attribute:


#### Person.java

<pre class="prettyprint lang-java">
public class Person {
	private String firstName;
	private String lastName;

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
</pre>

Now, let's start with the `ComboBox` stuff:

First, create an instance variable for the `ComboBox` and an `ObservableList` in our **controller** class. Select the `comboBox` variable as `fx:id` in *Scene Builder*:

<pre class="prettyprint lang-java">
@FXML
private ComboBox&lt;Person&gt; comboBox;

private ObservableList&lt;Person&gt; comboBoxData = FXCollections.observableArrayList();
</pre>

Second, add some data (e.g. in the constructor or initialize method):

<pre class="prettyprint lang-java">
comboBoxData.add(new Person("Hans", "Muster"));
comboBoxData.add(new Person("Ruth", "Mueller"));
comboBoxData.add(new Person("Heinz", "Kurz"));
comboBoxData.add(new Person("Cornelia", "Meier"));
</pre>

Third, set the data into the list. This must be in the `initialize()` method as this is the time when we can be shure that the variable `comboBox` is initialized with the `ComboBox` from the `fxml` file.

<pre class="prettyprint lang-java">
comboBox.setItems(comboBoxData);
</pre>

Fourth, create a handler method and select the method as *On Action* in *Scene Builder*.

<pre class="prettyprint lang-java">
@FXML
private void handleComboBoxAction() {
  Person selectedPerson = comboBox.getSelectionModel().getSelectedItem();
  outputTextArea.appendText("ComboBox Action (selected: " + selectedPerson + ")\n");
}
</pre>


* * *

## Hyperlink Events

`Hyperlink`s can be used the same way as a `Button` (see above).

<pre class="prettyprint lang-java">
@FXML
private void handleHyperlinkAction() {
	outputTextArea.appendText("Hyperlink Action\n");
}
</pre>


* * *

## Slider Events

`Slider`s don't have `ActionEvent`s. Instead they have a `Number` called `valueProperty` that contains the current value of the slider. 


### Change Listener

In JavaFX a `Property` is more than just a simple value. Each `Property` provides methods to observe changes made to its value. We can **"listen for changes"**.

So, create an instance variable and select it `fx:id` in *Scene Builder*:

<pre class="prettyprint lang-java">
@FXML
private Slider slider;
</pre>

In the `initialize()` method add a `ChangeListener`:

<pre class="prettyprint lang-java">
// Listen for Slider value changes
slider.valueProperty().addListener(new ChangeListener&lt;Number&gt;() {
	@Override
	public void changed(ObservableValue&lt;? extends Number&gt; observable,
			Number oldValue, Number newValue) {
		
		outputTextArea.appendText("Slider Value Changed (newValue: " + newValue.intValue() + ")\n");
	}
});
</pre>

This construct looks a bit coplicated. Fortunately, it's always the same pattern for every `ChangeListener`. Let me explain:

1. `slider.valueProperty()` gives us access to the `Property`.   
   **Note:** There are many other `Properties` like `focusedProperty() or `disabledProperty()`. We could use any of those properties and add a `ChangeListener` to it.
2. `addListener(...)` expects a `ChangeListener` of type `Number`. `ChangeListener` is an interface so we need to create a concrete class that `implements ChangeListener`:
  * Normally, we would create a new class implementing the `ChangeListener` interface like this: `public class MyChangeListener implements ChangeListener<Number> {...}`. Then, we'd create an instance of this class and pass it to `addListener(...)`.
  * Since we will *use this class only once*, it's a bit overkill to create a normal class. Instead we can use a short form of creating a class and instantiating it. This is called an **Anonymous Inner Class** as it does not have a name.
3. `new ChangeListener<Number>() {...}` creates such an *Anonymous Inner Class* of type `<Number>`.
4. Every `ChangeListener` must have a method called `changed(...)` that will be called every time a change occurs. 


* * *

## TextBox Events

In a `TextBox` the `ActionEvent` is triggered with the return key. We might use this, but probably the most common case is that we must react to changes made to the text.

First, we'll create an instance variable and select it as `fx:id` in *Scene Builder*:

<pre class="prettyprint lang-java">
@FXML
private TextField textField;
</pre>

And this is how we can react to changes of the text. We'll use a `ChangeListener` as described above (see Slider section):

<pre class="prettyprint lang-java">
// Listen for TextField text changes
textField.textProperty().addListener(new ChangeListener&lt;String&gt;() {
	@Override
	public void changed(ObservableValue&lt;? extends String&gt; observable,
			String oldValue, String newValue) {
		
		outputTextArea.appendText("TextField Text Changed (newValue: " + newValue + ")\n");
	}
});
</pre>


* * *

## ListView Events

We'll use the same `Person` class as we used for the `ComboBox` above.

First, create an instance variable for the `ListView` and an `ObservableList` in our **controller** class. Select the `listView` variable as `fx:id` in *Scene Builder*:

<pre class="prettyprint lang-java">
@FXML
private ListView&lt;Person&gt; listView;

private ObservableList&lt;Person&gt; listViewData = FXCollections.observableArrayList();
</pre>

Second, add some data (e.g. in the constructor or initialize method):

<pre class="prettyprint lang-java">
listViewData.add(new Person("Lydia", "Kunz"));
listViewData.add(new Person("Anna", "Best"));
listViewData.add(new Person("Stefan", "Meier"));
listViewData.add(new Person("Martin", "Mueller"));
</pre>

Third, set the data into the list. This must be in the `initialize()` method as this is the time when we can be shure that the variable `listView` is initialized with the `ListView` from the `fxml` file.

<pre class="prettyprint lang-java">
listView.setItems(listViewData);
</pre>

Fourth, also in the `initialize()` method, we'll add a `ChangeListener`. It is the same ChangeListener pattern as described above (see Slider section):

<pre class="prettyprint lang-java">
listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener&lt;Person&gt;() {
	@Override
	public void changed(ObservableValue&lt;? extends Person&gt; observable,
			Person oldValue, Person newValue) {
		
		outputTextArea.appendText("ListView Selection Changed (newValue: " + newValue + ")\n");
	}
});
</pre>


* * *

## Download Example Source ##
Download the complete [event-handlers example source](/assets/blog/12-12-19-javafx-2-event-handlers-and-change-listeners/javafx-event-handlers-and-change-listeners.zip).
