---
layout: article
title: "Підручник з JavaFX 8 (Українська)"
date: 2014-04-19 00:00
updated: 2014-12-04 00:00
description: "Цей підручник, що складається з семи частин, введе вас у проектування, програмування та розгортання додатку Адресна книга за допомогою JavaFX"
slug: javafx-8-tutorial/uk
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-uk.md
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: true
sidebars:
- header: "Статті цієї серії"
  body:
  - text: "Вступ"
    link: /library/javafx-8-tutorial/uk/
    paging: Intro
    active: true
  - text: "Частина 1: Scene Builder"
    link: /library/javafx-8-tutorial/uk/part1/
    paging: 1
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
languages: 
  header: Мови
  collection: library
  item: javafx-8-tutorial
  part: 
  active: uk
---

В 2012 році я написав дуже детальний [підручник з JavaFX 2](http://code.makery.ch/library/javafx-2-tutorial/) для моїх студентів. Багато людей по всьому світу прочитали цей матеріал та дали дуже позитивні відгуки про нього. Тому я вирішив **переписати цей підручник для нової версії JavaFX 8** (про зміни ви можете прочитати тут - [Оновлення до JavaFX 8 - Що нового?](http://code.makery.ch/blog/update-to-javafx-8-whats-new/ "Update to JavaFX 8 - What's New")).

Цей підручник проведе вас крізь проектування, програмування та розгортання додатку з функціональністю адресної книги. Так буде виглядати наш додаток в кінці розробки:

![Screenshot AddressApp](http://code.makery.ch/assets/library/javafx-8-tutorial/addressapp.png "AdressApp")

## Ви навчитесь

- Створювати та запускати JavaFX проект;
- Використовувати додаток Scene Builder для проектування інтерфейсу користувача;
- Структурувати додаток за допомогою шаблону проектування Модель-Вигляд-Контролер (MVC);
- Використовувати колекцію `ObservableList` для автоматичного оновлення інтерфейсу користувача;
- Використовувати компонент `TableView` та реагувати на виділення комірок в таблиці;
- Створювати спливаючі діалоги користувача для редагування записів;
- Виконувати перевірку вводу користувача;
- Змінювати дизайн JavaFX додатку за допомогою каскадних таблиць стилів (CSS);
- Зберігати дані додатку як XML файл;
- Зберігати останній відкритий шлях до файлу в налаштуваннях користувача;
- Створювати JavaFX діаграми для відображення статистики;
- Розгортати JavaFX додаток з упаковки, що залежить від платформи (native package).

**Цього доволі багато!** Отже, після вивчення даного матеріалу ви будете готові до створення складних додатків за допомогою JavaFX.

## Як користуватися цим підручником

Є два шляхи використання матеріалу:

- **вчіть багато**: Створюйте свій JavaFX проект з нуля та поступово наповнюйте його класами.
- **вчіть швидко**: Імпортуйте вихідний код для кожної частини підручника у ваше середовище розробки (проект розрахований на IDE Eclipse, проте ви можете використовувати інші середовища розробки за невеликих змін, наприклад NetBeans), а потім намагайтесь розібрати даний матеріал.

Сподіваюсь, вам сподобається! Почнемо з [Частина 1: додаток Scene Builder](/library/javafx-8-tutorial/uk/part1/ "Part 1: Scene Builder.").

<div class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> Ukrainian translations have been contributed by 
  <ul>
    <li><a href="https://github.com/mrudnytskyi" class="alert-link">Myroslav Rudnytskyi</a></li> 
  </ul>
  Thank you very much!
</div>