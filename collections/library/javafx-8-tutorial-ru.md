---
layout: article
title: "Учебник по JavaFX 8"
date: 2014-04-19 00:00
updated: 2014-12-04 00:00
slug: javafx-8-tutorial/ru
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-ru.md
description: "This seven-part tutorial walks through designing, programming and deploying an address application with JavaFX."
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: true
sidebars:
- header: "Статьи в этой серии"
  body:
  - text: "введение"
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
  - text: "Part 4: CSS Styling"
    link: /library/javafx-8-tutorial/ru/part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /library/javafx-8-tutorial/ru/part5/
    paging: 5
  - text: "Part 6: Statistics Chart"
    link: /library/javafx-8-tutorial/ru/part6/
    paging: 6
  - text: "Part 7: Deployment"
    link: /library/javafx-8-tutorial/ru/part7/
    paging: 7
- header: языки
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-intro/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/
    icon-css: fa fa-fw fa-globe
    active: true
---

В 2012 году я написал очень детальный [учебник по JavaFX 2](http://code.makery.ch/java/javafx-2-tutorial-intro/) для моих студентов. Многие люди с разных частей мира прочитали его и дали очень позитивные отзывы о данном материале. Поэтому я решил переписать этот учебник для новой версии JavaFX 8 (об изменениях вы можете почитать здесь - [Обновление до JavaFX 8 - Что Нового](http://code.makery.ch/blog/update-to-javafx-8-whats-new/ "Update to JavaFX 8 - What's New")).

В этом учебнике я расскажу о проектировании, программировании и развертывании приложения с функциональностью адресной книги. Так будет выглядеть наше приложение в конце разработки:

![Screenshot AddressApp](http://code.makery.ch/assets/library/javafx-8-tutorial/addressapp.png "AdressApp")


## Вам предстоит научиться

- Создавать и запускать JavaFX-проект;
- Использовать приложение Scene Builder для проектирования пользовательского интерфейса;
- Структурировать приложение с помощью патерна MVC;
- Использовать коллекцию `ObservableList` для автоматического обновления пользовательского интерфейса;
- Использовать компонент `TableView` и реагировать на выделение ячеек в таблице;
- Создавать пользовательские всплывающие диалоги для редактирования записей в приложении;
- Выполнять проверку пользовательского ввода;
- Изменять дизайн JavaFX-приложения с помощью каскадных таблиц стилей (CSS);
- Хранить данные приложения в виде XML-файла;
- Сохранять последний открытый путь к файлу в пользовательских настройках;
- Создавать JavaFX-диаграммы для отображения статистики;
- Развертывать JavaFX-приложение с помощью процесса нативной упаковки (native package).

**Это довольно много!** А это значит, что после изучения данного материала вы будете знать, как создавать сложные приложения с помощью JavaFX.


## Как пользоваться этим учебником

Есть два варианта использования этого материала:

- **учите много**: Создавайте свой JavaFX проект с нуля и постепенно наполняйте его классы и методы кодом.
- **учите быстро**: Импортируйте исходники кода для каждой части учебника в вашу среду разработки а потом пытайтесь понять данный материал.

Я надеюсь, что вы получите удовольствие! Начнем с [Часть 1: Приложение Scene Builder](/library/javafx-8-tutorial/ru/part1/ "Part 1: Scene Builder.").

<div class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> Russian translations have been contributed by 
  <ul>
    <li><a href="https://github.com/sobolevstp" class="alert-link">Sobolev Stepan</a></li> 
  </ul>
  Thank you very much!
</div>

