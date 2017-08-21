---
layout: article
title: "Hello Dart - Solutions Part 4"
date: 2015-05-01 00:00
slug: hello-dart/part4-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part4-solutions.md
description: "Solutions for Hello Dart Part 4."
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
  - text: "Solutions Part 4"
    link: /library/hello-dart/part4-solutions/
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
  part: part4-solutions
  active: en
---

#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 4.01: Counting Stars

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    int count = 0;

    while (canMove()) {
      move();

      if (onStar()) {
        count = count + 1;
      }
    }

    say('I have found ${count} stars.');
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 4.02: Cleaning Up

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    bool goingRight = true;

    while (!treeFront()) {
      removeStar();

      if (canMove()) {
        move();
      } else {
        // We are at a border.
        if (goingRight) {
          // We are at the right border and turn around.
          turnAroundRight();
          goingRight = false;
        } else {
          // We are at the left border and turn around.
          turnAroundLeft();
          goingRight = true;
        }
      }
    }

    // Remove the last star.
    removeStar();
  }

  /// Turn around at the right border.
  turnAroundRight() {
    turnRight();
    move();
    turnRight();
  }

  /// Turn around at the left border.
  turnAroundLeft() {
    turnLeft();
    move();
    turnLeft();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 4.03: Inverting

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    bool goingRight = true;

    while (!treeFront()) {
      invertField();

      if (canMove()) {
        move();
      } else {
        // We are at a border.
        if (goingRight) {
          // We at the right border and turn around.
          turnAroundRight();
          goingRight = false;
        } else {
          // We are at the left border and turn around.
          turnAroundLeft();
          goingRight = true;
        }
      }
    }

    // Invert the last field.
    invertField();
  }

  /// Turn around at the right border.
  turnAroundRight() {
    turnRight();
    move();
    turnRight();
  }

  /// Turn around at the left border.
  turnAroundLeft() {
    turnLeft();
    move();
    turnLeft();
  }

  /// Invert a single field.
  invertField() {
    if (onStar()) {
      removeStar();
    } else {
      putStar();
    }
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 4.04: Tree Line

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  int longestRow = 0;

  /// Your program.
  start() {
    while (!onStar()) {
      if (treeFront()) {
        countRow();
      } else {
        move();
      }
    }

    say('The longest tree line is ${longestRow} trees long.');
  }

  /// Walks along a tree line and counts the trees.
  countRow() {
    int currentRow = 0;

    turnLeft();

    while (treeRight())  {
      // Increment the counter for the row by one.
      currentRow++;
      move();
    }

    // Go around the tree line.
    turnRight();
    move();
    move();
    turnRight();

    // Go back down.
    while (canMove()) {
      move();
    }

    turnLeft();

    // Test if the current tree line is longer than the previous lines.
    if (currentRow > longestRow) {
      longestRow = currentRow;
    }
  }
}
</pre>
