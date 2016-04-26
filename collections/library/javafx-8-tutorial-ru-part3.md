---
layout: article
title: "Учебник по JavaFX 8 - Часть 3: Взаимодействие с пользователем"
date: 2014-04-24 00:00
updated: 2016-21-04 00:00
slug: javafx-8-tutorial/ru/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-ru-part3.md
description: "Реакция на выбор записей в JavaFX TableView. Добавление, редактирование и удаление элементов из таблицы, а так же проверка пользовательского ввода"
image: /assets/library/javafx-8-tutorial/part3/addressapp-part3.png
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
    active: true
  - text: "Часть 4: Стилизация с помощью CSS"
    link: /library/javafx-8-tutorial/ru/part4/
    paging: 4
  - text: "Часть 5: Хранение данных в XML"
    link: /library/javafx-8-tutorial/ru/part5/
    paging: 5
  - text: "Часть 6: Статистическая диаграмма"
    link: /library/javafx-8-tutorial/ru/part6/
    paging: 6
  - text: "Часть 7: Развёртывание"
    link: /library/javafx-8-tutorial/ru/part7/
    paging: 7
- header: Скачать исходники
  body:
  - text: Часть 3 как проект Eclipse <em>(Требуется хотя бы JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-3.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Языки
  collection: library
  item: javafx-8-tutorial
  part: part3
  active: ru
---

**Обновление от 27 августа 2014-го года:** *Сейчас используем JDK 8u20 и ControlsFX 8.0.6_20*

![Учебник по JavaFX 8 - Часть 3: Взаимодействие с пользователем](/assets/library/javafx-8-tutorial/part3/addressapp-part3.png "AdressApp")


## Часть 3: Содержание

* Реакция на выбор записей в таблице.
* Добавление функциональности к кнопкам **add**, **edit** и **remove**.
* Создание пользовательского диалогового окна для редактирование записей адресной книги.
* Проверка пользовательского ввода.


*****


## Реакция на выбор записей в таблице

Пока мы ещё не использовали правую часть нашего приложения. Идея заключается в том, чтобы при выборе записи в таблице, в правой части приложения отображать детали этой записи. 

Сначала давайте добавим новый метод в класс `PersonOverviewController` который поможет нам заполнять текстовые метки данными указанной записи.

Создайте метод `showPersonDetails(Person person)`. Его код проходится по меткам, и, используя метод `setText(...)`, присваивает им соответствующие значения, взятые из переданной в параметре записи Person. Если в качестве параметра передаётся `null`, то весь текст в метках будет очищен.


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


### Преобразование дня рождения в строку

Обратите внимание, что мы не можем присвоить значение поля `birthday` текстовой метке, т.к. тип этого значения `LocalDate` а не `String`. Для того чтобы это сделать, нам надо переформатировать нашу дату рождения.

Т.к. мы будем использовать конвертацию типа `LocalDate` в `String` и обратно в нескольких местах, то хорошей практикой считается создание для этой цели класса-помощника, содержащего статические методы. Наш класс-помощник мы назовем `DateUtil` и разместим его в новом пакете `util`:


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
**Подсказка**: изменяя константу `DATE_PATTERN` можно поменять формат даты. Все возможные форматы описаны в документации к классу [DateTimeFormatter](http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)
</div>



#### Использование класса DateUtil

Теперь нам необходимо использовать наш новый класс `DateUtil` в методе `showPersonDetails` класса `PersonOverviewController`. Замените метку *TODO* следующей строкой:

<pre class="prettyprint lang-java">
birthdayLabel.setText(DateUtil.format(person.getBirthday()));
</pre>


### Слежка за выбором записи в таблице

Для получения информации о том, что пользователь выбрал запись в таблице нам необходимо *прослушивать изменения*.

Для этого в JavaFX существует интерфейс `ChangeListener` с единственным методом `changed(...)`. Этот метод принимает три параметра: `observable`, `oldValue` и `newValue`.

Мы будем создавать интерфейс `ChangeListener` с помощью *лямбда-выражений* из Java 8. Давайте добавим несколько строчек кода к методу `initialize()` класса `PersonOverviewController`. Теперь наш метод выглядит так:


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

Если мы передаем в параметр метода `showPersonDetails(...)` значение `null`, то все значения меток будут стёрты.

В строке `personTable.getSelectionModel...` мы получаем *selectedItemProperty* таблицы и добавляем к нему слушателя. Когда пользователь выбирает запись в таблице, выполняется наше лямбда-выражение. Мы берём только что выбранную запись и передаём её в метод `showPersonDetails(...)`.

**Запустите свое приложение** и проверьте отображаются ли детали записи в правой части когда вы выбираете запись из таблицы.

Если у вас что-то не работает, то вы можете сравнить свой класс `PersonOverviewController` с [PersonOverviewController.java](/assets/library/javafx-8-tutorial/part3/PersonOverviewController.java "PersonOverviewController.java").


*****

## Кнопка Delete

В нашем интерфейсе пользователя есть кнопка **Delete**, но она пока не имеет функциональности. В приложении Scene Builder мы можем задать действие, которое будет выполняться при нажатии на эту кнопку. Любой метод внутри класса-контроллера помеченный аннотацией @FXML (или публичный) доступен приложению Scene Builder. Поэтому, давайте сперва добавим метод удаления записи в конец нашего класса `PersonOverviewController`, а потом уже назначим его обработчиком кнопки **Delete**.


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

Теперь откройте файл `PersonOverview.fxml` в приложении Scene Builder. Выберите кнопку **Delete**, откройте вкладку *Code* и проставьте метод `handleDeletePerson` в значение пункта *On Action*.  

![On Action](/assets/library/javafx-8-tutorial/part3/handle-delete.png "Handle Delete")


<div class="alert alert-info">
**Помните:** После редактирования файла FXML в Scene Builder, чтобы изменения вступили в силу, вам может понадобится обновить проект в Eclipse.
</div>

### Обработка ошибок

Если вы сейчас запустите наше приложение, то уже сможете удалять выбранную запись из таблицы. Но что случится, когда вы нажмете кнопку **Delete**, а ни одна запись выбрана не будет?

Вылетит исключение `ArrayIndexOutOfBoundsException`, потому что мы не можем удалить запись с индексом `-1`. Значение `-1` возвращается методом `getSelectedIndex()` когда в таблице не выделено ни одной ячейки.

Конечно, будет неправильно игнорировать такую ошибку. Поэтому мы должны сообщить пользователю что он, перед тем как нажимать кнопку **Delete**, должен выбрать запись в таблице.

Добавим некоторые изменения в метод `handleDeletePerson`. Теперь, когда пользователь нажимает на кнопку **Delete**, а в таблице ничего не выбрано, он увидит простое диалоговое окно:


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
Чтобы посмотреть другие примеры использования диалогов, откройте статью в блоге автора учебника <a class="alert-link" href="/blog/javafx-dialogs-official/">Диалоги JavaFX</a>.
</div>



*****


## Диалоги создания и редактирования записей

Для реализации методов-обработчиков создания и редактирования записей нам надо будет потрудится немного больше. Необходимо создать новое пользовательское окно (то есть сцену) с формой, содержащей поля для заполнения всех деталей записи.


### Дизайн Окна Редактирования

1. Создайте новый fxml-файл `PersonEditDialog` внутри пакета `view`.  
![Create Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog1.png "Person Edit Dialog")
2. Используйте компоненты `GridPane`, `Label`, `TextField` и `Button` для создания окна редактирования, похожего на это:  
![Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog2.png "Person Edit Dialog")


*Если что-то не работает, вы можете скачать [PersonEditDialog.fxml](/assets/library/javafx-8-tutorial/part3/PersonEditDialog.fxml "PersonEditDialog.fxml").*


### Создание Контроллера

Создайте класс-контроллер `PersonEditDialogController.java`:

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

Некоторые заметки по поводу этого контроллера:

* Для сохранения записи, которая была отредактирована, метод `setPerson(...)` может быть вызван из другого класса;
* Когда пользователь нажимает на кнопку **ОК**, то вызывается метод `handleOK()`. Сперва идёт проверка введённых пользователем данных посредством вызова метода `isInputValid()`. Если проверка прошла успешно, то происходит заполнение записи данными, которые ввёл пользователь. Эти изменения будут напрямую применяться к записи, которая является аргументом метода `setPerson(...)`! 
* Логическая переменная `okClicked` служит для определения того, какую из двух кнопок, `ОК` или `Cancel` нажал пользователь.


### Привязка класса-контроллера к fxml-файлу

Теперь мы должны связать наш класс-контроллер с fxml-файлом. Для этого выполните следующие действия:

1. Откройте файл `PersonEditDialog.fxml` в Scene Builder;
2. С левой стороны во вкладке *Controller* установите наш класс `PersonEditDialogController` как значение параметра *Сontroller Сlass*;
3. Установине соответствующие значения параметра **fx:id** для всех компонентов `TextField`;
4. В значениях параметров **onAction** для наших кнопок укажите соответствующие методы-обработчики.



### Вызов диалога редактирования

Добавьте в наш класс `MainApp` метод для загрузки и отображения диалога редактирования записей:


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

Добавьте следующие методы в класс `PersonOverviewController`. Когда пользователь будет нажимать на кнопки `New...` или `Edit...`, эти методы будут обращаться к методу `showPersonEditDialog(...)` в классе `MainApp`.

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

Откройте `PersonOverview.fxml` в приложении Scene Builder и присвойте соответствующие методы-обработчики параметру *On Action* для кнопок `New...` и `Edit...`.


*****

## Сделано!

Сейчас вы должны получить работающее приложение адресной книги. Оно позволяет добавлять, редактировать и удалять записи. Также приложение осуществляет проверку пользовательского ввода.

Я надеюсь, что концепция и структура данного приложения поможет вам начать писать свои собственные приложения JavaFX! Удачи.


### Что дальше?

В [4-й части учебника](/library/javafx-8-tutorial/ru/part4/ "Tutorial Part 4") мы будем подключать к нашему приложению CSS-стили.


##### Вам могут быть интересны также некоторые другие статьи

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
