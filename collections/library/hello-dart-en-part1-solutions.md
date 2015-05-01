---
layout: article
title: "Hello Dart - Solutions Part 1"
date: 2015-05-01 00:00
slug: hello-dart/part1-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part1-solutions.md
description: "Solutions for Hello Dart Part 1."
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
  - text: "Next Steps"
    link: /library/hello-dart/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Solutions
  body:
  - text: "Solutions Part 1"
    link: /library/hello-dart/part1-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
- header: Links
  body:
  - text: Page as Word File
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-word-o
languages:
  header: Languages
  collection: library
  item: hello-dart
  part: part1-solutions
  active: en
---

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 1.01: First Steps

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    move();
    putStar();
    move();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 1.02: World Design

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    move();
    putStar();
    move();
  }
}

main() {
  character = 'catgirl';
  field = 'stone';

  createWorld('scenario.txt', new MyPlayer());
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 1.03 Around Tree

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
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

    removeStar();
  }
}
</pre>


#### Explanations

The **comments** in the source code have been omitted (that is the text that is displayed in light blue in the Dart Editor).

Comments in the source code are written for additional information. Comments are intended only for humans and are ignored by the computer. There are several ways to write comments in the source code:

1. Simple comments with a double slash `//` (everything between the slashes and the end of line is a comment).
2. Comments for functions and classes are written with a tripple slash `///`.
3. (Rarely used) Longer comments over multiple lines that start with `/*` and end with `*/`.


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 1.04: Around Tree with Function

<pre class="prettyprint lang-java">
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

#### Explanations

1. For a better overview and to avoid that we have to write the same code three times, we have introduced a new function called `goAroundTree()`.
2. After each function is a set of parentheses `()`, which means that no parameters are passed to this function. Later we will learn about functions with parameters.