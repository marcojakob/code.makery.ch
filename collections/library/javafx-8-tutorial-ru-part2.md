---
layout: article
title: "Учебник по JavaFX 8 - Часть 2: Модель и компонент TableView"
date: 2014-04-19 00:00
updated: 2014-12-04 00:00
slug: javafx-8-tutorial/ru/part2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-ru-part2.md
description: "Используйте JavaFX TableView для отображения списка персон из ObservableList"
image: /assets/library/javafx-8-tutorial/part2/addressapp-part2.png
published: true
prettify: true
comments: true
sidebars:
- header: "Статьи в этой серии"
  body:
  - text: "Введение"
    link: /library/javafx-8-tutorial/ru/
    paging: Intro
  - text: "Часть 1: Scene Builder"
    link: /library/javafx-8-tutorial/ru/part1/
    paging: 1
  - text: "Часть 2: Модель и компонент TableView"
    link: /library/javafx-8-tutorial/ru/part2/
    paging: 2
    active: true
  - text: "Часть 3: Взаимодействие с пользователем"
    link: /library/javafx-8-tutorial/ru/part3/
    paging: 3
  - text: "Часть 4: Стилизация с помощью CSS"
    link: /library/javafx-8-tutorial/ru/part4/
    paging: 4
  - text: "Часть 5: Хранение данных в XML"
    link: /library/javafx-8-tutorial/ru/part5/
    paging: 5
  - text: "Часть 6: Статистическая диаграмма"
    link: /library/javafx-8-tutorial/ru/part6/
    paging: 6
  - text: "Часть 7: Развертывание"
    link: /library/javafx-8-tutorial/ru/part7/
    paging: 7
- header: Скачать исходники
  body:
  - text: Часть 2 как проект Eclipse <em>(Требуется хотя бы JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-2.zip
    icon-css: fa fa-fw fa-download
- header: Языки
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-part2/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part2/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part2/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part2/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/part2/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/part2/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/javafx-8-tutorial/fr/part2/
    icon-css: fa fa-fw fa-globe
---

![Screenshot AddressApp Part 2](http://code.makery.ch/assets/library/javafx-8-tutorial/part2/addressapp-part2.png "GridPane Add Row")


## Часть 2: Содержание

* Создание класса-**модели**;
* Использование класса-модели в коллекции **ObservableList**;
* Отображение данных в компоненте **TableView** используя **Контроллеры**.


*****

## Создание класса-модели

Класс-модель нам необходим для хранения информации о людях в нашей адресной книге. Добавьте класс `Person.java` в пакет `ch.makery.address.model`. В нем будет несколько переменных для хранения имени, адреса и дня рождения. Добавьте следующий код в этот класс.


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


### Объяснения

* В JavaFX предпочтительно использовать [`Properties`](http://docs.oracle.com/javase/8/javafx/api/javafx/beans/property/Property.html) для всех полей класса-модели. `Property` позволяет нам получать автоматические уведомления при любых изменениях переменных, таких как `lastName` или других. Это помогает нам сохранять синхронность отображения и данных. Для более делатьного изучения `Properties` читайте [Using JavaFX Properties and Binding](http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm "Using JavaFX Properties and Binding");
- Класс [`LocalDate`](http://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html), тип которого мы выбрали для нашей переменной `birthday`, это часть нового [Date and Time API для JDK 8](http://docs.oracle.com/javase/tutorial/datetime/iso/ "Date and Time API for JDK 8").


*****

## Список записей

Основные данные, которыми оперирует наше приложение - это связка экземпляров класса `Person`. Давайте создадим список объектов класса `Person` внутри класса `MainApp.java`. Все остальные классы-контроллеры позже получат доступ к этой центральной коллекции внутри класса `MainApp.java`.


### Список ObservableList

Мы работаем с классами-видами, которые необходимо информировать при любых изменениях в записях адресной книги. Не будь этого, мы бы не могли синхронизировать отображение данных с самими данными, которые у нас есть. Для этой цели в JavaFX предоставлены некоторые новые [классы-коллекции](http://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm "Collection classes").

Из этих классов нам необходим класс `ObservableList`. Для создания экземпляра данного класса добавьте приведенный код в начало нашего класса `MainApp.java`. Также добавьте в код конструктор, внутри которого наш список будет наполняться данными и добавьте геттер для нашего списка с публичным модификатором доступа:


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

## Класс PersonOverviewController

Теперь мы отобразим некоторые данные в нашей таблице. Для этого нам необходимо создать класс-контроллер для нашего `PersonOverview.fxml`.

1. Создайте новый класс внутри пакета `view` и назовите его `PersonOverviewController.java`. (Мы должны разместить наш класс-контроллер в тот же пакет, где находится наш файл разметки `PersonOverview.fxml`, иначе приложение Scene Builder не сможет найти его - по крайней мере не в текущей версии);
2. Для того, чтобы получить доступ к таблице и меткам внутри нашего окна, мы определим некоторые переменные. Эти переменные и некоторые методы имеют специальную аннотацию `@FXML`. Она необходима для того, чтобы fxml-файл имел доступ к приватным полям и приватным методам. После этого мы настроем наш fxml-файл так, что при его загрузке приложение  автоматически будет заполнять эти переменные данными. Ну что, давайте добавим следующий код в наш класс:

<div class="alert alert-info">
**Заметка**: При импорте пакетов всегда используйте пакет ***javafx***, а НЕ *awt* или *swing*!
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


Наш код требует некоторых объяснений:

*- Все поля и методы, к которым fxml-файлу потребуется доступ, должны быть помечены аннотацией `@FXML`. Правда, это верно только для полей и методов с модификатором private, но лучше оставить их такими и помечать аннотацией, чем делать публичными!
* Метод `initialize()` автоматически вызывается после загрузки fxml-файла. На этот момент все FXML-поля должны быть иниализированны;
* Метод `setCellValueFactory(...)` используется для определения того, какое поле внутри класса `Person` будут использоватся для конкретного столбца в таблице. Стрелка `->` означает, что мы использовали **лямбда-выражение** из Java 8. (Есть вариант сделать то же самое через [PropertyValueFactory](http://docs.oracle.com/javase/8/javafx/api/index.html?javafx/scene/control/cell/PropertyValueFactory.html "PropertyValueFactory"), но этот способ нарушает безопасность типов).


### Соединение класса MainApp с классом PersonOverviewController

Метод `setMainApp(...)` должен быть вызван с класса `MainApp`. Это даст нашему контроллеру доступ к экземпляру `MainApp`, к коллекции записей `personList` внутри него и к другим элементам класса. Добавьте в метод `showPersonOverview()` две дополнительные строки:


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


## Привязка класса-Контроллера к fxml-файлу

Мы подходим к завершению данной части! Но мы пропустили одну маленькую вещь: мы не сказали нашему файлу `PersonOverview.fxml`, какой контроллер он должен использовать и какие элементы вида каким полям внутри класса-контроллера должны соответствовать. Для этого:

1. Откройте файл `PersonOverview.fxml` в приложении *Scene Builder*.

2. Откройте вкладку *Controller* слева на панели *Document* и выберите класс `PersonControllerOverview` в качестве класса-контроллера.  
![Set Controller Class](/assets/library/javafx-8-tutorial/part2/set-controller-class.png "Set Controller Class")

3. Выберите компонент `TableView` на вкладке *Hierarchy*, перейдите на вкладку *Code* и установите значение `personTable` полю **fx:id**.  
![Set TableView fx:id](/assets/library/javafx-8-tutorial/part2/set-tableview-fx-id.png "Set TableView fx:id")

4. Сделайте то же самое для колонок таблицы и установите значения свойства **fx:id** `firstNameColumn` и `secondNameColumn` соответствено.

5. Для каждой метки во второй колонке компонента GridPane также установите соответствующие значения **fx:id**.  
![Set Label fx:id](/assets/library/javafx-8-tutorial/part2/set-label-fx-id.png "Set Label fx:id")

6. Важно: сохрание наш файл `PersonOverview.fxml`, а потом вернитесь в среду разработки Eclipse и обновите весь проект AdressApp (F5). Это необходимо для того, чтобы приложение Eclipse определило те изменения, которые мы сделали в приложении Scene Builder.


*****

## Запуск приложения

Когда вы запустите ваше приложение, вы должны будете увидеть что-то похожее на то, что изображено на картинке вначале данной статьи.

Поздравляю!


### Что дальше?

В [3 Части Учебника](/library/javafx-8-tutorial/ru/part3/ "Tutorial Part 3") мы научим наше приложение добавлять, редактировать и удалять адресные записи.


##### Вам могут быть интересны также некоторые другие статьи

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
