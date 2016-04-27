---
layout: article
title: "Учебник по JavaFX 8 - Часть 3: Взаимодействие с пользователем"
date: 2014-04-24 00:00
updated: 2016-21-04 00:00
slug: javafx-8-tutorial/ru/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-ru-part3.md
description: "Реакция на выбор записей в JavaFX TableView. Добавление, изменение и удаление элементов из таблицы, а так же проверка пользовательского ввода"
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

* Реакция на выбор адресатов в таблице.
* Добавление функциональности кнопкам **add**, **edit** и **remove**.
* Создание диалогового окна для изменения информации об адресатах.
* Проверка пользовательского ввода.


*****


## Реакция на выбор адресатов в таблице

Пока мы ещё не использовали правую часть нашего приложения. Идея заключается в том, чтобы при выборе адресата в таблице, в правой части приложения отображать дополнительную информацию об этом адресате. 

Сначала давайте добавим новый метод в класс `PersonOverviewController`. Он поможет нам заполнять текстовые метки данными указанного адресата (`Person`).

Создайте метод `showPersonDetails(Person person)`. Его код проходится по меткам, и, используя метод `setText(...)`, присваивает им соответствующие значения, взятые из переданного в параметре объекта Person. Если в качестве параметра передаётся `null`, то весь текст в метках будет очищен.


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Заполняет все текстовые поля, отображая подробности об адресате.
 * Если указанный адресат = null, то все текстовые поля очищаются.
 * 
 * @param person — адресат типа Person или null
 */
private void showPersonDetails(Person person) {
    if (person != null) {
        // Заполняем метки информацией из объекта person.
        firstNameLabel.setText(person.getFirstName());
        lastNameLabel.setText(person.getLastName());
        streetLabel.setText(person.getStreet());
        postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
        cityLabel.setText(person.getCity());

        // TODO: Нам нужен способ для перевода дня рождения в тип String! 
        // birthdayLabel.setText(...);
    } else {
        // Если Person = null, то убираем весь текст.
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

Обратите внимание, что мы просто так не можем присвоить текстовой метке значение поля `birthday`, так как тип его значения `LocalDate` а не `String`. Для того чтобы это сделать, нам надо сначала отформатировать дату.

Мы будем преобразовывать тип `LocalDate` в тип `String` и обратно в нескольких местах программы, поэтому, хорошей практикой считается создание для этой цели вспомогательного класса, содержащего статические методы. Этот вспомогательный класс мы назовем `DateUtil` и разместим его в новом пакете `ch.makery.address.util`:


##### DateUtil.java

<pre class="prettyprint lang-java">
package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Вспомогательные функции для работы с датами.
 * 
 * @author Marco Jakob
 */
public class DateUtil {
	
	/** Шаблон даты, используемый для преобразования. Можно поменять на свой. */
	private static final String DATE_PATTERN = "dd.MM.yyyy";
	
	/** Форматировщик даты. */
	private static final DateTimeFormatter DATE_FORMATTER = 
			DateTimeFormatter.ofPattern(DATE_PATTERN);
	
	/**
     * Возвращает полученную дату в виде хорошо отформатированной строки.
     * Используется определённый выше {@link DateUtil#DATE_PATTERN}.
     * 
     * @param date - дата, которая будет возвращена в виде строки
     * @return отформатированную строку
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Преобразует строку, которая отформатирована по правилам
     * шаблона {@link DateUtil#DATE_PATTERN} в объект {@link LocalDate}.
     * 
     * Возвращает null, если строка не может быть преобразована.
     * 
     * @param dateString - дата в виде String
     * @return объект даты или null, если строка не может быть преобразована
     */
    public static LocalDate parse(String dateString) {
        try {
        	return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Проверяет, является ли строка корректной датой.
     * 
     * @param dateString
     * @return true, если строка является корректной датой
     */
    public static boolean validDate(String dateString) {
    	// Пытаемся разобрать строку.
    	return DateUtil.parse(dateString) != null;
    }
}
</pre>

<div class="alert alert-info">
**Подсказка**: формат даты можно поменять, просто изменив константу `DATE_PATTERN`. Все возможные форматы описаны в документации к классу [DateTimeFormatter](http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)
</div>



#### Использование класса DateUtil

Теперь мы можем использовать наш новый класс `DateUtil` в методе `showPersonDetails` класса `PersonOverviewController`. Замените комментарий *TODO* следующей строкой:

<pre class="prettyprint lang-java">
birthdayLabel.setText(DateUtil.format(person.getBirthday()));
</pre>


### Наблюдение за выбором адресатов в таблице

Чтобы знать о том, что пользователь выбрал определённую запись в таблице, нам необходимо *прослушивать изменения*.

Для этого в JavaFX существует интерфейс `ChangeListener` с единственным методом `changed(...)`. Этот метод имеет три параметра: `observable`, `oldValue` и `newValue`.

Мы будем реализовывать интерфейс `ChangeListener` с помощью *лямбда-выражений* из Java 8. Давайте добавим несколько строчек кода к методу `initialize()` класса `PersonOverviewController`. Теперь этот метод выглядит так:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
@FXML
private void initialize() {
    // Инициализация таблицы адресатов с двумя столбцами.
    firstNameColumn.setCellValueFactory(
            cellData -> cellData.getValue().firstNameProperty());
    lastNameColumn.setCellValueFactory(
            cellData -> cellData.getValue().lastNameProperty());

    // Очистка дополнительной информации об адресате.
    showPersonDetails(null);

    // Слушаем изменения выбора, и при изменении отображаем
    // дополнительную информацию об адресате.
    personTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showPersonDetails(newValue));
}
</pre>

Если мы передаём в параметр метода `showPersonDetails(...)` значение `null`, то все значения меток будут стёрты.

В строке `personTable.getSelectionModel...` мы получаем *selectedItemProperty* таблицы и добавляем к нему слушателя. Когда пользователь выбирает запись в таблице, выполняется наше лямбда-выражение. Мы берём только что выбранную запись и передаём её в метод `showPersonDetails(...)`.

**Запустите свое приложение** и проверьте, отображаются ли детали адресата в правой части, когда в таблице выбирается определённый адресат.

Если у вас что-то не работает, то вы можете сравнить свой класс `PersonOverviewController` с [PersonOverviewController.java](/assets/library/javafx-8-tutorial/part3/PersonOverviewController.java "PersonOverviewController.java").


*****

## Кнопка Delete

В нашем пользовательским интерфейсе есть кнопка **Delete**, но пока она не работает. В приложении Scene Builder мы можем задать действие, которое будет выполняться при нажатии на эту кнопку. Любой метод внутри класса-контроллера, помеченный аннотацией @FXML (или публичный), доступен Scene Builder. Поэтому, давайте сперва добавим в конец класса `PersonOverviewController` метод удаления адресата, а уже потом назначим его обработчиком кнопки **Delete**.


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Вызывается, когда пользователь кликает по кнопке удаления.
 */
@FXML
private void handleDeletePerson() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    personTable.getItems().remove(selectedIndex);
}
</pre>

Теперь в приложении Scene Builder откройте файл `PersonOverview.fxml`. Выберите кнопку **Delete**, откройте вкладку *Code* и укажите метод `handleDeletePerson` в значение пункта *On Action*.  

![On Action](/assets/library/javafx-8-tutorial/part3/handle-delete.png "Handle Delete")


<div class="alert alert-info">
**Помните:** После изменения FXML-файла в Scene Builder, для того, чтобы изменения вступили в силу, вам может понадобится обновить проект в Eclipse.
</div>

### Обработка ошибок

Если вы сейчас запустите приложение, то уже сможете удалять выбранных адресатов из таблицы. Но что произойдёт, если не будет выбран ни один адресат, а вы нажмёте кнопку **Delete**,?

Вылетит исключение `ArrayIndexOutOfBoundsException`, потому что не получится удалить адресата с индексом `-1`. Значение `-1` возвращается методом `getSelectedIndex()`, когда в таблице ничего не выделено.

Естественно, игнорировать эту ошибку будет некрасиво. Мы должны сообщать пользователю о том, что он, перед тем как нажимать кнопку **Delete**, должен выбрать запись в таблице. (Ещё лучше совсем деактивировать кнопку, чтобы у пользователя не было соблазна сделать что-то не так).

Добавим в метод `handleDeletePerson` некоторые изменения. Теперь, когда пользователь нажимает на кнопку **Delete**, а в таблице ничего не выбрано, он увидит простое диалоговое окно:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Вызывается, когда пользователь кликает по кнопке удаления.
 */
@FXML
private void handleDeletePerson() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        personTable.getItems().remove(selectedIndex);
    } else {
        // Ничего не выбрано.
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


## Диалоги создания и изменения адресатов

Для реализации методов-обработчиков создания и изменения адресатов нам придётся  потрудится несколько больше. Необходимо создать новое пользовательское окно (то есть сцену) с формой, содержащей поля для заполнения всей необходимой информации об адресате.


### Дизайн окна редактирования

1. Внутри пакета `view` создайте новый fxml-файл `PersonEditDialog`.  
![Create Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog1.png "Person Edit Dialog")
2. Используйте компоненты `GridPane`, `Label`, `TextField` и `Button` для создания окна редактирования, похожего на это:  
![Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog2.png "Person Edit Dialog")


*Если что-то не работает, вы можете скачать [PersonEditDialog.fxml](/assets/library/javafx-8-tutorial/part3/PersonEditDialog.fxml "PersonEditDialog.fxml").*


### Создание контроллера

Создайте класс-контроллер для нового окна - `PersonEditDialogController.java`:

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
 * Окно для изменения информации об адресате.
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
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Устанавливает сцену для этого окна.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Задаёт адресата, информацию о котором будем менять.
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
     * Returns true, если пользователь кликнул OK, в другом случае false.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
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
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     * 
     * @return true, если пользовательский ввод корректен
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
            // пытаемся преобразовать почтовый код в int.
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
            // Показываем сообщение об ошибке.
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

Кое-какие заметки по поводу этого контроллера:

* Для указания адресата, данные которого должны быть изменены, метод `setPerson(...)` может быть вызван из другого класса;
* Когда пользователь нажимает на кнопку **ОК**, то вызывается метод `handleOK()`. Первым делом данные, введённые пользователем, проверяются в методе `isInputValid()`. Если проверка прошла успешно, то объект адресата заполняется данными, которые ввёл пользователь. Эти изменения будут напрямую применяться к объекту адресата, который был передан в качестве аргумента метода `setPerson(...)`! 
* Логическая переменная `okClicked` служит для определения того, какую из двух кнопок, `ОК` или `Cancel` нажал пользователь.


### Привязка класса-контроллера к fxml-файлу

Теперь мы должны связать наш класс-контроллер с fxml-файлом. Для этого выполните следующие действия:

1. Откройте файл `PersonEditDialog.fxml` в Scene Builder;
2. С левой стороны во вкладке *Controller* установите наш класс `PersonEditDialogController` в качестве значения параметра *Сontroller Сlass*;
3. Установите соответствующие значения **fx:id** для всех компонентов `TextField`;
4. В значениях параметров **onAction** для наших кнопок укажите соответствующие методы-обработчики.



### Вызов диалога редактирования

Добавьте в класс `MainApp` метод для загрузки и отображения диалога редактирования записей:


##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * Открывает диалоговое окно для изменения деталей указанного адресата.
 * Если пользователь кликнул OK, то изменения сохраняются в предоставленном
 * объекте адресата и возвращается значение true.
 * 
 * @param person - объект адресата, который надо изменить
 * @return true, если пользователь кликнул OK, в противном случае false.
 */
public boolean showPersonEditDialog(Person person) {
    try {
        // Загружаем fxml-файл и создаём новую сцену
        // для всплывающего диалогового окна.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Создаём диалоговое окно Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Передаём адресата в контроллер.
        PersonEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPerson(person);

        // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
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
 * Вызывается, когда пользователь кликает по кнопке New...
 * Открывает диалоговое окно с дополнительной информацией нового адресата.
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
 * Вызывается, когда пользователь кликает по кнопка Edit...
 * Открывает диалоговое окно для изменения выбранного адресата.
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
        // Ничего не выбрано.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }
}
</pre>

В приложении Scene Builder откройте представление `PersonOverview.fxml` и для кнопок `New...` и `Edit...` задайте соответствующие методы-обработчики в параметре *On Action* .


*****

## Готово!

Сейчас у вас должно быть работающее приложение адресной книги. Оно позволяет добавлять, изменять и удалять адресатов. Это приложение так же осуществляет проверку всего, что вводит пользователь.

Я надеюсь, что идея и структура данного приложения поможет вам начать писать свои собственные приложения JavaFX! Удачи.


### Что дальше?

В [4-й части учебника](/library/javafx-8-tutorial/ru/part4/ "Tutorial Part 4") мы будем подключать к нашему приложению CSS-стили.


##### Вам могут быть интересны также некоторые другие статьи

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
