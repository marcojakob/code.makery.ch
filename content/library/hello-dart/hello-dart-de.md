---
layout: article
title: "Hello Dart: Einführung in die Programmierung (Deutsch)"
date: 2015-01-21 00:00
slug: hello-dart/de
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-de.md
description: "Hello Dart ist eine spielerische Einführung in die Programmierung. Lernen Sie die Grundlagen der Programmierung mit Dart."
image: /assets/library/hello-dart/hello-dart.png
published: true
prettify: false
comments: true
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/hello-dart/de/
    paging: Einleitung
    active: true
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
languages:
  header: Sprachen
  collection: library
  item: hello-dart
  part:
  active: de
---

![Hello Dart](/assets/library/hello-dart/hello-dart-animation.gif)

> `Hello Dart` ist eine spielerische Einführung in die Programmierung mit Dart.


Bisher war es kaum realistisch, als Programmieranfänger mobile oder webbasierte Applikationen zu entwickeln. Die Programmiersprache Dart bietet neue Möglichkeiten, wie solche Applikationen viel einfacher programmiert werden können.

`Hello Dart` führt Sie schrittweise durch die Grundlagen der Programmierung. Es werden keine Programmierkenntnisse vorausgesetzt. Die spielerische Welt von `Hello Dart` veranschaulicht Ihre Programme. Bald werden Sie aber aus den engen Regeln dieser Welt ausbrechen wollen, was auch das Ziel ist. Nach der Einführung mit `Hello Dart` haben Sie eine gute Grundlage, um schon bald eigene Web-Programmierprojekte zu realisieren.

<div class="alert alert-info">
Lesen Sie unter <a class="alert-link" href="/library/hello-dart/de/background/">Hintegrundinfos</a>, warum ich Dart im Moment als optimale Sprache für Web und Mobile Apps sehe, die sich sowohl für Einsteiger wie auch für professionelle Softwareentwickler eignet.
</div>


## Die Welt

Die Welt von `Hello Dart` besteht aus Feldern, Bäumen und Sternen.

![Elemente](/assets/library/hello-dart/intro/elements.png)


## Die Spieler

In der Welt von `Hello Dart` bewegen wir uns mit unserem Spielcharakter. Sie können aus einem von vier Charakteren auswählen. 

![Charaktere](/assets/library/hello-dart/intro/characters.png)

Ein Spielcharakter, genannt `Player`, hat folgende Möglichkeiten:


### Aktionen

<table class="table">
  <thead>
    <tr>
      <th>Aktion</th>
      <th>Befehl</th>
      <th>Beschreibung</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="vertical-align:middle">![Move](/assets/library/hello-dart/intro/move.png)</td>
      <td style="vertical-align:middle">`move()`</td>
      <td style="vertical-align:middle">Der Spieler macht einen Schritt vorwärts.</td>
    </tr>
    <tr>
      <td style="vertical-align:middle">![Turn Left](/assets/library/hello-dart/intro/turn-left.png)</td>
      <td style="vertical-align:middle">`turnLeft()`</td>
      <td style="vertical-align:middle">Der Spieler dreht sich um 90° nach links.</td>
    </tr>
    <tr>
      <td style="vertical-align:middle">![Turn Right](/assets/library/hello-dart/intro/turn-right.png)</td>
      <td style="vertical-align:middle">`turnRight()`</td>
      <td style="vertical-align:middle">Der Spieler dreht sich um 90° nach rechts.</td>
    </tr>
    <tr>
      <td style="vertical-align:middle">![Put Star](/assets/library/hello-dart/intro/put-star.png)</td>
      <td style="vertical-align:middle">`putStar()`</td>
      <td style="vertical-align:middle">Der Spieler legt einen Stern hin.</td>
    </tr>
    <tr>
      <td style="vertical-align:middle">![Move](/assets/library/hello-dart/intro/remove-star.png)</td>
      <td style="vertical-align:middle">`removeStar()`</td>
      <td style="vertical-align:middle">Der Spieler nimmt einen Stern weg.</td>
    </tr>
    <tr>
      <td style="vertical-align:middle">![Say](/assets/library/hello-dart/intro/say.png)</td>
      <td style="vertical-align:middle; ">`say('Hello')`</td>
      <td style="vertical-align:middle">Der Spieler sagt etwas in einer Sprechblase.</td>
    </tr>
  </tbody>
</table>


### Sensoren

<table class="table">
  <thead>
    <tr>
      <th>Sensor</th>
      <th>Befehl</th>
      <th>Beschreibung</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="vertical-align:middle">![Can Move](/assets/library/hello-dart/intro/can-move.png)</td>
      <td style="vertical-align:middle">`canMove()`</td>
      <td style="vertical-align:middle">Der Spieler schaut nach, ob er sich auf das nächste Feld bewegen kann.</td>
    </tr>
    <tr>
      <td style="vertical-align:middle">![Tree Front](/assets/library/hello-dart/intro/tree-front.png)</td>
      <td style="vertical-align:middle">`treeFront()`</td>
      <td style="vertical-align:middle">Der Spieler schaut nach, ob sich vor ihm ein Baum befindet.</td>
    </tr>
    <tr>
      <td style="vertical-align:middle">![Tree Left](/assets/library/hello-dart/intro/tree-left.png)</td>
      <td style="vertical-align:middle">`treeLeft()`</td>
      <td style="vertical-align:middle">Der Spieler schaut nach, ob sich links von ihm ein Baum befindet.</td>
    </tr>
    <tr>
      <td style="vertical-align:middle">![Tree Right](/assets/library/hello-dart/intro/tree-right.png)</td>
      <td style="vertical-align:middle">`treeRight()`</td>
      <td style="vertical-align:middle">Der Spieler schaut nach, ob sich rechts von ihm ein Baum befindet.</td>
    </tr>
    <tr>
      <td style="vertical-align:middle">![On Star](/assets/library/hello-dart/intro/on-star.png)</td>
      <td style="vertical-align:middle">`onStar()`</td>
      <td style="vertical-align:middle">Der Spieler schaut nach, ob er auf einem Stern steht.</td>
    </tr>
  </tbody>
</table>


## Los geht's

#### Installation

Als erstes sollten Sie den Dart Editor und die `Hello Dart`-Szenarien [installieren](/library/hello-dart/de/install/).


#### Hintergrundinfos

Falls Sie mehr darüber erfahren möchten, weshalb ich Dart und `Hello Dart` für die Programmierung empfehle, finden Sie unter [Hintergrundinfos](/library/hello-dart/de/background/) weiterführende Informationen.

***

### Copyright

Ich veröffentliche das Material von `Hello Dart` unter der [Creative Commons Attribution 4.0](http://creativecommons.org/licenses/by/4.0/) Lizenz. Dies bedeutet, dass Sie damit so ziemlich alles machen dürfen, was Sie möchten. Bitte beachten Sie aber Folgendes:

* Wenn Sie meine Unterlagen oder Programme verwenden, müssen Sie klar angeben, dass dieses Material von mir stammt. Die Angabe muss meinen Namen, einen Link zum Original und einen Link zur Lizenz enthalten. Es könnte etwa so aussehen, wie meine Quellenangaben für die Bilder unten an den Seiten.


### Danksagung

Herzlichen Dank an die [Hasler Stiftung](http://www.haslerstiftung.ch/) für die Mitfinanzierung des Unterrichtsmaterials zu `Hello Dart`.

***

*Quellen*<br>
<em class="small">
[Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) Bilder stammen von Daniel Cook (Lostgarden.com), veröffentlicht unter [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) hat die "Planet Cute" Bilder weiterentwickelt und mir zur Verfügung gestellt. Optimiert wurden sie mit dem grossartigen [TexturePacker](https://www.codeandweb.com/texturepacker).
</em>