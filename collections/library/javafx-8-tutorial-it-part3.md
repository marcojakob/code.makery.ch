---
layout: article
title: "JavaFX 8 Tutorial - Part 3: Interacting with the User"
date: 2014-04-24 00:00
updated: 2015-11-01 00:00
slug: javafx-8-tutorial/it/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-it-part3.md
description: "React to selection changes in the JavaFX TableView. Add, edit and remove items from the table and validate user input."
image: /assets/library/javafx-8-tutorial/part3/addressapp-part3.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articoli in questa serie"
  body:
  - text: "Introduzione"
    link: /library/javafx-8-tutorial/it/
    paging: Intro
  - text: "Parte 1: Scene Builder"
    link: /library/javafx-8-tutorial/it/part1/
    paging: 1
  - text: "Parte 2: Model and TableView"
    link: /library/javafx-8-tutorial/it/part2/
    paging: 2
  - text: "Parte 3: Interazione con l'utente"
    link: /library/javafx-8-tutorial/it/part3/
    paging: 3
    active: true
  - text: "Parte 4: CSS Styling"
    link: /library/javafx-8-tutorial/it/part4/
    paging: 4
  - text: "Parte 5: Conservazione dati come XML"
    link: /library/javafx-8-tutorial/it/part5/
    paging: 5
  - text: "Parte 6: Grafico delle statistiche"
    link: /library/javafx-8-tutorial/it/part6/
    paging: 6
  - text: "Parte 7: Deployment"
    link: /library/javafx-8-tutorial/it/part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: Part 3 as Eclipse Project <em>(requires at least JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-3.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Languages
  collection: library
  item: javafx-8-tutorial
  part: part3
  active: it
---

![Screenshot AddressApp Part 3](/assets/library/javafx-8-tutorial/part3/addressapp-part3.png)


## Argomenti della parte 3

* **Reagire alle variazioni di selezione** nella tabella.
* Aggiunta delle funzionalità ai pulsanti **add**, **edit**, e **remove**.
* Creazione di un **popup dialog** per modificare una persona.
* **Validazione dell'input dell'utente**.


*****


## Reagire alla selezione nella tabella.

Ovviamente non abbiamo ancora utilizzato la parte funzionale della nostra applicazione. L'idea è di visualizzare i dettagli di una persona sulla parte destra della finestra quando un utente seleziona la persona dalla tabella.

Primo, aggiungiamo un nuovo metodo dentro a `PersonOverviewController` che ci aiuterà a riempire i label con i dati provenienti da un sigolo `Person`.

Create un metodo chiamato `showPersonDetails(Person person)`. Andate all'interno di tutti i label e settate il testo usando `setText(...)` con i dettagli provenienti dall'oggetto persona. Se è passato `null` come parametro, tutti i label devono essere puliti.


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


### Convertire la data di compleanno in una stringa

Noterai che non possiamo settare `birthday` all'interno di `Label` perché è del tipo `LocalDate` e non `String`. Prima dobbiamo formattare la data.

Useremo la conversione da `LocalDate` a `String` e vice versa in diversi posti. E' buona norma creare una classe di supporto(helper) con metodi `static` per questo. La chiameremo `DateUtil` e la posizioneremo in un package separato chiamato `ch.makery.address.util`:


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
**Hint:** Puoi cambiare il formato della data cambiando
`DATE_PATTERN`. Per tutti i formati possibili consulta <a class="alert-link" href="http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">DateTimeFormatter</a>.
</div>


#### Usare DateUtil

Adesso dobbiamo usare la nostra nuova `DateUtil` nel metodo `showPersonDetails` di `PersonOverviewController`. Sostituisci il *TODO* che avevamo aggiunto con la seguente linea:

<pre class="prettyprint lang-java">
birthdayLabel.setText(DateUtil.format(person.getBirthday()));
</pre>


### Ascoltare i cambiamenti di selezione della tabella

Per essere informati quando un utente seleziona una persona nella tabella delle persone, dobbiamo metterci *in ascolto dei cambiamenti*.

C'è un interfaccia nelle JavaFX chiamata [`ChangeListener`](http://docs.oracle.com/javase/8/javafx/api/) con un metodo chiamato `changed(...)`. Il metodo ha 3 parametri: `observable`, `oldValue`, e `newValue`.

Creeremo tale `ChangeListener` usando una Java 8 *espressione lambda*. Aggiungiamo qualche linea nel metodo `initialize()` in `PersonOverviewController`. Adesso è diventato come segue:


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

Con `showPersonDetails(null);` resettiamo i dettagli della persona. 

Con `personTable.getSelectionModel...` prendiamo il *selectedItemProperty* della tabella persone e vi aggiungiamo un ascoltatore. Ogni volta che l'utente seleziona una persona nella tabella, la nostra *lespressione ambda* sarà eseguita. Prendiamo la nuova persona selezionata e la passiamo al metodo `showPersonDetails(...)`.

Prova ad **avviare la tua applicazione** a questo punto. Verifica che quando selezioni una persona nella tabella, idettagli di questa persona sono visualizzati sulla destra.

Se qualcosa non funziona, puoi comparare la tua classe `PersonOverviewController` con [PersonOverviewController.java](/assets/library/javafx-8-tutorial/part3/PersonOverviewController.java).


*****

## Il pulsante Elimina

La nostra interfaccia utente contiene anche un pulsante elimina senza nessuna funzionalità. Possiamo selezionare l'azione per un pulsante all'interno di *Scene Builder*. Oggni metodo all'interno del nostro controllore che è annotato con `@FXML` (o è pubblico) è accessibile da *Scene Builder*. Quindi, prima aggiungiamo un metodo elimina alla fine della nostra classe `PersonOverviewController`:


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

Ora, apri il file `PersonOverview.fxml` in *SceneBuilder*. Seleziona il pulsante *Delete*, apri il gruppo *Code* e scegli `handleDeletePerson` nel menu a tendina di **On Action**.

![On Action](/assets/library/javafx-8-tutorial/part3/handle-delete.png)

<div class="alert alert-info">
**Ricorda:** Dopo aver effettuato cambiamenti nel file FXML in Scene Builder dovresti riaggioranre il progetto in Eclipse affinché i cambiamenti siano applicati.
</div>



### Gestire gli errori

Se avvii l'applicazion a questo punto dovresti essere in grado di eliminare una persona dalla tabella. Ma cosa succede se **clicchi il pulsante delete mentre nessuna persona è selezionata** nella tabella? 

Verrà generata una `ArrayIndexOutOfBoundsException` perché non può essere eliminata un elemento persona con l'indice `-1`. L'indice `-1` è restituito da `getSelectedIndex()` - il quale significa che non c'è selezione.

Naturalmente ignorare l'errore non è molto elegante. Dovremmo informare l'utente che deve selezionare una persona prima di eliminarla. (Potrebbe essere meglio se disabilitiamo il pulsante così l'utente non ha mai la possibilità di fare qualcosa di sbagliato).
Aggiungeremo un *popup dialog* per informare l'utente.

Con alcuni cambiamenti al metodo `handleDeletePerson()`, possiamo mostrare un semplice popup dialog ogni volta che l'utente preme il pulsante delete mentre nessuna pesona nella tabella è selezionata:


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
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }
}
</pre>

<div class="alert alert-info">
Per altri esempi su come usare i Dialogs il post sul mio blog a riguardo <a class="alert-link" href="/blog/javafx-dialogs-official/">JavaFX Dialogs</a>.
</div>


*****


## I Dialogs Nuovo e Modifica

Le azioni Nuovo e Modifica richiedono un pò più di impegno: Avremo bisogno di crere un dialog personalizzato (che significa un nuovo stage) con un form per chiedere all'utente i dettagli riguardo la persona.


### Disegnare il Dialog

1. Crea un nuovo file fxml chiamato `PersonEditDialog.fxml` dentro al package *view*.   
![Create Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog1.png)

2. Usa il `GridPane`, `Label`s, `TextField`s e `Button`s per creare un Dialog come il seguente:   
![Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog2.png)   


### Creare il Controllore

Create il controller per il Dialog con il nome `PersonEditDialogController.java`:

##### PersonEditDialogController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}
</pre>

Qualhe informazione riguardo questo controllore:

* Il metodo `setPerson(...)` può essere chiamato da un'altra classe per settare la persona che deve essere modificata.
* Quando l'utente clicca sul pulsante OK, viene chiamato il metodo `handleOk()`. Prima, vengono effettuate alcune validazione del metodo `isInputValid()`. Solo se le validazioni hanno successo, l'oggetto persona viene riempito con i dati che l'utente ha inserito. Questi cambiamenti saranno applicati direttamente all'oggetto person che è stato passato a `setPerson(...)`!
* Il boleano `okClicked` è usato per far si che colui che chiama può determinare se l'utente ha premuto il pulsante Ok oppure Cancel.


### Collegare Vista e Controllore 

Con la vista (FXML) ed il controllore creati dobbiamo procedere al loro collegamento:

1. Apri `PersonEditDialog.fxml`.
2. Nel gruppo *Controller* sul lato sinistro seleziona `PersonEditDialogController` come classe controllore.
3. Setta il campo **fx:id** per tutti i `TextField` il corrispondente campo del controllore.
4. Setta il campo **onAction** dei due pulsanti al corrispondente metodo.



### Aprire il Dialog

Aggiungi un metodo per aggiungere e mostrare il Dialog di modifica della persona all'interno del nostro `MainApp`:   


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

Aggiungi i seguenti metodi in `PersonOverviewController`. Questi metodi chiameranno il `showPersonEditDialog(...)` da `MainApp` quando l'utente clicca i pulsanti *new* o *edit*.   

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
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }
}
</pre>

Apri il file `PersonOverview.fxml` in Scene Builder. Scegli il corrispondente metodo in *On Action* per i pulsanti *New* e *Cancel*.


*****

## Fatto!

Dovresti avere un *Applicazione Rubrica* funzionante adesso. L'applicazione è in grado di aggiungere, modificare ed eliminare persone. Ci sono anche alcune validazioni per i testi per evitare input sbagliati.

Spero che i concetti e la struttura di questa applicazione di permetteranno di cominciare a scrivere le tue applicazioni JavaFX! Buon divertimento.


### Qual'è il prossimo?

Nella [Parte 4 del tutorial](/library/javafx-8-tutorial/it/part4/) aggiungeremo alcuni CSS per lo stile.


##### Alcuni articoli che potresti trovare interessanti

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

