---
layout: article
title: Dart Event Bus
date: 2014-03-25 00:00
updated: 2014-09-08 00:00
slug: dart-event-bus
published: true
prettify: true
comments: true
sidebars:
- header: Source Code
  body:
  - text: Source on GitHub
    link: https://github.com/marcojakob/dart-event-bus
    icon-css: fa fa-fw fa-github-alt
  - text: Package on Pub
    link: http://pub.dartlang.org/packages/event_bus
    icon-css: fa fa-fw fa-download
---

A simple Event Bus using Dart [Streams](https://api.dartlang.org/apidocs/channels/stable/dartdoc-viewer/dart:async.Stream) 
for decoupling applications.


***

## Demo

<iframe src="http://marcojakob.github.io/dart-event-bus/" width="100%" height="420px"></iframe>

***


## Event Bus Pattern

An Event Bus follows the publish/subscribe pattern. It allows listeners to 
subscribe for events and publishers to fire events. This enables objects to
interact without requiring to explicitly define listeners and keeping track of
them.


### Event Bus and MVC

The Event Bus pattern is especially helpful for decoupling [MVC](http://wikipedia.org/wiki/Model_View_Controller) 
(or [MVP](http://wikipedia.org/wiki/Model_View_Presenter)) applications.

**One group of MVC** is not a problem.

![Model-View-Controller](/assets/library/dart-event-bus/mvc.png)

But as soon as there are **multiple groups of MVCs**, those groups will have to talk
to each other. This creates a tight coupling between the controllers.

![Multi Model-View-Controllers](/assets/library/dart-event-bus/mvc-multi.png)

By communication through an **Event Bus**, the coupling is reduced.

![Event Bus](/assets/library/dart-event-bus/event-bus.png)


## Usage

### 1. Create an Event Bus

Create an instance of `EventBus` and make it available to other classes.

Usually there is just one Event Bus per application, but more than one may be 
set up to group a specific set of events.

<pre class="prettyprint lang-dart">
import 'package:event_bus/event_bus.dart';

EventBus eventBus = new EventBus();
</pre>

You can alternatively use the `HierarchicalEventBus` that filters events by 
event class **including** its subclasses. 

<pre class="prettyprint lang-dart">
EventBus eventBus = new EventBus.hierarchical();
</pre>


### 2. Define Events

Any Dart class can be used as an event.

<pre class="prettyprint lang-dart">
class UserLoggedInEvent {
  User user;
  
  UserLoggedInEvent(this.user);
}

class NewOrderEvent {
  Order order;
  
  NewOrderEvent(this.order);
}
</pre>


### 3. Register Listeners

Register listeners for a **specific events**: 

<pre class="prettyprint lang-dart">
eventBus.on(UserLoggedInEvent).listen((UserLoggedInEvent event) {
  print(event.user);
});
</pre>

Register listeners for **all events**:

<pre class="prettyprint lang-dart">
eventBus.on().listen((event) {
  // Print the runtime type. Such a set up could be used for logging.
  print(event.runtimeType); 
});
</pre>


#### About Dart Streams

`EventBus` uses Dart [Streams](https://api.dartlang.org/apidocs/channels/stable/dartdoc-viewer/dart:async.Stream)
as its underlying mechanism to keep track of listeners. You may use all 
functionality available by the [Stream](https://api.dartlang.org/apidocs/channels/stable/dartdoc-viewer/dart:async.Stream)
API. One example is the use of [StreamSubscriptions](https://api.dartlang.org/apidocs/channels/stable/dartdoc-viewer/dart:async.StreamSubscription)
to later unsubscribe from the events.

<pre class="prettyprint lang-dart">
StreamSubscription loginSubscription = eventBus.on(UserLoggedInEvent).listen((UserLoggedInEvent event) {
  print(event.user);	
});

loginSubscription.cancel();
</pre>


### 4. Fire Events

Finally, we need to fire an event.

<pre class="prettyprint lang-dart">
User myUser = new User('Mickey');
eventBus.fire(new UserLoggedInEvent(myUser));
</pre>


## Download

<i class="fa fa-fw fa-github-alt"></i> [Source on GitHub](https://github.com/marcojakob/dart-event-bus)

<i class="fa fa-fw fa-download"></i> [Package on Pub](http://pub.dartlang.org/packages/event_bus)
