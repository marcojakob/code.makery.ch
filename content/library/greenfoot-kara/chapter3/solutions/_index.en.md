+++
title = "Solutions"
date = 2012-10-03
updated = 2014-03-08
prettify = true
# comments = true
commentsIdentifier = "/library/greenfoot-kara/chapter3-solutions/"
aliases = [ 
  "/library/greenfoot-kara/chapter3-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Solutions"

[[sidebars]]
header = "Downloads"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-archive-o\"></i> scenarios-chapter-3-solutions.zip"
link = "https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-3-solutions.zip"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Page as Word File"
link = "/library/convert-web-page-to-word/"
+++

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


***

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


***

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


***

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


#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 3.05

See the solution scenario in the downloads section.

