---
layout: article
title: "Tutoriel JavaFX 8 - partie 2 : Modèle et TableView"
date: 2014-04-23 00:00
updated: 2015-04-15 00:00
slug: javafx-8-tutorial/fr/part2
canonical: /library/javafx-8-tutorial/part2/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-fr-part2.md
description: "Utiliser un contrôle JavaFX TableView pour afficher une ObservableList de personnes."
image: /assets/library/javafx-8-tutorial/part2/addressapp-part2.png
published: true
prettify: true
comments: true
sidebars:
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
    active: true
  - text: "Partie 3 : interaction avec l'utilisateur"
    link: /library/javafx-8-tutorial/fr/part3/
    paging: 3
  - text: "Partie 4 : style CSS"
    link: /library/javafx-8-tutorial/fr/part4/
    paging: 4
  - text: "Partie 5 : stockage de données en XML"
    link: /library/javafx-8-tutorial/fr/part5/
    paging: 5
  - text: "Partie 6 : statistiques graphiques"
    link: /library/javafx-8-tutorial/fr/part6/
    paging: 6
  - text: "Partie 7 : déploiement"
    link: /library/javafx-8-tutorial/fr/part7/
    paging: 7
- header: "Téléchargez les sources"
  body:
  - text: Projet Eclipse relatif à la partie 2 <em>(JDK 8u20 requis au minimum)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-2.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Langues
  collection: library
  item: javafx-8-tutorial
  part: part2
  active: fr
---

![Screenshot AddressApp Part 2](/assets/library/javafx-8-tutorial/part2/addressapp-part2.png)


## Sujets dans la partie 2

* Créer une classe **modèle** 
* Utiliser la classe modèle dans une **ObservableList**
* Afficher les données dans une **TableView** en utilisant des **Controllers**


*****

## Créer la classe modèle

Nous avons besoin d'une classe modèle pour contenir les informations relatives aux gens dans le carnet d'adresses. Ajoutez une nouvelle classe au package modèle (`ch.makery.address.model`) nommée `Person`. La classe `Person` aura quelques variables d'instance pour le nom, l'adresse et la date d'anniversaire. Ajoutez le code suivant à la classe ! J'expliquerai les parties spécifiques à JavaFX après le code. 


##### Person.java

<pre class="prettyprint lang-java">
package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty street;
	private final IntegerProperty postalCode;
	private final StringProperty city;
	private final ObjectProperty&lt;LocalDate&gt; birthday;

	/**
	 * Default constructor.
	 */
	public Person() {
		this(null, null);
	}
	
	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
		// Some initial dummy data, just for convenient testing.
		this.street = new SimpleStringProperty("some street");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.city = new SimpleStringProperty("some city");
		this.birthday = new SimpleObjectProperty&lt;LocalDate&gt;(LocalDate.of(1999, 2, 21));
	}
	
	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}
	
	public StringProperty streetProperty() {
		return street;
	}

	public int getPostalCode() {
		return postalCode.get();
	}

	public void setPostalCode(int postalCode) {
		this.postalCode.set(postalCode);
	}
	
	public IntegerProperty postalCodeProperty() {
		return postalCode;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}
	
	public StringProperty cityProperty() {
		return city;
	}

	public LocalDate getBirthday() {
		return birthday.get();
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}
	
	public ObjectProperty&lt;LocalDate&gt; birthdayProperty() {
		return birthday;
	}
}
</pre>


### Explications

* Avec JavaFX il est courant d'utiliser les [`Properties`](http://docs.oracle.com/javase/8/javafx/api/javafx/beans/property/Property.html) pour tous les champs de votre classe. Une `Property` (propriété) nous permet, par exemple, d'être automatiquement averti lorsque la variable `lastName` ou toute autre variable a été modifiée. Ceci nous aide à maintenir la view synchronisée avec les données. Pour en apprendre plus au sujet des `Properties`, lisez [Using JavaFX Properties and Binding](http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm) ! 
* [`LocalDate`](http://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html), le type utilisé pour la date de naissance (`birthday`), fait partie des nouvelles [APIs Date et Time pour JDK 8](http://docs.oracle.com/javase/tutorial/datetime/iso/).


*****

## Une liste de personnes

La donnée principale gérée par notre application est un ensemble de personnes. Créons une liste pour les objets `Person` dans la classe `MainApp`. Toutes les autres classes contrôleurs auront accès à cette liste centrale plus tard au sein de `MainApp`.


### ObservableList

Nous travaillons avec les classes d'affichages JavaFX qui ont besoin d'être informées de tous les changements faits sur la liste des gens. C'est important sans quoi l'affichage ne sera pas synchronisé avec les données. A cette fin, JavaFX introduit quelques nouvelles [classes de collections](http://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm). 

Nous avons besoin de l'`ObservableList` fournit par les collections. Pour créer une nouvelle `ObservableList`, ajoutez le code suivant au début de la classe `MainApp`. Nous ajouterons aussi un constructeur qui créera quelques données à titre d'exemple et qui aura un getteur public : 


##### MainApp.java

<pre class="prettyprint lang-java">

    // ... AFTER THE OTHER VARIABLES ...

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList&lt;Person&gt; personData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {
		// Add some sample data
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Mueller"));
	}
  
	/**
	 * Returns the data as an observable list of Persons. 
	 * @return
	 */
	public ObservableList&lt;Person&gt; getPersonData() {
		return personData;
	}
  
    // ... THE REST OF THE CLASS ...
</pre>


*****

## Le PersonOverviewController ##

Maintenant intégrons finalement les données dans notre tableau ! Nous aurons besoin d'un contrôleur pour notre `PersonOverview.fxml`. 

1. Créez une classe normale dans le package **view** nommé `PersonOverviewController.java`. (On doit le mettre dans le même package que `PersonOverview.fxml`, sans cela le SceneBuilder ne le trouvera pas - en tout cas pas la version courante). 
2. Nous allons ajouter quelques variables qui nous donneront accès au tableau et aux étiquettes de l'affichage. Les champs et quelques méthodes ont une annotation spéciale `@FXML`. Ceci est nécessaire au fichier fxml pour accéder aux champs et aux méthodes privées. Après avoir tout préparé dans le fichier fxml, l'application remplira automatiquement les variables lors du chargement du fxml. Ajoutons le code suivant : 

<div class="alert alert-info">
**Note : ** rappelez-vous de toujours utiliser les **imports javafx**, PAS awt ou swing ! 
</div>


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;

public class PersonOverviewController {
    @FXML
    private TableView&lt;Person&gt; personTable;
    @FXML
    private TableColumn&lt;Person, String&gt; firstNameColumn;
    @FXML
    private TableColumn&lt;Person, String&gt; lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
}
</pre>


Ce code nécessitera probablement quelques explications : 

* Tous les champs et toutes les méthodes auxquelles le fichier fxml aura besoin d'accéder devront être annotées avec `@FXML`. En fait seulement ceux et celles qui sont privées mais c'est bien mieux de les spécifier private et de les annoter ! 
* La méthode `initialize()` est appelée automatiquement après le chargement du fichier fxml. À ce moment, tous les champs devraient déjà être initialisés. 
* Les `setCellValueFactory(...)` que nous assignons aux colonnes du tableau sont utilisés pour déterminer quel champ dans les objets `Person` devraient être utilisés pour une colonne particulière. La flèche `->` indique que nous utilisons une fonctionnalité de Java 8 nommée fonctions *Lambdas*. (Une autre approche est possible en utilisant une [PropertyValueFactory](http://docs.oracle.com/javase/8/javafx/api/) mais ce n'est type-safe). 


### Connexion de MainApp avec le PersonOverviewController

La méthode `setMainApp(...)` doit être appelée par la classe `MainApp`. Ceci nous donne la possibilité d'accéder à  l'objet `MainApp` et d'obtenir la liste des `Persons` et d'autres choses. Remplacez la méthode `showPersonOverview()` avec ce qui suit ! Le code contient deux lignes en plus : 


##### MainApp.java - nouvelle méthode showPersonOverview() 

<pre class="prettyprint lang-java">
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

        // Give the controller access to the main app.
        PersonOverviewController controller = loader.getController();
        controller.setMainApp(this);

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>



*****


## Assigner la vue au contrôleur

Nous y sommes persque ! Mais une petite chose manque encore : pour l'instant nous n'avons pas défini dans notre `PersonOverview.fxml` quel élément doit correspondre à quel champ dans le contrôleur. 

1. Ouvrez `PersonOverview.fxml` avec le *SceneBuilder* ! 

2. Ouvrez le groupe *Controller* sur le côté gauche et sélectionnez la classe `PersonOverviewController` comme **classe de contrôleur** !    
![Set Controller Class](/assets/library/javafx-8-tutorial/part2/set-controller-class.png)

3. Sélectionnez le `TableView` dans le groupe *Hierarchy* et sélectionnez dans le groupe *Code* le champ `personTable` en tant que **fx:id**.   
![Set TableView fx:id](/assets/library/javafx-8-tutorial/part2/set-tableview-fx-id.png)

4. Faites la même chose pour les colonnes et sélectionnez `firstNameColumn` et `lastNameColumn` dans le champ **fx:id** ! 

5. Sélectionnez le champ **fx:id** correspondant pour **chaque étiquette** dans la deuxième colonne ! 
![Set Label fx:id](/assets/library/javafx-8-tutorial/part2/set-label-fx-id.png)

6. Important : retournez dans Eclipse, **raffraîchissez le projet complet** AddressApp (F5). C'est nécessaire car    Eclipse n'a pas connaissance des changements faits dans le Scene Builder ! 


*****

## Démarrer l'application

Si vous démarrez votre application maintenant vous devriez voir quelque chose comme la capture d'écran au début de ce post.    

Félicitations!

*Note : Il n'y a pas de mise à jour des étiquettes lorsqu'une personne est sélectionnée pour l'instant. Nous allons programmer les interactions utilisateur dans la prochaine partie du tutoriel. *


### Et après ?

Dans la [Partie 3 du tutoriel](/library/javafx-8-tutorial/fr/part3/), nous allons ajouter plus de fonctionnalités telles que l'ajout, la suppression et la modification des personnes. 


##### Voici quelques articles qui pourraient aussi vous intéresser : 

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
