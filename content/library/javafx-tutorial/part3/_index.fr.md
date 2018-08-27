+++
title = "Partie 3 : interaction avec l'utilisateur"
date = 2014-04-24
updated = 2015-04-15
description = "Réagir aux changements de sélection dans le JavaFX TableView. Ajouter, modifier et supprimer des éléments de la table et valider l'entrée de l'utilisateur."
image = "addressapp-part3.png"
prettify = true
comments = true 
commentsIdentifier = "/library/javafx-8-tutorial/fr/part3/"
aliases = [ 
  "/library/javafx-8-tutorial/fr/part3/"
]
weight = 3

[[sidebars]]
header = "Téléchargez les sources"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Projet Eclipse relatif à la partie 3 <em>(JDK 8u40 requis au minimum)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-3.zip"
+++

![Screenshot AddressApp Part 3](addressapp-part3.png)


## Topics in Part 3

* **Réagir aux changements de sélections** dans le tableau.
* Ajouter les fonctionnalités aux boutons **add**, **edit** et **remove**.
* Créer une **fenêtre de dialogue** personnalisée pour éditer une personne.
* **Valider les entrées de l'utilisateur**.


*****


## Réagir aux sélections dans le tableau

Évidemment, nous n'avons pas utilisé la droite de notre application, pour l'instant. L'idée est de montrer les détails d'une personne sur le côté juste quand l'utilisateur sélectionne une personne dans la liste.

Premièrement, ajoutons une nouvelle méthode dans `PersonOverviewController` qui nous aidera à remplir les labels avec les données d'une `Person`

Créons une méthode appelée `showPersonDetails(Person person)`. Parcourons tous les labels et définissons le texte en utilisant `setText(...)` avec les détails de la personne. Si `null` est passé en paramètre, tous les labels devront être réinitialisés.

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

### Convertir Birthday Date en un String


Vous aurez remarqué que nous pouvons mettre `birthday` dans le `Label` car il est du type `LocalDate` et non un `String`. Nous devons d'abord formater la date.

Nous allons utiliser la conversion à partir de `LocalDate` et `String` et vice-versa en plusieurs endroits du code. C'est un bon exercice de créer une classe auxiliaire avec une méthode `static` pour cela. Nous allons l'appeler `DateUtil` et la placer dans un package séparé appelé `ch.makery.address.util`:

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
<strong>Astuce:</strong> Vous pouvez changer le format de la date en changeant le
`DATE_PATTERN`. Pour tous les formats possibles voir <a class="alert-link" href="http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">DateTimeFormatter</a>.
</div>


#### Utiliser le DateUtil

Maintenant nous devons utiliser notre tout nouveau `DateUtil` dans la méthode `showPersonDetails` de `PersonOverviewController`. Remplaçons le *TODO* que nous avions ajouté par la ligne suivante:

<pre class="prettyprint lang-java">
birthdayLabel.setText(DateUtil.format(person.getBirthday()));
</pre>


### Ecouter les changements dans la sélection du tableau

Pour être informé quand l'utilisateur sélectionne une personne dans la tableau correspondant, nous avons besoin *d'écouter les changements*.

Il y a une interface dans JavaFX appelée [`ChangeListener`](http://docs.oracle.com/javase/8/javafx/api/) comprenant une méthode nommé `changed(...)`. Cette méthode possède trois paramètres: `observable`, `oldValue`, et `newValue`.

Nous allons créer un `ChangeListener` via les *expressions lambda* introduite dans Java 8. Ajoutons quelques lignes à la méthode `initialize()`dans la class `PersonOverviewController`. Cela ressemble maintenant à ceci:

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

Avec `showPersonDetails(null);` nous réinitialisons les détails de la personne. 

Avec `personTable.getSelectionModel...` nous obtenons la *selectedItemProperty* de la table de personnes et lui ajoutons un listener. Chaque fois que l'utilisateur sélectionne une personne dans la table, notre *expression lambda* est exécutée.  Nous prenons la personne nouvellement sélectionnée pour la transmettre à la méthode `showPersonDetails(...)`.

Essayez de **lancer l'application** à ce stade. Vérifiez que lorsque vous sélectionnez une personne dans la table, les détails de celle-ci soient affichés à droite.

Si quelque chose ne fonctionne pas, vous pouvez comparer votre classe `PersonOverviewController` avec [PersonOverviewController.java](PersonOverviewController.java).


*****

## Le bouton Delete

Notre interface utilisateur contient déjà un bouton de suppression mais sans aucune fonctionnalité. Nous pouvons choisir l'action d'un bouton à l'intérieur du *Scene Builder *. 
Toute méthode dans notre contrôleur qui est annotée avec `@ FXML` (ou est publique) est accessible par le *Scene Builder*. Ainsi, nous allons ajouter d'abord une méthode de suppression à la fin de notre classe `PersonOverviewController`:


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

Maintenant, ouvrez le fichier `PersonOverview.fxml` dans *SceneBuilder*. Sélectionnez le bouton *Delete*, ouvrez le groupe *Code* et choisissez `handleDeletePerson` dans la liste déroulante de ** On Action **.

![On Action](handle-delete.png)

<div class="alert alert-info">
<strong>N'oubliez pas: </strong> Après avoir modifié un fichier FXML à partir de Scene Builder vous pourriez avoir besoin de rafraîchir le projet dans Eclipse pour que les modifications soient appliquées.
</div>


### Gestion des erreurs

Si vous lancez l'application à ce stade, vous devriez être capable de supprimer la personne sélectionnée de la table. Cependant, qu'arriverait-il si vous **cliquiez sur le bouton supprimer en ayant sélectionné aucune personne** dans la table?

Il y aura un `ArrayIndexOutOfBoundsException` parce qu'il ne pouvait pas enlever un objet  personne à l'index `-1`. L'index `-1` a été retourné par `getSelectedIndex()` - ce qui signifie qu'il n'y avait pas de sélection.

Ignorer de telles erreurs n'est pas très recommandable. Nous devrions laisser l'utilisateur savoir qu'il ou elle doit sélectionner une personne avant d'appuyer sur le bouton supprimer. (Il serait encore mieux de désactiver le bouton de sorte que l'utilisateur n'ait même pas la chance de faire quelque chose de mal.)

Après quelques modifications apportées à la méthode `handleDeletePerson()`, nous pouvons afficher une simple boîte de dialogue, chaque fois que l'utilisateur appuiera sur le bouton supprimer sans aucune personne sélectionnée au préalable dans la table:


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
Pour plus d'exemples sur comment utiliser les boîtes de dialogue, lisez mon article sur <a class="alert-link" href="/blog/javafx-dialogs-official/">JavaFX Dialogs</a>.
</div>


*****


## Les dialogues Nouveau et Editer

Les actions Nouveau et Editer demandent un peu plus de travail. Nous allons avoir besoin d'une boîte de dialogue modifiée (ce qui veut dire un nouveau `Stage`) avec un formulaire pour demander à l'utilisateur des détails à propos d'une personne.


### Conception de la boîte de dialogue

1. Créer un nouveau fichier fxml appelé `PersonEditDialog.fxml` à l'intérieur du package *view*.   
![Create Edit Dialog](person-edit-dialog1.png)

2. Utilisez un `GridPane`, des `Label`, des `TextField` et des `Button` pour créer une boîte de dialogue comme suit:   
![Edit Dialog](person-edit-dialog2.png)


### Créer le Contrôleur

Créez le contrôleur de la boîte de dialogue comme `PersonEditDialogController.java`:

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

De petites choses sont à noter concernant ce contrôleur:

* La méthode `setPerson(...)` peut être appelée par une autre classe pour définir la personne qui doit être éditée.
* Quand l'utilisateur clique sur le bouton OK, la méthode `handleOk()` est appelée. Tout d'abord, une certaine validation est effectuée en appelant la méthode `isInputValid ()`. Si et seulement si la validation est réussie, l'objet personne est rempli avec les données entrées par l'utilisateur. Ces changements vont directement être appliqués à l'objet personne passé en paramètre de `setPerson(...)`!
* Le booléen `okClicked` est utilisé de sorte que l'appelant peut déterminé si l'utilisateur a cliqué sur le bouton OK ou Annuler.


### Lier la vue et le contrôleur

Avec la vue (FXML) et le contrôleur enfin créés, nous avons besoin de les lier.

1. Ouvrez le fichier `PersonEditDialog.fxml`.
2. Dans le groupe *Controller* à gauche sélectionnez `PersonEditDialogController` comme classe contrôleur.
3. Définissez les **fx:id** de tous les `TextField` avec les champs du contrôleur correspondant.
4. Définissez les **onAction** des deux boutons aux méthodes handler correspondantes.


### Ouvrir la boîte de dialogue

Ajoutez une méthode pour charger et afficher la boîte de dialogue d'édition de personnes dans notre `MainApp`:


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

Ajoutez les méthodes suivantes à la classe `PersonOverviewController`. Ces méthodes vont appeler `showPersonEditDialog(...)`  de `MainApp` lorsque l'utilisateur clique sur les boutons *Nouveau* ou *Editer*.


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

Ouvrez le fichier `PersonOverview.fxml` dans Scene Builder. Choisissez les méthodes correspondantes dans *On Action* pour les boutons Nouveau et Editer.


*****

## Terminé !

Vous devriez avoir une *application d'adresses* fonctionnelle dorénavant. L'application est capable d'ajouter, d'éditer et de supprimer des personnes. Il y a même quelques validations pour les champs textes pour éviter les mauvaises manipulations des utilisateurs.

J'espère que les concepts et la structure de cette application vous permettra de bien démarrer la rédaction de votre propre application! Amusez-vous bien.


### Et après ?

Dans le [Tutorial Part 4](/fr/library/javafx-tutorial/part4/) nous allons ajouter quelques styles CSS.


##### Voici quelques articles qui pourraient aussi vous intéresser :

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
