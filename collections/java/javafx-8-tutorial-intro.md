---
layout: article
title: "JavaFX 8 Tutorial - Introduction"
date: 2014-04-19 00:00
updated: 2014-08-27 00:00
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/java/javafx-8-tutorial-intro.md
description: "This seven-part tutorial walks through designing, programming and deploying an address application with JavaFX."
image: /assets/java/javafx-8-tutorial-intro/addressapp.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /java/javafx-8-tutorial-intro
    paging: Intro
    active: true
  - text: "Part 1: Scene Builder"
    link: /java/javafx-8-tutorial-part1/
    paging: 1
  - text: "Part 2: Model and TableView"
    link: /java/javafx-8-tutorial-part2/
    paging: 2
  - text: "Part 3: Interacting with the User"
    link: /java/javafx-8-tutorial-part3/
    paging: 3
  - text: "Part 4: CSS Styling"
    link: /java/javafx-8-tutorial-part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /java/javafx-8-tutorial-part5/
    paging: 5
  - text: "Part 6: Statistics Chart"
    link: /java/javafx-8-tutorial-part6/
    paging: 6
  - text: "Part 7: Deployment"
    link: /java/javafx-8-tutorial-part7/
    paging: 7
---


Back in 2012 I wrote a very detailed [JavaFX 2 tutorial series](/java/javafx-2-tutorial-intro/) for my students. Many people all over the world have been reading the tutorial and gave very positive feedback. So I decided to **rewrite the JavaFX 2 tutorial for JavaFX 8** (read about what changed in [Update to JavaFX 8 - What's New](/blog/update-to-javafx-8-whats-new/)).

This tutorial walks you through designing, programming and deploying an address application. This is how the final appllication will look like:

![Screenshot AddressApp](/assets/java/javafx-8-tutorial-intro/addressapp.png)


## What you will learn

* Creating and starting a JavaFX project
* Using Scene Builder to design the user interface
* Structuring an application with the Model View Controller (MVC) pattern
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

Now, I hope you'll have fun! Start with [Part 1: Scene Builder](/java/javafx-8-tutorial-part1/).