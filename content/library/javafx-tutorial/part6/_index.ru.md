---
layout: article
title: "Учебник по JavaFX 8 - Часть 6: Статистическая диаграмма"
date: 2014-05-09
updated: 2016-04-26
slug: javafx-8-tutorial/ru/part6
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-ru-part6.md
description: "Учимся в JavaFX создавать столбцовую диаграмму."
image: /assets/library/javafx-8-tutorial/part6/addressapp-part6.png
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
  - text: "Часть 4: Стилизация с помощью CSS"
    link: /library/javafx-8-tutorial/ru/part4/
    paging: 4
  - text: "Часть 5: Хранение данных в XML"
    link: /library/javafx-8-tutorial/ru/part5/
    paging: 5
  - text: "Часть 6: Статистическая диаграмма"
    link: /library/javafx-8-tutorial/ru/part6/
    paging: 6
    active: true
  - text: "Часть 7: Развёртывание"
    link: /library/javafx-8-tutorial/ru/part7/
    paging: 7
- header: Скачать исходники
  body:
  - text: Часть 6 как проект Eclipse <em>(Требуется хотя бы JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-6.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Языки
  collection: library
  item: javafx-8-tutorial
  part: part6
  active: ru
---

![Screenshot AddressApp Part 6](/assets/library/javafx-8-tutorial/part6/addressapp-part6.png)


## Часть 6: Содержание

* Создание статистической диаграммы для показа распределения дней рождений по месяцам.


*****

## Статистика дней рождений

Каждый адресат в нашем приложении AddressApp имеет свой день рождения. Было бы неплохо видеть некоторую статистику о том, когда они празднуют свои дни рождения.

Мы будем использовать столбцовую диаграмму, один столбец будет символизировать один месяц. Каждый столбец будет показывать, сколько дней рождений приходится на его месяц.


## FXML-представление статистики

1. Начните с создания внутри пакета `ch.makery.address.view` файла `BirthdayStatistics.fxml` *(правый клик на пакете | New | other... | New FXML Document)*.  
![Birthday Statistics FXML](/assets/library/javafx-8-tutorial/part6/birthday-statistics-fxml.png)

2. Откройте файл `BirthdayStatistics.fxml` в приложении Scene Builder.

3. Выберите корневой компонент `AnchorPane`. Во вкладке *Layout* установите значение *Pref Width* в 620, а *Pref Height* в 450.

4. Добавьте на панель `AnchorPane` компонент `BarChart`.

5. Кликните правой кнопкой мышки на добавленном `BarChart` и выберите *Fit to Parent*.

6. Сохраните fxml-файл, перейдите в Eclipse и обновите проект.

Перед тем, как вернутся в приложение Scene Builder, давайте создадим контроллер и в классе `MainApp` свяжем всё между собой.


## Класс-контроллер статистики

В пакете `ch.makery.address.view` создайте класс `BirthdayStatisticsController.java`.

Перед тем, как я начну объяснять что к чему, взгляните на содержимое этого класса:


##### BirthdayStatisticsController.java

<pre class="prettyprint lang-java">
package ch.makery.address.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import ch.makery.address.model.Person;

/**
 * Контроллер для представления статистики дней рождений.
 * 
 * @author Marco Jakob
 */
public class BirthdayStatisticsController {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл был загружен.
     */
    @FXML
    private void initialize() {
        // Получаем массив с английскими именами месяцев.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Преобразуем его в список и добавляем в наш ObservableList месяцев.
        monthNames.addAll(Arrays.asList(months));

        // Назначаем имена месяцев категориями для горизонтальной оси.
        xAxis.setCategories(monthNames);
    }

    /**
     * Задаёт адресатов, о которых будет показана статистика.
     * 
     * @param persons
     */
    public void setPersonData(List<Person> persons) {
        // Считаем адресатов, имеющих дни рождения в указанном месяце.
        int[] monthCounter = new int[12];
        for (Person p : persons) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Создаём объект XYChart.Data для каждого месяца.
        // Добавляем его в серии.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }

        barChart.getData().add(series);
    }
}
</pre>


#### Как работает этот контроллер

1. Контроллеру нужен доступ к двум элементам из нашего fxml-файла:
    * `barChart` использует типы данных `String` и `Integer`. Тип данных `String` отображает название месяцев на оси X, а тип данных `Integer` - количество записей в конкретном месяце.
    * Ось `xAxis` мы используем для добавления названий месяцев.

2. Метод `initialize()` заполняет ось X строковыми значениями названий всех месяцев.

3. Метод `setPersonData(...)` будет доступен классу `MainApp` для передачи данных об адресатах. Он проходится по всем адресатам и подсчитывает количество дней рождений в каждом месяце. Потом он добавляет `XYChart.Data` для каждого месяца в серию данных `XYChart.Series`. Каждый объект `XYChart.Data` будет представлять один столбец диаграммы.


*****

## Соединяем представление и контроллер

1. В приложении Scene Builder откройте файл `BirthdayStatistics.fxml`.

2. Во вкладке *Controller* установите в качестве контроллера `BirthdayStatisticsController`.

3. Выберите компонент `BarChart` и в свойстве *fx:id* установите значение `barChart`.

4. Выберите `CategoryAxis` и в свойстве *fx:id* установите значение `xAxis`.
![Category Axis](/assets/library/javafx-8-tutorial/part6/category-axis.png)

5. Для будущей стилизации, во вкладке *Properties* вы можете добавить название своей диаграммы.



*****


## Соединяем представление/контроллер с классом MainApp

Для отображения статистики дней рождений мы будем использовать тот же механизм, что ранее использовали для отображения окна редактирования данных об адресатах.

Добавьте следующий метод в класс `MainApp`:


<pre class="prettyprint lang-java">
/**
 * Открывает диалоговое окно для вывода статистики дней рождений.
 */
public void showBirthdayStatistics() {
    try {
        // Загружает fxml-файл и создаёт новую сцену для всплывающего окна.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Birthday Statistics");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Передаёт адресатов в контроллер.
        BirthdayStatisticsController controller = loader.getController();
        controller.setPersonData(personData);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>

Всё готово. Правда, пока метод `showBirthdayStatistics()` нигде не вызывается. К счастью, в разметке `RootLayout.fxml` у нас есть меню, которое может быть использовано для этих целей.


### Отображаем меню статистики

В класс `RootLayoutController` добавьте метод, который будет обрабатывать нажатие пункта меню *Show Birthday Statistics*:

<pre class="prettyprint lang-java">
/**
 * Открывает статистику дней рождений.
 */
@FXML
private void handleShowBirthdayStatistics() {
  mainApp.showBirthdayStatistics();
}
</pre>

Теперь в приложении *Scene Builder* откройте файл `RootLayout.fxml` и создайте меню *Statistics* с единственным пунктом меню *Show Statistics*:

![Show Statistics Menu](/assets/library/javafx-8-tutorial/part6/show-statistics-menu.png)

Выберите пункт меню *Show Statistics* и в качестве значения свойства `On Action` установите метод `handleShowBirthdayStatistics`.

![Show Statistics On Action](/assets/library/javafx-8-tutorial/part6/show-statistics-on-action.png)

Перейдите в среду разработки *Eclipse*, обновите проект и протестируйте ваше приложение.


*****

## Больше информации о диаграммах в JavaFX

Хороший источник для получения дополнительной информации о диаграммах в JavaFX - официальный учебник от Oracle [Работа с диаграммами в JavaFX](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/charts.htm)


### Что дальше?

В последней, [7-й части учебника](/library/javafx-8-tutorial/ru/part7/) мы наконец развернём наше приложение (то есть, упакуем и доставим приложение нашим пользователям).


##### Вам могут быть интересны также некоторые другие статьи

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
