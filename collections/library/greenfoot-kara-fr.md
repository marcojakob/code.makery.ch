---
layout: article
title: "GreenfootKara"
date: 2014-07-10 00:00
slug: greenfoot-kara/fr
canonical: /library/greenfoot-kara/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-fr.md
published: true
prettify: false
comments: true
sidebars:
- header: Articles de cette Série
  body:
  - text: "Introduction"
    link: /library/greenfoot-kara/fr/
    paging: Intro
    active: true
  - text: "Teacher's Info"
    link: /library/greenfoot-kara/fr/teachers-info/
    icon-css: fa fa-fw fa-info
  - text: "Chapitre 1: Premiers Pas"
    link: /library/greenfoot-kara/fr/chapter1/
    paging: 1
  - text: "Chapitre 2: Ordinogramme"
    link: /library/greenfoot-kara/fr/chapter2/
    paging: 2
  - text: "Chapitre 3: Variables"
    link: /library/greenfoot-kara/fr/chapter3/
    paging: 3
  - text: "Chapitre 4: Jeu Sokoban"
    link: /library/greenfoot-kara/fr/chapter4/
    paging: 4
  - text: "Chapitre 5: Méthodes"
    link: /library/greenfoot-kara/fr/chapter5/
    paging: 5
- header: Downloads
  body:
  - text: Page as Word File
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-word-o
- header: Langues
  body:
  - text: English
    link: /library/greenfoot-kara/
    icon-css: fa fa-fw fa-globe
  - text: Deutsch
    link: /library/greenfoot-kara/de/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/greenfoot-kara/fr/
    icon-css: fa fa-fw fa-globe
    active: true
---

<div class="alert alert-warning">
  <i class="fa fa-language"></i> This page needs a French translation. If you'd like to help out please read <a href="/library/how-to-contribute/" class="alert-link">how to contribute</a>.
</div>

> `GreenfootKara` is a visual and interactive introduction to programming based on the [Greenfoot IDE](http://www.greenfoot.org). It combines the proven concept of the mini-world `Kara` with `Greenfoot`'s flexibility and ease of use.

*Note: If you prefer to work with a professional IDE like **Eclipse or NetBeans** instead of Greenfoot, take a look at [GameGridKara](/library/gamegrid-kara/).*


## Greenfoot

[Greenfoot](http://www.greenfoot.org) is a simple development environment for Java. It allows very rapid development of interactive, graphical projects.

![GreenfootKara](/assets/library/greenfoot-kara/greenfootkara-screenshot.png)

If you're a teacher you can provide Greenfoot scenarios at any level of difficulty for your students. `GreenfootKara` contains many such scenarios with the ladybug Kara.

Greenfoot is great for novice programmers because one doesn't have to deal with many of the difficulties when starting to program (like `public static void main(String [])` and friends). Nevertheless, Greenfoot is real Java and leaves all options open. Thus, advanced students can also implement very complex projects.


## Kara

Kara is a ladybug that lives in a simple world with trees, leafs and mushrooms.

![Kara](/assets/library/greenfoot-kara/kara.png) ![Mushroom](/assets/library/greenfoot-kara/mushroom.png) ![Leaf](/assets/library/greenfoot-kara/leaf.png) ![Tree](/assets/library/greenfoot-kara/tree.png)

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

Download and install [Greenfoot](http://www.greenfoot.org/download). After the installation you should be able to open and compile the predefined Kara scenarios (see downloads on the following pages).

To jump right in go to [Chapter 1: First Steps](/library/greenfoot-kara/chapter1/).

To get more background about `GreenfootKara` and its design decisions visit [Teacher's Info](/library/greenfoot-kara/teachers-info/) (is especially recommmended for teachers).

