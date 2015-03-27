---
layout: article
title: "HTML & CSS Tutorial - Teil 6: Bootstrap Framework verwenden"
date: 2014-08-13 00:00
updated: 2015-03-18 00:00
slug: html-css/de/part6
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/html-css-de-part6.md
description: "Lernen Sie das Bootstrap-Framework für Ihre Webseite einzusetzen."
image: /assets/library/html-css/portfolio.png
published: true
prettify: true
comments: true
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/html-css/de/
    paging: Intro
  - text: "Teil 1: Unsere erste Webseite"
    link: /library/html-css/de/part1/
    paging: 1
  - text: "Teil 2: Webseite veröffentlichen"
    link: /library/html-css/de/part2/
    paging: 2
  - text: "Teil 3: Einführung in CSS"
    link: /library/html-css/de/part3/
    paging: 3
  - text: "Teil 4: Entwicklertools im Browser"
    link: /library/html-css/de/part4/
    paging: 4
  - text: "Teil 5: Navigieren zwischen Seiten"
    link: /library/html-css/de/part5/
    paging: 5
  - text: "Teil 6: Bootstrap Framework verwenden"
    link: /library/html-css/de/part6/
    paging: 6
    active: true
  - text: "Nächste Schritte"
    link: /library/html-css/de/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Downloads
  body:
  - text: Portfolio Teil 6
    link: https://github.com/marcojakob/tutorial-html-css/releases/download/v0.4/portfolio-de-part6.zip
    icon-css: fa fa-fw fa-file-archive-o
---

Bis hierhin haben wir alles HTML und CSS von Grund auf selber programmiert. Unser Projekt, das *Web Portfolio*, hat bereits einige HTML-Seiten mit etwas Inhalt, eine noch unfertige Navigation und etwas Styling.

Ganz von Hand unser Projekt zu einem kompletten und modernen Webauftritt weiter zu entwickeln wäre **eine riesige Menge Arbeit**! Hier ist eine Liste mit den paar wichtigsten Aufgaben, die wir noch bewältigen müssten (keine Angst, es gibt eine Lösung!):

* Schriftart und Textgrösse für alle Überschriften und Abschnitte anpassen.
* Knöpfe, Textfelder, etc. schön gestalten (sehen standardmässig recht veraltet aus).
* Abstände definieren und Elemente auf der Seite positionieren (ist sehr schwierig).
* Einen einheitlichen Stil definieren, damit alle verschiedenen Browser in etwa das Gleiche anzeigen.
* Die Webseiten optimieren für verschiedene Bildschirmgrössen (Mobile, Tablet und Desktop)
* Und Einiges mehr...

Zum Glück sind wir nicht alleine mit diesen Aufgaben - dies betrifft mehr oder weniger alle Webdesigner. Da es kaum sinnvoll ist, wenn alle immer wieder die gleichen Aufgaben lösen, sind sogenannte *Frameworks* entstanden, die diese Arbeit viel einfacher machen.

![Bootstrap Logo](/assets/library/html-css/part6/bootstrap-logo.png)

Das bekannteste dieser *Frameworks* heisst ***Bootstrap*** und wurde von Twitter entwickelt. Das *Bootstrap-Framework* ist eine enorme erleichtert für uns als Webentwickler. Wir werden Schritt-für-Schritt entdecken, was wir mit *Bootstrap* tun können. Hier aber schon mal eine Kostprobe, welche Auswirkungen *Bootstrap* auf unser Projekt haben kann:

![Bootstrap Effekt](/assets/library/html-css/part6/bootstrap-effect-de.png)

Mit *Bootstrap* sehen die Elemente nicht nur besser aus, sondern sie werden auch in allen Browsern praktisch gleich dargestellt.


## Bootstrap einsetzen

### Dokumentation

Die offizielle Dokumentation von [Bootstrap](http://getbootstrap.com/) ist auf Englisch. Es gibt aber auch eine [Bootstrap Übersetzung auf Deutsch](http://holdirbootstrap.de/). Sie sollten sich diese Webseiten irgendwo speichern, denn Sie werden dort immer wieder etwas nachschlagen müssen.


### Bootstrap-CSS einbinden

Der wichtigste Teil von Bootstrap besteht aus einem grossen CSS. Für etwas komplexere Elemente gibt es auch noch eine JavaScript-Datei. Wir werden aber für den Moment nur die CSS-Datei verwenden.

Nun wollen wir das Bootstrap-CSS in unsere HTML-Seiten einbinden:

1. Öffnen Sie die [Bootstrap-Webseite](http://holdirbootstrap.de/los-gehts/). Sie sehen dort, dass es mehrere Möglichkeiten gibt, Bootstrap herunterzuladen. Wir wählen die Variante über das *Bootstrap-CDN*. Damit müssen wir eigentlich gar nichts herunterladen, sondern nur im HTML angeben, wo die zusätzliche CSS-Datei liegt. Sie wird dann bei jedem Aufruf unserer Webseite direkt von den *CDN*-Servern heruntergeladen. *CDN*-Server sind Server, die über die ganze Welt verteilt sind, damit sie eine Datei sehr schnell ausliefern können. 

2. Kopieren Sie also vom Abschnitt *Bootstrap-CDN* die erste Zeile mit dem `link`-Element.

3. Fügen Sie das `link`-Element in ihre HTML-Seiten ein. Wichtig ist, dass Sie es oberhalb von unserem `link`-Element für die `main.css`-Datei einfügen. Dadurch hat unser `main.css` eine höhere Wichtigkeit. Das bedeutet, dass wir CSS-Regeln aus dem Bootstrap-CSS mit unseren eigenen Regeln überschreiben können.

So sollte Ihr `head`-Bereich nun etwa aussehen (evtl. mit einer neueren Versionsnummer von Bootstrap):


##### index.html

<pre class="prettyprint lang-html">
&lt;head>
  &lt;meta charset="utf-8">
  <mark>&lt;link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"></mark>
  &lt;link rel="stylesheet" href="main.css">
  &lt;title>Web Portfolio von Marco&lt;/title>
&lt;/head>
</pre>


### Wie funktioniert Bootstrap?

Manche *Styles* aus dem Bootstrap-CSS werden direkt auf HTML-Elemente angewandt. Schauen wir uns zum Beispiel einen Link in *Chrome DevTools* an, sehen wir, wie im Bootstrap-CSS die Link-Farbe geändert wird und mit `text-decoration` der Unterstrich entfernt wird:

![Bootstrap Link-Farbe](/assets/library/html-css/part6/bootstrap-link-color.png)

Viele Bootstrap-Styles werden aber erst aktiviert, wenn man eine entsprechende **CSS-Klasse** hinzufügt. 


### Bootstrap Klassen verwenden

Als Beispiel schauen wir uns an, wie mit Bootstrap die Textausrichtung geändert werden kann. In der Bootstrap-Dokumentation unter [Ausrichtungsklassen](http://holdirbootstrap.de/css/#type-alignment) finden Sie eine kurze Anleitung zur Textausrichtung. 

Wenn wir also zum Beispiel den `h2`-Titel zentrieren möchten, müssten wir die Klasse `text-center` hinzufügen:

<pre class="prettyprint lang-html">
&lt;h2 class="text-center">Willkommen!&lt;/h2>
</pre>

Unser `h1`-Titel hat bereits ein `class`-Attribut. Wenn wir auch diesen Titel zentrieren möchten, können wir das `text-center` als zweite Klasse hinzufügen. **Mehrere CSS-Klassen werden immer durch Leerschläge getrennt**:

<pre class="prettyprint lang-html">
&lt;h1 class="title text-center">Web Portfolio von Marco&lt;/h1>
</pre>

Was aber, wenn wir einen grösseren Teil unserer Webseite zentrieren möchten? Da wäre es recht mühsam, wenn wir bei jedem Element einzeln ein `text-center` hinzufügen müssten. Es ist deshalb möglich, dass man auf einem übergeordneten Element eine Klasse hinzufügt. Viele CSS-Regeln können so auf alle Unterelemente weitervererbt werden.

Wir könnten sogar auf dem `body`-Element eine `text-center`-Klasse hinzufügen. Damit wird gleich alles zentriert. Wenn man aber nur einen Teil zentrieren möchte, verwendet man oft sogenannte `div`-Elemente.


## div-Elemente

Mit dem `<div>`-Element werden Elemente zu einem Block gruppiert. Für Bootstrap werden wir `div`-Elemente recht oft verwenden, um Styles auf einen ganzen Abschnitt der Seite anzuwenden.

Eine zentrale CSS-Klasse in Bootstrap ist `container`. Der `container` passt den Inhalt automatisch an die Seitenbreite an und sorgt für einen sinnvollen Abstand zum Rand. Sie sollten auf Ihrer Bootstrap-Seite immer ein `div`-Element mit der `container`-Klasse haben. Lesen Sie die [Beschreibung von Container](http://holdirbootstrap.de/css/#overview-container) auf der Bootstrap-Webseite.

Wir packen nun den Hauptinhalt unserer Startseite zwischen ein `<div class="container">` und `</div>`. (Um die Navigation werden wir uns weiter unten noch speziell kümmern.)

Der `body`-Teil Ihrer Hauptseite sollte nun wie folgt aussehen (achten Sie auf sauberes Einrücken der Elemente, damit der Code übersichtlich bleibt):


##### index.html

<pre class="prettyprint lang-html">
&lt;body>
  &lt;ul>
    &lt;li>&lt;a href="./">Home&lt;/a>&lt;/li>
    &lt;li>&lt;a href="blog/">Blog&lt;/a>&lt;/li>
    &lt;li>&lt;a href="projekte/">Projekte&lt;/a>&lt;/li>
    &lt;li>&lt;a href="kontakt/">Kontakt&lt;/a>&lt;/li>
  &lt;/ul>

  <mark>&lt;div class="container"></mark>
    &lt;h1 class="title">Web Portfolio von Marco&lt;/h1>

    &lt;h2>Willkommen!&lt;/h2>

    &lt;p>Schön, dass Sie auf meiner Seite vorbeischauen.&lt;/p>

    &lt;p>Sie können sich ruhig etwas umsehen. Im Blog dokumentiere ich meine Erfahrungen beim Programmieren. Daneben können Sie auch meine Webprojekte anschauen. Viel Spass.&lt;/p>

    &lt;img src="marco.jpg" alt="Foto von mir">

    &lt;p>Marco :-)&lt;/p>
  <mark>&lt;/div></mark>
&lt;/body>
</pre>

Im Browser beginnen die `div`-Elemente **immer auf einer neuen Zeile**. Weil sie die ganze verfügbare Breite in Anspruch nehmen nennt man solche Elemente auch [Block-Elemente](https://developer.mozilla.org/de/docs/Web/HTML/Block-level_elemente).


## span-Elemente

Im Gegensatz zu `<div>`-Elementen beginnt ein `<span>`-Element nicht auf einer neuen Zeile. Ein `span`-Element ist nur so breit. Man nennt solche Elemente auch [Inline-Elemente](https://developer.mozilla.org/de/docs/Web/HTML/Inline_elemente), weil sie sich innerhalb einer Zeile einbetten.  

Hier ein Beispiel, wie man ein `span`-Element einsetzen könnte: 

<pre class="prettyprint lang-html">
Ich möchte &lt;span class="text-warning">diese Worte&lt;/span> besonders hervorheben. 
</pre>


## Navigation mit Bootstrap

Nun wollen wir endlich die Navigation schöner gestalten mit der Hilfe von Bootstrap. In der Bootstrap-Dokumentation wird eine [Navbar](http://holdirbootstrap.de/komponenten/#navbar) beschrieben. Das Beispiel dort ist jedoch sehr komplex und beinhaltet Menus, Textfelder und Knöpfe. Wir wählen eine etwas einfachere Variante.

Programmieren sie die folgenden Schritte. Am besten probieren Sie nach jedem Schritt aus, was die Auswirkungen sind.

1. Fügen Sie dem `ul`-Element Ihrer Navigation die zwei Klassen `nav` und `navbar-nav` hinzu:   
`<ul class="nav navbar-nav">`

2. Packen Sie die gesamte Navigation in ein `div` mit der Klasse `container`. Dies bewirkt, dass die Abstände vom Rand gleich sind wie bei unserem Hauptinhalt. Vergessen Sie nicht das schliessende Tag `</div>`.   
`<div class="container">`

3. Umgeben Sie das Ganze mit einem weiteren `div`. Diesmal brauchen wir die drei Klassen `navbar`, `navbar-default` und `navbar-static-top`. Diese Klassen bewirken, dass ein Balken oben am Rand dargestellt wird. Vergessen Sie auch hier nicht das schliessende Tag `</div>`.   
`<div class="navbar navbar-default navbar-static-top">`

4. Fügen Sie beim `li`-Elemente von *Home* eine Klasse `active` hinzu. Dies bewirkt, dass der *Home*-Link speziell hervorgehoben wird. Damit merkt der Besucher, auf welcher Seite er sich im Moment befindet.   
`<li class="active"><a href="./">Home</a></li>`

So sieht der Code für die Navigation nun aus (achten Sie auf korrektes Einrücken!):


##### index.html

<pre class="prettyprint lang-html">
<mark>&lt;div class="navbar navbar-default navbar-static-top"></mark>
  <mark>&lt;div class="container"></mark>
    &lt;ul <mark>class="nav navbar-nav"></mark>
      &lt;li <mark>class="active"</mark>>&lt;a href="./">Home&lt;/a>&lt;/li>
      &lt;li>&lt;a href="blog/">Blog&lt;/a>&lt;/li>
      &lt;li>&lt;a href="projekte/">Projekte&lt;/a>&lt;/li>
      &lt;li>&lt;a href="kontakt/">Kontakt&lt;/a>&lt;/li>
    &lt;/ul>
  <mark>&lt;/div></mark>
<mark>&lt;/div></mark>
</pre>

Und so wird es dargestellt:

![Navbar](/assets/library/html-css/part6/navbar-default-de.png)


### Dunkle Variante

Es gibt eine zweite Variante der Bootstrap-Navigation:

![Navbar Inverse](/assets/library/html-css/part6/navbar-inverse-de.png)

Um diese zu aktivieren, ändern Sie die Klasse `navbar-default` in `navbar-inverse` um.


## Optimieren für Mobile

Mit Bootstrap und der Navigation sieht die Webseite in einem Desktop-Browser schon recht ansprechend aus. Versuchen Sie mal, die Webseite auf einem Mobiltelefon aufzurufen (dazu müssen Sie die Seite [veröffentlichen](/library/html-css/de/part2/)).

Sie werden feststellen, dass die Seite entweder abgeschnitten oder die Navigation viel zu klein dargestellt wird:

![Mobile ohne Viewport](/assets/library/html-css/part6/mobile-without-viewport-de.png)

Mit einer `meta`-Information im `head`-Bereich können wir den mobilen Browsern mitteilen, dass sie die Seite vernünftig anzeigen sollen:

<pre class="prettyprint lang-html">
&lt;head>
  &lt;meta charset="utf-8">
  <mark>&lt;meta name="viewport" content="width=device-width, initial-scale=1"></mark>
  &lt;link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  &lt;link rel="stylesheet" href="main.css">
  &lt;title>Web Portfolio von Marco&lt;/title>
&lt;/head>
</pre>

Nun sollte es etwa so aussehen:

![Mobile mit Viewport](/assets/library/html-css/part6/mobile-with-viewport-de.png)

Bootstrap wurde so entworfen, dass es auf [mobilen Bilschirmen gut funktioniert](http://holdirbootstrap.de/css/#overview-mobile). Im Screenshot oben sehen Sie, wie die Navigation automatisch untereinander angezeigt wird, sobald die Bildschirmbreite zu klein wird. Dies können Sie auch ausprobieren, indem Sie einfach das Browserfenster verkleinern.

Ein Webdesign, welches auf die Bildschirmgrösse reagiert, nennt man [Responsive Webdesign](http://de.wikipedia.org/wiki/Responsive_Webdesign).

<div class="alert alert-warning">
  <strong>Hinweis:</strong> Bootstrap kann die Navigation auch automatisch zusammenklappen in ein Menu. Dafür müssen aber zusätzliche JavaScript-Dateien eingebunden werden. Im Artikel <a class="alert-link" href="/library/more-html-css/de/javascript-bootstrap/">JavaScript mit Bootstrap</a> erkläre ich, wie Sie eine solche Navigation programmieren können.  
</div>


## Bootstrap und Navigation auf allen Seiten

Bis jetzt haben wir Bootstrap und die Navigation nur auf unserer Startseite eingefügt. Das Ziel ist natürlich, dass diese auf allen Seiten eingebunden sind.

Mit kleinen Anpassung können wir den Code aus der Startseite in alle anderen Seiten (Blog, Blogeinträge, Kontakt, etc.) kopieren:


#### 1. Viewport und Bootstrap-CSS einfügen

Kopieren Sie aus dem `head`-Bereich der Startseite die Zeile mit dem `viewport` und die Zeile mit dem Bootstrap CSS und fügen Sie diese in alle anderen Seiten ein:
<pre class="prettyprint lang-html">
  &lt;meta name="viewport" content="width=device-width, initial-scale=1">
  &lt;link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
</pre>


#### 2. Navigation einfügen

Kopieren Sie den gesamten Navigationsbereich aus der Startseite und fügen Sie diese in alle anderen Seiten ein. Achten Sie darauf, dass Sie jeweils beim aktuellen `li`-Element die `active`-Klasse hinzufügen:   

<pre class="prettyprint lang-html">
&lt;div class="navbar navbar-default navbar-static-top">
  &lt;div class="container">
    &lt;ul class="nav navbar-nav">
      &lt;li>&lt;a <mark>href="../"</mark>>Home&lt;/a>&lt;/li>
      &lt;li <mark>class="active"</mark>>&lt;a <mark>href="../blog/"</mark>>Blog&lt;/a>&lt;/li>
      &lt;li>&lt;a <mark>href="../projekte/"</mark>>Projekte&lt;/a>&lt;/li>
      &lt;li>&lt;a <mark>href="../kontakt/"</mark>>Kontakt&lt;/a>&lt;/li>
    &lt;/ul>
  &lt;/div>
&lt;/div>
</pre>

<div class="alert alert-info">
<p>
**Achtung:** Links im Unterverzeichnis müssen mit `../` ergänzt werden, damit sie auf das übergeordnete Verzeichnis zeigen.
</p>
<p>
Da die Blogeinträge noch ein Unterverzeichnis tiefer liegen, müssen die Links dort `../../` enthalten, um auf das oberste Verzeichnis zu zeigen.
</p>
</div>


#### 3. Container-div einfügen

Wenn Sie die Unterseiten im Browser öffnen, werden Sie merken, dass die Seitenränder noch nicht korrekt sind. Um das zu korrigieren, müssen wir den gesamten Inhalt (alles unterhalb der Navigation) mit einem `div` umgeben. Dieses `div` muss die Klasse `container` haben. Korrigieren Sie das in allen Unterseiten: 

<pre class="prettyprint lang-html">
[... Navigation ...]

<mark>&lt;div class="container"></mark>
  [... Seiteninhalt ...]
<mark>&lt;/div></mark>
</pre>


#### 4. Testen

Nun ist es wichtig, dass Sie alle Seiten aufrufen und die **Links sorgfältig testen**!

Schauen Sie als nächstes Ihren Code an und stellen Sie sicher, dass das **HTML schön aufgeräumt** ist. Immer, wenn ein HTML-Tag geöffnet wird, dann sollte die nächste Zeile eingerückt werden. Das schliessende Tag wird dann wieder nach links gerückt.


## Wie weiter?

Erfahren Sie die [nächsten Schritte](/library/html-css/de/next/).

