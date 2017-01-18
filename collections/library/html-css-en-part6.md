---
layout: article
title: "HTML & CSS Tutorial - Part 6: Navigation"
date: 2015-04-08 00:00
slug: html-css/part6
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/html-css-en-part6.md
description: "Learn how to create a navigation for your website."
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
    active: true
  - text: "Part 7: Bootstrap Framework"
    link: /library/html-css/part7/
    paging: 7
  - text: "Next Steps"
    link: /library/html-css/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Downloads
  body:
  - text: Portfolio Part 6
    link: https://github.com/marcojakob/tutorial-html-css/releases/download/v1.0/portfolio-part6.zip
    icon-css: fa fa-fw fa-file-archive-o
languages: 
  header: Languages
  collection: library
  item: html-css
  part: part6
  active: en
---

In this part we will create a navigation so that visitors can switch between the pages.

A navigation can be as simple as a few links to other pages. We will begin with this very simple approach and continuously improve it until we have a beautiful and complete navigation on all pages.

We start by programming the navigation in our home page. Only when we're done, we will copy the navigation code into all the other pages.


## Navigation with Links

Open the `index.html` of your home page.

With simple links we can jump from our home page to other pages. The navigation should be at the very beginning of the `body` part. Let's insert the following four links between the `body` tag and the title.


##### index.html

<pre class="prettyprint lang-html">
&lt;body>
  <mark>&lt;a href="/">Home&lt;/a></mark>
  <mark>&lt;a href="/blog">Blog&lt;/a></mark>
  <mark>&lt;a href="/projects">Projects&lt;/a></mark>
  <mark>&lt;a href="/contact">Contact&lt;/a></mark>

  &lt;h1 class="title">Web Portfolio of Marco&lt;/h1>
</pre>

<div class="alert alert-info">
  **Note:** The link to `./` refers to the current directory. More info about this can be found in the section <a class="alert-link" href="/library/html-css/part1#relative-and-absolute-urls">relative and absolute URLs</a>.
</div>

Our navigation should now look as follows:

![Navigation with Links](/assets/library/html-css/part6/navigation-links.png)

Verify that the links actually work and that they lead you to the desired page.

<div class="alert alert-info">
  <p>**Note:** If you open the page as a file directly in the browser (without Bracket's *Live Preview*) the links might not work quite right. The reason is that the file system simply displays the folder contents and not automatically opens `index.html`. You will have to manually click on `index.html`.</p>
  <p>As soon as you publish the site on a server, the links should work as normal.</p>
</div>

At the moment our navigation is simply a series of links. Mostly, in HTML, a list of links is used for navigations. With an HTML list elements can be grouped together horizontally or vertically which will simplify their syling.

Let's learn how to create HTML lists.


## Lists

There are many situations in which we use lists. In HTML, there are three different types of lists: **unordered lists**, **ordered lists**, and **description lists**. Description lists are not used often. Therefore, we will only look at the first two in more detail.


### Unordered Lists

An unordered list is simply a list of entries in which the order is arbitrary. Unordered lists are created in HTML with the element `<ul>` (stands for unordered list). For each entry in the list a `<li>` (list item) element is needed within the `<ul>` element.

Here is an example of a typical todo list:

<pre class="prettyprint lang-html">
&lt;ul>
  &lt;li>Shopping&lt;/li>
  &lt;li>Feeding the cat&lt;/li>
  &lt;li>Visit grandmother&lt;/li>
&lt;/ul>
</pre>

The result looks like this:

<div class="panel panel-default">
  <div class="panel-body">
    <ul style="margin-bottom: 0">
      <li>Shopping</li>
      <li>Feeding the cat</li>
      <li>Visit grandmother</li>
    </ul>
  </div>
</div>


### Ordered Lists

In ordered lists, the order is significant and is therefore numbered. In HTML, it looks very similar to the unordered list: Instead of `<ul>` we use an `<ol>` element.

A typical example of an ordered list is a recipe for a meal:

<pre class="prettyprint lang-html">
&lt;ol>
  &lt;li>Crack eggs, add a pinch of salt, then use a fork to beat them together well.&lt;/li>
  &lt;li>Add milk.&lt;/li>
  &lt;li>Melt butter in a frying pan and pour in the egg mixture.&lt;/li>
  &lt;li>Continue cooking until thickened, then serve.&lt;/li>
&lt;/ol>
</pre>

The result looks like this:

<div class="panel panel-default">
  <div class="panel-body">
    <ol style="margin-bottom: 0">
      <li>Crack eggs, add a pinch of salt, then use a fork to beat them together well.</li>
      <li>Add milk.</li>
      <li>Melt butter in a frying pan and pour in the egg mixture.</li>
      <li>Continue cooking until thickened, then serve.</li>
    </ol>
  </div>
</div>


### Navigation as List

We will now program the navigation of our portfolio as an unordered list.

The links of our navigation are put in `<li>` elements that themselves are withn an `<ul>` element.

<pre class="prettyprint lang-html">
&lt;ul>
  &lt;li>&lt;a href="/">Home&lt;/a>&lt;/li>
  &lt;li>&lt;a href="/blog">Blog&lt;/a>&lt;/li>
  &lt;li>&lt;a href="/projects">Projects&lt;/a>&lt;/li>
  &lt;li>&lt;a href="/contact">Contact&lt;/a>&lt;/li>
&lt;/ul>
</pre>

In the final part of this tutorial we will see how the navigation can be displayed horizontally and nicely formatted. Once the navigation is complete, we will copy it to all other pages.


### Blog Entries as List

On our blog page we had put a comment where we wanted to list our blog entries. Now that we know how lists are created in HTML, we can list the blog entries.

Open the blog page `blog/index.html`. Below the "Blog Entries" heading insert the list of entries as follows.


##### blog/index.html

<pre class="prettyprint lang-html">
&lt;h2>Blog Entries&lt;/h2>

&lt;!-- Here will be a list of all the blog entries. -->
&lt;ul>
  &lt;li>More entries will follow...&lt;/li>
  &lt;li>&lt;a href="second-entry/">Second Entry&lt;/a>&lt;/li>
  &lt;li>&lt;a href="first-entry/">First Entry&lt;/a>&lt;/li>
&lt;/ul>
</pre>

<div class="alert alert-info">
  **Note:** Blog entries are often listed in reverse chronological order so that the last entry appears at the top of the list.
</div>


## What's next?

&rarr; Continue with [Part 7: Using the Bootstrap Framework](/library/html-css/part7/).
