+++
title = "JavaFX Dialogs (official)"
date = 2014-10-28
udpated = 2015-03-12
image = "login-dialog.png"
description = "Examples of how to create simple popup Dialogs and Alerts in JavaFX. Official Dialogs were included in JDK 8u40."
prettify = true
# comments = true
tags = [ "Java", "JavaFX" ]
+++

JavaFX 8u40 finally includes simple Dialogs and Alerts! I've been waiting for this since 2012! In the meantime I wrote about how to use Dialogs [in JavaFX 2](/blog/javafx-2-dialogs/) and later [in JavaFX 8 with ControlsFX](/blog/javafx-8-dialogs/).

Now that they are available in the official JDK, let's learn how to use them.


## Prerequisites

To use the official JavaFX Dialogs you need JDK 8u40 or later. 

&rarr; [Download JDK 8u40](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or newer.


## Standard Dialogs

### Information Dialog

![JavaFX Information Dialog](information-dialog.png)

<pre class="prettyprint lang-java">
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText("Look, an Information Dialog");
alert.setContentText("I have a great message for you!");

alert.showAndWait();
</pre>


#### Without Header Text

![JavaFX Information Dialog without Header Text](information-dialog-no-header.png)

<pre class="prettyprint lang-java">
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("I have a great message for you!");

alert.showAndWait();
</pre>



### Warning Dialog

![JavaFX Warning Dialog](warning-dialog.png)

<pre class="prettyprint lang-java">
Alert alert = new Alert(AlertType.WARNING);
alert.setTitle("Warning Dialog");
alert.setHeaderText("Look, a Warning Dialog");
alert.setContentText("Careful with the next step!");

alert.showAndWait();
</pre>


### Error Dialog

![JavaFX Error Dialog](error-dialog.png)

<pre class="prettyprint lang-java">
Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error Dialog");
alert.setHeaderText("Look, an Error Dialog");
alert.setContentText("Ooops, there was an error!");

alert.showAndWait();
</pre>


### Exception Dialog

![JavaFX Exception Dialog](exception-dialog.png)

There is not a complete Exception Dialog out of the box. But we can easily provide `TextArea` as expandable content.


<pre class="prettyprint lang-java">
Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Exception Dialog");
alert.setHeaderText("Look, an Exception Dialog");
alert.setContentText("Could not find file blabla.txt!");

Exception ex = new FileNotFoundException("Could not find file blabla.txt");

// Create expandable Exception.
StringWriter sw = new StringWriter();
PrintWriter pw = new PrintWriter(sw);
ex.printStackTrace(pw);
String exceptionText = sw.toString();

Label label = new Label("The exception stacktrace was:");

TextArea textArea = new TextArea(exceptionText);
textArea.setEditable(false);
textArea.setWrapText(true);

textArea.setMaxWidth(Double.MAX_VALUE);
textArea.setMaxHeight(Double.MAX_VALUE);
GridPane.setVgrow(textArea, Priority.ALWAYS);
GridPane.setHgrow(textArea, Priority.ALWAYS);

GridPane expContent = new GridPane();
expContent.setMaxWidth(Double.MAX_VALUE);
expContent.add(label, 0, 0);
expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
alert.getDialogPane().setExpandableContent(expContent);

alert.showAndWait();
</pre>


### Confirmation Dialog

![JavaFX Confirmation Dialog](confirmation-dialog.png)

<pre class="prettyprint lang-java">
Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("Are you ok with this?");

Optional&lt;ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
} else {
    // ... user chose CANCEL or closed the dialog
}
</pre>


### Confirmation Dialog with Custom Actions

![JavaFX Confirmation Dialog with Custom Actions](confirmation-dialog-custom-actions.png)

<pre class="prettyprint lang-java">
Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog with Custom Actions");
alert.setHeaderText("Look, a Confirmation Dialog with Custom Actions");
alert.setContentText("Choose your option.");

ButtonType buttonTypeOne = new ButtonType("One");
ButtonType buttonTypeTwo = new ButtonType("Two");
ButtonType buttonTypeThree = new ButtonType("Three");
ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

Optional&lt;ButtonType> result = alert.showAndWait();
if (result.get() == buttonTypeOne){
    // ... user chose "One"
} else if (result.get() == buttonTypeTwo) {
    // ... user chose "Two"
} else if (result.get() == buttonTypeThree) {
    // ... user chose "Three"
} else {
    // ... user chose CANCEL or closed the dialog
}
</pre>


## Text Input Dialog

![JavaFX Text Input Dialog](text-input-dialog.png)

<pre class="prettyprint lang-java">
TextInputDialog dialog = new TextInputDialog("walter");
dialog.setTitle("Text Input Dialog");
dialog.setHeaderText("Look, a Text Input Dialog");
dialog.setContentText("Please enter your name:");

// Traditional way to get the response value.
Optional&lt;String> result = dialog.showAndWait();
if (result.isPresent()){
    System.out.println("Your name: " + result.get());
}

// The Java 8 way to get the response value (with lambda expression).
result.ifPresent(name -> System.out.println("Your name: " + name));
</pre>

**Note:** The `result.isPresent()` will return `false` if the user cancelled the dialog.


## Choice Dialog

![JavaFX Choice Dialog](choice-dialog.png)

<pre class="prettyprint lang-java">
List&lt;String> choices = new ArrayList&lt;>();
choices.add("a");
choices.add("b");
choices.add("c");

ChoiceDialog&lt;String> dialog = new ChoiceDialog&lt;>("b", choices);
dialog.setTitle("Choice Dialog");
dialog.setHeaderText("Look, a Choice Dialog");
dialog.setContentText("Choose your letter:");

// Traditional way to get the response value.
Optional&lt;String> result = dialog.showAndWait();
if (result.isPresent()){
    System.out.println("Your choice: " + result.get());
}

// The Java 8 way to get the response value (with lambda expression).
result.ifPresent(letter -> System.out.println("Your choice: " + letter));
</pre>

**Note:** The `result.isPresent()` will return `false` if the user didn't choose anything or cancelled the dialog.


## Custom Login Dialog

Here is an example of how to create a custom dialog with a login form:

![JavaFX Custom Login Dialog](login-dialog.png)

<pre class="prettyprint lang-java">
// Create the custom dialog.
Dialog&lt;Pair&lt;String, String>> dialog = new Dialog&lt;>();
dialog.setTitle("Login Dialog");
dialog.setHeaderText("Look, a Custom Login Dialog");

// Set the icon (must be included in the project).
dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

// Set the button types.
ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
GridPane grid = new GridPane();
grid.setHgap(10);
grid.setVgap(10);
grid.setPadding(new Insets(20, 150, 10, 10));

TextField username = new TextField();
username.setPromptText("Username");
PasswordField password = new PasswordField();
password.setPromptText("Password");

grid.add(new Label("Username:"), 0, 0);
grid.add(username, 1, 0);
grid.add(new Label("Password:"), 0, 1);
grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
username.textProperty().addListener((observable, oldValue, newValue) -> {
    loginButton.setDisable(newValue.trim().isEmpty());
});

dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
dialog.setResultConverter(dialogButton -> {
    if (dialogButton == loginButtonType) {
        return new Pair&lt;>(username.getText(), password.getText());
    }
    return null;
});

Optional&lt;Pair&lt;String, String>> result = dialog.showAndWait();

result.ifPresent(usernamePassword -> {
    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
});
</pre>


## Styling the Dialogs

### Custom Icon

![Custom Dialog Icon](custom-icon.png)

In the current version it's a bit cumbersome to get to the Dialog's `Stage` to be able to set its icon. Here is how:

<pre class="prettyprint lang-java">
// Get the Stage.
Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();

// Add a custom icon.
stage.getIcons().add(new Image(this.getClass().getResource("login.png").toString()));
</pre>

According to [this bug report](https://javafx-jira.kenai.com/browse/RT-38895) the final version of the JavaFX 8u40 Dialogs should use the same icon as the application that it is running from. In that case you would need to set its owner and the Dialog would get the owner's icon:

<pre class="prettyprint lang-java">
dialog.initOwner(otherStage);
</pre>


### Minimal Decorations

Another option is to remove the icon and use only minimal window decorations.

![Minimal Decorations](minimal-decorations.png)

<pre class="prettyprint lang-java">
dialog.initStyle(StageStyle.UTILITY);
</pre>


## Other Options

### Setting the Owner

You can specify the owner `Window` for a dialog. If no owner or null is specified for the owner, it is a top-level, unowned dialog. 

<pre class="prettyprint lang-java">
dialog.initOwner(parentWindow);
</pre>


### Setting the Modality

You can specify the modality for a dialog. The modality must be one of `Modality.NONE`, `Modality.WINDOW_MODAL`, or `Modality.APPLICATION_MODAL`.

<pre class="prettyprint lang-java">
dialog.initModality(Modality.NONE);
</pre>


## API Documentation

For more information on the Dialogs have a look at the JavaFX API docs:

* [Alert](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Alert.html)
* [Dialog](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html)
* [TextInputDialog](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TextInputDialog.html)
* [ChoiceDialog](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ChoiceDialog.html)
* [DialogPane](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/DialogPane.html)
