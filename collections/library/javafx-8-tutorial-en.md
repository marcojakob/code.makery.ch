---
layout: article
title: "JavaFX 8 Tutorial"
date: 2014-04-19 00:00
updated: 2015-03-12 00:00
slug: javafx-8-tutorial
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-en.md
description: "This seven-part tutorial walks through designing, programming and deploying an address application with JavaFX."
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: 
  identifier: /java/javafx-8-tutorial-intro/
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /library/javafx-8-tutorial/
    paging: Intro
    active: true
  - text: "Part 1: Scene Builder"
    link: /library/javafx-8-tutorial/part1/
    paging: 1
  - text: "Part 2: Model and TableView"
    link: /library/javafx-8-tutorial/part2/
    paging: 2
  - text: "Part 3: Interacting with the User"
    link: /library/javafx-8-tutorial/part3/
    paging: 3
  - text: "Part 4: CSS Styling"
    link: /library/javafx-8-tutorial/part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /library/javafx-8-tutorial/part5/
    paging: 5
  - text: "Part 6: Statistics Chart"
    link: /library/javafx-8-tutorial/part6/
    paging: 6
  - text: "Part 7: Deployment"
    link: /library/javafx-8-tutorial/part7/
    paging: 7
- header: Languages
  languages: true
  body:
  - text: English
    link: /library/javafx-8-tutorial/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Português
    link: /library/javafx-8-tutorial/pt/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/
    icon-css: fa fa-fw fa-globe
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/javafx-8-tutorial/fr/
    icon-css: fa fa-fw fa-globe
---


Back in 2012 I wrote a very detailed [JavaFX 2 tutorial series](/library/javafx-2-tutorial/) for my students. Many people all over the world have been reading the tutorial and gave very positive feedback. So I decided to **rewrite the JavaFX 2 tutorial for JavaFX 8** (read about what changed in [Update to JavaFX 8 - What's New](/blog/update-to-javafx-8-whats-new/)).

This tutorial walks you through designing, programming and deploying an address application. This is how the final application will look like:

![Screenshot AddressApp](/assets/library/javafx-8-tutorial/addressapp.png)


## What you will learn

* Creating and starting a JavaFX project
* Using Scene Builder to design the user interface
* Structuring an application with the Model-View-Controller (MVC) pattern
* Using `ObservableLists` for automatically updating the user interface
* Using `TableView` and reacting to selection changes in the table
* Create a custom popup dialog to edit persons
* Validating user input
* Styling a JavaFX application with CSS
* Persisting data as XML
* Saving the last opened file path in user preferences
* Creating a JavaFX chart for statistics
* Deploying a JavaFX application as a native package

**This is quite a lot!** So, after you you've completed this tutorial series you should be ready to build sophisticated applications with JavaFX.


## How to use this Tutorial

There are two ways to use this tutorial:

* **learn-a-lot track:** Create your own JavaFX project from the ground up.
* **fast track:** Import the source code for a tutorial part into your IDE (it's an Eclipse project, but you could use other IDEs like NetBeans with slight modifications). Then go through the tutorial to understand the code.

Now, I hope you'll have fun! Start with [Part 1: Scene Builder](/library/javafx-8-tutorial/part1/).