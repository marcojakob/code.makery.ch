---
layout: article
title: "Hello Dart - Solutions Part 3"
date: 2015-04-18 00:00
slug: hello-dart/part3-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-en-part3-solutions.md
description: "Solutions for Hello Dart Part 3."
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
  - text: "Solutions Part 3"
    link: /library/hello-dart/part3-solutions/
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
  part: part3-solutions
  active: en
---

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
      findNextLeaf();
    }

    removeStar();
  }

  /// Function to find the next star.
  findNextLeaf() {
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
