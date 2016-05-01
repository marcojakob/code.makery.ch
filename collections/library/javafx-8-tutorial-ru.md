---
layout: article
title: "Учебник по JavaFX 8 (Русский)"
date: 2014-04-19 00:00
updated: 2016-04-20 00:00
description: "Этот учебник, состоящий из семи частей, введёт вас в проектирование, программирование и развёртывание приложения Адресной книги, с помощью JavaFX"
slug: javafx-8-tutorial/ru
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-ru.md
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: true
sidebars:
- header: "Статьи в этой серии"
  body:
  - text: "Введение"
    link: /library/javafx-8-tutorial/ru/
    paging: Intro
    active: true
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
  - text: "Часть 7: Развёртывание"
    link: /library/javafx-8-tutorial/ru/part7/
    paging: 7
languages: 
  header: Языки
  collection: library
  item: javafx-8-tutorial
  part: 
  active: ru
---

В 2012-м году я написал для своих студентов очень детальный [учебник по JavaFX 2](http://code.makery.ch/library/javafx-2-tutorial/). Его прочитали в разных частях света, и многие люди очень позитивно отозвались о данном материале. Поэтому я решил переписать учебник для новой версии JavaFX 8 (об изменениях вы можете почитать здесь - [Обновление до JavaFX 8 - Что Нового](http://code.makery.ch/blog/update-to-javafx-8-whats-new/ "Update to JavaFX 8 - What's New")).

В этом учебнике я расскажу о проектировании, программировании и развёртывании приложения с функциональностью адресной книги. Когда мы закончим разработку, наше приложение будет выглядеть так:

![Screenshot AddressApp](http://code.makery.ch/assets/library/javafx-8-tutorial/addressapp.png "AdressApp")


## Нам предстоит научиться

- Создавать и запускать проект JavaFX;
- Использовать приложение Scene Builder для проектирования пользовательского интерфейса;
- Структурировать приложение с помощью шаблона Модель-Представление-Контроллер (MVC);
- Использовать коллекцию `ObservableList` для автоматического обновления пользовательского интерфейса;
- Использовать компонент `TableView` и реагировать на выбор ячеек в таблице;
- Создавать пользовательские всплывающие диалоги для редактирования записей в приложении;
- Выполнять проверку пользовательского ввода;
- Изменять дизайн приложения JavaFX с помощью каскадных таблиц стилей (CSS);
- Хранить данные приложения в XML-файле;
- Сохранять в настройках пользователя путь к последнему открытому файлу;
- Создавать диаграммы JavaFX для отображения статистики;
- Развёртывать приложение JavaFX в виде нативного пакета (native package).

**Это довольно много!** А это значит, что после изучения данного материала мы будем готовы с помощью JavaFX создавать сложные приложения.


## Как пользоваться данным учебником

Есть два варианта использования этого учебника:

- **учите много**: Создавайте с нуля свой проект JavaFX и постепенно наполняйте кодом его классы и методы.
- **учите быстро**: Импортируйте в вашу среду разработки исходный код для каждой части учебника, а потом читайте учебник и старайтесь понять код. *Этот проект написан для среды разработки Eclipse, но с некоторыми модификациями (это не касается процесса развёртывания), его можно использовать в NetBeans, IntelliJ IDEA и других IDE*.

Надеюсь, что, процесс обучения вам понравится! Начнём с [Часть 1: Scene Builder](/library/javafx-8-tutorial/ru/part1/ "Part 1: Scene Builder.").

<div class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> Russian translations have been contributed by 
  <ul>
    <li><a href="https://github.com/sobolevstp" class="alert-link">Sobolev Stepan</a></li> 
    <li><a href="https://github.com/eugenedotru" class="alert-link">Evgen Ishchenko</a></li>
	<li><a href="https://github.com/leonisx" class="alert-link">Stavila Leonid</a></li>
  </ul>
  Thank you very much!
</div>

