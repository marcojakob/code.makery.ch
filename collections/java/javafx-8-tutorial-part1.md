---
layout: article
title: "Учебник по JavaFX 8 - Часть 1: Scene Builder"
date: 2014-04-19 01:00
updated: 2014-08-27 00:00
slug: javafx-8-tutorial-part1
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/java/javafx-8-tutorial-part1.md
description: "This seven-part tutorial walks through designing, programming and deploying an address application with JavaFX."
image: /assets/library/javafx-8-tutorial/part1/addressapp-part1.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /java/javafx-8-tutorial-intro
    paging: Intro
  - text: "Part 1: Scene Builder"
    link: /java/javafx-8-tutorial-part1/
    paging: 1
    active: true
  - text: "Part 2: Model and TableView"
    link: /java/javafx-8-tutorial-part2/
    paging: 2
  - text: "Part 3: Interacting with the User"
    link: /java/javafx-8-tutorial-part3/
    paging: 3
  - text: "Part 4: CSS Styling"
    link: /java/javafx-8-tutorial-part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /java/javafx-8-tutorial-part5/
    paging: 5
  - text: "Part 6: Statistics Chart"
    link: /java/javafx-8-tutorial-part6/
    paging: 6
  - text: "Part 7: Deployment"
    link: /java/javafx-8-tutorial-part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: Part 1 as Eclipse Project <em>(requires at least JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-1.zip
    icon-css: fa fa-fw fa-download
- header: Languages
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-part1/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Português
    link: /library/javafx-8-tutorial/pt/part1/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part1/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part1/
    icon-css: fa fa-fw fa-globe
---

![Screenshot AddressApp Part 1](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/addressapp-part1.png "AdressApp")

### Часть 1: Содержание

* Знакомство с JavaFX;
* Создание и запуск JavaFX-проекта;
* Использование приложения Scene Builder для проектирования пользовательского интерфейса;
* Простая структуризация приложения с использованием патерна MVC.


***** 


### Предпосылки

* Последняя [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html "Java JDK") (включающая в себя **JavaFX 8**);
* Среда разработки Eclipse версии 4.3 или выше с установленным плагином f(ex)lipse. Сконфигурированную версию среды разработки Eclipse можно скачать с сайта e(fx)lipse. Или использовать [сайт обновлений](http://www.eclipse.org/efxclipse/install.html "eclipse update site"), если Eclipse уже установлена.
* Приложение [Scene Builder](http://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-info-2157684.html "Scene Builder") версии 2.0 или новее.


### Настройка среды разработки Eclipse

Нам нужно указать среде разработки Eclipse использовать JDK 8, а также указать путь к приложению Scene Builder:

1. Откройте настройки среды разработки Eclipse и перейдите к пункту *Java* | *Installed JREs*.

2. Нажмите *Add...*, выберите *Standart VM* и укажите путь к установленной JDK 8.

3. Уберите другие добавленные JDK и JDK 8 будет использоваться по умолчанию.  
![Preferences JDK](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/preferences-jdk.png "Eclipse Preferences JDK")

4. Перейдите к пункту *Java* | *Compiler*. Установите значение **Compiler compliance level** на **1.8**.  
![Preferences Compliance](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/preferences-compliance.png "Eclipse Preferences Compiler")

5. Перейдите к пункту *JavaFX* и укажите путь к исполняемому файлу приложения Scene Builder.  
![Preferences JavaFX](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/preferences-javafx.png "Eclipse Preferences JavaFX")


### Полезные ссылки

Возможно, вы захотите добавить закладки на следующие ссылки:

* [Java 8 API](http://docs.oracle.com/javase/8/docs/api/) - документация по стандартным классам Java;
* [JavaFX 8 API](http://docs.oracle.com/javase/8/javafx/api/) - документация по классам JavaFX;
* [ControlsFX API](http://controlsfx.bitbucket.org/) - документация для проекта ControlsFX;
* [Oracle's JavaFX Tutorials](http://docs.oracle.com/javase/8/javafx/get-started-tutorial/get_start_apps.htm) - официальный учебник по JavaFX от Oracle.

Ну что же, давайте приступим к изучению!


*****


## Создание нового JavaFX-проекта

Перейдите в приложение Eclipse и нажмите *File | New | Other...* и выберите *JavaFX Project*.
Укажите имя проекта (например, AdressApp) и нажмите *Finish*. 

Если приложение Eclipse автоматически создало начальные файлы и пакеты, то удалите их.


### Создание структуры пакетов

С самого начала мы будем следовать хорошим принципам проектирования ПО. Один из них - это патерн MVC. Опираясь на этот паттерн мы разбиваем код нашего приложения на три части и создаем для каждой свой пакет (правык клик на папке src, *New... | Package*):

* `ch.makery.adress` - содержит *большинство* классов-контроллеров (Controller) (= бизнес логики);
* `ch.makery.adress.model` - содержит классы Модели (Model);
* `ch.makery.adress.view` - содержит классы Вида (View).

**Заметка:** Внутри пакета Вид также содержатся некоторые классы-контроллеры, которые непосредственно связаны с одним видом. Давайте назовем их виды-контроллеры (**view-controllers**).


*****


## Создание файла разметки FXML

Есть два пути создания пользовательского интерфейса: использовать файл разметки FXML или программировать все на Java. Для большинства случаев мы будем использовать XML (.fxml). Я считаю, этот способ больше подходит для сохранения разделенности Контроллера и Вида друг от друга. В дальнейшем мы сможем использовать Scene Builder для визуального редактирования нашего XML. А это значит, что мы не будем работать с XML на прямую.

Кликните на пакет `view` правуй кнопкой мышки и создайте новый FXML-документ с названием `PersonOverview`.

![New FXML Document](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/new-fxml-document.png "New FXML Document")

![New PersonOverview](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/new-person-overview.png "New Person Overview FXML ")



*****


## Проектировка визуального интерфейса в приложении Scene Builder

<div class="alert alert-warning">
**Заметка:** Если по какой-то причине ваш код не работает, скачайте исходники к этой части учебника и попытайтесь открыть скачанный fxml-файл оттуда.
</div>

Откройте наш созданный fxml-документ в приложении Scene Builder. На вкладке *Hierarchy* у вас должен быть единственный компонент *AnchorPane*.

1. Выберите компонент *AnchorPane* на вкладке *Hierarchy*, перейдите на вкладку *Layout* и установите значение характеристикам *Pref Width* и *Pref Height* 600 и 300.  
![Anchor Pane Size](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/anchor-pane-size.png "Pref Width & Height")

2. Добавьте компонент *SplitPane (Horizontal Flow)* на вкладку *Hierarchy* на уже добавленный  компонент *AnchorPane*. Клацните по нем правой кнопкой миши и выберите *Fit to Parent*.  
![Fit to Parent](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/fit-to-parent.png "Fit to Parent")

3. Теперь добавьте компонент TableView (с вкладки *Library*)в левую часть только что добавленного компонента *SplitPane*. Выделите его и проставьте оступы от краев так, как показано на рисунке. Внутри компонента *AnchorPane* вы всегда можете проставить отступы для четырех сторон ([дополнительная информация о разметках](http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm)).  
![TableView Anchors](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/table-view-anchors.png "Anchor Pane Constraints")

4. Перейдите в меню *Preview | Show Preview in Window* для того, чтобы увидеть правильно ли отображается созданное окно. Попробуйте изменить размер окна. Добавленная таблица должна изменятся вместе с окном, т.к. она прикреплена к границам окна.

5. Измените заголовки колонок в таблице (на вкладке *Properties* компонента *TableColumn*) на "First Name" и "Last Name".  
![Column Texts](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/column-texts.png "Table Column names")

6. Выберите наш компонент TableView и измените значение *Column Resize Policy* (на вкладке *Properties*) на *'constrained-resize'*. Выбор этой характеристики гарантирует, что колонки таблицы всегда будут занимать все доступное пространство.  
![Column Resize Policy](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/column-resize-policy.png "TableView Column Resize Policy")

7. Добавьте компонент *Label* на правую часть компонента *SplitPane* и измените его текст на "Person Details" (подсказка: для нахождения компонентов вы можете использовать поиск). Скоректируйте его положение используя привязки к границам (на вкладке *Layout*).  
![Person Details Label](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/person-details-label.png "Person Details Label")

8. Добавьте компонент *GridPane* на правую и тоже настройте привязки к границам так, как показано на рисунке.  
![GridPane Layout](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/grid-pane-layout.png "Grid Pane Layout")

9. Приведите свое окно в соответствие с тем, что показано на рисунке, добавляя компоненты *Label* внутрь ячеек компонента *GridPane*. Для того, чтобы добавить новый ряд в компонент *GridPane*, кликните правой кнопкой мышки на номере рядка и выберите пункт "*Add Row*".  
![Add labels](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/add-labels.png)

10. Добавьте три компонента *Button* на правую часть так, как показано на предыдущем рисунке. Выделите их всех вместе, кликните по ним правой клавишой мышки и выберите пункт *Wrap In | HBox*. Это действие их сгруппирует. Вы можете задать расстояние между компонентами во вкладке *Properties* компонента *HBox*. Также установите привязки к границам (правой и нижней).  
![Button Group](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/button-group.png "Button Group")

11. Если вы все сделали правильно, то у вас должно получится что-то похожее на это. Для того, чтобы протестировать созданное окно используйте пункт меню *Preview*.  
![Preview](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/scene-builder-preview.png "Scene Bulider Preview")




*****


## Создание основного приложения

Нам необходимо создать еще один файл fxml-разметки, в котором будет компонент *Menu Bar* и который будет служить оберткой для только что созданного `PersonOverview.fxml`.

1. Создайте другой fxml-файл в том же пакете, что и предыдущий и назовите его `RootLayout.fxml`.  
![[New RootLayout](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/new-root-layout.png "New Root Layout")

2. Откройте файл `RootLayout.fxml` в приложении Scene Builder.

3. Установите предпочитаемое значение ширины и высоты компонента 600 и 400 соответственно.  
![RootLayout Size](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/root-layout-size.png "Root Layout Size") 

4. Добавьте компонент *MenuBar* в верхний слот компонента *BorderPane*. Функциональность меню мы будем реализовывать чуть позже.  
![MenuBar](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/menu-bar.png "Menu Bar")


### Основной класс JavaFX-приложения 

Теперь нам надо создать основной класс, который запускает наше приложение с `RootLayout.fxml` и добавляет `PersonOverview.fxml` в центр.

1. Кликните правой кнопкой мыши по вашему проекту и перейдите на *New | Other...* и выберите *JavaFX Main Class*.
![New JavaFX Main Class](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/new-main-class.png "New Main Class")

2. Назовите класс `MainApp` и поместите его в пакет `ch.makery.address` (заметка: это родительский пакет таких пакетов как `view` и `model`).
![New JavaFX Main Class](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/new-main-class2.png "New Main Class 2")


Созданный класс `MainApp.java` расширяет класс `Application` и содержит два метода. Это базовая структура для запуска JavaFX-приложения. Для нас важен метод `start(Stage primaryStage)`. Он автоматически вызывается при вызове метода `launch(...)` с метода `main`.

Как вы видите, метод `start(...)` принимает экземпляр класса `Stage` в роли параметра. На рисунке снизу представлена структура любого JavaFX-приложения:  

![New FXML Document](http://code.makery.ch/assets/library/javafx-8-tutorial/part1/javafx-hierarchy.png "JavaFX Hierarchy")
*Источник изображения: http://www.oracle.com/*

`Stage` является основным контейнером, который, как правило, представляет из себя окно с рамками и стандартными кнопками закрыть, уменьшить и увеличить. Внутрь `Stage` добавляется `Scene`, которая может быть заменена другой `Scene`. Внутрь `Scene` уже добавляются стандартные компоненты типа `AnchorPane`, `TextBox` и другие.

Для получения более детальной информации о вышерасказанном обратитесь к этому руководству: [Working with the JavaFX Scene Graph](http://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm "Working with the JavaFX Scene Graph").


*****

Откройте класс `MainApp.java` и замените его содержимое на это:

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

Комментарии могут служить вам в роли подсказок того, что и как делается.

Запустив приложение вы должны увидеть что-то похожее на то, что изображено на рисунке в начале этой статьи.


### Частые проблемы

Если приложение не сможет найти указанного `fxml`-файла, вы получите следующее предупреждение:

`java.lang.IllegalStateException: Location is not set.`

Для решения данной проблемы проверьте правильность указания пути к файлу и правильность написания его названия.

<div class="alert alert-warning">
Если после этого вам все же не удастся запустить приложение, попробуйте скачать исходники к этой части и запустить их.
</div>


*****

### Что дальше?

Во [2 Части Учебника](http://code.makery.ch/java/javafx-8-tutorial-part3/ "Tutorial Part 3") мы добавим в наше приложение некоторые данные и функциональность.


##### Вам могут быть интересны также некоторые другие статьи

* [JavaFX Dialogs](http://code.makery.ch/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](http://code.makery.ch/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](http://code.makery.ch/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](http://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](http://code.makery.ch/blog/javafx-8-tableview-cell-renderer/)
