---
layout: article
title: "Hello Dart - Part 1: First Steps"
date: 2015-05-01 00:00
slug: hello-dart/part1
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part1.md
description: "The first steps in programming with Dart. Get to know classes and functions and understand what the main function is for."
image: /assets/library/hello-dart/part1/around-tree.png
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
    active: true
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
  - text: "Next Steps"
    link: /library/hello-dart/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Solutions
  body:
  - text: "Solutions Part 1"
    link: /library/hello-dart/part1-solutions/
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
  part: part1
  active: en
---

In this part we will take our first steps in programming.

![Hello Dart](/assets/library/hello-dart/part1/hello.png)


### Prerequisites

* You need no prior knowledge of programming to start with `Hello Dart`!
* You must have the Dart Editor with the `Hello Dart` scenarios installed. If you don't have it yet, please go through the [installation instructions](/library/hello-dart/install/).

***


## The `MyPlayer` Class

Open the file `my_player.dart` from the folder `scenario1.01`.

![My Player](/assets/library/hello-dart/part1/my-player.png)

Let's have a look at the `MyPlayer` **class**. Attributes and behavior of our player are defined between the curly braces `{` and `}`.

Currently, the only thing that is in this class is `start()` and three instructions to move the player. `start()` is a function (functions are sometimes also called *methods*). In this function we can change the behavior of the player.


#### <i class="fa fa-rocket mg-t"></i> TASK 1.01: First Steps

Change the `start()` function so that the player first makes a step, then places a star, and finally takes another step.

In the [introduction](/library/hello-dart/) you will find all commands a player can execute.

<div class="alert alert-info">
  **Note:** After every command you must place a semicolon `;`.
</div>

Click the *run* button ![Run](/assets/library/hello-dart/part1/run.png) to test your program.


## `index.html` and the `main()` Function

As we let our programs run in a web browser, we always need an `html` file. In `index.html` you will find an declaration for the browser to load the `my_player.dart` script. For the browser to know where this Dart program starts, a `main()` function is needed.

At the bottom of the Dart file you will find the `main()` function. Each Dart program must have exactly one `main()` function as entry point.

In our `main()`, we call the `createWorld(...)` function. This function is part of `Hello Dart` and builds the whole world with the player and the fields. As soon as everything is ready, our `start()` function is automatically called and the player starts to move.


## Design the World

The scenarios of `Hello Dart` include additional graphics that you can turn on if you want.

![Catgirl](/assets/library/hello-dart/part1/catgirl.png)


#### <i class="fa fa-rocket mg-t"></i> TASK 1.02: World Design

Change the `main()` function as follows:

<pre class="prettyprint lang-dart">
main() {
  character = 'catgirl';
  field = 'stone';
  
  createWorld('scenario.txt', new MyPlayer());
}
</pre>

For **character** you can define values in quotes. Valid values are `boy`, `catgirl`, `stargirl`, or `pinkgirl`.

For **field** you can use the values `grass`, `stone`, `wood`, or `dirt`.

<div class="alert alert-info">
  **Hint:** To test a change it's fastest if you click `Ctrl+S` (or `⌘+S`) to save the file in the Dar Editor and then click `F5` (or `⌘+R`) to refresh the browser.
</div>


#### <i class="fa fa-rocket mg-t"></i> TASK 1.03: Around Tree

Open `scenario1.03`. If you start the scenario you should see a world with three trees and one star.

Write a program that makes your player move on the specified path to the star. He must move around the trees. Arriving on the star, he should remove it.

![Around Tree](/assets/library/hello-dart/part1/around-tree.png)


## New Functions

#### <i class="fa fa-rocket mg-t"></i> TASK 1.04: Around Tree with Function

If you've solved task 1.03 correctly, your program will probably have *three equal parts*, namely for walking aroud every tree. We can improve this by introducing a new function so we don't need to repeat the same sequence. Add the following function below the closing brace `}` from the `start()` function:

<pre class="prettyprint lang-dart">
goAroundTree() {

}
</pre>

Write between the curly braces the sequence of commands that are needed to go around one tree.

Now, use the new `goAroundTree()` function in your `start()` function for each of the three trees.


## What's next?

&rarr; Continue with [Part 2: Loops](/library/hello-dart/part2/)

***

*Credits*<br>
<em class="small">
  [Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) images by Daniel Cook (Lostgarden.com), published under [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) improved the "Planet Cute" images and was so kind to let me use them. The images were optimized with the great [TexturePacker](https://www.codeandweb.com/texturepacker).<br>
Some exercises in `Hello Dart`were inspired by [Kara](http://www.swisseduc.ch/compscience/karatojava/javakara/). Kara was developed by Jürg Nievergelt, Werner Hartmann, Raimond Reichert et. al.
</em>
