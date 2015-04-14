---
layout: article
title: "Hello Dart - Part 4: Variables"
date: 2015-04-18 00:00
slug: hello-dart/part4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part4.md
description: "With variables we can store data in Dart. Learn about the different data types available in Dart."
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
  - text: "Background Infos"
    link: /library/hello-dart/background/
    icon-css: fa fa-fw fa-info
    paging: <i class="fa fa-info"></i>
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
    active: true
  - text: "Part 5: Functions"
    link: /library/hello-dart/part5/
    paging: 5
  - text: "Next Steps"
    link: /library/hello-dart/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Solutions
  body:
  - text: "Solutions Part 4"
    link: /library/hello-dart/part4-solutions/
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
  part: part4
  active: en
---

With loops we have learned to repeat certain events while a condition is met. Now we want to do the following:

*The player is to lay a trail of five stars.*

![Five Stars](/assets/library/hello-dart/part4/five-stars.png)

This would, of course, be quite easy if we simple called `putStar()` and `move()` five times. But this is not very elegant. It would be nice if the player would count how many stars he has already placed. To do so, the player will need a "brain", that means some kind of memory. Memory in programming can be used through the use of variables.


## Counting with Variables

<pre class="prettyprint lang-dart">
var i;
i = 0;

while (i &lt; 5) {
  putStar();
  move();

  i = i + 1;
}
</pre>


#### Explanations

1. With `var i;` we reserve space for a variable named `i`. We say: The **variable i is declared**.
2. With `i = 0;` the variable `i` is assigned a value of `0`. Since it is the first assignment for the variable, we also say: The **variable i is initialized**.
3. Mostly, declaring and initializing a variable is done in a single step:   
`int i = 0;`
4. For the condition `i < 5`, the comparison operator `<` means less than (more comparison operators see tables below).
5. For the assignment `i = i + 1`, we must first look at the right part. It means: "Take the current value of i, add 1 to it and save the new value again under the name i."


#### More Information about Variables

* It is possible to create a variable with an unchangeable value, that means making it into a constant:   
`const anzahl = 5;`   
With this constant we could change the condition in the program above to   
`while (i < anzahl)`.
* Variables always start with a lower case letter, just like function names.


***

## Data Types in Dart

In Dart there are a few pre-defined data types. With data types we can specify what type of data we intend to store in a variable. This information is optional Dart. This means that we could declare all variables with the generic `var`, as we did above. But I recommend that you specify the data type most of the time. This helps to detect errors early as Dart will tell you when you try to put the wrong type of data into a variable.


<div class="alert alert-info">
  **Note:** Anything that can be stored in a variable in Dart is called an **object**. The data type of an object is called a **class**. Classes and Objects are the basic building blocks of <a class="alert-link" href="http://en.wikipedia.org/wiki/Object-oriented_programming">object-oriented programming</a>.
</div>

Later, we will create our own classes. Let's have a look at the basic data types of Dart.

*For more information about the basic data types of Dart see [Dart Up and Running - Chapter 2: Built-in types](https://www.dartlang.org/docs/dart-up-and-running/ch02.html#built-in-types).*

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Type</th>
      <th>Description</th>
      <th>Example</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`int`</td>
      <td>Integer values</td>
      <td>`1`, `2`, `-1233123`</td>
    </tr>
    <tr>
      <td>`double`</td>
      <td>Floating-point numbers</td>
      <td>`0.5`, `-333.234`</td>
    </tr>
    <tr>
      <td>`String`</td>
      <td>Text of arbitrary length. Either *singel or double quotes* can be used to create a string.</td>
      <td>`'I am a text'` or `"I am also a text"`</td>
    </tr>
    <tr>
      <td>`bool`</td>
      <td>A boolean is a logic value that can either be *true* or *false*.</td>
      <td>`true` or `false`</td>
    </tr>
    <tr>
      <td>`List`</td>
      <td>A collection of objects. Lists are sometimes also called *arrays*.</td>
      <td>`[1, 2, 3]`</td>
    </tr>
    <tr>
      <td>`Map`</td>
      <td>A map is an object that associates *keys* and *values*.</td>
      <td>`{ 'key-1': 'value-1', 'key-2': 'value-2' }`</td>
    </tr>
  </tbody>
</table>


## Comparison Operators

The following operators can be used for comparisons in Dart. The result of each expression is always a `bool`, that means it is either `true` or `false`. 

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Operator</th>
      <th>Description</th>
      <th>Example</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`==`</td>
      <td>equal</td>
      <td>`k == 2`</td>
    </tr>
    <tr>
      <td>`!=`</td>
      <td>unequal</td>
      <td>`k != 12`</td>
    </tr>
    <tr>
      <td>`>`</td>
      <td>greater than</td>
      <td>`k > 67`</td>
    </tr>
    <tr>
      <td>`<`</td>
      <td>less than</td>
      <td>`k < 12`</td>
    </tr>
    <tr>
      <td>`>=`</td>
      <td>greater than or equal to</td>
      <td>`k >= 45`</td>
    </tr>
    <tr>
      <td>`<=`</td>
      <td>less than or equal to</td>
      <td>`k <= 23`</td>
    </tr>
  </tbody>
</table>

<div class="alert alert-info">
  **Note:** The equality operator always has two equal signs `==`. A single equal sign `=` is used for assignments to variables!
</div>


## Arithmetic Operators

For calculations we use the following arithmetic operators.


<table class="table table-bordered">
  <thead>
    <tr>
      <th>Operator</th>
      <th>Description</th>
      <th>Example</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`+`</td>
      <td>Add</td>
      <td>`h = w + 34`</td>
    </tr>
    <tr>
      <td>`-`</td>
      <td>Subtract</td>
      <td>`y = 3.4 – t`</td>
    </tr>
    <tr>
      <td>`*`</td>
      <td>Multiply</td>
      <td>`z = h * 3.56`</td>
    </tr>
    <tr>
      <td>`/`</td>
      <td>Divide</td>
      <td>`d = m / v`</td>
    </tr>
    <tr>
      <td>`%`</td>
      <td>Modulo (the remainder of an integer division)</td>
      <td>`count = w % 2`</td>
    </tr>
  </tbody>
</table>


***

#### <i class="fa fa-rocket mg-t"></i> TASK 4.01: Counting Stars

![Counting Stars](/assets/library/hello-dart/part4/counting-stars.png)

The player is to move from the left to the right and count all the stars.

*Hints:*

1. Define and initialize a variable, for example like      
`int count = 0;`
2. Below it write a loop that increments the variable by one:   
`count = count + 1;`
3. The variable should only be incremented when the player is on a star.
4. At the end the player should tell us how many stars he counted. With `${count}` we can include a variable inside a text:   
`say('I have found ${count} stars.');`


<div class="alert alert-info">
  **The Scope of Variables:** Variables are only valid within the block (curly braces) in which they are declared. They can also be declared outside the functions. Those variables are then available inside an entire class or, if declared outside any class, they are globally available.
</div>


#### <i class="fa fa-rocket mg-t"></i> TASK 4.02: Cleaning Up

![Cleaning Up](/assets/library/hello-dart/part4/cleaning-up.png)

The player is to remove all the stars.

*Hints:*

1. You can use the tree to find out when the player is at the end with a `while (!treeFront()) {...}` loop.
2. First remove the stars in the first row.
3. Turn at the border and remove the stars of the second line.
4. Now introduce a `bool` variable, so that the player can remember in which direction he is facing, for example like the following:   
`bool goingRight = true;`
5. With the help of this variable you can now find out if the player reaches the left or the right border and turn accordingly. A `bool` can be included in a condition as follows:   
`if (goingRight) {...}`
6. Remember to save the new direction to the variable whenever the player turns:   
`goingRight = false;`
7. *Improvement*: To make the code more readable you could create two functions for turning around: `turnAroundRight()` and `turnAroundLeft()`.


#### <i class="fa fa-rocket mg-t"></i> TASK 4.03: Inverting

![Inverting](/assets/library/hello-dart/part4/inverting.png)

The world is a square and has a tree at the end. Within the world there is a pattern of stars that is to be inverted by the player. Make sure that your code stays clear by extracting parts into their own functions.


#### <i class="fa fa-rocket mg-t"></i> TASK 4.04: Chessboard

![Chessboard](/assets/library/hello-dart/part4/chessboard.png)

The world is a square and has a tree at the end. The player is to create a chessboard pattern with stars. 


#### <i class="fa fa-rocket mg-t"></i> TASK 4.04 (difficult): Tree Line

![Tree Line](/assets/library/hello-dart/part4/tree-line.jpg)

In this world there are several rows of trees. The player is to determine the length (in number of trees) of the longest line of trees and report the result. Between the rows of trees is always at least one empty row. A star marks the end of the world.


## What's next?

&rarr; Continue with [Part 5: Functions](/library/hello-dart/part5/)


***

*Credits*<br>
<em class="small">
  [Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) images by Daniel Cook (Lostgarden.com), published under [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) improved the "Planet Cute" images and was so kind to let me use them.<br>
Some exercises in `Hello Dart`were inspired by [Kara](http://www.swisseduc.ch/compscience/karatojava/javakara/). Kara was developed by Jürg Nievergelt, Werner Hartmann, Raimond Reichert et. al.
</em>