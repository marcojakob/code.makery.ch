+++
title = "Part 1: Your First Website"
date = 2018-08-10
description = "Create your first website. Get to know the programming tools and learn the basics of HTML and CSS."
image = "portfolio.png"
prettify = true
comments = true
weight = 1

[[sidebars]]
header = "Downloads"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Portfolio Part 1"
link = "https://github.com/marcojakob/tutorial-html-css/releases/download/v2.0/portfolio-part1.zip"
+++

Let's dive right in and create our first web page.


## Our Tools

For web development, you need two programs: an **editor** to create the files for the website and a **browser** (for example Edge, Firefox, Safari, or Chrome) to view and test your website.


### Editor

For creating web pages, a normal text editor would be sufficient. But there are editors that greatly simplify your work as a programmer. Therefore, I recommend that you use a modern code editor.

![Visual Studio Code Editor](vs-code-editor.png)

My current favorite among the many editors is [Visual Studio Code](https://code.visualstudio.com/) (free). You may use an editor of your choice but I will occasionally refer to some functions of Visual Studio Code (VS Code). Good alternatives are [Atom](https://atom.io) or [Brackets](http://brackets.io).

<a href="https://code.visualstudio.com/" target="_blank">![VS Code Editor Logo](vs-code-logo.png)
</a>

Go ahead and install an editor. If you do not know which one, use [Visual Studio Code](https://code.visualstudio.com/) for now.


#### Editor Extensions

While we're at it we'll an extension to VS Code that will give superpowers to our editor: [Live Server](https://marketplace.visualstudio.com/items?itemName=ritwickdey.LiveServer).

1. Open Visual Studio Code.

2. Open the menu "Extensions".   
![Open Extensions](open-extensions.png)

3. Type "Live Server" into the search field and install the extension.    
![Install Live Server](install-live-server.png)

4. Reload Visual Studio Code to activate the extension.   
![Install Live Server Reload](install-live-server-reload.png)

### Browser

Our website should, of course, run in all major browsers (Edge, Firefox, Chrome and Safari). However, for web development I recommend you use [Chrome](https://www.google.com/chrome/). Chrome includes very useful tools for developers which you will use often.

<a href="https://www.google.com/chrome/" target="_blank">![Chrome Logo](chrome-logo.png)</a>

If you do not have Chrome on your computer, you can [install it here](https://www.google.com/chrome/).


## Creating an HTML Document

Equipped with editor and browser, let's create the first HTML document for our website.

1. Create a folder on your computer for your project. Name the folder *Portfolio* (or anything you want).

2. Open VS Code.

3. Open the **File** menu and select **Open Folder ...**. Browse for the folder you created and open it.    
![Open Folder](open-folder.png)

4. Right-click below the folder and select **New File**. Name the file `index.html`.
![New File](new-file.png)

5. Now you have a blank text file named `index.html`.
![index.html](new-index-file.png)


### Why index.html?

You could probably assume, the name `index.html` does have special meaning. If a website address is called, for example `https://code.makery.ch`, the `index.html` file is automatically displayed first, which in this case is `https://code.makery.ch/index.html`. For us, our first `index.html` will become our home page.


### Viewing and Refreshing

Now you can fill the document with content. Type the following lines in your HTML.

![HTML Structure](first-website.png)

To view the page, we'll use the **Live Server** extension that we installed above. Right-click on your `index.html` (the file below the Portfolio folder and not the one above under "Open Editors"). Then click **Open with Live Server**. A browser window should open up and show your first website. Now, whenever you save any changes they are automatically updated by the live server.

![Start Live Server](start-live-server.png)

<div class="alert alert-info">
<p>
If the page does <strong>not open in your preferred browser</strong>: Change the settings on your computer to make another browser your standard browser. Alternatively you can copy the address (for example <code>http://127.0.0.1:5500/index.html</code>) from one browser and insert it into another.
</p>
<p>
If the the page <strong>is not updated</strong> after changes: Save all your files in the editor and then - inside the browser - refresh with <code>F5</code> or <code>cmd+r</code>.
</p>
</div>

**Congratulations! You have just created your first website!**


### Indentation

In order to keep your code clean, it is important that you correctly indent the lines with the **tab key**. Pay close attention to the example code and indent accordingly. The browser does not care but it is important for us as programmers to be able to have a clear view.

<div class="alert alert-info">
  <p>
  <strong>Tip 1:</strong> Use <code>Shift+Tab</code> keys to move indentation to the left.
  </p>
  <p>
  <strong>Tip 2:</strong> You can indent multiple lines at once if you select them and click <code>Tab</code> or <code>Shift+Tab</code>.
  </p>
  <p>
  <strong>Tip 3 (automatically):</strong> Right-click somewhere in your program code and choose <code>Format Document</code>.
  </p>
</div>


## HTML Elements

### Tags

In the above example you have already seen the typical HTML characters with angle brackets. These are called **tags**.

**HTML elements** consist usually (but not always) of two tags, one opening and one closing. In our example, `<html>` is an opening tag and `</html>` with the slash is a closing tag.

The text between the opening and the closing tag is the content of the HTML element. With `<html>` and `</html>` we tell the browser about the beginning and end of the HTML of our web page.

The second tag we have seen is the `<body>` tag. It states that all content between the opening `<body>` and the closing `</body>` tags are to be shown in the main area of the browser.


### Attributes

Attributes declare additional information for an item. Attributes are part of the opening tag of an element and always have a **name** and a **value**.

As an example, let's look at the HTML element for a link. It is probably one of the most important elements &mdash; what would the internet be without links?

![HTML Element](html-element-attribute.png)

The `<a>` element above includes an `href` attribute (short for "hypertext reference") with the value `https://code.makery.ch`. The browser knows that it must show the link as [My Website](https://code.makery.ch).


## Basic Structure of an HTML Page

We have already seen the two elements `<html>` and `<body>`. But the basic structure of an HTML page usually contains a few more. Adjust your website to the following code. Afterwards, we will discuss each element it.


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
  * Within the `<head>`, there should be an indication about the character set: `<meta charset="utf-8">`. If you do not specify the character set some special characters may not get displayed correctly. You might have noticed that the `<meta>` element has no closing tag. There are a few elements without closing tags (`<br>`, `<img>`, etc.), but they are the exception.
  * Next you'll see the `<title>` element. The title is displayed in the title bar at the top of your browser window.
* Everything inside the `<body>` element is displayed in the main area of the browser.
  * A `<h1>` defines the main heading. Sub headings can be created with `<h2>`, `<h3>`, `<h4>`, `<h5>`, and `<h6>`.
  * Text between `<p>` and`</p>` is a paragraph.
* After each opening tag, the next element should be indented (with a tab or two spaces) for better overview. Make sure you follow this habit.

<div class="alert alert-info">
  <p><strong>Tip 1:</strong> Keep this basic HTML structure handy. You can use it for any new HTML page! </p>
  <p><strong>Tip 2:</strong> Use the keyboard shortcut <code>Ctrl+S</code> to save the current file.</p>
  <p><strong>Tip 3:</strong> Use the keyboard shortcut <code>Ctrl+Z</code> for undo.</p>
</div>

With these basic HTML elements we are well prepared to bring our website to a new level. First, let's add an image so that the home page of our portfolio looks a bit more interesting.


## Inserting an Image

To insert an image we use the `<img>` element. The following example will insert my picture:

<pre class="prettyprint lang-html">
&lt;img src="marco.jpg" alt="Picture of me">
</pre>

The `<img>` element only has one opening but no closing tag. It contains a `src` and an `alt` attribute. The `src` attribute specifies the URL, that is the location and file name of the image. The `alt` attribute is an "alternative text", which describes the content of the image. This text is used by search engines and if for some reason the image can not be displayed, for example on a screen reader for the blind.


### Relative and Absolute URLs

URLs are used for the `src` attribute of images and also for the` href` attribute of links. The URL specifies the "address" of a file (for example another web page or an image). Depending on the location of the file, either a *relative* or *absolute* URL must be used.

If a file **is part of the same web site**, then a **relative URL** can be used. As we have seen in the example above, this is only the name of the file.

A **relative URL** is either *relative* to the current HTML page or *relative* to the root of our website. To reference a file in the same folder, we can just use the filename like `marco.jpg`. If the target file is located in another folder, this must be taken into account. If the image from the example above were in a subfolder called `images`, the *relative URL* would be `images/marco.jpg`. If the file is in a parent folder you can reach it with `../`. The URL for the image in this case would be `../marco.jpg`.

Instead of navigating from the current file we can also start from the root of our website by adding a leading slash `/marco.jpg`. We can use this from any file in your website and in any subfolder and it will always start at the root. This is helpful for navigation links as we will see in a following part of the tutorial.

If the file is located **on another website**, an **absolut URL** must be used. Absolute URLs contain the entire domain name and path. An example would be `https://code.makery.ch/images/marco.jpg`.


<div class="alert alert-info">
  <strong>Remember the following about URLs:</strong>
  <ul>
    <li>Inside the same folder we just use the filename, for example <code>marco.jpg</code></li>
    <li>Two dots (`..`) refer to the parent directory.</li>
    <li>If we want to start in the root directory we add an `/` before the path of the file, for example <code>/marco.jpg</code>.</li>
  </ul>
</div>


##### Examples of Relative and Absolute URLs

<pre class="prettyprint lang-html">
&lt;!-- Relative URLs -->
&lt;a href="image-gallery.html">Image Gallery&lt;/a>
&lt;a href="blog/first-blog-entry.html">My First Blog Entry&lt;/a>
&lt;a href="../image-gallery.html">Back to Image Gallery&lt;/a>

&lt;!-- Absolute URLs -->
&lt;a href="http://www.my-colleague.com/blog.html">Blog of a Colleague&lt;/a>
</pre>


### Portfolio with an Image

If you have not already done so, try and insert an image in your portfolio. You must copy an image to your *Portfolio* folder on your computer. Make sure that you specify the exact file name, including the file extension.

All of your code could now look something like this (I've added a subheading and some more text):


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

    &lt;p>Thanks for stopping by.&lt;/p>

    &lt;p>Please have a look around. In the blog section I document my experiences in programming. You may also look at my web projects. Have Fun.&lt;/p>

    &lt;img src="marco.jpg" alt="Picture of me">

    &lt;p>Marco :-)&lt;/p>
  &lt;/body>
&lt;/html>
</pre>

That's how the portfolio currently looks like in the browser:

![Portfolio](portfolio.jpg)


## What's next?

&rarr; In the next section you will learn how to publish your website on the internet. Continue with [Part 2: Publishing Your Website](/library/html-css/part2/)
