---
layout: post
title: JavaFX 8 Event Handling Examples
date: 2014-05-03 00:00
slug: javafx-8-event-handling-examples
description: "We explore how to handle the most common JavaFX events: Button events, CheckBox events, Hyperlink events, Slider events, TextBox events, ListView events."
image: /assets/blog/14-05-03-javafx-8-event-handling-examples/event-handling-examples.png
published: true
prettify: true
comments: true
tags:
- Java
- JavaFX
---

In most JavaFX applications we have to react to many user events: The user clicks a button, presses a key, moves the mouse, etc. 

This post describes how to handle some of the most common JavaFX events.

### Our Example Application

![Event Handling Examples](/assets/blog/14-05-03-javafx-8-event-handling-examples/event-handling-examples.png)


#### Download Example

<i class="fa fa-fw fa-download"></i> [Example Source Code](/assets/blog/14-05-03-javafx-8-event-handling-examples/event-handling-examples.zip)


### FXML and Event Handling

I like to keep the view code nicely separated inside the `fxml` files (instead of constructing it with Java code). This allows me to work with the *Scene Builder* for constructing the GUI.

Working with `fxml` also provides some convenience for event handling: We can choose event handling methods in the *Scene Builder* directly. Here is an example for handling the *action event* of a `Button`:

1. Create a handler method in the controller class (the parameter `ActionEvent` is optional):
<pre class="prettyprint lang-java">
    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Button was clicked, do something...
        outputTextArea.appendText("Button Action\n");
    }
</pre>

2. Select the `Button` in *Scene Builder* and choose `handleButtonAction` as *On Action*.   
![Choose Button Action](/assets/blog/14-05-03-javafx-8-event-handling-examples/choose-button-action.png)


As you can see, this is very simple. But it will not work for all events. For example, the event for selecting a `ComboBox` item can not be handled this way.


### Java 8 and Event Handling

Java 8 **lambda expressions** and **method references** improve the event handling code a lot. I will show two variations of how to handle the *action event* of a `Button` in Java 8 code:


#### Option 1: Lambda Expression

Usually inside the `initialize()` method:

<pre class="prettyprint lang-java">
myButton.setOnAction((event) -> {
    // Button was clicked, do something...
    outputTextArea.appendText("Button Action\n");
});
</pre>


#### Option 2: Method Reference

1. Define the handler method somewhere in the controller class:   
<pre class="prettyprint lang-java">
	private void handleButtonAction(ActionEvent event) {
        // Button was clicked, do something...
		outputTextArea.appendText("Button Action\n");
	}
</pre>

2. Reference the handler method (usually inside `initialize()`):
<pre class="prettyprint lang-java">
    myButton.setOnAction(this::handleButtonAction);
</pre>



*****


## Examples

For all examples I will use **lambda expressions**, but you can always use **method references** or (for most examples) **fxml event handling**, as shown above.  


*****

### Button Events

Handling `Button` events is very easy. Altough there are other events we'll likely only need `ActionEvent`s.

<pre class="prettyprint lang-java">
// Handle Button event.
myButton.setOnAction((event) -> {
    System.out.println("Button Action");
});
</pre>


*****

### CheckBox Events

Handling `CheckBox` events works almost the same as handling `Button` events. The only difference is that we might need to know the current state of the `CheckBox`, wheter it is selected or not. 

<pre class="prettyprint lang-java">
// Handle CheckBox event.
myCheckBox.setOnAction((event) -> {
    boolean selected = myCheckBox.isSelected();
    System.out.println("CheckBox Action (selected: " + selected + ")");
});
</pre>


*****

### ComboBox Events

For the `ComboBox` (and later for the `ListView`) we'll use a simple model class [Person.java](/assets/blog/14-05-03-javafx-8-event-handling-examples/Person.java) with a `firstName` and `lastName` property:


#### Initializing the ComboBox

First, create an instance variable for the `ComboBox` and an `ObservableList` in our **controller** class. Then don't forget to select the `myComboBox` variable as `fx:id` in *Scene Builder*:

<pre class="prettyprint lang-java">
@FXML
private ComboBox&lt;Person> myComboBox;
private ObservableList&lt;Person> myComboBoxData = FXCollections.observableArrayList();
</pre>

Second, create some sample data (e.g. in the constructor or initialize method):

<pre class="prettyprint lang-java">
// Add some sample data.
myComboBoxData.add(new Person("Hans", "Muster"));
myComboBoxData.add(new Person("Ruth", "Mueller"));
myComboBoxData.add(new Person("Heinz", "Kurz"));
myComboBoxData.add(new Person("Cornelia", "Meier"));
</pre>

Third, add sample data to the `ComboBox`. This must be done in the `initialize()` method as this is the time when we can be shure that the variable `myComboBox` is initialized with the `ComboBox` from the `fxml` file.


<pre class="prettyprint lang-java">
// Init ComboBox items.
myComboBox.setItems(myComboBoxData);
</pre>


#### ComboBox Rendering

The ComboBox needs to know how to render our `Person` objects. This is a bit more work:

<pre class="prettyprint lang-java">
// Define rendering of the list of values in ComboBox drop down. 
myComboBox.setCellFactory((comboBox) -> {
    return new ListCell&lt;Person>() {
        @Override
        protected void updateItem(Person item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
            } else {
                setText(item.getFirstName() + " " + item.getLastName());
            }
        }
    };
});

// Define rendering of selected value shown in ComboBox.
myComboBox.setConverter(new StringConverter&lt;Person>() {
    @Override
    public String toString(Person person) {
        if (person == null) {
            return null;
        } else {
            return person.getFirstName() + " " + person.getLastName();
        }
    }

    @Override
    public Person fromString(String personString) {
        return null; // No conversion fromString needed.
    }
});
</pre>


#### Handling Selection Events

Finally, we can handle the selection events: 

<pre class="prettyprint lang-java">
// Handle ComboBox event.
myComboBox.setOnAction((event) -> {
    Person selectedPerson = myComboBox.getSelectionModel().getSelectedItem();
    System.out.println("ComboBox Action (selected: " + selectedPerson.toString() + ")");
});
</pre>


*****

### Hyperlink Events

`Hyperlink`s can be used the same way as a `Button`.

<pre class="prettyprint lang-java">
// Handle Hyperlink event.
myHyperlink.setOnAction((event) -> {
    System.out.println("Hyperlink Action");
});
</pre>


*****

### ListView Events

We'll use the same [Person.java](/assets/blog/14-05-03-javafx-8-event-handling-examples/Person.java) class as we used for the `ComboBox` above.


#### Initializing the ListView

<pre class="prettyprint lang-java">
@FXML
private ListView&lt;Person> myListView;
private ObservableList&lt;Person> listViewData = FXCollections.observableArrayList();
</pre>

Add some example data (e.g. in the constructor or initialize method):

<pre class="prettyprint lang-java">
// Add some sample data.
listViewData.add(new Person("Lydia", "Kunz"));
listViewData.add(new Person("Anna", "Best"));
listViewData.add(new Person("Stefan", "Meier"));
listViewData.add(new Person("Martin", "Mueller"));
</pre>


#### Handling Selection Events

Now, we add the sample data into the `ListView` and handle the selection events. This must be in the `initialize()` method as this is the time when we can be shure that the variable `myListView` is initialized with the `ListView` from the `fxml` file.

`ListView` does not have `ActionEvent`s. Instead it has a `selectedItemProperty` that contains the currenty selected item of the list.

In JavaFX a `Property` is more than just a simple value. Each `Property` provides methods to observe changes made to its value. We can **"listen for changes"**. This is how we handle such changes for a `ListView`:

<pre class="prettyprint lang-java">
// Init ListView.
myListView.setItems(listViewData);
myListView.setCellFactory((list) -> {
    return new ListCell&lt;Person>() {
        @Override
        protected void updateItem(Person item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
            } else {
                setText(item.getFirstName() + " " + item.getLastName());
            }
        }
    };
});

// Handle ListView selection changes.
myListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    System.out.println("ListView Selection Changed (selected: " + newValue.toString() + ")");
});
</pre>


#### The Change Listener Explained

Listening to changes as seen in the `ListView` example above, follows the same pattern for every other `ChangeListener`:

1. `.selectedItemProperty()` gives us access to the `Property`.   
   **Note:** There are many other `Properties` like `focusedProperty()` or `disabledProperty()`. We could use any of those properties and add a `ChangeListener` to it.
   
2. `addListener(...)` expects a `ChangeListener`. `ChangeListener` is a [FunctionalInterface](http://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html) which means we can use lambda expressions here.

3. The lambda expression `(observable, oldValue, newValue) -> {...}` is where we put our handler code. Every time a change occurs, our lambda expression gets called with the three parameters.


*****

### Slider Events

For tracking `Slider` changes we need a `ChangeListener` as seen above for the `ListView`. The `Slider` has a `valueProperty` that contains the current value.

<pre class="prettyprint lang-java">
// Handle Slider value change events.
mySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
    System.out.println("Slider Value Changed (newValue: " + newValue.intValue() + ")");
});
</pre>


*****

### TextBox Events

In a `TextBox` the `ActionEvent` is triggered with the return key. We might use this, but probably the most common case is that we must react to changes made to the text.


We'll use a `ChangeListener` as described above in the `ListView` section.

<pre class="prettyprint lang-java">
// Handle TextField text changes.
myTextField.textProperty().addListener((observable, oldValue, newValue) -> {
    System.out.println("TextField Text Changed (newValue: " + newValue + ")");
});

// Handle TextField enter key event.
myTextField.setOnAction((event) -> {
    System.out.println("TextField Action");
});
</pre>



*****


## Additional Info

For more information turn to the official [JavaFX Events Tutorial](http://docs.oracle.com/javase/8/javafx/events-tutorial/events.htm) (does not cover `fxml`).

