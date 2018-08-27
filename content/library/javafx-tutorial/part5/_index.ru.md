+++
title = "Часть 5: Хранение данных в XML"
date = 2014-04-25
updated = 2016-04-22
description = "Сохранение данных в XML с помощью JAXB. Учимся использовать JavaFX-компоненты FileChooser и Menu."
image = "addressapp-part5.png"
prettify = true
comments = true 
commentsIdentifier = "/library/javafx-8-tutorial/ru/part5/"
aliases = [ 
  "/library/javafx-8-tutorial/ru/part5/"
]
weight = 5

[[sidebars]]
header = "Скачать исходники"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Часть 5 как проект Eclipse <em>(Требуется хотя бы JDK 8u40)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-5.zip"
+++

![Screenshot AddressApp Part 5](addressapp-part5.png)


## Часть 5: Содержание

* **Хранение данных в XML**
* Использование компонента JavaFX **FileChooser**
* Использование компонента JavaFX **Menu**
* Сохранение пути к последнему открытому файлу в пользовательских настройках



*****

В данный момент, все данные об адресатах могут находиться исключительно в памяти. Каждый раз, когда мы закрываем адресную книгу, они теряются. Самое время подумать о постоянном хранении данных.


## Сохранение пользовательских настроек

Благодаря классу `Preferences`, Java позволяет сохранять некоторую информацию о состоянии приложения. В зависимости от операционной системы, `Preferences` сохраняются в различных местах (например, в файле реестра Windows).

Мы не можем использовать класс `Preferences` для сохранения всей адресной книги. Но он позволяет сохранять некоторые простые настройки приложения, например, путь к последнему открытому файлу. Имея эти данные, после перезапуска приложения мы всегда сможем восстанавливать состояние нашего приложения.

Следующие два метода обеспечивают сохранение и восстановление настроек нашего приложения. Добавьте их в конец класса `MainApp`:


##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * Возвращает preference файла адресатов, то есть, последний открытый файл.
 * Этот preference считывается из реестра, специфичного для конкретной
 * операционной системы. Если preference не был найден, то возвращается null.
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
 * Задаёт путь текущему загруженному файлу. Этот путь сохраняется
 * в реестре, специфичном для конкретной операционной системы.
 * 
 * @param file - файл или null, чтобы удалить путь
 */
public void setPersonFilePath(File file) {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    if (file != null) {
        prefs.put("filePath", file.getPath());

        // Обновление заглавия сцены.
        primaryStage.setTitle("AddressApp - " + file.getName());
    } else {
        prefs.remove("filePath");

        // Обновление заглавия сцены.
        primaryStage.setTitle("AddressApp");
    }
}
</pre>


## Хранение данных в XML

### Почему именно XML?

Один из наиболее распространённых способов хранения данных, это использование баз данных. В то время, как данные, которые мы должны хранить, являются объектами, базы данных содержат их в виде реляционных данных (например, таблиц). Это называется [объектно-реляционное рассогласование импендансов](http://wikipedia.org/wiki/Object-relational_impedance_mismatch). Для того, чтобы привести наши объектные данные в соответствие с реляционными таблицами, требуется выполнить дополнительную работу. Существуют фреймворки, которые помогают приводить объектные данные в соответствие с реляционной базой данных ([Hibernate](http://www.hibernate.org/) - один из наиболее популярных), но чтобы начать их использовать, также необходимо проделать дополнительную работу и настройку.

Для нашей простой модели данных намного легче хранить данные в виде XML. Для этого мы будем использовать библиотеку [JAXB](https://jaxb.java.net/ "JAXB Homepage") (**J**ava **A**rchitechture for **X**ML **B**inding). Написав всего несколько строк кода, JAXB позволит нам сгенерировать примерно такой XML-файл:

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

Библиотека JAXB уже включена в JDK. Это значит, что никаких дополнительных библиотек подключать не придётся.

JAXB предоставляет две основные функции: способность к **маршаллированию** объектов Java в XML и обратную **демаршализацию** из xml-файла в объекты Java.

Для того, чтобы с помощью JAXB можно было выполнять подобные преобразования, нам необходимо подготовить нашу модель.


#### Подготовка класса-модели для JAXB

Данные, которые мы хотим сохранять, находятся в переменной `personData` класса `MainApp`. JAXB требует, чтобы внешний класс наших данных был отмечен аннотацией `@XmlRootElement` (только класс, поле этой аннотацией пометить нельзя). Типом переменной `personData` является `ObservableList`, а его мы не можем аннотировать. Для того, чтобы разрешить эту ситуацию, необходимо создать класс-обёртку, который будет использоваться исключительно для хранения списка адресатов, и который мы сможем аннотировать как `@XmlRootElement`.

Создайте в пакете `ch.makery.address.model` новый класс `PersonListWrapper`.


##### PersonListWrapper.java

<pre class="prettyprint lang-java">
package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Вспомогательный класс для обёртывания списка адресатов.
 * Используется для сохранения списка адресатов в XML.
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
* `@XmlElement` это необязательное имя, которое мы можем задать для элемента.


#### Чтение и запись данных с помощью JAXB

Сделаем наш класс `MainApp` ответственным за чтение и запись данных нашего приложения. Для этого добавьте в конец класса `MainApp.java` два метода:


<pre class="prettyprint lang-java">
/**
 * Загружает информацию об адресатах из указанного файла.
 * Текущая информация об адресатах будет заменена.
 * 
 * @param file
 */
public void loadPersonDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Чтение XML из файла и демаршализация.
        PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

        personData.clear();
        personData.addAll(wrapper.getPersons());

        // Сохраняем путь к файлу в реестре.
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
 * Сохраняет текущую информацию об адресатах в указанном файле.
 * 
 * @param file
 */
public void savePersonDataToFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Обёртываем наши данные об адресатах.
        PersonListWrapper wrapper = new PersonListWrapper();
        wrapper.setPersons(personData);

        // Маршаллируем и сохраняем XML в файл.
        m.marshal(wrapper, file);

        // Сохраняем путь к файлу в реестре.
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

Маршаллинг и демаршализация готовы. Теперь для того, чтобы использовать новый функционал, давайте создадим пункты меню для сохранения и загрузки. 


## Обработка действий меню

Мы уже создавали меню в файле `RootLayout.fxml`, но пока не использовали его. Перед тем, как мы добавим в наше меню поведение, давайте создадим в нём все необходимые пункты.

В приложении Scene Builder откройте файл `RootLayout.fxml` и перенесите необходимое количество пунктов меню (MenuItem) из вкладки *Library* на вкладку *Hierarchy*. Создайте следующие пункты меню:  **New**, **Open...**, **Save**, **Save as...** и **Exit**.

![Add Menu Items](add-menu-items.png)

Подсказка: для установки на пункты меню горячих клавиш спользуйте свойство *Accelerator* во вкладке *Properties*.


### Класс RootLayoutController

Для обработки поведения меню нам необходим ещё один класс-контроллер. В пакете `ch.makery.address.view` создайте класс `RootLayoutController`.

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
 * Контроллер для корневого макета. Корневой макет предоставляет базовый
 * макет приложения, содержащий строку меню и место, где будут размещены
 * остальные элементы JavaFX.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController {

    // Ссылка на главное приложение
    private MainApp mainApp;

    /**
     * Вызывается главным приложением, чтобы оставить ссылку на самого себя.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Создаёт пустую адресную книгу.
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * Открывает FileChooser, чтобы пользователь имел возможность
     * выбрать адресную книгу для загрузки.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог загрузки файла
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * Сохраняет файл в файл адресатов, который в настоящее время открыт.
     * Если файл не открыт, то отображается диалог "save as".
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
     * Открывает FileChooser, чтобы пользователь имел возможность
     * выбрать файл, куда будут сохранены данные
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Задаём фильтр расширений
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Показываем диалог сохранения файла
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
     * Открывает диалоговое окно about.
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
     * Закрывает приложение.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
</pre>


#### Компонент FileChooser

Обратите внимание на методы в классе `RootLayoutController`, которые используют компонент `FileChooser`. Сперва мы создаём новый экземпляр класса `FileChooser`. Потом применяем фильтр расширения - при выборе файлов будут показываться только те, которые имеют расширение `.xml`. Ну и наконец, мы отображаем данный компонент выше *PrimaryStage*.

Если пользователь закрывает диалог выбора файлов ничего не выбрав, то возвращается `null`. В противном случае мы берём выбранный файл и передаём его в методы `loadPersonDataFromFile(...)` или `savePersonDataToFile(...)`, которые находятся в классе `MainApp.`


### Связывание fxml-представления с классом-контроллером

1. В приложении Scene Builder откройте файл `RootLayout.fxml`. Во вкладке *Controller* в качестве класса-контроллера выберите значение `RootLayoutController`.

2. Перейдите на вкладку *Hierarchy* и выберите пункт меню. Во вкладке *Code* в качестве значений свойства *On Action* вы можете увидеть все доступные методы выбранного класса-контроллера. Выберите метод, соответствующий данному пункту меню.  
![Menu Actions](menu-actions.png)

3. Повторите предыдущий шаг для каждого пункта меню.

4. Закройте приложение Scene Builder и обновите проект (нажмите **Refresh (F5)** на корневой папке вашего проекта). Это позволит среде разработки Eclipse "увидеть" изменения, сделанные в приложении Scene Builder.


### Связывание главного класса с классом RootLayoutController

В некоторых местах кода классу `RootLayoutController` требуется ссылка на класс `MainApp`. Эту ссылку мы ещё пока не передали.

Откройте класс `MainApp` и замените метод `initRootLayout()` следующим кодом:

<pre class="prettyprint lang-java">
/**
 * Инициализирует корневой макет и пытается загрузить последний открытый
 * файл с адресатами.
 */
public void initRootLayout() {
    try {
        // Загружаем корневой макет из fxml файла.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class
                .getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        // Отображаем сцену, содержащую корневой макет.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // Даём контроллеру доступ к главному прилодению.
        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Пытается загрузить последний открытый файл с адресатами.
    File file = getPersonFilePath();
    if (file != null) {
        loadPersonDataFromFile(file);
    }
}
</pre>

Обратите внимание на два изменения: на строки, *дающие доступ контроллеру к главному классу приложения* и на три последних строки для *загрузки последнего открытого файла с записями*.


### Тестирование

Устроив небольшой тест-драйв своему приложению убедитесь, что вы уже можете использовать меню для сохранения информации об адресатах в файл.

Когда вы откроете `xml`-файл в текстовом редакторе, то вместо значения дня рождения увидите пустой тег `<birthday/>`. Дело в том, что JAXB не знает как преобразовать тип `LocalDate` в XML. Чтобы  определить процесс преобразования, мы должны предоставить собственный класс `LocalDateAdapter`.

Внутри пакета `ch.makery.address.util` создайте новый класс `LocalDateAdapter` и скопируйте туда следующий код:

##### LocalDateAdapter.java

<pre class="prettyprint lang-java">
package ch.makery.address.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Адаптер (для JAXB) для преобразования между типом LocalDate и строковым
 * представлением даты в стандарте ISO 8601, например как '2012-12-03'.
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


Потом откройте класс `Person.java` и аннотируйте метод `getBirthday()`:

<pre class="prettyprint lang-java">
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public LocalDate getBirthday() {
    return birthday.get();
}
</pre>

Теперь запустите приложение ещё раз. Попытайтесь сохранить и загрузить xml-файл с данными. Приложение должно автоматически загружать последний открытый файл после перезапуска.



## Как это работает


Давайте посмотрим как это всё работает вместе:

1. Приложение запускается через метод `main(...)` класса `MainApp`.
2. Вызывается конструктор `public MainApp()` и добавляются некоторые тестовые данные.
3. Дальше в классе `MainApp` запускается метод `start(...)`, который вызывает метод `initRootLayout()` для инициализации корневого макета из файла `RootLayout.fxml`. Файл fxml уже знает, какой контроллер следует использовать и связывает представление с `RootLayoutController`'ом.
4. Класс `MainApp` из fxml-загрузчика получает ссылку на `RootLayoutController` и передаёт этому контроллеру ссылку на самого себя. Потом, имея эту ссылку, контроллер может обращаться к публичным методам класса `MainApp`.
5. В конце метода `initRootLayout` мы стараемся из настроек `Preferences` получить путь к последнему открытому файлу адресатов. Если этот файл в настройках описан, то мы загружаем из него данные. Эта процедура перезапишет тестовые данные, которые мы добавляли в конструкторе. 


### Что дальше?

В [6-й части учебника](/ru/library/javafx-tutorial/part6/ "Tutorial Part 6") мы добавим статистический график дней рождений.


##### Вам могут быть интересны также некоторые другие статьи
* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
