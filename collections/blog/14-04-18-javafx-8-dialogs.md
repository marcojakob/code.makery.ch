---
layout: post
title: JavaFX 8 Dialogs
date: 2014-04-18 00:00
updated: 2014-06-02 00:00
slug: javafx-8-dialogs
description: "Examples of how to create simple popup Dialogs in JavaFX 8."
image: /assets/blog/14-04-18-javafx-8-dialogs/custom-dialog.png
published: true
prettify: true
comments: true
tags:
- Java
- JavaFX
---

Back in 2012 I back-ported an early example of a JavaFX 8 Dialog to JavaFX 2. You can read about it in the blog post [JavaFX 2 Dialogs](/blog/javafx-2-dialogs/). I did hope that the Dialogs would be included in JavaFX 8, but they were not. 

Lately, I've been asked a lot about how to use the Dialogs with JavaFX 8. Fortunately, the JavaFX team created the [ControlsFX project](http://fxexperience.com/controlsfx/), which is a "convenient way to get access to a number of controls that do not ship with JavaFX". ControlsFX contains a good and simple to use Dialog.

In this post I will show some examples of how to use the [ControlsFX Dialogs](http://controlsfx.bitbucket.org/org/controlsfx/dialog/Dialogs.html).


## How To Use ControlsFX Dialogs

1. Download the latest **ControlsFX** zip file from the [ControlsFX Website](http://fxexperience.com/controlsfx/). (It is also available as Maven dependency if you prefer to use Maven.)   
**Important: The ControlsFX must be version `8.0.6_20` or greater to work with `JDK 8u20` and above as there was a breaking change introduced in that version.**

2. Inside the zip file you should find a `controlsfx-8.x.x.jar` file. Add the jar file to your JavaFX 8 project (usually inside a `lib` subfolder).   
**Note: ControlsFX will only work on JavaFX 8.0 or later. Make sure you are using JDK 8 or later in your project.**

3. Add the jar file to your project's classpath: In Eclipse *right-click on the jar file* | *Build Path* | *Add to Build Path*. Now Eclipse knows about the library.

4. Now ControlsFX is ready to be used. Add one of the following code snippets to create a Dialog.


### Localizing the Dialogs

If you want to **change the text of the buttons**, you must add a localized properties file: 

1. Open the `controlsfx-8.x.x.jar` and make a copy of the file `controlsfx.properties`. This file contains the default English translation.
2. Add your desired locale to the filname of the copied properties file. For example, my locale is `de` for language and `CH` for country. That results in `controlsfx_de_CH.properties` as my file name.
3. Change the texts inside the properties file. 
4. Move the new properties file to your project, e.g. into your `src` folder or somewhere else on the classpath.
5. It should already work for your system's locale. If you want to change to a different locale, you can set the locale by calling `Localization.setLocale(new Locale("de", "CH"))`.


## Standard Dialogs

### Information Dialog

![JavaFX Information Dialog](/assets/blog/14-04-18-javafx-8-dialogs/information-dialog.png)

<pre class="prettyprint lang-java">
Dialogs.create()
        .owner(stage)
        .title("Information Dialog")
        .masthead("Look, an Information Dialog")
        .message("I have a great message for you!")
        .showInformation();
</pre>


#### Without Masthead

![JavaFX Information Dialog without Masterhead](/assets/blog/14-04-18-javafx-8-dialogs/information-dialog-no-masterhead.png)

<pre class="prettyprint lang-java">
Dialogs.create()
        .owner(stage)
        .title("Information Dialog")
        .masthead(null)
        .message("I have a great message for you!")
        .showInformation();
</pre>



### Warning Dialog

![JavaFX Warning Dialog](/assets/blog/14-04-18-javafx-8-dialogs/warning-dialog.png)

<pre class="prettyprint lang-java">
Dialogs.create()
        .owner(stage)
        .title("Warning Dialog")
        .masthead("Look, a Warning Dialog")
        .message("Careful with the next step!")
        .showWarning();
</pre>


### Error Dialog

![JavaFX Error Dialog](/assets/blog/14-04-18-javafx-8-dialogs/error-dialog.png)

<pre class="prettyprint lang-java">
Dialogs.create()
        .owner(stage)
        .title("Error Dialog")
        .masthead("Look, an Error Dialog")
        .message("Ooops, there was an error!")
        .showError();
</pre>


### Exception Dialog

![JavaFX Exception Dialog](/assets/blog/14-04-18-javafx-8-dialogs/exception-dialog.png)

<pre class="prettyprint lang-java">
Dialogs.create()
        .owner(stage)
        .title("Exception Dialog")
        .masthead("Look, an Exception Dialog")
        .message("Ooops, there was an exception!")
        .showException(new FileNotFoundException("Could not find file blabla.txt"));
</pre>


### Confirm Dialog

![JavaFX Confirm Dialog](/assets/blog/14-04-18-javafx-8-dialogs/confirm-dialog.png)

<pre class="prettyprint lang-java">
Action response = Dialogs.create()
        .owner(stage)
        .title("Confirm Dialog")
        .masthead("Look, a Confirm Dialog")
        .message("Do you want to continue?")
        .showConfirm();

if (response == Dialog.Actions.YES) {
    // ... user chose YES
} else {
    // ... user chose NO, CANCEL, or closed the dialog
}
</pre>

The response will either be `Dialog.Actions.NO`, `Dialog.Actions.YES`, or `Dialog.Actions.CANCEL`.


### Confirm Dialog with Custom Actions

![JavaFX Confirm Dialog with Custom Actions](/assets/blog/14-04-18-javafx-8-dialogs/confirm-dialog-custom-actions.png)

If `actions` are provided, they completely replace the standard actions.

<pre class="prettyprint lang-java">
Action response = Dialogs.create()
        .owner(stage)
        .title("Confirm Dialog with Custom Actions")
        .masthead("Look, a Confirm Dialog with Custom Actions")
        .message("Are you ok with this?")
        .actions(Dialog.Actions.OK, Dialog.Actions.CANCEL)
        .showConfirm();

if (response == Dialog.Actions.OK) {
    // ... user chose OK
} else {
    // ... user chose CANCEL, or closed the dialog
}
</pre>


### Text Input Dialog

![JavaFX Choice Input Dialog](/assets/blog/14-04-18-javafx-8-dialogs/text-input-dialog.png)

<pre class="prettyprint lang-java">
Optional&lt;String> response = Dialogs.create()
        .owner(stage)
        .title("Text Input Dialog")
        .masthead("Look, a Text Input Dialog")
        .message("Please enter your name:")
        .showTextInput("walter");

// One way to get the response value.
if (response.isPresent()) {
    System.out.println("Your name: " + response.get());
}

// The Java 8 way to get the response value (with lambda expression).
response.ifPresent(name -> System.out.println("Your name: " + name));
</pre>


### Choice Input Dialog

![JavaFX Choice Input Dialog](/assets/blog/14-04-18-javafx-8-dialogs/choice-input-dialog.png)

<pre class="prettyprint lang-java">
List&lt;String> choices = new ArrayList<>();
choices.add("a");
choices.add("b");
choices.add("c");

Optional&lt;String> response = Dialogs.create()
        .owner(stage)
        .title("Choice Input Dialog")
        .masthead("Look, a Choice Input Dialog")
        .message("Choose your letter:")
        .showChoices(choices);

// One way to get the response value.
if (response.isPresent()) {
    System.out.println("The user chose: " + response.get());
}

// The Java 8 way to get the response value (with lambda expression).
response.ifPresent(chosen -> System.out.println("The user chose: " + chosen));
</pre>


Note: The `response.isPresent()` will return `false` if the user didn't choose anything or cancelled the dialog.


## Special Purpose Dialogs

### Command Link Dialog

![JavaFX Command Link Dialog](/assets/blog/14-04-18-javafx-8-dialogs/command-link-dialog.png)

<pre class="prettyprint lang-java">
List&lt;CommandLink&gt; links = new ArrayList&lt;&gt;();
links.add(new CommandLink("Go to the Zoo",
        "Here you will see zebras, monkeys, lions, elephants, and maybe also an alligator."));
links.add(new CommandLink("Go to the Circus",
        "Watch acrobats fly around and clowns, of course."));
links.add(new CommandLink("Stay Home",
        "Watch TV or play some board games with your siblings."));

Action response = Dialogs.create()
        .owner(stage)
        .title("Command Link Dialog")
        .masthead(null)
        .message("Where do you want to go?")
        .showCommandLinks(links.get(2), links);

System.out.println(response);
</pre>

The response will either be one of the `CommandLink`s or `Dialog.Actions.CANCEL`.



### Font Selector Dialog

![JavaFX Font Selector Dialog](/assets/blog/14-04-18-javafx-8-dialogs/font-selector-dialog.png)

<pre class="prettyprint lang-java">
Optional&lt;Font> response = Dialogs.create()
        .owner(stage)
        .masthead("Choose what you like")
        .showFontSelector(Font.font("Times New Roman"));
</pre>



### Progress Dialog

![JavaFX Progress Dialog](/assets/blog/14-04-18-javafx-8-dialogs/progress-dialog.png)

<pre class="prettyprint lang-java">
Service&lt;Void&gt; service = new Service&lt;Void&gt;() {
    @Override
    protected Task&lt;Void&gt; createTask() {
        return new Task&lt;Void&gt;() {
            @Override
            protected Void call()
                    throws InterruptedException {
                updateMessage("Finding friends . . .");
                updateProgress(0, 10);
                for (int i = 0; i &lt; 10; i++) {
                    Thread.sleep(300);
                    updateProgress(i + 1, 10);
                    updateMessage("Found " + (i + 1) + " friends!");
                }
                updateMessage("Found all.");
                return null;
            }
        };
    }
};

Dialogs.create()
        .owner(stage)
        .title("Progress Dialog")
        .masthead("Searching for friends")
        .showWorkerProgress(service);

service.start();
</pre>


## Custom Dialogs

Using the [lower-level API](http://controlsfx.bitbucket.org/org/controlsfx/dialog/Dialog.html) of ControlsFX you can create custom dialogs.

Here is an example of how to create a login form:

![JavaFX Custom Login Dialog](/assets/blog/14-04-18-javafx-8-dialogs/custom-dialog.png)

<pre class="prettyprint lang-java">
final TextField username = new TextField();
final PasswordField password = new PasswordField();
final Action actionLogin = new AbstractAction("Login") {
    // This method is called when the login button is clicked ...
    public void handle(ActionEvent ae) {
        Dialog d = (Dialog) ae.getSource();
        // Do the login here.
        d.hide();
    }
};

@Override
public void start(Stage stage) {
    // Create the custom dialog.
    Dialog dlg = new Dialog(stage, "Login Dialog");

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(0, 10, 0, 10));

    username.setPromptText("Username");
    password.setPromptText("Password");

    grid.add(new Label("Username:"), 0, 0);
    grid.add(username, 1, 0);
    grid.add(new Label("Password:"), 0, 1);
    grid.add(password, 1, 1);

    ButtonBar.setType(actionLogin, ButtonType.OK_DONE);
    actionLogin.disabledProperty().set(true);

    // Do some validation (using the Java 8 lambda syntax).
    username.textProperty().addListener((observable, oldValue, newValue) -> {
        actionLogin.disabledProperty().set(newValue.trim().isEmpty());
    });

    dlg.setMasthead("Look, a Custom Login Dialog");
    dlg.setContent(grid);
    dlg.getActions().addAll(actionLogin, Dialog.Actions.CANCEL);

    // Request focus on the username field by default.
    Platform.runLater(() -> username.requestFocus());

    dlg.show();
</pre>


## Further Information

**The javadoc documentation of ControlsFX is very good!** Look up the following pages for further info about Dialogs:

* [ControlsFX Dialogs](http://controlsfx.bitbucket.org/org/controlsfx/dialog/Dialogs.html)
* [ControlsFX Dialog](http://controlsfx.bitbucket.org/org/controlsfx/dialog/Dialog.html) - lower-level API for custom dialogs
