---
layout: article
title: "JavaFX 8 Tutorial - Introduction"
date: 2014-04-19 00:00
published: false
prettify: true
comments: true
sidebars:
- header: "Articles in this Series"
  paging: true
  body:
  - text: "Introduction"
    link: /java/javafx-8-tutorial-intro
    intro: true
    active: true
  - text: "Part 1: Scene Builder"
    link: /java/javafx-8-tutorial-part1/
  - text: "Part 2: Model and TableView"
    link: /java/javafx-8-tutorial-part2/
  - text: "Part 3: Interacting with the User"
    link: /java/javafx-8-tutorial-part3/
  - text: "Part 4: CSS Styling"
    link: /java/javafx-8-tutorial-part4/
  - text: "Part 5: Storing Data as XML"
    link: /java/javafx-8-tutorial-part5/
  - text: "Part 6: Statistics Chart"
    link: /java/javafx-8-tutorial-part6/
  - text: "Part 7: Deployment with e(fx)clipse"
    link: /java/javafx-8-tutorial-part7/
---

JavaFX 8 is included in JDK 8 and is the **officially recommended graphics toolkit for Java 8 applications**. I've been using JavaFX 2 since 2012 and am very pleased as it was already a major step forward compared to Swing. Now with JavaFX 8 it has become even better, with new UI controls like [DatePicker](/blog/javafx-8-date-picker), a modern theme called *Modena*, rich text support, to just name a few. 

![JavaFX Logo](/assets/java/javafx-8-tutorial-intro/javafx-logo.png)


From the start, JavaFX 8 has been designed to support the new **Java 8 language features**. Especially *Lambdas* make the UI code much more pleasant. An example of how to provide custom cell rendering with and without *Lambdas*:

<pre class="prettyprint lang-java">
// With Lambdas
myListView.setCellFactory(c -> new MyCustomCell());

// Without Lambdas
myListView.setCellFactory(new Callback&lt;ListView&lt;Person>, ListCell&lt;Person>>() {
    @Override
    public ListCell&lt;Person> call(ListView&lt;Person> param) {
      return new MyCustomCell();
    }
});

</pre>


## About this Tutorial Series

Back in 2012 I wrote a very detailed [JavaFX 2 tutorial series](/java/javafx-2-tutorial-intro) for my students. Many people all over the world have been reading the tutorial and gave very positive feedback. So I decided to **rewrite the JavaFX 2 tutorial for JavaFX 8**.

The tutorial walks you through designing, programming and deploying an address application. This is how the final appllication will look like:

![Screenshot AddressApp Part 1](/assets/java/javafx-8-tutorial-intro/addressapp01.png)


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

Now, I hope you'll have fun! Start with [Part 1: Scene Builder](/java/javafx-8-tutorial-part1/).