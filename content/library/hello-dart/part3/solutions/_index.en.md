+++
title = "Solutions"
date = 2015-05-01
description = "Solutions for Hello Dart Part 3."
prettify = true
comments = true
commentsIdentifier = "/library/hello-dart/part3-solutions/"
aliases = [ 
  "/library/hello-dart/part3-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Solutions"

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Page as Word File"
link = "/library/convert-web-page-to-word/"
+++

#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 3.01: Conditionals

##### a.

Removes a star if there is one.


##### b.

Removes a star if there is one, puts down a star if there is none. This means the star pattern is inverted.


##### c.

Removes the star if there is a tree on the left.


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 3.02: Star Track

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (canMove()) {
      if (!onStar()) {
        putStar();
      }
      move();
    }
    putStar();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 3.03: Star at Tree

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (canMove()) {
      if (treeLeft() || treeRight()) {
        putStar();
      }
      move();
    }
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 3.04: Around Tree II

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!onStar()) {
      if (treeFront()) {
        goAroundTree();
      } else {
        move();
      }
    }

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


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 3.05: Round Trip

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!onStar()) {
      if (!treeFront()) {
        move();
      } else {
        if (!treeLeft()) {
          turnLeft();
          move();
        } else {
          turnRight();
          move();
        }
      }
    }

    removeStar();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 3.06: Around Tree III

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!onStar()) {
      if (treeFront()) {
        goAroundTree();
      } else {
        move();
      }
    }

    removeStar();
  }

  goAroundTree() {
    turnLeft();
    move();
    turnRight();
    move();

    while (treeRight()) {
      move();
    }

    turnRight();
    move();
    turnLeft();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 3.07: Follow the Trail

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (!treeFront()) {
      removeStar();
      findNextStar();
    }

    removeStar();
  }

  /// Function to find the next star.
  findNextStar() {
    // First look in the front.
    move();
    if (!onStar()) {

      // No star in front, get back and try on the left.
      turnAndGoBack();
      turnRight();
      move();
      if (!onStar()) {

        // No star on the left, it must be on the right.
        turnAndGoBack();
        move();
      }
    }
  }

  /// Function to go back one step.
  turnAndGoBack() {
    turnLeft();
    turnLeft();
    move();
  }
}
</pre>


#### <i class="fa fa-check-square-o mg-t-lg"></i> SOLUTION TASK 3.08: Guard

<pre class="prettyprint lang-dart">
class MyPlayer extends Player {

  start() {
    while (true) {
      makeOneStep();
    }
  }

  makeOneStep() {
    if (!treeRight()) {
      // No tree right --> go to the right.
      turnRight();
      move();
    } else {
      // There is a tree on the right.
      if (!treeFront()) {
        // No tree in front --> make step forward.
        move();
      } else {
        // Bäume rechts und vorne.
        if (!treeLeft()) {
          // No tree left --> go to the left.
          turnLeft();
          move();
        } else {
          // Trees right, in front, and left --> dead end.
          turnLeft();
          turnLeft();
          move();
        }
      }
    }
  }
}
</pre>
