---
layout: article
title: "GreenfootKara - Teacher's Info"
date: 2012-10-03 00:00
slug: greenfoot-kara/teachers-info
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-en-teachers-info.md
published: true
prettify: true
comments: true
sidebars:
- header: Articles in this Series
  body:
  - text: "Introduction"
    link: /library/greenfoot-kara/
    paging: Intro
  - text: "Teacher's Info"
    link: /library/greenfoot-kara/teachers-info/
    icon-css: fa fa-fw fa-info
    active: true
  - text: "Chapter 1: First Steps"
    link: /library/greenfoot-kara/chapter1/
    paging: 1
  - text: "Chapter 2: Program Flow"
    link: /library/greenfoot-kara/chapter2/
    paging: 2
  - text: "Chapter 3: Variables"
    link: /library/greenfoot-kara/chapter3/
    paging: 3
  - text: "Chapter 4: Sokoban Game"
    link: /library/greenfoot-kara/chapter4/
    paging: 4
  - text: "Chapter 5: Methods"
    link: /library/greenfoot-kara/chapter5/
    paging: 5
- header: Downloads
  body:
  - text: GreenfootKara Sources on GitHub
    link: https://github.com/marcojakob/greenfoot-kara
    icon-css: fa fa-fw fa-github-alt
  - text: Page as Word File
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-word-o
- header: Languages
  languages: true
  body:
  - text: English
    link: /library/greenfoot-kara/teachers-info/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Deutsch
    link: /library/greenfoot-kara/de/teachers-info/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/greenfoot-kara/fr/teachers-info/
    icon-css: fa fa-fw fa-globe
---

In this article you will find background information on how to work with `GreenfootKara`. This might be especially helpful if you are a teacher.

When used in schools, the entire `GreenfootKara` material will take about **16-20 lessons**. 


## Topics Covered

* *1 lesson:* Installing Greenfoot, starting Greenfoot projects
* *3 lessons:* Getting to know Kara, creating objects and calling methods with the mouse, reading source code, writing the first program (chapter 1)
* *3-4 lessons:* flow diagram, control structures (conditions and loops), boolean and logic operators (chapter 2)
* *3-4 lessons:* Variables, data types and operators, the structure of a class, nested statements (chapter 3)
* *3-4 lessons:* Programming a game, keyboard input, comparing Strings, reading Javadoc, designing ASCII levels, publishing the program on the Greenfoot website (chapter 4)
* *3 lessons:* Writing own methods with parameters and return values, repetition (chapter 5)


### How to Continue after GreenfootKara

* *Option 1:* Develop a custom project with Greenfoot
* *Option 2:* Start using a professional development environment like Eclipse, Netbeans, etc. (see [GameGridKara](/library/gamegrid-kara/))
* *Option 3:* Start [GUI-programming with JavaFX](/java/javafx-2-tutorial-intro/)


***

## The Kara Scenario

To use the Kara-Scenario [Greenfoot](http://www.greenfoot.org) must be installed first.

Run the setup program and follow the installation instructions.
After installation, any Kara-Scenario may be opened using the `project.greenfoot` file in the scenario folder.

For each exercise there is a separate scenario (see folder *scenarios-chapter-1*, *scenarios-schapter-1-solutions*, etc.). In each scenario, the world of Kara (with trees, leafs, etc.), is already prepared for each exercise.

The possibilities of Kara remain the same for all exercises, except for chapter 4 and 5. In Chapter 4, Kara has a additional methods so that a Sokoban game can be programmed. In Chapter 5, Kara has a few additional methods to show messages and to ask the user for input. 


### The Classes Kara and MyKara

The most important classes are `Kara` and `MyKara`. The class Kara includes all the functionality of the ladybug Kara as seen above. But programming is always done in the class `MyKara` which, through inheritance, can accessed all the methods of `Kara`. Thus, the **complexity of `Kara`'s methods are hidden from the students at first**.

Later, the students may choose to find out how the methods were implemented in Kara itself. For this step it is recommended that they first view the class in the *documentation mode*. This only shows the Javadoc comments inside the Greenfoot editor.


### Starting with Mouse Only

Objects of classes can be instantiated through a *right-click*, *new ....()* and can then be placed in the world. (Tip: by  pressing the shift-key you can place multiple instances in the world without using the context menu).

On first contact with `GreenfootKara` it is helpful to only use the mouse. If you *right-click* on a Kara-object, all available methods are shown and can be selected with the mouse. This way one can get accustomed with how Kara works.


### Programming

The programming can be done inside the `act()`-method of `MyKara`.
This method is executed when the Act-button is pressed. When the Run-button is pressed, the `act()`-method is called repeatedly.


***

## Creating your own Scenarios

To create your own scenarios I recommend you copy an existing scenario and make changes to the `WorldSetup.txt` file.

*Note: If you want to use a name other than `WorldSetup.txt`, adjust the constant `WORLD_SETUP_FILE` in the `KaraWorld` class.*

A world setup file **may contain multiple worlds**. Each world must start with the following three lines:

<pre class="prettyprint">
World: [Your title]
X: [Width of the world]
Y: [Height of the world]
[Your actors]
</pre>

Actors are represented as follows:

* `#`: Tree
* `@`: Kara
* `.`: Leaf
* `$`: Mushroom
* `*`: Mushroom on a Leaf
* `+`: Kara on a Leaf

**Tip:** Create the world inside the Greenfoot editor and use right-click on the world | *saveWorldSetupToFile()* or *printWorldSetupToConsole()* to save the created world.


***

## Scenario Sources on GitHub

If you want to make adjustments to GreenfootKara or would like to report a bug, take a look at the [GreenfootKara Repository on GitHub](https://github.com/marcojakob/greenfoot-kara). This repository contains an Eclipse project with the source of GreenfootKara and all the scenarios.


*** 

## General Tips

### Greenfoot Editor Tips

* Often the students have trouble with cleanly structuring the code. The editor helps you format by an autolayout feature found inside the Edit menu.
* Ctrl-Space will open a pop-up for auto completion.
* Top right in the editor you can switch the view from Source Code to Documentation.
* In the menu *Options | Preferences ...* the font size can be changed (e.g. for presentation with a projector).


### Screen Output and User Input

There are several ways to interact with the user through Greenfoot over Input/Output:

* `System.out.println(...)` will write something on the console.
* With a Swing dialog (e.g. `JOptionPane`): This is used in `KaraIO` (Chapter 5).
* Drawing labels: This is the most complex version but also elegant as the input and output appears directly on the world and not in a pop-up dialog. An example can be found in `KaraSokoban` (Chapter 4).


### Sharing a Scenario with Others (Deployment)

With Greenfoot, scenarios can easily be exported and shared with others. There are three ways to this:

* Upload the scenario on the [Greenfoot Gallery](http://greenfootgallery.org). In the Greenfoot Gallery it can be directly started as an applet. Even the highscore of the Sokoban game should work.
* Export the scenario as a runnable jar application.
* Create a custom website with the scenario.


*** 

## Known Bugs

### The World Disappears

It is possible that Greenfoot blocks and the world of Kara is no longer drawn. Even recompiling or pressing reset does not help.

**Reason:** This happens if the program remains too long inside the act method and then the user tries to interrupt the program. This is a problem that is known for Greenfoot but it is difficult to solve. Indeed, it is hardly possible in Java to stop a running method from the outside.

**Solution:** Exit and restart Greenfoot. Alternatively, you can open the debugger and click on *Terminate*. This exits the scenario and automatically starts it again.


*** 

## Recommended Books and Additional Resources

I recommend taking a look at the book by Michael Kölling **Introduction to Programming with Greenfoot**. It can either be used as inspiration for the teacher or as a textbook for the whole class.

* **[code.makery.ch Blog](/blog/) (where new versions of GrennfootKara are announced)**

* **Links for Kara and Greenfoot:**
  * Worksheets and exercises for Kara: http://www.swisseduc.ch/informatik/karatojava/javakara/material/
  * Main Website for Greenfoot: http://www.greenfoot.org
  * Greenfoot forum for teachers: http://greenroom.greenfoot.org





