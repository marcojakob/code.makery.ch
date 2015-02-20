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

    while (!treeFront()) {
      move();

      if (onStar()) {
        count = count + 1;
      }
    }

    say('Ich habe ${count} Sterne gefunden.');
  }
}
</pre>