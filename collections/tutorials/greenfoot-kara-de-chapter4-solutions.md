---
layout: article
title: "GreenfootKara - Lösungen Kapitel 4"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara/de/chapter4-solutions
published: true
prettify: true
comments: false
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /tutorials/greenfoot-kara/de/
    paging: Intro
  - text: "Kapitel 1: Erste Schritte"
    link: /tutorials/greenfoot-kara/de/chapter1/
    paging: 1
  - text: "Kapitel 2: Programmfluss"
    link: /tutorials/greenfoot-kara/de/chapter2/
    paging: 2
  - text: "Kapitel 3: Variablen"
    link: /tutorials/greenfoot-kara/de/chapter3/
    paging: 3
  - text: "Kapitel 4: Sokoban Spiel"
    link: /tutorials/greenfoot-kara/de/chapter4/
    paging: 4
  - text: "Lösungen Kapitel 4"
    link: /tutorials/greenfoot-kara/de/chapter4-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
  - text: "Kapitel 5: Methoden"
    link: /tutorials/greenfoot-kara/de/chapter5/
    paging: 5
- header: Downloads
  body:
  - text: scenarios-chapter-4-solutions.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/2.1.0/scenarios-chapter-4-solutions.zip
    icon-css: fa fa-fw fa-archive
  - text: Seite als Word-Datei
    link: /tutorials/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-text
- header: Sprachen
  body:
  - text: English
    link: /tutorials/greenfoot-kara/chapter4-solutions/
    icon-css: fa fa-fw fa-globe
  - text: Deutsch
    link: /tutorials/greenfoot-kara/de/chapter4-solutions/
    icon-css: fa fa-fw fa-globe
    active: true
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