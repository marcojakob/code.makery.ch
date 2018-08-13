+++
title = "Teil 6: Navigation"
date = 2014-08-13
updated = 2015-03-18
description = "Lernen Sie, wie man eine Navigation für die Website erstellt."
image = "portfolio.de.png"
prettify = true
comments = true
aliases = [
  "/library/html-css/de/part6/"
]
weight = 6
+++

In diesem Teil werden wir eine Navigation einfügen, damit die Besucher zwischen den verschiedenen Seiten wechseln können.

Eine Navigation besteht eigentlich aus ganz normalen Links zu anderen Seiten. Wir beginnen mit einer ganz einfachen Variante und werden diese dann laufend verbessern, bis wir eine schöne und komplette Navigation auf allen Seiten haben.

Zuerst werden wir die Navigation nur für unsere Startseite programmieren. Erst am Schluss werden wir diese dann in alle anderen Seiten hinein kopieren.


## Navigation mit Links

Öffnen Sie `index.html` von der Startseite.

Mit einfachen Links können wir von der Startseite auf die anderen Seiten springen. Die Navigation kommt im `body`-Teil noch vor dem Titel. Fügen Sie also folgende vier Links zwischen `body`-Tag und dem Titel ein:


##### index.html

<pre class="prettyprint lang-html">
&lt;body>
  <mark>&lt;a href="/">Home&lt;/a></mark>
  <mark>&lt;a href="/blog/">Blog&lt;/a></mark>
  <mark>&lt;a href="/projekte/">Projekte&lt;/a></mark>
  <mark>&lt;a href="/kontakt/">Kontakt&lt;/a></mark>

  &lt;h1 class="title">Web Portfolio von Marco&lt;/h1>
</pre>

<div class="alert alert-info">
**Hinweis:** Der Link zu `/` verweist auf das Hauptverzeichnis. Mehr dazu im Abschnitt <a class="alert-link" href="/de/library/html-css/part1/#relative-und-absolute-urls">relative und absolute URLs</a>.
</div>

Und so sieht die Navigation nun aus:

![Navigation mit Links](navigation-links.de.png)

Überprüfen Sie, ob die Links auch wirklich funktionieren und Sie zur gewollten Seite bringen.

<div class="alert alert-info">
  <p>**Hinweis:** Wenn Sie die Seite als Datei direkt im Browser öffnen (ohne die *Live-Vorschau* vom Brackets-Editor), dann werden die Links nicht ganz korrekt funktionieren. Der Grund ist, dass das Dateisystem einfach den Ordnerinhalt anzeigt und nicht automatisch `index.html` öffnet. Sie müssen dann manuell auf `index.html` klicken.</p>
  <p>Sobald Sie aber die Seite auf einem Server veröffentlichen, sollten die Links ganz normal funktionieren.</p>
</div>

Im Moment ist unsere Navigation einfach eine Aneinanderreihung von Links. Meistens verwendet man für eine Navigation aber eine HTML-Liste von Links. Mit einer Liste werden die Navigationselemente entweder horizontal oder vertikal zusammen gruppiert und können so später besser formatiert werden.

Wir lernen nun, wie man in HTML Listen erstellen kann.


## Listen

Es gibt viele Situationen, in denen man Listen verwendet. In HTML gibt es drei verschiedene Arten von Listen: **ungeordnete Listen**, **geordnete Listen** und **Definitionslisten**. Definitionslisten werden nicht so häufig verwendet. Deshalb werden wir uns hier nur die ersten beiden genauer anschauen.


### Ungeordnete Listen

Eine ungeordnete Liste ist ganz einfach eine Liste von Einträgen, bei welchen die Reihenfolge beliebig ist. Ungeordnete Listen werden in HTML mit dem Element `<ul>` erstellt (steht für *unordered list*). Für jeden Eintrag in der Liste braucht es innerhalb des `<ul>`-Elements ein `<li>`-Element (steht für *list item*).

Hier ein Beispiel einer Todo-Liste:

<pre class="prettyprint lang-html">
&lt;ul>
  &lt;li>Einkaufen&lt;/li>
  &lt;li>Katze füttern&lt;/li>
  &lt;li>Grossmutter besuchen&lt;/li>
&lt;/ul>
</pre>

Das Resultat sieht dann so aus:

<div class="panel panel-default">
  <div class="panel-body">
      <ul style="margin-bottom: 0">
        <li>Einkaufen</li>
        <li>Katze füttern</li>
        <li>Grossmutter besuchen</li>
      </ul>
  </div>
</div>


### Geordnete Listen

Bei geordneten Listen ist die Reihenfolge entscheidend und wird deshalb nummeriert. In HTML sieht sie sehr ähnlich aus wie die ungeordnete Liste: Anstelle des `<ul>`-Elementes wird ein `<ol>`-Element verwendet (steht für *ordered list*).

Ein typisches Beispiel für eine geordnete Liste ist die Zubereitung in einem Rezept:

<pre class="prettyprint lang-html">
&lt;ol>
  &lt;li>Eier aufschlagen, salzen und mit einer Gabel leicht verschlagen.&lt;/li>
  &lt;li>Milch darunter mischen.&lt;/li>
  &lt;li>Butter in einer Pfanne schmelzen und die Eimischung hineingeben.&lt;/li>
  &lt;li>Wenn es cremig ist, vom Herd nehmen und mit Schnittlauch bestreuen.&lt;/li>
&lt;/ol>
</pre>

Das Resultat sieht so aus:

<div class="panel panel-default">
  <div class="panel-body">
    <ol style="margin-bottom: 0">
      <li>Eier aufschlagen, salzen und mit einer Gabel leicht verschlagen.</li>
      <li>Milch darunter mischen.</li>
      <li>Butter in einer Pfanne schmelzen und die Eimischung hineingeben.</li>
      <li>Wenn es cremig ist, vom Herd nehmen und mit Schnittlauch bestreuen.</li>
    </ol>
  </div>
</div>


### Navigation als Liste

Wir werden die Navigation von unserem Portfolio nun als ungeordnete Liste programmieren.

Die Links unserer Navigation werden also alle verpackt in `<li>`-Elemente, welche innerhalb eines `<ul>`-Elements liegen:

<pre class="prettyprint lang-html">
&lt;ul>
  &lt;li>&lt;a href="/">Home&lt;/a>&lt;/li>
  &lt;li>&lt;a href="/blog/">Blog&lt;/a>&lt;/li>
  &lt;li>&lt;a href="/projekte/">Projekte&lt;/a>&lt;/li>
  &lt;li>&lt;a href="/kontakt/">Kontakt&lt;/a>&lt;/li>
&lt;/ul>
</pre>

Im nächsten Teil sehen wir, wie die Navigation horizontal dargestellt und schön formatiert werden kann. Sobald die Navigation fertig gestellt ist, werden wir sie auch in allen anderen Seiten einfügen.


### Blogeinträge als Liste

Auf unserer Blog-Seite hatten wir noch etwas Platz gelassen, wo wir unserer Blogeinträge auflisten können. Da wir nun wissen, wie Listen in HTML erstellt werden, können wir die Blogeinträge angeben.

Öffnen Sie die Blog-Seite `blog/index.html`. Fügen Sie unterhalb der Überschrift "Blogeinträge" eine Liste mit Blogeinträgen ein:

##### blog/index.html

<pre class="prettyprint lang-html">
&lt;h2>Blogeinträge&lt;/h2>

&lt;!-- Hier kommt eine Liste mit allen Blogeinträgen. -->
&lt;ul>
  &lt;li>Weitere Einträge folgen...&lt;/li>
  &lt;li>&lt;a href="01-erster-eintrag/">Erster Eintrag&lt;/a>&lt;/li>
&lt;/ul>
</pre>

<div class="alert alert-info">
<strong>Hinweis:</strong> </em>Blogeinträge werden meist in umgekehrter Reihenfolge aufgelistet, so dass der letzte Eintrag zuoberst in der Liste erscheint.</em>
</div>


## Wie weiter?

&rarr; Fahren Sie weiter mit [Teil 7: Bootstrap Framework verwenden](/de/library/html-css/part7/).
