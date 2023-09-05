+++
title = "Chapter 3: Variables"
date = 2012-10-03
updated = 2014-03-08
image = "greenfootkara-screenshot.png"
description = "Dans ce chapitre de GreenfootKara nous apprenons à propos des variables."
prettify = true
# comments = true

pagingName = "3"
weight = 4

[[sidebars]]
header = "Solutions"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-check-square-o\"></i> Solutions Chapter 3"
link = "/library/greenfoot-kara/chapter3/solutions/"

[[sidebars]]
header = "Downloads"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-archive-o\"></i> scenarios-chapter-3.zip"
link = "https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-3.zip"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Page as Word File"
link = "/library/convert-web-page-to-word/"
+++

In the last chapter we have learned to repeat certain events while a condition is met. Now we want to do the following:

*Kara is to lay a trail of five leaves.*

![Loop Example](loop-example.png) 

This would, of course, be quite easy if we simple called `putLeaf()` and `move()` five times. But this is not very elegant. It would be nice if Kara counts how many leaves he has already placed. If so, Kara will need a "brain", i.e. some kind of memory. Memory in programming can be used through the use of variables.

### Counting with Kara

<pre class="prettyprint lang-java">
int i;
i = 0;

while (i &lt; 5) {
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


***

## Elementary Data Types in Java

These data types are called "elementary" because they are the basic data types in Java. (Later we will learn how to create variable types for entire objects).

### Integers and Characters

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
      <td><code>byte</code></td>
      <td>-128</td>
      <td>127</td>
      <td>8 bit</td>
    </tr>
    <tr>
      <td><code>short</code></td>
      <td>-32'768</td>
      <td>32'767</td>
      <td>16 bit</td>
    </tr>
    <tr>
      <td><code>int</code></td>
      <td>-2'147'483'648</td>
      <td>2'147'483'647</td>
      <td>32 bit</td>
    </tr>
    <tr>
      <td><code>long</code></td>
      <td>-9'223'372'036'854'775'808</td>
      <td>9'223'372'036'854'775'807</td>
      <td>64 bit</td>
    </tr>
    <tr>
      <td><code>char</code></td>
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
      <td><code>float</code></td>
      <td>-3.4 \* 10<sup>38</sup></td>
      <td>3.4 \* 10<sup>38</td>
      <td>32 bit</td>
    </tr>
    <tr>
      <td><code>double</code></td>
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
      <td><code>boolean</code></td>
      <td><code>true</code> or <code>false</code></td>
      <td>1 bit</td>
    </tr>
  </tbody>
</table>


***

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
      <td><code>==</code></td>
      <td>equal</td>
      <td><code>k == 2</code></td>
    </tr>
    <tr>
      <td><code>!=</code></td>
      <td>not equal</td>
      <td><code>k < 12</code></td>
    </tr>
    <tr>
      <td><code>></code></td>
      <td>greater than</td>
      <td><code>k > 67</code></td>
    </tr>
    <tr>
      <td><code><</code></td>
      <td>less than</td>
      <td><code>k < 12</code></td>
    </tr>
    <tr>
      <td><code>>=</code></td>
      <td>greater than or equal to</td>
      <td><code>k >= 45</code></td>
    </tr>
    <tr>
      <td><code><=</code></td>
      <td>less than or equal to</td>
      <td><code>k <= 23</code></td>
    </tr>
  </tbody>
</table>


**Note:** The equality operator always has two equal signs `==`. A single equal sign `=` is used for assignments!


***

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
      <td><code>+</code></td>
      <td>Add</td>
      <td><code>h = value + 34</code></td>
    </tr>
    <tr>
      <td><code>-</code></td>
      <td>Subtract</td>
      <td><code>y = 3.4 – t</code></td>
    </tr>
    <tr>
      <td><code>*</code></td>
      <td>Multiply</td>
      <td><code>z = h * 3.56</code></td>
    </tr>
    <tr>
      <td><code>/</code></td>
      <td>Divide</td>
      <td><code>d = m / v</code></td>
    </tr>
    <tr>
      <td><code>%</code></td>
      <td>Get the remainder of an integer division (modulo)</td>
      <td><code>count = w % 2</code></td>
    </tr>
  </tbody>
</table>


***

#### <i class="fa fa-rocket"></i> TASK 3.01: Counting Leaves

![TASK 3.01](task01.png) 

Kara is going horizontally from left to right until he reaches the tree. On his way he counts the leaves and reports the result at the end.

*Hints:*

* At the end, you can write the result with the following command to the console: `System.out.println("The result is: " + count);`
* Text must always be written in quotes. The plus sign appends the value of the variable `count` (which may of course be named differently).
* *Scope of variables*: variables are visible only within the block (between the curly brackets), in which they are declared. They can also be declared outside the methods in order to be visible to the entire class.


#### <i class="fa fa-rocket"></i> TASK 3.02: Kara in a Box I

![TASK 3.02](task02.png) 

A square area is surrounded by trees. Within the area is a set pattern of leaves
that Kara must invert. Kara starts in the upper left corner with a view to the right.

*Hints:*

* For this task it is helpful to work with boolean variables, e.g.:
  * declaration and initialization: `boolean goingRight = false;`
  * switching from false to true and vice versa: `goingRight = !goingRight;`
  * boolean as a condition: `if (goingRight)`



#### <i class="fa fa-rocket"></i> TASK 3.03: Kara in a Box II

![TASK 3.03](task03.png) 

A square area is surrounded by trees. Within the area is a checkerboard-like pattern of leaves to be laid by Kara. Kara starts in the upper left corner with a view to the right.



#### <i class="fa fa-rocket"></i> TASK 3.04 (difficult): The Longest Tree Line

![TASK 3.04](task04.png) 

In this world there are several rows of trees. Kara is to determine the length (in number of trees) of the longest line of trees and output the result to the console. Between the rows of trees is always at least one empty row. A leaf marks the end of the world.



#### <i class="fa fa-rocket"></i> TASK 3.05 (very difficult): Push Mushroom Through Tunnel

![TASK 3.05](task05.png) 

This world of Kara has two boxes connected by a tunnel. In the box on the left is Kara and a leaf. In the box on the right is a mushroom. Kara is to get to the other side, find the mushroom, then push it to the left side. Once on the left side, the mushroom must be pushed onto the leaf.

Kara always starts in the top left corner and the leaf is always in the bottom left corner. The mushroom, on the other hand, can be at an arbitrary position on the right side of the tunnel.

*Hints:*

* This problem may be solved in pairs and in collaboration. 
* Some sub-problems may be divided among the collaborators:
  * Find the tunnel entrance
  * Find the mushroom 
  * Place the mushroom in the tunnel entrance
  * Push the mushroom onto the leaf


***

## More about Variables

So far, we have had a quick introduction to variables. Now, a few additional comments on the different types:


### Elementary Data Types

![Elementary Types](elementary-types.png) 

Elementary data types are like cups (in memory) where the value is saved directly into the variable.


### Reference Types

![Reference Types](reference-types.png) 


The value in `k` is a reference to the Kara-object. With the dot operator (`k.`)
`k` can be used like a remote control for the Kara object!


*** 

***Credits:*** *[Ideas and concepts of Kara](http://www.swisseduc.ch/informatik/karatojava/) were developed by Jürg Nievergelt, Werner Hartmann, Raimond Reichert et al. Some Kara exercises are based on [material by Horst Gierhardt](http://www.swisseduc.ch/informatik/karatojava/javakara/material/).*


***

## What's Next?

Continue with [Chapter 4: Sokoban Game](/library/greenfoot-kara/chapter4/)






