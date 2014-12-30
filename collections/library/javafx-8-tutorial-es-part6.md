---
layout: article
title: "Tutorial JavaFX 8 - Parte 6: Gráficos estadísticas"
date: 2014-09-17 00:00
slug: javafx-8-tutorial/es/part6
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-es-part6.md
description: "Aprende a crear un gráfico de barras en JavaFX."
image: /assets/library/javafx-8-tutorial/part6/addressapp-part6.png
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
  - text: "Parte 5: Persistencia de datos con XML"
    link: /library/javafx-8-tutorial/es/part5/
    paging: 5
  - text: "Parte 6: Gráficos estadísticos"
    link: /library/javafx-8-tutorial/es/part6/
    paging: 6
    active: true
  - text: "Parte 7: Despliegue"
    link: /library/javafx-8-tutorial/es/part7/
    paging: 7
- header: "Código fuente"
  body:
  - text: Parte 6 proyecto Eclipse <em>(requiere al menos JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-6.zip
    icon-css: fa fa-fw fa-download
- header: Lenguajes
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-part6/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part6/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part6/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part6/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/part6/
    icon-css: fa fa-fw fa-globe
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/part6/
    icon-css: fa fa-fw fa-globe
---

![Screenshot AddressApp Part 6](/assets/library/javafx-8-tutorial/part6/addressapp-part6.png)


## Contenidos en Parte 6

* Creando un **Gráfico estadístico** para mostrar la distribución de meses de nacimiento.


*****

## Estadísticas sobre fecha de nacimiento

Las personas en nuestra libreta de direcciones tienen una fecha de nacimiento. ¿No sería bonito tener algunas estadísticas sobre los meses en que  celebran el cumpleaños?.

Usaremos un **Gráfico de barras** conteniendo una barra para cada mes. Cada barra mostrará cuantas personas en nuestra libreta cumplen años ese mes en particular.


## La vista FXML de estadísticas

1. Empezamos creando un archivo `BirthdayStatistics.fxml` dentro del paquete `ch.makery.address.view` (*Clic derecho | New | Other... | New FXML Document*).   
![Birthday Statistics FXML](/assets/library/javafx-8-tutorial/part6/birthday-statistics-fxml.png)

2. Abre el archivo `BirthdayStatistics.fxml` en Scene Builder.

3. Elige el panel raíz `AnchorPane`. En la sección de *Layout* establece la anchura y altura preferidas (*Pref Width*, *Pref Height*) en 620 y 450 respectivamente.

4. Añade un componente `BarChart` al `AnchorPane`.

5. Clic derecho sobre el elemento `BarChart` y elige *Fit to Parent*.

6. Guarda el archivo FXML, vuelve a Eclipse y refresca el proyecto (F5).

Antes de volver a Scene Builder vamos a crear el controlador y a realizar las conexiones necesarias en nuestra `MainApp`.


## El controlador para las estadísticas

En el paquete de vistas `ch.makery.address.view` crea una nueva clase Java denominada `BirthdayStatisticsController.java`.

Veamos primero el controlador completo antes de explicarlo:


##### BirthdayStatisticsController.java

<pre class="prettyprint lang-java">
package ch.makery.address.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import ch.makery.address.model.Person;

/**
 * The controller for the birthday statistics view.
 * 
 * @author Marco Jakob
 */
public class BirthdayStatisticsController {

    @FXML
    private BarChart&lt;String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList&lt;String> monthNames = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
    }

    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    public void setPersonData(List&lt;Person> persons) {
    	// Count the number of people having their birthday in a specific month.
        int[] monthCounter = new int[12];
        for (Person p : persons) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series&lt;String, Integer> series = new XYChart.Series&lt;>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i &lt; monthCounter.length; i++) {
        	series.getData().add(new XYChart.Data&lt;>(monthNames.get(i), monthCounter[i]));
        }
        
        barChart.getData().add(series);
    }
}
</pre>


#### Como funciona el controlador

1. El controlador necesitará acceso a dos elementos de nuestro archivo FXML:
   * `barChar`: Tiene los tipos `String` e `Integer`. El tipo `String` se utiliza para el mes en el eje-X y el tipo `Integer` se utiliza para el número de personas en un mes determinado. 
   * `xAxis`: Lo usaremos para añadir las nombres de los meses   

2. El método `initialize()` rellena el eje-X con la lista de todos los meses.

3. El método `setPersonData(...)`será accedido por la clase `MainApp` para establecer los datos de personas. Este método recorre todas las personas y cuenta los nacimientos por mes. Entonces añade `XYChart.Data` para cada mes a las series de datos. Cada objeto `XYChart.Data` representará una barra en el gráfico.


*****

## Conectando vista y controlador

1. Abre `BirthdayStatistics.fxml` en Scene Builder.

2. En la sección *Controller* pon `BirthdayStatisticsController` como controlador.

3. Selecciona el componente `BarChart` y pon `barChart` en la propiedad *fx:id*  (sección *Code*).

4. Elige el componente `CategoryAxis` y pon `xAxis` como propiedad *fx:id*.   
![Category Axis](/assets/library/javafx-8-tutorial/part6/category-axis.png)

5. Puedes añadir un título al componente `BarChart` (en *Properties*) para mejorar la apariencia.



*****


## Conectando vista y controlador con MainApp

Para el gráfico de estadísticas usaremos el mismo mecanismo que utilizamos para el diálogo de edición de personas, una ventana de diálogo emergente.

Añade el siguiente método a tu clase `MainApp`:


<pre class="prettyprint lang-java">
/**
 * Opens a dialog to show birthday statistics.
 */
public void showBirthdayStatistics() {
    try {
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Birthday Statistics");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller.
        BirthdayStatisticsController controller = loader.getController();
        controller.setPersonData(personData);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>

Todo está dispuesto, pero todavía no tenemos nada para invocar al nuevo método `showBirthdayStatistics()`. Afortunadamente ya tenemos un menú en en la vista `RootLayout.fxml` que puede ser usado para estos fines.


### Muestra el menú de estadísticas de cumpleaños
En tu `RootLayoutController` añade el siguiente método para gestionar las pulsaciones sobre el ítem de menú *Show Statistics*: 

<pre class="prettyprint lang-java">
/**
 * Opens the birthday statistics.
 */
@FXML
private void handleShowBirthdayStatistics() {
  mainApp.showBirthdayStatistics();
}
</pre>

Ahora, abre `RootLayout.fxml` en Scene Builder. Crea el menú *Statistics* con un ítem denominado *Show Statistics*:

![Show Statistics Menu](/assets/library/javafx-8-tutorial/part6/show-statistics-menu.png)

Elige el ítem de menú *Show Statistics* y establece `handleShowBirthdayStatistics` en su campo `On Action` (sección *Code*).   

![Show Statistics On Action](/assets/library/javafx-8-tutorial/part6/show-statistics-on-action.png)

Vuelve a Eclipse, refresca el proyecto y **pruébalo**.


*****

## Más información sobre gráficos JavaFX

Un buen sitio para obtener más información es el tutorial oficial de Oracle: [Working with JavaFX Charts](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/charts.htm).


### ¿Qué es lo siguiente?

En la última parte del tutorial, [Part 7](/library/javafx-8-tutorial/es/part7/), vamos por fin a desplegar nuestra aplicación (es decir, la empaquetaremos y la haremos llegar a los usuarios).


##### Otros artículos que podrías encontrar de interés

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

