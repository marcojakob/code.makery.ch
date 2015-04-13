---
layout: article
title: "Hello Dart - Solutions Part 5"
date: 2015-04-18 00:00
slug: hello-dart/part5-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part5-solutions.md
description: "Solutions for Hello Dart Part 5."
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
  - text: "Solutions Part 5"
    link: /library/hello-dart/part5-solutions/
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
  part: part5-solutions
  active: en
---

#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 5.01: Counting Stars

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    drawRectangle(21, 4);
  }

  /// Zeichnet ein Rechteck mit [width] als Breite und [height] als Höhe.
  drawRectangle(int width, int height) {
    int i = 0;
    while (i < height) {
      putStars(width);
      turnAround();
      multiMove(width);

      // Zur nächsten Zeile gehen.
      turnRight();
      move();
      turnRight();

      i = i + 1;
    }
  }

  /// Legt eine Spur von [count] Sternen.
  putStars(int count) {
    int i = 0;
    while (i < count) {
      putStar();
      move();
      i = i + 1;
    }
  }

  /// Macht die Anzahl [steps] in die aktuelle Richtung.
  multiMove(int steps) {
    int i = 0;
    while (i < steps) {
      move();
      i = i + 1;
    }
  }

  /// Dreht um 180 Grad um.
  turnAround() {
    turnLeft();
    turnLeft();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 5.02: Candles on Cake

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    drawRectangle(21, 4);
    drawCandles(10);
  }

  /// Zeichnet Kerzen.
  drawCandles(int count) {
    int i = 0;
    while (i < count) {
      move();
      turnLeft();
      putStars(3);
      turnAround();
      multiMove(3);
      turnLeft();
      move();
      i = i + 1;
    }
  }

  /// Zeichnet ein Rechteck mit [width] als Breite und [height] als Höhe.
  drawRectangle(int width, int height) {
    int i = 0;
    while (i < height) {
      putStars(width);
      turnAround();
      multiMove(width);

      // Zur nächsten Zeile gehen.
      turnRight();
      move();
      turnRight();

      i = i + 1;
    }
  }

  /// Legt eine Spur von [count] Sternen.
  putStars(int count) {
    int i = 0;
    while (i < count) {
      putStar();
      move();
      i = i + 1;
    }
  }

  /// Macht die Anzahl [steps] in die aktuelle Richtung.
  multiMove(int steps) {
    int i = 0;
    while (i < steps) {
      move();
      i = i + 1;
    }
  }

  /// Dreht um 180 Grad um.
  turnAround() {
    turnLeft();
    turnLeft();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 5.03: Candles for Age

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  /// Your program.
  start() {
    drawRectangle(21, 4);

    int candles = (howOldIsGrandma() / 10).toInt();
    drawCandles(candles);
  }

  /// Gibt an, wie alt die Grossmutter ist.
  int howOldIsGrandma() {
    return 80;
  }

  /// Zeichnet Kerzen.
  drawCandles(int count) {
    int i = 0;
    while (i < count) {
      move();
      turnLeft();
      putStars(3);
      turnAround();
      multiMove(3);
      turnLeft();
      move();
      i = i + 1;
    }
  }

  /// Zeichnet ein Rechteck mit [width] als Breite und [height] als Höhe.
  drawRectangle(int width, int height) {
    int i = 0;
    while (i < height) {
      putStars(width);
      turnAround();
      multiMove(width);

      // Zur nächsten Zeile gehen.
      turnRight();
      move();
      turnRight();

      i = i + 1;
    }
  }

  /// Legt eine Spur von [count] Sternen.
  putStars(int count) {
    int i = 0;
    while (i < count) {
      putStar();
      move();
      i = i + 1;
    }
  }

  /// Macht die Anzahl [steps] in die aktuelle Richtung.
  multiMove(int steps) {
    int i = 0;
    while (i < steps) {
      move();
      i = i + 1;
    }
  }

  /// Dreht um 180 Grad um.
  turnAround() {
    turnLeft();
    turnLeft();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 5.04: Layered Cake

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  /// Your program.
  start() {
    int layers = (howOldIsGrandma() / 10 - 5).toInt();
    drawLayers(layers);
  }

  /// Gibt an, wie alt die Grossmutter ist.
  int howOldIsGrandma() {
    return 80;
  }

  /// Zeichnet Schichten.
  drawLayers(int count) {
    int currentWidth = 21;

    for (int i = 0; i < count; i++) {
      drawRectangle(currentWidth, 2);
      move();
      move();

      currentWidth = currentWidth - 4;
    }
  }

  /// Zeichnet ein Rechteck mit [width] als Breite und [height] als Höhe.
  drawRectangle(int width, int height) {
    int i = 0;
    while (i < height) {
      putStars(width);
      turnAround();
      multiMove(width);

      // Zur nächsten Zeile gehen.
      turnRight();
      move();
      turnRight();

      i = i + 1;
    }
  }

  /// Legt eine Spur von [count] Sternen.
  putStars(int count) {
    int i = 0;
    while (i < count) {
      putStar();
      move();
      i = i + 1;
    }
  }

  /// Macht die Anzahl [steps] in die aktuelle Richtung.
  multiMove(int steps) {
    int i = 0;
    while (i < steps) {
      move();
      i = i + 1;
    }
  }

  /// Dreht um 180 Grad um.
  turnAround() {
    turnLeft();
    turnLeft();
  }
}
</pre>