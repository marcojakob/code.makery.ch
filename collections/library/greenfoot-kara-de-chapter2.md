---
layout: article
title: "GreenfootKara - Kapitel 2: Programmfluss"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara/de/chapter2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-de-chapter2.md
published: true
prettify: true
comments: true
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/greenfoot-kara/de/
    paging: Intro
  - text: "Kapitel 1: Erste Schritte"
    link: /library/greenfoot-kara/de/chapter1/
    paging: 1
  - text: "Kapitel 2: Programmfluss"
    link: /library/greenfoot-kara/de/chapter2/
    paging: 2
    active: true
  - text: "Lösungen Kapitel 2"
    link: /library/greenfoot-kara/de/chapter2-solutions/
    icon-css: fa fa-fw fa-check-square-o
  - text: "Kapitel 3: Variablen"
    link: /library/greenfoot-kara/de/chapter3/
    paging: 3
  - text: "Kapitel 4: Sokoban Spiel"
    link: /library/greenfoot-kara/de/chapter4/
    paging: 4
  - text: "Kapitel 5: Methoden"
    link: /library/greenfoot-kara/de/chapter5/
    paging: 5
- header: Downloads
  body:
  - text: scenarios-chapter-2.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-2.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
- header: Sprachen
  languages: true
  body:
  - text: English
    link: /library/greenfoot-kara/chapter2/
    icon-css: fa fa-fw fa-globe
  - text: Deutsch
    link: /library/greenfoot-kara/de/chapter2/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Français
    link: /library/greenfoot-kara/fr/chapter2/
    icon-css: fa fa-fw fa-globe
---

## Das Flussdiagramm

Um schwierige Aufgaben beim Programmieren zu lösen, ist es oft hilfreich, den Programmablauf in einem Flussdiagramm darzustellen. Folgende Symbole werden in einem Flussdiagramm verwendet:


<table class="table table-bordered text-center">
  <tbody>
    <tr>
      <td><img src="/assets/library/greenfoot-kara/chapter2/flowchart-symbol1.png"></td>
      <td>Start / Stopp</td>
    </tr>
    <tr>
      <td><img src="/assets/library/greenfoot-kara/chapter2/flowchart-symbol2.png"></td>
      <td>Aktivität</td>
    </tr>
    <tr>
      <td><img src="/assets/library/greenfoot-kara/chapter2/flowchart-symbol3.png"></td>
      <td>Entscheidung / Bedingung</td>
    </tr>
  </tbody>
</table>


#### <i class="fa fa-rocket"></i> AUFGABE 2.01

Ergänzen Sie das Flussdiagramm so, dass Kara das Kleeblatt in allen Welten mit den folgenden Eigenschaften erreicht:   

![Aufgabe 1 - Welt 1](/assets/library/greenfoot-kara/chapter2/task01-world1.png)

![Aufgabe 1 - Welt 2](/assets/library/greenfoot-kara/chapter2/task01-world2.png)

* Das Kleeblatt liegt immer gerade vor ihm – er muss nur um die Bäume herumlaufen.
* Es stehen nie zwei Bäume nebeneinander.

![Aufgabe 1 - Flussdiagramm](/assets/library/greenfoot-kara/chapter2/task01-flowchart.png) 


#### <i class="fa fa-rocket"></i> AUFGABE 2.02

Zeichnen Sie ein erweitertes Diagramm, so dass Kara das Kleeblatt am Schluss auch aufliest, wenn er es findet. Hier finden Sie nochmals die verfügbaren Methoden von Kara als Hilfe:

![Kara Methods](/assets/library/greenfoot-kara/chapter2/task02-kara-methods.png)


***

## Bedingte Anweisungen in Java

<pre class="prettyprint lang-java">
if (treeFront()) {      // Bedingung.
	turnLeft();         // Block 1 wird ausgeführt, wenn Bedingung true ist.
} else {
	move();             // Block 2 wird ausgeführt, wenn Bedingung false ist.
}
</pre>

**Hinweis: Der `else`-Teil (Block 2) kann weggelassen werden, wenn er nicht benötigt wird.**


#### <i class="fa fa-rocket"></i> AUFGABE 2.03

<div class="alpha-list hidden"></div>

1. Beschreiben Sie zuerst mit Worten, was die folgenden Codebeispiele bewirken.
2. Skizzieren Sie es als Flussdiagramm.

<pre class="prettyprint lang-java">
if (onLeaf()) {
	removeLeaf();
} else {
	putLeaf();
}
move();
</pre>

#### <i class="fa fa-rocket"></i> AUFGABE 2.04

<div class="alpha-list hidden"></div>

1. Beschreiben Sie zuerst mit Worten, was die folgenden Codebeispiele bewirken.
2. Skizzieren Sie es als Flussdiagramm.

<pre class="prettyprint lang-java">
if (onLeaf()) {
	move();
}
</pre>


#### <i class="fa fa-rocket"></i> AUFGABE 2.05

Bedingte Anweisungen können auch ineinander verschachtelt werden.

<div class="alpha-list hidden"></div>

1. Beschreiben Sie zuerst mit Worten, was die folgenden Codebeispiele bewirken.
2. Skizzieren Sie es als Flussdiagramm.

<pre class="prettyprint lang-java">
if (treeLeft()) {
	if (onLeaf()) {
		removeLeaf();
		move();
	} else {
		move();
	}
} else {
	move();
}
</pre>


### Implementieren

#### <i class="fa fa-rocket"></i> AUFGABE 2.06: Um Baum Herum II

* Öffnen Sie das Szenario **Kara 206...** aus dem Ordner **scenarios-chapter-2**. In diesem Szenario ist die Methode `goAroundTree()` bereits programmiert und ein Teil der `act()`-Methode ist vorbereitet.
* Programmieren Sie nun den Ablauf, welchen Sie unter *Aufgabe 2* als Flussdiagram gezeichnet haben in der `act()`-Methode.
* In diesem Szenario haben Sie **verschiedene Welten** zur Verfügung (a, b und c). 
* Ihr Programm sollte in jeder Welt **ohne Fehlermeldungen** funktionieren.


#### <i class="fa fa-rocket"></i> AUFGABE 2.07: Verschachtelte Bedingungen

* Laden Sie das Szenario **Kara 207...** in Greenfoot und programmieren Sie das unter *Aufgabe 5* skizzierte Programm.
* Formen Sie das Programm so um, dass Kara die Kleeblätter nur dann aufliest, wenn *kein* Baum neben ihm ist.


***

## Logische Operatoren

Unser Kara kann nun schon mehr als einfach nur simple Befehle abzuarbeiten. Er kann durch die Benutzung von Bedingungen auf die jeweiligen Ergebnisse eines Sensors unterschiedlich reagieren. Es sollte Kara aber natürlich auch möglich sein, auf zwei oder mehrere Sensoren gleichzeitig zu reagieren. 

Die folgende Tabelle zeigt die drei wichtigsten logischen Operatoren in Java:

<table class="table">
  <thead>
    <tr>
      <th>Operator</th>
      <th>Beschreibung</th>
      <th>Beispiel</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`&&`</td>
      <td>und</td>
      <td>`treeFront() && onLeaf()`</td>
      <td>Ist erfüllt (true), wenn beide Aussagen erfüllt sind, d.h. wenn Kara vor einem Baum **und** auf einem Blatt steht.</td>
    </tr>
    <tr>
      <td>`||`</td>
      <td>oder</td>
      <td>`treeFront() || onLeaf()`</td>
      <td>Ist dann erfüllt (true), wenn entweder die eine **oder** die andere Aussage oder beide erfüllt sind.</td>
    </tr>
    <tr>
      <td>`!`</td>
      <td>nicht</td>
      <td>`!treeFront()`</td>
      <td>Ändert einen Ausdruck von true in false und umgekehrt. Diese Aussage wäre also dann erfüllt (true), wenn Kara **nicht** vor einem Baum steht.</td>
    </tr>
  </tbody>
</table>

Ein Beispiel in Java würde wie folgt aussehen:

<pre class="prettyprint lang-java">
if (treeLeft() && onLeaf()) {
	// Mache etwas ...
}
</pre>

oder auch kombiniert:

<pre class="prettyprint lang-java">
if (treeLeft() && !treeRight()) {
	// Mache etwas ...
}
</pre>


#### <i class="fa fa-rocket"></i> AUFGABE 2.08: Angst vor Tunnel

![Aufgabe 8](/assets/library/greenfoot-kara/chapter2/task08.png)

Kara hat etwas Angst vor Tunneln. Er soll auf jedem Feld überprüfen, ob es ein Tunneleingang ist (d.h. ob es auf beiden Seiten Bäume hat). Ist dies der Fall, so lässt er vor Schreck gleich ein Kleeblatt fallen.

Laden Sie das Szenario **Kara 208...**, schreiben Sie das Programm und testen Sie es mit allen drei Welten.


#### <i class="fa fa-rocket"></i> AUFGABE 2.09: Blatt beim Baum

![Aufgabe 9](/assets/library/greenfoot-kara/chapter2/task09.png)

Nun soll Kara geradeaus gehen und überall dort ein Blatt legen, wo entweder links oder rechts oder auf beiden Seiten ein Baum steht.

Laden Sie das Szenario **Kara 209...** und schreiben Sie das Programm dazu.


#### <i class="fa fa-rocket"></i> AUFGABE 2.10: Blätter legen bis zum Baum

![Aufgabe 10](/assets/library/greenfoot-kara/chapter2/task10.png)

Kara soll vorwärts laufen und dabei überall ein Blatt legen, wo keines ist. Wenn er beim Baum angelangt ist, soll er nichts mehr machen (auch wenn der Act- oder der Run-Knopf nochmals gedrückt wird).

Laden Sie das Szenario **Kara 210...** und schreiben Sie das Programm dazu.


#### <i class="fa fa-rocket"></i> AUFGABE 2.11: Rundgang

Kara geht in einem Rundgang auf die Suche nach einem Kleeblatt (und liest es auf). Jedes Feld im Rundgang hat genau zwei freie benachbarte Felder. Eines liegt immer hinter Kara, von diesem Feld aus ist er auf das aktuelle Feld gekommen.

Laden Sie das Szenario **Kara 211...** und schreiben Sie das Programm dazu. Testen Sie das Programm in allen drei Welten.

*Tipp: Stellen Sie sich vor, was nach jedem Drücken des Act-Knopfes geschehen muss. Zeichnen Sie dafür als Hilfe ein Flussdiagramm.*


#### <i class="fa fa-rocket"></i> AUFGABE 2.12 (schwierig): Kara spielt Pacman

![Aufgabe 12](/assets/library/greenfoot-kara/chapter2/task12.png)

Kara spielt Pacman: Er steht auf dem ersten Kleeblatt einer langen Spur von Kleeblättern, die vor einem Baum endet. Er soll alle Kleeblätter auffressen und vor den Bäumen stoppen.

Schreiben Sie zur besseren Übersicht für gewisse Programmteile eigene Methoden.


***

## Schleifen

Kara kann jetzt nach von uns festgelegten Regeln auf verschiedene Situationen reagieren. Er ist allerdings noch nicht in der Lage, Anweisungen bis zum Eintreten eines bestimmten Ereignisses zu wiederholen. Zum mehrfachen Ausführen von Anweisungsblöcken werden Schleifen verwendet.

Als Beispiel möchten wir folgendes tun: 

*Kara soll sich solange vorwärts bewegen, bis er auf einen Baum trifft.*

![Schleifen Beispiel](/assets/library/greenfoot-kara/chapter2/loop-example-world.png)

Im Flussdiagramm sieht man, dass `move()` immer wieder ausgeführt wird, 
solange kein Baum vor Kara steht.

![Loop Example - Flowchart](/assets/library/greenfoot-kara/chapter2/loop-example-flowchart.png)

Dies ist die Schreibweise in Java:

<pre class="prettyprint lang-java">
while (!treeFront()) {
	move();
}
</pre>


#### <i class="fa fa-rocket"></i> AUFGABE 2.13

![Aufgabe 13](/assets/library/greenfoot-kara/chapter2/task13.png)

Kara steht vor einem Tunnel.

Beschreiben Sie, was die folgenden Schleifen bewirken und wie viele Schritte Kara macht.

<table class="table">
  <thead>
    <tr>
      <th>#</th>
      <th>Code</th>
      <th>Beischreibung</th>
      <th>Anzahl Schritte</th>
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
      <td>?</td>
      <td>?</td>
    </tr>
    
    <tr>
      <td>c.</td>
      <td><pre>while (treeLeft() || treeRight()) {
  move();
}</pre></td>
      <td>?</td>
      <td>?</td>
    </tr>
    
    <tr>
      <td>d.</td>
      <td><pre>if (treeLeft()) {
  move();
} while (treeLeft() && treeRight()) {
  move();
}</pre></td>
      <td>?</td>
      <td>?</td>
    </tr>

    <tr>
      <td>e.</td>
      <td><pre>while (!treeFront) {
  if (treeLeft()) {
    move();
  }
}</pre></td>
      <td>?</td>
      <td>?</td>
    </tr>
    
  </tbody>
</table>


#### <i class="fa fa-rocket"></i> AUFGABE 2.14: Um Baum herum III

![Aufgabe 14](/assets/library/greenfoot-kara/chapter2/task14.png)

Dies ist die ähnliche Übung, wie in Aufgabe 6: Kara soll ein Kleeblatt finden, das geradeaus vor ihm liegt. Nun können aber eine beliebige Anzahl Bäume hintereinander stehen. 

* Laden Sie das Szenario **Kara 214..** und verbessern Sie die Methode `goAroundTree()` so, dass Kara um mehrere Bäume herumgehen kann. Testen Sie Ihr Programm in allen vorgegebenen Welten. 
* Ändern Sie die `act()`-Methode so ab, dass man nur noch einmal auf den Act-Knopf drücken muss. Kara soll dann automatisch um die Bäume laufen, bis er das Kleeblatt erreicht. Am Schluss soll er es dann wie-der auffressen.


#### <i class="fa fa-rocket"></i> AUFGABE 2.15: Treppensteigen

![Aufgabe 15](/assets/library/greenfoot-kara/chapter2/task15.png)

Kara soll eine beliebig lange Treppe hochlaufen. 

Schreiben Sie eine Methode `oneStepUp()`, wo Sie Kara eine einzelne Stufe hochsteigen lassen. Überlegen Sie Sich, wie Kara erkennen kann, ob er noch eine Stufe steigen muss. 


#### <i class="fa fa-rocket"></i> AUFGABE 2.16 (difficult): Kara als Wächter

![Aufgabe 16](/assets/library/greenfoot-kara/chapter2/task16.png)

Kara will einen Wald bewachen. Er soll endlos aussen am Waldrand entlang laufen. 

Als Hilfe können Sie Sich ein Flussdiagramm zeichnen.

*Hinweis: Für eine Endlosschleife können wir auf den Run-Knopf drücken.*


*** 

***Credits:*** *[Ideen und Konzepte von Kara](http://www.swisseduc.ch/informatik/karatojava/) wurden entwickelt von Jürg Nievergelt, Werner Hartmann, Raimond Reichert et al. Einige Kara-Übungen basieren auf [Unterlagen von Horst Gierhardt](http://www.swisseduc.ch/informatik/karatojava/javakara/material/).*


***

## Wie weiter?

Fahren Sie weiter mit [Kapitel 3: Variablen](/library/greenfoot-kara/de/chapter3/)






