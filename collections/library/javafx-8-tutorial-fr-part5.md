---
layout: article
title: "Tutoriel JavaFX 8 - partie 5 : stockage de données en XML"
date: 2014-04-25 01:00
updated: 2015-04-15 00:00
slug: javafx-8-tutorial/fr/part5
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-fr-part5.md
description: "Enregistrer les données en XML avec JAXB. Apprenez comment utiliser le FileChooser et le menu de JavaFX."
image: /assets/library/javafx-8-tutorial/part5/addressapp-part5.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articles dans ces séries"
  body:
- header: "Les articles dans ce tutoriel"
  body:
  - text: "Introduction"
    link: /library/javafx-8-tutorial/fr/
    paging: Intro
  - text: "Partie 1 : le Scene Builder"
    link: /library/javafx-8-tutorial/fr/part1/
    paging: 1
  - text: "Partie 2 : modèle et TableView"
    link: /library/javafx-8-tutorial/fr/part2/
    paging: 2
  - text: "Partie 3 : interaction avec l'utilisateur"
    link: /library/javafx-8-tutorial/fr/part3/
    paging: 3
  - text: "Partie 4 : style CSS"
    link: /library/javafx-8-tutorial/fr/part4/
    paging: 4
  - text: "Partie 5 : stockage de données en XML"
    link: /library/javafx-8-tutorial/fr/part5/
    paging: 5
    active: true
  - text: "Partie 6 : statistiques graphiques"
    link: /library/javafx-8-tutorial/fr/part6/
    paging: 6
  - text: "Partie 7 : déploiement"
    link: /library/javafx-8-tutorial/fr/part7/
    paging: 7
- header: "Téléchargez les sources"
  body:
  - text: Projet Eclipse relatif à la partie 5 <em>(JDK 8u40 requis au minimum)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-5.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Langues
  collection: library
  item: javafx-8-tutorial
  part: part5
  active: fr
---

![Screenshot AddressApp Part 5](/assets/library/javafx-8-tutorial/part5/addressapp-part5.png)


## Sujets dans la partie 5

* **Données persistantes en XML**
* Utiliser le **FileChooser** de JavaFX
* Utiliser le **Menu** de JavaFX
* Enregistrer le chemin du dernier fichier utilisé dans les **préférences utilisateur**



*****

Pour l'instant, les données de notre application carnet d'adresses résident seulement dans la mémoire vive. Les données sont perdues à chaque fois que nous refermons l'application ! Il est temps de penser à sauvegarder de manière persistante. 


## Enregistrer les préférences utilisateur

Java nous permet d'enregistrer l'état de l'application par le biais d'une classe nommée `Preferences`. Les `Préférences` sont enregistrées dans des endroits différents selon l'OS (p. ex. la base de registre sous Windows). 

Nous ne pourrons pas utiliser les `Préférences` pour sauvegarder tout le carnet d'adresses. Mais elles vont nous permettre d'**enregistrer des états simples de l'application**. Le **chemin du dernier fichier ouvert** fait partie de tout cela. Avec cette information nous pourrons recharger le dernier état de l'application quand l'utilisateur redémarrera l'applicaton. 

Les deux méthodes ci-dessous s'occupent d'enregistrer et de lire les préférences. Ajoutez-les à la fin de votre classe `MainApp` ! 


##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * Returns the person file preference, i.e. the file that was last opened.
 * The preference is read from the OS specific registry. If no such
 * preference can be found, null is returned.
 * 
 * @return
 */
public File getPersonFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    String filePath = prefs.get("filePath", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

/**
 * Sets the file path of the currently loaded file. The path is persisted in
 * the OS specific registry.
 * 
 * @param file the file or null to remove the path
 */
public void setPersonFilePath(File file) {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    if (file != null) {
        prefs.put("filePath", file.getPath());

        // Update the stage title.
        primaryStage.setTitle("AddressApp - " + file.getName());
    } else {
        prefs.remove("filePath");

        // Update the stage title.
        primaryStage.setTitle("AddressApp");
    }
}
</pre>


## Données persistantes en XML

### Pourquoi XML?

Une des façons habituelles de rendre les données persistantes consiste à utiliser une base de données. Les bases de données contiennent des données typées relationnel (dans les tables) or les données que nous voulons enregistrer sont des objets. C'est appelé l'[object-relational impedance mismatch](http://wikipedia.org/wiki/Object-relational_impedance_mismatch). C'est beaucoup de travail de faire correspondre les objets aux tables des bases de données relationelles. Il existe des frameworks qui facilitent la mise en correspondance (p. ex. [Hibernate](http://www.hibernate.org/), le plus populaire) mais il requiert tout de même du travail de mise en place. 

Pour notre modèle de données basique, c'est plus facile d'utiliser le XML. Nous utiliserons une librairie nommée [JAXB](https://jaxb.java.net/) (**J**ava **A**rchitecture for **X**ML **B**inding). JAXB va nous permettre de générer du XML avec quelques lignes de code seulement comme ceci :   

##### Exemple de texte formatté en xml

<pre class="prettyprint lang-xml">
&lt;persons&gt;
    &lt;person&gt;
        &lt;birthday&gt;1999-02-21&lt;/birthday&gt;
        &lt;city&gt;some city&lt;/city&gt;
        &lt;firstName&gt;Hans&lt;/firstName&gt;
        &lt;lastName&gt;Muster&lt;/lastName&gt;
        &lt;postalCode&gt;1234&lt;/postalCode&gt;
        &lt;street&gt;some street&lt;/street&gt;
    &lt;/person&gt;
    &lt;person&gt;
        &lt;birthday&gt;1999-02-21&lt;/birthday&gt;
        &lt;city&gt;some city&lt;/city&gt;
        &lt;firstName&gt;Anna&lt;/firstName&gt;
        &lt;lastName&gt;Best&lt;/lastName&gt;
        &lt;postalCode&gt;1234&lt;/postalCode&gt;
        &lt;street&gt;some street&lt;/street&gt;
    &lt;/person&gt;
&lt;/persons&gt;
</pre>




### Utiliser JAXB

JAXB est déjà inclu dans le JDK. Cela signifie que nous n'avons pas besoin d'inclure une librairie supplémentaire. 

JAXB fournit deux fonctionalités principales : la capacité de faire correspondre (**marshal**) des objets Java en code XML et vice et versa (**unmarshal**). 

Pour que JAXB soit capable de faire la conversion, nous devons préparer notre modèle. 


#### Préparer la classe modèle pour JAXB 

Nos données que nous voulons enregistrer résident dans la variable `personData` dans notre classe `MainApp`. JAXB requiert que nous annotions la classe racine avec `@XmlRootElement`. `personData` est une classe de type `ObservableList` et nous ne pouvons pas ajouter d'annotations à `ObservableList`. Nous devons donc créer une autre classe qui est utilisée seulement pour contenir notre liste de `Persons` en vue de l'enregistrer en XML. 

La nouvelle classe que nous créons est appelée `PersonListWrapper` et elle est insérée dans le package `ch.makery.address.model`. 


##### PersonListWrapper.java

<pre class="prettyprint lang-java">
package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

	private List&lt;Person> persons;

	@XmlElement(name = "person")
	public List&lt;Person> getPersons() {
		return persons;
	}

	public void setPersons(List&lt;Person> persons) {
		this.persons = persons;
	}
}
</pre>

Remarquez les deux annotations : 

* `@XmlRootElement` définit le nom de l'élément racine.
* `@XmlElement` est un nom optionel que nous pouvons spécifier pour l'élément. 


#### Lire et écrire des données avec JAXB

Nous allons rendre notre classe `MainApp` responsable pour la lecture et l'écriture des données relatives aux personnes. Ajoutez les deux méthodes suivantes à la fin de `MainApp.java` : 


<pre class="prettyprint lang-java">
/**
 * Loads person data from the specified file. The current person data will
 * be replaced.
 * 
 * @param file
 */
public void loadPersonDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

        personData.clear();
        personData.addAll(wrapper.getPersons());

        // Save the file path to the registry.
        setPersonFilePath(file);

    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());

        alert.showAndWait();
    }
}

/**
 * Saves the current person data to the specified file.
 * 
 * @param file
 */
public void savePersonDataToFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our person data.
        PersonListWrapper wrapper = new PersonListWrapper();
        wrapper.setPersons(personData);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

        // Save the file path to the registry.
        setPersonFilePath(file);
    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not save data");
        alert.setContentText("Could not save data to file:\n" + file.getPath());

        alert.showAndWait();
    }
}
</pre>

La mise en correspondance (marshalling/unmarshalling) est prête. Maintenant créons le menu save/load pour être réellement capable de l'utiliser. 


## Gérer les actions des menus

Dans notre `RootLayout.fxml` il y a déjà un menu mais nous ne l'avons pas encore utilisé. Avant d'ajouter des actions au menu nous allons commencer par créer tous les éléments du menu. 

Ouvrez le fichier `RootLayout.fxml` dans Scene Builder puis faites un drag and drop depuis le groupe *library* pour ajouter les éléments menus à la `MenuBar` dans le groupe *hierarchy*. Créez un menu **New**, **Open...**, **Save**, **Save As...**, et **Exit** ! 

![Add Menu Items](/assets/library/javafx-8-tutorial/part5/add-menu-items.png)

Astuce : Utilisez le paramètre *Accelerator* dans le groupe des *Properties* pour ajouter des raccourcis clavier aux éléments du menu ! 


### Le RootLayoutController

Nous allons avoir besoin d'une nouvelle classe contrôleur pour gérer les actions du menu. Créez une classe `RootLayoutController` dans le package contrôleur `ch.makery.address.view` ! 

Ajoutez le contenu suivant au contrôleur : 


##### RootLayoutController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import ch.makery.address.MainApp;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.savePersonDataToFile(file);
		}
	}

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("AddressApp");
    	alert.setHeaderText("About");
    	alert.setContentText("Author: Marco Jakob\nWebsite: http://code.makery.ch");

    	alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
</pre>


#### La boîte de dialogue FileChooser

Remarquez les méthodes que la classe `FileChooser` utilise dans le `RootLayoutController` ci-dessus ! D'abord un nouvel objet de la classe `FileChooser` est créé. Puis un filtre d'extension est ajouté de telle manière que seuls sont affichés les fichiers dont l'extension est `.xml`. À la fin, le file chooser (la boîte de dialogue pour sélectionner le/les fichiers) est affiché au dessu du stage (affichage principal). 

La valeur `null` est renvoyée lorsque l'utilisateur referme la boîte de dialogue sans choisir de fichier. Dans l'autre cas nous avons le fichier sélectionné et nous pouvons le passer à la méthode `loadPersonDataFromFile(...)` ou `savePersonDataToFile(...)` de `MainApp`. 


### Connecter la vue fxml au contrôleur

1. Ouvrez le fichier `RootLayout.fxml` dans Scene Builder ! Sélectionnez le `RootLayoutController` comme classe contrôleur dans le groupe *Controller* ! 

2. Retournez dans le groupe *Hierarchy* et sélectionnez un élément menu. Dans le groupe *Code* sous **On Action** vous devriez voir un choix de toutes les méthodes contrôleur disponibles. Sélectionnez la méthode correspondante pour chaque élément du menu !   
![Menu Actions](/assets/library/javafx-8-tutorial/part5/menu-actions.png)

3. Répétez ces étapes pour tous les éléments du menu ! 

4. Refermez Scene Builder et tapez **F5 pour raffraîchir** le dossier racine de votre projet étant sélectionné. Ceci rendra Eclipse conscient des changements que vous avez faits dans Scene Builder. 


### Connecter la MainApp et le RootLayoutController

À plusieurs endroits, le `RootLayoutController` a besoin d'une référence sur `MainApp`. Nous n'avons pas passer la référence au `RootLayoutController` pour l'instant. 

Ouvrez la classe `MainApp` et remplacez la méthode `initRootLayout()` par le code suivant : 

<pre class="prettyprint lang-java">
/**
 * Initializes the root layout and tries to load the last opened
 * person file.
 */
public void initRootLayout() {
    try {
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class
                .getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // Give the controller access to the main app.
        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Try to load last opened person file.
    File file = getPersonFilePath();
    if (file != null) {
        loadPersonDataFromFile(file);
    }
}
</pre>

Remarquez les deux modifications : Les lignes qui *donnent accès au contrôleur à la main app* et les trois dernières lignes pour *charger (load) le dernier fichier de la personne*. 


### Tester

En faisant un essai avec votre application, vous devriez pouvoir utiliser les menus pour enregistrer les données des personnes dans un fichier.  

Lorsque vous ouvrez le fichier `xml` dans un éditeur vous remarquerez que la date de naissance n'est pas enregistrée correctement. C'est un tag vide `<birthday/>`. La raison à cela est que JAXB ne connait pas comment convertir la `LocalDate` en XML. Nous devons fournir un `LocalDateAdapter` personnalisé pour définir cette conversion. 

Créez une nouvelle classe dans `ch.makery.address.util` nommée `LocalDateAdapter` avec le contenu suivant : 

##### LocalDateAdapter.java

<pre class="prettyprint lang-java">
package ch.makery.address.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter (for JAXB) to convert between the LocalDate and the ISO 8601 
 * String representation of the date such as '2012-12-03'.
 * 
 * @author Marco Jakob
 */
public class LocalDateAdapter extends XmlAdapter&lt;String, LocalDate> {

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}
}
</pre>

Ensuite ouvrez `Person.java` et ajoutez l'annotation suivante à la méthode `getBirthday()` : 

<pre class="prettyprint lang-java">
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public LocalDate getBirthday() {
    return birthday.get();
}
</pre>

Maintenant testez encore une fois ! Essayez d'enregistrer et de charger le fichier xml. Après un redémarrage, le dernier fichier utilisé devrait être automatiquement chargé.  



## Comment ça fonctionne ? 


Voyons maintenant comment tout cela s'articule : 

1. L'application et démarrée en utilisant la méthode `main(...)` de `MainApp`. 
2. Le constructor `public MainApp()` est appelé et ajoute quelques données test. 
3. La méthode `start(...)` de `MainApp` est appelée est appelle `initRootLayout()` pour initialiser le layout racine de `RootLayout.fxml`. Le fichier fxml a l'information pour faire correspondre le contrôleur et lie l'affichage à son `RootLayoutController`. 
4. La `MainApp` reçoit le `RootLayoutController` du chargeur fxml et passe une référence à elle-même au contrôleur. Avec cette référence, le contrôleur peut accéder plus tard aux méthodes (publiques) de `MainApp`.
5. À la fin de la méthode `initRootLayout()` nous essayons d'obtenir des `Preferences` le *dernier fichier de personne ouvert*. Si les `Preferences` ont connaissance d'un tel fichier XML, nous chargerons les données depuis ce fichier XML. Visiblement, cela écrasera les données test du constructeur. 


### Et ensuite ?

Dans la [Partie 6](/library/javafx-8-tutorial/fr/part6/) du tutoriel, nous allons ajouter un graphique illustrant les statistiques des dates de naissance. 


##### Voici quelques autres article qui pourraient vous intéresser

* [JavaFX - Boîtes de dialogue (officiel)](/blog/javafx-dialogs-official/)
* [JavaFX - Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX - Exemple de gestion d'événements](/blog/javafx-8-event-handling-examples/)
* [JavaFX - Trier et filtrer des TableView](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX - Rendu des cellules TableView](/blog/javafx-8-tableview-cell-renderer/)
