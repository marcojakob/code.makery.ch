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
comments: false
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/hello-dart/de/
    paging: Einleitung
  - text: "<em>Installation</em>"
    link: /library/hello-dart/de/install/
    icon-css: fa fa-fw fa-cog
  - text: "<em>Hintergrundinfos</em>"
    link: /library/hello-dart/de/background/
    icon-css: fa fa-fw fa-info
  - text: "Teil 1: Erste Schritte"
    link: /library/hello-dart/de/part1/
    paging: 1
  - text: "Teil 2: Programmfluss"
    link: /library/hello-dart/de/part2/
    paging: 2
  - text: "Teil 3: Variablen"
    link: /library/hello-dart/de/part3/
    paging: 3
  - text: "Teil 4: Funktionen"
    link: /library/hello-dart/de/part4/
    paging: 4
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


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 2.04: Conditionals

##### a.

Nimmt den Stern weg, wenn es einen hat.


##### b.

Nimmt den Stern weg, wenn es einen hat und legt einen Stern hin, wenn es keinen hat (Invertieren).


##### c.

Nimmt den Stern weg, wenn links ein Baum steht.
