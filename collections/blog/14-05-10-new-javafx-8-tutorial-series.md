---
layout: post
title: JavaFX 8 Event Handling Examples
date: 2014-05-03 00:00
slug: new-javafx-8-tutorial-series
description: "We explore how to handle the most common JavaFX events: Button events, CheckBox events, Hyperlink events, Slider events, TextBox events, ListView events."
image: /assets/blog/14-05-03-javafx-8-event-handling-examples/event-handling-examples.png
published: false
prettify: true
comments: true
tags:
- Java
- JavaFX
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