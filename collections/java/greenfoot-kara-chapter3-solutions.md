---
layout: article
title: "GreenfootKara - Solutions Chapter 3"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
published: true
prettify: true
comments: true
sidebars:
- header: Articles in this Series
  body:
  - text: "Introduction"
    link: /java/greenfoot-kara-intro/
    paging: Intro
  - text: "Chapter 1: First Steps"
    link: /java/greenfoot-kara-chapter1/
    paging: 1
  - text: "Chapter 2: Program Flow"
    link: /java/greenfoot-kara-chapter2/
    paging: 2
  - text: "Chapter 3: Variables"
    link: /java/greenfoot-kara-chapter3/
    paging: 3
  - text: "Solutions Chapter 3"
    link: /java/greenfoot-kara-chapter3-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
  - text: "Chapter 4: Sokoban Game"
    link: /java/greenfoot-kara-chapter4/
    paging: 4
  - text: "Chapter 5: Methods"
    link: /java/greenfoot-kara-chapter5/
    paging: 5
- header: Downloads
  body:
  - text: scenarios-chapter-3-solutions.zip
    link: http://swisseduc.ch/informatik/karatojava/greenfootkara/classes/scenarios-chapter-3-solutions.zip
    icon-css: fa fa-fw fa-archive
  - text: chapter-3-variables-solutions.docx
    link: http://swisseduc.ch/informatik/karatojava/greenfootkara/docs/en/chapter-3-variables-solutions.docx
    icon-css: fa fa-fw fa-file-text
- header: Languages
  body:
  - text: Deutsch
    link: /java/greenfoot-kara-intro-de/
    icon-css: fa fa-fw fa-globe
---

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 3.01

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


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 3.02

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


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 3.03

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


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 3.04

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

		System.out.println("The longest tree line is " + longestRow
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
		while (i < currentRow) {
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


#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 3.05

See the solution scenario.

