---
layout: article
title: "GreenfootKara - Lösungen Kapitel 3"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara/de/chapter3-solutions
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
  - text: "Kapitel 3: Variablen"
    link: /library/greenfoot-kara/de/chapter3/
    paging: 3
  - text: "Lösungen Kapitel 3"
    link: /library/greenfoot-kara/de/chapter3-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
  - text: "Kapitel 4: Sokoban Spiel"
    link: /library/greenfoot-kara/de/chapter4/
    paging: 4
  - text: "Kapitel 5: Methoden"
    link: /library/greenfoot-kara/de/chapter5/
    paging: 5
- header: Downloads
  body:
  - text: scenarios-chapter-3-solutions.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/2.1.0/scenarios-chapter-3-solutions.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
- header: Sprachen
  body:
  - text: English
    link: /library/greenfoot-kara/chapter3-solutions/
    icon-css: fa fa-fw fa-globe
  - text: Deutsch
    link: /library/greenfoot-kara/de/chapter3-solutions/
    icon-css: fa fa-fw fa-globe
    active: true
---

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 3.01

<pre class="prettyprint lang-java">
public void act() {
	int count = 0;

	while (!treeFront()) {
		move();

		if (onLeaf()) {
			count = count + 1;
		}
	}

	System.out.println("Das Resultat ist: " + count);
	
	stop();
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 3.02

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	
	boolean goingRight = true;
	
	public void act() {
		invertField();
		
		if (treeFront()) {
			if (goingRight) {
				// Wir sind am rechten Rand.
				turnAroundRight();
			} else {
				// Wir sind am linken Rand.
				turnAroundLeft();
			}
		} else {
			move();
		}
	}
	
	public void turnAroundRight() {
		if (treeRight()) {
			// Wir sind in der Ecke rechts unten.
			stop();
		} else {
			turnRight();
			move();
			turnRight();
			goingRight = false;
		}
	}
	
	public void turnAroundLeft() {
		if (treeLeft()) {
			// Wir sind in der Ecke links unten.
			stop();
		} else {
			turnLeft();
			move();
			turnLeft();
			goingRight = true;
		}
	}

	public void invertField() {
		if (onLeaf()) {
			removeLeaf();
		} else {
			putLeaf();
		}
	}
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 3.03

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	
	boolean goingRight = true;
	int step = 0;
	
	public void act() {
		putLeafIfEvenStep();
		
		if (treeFront()) {
			if (goingRight) {
				// Wir sind am rechten Rand.
				turnAroundRight();
			} else {
				// Wir sind am linken Rand.
				turnAroundLeft();
			}
		} else {
			move();
			step = step + 1;
		}
	}
	
	public void turnAroundRight() {
		if (treeRight()) {
			// Wir sind in der Ecke rechts unten.
			stop();
		} else {
			turnRight();
			move();
			turnRight();
			goingRight = false;
			step = step + 1;
		}
	}
	
	public void turnAroundLeft() {
		if (treeLeft()) {
			// Wir sind in der Ecke links unten.
			stop();
		} else {
			turnLeft();
			move();
			turnLeft();
			goingRight = true;
			step = step + 1;
		}
	}

	public void putLeafIfEvenStep() {
		if (step % 2 == 0) {
			// Gerade Schrittnummer --> Lege ein Blatt.
			putLeaf();
		}
	}
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 3.04

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	int longestRow = 0;

	public void act() {
		while (!onLeaf()) {
			if (treeFront()) {
				countRow();
			} else {
				move();
			}
		}

		System.out.println("Die Längste Baumreihe ist " + longestRow + 
				" Bäume lang");
		stop();
	}

	public void countRow() {
		int currentRow = 0;
		turnLeft();

		while (treeRight()) {
			currentRow = currentRow + 1;
			move();
		}

		// Gehe um die Baumreihe herum.
		turnRight();
		move();
		move();
		turnRight();

		// Gehe zurück nach unten.
		int i = 0;
		while (i &lt; currentRow) {
			move();
			i = i + 1;
		}

		turnLeft();

		// Teste, ob die aktuelle Zeile länger ist.
		if (currentRow > longestRow) {
			longestRow = currentRow;
		}
	}
}
</pre>


#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 3.05

Siehe Lösungsszenario unter Downloads.

