---
layout: article
title: "Mehr HTML & CSS: JavaScript mit Bootstrap"
date: 2015-03-18 00:00
slug: more-html-css/de/javascript-bootstrap
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/more-html-css-de-javascript-bootstrap.md
description: "Lernen Sie, wie die das Bootstrap-JavaScript in eine Webseite integriert werden kann. Enthält ein Beispiel zur Bootstrap Navigation."
published: true
prettify: true
comments: true
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/more-html-css/de/
    paging: Intro
  - text: "Website-Layout mit Bootstrap"
    link: /library/more-html-css/de/website-layout/
    paging: 1
    icon-css: fa fa-fw fa-th-large
  - text: "Icons"
    link: /library/more-html-css/de/icons/
    paging: 2
    icon-css: fa fa-fw fa-flag
  - text: "Bilder mit Bootstrap"
    link: /library/more-html-css/de/image-bootstrap/
    icon-css: fa fa-fw fa-image
    paging: 3
  - text: "Bilder gestalten"
    link: /library/more-html-css/de/image-editing/
    icon-css: fa fa-fw fa-image
    paging: 4
  - text: "Kostenlose Bilder"
    link: /library/more-html-css/de/image-sources/
    icon-css: fa fa-fw fa-image
    paging: 5
  - text: "Textformatierung"
    link: /library/more-html-css/de/text/
    paging: 6
    icon-css: fa fa-fw fa-font
  - text: "JavaScript mit Bootstrap"
    link: /library/more-html-css/de/javascript-bootstrap/
    paging: 7
    icon-css: fa fa-fw fa-code
    active: true
- header: Links
  body:
  - text: HTML & CSS Tutorial
    link: /library/html-css/de/
    icon-css: fa fa-fw fa-external-link
languages:
  header: Sprachen
  collection: library
  item: more-html-css
  part: javascript-bootstrap
  active: de
---

Der Kern von Bootstrap ist das CSS. Es gibt jedoch auch eine JavaScript-Datei von Bootstrap, die sehr nützliche Funktionen bietet. Schauen Sie sich mal um auf der [Bootstrap JavaScript Seite](http://holdirbootstrap.de/javascript/).

Im [HTML & CSS Tutorial - Teil 7](/library/html-css/de/part7/) haben wir gesehen, wie das Bootstrap-CSS eingebunden werden kann. Nun wollen wir zusätzlich die JavaScript-Datei einfügen.


## Bootstrap-JavaScript einbinden

Das Bootstrap-CSS wird im `head`-Bereich eingefügt. Das JavaScript wird ganz am Schluss vom `body`-Bereich eingefügt. Damit das Bootstrap-JavaScript funktioniert, braucht es noch ein JavaScript mit *jQuery*. So sieht es dann aus:

##### HTML-Vorlage mit Bootstrap

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>
&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    &lt;meta name="viewport" content="width=device-width, initial-scale=1">
    &lt;link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    &lt;link rel="stylesheet" href="main.css">
    &lt;title>Bootstrap-Vorlage&lt;/title>
  &lt;/head>
  &lt;body>
    &lt;h1>Hello, world!&lt;/h1>

    &lt;script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">&lt;/script>
    &lt;script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js">&lt;/script>
  &lt;/body>
&lt;/html>
</pre>

Es braucht also drei Dinge:

* Bootstrap-CSS im `head`.
* jQuery-JavaScript am Ende von `body`.
* Bootstrap-JavaScript am Ende von `body`.


*Schauen Sie auf der [Bootstrap Website](http://holdirbootstrap.de/los-gehts/) nach, welches die aktuellen Versionen von Bootstrap und jQuery sind, und ändern Sie die Versionsnummern entsprechend.*


<div class="alert alert-info">
  <strong>Hinweis:</strong> Das JavaScript könnte auch im `head`-Bereich eingefügt werden, was jedoch nicht optimal ist. Der Browser lädt nämlich die Webseite Schritt-für-Schritt und wenn das JavaScript zu früh kommt, dann könnte das die Ladezeit verlängern. Deshalb kommt JavaScript meist ganz am Schluss.
</div>


## Einklappende Navigation

Mit dem Bootstrap-CSS alleine konnten wir im [HTML & CSS Tutorial - Teil 7](/library/html-css/de/part7#navigation-mit-bootstrap) bereits eine ansehnliche Navigation programmieren. Nun verbessern wir diese Navigation weiter, so dass sie auf kleinen Bildschirmen automatisch einklappt.

![Navigation eingeklappt](/assets/library/more-html-css/javascript-bootstrap/navigation-collapsed.png)

Der Programmcode dazu sieht nun so aus:

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>

&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    &lt;meta name="viewport" content="width=device-width, initial-scale=1">
    &lt;link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    &lt;link rel="stylesheet" href="main.css">
    &lt;title>Portfolio Marco&lt;/title>
  &lt;/head>
  &lt;body>
    &lt;div class="navbar navbar-default navbar-static-top">
      &lt;div class="container">
        
        &lt;div class="navbar-header">
          &lt;button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#my-navbar">
            &lt;span class="sr-only">Toggle Navigation&lt;/span>
            &lt;span class="icon-bar">&lt;/span>
            &lt;span class="icon-bar">&lt;/span>
            &lt;span class="icon-bar">&lt;/span>
          &lt;/button>
          &lt;a class="navbar-brand" href="#">Portfolio Marco&lt;/a>
        &lt;/div>
        
        &lt;div class="collapse navbar-collapse" id="my-navbar">
          &lt;ul class="nav navbar-nav">
            &lt;li class="active">&lt;a href="./">Home&lt;/a>&lt;/li>
            &lt;li>&lt;a href="blog/">Blog&lt;/a>&lt;/li>
            &lt;li>&lt;a href="projekte/">Projekte&lt;/a>&lt;/li>
            &lt;li>&lt;a href="kontakt/">Kontakt&lt;/a>&lt;/li>
          &lt;/ul>
        &lt;/div>
      &lt;/div>
    &lt;/div>
    
    &lt;div class="container">
      &lt;h1 class="title">Willkommen auf meinem Web Portfolio&lt;/h1>
    &lt;/div>
    
    &lt;script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">&lt;/script>
    &lt;script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js">&lt;/script>
  &lt;/body>
&lt;/html>
</pre>



