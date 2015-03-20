---
layout: article
title: "Hello Dart - Lösungen Teil 1"
date: 2015-01-21 00:00
slug: hello-dart/de/part1-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-de-part1-solutions.md
description: "Lösungen zu Hello Dart Teil 1."
image: /assets/library/hello-dart/hello-dart.png
published: true
prettify: true
comments: true
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/hello-dart/de/
    paging: Einleitung
  - text: "<em>Hintergrundinfos</em>"
    link: /library/hello-dart/de/background/
    icon-css: fa fa-fw fa-info
  - text: "<em>Installation</em>"
    link: /library/hello-dart/de/install/
    icon-css: fa fa-fw fa-cog
  - text: "Teil 1: Erste Schritte"
    link: /library/hello-dart/de/part1/
    paging: 1
  - text: "Teil 2: Schleifen"
    link: /library/hello-dart/de/part2/
    paging: 2
  - text: "Teil 3: Bedingte Anweisungen"
    link: /library/hello-dart/de/part3/
    paging: 3
  - text: "Teil 4: Variablen"
    link: /library/hello-dart/de/part4/
    paging: 4
  - text: "Teil 5: Funktionen"
    link: /library/hello-dart/de/part5/
    paging: 5
- header: Lösungen
  body:
  - text: "Lösungen zu Teil 1"
    link: /library/hello-dart/de/part1-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
- header: Links
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
---

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 1.01: First Steps

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    move();
    putStar();
    move();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 1.02: World Design

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    move();
    putStar();
    move();
  }
}

main() {
  character = 'catgirl';
  field = 'stone';

  createWorld('scenario.txt', new MyPlayer());
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 1.03 Around Tree

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    move();

    turnLeft();
    move();
    turnRight();
    move();
    move();
    turnRight();
    move();
    turnLeft();

    turnLeft();
    move();
    turnRight();
    move();
    move();
    turnRight();
    move();
    turnLeft();

    move();

    turnLeft();
    move();
    turnRight();
    move();
    move();
    turnRight();
    move();
    turnLeft();

    removeStar();
  }
}
</pre>


#### Erklärungen

Die **Kommentare** im Quelltext wurden weggelassen (= der Text, welcher im Dart-Editor hellblau dargestellt wird).   
Kommentare schreibt man in den Quelltext als zusätzliche Informationen. Die Kommentare sind nur für Menschen und werden vom Computer ignoriert. Es gibt mehrere Möglichkeiten, um Kommentare in den Quelltext zu schreiben:
  1. Einfache Kommentare mit zwei Schrägstrichen `//` (nach den Strichen gilt der Rest der Zeile als Kommentar).
  2. Kommentare für Funktionen und Klassen werden mit drei Schrägstrichen `///` eingeleitet.
  3. (selten) Längere Kommentare über mehrere Zeilen schliesst man mit `/*` und `*/` ein.


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 1.04: Around Tree with Function

<pre class="prettyprint lang-java">
class MyPlayer extends Player {

  start() {
    move();
    goAroundTree();
    goAroundTree();
    move();
    goAroundTree();
    removeStar();
  }

  goAroundTree() {
    turnLeft();
    move();
    turnRight();
    move();
    move();
    turnRight();
    move();
    turnLeft();
  }
}
</pre>

#### Erklärungen

1. Zur besseren Übersicht und um zu vermeiden, dass wir dreimal den gleichen Code schreiben müssen, haben wir eine neue Funktion `goAroundTree()` eingeführt. 
2. Hinter jeder Funktion steht das Klammerpaar `()`, was bedeutet, dass dieser Funktion keine Parameter übergeben werden. Später werden wir Funktionen mit Parametern kennen lernen.