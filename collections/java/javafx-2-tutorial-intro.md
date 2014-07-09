---
layout: article
title: "JavaFX 2 Tutorial - Introduction"
date: 2012-11-16 20:00
updated: 2013-02-08 00:00
github: https://github.com/marcojakob/code.makery.ch/blob/master/collections/java/javafx-2-tutorial-intro.md
description: "This seven-part tutorial walks through designing, programming and deploying an address application with JavaFX."
published: true
prettify: false
comments: false
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /java/javafx-2-tutorial-intro
    paging: Intro
    active: true
  - text: "Part 1: Scene Builder"
    link: /java/javafx-2-tutorial-part1/
    paging: 1
  - text: "Part 2: Model and TableView"
    link: /java/javafx-2-tutorial-part2/
    paging: 2
  - text: "Part 3: Interacting with the User"
    link: /java/javafx-2-tutorial-part3/
    paging: 3
  - text: "Part 4: CSS Styling"
    link: /java/javafx-2-tutorial-part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /java/javafx-2-tutorial-part5/
    paging: 5
  - text: "Part 6: Statistics Chart"
    link: /java/javafx-2-tutorial-part6/
    paging: 6
  - text: "Part 7: Deployment with e(fx)clipse"
    link: /java/javafx-2-tutorial-part7/
    paging: 7
---

<div class="alert alert-danger">
  &rarr; UPDATED VERSION for Java 8 available: <a href="/java/javafx-8-tutorial-intro/" class="alert-link">JavaFX 8 Tutorial</a>
</div>

JavaFX provides Java developers with a very nice graphics platform. In October 2011, JavaFX 2.0 was released. It was intended to be the choice for new graphical user interfaces (GUI) rather than the older Java Swing. When I started teaching JavaFX in 2012 it was still very early. There were no books covering JavaFX that I found **suitable for students new to programming**. So I started to write a very detailed tutorial series on JavaFX. 

![JavaFX Logo](/assets/java/javafx-2-tutorial-intro/javafx-logo.png)

The tutorial walks you through designing, programming and deploying an address application with JavaFX 2. This is how the final appllication will look like:

![Screenshot AddressApp Part 1](/assets/java/javafx-2-tutorial-intro/addressapp01.png)


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

This is quite a lot! So, after you you've completed this tutorial series you should be ready to build sophisticated applications with JavaFX.



## How to use this Tutorial

There are two ways to use this tutorial:

* **learn-a-lot track:** Create your own JavaFX project from the ground up.
* **fast track:** Import the source code for a tutorial part into your IDE (it's an Eclipse project, but you could use other IDEs like NetBeans with slight modifications). Then go through the tutorial to understand the code. This approach is also helpful whenever you get stuck when you're on the *learn-a-lot track*!

Now, I hope you'll have fun and learn a lot! Start with [Part 1: Scene Builder](/java/javafx-2-tutorial-part1/).