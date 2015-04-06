---
layout: article
title: "HTML & CSS Tutorial - Part 1: Your First Website"
date: 2015-04-01 00:00
slug: html-css/part1
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/html-css-en-part1.md
description: "Create your first website. Get to know the programming tools and learn the basics of HTML and CSS."
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
    active: true
  - text: "Part 2: Publishing Your Website"
    link: /library/html-css/part2/
    paging: 2
  - text: "Part 3: Introduction to CSS"
    link: /library/html-css/part3/
    paging: 3
  - text: "Part 4: Development Tools in the Browser"
    link: /library/html-css/part4/
    paging: 4
  - text: "Part 5: Navigating Between Pages"
    link: /library/html-css/part5/
    paging: 5
  - text: "Part 6: Using the Bootstrap Framework"
    link: /library/html-css/part6/
    paging: 6
  - text: "Next Steps"
    link: /library/html-css/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Downloads
  body:
  - text: Portfolio Teil 1
    link: https://github.com/marcojakob/tutorial-html-css/releases/download/v0.4/portfolio-de-part1.zip
    icon-css: fa fa-fw fa-file-archive-o
languages: 
  header: Languages
  collection: library
  item: html-css
  part: part1
  active: en
---

Let's dive right in and create our first web page.


## Our Tools

For web development, you need two programs: an <abbr data-toggle="tooltip" title="Normal text editor or a Code-Editor">editor</abbr> to create the files for the website and a <abbr data-toggle="tooltip" title="For example, Internet Explorer, Firefox, Safari, or Chrome">browser</abbr> to view and test your website.


### Editor

For creating web pages a normal text editor would be sufficient. But there are editors that greatly simplify your programming work. Therefore, I recommend that you use with a modern code editor.

![Brackets Editor](/assets/library/html-css/part1/brackets-editor-screenshot.png)

My current favorite among the many editors is [Brackets](http://brackets.io/) (free from Adobe). You may use an editor of your choice but I will occasionally refer to some functions of Brackets. Good alternatives to Brackets would be [Atom](http://atom.io) or the slightly older but very good [Notepad++](http://notepad-plus-plus.org/).


<a href="http://brackets.io" target="_blank">![Brackets Editor Logo](/assets/library/html-css/part1/brackets-editor-logo.jpg)
</a>

Go ahead and install an editor. If you do not know which one use [Brackets](http://brackets.io) for now.


### Browser

Our website should of course run in all major browsers (Internet Explorer, Firefox, Chrome and Safari). However, for web development I recommend you use [Chrome](https://www.google.com/chrome/browser/). Chrome includes very useful tools for developers which you will use often. In addition, Chrome is best supported by the Brackets editor so that changes in the editor are immediately visible in Chrome (via live reload).

<a href="https://www.google.com/chrome/browser/" target="_blank">![Chrome Logo](/assets/library/html-css/part1/chrome-logo.png)</a>

If you do not have Chrome on your computer, you can [install it here]((https://www.google.com/chrome/browser/)).


## Creating an HTML Document

Equipped with editor and browser, create the first HTML document for your website:

1. Create a folder on your computer for your project. Name the folder *Portfolio*, for example.

2. Open Brackets (or your code editor).

3. Open the **File** menu and select **Open Folder ...**. Browse for the folder you created and open it.
<p>
![Open Folder](/assets/library/html-css/part1/open-folder-de.png)
</p>

4. Click below the folder with the **right mouse button** and select **New File**. Name the file `index.html`.
<p>
![New File](/assets/library/html-css/part1/new-file-de.png)
</p>

5. Now you have a very simple, blank text file named `index.html`.
<p>
![index.html](/assets/library/html-css/part1/new-index-file-de.png)
</p>


### Why index.html?

How you could probably assume, the name `index.html` has a special meaning. If a website address is called, for example, `http://code.makery.ch`, the `index.html` file is automatically displayed first, which is in this case `http://code.makery.ch/index.html`. For us, the first `index.html` will become your home page.


### Viewing and Refreshing

Now you can fill the document with content. Tap on the following lines. As a text, you can choose any course something.

Nun wollen wir das Dokument mit Inhalt füllen. Write the following lines in your HTML file. 

![HTML Structure](/assets/library/html-css/part1/first-website-de.png)

To view the page, click the *flash* on the right edge (Live Preview). If Bracket finds the Chrome browser, the page is now displayed and whenever you make changes, they are automatically updated.

<div class="alert alert-info">
<p>
If the **page does not appear**: Locate the `index.html` file on your computer and open it in Chrome or in another browser.
</p>
<p>
If the page is **not updated automatically**: Save all files and click `F5` or `cmd+R` in the browser. 
</p>
</div>

***Congratulations! You have just created your first website!***


### Proper Indentation

In order to keep an overview, it is important that you correctly indent the lines with the **tab key**. The default in Brackets is usually that a tab is 4 spaces long. This is a bit long. I recommend, therefore, that you change the setting at the bottom of Brackets to `Spaces: 2`.

![Brackets Spaces](/assets/library/html-css/part1/brackets-spaces-de.png)

**Important:** Pay close attention to the example code and indent accordingly. It is really important that you develop clean programming habits from the very beginning!

<div class="alert alert-info">
<p>
**Tip 1:** Use `Shift+Tab` keys to move indentation to the left.
</p>
<p>
**Tip 2:** You can indent multiple lines at once if you select them and click `Tab` or `Shift+Tab`.
</p>
</div>


## HTML Elements

### Tags

In the above example you have already seen the typical HTML characters with angle brackets. These are called **tags**.

**HTML elements** are usually (but not always) two tags, one opening and one closing. In our example, `<html>` is an opening tag and `</html>` with the slash is a closing tag.

The text between the opening and the closing tag is the content of the HTML element. With `<html>` and `</html>` we tell the browser about the beginning and end of the HTML of our web page.

The second tag we have seen is the `<body>` tag. It states that all content between the opening `<body>` and the closing `</body>` tags are to be shown in the main area of the browser.


### Attributes

Attributes declare additional information for an item. Attributes are part of the opening tag of an element and always have a name and a value.

As an example, let's look at the HTML element for a link. It is probably one of the most important elements - what would the internet be without links?

![HTML Element](/assets/library/html-css/part1/html-element-attribute-de.png)

The `<a>` element above includes an `href` attribute (short for "hypertext reference") with the value `http://code.makery.ch`. The browser knows that it must show link as follows: [My Website](http://code.makery.ch)


## Basic Structure of an HTML Page

The elements `<html>` and `<body>` we have already seen. But the basic structure of an HTML page usually contains a few more. Adjust your website to the following code. Afterwards we will discuss it.


##### HTML Structure

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>
&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    &lt;title>Web Portfolio of Marco&lt;/title>
  &lt;/head>
  &lt;body>
    &lt;h1>Web Portfolio of Marco&lt;/h1>
    &lt;p>Write anything you want to tell the world.&lt;/p>
  &lt;/body>
&lt;/html>
</pre>


#### Explanations

* Always put `<!DOCTYPE html>` on the first line. It tells the browser about the type of the document.
* The `<html>` tag indicates the start and `</html>` the end of the document.
* The `<head>` element contains additional information about the page. In contrast to the `<body>` element, this information does not appear in the main area of the browser.
  * Within the `<head>`, there should be an indication about the character set: `<meta charset = "utf-8">`. If you do not specify the character set some special characters may not get displayed correctly. You might have noticed that the `<meta>` element has no closing tag. There are a few <abbr data-toggle="tooltip" title="Important elements without closing tags: <br>, <img>, <meta>, <hr>, <link>, <input>">elements without closing tags</abbr>, but they are the exception.
  * Next you'll see the `<title>` element. The title is displayed in the title bar at the top of your browser window.
* Everything inside the `<body>` element is displayed in the main area of the browser.
  * A `<h1>` element identifies the main heading. Sub headings can be created with `<h2>`, `<h3>`, ....
  * Text between `<p>` and`</p>` is a paragraph.
* After each opening tag, the next element is indented (with a tab or two spaces) for better overview. Make sure you follow this habit.
  
<div class="alert alert-info">
<p>
**Tip 1:** Keep this HTML basic structure handy. You can use it for any new HTML page! 
</p>
<p>
**Tip 2:** Use the keyboard shortcut `Ctrl+S` to save the current file. 
</p>
<p>
**Tip 3:** Use the keyboard shortcut `Ctrl+Z` for undo.
</p>
</div>

With these basic HTML elements you are well prepared to bring your website to a new level. First, let's add an image so that the home page of your portfolio looks a bit more interesting.


## Inserting an Image

To insert an image we use the `<img>` element. The following example will insert a picture of my avatar:

<pre class="prettyprint lang-html">
&lt;img src="marco.jpg" alt="Picture of me">
</pre>

The `<img>` element only has one opening but no closing tag. It contains a `src` and an `alt` attribute. The `src` attribute specifies the <abbr data-toggle="tooltip" title="Uniform Resource Locator">URL</abbr>, that is the location and file name of the image. The `alt` attribute is an "alternative text", which describes the content of the image. This text is used by search engines and if for some reason the image can not be displayed, for example with a screen reader for the blind.


### Relative and Absolute URLs

URLs are used for the `src` attribute of images and also for the` href` attribute of links. The URL specifies the "address" of some file is (for example another web page or an image). Depending on the location of the file, either a *relative* or *absolute* URL must be used.

If a file **is part of the same web site**, then a ***relative URL*** is used. As we have seen in the example above, this is only the name of the file.

A *relative URL* always *relative* to the current HTML page. If the target file is located in another folder, this must be taken into account. If the image from the example above were in a subfolder called `images`, the *relative URL* would be `images/marco.jpg`. If the file is in a parent folder you can reach this folder with `../`. The URL for the image in this case would be `../marco.jpg`.

If the file is located **on an other website**, an ***absolut URL*** is used. Absolute URL's contain the entire domain name and path. An example would be `http://code.makery.ch/images/marco.jpg`.


<div class="alert alert-info">
  **Remember the following about URL's:**
  <ul>
    <li>An URL that starts with `http://` is an absolute URL.</li>
    <li>An URL without `http://` is an URL relative to the current web page.<li>
    <li>A dot (`.`) refers to the current directory.</li>
    <li>Two dots (`..`) refer to the parent directory.<li>
  </ul>
</div>
 

##### Examples of Relative and Absolute URL's

<pre class="prettyprint lang-html">
&lt;!-- Relative URL's -->
&lt;a href="image-gallery.html">Image Gallery&lt;/a>
&lt;a href="blog/first-blog-entry.html">My First Blog Entry&lt;/a>
&lt;a href="../image-gallery.html">Back to Image Gallery&lt;/a>

&lt;!-- Absolute URL's -->
&lt;a href="http://www.my-colleague.com/blog.html">Blog of a Colleague&lt;/a>
</pre>


### Portfolio with an Image

If you have not already done so, try and insert an image in your portfolio. You must copy an image to your *Portfolio* folder on your computer. Make sure that you specify the exact file name, including the file extension.

All of your code could now look something like this (I added a subheading and some more text):


##### index.html with Finished Portfolio Code

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>
&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    &lt;title>Web Portfolio of Marco&lt;/title>
  &lt;/head>
  &lt;body>
    &lt;h1>Web Portfolio of Marco&lt;/h1>
    
    &lt;h2>Welcome!&lt;/h2>
    
    &lt;p>Nice to have you stop by my page.&lt;/p>
    
    &lt;p>Please have a look around. In the blog section I document my experiences in programming. You may also look at my web projects. Have Fun.&lt;/p>
    
    &lt;img src="marco.jpg" alt="Picture of me">

    &lt;p>Marco :-)&lt;/p>
  &lt;/body>
&lt;/html>
</pre>

That's how the portfolio currently looks like in the browser:

![Portfolio](/assets/library/html-css/part1/portfolio-de.jpg)


## What's next?

&rarr; In the next section you will learn how to publish your website on the internet. Continue with [Part 2: Publishing Your Website](/library/html-css/part2/)


