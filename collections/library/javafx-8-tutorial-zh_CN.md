---
layout: article
title: "JavaFX 8 教程"
date: 2014-10-08 00:00
updated: 2014-10-08 00:00
slug: javafx-8-tutorial/zh_CN
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-zh_CN.md
description: "这 7 部分组成的教程将介绍一个用 JavaFX 编写的联系人程的设计，开发和部署的过程。"
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: false
sidebars:
- header: "系列文章"
  body:
  - text: "简介"
    link: /library/javafx-8-tutorial/zh_CN/
    paging: 简介
    active: true
  - text: "第一部分: Scene Builder"
    link: /library/javafx-8-tutorial/zh_CN/part1/
    paging: 1
  - text: "第二部分: Model 和 TableView"
    link: /library/javafx-8-tutorial/zh_CN/part2/
    paging: 2
  - text: "第三部分: 与用户的交互"
    link: /library/javafx-8-tutorial/zh_CN/part3/
    paging: 3
  - text: "第四部分: CSS 样式"
    link: /library/javafx-8-tutorial/zh_CN/part4/
    paging: 4
  - text: "第五部分: 将数据用 XML 格式存储"
    link: /library/javafx-8-tutorial/zh_CN/part5/
    paging: 5
  - text: "第六部分: 统计图"
    link: /library/javafx-8-tutorial/zh_CN/part6/
    paging: 6
  - text: "第七部分: 部署"
    link: /library/javafx-8-tutorial/zh_CN/part7/
    paging: 7
- header: 语言
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-intro/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh_CN/
    icon-css: fa fa-fw fa-globe
---


Back in 2012 I wrote a very detailed [JavaFX 2 tutorial series](/java/javafx-2-tutorial-intro/) for my students. Many people all over the world have been reading the tutorial and gave very positive feedback. So I decided to **rewrite the JavaFX 2 tutorial for JavaFX 8** (read about what changed in [Update to JavaFX 8 - What's New](/blog/update-to-javafx-8-whats-new/)).

This tutorial walks you through designing, programming and deploying an address application. This is how the final appllication will look like:

![Screenshot AddressApp](/assets/library/javafx-8-tutorial/addressapp.png)


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

Now, I hope you'll have fun! Start with [Part 1: Scene Builder](/java/javafx-8-tutorial-zh_CN-part1/).
