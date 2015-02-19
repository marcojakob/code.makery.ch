---
layout: article
title: "Hello Dart - Lösungen Teil 3"
date: 2015-01-21 00:00
slug: hello-dart/de/part3-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-de-part3-solutions.md
description: "Lösungen zu Hello Dart Teil 3."
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
  - text: "Lösungen zu Teil 3"
    link: /library/hello-dart/de/part3-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
- header: Links
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
---

#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 3.01: Conditionals

##### a.

Nimmt den Stern weg, wenn es einen hat.


##### b.

Nimmt den Stern weg, wenn es einen hat und legt einen Stern hin, wenn es keinen hat (Invertieren).


##### c.

Nimmt den Stern weg, wenn links ein Baum steht.


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 3.02: Star Track

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (canMove()) {
      if (!onStar()) {
        putStar();
      }
      move();
    }
    putStar();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 3.03: Star at Tree

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (canMove()) {
      if (treeLeft() || treeRight()) {
        putStar();
      }
      move();
    }
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 3.04: Around Tree II

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!onStar()) {
      if (treeFront()) {
        goAroundTree();
      } else {
        move();
      }
    }

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


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 3.05: Round Trip

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!onStar()) {
      if (!treeFront()) {
        move();
      } else {
        if (!treeLeft()) {
          turnLeft();
          move();
        } else {
          turnRight();
          move();
        }
      }
    }

    removeStar();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 3.06: Around Tree III

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!onStar()) {
      if (treeFront()) {
        goAroundTree();
      } else {
        move();
      }
    }

    removeStar();
  }

  goAroundTree() {
    turnLeft();
    move();
    turnRight();
    move();

    while (treeRight()) {
      move();
    }

    turnRight();
    move();
    turnLeft();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 3.07: Follow the Trail

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!treeFront()) {
      removeStar();
      findNextLeaf();
    }

    removeStar();
  }

  /// Funktion, um den nächsten Stern zu finden.
  findNextLeaf() {
    // Erst mal vorne schauen.
    move();
    if (!onStar()) {

      // Kein Stern vorne, also zurück und links schauen.
      turnAndGoBack();
      turnRight();
      move();
      if (!onStar()) {

        // Links ist auch kein Stern; dann muss er rechts liegen.
        turnAndGoBack();
        move();
      }
    }
  }

  /// Funktion, um einen Schritt zurück zu gehen.
  turnAndGoBack() {
    turnLeft();
    turnLeft();
    move();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> LÖSUNG AUFGABE 3.08: Guard

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (true) {
      makeOneStep();
    }
  }

  makeOneStep() {
    if (!treeRight()) {
      // Kein Baum rechts --> gehe nach rechts.
      turnRight();
      move();
    } else {
      // Baum rechts.
      if (!treeFront()) {
        // Kein Baum vorne --> ein Schritt vorwärts.
        move();
      } else {
        // Bäume rechts und vorne.
        if (!treeLeft()) {
          // Kein Baum links --> gehe nach links.
          turnLeft();
          move();
        } else {
          // Bäume rechts, vorne und links --> Sackgasse.
          turnLeft();
          turnLeft();
          move();
        }
      }
    }
  }
}
</pre>
