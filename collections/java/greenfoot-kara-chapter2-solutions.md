---
layout: article
title: "GreenfootKara - Solutions Chapter 2"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
published: true
prettify: true
comments: true
sidebars:
- header: Articles in this Series
  paging: true
  body:
  - text: "Introduction"
    link: /java/greenfoot-kara-intro/
    intro: true
  - text: "Chapter 1: First Steps"
    link: /java/greenfoot-kara-chapter1/
  - text: "Chapter 2: Program Flow"
    link: /java/greenfoot-kara-chapter2/
  - text: "Solutions Chapter 2"
    link: /java/greenfoot-kara-chapter2-solutions/
    icon-css: fa fa-fw fa-check-square-o
    paging-exclude: true
    active: true
  - text: "Chapter 3: Variables"
    link: /java/greenfoot-kara-chapter3/
  - text: "Chapter 4: Sokoban Game"
    link: /java/greenfoot-kara-chapter4/
  - text: "Chapter 5: Methods"
    link: /java/greenfoot-kara-chapter5/
- header: Downloads
  body:
  - text: scenarios-chapter-2-solutions.zip
    link: http://swisseduc.ch/informatik/karatojava/greenfootkara/classes/scenarios-chapter-2-solutions.zip
    icon-css: fa fa-fw fa-archive
  - text: chapter-2-program-flow-solutions.docx
    link: http://swisseduc.ch/informatik/karatojava/greenfootkara/docs/en/chapter-2-program-flow-solutions.docx
    icon-css: fa fa-fw fa-file-text
- header: Languages
  body:
  - text: Deutsch
    link: /java/greenfoot-kara-intro-de/
    icon-css: fa fa-fw fa-globe
---

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.01

![TASK 2.01 - Solution](/assets/java/greenfoot-kara-chapter2/task01-flowchart-solution.png)


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.02

![TASK 2.02 - Solution](/assets/java/greenfoot-kara-chapter2/task02-solution.png)


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.03

<div class="alpha-list hidden"></div>

1. Describe with words what the following code does.
  **Removes a leaf if there is one, puts a leaf if there is no leaf.**

2. Sketch it as a flowchart.

![TASK 2.03 - Solution](/assets/java/greenfoot-kara-chapter2/task03-solution.png)


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.04

<div class="alpha-list hidden"></div>

1. Describe with words what the following code does.
  **If Kara is on a leaf he makes one step forward.**
2. Sketch it as a flowchart.

![TASK 2.04 - Solution](/assets/java/greenfoot-kara-chapter2/task04-solution.png)


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.05

<div class="alpha-list hidden"></div>

1. Describe what happens when you run the program.
  **Removes the leaf if there is a tree on the left, otherwise, just makes a move.**
2. Draw a corresponding flowchart.

![TASK 2.05 - Solution](/assets/java/greenfoot-kara-chapter2/task05-solution.png)


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.06: Around Tree II

<pre class="prettyprint lang-java">
public void act() {
    if (treeFront()) {
        goAroundTree();
    } else {
        move();
    }

    if (onLeaf()) {
        removeLeaf();
        stop();
    }
}

public void goAroundTree() {
    turnLeft();
    move();
    turnRight();
    move();
    move();
    turnRight();
    move();
    turnLeft();
}
</pre>


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.07: Nested Conditions

<pre class="prettyprint lang-java">
public void act() {
    if (treeLeft()) {
        move();
    } else {
        if (onLeaf()) {
            removeLeaf();
            move();
        } else {
            move();
        }
    }
}
</pre>


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.08: Afraid of Tunnel

<pre class="prettyprint lang-java">
public void act() {
    if (treeLeft() && treeRight()) {
        putLeaf();
        stop();
    } else {
        move();
    }
}
</pre>


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.09: Leaf at Tree

<pre class="prettyprint lang-java">
public void act() {
    if (treeLeft() || treeRight()) {
        putLeaf();
        move();
    } else {
        move();
    }

    if (onLeaf()) {
        stop();
    }
}
</pre>


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.10: Put Leaf Track

<pre class="prettyprint lang-java">
public void act() {
    if (!onLeaf()) {
        putLeaf();
    }

    if (!treeFront()) {
        move();
    } else {
        stop();
    }
}
</pre>


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.11: Round Trip

<pre class="prettyprint lang-java">
public void act() {
    if (onLeaf()) {
        removeLeaf();
    } else {
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
}
</pre>


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.12: Kara Plays Pacman

<pre class="prettyprint lang-java">
public void act() {
    if (!treeFront()) {
        removeLeaf();
        findNextLeaf();
    } else {
        removeLeaf();
        stop();
    }
}

public void findNextLeaf() {
    // look for leaf in front
    // (erst mal vorne schauen)
    move();
    if (!onLeaf()) {
        // no leaf in front, go back and look left
        // (kein Blatt vorne, also zurueck und links schauen)
        turnAndGoBack();
        turnRight();
        move();
        if (!onLeaf()) {
            // no leaf left; leaf must be on right side
            // (links ist auch kein Blatt; dann muss es rechts liegen)
            turnAndGoBack();
            move();
        }
    }
}

public void turnAndGoBack() {
    turnLeft();
    turnLeft();
    move();
}
</pre>


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.13

<table class="table">
  <thead>
    <tr>
      <th>#</th>
      <th>Code</th>
      <th>Description</th>
      <th>Steps</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>a.</td>
      <td><pre>while (treeLeft()) {
  move();
}</pre></td>
      <td>Move as long as ther is no tree on the left.</td>
      <td>4</td>
    </tr>
    
    <tr>
      <td>b.</td>
      <td><pre>while (treeRight()) {
  move();
}</pre></td>
      <td>Move as long as ther is no tree on the right.</td>
      <td>0</td>
    </tr>
    
    <tr>
      <td>c.</td>
      <td><pre>while (treeLeft() || treeRight()) {
  move();
}</pre></td>
      <td>Move as long there is a tree either on the right or on the left side.</td>
      <td>5</td>
    </tr>
    
    <tr>
      <td>d.</td>
      <td><pre>if (treeLeft()) {
  move();
} while (treeLeft() && treeRight()) {
  move();
}</pre></td>
      <td>First, move if there is a tree on the left side.
Then move as long as there is a tree on the right and on the left side.
</td>
      <td>4</td>
    </tr>

    <tr>
      <td>e.</td>
      <td><pre>while (!treeFront) {
  if (treeLeft()) {
    move();
  }
}</pre></td>
      <td>As long as thre is no tree in front of Kara: If there is a tree on the left, make a step.

**Warning: never-ending loop**
</td>
      <td>4</td>
    </tr>
    
  </tbody>
</table>


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.14: Around Tree III

<pre class="prettyprint lang-java">
public void act() {
    while (!onLeaf()) {
        if (treeFront()) {
            goAroundTree();
        } else {
            move();
        }
    }

    // Found leaf --> eat it
    removeLeaf();

    stop();
}

public void goAroundTree() {
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
</pre>


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.15: Climbing Up

<pre class="prettyprint lang-java">
public void act() {
    while (treeFront()) {
        oneStepUp();
    }

    stop();
}

public void oneStepUp() {
    turnLeft();
    move();
    turnRight();
    move();
}
</pre>


* * *

#### <i class="fa fa-check-square-o"></i> SOLUTION TASK 2.16: Kara as Guard

<pre class="prettyprint lang-java">
public void act() {
    makeOneStep();
}

public void makeOneStep() {
    if (!treeRight()) {
        // no tree right --> go right
        turnRight();
        move();
    } else {
        // there is a tree right
        if (!treeFront()) {
            // no tree in front --> move
            move();
        } else {
            // trees right and front
            if (!treeLeft()) {
                // no tree left --> go left
                turnLeft();
                move();
            } else {
                // trees right, front and left: dead end
                turnLeft();
                turnLeft();
                move();
            }
        }
    }
}
</pre>

