+++
title = "Solutions"
date = 2012-10-03
updated = 2014-03-08
prettify = true
# comments = true
commentsIdentifier = "/library/greenfoot-kara/chapter1-solutions/"
aliases = [ 
  "/library/greenfoot-kara/chapter1-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Solutions"

[[sidebars]]
header = "Downloads"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-archive-o\"></i> scenarios-chapter-1-solutions.zip"
link = "https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-1-solutions.zip"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Page as Word File"
link = "/library/convert-web-page-to-word/"
+++

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 1.02

<div class="alpha-list hidden"></div>

1. What does the move() method do?   
  **Kara moves ahead one field.**
2. Place two Karas in your world and make sure that they face each other. Which method do you need?   
  **turnLeft() or turnRight()**
3. Try the other methods. There are two types of methods. What are those types and what do they do?   
  **Method with void: Perform an action.**   
  **Methods with boolean: Open a window containing the methods result.**


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 1.03

<div class="alpha-list hidden"></div>

1. Right-click on the Kara object and call the `onLeaf()` method. Does it always return `false`? If no, in which situations does it return `true`?   
  **If Kara is on a leaf this method returns true.**
2. Add an additional tree to the world. What method can you call to check if Kara is facing a tree?   
  **treeFront()**
3. What happens if you call Kara's `move()` method when Kara is facing a tree?   
  **Kara complains that he can't move.**


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 1.04

What are the values of the coordinates and rotation in the following situations?

1. **x: 0, y: 0, rotation: 0**

2. **x: 1, y: 2, rotation: 180**


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 1.05

<div class="alpha-list hidden"></div>

1. Place a `MyKara` object into your world. What new method do you have available?   
  **act()**
2. What does this method do?   
  **Move, turn right, move**
3. What happens if you press the **Act button** in the Greenfoot controls?   
  **The same as before, the act() Method is called**
4. Click on the **Run button**. What is happening? Try using the slider.   
  **act() is called again and again until the pause button is clicked**
5. What methods can be found when you *right-click* on *MyKara* and then *inherited from Kara*? Can you use them?   
  **All methods from Kara. They can directly be used by MyKara**


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 1.06

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	
	public void act() {
		move();
        putLeaf();
        move();

		stop();
	}
}
</pre>

***

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 1.07

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	
	public void act() {
		move();

		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
		turnLeft();

		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
		turnLeft();

		move();

		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
		turnLeft();

		removeLeaf();

		stop();
	}
}
</pre>

#### Notes

1. The **comments** in the code have been omitted (= the text that is in the Greenfoot editor either gray or blue).
Comments in the source code are written as additional information. The comments are for people and are ignored by the computer. There are **three ways** to post comments in the code:
  1. With a double slash `//` (after the double slash the rest of the line is a comment)
  2. Longer comments over several lines are enclosed between `/*` and `*/`.
  3. Comments on methods and classes are written between `/**` and `*/`.
2.	The method `stop()` makes sure the simulation stops after the `act()`-Method even when the Run-button was pressed.


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 1.08

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	public void act() {
		move();
		goAroundTree();
		goAroundTree();
		move();
		goAroundTree();
		removeLeaf();

		stop();
	}

	public void goAroundTree() {
		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
		turnLeft();
	}
}
</pre>

#### Notes

1. For greater clarity and to avoid that we need to write the same code three times, we have developed a new method `goAroundTree()`.
2. The name of the methods here are preceded by two keywords: `public void`.
`public` means that the method can be called from outside.
`void` means that the method returns no value.
3. Behind each method is a pair of parentheses `()` which means that the method gets passed no parameters. Later we will learn how to write methods with parameters.
