---
layout: article
title: "GreenfootKara - Chapter 3: Variables"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
published: true
prettify: true
comments: true
sidebars:
- header: Articles in this Series
  paging: true
  body:
  - text: "Introduction"
    link: /java/greenfoot-kara-intro/
    intro: true
  - text: "Chapter 1: First Steps"
    link: /java/greenfoot-kara-chapter1/
  - text: "Chapter 2: Program Flow"
    link: /java/greenfoot-kara-chapter2/
  - text: "Chapter 3: Variables"
    link: /java/greenfoot-kara-chapter3/
    active: true
  - text: "Solutions Chapter 3"
    link: /java/greenfoot-kara-chapter3-solutions/
    icon-css: fa fa-fw fa-check-square-o
    paging-exclude: true
  - text: "Chapter 4: Sokoban Game"
    link: /java/greenfoot-kara-chapter4/
  - text: "Chapter 5: Methods"
    link: /java/greenfoot-kara-chapter5/
- header: Downloads
  body:
  - text: scenarios-chapter-3.zip
    link: http://swisseduc.ch/informatik/karatojava/greenfootkara/classes/scenarios-chapter-3.zip
    icon-css: fa fa-fw fa-archive
  - text: chapter-3-variables.docx
    link: http://swisseduc.ch/informatik/karatojava/greenfootkara/docs/en/chapter-3-variables.docx
    icon-css: fa fa-fw fa-file-text
- header: Languages
  body:
  - text: Deutsch
    link: /java/greenfoot-kara-intro-de/
    icon-css: fa fa-fw fa-globe
---

In the last chapter we have learned to repeat certain events while a condition is met. Now we want to do the following:

*Kara is to lay a trail of five leafs.*

![Loop Example](/assets/java/greenfoot-kara-chapter3/loop-example.png) 

This would, of course, be quite easy if we simple called `putLeaf()` and `move()` five times. But this is not very elegant. It would be nice if Kara counts how many leaves he has already placed. If so, Kara will need a "brain", i.e. some kind of memory. Memory in programming can be used through the use of variables.

### Counting with Kara

<pre class="prettyprint lang-java">
int i;
i = 0;

while (i < 5) {
	putLeaf();
	move();

	i = i + 1;
}
</pre>

#### Explanations

1. With `int i;` we reserve space for a variable named `i` of the type `int`. We say: The **variable i is declared**. In Java, there are different types of variables that can be used (see tables below).
2. With `i = 0;` the variable `i` is assigned the value `0`. Since it is the first assignment for the variable, we also say: The **variable i is initialized**.
3. We can declare a variable and initialize it in one step: `int i = 0;`
4. For the condition `i < 5`, the comparison operator `<` means less than (more comparison operators, see tables below).
5. For the assignment `i = i + 1`, we must first look at the right part. It means: "Take the current value of `i`, add `1` to it and save the new value again under the name `i`."


#### More Information about Variables

* It is possible to provide a variable with a final value, i.e. to make it a constant: `final int NUMBER = 5;`   
Then we could rewrite the above example as `while (i < NUMBER)`   
Constants are written entirely in uppercase letters.
* Variables that are not constants always start with lower case.


* * *

## Elementary Data Types in Java

These data types are called "elementary" because they are the basic data types in Java. (Later we will learn how to create variable types for entire objects).

### Integers and Characters

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Rype</th>
      <th>From</th>
      <th>Up to and including</th>
      <th>Required memory</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`byte`</td>
      <td>-128</td>
      <td>127</td>
      <td>8 bit</td>
    </tr>
    <tr>
      <td>`short`</td>
      <td>-32'768</td>
      <td>32'767</td>
      <td>16 bit</td>
    </tr>
    <tr>
      <td>`int`</td>
      <td>-2'147'483'648</td>
      <td>2'147'483'647</td>
      <td>32 bit</td>
    </tr>
    <tr>
      <td>`long`</td>
      <td>-9'223'372'036'854'775'808</td>
      <td>9'223'372'036'854'775'807</td>
      <td>64 bit</td>
    </tr>
    <tr>
      <td>`char`</td>
      <td>0</td>
      <td>65'635</td>
      <td>16 bit</td>
    </tr>
  </tbody>
</table>


### Floating Point Numbers

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Type</th>
      <th>From</th>
      <th>Up to and including</th>
      <th>Required memory</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`float`</td>
      <td>-3.4 \* 10<sup>38</sup></td>
      <td>3.4 \* 10<sup>38</td>
      <td>32 bit</td>
    </tr>
    <tr>
      <td>`double`</td>
      <td>-1.7 \* 10<sup>308</sup></td>
      <td>1.7 \* 10<sup>308</sup></td>
      <td>64 bit</td>
    </tr>
  </tbody>
</table>


### Logical Values

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Type</th>
      <th>Range of values</th>
      <th>Required memory</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`boolean`</td>
      <td>`true` or `false`</td>
      <td>1 bit</td>
    </tr>
  </tbody>
</table>


* * *

## Comparison Operators

The following operators can be used for comparisons in Java. The result is always a `boolean` (either `true` or `false`).

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Operator</th>
      <th>Meaning</th>
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
      <td>not equal</td>
      <td>`k < 12`</td>
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


**Note:** The equality operator always has two equal signs `==`. A single equal sign `=` is used for assignments!


* * *

## Arithmetic Operations

To calculate we use the following arithmetic operators:

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Operator</th>
      <th>Meaning</th>
      <th>Example</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`+`</td>
      <td>Add</td>
      <td>`h = value + 34`</td>
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
      <td>Get the remainder of an integer division (modulo)</td>
      <td>`count = w % 2`</td>
    </tr>
  </tbody>
</table>


* * *

#### <i class="fa fa-rocket"></i> TASK 3.01: Counting Leafs

![TASK 3.01](/assets/java/greenfoot-kara-chapter3/task01.png) 

Kara is going horizontally from left to right until he reaches the tree. On his way he counts the leafs and reports the result at the end.

*Hints:*

* At the end, you can write the result with the following command to the console: `System.out.println("The result is: " + count);`
* Text must always be written in quotes. The plus sign appends the value of the variable `count` (which may of course be named differently).
* *Scope of variables*: variables are visible only within the block (between the curly brackets), in which they are declared. They can also be declared outside the methods in order to be visible to the entire class.


#### <i class="fa fa-rocket"></i> TASK 3.02: Kara in a Box I

![TASK 3.02](/assets/java/greenfoot-kara-chapter3/task02.png) 

A square area is surrounded by trees. Within the area is a set pattern of leafs
that Kara must invert. Kara starts in the upper left corner with a view to the right.

*Hints:*

* For this task it is helpful to work with boolean variables, e.g.:
  * declaration and initialization: `boolean goingRight = false;`
  * switching from false to true and vice versa: `goingRight = !goingRight;`
  * boolean as a condition: `if (goingRight)`



#### <i class="fa fa-rocket"></i> TASK 3.03: Kara in a Box II

![TASK 3.03](/assets/java/greenfoot-kara-chapter3/task03.png) 

A square area is surrounded by trees. Within the area is a checkerboard-like pattern of leafs to be laid by Kara. Kara starts in the upper left corner with a view to the right.



#### <i class="fa fa-rocket"></i> TASK 3.04: The Longest Tree Line

![TASK 3.04](/assets/java/greenfoot-kara-chapter3/task04.png) 

In this world there are several rows of trees. Kara is to determine the length (in number of trees) of the longest line of trees and output the result to the console. Between the rows of trees is always at least one empty row. A leaf marks the end of the world.



#### <i class="fa fa-rocket"></i> TASK 3.05 (very difficult): Push Mushroom Through Tunnel

![TASK 3.05](/assets/java/greenfoot-kara-chapter3/task05.png) 

This world of Kara has two boxes connected by a tunnel. In the box on the left is Kara and a leaf. In the box on the right is a mushroom. Kara is to get to the other side, find the mushroom, then push it to the left side. Once on the left side, the mushroom must be pushed onto the leaf.

Kara always starts in the top left corner and the leaf is always in the bottom left corner. The mushroom, on the other hand, can be at an arbitrary position on the right side of the tunnel.

*Hints:*

* This problem may be solved in pairs and in collaboration. 
* Some sub-problems may be divided among the collaborators:
  * Find the tunnel entrance
  * Find the mushroom 
  * Place the mushroom in the tunnel entrance
  * Push the mushroom onto the leaf


* * *

## More about Variables

So far, we have had a quick introduction to variables. Now, a few additional comments on the different types:


### Elementary Data Types

![Elementary Types](/assets/java/greenfoot-kara-chapter3/elementary-types.png) 

Elementary data types are like cups (in memory) where the value is saved directly into the variable.


### Reference Types

![Reference Types](/assets/java/greenfoot-kara-chapter3/reference-types.png) 


The value in `k` is a reference to the Kara-object. With the dot operator (`k.`)
`k` can be used like a remote control for the Kara object!







* * * 

***Credits:*** *[Ideas and concepts of Kara](http://www.swisseduc.ch/informatik/karatojava/) were developed by Jürg Nievergelt, Werner Hartmann, Raimond Reichert et al. Some Kara exercises are based on [material by Horst Gierhardt](http://www.swisseduc.ch/informatik/karatojava/javakara/material/).*

* * *

## What's Next?

Continue with [Chapter 4: Sokoban Game](/java/greenfoot-kara-chapter4/)






