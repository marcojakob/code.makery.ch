---
layout: article
title: "Hello Dart - Part 1: First Steps"
date: 2015-04-18 00:00
slug: hello-dart/part1
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part1.md
description: "The first steps in programming with Dart. Get to know classes and functions and understand what the main function is for."
image: /assets/library/hello-dart/part1/around-tree.png
published: true
prettify: true
comments: true
sidebars:
- header: Articles in this Series
  body:
  - text: "Introduction"
    link: /library/hello-dart/
    paging: Intro
  - text: "Background Infos"
    link: /library/hello-dart/background/
    icon-css: fa fa-fw fa-info
    paging: <i class="fa fa-info"></i>
  - text: "Installation"
    link: /library/hello-dart/install/
    icon-css: fa fa-fw fa-cog
    paging: <i class="fa fa-cog"></i>
  - text: "Part 1: First Steps"
    link: /library/hello-dart/part1/
    paging: 1
    active: true
  - text: "Part 2: Loops"
    link: /library/hello-dart/part2/
    paging: 2
  - text: "Part 3: Conditionals"
    link: /library/hello-dart/part3/
    paging: 3
  - text: "Part 4: Variables"
    link: /library/hello-dart/part4/
    paging: 4
  - text: "Part 5: Functions"
    link: /library/hello-dart/part5/
    paging: 5
  - text: "Next Steps"
    link: /library/hello-dart/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Solutions
  body:
  - text: "Solutions Part 1"
    link: /library/hello-dart/part1-solutions/
    icon-css: fa fa-fw fa-check-square-o
- header: Links
  body:
  - text: Page as Word File
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-word-o
languages:
  header: Languages
  collection: library
  item: hello-dart
  part: part1
  active: en
---

In this part we will take our first steps in programming.

![Hello Dart](/assets/library/hello-dart/part1/hello.png)


### Prerequisites

* You need no prior knowledge of programming to start with `Hello Dart`!
* You must have the Dart Editor with the `Hello Dart` scenarios installed. If you don't have it yet, please go through the [installation instructions](/library/hello-dart/install/).

***


## The `MyPlayer` Class

Open the file `my_player.dart` from the folder `scenario1.01`.

![My Player](/assets/library/hello-dart/part1/my-player.png)

Let's have a look at the `MyPlayer` **class**. Attributes and behavior of our player are defined between the curly braces `{` and `}`.

Currently, the only thing that is in this class is `start()` and three instructions to move the player. `start()` is a function (functions are sometimes also called *methods*). In this function we can change the behavior of the player.


#### <i class="fa fa-rocket mg-t"></i> TASK 1.01: First Steps

Change the `start()` function so that the player first makes a step, then places a star and finally takes another step.

In the [introduction](/library/hello-dart/) you will find all commands a player can execute.

<div class="alert alert-info">
  **Note:** After every command you must place a semicolon `;`.
</div>

Click the *run* button ![Run](/assets/library/hello-dart/part1/run.png) to test your program.


## `index.html` and the `main()` Function

As we let our programs run in a web browser, we always need an `html` file. In `index.html` you will find an instruction for the browser to load the `my_player.dart` script. For the browser to know where the Dart program starts, a `main()` function is needed.

At the bottom of the Dart file you will find the `main()` function. Each Dart program must have  shall include the `main ()` function have as an entry point exactly.

Zuunterst in der Dart-Datei finden Sie die `main()`-Funktion. Jedes Dart-Programm muss genau eine `main()`-Funktion als Einstiegspunkt haben.

In unserer `main()`-Funktion rufen wir die Funktion `createWorld(...)` auf. Diese Funktion ist Teil von `Hello Dart` und zeichnet die ganze Welt mit dem Spieler und den Feldern. Sobald alles parat ist wird automatisch unsere `start()`-Funktion aufgerufen und der Spieler beginnt sich zu bewegen.


## Welt gestalten

Die Szenarien von `Hello Dart` beinhalten zusätzliche Grafiken, die Sie nach Ihren Vorlieben einschalten können.

![Catgirl](/assets/library/hello-dart/part1/catgirl.png)


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 1.02: World Design

Ändern Sie die `main()`-Funktion wie folgt ab:

<pre class="prettyprint lang-dart">
main() {
  character = 'catgirl';
  field = 'stone';
  
  createWorld('scenario.txt', new MyPlayer());
}
</pre>

Für **character** können Sie innerhalb der Anführungszeichen die Werte `boy`, `catgirl`, `stargirl` oder `pinkgirl` verwenden.

Für **field** sind die Werte `grass`, `stone`, `wood` oder `dirt` gültig.

<div class="alert alert-info">
  <strong>Tipp:</strong> Um eine Änderung zu testen geht es am schnellsten, wenn man `Ctrl+S` (oder `⌘+S`) zum Speichern und anschliessend im Browser `F5` (oder `⌘+R`) zum Aktualisieren klickt.
</div>


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 1.03: Around Tree

Öffnen Sie `scenario1.03`. Wenn Sie das Szenario starten, dann sollten Sie eine Welt mit drei Bäumen und einem Stern sehen.

Schreiben Sie ein Programm, welches Ihren Spieler auf dem angegebenen Weg zum Stern führt. Er muss dabei um die Bäume herumlaufen. Beim Stern angekommen, soll er ihn entfernen.

![Around Tree](/assets/library/hello-dart/part1/around-tree.png)


## Neue Funktionen

#### <i class="fa fa-rocket mg-t"></i> AUFGABE 1.04: Around Tree with Function

Wenn Sie Aufgabe 1.03 korrekt gelöst haben, so wird Ihr Programm wahrscheinlich *drei gleiche Teile* enthalten, nämlich für das Herumgehen um jeden Baum. Dies können wir zur besseren Übersicht noch etwas erweitern, indem wir eine neue Funktion einführen. Unterhalb der schliessenden Klammer `}` von der `start()`-Funktion erstellen wir eine neue Funktion:

<pre class="prettyprint lang-dart">
goAroundTree() {

}
</pre>

Schreiben Sie zwischen die geschweiften Klammern der Funktion die Befehle, die es braucht, um um den Baum zu kommen.

Benutzen Sie nun innerhalb der `start()`-Funktion die Funktion `goAroundTree()` für jeden der drei Bäume.

***

*Quellen*<br>
<em class="small">
[Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) Bilder stammen von Daniel Cook (Lostgarden.com), veröffentlicht unter [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) hat die "Planet Cute" Bilder weiterentwickelt und sie mir zur Verfügung gestellt.<br>
Einige Übungen in `Hello Dart` sind inspiriert von [Kara](http://www.swisseduc.ch/informatik/karatojava/). Kara wurde entwickelt von Jürg Nievergelt, Werner Hartmann, Raimond Reichert und anderen.
</em>