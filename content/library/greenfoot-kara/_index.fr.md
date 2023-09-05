+++
title = "GreenfootKara"
date = 2014-07-10
canonical = "/library/greenfoot-kara/"
image = "greenfootkara-screenshot.png"
description = "GreenfootKara is a visual and interactive introduction to programming in Java based on the Greenfoot IDE."
prettify = false
# comments = true
commentsIdentifier = "/library/greenfoot-kara/fr/"
aliases = [ 
  "/library/greenfoot-kara/fr/" 
]

sidebarName = "Introduction"
pagingName = "Intro"

[[sidebars]]
header = "Downloads"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Page en fichier Word"
link = "/library/convert-web-page-to-word/"
+++

<div class="alert alert-warning">
  <i class="fa fa-language"></i> Cette page a besoin d'une traduction française. Si vous voulez nous aider, lisez <a href="/library/how-to-contribute/" class="alert-link">comment contribuer</a>.
</div>

> `GreenfootKara` is a visual and interactive introduction to programming based on the [Greenfoot IDE](https://www.greenfoot.org). It combines the proven concept of the mini-world `Kara` with `Greenfoot`'s flexibility and ease of use.

**Note:** If you prefer to work with an IDE like **Eclipse or NetBeans** instead of Greenfoot, take a look at [GameGridKara](/library/gamegrid-kara/).


## Greenfoot

[Greenfoot](https://www.greenfoot.org) is a simple development environment for Java. It allows very rapid development of interactive, graphical projects.

![GreenfootKara](greenfootkara-screenshot.png)

If you're a teacher you can provide Greenfoot scenarios at any level of difficulty for your students. `GreenfootKara` contains many such scenarios with the ladybug Kara.

Greenfoot is great for novice programmers because one doesn't have to deal with many of the difficulties when starting to program (like `public static void main(String [])` and friends). Nevertheless, Greenfoot is real Java and leaves all options open. Thus, advanced students can also implement very complex projects.


## Kara

Kara is a ladybug that lives in a simple world with trees, leafs and mushrooms.

![Kara](kara.png) ![Mushroom](mushroom.png) ![Leaf](leaf.png) ![Tree](tree.png)

The rules of Kara's world are simple:


### Kara's Actions

* Kara can move around with `move()` (Kara can push a mushroom, can step on leafs but can't walk through trees)
* Kara turns left or right with `turnLeft()` or `turnRight()`
* Kara puts down a leaf with `putLeaf()`
* Kara picks up a leaf with `removeLeaf()`


### Kara's Sensors

* Kara checks if he stands on a leaf with `onLeaf()`
* Kara checks if there is a tree with `treeFront()`, `treeLeft()`, or `treeRight()`
* Kara checks if there is a mushroom in front with `mushroomFront()`


***

## Get Started

Download and install [Greenfoot](https://www.greenfoot.org/download). After the installation you should be able to open and compile the predefined Kara scenarios (see downloads on the following pages).

To jump right in go to [Chapter 1: First Steps](/library/greenfoot-kara/chapter1/).

To get more background about `GreenfootKara` and its design decisions visit [Background Info](/library/greenfoot-kara/background/) (is especially recommmended for teachers).

