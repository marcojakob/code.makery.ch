---
layout: article
title: "Mehr HTML & CSS: Bilder"
date: 2014-08-22 00:00
slug: more-html-css/de/images
github: false
published: true
prettify: true
comments: false
sidebars:
- header: Teile dieser Serie
  body:
  - text: "Einleitung"
    link: /library/more-html-css/de/
    paging: Intro
  - text: "Website-Layout"
    link: /library/more-html-css/de/website-layout/
    paging: 1
  - text: "Icons"
    link: /library/more-html-css/de/icons/
    paging: 2
  - text: "Bilder"
    link: /library/more-html-css/de/images/
    paging: 3
    active: true
  - text: "Textformatierung"
    link: /library/more-html-css/de/text/
    paging: 4
---

In diesem Teil lernen Sie ein paar Tricks mit Bildern:

* [Responsive Bilder](#responsive-bilder) - passen sich auf Bildschirmgrösse an
* [Bilder ausrichten](#bilder-ausrichten) - zentrieren, links, rechts
* [Bilder mit Konturen](#bilder-mit-konturen) - Ecken abrunden, etc.
* [Gute Bildquellen](#gute-bildquellen) - hier finden Sie gute Bilder



Für die meisten Beispiele wird [Bootstrap 3](http://holdirbootstrap.de/) verwendet.

<div class="alert alert-warning">
Falls Sie Bootstrap in Ihrem Projekt noch nicht integriert haben, lesen Sie zuerst den Teil über <a href="/library/html-css/de/part6/" class="alert-link">Bootstrap Framework verwenden</a> aus dem HTML & CSS Tutorial.
</div>

Bilder in eine Webseite einbinden gelingt am einfachsten wie folgt:


##### Bild einfügen

<pre class="prettyprint lang-html">
&lt;img src="mein-bild.png" alt="Mein Bild">
</pre>

Damit hat das Bild eine fixe Grösse. Man kann das Bild zwar verkleinern oder vergrössern mit einer CSS-Regel wie `width: 200px;`, aber das ist oft nicht sinnvoll. Man versucht nämlich immer die Downloadgrösse der Webseite zu optimieren. Wenn man also ein Bild mit 400px-Breite herunterlädt und es dann immer auf 200px verkleinert, dann ist das nicht optimal. Deshalb sollte man schauen, dass das **Bild immer die optimale Grösse hat**.

<div class="alert alert-info">
**Merke:** Ein Bild sollte nicht grösser sein als es im Browser dargestellt wird. Ansonsten lädt man ein unnötig grosses Bild herunter.
</div>


## Responsive Bilder

Wenn eine Webseite auf unterschiedlichen Bildschirmen angezeigt wird, dann kommt man nicht darum herum, diese auf kleinen Bildschirmen zu verkleinern.

Bootstrap 3 bietet hierzu eine CSS-Klasse `img-responsive`, welche Bilder automatisch auf die Bildschirmgrösse anpasst (lesen Sie über [Responsive Bilder in der Bootstrap Dokumentation](http://holdirbootstrap.de/css/#images-responsive)).


##### Responsive Bild einfügen

<pre class="prettyprint lang-html">
&lt;img src="mein-bild.png" alt="Mein Bild" class="img-responsive" >
</pre>

<div class="alert alert-info">
**Merke:** Die CSS-Klasse `img-responsive` sollten Sie bei Ihren Bildern hinzufügen, damit diese auf kleinen Bildschirmen automatisch angepasst werden.
</div>


## Bilder ausrichten

### Zentrieren

Mit der CSS-Klasse `center-block` können Sie mit Bootstrap 3 Bilder zentrieren (siehe auch [center](http://holdirbootstrap.de/css/#helper-classes-center) in der Bootstrap Dokumentation):

<img src="/assets/library/more-html-css/images/center-block.png" class="center-block">


##### Bild zentrieren

<pre class="prettyprint lang-html">
&lt;img src="..." alt="..." class="center-block">
</pre>

<div class="alert alert-info">
**Hinweis:** Die Klasse `center-block` kann auch zum Zentrieren von anderen HTML-Elementen gebraucht werden. Für Text und andere Inline-Elemente muss aber `text-center` verwendet werden (siehe <a href="http://holdirbootstrap.de/css/#type-alignment" class="alert-link">Ausrichtungsklassen</a> in der Bootstrap Dokumentation).
</div>


### Links und rechts ausrichten

Bilder können nach links und nach rechts ausgerichtet werden. Der Text fliesst dabei um die Bilder herum. Dazu werden sogenannte *Floats* verwendet (lesen Sie über [Floats](http://holdirbootstrap.de/css/#helper-classes-floats) in der Bootstrap Dokumentation).


##### Links ausrichten

<pre class="prettyprint lang-html">
&lt;img src="..." alt="..." class="pull-left">
</pre>

![Links ausrichten](/assets/library/more-html-css/images/pull-left.png)


##### Rechts ausrichten

<pre class="prettyprint lang-html">
&lt;img src="..." alt="..." class="pull-right">
</pre>

![Rechts ausrichten](/assets/library/more-html-css/images/pull-right.png)


#### Neue Zeile

Beim Ausrichten nach links oder rechts werden alle nachfolgenden Elemente neben dem  Bild dargestellt. Falls man in einer neuen Zeile beginnen möchte, muss man die `clearfix`-Klasse verwenden. Diese Klasse wird meistens auf ein übergeordnetes `<div>`-Element angewendet (mehr Infos zu [clearfix](http://holdirbootstrap.de/css/#helper-classes-clearfix) in der Bootstrap Dokumentation):

<pre class="prettyprint lang-html">
&lt;div class="clearfix">
  &lt;img src="..." alt="..." class="pull-left">
  &lt;p>Dieser Text ist neben dem Bild.&lt;/p>
&lt;/div>
&lt;p>Dieser Text ist unter dem Bild.&lt;/p>
</pre>

![Clearfix](/assets/library/more-html-css/images/clearfix.png)


#### Abstand neben dem Bild

Meist möchte man neben dem Bild einen Abstand zum Text oder zu einem anderen Inhalt. Dies erreicht man am einfachsten durch definieren eines `margins` im CSS.

Wenn wir im CSS eine Regel für das `img`-Element erstellen würden, dann würde diese auf alle Bilder auf der Webseite angewendet werden. Dies ist meist nicht sinnvoll. 

Für den Abstand rechts von einem Bild führen deshalb eine neue CSS-Klasse ein. Wir nennen diese `abstand-rechts`:


##### HTML für Abstand rechts

<pre class="prettyprint lang-html">
&lt;img src="..." alt="..." class="pull-left abstand-rechts">
</pre>


Im CSS definieren wir jetzt den Abstand.

##### CSS für Abstand rechts

<pre class="prettyprint lang-css">
.abstand-rechts {
  margin-right: 10px; 
}
</pre> 

![Abstand](/assets/library/more-html-css/images/margin.png)

Neben `margin-right` gibt es natürlich `margin-top`, `margin-bottom` und `margin-left`.


## Bilder mit Konturen

Bootstrap 3 bietet Möglichkeiten, um Bilder ganz einfach auf einer Webseite zu gestalten. Sie können die Ecken abrunden, einen Kreis ausschneiden oder einen dezenten Rahmen hinzufügen:

![Bilder mit Konturen](/assets/library/more-html-css/images/image-shapes.png)

Mit den folgenden drei CSS-Klassen können Sie Ihren Bildern solche Konturen hinzufügen (weitere Infos unter [Bilder-Konturen](http://holdirbootstrap.de/css/#images-shapes) in der Bootstrap Dokumentation):


##### Konturen mit Bootstrap

<pre class="prettyprint lang-html">
&lt;img src="..." alt="..." class="img-rounded">
&lt;img src="..." alt="..." class="img-circle">
&lt;img src="..." alt="..." class="img-thumbnail">
</pre>


## Gute Bildquellen

Es gibt viele Quellen für Bilder im Internet, aber solche, die gute Bilder frei zur Verfügung stellen sind manchmal schwer zu finden. Hier sind ein paar, die ich empfehlen kann:

* [Stock photos that don't suck](https://medium.com/@dustin/stock-photos-that-dont-suck-62ae4bcbe01b) - eine Liste von Webseiten mit den besten gratis Fotos
* [The Best Places to Find Free, High-Res Images for your Website](http://www.labnol.org/internet/find-free-images/24990/) - eine weitere Liste mit den besten Webseiten für gratis Bilder mit hoher Auflösung
* [Iconfinder](https://www.iconfinder.com/) - Viele frei verfügbare Icons (Achtung: je nach Lizenz muss der Author genannt werden)


## Tools für die Bildbearbeitung

### Grafikprogramme zum Installieren

* [Photoshop](http://www.adobe.com/products/photoshop.html) - professionell, teuer
* [Photoshop Elements](http://www.adobe.com/products/photoshop-elements.html) - kleiner Bruder von Photoshop
* [GIMP](http://www.gimp.org/) - beste Gratis-Alternative
* [Paint.net](http://www.getpaint.net/) - auch gratis, aber einfacher als GIMP
* [Inkscape](http://www.inkscape.org/) - ein Gratis-Programm für **Vektorgrafiken**


### Grafikprogramme Online

* [PIXLR Editor](http://pixlr.com/editor/) - beste online Bildbearbeitung
* [PIXLR Express](http://pixlr.com/express/) - schnelle Effekte und Filter für Fotos
* [iPiccy](http://ipiccy.com/)