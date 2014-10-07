---
layout: article
title: "Tutorial JavaFX 8 - Parte 4: Estilos usando CSS"
date: 2014-09-10 00:00
updated: 2014-09-10 00:00
slug: javafx-8-tutorial/pt/part4
canonical: /java/javafx-8-tutorial-part4/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-pt-part4.md
description: "In JavaFX you can style your user interface using CSS. We'll also add an application icon in this tutorial part."
image: /assets/library/javafx-8-tutorial/part4/addressapp-part4.png
published: true
prettify: true
comments: false
sidebars:
- header: "Artigos nesta serie"
  body:
  - text: "Introdução"
    link: /library/javafx-8-tutorial/pt/
    paging: Intro
  - text: "Parte 1: Scene Builder"
    link: /library/javafx-8-tutorial/pt/part1/
    paging: 1
  - text: "Parte 2: Modelo e TableView"
    link: /library/javafx-8-tutorial/pt/part2/
    paging: 2
  - text: "Parte 3: Interagindo com o usuário"
    link: /library/javafx-8-tutorial/pt/part3/
    paging: 3
  - text: "Parte 4: Estilos usando CSS"
    link: /library/javafx-8-tutorial/pt/part4/
    paging: 4
    active: true
  - text: "Parte 5: Salvando dados como XML"
    link: /library/javafx-8-tutorial/pt/part5/
    paging: 5
  - text: "Parte 6: Gráficos de Estatistica"
    link: /library/javafx-8-tutorial/pt/part6/
    paging: 6
  - text: "Parte 7: Implantação"
    link: /library/javafx-8-tutorial/pt/part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: Part 4 as Eclipse Project <em>(requires at least JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-4.zip
    icon-css: fa fa-fw fa-download
- header: Linguagens
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-part4/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part4/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Español
    link: /library/javafx-8-tutorial/es/part4/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part4/
    icon-css: fa fa-fw fa-globe
---

<div class="alert alert-warning">
  <i class="fa fa-language"></i> This page is beeing translated to Portuguese. If you'd like to help out please read <a href="/library/how-to-contribute/" class="alert-link">how to contribute</a>.
</div>


![Screenshot AddressApp Part 4](/assets/library/javafx-8-tutorial/part4/addressapp-part4.png)


## Topics in Part 4

* **CSS Styling**
* Adding an **Application Icon**



*****


## CSS Styling 

In JavaFX you can style your user interface using Cascading Style Sheets (CSS). This is great! It's never been as easy to customize the appearance of a Java application.

In this tutorial we will create a *DarkTheme* inspired by the Windows 8 Metro design. The css for the buttons is based on the blog post [JMetro - Windows 8 Metro controls on Java](http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/) by Pedro Duque Vieira.


### Getting Familiar with CSS

If you want to style your JavaFX application you should have a basic understanding of CSS in general. A good place to start is this [CSS tutorial](http://www.csstutorial.net/).

For more JavaFX specific information about CSS:

* [Skinning JavaFX Applications with CSS](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/css_tutorial.htm) - Tutorial by Oracle
* [JavaFX CSS Reference](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html) - Official Reference


### Default JavaFX CSS

The default source for CSS styles in JavaFX 8 is a file called **`modena.css`**. This css file can be found in the Java FX jar file `jfxrt.jar` located in your Java folder under `/jdk1.8.x/jre/lib/ext/jfxrt.jar`.

Unzip the `jfxrt.jar`. You should find the `modena.css` under `com/sun/javafx/scene/control/skin/modena/`

This default style sheet is always applied to a JavaFX application. By adding a custom style sheet we can override the default styles of the `modena.css`.   

<div class="alert alert-info">
**Hint:** It helps to look at the default CSS file to see which styles you might need to override.
</div>


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
    -fx-background-color: transparent;
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

.split-pane:horizontal > .split-pane-divider {
    -fx-border-color: transparent #1d1d1d transparent #1d1d1d;
    -fx-background-color: transparent, derive(#1d1d1d,20%);
}

.split-pane {
    -fx-padding: 1 0 0 0;
}

.menu-bar {
    -fx-background-color: derive(#1d1d1d,20%);
}

.context-menu {
    -fx-background-color: derive(#1d1d1d,50%);
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 0.9;
}

.menu .left-container {
	-fx-background-color: black;
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

1. Open the file `RootLayout.fxml` in Scene Builder. 

2. Select the root `BorderPane` in the Hierarchy view. Under *Properties* group add the `DarkTheme.css` file as stylesheet.   
![DarkTheme for RootLayout](/assets/library/javafx-8-tutorial/part4/darktheme-rootlayout.png)


#### Attach CSS to PersonEditDialog.fxml

1. Open the file `PersonEditDialog.fxml` in Scene Builder. Select the root `AnchorPane` and choose `DarkTheme.css` in the *Properties* group as stylesheet.

2. The background is still white, so add the Style Class `background` to the root `AnchorPane`.   
![Add Style Class](/assets/library/javafx-8-tutorial/part4/darktheme-personeditdialog.png)

3. Select the OK button and choose *Default Button* in the Properties View. This will change its color and make this the default button when the *enter* key is pressed by the user.


#### Attach CSS to PersonOverview.fxml

1. Open the file `PersonOverview.fxml` in Scene Builder. Select the root `AnchorPane` in the *Hierarchy* group. Under properties add the `DarkTheme.css` file as stylesheet.

2. You should already see some changes now: The table and the buttons are black. All class styles `.table-view` and `.button` from `modena.css` apply to the table and buttons. Since we've redefined (and thus overridden) some of those styles in our custom CSS, the new styles are applied automatically.

3. You might need to adjust the size of the buttons so that all text is displayed.

4. Select the right `AnchorPane` that is inside the `SplitPane`.   
![Background Style Select](/assets/library/javafx-8-tutorial/part4/background-style-select.png)   

5. Go to the *Properties* group and select `background` as style class. The background should now turn black.   
![Background Style](/assets/library/javafx-8-tutorial/part4/background-style.png)


#### Labels with Different Style

Right now, all the labels on the right side have the same size. There are already some styles defined in the css file called `.label-header` and `.label-bright` that we'll use to further style the labels.

1. Select the *Person Details* label and add `label-header` as a Style Class.   
![Label Header Style](/assets/library/javafx-8-tutorial/part4/label-header-style.png)

2. To each label in the right column (where the actual person details are displayed), add the css Style Class `label-bright`.   
![Label Bright Style](/assets/library/javafx-8-tutorial/part4/label-bright-style.png)


*****


## Adding an Application Icon

Right now our application just has the default icon in the title bar and taks bar:

![Default Icon](/assets/library/javafx-8-tutorial/part4/default-app-icon.png)

It looks much nicer with a custom icon:

![Custom Icon](/assets/library/javafx-8-tutorial/part4/custom-app-icon.png)


### The Icon File

A possible place to get free icons is [Icon Finder](http://www.iconfinder.com). I downloaded a little [address book icon](http://www.iconfinder.com/icondetails/86957/32/).

Create a (normal) folder inside your AddressApp project called **resources** and a subfolder called **images** in it. Put the icon of your choice inside the images folder. Your folder structure should look something like this now:

![Custom Icon File](/assets/library/javafx-8-tutorial/part4/custom-icon-file.png)


### Set Icon to Scene

To set the icon for our scene add the following line to the `start(...)` method in `MainApp.java`


##### MainApp.java

<pre class="prettyprint lang-java">
this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
</pre>

The whole `start(...)` method should look something like this now:

<pre class="prettyprint lang-java">
public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("AddressApp");

    // Set the application icon.
    this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

    initRootLayout();

    showPersonOverview();
}
</pre>

You can also add an icon to the stage of the person edit dialog, of course.


### What's Next?

In [Tutorial Part 5](/library/javafx-8-tutorial/pt/part5/) we will add XML storage for our data.


##### Some other articles you might find interesting

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

