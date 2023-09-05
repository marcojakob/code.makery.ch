+++
title = "Icons on Websites"
date = 2015-04-09
updated = 2018-08-28
description = "Integrate icons with Icon Fonts and SVG on your website. Links to free icon libraries."
prettify = true
# comments = true
weight = 2

sidebarName = "<i class=\"fa fa-fw fa-flag\"></i> Icons on Websites"

# Custom Sidebars
[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-external-link\"></i> HTML & CSS Tutorial"
link = "/library/html-css/"
+++

Inserting icons into a website isn't difficult. But when you combine text and icons (for example on a button), or if the icon should be resizable, it can cause some trouble.

The solution is to use **Icon Fonts** or **SVG Icons**. Icon fonts and SVG icons can be adjusted to any size and color.

![Icons](icons.png)

In the following, we will look at a few options.


## Ionicons (SVG)

[Ionicons](https://ionicons.com/) are very easy to integrate in your website.

Go to the [Ionicons usage](https://ionicons.com/usage) page and copy the installation script tag. Place the `<script>` near the end of your page, right before the closing `</body>` tag.

Here is an example of the script tag, but you should get the newest version from the website:

<pre class="prettyprint lang-javascript">
&lt;script src="https://unpkg.com/ionicons@4.4.2/dist/ionicons.js">&lt;/script>
</pre>

To use an icon just choose one from the [Ionicons Icon Overview](https://ionicons.com/). If you click on an icon it will show you the code you must use. Here is an example with an icon and text:

<pre class="prettyprint lang-html">
&lt;ion-icon name="heart">&lt;/ion-icon> I love icons
</pre>

This will result in an icon and text like this:

![Love Icons](love-icons.png)


## Other Icon Libraries

* [Font Awesome](https://fontawesome.com/free) - a very popular icon font.
* [Octicons](https://octicons.github.com/)
* [Iconic](https://useiconic.com/open/)
* [Glyph](http://glyph.smarticons.co/)
* [Bytesize](https://github.com/danklammer/bytesize-icons)
* [Google Material Icons](https://material.io/tools/icons/)
* And much more...

