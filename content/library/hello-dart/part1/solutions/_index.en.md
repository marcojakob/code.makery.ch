+++
title = "Solutions"
date = 2015-05-01
description = "Solutions for Hello Dart Part 1."
prettify = true
comments = true
commentsIdentifier = "/library/hello-dart/part1-solutions/"
aliases = [ 
  "/library/hello-dart/part1-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Solutions"

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Page as Word File"
link = "/library/convert-web-page-to-word/"
+++

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

  createWorld('scenario.txt', MyPlayer());
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

The **comments** in the source code have been omitted (that is the text that is displayed in green in the Editor).

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