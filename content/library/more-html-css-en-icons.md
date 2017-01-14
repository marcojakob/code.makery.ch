---
layout: article
title: "More HTML & CSS: Icons"
date: 2015-04-09 00:00
slug: more-html-css/icons
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/more-html-css-en-icons.md
description: "Learn how to layout elements on web pages using the bootstrap grid."
published: true
prettify: true
comments: true
sidebars:
- header: Articles in this Series
  body:
  - text: "Introduction"
    link: /library/more-html-css/
    paging: Intro
  - text: "Website Layout with Bootstrap"
    link: /library/more-html-css/website-layout/
    paging: 1
    icon-css: fa fa-fw fa-th-large
  - text: "Icons"
    link: /library/more-html-css/icons/
    paging: 2
    icon-css: fa fa-fw fa-flag
    active: true
  - text: "Images with Bootstrap"
    link: /library/more-html-css/image-bootstrap/
    icon-css: fa fa-fw fa-image
    paging: 3
  - text: "Image Editing"
    link: /library/more-html-css/image-editing/
    icon-css: fa fa-fw fa-image
    paging: 4
  - text: "Free Image Sources"
    link: /library/more-html-css/image-sources/
    icon-css: fa fa-fw fa-image
    paging: 5
  - text: "Formatting Text"
    link: /library/more-html-css/text/
    paging: 6
    icon-css: fa fa-fw fa-font
  - text: "JavaScript with Bootstrap"
    link: /library/more-html-css/javascript-bootstrap/
    paging: 7
    icon-css: fa fa-fw fa-code
- header: Links
  body:
  - text: HTML & CSS Tutorial
    link: /library/html-css/
    icon-css: fa fa-fw fa-external-link
languages:
  header: Languages
  collection: library
  item: more-html-css
  part: icons
  active: en
---

Inserting icons into a website isn't difficult. But when you combine text and icons (for example on a button), or if the icon should be resizable, it can cause some trouble.

The solution is to use **Icon Fonts**. Icon fonts are fonts that contain icons instead of letters. Thus, these icons can be used just like any other font, that means you can change their size and even their color.

<i class="fa fa-umbrella" style="color: #333"></i> 
<i class="fa fa-umbrella fa-3x" style="color: #333"></i> 
<i class="fa fa-umbrella fa-5x" style="color: #333"></i>
<i class="fa fa-umbrella" style="color: #ffc107"></i> 
<i class="fa fa-umbrella fa-3x" style="color: #ffa000"></i> 
<i class="fa fa-umbrella fa-5x" style="color: #ff6f00"></i>


In the following, we will look at a few icon fonts and their use.


## Glyphicons (with Bootstrap 3)

<div class="alert alert-warning">
  If you haven't yet integrated Bootstrap in your project, please read <a href="/library/html-css/part7/" class="alert-link">how to use the Bootstrap Framework</a> in the HTML &amp; CSS Tutorial.
</div>

If you're using Bootstrap 200 [Glyphicons](http://glyphicons.com/) are automatically integrated.

Here is a list of all available icons in the [Bootstrap dokumentation under Glyphicons](http://getbootstrap.com/components/#glyphicons).

To use an icon we define a `<span>` element and give it two CSS classes:

<pre class="prettyprint lang-html">
&lt;span class="glyphicon glyphicon-send">&lt;/span>
</pre>

This `span` element can be inserted anywhere, for example in a title of a contact page: 

![Contact Icon](/assets/library/more-html-css/icons/contact-icon.png)


## Font Awesome

One of the most popular freely available icon font is [Font Awesome](http://fontawesome.io/). Font Awesome comes with more than 500 icons.

To [integrate Font Awesome](http://fontawesome.io/get-started/) you can simply use their CDN link. This means you don't need to download anything and you can simple add the following line to the `<head>` section of your HTML file:

<pre class="prettyprint lang-html">
&lt;link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
</pre>

**Note:** *Look up the CDN link on the [Font Awesome Website](http://fontawesome.io/get-started/) to get the latest version.*

Font Awesome icons are used by declaring an `<i>` element with specific CSS classes.

<pre class="prettyprint lang-html">
&lt;i class="fa fa-flag">&lt;i>
</pre>

This example produces a flag: ![Font Awesome Flagge](/assets/library/more-html-css/icons/fontawesome-flag.png)

Have a look at the [Font Awesome examples](http://fontawesome.io/examples/) to see what you can do with the icons. You will also find some examples of how to use Font Awesome Icons in combination with Bootstrap components.


## Other Icon Libraries

* On [IcoMoon](https://icomoon.io/) you'll find an app to create your own icon font.
* [Fontello](http://fontello.com/) is another generator for icon fonts.
* [Iconfinder](https://www.iconfinder.com/) - A lot of freely available icons (Note: Depending on the license you will need to mention the author on your website)
* A list of [12 free icon fonts](http://t3n.de/news/10-kostenlose-icon-fonts-450651/) (in German).

