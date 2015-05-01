---
layout: article
title: "Hello Dart - Part 3: Conditionals"
date: 2015-05-01 00:00
slug: hello-dart/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part3.md
description: "Learn to control the program flow with conditional statements. Numerous Hello Dart exercises will help you understand it."
image: /assets/library/hello-dart/hello-dart.png
published: true
prettify: true
comments: true
sidebars:
- header: Articles in this Series
  body:
  - text: "Introduction"
    link: /library/hello-dart/
    paging: Intro
  - text: "Installation"
    link: /library/hello-dart/install/
    icon-css: fa fa-fw fa-cog
    paging: <i class="fa fa-cog"></i>
  - text: "Part 1: First Steps"
    link: /library/hello-dart/part1/
    paging: 1
  - text: "Part 2: Loops"
    link: /library/hello-dart/part2/
    paging: 2
  - text: "Part 3: Conditionals"
    link: /library/hello-dart/part3/
    paging: 3
    active: true
  - text: "Part 4: Variables"
    link: /library/hello-dart/part4/
    paging: 4
  - text: "Part 5: Functions"
    link: /library/hello-dart/part5/
    paging: 5
  - text: "Next Steps"
    link: /library/hello-dart/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Solutions
  body:
  - text: "Solutions Part 3"
    link: /library/hello-dart/part3-solutions/
    icon-css: fa fa-fw fa-check-square-o
- header: Links
  body:
  - text: Page as Word File
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-word-o
languages:
  header: Languages
  collection: library
  item: hello-dart
  part: part3
  active: en
---

In addition to loops there is a second structure, which is very important to control the program flow. With **conditional statements** we can specify if a block of statements should be executed or not.

<pre class="prettyprint lang-dart">
if (treeFront()) {  // Condition.
  turnLeft();       // Block 1, executed if the condition is true.
} else {
  move();           // Block 2, executed if the condition is false.
}
</pre>

<div class="alert alert-info">
  **Note:** The `else` part (block 2) may be omitted if it is not needed.
</div>


#### <i class="fa fa-rocket mg-t"></i> TASK 3.01: Conditionals

Describe in words the effect of the following code examples. Then test them in `scenario3.01`. For the conditional statements to be executed more than once you should put them into the loop that is already in the scenario.


##### a.

<pre class="prettyprint lang-dart">
if (onStar()) {
  removeStar();
}
move();
</pre>


##### b.

<pre class="prettyprint lang-dart">
if (onStar()) {
  removeLeaf();
} else {
  putLeaf();
}
move();
</pre>


##### c.

Conditional statements may be nested:

<pre class="prettyprint lang-dart">
if (treeLeft()) {
  if (onStar()) {
    removeStar();
  }
}
move();
</pre>

#### <i class="fa fa-rocket mg-t"></i> TASK 3.02: Star Track

![Star Track](/assets/library/hello-dart/part3/star-track.png)

The player is to move forward and always put a star where there is none. Make sure that he also puts a star on the last field.

Open the `scenario3.02` and write the program.


#### <i class="fa fa-rocket mg-t"></i> TASK 3.03: Star at Tree

![Star at Tree](/assets/library/hello-dart/part3/star-at-tree.png)

Let the player go straight ahaid and put a leaf anywhere where there is a tree on his left or right or on both sides.

Note that you can use the *logical operators* `&&`, `||`, and `!` in the same way we did in loops.


#### <i class="fa fa-rocket mg-t"></i> TASK 3.04: Around Tree II

![Around Tree A](/assets/library/hello-dart/part3/around-tree-a.png)

How to walk around trees we already know from the first part. We had solved it like this:

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    move();
    goAroundTree();
    goAroundTree();
    move();
    goAroundTree();
    removeStar();
  }

  goAroundTree() {
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

This program works very well as long as the world and trees looks exactly the same. Once a tree is moved, like in the following example, the program will fail.

![Around Tree B](/assets/library/hello-dart/part3/around-tree-b.png)

The `scenario3.04` has three variants. Open this scenario and in the `main()` function change the text `scenario-a.txt` to `scenario-b.txt`. Now test what happens when you start the program in the alternate world.

By now you know various ways you can respond to circumstances with the help of sensor methods. Try to change the program so that your player reaches the star in all worlds with the following properties:

* The star is always right in front of the player. He must walk around the trees to get to the star.
* There are never two trees standing next to each other.


#### <i class="fa fa-rocket mg-t"></i> TASK 3.05: Round Trip

![Round Trip](/assets/library/hello-dart/part3/round-trip.png)

The player makes a round trip in search of a star (and removes it). Each field in the tour has exactly two empty adjacent fields. One is always behind the player which is the field the player came from.

Open the `scenario3.05` and write a program for it. Test your program in **all three worlds**, `scenario-a.txt`, `scenario-b.txt`, and `scenario-c.txt`.

*Hint: First write a loop that stops when the player is on a star. Then imagine what must happen after every step through the loop.*


#### <i class="fa fa-rocket mg-t"></i> TASK 3.06: Around Tree III

![Around Tree III](/assets/library/hello-dart/part3/around-tree-iii.png)

This is a similar exercise as task 3.04: The player has to find the star that lies before him. Now, however, any number of trees may stand next to each other.

Load the `scenario3.06` und improve the `goAroundTree()` method so that the player can go around multiple trees. Test your program in all the available worlds (a, b, c, and d).


#### <i class="fa fa-rocket mg-t"></i> TASK 3.07 (difficult): Follow the Trail

![Rundgang](/assets/library/hello-dart/part3/follow-the-trail.png)

The player has to follow a trail of stars and pick them up. Before the trees he should stop.

*Important: Write new methods for specific parts of your program. This greatly improves the clarity of your program.*


#### <i class="fa fa-rocket mg-t"></i> TASK 3.08 (difficult): Guard

![Guard](/assets/library/hello-dart/part3/guard.png)

The player is to guard a forest full of stars. He should walk along the outer edge of the forest.

Start with a single walk around the forest. Then you can let the player make multiple rounds with an infinite loop `while (true)`.

Also test your program in the second world that is available.


## What's next?

&rarr; Continue with [Part 4: Variables](/library/hello-dart/part4/)


***

*Credits*<br>
<em class="small">
  [Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) images by Daniel Cook (Lostgarden.com), published under [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) improved the "Planet Cute" images and was so kind to let me use them.<br>
Some exercises in `Hello Dart`were inspired by [Kara](http://www.swisseduc.ch/compscience/karatojava/javakara/). Kara was developed by Jürg Nievergelt, Werner Hartmann, Raimond Reichert et. al.
</em>