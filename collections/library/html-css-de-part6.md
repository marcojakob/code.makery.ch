---
layout: article
title: "HTML & CSS Tutorial: Bootstrap Framework verwenden"
date: 2014-07-22 00:00
slug: html-css/de/part6
github: false
published: true
prettify: true
comments: false
sidebars:
- header: Teile dieser Serie
  body:
  - text: "Unsere erste Webseite"
    link: /library/html-css/de/first-web-page/
    paging: 1
- header: Downloads
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
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

1. Öffnen Sie die [Bootstrap Webseite](http://holdirbootstrap.de/los-gehts/). Sie sehen dort, dass es mehrere Möglichkeiten gibt, Bootstrap herunterzuladen. Wir wählen die Variante über das *Bootstrap-CDN*. Damit müssen wir eigentlich gar nichts herunterladen, sondern nur im HTML angeben, wo die zusätzliche CSS-Datei liegt. Sie wird dann bei jedem Aufruf unserer Webseite direkt von den *CDN*-Servern heruntergeladen. *CDN*-Server sind Server, die über die ganze Welt verteilt sind, damit sie eine Datei sehr schnell ausliefern können. 

2. Kopieren Sie also vom Abschnitt *Bootstrap-CDN* die erste Zeile mit dem `link`-Element.

3. Fügen Sie das `link`-Element in ihre HTML-Seiten ein. Wichtig ist, dass Sie es oberhalb von unserem `link`-Element für die `main.css`-Datei einfügen. Dadurch hat unser `main.css` eine höhere Wichtigkeit. Das bedeutet, dass wir CSS-Regeln aus dem Bootstrap-CSS mit unseren eigenen Regeln überschreiben können. 

So sollte Ihr `head`-Bereich nun etwa aussehen (evtl. mit einer neueren Versionsnummer von Bootstrap):


##### index.html

<pre class="prettyprint lang-html">
&lt;head>
  &lt;meta charset="utf-8">
  &lt;link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  &lt;link rel="stylesheet" href="main.css">
  &lt;title>Web Portfolio von Marco&lt;/title>
&lt;/head>
</pre>

**Wichtig:** *Fügen Sie, wie im Code oben, beim Link zum Bootstrap-CSS ein `http:` vorne dran. Ansonsten gibt es Probleme, wenn man die Webseite auf dem eigenen Computer aufrufen möchte ([hier](http://www.paulirish.com/2010/the-protocol-relative-url/) können Sie lesen weshalb).*


### Wie funktioniert Bootstrap?

Manche *Styles* aus dem Bootstrap-CSS werden direkt auf HTML-Elemente angewandt. Schauen wir uns zum Beispiel einen Link in *Chrome DevTools* an, sehen wir, wie im Bootstrap-CSS die Link-Farbe geändert und mit `text-decoration` der Unterstrich entfernt wird:

![Bootstrap Link-Farbe](/assets/library/html-css/part6/bootstrap-link-color.png)

Viele Bootstrap-Styles werden aber erst aktiviert, wenn man eine entsprechende **CSS-Klasse** hinzufügt. 


#### Bootstrap Klassen verwenden

Als Beispiel schauen wir uns an, wie mit Bootstrap die Textausrichtung geändert werden kann. In der Bootstrap-Dokumentation unter [Ausrichtungsklassen](http://holdirbootstrap.de/css/#type-alignment) finden Sie eine kurze Anleitung zur Textausrichtung. 

Wenn wir also zum Beispiel den `h2`-Titel zentrieren möchten, müssten wir die Klasse `text-center` hinzufügen:

<pre class="prettyprint lang-html">
&lt;h2 class="text-center">Willkommen!&lt;/h2>
</pre>

Unser `h1`-Titel hat bereits ein `class`-Attribut. Wenn wir auch diesen Titel zentrieren möchten, dann können wir das `text-center` als zweite Klasse hinzufügen. **Mehrere CSS-Klassen werden immer durch Leerschläge getrennt**:

<pre class="prettyprint lang-html">
&lt;h1 class="title text-center">Web Portfolio von Marco&lt;/h1>
</pre>

Was aber, wenn wir einen grösseren Teil unserer Webseite zentrieren möchten? Da wäre es recht mühsam, wenn wir bei jedem Element einzeln ein `text-center` hinzufügen müssten. Es ist deshalb möglich, dass man auf einem übergeordneten Element eine Klasse hinzufügt. Viele CSS-Regeln können so auf alle Unterelemente weitervererbt werden.

Wir könnten also jetzt gleich auf dem `body`-Element eine `text-center`-Klasse hinzufügen. Damit wird gleich alles zentriert. Wenn man aber nur einen Teil zentrieren möchte, verwendet man oft sogenannte `div`-Elemente.


## div-Elemente





### Was passiert?

Wenn Sie *Bootstrap* wie oben gezeigt eingefügt haben, sollten Sie in Ihrem Portfolio sofort eine Veränderung sehen. Die offensichtlichste Änderung im Moment ist wohl die Schriftart. 

Das ist noch nicht viel, aber mit dem *Bootstrap*-CSS stehen uns enorme Möglichkeiten offen, um schön und einheitlich gestaltete Elemente in unsere Webseite einzufügen. Sie können sich ruhig die verschiedenen Elemente auf der [Bootstrap-Webseite](http://holdirbootstrap.de/los-gehts/) anschauen.






***

in andere einfügen `blog.index.html`


TODO:

* DIVs
* Reference to CSS File
* ID, Class, Element Selectors
* Basic Styling
* Include Bootstrap