---
layout: article
title: "Tutorial JavaFX 8 - Parte 1: Scene Builder"
date: 2014-09-17 00:00
updated: 2014-09-17 00:00
slug: javafx-8-tutorial/es/part1
canonical: /java/javafx-8-tutorial-part1/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-es-part1.md
description: "Este tutorial describe como crear y empezar un proyecto JavaFX con Eclipse."
image: /assets/library/javafx-8-tutorial/part1/addressapp-part1.png
published: true
prettify: true
comments: true
sidebars:
- header: "Artículos en esta serie"
  body:
  - text: "Introducción"
    link: /library/javafx-8-tutorial/es/
    paging: Intro
  - text: "Parte 1: Scene Builder"
    link: /library/javafx-8-tutorial/es/part1/
    paging: 1
    active: true
  - text: "Parte 2: Modelo y TableView"
    link: /library/javafx-8-tutorial/es/part2/
    paging: 2
  - text: "Parte 3: Interacción con el usuario"
    link: /library/javafx-8-tutorial/es/part3/
    paging: 3
  - text: "Parte 4: Hojas de estilo CSS"
    link: /library/javafx-8-tutorial/es/part4/
    paging: 4
  - text: "Parte 5: Persistencia de datos con XML"
    link: /library/javafx-8-tutorial/es/part5/
    paging: 5
  - text: "Parte 6: Gráficos estadísticos"
    link: /library/javafx-8-tutorial/es/part6/
    paging: 6
  - text: "Parte 7: Publicación con e(fx)clipse"
    link: /library/javafx-8-tutorial/es/part7/
    paging: 7
- header: Lenguajes
  body:
  - text: English
    link: /java/javafx-8-tutorial-part1/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part1/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part1/
    icon-css: fa fa-fw fa-globe
    active: true
---

<div class="alert alert-warning">
  <i class="fa fa-language"></i> This page is beeing translated to Spanish. If you'd like to help out please read <a href="/library/how-to-contribute/" class="alert-link">how to contribute</a>.
</div>


![Screenshot AddressApp Part 1](/assets/library/javafx-8-tutorial/part1/addressapp-part1.png)

### Temas en Parte 1

* Familiarizándose con JavaFX
* Crear y empezar un proyecto JavaFX
* Uso de Scene builder para diseñar la interfaz de usuario
* Estructura básica de una aplicación mediante el patrón Modelo Vista Controlador (MVC)


*****


### Prerequisitos

* Última versión de  [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (includes **JavaFX 8**).
* Eclipse 4.3 o superior con el plugin e(fx)clipse. La forma más sencilla de obtenerlo es descargarse la distribución preconfigurada desde [e(fx)clipse website](http://efxclipse.bestsolution.at/install.html#all-in-one). Como alternativa puedes usar un [sitio de actualización](http://www.eclipse.org/efxclipse/install.html) para tu instalación de Eclipse.
* [Scene Builder 2.0](http://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-info-2157684.html) o superior


### Configuración de Eclipse

Hay que indicarle a Eclipse que use JDK 8 y también dónde se encuentra el ejecutable del Scene Builder:

1. Abre las Preferencias de Eclipse (menú *Window | Preferences* y navega hasta *Java | Installed JREs*.

2. Si no lo tienes el jre1.8 en la lista de JREs, entonces pulsa *Add...*, selecciona *Standard VM* y elige el *Directorio* de instalación (JRE Home directory) de tu JDK 8.

3. Elimina otros JREs o JDKs de tal manera que **JDK 8 se convierta en la opción por defecto**.   
![Preferences JDK](/assets/library/javafx-8-tutorial/part1/preferences-jdk.png)

4. Navega a *Java | Compiler*. Establece el **nivel de cumplimiento del compilador en 1.8** (Compiler compliance level).   
![Preferences Compliance](/assets/library/javafx-8-tutorial/part1/preferences-compliance.png)

5. Navega hasta *Java | JavaFX*. Especifica la ruta al ejecutable del Scene Builder.   
![Preferences JavaFX](/assets/library/javafx-8-tutorial/part1/preferences-javafx.png)


### Enlaces útiles

Te podría interesar mantener los siguientes enlaces:

* [Java 8 API](http://docs.oracle.com/javase/8/docs/api/) - Documentación (JavaDoc) de las clases estándar de Java
* [JavaFX 8 API](http://docs.oracle.com/javase/8/javafx/api/) - Documentación de las clases JavaFX
* [ControlsFX API](http://controlsfx.bitbucket.org/) - Documentación para el proyecto [ControlsFX](http://fxexperience.com/controlsfx/), el cual ofrece controles JavaFX adicionales
* [Oracle's JavaFX Tutorials](http://docs.oracle.com/javase/8/javafx/get-started-tutorial/get_start_apps.htm) - Tutoriales oficiales de Oracle sobre JavaFX

Y ahora, manos a la obra!


*****


## Crea un nuevo proyecto JavaFX

En Eclipse (con e(fx)clipse instalado) ve a *File | New | Other...* y elige *JavaFX Project*. Especifica el nombre del proyecto (ej. *AddressApp*) y aprieta *Finish*.

Borra el paquete *application* y su contenido si ha sido automáticamente creado.


### Crea los paquetes

Desde el principio vamos a seguir buenos principios de diseño de software. Algunos de estos principios se traducen en el uso de la arquitectura o patrón conocida como [**Modelo-Vista-Controlador** (MVC)](http://es.wikipedia.org/wiki/Modelo%E2%80%93vista%E2%80%93controlador). Esta arquitectura promueve la división de nuestro código en tres apartados claramente definidos, uno por cada elemento de la arquitectura. En Java esta separación se traduce en la creación de tres paquetes separados.

Right-click on the src-folder, *New... | Package*:

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

1. Select the *Anchor Pane* in your Hierarchy and adjust the size under Layout (right side):   
![Anchor Pane Size](/assets/library/javafx-8-tutorial/part1/anchor-pane-size.png)

2. Add a *Split Pane (Horizontal Flow)* by dragging it from the Library into the main area. Right-click the *Split Pane* in the *Hierarchy* view and select *Fit to Parent*.   
![Fit to Parent](/assets/library/javafx-8-tutorial/part1/fit-to-parent.png)

3. Drag a *TableView* (under *Controls*) into the left side of the *SplitPane*. Select the TableView (not a Column) and set the following layout constraints to the TableView. Inside an *AnchorPane* you can always set anchors to the four borders ([more information on Layouts](http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm)).   
![TableView Anchors](/assets/library/javafx-8-tutorial/part1/table-view-anchors.png)

4. Go to the menu *Preview | Show Preview in Window* to see, whether it behaves right. Try resizing the window. The TableView should resize together with the window as it is anchored to the borders.

5. Change the column text (under Properties) to "First Name" and "Last Name".   
![Column Texts](/assets/library/javafx-8-tutorial/part1/column-texts.png)

6. Select the *TableView* choose *constrained-resize* for the *Column Resize Policy* (under Properties). This ensures that the colums will always take up all available space.   
![Column Resize Policy](/assets/library/javafx-8-tutorial/part1/column-resize-policy.png)

7. Add a *Label* on the right side with the text "Person Details" (hint: use the search to find the *Label*). Adjust it's layout using anchors.   
![Person Details Label](/assets/library/javafx-8-tutorial/part1/person-details-label.png)

8. Add a *GridPane* on the right side, select it and adjust its layout using anchors (top, right and left).    
![GridPane Layout](/assets/library/javafx-8-tutorial/part1/grid-pane-layout.png)

9. Add the following labels to the cells.   
*Note: To add a row to the GridPane select an existing row number (will turn yellow), right-click the row number and choose "Add Row".*   
![Add labels](/assets/library/javafx-8-tutorial/part1/add-labels.png)

10. Add the three buttons at the bottom. Tip: Select all of them, right-click and call *Wrap In | HBox*. This groups them together. You might need to specify a *spacing* inside the HBox. Then, also set anchors (right and bottom) so they stay in the right place.   
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

If JavaFX can't find the `fxml` file you speicified, you might get the following error message: 

`java.lang.IllegalStateException: Location is not set.`

To solve this issue double check if you didn't misspell the name of your `fxml` files!

<div class="alert alert-warning">
  If it still doesn't work, download the source of this tutorial part and try it with the included fxml.
</div>


*****

### What's Next?

In [Tutorial Part 2](/library/javafx-8-tutorial/pt/part2/) we will add some data and functionality to our AddressApp.


##### Some other articles you might find interesting

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)