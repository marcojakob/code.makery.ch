---
layout: article
title: "HTML & CSS Tutorial: Unsere erste Webseite"
date: 2014-07-22 00:00
slug: html-css/de/part1
github: false
published: true
prettify: true
comments: false
sidebars:
- header: Artikel in dieser Serie
  body:
  - text: "Unsere erste Webseite"
    link: /library/html-css/de/part1/
    paging: 1
    active: true
  - text: "Some Style"
    link: /library/html-css/de/some-style/
    paging: 2
- header: Downloads
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
---

Wir legen gleich los und erstellen unsere erste Webseite. 

<!-- TODO: Beschreibung des Portfolio-Projektes -->

## Unsere Tools

Für die Webentwicklung brauchen wir zwei Programme: einen <abbr data-toggle="tooltip" title="Normaler Texteditor oder einen Code-Editor">Editor</abbr>, um die Dateien für die Webseite zu erstellen und einen <abbr data-toggle="tooltip" title="z.B. Internet Explorer, Firefox, Safari oder Chrome">Browser</abbr>, um unsere Webseite anzuzeigen und zu testen.


### Editor

Zum Erstellen von Webseiten reicht eigentlich ein ganz normaler Texteditor. Es gibt aber Editoren, die uns die Arbeit beim Programmieren stark vereinfachen. Deshalb empfehle ich, dass Sie gleich mit einem modernen Code-Editor beginnen.

![Brackets Editor](/assets/library/html-css/part1/brackets-editor.png)

Mein aktueller Favorit unter den zahlreichen Editoren ist [Brackets](http://brackets.io/) (kostenlos von Adobe). Ich werde zwischendurch auf die Bedienung von Brackets eingehen, was nicht heisst, dass Sie nicht auch einen anderen Editor verwenden können. Gute Alternativen zu Brackets wären zum Beispiel [Atom](http://atom.io) oder das etwas ältere [Notepad++](http://notepad-plus-plus.org/).

Also, legen Sie los und installieren Sie einen Editor. Wenn Sie nicht wissen welchen, nehmen Sie vorläufig mal [Brackets](http://brackets.io).


### Browser

Unsere Webseite sollte natürlich in allen gängigen Browsern (Internet Explorer, Firefox, Chrome und Safari) laufen. Für die Webentwicklung ist jedoch unbedingt [Chrome](https://www.google.com/chrome/browser/) zu empfehlen. Chrome beinhaltet sehr nützliche Tools für Entwickler, die wir oft nutzen werden. Ausserdem wird Chrome optimal vom Brackets Editor unterstützt, so dass Änderungen im Editor sofort in Chrome sichtbar sind (live reload).

![Chrome Logo](/assets/library/html-css/part1/chrome-logo.png)

Falls Sie Chrome noch nicht haben, können Sie ihn [hier installieren](https://www.google.com/chrome/browser/).


## Ein HTML Dokument erstellen

Ausgerüstet mit Editor und Browser erstellen wir das erste HTML Dokument für unsere Webseite:

1. Erstellen Sie zuerst einen Ordner auf ihrem Computer für unser erstes Projekt. Nennen Sie den Ordner zum Beispiel *Portfolio*.

2. Öffnen Sie Brackets (oder Ihren Code-Editor). Wenn Sie Brackets zum ersten Mal starten, können Sie im Menu *Debug | Change Language* die Sprache auf Deutsch umstellen.

3. Öffnen Sie das Menu *Datei* und wählen Sie ***Ordner öffnen...***. Suchen Sie dort den vorhin erstellten *Portfolio* Ordner und öffnen Sie diesen.
<p>
![Ordner öffnen](/assets/library/html-css/part1/open-folder-de.png)
</p>

4. Klicken Sie unterhalb des Ordners *Portfolio* mit der *rechten Maustaste* und wählen Sie *Neue Datei*. Geben Sie der Datei den Namen `index.html`.
<p>
![Neue Datei](/assets/library/html-css/part1/new-file-de.png)
</p>

5. Nun haben wir eine ganz einfache, leere Textdatei namens `index.html`.
<p>
![index.html](/assets/library/html-css/part1/new-index-file-de.png)
</p>


### Warum index.html?

Wie Sie wahrscheinlich ahnen konnten hat der Name `index.html` eine spezielle Bedeutung. Wenn nämlich eine Webseitenadresse aufgerufen wird, z.B. `http://code.makery.ch`, dann wird automatisch zuerst die Datei `index.html` angezeigt, in diesem Fall `http://code.makery.ch/index.html`. Für uns wird `index.html` also quasi zur Startseite.


### Anzeigen und aktualisieren

Nun wollen wir das Dokument mit Inhalt füllen. Tippen Sie die folgenden Zeilen ab. Als Text können Sie natürlich irgend etwas wählen.

![HTML Struktur](/assets/library/html-css/part1/first-website-de.png)

Zum Anzeigen der Seite klicken Sie auf den *Blitz* am rechten Rand (Live Vorschau). Falls Brackets den Chrome Browser findet, dann wird jetzt die Seite angezeigt und sobald wir Änderungen vornehmen, werden diese gleich aktualisiert. 

Falls die Seite **nicht angezeigt wird**: Suchen Sie die `index.html`-Datei auf Ihrem Computer und öffnen Sie diese mit Chrome oder einem anderen Browser.

Falls die Seite **nicht aktualisiert wird** bei Änderungen: Klicken Sie im Browser die Taste `F5` oder `cmd+r`.

*Gratuliere! Sie haben soeben Ihre erste Webseite erstellt!*


## HTML-Elemente

### Tags

Im Beispiel oben haben Sie bereits die für HTML typischen Zeichen mit den spitzen Klammern gesehen. Man nennt diese **Tags**. 

**HTML-Elemente** bestehen meist (aber nicht immer) aus zwei Tags, einem öffnenden und einem schliessenden. In unserem Beispiel ist `<html>` ein öffnendes Tag und `</html>` mit dem Schrägstrich ein schliessendes Tag.

Das, was zwischen dem öffnenden und dem schliessenden Tag steht, ist der Inhalt des HTML-Elements. Mit `<html>` und `</html>` sagt wir also dem Browser, dass dazwischen das HTML unserer Webseite steht. 

Das zweite Tag, welches wir gesehen haben, ist das `<body>`-Tag. Es besagt, dass alles zwischen dem öffnenden `<body>`-Tag und dem schliessenden `</body>`-Tag im Hauptbereich des Browsers angezeigt werden soll.


### Attribute

Mit Attributen werden zusätzliche Informationen zu einem Element angegeben. Sie stehen im öffnenden Tag des Elements und beinhalten einen Namen und einen Wert. 

Als Beispiel schauen wir uns das HTML-Element für einen Link an. Es ist wahrscheinlich eines der wichtigsten Elemente &mdash; ja was wäre das Internet ohne Links?

![HTML Element](/assets/library/html-css/part1/html-element-attribute-de.png)

Das abgebildete `a`-Element enthält ein `href`-Attribut (kurz für "Hypertext Reference") mit dem Wert `http://code.makery.ch`. Der Browser weiss damit, dass er den folgenden Link anzeigen muss: [Meine Webseite](http://code.makery.ch)


## Grundgerüst einer HTML-Seite

Die Element `<html>` und `<body>` haben wir bereits kennen gelernt. Die Grundstruktur einer HTML-Seite beinhaltet aber üblicherweise noch ein paar weitere Elemente. Übernehmen Sie für Ihre Webseite den folgenden Code. Anschliessend werden wir ihn besprechen:


##### HTML Grundgerüst

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>
&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    &lt;title>Web Portfolio von Marco&lt;/title>
  &lt;/head>
  &lt;body>
    &lt;h1>Web Portfolio von Marco&lt;/h1>
    &lt;p>Hier kann irgend ein Bla Bla stehen.&lt;/p>
  &lt;/body>
&lt;/html>
</pre>


#### Erklärung

* Setzen Sie `<!DOCTYPE html>` immer an die erste Stelle. Es sagt dem Browser, welcher Dokumententyp verwendet wird.
* Das `<html>`-Tag bezeichnet den Start und `</html>` das Ende des Dokumentes.
* Das `<head>`-Element beinhaltet zusätzliche Informationen über die Seite. Im Gegensatz zum `<body>`-Element werden diese Informationen nicht im Hauptbereich des Browsers angezeigt.
  * Innerhalb vom `<head>` sollte eine Angabe stehen über den Zeichensatz: `<meta charset="utf-8">`. Wenn Sie den Zeichensatz nicht angeben, kann es vorkommen, dass zum Beispiel die Umlaute ä, ö und ü falsch angezeigt werden. Vielleicht haben Sie gemerkt, dass das `<meta>` Element kein schliessendes Tag besitzt. Es gibt ein paar <abbr data-toggle="tooltip" title="Wichtigste Elemente ohne schliessende Tags: <br>, <img>, <meta>, <hr>, <link>, <input>">Elemente ohne schliessende Tags</abbr>, sie sind aber die Ausnahme.
  * Weiter steht dort meist das Titelelement `<title>`. Der Titel wird in der Titelleiste oben im Browserfenster angezeigt.
* Alles innerhalb des `<body>`-Elements wird im Hauptbereich des Browsers angezeigt.
  * Ein `<h1>`-Element bezeichnet die Hauptüberschrift. Untergeordnete Überschriften können mit `<h2>`, `<h3>`, ... erstellt werden.
  * Alles, was zwischen `<p>` und `</p>` steht ist ein Textabsatz (auch **P**aragraph genannt).
* Nach jedem öffnenden Tag wird das nächste Element eingerückt (mit Tabulator oder zwei Leerschlägen) für eine bessere Übersicht. Dies sollten Sie sich unbedingt auch angewöhnen.

**Tipp:** *Halten Sie das HTML Grundgerüst stets griffbereit. Wir werden es für jede neue HTML-Seite verwenden!*

Mit diesen Grundlegenden HTML-Elementen sind wir nun bestens vorbereitet, um unsere Webseite auf ein neues Level zu bringen. Als erstes fügen wir noch ein Bild ein, damit die Startseite unseres Portfolios etwas interessanter wird.


## Ein Bild einfügen

Um ein Bild einzufügen verwenden wir das `<img>`-Element. Das folgende Beispiel würde ein Bild von mir einfügen:

<pre class="prettyprint lang-html">
&lt;img src="marco.jpg" alt="Foto von mir">
</pre>

Das `<img>`-Element hat nur ein öffnendes aber kein schliessendes Tag. Es enthält ein `src`- und ein `alt`-Attribut. Mit dem `src`-Attribut wird die <abbr data-toggle="tooltip" title="Uniform Resource Locator">URL</abbr>, d.h. der Ort und Name des Bildes angegeben. Das `alt`-Attribut ist ein "alternativer Text", der den Inhalt des Bildes beschreibt. Dieser Text wird von Suchmaschinen verwendet oder wenn aus irgend einem Grund das Bild nicht angezeigt werden kann, z.B. bei einem Screenreader für Blinde.


### Relative und absolute URLs

Für das `src`-Attribut von Bildern, aber auch für das `href`-Attribut von Links, werden URLs verwendet. Mit der URL wird also die "Adresse" einer anderen Datei (z.B. eine andere Webseite oder ein Bild) angegeben. Je nach dem, wo diese Datei liegt, muss entweder eine *relative* oder eine *absolute* URL verwendet werden. 

Wenn eine Datei **auf der gleichen Internetseite** liegt, dann wird eine ***relative URL*** verwendet. Diese besteht, wie wir es oben im Beispiel gesehen haben, nur aus dem Namen der Datei. 

Eine *relative URL* ist immer *relativ* zur aktuellen HTML-Seite. Wenn die Zieldatei in einem anderen Ordner liegt, muss dies berücksichtigt werden. Wenn das Bild aus dem Beispiel oben in einem Unterordner namens `bilder` liegt, dann würde die *relative URL* `bilder/marco.jpg` lauten. Falls die Datei in einem übergeordneten Ordner liegt, dann können wir mit `../` in diesen Ordner gelangen. Die URL wäre in diesem Fall `../marco.jpg`. 

Wenn die Datei **auf einer anderen Internetseite** liegt, dann wird eine ***absolute URL*** verwendet. Dabei wird der ganze Domainname angegeben. Ein Beispiel wäre `http://code.makery.ch/bilder/marco.jpg`.
 

##### Beispiele für relative und absolute URLs

<pre class="prettyprint lang-html">
&lt;!-- Relative URLs -->
&lt;a href="bildergalerie.html">Bildergalerie&lt;/a>
&lt;a href="blog/mein-erster-eintrag.html">Mein Erster Blogeintrag&lt;/a>
&lt;a href="../bildergalerie.html">Zurück zur Bildergalerie&lt;/a>

&lt;!-- Relative URL -->
&lt;a href="http://www.mein-super-kollege.ch/blog.html">Blog eines Kollegen&lt;/a>
</pre>


### Das Portfolio mit Bild

Wenn Sie es noch nicht gemacht haben, versuchen Sie nun ein Bild in Ihr Portfolio einzufügen. Dazu müssen Sie ein Bild in den *Portfolio*-Ordner auf Ihrem Computer kopieren. Achten Sie darauf, dass Sie den exakten Dateinamen inklusive der Dateiendung angeben.

Der gesamte Code könnte nun etwa so aussehen (ich habe noch eine Unterüberschrift und etwas mehr Text eingefügt):

##### index.html mit fertigem Portfolio-Code

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>
&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    &lt;title>Web Portfolio von Marco&lt;/title>
  &lt;/head>
  &lt;body>
    &lt;h1>Web Portfolio von Marco&lt;/h1>
    
    &lt;h2>Willkommen!&lt;/h2>
    
    &lt;p>Schön, dass Sie auf meiner Seite vorbeischauen.&lt;/p>
    
    &lt;p>Sie können sich ruhig etwas umsehen. Im Blog dokumentiere ich meine Erfahrungen beim Programmieren. Daneben können Sie auch meine Webprojekte anschauen. Viel Spass.&lt;/p>
    
    &lt;img src="marco.jpg" alt="Foto von mir">

    &lt;p>Marco :-)&lt;/p>
  &lt;/body>
&lt;/html>
</pre>

So sieht im Moment mein Portfolio im Browser aus:

![Portfolio](/assets/library/html-css/part1/portfolio-de.png)

***

Als nächster Schritt...



<!-- TODO: Bschreibung wie weiter. -->






