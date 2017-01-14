---
layout: article
title: "GreenfootKara - Lösungen Kapitel 2"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara/de/chapter2-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-de-chapter2-solutions.md
published: true
prettify: true
comments: true
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/greenfoot-kara/de/
    paging: Intro
  - text: "Hintergrundinfos"
    link: /library/greenfoot-kara/de/background/
    icon-css: fa fa-fw fa-info
  - text: "Kapitel 1: Erste Schritte"
    link: /library/greenfoot-kara/de/chapter1/
    paging: 1
  - text: "Kapitel 2: Programmfluss"
    link: /library/greenfoot-kara/de/chapter2/
    paging: 2
  - text: "Kapitel 3: Variablen"
    link: /library/greenfoot-kara/de/chapter3/
    paging: 3
  - text: "Kapitel 4: Sokoban Spiel"
    link: /library/greenfoot-kara/de/chapter4/
    paging: 4
  - text: "Kapitel 5: Methoden"
    link: /library/greenfoot-kara/de/chapter5/
    paging: 5
- header: Lösungen
  body:
  - text: "Lösungen Kapitel 2"
    link: /library/greenfoot-kara/de/chapter2-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
- header: Downloads
  body:
  - text: scenarios-chapter-2-solutions.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-2-solutions.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
languages: 
  header: Sprachen
  collection: library
  item: greenfoot-kara
  part: chapter2-solutions
  active: de
---

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.01

![TASK 2.01 - Solution](/assets/library/greenfoot-kara/chapter2/task01-flowchart-solution.png)


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.02

![TASK 2.02 - Solution](/assets/library/greenfoot-kara/chapter2/task02-solution.png)


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.03

<div class="alpha-list hidden"></div>

1. Beschreiben Sie zuerst mit Worten, was die folgenden Codebeispiele bewirken.    
  **Nimmt ein Blatt weg, wenn es eines hat, legt ein Blatt hin, wenn keines dort ist (Invertieren).**

2. Skizzieren Sie es als Flussdiagramm.

![TASK 2.03 - Solution](/assets/library/greenfoot-kara/chapter2/task03-solution.png)


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.04

<div class="alpha-list hidden"></div>

1. Beschreiben Sie zuerst mit Worten, was die folgenden Codebeispiele bewirken.    
  **Wenn Kara auf einem Blatt ist, geht er einen Schritt vorwärts.**

2. Skizzieren Sie es als Flussdiagramm.

![TASK 2.04 - Solution](/assets/library/greenfoot-kara/chapter2/task04-solution.png)


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.05

<div class="alpha-list hidden"></div>

1. Beschreiben Sie zuerst mit Worten, was die folgenden Codebeispiele bewirken.    
  **Nimmt das Blatt auf, wenn links ein Baum steht.**

2. Skizzieren Sie es als Flussdiagramm.

![TASK 2.05 - Solution](/assets/library/greenfoot-kara/chapter2/task05-solution.png)


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.06

<pre class="prettyprint lang-java">
public void act() {
    if (treeFront()) {
        goAroundTree();
    } else {
        move();
    }

    if (onLeaf()) {
        removeLeaf();
        stop();
    }
}

public void goAroundTree() {
    turnLeft();
    move();
    turnRight();
    move();
    move();
    turnRight();
    move();
    turnLeft();
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.07

<pre class="prettyprint lang-java">
public void act() {
    if (treeLeft()) {
        move();
    } else {
        if (onLeaf()) {
            removeLeaf();
            move();
        } else {
            move();
        }
    }
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.08

<pre class="prettyprint lang-java">
public void act() {
    if (treeLeft() && treeRight()) {
        putLeaf();
        stop();
    } else {
        move();
    }
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.09

<pre class="prettyprint lang-java">
public void act() {
    if (treeLeft() || treeRight()) {
        putLeaf();
        move();
    } else {
        move();
    }

    if (onLeaf()) {
        stop();
    }
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.10

<pre class="prettyprint lang-java">
public void act() {
    if (!onLeaf()) {
        putLeaf();
    }

    if (!treeFront()) {
        move();
    } else {
        stop();
    }
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.11

<pre class="prettyprint lang-java">
public void act() {
    if (onLeaf()) {
        removeLeaf();
    } else {
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
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.12

<pre class="prettyprint lang-java">
public void act() {
    if (!treeFront()) {
        removeLeaf();
        findNextLeaf();
    } else {
        removeLeaf();
        stop();
    }
}

public void findNextLeaf() {
    // Erst mal vorne schauen.
    move();
    if (!onLeaf()) {
        // Kein Blatt vorne, also zurueck und links schauen.
        turnAndGoBack();
        turnRight();
        move();
        if (!onLeaf()) {
            // Links ist auch kein Blatt; dann muss es rechts liegen.
            turnAndGoBack();
            move();
        }
    }
}

public void turnAndGoBack() {
    turnLeft();
    turnLeft();
    move();
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.13

<table class="table">
  <thead>
    <tr>
      <th>#</th>
      <th>Code</th>
      <th>Description</th>
      <th>Steps</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>a.</td>
      <td><pre>while (treeLeft()) {
  move();
}</pre></td>
      <td>Solange links ein Baum steht, mache einen Schritt.</td>
      <td>4</td>
    </tr>
    
    <tr>
      <td>b.</td>
      <td><pre>while (treeRight()) {
  move();
}</pre></td>
      <td>Solange rechts ein Baum steht, mache einen Schritt.</td>
      <td>0</td>
    </tr>
    
    <tr>
      <td>c.</td>
      <td><pre>while (treeLeft() || treeRight()) {
  move();
}</pre></td>
      <td>Solange entweder links oder rechts ein Baum steht, mache einen Schritt.</td>
      <td>5</td>
    </tr>
    
    <tr>
      <td>d.</td>
      <td><pre>if (treeLeft()) {
  move();
} while (treeLeft() && treeRight()) {
  move();
}</pre></td>
      <td>Zuerst ein Schritt, wenn links ein Baum.
Solange links und rechts ein Baum steht, ma-che einen Schritt.</td>
      <td>4</td>
    </tr>

    <tr>
      <td>e.</td>
      <td><pre>while (!treeFront) {
  if (treeLeft()) {
    move();
  }
}</pre></td>
      <td>Solange kein Baum vor Kara steht: Wenn links ein Baum, mache einen Schritt.

**Achtung: Endlosschleife**
</td>
      <td>4</td>
    </tr>
    
  </tbody>
</table>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.14

<pre class="prettyprint lang-java">
public void act() {
    while (!onLeaf()) {
        if (treeFront()) {
            goAroundTree();
        } else {
            move();
        }
    }

    // Blatt gefunden --> essen.
    removeLeaf();

    stop();
}

public void goAroundTree() {
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
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.15

<pre class="prettyprint lang-java">
public void act() {
    while (treeFront()) {
        oneStepUp();
    }

    stop();
}

public void oneStepUp() {
    turnLeft();
    move();
    turnRight();
    move();
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 2.16

<pre class="prettyprint lang-java">
public void act() {
    makeOneStep();
}

public void makeOneStep() {
    if (!treeRight()) {
        // Kein Baum rechts --> gehe nach rechts.
        turnRight();
        move();
    } else {
        // Baum rechts.
        if (!treeFront()) {
            // Kein Baum vorne --> vorwärts gehen.
            move();
        } else {
            // Bäume rechts und vorne.
            if (!treeLeft()) {
                // Kien Baum links --> gehe nach links.
                turnLeft();
                move();
            } else {
                // Bäume rechts, vorne und links: Sackgasse.
                turnLeft();
                turnLeft();
                move();
            }
        }
    }
}
</pre>

