---
layout: post
title: Drag and Drop without HTML5
date: 2014-07-17 00:00
slug: drag-and-drop-without-html5
description: "I gave up on HTML5 drag and drop and created a new Dart library without HTML5."
image: /assets/blog/14-07-17-drag-and-drop-without-html5/dnd-screenshot.png
published: true
prettify: false
comments: true
tags:
- Dart
- HTML5
---

I gave up on HTML5 drag and drop! A step I should have made a long time ago. Even though I read some reports of people struggling with it (like the [HTML5 drag and drop disaster](http://www.quirksmode.org/blog/archives/2009/09/the_html5_drag.html) article) I thought I could takle it and make it work for the most recent browsers. That was at the beginning of last year. 

I had discovered Dart as my new favourite programming language so I set out to give it a try in Dart. The plan was to create a layer on top of the HTML5 drag and drop API to make it pleasant to work with. The problem was that the layer got bigger and bigger with all the quirks of the different browsers.

The resulting [HTML5 drag and drop library](https://github.com/marcojakob/dart-html5-dnd) wasn't all too bad and it did actually work quite well. I received feedback of people using it, some created issues and some even contributed bug fixes.

But there were some problems that just couldn't be solved (see below). And with all the strange and changing browser quirks it was just a pain to maintain.


## New Drag and Drop Library


Wait, there's hope. I've started again and created a [new Dart drag and drop library](/library/dart-drag-and-drop/) just without the HTML5 - and it's much better! 

<a href="/library/dart-drag-and-drop/" class="btn btn-default">&rarr; New Library</a>

[![Drag and Drop](/assets/blog/14-07-17-drag-and-drop-without-html5/dnd-screenshot.png)](/library/dart-drag-and-drop/)



## Why You Shouldn't Use HTML5

I won't get into all the details here as others have [greatly](https://www.inkling.com/engineering/native-html5-drag-drop/) and [amusingly](http://www.quirksmode.org/blog/archives/2009/09/the_html5_drag.html) done so already. Here are the reasons in short:

* **Bad API Design.** There are lot's of events you *have* to register for even though you don't actually need them. For example, you have to cancel the default actions in the `dragEnter` and `dragOver` events to actually get a `drop` event. This is just one of many problems that lead to a very bloated library of handling all the special cases.

* **Inconsistent `dragEnter` and `dragLeave`.** If you have child elements inside a dropzone it is very, very difficult to find out if the dropzone was actually left or if the user just entered a child element. This makes it hard to implement drag hover behavior in a reliable way.

* **Inflexible drag images.** This is a big one. By default, the browser takes a snapshot of the draggable element as visual feedback. The possibilities to change this image are very limited. At the start of the drag you can provide an image or a canvas element (and this isn't even supported by IE10). With some horrible(!) hacks I was able pass some custom HTML as drag image. But what's definitely not possible is to change this image during the drag operation.  

* **No touch support.** I know. I don't plan to go crazy with drag and drop on small mobile screens. But it would still be nice to get the same drag events on touch screens as when using a mouse and with HTML5 drag and drop this just isn't possible. Pointer events might help here in the future (or [present](http://www.polymer-project.org/platform/pointer-events.html)).

There are more, but those are my main reasons for dropping HTML5. 


## When You Should Use HTML5

It's broken and probably won't get fixed very soon, but there are some cases where you really need it:

* **Dragging from the desktop.** Basically, if you're dragging something from the desktop into your browser you'll have to use HTML5 drag and drop.

* **Dragging between web applications.** If your dragging between different windows in the browser you might consider using HTML5 drag and drop. There are ways to do cross-document communication. I haven't tried, but both ways seem hard.


## The New Library and More

Have a look at the [demos of the new library](/library/dart-drag-and-drop/) and let me know what you think and if it works for you.

*There is more to come soon:* Based on the drag and drop library I've created a Dart swiper library that works with mouse and touch. I will write when I have it cleaned up and ready to use. 




