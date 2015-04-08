---
layout: article
title: "HTML & CSS Tutorial - Part 4: Development Tools in the Browser"
date: 2015-04-08 00:00
slug: html-css/part4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/html-css-en-part4.md
description: "Learn how to use the developer tools in the browser (Chrome Developer Tools (Chrome Developer Tools)."
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
    active: true
  - text: "Part 5: Blog and Other Pages"
    link: /library/html-css/part5/
    paging: 5
  - text: "Part 6: Navigation"
    link: /library/html-css/part6/
    paging: 6
  - text: "Part 7: Bootstrap Framework"
    link: /library/html-css/part7/
    paging: 7
  - text: "Next Steps"
    link: /library/html-css/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
languages: 
  header: Languages
  collection: library
  item: html-css
  part: part4
  active: en
---

Most browsers have a "hidden" tool that allows us to look behind the scenes of a website. This is extremely helpful, either to examine our own website or to take inspirations from other websites. Currently, Chrome has the most complete tool, called **Chrome Developer Tools** (abbreviated **DevTools**).


## Opening Chrome DevTools

*DevTools* can be opened in multiple ways:

* Right-click on any element on a website and choose **Inspect Element**.
* Use `Ctrl+Shift+I` or `F12`.

Let's try this in our portfolio website. You should now see the following window:

![Open DevTools](/assets/library/html-css/part4/open-devtools-de.png)

<div class="alert alert-info">
  **Hint:** If you're using the Brackets editor it's best to deactivate the *Live Preview* and to reload the website in the browser. Otherwise, you will see some additional ids in the code that were added by Brackets.
</div>


## Inspecting an Element

With *DevTools* we can easily examine individual elements on our website.

Take the example of the `h2` element: Right-click on the "Welcome" title and choose *Inspect Element*. This opens *DevTools* and jumps to the `h2` element.

![DevTools h2 Element](/assets/library/html-css/part4/devtools-h2-de.png)

On the right side you can see the styles that are applied to the `h2` element. You should see the colors that we have defined in `main.css`. Below the `main.css` rules you can see an area for the `user agent stylesheet` rules. These are the default rules that are applied to `h2` elements by the browser.

At the bottom you will see a colored rectangle. This shows the size, spacings, and borders of the `h2` element. For example, you can see the value `5` for `padding`, as we defined it in `main.css`.


### Changing Styles and HTML

Now comes the exciting part: We can change or disable the styles and also edit the HTML live in the browser! For example, if we click on the color square of `background-color` we can select a different background color or we could enter a different value for `padding`.

The HTML can be changed by right-clicking on an element in the *DevTools* window and choosing **Edit...**.

That's a very convenient way for making experimental changes. The changes, however, are **only temporary**, when we reload the page, the original website is restored.


### Inspiration From Other Websites

With *DevTools* we can investigate any web page and change it temporarily. Make frequent use of this! For example, if you see a color that you like while browsing the web, you can use *DevTools* to retrieve the *Hex value* so that you can use it in your CSS.

For larger websites, styles can get quite long. There will be multiple CSS rules that override each other. You can see an example of this with our `h1` title element. The font size, which we have defined, overrides the standard font size of the browser. Therefore, this is crossed out.

![CSS Overwrite](/assets/library/html-css/part4/css-overwrite-de.png)


## Other DevTools Features

There are many more ways to work with *DevTools*. If you want to know more about it, read the [DevTools documentation](https://developer.chrome.com/devtools/index).

There is also a very good [DevTools online course](http://discover-devtools.codeschool.com/), which is available for free.


## What's next?

In the next part we create additional pages for a blog, projects and contact.

&rarr; Continue with [Part 5: Blog and Other Pages](/library/html-css/part5/)