+++
title = "Lösungen"
date = 2015-01-21
description = "Lösungen zu Hello Dart Teil 3."
prettify = true
# comments = true
commentsIdentifier = "/library/hello-dart/de/part3-solutions/"
aliases = [ 
  "/library/hello-dart/de/part3-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Lösungen"

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Seite als Word-Datei"
link = "/de/library/convert-web-page-to-word/"
+++

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
      findNextStar();
    }

    removeStar();
  }

  /// Funktion, um den nächsten Stern zu finden.
  findNextStar() {
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
