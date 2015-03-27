---
layout: article
title: "Hello Dart - Lösungen Teil 4"
date: 2015-01-21 00:00
slug: hello-dart/de/part4-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-de-part4-solutions.md
description: "Lösungen zu Hello Dart Teil 4."
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
  - text: "Hintergrundinfos"
    link: /library/hello-dart/de/background/
    icon-css: fa fa-fw fa-info
    paging: <i class="fa fa-info"></i>
  - text: "Installation"
    link: /library/hello-dart/de/install/
    icon-css: fa fa-fw fa-cog
    paging: <i class="fa fa-cog"></i>
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
  - text: "Nächste Schritte"
    link: /library/hello-dart/de/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Lösungen
  body:
  - text: "Lösungen zu Teil 4"
    link: /library/hello-dart/de/part4-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
- header: Links
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
---

#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 4.01: Counting Stars

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    int count = 0;

    while (canMove()) {
      move();

      if (onStar()) {
        count = count + 1;
      }
    }

    say('Ich habe ${count} Sterne gefunden.');
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 4.02: Cleaning Up

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    bool goingRight = true;

    while (!treeFront()) {
      removeStar();

      if (canMove()) {
        move();
      } else {
        // Wir sind an einem Rand.
        if (goingRight) {
          // Wir sind am rechten Rand und drehen um.
          turnAroundRight();
          goingRight = false;
        } else {
          // Wir sind am linken Rand und drehen um.
          turnAroundLeft();
          goingRight = true;
        }
      }
    }

    // Entferne den letzten Stern.
    removeStar();
  }

  /// Umdrehen am rechten Rand.
  turnAroundRight() {
    turnRight();
    move();
    turnRight();
  }

  /// Umdrehen am linken Rand.
  turnAroundLeft() {
    turnLeft();
    move();
    turnLeft();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 4.03: Inverting

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    bool goingRight = true;

    while (!treeFront()) {
      invertField();

      if (canMove()) {
        move();
      } else {
        // Wir sind an einem Rand.
        if (goingRight) {
          // Wir sind am rechten Rand und drehen um.
          turnAroundRight();
          goingRight = false;
        } else {
          // Wir sind am linken Rand und drehen um.
          turnAroundLeft();
          goingRight = true;
        }
      }
    }

    // Das letzte Feld invertieren.
    invertField();
  }

  /// Umdrehen am rechten Rand.
  turnAroundRight() {
    turnRight();
    move();
    turnRight();
  }

  /// Umdrehen am linken Rand.
  turnAroundLeft() {
    turnLeft();
    move();
    turnLeft();
  }

  /// Ein einzelnes Feld invertieren.
  invertField() {
    if (onStar()) {
      removeStar();
    } else {
      putStar();
    }
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 4.04: Tree Line

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  int longestRow = 0;

  /// Your program.
  start() {
    while (!onStar()) {
      if (treeFront()) {
        countRow();
      } else {
        move();
      }
    }

    say('Die längste Baumreihe ist ${longestRow} Bäume lang.');
  }

  /// Geht einer Baumreihe entlang und zählt die Bäume.
  countRow() {
    int currentRow = 0;

    turnLeft();

    while (treeRight())  {
      // Den Zähler für die aktuelle Zeile um eins erhöhen.
      currentRow++;
      move();
    }

    // Gehe um die Baumreihe herum.
    turnRight();
    move();
    move();
    turnRight();

    // Gehe zurück nach unten.
    while (canMove()) {
      move();
    }

    turnLeft();

    // Teste, ob die aktuelle zeile länger ist als alle bisherigen.
    if (currentRow > longestRow) {
      longestRow = currentRow;
    }
  }
}
</pre>
