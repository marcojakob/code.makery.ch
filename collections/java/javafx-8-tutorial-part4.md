---
layout: article
title: "JavaFX 2 Tutorial - Part 4: CSS Styling"
date: 2012-11-26 13:00
published: false
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2012/11/26/javafx-tutorial-addressapp-4/
sidebars:
- header: "Articles in this Series"
  paging: true
  body:
  - text: "Introduction"
    link: /java/javafx-2-tutorial-intro/
    intro: true
  - text: "Part 1: Scene Builder"
    link: /java/javafx-2-tutorial-part1/
  - text: "Part 2: Model and TableView"
    link: /java/javafx-2-tutorial-part2/
  - text: "Part 3: Interacting with the User"
    link: /java/javafx-2-tutorial-part3/
  - text: "Part 4: CSS Styling"
    link: /java/javafx-2-tutorial-part4/
    active: true
  - text: "Part 5: Storing Data as XML"
    link: /java/javafx-2-tutorial-part5/
  - text: "Part 6: Statistics Chart"
    link: /java/javafx-2-tutorial-part6/
  - text: "Part 7: Deployment with e(fx)clipse"
    link: /java/javafx-2-tutorial-part7/
- header: "Download Sources"
  body:
  - text: Source of Part 4 (Eclipse Project)
    link: /assets/java/javafx-2-tutorial-part4/addressapp-part-4.zip
    icon-css: fa fa-fw fa-download
---

![Screenshot AddressApp Part 4](/assets/java/javafx-2-tutorial-part4/addressapp01.png)


## Topics in Part 4

* **CSS Styling**
* Adding an **Application Icon**

* * *

## CSS Styling 

In JavaFX you can style your user interface using Cascading Style Sheets (CSS). This is great! It's never been as easy to customize the appearance of a Java application.

In this tutorial we will create a *DarkTheme* inspired by the Windows 8 Metro design. The css for the buttons is based on the blog post [JMetro - Windows 8 Metro controls on Java](http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/) by Pedro Duque Vieira.


### Getting Familiar with CSS

If you want to style your JavaFX application you should have a basic understanding of CSS in general. A good place to start is this [CSS tutorial](http://www.csstutorial.net/).

For more JavaFX specific information about CSS:

* [Skinning JavaFX Applications with CSS](http://docs.oracle.com/javafx/2/css_tutorial/jfxpub-css_tutorial.htm) - Tutorial by Oracle
* [JavaFX CSS Reference](http://docs.oracle.com/cd/E17802_01/javafx/javafx/1.3/docs/api/javafx.scene/doc-files/cssref.html) - Official Reference


### Default JavaFX CSS

The default source for CSS styles is a file called **`caspian.css`**. This css file can be found in the Java FX jar file `jfxrt.jar` located in your Java folder under `/jdk_x.x.x/jre/lib/jfxrt.jar`.

This default style sheet is always applied to a JavaFX application. By adding a custom style sheet we can override the default styles of the `caspian.css`.   
Hint: It helps to look at the default CSS file to see which styles you might need to override. 


### Attaching CSS Style Sheets

Add the following CSS file called `DarkTheme.css` to the *view* package.

##### DarkTheme.css

<pre class="prettyprint lang-css pre-scrollable">
.background {
    -fx-background-color: #1d1d1d;
}

.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 0.6;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.table-view {
	-fx-base: #1d1d1d;
	-fx-control-inner-background: #1d1d1d;
	-fx-background-color: #1d1d1d;
	-fx-table-cell-border-color: transparent;
	-fx-table-header-border-color: transparent;
	-fx-padding: 5;
}

.table-view .column-header-background {
    -fx-background-color: transparent;
}

.table-view .column-header, .table-view .filler {
    -fx-size: 35;
    -fx-border-width: 0 0 1 0;
    -fx-border-color: 
        transparent
        transparent
        derive(-fx-base, 80%) 
        transparent;
    -fx-border-insets: 0 10 1 0;
}

.table-view .column-header .label {
    -fx-font-size: 20pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-alignment: center-left;
    -fx-opacity: 1;
}

.table-view:focused .table-row-cell:filled:focused:selected {
    -fx-background-color: -fx-focus-color;
}

.split-pane:horizontal > * > .split-pane-divider {
	-fx-border-color: transparent #1d1d1d transparent #1d1d1d;
	-fx-background-color: transparent, derive(#1d1d1d,20%);
}

.split-pane {
    -fx-padding: 1 0 0 0;
}

.menu-bar {
    -fx-background-color: derive(#1d1d1d,20%);
    -fx-selection-bar: derive(-fx-background,-7%);
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 0.9;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}



/* 
 * Metro style Push Button
 * Author: Pedro Duque Vieira
 * http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/
 */
.button {
    -fx-padding: 5 22 5 22;   
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #1d1d1d;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
  -fx-background-color: white;
  -fx-text-fill: #1d1d1d;
}

.button:focused {
    -fx-border-color: white, white;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #1d1d1d;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color,30%);
}
</pre>

We now need to attach the CSS to our Scene. We could do this programmatically in Java code, but we'll use the Scene Builder to add it to our fxml files: 


#### Attach CSS to RootLayout.fxml

Open the file `RootLayout.fxml` in Scene Builder. Select the root `BorderPane` in the Hierarchy view. Under properties add the `DarkTheme.css` file as stylesheet.


#### Attach CSS to PersonEditDialog.fxml

Open the file `PersonEditDialog.fxml` in Scene Builder. Select the root `AnchorPane` and choose `DarkTheme.css` in the properties view as stylesheet.

The background is still white, so add the Style Class `background` to the root `AnchorPane`.

![Add Style Class](/assets/java/javafx-2-tutorial-part4/addressapp02.png)

Select the OK button and choose *Default Button* in the Properties View. This will change its color and make this the default button when the *enter* key is pressed by the user.


#### Attach CSS to PersonOverview.fxml

Open the file `PersonOverview.fxml` in Scene Builder. Select the root `AnchorPane` in the Hierarchy view. Under properties add the `DarkTheme.css` file as stylesheet.

![Add CSS](/assets/java/javafx-2-tutorial-part4/addressapp03.png)

You should already see some changes now: The table and the buttons are black. If you select a button and look at the CSS part in the Properties view you will see that there already is a default style class called `button`.

![Button Style](/assets/java/javafx-2-tutorial-part4/addressapp04.png)

All class styles `.button` from `caspian.css` apply to those buttons. Since we've redefined (and thus overridden) some of those styles in our custom CSS, the appearance of the buttons change automatically.

You might need to adjust the size of the buttons so that all text is displayed.

Select the right `AnchorPane` that is inside the `SplitPane`. Go to the Properties view and use the plus (+) sign to add a Style Class. Select the `background` style class. The background should now turn black.

![AnchorPane](/assets/java/javafx-2-tutorial-part4/addressapp05.png)

![Background Style](/assets/java/javafx-2-tutorial-part4/addressapp06.png)

If there is a white border around the table, select the `TableView` and choose 0 for all anchors in the Layout view. Now the table should take up all the left space.


#### Labels with different style

Right now, all the labels on the right side have the same size. There are already some styles defined in the css file called `.label-header` and `.label-bright` that we'll use to further style the labels.

Select the *Person Details* label and add `label-header` as a Style Class.

![](/assets/java/javafx-2-tutorial-part4/addressapp07.png)

To each label in the right column (where the actual person details are displayed), add the css Style Class `label-bright`.

* * *

## Adding an Application Icon

Right now our application just has the default icon in the title bar and taks bar:

![Default Icon](/assets/java/javafx-2-tutorial-part4/addressapp08.png)

It looks much nicer with a custom icon:

![Custom Icon](/assets/java/javafx-2-tutorial-part4/addressapp09.png)


### The Icon File

A possible place to get free icons is [Icon Finder](http://www.iconfinder.com). I downloaded a little [address book icon](http://www.iconfinder.com/icondetails/86957/32/).

Create a (normal) folder inside your AddressApp project called **resources** and a subfolder called **images** in it. Put the icon of your choice inside the images folder. Your folder structure should look something like this now:

![Custom Icon File](/assets/java/javafx-2-tutorial-part4/addressapp10.png)


### Set Icon to Scene

To set the icon for our scene add the following line to the `start(...)` method in `MainApp.java`


##### MainApp.java

<pre class="prettyprint lang-java">
this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
</pre>

The whole `start(...)` method will look something like this now:

<pre class="prettyprint lang-java">
public void start(Stage primaryStage) {
  this.primaryStage = primaryStage;
  this.primaryStage.setTitle("AddressApp");
  // Set the application icon
  this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
  
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
</pre>

You can also add an icon to the stage of the person edit dialog, of course.


### What's Next? ###
In [Tutorial Part 5](/java/javafx-2-tutorial-part5/) we will add XML storage for our data.

