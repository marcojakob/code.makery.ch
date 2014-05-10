---
layout: article
title: "GreenfootKara - Introduction"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
published: true
prettify: false
comments: true
sidebars:
- header: Articles in this Series
  body:
  - text: "Introduction"
    link: /java/greenfoot-kara-intro/
    paging: Intro
    active: true
  - text: "Teacher's Info"
    link: /java/greenfoot-kara-teachers-info/
    icon-css: fa fa-fw fa-info
  - text: "Chapter 1: First Steps"
    link: /java/greenfoot-kara-chapter1/
    paging: 1
  - text: "Chapter 2: Program Flow"
    link: /java/greenfoot-kara-chapter2/
    paging: 2
  - text: "Chapter 3: Variables"
    link: /java/greenfoot-kara-chapter3/
    paging: 3
  - text: "Chapter 4: Sokoban Game"
    link: /java/greenfoot-kara-chapter4/
    paging: 4
  - text: "Chapter 5: Methods"
    link: /java/greenfoot-kara-chapter5/
    paging: 5
- header: Languages
  body:
  - text: Deutsch
    link: /java/greenfoot-kara-intro-de/
    icon-css: fa fa-fw fa-globe
---

> `GreenfootKara` is a visual and interactive introduction to programming based on the [Greenfoot IDE](http://www.greenfoot.org). It combines the proven concept of the mini-world `Kara` with `Greenfoot`’s flexibility and ease of use.

*Note: If you prefer to work with a professional IDE like Eclipse or NetBeans instead of Greenfoot, take a look at [GameGridKara](/java/gamegrid-kara-intro).*


## Greenfoot

[Greenfoot](http://www.greenfoot.org) is a simple development environment for Java. It allows very rapid development of interactive, graphical projects.

![Tree](/assets/java/greenfoot-kara-intro/greenfootkara-screenshot.png)

If you're a teacher you can provide Greenfoot scenarios at any level of difficulty for your students. `GreenfootKara` contains many such scenarios with the ladybug Kara.

Greenfoot is great for novice programmers because one doesn’t have to deal with many of the difficulties when starting to program (like `public static void main(String [])` and friends). Nevertheless, Greenfoot is real Java and leaves all options open. Thus, advanced students can also implement very complex projects.


## Kara

Kara is a ladybug that lives in a simple world with trees, leafs and mushrooms.

![Kara](/assets/java/greenfoot-kara-intro/kara.png) ![Mushroom](/assets/java/greenfoot-kara-intro/mushroom.png) ![Leaf](/assets/java/greenfoot-kara-intro/leaf.png) ![Tree](/assets/java/greenfoot-kara-intro/tree.png)

The rules of Kara’s world are simple:


### Kara's Actions

* Kara can move aroudn with `move()` (Kara can push a mushroom, can step on leafs but can’t walk through trees)
* Kara turns left or right with `turnLeft()` or `turnRight()`
* Kara puts down a leaf with `putLeaf()`
* Kara picks up a leaf with `removeLeaf()`


### Kara's Sensors

* Kara checks if he stands on a leaf with `onLeaf()`
* Kara checks if there is a tree with `treeFront()`, `treeLeft()`, or `treeRight()`
* Kara checks if there is a mushroom in front with `mushroomFront()`


* * *

## Get Started

Download and install [Greenfoot](http://www.greenfoot.org/download). After the installation you should be able to open and compile the predefined Kara scenarios (see downloads on the following pages).

To jump right in go to [Chapter 1: First Steps](/java/greenfoot-kara-chapter1/).

To get mor background about `GreenfootKara` and its design decisions visit [Teacher's Info](/java/greenfoot-kara-teachers-info/) (is especially recommmended for teachers).

