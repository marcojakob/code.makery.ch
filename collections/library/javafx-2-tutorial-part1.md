---
layout: article
title: "JavaFX 2 Tutorial - Part 1: Scene Builder"
date: 2012-11-16 22:00
updated: 2013-02-08 00:00
slug: javafx-2-tutorial/part1
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-2-tutorial/part1.md
description: "Learn how to set up a JavaFX project. This is part one of a seven-part tutorial about designing, programming and deploying an address application with JavaFX."
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2012/11/16/javafx-tutorial-addressapp-1/
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /library/javafx-2-tutorial/
    paging: Intro
  - text: "Part 1: Scene Builder"
    link: /library/javafx-2-tutorial/part1/
    paging: 1
    active: true
  - text: "Part 2: Model and TableView"
    link: /library/javafx-2-tutorial/part2/
    paging: 2
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
  - text: Source of Part 1 (Eclipse Project)
    link: https://github.com/marcojakob/tutorial-javafx-2/releases/download/v1.0/addressapp-part-1.zip
    icon-css: fa fa-fw fa-download
---

<div class="alert alert-danger">
  &rarr; UPDATED VERSION for Java 8 available: <a href="/library/javafx-8-tutorial/" class="alert-link">JavaFX 8 Tutorial</a>
</div>

![Screenshot AddressApp Part 1](/assets/library/javafx-2-tutorial/part1/addressapp01.png)

### Topics in Part 1

* Getting to know JavaFX 2
* Creating and starting a JavaFX Project
* Using Scene Builder to design the user interface
* Basic application structure using the Model-View-Controller (MVC) pattern


* * *


### Prerequisites

* Latest [Java JDK 7](http://www.oracle.com/technetwork/java/javase/downloads/index.html) that includes **JavaFX 2.2** or greater.
* Eclipse 4.2 or greater with e(fx)clipse plugin. The easiest way is to download the preconfigured distro from the [e(fx)clipse website](http://efxclipse.bestsolution.at/install.html#all-in-one). As an alternative you can use an [update site](http://www.eclipse.org/efxclipse/install.html) for your Eclipse installation.
* [Scene Builder 1.1](http://www.oracle.com/technetwork/java/javafx/downloads/index.html) or greater


### Preparation and Helpful Links

Play around with the JavaFX widgets in the [JavaFX Ensemble](http://www.oracle.com/technetwork/java/javafx/samples/index.html) (Under *Downloads*, go to *JavaFX Demos and Samples Downloads*, unzip the samples and click on *Ensemble.jar*):

* JavaFX Ensemble is a gallery of over 100 sample applications that use a wide range of JavaFX features.
* Contains source code for each sample.
* Contains links to API documentation (JavaDoc).

Some other helpful links:

* [JavaFX Tutorials](http://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm) - Official Tutorials by Oracle
* [JavaFX 2 API](http://docs.oracle.com/javafx/2/api/) - JavaDoc for JavaFX classes
* [Java 7 API](http://docs.oracle.com/javase/7/docs/api/) - JavaDoc for the standard Java classes

Now, let's get started!


* * *

## Create a new JavaFX Project

In Eclipse (with e(fx)clipse installed) go to *File | New | Other...* and choose *JavaFX Project*.
Specify the Name of the project (i.e. AddressApp).


### Create the Packages

Right from the start we will follow good software design principles. One very important principle is that of [**Model-View-Controller** (MVC)](http://de.wikipedia.org/wiki/Model_View_Controller). According to this we divide our code into three units and create a package for each (Right-click on the src-folder, *New... | Package*):

* For the controller classes: `ch.makery.address`
* For the view classes: `ch.makery.address.view`
* For the model classes: `ch.makery.address.model`


* * *


## Create the FXML Layout File

There are two ways to create the user interface. Either using an XML file or programming everything in Java. Looking around the internet you will encounter both. We will use XML (ending in .fxml) for most parts. I find it a cleaner way to keep the controller and model separated from each other. Further, we can use the graphical Scene Builder to edit the XML. That means we will almost never have to directly work with XML.

Right-click on the view package and create a new *FXML Document* called `PersonOverview`.   

![New FXML Document](/assets/library/javafx-2-tutorial/part1/addressapp02.png)

![New PersonOverview](/assets/library/javafx-2-tutorial/part1/addressapp03.png)

* * *


## Design with Scene Builder

**Note:** If you get stuck somewhere, watch the [Getting Started with JavaFX Scene Builder](http://www.youtube.com/watch?v=rHcnsEoSK_c) YouTube Film. This helps a lot!

Right-click on `PersonOverview.fxml` and choose *Open with Scene Builder*. Now you should see the Scene Builder with just an *AncherPane* (visible under Hierarchy on the left).

1. Select the *Anchor Pane* in your Hierarchy and adjust the size under Layout (right side):   
![Adjust size](/assets/library/javafx-2-tutorial/part1/addressapp04.png)

2. Add a *Split Pane (Horizontal Flow)* by dragging it from the Library into the main area. Right-click and select *Fit to Parent*.   
![Fit to Parent](/assets/library/javafx-2-tutorial/part1/addressapp05.png)

3. Add a *TableView* into the left side. Select the TableView (not a Column) and set the following layout constraints. Inside an *AnchorPane* you can always set anchors to the four borders ([more information on Layouts](http://docs.oracle.com/javafx/2/layout/builtin_layouts.htm)).   
![Adjust sizes](/assets/library/javafx-2-tutorial/part1/addressapp06.png)

4. Go to the menu *Preview | Preview in Window* to see, whether it behaves right. Try resizing the window. The TableView should always keep the 10px distance to the surrounding border.

5. Change the column text (under Properties) to "First Name" and "Last Name" and adjust the sizes.   
![Adjust columns](/assets/library/javafx-2-tutorial/part1/addressapp07.png)

6. Add a *Label* on the right side with the text "Person Details". Adjust it's Layout using anchors.
7. Add a *GridPane* on the right side, select it and adjust it's Layout.    
![GridPane Layout](/assets/library/javafx-2-tutorial/part1/addressapp08.png)

8. Add some rows (under *Layout | GridPane Rows*). Add labels to the cells.   
![Add labels](/assets/library/javafx-2-tutorial/part1/addressapp09.png)

9. Add the three buttons at the bottom. Tipp: Select all of them, right-click and call *Wrap In | HBox*. This groups them together. You might need to specify a Spacing inside the HBox.

10. Now you should see something like the following. Please test it using the Preview Menu.      
![Preview](/assets/library/javafx-2-tutorial/part1/addressapp10.png)


* * *


## Creating the Main Application

We need another FXML for our root layout which will contain a menu bar and wraps the just created `PersonOverview.fxml?`.

1. Create another *FXML Document* inside the view package called `RootLayout.fxml`. This time, choose *BorderPane* as the root element.   
![RootLayout](/assets/library/javafx-2-tutorial/part1/addressapp11.png)

2. Open it in the Scene Builder.
3. Resize the *BorderPane* with *Pref Width* set to 600 and *Pref Height* set to 400.   
![Resize BorderPane](/assets/library/javafx-2-tutorial/part1/addressapp12.png)

4. Add a *MenuBar* into the TOP Slot. We will not implement the menu functionality at the moment.   
![MenuBar](/assets/library/javafx-2-tutorial/part1/addressapp13.png)

5. Now, we need to create the Main Java that starts up our application with the `RootLayout.fxml` and adds the `PersonOverview.fxml` in the center. 

6. Right-click on the controller package, *New | Other...* and choose *JavaFX Main Class*. We'll call it `MainApp`.   
![New JavaFX Main Class](/assets/library/javafx-2-tutorial/part1/addressapp14.png)


### Understanding the JavaFX Main class 

The generated `MainApp.java` class extends from `Application` and contains two methods. This is the basic structure that we need to start a JavaFX Application. The most important part for us is the `start(Stage primaryStage)` method. It is automatically called when we run the application.

As you see, the `start(...)` method receives a `Stage` as parameter. It's good to understand the basic concept of a graphical application with JavaFX:

![New FXML Document](/assets/library/javafx-2-tutorial/part1/javafx-hierarchy.png)
*Image Source: http://www.oracle.com/*

It's like a theater play: The Stage is the main container which is usually a Window with a border and the typical minimize, maximize and close buttons. Inside the Stage you add a Scene which can, of course, be switched out by another Scene. Inside the Scene the actual JavaFX nodes like AnchorPane, TextBox, etc. are added.

Open `MainApp.java` and replace the code with the following:

<pre class="prettyprint lang-java">
package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        try {
            // Load the root layout from the fxml file
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }

        showPersonOverview();
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Shows the person overview scene.
     */
    public void showPersonOverview() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane overviewPage = (AnchorPane) loader.load();
            rootLayout.setCenter(overviewPage);

        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
</pre>

Try to understand the code. The various comments should give you some hints about what's going on.

If you run the application now, you should see something like the screenshot at the beginning of this post.


* * *

### What's Next?

In [Tutorial Part 2](/library/javafx-2-tutorial/part2/) we will add some data and functionality to our AddressApp.


