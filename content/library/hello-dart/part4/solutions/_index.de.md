+++
title = "Lösungen"
date = 2015-01-21
description = "Lösungen zu Hello Dart Teil 4."
prettify = true
comments = true
commentsIdentifier = "/library/hello-dart/de/part4-solutions/"
aliases = [ 
  "/library/hello-dart/de/part4-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Lösungen"

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Seite als Word-Datei"
link = "/de/library/convert-web-page-to-word/"
+++

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
