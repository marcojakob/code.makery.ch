+++
title = "Solutions"
date = 2015-05-01
description = "Solutions for Hello Dart Part 2."
prettify = true
comments = true
commentsIdentifier = "/library/hello-dart/part2-solutions/"
aliases = [ 
  "/library/hello-dart/part2-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Solutions"

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Page as Word File"
link = "/library/convert-web-page-to-word/"
+++

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