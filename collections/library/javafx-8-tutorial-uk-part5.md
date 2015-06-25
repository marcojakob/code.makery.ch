---
layout: article
title: "Підручник з JavaFX 8 - Частина 5: Збереження даних в XML"
date: 2014-04-25 00:00
updated: 2015-06-25 00:00
slug: javafx-8-tutorial/uk/part5
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-uk-part5.md
description: "Збереження даних в XML за допомогою JAXB. Вчимося використовувати JavaFX компоненти FileChooser та Menu"
image: /assets/library/javafx-8-tutorial/part5/addressapp-part5.png
published: true
prettify: true
comments: true
sidebars:
- header: "Статті цієї серії"
  body:
  - text: "Вступ"
    link: /library/javafx-8-tutorial/uk/
    paging: Intro
  - text: "Частина 1: Scene Builder"
    link: /library/javafx-8-tutorial/uk/part1/
    paging: 1
  - text: "Частина 2: Модель та компонент TableView"
    link: /library/javafx-8-tutorial/uk/part2/
    paging: 2
  - text: "Частина 3: Взаємодія з користувачем"
    link: /library/javafx-8-tutorial/uk/part3/
    paging: 3
  - text: "Частина 4: Стилізація за допомогою CSS"
    link: /library/javafx-8-tutorial/uk/part4/
    paging: 4
  - text: "Частина 5: Збереження даних в XML"
    link: /library/javafx-8-tutorial/uk/part5/
    paging: 5
    active: true
  - text: "Частина 6: Статистична діаграма"
    link: /library/javafx-8-tutorial/uk/part6/
    paging: 6
  - text: "Частина 7: Розгортання"
    link: /library/javafx-8-tutorial/uk/part7/
    paging: 7
- header: Скачати вихідний код
  body:
  - text: Частина 5 як проект Eclipse <em>(Необхідно хоча б JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-5.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Мови
  collection: library
  item: javafx-8-tutorial
  part: part5
  active: uk
---

![Screenshot AddressApp Part 5](/assets/library/javafx-8-tutorial/part5/addressapp-part5.png)

## Частина 5: Зміст

* **Збереження даних в XML**
* Використання компоненту JavaFX **FileChooser**
* Використання компоненту JavaFX **Menu**
* Збереження шляху до останнього відкритого файлу в налаштуваннях користувача

*****

На даний момент, наш додаток вміє зберігати дані тільки в пам'яті. Кожен раз, коли ми закриваємо його, дані втрачаються. Тому настав час подумати про постійне їх збереження.

## Збереження налаштувань користувача

Java дозволяє нам зберігати деякі дані про стан програми в класі `Preferences`. Залежно від операційної системи, клас `Preferences` записує дані в різні місця (наприклад, у файл регістру в ОС Windows).

Ми не можемо використовувати клас `Preferences` для зберігання всього нашого записника. Але ми можемо зберегти там деякі **прості налаштування програми**, наприклад, **шлях до останнього відкритого файлу**. Маючи ці дані, ми зможемо відновлювати стан нашого додатку після перевантаження.

Наступні два методи забезпечують збереження і відновлення налаштувань нашого додатку. Додайте їх в кінець класу `MainApp`:

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

## Збереження даних в XML

### Чому саме XML?

Використання баз даних є одним з найбільш поширених способів зберігання даних. Бази даних зазвичай містять деякий вид реляційних даних (наприклад, таблиці), а дані, які ми повинні зберігати - це об'єкти. Це називається [об'єктно-реляційною невідповідністю](http://wikipedia.org/wiki/Object-relational_impedance_mismatch). Але для того, щоб привести наші об'єктні дані у відповідність із реляційними таблицями, потрібно виконати додаткову роботу. Існують фреймворки, які допомагають в цьому (наприклад, [Hibernate](http://www.hibernate.org/) - один з найбільш популярних), але щоб почати їх використовувати, необхідно проробити додаткову роботу для налаштування.

Для нашого простого додатку набагато легше зберігати дані у вигляді XML. Для цього ми будемо використовувати бібліотеку [JAXB](https://jaxb.java.net/ "JAXB") (**J**ava **A**rchitechture for **X**ML **B**inding). Написавши всього кілька рядків коду, JAXB дозволить нам згенерувати такий вихідний XML файл:

##### Приклад згенерованого XML файлу

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

### Використання JAXB

Бібліотека JAXB вбудована в JDK. Це означає, що ніяких додаткових бібліотек нам підключати не доведеться.

JAXB надає дві основні функції: **маршалювання** (marshalling) Java об'єктів в XML й зворотну - **демаршалювання** (unmarshalling) з XML файлу в Java об'єкти.

Для того, щоб JAXB надавав можливість такої конвертації, нам необхідно підготувати нашу модель.

#### Підготовка класу-моделі для JAXB

Дані, які ми хочемо зберегти, знаходяться у змінній `personData` в класі `MainApp`. JAXB вимагає помітити анотацією `@XmlRootElement` зовнішній клас наших даних (тільки клас, поле цієї анотацією помітити не можна). Тип змінної `personData` є `ObservableList`, в який ми не можемо втрутитись. Тому, для того, щоб вирішити цю проблему, нам необхідно створити клас-обгортку, який буде містити тільки нашу колекцію записів, і на який ми поставимо анотацію `@XmlRootElement`.

Створіть у пакеті `model` новий клас `PersonListWrapper`.

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

Зверніть увагу на дві анотації:

* `@XmlRootElement` визначає ім'я кореневого елемента.
* `@XmlElement` визначає ім'я елемента, яке нам вказувати не обов'язково.

#### Читання і запис даних за допомогою JAXB

Зробимо наш клас `MainApp` відповідальним за читання і запис даних нашого додатку. Для цього додайте два методи в кінець класу `MainApp.java`:

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

Можливість маршалізації та демаршалізації готові. Тепер давайте створимо пункти меню *"Зберегти"* і *"Завантажити"* для того, щоб використовувати дані можливості.

## Обробка дій меню

Ми вже створювали меню в файлі `RootLayout.fxml`, яке поки що не використали. Перед тим, як ми додамо поведінку нашому меню, давайте створимо всі необхідні нам пункти.

Відкрийте файл `RootLayout.fxml` в додатку Scene Builder і перенесіть необхідну кількість пунктів меню з вкладки *Бібліотека* на вкладку *Ієрархія* в `MenuBar`. Створіть наступні пункти меню: **Новий**, **Відкрити...**, **Зберегти**, **Зберегти як...** та **Вихід**.

![Add Menu Items](/assets/library/javafx-8-tutorial/part5/add-menu-items.png)

Підказка: Використовуйте властивість *Accelerator* на вкладці *Properties* для встановлення гарячих клавіш для пунктів меню.

### Клас RootLayoutController

Для обробки поведінки меню нам необхідний новий клас-контролер. Створіть клас `RootLayoutController` в пакеті `ch.makery.address.view`.

Додайте до класу-контролера такий вміст:

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

#### Компонент FileChooser

Зверніть увагу на методи, які використовують компонент `FileChooser` в класі `RootLayoutController`. Спершу ми створюємо новий екземпляр класу `FileChooser`. Потім ми застосовуємо фільтр розширення і при виборі файлів будуть показуватися лише ті, які мають розширення `.xml`. Ну і нарешті, ми відображаємо даний компонент над *PrimaryStage*.

Якщо користувач закриває діалог вибору файлів не вибравши жодного файлу, тоді повертається `null`. В іншому випадку, ми передаємо об'єкт вибраного файлу всередину методу `loadPersonDataFromFile(...)` або `savePersonDataToFile(...)`, які знаходяться в класі `MainApp`.

### З'єднання FXML файлу з класом-контролером

1. Відкрийте файл `RootLayout.fxml` в додатку Scene Builder. На вкладці *Controller* в якості класу-контролера виберіть значення `RootLayoutController`.

2. Перейдіть на вкладку *Hierarchy* і виберіть пункт меню. У вкладці *Code* в якості значень властивості *On Action* ви зможете побачити всі доступні методи обраного класу-контролера. Виберіть той метод з перечислених у випадаючому списку справа, який відповідає цьому пункту меню.   
![Menu Actions](/assets/library/javafx-8-tutorial/part5/menu-actions.png)

3. Повторіть попередній крок для кожного пункту меню.

4. Закрийте додаток Scene Builder та оновіть ваш проект (натиснувши **Refresh (F5)** на кореневій папці вашого проекту). Це дозволить середовищу розробки Eclipse побачити зроблені вами зміни в додатку Scene Builder.

### З'єднання класу MainApp з класом RootLayoutController

У деяких місцях коду клас `RootLayoutController` вимагає посилання на клас `MainApp`. Поки ми це посилання ще не передали.

Відкрийте клас `MainApp` і замініть метод `initRootLayout()` наступним кодом:

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

Зверніть увагу на дві зміни: на рядки, *що дають доступ контролеру до основного класу програми* і на три останніх рядки для *завантаження останнього відкритого файлу із записами*.

### Тестування

Влаштувавши тест-драйв вашому додатку, переконайтеся, що ви здатні використовувати меню для збереження записів в файл.

Коли ви відкриєте `xml` файл в редакторі, то замість значення дня народження ви побачите порожній тег `<birthday/>`. Справа в тому, що JAXB не знає як виконати конвертацію типу `LocalDate` у формат XML. Тому, ми повинні надати власний клас `LocalDateAdapter` і визначити процес конвертації вручну.

Створіть новий клас `LocalDateAdapter` всередині пакету `ch.makery.address.util` і скопіюйте туди наступний код:

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

Потім відкрийте клас `Person.java` і додайте до методу `getBirthday()` наступний код:

<pre class="prettyprint lang-java">
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public LocalDate getBirthday() {
    return birthday.get();
}
</pre>

Тепер протестуйте додаток ще раз. Спробуйте зберегти і завантажити XML файл з даними. Після перезапуску, додаток повинен автоматично завантажити останній відкритий файл.

## Як це працює

Давайте подивимося як все це працює разом:

1. Додаток запускається через метод `main(...)` з класу `MainApp`.
2. Викликається конструктор `public MainApp()`, що додає деякі тестові дані.
3. Далі запускається метод `start(...)` в класі `MainApp` і викликає метод `initRootLayout()` для ініціалізації кореневої розмітки з файлу `RootLayout.fxml`. Fxml файл володіє інформацією про те, який контролер використовувати та пов'язує представлення з контролером `RootLayoutController`.
4. Клас `MainApp` отримує посилання на `RootLayoutController` з завантажувача fxml файлу і передає цьому контролеру посилання на себе. З цим посиланням контролер потім зможе отримати доступ до публічних методів класу `MainApp`.
5. Наприкінці методу `initRootLayout` ми намагаємося отримати шлях до останнього відкритого файлу записів з налаштувань `Preferences`. Якщо налаштування мають інформацію про такий файл, то ми завантажуємо з нього дані. Ця процедура перезапише тестові дані, які ми завантажували в конструкторі.

### Що далі?

В [6 частині підручника](/library/javafx-8-tutorial/uk/part6/ "Tutorial Part 6") ми додамо статистичний графік днів народження.

##### Вам можуть бути цікаві також деякі інші статті

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
