---
layout: article
title: "GreenfootKara - Solutions Chapter 4"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
published: true
prettify: true
comments: true
sidebars:
- header: Articles in this Series
  paging: true
  body:
  - text: "Introduction"
    link: /java/greenfoot-kara-intro
    intro: true
  - text: "Chapter 1: First Steps"
    link: /java/greenfoot-kara-chapter1
  - text: "Chapter 2: Program Flow"
    link: /java/greenfoot-kara-chapter2
  - text: "Chapter 3: Variables"
    link: /java/greenfoot-kara-chapter3
  - text: "Chapter 4: Sokoban Game"
    link: /java/greenfoot-kara-chapter4
  - text: "Solutions Chapter 4"
    link: /java/greenfoot-kara-chapter4-solutions
    icon-css: fa fa-fw fa-check-square-o
    active: true
    paging-exclude: true
  - text: "Chapter 5: Methods"
    link: /java/greenfoot-kara-chapter5
- header: Downloads
  body:
  - text: scenarios-chapter-4-solutions.zip
    link: http://swisseduc.ch/informatik/karatojava/greenfootkara/classes/scenarios-chapter-4-solutions.zip
    icon-css: fa fa-fw fa-archive
  - text: chapter-4-sokoban-game-solutions.docx
    link: http://swisseduc.ch/informatik/karatojava/greenfootkara/docs/en/chapter-4-sokoban-game-solutions.docx
    icon-css: fa fa-fw fa-file-text
- header: Languages
  body:
  - text: Deutsch
    link: /java/greenfoot-kara-intro-de
    icon-css: fa fa-fw fa-globe
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