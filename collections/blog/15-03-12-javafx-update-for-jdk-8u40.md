---
layout: post
title: "JavaFX Update for JDK 8u40"
date: 2015-03-12 00:00
slug: javafx-update-for-jdk-8u40
description: "JDK 8u40 was released. It includes some new JavaFX UI controls like Dialogs, Spinner, and Formatted TextField."
image: /assets/blog/15-03-12-javafx-update-for-jdk-8u40/addressapp.png
published: true
prettify: true
comments: true
tags:
- Java
- JavaFX
---

[JDK 8u40 was released](https://blogs.oracle.com/thejavatutorials/entry/jdk_8u40_released). It includes some new JavaFX UI controls like [Spinner](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Spinner.html), [Formatted TextFields](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TextFormatter.html), and (finally) simple [Dialogs](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html).

![Dialog](/assets/blog/15-03-12-javafx-update-for-jdk-8u40/information-dialog.png)


## Tutorial Update

![AddressApp](/assets/blog/15-03-12-javafx-update-for-jdk-8u40/addressapp.png)

I've updated the entire [JavaFX 8 Tutorial](/library/javafx-8-tutorial/) for JDK8u40. This is what changed:

* Use the new Dialogs instead of the deprecated ControlsFX Dialogs (see this  [article about the official JavaFX Dialogs](/blog/javafx-dialogs-official/)).
* Create a new release for the [source code of all tutorial parts](https://github.com/marcojakob/tutorial-javafx-8/releases/tag/v1.1).
* New download links for [Scene Builder](http://gluonhq.com/products/downloads/) (provided by Gluon).
* Correct some typos and misleading texts.

*Here is the [commit with all tutorial changes](https://github.com/marcojakob/code.makery.ch/commit/f6b4a51b41957b753bfbfc8c1f637a6849ceeab5).* 


## Translations

Every time someone contacts me, who wants to contribute a translation, I get very excited!

As of today, the JavaFX 8 Tutorial is **available in six languages and two are beeing translated** right now!  [English](/library/javafx-8-tutorial/), [Portuguese](/library/javafx-8-tutorial/pt/), [Spanish](/library/javafx-8-tutorial/es/), [Chinese](/library/javafx-8-tutorial/zh-cn/), [Russian](/library/javafx-8-tutorial/ru/), and [Indonesian](/library/javafx-8-tutorial/id/). [French](/library/javafx-8-tutorial/fr/) has a partial translation and someone has just sent a pull request today for an Italian translation.


### Help Needed for Updating the Translations

The translated tutorials still need an update to reflect the changes described above. So, if you care about one of the languages and would like to help out, let me know.

To see what has changed, you could take a look at [this commit](https://github.com/marcojakob/code.makery.ch/commit/f6b4a51b41957b753bfbfc8c1f637a6849ceeab5). Then, it's very simple: Just click on the **Edit on GitHub** link at to top of the page you would like to change and edit it online. If you're not familiar with GitHub, please read [how to contribute](/library/how-to-contribute/).


## @codemakery on Twitter

I've set up a new Twitter handle if you want to follow what's happening on code.makery: Follow [@codemakery on Twitter](https://twitter.com/codemakery).

