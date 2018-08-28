+++
title = "Icons auf Website verwenden"
date = 2015-04-09
updated = 2018-08-28
description = "Icons mit Icon Fonts und SVG auf einer Webseite einbinden. Links zu verschiedenen Gratis-Bibliotheken für Icons."
prettify = true
comments = true
commentsIdentifier = "/library/more-html-css/de/icons/"
aliases = [ 
  "/library/more-html-css/de/icons/" 
]
weight = 2

sidebarName = "<i class=\"fa fa-fw fa-flag\"></i> Icons auf Website verwenden"

# Custom Sidebars
[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-external-link\"></i> HTML & CSS Tutorial"
link = "/de/library/html-css/"
+++

Icons in eine Webseite einzufügen ist nicht schwer. Aber wenn man Texte und Icons kombiniert (z.B. für einen Button) oder wenn die Icons in der Grösse veränderbar sein sollen, dann wird es schwierig.

Die Lösung für dieses Problem sind **Icon Fonts** und **SVG Icons**. Icon Fonts und SVG Icons können in der Grösse und Farbe angepasst werden. 

![Icons](icons.png)

Im Folgenden schauen wir ein paar Optionen an.


## Ionicons (SVG)

[Ionicons](https://ionicons.com/) sind sehr einfach in der Website zu integrieren.

Gehen Sie zur [Ionicons Usage](https://ionicons.com/usage) Seite und kopieren Sie das Script Tag. Platzieren sie das `<script>` Tag am Schluss Ihrer Website, gerade vor dem schliessenden `</body>` Tag.

Hier ist ein Beispiel. Sie sollten jedoch die neuste Version von der Website oben kopieren:

<pre class="prettyprint lang-javascript">
&lt;script src="https://unpkg.com/ionicons@4.4.2/dist/ionicons.js">&lt;/script>
</pre>

Um ein Icon zu verwenden, wählen Sie eines aus der [Ionicons Icon Übersicht](https://ionicons.com/) aus. Wenn Sie darauf klicken, wird der Code angezeigt, welcher eingefügt werden muss. Hier ist ein Beispiel mit Icon und Text:

<pre class="prettyprint lang-html">
&lt;ion-icon name="heart">&lt;/ion-icon> I love icons
</pre>

So sollte das Resultat aussehen:

![Love Icons](love-icons.png)


## Andere Icon-Bibliotheken

* [Font Awesome](https://fontawesome.com/free) - eine sehr beliebte Icon Font.
* [Octicons](https://octicons.github.com/)
* [Iconic](https://useiconic.com/open/)
* [Glyph](http://glyph.smarticons.co/)
* [Bytesize](https://github.com/danklammer/bytesize-icons)
* [Google Material Icons](https://material.io/tools/icons/)
* Und viele mehr...
