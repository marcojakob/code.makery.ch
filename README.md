# code.makery.ch Website

This is the website source that is used for the [code.makery.ch](http://code.makery.ch). It uses the fantastic [mixture.io](http://mixture.io) as its underlying technology. Mixture greatly simplifies my life!

This readme describes the set up of the mixture project and the relevant settings for this project. For a complete description of what you can do with mixture read the [mixture docs](http://docs.mixture.io/).


## General Settings

### Mixture Settings (mixture.json)

* `projectName` and `projectDescription` (is only used for the displayed name in mixture).
* `routes` are necessary for blog paging and blog tags to work. It defines which route takes which template file for rendering. 
* `convertHtml` must be defined for the *Convert to HTML* feature of mixture. 
  * We set `relativePath` to `false` so that paths are kept as they are and not converted to relative paths. We use website-relative paths everywhere in the project and we keep them that way for convertion. Relative paths might also work, except for the 404 page when it is shown from a subdirectory. 
  * The `default` property must be set to `index`. This creates a subfolder for each page containing an `index.html` file. This enables us to use GitHub Pages with *nice* urls like `http://code.makery.ch/blog/my-blog-entry/`. (Otherwise, the url would be `http://code.makery.ch/blog/my-blog-entry.html` or other not so nice alternatives.)

Most other settings in `mixture.json` are left on their defaults.


### GitHub Settings (.githubsettings)

The file `.githubsettings` contains information about the GitHub `username`, `password` (GitHub Token automatically encrypted, encryption is unique to the application and account), `repoUrl`, `branch`, etc.


### CNAME

* For publishing with a custom URL on GitHub pages, the `CNAME` file must contain the domain name.


### Create Icons

* Don't forget to create a favicon.ico file.



## Theme

The `theme` folder contains all sass, css, fonts, and javascript that make up the stylinig of the page.

The `sass` folder contains a the main styling file called `style.scss`. This file imports all other files: the `custom-variable`, `fonts`, and `bootstrap`. The sass files are automatically preprocessed and minified and copied into the `css` folder.

The `css` folder contains some additional css files for the code styling with *prettify*.



## Templates

The `templates` folder contains all liquid templates for individual pages.

* Normal liquid pages: They are based on a layout file and contain custom html content. 
* RSS liquid page: Generates an `rss.xml` file. There is also an RSS file for each tag (e.g. `rss-something1.xml`). The tag-specific rss files are referenced from the filtered blog pages (e.g. page `/blog/tag/something1/`).


### Layouts

Every page inherits a layout file from the `templates/layouts` subdirectory. Those  are the currently used layouts:

* `layout.liquid`: The default layout.
* `post.liquid`: For blog posts. Includes a post header with date and tags and disqus comments at the end.
* `article.liquid`: Similar to a blog post but with additional blocks for navigation between a series of articles (sidebar and paging). Sidebars can also be used for additional items like downloads or links.


### Includes

Includes are small templates that are included inside layout files. Some of the includes must likely be customized:

* `header.liquid`: Contains the logo and tagline.
* `navigation.liquid`: Contains the main navigation.
* `footer.liquid`: Contains the footer with feed and email subscriptions, copyright and attribution.



## Models

There is an in-built global mixture model, accessible via the `mixture` namespace.

We're also using a custom global model called `global` inside the `models` folder. This model contains the website title, intro, google anayltics id, domain, email, and more. 

It's important to define all the `tags` that are used. This list of tags is used by the `archive.liquid` template to display all possible tags. 


## Collections

Collections is the place where articles and blog posts are written. Every subdirectory corresponds to a collection. The `blog` collection is reserved for blog posts. All other collections are used for articles.


### Blog Collection

At the beginning of each (markdown) blog post we must define meta information about the post in a YAML format:

```
---
layout: post
title: My Example Post
date: 2014-05-03 00:00
slug: my-example-post
description: "This is the place where we can put a description in one or two sentences."
image: /assets/blog/14-05-03-my-example-post/some-image.png
published: true
prettify: true
comments: false
tags:
- Something1
- Something2
css:
- /assets/blog/14-05-03-my-example-post/some-custom-css.css
javascript: 
- /assets/blog/14-05-03-my-example-post/some-custom-js.js
- /assets/blog/14-05-03-my-example-post/more-custom-js.js
---
```

* `layout`: Defines the layout that is used (see above).
* `title`: The post title.
* `date`: The date of the blog post.
* `slug`: The url that will be used together with the collection name. The example above will result in the following url `/blog/my-example-post/`.
* `description`: (optional) The description is used for the meta description tag on the page (is displayed by search results). It can also be used in other places on the blog as a short summary.
* `image`: (optional) Used as meta information for the Open Graph in the website (is displayed in Facebook, Google+, etc. when the post is shared).
* `published`: Must be set to true for the post to be published.
* `prettify`: (optional) If true, the Google prettify JavaScript library is included for code highlighting.
* `comments`: (optional) If true, the disqus comments are included. The disqus *shortname* is taken from the global model. The disqus *identifier* is the url of the blog post without the domain. The disqus *url* is the url with the domain. If you want to override any of those settings you can add the following sub-properties of `comments` instead of writing true: 
  * `shortname:`
  * `identifier:`
  * `url:`
  * `title:`
* `tags`: (optional) The tags of this blog post.
* `css`: (optional) Custom css file that should be included.
* `javascript`: (optional) Custom javascript file that should be included.


#### Filename

It's a good practice to use a filename combined with date and slug. The example above would have the filename `14-05-03-my-example-post.md`.


#### Asset Directory

I like to put all assets belonging to a blog post inside one separate folder. The assets for the blog post `14-05-03-my-example-post.md` would thus be under `/assets/blog/14-05-03-my-example-post/`.



### Article Collections

Articles are very similar to blog posts but contain some additional elements, mainly `sidebars` and `updated`.


```
---
layout: article
title: "My Example Article"
date: 2014-04-19 00:00
updated: 2014-05-07 00:00
slug: my-example-article1
description: "This is the place where we can put a description in one or two sentences."
githubEdit: https://github.com/marcojakob/code.makery.ch/edit/master/collections/mycollection/my-example-article1.md
image: /assets/mycollection/my-example-article1/some-image.png
published: true
prettify: true
comments: false
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /mycollection/my-example-article-intro/
    paging: Intro
  - text: "Part 1: Lorem"
    link: /mycollection/my-example-article1/
    paging: 1
    active: true
  - text: "Additional Infos Part 1"
    link: /mycollection/my-example-article1-infos/
    icon-css: fa fa-fw fa-info
  - text: "Part 2: Ipsum"
    link: /mycollection/my-example-article2/
    paging: 2
  - text: "Part 3: Dolor"
    link: /mycollection/my-example-article3/
    paging: 3
  - text: "Part 4: Sit"
    link: /mycollection/my-example-article4/
    paging: 4
- header: "Download Sources"
  body:
  - text: Source of Examples
    link: /assets/mycollection/my-example-article/some-sources.zip
    icon-css: fa fa-fw fa-download
css:
- /assets/blog/14-05-03-my-example-post-1/some-custom-css.css
javascript: 
- /assets/blog/14-05-03-my-example-post-1/some-custom-js.js
- /assets/blog/14-05-03-my-example-post-1/more-custom-js.js
---
```

For a description of most items see above. Here are the items that are special to the article layout:

* `updated`: (optional) The date when the article was updated.
* `slug` (optional): The url that will be used together with the collection name. The example above will result in the following url `/mycollection/my-example-article1/`.
* `githubEdit` (optional): The url to edit the article on GitHub.
* `sidebars`: (optional) A list of custom sidebars. A single sidebar supports the following elements:
  * `header`: The header of the sidebar.
  * `body`: The content (items) of the sidebar. Each item contains the following subelements:
    * `text`: The text displayed for this item.
    * `link`: The href of this item.
    * `paging`: If set, the item is used for a paging element at the bottom of the website. The value is the text displayed for the paging element.
    * `icon-css`: Adds tag for a font icon, e.g. `<i class="fa fa-fw fa-info"></i>`


#### Filename

I use the desired url slug as filename. If we don't provide a `slug` yaml element, mixture just uses the filename as url. This is ok here as I don't put the date inside the filename.


#### Asset Directory

For the example above, the asset directory would be `/assets/mycollection/my-example-article1/`



## Template License

This template is released under the MIT License (see LICENSE file). If you use the template, I would love to hear about how you're using it and it'd be great if you could include some form of attribution. 