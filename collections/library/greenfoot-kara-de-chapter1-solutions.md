---
layout: article
title: "GreenfootKara - Lösungen Kapitel 1"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara/de/chapter1-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-de-chapter1-solutions.md
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
  - text: "Lösungen Kapitel 1"
    link: /library/greenfoot-kara/de/chapter1-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
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
- header: Downloads
  body:
  - text: scenarios-chapter-1-solutions.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/2.1.0/scenarios-chapter-1-solutions.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
- header: Sprachen
  body:
  - text: English
    link: /library/greenfoot-kara/chapter1-solutions/
    icon-css: fa fa-fw fa-globe
  - text: Deutsch
    link: /library/greenfoot-kara/de/chapter1-solutions/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Français
    link: /library/greenfoot-kara/fr/chapter1-solutions/
    icon-css: fa fa-fw fa-globe
---

#### <i class="fa fa-check-square-o"></i> LÖSUNGEN AUFGABE 1.02

<div class="alpha-list hidden"></div>

1. Was bewirkt die Methode move()?   
  **Kara geht ein Feld nach vorne.**
2. Platzieren Sie zwei Karas in Ihrer Welt und sorgen Sie dafür, dass sie sich anschauen. Welche Methode brauchen Sie dazu?   
  **turnLeft() oder turnRight()**
3. Testen Sie die weiteren Methoden. In welche zwei Arten können die Methoden unterteilt werden?   
  **Methoden mit void: führen nur eine Aktion aus.**   
  **Methoden mit boolean: Öffnen ein Fenster mit dem Methodenergebnis.**


***

#### <i class="fa fa-check-square-o"></i> LÖSUNGEN AUFGABE 1.03

<div class="alpha-list hidden"></div>

1. Rufen Sie die Methode `onLeaf()` für einen Kara auf. Liefert Sie immer `false` zurück? Oder gibt es auch Situationen, in denen sie `true` zurückliefert?   
  **Wenn Kara auf einem Blatt steht, liefert die Methode true zurück.**
2. Setzen Sie zusätzlich einen Baum (Tree) in die Welt. Mit welcher Methode können Sie überprüfen, ob Kara vor einem Baum steht?   
  **treeFront()**
3. Was passiert, wenn Sie Kara mittels der Methode `move()` in einen Baum fahren lassen?   
  **Kara reklamiert dass er sich nicht bewegen kann.**


***

#### <i class="fa fa-check-square-o"></i> LÖSUNGEN AUFGABE 1.04

Welchen Zustandswerte hat Kara in den folgenden Situationen?

1. **x: 0, y: 0, rotation: 0**

2. **x: 1, y: 2, rotation: 180**


***

#### <i class="fa fa-check-square-o"></i> LÖSUNGEN AUFGABE 1.05

<div class="alpha-list hidden"></div>

1. Platzieren Sie ein Objekt von `MyKara` in Ihre Welt. Welche Methode haben Sie nun neu zur Verfügung?   
  **act()**
2. Was macht diese Methode?   
  **Schritt vorwärts, Drehung nach rechts, Schritt vorwärts**
3. Was passiert, wenn Sie in der Greenfoot-Steuerung (unten links) auf den **Act-Button** drücken?   
  **Das Gleiche wie vorher. Die act()-Methode wird aufgerufen.**
4. Klicken Sie auf den **Run-Button**. Was geschieht? (Versuchen Sie mal die Geschwindigkeit zu verstellen)   
  **act() wird immer wieder aufgerufen, bis man auf Pause drückt.**
5. Welche Methoden finden Sie unter *Rechtsklick | inherited from Kara*? Können Sie die auch benutzen?   
  **Alle Methoden von Kara. Die können auch von MyKara benutzt werden.**


***

#### <i class="fa fa-check-square-o"></i> LÖSUNGEN AUFGABE 1.06

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	
	public void act() {
		move();
        putLeaf();
        move();

		stop();
	}
}
</pre>

***

#### <i class="fa fa-check-square-o"></i> LÖSUNGEN AUFGABE 1.07

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	
	public void act() {
		move();

		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
		turnLeft();

		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
		turnLeft();

		move();

		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
		turnLeft();

		removeLeaf();

		stop();
	}
}
</pre>

#### Erklärungen

1. Die **Kommentare** im Quelltext wurden weggelassen (= der Text, welcher im Greenfoot-Editor entweder grau oder blau dargestellt wird).   
Kommentare schreibt man in den Quelltext als zusätzliche Informationen. Die Kommentare sind für Menschen und werden vom Computer ignoriert. Es gibt **drei Möglichkeiten**, um Kommentare in den Quelltext zu schreiben:
  1. Mit zwei Schrägstrichen `//` (nach den Strichen gilt der Rest der Zeile als Kommentar)
  2. Längere Kommentare über mehrere Zeilen schliesst man mit `/*` und `*/` ein.
  3. Kommentare für Methoden und Klassen werden zwischen `/**` und `*/` geschrieben.
2.	Die Methode `stop()` bewirkt, dass nach der `act()`-Methode gestoppt wird, auch wenn der Run-Knopf gedrückt wurde.


***

#### <i class="fa fa-check-square-o"></i> LÖSUNGEN AUFGABE 1.08

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	public void act() {
		move();
		goAroundTree();
		goAroundTree();
		move();
		goAroundTree();
		removeLeaf();

		stop();
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
}
</pre>

#### Erklärungen

1. Zur besseren Übersicht und um zu vermeiden, dass wir dreimal den gleichen Code schreiben müssen, haben wir eine neue Methode `goAroundTree()` eingeführt. 
2. Dem Namen der Methode werden hier zwei Schlüsselwörter `public void` vorangestellt.   
`public` bedeutet, dass die Methode auch von ausserhalb aufgerufen werden kann.   
`void` (engl. Leer) bedeutet, dass die Methode keinen Wert zurückliefert.
3. Hinter jeder Methode steht das Klammerpaar `()`, was bedeutet, dass die Methode keine Parameter übergeben bekommt. Später werden wir Methoden mit Parametern kenne lernen.