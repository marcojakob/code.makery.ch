---
layout: article
title: "Hello Dart - Solutions Part 4"
date: 2015-04-18 00:00
slug: hello-dart/part4-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part4-solutions.md
description: "Solutions for Hello Dart Part 4."
image: /assets/library/hello-dart/hello-dart.png
published: true
prettify: true
comments: true
sidebars:
- header: Articles in this Series
  body:
  - text: "Introduction"
    link: /library/hello-dart/
    paging: Intro
  - text: "Background Infos"
    link: /library/hello-dart/background/
    icon-css: fa fa-fw fa-info
    paging: <i class="fa fa-info"></i>
  - text: "Installation"
    link: /library/hello-dart/install/
    icon-css: fa fa-fw fa-cog
    paging: <i class="fa fa-cog"></i>
  - text: "Part 1: First Steps"
    link: /library/hello-dart/part1/
    paging: 1
  - text: "Part 2: Loops"
    link: /library/hello-dart/part2/
    paging: 2
  - text: "Part 3: Conditionals"
    link: /library/hello-dart/part3/
    paging: 3
  - text: "Part 4: Variables"
    link: /library/hello-dart/part4/
    paging: 4
  - text: "Part 5: Functions"
    link: /library/hello-dart/part5/
    paging: 5
  - text: "Next Steps"
    link: /library/hello-dart/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Solutions
  body:
  - text: "Solutions Part 4"
    link: /library/hello-dart/part4-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
- header: Links
  body:
  - text: Page as Word File
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-word-o
languages:
  header: Languages
  collection: library
  item: hello-dart
  part: part4-solutions
  active: en
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
