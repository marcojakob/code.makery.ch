+++
title = "Частина 4: Стилізація за допомогою CSS"
date = 2014-04-25
updated = 2015-06-25
description = "В JavaFX ви можете змінювати зовнішній вигляд додатку за допомогою CSS. В цій частині ми також навчимося додавати іконку нашого додатку"
image = "addressapp-part4.png"
prettify = true
comments = true 
commentsIdentifier = "/library/javafx-8-tutorial/uk/part4/"
aliases = [ 
  "/library/javafx-8-tutorial/uk/part4/"
]
weight = 4

[[sidebars]]
header = "Скачати вихідний код"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Частина 4 як проект Eclipse <em>(Необхідно хоча б JDK 8u40)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-4.zip"
+++

![Screenshot AddressApp Part 4](addressapp-part4.png "AddressApp Part4")

## Частина 4: Зміст

- Стилізація за допомогою каскадних таблиць стилів (CSS)
- Додавання іконки програми

*****

## Стилізація за допомогою CSS

У JavaFX ви можете стилізувати ваш інтерфейс користувача за допомогою каскадних таблиць стилів (CSS). Це дуже добре! Ще ніколи не було так легко налаштувати зовнішній вигляд Java додатку.

У цьому підручнику ми створимо тему *DarkTheme*, натхненну Метро-дизайном з Windows 8. Використовувані стилі для кнопок базуються на [статті](http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/ "JMetro - Windows 8 Metro controls on Java"), написаній Pedro Duque Vieira.

### Знайомство з CSS

Якщо ви хочете стилізувати ваш JavaFX додаток, ви повинні мати початкове уявлення про CSS в цілому. Гарне місце для старту - цей [підручник CSS](http://www.csstutorial.net/).

Для отримання специфічної інформації про використання CSS в JavaFX читайте це:

* [підручник від Oracle](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/css_tutorial.htm "Skinning JavaFX Applications with CSS")
* [офіційний довідник](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html "JavaFX CSS Reference")

### Стилі, що використовуються в JavaFX за замовчуванням

Стиль, який використовується в JavaFX за замовчуванням, зберігається у файлі `modena.css`. Цей css файл можна знайти у файлі `jfxrt.jar`, розташованому у вашій Java-директорії`/jdk1.8.x/jre/lib/ext/jfxrt.jar`.

Розпакуйте архів, і ви знайдете `modena.css` в папці `com/sun/javafx/scene/control/skin/modena`.

Цей стиль завжди застосовується для JavaFX додатків за замовчуванням. Додаючи стилі користувача, ви замінюєте оригінальні стилі з файлу `modena.css`.

<div class = "alert alert-info">
<strong>Підказка:</strong> Для того, щоб знати які стилі вам треба замінити, перегляньте цей файл.
</div>

### Установка стилів користувача

Додайте файл `DarkTheme.css` в пакет `view`.

##### DarkTheme.css

<pre class="prettyprint lang-css pre-scrollable">
.background {
    -fx-background-color: #1d1d1d;
}

.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 0.6;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.table-view {
    -fx-base: #1d1d1d;
    -fx-control-inner-background: #1d1d1d;
    -fx-background-color: #1d1d1d;
    -fx-table-cell-border-color: transparent;
    -fx-table-header-border-color: transparent;
    -fx-padding: 5;
}

.table-view .column-header-background {
    -fx-background-color: transparent;
}

.table-view .column-header, .table-view .filler {
    -fx-size: 35;
    -fx-border-width: 0 0 1 0;
    -fx-background-color: transparent;
    -fx-border-color: 
        transparent
        transparent
        derive(-fx-base, 80%) 
        transparent;
    -fx-border-insets: 0 10 1 0;
}

.table-view .column-header .label {
    -fx-font-size: 20pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-alignment: center-left;
    -fx-opacity: 1;
}

.table-view:focused .table-row-cell:filled:focused:selected {
    -fx-background-color: -fx-focus-color;
}

.split-pane:horizontal > .split-pane-divider {
    -fx-border-color: transparent #1d1d1d transparent #1d1d1d;
    -fx-background-color: transparent, derive(#1d1d1d,20%);
}

.split-pane {
    -fx-padding: 1 0 0 0;
}

.menu-bar {
    -fx-background-color: derive(#1d1d1d,20%);
}

.context-menu {
    -fx-background-color: derive(#1d1d1d,50%);
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 0.9;
}

.menu .left-container {
	-fx-background-color: black;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}

/* 
 * Metro style Push Button
 * Author: Pedro Duque Vieira
 * http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/
 */
.button {
    -fx-padding: 5 22 5 22;   
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #1d1d1d;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
  -fx-background-color: white;
  -fx-text-fill: #1d1d1d;
}

.button:focused {
    -fx-border-color: white, white;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #1d1d1d;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color,30%);
}
</pre>

Тепер нам треба приєднати ці стилі до нашої сцени. Це можна зробити програмно, в коді Java, але ми будемо використовувати додаток Scene Builder для того, щоб додати стилі в наші fxml файли:

#### Приєднуємо таблиці стилів до файлу RootLayout.fxml

1. Відкрийте файл `RootLayout.fxml` в додатку Scene Builder.

2. Виберіть кореневий контейнер `BorderPane` на вкладці *Hierarchy*, перейдіть на вкладку *Properties* і вкажіть файл `DarkTheme.css` в ролі таблиць стилів.   
![DarkTheme for RootLayout](darktheme-rootlayout.png "DarkTheme RootLayout")

#### Приєднуємо таблиці стилів до файлу PersonEditDialog.fxml

1. Відкрийте файл `PersonEditDialog.fxml` в додатку Scene Builder. Виберіть кореневий контейнер `AnchorPane` у вкладці *Hierarchy*, перейдіть на вкладку *Properties* і вкажіть файл `DarkTheme.css` в ролі таблиць стилів.

2. Фон залишився білим, тому вкажіть для кореневого компонента `AnchorPane` в класі стилю значення `background`.   
![Add Style Class](darktheme-personeditdialog.png "DarkTheme Person Edit Dialog")

3. Виберіть кнопку *OK* і встановіть властивість *Default Button* у вкладці *Properties*. В результаті зміниться її колір і кнопка буде використовуватися за замовчуванням, коли користувач, перебуваючи у вікні, натисне клавішу enter.

#### Приєднуємо таблиці стилів до файлу PersonOverview.fxml

1. Відкрийте файл `PersonOverview.fxml` в додатку Scene Builder. Виберіть кореневий контейнер `AnchorPane` у вкладці *Hierarchy*, перейдіть на вкладку *Properties* і вкажіть файл `DarkTheme.css` в ролі таблиць стилів.

2. Ви відразу повинні побачити деякі зміни: колір таблиці і кнопок став чорним. Всі класи стилів `.table-view` і `.button` з файлу `modena.css` застосувались до таблиці і кнопок. З того моменту, як ми перевизначили деякі з стилів в нашому css файлі, нові стилі застосовуються автоматично.

3. Можливо, вам буде потрібно змінити розмір кнопок для того, щоб відображався весь текст.

4. Виберіть правий компонент `AnchorPane` всередині компонента `SplitPane`.
![Background Style Select](background-style-select.png)

5. Перейдіть на вкладку *Properties* та вкажіть значення `background` як клас стилю. Тепер фон стане чорного кольору.  
![Background Style](background-style.png)

#### Текстові мітки з іншими стилями

Тепер всі текстові мітки з правого боку мають однаковий розмір. Для подальшої стилізації текстових міток, ми будемо використовувати вже визначені стилі `.label-header` та `label-bright`.

1. Виберіть мітку *Person Details* і додайте в якості класу стилю значення `label-header`.
![Label Header Style](label-header-style.png)

2. Для кожної мітки в правій колонці (де будуть відображатись дані наших записів) додайте як клас стилю значення `label-bright`.   
![Label Bright Style](label-bright-style.png "Label Bright Style")

*****

## Додаємо додатку іконку 

На даний момент в нашому додатку на панелі назв і панелі завдань використовується іконка за замовчуванням:

![Default Icon](default-app-icon.png "Default App Icon")

З іконкою користувача наш додаток буде виглядати красивіше:

![Custom Icon](custom-app-icon.png)

### Файл зображення

Одне з можливих місць, де можна вільно завантажити іконки - це [Icon Finder](http://www.iconfinder.com/ "Icon Finder"). Я завантажив маленьку іконку [адресної книги](https://www.iconfinder.com/icons/86957/address_book_icon#size=32).

Створіть папку **resources** всередині вашого проекту AddressApp, а в ній папку **images**. Помістіть обрану вами іконку в папку зображень. Ваша структура папок повинна мати такий вигляд:

![Custom Icon File](custom-icon-file.png "Custom Icon File")

### Встановлення іконки в додаток

Для установки обраної іконки в наш додаток, додайте наступний код в метод `start(...)` в класі `MainApp.java`:

##### MainApp.java

<pre class="prettyprint lang-java">
this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
</pre>

Весь метод `start(...)` має виглядати приблизно так, як зображено нижче:

<pre class="prettyprint lang-java">
public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("AddressApp");

    // Set the application icon.
    this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

    initRootLayout();

    showPersonOverview();
}
</pre>

Ви також можете додати іконку в вікно редагування адресного запису.

### Що далі?

В [5 частині підручника](/uk/library/javafx-tutorial/part5/ "JavaFX Tutorial Part 5") ми додамо XML сховище для наших даних.

##### Вам можуть бути цікаві також деякі інші статті

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)