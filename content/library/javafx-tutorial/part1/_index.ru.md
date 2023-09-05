+++
title = "Часть 1: Scene Builder"
date = 2014-04-19
updated = 2016-04-20
description = "Узнайте, как создать проекты JavaFX. Это первая часть учебника по проектированию, программированию и развёртыванию приложения Адресная книга с помощью JavaFX"
image = "addressapp-part1.png"
prettify = true
# comments = true 
commentsIdentifier = "/library/javafx-8-tutorial/ru/part1/"
aliases = [ 
  "/library/javafx-8-tutorial/ru/part1/"
]
weight = 1

[[sidebars]]
header = "Скачать исходники"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Часть 1 как проект Eclipse <em>(Требуется хотя бы JDK 8u20)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-1.zip"
+++

![Screenshot AddressApp Part 1](addressapp-part1.png "AddressApp")

### Часть 1: Содержание

* Знакомство с JavaFX;
* Создание и запуск проекта JavaFX;
* Использование приложения Scene Builder для проектирования пользовательского интерфейса;
* Простая структуризация приложения с использованием шаблона MVC.


***** 


### Предварительные требования

* Последняя [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html "Java JDK") (включающая в себя **JavaFX 8**);
* Среда разработки Eclipse версии 4.4 или выше с установленным плагином e(fx)lipse. Уже настроенную версию Eclipse можно скачать с сайта e(fx)lipse. Или использовать [сайт обновлений](http://www.eclipse.org/efxclipse/install.html "eclipse update site"), если Eclipse уже установлена.
* Приложение [Scene Builder](http://gluonhq.com/open-source/scene-builder/#download "Scene Builder") версии 8.0 или новее. Сейчас оно предоставляется Gluon, потому как [Oracle теперь распространяет его только в виде исходного кода](http://www.oracle.com/technetwork/java/javase/downloads/sb2download-2177776.html). *Предыдущая версия [Scene Builder 2.0](http://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-info-2157684.html "Scene Builder")*.


### Настройка среды разработки Eclipse

Нам нужно указать среде разработки Eclipse использовать JDK 8, а также задать путь к приложению Scene Builder:

1. Откройте настройки среды разработки Eclipse и перейдите к пункту *Java* | *Installed JREs*.

2. Нажмите *Add...*, выберите *Standart VM* и укажите путь к установленной JDK 8.

3. Уберите другие добавленные JDK и 8-я JDK будет использоваться по умолчанию.  
![Preferences JDK](preferences-jdk.png "Eclipse Preferences JDK")

4. Перейдите к пункту *Java* | *Compiler*. Установите значение настройки **Compiler compliance level** в **1.8**.  
![Preferences Compliance](preferences-compliance.png "Eclipse Preferences Compiler")

5. Перейдите к пункту *JavaFX* и укажите путь к **исполняемому файлу** приложения Scene Builder.  
![Preferences JavaFX](preferences-javafx.png "Eclipse Preferences JavaFX")


### Полезные ссылки

Возможно, вы захотите добавить закладки на следующие ссылки:

* [Java 8 API](http://docs.oracle.com/javase/8/docs/api/) - документация по стандартным классам Java;
* [JavaFX 8 API](http://docs.oracle.com/javase/8/javafx/api/) - документация по классам JavaFX;
* [ControlsFX API](https://controlsfx.bitbucket.io/) - документация по дополнительным элементам JavaFX [из проекта ControlsFX](http://fxexperience.com/controlsfx/);
* [Oracle's JavaFX Tutorials](http://docs.oracle.com/javase/8/javafx/get-started-tutorial/get_start_apps.htm) - официальный учебник по JavaFX от Oracle.

Ну что же, давайте приступим к изучению!


*****


## Создание нового проекта JavaFX

В приложение Eclipse (с уже установленным e(fx)clipse) в меню выберите пункт *File | New | Other...*, и затем выберите *JavaFX Project*.  
Укажите имя проекта (наше будет называться AddressApp) и нажмите *Finish*. 

Если Eclipse автоматически создало какие-то начальные файлы и пакеты, то удалите их.


### Создание структуры пакетов

С самого начала мы будем следовать хорошим принципам проектирования ПО. Один из них - это шаблон проектирования Модель-Представление-Контроллер (MVC). Опираясь на этот шаблон мы разобьём код нашего приложения на три части и создадим для каждой из них свой пакет (правый клик на папке src, *New... | Package*):

* `ch.makery.address` - содержит *большинство* классов-контроллеров (Controller) (= классов бизнес логики);
* `ch.makery.address.model` - содержит классы Моделей (Model);
* `ch.makery.address.view` - содержит Представления (View).

**Заметка:** Внутри пакета `view` также будут лежать некоторые классы-контроллеры, которые непосредственно связаны с конкретными представлениями. Давайте называть их контроллеры-представлений (**view-controllers**).


*****


## Создание файла разметки FXML

Есть два пути создания пользовательского интерфейса: либо использовать файл разметки FXML, либо программировать всё на Java. В большинстве своём мы будем использовать XML (.fxml). Я считаю, что этот способ больше подходит для отделения контроллеров от представлений. В дальнейшем мы сможем использовать Scene Builder для визуального редактирования наших XML-файлов. А это значит, что мы не будем напрямую работать с XML.

Кликните на пакете `view` правой кнопкой мышки и создайте новый документ FXML с названием `PersonOverview`.

![New FXML Document](new-fxml-document.png "New FXML Document")

![New PersonOverview](new-person-overview.png "New Person Overview FXML ")



*****


## Проектировка визуального интерфейса в Scene Builder

<div class="alert alert-warning">
<strong>Примечание:</strong> Если по какой-то причине ваш код не заработает, то скачайте исходники к этой части учебника и возьмите файл fxml оттуда.
</div>

Откройте только что созданный fxml-документ в приложении Scene Builder - клик правой кнопкой мышки по файлу `PersonOverview.fxml`, *Open with SceneBuilder*. На вкладке *Hierarchy* должен находиться единственный компонент *AnchorPane*.

(Если Scene Builder не запустился, то открываем пункт меню *Window | Preferences | JavaFX* и настраиваем верный путь к **исполняемому файлу установленного приложения** Scene Builder).

1. На вкладке *Hierarchy* выберите компонент *AnchorPane*, и справа, на вкладке *Layout* установите значение характеристикам *Pref Width* и *Pref Height* - 600 и 300 соответственно.  
![Anchor Pane Size](anchor-pane-size.png "Pref Width & Height")

2. На вкладке *Hierarchy* в компонент *AnchorPane* добавьте новый компонент *SplitPane (horizontal)*. Кликните по нему правой кнопкой мыши и выберите *Fit to Parent*.  
![Fit to Parent](fit-to-parent.png "Fit to Parent")

3. Теперь, в левую часть компонента *SplitPane* со вкладки *Controls* перетащите компонент *TableView*. Выделите его целиком (а не отдельный столбец) и проставьте отступы от краёв так, как показано на рисунке. Внутри компонента *AnchorPane* всегда можно проставить отступы от четырёх границ рамки ([дополнительная информация о разметках](http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm)).  
![TableView Anchors](table-view-anchors.png "Anchor Pane Constraints")

4. Чтобы увидеть, правильно ли отображается созданное окно, выполните пункт меню *Preview | Show Preview in Window*. Попробуйте поменять размер окна. Добавленная таблица должна изменятся вместе с окном, так как она прикреплена к границам окна.

5. В таблице измените заголовки колонок (вкладка *Properties* компонента *TableColumn*) на "First Name" и "Last Name".  
![Column Texts](column-texts.png "Table Column names")

6. Выберите компонент TableView и во вкладке *Properties* измените значение *Column Resize Policy* на *constrained-resize*. Выбор этой характеристики гарантирует, что колонки таблицы всегда будут занимать всё доступное пространство.  
![Column Resize Policy](column-resize-policy.png "TableView Column Resize Policy")

7. В правую часть компонента *SplitPane* перетащите компонент *Label* и измените его текст на "Person Details" (подсказка: используйте поиск для скорейшего нахождения компонентов). Используя привязки к границам (вкладка *Layout*) скорректируйте его положение.  
![Person Details Label](person-details-label.png "Person Details Label")

8. На правую панель *SplitPane* добавьте компонент *GridPane* и так же настройте привязки к границам, как показано на рисунке.  
![GridPane Layout](grid-pane-layout.png "Grid Pane Layout")

9. Приведите своё окно в соответствие с тем, что показано на рисунке, добавляя компоненты *Label* внутрь ячеек компонента *GridPane*.  
*Примечание: для того, чтобы добавить новый ряд в компонент GridPane, выберите существующий номер ряда (он окрасится жёлтым), кликните правой кнопкой мышки на номере ряда и выберите пункт "Add Row Above" или "Add Row Below"*.  
![Add labels](add-labels.png)

10. Внизу добавьте ButtonBar, а в него три кнопки Button. Теперь установите привязки к границам (правой и нижней), чтобы ButtonBar всегда находилась справа.
  
*Так как панель ButtonBar доступна только с JavaFX 8, и её поддержка в Scene Builder на данный момент несколько хромает, то имеется альтернативный способ*. Добавьте три компонента *Button* в правую часть так, как показано на предыдущем рисунке. Выделите их всех вместе (Shift + клик), кликните по ним правой кнопкой мышки и выберите пункт *Wrap In | HBox*. Это действие их сгруппирует. Вы можете задать расстояние (*Spacing*) между компонентами во вкладке *Properties* компонента *HBox*. Также установите привязки к границам (правой и нижней).  
![Button Group](button-group.png "Button Group")

11. Если всё сделано правильно, то у нас должно получится что-то похожее на рисунок ниже. Используйте пункт меню *Preview*, чтобы протестировать созданное окно и его реакцию на изменение размеров.  
![Preview](scene-builder-preview.png "Scene Bulider Preview")




*****


## Создание основного приложения

Нам необходимо создать ещё один файл fxml-разметки, в котором будет компонент полосы меню. Этот файл будет служить обёрткой для только что созданного `PersonOverview.fxml`.

1. В  пакете `view` создайте другой fxml-документ, и назовите его `RootLayout.fxml`. На этот раз в качестве корневого элемента выберите *BorderPane*.  
![New RootLayout](new-root-layout.png "New Root Layout")

2. Откройте файл `RootLayout.fxml` в приложении Scene Builder.

3. Установите предпочитаемое значение ширины и высоты компонента: 600 и 400 соответственно.  
![RootLayout Size](root-layout-size.png "Root Layout Size") 

4. В верхний слот компонента *BorderPane* добавьте компонент *MenuBar*. Функциональность меню мы будем реализовывать в последующих уроках.  
![MenuBar](menu-bar.png "Menu Bar")


### Основной класс приложения JavaFX

Теперь нам надо создать **основной класс Java**, который будет запускать наше приложение с `RootLayout.fxml` и добавлять в его центральную область `PersonOverview.fxml`.

1. Кликните правой кнопкой мыши по нашему проекту, перейдите к пункту *New | Other...* и выберите *JavaFX Main Class*.
![New JavaFX Main Class](new-main-class.png "New Main Class")

2. Назовите класс `MainApp` и поместите его в пакет `ch.makery.address` (примечание: это пакет является родительским для `view` и `model`).
![New JavaFX Main Class](new-main-class2.png "New Main Class 2")

Созданный класс `MainApp.java` расширяет класс `Application` и содержит два метода. Это базовая структура, которая необходима для запуска приложения JavaFX. Нам интересен метод `start(Stage primaryStage)`. Он автоматически вызывается при вызове метода `launch(...)` из метода `main`.

Как можно заметить, метод `start(...)` в качестве параметра принимает экземпляр класса `Stage`. На следующем рисунке представлена структура любого приложения JavaFX:  

![New FXML Document](javafx-hierarchy.png "JavaFX Hierarchy")
*Источник изображения: http://www.oracle.com/*

**Это как театральное представление** `Stage` (театральные подмостки) является основным контейнером, который, как правило, представляет собой обрамлённое окно со стандартными кнопками: закрыть, свернуть, развернуть. Внутрь `Stage` добавляется сцена`Scene`, которая может быть заменена другой `Scene`. Внутрь `Scene` добавляются стандартные компоненты типа `AnchorPane`, `TextBox` и другие.

Для получения более детальной информации о такой компоновке обратитесь к этому руководству: [Working with the JavaFX Scene Graph](http://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm "Working with the JavaFX Scene Graph").


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
     * Инициализирует корневой макет.
     */
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Показывает в корневом макете сведения об адресатах.
     */
    public void showPersonOverview() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает главную сцену.
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

Комментарии могут служить подсказками того, что и как делается.

Запустив приложение мы должны увидеть что-то похожее на то, что изображено на рисунке в начале этой статьи.


### Часто встречающиеся проблемы

Если JavaFX не может найти указанный `fxml`-файл, то вы получите следующее сообщение об ошибке:

`java.lang.IllegalStateException: Location is not set.`

Для решения этой проблемы внимательно проверьте правильность указания пути к файлам `fxml` и правильность написания его названия.

<div class="alert alert-warning">
Если и после этого не удастся запустить приложение, то скачайте исходники к этой части и запустить их.
</div>


*****

### Что дальше?

Во [2-й части учебника](/ru/library/javafx-tutorial/part2/ "Tutorial Part 2") мы добавим в наше приложение некоторые данные и функциональность.


##### Вам могут быть интересны также некоторые другие статьи на английском языке:

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
