---
layout: article
title: "Hello Dart - Teil 1: Erste Schritte"
date: 2015-01-21 00:00
slug: hello-dart/de/part1
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-de-part1.md
description: "Die ersten Schritte im Programmieren mit Dart. Lernen Sie Klassen und Funktionen kennen und verstehen Sie, was die main-Funktion bewirkt."
image: /assets/library/hello-dart/part1/around-tree.png
published: true
prettify: true
comments: true
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/hello-dart/de/
    paging: Einleitung
  - text: "<em>Hintergrundinfos</em>"
    link: /library/hello-dart/de/background/
    icon-css: fa fa-fw fa-info
  - text: "<em>Installation</em>"
    link: /library/hello-dart/de/install/
    icon-css: fa fa-fw fa-cog
  - text: "Teil 1: Erste Schritte"
    link: /library/hello-dart/de/part1/
    paging: 1
    active: true
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
  - text: "Lösungen zu Teil 1"
    link: /library/hello-dart/de/part1-solutions/
    icon-css: fa fa-fw fa-check-square-o
- header: Links
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
---

In diesem ersten Teil wagen wir unsere ersten Schritte im Programmieren.  

![Hello Dart](/assets/library/hello-dart/part1/hello.png)


### Voraussetzungen

* Sie brauchen keine Vorkenntnisse im Programmieren, um mit `Hello Dart` zu starten!
* Sie müssen den Dart Editor mit den `Hello Dart`-Szenarien installiert haben. Falls Sie das noch nicht gemacht haben, finden Sie hier eine [Installationsanleitung](/library/hello-dart/de/install/).

***


## Die Klasse `MyPlayer`

Öffnen Sie die Datei `my_player.dart` aus dem Ordner `scenario1.01`.

![My Player](/assets/library/hello-dart/part1/my-player.png)

Betrachten wir einmal die **Klasse** `MyPlayer`. Zwischen den geschweiften Klammern `{` und `}` werden die Eigenschaften und das Verhalten unseres Spielers definiert. 

Das Einzige, was im Moment in dieser Klasse steht ist `start()` und drei Anweisungen für die Bewegungen des Spielers. `start()` ist eine Funktion (wird manchmal auch Methode genannt). Darin können wir das Verhalten unseres Spieleres verändern.


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 1.01: First Steps

Ändern Sie die `start()`-Funktion so ab, dass der Spieler zuerst einen Schritt macht, dann einen Stern legt und schliesslich wieder einen Schritt macht.

In der [Einleitung](/library/hello-dart/de/) finden Sie alle Befehle, die ein Spieler ausführen kann.

<div class="alert alert-info">
  <strong>Beachten Sie:</strong> Nach jedem Befehl muss ein Strichpunkt `;` stehen. 
</div>

Klicken Sie auf das *Run*-Symbol ![Run](/assets/library/hello-dart/part1/run.png), um Ihr Programm zu testen.


## `index.html` und die `main()`-Funktion

Da wir unsere Programme in einem Webbrowser laufen lassen, brauchen wir immer eine `html`-Datei. In `index.html` finden Sie jeweils eine Angabe, dass das Script `my_player.dart` geladen werden soll. Damit der Browser weiss, wo das Dart-Programm beginnt, braucht es eine `main()`-Funktion. 

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

#### <i class="fa fa-rocket mg-t"></i> AUFGABE 1.04: Around Tree with Method

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