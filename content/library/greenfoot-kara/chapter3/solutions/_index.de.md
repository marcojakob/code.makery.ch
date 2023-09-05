+++
title = "Lösungen"
date = 2012-10-03
updated = 2014-03-08
prettify = true
# comments = true
commentsIdentifier = "/library/greenfoot-kara/de/chapter3-solutions/"
aliases = [ 
  "/library/greenfoot-kara/de/chapter3-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Lösungen"

[[sidebars]]
header = "Downloads"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-archive-o\"></i> scenarios-chapter-3-solutions.zip"
link = "https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-3-solutions.zip"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Seite als Word-Datei"
link = "/de/library/convert-web-page-to-word/"
+++

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

