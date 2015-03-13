---
layout: article
title: "GreenfootKara - Lösungen Kapitel 4"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara/de/chapter4-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-de-chapter4-solutions.md
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
  - text: "Lösungen Kapitel 4"
    link: /library/greenfoot-kara/de/chapter4-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
- header: Downloads
  body:
  - text: scenarios-chapter-4-solutions.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-4-solutions.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
languages: 
  header: Sprachen
  collection: library
  item: greenfoot-kara
  part: chapter4-solutions
  active: de
---

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 4.01 bis 4.09

<pre class="prettyprint lang-java">
public class MyKara extends KaraSokoban {
	
    int counter = 0;

	public void act() {
		String key = getKey();

		if (key.equals("right")) {
			setDirectionRight();
			tryToMove();
		}

		if (key.equals("down")) {
			setDirectionDown();
			tryToMove();
		}

		if (key.equals("left")) {
			setDirectionLeft();
			tryToMove();
		}

		if (key.equals("up")) {
			setDirectionUp();
			tryToMove();
		}
	}

	/**
	 * Kara macht einen Schritt. Diese Methode schaut zuerst, ob sich Kara
	 * bewegen kann oder ob er zuerst noch einen Pilz schieben muss.
	 */
	public void tryToMove() {
		if (!treeFront()) {
			if (mushroomFront()) {
				if (canPushMushroom()) {
					move();
					counter++;
					setNumberOfMoves(counter);
				}
			} else {
				move();
				counter++;
				setNumberOfMoves(counter);
			}

			if (testLevelComplete()) {
				saveHighscore();
				levelComplete();
			}
		}
	}

	/**
	 * Diese Methode behandelt das Speichern der Highscore.
	 */
	public void saveHighscore() {
		// Testen, ob in den Top 3.
		if (isHighscoreTop3(counter)) {
			// Ist in Top 3 --> Hinzufügen zur Highscore.
			addHighscoreEntry(counter);
		}
	}
}
</pre>