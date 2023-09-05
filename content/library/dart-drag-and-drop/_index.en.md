+++
title = "Dart Drag and Drop"
date = 2014-07-17
updated = 2018-08-17
image = "dnd-screenshot.png"
description = "Drag and Drop for Dart web apps with mouse and touch support."
prettify = true
# comments = true

# Series Overview Info
overview = true
overviewImage = "dnd-screenshot.png"
overviewDescription = "Drag and Drop for Dart web apps with mouse and touch support."
topics = [ "Dart" ]
weight = 8

[[sidebars]]
header = "Source Code"

[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-github-alt\"></i> Source on GitHub"
link = "https://github.com/marcojakob/dart-dnd"

[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Package on Pub"
link = "https://pub.dartlang.org/packages/dnd"
+++

There are a few options when you want to support drag and drop in your web application the most popular being jQuery UI [Draggable](http://jqueryui.com/draggable/) and [Droppable](http://jqueryui.com/droppable/). I didn't like the dependency on jQuery and wanted something that would also support dragging on touch screens.

My first attempt was to use the browser's native HTML5 Drag and Drop which you can find in my [Dart HTML5 Drag and Drop library](https://github.com/marcojakob/dart-html5-dnd). But **I've given up on HTML5 Drag and Drop** (and you should too)! You can read the details about [why I decided against HTML5 Drag and Drop](/blog/drag-and-drop-without-html5/).

Now, let's take a look at the new approach that is much more convenient to work with. For details about how to use the library see [package on Pub](https://pub.dartlang.org/packages/dnd) or [source on GitHub](https://github.com/marcojakob/dart-dnd). Following are some examples of what you can do with the Drag and Drop library.


## Examples

### Basic

This is a basic example using some draggable documents and a trash as a dropzone.

<a href="https://marcojakob.github.io/dart-dnd/basic/" target="_blank" class="pull-right"><i class="fa fa-external-link"></i> Open in separate tab</a>

#### Live demo


<iframe src="https://marcojakob.github.io/dart-dnd/basic/" width="100%" height="330px"></iframe>

***


### Custom Drag Avatar

An example with a custom image as drag avatar. The image is changed depending on the remaining distance to the trash.

<a href="https://marcojakob.github.io/dart-dnd/custom_avatar/" target="_blank" class="pull-right"><i class="fa fa-external-link"></i> Open in separate tab</a>

#### Live demo

<iframe src="https://marcojakob.github.io/dart-dnd/custom_avatar/" width="100%" height="330px"></iframe>

***


### Drag Detection Only

An example that uses only drag detection. Use this if you want to implement your own custom dragging behavior.

Even without a drag avatar and `Dropzone` this helps quite a lot as it unifies touch and mouse dragging and provides convenient event streams.

<a href="https://marcojakob.github.io/dart-dnd/detection_only/" target="_blank" class="pull-right"><i class="fa fa-external-link"></i> Open in separate tab</a>

#### Live demo

<iframe src="https://marcojakob.github.io/dart-dnd/detection_only/" width="100%" height="300px"></iframe>

***


### Free Dragging

This example shows how to freely drag and position an element. Instead of using a clone for the drag avatar, the original element itself is dragged.

<a href="https://marcojakob.github.io/dart-dnd/free_dragging/" target="_blank" class="pull-right"><i class="fa fa-external-link"></i> Open in separate tab</a>

#### Live demo

<iframe src="https://marcojakob.github.io/dart-dnd/free_dragging/" width="100%" height="300px"></iframe>

***


### Handle

You can use any subelement of a draggable as a drag handle. If a handle is specified, dragging can only be started on the handle.

<a href="https://marcojakob.github.io/dart-dnd/handle/" target="_blank" class="pull-right"><i class="fa fa-external-link"></i> Open in separate tab</a>

#### Live demo

<iframe src="https://marcojakob.github.io/dart-dnd/handle/" width="100%" height="300px"></iframe>

***


### Cancel

Dragging can be prevented on specified elements. By default dragging is cancelled when the drag starts on form elements (`input`, `textarea`, `button`, `select`, and `option`).

<a href="https://marcojakob.github.io/dart-dnd/cancel/" target="_blank" class="pull-right"><i class="fa fa-external-link"></i> Open in separate tab</a>

#### Live demo

<iframe src="https://marcojakob.github.io/dart-dnd/cancel/" width="100%" height="400px"></iframe>

***


### Constrain to Horizontal or Vertical Axis

Dragging can be constrained to horizontal or vertical only.

*A nice side effect: When a drag is constrained to either horizontal or vertical only, the other direction can be used for scrolling on touch devices.*

<a href="https://marcojakob.github.io/dart-dnd/horizontal_only/" target="_blank" class="pull-right"><i class="fa fa-external-link"></i> Open in separate tab</a>

#### Live demo

<iframe src="https://marcojakob.github.io/dart-dnd/horizontal_only/" width="100%" height="200px"></iframe>

***


### Custom Acceptor

You can use predefined or custom `Acceptor`s to determine which `Draggable`s are accepted by which `Dropzone`s.

<a href="https://marcojakob.github.io/dart-dnd/custom_acceptor/" target="_blank" class="pull-right"><i class="fa fa-external-link"></i> Open in separate tab</a>

#### Live demo

<iframe src="https://marcojakob.github.io/dart-dnd/custom_acceptor/" width="100%" height="500px"></iframe>

***


### Nested Dropzones

An example of how to drag over nested `Dropzone`s.

*Note: If dropped on an inner [Dropzone] the outer [Dropzone] will also receive the drop event.*


<a href="https://marcojakob.github.io/dart-dnd/nested_dropzones/" target="_blank" class="pull-right"><i class="fa fa-external-link"></i> Open in separate tab</a>

#### Live demo

<iframe src="https://marcojakob.github.io/dart-dnd/nested_dropzones/" width="100%" height="350px"></iframe>

***


### Sortable

An example example to create basic sortable/rearranging behavior.

<a href="https://marcojakob.github.io/dart-dnd/simple_sortable/" target="_blank" class="pull-right"><i class="fa fa-external-link"></i> Open in separate tab</a>

#### Live demo

<iframe src="https://marcojakob.github.io/dart-dnd/simple_sortable/" width="100%" height="450px"></iframe>
