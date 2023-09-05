+++
title = "Solutions"
date = 2014-07-10
prettify = true
# comments = true
commentsIdentifier = "/library/greenfoot-kara/fr/chapter5-solutions/"
aliases = [ 
  "/library/greenfoot-kara/fr/chapter5-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Solutions"

[[sidebars]]
header = "Downloads"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-archive-o\"></i> scenarios-chapter-5-solutions.zip"
link = "https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-5-solutions.zip"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Page en fichier Word"
link = "/library/convert-web-page-to-word/"
+++

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 5.01: Baking a Cake

<pre class="prettyprint lang-java">
public class MyKaraIO extends KaraIO {
	
	public void act() {
		drawRectangle(21, 4);
		
		stop();
	}

	public void drawRectangle(int width, int height) {
		int i = 0;
		while (i &lt; height) {
			putLeafs(width);
			turnAround();
			multiMove(width);

			// Go to next line.
			turnRight();
			move();
			turnRight();

			i = i + 1;
		}
	}

	public void putLeafs(int count) {
		int i = 0;
		while (i &lt; count) {
			putLeaf();
			move();
			i = i + 1;
		}
	}

	public void multiMove(int steps) {
		int i = 0;
		while (i &lt; steps) {
			move();
			i = i + 1;
		}
	}

	public void turnAround() {
		turnLeft();
		turnLeft();
	}
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 5.02: Candles on Cake

<pre class="prettyprint lang-java">
public class MyKaraIO extends KaraIO {
	
	public void act() {
		drawRectangle(21, 4);
		drawCandles(10);
		
		stop();
	}

	public void drawCandles(int count) {
		int i = 0;
		while (i &lt; count) {
			move();
			turnLeft();
			putLeafs(3);
			turnAround();
			multiMove(3);
			turnLeft();
			move();
			i = i + 1;
		}
	}

	public void drawRectangle(int width, int height) {
		// ...
	}

	public void putLeafs(int count) {
		// ...
	}

	public void multiMove(int steps) {
		// ...
	}

	public void turnAround() {
		// ...
	}
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 5.03: Candles for Age

<pre class="prettyprint lang-java">
public class MyKaraIO extends KaraIO {

	public void act() {
		drawRectangle(21, 4);

		int age = intInput("How old is your grandmother?");
		drawCandles(age / 10);
		
		stop();
	}

	public void drawCandles(int count) {
		// ...
	}

	public void drawRectangle(int width, int height) {
		// ...
	}

	public void putLeafs(int count) {
		// ...
	}

	public void multiMove(int steps) {
		// ...
	}

	public void turnAround() {
		// ...
	}
}
</pre>


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 5.04: Layered Cake

<pre class="prettyprint lang-java">
public class MyKaraIO extends KaraIO {

	public void act() {
		int age = intInput("How old is your grandmother?");
		drawLayers(age / 10 - 5);
		
		stop();
	}

	public void drawLayers(int layers) {
		int currentWidth = 21;

		int i = 0;
		while (i &lt; layers) {
			drawRectangle(currentWidth, 2);
			move();
			move();

			currentWidth = currentWidth - 4;
			i = i + 1;
		}
	}

	public void drawRectangle(int width, int height) {
		// ...
	}

	public void putLeafs(int count) {
		// ...
	}

	public void multiMove(int steps) {
		// ...
	}

	public void turnAround() {
		// ...
	}
}
</pre>


