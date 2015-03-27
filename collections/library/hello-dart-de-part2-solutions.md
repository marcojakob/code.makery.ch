---
layout: article
title: "Hello Dart - Lösungen Teil 2"
date: 2015-01-21 00:00
slug: hello-dart/de/part2-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-de-part2-solutions.md
description: "Lösungen zu Hello Dart Teil 2."
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
  - text: "Lösungen zu Teil 2"
    link: /library/hello-dart/de/part2-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
- header: Links
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
---

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