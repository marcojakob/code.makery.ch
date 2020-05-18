+++
title = "Solutions"
date = 2015-05-01
description = "Solutions for Hello Dart Part 4."
prettify = true
comments = true
commentsIdentifier = "/library/hello-dart/part4-solutions/"
aliases = [ 
  "/library/hello-dart/part4-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Solutions"

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Page as Word File"
link = "/library/convert-web-page-to-word/"
+++

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

#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 4.04: Chessboard

(Solution is missing)

#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 4.05: Tree Line

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
