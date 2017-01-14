---
layout: article
title: "GreenfootKara - Solutions Chapitre 3"
date: 2014-07-10 00:00
slug: greenfoot-kara/fr/chapter3-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-fr-chapter3-solutions.md
published: true
prettify: true
comments: true
sidebars:
- header: Articles de cette Série
  body:
  - text: "Introduction"
    link: /library/greenfoot-kara/fr/
    paging: Intro
  - text: "Background Info"
    link: /library/greenfoot-kara/fr/background/
    icon-css: fa fa-fw fa-info
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
  - text: "Chapitre 5: Méthodes"
    link: /library/greenfoot-kara/fr/chapter5/
    paging: 5
- header: Solutions
  body:
  - text: "Solutions Chapitre 3"
    link: /library/greenfoot-kara/fr/chapter3-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
- header: Downloads
  body:
  - text: scenarios-chapter-3-solutions.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-3-solutions.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Page as Word File
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-word-o
languages: 
  header: Langues
  collection: library
  item: greenfoot-kara
  part: chapter3-solutions
  active: fr
---

#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 3.01

<pre class="prettyprint lang-java">
public void act() {
	int count = 0;

	while (!treeFront()) {
		move();

		if (onLeaf()) {
			count = count + 1;
		}
	}

	System.out.println("The result is: " + count);
	
	stop();
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 3.02

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	
	boolean goingRight = true;
	
	public void act() {
		invertField();
		
		if (treeFront()) {
			if (goingRight) {
				// we are at the right border
				turnAroundRight();
			} else {
				// we are at the left border
				turnAroundLeft();
			}
		} else {
			move();
		}
	}
	
	public void turnAroundRight() {
		if (treeRight()) {
			// we are in the bottom right corner
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
			// we are in the bottom left corner
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

#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 3.03

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	
	boolean goingRight = true;
	int step = 0;
	
	public void act() {
		putLeafIfEvenStep();
		
		if (treeFront()) {
			if (goingRight) {
				// we are at the right border
				turnAroundRight();
			} else {
				// we are at the left border
				turnAroundLeft();
			}
		} else {
			move();
			step = step + 1;
		}
	}
	
	public void turnAroundRight() {
		if (treeRight()) {
			// we are in the bottom right corner
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
			// we are in the bottom left corner
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
			// even step number --> put a leaf
			putLeaf();
		}
	}
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 3.04

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

		System.out.println("The longest tree line is " + longestRow + 
				" trees long");
		stop();
	}

	public void countRow() {
		int currentRow = 0;
		turnLeft();

		while (treeRight()) {
			currentRow = currentRow + 1;
			move();
		}

		// go around tree line
		turnRight();
		move();
		move();
		turnRight();

		// go back down
		int i = 0;
		while (i &lt; currentRow) {
			move();
			i = i + 1;
		}

		turnLeft();

		// test whether the current row is longer
		if (currentRow > longestRow) {
			longestRow = currentRow;
		}
	}
}
</pre>


#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 3.05

Voir le scénario de solution dans la section "downloads".

