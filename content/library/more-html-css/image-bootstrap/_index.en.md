+++
title = "Images with Bootstrap"
date = 2015-04-09
updated = 2018-08-28
description = "Learn some tricks on how to use images with Bootstrap."
prettify = true
comments = true
weight = 3

sidebarName = "<i class=\"fa fa-fw fa-image\"></i> Images with Bootstrap"

# Custom Sidebars
[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-external-link\"></i> HTML & CSS Tutorial"
link = "/library/html-css/"
+++

<div class="alert alert-info">
This page has been updated to cover <a href="https://getbootstrap.com/" class="alert-link">Bootstrap 4</a>. 
</div>

In this part we will learn some tricks on how to use images with Bootstrap.

- [Responsive Images](#responsive-images) - automatically fit the screen size
- [Aligning Images](#aligning-images) - center, left align, right align
- [Image Shapes](#image-shapes) - Round edges, etc.

<div class="alert alert-warning">
  If you haven't yet integrated Bootstrap in your project, please read <a href="/library/html-css/part7/" class="alert-link">how to use the Bootstrap Framework</a> in the HTML &amp; CSS Tutorial.
</div>

The easiest way to include images in a website is as follows.

##### Insert Images

<pre class="prettyprint lang-html">
&lt;img src="my-image.png" alt="My Image">
</pre>

## Responsive Images

When a web page is displayed on different screens you will have to shrink images on smaller screens.

Bootstrap provides a CSS class `img-fluid` that will automatically adjusts images to fit the container size (read about [responsive images in the Bootstrap documentation](https://getbootstrap.com/docs/4.1/content/images/#responsive-images)).

##### Insert a responsive image

<pre class="prettyprint lang-html">
&lt;img src="my-image.png" alt="My Image" class="img-fluid" >
</pre>

## Image Sizes

Although, you can shrink or enlarge the image with a CSS rule like `width: 200px;`, this is often not what you want. You should always try to optimize the download size of your web page. If you let the user download a 400px wide image and display it at 200px it might be a waste of bandwidth.

### Resulution Switching

If you want to optimize further, you could server small images to small screens while serving large images to large screens. This is called resolution switching for responsive images. In [this article on responsive images](https://developer.mozilla.org/en-US/docs/Learn/HTML/Multimedia_and_embedding/Responsive_images) you can learn how this can be done using the `srcset` attribute.

## Aligning Images

Also see [Aligning Images](https://getbootstrap.com/docs/4.1/content/images/#aligning-images) in the official Bootstrap documentation.

### Center

With the CSS class `mx-auto` you can center images in Bootstrap. Images are inline elements by default. They can only be centered if we make it a block element by adding the class `d-block`.

![Image Center](center.png)

##### Center an image

<pre class="prettyprint lang-html">
&lt;img src="..." alt="..." class="mx-auto d-block">
</pre>

<div class="alert alert-info">
  <strong>Note:</strong> The class <code>mx-auto</code> can also be used to center any HTML elements. But for text and other inline elements you should use <code>text-center</code> instead (see <a href="https://getbootstrap.com/docs/4.1/utilities/spacing/#horizontal-centering" class="alert-link">horizontal centering</a> and <a class="alert-link" href="https://getbootstrap.com/docs/4.1/utilities/text/#text-alignment">text alignment</a>).
</div>

### Left and Right Align

Images can be aligned to the left or to the right. The text will then flow around the image. To do this we use the so-called _floats_ (read about [floats](https://getbootstrap.com/docs/4.1/utilities/float/) in the Bootstrap documentation).

##### Float left

<pre class="prettyprint lang-html">
&lt;img src="..." alt="..." class="float-left">
</pre>

![Left Align](float-left.png)

##### Float right

<pre class="prettyprint lang-html">
&lt;img src="..." alt="..." class="float-right">
</pre>

![Right Align](float-right.png)

#### New Line after Float (Clearfix)

When floating to the left or right all following elements are displayed next to the image. If you want to start on a new line, you have to use the `clearfix` class. This class is usually added on a parent `<div>` element (more info about [clearfix](https://getbootstrap.com/docs/4.1/utilities/clearfix/) in the Bootstrap documentation).

<pre class="prettyprint lang-html">
&lt;div class="clearfix">
  &lt;img src="..." alt="..." class="float-left">
  &lt;p>This text appears next to the image.&lt;/p>
&lt;/div>
&lt;p>This text appears below the image.&lt;/p>
</pre>

![Clearfix](clearfix.png)

#### Margin Around Image

Usually you will want some margin between the image and text or other content. A simple way to do this is to use the [Bootstrap Spacing Classes](https://getbootstrap.com/docs/4.1/utilities/spacing/) like `mr-2`. But you could also define `margins` in your CSS.

##### HTML for Gap Right

<pre class="prettyprint lang-html">
&lt;img src="..." alt="..." class="pull-left mr-2">
</pre>

![Gap](margin.png)

## Image Shapes

Bootstrap provides ways for some simple image styles. You can [round the corners](https://getbootstrap.com/docs/4.1/utilities/borders/#border-radius), [cut out a circle](https://getbootstrap.com/docs/4.1/utilities/borders/#border-radius) or [add a subtle frame](https://getbootstrap.com/docs/4.1/content/images/#image-thumbnails).

![Image Shapes](image-shapes.png)

You can add the following CSS classes to your images:

##### Image Shapes with Bootstrap

<pre class="prettyprint lang-html">
&lt;img src="..." alt="..." class="rounded">
&lt;img src="..." alt="..." class="rounded-circle">
&lt;img src="..." alt="..." class="img-thumbnail">
</pre>

## Other Articles for Images

The following articles about images might also be interesting to you:

- [Image Editing](/library/more-html-css/image-editing/)
- [Free Image Sources](/library/more-html-css/image-sources/)
