+++
title = "JavaFX vs. HTML5"
date = 2014-10-29
image = "javafx-vs-html5.png"
description = "Should you choose JavaFX or HTML5 for your next richt client application? This article can give you some criteria to make your choice."
prettify = true
comments = true
tags = [ "Web", "Java", "JavaFX" ]
+++

Choosing a rich client technology is very difficult! I recently did some consulting for a company that needed to choose between JavaFX and HTML5. In this article I would like to share some of my insights on this topic.

![JavaFX vs. HTML5](javafx-vs-html5.png)


I don't intend this to be a battle between technologies by making one win over another. It really depends on your situation. But this article can give you some criteria to keep in mind when you make your choice.

I know that by sharing all this information here for free it'll be less likely that people will need my consulting advice. So, if this article is a help to you and saves you a lot of work and time, consider [supporting me](/about#support-me). This will help me provide more free resources like this.


### Target Audience

A lot of Java developers are asking themselves if they should adopt JavaFX for their next user interface or make the switch to an HTML5 technology. I know and love both worlds. I've been using JavaFX since early 2012. Lately I've mostly been programming web applications and will continue to do so in the coming months.

I'm not talking about small projects that can be implemented as simple websites with a little interactivity. This article is about rich client applications with a lot of interactive elements.


## Table of Contents

Here is a list of the criteria that we will look at:

* [Richness and Aesthetics of UI](#richness-and-aesthetics-of-ui)
* [Options for Mobile and Tablet](#options-for-mobile-and-tablet)
* [Learning Curve](#learning-curve)
* [Long-term Support](#long-term-support)
* [Rolling Replacement of a Java Swing Application](#rolling-replacement-of-a-java-swing-application)
* [Deployment](#deployment)


Then we finish off with:

* [Conclusions](#conclusions)
* [My Recommendations](#my-recommendations)


## Richness and Aesthetics of UI

#### JavaFX

* JavaFX comes with a decent default theme called [Modena](http://fxexperience.com/2013/01/modena-new-theme-for-javafx-8/).
* The theme is easily [customizable with CSS](/library/javafx-tutorial/part4/).
* There are [many UI widgets](http://fxexperience.com/2013/01/modena-new-theme-for-javafx-8/) (scroll down to "All controls in Modena"). It comes with complex widgets and interactions out of the box: [TreeTableView](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/tree-table-view.htm), TextFlow (supports rich text), [DatePicker](/blog/javafx-8-date-picker/), Charts, 3D support, [WebView](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/web/WebView.html) (with a modified Webkit engine), Drag and Drop, and more.
* If you need more widgets there is the excellent [ControlsFX](http://fxexperience.com/controlsfx/) project and also [JFXtras](http://jfxtras.org/).
* JavaFX also comes with a nice visual designer called [JavaFX Scene Builder](http://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-info-2157684.html) which I like a lot.


#### HTML5

* I guess (almost) everything is possible with HTML5.
* The options of libraries and frameworks are immense.
* HTML5 targets many browsers and all possible screen sizes. This is great but it is also very difficult to implement one clean solution that works everywhere.
* Complex widgets (like tree-tables) are hard to implement.
* Complex interactions with good performance are hard to implement. I've had my share of experiences by writing a [drag and drop library](/blog/drag-and-drop-without-html5/)!


#### Result

Since JavaFX mainly targets the desktop and HTML5 all possible screen sizes, it is **not really fair to compare** the two in terms of *richness and aesthetics of UI*.

With JavaFX you will not have to think a lot about UI widgets and interactions. You get everything out of the box. So, if you're implementing a larger business application you will certainly get nice results much faster and easier with JavaFX.


***

## Options for Mobile and Tablet

#### JavaFX

JavaFX is mainly for Desktop use. JavaFX for Android/iOS is a community effort. There is currently **no official support by Oracle**.

* [JavaFX Ports](http://javafxports.org/) - website about the current state of JavaFX ports to different platforms.
* [Is it possible to run JavaFX applications on iPhone/Android/Win8 mobile?](http://stackoverflow.com/questions/20860931/is-it-possible-to-run-javafx-applications-on-iphone-android-win8-mobile) - good collection of links.


#### HTML5

Targetting mobile and desktop browsers there is no limit for HTML5. But to create a larger application that works accross many different screen sizes is everything but trivial both from a design and from a technical perspective.


#### Result

Obviously, **HTML5 is the winner here**.

I haven't tried any of the JavaFX ports for mobile but from what I've seen they don't seem to be production ready, yet. Through community effort this might change in the near future and could actually be usable. So, definitely create some prototype to see if you can use it.


***

## Learning Curve

#### JavaFX

When you're a Java programmer and you've created some Swing GUIs, learning JavaFX will be straigt forward. As a Java Swing developer you should be excited by the new possibilities of JavaFX.

If you work through my [JavaFX 8 Tutorial](/library/javafx-tutorial/) you will get familiar with the most important concepts of JavaFX and even some new Java 8 language features:

* Separating the presentation layer with FXML and SceneBuilder (you can also create JavaFX applications in Java code completely, if you want).
* Using the new Properties classes and ObservableLists for automatic UI updates.
* Working with the TableView.
* Using CSS for styling.
* Creating charts.
* Deployment.


#### HTML5

For a Java developer it is quite difficult to make the switch to HTML5. I know because I've gone that route and it has taken me more than two years to really feel comfortable with the web platform and the various web technologies.

Here are some reasons that make it difficult for experienced Java developers to learn HTML5:

* Many browsers with differing behaviors (Chrome, Firefox, Safari, mobile Safari, different versions of Internet Explorer, etc.)
* Must learn HTML: This is the easiest part.
* Must learn CSS: Not too hard to understand the general concepts but very difficult to work with in larger projects. It gets you in a big mess if not done right (no encapsulation!). And making the styles work across many browsers is a great challenge. You'll end up using some CSS pre-processor like SASS or LESS which adds one more thing you need to learn.
* Must learn JavaScript (there are alternatives though):
  * No static types: This is difficult to grasp for a Java developer.
  * Tool support: Because there are no static types the IDE can't help you much with content assists.
  * No real classes: Prototype-based programming.
  * JavaScript oddities: A lot has been [said](http://www.smashingmagazine.com/2011/05/30/10-oddities-and-secrets-about-javascript/) [about](http://johnkpaul.github.io/presentations/empirejs/javascript-bad-parts/) [that](http://www.oreillynet.com/pub/a/javascript/excerpts/javascript-good-parts/bad-parts.html).
  * You will end up using a framework or two which is yet another thing to learn.
  * **JavaScript itself is ok, but difficult to get right.**

I think it takes quite some time but it is possible for Java developers to make the switch to HTML5 if they are excited about learning new web technologies. 

But it will be really **difficult for unexperienced web developers to create a well architected and maintainable project**. It takes a lot of experience and discipline to stick to good practices in JavaScript projects. If the project is fairly large and multiple developers are working on the same web client it's easy to end up in a mess.

Because many larger organizations have experienced those problems they created their own solutions to tackle them. In my opinion, the best approach so far is [Dart](https://www.dartlang.org/). Dart is an open source language developed by Google that provides a very nice and fresh but still familiar language. It comes with lots of libraries and tools that make developing large web application much easier. And, of course, it compiles to JavaScript so it can be used everywhere.

The other web technology I can recommend is [Web Components](http://webcomponents.org/) which I believe is the primary way how we will develop for the web in the future. Web Components enable real encapsulation of HTML, CSS and JavaScript (or Dart) so they don't interfere with the rest of the page. Nicely encapsulated, the UI widgets are reusable as components anywhere in your application. Web Components is an evolving standard but it isn't implemented in all browsers yet. It's still early days but you can use it today with the polyfill libraries [polymer](http://www.polymer-project.org/) or [x-tags](http://x-tags.org/). If you're using Dart instead of JavaScript there is [Polymer.dart](https://www.dartlang.org/polymer/).


#### Result

For Java developers it is quite a large step until they will be able to design reasonable web applications. Migrating a larger Swing application to HTML5 without experienced web developers would entail a significant risk of failure.

**JavaFX is the obvious winner** concering the learning curve if your primary background is in a non-web language like Java, C#,... If you're already comfortable with web technologies or you're excited to learn something new I recommend you give Dart a try.


***

## Long-term Support

#### JavaFX

* JavaFX is now part of JRE/JDK for Java 8.
* Oracle says: JavaFX replaces Swing as the new client UI library.
* [Java is huge](http://www.tiobe.com/index.php/content/paperinfo/tpci/index.html) and when used on the client, JavaFX is now the default.
* JavaFX is [open source](http://openjdk.java.net/projects/openjfx/)


#### HTML5

HTML5 is here to stay. But for a business application of significant size you will need to rely on third-party frameworks, tools and maybe even languages. Just a quick, more-or-less random selection of choices you will likely encounter:

<table class="table">
  <tr>
    <td>HTML manipulation, event handling, animations, ajax</td>
    <td>jQuery</td>
  </tr>
  <tr>
    <td>JavaScript MVC Framework</td>
    <td>[angular.js](https://angularjs.org/) (Google), [ember.js](http://emberjs.com/), [backbone.js](http://backbonejs.org/), [react.js](http://facebook.github.io/react/) (Facebook), etc. </td>
  </tr>
  <tr>
    <td>Languages to improve or replace JavaScript</td>
    <td>[CoffeeScript](http://coffeescript.org/), [TypeScript](http://www.typescriptlang.org/) (Microsoft), [Dart](https://www.dartlang.org/) (Google), etc.</td>
  </tr>
  <tr>
    <td>CSS frameworks:</td>
    <td>[Less](http://lesscss.org/), [Sass](http://sass-lang.com/), etc.</td>
  </tr>
  <tr>
    <td>UI Components</td>
    <td>jQuery UI, Bootstrap (or similar), some charts library, Web Components, etc.</td>
  </tr>
</table>

The lifecycle of web frameworks and tools is quite short. When relying on many third-party libraries and tools you will need to track all the changes and you can only hope that support and updates will continue. 

Also, browsers are changing fast, so you might need to keep up with those changes.


#### Result

It's difficult to predict the future here: Who can say what happens to Oracle, Google, Facebook, etc. and to any of the Web Frameworks in a few years. 

Even though it might decline in favor of some new and fancy languages, I believe Java will still be with us for quite some time to come. With JavaFX you get almost everything you need for your UI from one single source and you know you can rely on it to stay stable for quite a while. Since JavaFX is open source this at least gives you some confidence that an open source community could provide support even if Oracle wouldn't.

Concerning web technologies: I presume that we will continue to see some major shifts in web technologies. These are my guesses for web technologies in a few years:

* As browsers and languages evolve we will not use jQuery any more.
* JavaScript will still be around but I believe it will mostly be used as a compile target and not as a language to write large web applications with. Languages like Dart, CoffeeScript, TypeScript, and [AtScript](http://www.andrewconnell.com/blog/atscript-another-language-to-compile-down-to-javascript) are the alternatives.
* I believe [Web Components](http://webcomponents.org/) will pretty much replace most web frameworks.

Since it's really difficult to tell where the HTML5 trends are heading, I'd currently **favor JavaFX when it comes to long-term support**.


***

## Rolling Replacement of a Java Swing Application

If you're coming from a larger Java Swing application you might want to **gradually replace** the existing application.


#### JavaFX

There is the possibility to [embed Swing content in JavaFX applications](http://docs.oracle.com/javafx/8/embed_swing/jfxpub-embed_swing.htm) so a rolling replacement seems possible. I, however, wouldn't underestimate the problems with a hybrid approach. An article by Dirk Lemmermann sums up the reasons why you [shouldn't mix Swing and JavaFX](http://dlemmermann.wordpress.com/2014/07/17/javafx-tip-9-do-not-mix-swing-javafx/). I agree with his conclusion that you're probably better off with migrating everything to JavaFX. 


#### HTML5

I don't see a good rolling replacement option for HTML5.


***

## Deployment

#### JavaFX

The promise of Java is "write once, run anywhere". But we've all had problems with deployment of Java programs because the user didn't have the expected Java runtime version. 

JavaFX has come to solve this problem through a new deployment option called [Self-Contained Application Packaging](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm): This bundles the native Java runtime together with your application code so you can always be sure about the Java version. It's even possible to do an installation without admin rights.

Of course, there are some caveats like the bigger download-size and that you have to build a bundle for every target platform (Windows, Linux, and Mac). The hardest part is probably to figure out a good way to deploy application updates. Currently, there is no built-in support for auto-updates but this may be included in the future. In the meantime have a look at the comments on the [Feature Request for Auto-Update](https://javafx-jira.kenai.com/browse/RT-22211) which includes a discussion about some alternate mechanisms.


#### HTML5

Deploying web applications only requires a web server that users can access with their browsers. This also makes it very easy to distribute updates.


#### Result

**HTML5 is the winnner in terms of deployment** even though JavaFX provides a better solution than before with its *native packaging*.


***

## Conclusions

Here is a summary of the comparison between JavaFX and HTML5:

<table class="table table-striped">
  <thead>
    <tr>
      <th>Criteria</th>
      <th>Result</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Richness and Aesthetics of UI</td>
      <td>Difficult to compare. But for larger business application it is faster and easier to get nice results with <strong>JavaFX</strong>.</td>
    </tr>
    <tr>
      <td>Options for Mobile and Tablet</td>
      <td>Clear win for <strong>HTML5</strong>.</td>
    </tr>
    <tr>
      <td>Learning Curve</td>
      <td>Heavily depends on your background. Obviousely a clear win for <strong>JavaFX</strong> if you're coming from a Java or Java-like language.</td>
    </tr>
    <tr>
      <td>Long-term Support</td>
      <td>Because of fast changing trends in HTML5 I consider this a tight win for <strong>JavaFX</strong>.</td>
    </tr>
    <tr>
      <td>Rolling Replacement of a Java Swing Application</td>
      <td>A <strong>tie</strong> because I wouldn't rely on combining Swing and JavaFX.</td>
    </tr>
    <tr>
      <td>Deployment</td>
      <td>A win for <strong>HTML5</strong>.</td>
    </tr>
  </tbody>
</table>


## My Recommendations

All criteria are relevant but I think there are two that might be relevant for most applications: *Learning Curve* and *Options for Mobile and Tablet*. 

The *Leraning Curve* depends on your team: How high is the team's motivation to learn web technologies? Do you have any web developers with experience in architecting complex web applications?

If the project is large and your team has limited experience in web technologies I'd consider HTML5 as quite risky. JavaFX might be the safer choice.

**But what if you need to support mobile devices?**

You could create a separate web application just for mobile. I know it would be nice to have one application that works everywhere, but this is even hard to do if you were only using HTML5. The approach of taking a fairly complex desktop UI and shrinking it down to a mobile screen usually fails. Eventually, developers come to the conclusion that they must rethink the user experience on mobile from the ground up. (That's why the mobile-first approach has gained popularity.)

**I really love Dart and the Web!**

This article might look like it favors JavaFX in a lot of places, but it doesn't. Most of the time HTML5 gets a lot of attention and I tried to focus a bit more on JavaFX as a reasonable alternative. 

Still I must say that I just love to write programs for the web that run on many different platforms without the need to install anything. And I'm really excited about the beautiful Dart language! 

If you decide to go with HTML5 I recommend you try [Dart](https://www.dartlang.org/) and [Polymer.dart](https://www.dartlang.org/polymer-dart/) for Web Components. If, after a prototype, you still decide to go with JavaScript, at least use some JavaScript MVC framework (like angular.js) to give you some structure.


***

I'm aware that the criteria we looked at are only a part of what might be relevant to your specific case. I'd be glad to hear your opinion on this article and what you think is missing from the discussion.


<small><em>Images: JavaFX Island by [Planet JFX](http://jfx.wikia.com/wiki/JavaFX_Logo_-_Centigrade), HTML5 Guy by [Pete LePage](http://www.petelepage.com/blog/2011/11/html5-guy-gelaskin/)</em></small>