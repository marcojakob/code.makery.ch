---
layout: article
title: "GreenfootKara - Chapter 2: Program Flow"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara/chapter2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-en-chapter2.md
published: true
prettify: true
comments: true
sidebars:
- header: Articles in this Series
  body:
  - text: "Introduction"
    link: /library/greenfoot-kara/
    paging: Intro
  - text: "Chapter 1: First Steps"
    link: /library/greenfoot-kara/chapter1/
    paging: 1
  - text: "Chapter 2: Program Flow"
    link: /library/greenfoot-kara/chapter2/
    paging: 2
    active: true
  - text: "Solutions Chapter 2"
    link: /library/greenfoot-kara/chapter2-solutions/
    icon-css: fa fa-fw fa-check-square-o
  - text: "Chapter 3: Variables"
    link: /library/greenfoot-kara/chapter3/
    paging: 3
  - text: "Chapter 4: Sokoban Game"
    link: /library/greenfoot-kara/chapter4/
    paging: 4
  - text: "Chapter 5: Methods"
    link: /library/greenfoot-kara/chapter5/
    paging: 5
- header: Downloads
  body:
  - text: scenarios-chapter-2.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/2.1.0/scenarios-chapter-2.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Page as Word File
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-word-o
- header: Languages
  body:
  - text: English
    link: /library/greenfoot-kara/chapter2/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Deutsch
    link: /library/greenfoot-kara/de/chapter2/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/greenfoot-kara/fr/chapter2/
    icon-css: fa fa-fw fa-globe
---

## The Flowchart

In order to solve difficult tasks in programming, it is often helpful to outline the program flow in a flowchart. The following symbols are used in flowcharts:


<table class="table table-bordered text-center">
  <tbody>
    <tr>
      <td><img src="/assets/library/greenfoot-kara/chapter2/flowchart-symbol1.png"></td>
      <td>Start / Stop</td>
    </tr>
    <tr>
      <td><img src="/assets/library/greenfoot-kara/chapter2/flowchart-symbol2.png"></td>
      <td>Activity</td>
    </tr>
    <tr>
      <td><img src="/assets/library/greenfoot-kara/chapter2/flowchart-symbol3.png"></td>
      <td>Decision / Condition</td>
    </tr>
  </tbody>
</table>


#### <i class="fa fa-rocket"></i> TASK 2.01

Complete the flowchart so that Kara reaches the leaf in all worlds with the following properties:

![Task 1 - World 1](/assets/library/greenfoot-kara/chapter2/task01-world1.png)

![Task 1 - World 2](/assets/library/greenfoot-kara/chapter2/task01-world2.png)

* The leaf is always right in front of Kara - Kara only needs to walk around the trees.
* There are never two trees standing side by side.


![Task 1 - Flowchart](/assets/library/greenfoot-kara/chapter2/task01-flowchart.png) 


#### <i class="fa fa-rocket"></i> TASK 2.02

Draw an extended diagram so that Kara picks up the leaf at the end if he finds it. Here is an overview of all available methods of Kara as a help:

![Kara Methods](/assets/library/greenfoot-kara/chapter2/task02-kara-methods.png)


***

## Conditional Statements in Java

<pre class="prettyprint lang-java">
if (treeFront()) {      // Condition.
	turnLeft();         // Block 1, executed if the condition is true.
} else {
	move();             // Block 2, executed if the condition is false.
}
</pre>

**Note: The else part (block 2) may be omitted if it is not needed.**


#### <i class="fa fa-rocket"></i> TASK 2.03

<div class="alpha-list hidden"></div>

1. Describe with words what the following code does.
2. Sketch it as a flowchart.

<pre class="prettyprint lang-java">
if (onLeaf()) {
	removeLeaf();
} else {
	putLeaf();
}
move();
</pre>

#### <i class="fa fa-rocket"></i> TASK 2.04

<div class="alpha-list hidden"></div>

1. Describe with words what the following code does.
2. Sketch it as a flowchart.

<pre class="prettyprint lang-java">
if (onLeaf()) {
	move();
}
</pre>


#### <i class="fa fa-rocket"></i> TASK 2.05

Conditional statements can be nested. 

<div class="alpha-list hidden"></div>

1. Describe what happens when you run the program.
2. Draw a corresponding flowchart.

<pre class="prettyprint lang-java">
if (treeLeft()) {
	if (onLeaf()) {
		removeLeaf();
		move();
	} else {
		move();
	}
} else {
	move();
}
</pre>


### Implementing

#### <i class="fa fa-rocket"></i> TASK 2.06: Around Tree II

* Open the scenario **Kara 206...** from the folder **scenarios-chapter-2**. In this scenario, the method `goAroundTree()` is already programmed and part of the `act()` method is prepared.
* Program inside the `act()` method what you have drawn in *Task 2* as a flowchart. 
* In this scenario, you have **several worlds** (a, b and c).
* Your program should work in any of those worlds **without error messages**.


#### <i class="fa fa-rocket"></i> TASK 2.07: Nested Conditions

* Open the scenario **Kara 207...** in Greenfoot and write the program as outlined in task *Task 5*.
* Modify the program so that Kara only picks up the leaf if *no* tree is on his side.


***

## Logical Operations

Our Kara can already do more than just execute simple commands. Kara will react differently based on a test. It should also be possible for Kara to react simultaneously to two or more tests.

The following table shows the three main logical operators in Java:

<table class="table">
  <thead>
    <tr>
      <th>Operator</th>
      <th>Description</th>
      <th>Example</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`&&`</td>
      <td>and</td>
      <td>`treeFront() && onLeaf()`</td>
      <td>Is only satisfied (true) if both statements are true, i.e. if Kara is facing a tree **and** is on a leaf.</td>
    </tr>
    <tr>
      <td>`||`</td>
      <td>or</td>
      <td>`treeFront() || onLeaf()`</td>
      <td>Is satisfied (true) if either one **or** the other or both statements are true.</td>
    </tr>
    <tr>
      <td>`!`</td>
      <td>not</td>
      <td>`!treeFront()`</td>
      <td>Changes an expression of true to false and vice versa. This statement would be satisfied (true) if Kara is **not** facing a tree.</td>
    </tr>
  </tbody>
</table>

An example in Java would look like this:

<pre class="prettyprint lang-java">
if (treeLeft() && onLeaf()) {
	// Do something ...
}
</pre>

or combined:

<pre class="prettyprint lang-java">
if (treeLeft() && !treeRight()) {
	// Do something ...
}
</pre>


#### <i class="fa fa-rocket"></i> TASK 2.08: Afraid of Tunnel

![Task 8](/assets/library/greenfoot-kara/chapter2/task08.png)

Kara is a little afraid of tunnels. Kara should check on every field whether it is a tunnel entrance (i.e. whether it has trees on both sides). If so, Kara instantly drops a leaf because of the shock.

Load the scenario **Kara 208...**, write the program and test it with all three worlds.


#### <i class="fa fa-rocket"></i> TASK 2.09: Leaf at Tree

![Task 9](/assets/library/greenfoot-kara/chapter2/task09.png)

Now let Kara go straight and put a leaf anywhere where there is a tree on its left or right or on both sides.

Load the scenario **Kara 209...** and write the program.


#### <i class="fa fa-rocket"></i> TASK 2.10: Put Leaf Track

![Task 10](/assets/library/greenfoot-kara/chapter2/task10.png)

Kara is running straight ahead and lays a leaf anywhere where there is none. When he reaches the tree he will do nothing (even if the Act-button is pressed again).

Load the scenario **Kara 210...**, write the program and test it.


#### <i class="fa fa-rocket"></i> TASK 2.11: Round Trip



Kara must find the leaf on this round trip. Each field in the tour has exactly two empty neighboring fields. One empty field always lays behind Kara which is the field he came from. 

Load the scenario **Kara 211...** and write a program for it. Test your program in all three worlds.

*Tip: Imagine what must be done each time the Act button is pressed. You can draw a flowchart as a help to find the solution.*


#### <i class="fa fa-rocket"></i> TASK 2.12 (difficult): Kara Plays Pacman

![Task 12](/assets/library/greenfoot-kara/chapter2/task12.png)

Kara plays Pacman: Kara is on the first of a long trail of leafs, ending in front of a tree. Kara picks up all leafs and stops in front of the trees.

As a better overview, write some parts of the program in their own methods.


***

## Loops

Kara can now react to rules set by us in different situations. But Kara is not yet capable of repeating a specified set of instructions. To execute an instruction block multiple times, loops are used.

As an example, we want to do the following: 

*Kara moves forward until he hits a tree.*

![Loop Example](/assets/library/greenfoot-kara/chapter2/loop-example-world.png)

In the flow chart you can see that `move()` is executed repeatedly, as long as no tree stands in front of Kara.

![Loop Example - Flowchart](/assets/library/greenfoot-kara/chapter2/loop-example-flowchart.png)

And this is the notation in Java:

<pre class="prettyprint lang-java">
while (!treeFront()) {
	move();
}
</pre>


#### <i class="fa fa-rocket"></i> TASK 2.13

![Task 13](/assets/library/greenfoot-kara/chapter2/task13.png)

Kara stands in front of a tunnel.

Describe what each of the following loops does, and how many steps Kara takes.

<table class="table">
  <thead>
    <tr>
      <th>#</th>
      <th>Code</th>
      <th>Description</th>
      <th>Steps</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>a.</td>
      <td><pre>while (treeLeft()) {
  move();
}</pre></td>
      <td>Move as long as ther is a tree on the left.</td>
      <td>4</td>
    </tr>
    
    <tr>
      <td>b.</td>
      <td><pre>while (treeRight()) {
  move();
}</pre></td>
      <td>?</td>
      <td>?</td>
    </tr>
    
    <tr>
      <td>c.</td>
      <td><pre>while (treeLeft() || treeRight()) {
  move();
}</pre></td>
      <td>?</td>
      <td>?</td>
    </tr>
    
    <tr>
      <td>d.</td>
      <td><pre>if (treeLeft()) {
  move();
} while (treeLeft() && treeRight()) {
  move();
}</pre></td>
      <td>?</td>
      <td>?</td>
    </tr>

    <tr>
      <td>e.</td>
      <td><pre>while (!treeFront) {
  if (treeLeft()) {
    move();
  }
}</pre></td>
      <td>?</td>
      <td>?</td>
    </tr>
    
  </tbody>
</table>


#### <i class="fa fa-rocket"></i> TASK 2.14: Around Tree III

![Task 14](/assets/library/greenfoot-kara/chapter2/task14.png)

This is a similar exercise as in task 6: Kara must find a leaf that lies ahead of him. But now there can be any number of trees in a row.

* Load the scenario **Kara 214...** and improve the method `goAroundTree()` so that Kara can walk around several trees. Test your program in all available worlds.
* Modify `act()` so that you can just press the act-button once. Kara should then automatically run around the trees until he reached the leaf. In the end he should eat it again.


#### <i class="fa fa-rocket"></i> TASK 2.15: Climbing Up

![Task 15](/assets/library/greenfoot-kara/chapter2/task15.png)

Kara shall climb arbitraryily long stairs.

Write a method `oneStepUp()` to make Kara climb a single step. You need to figure out how Kara knows if he still has to climb a step or if he's reached the top.

*Note: The Solution should work with pressing on the Act-button only once.*


#### <i class="fa fa-rocket"></i> TASK 2.16 (difficult): Kara as Guard

![Task 15](/assets/library/greenfoot-kara/chapter2/task15.png)

Kara wants to guard the forest. He is endlessly walking along outside the forest. 

To help yourself, you can draw a flowchart.

*Note: For an infinite loop, we can press the Run-button.*


*** 

***Credits:*** *[Ideas and concepts of Kara](http://www.swisseduc.ch/informatik/karatojava/) were developed by Jürg Nievergelt, Werner Hartmann, Raimond Reichert et al. Some Kara exercises are based on [material by Horst Gierhardt](http://www.swisseduc.ch/informatik/karatojava/javakara/material/).*

***

## What's Next?

Continue with [Chapter 3: Variables](/library/greenfoot-kara/chapter3/)






