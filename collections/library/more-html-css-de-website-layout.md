---
layout: article
title: "Website-Layout mit Bootstrap 3"
date: 2014-08-16 00:00
slug: more-html-css/de/website-layout
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
    active: true
---

Standardmässig werden HTML-Elemente auf zwei Arten angeordnet:

**Blockelemente** beginnen immer in einer neuen Zeile. Beispiele für Blockelemente sind `<div>`, `<h1>`, `<p>` und `<li>`.

![Blockelemente](/assets/library/more-html-css/website-layout/block-elements.png)

**Inline-Elemente** stehen in der gleichen Zeile. Beispiele für Inline-Elemente sind `<span>`, `<a>`, `<strong>`, `<em>` und `<img>`.

![Inline-Elemente](/assets/library/more-html-css/website-layout/inline-elements.png)

Diese zwei Möglichkeiten reichen jedoch nicht, um ein richtiges Layout für eine Webseite zu erstellen. 

Die meisten Webseiten haben entweder ein Layout mit **zwei Spalten** ...

![Zwei-Spalten Layout](/assets/library/more-html-css/website-layout/two-columns.png)

... oder ein Layout mit **drei Spalten**.

![Drei-Spalten Layout](/assets/library/more-html-css/website-layout/three-columns.png)

So etwas hinzubekommen ohne ein CSS-Framework wie *Bootstrap* wäre eine rechte Herausforderung. Mit Bootstrap wird es einfacher. 

Zusätzlich wird das Layout in Bootstrap automatisch auf die Bilschirmgrösse angepasst (sogenanntes [Responsive Layout](http://de.wikipedia.org/wiki/Responsive_Webdesign)). Wir können zum Beispiel angeben, dass auf kleinen Bilschirmen die Spalten untereinander angezeigt werden sollen, weil sie nebeneinander nicht mehr Platz hätten.

<div class="alert alert-info">
Falls Sie Bootstrap in Ihrem Projekt noch nicht integriert haben, lesen Sie den Teil über <a href="/library/html-css/de-part6/" class="alert-link">Bootstrap Framework verwenden</a>.
</div>


## Das Bootstrap Raster

Bootstrap beinhaltet ein **12-spaltiges Rastersystem** für das Layout (siehe [Dokumentation des Bootstrap-Raster](http://holdirbootstrap.de/css/#grid)). Ein Raster kann man sich vorstellen wie eine unsichtbare Tabelle mit zwölf Spalten:

![Zwölf Spalten](/assets/library/more-html-css/website-layout/bootstrap-twelve-columns.png)

Wir können nun unsere eigenen Spalten definieren, indem wir angeben, wie breit sie in diesem Raster sein sollen.


### Beispiel für ein 2-spaltiges Layout

In diesem Beispiel definieren wir eine linke Spalte mit der Breite von 4 und eine rechte Spalte mit der Breite von 8. Links könnte man eine Navigation einfügen und rechts den Seiteninhalt.

![2-Spalten Layout](/assets/library/more-html-css/website-layout/bootstrap-two-columns.png)

Im HTML-Code würde dieses Layout wie folgt aussehen:

<pre class="prettyprint lang-html">
&lt;div class="<mark>container</mark>">
  &lt;div class="<mark>row</mark>">
    &lt;div class="<mark>col-sm-4</mark>">
      Hier kommt der Inhalt der linken Spalte.
    &lt;/div>
    &lt;div class="<mark>col-sm-8</mark>">
      Hier kommt der Inhalt der rechten Spalte.
    &lt;/div>
  &lt;/div>
&lt;/div>
</pre>

<div class="alert alert-info">
**Zu Beachten beim Bootstrap-Raster:**

<ul>
  <li>Das Raster sollte immer in einem `<div>` mit einer `container`-Klasse sein. (Wenn auch bei grossen Bilschirmen die ganze Breite ausgenutzt werden soll, kann man alternativ die `container-fluid`-Klasse verwenden.)</li>
  <li>Innerhalb des Containers kommt ein `<div>` mit der Klasse `row`. Damit definieren wir eine Zeile im Raster.</li>
  <li>Innerhalb der Zeilen kommen schliesslich die Spalten mit den `col`-Klassen.  </li>
</ul>
</div>


### Bildschirmgrössen

Im Beispiel oben sehen Sie eine CSS-Klasse für die linke Spalte: `col-sm-4`

Eine Spaltenangabe beinhaltet immer in der Mitte eine Angabe über die Bildschirmgrösse. Es gibt die folgenden vier Bildschirmgrössen:

* `col-xs` - Spalte für Extra-Small-Geräte (Smartphones)
* `col-sm` - Spalte für Small-Geräte (Tablets)
* `col-md` - Spalte für Medium-Geräte (kleiner Desktop)
* `col-lg` - Spalte für Large-Geräte (grosser Desktop)

Die Angabe `col-sm-4` bedeutet also, dass ungefähr ab der grösse eines Tablets eine Spalte der Breite 4 dargestellt wird. 

Alle Bildschirme, welche kleiner sind als die angegebene Grösse, stellen die Spalten automatisch auf der ganzen Breite dar.

So sieht unser Beispiel für das 2-spaltige Layout auf einem Smartphone aus:

![2-Spalten Layout Smartphone](/assets/library/more-html-css/website-layout/bootstrap-two-columns-smartphone.png)


### Der Portfolio-Blog mit 2 Spalten

Im [HTML & CSS Tutorial](/library/html-css/de/) haben wir ein Portfolio entwickelt mit einer Blog-Seite. Das folgende Beispiel zeigt ein 2-Spalten-Layout für diese Blog-Seite. In der linken Spalte ist der bisherige Inhalt. In der rechten Spalte haben wir neu Platz für ein paar Links. 

<img src="/assets/library/more-html-css/website-layout/portfolio-two-columns-de.png" alt="Portfolio" class="img-thumbnail">


##### blog/index.html

<pre class="prettyprint lang-html">
&lt;div class="container">
  &lt;h1 class="title">Blog&lt;/h1>

  &lt;p>Hier schreibe ich über alles, was mir beim Lernen von Webprogrammierung begegnet.&lt;/p>

  &lt;div class="<mark>row</mark>">
    &lt;div class="<mark>col-sm-8</mark>">
      &lt;h2>Blogeinträge&lt;/h2>
      &lt;ul>
        &lt;li>Weitere Einträge folgen...&lt;/li>
        &lt;li>&lt;a href="2014-08-02-erster-eintrag/">Erster Eintrag&lt;/a>&lt;/li>
      &lt;/ul>
    &lt;/div>

    &lt;div class="<mark>col-sm-3 col-sm-offset-1</mark>">
      &lt;h3>Empfehlungen&lt;/h3>
      &lt;p>
        Die folgenden Blogs von Kollegen kann ich sehr empfehlen:
      &lt;/p>
      &lt;ul class="list-unstyled">
        &lt;li>&lt;a href="#">Blog 1&lt;/a>&lt;/li>
        &lt;li>&lt;a href="#">Blog 2&lt;/a>&lt;/li>
        &lt;li>&lt;a href="#">Blog 3&lt;/a>&lt;/li>
        &lt;li>&lt;a href="#">Blog 4&lt;/a>&lt;/li>
      &lt;/ul>
    &lt;/div>
  &lt;/div>

&lt;/div>
</pre>

#### Hinweise

* Bei der zweiten Spalte wurde noch eine zweite CSS-Klasse hinzugefügt: `col-sm-offset-1`. Dies bedeutet, dass die zweite Spalte um eins nach rechts gerückt werden soll. Damit erhalten wir einen etwas grösseren Abstand zwischen den beiden Spalten.
* Testen Sie das Layout in unterschiedlichen Browsergrössen.
* Die Klasse `list-unstyled` bewirkt, dass die Listenelemente ohne Punkt angezeigt werden (siehe [Bootstrap Listen](http://holdirbootstrap.de/css/#type-lists)).


### Beispiel für ein 3-spaltiges Layout

In diesem Beispiel definieren wir drei Spalten.

![3-Spalten Layout](/assets/library/more-html-css/website-layout/bootstrap-three-columns.png)

Im HTML-Code würde dieses Layout wie folgt aussehen:

<pre class="prettyprint lang-html">
&lt;div class="<mark>container</mark>">
  &lt;div class="<mark>row</mark>">
    &lt;div class="<mark>col-sm-3</mark>">
      Hier kommt der Inhalt der linken Spalte.
    &lt;/div>
    &lt;div class="<mark>col-sm-6</mark>">
      Hier kommt der Inhalt der mittleren Spalte.
    &lt;/div>
    &lt;div class="<mark>col-sm-3</mark>">
      Hier kommt der Inhalt der rechten Spalte.
    &lt;/div>
  &lt;/div>
&lt;/div>
</pre>


## Weitere Informationen zu Layout

Lesen Sie den Abschnitt über das [Raster-System](http://holdirbootstrap.de/css/#grid) in der Bootstrap Dokumentation.

Eine ausführliche Erklärung, warum das Raster-System funktioniert finden Sie in [The Subtle Magic Behind Why the Bootstrap 3 Grid Works](http://www.helloerik.com/the-subtle-magic-behind-why-the-bootstrap-3-grid-works). 





