+++
title = "Lösungen"
date = 2015-01-21
description = "Lösungen zu Hello Dart Teil 1."
prettify = true
comments = true
commentsIdentifier = "/library/hello-dart/de/part1-solutions/"
aliases = [ 
  "/library/hello-dart/de/part1-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Lösungen"

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Seite als Word-Datei"
link = "/de/library/convert-web-page-to-word/"
+++

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

  createWorld('scenario.txt', MyPlayer());
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