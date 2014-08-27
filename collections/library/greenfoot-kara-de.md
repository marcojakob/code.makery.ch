---
layout: article
title: "GreenfootKara - Einleitung"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara/de
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-de.md
published: true
prettify: false
comments: true
sidebars:
- header: Artikel in dieser Serie
  body:
  - text: "Einleitung"
    link: /library/greenfoot-kara/de/
    paging: Intro
    active: true
  - text: "Hinweise für Lehrpersonen"
    link: /library/greenfoot-kara/de/teachers-info/
    icon-css: fa fa-fw fa-info
  - text: "Kapitel 1: Erste Schritte"
    link: /library/greenfoot-kara/de/chapter1/
    paging: 1
  - text: "Kapitel 2: Programmfluss"
    link: /library/greenfoot-kara/de/chapter2/
    paging: 2
  - text: "Kapitel 3: Variablen"
    link: /library/greenfoot-kara/de/chapter3/
    paging: 3
  - text: "Kapitel 4: Sokoban Spiel"
    link: /library/greenfoot-kara/de/chapter4/
    paging: 4
  - text: "Kapitel 5: Methoden"
    link: /library/greenfoot-kara/de/chapter5/
    paging: 5
- header: Downloads
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
- header: Sprachen
  body:
  - text: English
    link: /library/greenfoot-kara/
    icon-css: fa fa-fw fa-globe
  - text: Deutsch
    link: /library/greenfoot-kara/de/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Français
    link: /library/greenfoot-kara/fr/
    icon-css: fa fa-fw fa-globe
---

> `GreenfootKara` ist eine visuelle und interaktive Einführung in die Programmierung. Es basiert auf der [Greenfoot IDE](http://www.greenfoot.org) und kombiniert das bewährte Konzept von [Kara](http://www.swisseduc.ch/informatik/karatojava/) mit der Flexibilität und Bedienerfreundlichkeit von Greenfoot.

*Hinweis: Wenn Sie anstatt mit Greenfoot lieber mit einer IDE wie **Eclipse oder NetBeans** arbeiten möchten, lesen Sie die Einführung zu [GameGridKara](/library/gamegrid-kara/de/).*


## Greenfoot

[Greenfoot](http://www.greenfoot.org) ist eine einfache Entwicklungsumgebung für Java. Sie ermöglicht eine sehr schnelle Entwicklung von interaktiven, grafischen Projekten.

![GreenfootKara](/assets/library/greenfoot-kara/greenfootkara-screenshot.png)

Wenn Sie eine Lehrperson sind, können Sie für Greenfoot Szenarien beliebiger Schwierigkeitsstufen erstellen, welche von den Lernenden weiterbearbeitet werden können. `GreenfootKara` beinhaltet eine ganze Reihe solcher Szenarien mit dem Kara-Käfer.

Programmieranfänger können durch Greenfoot von Schwierigkeiten beim Einstieg in die Programmierung abgeschirmt werden (z.B. `public static void main(String [])` und co.). Trotzdem sind in Greenfoot nach oben alle Möglichkeiten offen. So können Fortgeschrittene damit auch sehr komplexe Projekte realisieren.


## Kara

Kara ist ein Käfer, der in einer einfachen Welt aus Bäumen, Blättern und Pilzen lebt.

![Kara](/assets/library/greenfoot-kara/kara.png) ![Mushroom](/assets/library/greenfoot-kara/mushroom.png) ![Leaf](/assets/library/greenfoot-kara/leaf.png) ![Tree](/assets/library/greenfoot-kara/tree.png)

Die Möglichkeiten von Kara sind wie folgt:


### Karas Aktionen

* Kara macht einen Schritt vorwärts mit `move()` (Kara kann Pilze schieben, auf Kleeblätter treten aber er kann nicht durch Bäume hindurch gehen)
* Kara dreht sich um 90° nach mit `turnLeft()` oder `turnRight()`
* Kara legt ein Kleeblatt mit `putLeaf()`
* Kara entfernt ein Kleeblatt mit `removeLeaf()`


### Karas Sensoren

* Kara schaut nach, ob er sich auf einem Kleeblatt befindet mit `onLeaf()`
* Kara schaut nach, ob er sich vor oder neben einem Baum befindet mit `treeFront()`, `treeLeft()` oder `treeRight()`
* Kara schaut nach, ob er einen Pilz vor sich hat mit `mushroomFront()`


***

## Los geht's

Laden Sie [Greenfoot](http://www.greenfoot.org/download) herunter und installieren Sie es. Nach der Installation sollte es möglich sein die vorgefertigten Kara Szenarien zu öffnen (siehe Downloads auf den nachfolgenden Seiten).

Um gleich loszulegen, gehen Sie zu [Kapitel 1: Erste Schritte](/library/greenfoot-kara/de/chapter1/).

Um mehr Hintergrundinformationen zu erhalten über `GreenfootKara` lesen Sie die [Hinweise für Lehrpersonen](/library/greenfoot-kara/de/teachers-info/).

