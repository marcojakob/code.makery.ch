---
layout: article
title: "Учебник по JavaFX 8 - Часть 2: Модель и компонент TableView"
date: 2014-04-19
updated: 2016-04-21
slug: javafx-tutorial/ru/part2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-ru-part2.md
description: "Используйте JavaFX TableView для отображения списка людей из ObservableList"
image: /assets/library/javafx-tutorial/part2/addressapp-part2.png
published: true
prettify: true
comments: true
sidebars:
- header: "Статьи в этой серии"
  body:
  - text: "Введение"
    link: /library/javafx-tutorial/ru/
    paging: Intro
  - text: "Часть 1: Scene Builder"
    link: /library/javafx-tutorial/ru/part1/
    paging: 1
  - text: "Часть 2: Модель и компонент TableView"
    link: /library/javafx-tutorial/ru/part2/
    paging: 2
    active: true
  - text: "Часть 3: Взаимодействие с пользователем"
    link: /library/javafx-tutorial/ru/part3/
    paging: 3
  - text: "Часть 4: Стилизация с помощью CSS"
    link: /library/javafx-tutorial/ru/part4/
    paging: 4
  - text: "Часть 5: Хранение данных в XML"
    link: /library/javafx-tutorial/ru/part5/
    paging: 5
  - text: "Часть 6: Статистическая диаграмма"
    link: /library/javafx-tutorial/ru/part6/
    paging: 6
  - text: "Часть 7: Развёртывание"
    link: /library/javafx-tutorial/ru/part7/
    paging: 7
- header: Скачать исходники
  body:
  - text: Часть 2 как проект Eclipse <em>(Требуется хотя бы JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-2.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Языки
  collection: library
  item: javafx-tutorial
  part: part2
  active: ru
---

![Screenshot AddressApp Part 2](http://code.makery.ch/assets/library/javafx-tutorial/part2/addressapp-part2.png "GridPane Add Row")


## Часть 2: Содержание

* Создание класса-**модели**;
* Использование класса-модели в коллекции **ObservableList**;
* Отображение данных в компоненте **TableView** с помощью **Контроллеров**.


*****

## Создание класса-модели

Класс-модель необходим для хранения в нашей будущей адресной книге информации об адресатах. Добавьте класс `Person.java` в пакет `ch.makery.address.model`. В нём будет несколько переменных для хранения информации об имени, адресе и дне рождения. Добавьте в этот класс следующий код.


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
 * Класс-модель для адресата (Person).
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
	 * Конструктор по умолчанию.
	 */
	public Person() {
		this(null, null);
	}
	
	/**
	 * Конструктор с некоторыми начальными данными.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
		// Какие-то фиктивные начальные данные для удобства тестирования.
		this.street = new SimpleStringProperty("какая-то улица");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.city = new SimpleStringProperty("какой-то город");
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


### Объяснение

* В JavaFX для всех полей класса-модели предпочтительно использовать [`Properties`](http://docs.oracle.com/javase/8/javafx/api/javafx/beans/property/Property.html. `Property` позволяет нам получать автоматические уведомления при любых изменениях переменных, таких как `lastName` или любых других. Это позволяет поддерживать синхронность представления и данных. Для более детального изучения `Properties` можно прочесть статью [Using JavaFX Properties and Binding](http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm "Using JavaFX Properties and Binding");
- Класс [`LocalDate`](http://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html), тип которого мы выбрали для нашей переменной `birthday`, это часть нового [Date and Time API для JDK 8](http://docs.oracle.com/javase/tutorial/datetime/iso/ "Date and Time API for JDK 8").


*****

## Список людей

Основные данные, которыми оперирует наше приложение - это группа экземпляров класса `Person`. Давайте создадим в классе `MainApp.java` список объектов класса `Person`. Все остальные классы-контроллеры позже получат доступ к этому центральному списку внутри этого класса.


### Список ObservableList

Мы работаем с классами-представлениями JavaFX, которые необходимо информировать при любых изменениях в списке адресатов. Это важно, потому что, не будь этого, мы бы не смогли синхронизировать представление данных с самими данными. Для этой цели в JavaFX были введены некоторые новые [классы коллекций](http://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm "Collection classes").

Из этих классов нам понадобится класс `ObservableList`. Для создания экземпляра данного класса добавьте приведённый код в начало `MainApp.java`. Мы так же добавим в код конструктор, который будет создавать некоторые демонстрационный данные и метод-геттер с публичным модификатором доступа:


##### MainApp.java

<pre class="prettyprint lang-java">

    // ... ПОСЛЕ ДРУГИХ ПЕРЕМЕННЫХ ...

	/**
	 * Данные, в виде наблюдаемого списка адресатов.
	 */
	private ObservableList&lt;Person&gt; personData = FXCollections.observableArrayList();

	/**
	 * Конструктор
	 */
	public MainApp() {
		// В качестве образца добавляем некоторые данные
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
	 * Возвращает данные в виде наблюдаемого списка адресатов.
	 * @return
	 */
	public ObservableList&lt;Person&gt; getPersonData() {
		return personData;
	}
  
    // ... ОСТАЛЬНАЯ ЧАСТЬ КЛАССА ...
</pre>


*****

## Класс PersonOverviewController

Теперь мы отобразим в нашей таблице некоторые данные. Для этого необходимо создать класс-контроллер для представления `PersonOverview.fxml`.

1. Создайте новый класс внутри пакета `view` и назовите его `PersonOverviewController.java`. (Мы должны разместить этот класс-контроллер в том же пакете, где находится файл разметки `PersonOverview.fxml`, иначе Scene Builder не сможет его найти.)
2. Для того, чтобы получить доступ к таблице и меткам представления, мы определим некоторые переменные. Эти переменные и некоторые методы имеют специальную аннотацию `@FXML`. Она необходима для того, чтобы fxml-файл имел доступ к приватным полям и методам. После этого мы настроим наш fxml-файл так, что при его загрузке приложение автоматически заполняло эти переменные данными. Итак, давайте добавим следующий код в наш класс:

<div class="alert alert-info">
**Примечание**: При импорте пакетов всегда используйте пакет ***javafx***, а НЕ *awt* или *swing*!
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

    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public PersonOverviewController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    	// Инициализация таблицы адресатов с двумя столбцами.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        personTable.setItems(mainApp.getPersonData());
    }
}
</pre>


Этот код требует некоторых разъяснений:

* Все поля и методы, к которым fxml-файлу потребуется доступ, должны быть отмечены аннотацией `@FXML`. Несмотря на то, что это требование предъявляется только для полей и методов с модификатором private, лучше оставить их закрытыми и помечать аннотацией, чем делать публичными!
* После загрузки fxml-файла автоматически вызывается метод `initialize()`. На этот момент все FXML-поля должны быть инициализированы;
* Метод `setCellValueFactory(...)` определяет, какое поле внутри класса `Person` будут использоваться для конкретного столбца в таблице. Стрелка `->` означает, что мы использовали **лямбда-выражение** из Java 8. (Есть вариант сделать то же самое через [PropertyValueFactory](http://docs.oracle.com/javase/8/javafx/api/index.html?javafx/scene/control/cell/PropertyValueFactory.html "PropertyValueFactory"), но этот способ нарушает безопасность типов).

<div class="alert alert-info">
  <p>
    В нашем примере для столбцов таблицы мы использовали только значения `StringProperty`. Если нам понадобится использовать `IntegerProperty` или `DoubleProperty`, то `setCellValueFactory(...)` должен иметь дополнительный метод `asObject()`:
  </p>
  <p>
  <pre>myIntegerColumn.setCellValueFactory(cellData -> 
      cellData.getValue().myIntegerProperty().<mark>asObject()</mark>);</pre>
  </p>
  <p>
    Это добавление необходимо сделать из-за неудачного решения при проектировании JavaFX (для подробностей см. <a href="https://community.oracle.com/thread/2575601">это обсуждение</a>).
  </p>
</div>


### Соединение класса MainApp с классом PersonOverviewController

Метод `setMainApp(...)` должен быть вызван из класса `MainApp`. Это даст нашему контроллеру доступ к экземпляру `MainApp`, к коллекции записей `personList` внутри него и к другим элементам класса. Добавьте в метод `showPersonOverview()` две дополнительные строки:


##### MainApp.java - метод showPersonOverview()

<pre class="prettyprint lang-java">
/**
 * Показывает в корневом макете сведения об адресатах.
 */
public void showPersonOverview() {
    try {
        // Загружаем сведения об адресатах.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Помещаем сведения об адресатах в центр корневого макета.
        rootLayout.setCenter(personOverview);

<mark>        // Даём контроллеру доступ к главному приложению.
        PersonOverviewController controller = loader.getController();
        controller.setMainApp(this);</mark>

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>



*****


## Привязка класса-контроллера к fxml-файлу

Данная часть учебника близится к своему завершению, однако мы пропустили одну маленькую деталь! Мы не сказали файлу `PersonOverview.fxml`, какой контроллер он должен использовать, а так же не указали соответствие между элементами представления и полями внутри класса-контроллера. Для этого:

1. Откройте файл `PersonOverview.fxml` в приложении *Scene Builder*.

2. Откройте вкладку *Controller* слева на панели *Document* и выберите класс `PersonControllerOverview` в качестве класса-контроллера.  
![Set Controller Class](/assets/library/javafx-tutorial/part2/set-controller-class.png "Set Controller Class")

3. Выберите компонент `TableView` на вкладке *Hierarchy*, перейдите на вкладку *Code* и в поле **fx:id** установите значение `personTable`.  
![Set TableView fx:id](/assets/library/javafx-tutorial/part2/set-tableview-fx-id.png "Set TableView fx:id")

4. Сделайте то же самое для колонок таблицы и установите значения свойства **fx:id** `firstNameColumn` и `lastNameColumn` соответственно.

5. Для каждой метки во второй колонке компонента GridPane также установите соответствующие значения **fx:id**.  
![Set Label fx:id](/assets/library/javafx-tutorial/part2/set-label-fx-id.png "Set Label fx:id")

6. Важно: сохраните файл `PersonOverview.fxml`, вернитесь в среду разработки Eclipse и обновите весь проект AdressApp (F5). Это необходимо для того, чтобы приложение Eclipse "увидело" те изменения, которые мы сделали в приложении Scene Builder.


*****

## Запуск приложения

После запуска приложения мы должны увидеть что-то похожее на то, что изображено на картинке в начале данной статьи.

Поздравляю!

*Примечание: пока ещё при выборе конкретного адресата у нас не обновляются метки. Взаимодействие с пользователем мы будем программировать в следующей части учебника.*


### Что дальше?

В [3-й части учебника](/library/javafx-tutorial/ru/part3/ "Tutorial Part 3") мы научим наше приложение добавлять, редактировать и удалять информацию в адресной книге.


##### Вам могут быть интересны также некоторые другие статьи

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
