---
layout: article
title: "Підручник з JavaFX 8 - Частина 1: Scene Builder"
date: 2014-04-19
updated: 2015-06-25
slug: javafx-8-tutorial/uk/part1
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-uk-part1.md
description: "Дізнайтесь, як створити проект JavaFX. Це перша частина підручника з проектування, програмування та розгортання додатку Адресна книга за допомогою JavaFX"
image: /assets/library/javafx-8-tutorial/part1/addressapp-part1.png
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
    active: true
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
  - text: "Частина 6: Статистична діаграма"
    link: /library/javafx-8-tutorial/uk/part6/
    paging: 6
  - text: "Частина 7: Розгортання"
    link: /library/javafx-8-tutorial/uk/part7/
    paging: 7
- header: Скачати вихідний код
  body:
  - text: Частина 1 як проект Eclipse <em>(Необхідно хоча б JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-1.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Мови
  collection: library
  item: javafx-8-tutorial
  part: part1
  active: uk
---

![Screenshot AddressApp Part 1](/assets/library/javafx-8-tutorial/part1/addressapp-part1.png "AdressApp")

### Частина 1: Зміст

* Знайомство з JavaFX;
* Створення та запуск JavaFX проекту;
* Використання додатку Scene Builder для проектування інтерфейсу користувача;
* Проста структуризація додатку з використанням шаблону проектування Модель-Вигляд-Контролер (MVC).

***** 

### Передумови

* Остання [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html "Java JDK") (включає в себе **JavaFX 8**);
* Середовище розробки Eclipse версії 4.4 чи вище з встановленим плагіном e(fx)lipse. Найлегший шлях - це скачати вже підготовлену версію Eclipse з [сайту e(fx)lipse](http://efxclipse.bestsolution.at/install.html#all-in-one). Як альтернативу, можна використати [сайт оновлень](http://www.eclipse.org/efxclipse/install.html "eclipse update site"), якщо Eclipse вже встановлено.
* Додаток [Scene Builder](http://gluonhq.com/products/scene-builder/ "Scene Builder") (забезпечує Gluon, оскільки [Oracle](http://www.oracle.com/technetwork/java/javase/downloads/sb2download-2177776.html) надає лише вихідний код).

### Налаштування середовища розробки Eclipse

Нам потрібно вказати середовищу розробки Eclipse використовувати JDK 8, а також вказати шлях до Scene Builder:

1. Відкрийте налаштування середовища розробки Eclipse та перейдіть до пункту *Java* | *Installed JREs*.

2. Натисніть *Add...*, виберіть *Standart VM* та вкажіть шлях до встановленої JDK 8.

3. Видаліть інші додані JDK або JRE і **JDK 8 буде використовуватись за замовчуванням**.  
![Preferences JDK](/assets/library/javafx-8-tutorial/part1/preferences-jdk.png "Eclipse Preferences JDK")

4. Перейдіть до пункту *Java* | *Compiler*. Встановіть значення **Compiler compliance level** як **1.8**.  
![Preferences Compliance](/assets/library/javafx-8-tutorial/part1/preferences-compliance.png "Eclipse Preferences Compiler")

5. Перейдіть до пункту *JavaFX* та вкажіть шлях до виконавчого файлу додатку Scene Builder.  
![Preferences JavaFX](/assets/library/javafx-8-tutorial/part1/preferences-javafx.png "Eclipse Preferences JavaFX")

### Корисні посилання

Можливо, ви захочете додати закладки на такі посилання:

* [Java 8 API](http://docs.oracle.com/javase/8/docs/api/) - документація стандартних класів Java;
* [JavaFX 8 API](http://docs.oracle.com/javase/8/javafx/api/) - документація класів JavaFX;
* [ControlsFX API](https://controlsfx.bitbucket.io/) - документація для проекту [ControlsFX](http://fxexperience.com/controlsfx/) для додаткових компонентів управління JavaFX;
* [Oracle's JavaFX Tutorials](http://docs.oracle.com/javase/8/javafx/get-started-tutorial/get_start_apps.htm) - офіційний підручник з JavaFX від Oracle.

Що ж, давайте почнемо!

*****

## Створення нового JavaFX проекту

Перейдіть в додаток Eclipse та натисніть *File | New | Other...* та виберіть *JavaFX Project*.
Вкажіть ім'я проекту (наприклад, AdressApp) та натисніть *Finish*. 

Якщо додаток Eclipse автоматично створив початкові файли та пакети, то видаліть їх.

### Створення структури пакетів

З самого початку ми будемо дотримуватись хороших принципів проектування програмного забезпечення. Один з них - це шаблон проектування [MVC](https://uk.wikipedia.org/wiki/Модель-вид-контролер). Опираючись на цей шаблон, ми розбиваємо код нашого додатку на три частини та створюємо для кожної власний пакет (правий клік на папці src, *New... | Package*):

* `ch.makery.adress` - вміщує *більшість* класів-контролерів (Controller) (= бізнес-логіки);
* `ch.makery.adress.model` - вміщує класи моделі (Model);
* `ch.makery.adress.view` - вміщує класи вигляду або представлення (View).

**Примітка:** Всередині пакету, що вміщує класи представлення, також містяться деякі класи-контролери, які безпосередньо зв'язані з одним виглядом. Давайте назвемо їх **вигляди-контролери** (**view-controllers**).

*****

## Створення файлу розмітки FXML

Існує два шляхи створення інтерфейсу користувача: використовувати файл розмітки FXML чи програмувати все на Java. Для більшості випадків ми будемо використовувати XML (.fxml). Я вважаю, цей спосіб більше підходить для збереження роздільності контролера та представлення один від іншого. В подальшому, ми зможемо використовувати графічний Scene Builder для візуального редагування нашого XML. А це означає, що нам не потрібно працювати з XML напряму.

Виконайте клік на пакеті `view` правою кнопкою мишки та створіть новий *FXML-документ* під назвою `PersonOverview`.

![New FXML Document](/assets/library/javafx-8-tutorial/part1/new-fxml-document.png "New FXML Document")

![New PersonOverview](/assets/library/javafx-8-tutorial/part1/new-person-overview.png "New Person Overview FXML ")

*****

## Проектування візуального інтерфейсу в додатку Scene Builder

<div class="alert alert-warning">
**Примітка:** Якщо з якоїсь причині ваш код не працює, скачайте вихідні коди до цієї частини підручника та спробуйте відкрити скачаний fxml-файл звідти.
</div>

Зробіть правий клік мишкою на `PersonOverview.fxml` та виберіть *Open with Scene Builder*. Тепер ви маєте побачити Scene Builder з єдиним компонентом *AnchorPane* (він видимий під вкладкою *Hierarchy* зліва).

(Якщо Scene Builder не відкривається, відкрийте *Window | Preferences | JavaFX* та встановіть правильний шлях до нього).

1. Виберіть компонент *AnchorPane* на вкладці *Hierarchy*, перейдіть на вкладку *Layout* (справа) та встановіть значення характеристикам *Pref Width* та *Pref Height* як 600 і 300.  
![Anchor Pane Size](/assets/library/javafx-8-tutorial/part1/anchor-pane-size.png "Pref Width & Height")

2. Додайте компонент *SplitPane (Horizontal Flow)* перетягуванням його з основної бібліотеки на вже доданий компонент *AnchorPane*. Клацніть на ньому правою кнопкою на вкладці *Hierarchy* (зліва) миші та виберіть *Fit to Parent*.  
![Fit to Parent](/assets/library/javafx-8-tutorial/part1/fit-to-parent.png "Fit to Parent")

3. Тепер додайте компонент *TableView* (з вкладки *Library*) в ліву частину щойно доданого компонента *SplitPane*. Виділіть його (не Column) та проставте відступи від країв так, як показано на рисунку. Всередині компонента *AnchorPane* ви завжди можете проставити відступи для чотирьох сторін ([додаткова інформація про розмітки](http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm)).  
![TableView Anchors](/assets/library/javafx-8-tutorial/part1/table-view-anchors.png "Anchor Pane Constraints")

4. Перейдіть в меню *Preview | Show Preview in Window* для того, щоб побачити, чи правильно відображається створене вікно. Спробуйте змінити розмір вікна. Додана таблиця повинна змінюватися разом з вікном, оскільки вона прикріплена до меж вікна.

5. Змініть заголовки колонок в таблиці (на вкладці *Properties* компонента *TableColumn*) на "First Name" та "Last Name".  
![Column Texts](/assets/library/javafx-8-tutorial/part1/column-texts.png "Table Column names")

6. Виберіть наш компонент TableView та змініть значення *Column Resize Policy* (на вкладці *Properties*) на *'constrained-resize'*. Вибір цієї характеристики гарантує, що колонки таблиці завжди будуть займати весь доступний простір.  
![Column Resize Policy](/assets/library/javafx-8-tutorial/part1/column-resize-policy.png "TableView Column Resize Policy")

7. Додайте компонент *Label* на праву частину компонента *SplitPane* та змініть його текст на "Person Details" (підказка: для пошуку компонентів ви можете використовувати пошук). Виконайте коректування його місцезнаходження, використовуючи прив'язки до меж (на вкладці *Layout*).  
![Person Details Label](/assets/library/javafx-8-tutorial/part1/person-details-label.png "Person Details Label")

8. Додайте компонент *GridPane* на праву частину та налаштуйте прив'язки до меж так, як показано на рисунку.  
![GridPane Layout](/assets/library/javafx-8-tutorial/part1/grid-pane-layout.png "Grid Pane Layout")

9. Приведіть своє вікно у відповідність з тим, що показано на рисунку, додаючи компоненти *Label* всередину комірок компонента *GridPane*. Для того, щоб додати новий ряд в компонент *GridPane*, виконайте клік правою кнопкою мишки на номері рядка (змінить колір на жовтий) та виберіть пункт "*Add Row*".  
![Add labels](/assets/library/javafx-8-tutorial/part1/add-labels.png)

10. Розмістіть компонент *ButtonBar* внизу та додайте три кнопки в *ButtonBar*. Тепер, встановіть прив'язки до меж (справа та знизу) так, щоб компонент залишався на своєму місці.  
![Button Group](/assets/library/javafx-8-tutorial/part1/button-group.png "Button Group")

11. Якщо ви все зробили вірно, то у вас мало вийти щось схоже на це. Для того, щоб протестувати створене вікно, використайте пункт меню *Preview*.  
![Preview](/assets/library/javafx-8-tutorial/part1/scene-builder-preview.png "Scene Bulider Preview")

*****

## Створення основного додатку

Нам необхідно створити ще один файл fxml розмітки, в якому буде компонент *Menu Bar* та який буде слугувати обгорткою для щойно створеного `PersonOverview.fxml`.

1. Створіть інший fxml файл в тому ж пакеті, що й попередній та назвіть його `RootLayout.fxml`.  
![New RootLayout](/assets/library/javafx-8-tutorial/part1/new-root-layout.png "New Root Layout")

2. Відкрийте файл `RootLayout.fxml` в додатку Scene Builder.

3. Встановіть бажане значення ширини та висоти компонента як 600 та 400, відповідно.  
![RootLayout Size](/assets/library/javafx-8-tutorial/part1/root-layout-size.png "Root Layout Size") 

4. Додайте компонент *MenuBar* у верхній слот компонента *BorderPane*. Функціональність меню ми будемо реалізовувати трохи пізніше.  
![MenuBar](/assets/library/javafx-8-tutorial/part1/menu-bar.png "Menu Bar")

### Основний клас JavaFX додатку 

Тепер нам потрібно створити *основний Java клас*, який запускає наш додаток з `RootLayout.fxml` та додає `PersonOverview.fxml` в центр.

1. Виконайте клік правою кнопкою миші по вашому проекту, перейдіть на *New | Other...* та виберіть *JavaFX Main Class*.

![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class.png "New Main Class")

2. Назвіть клас `MainApp` та помістіть його в пакет `ch.makery.address` (примітка: це батьківський пакет таких пакетів як `view` та `model`).

![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class2.png "New Main Class 2")

Створений клас `MainApp.java` наслідує клас `Application` та вміщує два методи. Це базова структура, що потрібна для для запуску JavaFX додатку. Для нас важливий метод `start(Stage primaryStage)`. Він автоматично викликається коли додаток запускається з методу `main`.

Як бачите, метод `start(...)` приймає екземпляр класу `Stage` в ролі параметра. На рисунку знизу представлена структура будь-якого JavaFX додатку:  

![New FXML Document](/assets/library/javafx-8-tutorial/part1/javafx-hierarchy.png "JavaFX Hierarchy")

*Джерело зображення: http://www.oracle.com/*

**Це наче театральна п'єса**: `Stage` являється основним контейнером, який, як правило, представляє вікно з рамками та стандартними кнопками закрити, мінімізувати та максимізувати. Всередину `Stage` додається `Scene`, яка, звичайно, може бути замінена іншою `Scene`. Всередину `Scene` вже додаються стандартні компоненти типу `AnchorPane`, `TextBox` та інші.

Для отримання більш детальної інформації про вищесказане, зверніться до [цього матеріалу](http://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm "Working with the JavaFX Scene Graph").

*****

Відкрийте клас `MainApp.java` та замініть його вміст на це:

<pre class="prettyprint lang-java">
package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
</pre>

Коментарії можуть слугувати вам в роли підказок того, що та як робиться.

Запустивши додаток, ви повинні побачити щось схоже на те, що зображено на рисунку на початку цієї статті.

### Часті проблеми

Якщо додаток не може знайти вказаного `fxml` файлу, ви отримаєте наступне повідомлення про помилку:

`java.lang.IllegalStateException: Location is not set.`

Для вирішення даної проблеми перевірте правильність вказування шляхів до файлу та правильність написання його назви.

<div class="alert alert-warning">
Якщо після цього вам все ж не вдається запустити додаток, спробуйте скачати вихідні коди до цієї частини та запустити їх.
</div>

*****

### Що далі?

В [2 частині підручника](/library/javafx-8-tutorial/uk/part2/ "Tutorial Part 2") ми додамо в наш додаток деякі дані та функціональність.

##### Вам можуть бути цікаві також деякі інші статті

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)