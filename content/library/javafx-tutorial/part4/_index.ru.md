+++
title = "Часть 4: Стилизация с помощью CSS"
date = 2014-04-25
updated = 2016-04-21
description = "В JavaFX с помощью CSS можно изменять внешний вид приложений. В этой части мы так же научимся добавлять к нашему приложению иконку"
image = "addressapp-part4.png"
prettify = true
comments = true 
commentsIdentifier = "/library/javafx-8-tutorial/ru/part4/"
aliases = [ 
  "/library/javafx-8-tutorial/ru/part4/"
]
weight = 4

[[sidebars]]
header = "Скачать исходники"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Часть 4 как проект Eclipse <em>(Требуется хотя бы JDK 8u40)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-4.zip"
+++

![Screenshot AddressApp Part 4](addressapp-part4.png "AddressApp Part4")


## Часть 4: Содержание

- Стилизация с помощью каскадных таблиц стилей (CSS)
- Добавление иконки приложения



*****


## Стилизация с помощью CSS

В JavaFX с помощью каскадных таблиц стилей (CSS) можно стилизировать интерфейс пользователя. Это просто здорово! Ещё никогда не было так легко настроить внешний вид приложения Java.

В этом учебнике мы создадим тему *DarkTheme*, вдохновленную Метро-дизайном из Windows 8. Принятые стили для кнопок заимствованы из статьи [JMetro - Windows 8 Metro controls on Java](http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/), написанной Pedro Duque Vieira.


### Знакомство с CSS

Если вы хотите приукрасить внешний вид своего приложение JavaFX, то надо иметь хотя бы начальное представление о том, что такое CSS. Хорошее место для старта - этот [учебник по CSS](http://www.csstutorial.net/).

Для получения специфической информации об использовании CSS в JavaFX доступны следующие ресурсы:

* [Skinning JavaFX Applications with CSS](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/css_tutorial.htm) - учебник от Oracle
* [JavaFX CSS Reference](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html) - официальный справочник


### Стиль, используемый в JavaFX по умолчанию

Стиль, который используется в JavaFX по умолчанию хранится в файле **`modena.css`**. Его можно найти в файле `jfxrt.jar`, который, в свою очередь, располагается в директории Java `/jdk1.8.x/jre/lib/ext/jfxrt.jar`.

Разархивируйте его и вы найдете `modena.css` в папке `com/sun/javafx/scene/control/skin/modena`.

Этот стиль всегда применяется по умолчанию для всех приложений JavaFX. Добавляя пользовательские стили мы переопределяем стили из файла `modena.css`.

<div class="alert alert-info">
<strong>Подсказка:</strong> Для того, чтобы знать какие стили следует переопределить, просмотрите этот файл.
</div>


### Подключение пользовательских CSS-стилей

Добавьте файл `DarkTheme.css` в пакет `view`.


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
 * Push Button в стиле Metro 
 * Автор: Pedro Duque Vieira
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

Теперь надо подключить эти стили к нашей сцене. Мы можем сделать это программно, в коде Java, но в этом уроке, чтобы добавить стили в наши fxml-файлы, мы будем использовать Scene Builder:


#### Подключаем таблицы стилей к файлу RootLayout.fxml

1. В приложении Scene Builder откройте файл `RootLayout.fxml`.

2. Во вкладке *Hierarchy* выберите корневой контейнер `BorderPane`, перейдите на вкладку *Properties* и укажите файл `DarkTheme.css` в роли таблиц стилей.   
![DarkTheme for RootLayout](darktheme-rootlayout.png "DarkTheme RootLayout")


#### Подключаем таблицы стилей к файлу PersonEditDialog.fxml

1. В приложении Scene Builder откройте файл `PersonEditDialog.fxml`. Во вкладке *Hierarchy* выберите корневой контейнер `AnchorPane`, перейдите на вкладку *Properties* и укажите файл `DarkTheme.css` в роли таблиц стилей.

2. Фон всё ещё белый, поэтому укажите для корневого компонента `AnchorPane` в классе стиля значение `background`.   
![Add Style Class](darktheme-personeditdialog.png "DarkTheme Person Edit Dialog")

3. Выберите кнопку *OK* и отметьте свойство *Default Button* в вкладке *Properties*. В результате изменится цвет кнопки и она будет использоваться по умолчанию когда пользователь, находясь в окне, будет нажимать клавишу Enter.


#### Подключаем таблицы стилей к файлу PersonOverview.fxml

1. В приложении Scene Builder откройте файл `PersonOverview.fxml`. Во вкладке *Hierarchy* выберите корневой контейнер `AnchorPane`, перейдите на вкладку *Properties* и укажите файл `DarkTheme.css` в роли таблиц стилей.

2. Вы сражу должны увидеть некоторые изменения: цвет таблицы и кнопок поменялся на чёрный. Все классы стилей `.table-view` и `.button` из файла `modena.css` были применены к таблице и кнопкам. С того момента, как мы переопределили некоторые из стилей в нашем css-файле, новые стили применяются автоматически.

3. Возможно, вам потребуется изменить размер кнопок для того, чтобы отображался весь текст.

4. Выберите правый компонент `AnchorPane` внутри компонента `SplitPane`.   
![Background Style Select](background-style-select.png)

5. Перейдите на вкладку *Properties* и укажите в классе стиля значение `background`. Теперь фон станет чёрного цвета.   
![Background Style](background-style.png)


#### Текстовые метки с другими стилями

Сейчас все текстовые метки с правой стороны имеют одинаковый размер. Для дальнейшей стилизации текстовых меток мы будем использовать уже определённые стили `.label-header` и `label-bright`.

1. Выберите метку *Person Details* и добавьте в качестве класса стиля значение `label-header`.   
![Label Header Style](label-header-style.png)

2. Для каждой метки в правой колонке (где отображаются фактические данные об адресатах) добавьте в качестве класса стиля значение `label-bright`.   
![Label Bright Style](label-bright-style.png "Label Bright Style")


*****


## Добавляем иконку приложения

На данный момент в нашем приложении в панели названия и панели задач используется иконка по умолчанию:

![Default Icon](default-app-icon.png "Default App Icon")

С пользовательской иконкой наше приложение будет выглядеть симпатичнее:

![Custom Icon](custom-app-icon.png)


### Файл иконки

Одно из возможных мест, где можно свободно скачать иконки — это сайт [Icon Finder](http://www.iconfinder.com/ "Icon Finder"). Я скачал маленькую иконку [адресной книги](https://www.iconfinder.com/icons/86957/address_book_icon#size=32).

Создайте внутри вашего проекта AddressApp обычную папку **resources**, а в ней папку **images**. Поместите выбранную вами иконку в папку изображений. Структура папок должна иметь такой вид:

![Custom Icon File](custom-icon-file.png "Custom Icon File")


### Установка иконки для сцены

Для того, чтобы для нашей сцены установить новую иконку, в классе `MainApp.java` добавьте следующий код в метод `start(...)`


##### MainApp.java

<pre class="prettyprint lang-java">
this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
</pre>

Весь метод `start(...)` теперь будет выглядеть так:

<pre class="prettyprint lang-java">
public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("AddressApp");

    // Устанавливаем иконку приложения.
    this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

    initRootLayout();

    showPersonOverview();
}
</pre>

Вы также можете добавить иконку в окно редактирования адресатов.


### Что дальше?

В [5-й части учебника](/ru/library/javafx-tutorial/part5/ "JavaFX Tutorial Part 5") мы добавим для наших данных XML-хранилище.


##### Вам могут быть интересны также некоторые другие статьи

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
