---
layout: article
title: "GreenfootKara - Introduction"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara
published: true
prettify: false
comments: false
sidebars:
- header: Articles in this Series
  body:
  - text: "Introduction"
    link: /tutorials/greenfoot-kara/
    paging: Intro
    active: true
  - text: "Teacher's Info"
    link: /tutorials/greenfoot-kara/teachers-info/
    icon-css: fa fa-fw fa-info
  - text: "Chapter 1: First Steps"
    link: /tutorials/greenfoot-kara/chapter1/
    paging: 1
  - text: "Chapter 2: Program Flow"
    link: /tutorials/greenfoot-kara/chapter2/
    paging: 2
  - text: "Chapter 3: Variables"
    link: /tutorials/greenfoot-kara/chapter3/
    paging: 3
  - text: "Chapter 4: Sokoban Game"
    link: /tutorials/greenfoot-kara/chapter4/
    paging: 4
  - text: "Chapter 5: Methods"
    link: /tutorials/greenfoot-kara/chapter5/
    paging: 5
- header: Downloads
  body:
  - text: Page as Word File
    link: /tutorials/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-text
- header: Languages
  body:
  - text: English
    link: /tutorials/greenfoot-kara/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Deutsch
    link: /tutorials/greenfoot-kara/de/
    icon-css: fa fa-fw fa-globe
---

> `GreenfootKara` is a visual and interactive introduction to programming based on the [Greenfoot IDE](http://www.greenfoot.org). It combines the proven concept of the mini-world `Kara` with `Greenfoot`’s flexibility and ease of use.

*Note: If you prefer to work with a professional IDE like **Eclipse or NetBeans** instead of Greenfoot, take a look at [GameGridKara](/tutorials/gamegrid-kara/).*


## Greenfoot

[Greenfoot](http://www.greenfoot.org) is a simple development environment for Java. It allows very rapid development of interactive, graphical projects.

![GreenfootKara](/assets/tutorials/greenfoot-kara/greenfootkara-screenshot.png)

If you're a teacher you can provide Greenfoot scenarios at any level of difficulty for your students. `GreenfootKara` contains many such scenarios with the ladybug Kara.

Greenfoot is great for novice programmers because one doesn’t have to deal with many of the difficulties when starting to program (like `public static void main(String [])` and friends). Nevertheless, Greenfoot is real Java and leaves all options open. Thus, advanced students can also implement very complex projects.


## Kara

Kara is a ladybug that lives in a simple world with trees, leafs and mushrooms.

![Kara](/assets/tutorials/greenfoot-kara/kara.png) ![Mushroom](/assets/tutorials/greenfoot-kara/mushroom.png) ![Leaf](/assets/tutorials/greenfoot-kara/leaf.png) ![Tree](/assets/tutorials/greenfoot-kara/tree.png)

The rules of Kara’s world are simple:


### Kara's Actions

* Kara can move around with `move()` (Kara can push a mushroom, can step on leafs but can’t walk through trees)
* Kara turns left or right with `turnLeft()` or `turnRight()`
* Kara puts down a leaf with `putLeaf()`
* Kara picks up a leaf with `removeLeaf()`


### Kara's Sensors

* Kara checks if he stands on a leaf with `onLeaf()`
* Kara checks if there is a tree with `treeFront()`, `treeLeft()`, or `treeRight()`
* Kara checks if there is a mushroom in front with `mushroomFront()`


***

## Get Started

Download and install [Greenfoot](http://www.greenfoot.org/download). After the installation you should be able to open and compile the predefined Kara scenarios (see downloads on the following pages).

To jump right in go to [Chapter 1: First Steps](/tutorials/greenfoot-kara/chapter1/).

To get more background about `GreenfootKara` and its design decisions visit [Teacher's Info](/tutorials/greenfoot-kara/teachers-info/) (is especially recommmended for teachers).

