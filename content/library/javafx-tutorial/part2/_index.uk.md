---
layout: article
title: "Підручник з JavaFX 8 - Частина 2: Модель та компонент TableView"
date: 2014-04-19
updated: 2015-06-25
slug: javafx-tutorial/uk/part2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-uk-part2.md
description: "Використовуйте JavaFX TableView для відображення списку персон з ObservableList"
image: /assets/library/javafx-tutorial/part2/addressapp-part2.png
published: true
prettify: true
comments: true
sidebars:
- header: "Статті цієї серії"
  body:
  - text: "Вступ"
    link: /library/javafx-tutorial/uk/
    paging: Intro
  - text: "Частина 1: Scene Builder"
    link: /library/javafx-tutorial/uk/part1/
    paging: 1
  - text: "Частина 2: Модель та компонент TableView"
    link: /library/javafx-tutorial/uk/part2/
    paging: 2
    active: true
  - text: "Частина 3: Взаємодія з користувачем"
    link: /library/javafx-tutorial/uk/part3/
    paging: 3
  - text: "Частина 4: Стилізація за допомогою CSS"
    link: /library/javafx-tutorial/uk/part4/
    paging: 4
  - text: "Частина 5: Збереження даних в XML"
    link: /library/javafx-tutorial/uk/part5/
    paging: 5
  - text: "Частина 6: Статистична діаграма"
    link: /library/javafx-tutorial/uk/part6/
    paging: 6
  - text: "Частина 7: Розгортання"
    link: /library/javafx-tutorial/uk/part7/
    paging: 7
- header: Скачати вихідний код
  body:
  - text: Частина 2 як проект Eclipse <em>(Необхідно хоча б JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-2.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Мови
  collection: library
  item: javafx-tutorial
  part: part2
  active: uk
---

![Screenshot AddressApp Part 2](http://code.makery.ch/assets/library/javafx-tutorial/part2/addressapp-part2.png "GridPane Add Row")

## Частина 2: Зміст

* Створення класу-**моделі**;
* Використання класу-моделі в колекції **ObservableList**;
* Відображення даних в компоненті **TableView** з використанням **контролерів**.

*****

## Створення класу-моделі

Клас-модель необхідний для збереження інформації про людей в нашій адресній книзі. Додайте клас `Person.java` в пакет `ch.makery.address.model`. У ньому буде кілька змінних для зберігання імені, адреси та дня народження. Додайте наступний код в цей клас. Нижче знаходиться деякі пояснення.

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

### Пояснення

* У JavaFX використовують переважно клас [`Properties`](http://docs.oracle.com/javase/8/javafx/api/javafx/beans/property/Property.html) для всіх полів класу-моделі. `Property` дозволяє сповіщати нас при будь-яких змінах змінних, таких як `lastName` чи інших. Це допомагає нам зберігати синхронність відображення та даних. Для більш детального вивчення `Properties` читайте [про використання класу властивостей](http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm "Using JavaFX Properties and Binding");
- Клас [`LocalDate`](http://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html), тип якого ми обрали для нашої змінної `birthday`, це частина нового [інтерфейсу дати та часу для JDK 8](http://docs.oracle.com/javase/tutorial/datetime/iso/ "Date and Time API for JDK 8").

*****

## Список записів

Основні дані, якими оперує наш додаток - це зв'язка екземплярів класу `Person`. Давайте створимо список об'єктів класу `Person` всередині класу `MainApp.java`. Всі інші класи-контролери пізніше отримають доступ до цієї центральної колекції всередині класу `MainApp.java`.

### Список ObservableList

Ми працюємо з класами представлення даних, які необхідно інформувати при будь-яких змінах в записах адресної книги. Без цього, ми б не могли синхронізувати відображення даних з самими даними, які у нас є. Для цієї мети в JavaFX додані деякі нові [класи-колекції](http://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm "Collection classes").

З них нам необхідний клас `ObservableList`. Для створення екземпляра даного класу додайте наведений код в початок файлу `MainApp.java`. Також додайте в клас конструктор, всередині якого наш список буде наповнюватися даними і додайте геттер з публічним модифікатором доступу для цього ж списку:

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

## Клас PersonOverviewController

Тепер ми відобразимо деякі дані в нашій таблиці. Для цього нам необхідно створити клас-контролер для нашого `PersonOverview.fxml`.

1. Створіть новий клас всередині пакету `view` і назвіть його `PersonOverviewController.java`. (Ми повинні розмістити клас-контролер в тому ж пакеті, де знаходиться файл розмітки `PersonOverview.fxml`, інакше додаток Scene Builder не зможе знайти його - принаймні не в поточній версії);
2. Для того, щоб отримати доступ до таблиці і текстових міток всередині нашого вікна, ми визначимо деякі змінні. Ці змінні і деякі методи будуть мати спеціальну анотацію `@FXML`. Вона необхідна для того, щоб fxml файл мав доступ до приватних полів та приватних методів. Після цього, ми налаштуємо наш fxml файл так, що при його завантаженні, додаток автоматично буде заповнювати ці змінні даними. Ну що, давайте додамо наступний код в клас:

<div class="alert alert-info">
**Примітка**: При імпорті пакетів завжди використовуйте пакет **javafx**, а НЕ *awt* чи *swing*!
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


Цей код вимагає деяких пояснень:

* Всі поля і методи, до яких fxml файлу потрібен доступ, повинні бути позначені анотацією `@FXML`. Правда, це вірно тільки для полів і методів з модифікатором private, але краще залишити їх такими та позначати анотацією, ніж робити публічними!
* Метод `initialize()` автоматично викликається після завантаження fxml файлу. На цей момент всі FXML поля повинні бути ініціалізовані;
* Метод `setCellValueFactory(...)` використовується для визначення того, яке поле всередині класу `Person` буде використовуватися для конкретного стовпця в таблиці. Стрілка `->` означає, що ми використали **лямбда-вираз** із Java 8. (Іншим варіантом зробити це - використати [PropertyValueFactory](http://docs.oracle.com/javase/8/javafx/api/index.html?javafx/scene/control/cell/PropertyValueFactory.html "PropertyValueFactory"), але цей спосіб порушує безпеку типів).

<div class="alert alert-info">
В нашому випадку використовуються лише значення `StringProperty`. Якщо ви хочете використати `IntegerProperty` або `DoubleProperty`, в методі `setCellValueFactory(...)` має бути додатковий виклик `asObject()`:
<pre class="prettyprint lang-java">
myIntegerColumn.setCellValueFactory(cellData -> 
      cellData.getValue().myIntegerProperty().asObject());
</pre>
Це необхідно через погане архітектурне рішення JavaFX (Дивіться [обговорення](https://community.oracle.com/thread/2575601) для детальної інформації).
</div>

### Поєднання класу MainApp з класом PersonOverviewController

Метод `setMainApp(...)` повинен бути викликаний з класу `MainApp`. Це дасть нашому контролеру доступ до екземпляру `MainApp`, до колекції записів `personList` всередині нього та до інших елементів класу. Замініть метод `showPersonOverview()` наступним, що відрізняється двома додатковими рядками:

##### MainApp.java - метод showPersonOverview()

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

## Прив'язка класу контролера до класу представлення

Ми підходимо до завершення цієї частини! Але ми пропустили одну маленьку річ: не сказали нашому файлу `PersonOverview.fxml`, який контролер він повинен використовувати та які елементи представлення повинні відповідати полям усередині контролера. Для цього:

1. Відкрийте файл `PersonOverview.fxml` в додатку *Scene Builder*.

2. Відкрийте вкладку *Controller* зліва на панелі *Document* та виберіть клас `PersonControllerOverview` в якості класу-контролера (можете скористатись випадаючим списком справа).   
![Set Controller Class](/assets/library/javafx-tutorial/part2/set-controller-class.png "Set Controller Class")

3. Виберіть компонент `TableView` на вкладці *Hierarchy*, перейдіть на вкладку *Code* та, нарешті, встановіть значення `personTable` для поля **fx:id** (також можете скористатись випадаючим списком справа).   
![Set TableView fx:id](/assets/library/javafx-tutorial/part2/set-tableview-fx-id.png "Set TableView fx:id")

4. Зробіть те ж саме для колонок таблиці і встановіть значення властивості **fx:id** `firstNameColumn` і `secondNameColumn`, відповідно.

5. Для **кожної текстової мітки** (`Label`) у другій колонці компонента *GridPane* також встановіть відповідні значення **fx:id** (на вкладці *Code*).   
![Set Label fx:id](/assets/library/javafx-tutorial/part2/set-label-fx-id.png "Set Label fx:id")

6. Важливо: збережіть файл `PersonOverview.fxml`, а потім поверніться в середовище розробки Eclipse і **поновіть весь проект** (F5). Це необхідно для того, щоб додаток Eclipse визначив ті зміни, які ми зробили в додатку Scene Builder.

*****

## Запуск програми

Коли ви запустите ваш додаток, ви повинні будете побачити щось схоже на те, що зображено на рисунку на початку даної статті.

Вітаю!

*Примітка: Текстові мітки поки що не оновлюються при виборі персони. Програмування взаємодії з користувачем описане в наступній частині підручника.*

### Що далі?

В [3 частині підручника](/library/javafx-tutorial/uk/part3/"Tutorial Part 3") ми навчимо наш додаток додавати, редагувати і видаляти адресні записи.

##### Вам можуть бути цікаві також деякі інші статті

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
