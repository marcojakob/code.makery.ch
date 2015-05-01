---
layout: article
title: "Hello Dart - Part 5: Functions"
date: 2015-05-01 00:00
slug: hello-dart/part5
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part5.md
description: "We expanding our knowledge about functions and methods in Dart. We will learn about functions with parameters and return values."
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
  - text: "Part 4: Variables"
    link: /library/hello-dart/part4/
    paging: 4
  - text: "Part 5: Functions"
    link: /library/hello-dart/part5/
    paging: 5
    active: true
  - text: "Next Steps"
    link: /library/hello-dart/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Solutions
  body:
  - text: "Solutions Part 5"
    link: /library/hello-dart/part5-solutions/
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
  part: part5
  active: en
---

<div class="row">
  <div class="col-sm-6">
    <img alt="Rotkäppchen" src="/assets/library/hello-dart/part5/red-riding-hood.jpg">
  </div>
  <div class="col-sm-6">
    <p>
      <em>Once upon a time, there was a little girl who always wore a red riding cloak. So everyone called her Little Red Riding Hood. One moning the mother said to the child: "Red Riding Hood, today is your grandmother's birthday. Bake her favorite cake, take a bottle of good old wine from the cellar, put everything in one basket, and go visit her."</em>
    </p>
    <p><strong>Help Little Red Riding Hood bake a cake! Doing this you will expand your knowledge about functions.</strong></p>
  </div>
</div>


## Functions with Parameters

With parameters, values can be passed to a function. In the following function you can specify how many steps the player should take:

<pre class="prettyprint lang-java">
multiMove(int steps) {
  int i = 0;

  while (i &lt; steps)     {
    move();

    i = i + 1;
  }
}
</pre>

To go 5 steps just put the number 5 between the parentheses:

<pre class="prettyprint lang-java">
multiMove(5);
</pre>

To invoke a function with multiple parameters, the values are separated by commas.

<pre class="prettyprint lang-java">
drawRectangle(21, 4);
</pre>


#### Explanations

1. In previous programs it was rather cumbersome to always write the parentheses `()` for each function. Now it is clear that the previously used functions were merely cases where no parameter was passed.
2. Inside the parentheses of a function declaration, the parameter (in this case `steps`) is specified with a type (here `int`). Multiple parameters are separated by commas as seen in this example:
<pre class="prettyprint lang-java">
public void drawRectangle(int width, int height)
</pre>
3. When such a function is called, the values are copied into the variables (in this case into `width` and `height`).

<div class="alert alert-info">
  **Importent:** Functions that are defined inside a class (which is usually the case) are also called **Methods**.
</div>


### Baking a Cake

We will now use our player to "bake" a birthday cake for the grandmother. The cake will be made of stars.


#### <i class="fa fa-rocket mg-t"></i> TASK 5.01: Baking a Cake

![Baking a Cake](/assets/library/hello-dart/part5/baking-a-cake.jpg) 

In preparation for baking, the following methods should be provided:

1. `turnAround()`   
Rotates the player by 180 degrees.

2. `multiMove(int steps)`   
The player takes the number of steps in the current direction (see example in the theory section above).

3. `putStars(int count)`   
The player puts the specified number of stars. The first one he places at the current position, the other following in his line of sight.

The player is to draw a rectangle with stars symbolizing the cake. 

The player starts in the lower left corner and looks to the right. The player should be able to make a rectangle with variable width and height! The function call `drawRectangle(21, 4)` should therefore create a rectangle with a width of 31 and height of 4.


#### <i class="fa fa-rocket mg-t"></i> TASK 5.02: Candles on Cake

![Candles on Cake](/assets/library/hello-dart/part5/candles-on-cake.jpg) 

To make the cake look like a birthday cake it needs a few candles, of course. Extend your program with an additional method `drawCandles(int count)` which sets the specified number of candles on the cake.


## Function with Return Values

A function can return a value as a result. The following function will multiply the specified `number` by two and return the result:

<pre class="prettyprint lang-java">
int twoTimes(int number) {
  return 2 * number;
}
</pre>


The result can either be stored in a variable or directly be used as follows:

<pre class="prettyprint lang-java">
multiMove(twoTimes(4));
</pre>


#### Explanations

1. The type of the return value (in this case `int`) is specified before the name of the function. If the function does not return a value, we write `void`.
2. To return a value we write `return` followed by the value.


#### <i class="fa fa-rocket mg-t"></i> TASK 5.03: Candles for Age

We want to put on the cake a **candle for every decade** of grandmother's age. As Little Red Riding Hood does not know exactly how old her grandmother is, she must first ask her mother.

Program a function called `howOldIsGrandma()` that returns the age. With the help of this function the rest of the program should place a candle on the cake for every decade.

<div class="alert alert-info">
  **Note:** The division of two numbers in Dart always results in a `double`. To get an `int` again, we can use the `toInt()` or `round()` functions. <br> Example: `(5 / 3).toInt()`
</div>




#### <i class="fa fa-rocket mg-t"></i> TASK 5.04 (difficult): Layered Cake

![Candles on Cake](/assets/library/hello-dart/part5/layered-cake.jpg) 

Let the player "bake" a layered cake for the grandmother. The player should add a layer for every decade that the grandmother is over 50. Each layer should have two lines and should be indented on both sides by two stars.



***

## What's next?


Learn about the [next steps](/library/hello-dart/next/).


***

*Credits*<br>
<em class="small">
  [Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) images by Daniel Cook (Lostgarden.com), published under [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) improved the "Planet Cute" images and was so kind to let me use them.<br>
Some exercises in `Hello Dart`were inspired by [Kara](http://www.swisseduc.ch/compscience/karatojava/javakara/). Kara was developed by Jürg Nievergelt, Werner Hartmann, Raimond Reichert et. al.
</em>