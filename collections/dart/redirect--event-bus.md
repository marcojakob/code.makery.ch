---
layout: redirect
title: "Dart Event Bus"
slug: event-bus
redirect: /library/dart-event-bus/
published: true
---

A simple Event Bus using Dart [Streams](http://api.dartlang.org/docs/releases/latest/dart_async/Stream.html) 
for decoupling applications.

For installation instructions and source code see <a href="https://github.com/marcojakob/dart-event-bus"> Dart Event Bus on GitHub</a>.


## Event Bus Pattern

An Event Bus follows the publish/subscribe pattern. It allows listeners to 
subscribe for events and publishers to fire events. This enables objects to
interact without requiring to explicitly define listeners and keeping track of
them.


### Event Bus and MVC

The Event Bus pattern is especially helpful for decoupling [MVC](http://wikipedia.org/wiki/Model_View_Controller) 
(or [MVP](http://wikipedia.org/wiki/Model_View_Presenter)) applications.

**One group of MVC** is not a problem.

![Model-View-Controller](/assets/dart/event-bus/mvc.png)

But as soon as there are **multiple groups of MVCs**, those groups will have to talk
to each other. This creates a tight coupling between the controllers.

![Multi Model-View-Controllers](/assets/dart/event-bus/mvc-multi.png)

By communication through an **Event Bus**, the coupling is reduced.

![Event Bus](/assets/dart/event-bus/event-bus.png)


* * * 

## Demo

<div id="example-container">
  <div class="row">
    <div class="listener col-xs-6" id="listener-1">
      <h4>Listener 1</h4>
      <p>
        <textarea class="form-control" rows="4"></textarea>
      </p>
      <p>
        <button class="listen-a btn btn-primary btn-sm">Listen for Event A</button>
        <button class="listen-b btn btn-success btn-sm">Listen for Event B</button>
      </p>
      <p>
        <button class="pause btn btn-default btn-sm">Pause</button>
        <button class="resume btn btn-default btn-sm">Resume</button>
        <button class="cancel btn btn-default btn-sm">Cancel</button>
      </p>
    </div>
    <div class="listener col-xs-6" id="listener-2">
      <h4>Listener 2</h4>
      <p>
        <textarea class="form-control" rows="4"></textarea>
      </p>
      <p>
        <button class="listen-a btn btn-primary btn-sm">Listen for Event A</button>
        <button class="listen-b btn btn-success btn-sm">Listen for Event B</button>
      </p>
      <p>
        <button class="pause btn btn-default btn-sm">Pause</button>
        <button class="resume btn btn-default btn-sm">Resume</button>
        <button class="cancel btn btn-default btn-sm">Cancel</button>
      </p>
    </div>
  </div>
  
  <h4>Fire Events</h4>
  <div class="row">
    <div class="event col-xs-12">
      <p>
        <button id="fire-button-a" class="btn btn-primary btn-sm">Fire Event A [1]</button>
        <label id="fire-label-a"></label>
      </p>
    </div>
    <div class="event col-xs-12">
      <p>
        <button id="fire-button-b" class="btn btn-success btn-sm">Fire Event B [1]</button>
        <label id="fire-label-b"></label>
      </p>
    </div>
  </div>
</div>