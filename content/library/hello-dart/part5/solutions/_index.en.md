+++
title = "Solutions"
date = 2015-05-01
description = "Solutions for Hello Dart Part 5."
prettify = true
comments = true
commentsIdentifier = "/library/hello-dart/part5-solutions/"
aliases = [ 
  "/library/hello-dart/part5-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Solutions"

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Page as Word File"
link = "/library/convert-web-page-to-word/"
+++

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