---
layout: article
title: "Hello Dart - Part 2: Loops"
date: 2015-05-01 00:00
slug: hello-dart/part2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part2.md
description: "Learn to program loops with Dart. With logical operators we control the program flow."
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
    active: true
  - text: "Part 3: Conditionals"
    link: /library/hello-dart/part3/
    paging: 3
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
  - text: "Solutions Part 2"
    link: /library/hello-dart/part2-solutions/
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
  part: part2
  active: en
---

In the last part, we learned how we can give our player a set of instructions. Instead of listing each and every statement, we can also repeat instructions. In programming such repetitions are called **loops**.

As a first example of a loop, we will do the following:


#### <i class="fa fa-rocket mg-t"></i> TASK 2.01: Loop

![Loop](/assets/library/hello-dart/part2/loop.png)

The player should move forward until he can't move any more.

In Dart we can express this as follows:

<pre class="prettyprint lang-dart">
while (canMove()) {
  move();
}
</pre>

The `while` loop is repeated as long as the condition in the paranthesis returns `true`. Once the player arrives at the edge, the sensor function `canMove()` will return `false` and the player stops moving.

Test the program by adding the above `while` loop to the `start()` function of `scenario2.01`.


## Logical Operators

Our sensor functions (see [Introduction](/library/hello-dart/#sensors)) all respond with either `true` or `false` when you call them. A data type that can either be true or false is called a [Boolean](http://en.wikipedia.org/wiki/Boolean_data_type).

Boolean values can also be combined or changed by means of logical operators. The following table shows the three most important logical operators in Dart:

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
      <td>`treeFront() && onStar()`</td>
      <td>Is true if both expressions are true, that means if the player is facing a tree **and** is on a star.</td>
    </tr>
    <tr>
      <td>`||`</td>
      <td>or</td>
      <td>`treeFront() || onStar()`</td>
      <td>Is true if either the first **or** the second expression, or both are true.</td>
    </tr>
    <tr>
      <td>`!`</td>
      <td>not</td>
      <td>`!treeFront()`</td>
      <td>Changes the expression from true to false and vice versa. This expression would return true if the player is **not** facing a tree.</td>
    </tr>
  </tbody>
</table>

An example in Dart could look like this:

<pre class="prettyprint lang-dart">
if (treeLeft() && onStar()) {
  // Do something ...
}
</pre>

or combined:

<pre class="prettyprint lang-dart">
if (treeLeft() && !treeRight()) {
  // Do something ...
}
</pre>


#### <i class="fa fa-rocket mg-t"></i> TASK 2.02: Loop Star

![Loop Star](/assets/library/hello-dart/part2/loop-star.png)

Open the `scenario 2.02` and program the player with a loop so that he runs forward until he stands on the star.


#### <i class="fa fa-rocket mg-t"></i> TASK 2.03: Leaving the Tunnel

![Leaving the Tunnel](/assets/library/hello-dart/part2/leaving-the-tunnel.png)

Our player is in a tunnel and wants to get out. Write a program for `scenario2.03` where the player walks out of the tunnel and stops as soon as there is no tree on one of the sides.

At the end he should also put down a star.


#### <i class="fa fa-rocket mg-t"></i> TASK 2.04: Afraid of Tunnel

![Afraid of Tunnel](/assets/library/hello-dart/part2/afraid-of-tunnel.png)

The player is afraid of tunnels. He should look on every field if it is a tunnel entrance (that is, whether it has trees on both sides). If this is the case, then he should stop and say that he is afraid in a speech bubble.


#### <i class="fa fa-rocket mg-t"></i> TASK 2.05: Climbing Up

![Climbing Up](/assets/library/hello-dart/part2/climbing-up.png)

The player must climb arbitraily long stairs.

Write a function called `oneStepUp()` to make the player climb a single step. You'll need to figure out how the player knows if he still has to climb a step or if he's arrived at the top.



## For Loops

In addition to the `while` loop there is an other very important type of loop, the `for` loop.

<pre class="prettyprint lang-dart">
for (var i = 0; i &lt; 4; i++) {
  move();
}
</pre>

In this example, a counter `i` is used. The counter is first set to `0`. At every loop cycle the program checks if `i` is smaller than `4` and increments `i` by one. This means that the `move()` statement is executed four times in this loop.

You will encounter the `for` loop a lot, in various forms. For the moment it is enough for us to know that it exists.


## What's next?

&rarr; Continue with [Part 3: Conditionals](/library/hello-dart/part3/)


***

*Credits*<br>
<em class="small">
  [Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) images by Daniel Cook (Lostgarden.com), published under [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) improved the "Planet Cute" images and was so kind to let me use them. The images were optimized with the great [TexturePacker](https://www.codeandweb.com/texturepacker).<br>
Some exercises in `Hello Dart`were inspired by [Kara](http://www.swisseduc.ch/compscience/karatojava/javakara/). Kara was developed by Jürg Nievergelt, Werner Hartmann, Raimond Reichert et. al.
</em>