---
layout: article
title: "Hello Dart - Solutions Part 2"
date: 2015-04-18 00:00
slug: hello-dart/part2-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part2-solutions.md
description: "Solutions for Hello Dart Part 2."
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
  part: part2-solutions
  active: en
---

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.01: Loop

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (canMove()) {
      move();
    }
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 2.02: Loop Star

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!onStar()) {
      move();
    }
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 2.03: Leaving the Tunnel

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (treeLeft() &amp;&amp; treeRight()) {
      move();
    }

    putStar();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 2.04: Afraid of Tunnel

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!treeLeft() || !treeRight()) {
      move();
    }
    say('AHHHH! This looks very dark in here!');
  }
}
</pre>

We could also write the `while` condition as follows:

<pre class="prettyprint lang-dart">
while (!(treeLeft() &amp;&amp; treeRight())
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 2.05: Climbing Up

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (treeFront()) {
      oneStepUp();
    }
  }

  /// Climbes one step.
  oneStepUp() {
    turnLeft();
    move();
    turnRight();
    move();
  }
}
</pre>