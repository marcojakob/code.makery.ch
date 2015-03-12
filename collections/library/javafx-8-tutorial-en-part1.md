---
layout: article
title: "JavaFX 8 Tutorial - Part 1: Scene Builder"
date: 2014-04-19 01:00
updated: 2015-03-12 00:00
slug: javafx-8-tutorial/part1
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-en-part1.md
description: "Learn how to set up a JavaFX project. This is part one of a seven-part tutorial about designing, programming and deploying an address application with JavaFX."
image: /assets/library/javafx-8-tutorial/part1/addressapp-part1.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /library/javafx-8-tutorial/
    paging: Intro
  - text: "Part 1: Scene Builder"
    link: /library/javafx-8-tutorial/part1/
    paging: 1
    active: true
  - text: "Part 2: Model and TableView"
    link: /library/javafx-8-tutorial/part2/
    paging: 2
  - text: "Part 3: Interacting with the User"
    link: /library/javafx-8-tutorial/part3/
    paging: 3
  - text: "Part 4: CSS Styling"
    link: /library/javafx-8-tutorial/part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /library/javafx-8-tutorial/part5/
    paging: 5
  - text: "Part 6: Statistics Chart"
    link: /library/javafx-8-tutorial/part6/
    paging: 6
  - text: "Part 7: Deployment"
    link: /library/javafx-8-tutorial/part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: Part 1 as Eclipse Project <em>(requires at least JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-1.zip
    icon-css: fa fa-fw fa-download
- header: Languages
  languages: true
  body:
  - text: English
    link: /library/javafx-8-tutorial/part1/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Português
    link: /library/javafx-8-tutorial/pt/part1/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part1/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part1/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/part1/
    icon-css: fa fa-fw fa-globe
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/part1/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/javafx-8-tutorial/fr/part1/
    icon-css: fa fa-fw fa-globe
---

![Screenshot AddressApp Part 1](/assets/library/javafx-8-tutorial/part1/addressapp-part1.png)

### Topics in Part 1

* Getting to know JavaFX
* Creating and starting a JavaFX Project
* Using Scene Builder to design the user interface
* Basic application structure using the Model-View-Controller (MVC) pattern


*****


### Prerequisites

* Latest [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (includes **JavaFX 8**).
* Eclipse 4.4 or greater with e(fx)clipse plugin. The easiest way is to download the preconfigured distro from the [e(fx)clipse website](http://efxclipse.bestsolution.at/install.html#all-in-one). As an alternative you can use an [update site](http://www.eclipse.org/efxclipse/install.html) for your Eclipse installation.
* [Scene Builder 8.0](http://gluonhq.com/products/downloads/) (provided by Gluon because [Oracle only ships it in source code form](http://www.oracle.com/technetwork/java/javase/downloads/sb2download-2177776.html)).


### Eclipse Configurations 

We need to tell Eclipse to use JDK 8 and also where it will find the Scene Builder:

1. Open the Eclipse Preferences and navigate to *Java | Installed JREs*.

2. Click *Add...*, select *Standard VM* and choose the installation *Directory* of your JDK 8.

3. Remove the other JREs or JDKs so that the **JDK 8 becomes the default**.   
![Preferences JDK](/assets/library/javafx-8-tutorial/part1/preferences-jdk.png)

4. Navigate to *Java | Compiler*. Set the **Compiler compliance level to 1.8**.   
![Preferences Compliance](/assets/library/javafx-8-tutorial/part1/preferences-compliance.png)

5. Navigate to the *JavaFX* preferences. Specify the path to your Scene Builder executable.   
![Preferences JavaFX](/assets/library/javafx-8-tutorial/part1/preferences-javafx.png)


### Helpful Links

You might want to bookmark the following links:

* [Java 8 API](http://docs.oracle.com/javase/8/docs/api/) - JavaDoc for the standard Java classes
* [JavaFX 8 API](http://docs.oracle.com/javase/8/javafx/api/) - JavaDoc for JavaFX classes
* [ControlsFX API](http://controlsfx.bitbucket.org/) - JavaDoc for the [ControlsFX project](http://fxexperience.com/controlsfx/) for additional JavaFX controls
* [Oracle's JavaFX Tutorials](http://docs.oracle.com/javase/8/javafx/get-started-tutorial/get_start_apps.htm) - Official JavaFX Tutorials by Oracle

Now, let's get started!


*****


## Create a new JavaFX Project

In Eclipse (with e(fx)clipse installed) go to *File | New | Other...* and choose *JavaFX Project*.   
Specify the Name of the project (e.g. *AddressApp*) and click *Finish*.

Remove the *application* package and its content if it was automatically created.


### Create the Packages

Right from the start we will follow good software design principles. One very important principle is that of [**Model-View-Controller** (MVC)](http://en.wikipedia.org/wiki/Model_View_Controller). According to this we divide our code into three units and create a package for each (Right-click on the src-folder, *New... | Package*):

* `ch.makery.address` - contains *most* controller classes (=business logic)
* `ch.makery.address.model` - contains model classes
* `ch.makery.address.view` - contains views 

**Note:** Our view package will also contain some controllers that are directly related to a single view. Let's call them **view-controllers**.


*****


## Create the FXML Layout File

There are two ways to create the user interface. Either using an XML file or programming everything in Java. Looking around the internet you will encounter both. We will use XML (ending in .fxml) for most parts. I find it a cleaner way to keep the controller and view separated from each other. Further, we can use the graphical Scene Builder to edit our XML. That means we will not have to directly work with XML.

Right-click on the view package and create a new *FXML Document* called `PersonOverview`.   

![New FXML Document](/assets/library/javafx-8-tutorial/part1/new-fxml-document.png)

![New PersonOverview](/assets/library/javafx-8-tutorial/part1/new-person-overview.png)



*****


## Design with Scene Builder

<div class="alert alert-warning">
  **Note:** If you can't get it to work, download the source of this tutorial part and try it with the included fxml.
</div>

Right-click on `PersonOverview.fxml` and choose *Open with Scene Builder*. Now you should see the Scene Builder with just an *AncherPane* (visible under Hierarchy on the left).

(If Scene Builder does not open, go to *Window | Preferences | JavaFX* and set the correct path to your Scene Builder installation).

1. Select the *Anchor Pane* in your Hierarchy and adjust the size under Layout (right side):   
![Anchor Pane Size](/assets/library/javafx-8-tutorial/part1/anchor-pane-size.png)

2. Add a *Split Pane (Horizontal Flow)* by dragging it from the Library into the main area. Right-click the *Split Pane* in the *Hierarchy* view and select *Fit to Parent*.   
![Fit to Parent](/assets/library/javafx-8-tutorial/part1/fit-to-parent.png)

3. Drag a *TableView* (under *Controls*) into the left side of the *SplitPane*. Select the TableView (not a Column) and set the following layout constraints to the TableView. Inside an *AnchorPane* you can always set anchors to the four borders ([more information on Layouts](http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm)).   
![TableView Anchors](/assets/library/javafx-8-tutorial/part1/table-view-anchors.png)

4. Go to the menu *Preview | Show Preview in Window* to see, whether it behaves right. Try resizing the window. The TableView should resize together with the window as it is anchored to the borders.

5. Change the column text (under Properties) to "First Name" and "Last Name".   
![Column Texts](/assets/library/javafx-8-tutorial/part1/column-texts.png)

6. Select the *TableView* and choose *constrained-resize* for the *Column Resize Policy* (under Properties). This ensures that the colums will always take up all available space.   
![Column Resize Policy](/assets/library/javafx-8-tutorial/part1/column-resize-policy.png)

7. Add a *Label* on the right side with the text "Person Details" (hint: use the search to find the *Label*). Adjust it's layout using anchors.   
![Person Details Label](/assets/library/javafx-8-tutorial/part1/person-details-label.png)

8. Add a *GridPane* on the right side, select it and adjust its layout using anchors (top, right and left).    
![GridPane Layout](/assets/library/javafx-8-tutorial/part1/grid-pane-layout.png)

9. Add the following labels to the cells.   
*Note: To add a row to the GridPane select an existing row number (will turn yellow), right-click the row number and choose "Add Row".*   
![Add labels](/assets/library/javafx-8-tutorial/part1/add-labels.png)

10. Add a *ButtonBar* at the bottom. Add three buttons to the bar. Now, set anchors (right and bottom) to the *ButtonBar* so it stays in the right place.   
![Button Group](/assets/library/javafx-8-tutorial/part1/button-group.png)

11. Now you should see something like the following. Use the *Preview* menu to test its resizing behaviour.   
![Preview](/assets/library/javafx-8-tutorial/part1/scene-builder-preview.png)



*****


## Create the Main Application

We need another FXML for our root layout which will contain a menu bar and wraps the just created `PersonOverview.fxml`.

1. Create another *FXML Document* inside the view package called `RootLayout.fxml`. This time, choose *BorderPane* as the root element.   
![New RootLayout](/assets/library/javafx-8-tutorial/part1/new-root-layout.png)

2. Open the `RootLayout.fxml` in Scene Builder.

3. Resize the *BorderPane* with *Pref Width* set to 600 and *Pref Height* set to 400.   
![RootLayout Size](/assets/library/javafx-8-tutorial/part1/root-layout-size.png)

4. Add a *MenuBar* into the TOP Slot. We will not implement the menu functionality at the moment.   
![MenuBar](/assets/library/javafx-8-tutorial/part1/menu-bar.png)


### The JavaFX Main Class 

Now, we need to create the **main java class** that starts up our application with the `RootLayout.fxml` and adds the `PersonOverview.fxml` in the center. 

1. Right-click on your project and choose *New | Other...* and choose *JavaFX Main Class*.   
![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class.png)

2. We'll call the class `MainApp` and put it in the controller package `ch.makery.address` (note: this is the parent package of the `view` and `model` subpackages).   
![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class2.png)


The generated `MainApp.java` class extends from `Application` and contains two methods. This is the basic structure that we need to start a JavaFX Application. The most important part for us is the `start(Stage primaryStage)` method. It is automatically called when the application is `launched` from within the `main` method.

As you see, the `start(...)` method receives a `Stage` as parameter. The following graphic illustrates the structure of every JavaFX application:

![New FXML Document](/assets/library/javafx-8-tutorial/part1/javafx-hierarchy.png)   
*Image Source: http://www.oracle.com*

**It's like a theater play**: The `Stage` is the main container which is usually a `Window` with a border and the typical minimize, maximize and close buttons. Inside the `Stage` you add a `Scene` which can, of course, be switched out by another `Scene`. Inside the `Scene` the actual JavaFX nodes like `AnchorPane`, `TextBox`, etc. are added.

For more information on this turn to [Working with the JavaFX Scene Graph](http://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm).


*****

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

        initRootLayout();

        showPersonOverview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}
</pre>

The various comments should give you some hints about what's going on.

If you run the application now, you should see something like the screenshot at the beginning of this post.


### Frequent Problems

If JavaFX can't find the `fxml` file you specified, you might get the following error message: 

`java.lang.IllegalStateException: Location is not set.`

To solve this issue double check if you didn't misspell the name of your `fxml` files!

<div class="alert alert-warning">
  If it still doesn't work, download the source of this tutorial part and try it with the included fxml.
</div>


*****

### What's Next?

In [Tutorial Part 2](/library/javafx-8-tutorial/part2/) we will add some data and functionality to our AddressApp.


##### Some other articles you might find interesting

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)