---
layout: article
title: "GreenfootKara - Chapter 1: First Steps"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara/chapter1
published: true
prettify: true
comments: false
sidebars:
- header: Articles in this Series
  body:
  - text: "Introduction"
    link: /tutorials/greenfoot-kara/
    paging: Intro
  - text: "Chapter 1: First Steps"
    link: /tutorials/greenfoot-kara/chapter1/
    paging: 1
    active: true
  - text: "Solutions Chapter 1"
    link: /tutorials/greenfoot-kara/chapter1-solutions/
    icon-css: fa fa-fw fa-check-square-o
  - text: "Chapter 2: Program Flow"
    link: /tutorials/greenfoot-kara/chapter2/
    paging: 2
  - text: "Chapter 3: Variables"
    link: /tutorials/greenfoot-kara/chapter3/
    paging: 3
  - text: "Chapter 4: Sokoban Game"
    link: /tutorials/greenfoot-kara/chapter4/
    paging: 4
  - text: "Chapter 5: Methods"
    link: /tutorials/greenfoot-kara/chapter5/
    paging: 5
- header: Downloads
  body:
  - text: scenarios-chapter-1.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/2.1.0/scenarios-chapter-1.zip
    icon-css: fa fa-fw fa-archive
  - text: Page as Word File
    link: /tutorials/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-text
- header: Languages
  body:
  - text: English
    link: /tutorials/greenfoot-kara/chapter1/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Deutsch
    link: /tutorials/greenfoot-kara/de/chapter1/
    icon-css: fa fa-fw fa-globe
---

In this chapter we will take our first steps in programming.


## Start Greenfoot

Start your installation of [Greenfoot](http://www.greenfoot.org).

(If you open Greenfoot for the first time, a dialog box asks what you'd like to do. Click on *choose scenario*.)

Open the scenario `Kara 101 First Steps` from the folder `scenarios-chapter-1`.
It now appears in the main Greenfoot window similar to the following:

![Screenshot 01](/assets/tutorials/greenfoot-kara/chapter1/screenshot01.png)
 
The Greenfoot window consists of three main areas and a few additional buttons. These three main areas are:

* The **world**: The largest area is called world. This is the area when the program runs, we can see what happens. In the Kara scenario it is a green meadow with gridlines.
* The **class Diagram**: The area to the right contains the classes that are available to us. First, we will mainly need the beetle Kara.
* The **Greenfoot Controls**: The buttons Act, Run, and Reset and the speed slider at the bottom serve the controlling of the program. We will come to that later.


***

## Classes and Objects

For our projects we use the **Java** programming language. Java is a so-called object-oriented language. For object-oriented programming, the concepts of classes and objects are of fundamental importance.

Consider the **class of** `Kara`. `Kara` is the class for the general concept of a ladybug - so to speak, it is like a blue-print from which we can create individual ladybugs. The produced ladybugs are called **objects** (or instances).

In Greenfoot, we create new objects as follows: *Right click* on the class of Kara and chooste the menu item *new Kara()*. Then move the object anywhere in the world and click where you wish to place it.

![Screenshot 02](/assets/tutorials/greenfoot-kara/chapter1/screenshot02.png)


#### <i class="fa fa-rocket"></i> TASK 1.01: First Steps

Create an object of (the **gray**!) `Kara`. Create several objects of the leafs.

*Tip: To create multiple objects of the same class, press the Shift-key before you click.*


***

## Interacting with Objects

To interact with objects in the world, we click with the right mouse button to bring up the **object menu**. The object menu of Kara shows us what Kara can do. These operations are called **methods** in Java.


#### <i class="fa fa-rocket"></i> TASK 1.02

<div class="alpha-list hidden"></div>

1. What does the move() method do?
2. Place two Karas in your world and make sure that they face each other. Which method do you need?
3. Try the other methods. There are two types of methods. What are those types and what do they do?

***

## Return Types

The word in front of a method name is also referred to as the **return type**. It tells us what the method returns, if we call it. The word `void` in this case means "nothing" - they return nothing and just perform an action.

If instead of `void` anything else is stated, we know that our method returns a result and also what type of result. The `boolean` type has two possible values: `true` or `false`. Then there are a number of other types which we will discuss later.


#### <i class="fa fa-rocket"></i> TASK 1.03

<div class="alpha-list hidden"></div>

1. Right-click on the Kara object and call the `onLeaf()` method. Does it always return `false`? If no, in which situations does it return `true`?
2. Add an additional tree to the world. What method can you call to check if Kara is facing a tree?
3. What happens if you call Kara’s `move()` method when Kara is facing a tree?


***

## Object State

If we *right-click* on a Kara-object, then select *inspect*, we can determine the state of the object.


#### <i class="fa fa-rocket"></i> TASK 1.04

What are the values of the coordinates and rotation in the following situations?

<div class="alpha-list hidden"></div>

1. x: ?, y: ?, rotation: ?   
![Screenshot 03](/assets/tutorials/greenfoot-kara/chapter1/screenshot03.png)

2. x: ?, y: ?, rotation: ?   
![Screenshot 04](/assets/tutorials/greenfoot-kara/chapter1/screenshot04.png)

**Note: The first field in the top left corner has the coordinates (0,0) and NOT (1,1)!**


***

## Running Programs in Greenfoot

So far we have only interacted with the objects using mouse-clicks. But there is another possibility, namely by writing programs.

From now on we will work with the **red Kara** (`MyKara` class)!

![Red Kara](/assets/tutorials/greenfoot-kara/chapter1/screenshot05.png)

In `MyKara` we will write our programs.


#### <i class="fa fa-rocket"></i> TASK 1.05

<div class="alpha-list hidden"></div>

1. Place a `MyKara` object into your world. What new method do you have available?
2. What does this method do?
3. What happens if you press the **Act button** in the Greenfoot controls?
4. Click on the **Run button**. What is happening? Try using the slider.
5. What methods can be found when you *right-click* on *MyKara* and then *inherited from Kara*? Can you use them?


***

## Source Code

We have just used the `act()` method. Now let's see where the behavior of this method is programmed. For this we need to open the **source code** in the editor: *Right-click* on the class *MyKara* then choose *Open editor* (or just use a *double-click*).

The source code is written in **Java** and contains all the details about a class and its objects. For the moment we are only interested in the lowest part where the method `act()` is defined. Here, the three commands: `move()`, `turnRight()` and `move()` are called. You can now modify and extend these commands.

When you have finished making changes, close the editor window. You will notice that the `MyKara` class is shown with gray stripes. 

![Screenshot 6](/assets/tutorials/greenfoot-kara/chapter1/screenshot06.png)

This is an indication that the class has been modified and now needs to be **compiled**. Compiling is a process of translation: the source code of the class is translated into machine code that can run on the computer.

The classes in Greenfoot are translated using the button **Compile** (bottom right of the main window). Once the classes are translated, the stripes disappear and we can create objects again.


### The Translation Process (Compiling)

![Compiling](/assets/tutorials/greenfoot-kara/chapter1/compiling.png)


#### <i class="fa fa-rocket"></i> TASK 1.06: Putting Leaf

Change the content of the act() method so that Kara makes a step first, then puts a leaf down and takes a step again. (At the beginning of the class you will find a comment describing all the actions that Kara can per-form.)

*Note: After each command we must include a semicolon!*


#### <i class="fa fa-rocket"></i> TASK 1.07: Around Tree

Create the world shown here:

![Screenshot 7](/assets/tutorials/greenfoot-kara/chapter1/screenshot07.png)

**Tip:** So that you don't have to recreate the world after each compile you can save the world by right-clicking on the World and calling saveWorldSetupToFile(). Then choose a new filename.
In order for your world to actually be loaded you must change the constant `WORLD_SETUP_FILE` in `KaraWorld`:

<pre class="prettyprint lang-java">
public static final String WORLD_SETUP_FILE = "[MyName].txt";
</pre>

Write a program that takes Kara to the leaf along the dotted line on the image above. Kara will have to run around the trees. Arriving at the leaf, Kara should pick it up.


***

## Kara gets new Methods

#### <i class="fa fa-rocket"></i> TASK 1.08: Around Tree with Method

If you have solved task 7 correctly, your program should contain **three equal parts**, namely for walking around each tree. We can expand our program for clarity by introducing a new method. Below the `act()` method we will create a new method:

<pre class="prettyprint lang-java">
public void goAroundTree() {

}
</pre>


Between the curly braces of the method, write the commands to make Kara go around a tree.

Now, use the new method `goAroundTree()` inside the `act()` method for each of the three trees.


***

## Learning Summary of Chapter 1

1. Find the following elements in the image:   
*World, Class diagram, compile button, a class, an object*   
![Screenshot 8](/assets/tutorials/greenfoot-kara/chapter1/screenshot08.png)

2. What is special about a method with a `void` return type?   
*Performs an action but does not return a result.*

3. What is special about a method with a `boolean` return type?   
*Returns a result of the type boolean.*

4. What values can a `boolean` have?   
*true or false*

5. What is the difference between `move()` and `act()` in our Kara scenario?   
*move() moves Kara one step forward. In act() we can call any method we want.*

6. What is compiling? Why is it necessary?   
*Compiling is the translation of source code into machine code so that the computer can understand it.*


*** 

***Credits:*** *This chapter is heavily inspired by the first chapter of the book [Introduction to Programming with Greenfoot](http://www.greenfoot.org/book) by Michael Kölling. [Ideas and concepts of Kara](http://www.swisseduc.ch/informatik/karatojava/) were developed by Jürg Nievergelt, Werner Hartmann, Raimond Reichert et al.*

***

## What's Next?

Continue with [Chapter 2: Program Flow](/tutorials/greenfoot-kara/chapter2)


