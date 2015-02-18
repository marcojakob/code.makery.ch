---
layout: article
title: "Tutorial JavaFX 8 - Parte 4: Hojas de estilo CSS"
date: 2014-09-17 00:00
slug: javafx-8-tutorial/es/part4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-es-part4.md
description: "En JavaFX puedes dar estilo al interfaz de usuario utilizando CSS. También añadiremos un icono de aplicación en esta parte del tutorial"
image: /assets/library/javafx-8-tutorial/part4/addressapp-part4.png
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
  - text: "Parte 2: Modelo y TableView"
    link: /library/javafx-8-tutorial/es/part2/
    paging: 2
  - text: "Parte 3: Interacción con el usuario"
    link: /library/javafx-8-tutorial/es/part3/
    paging: 3
  - text: "Parte 4: Hojas de estilo CSS"
    link: /library/javafx-8-tutorial/es/part4/
    paging: 4
    active: true
  - text: "Parte 5: Persistencia de datos con XML"
    link: /library/javafx-8-tutorial/es/part5/
    paging: 5
  - text: "Parte 6: Gráficos estadísticos"
    link: /library/javafx-8-tutorial/es/part6/
    paging: 6
  - text: "Parte 7: Despliegue"
    link: /library/javafx-8-tutorial/es/part7/
    paging: 7
- header: "Código fuente"
  body:
  - text: Parte 4 proyecto Eclipse <em>(requiere al menos JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-4.zip
    icon-css: fa fa-fw fa-download
- header: Lenguajes
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-part4/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part4/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part4/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part4/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/part4/
    icon-css: fa fa-fw fa-globe
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/part4/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/javafx-8-tutorial/fr/part4/
    icon-css: fa fa-fw fa-globe
---

![Screenshot AddressApp Part 4](/assets/library/javafx-8-tutorial/part4/addressapp-part4.png)


## Contenidos en Parte 4

* **Estilos mediante CSS**
* Añadiendo un **Icono de Aplicación**



*****


## Estilos mediante CSS 


En JavaFX puedes dar estilo al interfaz de usuario utilizando hojas de estilo en cascada (CSS). ¡ Esto es estupendo ! Nunca había sido tan fácil personalizar la apariencia de una aplicación Java.

En este tutorial vamos a crear un tema oscuro (*DarkTheme*) inspirado en el diseño de Windows 8 Metro. El código CSS de los botones está basado en el artículo de blog  
[JMetro - Windows 8 Metro controls on Java](http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/) by Pedro Duque Vieira.


### Familiarizándose con CSS

Para poder aplicar estilos a una aplicación JavaFX application debes tener una comprensión básica de CSS en general. Un buen punto de partida es este tutorial: [CSS tutorial](http://www.csstutorial.net/).

Para información más específica de CSS y JavaFX puedes consultar:

* [Skinning JavaFX Applications with CSS](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/css_tutorial.htm) - Tutorial by Oracle
* [JavaFX CSS Reference](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html) - Official Reference


### Estilo por defecto en JavaFX

Los estilos por defecto de JavaFX 8 se encuentran en un archivo denominado **`modena.css`**. Este archivo CSS se encuentra dentro del arhivo jar `jfxrt.jar` que se encuentra en tu directorio de instalación de Java, en la ruta  `/jdk1.8.x/jre/lib/ext/jfxrt.jar`.

Puedes descomprimir `jfxrt.jar` o abrirlo como si fuera un zip. Encontrarás el archivo `modena.css` en la ruta `com/sun/javafx/scene/control/skin/modena/`

Este estilo se aplica siempre a una aplicación JavaFX. Añadiendo un estilo personal podemos reescribir los estilos por defecto definidos en `modena.css`.   

<div class="alert alert-info">
**Truco:** Ayuda consultar el archivo CSS por defecto para ver qué estilos necesitas sobreescribir.
</div>


### Vinculando hojas de estilo CSS

Añade un archivo CSS denominado `DarkTheme.css` al paquete *view*.


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

A continuación necesitamos vincular el CSS a nuestra escena. Podemos hacer esto programáticamente, mediante código Java, pero en esta ocasión vamos a utilizar Scene Builder para añadirlo a nuestros archivos FXML: 


#### Añade el CSS a RootLayout.fxml

1. Abre el archivo `RootLayout.fxml` en Scene Builder. 

2. Selecciona el `BorderPane` raíz en la sección *Hierarchy*. En la vista *Properties* añade el archivo `DarkTheme.css` como hoja de estilo (campo denominado `Stylesheets`).   
![DarkTheme for RootLayout](/assets/library/javafx-8-tutorial/part4/darktheme-rootlayout.png)


#### Añade el CSS a PersonEditDialog.fxml

1. Abre el archivo `PersonEditDialog.fxml` en Scene Builder. Selecciona el `AnchorPane` raíz e incluye `DarkTheme.css` como hoja de estilo en la sección *Properties*.

2. El fondo todavía es blanco, hay que añadir la clase `background` al `AnchorPane` raíz.   
![Add Style Class](/assets/library/javafx-8-tutorial/part4/darktheme-personeditdialog.png)

3. Selecciona el botón OK y elige *Default Button* en la vista *Properties*. Eso cambiará su color y lo convertirá en el botón "por defecto", el que se ejecutará si el usuario aprieta la tecla *enter*.


#### Añade el CSS a PersonOverview.fxml

1. Abre el archivo `PersonOverview.fxml` en Scene Builder. Selecciona el `AnchorPane` raíz en la sección *Hierarchy* y añade `DarkTheme.css` a sus `Stylesheets`.

2. A estas alturas deberías haber observado algunos cambios: La tabla y los botones son negros. Las clases de estilo `.table-view` y `.button` de `modena.css` se aplican automáticamente a la tabla y los botones. Ya que hemos redefinido (sobreescrito) algunos de esos estilos en nuestro propio CSS, los nuevos estilos se aplican automáticamente.

3. Posiblemente tengas que ajustar el tamaño de los botones para que se muestre todo el texto.

4. Selecciona el panel `AnchorPane` de la derecha, dentro del `SplitPane`.   
![Background Style Select](/assets/library/javafx-8-tutorial/part4/background-style-select.png)   

5. Ves a la vista *Properties* y elige `background` como clase de estilo. El fondo debería volverse negro.   
![Background Style](/assets/library/javafx-8-tutorial/part4/background-style.png)


#### Etiquetas con un estilo diferente

En este momento, todas las etiquetas en el lado derecho tienen el mismo tamaño. Ya tenemos definidos en el CSS unos estilos denominados `.label-header` y `.label-bright` que vamos a usar para personalizar la apariencia de las etiquetas.

1. Selecciona la etiqueta (`Label`) *Person Details* y añade `label-header` como clase de estilo.   
![Label Header Style](/assets/library/javafx-8-tutorial/part4/label-header-style.png)

2. A cada etiqueta en la columna de la derecha (donde se muestran los detalles de una persona), añade la clase de estilo `label-bright`.   
![Label Bright Style](/assets/library/javafx-8-tutorial/part4/label-bright-style.png)


*****


## Añadiendo un icono a la aplicación

Ahora mismo nuestra aplicación utiliza el icono por defecto para la barra de título y la barra de tareas:

![Default Icon](/assets/library/javafx-8-tutorial/part4/default-app-icon.png)

Quedaría mucho mejor con un icono propio:

![Custom Icon](/assets/library/javafx-8-tutorial/part4/custom-app-icon.png)


### El archivo de icono

Un posible sitio para obtener iconos gratuitos es [Icon Finder](http://www.iconfinder.com). Yo por ejemplo descargué este [icono de libreta de direcciones](http://www.iconfinder.com/icondetails/86957/32/).

Crea una carpeta dentro de tu aplicación llamado **resources** y añádele una subcarpeta para almacenar imágenes, llámala **images**. Pon el icono que hayas elegido dentro de la carpeta de imágenes. La estructura de directorios de tu carpeta debe tener un aspecto similar a este:

![Custom Icon File](/assets/library/javafx-8-tutorial/part4/custom-icon-file.png)


### Establece el icono de la escena principal

Para establecer el icono de nuestra escena debemos añadir la línea de código siguiente al método `start(...)` dentro de `MainApp.java`


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

También puedes añadir un icono a la escena que contiene la venta de edición de los detalles de una persona.


### ¿Qué es lo siguiente?

En [Tutorial Parte 5](/library/javafx-8-tutorial/es/part5/) añadiremos la capacidad de almacenar datos mediante XML.


##### Otros artículos que podrían resultarte de interés

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

