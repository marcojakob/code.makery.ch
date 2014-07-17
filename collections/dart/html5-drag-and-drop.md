---
layout: article
title: Dart HTML5 Drag and Drop
date: 2014-03-25 00:00
updated: 2014-07-17
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/projects/dart-html5-drag-and-drop/index.html
sidebars:
- header: Source Code
  body:
  - text: Dart HTML5 Drag and Drop (GitHub)
    link: https://github.com/marcojakob/dart-html5-dnd
    icon-css: fa fa-fw fa-github-alt
---

<div class="alert alert-danger">
  <p>
    <strong>Note.</strong> This library is not maintained any more. It was replaced by <a href="/library/dart-drag-and-drop/" class="alert-link">Dart Drag and Drop</a>. 
</div>

Native HTML5 Drag and Drop is [not easy to work with](http://www.quirksmode.org/blog/archives/2009/09/the_html5_drag.html) The Dart HTML5 Drag and Drop library was created to use HTML5 Drag and Drop in a consistend way across all modern browsers. Here are some examples of how to use it.


## Features

* Make any HTML Element `draggable`.
* Create `dropzones` and connect them with `draggables`.
* Rearrange elements with `sortables` (similar to jQuery UI Sortable).
* Support for `touch events` on touch screen devices.
* Same functionality and API for IE9+, Firefox, Chrome and Safari.
* Uses fast native HTML5 Drag and Drop of the browser whenever possible.
* For browsers that do not support some features, the behaviour is emulated. This is the case for IE9 and partly for IE10 (when custom drag images are used).


## Source Code

[Dart HTML5 Drag and Drop on GitHub](https://github.com/marcojakob/dart-html5-dnd)