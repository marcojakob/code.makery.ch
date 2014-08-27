---
layout: article
title: "GreenfootKara - Solutions Chapitre 4"
date: 2014-07-10 00:00
slug: greenfoot-kara/fr/chapter4-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-fr-chapter4-solutions.md
published: true
prettify: true
comments: true
sidebars:
- header: Articles de cette Série
  body:
  - text: "Introduction"
    link: /library/greenfoot-kara/fr/
    paging: Intro
  - text: "Chapitre 1: Premiers Pas"
    link: /library/greenfoot-kara/fr/chapter1/
    paging: 1
  - text: "Chapitre 2: Ordinogramme"
    link: /library/greenfoot-kara/fr/chapter2/
    paging: 2
  - text: "Chapitre 3: Variables"
    link: /library/greenfoot-kara/fr/chapter3/
    paging: 3
  - text: "Chapitre 4: Jeu Sokoban"
    link: /library/greenfoot-kara/fr/chapter4/
    paging: 4
  - text: "Solutions Chapitre 4"
    link: /library/greenfoot-kara/fr/chapter4-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
  - text: "Chapitre 5: Méthodes"
    link: /library/greenfoot-kara/fr/chapter5/
    paging: 5
- header: Downloads
  body:
  - text: scenarios-chapter-4-solutions.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/2.1.0/scenarios-chapter-4-solutions.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Page as Word File
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-word-o
- header: Languages
  body:
  - text: English
    link: /library/greenfoot-kara/chapter4-solutions/
    icon-css: fa fa-fw fa-globe
  - text: Deutsch
    link: /library/greenfoot-kara/de/chapter4-solutions/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/greenfoot-kara/fr/chapter4-solutions/
    icon-css: fa fa-fw fa-globe
    active: true
---

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 4.01 to 4.09

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
	 * Kara makes one step. This method first tests if Kara can move or if he
	 * has to move a mushroom first.
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
	 * Handles the saving of the highscore.
	 */
	public void saveHighscore() {
		// Test if it is in the top 3
		if (isHighscoreTop3(counter)) {
			// Is in top 3 --> add it
			addHighscoreEntry(counter);
		}
	}
}
</pre>