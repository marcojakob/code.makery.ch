---
layout: article
title: "Учебник по JavaFX 8 - Часть 5: Хранение данных в XML"
date: 2014-04-25 00:00
updated: 2016-22-04 00:00
slug: javafx-8-tutorial/ru/part5
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-ru-part5.md
description: "Сохранение данных в XML с помощью JAXB. Учимся использовать JavaFX-компоненты FileChooser и Menu."
image: /assets/library/javafx-8-tutorial/part5/addressapp-part5.png
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
  - text: "Часть 3: Взаимодействие с пользователем"
    link: /library/javafx-8-tutorial/ru/part3/
    paging: 3
  - text: "Часть 4: Стилизация с помощью CSS"
    link: /library/javafx-8-tutorial/ru/part4/
    paging: 4
  - text: "Часть 5: Хранение данных в XML"
    link: /library/javafx-8-tutorial/ru/part5/
    paging: 5
    active: true
  - text: "Часть 6: Статистическая диаграмма"
    link: /library/javafx-8-tutorial/ru/part6/
    paging: 6
  - text: "Часть 7: Развёртывание"
    link: /library/javafx-8-tutorial/ru/part7/
    paging: 7
- header: Скачать исходники
  body:
  - text: Часть 5 как проект Eclipse <em>(Требуется хотя бы JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-5.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Языки
  collection: library
  item: javafx-8-tutorial
  part: part5
  active: ru
---

![Screenshot AddressApp Part 5](/assets/library/javafx-8-tutorial/part5/addressapp-part5.png)


## Часть 5: Содержание

* **Хранение данных в XML**
* Использование компонента JavaFX **FileChooser**
* Использование компонента JavaFX **Menu**
* Сохранение в пользовательских настройках пути к последнему открытому файлу



*****

На данный момент наше приложение может хранить данные только в памяти. Каждый раз, когда мы закрываем адресную книгу, все данные теряются. Поэтому настало время подумать о постоянном хранении данных.


## Сохранение пользовательских настроек

Java позволяет хранить некоторые данные о состоянии приложения в классе `Preferences`. В зависимости от операционной системы, класс `Preferences` пишет данные в разные места (например, в файл реестра Windows).

Мы не можем использовать класс `Preferences` для хранения всей записной книжки. Но мы можем сохранить в нём некоторые настройки приложения, например, путь к последнему открытому файлу. Имея эти данные, мы сможем после перезапуска восстанавливать состояние нашего приложения.

Следующие два метода обеспечивают сохранение и восстановление настроек нашего приложения. Добавьте их в конец класса `MainApp`:


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


## Хранение данных в XML

### Почему именно XML?

Использование баз данных это один из наиболее распространённых способов хранения данных. Пока наши данные, которые мы должны хранить, являются объектами, базы данных содержат их в виде реляционных данных (например, таблиц). Это называется [объектно-реляционное рассогласование импендансов](http://wikipedia.org/wiki/Object-relational_impedance_mismatch). Но для того, чтобы привести наши объектные данные в соответствие с реляционными таблицами, требуется выполнить дополнительную работу. Существуют фреймворки, которые помогают приводить наши объектные данные в соответствие с реляционной базой данных ([Hibernate](http://www.hibernate.org/) - один из наиболее популярных), но чтобы начать их использовать, также необходимо проделать дополнительную работу и настройку.

Для нашего простого приложения намного легче хранить данные в виде XML. Для этого мы будем использовать библиотеку [JAXB](https://jaxb.java.net/ "JAXB Homepage") (**J**ava **A**rchitechture for **X**ML **B**inding). Написав всего несколько строк кода, JAXB позволит нам сгенерировать такой исходный XML-файл:

##### Пример сгенерированного XML-файла

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




### Использование JAXB

Библиотека JAXB изначально включена в JDK. Это значит, что нам не придётся подключать никаких дополнительных библиотек.

JAXB предоставляет две основные функции: способность к **маршаллированию** Java-объектов в XML и обратную **демаршализацию** с xml-файла в Java-объекты.

Для того, чтобы с помощью JAXB можно было выполнять подобные преобразования, нам необходимо подготовить нашу модель.


#### Подготовка класса-модели для JAXB

В переменной `personData` класса `MainApp` находятся данные, которые мы хотим сохранять. JAXB требует пометить внешний класс наших данных аннотацией `@XmlRootElement` (только класс, поле этой аннотацией пометить нельзя). Типом переменной `personData` является `ObservableList`, его мы не можем отметить аннотацией. Для того, чтобы разрешить эту ситуацию, необходимо создать класс-обёртку, который будет содержать только нашу коллекцию записей, и на который мы поставим аннотацию `@XmlRootElement`.

Создайте в пакете `model` новый класс `PersonListWrapper`.


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

    private List<Person> persons;

    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
</pre>

Обратите внимание на две аннотации:

* `@XmlRootElement` определяет имя корневого элемента.
* `@XmlElement` определяет имя элемента, которое нам указывать необязательно.


#### Чтение и запись данных с помощью JAXB

Сделаем наш класс `MainApp` ответственным за чтение и запись данных нашего приложения. Для этого добавьте два метода в конец класса `MainApp.java`:


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

Маршаллинг и демаршализация готовы. Теперь для того, чтобы использовать данные возможности, давайте создадим пункты меню "*Сохранить*" и "*Загрузить*". 


## Обработка действий меню

Мы уже создавали меню в файле `RootLayout.fxml`, но пока не использовали его. Перед тем, как мы добавим поведение нашему меню, давайте создадим в нём все необходимые нам пункты.

Откройте файл `RootLayout.fxml` в приложении Scene Builder и перенесите необходимое количество пунктов меню (MenuItem) из вкладки *Library* на вкладку *Hierarchy*. Создайте следующие пункты меню:  **New**, **Open...**, **Save**, **Save as...** и **Exit**.

![Add Menu Items](/assets/library/javafx-8-tutorial/part5/add-menu-items.png)

Подсказка: Используйте свойство *Accelerator* в вкладке *Properties* для установки на пункты меню горячих клавиш.


### Класс RootLayoutController

Для обработки поведения меню нам необходим ещё один класс-контроллер. Создайте класс `RootLayoutController` в пакете `ch.makery.address.view`.

Добавьте новому классу-контроллеру следующее содержание:


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

Обратите внимание на методы в классе `RootLayoutController`, которые используют компонент `FileChooser`. Сперва мы создаём новый экземпляр класса `FileChooser`. Потом применяем фильтр расширения - при выборе файлов будут показываться только те, которые имеют расширение `.xml`. Ну и наконец, мы отображаем данный компонент поверх *PrimaryStage*.

Если пользователь закрывает диалог выбора файлов не выбрав ни никакого файла, то возвращается `null`. В противном случае мы берём выбранный файл и передаём его в методы `loadPersonDataFromFile(...)` или `savePersonDataToFile(...)`, которые находятся в классе `MainApp.`


### Связывание fxml-файла с классом-контроллером

1. Откройте файл `RootLayout.fxml` в приложении Scene Builder. На вкладке *Controller* в качестве класса-контроллера выберите значение `RootLayoutController`.

2. Перейдите на вкладку *Hierarchy* и выберите пункт меню. Во вкладке *Code* в качестве значений свойства *On Action* вы можете увидеть все доступные методы выбранного класса-контроллера. Выберите метод, соответствующий данному пункту меню.  
![Menu Actions](/assets/library/javafx-8-tutorial/part5/menu-actions.png)

3. Повторите предыдущий шаг для каждого пункта меню.

4. Закройте приложение Scene Builder и обновите проект (нажмите **Refresh (F5)** на корневой папке вашего проекта). Это позволит среде разработки Eclipse "увидеть" изменения, сделанные вами в приложении Scene Builder.


### Связывание основного класса с классом RootLayoutController

В некоторых местах кода класс `RootLayoutController` требует ссылку на класс `MainApp`. Пока мы эту ссылку ещё не передали.

Откройте класс `MainApp` и замените метод `initRootLayout()` следующим кодом:

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

Обратите внимание на два изменения: на строки, *дающие доступ контроллеру к основному классу приложения* и на три последних строки для *загрузки последнего открытого файла с записями*.


### Тестирование

Устроив небольшой тест-драйв вашему приложению убедитесь, что вы уже можете использовать меню для сохранения в файл адресных данных.

Когда вы откроете `xml`-файл в редакторе, то вместо значения дня рождения увидите пустой тег `<birthday/>`. Дело в том, что JAXB не знает как преобразовать в XML тип `LocalDate`. Мы должны предоставить собственный класс `LocalDateAdapter` и вручную определить процесс преобразования.

Внутри пакета `ch.makery.address.util` создайте новый класс `LocalDateAdapter` и скопируйте туда следующий код:

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
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

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


Потом откройте класс `Person.java` и добавьте к методу `getBirthday()` следующую аннотацию:

<pre class="prettyprint lang-java">
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public LocalDate getBirthday() {
    return birthday.get();
}
</pre>

Теперь протестируйте приложение ещё раз. Попытайтесь сохранить и загрузить xml-файл с данными. После перезапуска, приложение должно автоматически загрузить последний открытый файл.



## Как это работает


Давайте посмотрим как всё это работает вместе:

1. Приложение запускается через метод `main(...)` из класса `MainApp`.
2. Вызывается конструктор `public MainApp()` и добавляются некоторые тестовые данные.
3. Дальше в классе `MainApp` запускается метод `start(...)`, который вызывает метод `initRootLayout()` для инициализации корневой разметки из файла `RootLayout.fxml`. Файл fxml уже знает, какой контроллер следует использовать и связывает представление с `RootLayoutController`'ом.
4. Класс `MainApp` из fxml-загрузчика получает ссылку на `RootLayoutController` и передаёт этому контроллеру ссылку на самого себя. Потом, имея эту ссылку, контроллер может обращаться к публичным методам класса `MainApp`.
5. В конце метода `initRootLayout` мы стараемся из настроек `Preferences` получить путь к последнему открытому файлу записей. Если этот файл в настройках описан, то мы загружаем из него данные. Эта процедура перезапишет тестовые данные, которые мы загружали в конструкторе. 


### Что дальше?

В [6-й части учебника](/library/javafx-8-tutorial/ru/part6/ "Tutorial Part 6") мы добавим статистический график дней рождений.


##### Вам могут быть интересны также некоторые другие статьи
* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
