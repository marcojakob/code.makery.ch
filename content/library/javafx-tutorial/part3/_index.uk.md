---
layout: article
title: "Підручник з JavaFX 8 - Частина 3: Взаємодія з користувачем"
date: 2014-04-24
updated: 2015-06-25
slug: javafx-8-tutorial/uk/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-uk-part3.md
description: "Реакція на вибір записів у JavaFX TableView. Додавання, редагування та видалення елементів з таблиці, а також перевірка вводу користувача"
image: /assets/library/javafx-8-tutorial/part3/addressapp-part3.png
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
    active: true
  - text: "Частина 4: Стилізація за допомогою CSS"
    link: /library/javafx-8-tutorial/uk/part4/
    paging: 4
  - text: "Частина 5: Збереження даних в XML"
    link: /library/javafx-8-tutorial/uk/part5/
    paging: 5
  - text: "Частина 6: Статистична діаграма"
    link: /library/javafx-8-tutorial/uk/part6/
    paging: 6
  - text: "Частина 7: Розгортання"
    link: /library/javafx-8-tutorial/uk/part7/
    paging: 7
- header: Скачати вихідний код
  body:
  - text: Частина 3 як проект Eclipse <em>(Необхідно хоча б JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-3.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Мови
  collection: library
  item: javafx-8-tutorial
  part: part3
  active: uk
---

![Підручник з JavaFX 8 - Частина 3: Взаємодія з користувачем](/assets/library/javafx-8-tutorial/part3/addressapp-part3.png "AdressApp")

## Частина 3: Зміст

* **Реакція на вибір записів** у таблиці.
* Додавання функціональності для кнопок **add**, **edit** і **remove**.
* Створення **діалогового вікна** користувача для редагування записів адресної книги.
* **Перевірка** введення користувача.

*****

## Реакція на вибір записів в таблиці

Поки ми ще не використали праву частину нашого додатку. Ідея полягає в тому, що, при виборі запису в таблиці, відображаються деталі цього запису в правій частині програми.

Спочатку давайте додамо новий метод в клас `PersonOverviewController`, який допоможе нам заповнювати текстові мітки даними зазначеного запису.

Створіть метод `showPersonDetails(Person person)`. Пройдіться по текстових мітках та надайте їм відповідні значення, взяті з переданого параметром запису, використовуючи метод `setText(...)`. Якщо як параметр передається `null`, весь текст в мітках повинен бути очищений.

##### PersonOverviewController.java

<pre class = "prettyprint lang-java">
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

### Конвертація дня народження в рядок

Зверніть увагу, що ми не можемо присвоїти значення поля `birthday` текстовій мітці, оскільки тип цього значення `LocalDate` а не `String`. Для того щоб це зробити, нам треба переформатувати нашу дату народження.

Оскільки ми будемо використовувати конвертацію типу `LocalDate` в `String` і навпаки в декількох місцях, то хорошою практикою вважається створення класу-помічника, що містить статичні методи для цієї мети. Цей клас-помічник ми назвемо `DateUtil` і помістимо його в новий пакет `ch.makery.address.util`:

##### DateUtil.java

<pre class = "prettyprint lang-java">
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

<div class = "alert alert-info">
**Підказка**: Ви можете змінити формат дати, змінюючи константу `DATE_PATTERN`. За всіма можливими форматами дивіться документацію до класу [DateTimeFormatter](http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)
</div>

#### Використання класу DateUtil

Тепер нам необхідно використати новий клас `DateUtil` в методі `showPersonDetails` класу `PersonOverviewController`. Замініть мітку *TODO* наступним рядком:

<pre class="prettyprint lang-java">
birthdayLabel.setText(DateUtil.format(person.getBirthday()));
</pre>

### Стежимо за зміною вибору запису в таблиці

Для отримання інформації про те, що користувач вибрав запис у таблиці, нам необхідно *прослуховувати зміни*.

Для цього в JavaFX існує інтерфейс `ChangeListener` з одним методом `changed(...)`. Цей метод має три параметри: `observable`, `oldValue` і `newValue`.

Ми будемо створювати інтерфейс `ChangeListener` користуючись *лямбда-виразами* з Java 8. Давайте додамо кілька рядків коду до методу `initialize()` класу `PersonOverviewController`. Тепер наш метод виглядає так:

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

Якщо ми передаємо `null` в параметр методу `showPersonDetails(...)`, то будуть стерті всі значення міток.

У рядку `personTable.getSelectionModel...` ми отримуємо *selectedItemProperty* таблиці і додаємо до нього слухача. Коли користувач вибирає запис у таблиці, виконується наш лямбда-вираз. Ми беремо тільки що обраний запис і передаємо його в метод `showPersonDetails(...)`.

**Запустіть свій додаток** і перевірте, чи відображаються деталі запису в правій частині, коли ви обираєте запис з таблиці.

Якщо у вас щось не працює, то ви можете порівняти свій клас `PersonOverviewController` з моїм [PersonOverviewController.java](/assets/library/javafx-8-tutorial/part3/PersonOverviewController.java "PersonOverviewController.java").

*****

## Кнопка Delete

У нашому інтерфейсі користувача є кнопка **Delete**, але поки у неї немає функціональності. У додатку *Scene Builder* ми можемо вказати на те, яка дія буде виконуватися при натисканні на цю кнопку. Будь-який метод всередині класу-контролера, позначений анотацією @FXML (або публічний) доступний додатку *Scene Builder*. Тому, давайте спершу додамо метод видалення запису в кінець нашого класу `PersonOverviewController`, а потім вже призначимо його в ролі обробника кнопки **Delete**.

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

Тепер відкрийте файл `PersonOverview.fxml` в додатку *Scene Builder*. Виберіть кнопку **Delete**, відкрийте вкладку *Code* і проставте метод `handleDeletePerson` в значення пункту *On Action*.

![On Action](/assets/library/javafx-8-tutorial/part3/handle-delete.png "Handle Delete")

<div class = "alert alert-info">
**Пам'ятайте**: Після внесення змін у FXML файл в Scene Builder вам, можливо, буде потрібно оновити проект в Eclipse, щоб зміни застосовувалися.
</div>

### Обробка помилок

Якщо ви зараз запустите додаток, то ви зможете видаляти обраний запис з таблиці. Але що трапиться, коли ви **натиснете кнопку Delete, а жодний запис вибраний не буде?**

Ми отримаємо виняткову ситуацію `ArrayIndexOutOfBoundsException`, тому що ми не можемо видалити запис з індексом `-1`. Індекс `-1` повертається методом `getSelectedIndex()` коли в таблиці не виділено нічого.

Звичайно, не дуже добре ігнорувати таку помилку. Тому ми повинні повідомити користувачу, що він, перед тим як натискати кнопку **Delete**, повинен вибрати запис у таблиці. (Ще краще було б, якби ми відключили кнопку, щоб користувач навіть не мав змоги зробити щось неправильно.)

Додавши деякі зміни в метод `handleDeletePerson`, ми можемо показувати просте діалогове вікно, коли користувач натискає на кнопку **Delete**, а в таблиці нічого не вибрано:

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

<div class = "alert alert-info">
Щоб подивитися додаткові приклади використання діалогових вікон, читайте статтю [Діалоги в JavaFX 8](http://code.makery.ch/blog/javafx-dialogs-official/)
</div>

*****

## Діалоги створення і редагування записів

Для реалізації методів-обробників створення і редагування записів, нам потрібно попрацювати трохи більше. Нам необхідно створити нове вікно користувача (тобто нову сцену) з формою, що містить поля для заповнення всіх деталей запису.

### Дизайн вікна редагування

1. Створіть новий fxml файл під назвою `PersonEditDialog` всередині пакету `view`.
![Create Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog1.png "Person Edit Dialog")
2. Використовуйте компоненти `GridPane`, `Label`, `TextField` і `Button` для створення подібного вікна редагування:   
![Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog2.png "Person Edit Dialog")

*Якщо щось не працює, ви можете скачати [PersonEditDialog.fxml](/assets/library/javafx-8-tutorial/part3/PersonEditDialog.fxml "PersonEditDialog.fxml").*

### Створення контролера

Створіть клас-контролер `PersonEditDialogController.java`:

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

Деякі нотатки з приводу цього контролера:

* Метод `setPerson(...)` може бути викликаний з іншого класу для збереження запису, який був відредагований;
* Коли користувач натискає на кнопку **ОК**, то викликається метод `handleOK()`. Спершу йде перевірка введених користувачем даних через виклик методу `isInputValid()`. Якщо перевірка пройшла успішно, йде заповнення запису тими даними, які ввів користувач. Ці зміни будуть прямо застосовуватись до об'єкту, що був переданий до методу `setPerson(...)`!
* Логічна змінна `okClicked` використовується для визначення того, на яку з кнопок натиснув користувач: `ОК` або `Cancel`.

### Прив'язка класу-контролера до fxml файлу

Для працездатності нашої програми, ми повинні зв'язати разом клас-контролер та fxml файл. Для цього виконайте такі дії:

1. Відкрийте файл `PersonEditDialog.fxml` в додатку Scene Builder;
2. З лівого боку, у вкладці *Controller*, встановіть клас `PersonEditDialogController` як значення параметра *Сontroller Сlass*;
3. Встановіть відповідні значення параметра **fx:id** для всіх компонентів `TextField`;
4. Встановіть значення параметра **onAction** для кнопок, тобто надайте їм відповідні методи-обробники.

### Виклик діалогу редагування

Додайте метод для завантаження і відображення діалогу редагування записів в наш клас `MainApp`:

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

Додайте наступні методи в клас `PersonOverviewController`. Коли користувач буде натискати на кнопки `New ...` або `Edit ...`, ці методи будуть викликати метод `showPersonEditDialog(...)` з класу `MainApp`.

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

Відкрийте `PersonOverview.fxml` в додатку Scene Builder і надайте відповідні методи-обробники параметру *On Action* для кнопок `New...` і `Edit...`.

*****

## Зроблено!

Зараз ви повинні отримати працездатний додаток адресної книги. Він здатний додавати, редагувати і видаляти записи. Також він може виконувати перевірку введеного користувачем для уникнення появи некоректних значень в текстових полях.

Я сподіваюся, що концепція і структура даного додатку допоможе вам почати писати свої власні програми за допомогою JavaFX! Удачі!

### Що далі?

В [4 частині підручника](/library/javafx-8-tutorial/uk/part4/"Tutorial Part 4") ми будемо підключати CSS-стилі до нашого додатку.

##### Вам можуть бути цікаві також деякі інші статті

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
