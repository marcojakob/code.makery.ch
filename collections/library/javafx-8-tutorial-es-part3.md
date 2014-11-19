---
layout: article
title: "Tutorial JavaFX 8 - Parte 3: Interacción con el usuario"
date: 2014-09-17 00:00
slug: javafx-8-tutorial/es/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-es-part3.md
description: "Respuesta a cambios de selección en un TableView. Añadir, editar y borrar ítems de la tabla y validar la entrada del usuario."
image: /assets/library/javafx-8-tutorial/part3/addressapp-part3.png
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
    active: true
  - text: "Parte 4: Hojas de estilo CSS"
    link: /library/javafx-8-tutorial/es/part4/
    paging: 4
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
  - text: Parte 3 proyecto Eclipse <em>(requiere al menos JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-3.zip
    icon-css: fa fa-fw fa-download
- header: Lenguajes
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-part3/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part3/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part3/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part3/
    icon-css: fa fa-fw fa-globe
---

![Screenshot AddressApp Part 3](/assets/library/javafx-8-tutorial/part3/addressapp-part3.png)


## Contenidos en Parte 3

* **Respuesta a cambios en la selección** dentro de la tabla.
* Añade funcionalidad de los botones **añadir**, **editar**, y **borrar**.
* Crear un **diálogo emergente** (popup dialog) a medida para editar un contacto.
* **Validación de la entrada del usuario**.


*****


## Respuesta a cambios en la selección de la Tabla

Todavía no hemos usado la parte derecha de la interfaz de nuestra aplicación. La intención es usar esta parte para mostrar los detalles de la persona seleccionada por el usuario en la tabla.

En primer lugar vamos a añadir un nuevo método dentro de `PersonOverviewController` que nos ayude a rellenar las etiquetas con los datos de una sola persona.

Crea un método llamado `showPersonDetails(Person person)`. Este método recorrerá todas las etiquetas y establecerá el texto con detalles de la persona usando `setText(...)` . Si en vez de una instancia de `Person` se pasa `null` entonces las etiquetas deben ser borradas.

##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Fills all text fields to show details about the person.
 * If the specified person is null, all text fields are cleared.
 * 
 * @param person the person or null
 */
private void showPersonDetails(Person person) {
    if (person != null) {
        // Fill the labels with info from the person object.
        firstNameLabel.setText(person.getFirstName());
        lastNameLabel.setText(person.getLastName());
        streetLabel.setText(person.getStreet());
        postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
        cityLabel.setText(person.getCity());

        // TODO: We need a way to convert the birthday into a String! 
        // birthdayLabel.setText(...);
    } else {
        // Person is null, remove all the text.
        firstNameLabel.setText("");
        lastNameLabel.setText("");
        streetLabel.setText("");
        postalCodeLabel.setText("");
        cityLabel.setText("");
        birthdayLabel.setText("");
    }
}
</pre>


### Convierte la fecha de nacimiento en una cadena

Te darás cuenta de que no podemos usar el atributo `birthday` directamente para establecer el valor de una `Label` porque se requiere un `String`, y `birthday` es de tipo `LocalDate`. Así pues necesitamos convertir `birthday` de `LocalDate` a `String`.

En la práctica vamos a necesitar convertir entre `LocalDate` y `String`  en varios sitios y en ambos sentidos. Una buena práctica es crear una clase auxiliar con métodos estáticos (`static`) para esta finalidad. Llamaremos a esta clase `DateUtil` y la ubicaremos una paquete separado denominado `ch.makery.address.util`:


##### DateUtil.java

<pre class="prettyprint lang-java">
package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling dates.
 * 
 * @author Marco Jakob
 */
public class DateUtil {
	
	/** The date pattern that is used for conversion. Change as you wish. */
	private static final String DATE_PATTERN = "dd.MM.yyyy";
	
	/** The date formatter. */
	private static final DateTimeFormatter DATE_FORMATTER = 
			DateTimeFormatter.ofPattern(DATE_PATTERN);
	
	/**
     * Returns the given date as a well formatted String. The above defined 
     * {@link DateUtil#DATE_PATTERN} is used.
     * 
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Converts a String in the format of the defined {@link DateUtil#DATE_PATTERN} 
     * to a {@link LocalDate} object.
     * 
     * Returns null if the String could not be converted.
     * 
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String dateString) {
        try {
        	return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks the String whether it is a valid date.
     * 
     * @param dateString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {
    	// Try to parse the String.
    	return DateUtil.parse(dateString) != null;
    }
}
</pre>

<div class="alert alert-info">
**Truco:** Puedes cambiar el formato de la fecha cambiando el patrón
`DATE_PATTERN`. Para conocer los diferentes tipos de formato consulta  <a class="alert-link" href="http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">DateTimeFormatter</a>.
</div>


#### Utilización de la clase DateUtil

Ahora necesitamos utilizar la nueva clase `DateUtil` en el método `showPersonDetails` de `PersonOverviewController`. Sustituye el *TODO* que habíamos añadido con la línea siguiente:

<pre class="prettyprint lang-java">
birthdayLabel.setText(DateUtil.format(person.getBirthday()));
</pre>


### Detecta cambios de selección en la tabla

Para enterarse de que el usuario ha seleccionado a un persona en la tabla de contactos, necesitamos escuchar los cambios. Esto se consigue mediante la implementación de un interface de JavaFX que se llama [`ChangeListener`](http://docs.oracle.com/javase/8/javafx/api/) with one method called `changed(...)`. Este método solo tiene tres parámetros: `observable`, `oldValue`, y `newValue`.

En Java 8 la forma más elegante de implementar una interfaz con un único método es mediante una *lambda expression*. Añadiremos algunas líneas al método `initialize()` de  `PersonOverviewController`. El código resultante se asemejará al siguiente:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
@FXML
private void initialize() {
    // Initialize the person table with the two columns.
    firstNameColumn.setCellValueFactory(
            cellData -> cellData.getValue().firstNameProperty());
    lastNameColumn.setCellValueFactory(
            cellData -> cellData.getValue().lastNameProperty());

    // Clear person details.
    showPersonDetails(null);

    // Listen for selection changes and show the person details when changed.
    personTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showPersonDetails(newValue));
}
</pre>

Con `showPersonDetails(null);` borramos los detalles de una persona. 

Con `personTable.getSelectionModel...` obtenemos la *selectedItemProperty* de la tabla de personas, y le añadimos un *listener*. Cuando quiera que el usuario seleccione a una persona en la table, nuestra *lambda expression* será ejecutada: se toma la persona recien seleccionada y se le pasa al método `showPersonDetails(...)` method.

Intenta **ejecutar tu aplicación** en este momento. Comprueba que cuando seleccionas a una persona, los detalles sobre esta son mostrados en la parte derecha de la ventana.

Si algo no funciona, puedes comparar tu clase `PersonOverviewController` con [PersonOverviewController.java](/assets/library/javafx-8-tutorial/part3/PersonOverviewController.java).


*****

## El botón de borrar (Delete)

Nuestro interfaz de usuario ya contiene un botón de borrar, pero sin funcionalidad. Podemos seleccionar la acción a ejecutar al pulsar un botón desde el *Scene Builder*. Cualquier método de nuestro controlador anotado con `@FXML` (o declarado como *public*) es accesible desde *Scene Builder*. Así pues, empecemos añadiendo el método de borrado al final de nuestra clase`PersonOverviewController`:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Called when the user clicks on the delete button.
 */
@FXML
private void handleDeletePerson() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    personTable.getItems().remove(selectedIndex);
}
</pre>

Ahora, abre el archivo `PersonOverview.fxml` en el *SceneBuilder*. Selecciona el botón *Delete*, abre el apartado *Code* y pon `handleDeletePerson` en el menú desplegable denominado **On Action**.

![On Action](/assets/library/javafx-8-tutorial/part3/handle-delete.png)


### Gestión de errores

Si ejecutas tu aplicación en este punto deberías ser capaz de borrar personas de la tabla. Pero, ¿qué ocurre si **pulsas el botón de borrar sin seleccionar a nadie** en la tabla.

Se produce un error de tipo `ArrayIndexOutOfBoundsException` porque no puede borrar una persona en el índice `-1`, que es el valor devuelto por el método `getSelectedIndex()` - cuando no hay ningún elemento seleccionado.

Ignorar semejante error no es nada recomendable. Deberíamos hacerle saber al usuario que tiene que seleccionar una persona previamente para poderla borrar (incluso mejor sería deshabilitar el botón para que el usuario ni siquiera tenga la oportunidad de realizar una acción incorrecta).

Vamos a añadir un diálogo emergente para informar al usuario. Desafortunadamente no hay componentes para diálogos incluidos en JavaFX 8. Para evitar tener que crearlos manualmente podemos **añadir una librería** que ya los incluya ([Dialogs](/blog/javafx-8-dialogs/)): 

1. Descarga este [controlsfx-8.0.6_20.jar](https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/controlsfx-8.0.6_20.jar) (también se puede obtener de la [página web de ControlsFX](http://fxexperience.com/controlsfx/)).   
**Importante: La versión de ControlsFX debe ser la `8.0.6_20` o superior para que funcione con `JDK 8u20` debido a un cambio crítico en esa versión.**
2. Crea una subcarpeta **lib** dentro del proyecto y coloca dentro del archivo jar.
3. Añade la librería al **classpath** de tu proyecto: En Eclipse se puede hacer mediante *clic-derecho sobre el archivo jar* | *Build Path* | *Add to Build Path*. Ahora Eclipse ya sabe donde encontrar esa librería.

![ControlsFX Libaray](/assets/library/javafx-8-tutorial/part3/controlsfx-library.png)

Con algunos cambios en el método `handleDeletePerson()` podemos mostrar una simple ventana de diálogo emergente en el caso de que el usuario pulse el botón Delete sin haber seleccionado a nadie en la tabla de contactos:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Called when the user clicks on the delete button.
 */
@FXML
private void handleDeletePerson() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        personTable.getItems().remove(selectedIndex);
    } else {
        // Nothing selected.
        Dialogs.create()
            .title("No Selection")
            .masthead("No Person Selected")
            .message("Please select a person in the table.")
            .showWarning();
    }
}
</pre>

<div class="alert alert-info">
Para ver más ejemplos de utilización de ventanas de diálogo, consulta <a class="alert-link" href="/blog/javafx-8-dialogs/">JavaFX 8 Dialogs</a>.
</div>



*****


## Diálogos para crear y editar contactos

Las acciones de editar y crear nuevo contacto necesitan algo más de elaboración: vamos a necesitar una ventana de diálogo a medida (es decir, un nuevo `stage`) con un formulario para preguntar al usuario los detalles sobre la persona.


### Diseña la ventana de diálogo

1. Crea un nuevo archivo fxml llamado `PersonEditDialog.fxml` dentro del paquete *view*.   
![Create Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog1.png)

2. Usa un panel de rejilla (`GridPane`), etiquetas (`Label`),  campos de texto (`TextField`) y botones (`Button`) para crear una ventana de diálogo como la siguiente:   
![Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog2.png)   

*Si quieres puedes descargar el código desde [PersonEditDialog.fxml](/assets/library/javafx-8-tutorial/part3/PersonEditDialog.fxml).* 


### Create the Controller

Crea el controlador para la ventana de edición de personas y llámalo `PersonEditDialogController.java`:

##### PersonEditDialogController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
        	Dialogs.create()
		        .title("Invalid Fields")
		        .masthead("Please correct invalid fields")
		        .message(errorMessage)
		        .showError();
            return false;
        }
    }
}
</pre>

Algunas cuestiones relativas a este controlador:

* El método `setPerson(...)` puede ser invocado desde otra clase para establecer la persona que será editada.
* Cuando el usuario pula el botón OK, el método `handleOk()` es invocado. Primero se valida la entrada del usuario mediante la ejecución del método `isInputValid()`. Sólo si la validación tiene éxito el objeto persona es modificado con los datos introducidos por el usuario. Esos cambios son aplicados directamente sobre el objeto pasado como argumento del método `setPerson(...)`!
* El método boolean `okClicked` se utiliza para determinar si el usuario ha pulsado el botón OK o el botón Cancel.


### Enlaza la vista y el controlador 

Una vez creadas la vista (FXML) y el controlador, necesitamos vincular el uno con el otro:

1. Abre el archivo `PersonEditDialog.fxml`.
2. En la sección *Controller* a la izquierda selecciona `PersonEditDialogController` como clase de control.
3. Establece el campo **fx:id** de todas los `TextField` con los identificadores  de los atributos del controlador correspondientes.
4. Especifica el campo **onAction** de los dos botones con los métodos del controlador correspondientes a cada acción.



### Abriendo la ventana de diálogo

Añade un método para cargar y mostrar el método de edición de una persona dentro de la clase `MainApp`:   


##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * Opens a dialog to edit details for the specified person. If the user
 * clicks OK, the changes are saved into the provided person object and true
 * is returned.
 * 
 * @param person the person object to be edited
 * @return true if the user clicked OK, false otherwise.
 */
public boolean showPersonEditDialog(Person person) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        PersonEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPerson(person);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
</pre>

Añade los siguientes métodos a la clase `PersonOverviewController`. Esos métodos llamarán al método `showPersonEditDialog(...)` desde `MainApp` cuando el usuario pulse en los botones *new* o *edit*.   

##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Called when the user clicks the new button. Opens a dialog to edit
 * details for a new person.
 */
@FXML
private void handleNewPerson() {
    Person tempPerson = new Person();
    boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
    if (okClicked) {
        mainApp.getPersonData().add(tempPerson);
    }
}

/**
 * Called when the user clicks the edit button. Opens a dialog to edit
 * details for the selected person.
 */
@FXML
private void handleEditPerson() {
    Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
    if (selectedPerson != null) {
        boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
        if (okClicked) {
            showPersonDetails(selectedPerson);
        }

    } else {
        // Nothing selected.
        Dialogs.create()
            .title("No Selection")
            .masthead("No Person Selected")
            .message("Please select a person in the table.")
            .showWarning();
    }
}
</pre>

Abre el archivo `PersonOverview.fxml` mediante Scene Builder. Elige los métodos correspondientes en el campo *On Action* para los botones *new* y *edit*.


*****

## ¡Ya está!

Llegados a este punto deberías tener una aplicación de *libreta de contactos* en funcionamiento. Esta aplicación es capaz de añadir, editar y borrar personas. Tiene incluso algunas capacidades de validación para evitar que el usuario introduzca información incorrecta.

Espero que los conceptos y estructura de esta aplicación te permitan empezar tu propia aplicación JavaFX. ¡ Disfruta !


### Qué es lo siguiente?

En [Tutorial Parte 4](/library/javafx-8-tutorial/es/part4/) introduciremos algo de diseño mediante hojas de estilo CSS.


##### Otros artículos que podrían resultarte de interés

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

