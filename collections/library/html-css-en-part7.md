---
layout: article
title: "HTML & CSS Tutorial - Part 7: Bootstrap Framework"
date: 2015-04-01 00:00
slug: html-css/part7
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/html-css-en-part7.md
description: "Learn to use the Bootstrap Framework in your website."
image: /assets/library/html-css/portfolio.png
published: true
prettify: true
comments: true
sidebars:
- header: Articles in this Series
  body:
  - text: "Introduction"
    link: /library/html-css/
    paging: Intro
  - text: "Part 1: Your First Website"
    link: /library/html-css/part1/
    paging: 1
  - text: "Part 2: Publishing Your Website"
    link: /library/html-css/part2/
    paging: 2
  - text: "Part 3: Introduction to CSS"
    link: /library/html-css/part3/
    paging: 3
  - text: "Part 4: Development Tools in the Browser"
    link: /library/html-css/part4/
    paging: 4
  - text: "Part 5: Blog and Other Pages"
    link: /library/html-css/part5/
    paging: 5
  - text: "Part 6: Navigation"
    link: /library/html-css/part6/
    paging: 6
  - text: "Part 7: Bootstrap Framework"
    link: /library/html-css/part7/
    paging: 7
    active: true
  - text: "Next Steps"
    link: /library/html-css/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Downloads
  body:
  - text: Portfolio Teil 7
    link: https://github.com/marcojakob/tutorial-html-css/releases/download/v0.4/portfolio-de-part7.zip
    icon-css: fa fa-fw fa-file-archive-o
languages: 
  header: Languages
  collection: library
  item: html-css
  part: part7
  active: en
---

So far we have programmed all HTML and CSS from scratch. Our *web portfolio* project already has a few HTML pages with some content, a navigation and some styling.

To manually evolve our project to a complete and modern web presence would mean a **large amount of work**! Here is a list of some important tasks that we would have to deal with (do not worry, there is a solution!):

* Customize font and font size for all headings and sections.
* Beautifully design buttons, text boxes, etc. (the browser defaults look a bit outdated).
* Define margins and position elements on the page position (very difficult).
* Define a consistent style for all different browsers to show about the same thing.
* Optimize the web pages for different screen sizes (mobile, tablet and desktop)
* And more...

Fortunately we are not alone with these tasks - this applies more or less to all web designers. Since it hardly makes sense for all to solve the same tasks over and over again, so-called *frameworks* were developed that make this job a lot easier.

![Bootstrap Logo](/assets/library/html-css/part7/bootstrap-logo.png)

The best known of these frameworks is **Bootstrap**, developed by Twitter. The *Bootstrap framework* is a tremendous help for us as web developers. Step-by-step we will discover what we can do with *Bootstrap*. Here is some taste of the effect *Bootstrap* could have on the styling of our project:

![Bootstrap Effect](/assets/library/html-css/part7/bootstrap-effect-de.png)

With *Bootstrap* the elements don't only look better, they are also  consistent across different browsers.


## Using Bootstrap

### Documentation

The official [Bootstrap documentation](http://getbootstrap.com/) is the best place to learn what's possible. It contains a lot of examples that you can copy and paste into your website. You should have this website open very frequently!


### Linking to the Bootstrap CSS

The most important part of Bootstrap consists of a large CSS file. For more complex elements there is also a JavaScript file. However, for the moment we will only use the CSS file.

Now let's integrate the Bootstrap CSS in our HTML pages.

1. Open the [Bootstrap website](http://getbootstrap.com/getting-started/). You will find that there are several ways to download Bootstrap. Instead of downloading Bootstrap we will use the **Bootstrap CDN**. We only need to specify the location of the CSS file in our HTML. It will then be downloaded directly from the *CDN* servers every time our website is accessed. *CDN* servers are servers that are located all over the world so that they can deliver a file very quickly.

2. From the *Bootstrap CDN* section copy the first line with the `link` element.

3. Insert the `link` element into your HTML pages. It is important that you place the reference to the Bootstrap CSS **above** the `link` element of the `main.css` file. This enables us to override CSS rules from the Bootstrap CSS in our own CSS.

That's how your `head` section should look like (possibly with a newer version number of Bootstrap):


##### index.html

<pre class="prettyprint lang-html">
&lt;head>
  &lt;meta charset="utf-8">
  <mark>&lt;link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"></mark>
  &lt;link rel="stylesheet" href="main.css">
  &lt;title>Web Portfolio of Marco&lt;/title>
&lt;/head>
</pre>


### How does Bootstrap Work?

Some styles from the Bootstrap CSS are applied directly to HTML elements. If we look, for example, at a link in *Chrome DevTools*, we see how through the Bootstrap CSS the link color was changed and the underscore was removed with `text-decoration`.

![Bootstrap Link Color](/assets/library/html-css/part7/bootstrap-link-color.png)

Such styling is automatically applied. But a lot of Bootstrap styles require that we add specific **CSS classes** to elements, as we will see next.


### Using Bootstrap Classes

As an example, let's look at how the text alignment can be changed using Bootstrap. In the Bootstrap documentation under [Alignment classes](http://getbootstrap.com/css/#type-alignment) you can find instructions on text alignment.

Let's say we want to center the `h2` title. We can do this by adding the `text-center` class as follows:

<pre class="prettyprint lang-html">
&lt;h2 class="text-center">Welcome!&lt;/h2>
</pre>

Our `h1` title already has a `class` attribute. If we want to center this item, we can add `text-center` as a second class. **Multiple CSS classes are always separated by blank spaces**.

<pre class="prettyprint lang-html">
&lt;h1 class="title text-center">Web Portfolio of Marco&lt;/h1>
</pre>

But what if we want to center a bigger part of our website? Since it would be tedious if we had to add a `text-center` for each element. It is thus possible that we can add it to a parent element. Many CSS rules are automatically passed on to all sub-elements.

We could even add a `text-center` class to the `body` element. This would center everything on our page. If you just want to center a part of the page, it is common to use a `div` element.


## div Elements

With the `<div>` element other elements are grouped into a block. With Bootstrap `div` elements are used quite often to apply styles to an entire section of the page.

An essential CSS class in Bootstrap is `container`. The `container` automatically adjusts its content to the width of the page and guarantees a reasonable margin to the edges. You should always have a `div` element with the` container` class in your page. Read the [description for containers](http://getbootstrap.com/css/#overview-container) on the bootstrap website.

We will now pack the main content of our home page between a `<div class=" container">` and the closing `</ div>`. (We will deal with the navigation separately below.)

The `body` part of your page should look like this (pay attention to indentation of elements so that your code stays readable):


##### index.html

<pre class="prettyprint lang-html">
&lt;body>
  &lt;ul>
    &lt;li>&lt;a href="./">Home&lt;/a>&lt;/li>
    &lt;li>&lt;a href="blog/">Blog&lt;/a>&lt;/li>
    &lt;li>&lt;a href="projects/">Projects&lt;/a>&lt;/li>
    &lt;li>&lt;a href="contact/">Contact&lt;/a>&lt;/li>
  &lt;/ul>

  <mark>&lt;div class="container"></mark>
    &lt;h1 class="title">Web Portfolio of Marco&lt;/h1>

    &lt;h2>Welcome!&lt;/h2>

    &lt;p>Nice to have you stop by my page.&lt;/p>

    &lt;p>Please have a look around. In the blog section I document my experiences in programming. You may also look at my web projects. Have Fun.&lt;/p>

    &lt;img src="marco.jpg" alt="Picture of me">

    &lt;p>Marco :-)&lt;/p>
  <mark>&lt;/div></mark>
&lt;/body>
</pre>

The browser always displays `div` elements **on a new line**. Such elements, because they claim all available width, are called [block elements](https://developer.mozilla.org/en-US/docs/Web/HTML/Block-level_elements).


## span Elements

In contrast to `<div>` elements, a `<span>` element does not start on a new line. A `span` element is only as wide as its content. Such elements are called [inline elements](https://developer.mozilla.org/en-US/docs/Web/HTML/Inline_elemente) because they embed within a line.

Im Gegensatz zu `<div>`-Elementen beginnt ein `<span>`-Element nicht auf einer neuen Zeile. Ein `span`-Element ist nur so breit. Man nennt solche Elemente auch [Inline-Elemente](https://developer.mozilla.org/en-US/docs/Web/HTML/Inline_elemente).  

Here is an example of how we could use a `span` element:

<pre class="prettyprint lang-html">
I would like to &lt;span class="text-warning">highlight those words&lt;/span>.
</pre>


## Navigation with Bootstrap

Finally, we want to make the navigation more beautiful with the help of Bootstrap. In the Bootstrap documentation a [Navbar](http://getbootstrap.com/components/#navbar) is described. However, the example is very complex and includes menus, text boxes and buttons. We'll implement a slightly simpler version.

Program the following steps. I recommend you test what happens after every step.

1. Insert the the two classes `nav` and `navbar-nav` to the `ul` element in your navigation.   
`<ul class="nav navbar-nav">`

2. Surround the entire navigation with a new `div` with the `container` class. This has the effect that the distances from the edges are the same as in our main content. Do not forget the closing `</div>` tag after the navigation code.   
`<div class="container">`

3. Surround it all with yet another `div`. This time we will need the three classes `navbar`, `navbar-default`, and `navbar-static-top`. Those classes ensure that the navigation is displayed at the top of the screen. Don't forget the closing `</div>` tag.      
`<div class="navbar navbar-default navbar-static-top">`

4. In the `<li>` element of our home, insert a class called `active`. This has the effect that the *Home* link is highlighted. This tells the visitor about the currently active page.   
`<li class="active"><a href="./">Home</a></li>`

This is how the complete code will look like after the previous steps (pay attention to correct indentation!):


##### index.html

<pre class="prettyprint lang-html">
<mark>&lt;div class="navbar navbar-default navbar-static-top"></mark>
  <mark>&lt;div class="container"></mark>
    &lt;ul <mark>class="nav navbar-nav"></mark>
      &lt;li <mark>class="active"</mark>>&lt;a href="./">Home&lt;/a>&lt;/li>
      &lt;li>&lt;a href="blog/">Blog&lt;/a>&lt;/li>
      &lt;li>&lt;a href="projects/">Projects&lt;/a>&lt;/li>
      &lt;li>&lt;a href="contact/">Contact&lt;/a>&lt;/li>
    &lt;/ul>
  <mark>&lt;/div></mark>
<mark>&lt;/div></mark>
</pre>

And this is how it looks like:

![Navbar](/assets/library/html-css/part7/navbar-default-de.png)


### Dark Version

There is an alternative to the gray navigation:

![Navbar Inverse](/assets/library/html-css/part7/navbar-inverse-de.png)

To activate the alternative style, replace the `navbar-default` class with `navbar-inverse`.


## Optimizing for Mobile

With Bootstrap and the new navigation the website looks already quite appealing in a desktop browser. Now try opening it on a mobile phone (you will need to [publish it online](/library/html-css/part2/) to do this).

You will notice that the page either doesn't fit the screen or the navigation is displayed too small.

![Mobile without Viewport](/assets/library/html-css/part7/mobile-without-viewport-de.png)

With a `meta` info inside the `head` we can tell the mobile browsers to properly scale the page.

<pre class="prettyprint lang-html">
&lt;head>
  &lt;meta charset="utf-8">
  <mark>&lt;meta name="viewport" content="width=device-width, initial-scale=1"></mark>
  &lt;link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  &lt;link rel="stylesheet" href="main.css">
  &lt;title>Web Portfolio of Marco&lt;/title>
&lt;/head>
</pre>

It should now look something like this:

![Mobile with Viewport](/assets/library/html-css/part7/mobile-with-viewport-de.png)

Bootstrap was designed so that it [works well on mobile screens](http://getbootstrap.com/css/#overview-mobile). In the screenshot above you can see how the navigation is automatically displayed vertically when the width of the screen is too small. If you're not using a mobile phone you can also simulate this by simply resizing your browser window.

Web design that is optimized for different screen sizes is called [responsive web design](http://en.wikipedia.org/wiki/Responsive_web_design).

<div class="alert alert-warning">
  **Note:** Bootstrap is capable of automatically collapsing the menu into a menu button. But this requires an additional JavaScript files. In the <a class="alert-link" href="/library/more-html-css/javascript-bootstrap/">JavaScript with Bootstrap</a> article I explain how you can program such a navigations.
</div>


## Bootstrap and Navigation on all Pages

So far, we have inserted Bootstrap and the navigation only on our home page. The goal, of course, is that they are included on all pages.

With small adjustment we can copy the code from the home page to all other pages (blog, blog entries, contact, etc.).


#### 1. Insert Viewport and Bootstrap CSS

Copy the line with the `viewport` and the line with the Bootstrap CSS from the `head` area of the home page and paste it in all other sites.

<pre class="prettyprint lang-html">
  &lt;meta name="viewport" content="width=device-width, initial-scale=1">
  &lt;link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
</pre>


#### 2. Insert Navigation

Copy the entire navigation of the home page and paste it into all the other pages. Make sure that you add the `active` class to the current `li` element.

<pre class="prettyprint lang-html">
&lt;div class="navbar navbar-default navbar-static-top">
  &lt;div class="container">
    &lt;ul class="nav navbar-nav">
      &lt;li>&lt;a <mark>href="../"</mark>>Home&lt;/a>&lt;/li>
      &lt;li <mark>class="active"</mark>>&lt;a <mark>href="../blog/"</mark>>Blog&lt;/a>&lt;/li>
      &lt;li>&lt;a <mark>href="../projects/"</mark>>Projects&lt;/a>&lt;/li>
      &lt;li>&lt;a <mark>href="../contact/"</mark>>Contact&lt;/a>&lt;/li>
    &lt;/ul>
  &lt;/div>
&lt;/div>
</pre>

<div class="alert alert-info">
  <p>**Remember:** Links in subfolders must be prefixed with `../` to point to the parent folder.</p>
  <p>Because the blog entries are in a sub-subfolder the links must be prefixed with `../../` to point to the root folder.</p>
</div>


#### 3. Insert Container div

When you open some pages in the browser you will notice that the margins are not correct. To fix this we need the entire content (everything below the navigation) to be surrounded by a `div`. This `div` must have the `container` class. Put containers in all the pages that don't have it yet.

<pre class="prettyprint lang-html">
[... navigation ...]

<mark>&lt;div class="container"></mark>
  [... content ...]
<mark>&lt;/div></mark>
</pre>


#### 4. Testing and Cleanup

It's very important that you navigate through all pages and carefully **test all the links**!

Then look at your code and make sure that the HTML is **clean**. Each time an HTML tag is opened the next line should be indented. The closing tag is then moved to the left again.


## What's next?

&rarr; Read about the [Next Steps](/library/html-css/next/).

