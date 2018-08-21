+++
title = "Lösungen"
date = 2015-01-21
description = "Lösungen zu Hello Dart Teil 2."
prettify = true
comments = true
commentsIdentifier = "/library/hello-dart/de/part2-solutions/"
aliases = [ 
  "/library/hello-dart/de/part2-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Lösungen"

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Seite als Word-Datei"
link = "/de/library/convert-web-page-to-word/"
+++

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.01: Loop

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (canMove()) {
      move();
    }
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 2.02: Loop Star

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!onStar()) {
      move();
    }
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 2.03: Leaving the Tunnel

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (treeLeft() &amp;&amp; treeRight()) {
      move();
    }

    putStar();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 2.04: Afraid of Tunnel

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!treeLeft() || !treeRight()) {
      move();
    }
    say('AHHHH! This looks very dark in here!');
  }
}
</pre>

Man könnte die `while`-Bedingung auch anders formulieren, zum Beispiel:

<pre class="prettyprint lang-dart">
while (!(treeLeft() &amp;&amp; treeRight())
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 2.05: Climbing Up

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (treeFront()) {
      oneStepUp();
    }
  }

  /// Steigt eine Stufe nach oben.
  oneStepUp() {
    turnLeft();
    move();
    turnRight();
    move();
  }
}
</pre>