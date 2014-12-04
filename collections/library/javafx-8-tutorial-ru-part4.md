---
layout: article
title: "Учебник по JavaFX 8 - Часть 4: Стилизация с помощью CSS"
date: 2014-04-25 00:00
updated: 2014-12-04 00:00
slug: javafx-8-tutorial/ru/part4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-ru-part4.md
image: /assets/library/javafx-8-tutorial/part4/addressapp-part4.png
published: true
prettify: true
comments: true
sidebars:
- header: "Статьи в этой серии"
  body:
  - text: "введение"
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
    active: true
  - text: "Часть 5: Storing Data as XML"
    link: /library/javafx-8-tutorial/ru/part5/
    paging: 5
  - text: "Часть 6: Statistics Chart"
    link: /library/javafx-8-tutorial/ru/part6/
    paging: 6
  - text: "Часть 7: Deployment"
    link: /library/javafx-8-tutorial/ru/part7/
    paging: 7
- header: скачать исходники"
  body:
  - text: Часть 4 как проект Eclipse <em>(Требуется хотя бы JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-4.zip
    icon-css: fa fa-fw fa-download
- header: языки
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-part4/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part4/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part4/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part4/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/part4/
    icon-css: fa fa-fw fa-globe
    active: true
---

![Screenshot AddressApp Part 4](/assets/library/javafx-8-tutorial/part4/addressapp-part4.png "AddressApp Part4")


## Часть 4: Содержание

- Стилизация с помощью каскадных таблиц стилей (CSS)
- Добавление иконки приложения



*****


## Стилизация с помощью CSS

В JavaFX вы можете стилизировать свой интерфейс пользователя с помощью каскадных таблиц стилей (CSS). Это очень хорошо! Еще никогда не было так легко настроить внешний вид Java-приложения.

В этом учебнике мы создадим тему *DarkTheme*, вдохновленную Метро-дизайном из Windows 8. Используемые стили для кнопок базируются на статье [JMetro - Windows 8 Metro controls on Java](http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/), написанной Pedro Duque Vieira.


### Знакомство с CSS

Если вы хотите стилизировать ваше JavaFX-приложение, вы должны иметь начальное представление о CSS в целом. Хорошее место для старта - этот [учебник CSS](http://www.csstutorial.net/).

Для получения специфической информации про использование CSS в JavaFX читайте это:

* [Skinning JavaFX Applications with CSS](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/css_tutorial.htm) - учебник от Oracle
* [JavaFX CSS Reference](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html) - официальный справочник


### Стили, используемые в JavaFX по умолчанию

Стиль, который используется в JavaFX по умолчанию хранится в файле `modena.css`. Этот css-файл можно найти в файле `jfxrt.jar`, расположенном в вашей Java-директории `/jdk1.8.x/jre/lib/ext/jfxrt.jar`.

Разархивируйте его и вы найдете `modena.css` в папке `com/sun/javafx/scene/control/skin/modena`.

Этот стиль всегда применяется для JavaFX-приложений по умолчанию. Добавляя пользовательские стили вы переопределяете стили из файла `modena.css`.

<div class="alert alert-info">
**Намек:** Для того, чтобы знать какие стили вам надо переопределить, просмотрите этот файл.
</div>


### Установка пользовательських стилей

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

Теперь нам надо присоединить эти стили к нашей сцене. Можно сделать это программно в коде Java, но мы для того, чтобы добавить стили в наши fxml-файлы будем использовать приложение Scene Builder:


#### Присоединяем таблицы стилей к файлу RootLayout.fxml

1. Откройте файл `RootLayout.fxml` в приложении Scene Builder.

2. Выберите корневой контейнер `BorderPane` во вкладке *Hierarchy*, перейдите на вкладку *Properties* и укажите файл `DarkTheme.css` в роли таблиц стилей.  
![DarkTheme for RootLayout](/assets/library/javafx-8-tutorial/part4/darktheme-rootlayout.png "DarkTheme RootLayout")


#### Присоединяем таблицы стилей к файлу PersonEditDialog.fxml

1. Откройте файл `PersonEditDialog.fxml` в приложении Scene Builder. Выберите корневой контейнер `AnchorPane` во вкладке *Hierarchy*, перейдите на вкладку *Properties* и укажите файл `DarkTheme.css` в роли таблиц стилей.

2. Фон остался белым, поэтому укажите для корневого компонента `AnchorPane` в классе стиля значение `background`.
![Add Style Class](/assets/library/javafx-8-tutorial/part4/darktheme-personeditdialog.png "DarkTheme Person Edit Dialog")

3. Выберите кнопку *OK* и отметьте свойство *Default Button* в вкладке *Properties*. В результате изменется цвет кнопки и наша кнопка будет использоваться по умолчанию когда пользователь находясь в окне будет нажимать клавишу enter.


#### Присоединяем таблицы стилей к файлу PersonOverview.fxml

1. Откройте файл `PersonOverview.fxml` в приложении Scene Builder. Выберите корневой контейнер `AnchorPane` во вкладке *Hierarchy*, перейдите на вкладку *Properties* и укажите файл `DarkTheme.css` в роли таблиц стилей.

2. Вы сражу должны увидеть некоторые изменения: цвет таблицы и кнопок стал черным. Все классы стилей `.table-view` и `.button` из файла `modena.css` применились к таблице и кнопкам. С того момента, как мы переопределили некоторые из стилей в нашем css-файле, новые стили применяются автоматически.

3. Возможно, вам потребуется изменить размер кнопок для того, чтобы отображался весь текст.

4. Выберите правый компонент `AnchorPane` внутри компонента `SplitPane`.  
![Background Style Select](/assets/library/javafx-8-tutorial/part4/background-style-select.png)

5. Перейдите на вкладку *Properties* и укажине в классе стиля значение `background`. Теперь фон станет черного цвета.  
![Background Style](/assets/library/javafx-8-tutorial/part4/background-style.png)


#### Текстовые метки с другими стилями

Теперь все наши текстовые метки с правой стороны имеют одинаковый размер. Для дальнейшей стилизации тектсовых меток мы будем использовать уже определенные стили `.label-header` и `label-bright`.

1. Выберите метку *Person Details* и добавьте в качестве класса стиля значение `label-header`.  
![Label Header Style](/assets/library/javafx-8-tutorial/part4/label-header-style.png)

2. Для каждой метки в правой колонке (где отображаются актуальные данные наших записей) добавьте в качестве класса стиля значение `label-bright`.  
![Label Bright Style](/assets/library/javafx-8-tutorial/part4/label-bright-style.png "Label Bright Style")


*****


## Добавляем иконку приложения

На данный момент в нашем приложении в панели названия и панели задач используется иконка по умолчанию:

![Default Icon](/assets/library/javafx-8-tutorial/part4/default-app-icon.png "Default App Icon")

С пользовательской иконкой наше приложение будет выглядеть красивее:

![Custom Icon](/assets/library/javafx-8-tutorial/part4/custom-app-icon.png)


### Файл изображения

Одно из возможных мест, где можно свободно скачать иконки - это [Icon Finder](http://www.iconfinder.com/ "Icon Finder"). Я загрузил малельнькую иконку [адресной книги](http://www.iconfinder.com/icondetails/86957/32/).

Создайте папку **resources** внутри вашего проекта AddressApp, а в ней папку **images**. Поместите выбранную вами иконку в папку изображений. Ваша структура папок должна иметь такой вид:

![Custom Icon File](/assets/library/javafx-8-tutorial/part4/custom-icon-file.png "Custom Icon File")


### Установка иконки в приложение

Для установки выбранной иконки в наше приложение добавьте следующий код в метод `start(...)` в классе `MainApp.java`


##### MainApp.java

<pre class="prettyprint lang-java">
this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
</pre>

The whole `start(...)` method should look something like this now:

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

Вы также можете добавить иконку в окно редактирования адресной записи.


### Что дальше?

В [5 Части Учебника](/library/javafx-8-tutorial/ru/part5/ "JavaFX Tutorial Part 5") мы добавим XML-хранилище для наших данных.


##### Вам могут быть интересны также некоторые другие статьи

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
