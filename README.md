# code.makery.ch Website

This is the website source that is used for the [code.makery.ch](https://code.makery.ch).

This readme describes the setup of the website. With this information you can clone the entire website and run it on your local machine or on your own server.


## General Setup

The website uses the fantastic [Hugo](https://gohugo.io/) static site generator and is hosted on [Netlify](https://www.netlify.com/). Netlify makes a new build whenever a change is committed to GitHub.


### Building the Website

To build the website, install [Hugo](https://gohugo.io/), clone this repository, and run Hugo by typing `hugo server`. That's it. Hugo even compiles the `sass` files for you, so you don't need anything else (except a nice code editor, of course).


## Content

The important sections are `blog` and `library` which is where you will find the relevant content.


### Blog Posts

At the beginning of each (markdown) blog post we define meta information about the post in TOML format:

```
+++
title = "My Example Post"
date = 2018-05-03
updated = 2018-08-20
image = "some-image.png"
description = "This is the place where we can put a description in one or two sentences."
prettify = true
comments = true
tags = [ "Something1", "Something2" ]
css = [ "some-custom-css.css" ]
javascript = [ "some-custom-js.js", "more-custom-js.js" ]
+++
```

* `title`: The post title.
* `date`: The date of the blog post.
* `updated`: The date when the blog post was last updated.
* `image`: Used as meta information for the Open Graph in the website (is displayed in Facebook, Google+, etc. when the post is shared).
* `description`: The description is used as the summary in the blog overview and also for the meta description tag on the page (is displayed by search results). 
* `prettify`: (optional) If true, the Google prettify JavaScript library is included for code highlighting.
* `comments`: (optional) If true, the disqus comments are included. The disqus *shortname* is taken from the global model. The disqus *identifier* is the url of the blog post without the domain. The disqus *url* is the url with the domain. If you want to override any of those settings you can add the following sub-properties of `comments`: 
  * `commentsShortname`
  * `commentsIdentifier`
  * `commentsUrl`
  * `commentsTitle`
* `tags`: The tags of this blog post.
* `css`: (optional) Custom css file that should be included.
* `javascript`: (optional) Custom javascript file that should be included.


#### File Organization

Every blog post has its own folder and a file named `index.md`. The folder also contains all assets that are used in the blog post. This keeps the content together and allows us to use only the filename as reference to assets.


### Library

Library items are very similar to blog posts but contain some additional elements. 

```
+++
title = "My Example Article"
date = 2018-04-19
updated = 2018-05-07
canonical = "/library/my-series/part1/"
image = "some-image.png"
description = "This is the place where we can put a description in one or two sentences."
prettify = true
comments = false

sidebarName = "<i class=\"fa fa-fw fa-info\"></i> Background Info"
pagingName = "<i class=\"fa fa-fw fa-info\"></i>"
weight = 1

css = [ "some-custom-css.css" ]
javascript = [ "some-custom-js.js", "more-custom-js.js" ]

[[sidebars]]
header = "Download Sources"

[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Source of Examples"
link = "some-sources.zip"
+++
```

For a description of most items see blog post description above. Here are the items that are special to the library articles layout:

* `canonical` (optional): The canonical url. This is used if an article exists on two pages. It indicates to search engines which is the main url of the article. I use this for articles that still need translation to indicate that it is currently just a copy of the main article.  
* `sidebarName` (optional): By default, the sidebar shows the title of the article. If we want something else, maybe with an icon, we can provide it here.
* `pagingName` (optional): By default, the paging at the bottom of the pages is numbered. We may provide a different paging text here.
* `weight` (optional): Is used for sorting the articles inside a series. 
* `sidebars`: (optional) A list of custom sidebars. A single sidebar supports the following elements:
  * `header`: The header of the sidebar.
  * `sidebar.items`: The content (items) of the sidebar. Each item contains the following subelements:
    * `text`: The text displayed for this item.
    * `link`: The href of this item.


#### Series of Articles

A library item can be either a single article or a series of articles, optionally with multiple language files.

Every library item has one main article called `_index.en.md`. Additional articles belonging to the same series are organized in subfolders.

If we want to list the library item in the library overview, we must provide some additional meta information:

```
# Series Overview Info
overview = true
overviewImage = "some-overview-image.png"
overviewDescription = "Description shown in the overview."
topics = [ "SomeTopic1", "SomeTopic2" ]
weight = 2
```

Note: The main article is always shown at the top in the sidebar. The `weight` is used for sorting the library items in the library overview page.


## License

The entire website, including the tutorials and this template is released under the MIT License (see LICENSE file). I would love to hear about how you're using. Please don't forget to include some form of attribution. 