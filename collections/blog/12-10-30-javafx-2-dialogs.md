---
layout: post
title: JavaFX 2 Dialogs
date: 2012-10-30 00:00
updated: 2014-04-18 00:00
slug: javafx-2-dialogs
description: "When programming a graphical user interface (GUI) there are occasions where you'll need a simple popup dialog to communicate with the user."
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2012/10/30/javafx-2-dialogs/
tags:
- Java
- JavaFX
---

<div class="alert alert-info">
  &rarr; For an UPDATED VERSION of this article go to: <a href="/blog/javafx-8-dialogs/" class="alert-link">JavaFX 8 Dialogs</a>
</div>

When programming a graphical user interface (GUI) there are occasions where you'll need a simple popup dialog to communicate with the user. In Swing (the predecessor of JavaFX) there is a convenient class called [JOptionPane](http://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html) for such dialogs. A similar class doesn't exist in JavaFX 2.x (yet).

Fortunately, the authors of JavaFX [published](http://fxexperience.com/2012/10/announcing-the-javafx-ui-controls-sandbox/) some user interface controls they are currently working on. Those controls might be added in a future version of JavaFX.

One of those ui controls is a class called `Dialogs.java` which is exactly what we need.


## How To Use the Dialogs

1. Download the newest **javafx-dialogs-x.x.x.jar** file from my [GitHub Page](https://github.com/marcojakob/javafx-ui-sandbox/tree/master/javafx-dialogs/dist). I put all necessary classes, css files and images inside this jar.
2. Add the jar file to your project (usually inside a **lib** subfolder).
3. Add the jar file to the project's classpath: In Eclipse *right-click on the jar file* | *Build Path* | *Add to Build Path*. Now Eclipse knows about the library.
4. Then add one of the following lines:


### Information Dialog

![JavaFX Information Dialog](/assets/blog/12-10-30-javafx-2-dialogs/javafx-information-dialog.png)

<pre class="prettyprint lang-java">
Dialogs.showInformationDialog(stage, "I have a great message for you!", 
    "Information Dialog", "title");
</pre>


### Warning Dialog

![JavaFX Warning Dialog](/assets/blog/12-10-30-javafx-2-dialogs/javafx-warning-dialog.png)

<pre class="prettyprint lang-java">
Dialogs.showWarningDialog(stage, "Careful with the next step!", "Warning Dialog", "title");
</pre>

### Error Dialog

![JavaFX Error Dialog](/assets/blog/12-10-30-javafx-2-dialogs/javafx-error-dialog.png)

<pre class="prettyprint lang-java">
Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "title");
</pre>

You may also provide an exception:

<pre class="prettyprint lang-java">
Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog With Exception", 
    "title", new FileNotFoundException("Could not find file blabla.txt"));
</pre>

### Confirm Dialog

![JavaFX Confirm Dialog](/assets/blog/12-10-30-javafx-2-dialogs/javafx-confirm-dialog.png)

<pre class="prettyprint lang-java">
DialogResponse response = Dialogs.showConfirmDialog(stage, 
    "Do you want to continue?", "Confirm Dialog", "title");
</pre>

You may provide `DialogOptions` like this:

<pre class="prettyprint lang-java">
DialogResponse response = Dialogs.showConfirmDialog(stage, "Are you ok with this?", 
		"Confirm Dialog With Options", "title", DialogOptions.OK_CANCEL);
</pre>

The response will either be `DialogResponse.YES`, `DialogResponse.NO`, `DialogResponse.CANCEL`, `DialogResponse.OK`, or `DialogResponse.CLOSED`.


### Input Dialog

![JavaFX Input Dialog](/assets/blog/12-10-30-javafx-2-dialogs/javafx-input-dialog.png)

<pre class="prettyprint lang-java">
String input = Dialogs.showInputDialog(stage, "Please enter your name:", "Input Dialog", "title");
</pre>

If you provide some choices, a combobox will be displayed:

![JavaFX Input Dialog with Choices](/assets/blog/12-10-30-javafx-2-dialogs/javafx-input-dialog-choices.png)

<pre class="prettyprint lang-java">
List<String> choices = new ArrayList<>();
choices.add("a");
choices.add("b");
choices.add("c");
		
String input = Dialogs.showInputDialog(stage, "Choose your color:", 
    "Input Dialog With Choices", "title", "b", choices);
</pre>


### Custom Dialog

Since JavaFX dialogs version 0.0.3 there is support for custom dialogs (thanks to Guldner for providing the [patch](https://github.com/marcojakob/javafx-ui-sandbox/pull/7)).

Here is an example of how to use custom dialogs to create a login form:

![JavaFX Custom Dialog](/assets/blog/12-10-30-javafx-2-dialogs/javafx-custom-dialog.png)

<pre class="prettyprint lang-java">
GridPane grid = new GridPane();
grid.setHgap(10);
grid.setVgap(10);
grid.setPadding(new Insets(0, 10, 0, 10));
final TextField username = new TextField(); 
username.setPromptText("Username");
final PasswordField password = new PasswordField(); 
password.setPromptText("Password");

grid.add(new Label("Username:"), 0, 0);
grid.add(username, 1, 0);
grid.add(new Label("Password:"), 0, 1);
grid.add(password, 1, 1);

String usernameResult;
String passwordResult;

Callback<Void, Void> myCallback = new Callback<Void, Void>() {
  @Override
  public Void call(Void param) {
    usernameResult = username.getText();
    passwordResult = password.getText();
    return null;
  }
};

DialogResponse resp = Dialogs.showCustomDialog(stage, grid, "Please log in", "Login", DialogOptions.OK_CANCEL, myCallback);
System.out.println("Custom Dialog: User clicked: " + resp);
//You must check the resp, since input fields' texts are returned regardless of what button was pressed. (ie. If user clicked 'Cancel' disregard the input) 
System.out.println("Custom Dialog: Fields set from custom dialog: " + usernameResult + "/" + passwordResult);
</pre>
