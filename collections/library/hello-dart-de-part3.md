---
layout: article
title: "Hello Dart - Teil 3: Bedingte Anweisungen"
date: 2015-01-21 00:00
slug: hello-dart/de/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-de-part3.md
description: "Lernen Sie den Programmablauf mit bedingten Anweisungen zu steuern. Zahlreiche Hello Dart Übungen helfen beim Verständnis."
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
    active: true
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
  - text: "Lösungen zu Teil 3"
    link: /library/hello-dart/de/part3-solutions/
    icon-css: fa fa-fw fa-check-square-o
- header: Links
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
languages:
  header: Sprachen
  collection: library
  item: hello-dart
  part: part3
  active: de
---

Neben Schleifen gibt es eine zweite Struktur, die sehr wichtig ist, um den Programmablauf zu steuern. Mit **bedingten Anweisungen** kann man angeben, wann ein Block von Anweisungen ausgeführt werden soll und wann nicht.


<pre class="prettyprint lang-dart">
if (treeFront()) {  // Bedingung.
  turnLeft();       // Block 1 wird ausgeführt, wenn Bedingung zutrifft (=true).
} else {
  move();           // Block 2 wird ausgeführt, wenn Bedingung nicht zutrifft (=false).
}
</pre>

<div class="alert alert-info">
  **Hinweis:** Der `else`-Teil (Block 2) kann weggelassen werden, wenn er nicht benötigt wird.
</div>


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 3.01: Conditionals

Beschreiben Sie mit Worten, was die folgenden Codebeispiele bewirken. Testen Sie diese anschliessend im `scenario3.01`. Damit die bedingten Anweisungen nicht nur einmal ausgeführt werden, sollten Sie diese in die vorbereitete Schleife setzen.


##### a.

<pre class="prettyprint lang-dart">
if (onStar()) {
  removeStar();
}
move();
</pre>


##### b.

<pre class="prettyprint lang-dart">
if (onStar()) {
  removeLeaf();
} else {
  putLeaf();
}
move();
</pre>


##### c.

Bedingte Anweisungen können auch ineinander verschachtelt werden:

<pre class="prettyprint lang-dart">
if (treeLeft()) {
  if (onStar()) {
    removeStar();
  }
}
move();
</pre>

#### <i class="fa fa-rocket mg-t"></i> AUFGABE 3.02: Star Track

![Sternenspur](/assets/library/hello-dart/part3/star-track.png)

Der Spieler soll vorwärts laufen und dabei überall einen Stern legen, wo keiner ist. Achten Sie am Schluss darauf, dass auch auf dem letzten Feld noch ein Stern liegt.

Öffnen Sie das `scenario3.02` und schreiben Sie das Programm dazu.


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 3.03: Star at Tree

![Stern beim Baum](/assets/library/hello-dart/part3/star-at-tree.png)

Der Spieler soll geradeaus gehen und überall dort einen Stern legen, wo entweder links oder rechts ein Baum steht.

Beachten Sie, dass Sie, wie bei den Schleifen erklärt, auch hier die *Logischen Operatoren* `&&`, `||` und `!` verwenden können.


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 3.04: Around Tree II

![Um Baum herum A](/assets/library/hello-dart/part3/around-tree-a.png)

Um die Bäume herum laufen kennen wir bereits aus dem Teil 1. Wir haben es dort wie folgt gelöst:

<pre class="prettyprint lang-dart">
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

Dieses Programm funktioniert ganz gut, solange die Welt mit den Bäumen immer genau gleich aussieht. Sobald ein Baum verschoben wird, gibt es Probleme.

![Um Baum herum B](/assets/library/hello-dart/part3/around-tree-b.png)

Das `scenario3.04` hat drei Varianten. Öffnen Sie dieses Szenario und ändern Sie in der `main()`-Funktion den Text `scenario-a.txt` in `scenario-b.txt` um. Testen Sie nun, was passiert, wenn Sie das Programm wie oben dargestellt starten.

Sie kennen nun etliche Möglichkeiten, wie Sie mit Hilfe von Sensor-Methoden auf Umstände reagieren können. Versuchen Sie nun das Programm so abzuändern, dass unser Spieler den Stern in allen Welten mit folgenden Eigenschaften erreicht:

* Der Stern liegt immer gerade vor ihm. Er muss also um die Bäume herumlaufen.
* Es stehen nie zwei Bäume nebeneinander.


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 3.05: Round Trip

![Rundgang](/assets/library/hello-dart/part3/round-trip.png)

Der Spieler geht in einem Rundgang auf die Suche nach einem Stern (und liest diesen auf). Jedes Feld im Rundgang hat genau zwei freie benachbarte Felder. Eines liegt immer hinter dem Spieler; von diesem Feld aus ist er auf das aktuelle Feld gekommen.

Laden Sie das `scenario3.05` und schreiben Sie ein Programm dazu. Testen Sie das Programm in **allen drei Welten**, `scenario-a.txt`, `scenario-b.txt` und `scenario-c.txt`.

*Tipp: Schreiben Sie zuerst eine Schleife, die stoppt, sobald der Spieler auf einem Stern ist. Stellen Sie sich dann vor, was nach jedem einzelnen durchgang durch die Schleife geschehen soll.*


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 3.06: Around Tree III

![Um Baum herum III](/assets/library/hello-dart/part3/around-tree-iii.png)

Dies ist die ähnliche Übung wie die Aufgabe 3.04: Der Spieler soll den Stern finden, der geradeaus vor ihm liegt. Nun können aber eine beliebige Anzahl Bäume hintereinander stehen.

Laden Sie das `scenario3.06` und verbessern Sie die Methode `goAroundTree()` so, dass der Spieler um mehrere Bäume herumgehen kann. Testen Sie Ihr Programm in allen vorgegebenen Welten (a, b, c und d).


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 3.07 (schwierig): Follow the Trail

![Rundgang](/assets/library/hello-dart/part3/follow-the-trail.png)

Der Spieler soll einer Spur von Sternen folgen und diese jeweils auflesen. Vor den Bäumen soll er anhalten.

*Wichtig: Schreiben Sie zur besseren Übersicht für gewisse Programmteile eigene Methoden.*


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 3.08 (schwierig): Guard

![Wächter](/assets/library/hello-dart/part3/guard.png)

Der Spieler soll einen Wald bewachen voller Sterne. Er soll aussen am Waldrand entlang laufen.

Beginnen Sie mit einem einzelnen Rundgang um den Wald. Anschliessend können Sie mit einer Endlosschleife `while (true)` den Spieler mehrere Rundgänge machen lassen.

Testen Sie Ihr Programm auch in der zweiten Welt.


## Wie weiter?

Lernen Sie im [Teil 4](/library/hello-dart/de/part4/), wie wir mit Variablen etwas speichern können.


***

*Quellen*<br>
<em class="small">
[Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) Bilder stammen von Daniel Cook (Lostgarden.com), veröffentlicht unter [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) hat die "Planet Cute" Bilder weiterentwickelt und sie mir zur Verfügung gestellt.<br>
Einige Übungen in `Hello Dart` sind inspiriert von [Kara](http://www.swisseduc.ch/informatik/karatojava/). Kara wurde entwickelt von Jürg Nievergelt, Werner Hartmann, Raimond Reichert und anderen.
</em>