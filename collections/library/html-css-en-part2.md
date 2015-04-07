---
layout: article
title: "HTML & CSS Tutorial - Part 2: Publishing Your Website"
date: 2015-04-01 00:00
slug: html-css/part2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/html-css-en-part2.md
description: "Learn to publish a website live in the internet. Compare different hosting possibilities like BitBalloon, Dropbox, Google Drive, and GitHub Pages."
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
    active: true
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
  - text: "Next Steps"
    link: /library/html-css/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
languages: 
  header: Languages
  collection: library
  item: html-css
  part: part2
  active: en
---

In the previous part you have created your first web site. It would be a pity if this website only stays on your computer and could never be looked at by anyone else. Therefore, we will now learn how to publish our website live in the internet.

<div class="alert alert-info">
**Note:** There will not be many visitors on your website right from the start. Unless you tell people about the address of your site they will probably not find it.
</div>


## Hosting

What do you need that our website can be viewed on the Internet? You need a **server** where your website is stored and an **address** (URL) so that your website can be found on the server.

There are numerous services where you can rent server space. Such a service is called [Webhosting](http://en.wikipedia.org/wiki/Web_hosting_service). We will look at a few options for (free) hosting. If this is your first website, I recommend that you start with **BitBalloon**.


### Hosting on BitBalloon

![BitBalloon Logo](/assets/library/html-css/part2/bitballoon-logo.png)

[BitBalloon](https://www.bitballoon.com/) is a wonderfully simple service which I highly recommend. Here's how you can use it.

1. Subscribe on the [Bitballoon website](https://www.bitballoon.com/). Click on *Sign Up* and then choose *Sign in Using Persona*. Then enter your e-mail address and a password.

2. Once you are logged in, you can simply drag your entire web folder (your Portfolio folder) in the space provided.   
![BitBalloon drag and drop](/assets/library/html-css/part2/bitballoon-drag-and-drop.png)

3. Hard to believe, but that's it. Once the page has been uploaded a URL is generated for you which points to your site. Click this URL and check whether the page is displayed correctly.

4. To shorten your URL you can click on **Edit name** and enter an arbitrary name.   
![BitBalloon veröffentlicht](/assets/library/html-css/part2/bitballoon-published.png)

5. For Updates: When you want to release a new version of your website, simply drag the folder into the box with the text **Drag & Drop to Update your Site**.


### Hosting on Google Drive

![Google Drive Logo](/assets/library/html-css/part2/google-drive-logo.png)

[Google Drive](https://drive.google.com/) may also be used as hosting service. The disadvantage is that you will get a very long URL. Here is how it works.

1. Upload your website folder to Google Drive.

2. Select the folder on Drive and choose **Share**. Click on **Advanced** and change the access to **Public on the web**.

3. Open the folder in Drive and verify that all website file have been uploaded (index.html, image, CSS, etc.).

4. Copy the characters that follow after `#folders/` in the address bar of your browser. This is the ID of your folder.   
![Google Drive Copy Characters](/assets/library/html-css/part2/google-drive-copy-string.png)

5. In your browser, enter `http://googledrive.com/host/` followed by the characters you copied in the last step.   
![Google Drive Hosting Address](/assets/library/html-css/part2/google-drive-host-address.png)

Anyone you send this link will be able to access your website.


### Hosting on Dropbox

![Dropbox Logo](/assets/library/html-css/part2/dropbox-logo.png)

You can also use [Dropbox](https://www.dropbox.com/) as a hosting server. For this to work additional service are required for forwarding website requests. Two possible services are [Pancake.io](https://pancake.io/) or [DropPages](http://droppages.com/).


### Hosting on GitHub

![GitHub Logo](/assets/library/html-css/part2/github-logo.jpg)

If you already have some experience with programming then GitHub is a great way for web site hosting. GitHub is a platform to facilitate cooperation among programmers and also offers hosting for web pages on [GitHub Pages](https://pages.github.com/). GitHub Pages provides more options (for example with your own domain name link), but it requires skills in working with [Git](http://de.wikipedia.org/wiki/Git).


***

## URL Shorteners

Depending on the hosting you chose, the URL for your website might be quite long. There are simple services for shortening your URL.

Shorened URL's could also be entered on a mobile phone. Some services also create a [QR-Code](http://de.wikipedia.org/wiki/QR-Code).

Here is a list of a few URL shorteners:

* [Goo.gl](https://goo.gl/) (includes a QR-Code under *Details*)
* [bit.ly](https://bitly.com/)
* [Tiny.cc](http://tiny.cc/)


## Your Own Domain Name

There will come a time when you'll want your very own domain name such as `http://www.my-super-website.com`. In some cases you can register a domain name right at your hosting provider (for example at BitBalloon).

Otherwise, there are numerous web hosting and registrar services. It's worth it to compare a few services. If you want personal support, you can also contact a provider in your area.


## What's next?

&rarr; In the next part we will upgrade our site with some styling. Continue with [Part 3: Introduction to CSS](/library/html-css/part3/).

