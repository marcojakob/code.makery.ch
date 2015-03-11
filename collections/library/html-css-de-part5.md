---
layout: article
title: "HTML & CSS Tutorial - Teil 5: Navigieren zwischen Seiten"
date: 2014-08-13 00:00
slug: html-css/de/part5
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/html-css-de-part5.md
description: "Lernen Sie, wie man eine Navigation für die Website erstellt."
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
    active: true
  - text: "Teil 6: Bootstrap Framework verwenden"
    link: /library/html-css/de/part6/
    paging: 6
  - text: "Teil 7: Wie weiter?"
    link: /library/html-css/de/part7/
    paging: 7
- header: Downloads
  body:
  - text: Portfolio Teil 5
    link: https://github.com/marcojakob/tutorial-html-css/releases/download/v0.4/portfolio-de-part5.zip
    icon-css: fa fa-fw fa-file-archive-o
---

Unser Portfolio hat bis hierhin nur eine Startseite. Die meisten *Websites* (gemeint ist damit der ganze Webauftritt) haben natürlich mehr als nur eine Seite. In diesem Teil werden wir weitere Seiten erstellen und eine Navigation einfügen, damit die Besucher zwischen den verschiedenen Seiten wechseln können.


## Neue Seiten erstellen

Wir erstellen drei neue Seiten: Eine Seite für unseren eigenen **Blog**, eine für **Projekte** und eine für **Kontaktinformationen**. 

Diese drei Seiten stellen unsere "Hauptseiten" dar. Das heisst, dass wir daran denken müssen, dass jeweils weitere Unterseiten dazukommen könnten. So wird es zum Beispiel im Blog später für jeden Blogeintrag eine eigenen Unterseite geben. Aus diesem Grund werden wir für diese drei Seiten **je einen eigenen Unterordner** erstellen. Das gibt uns eine gute Ordnung. 

Damit bei einem Aufruf des Unterordners die jeweilige Hauptseite automatisch als erstes angezeigt wird, nennen wir alle Hauptseiten `index.html`.

Eine neue Seite zu erstellen ist einfach. Am besten kopieren Sie die bisherige `index.html`, damit Sie bereits die Grundstruktur haben. Dann müssen wir natürlich jeweils ein paar Anpassungen vornehmen.

<div class="alert alert-info">
**Wichtig:** Achten Sie darauf, dass Sie beim Benennen von Unterordnern und Dateinamen keine Umlaute, Sonderzeichen oder Leerschläge verwenden. Meistens wählt man auch nur Kleinbuchstaben. Sie können Wörter aber zum Beispiel durch ein Minus (`-`) trennen, wenn Sie möchten.
</div>


### Seite für den Blog

Erstellen Sie einen Unterordner in Ihrem *Portfolio*-Ordner namens `blog`. Kopieren Sie die Datei `index.html` in diesen Unterordner. Nun sollte Ihre Dateistruktur so aussehen:

![Blog Unterordner](/assets/library/html-css/part5/blog-subfolder.png)

Öffnen Sie nun die kopierte Datei `blog/index.html` in Ihrem Browser (wenn Sie den Brackets-Editor verwenden, klicken Sie auf den Blitz für die *Live-Vorschau*). Nun werden Sie merken, dass zwei Dinge nicht funktionieren:

1. Das Bild wird nicht angezeigt.
2. Die im CSS definierten Farben werden nicht übernommen.

Da wir uns in einem Unterordner befinden, stimmt jetzt die URL (oder auch Pfad genannt) nicht mehr. Damit das Bild angezeigt würde, müssten wir für das `src`-Attribut anstatt `marco.jpg` nun `../marco.jpg` angeben. Aber wahrscheinlich wollen wir nicht schon wieder das gleiche Bild auf der Blogseite. Also können Sie das gesamte `img`-Element entfernen.

Beim **CSS** ist es üblich, dass man die Regeln jeweils **für das gesamte Projekt** definiert. So ist es wichtig, dass wir die gleiche CSS-Datei auch in der Blogseite verwenden können. Dies erreichen wir, indem wir die URL ändern von `main.css` auf `../main.css`. So sieht jetzt das gesamte `link`-Element aus:

##### blog/index.html

<pre class="prettyprint lang-html">
&lt;link rel="stylesheet" href="../main.css">
</pre>

Diese Änderung sollte dazu führen, dass das Styling vom CSS auch für die Blogseite übernommen wird.

Jetzt passen wir noch den Titel und Inhalt an:

##### blog/index.html - Blog Hauptübersicht

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>

&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    &lt;link rel="stylesheet" href="../main.css">
    &lt;title>Blog - Web Portfolio von Marco&lt;/title>
  &lt;/head>
  &lt;body>
    &lt;h1 class="title">Blog&lt;/h1>
    
    &lt;p>Hier schreibe ich über alles, was mir beim Lernen von Webprogrammierung begegnet.&lt;/p>

    &lt;h2>Blogeinträge&lt;/h2>
    
    &lt;!-- Hier kommt eine Liste mit allen Blogeinträgen. -->
    
  &lt;/body>
&lt;/html>
</pre>

Im HTML-Code oben sehen Sie ein neues Element mit den Tags `<!--` und `-->`. Mit diesen Tags können wir in HTML einen **Kommentar** hinschreiben. Kommentare sind nur für uns als Programmierer gedacht und werden vom Browser ignoriert.


#### Blogeintrag als Unterseite

Der Blog braucht natürlich noch Einträge. Wir erstellen für jeden Blogeintrag eine eigen HTML-Unterseite. Erstellen Sie einen Unterordner innerhalb des `blog`-Ordners mit dem Namen `2014-08-02-erster-eintrag`. Kopieren Sie die Datei `blog/index.html` in diesen Ordner hinein.

![Blogeintrag](/assets/library/html-css/part5/blog-entry-subfolder-de.png)

Die Zahlenkombination `2014-08-02` ist das Datum in umgekehrter Reihenfolge, also der 2. August 2014. Man könnte natürlich irgend einen Namen vergeben, aber wenn man das Datum so in den Dateinamen integriert, dann hat man automatisch eine chronologische Sortierung der Dateien oder Ordner. Dies gibt uns eine bessere Übersicht, wenn wir später viele Blogeinträge haben.

Im Blogeintrag ändern wir den Inhalt wie folgt ab:

##### blog/2014-08-02-erster-eintrag/index.html - Erster Blogeintrag

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>

&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    &lt;link rel="stylesheet" href="../../main.css">
    &lt;title>Erster Eintrag - Web Portfolio von Marco&lt;/title>
  &lt;/head>
  &lt;body>
    &lt;h1 class="title">Erster Eintrag&lt;/h1>
    &lt;p>02. August 2014&lt;/p>
    
    &lt;hr>

    &lt;p>Dies ist mein erster Eintrag im Blog.&lt;/p>
    
  &lt;/body>
&lt;/html>
</pre>

Auch hier ist ein neues HTML-Element enthalten (`hr`). Sie werden mit ausprobieren oder einer Internetsuche schnell herausfinden, was es bewirkt.


#### Legen Sie los mit Blogeinträgen!

Ich empfehle Ihnen, dass Sie sofort damit anfangen, Blogeinträge zu schreiben. Sie könnten an jedem Tag, an dem Sie etwas Neues über Programmieren gelernt haben, einen kurzen Eintrag dazu schreiben. Ich notiere hier ein paar Gedanken, die Sie zu mögliche Inhalten inspirieren könnten:

* Was habe ich heute gelernt?
  * Screenshots einfügen
  * Links zu hilfreichen Seiten
* Welche Probleme sind mir begegnet?
* Wie habe ich diese Probleme gelöst?
* Woran sollte ich das nächste Mal arbeiten?

Mit solchen Blogeinträgen werden Sie beim Lernen von Programmierung viel schneller vorwärts kommen:

1. Sie werden sich bewusst, was Sie gelernt haben &rarr; mehr Motivation!
2. Sie können später in ihrer persönlichen Dokumentation etwas nachschlagen, z.B. Links zu hilfreichen Seiten.
3. Sie üben beim Schreiben der Einträge gleich die gelernten HTML-Elemente und CSS-Regeln.
4. Wenn wir später anschauen, wie man Webseiten veröffentlicht, dann können Sie Ihren Blog auch anderen zugänglich machen. So können Sie Ihr Wissen mit anderen Teilen oder jemanden einladen, Ihnen bei einem Problem zu helfen.
5. Falls Sie Ihr Portfolio veröffentlichen, würde mich das sehr interessieren. Schreiben Sie dann irgendwo einen Kommentar mit dem Link!

*Also, warten Sie nicht und legen Sie los. Es lohnt sich!*

<div class="alert alert-info">
**Hinweis:** Erstellen Sie für jeden Blogeintrag einen eigenen Unterordner (mit einer `index.html`-Seite). So haben Sie eine gute Ordnung und können Bilder oder andere Dateien für den Blogeintrag im Unterordner ablegen.
</div>

##### Zweiter Blogeintrag

![Blogeintrag 2](/assets/library/html-css/part5/blog-entry-subfolder2-de.png)


### Seite für Projekte

Wenn wir später verschiedene Webapplikationen programmieren, wäre es natürlich nützlich, dafür eine separate Seite zu haben. Wir bereiten schon mal eine Seite für unsere Projekte vor, jedoch noch ohne Inhalt.

Gehen Sie wie oben beim Blog vor und erstellen Sie einen Unterordner `projekte` mit einer Datei `index.html`:


##### projekte/index.html

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>

&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    &lt;link rel="stylesheet" href="../main.css">
    &lt;title>Projekte - Web Portfolio von Marco&lt;/title>
  &lt;/head>
  &lt;body>
    &lt;h1 class="title">Projekte&lt;/h1>
    
    &lt;p>Hier finden Sie später meine Webprojekte.&lt;/p>

  &lt;/body>
&lt;/html>
</pre>


### Seite für Kontakt

Als letzte Hauptseite fügen wir eine Seite mit Kontaktinformationen hinzu. Erstellen Sie einen Unterordner `kontakt` mit einer Datei `index.html`.

<div class="alert alert-danger">
**Wichtig:** *Überlegen Sie sich gut, welche Informationen Sie öffentlich machen wollen! Geben Sie zum Beispiel **nicht Ihre Haupt-E-Mail-Adresse an**, denn es kann sein, dass Sie nachher auf diese Adresse Spamnachrichten erhalten.*
</div>

##### kontakt/index.html

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>

&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    &lt;link rel="stylesheet" href="../main.css">
    &lt;title>Kontakt - Web Portfolio von Marco&lt;/title>
  &lt;/head>
  &lt;body>
    &lt;h1 class="title">Kontakt&lt;/h1>
    
    &lt;p>
      Sie können mich kontaktieren per Mail: &lt;a href="mailto:spammails@gmx.ch">spammails@gmx.ch&lt;/a>
    &lt;/p>
    
    &lt;p>
      Marco Jakob&lt;br>
      Schweiz
    &lt;/p>
  &lt;/body>
&lt;/html>
</pre>

Bei der Adresse sehen Sie ein neues Element: `<br>`. Es bewirkt einen Zeilenumbruch. Beachten Sie auch den speziellen Link mit `mailto:` und der E-Mail-Adresse. Dieser Link bewirkt, dass das E-Mail-Programm geöffnet wird und gleich die Adresse ausgefüllt ist.

Nun haben wir bereits fünf HTML-Seiten:

![Alle Dateien](/assets/library/html-css/part5/all-files-de.png)

Was natürlich fehlt, ist eine Navigation, so dass man einfach zwischen diesen Seiten hin und her wechseln kann. Das nehmen wir gleich als nächstes in Angriff.


## Navigation

Eine Navigation besteht eigentlich aus ganz normalen Links zu anderen Seiten. Wir beginnen mit einer ganz einfachen Variante und werden diese dann laufend verbessern, bis wir eine schöne und komplette Navigation auf allen Seiten haben.

Zuerst werden wir die Navigation nur für unsere Startseite programmieren. Erst am Schluss werden wir diese dann in alle anderen Seiten hinein kopieren.


### Navigation mit Links

Öffnen Sie `index.html` von der Startseite. 

Mit einfachen Links können wir von der Startseite auf die anderen Seiten springen. Die Navigation kommt im `body`-Teil noch vor dem Titel. Fügen Sie also folgende vier Links zwischen `body`-Tag und dem Titel ein:

##### index.html

<pre class="prettyprint lang-html">
&lt;body>
  <mark>&lt;a href="./">Home&lt;/a></mark>
  <mark>&lt;a href="blog/">Blog&lt;/a></mark>
  <mark>&lt;a href="projekte/">Projekte&lt;/a></mark>
  <mark>&lt;a href="kontakt/">Kontakt&lt;/a></mark>

  &lt;h1 class="title">Web Portfolio von Marco&lt;/h1>
</pre>

<div class="alert alert-info">
**Hinweis:** Der Link zu `./` verweist auf das aktuelle Verzeichnis. Mehr dazu im Abschnitt <a class="alert-link" href="/library/html-css/de/part1/#relative-und-absolute-urls">relative und absolute URLs</a>.
</div>

Und so sieht die Navigation nun aus:

![Navigation mit Links](/assets/library/html-css/part5/navigation-links-de.png)

Überprüfen Sie, ob die Links auch wirklich funktionieren und Sie zur gewollten Seite bringen.

<div class="alert alert-info">
<p>
**Hinweis:** Wenn Sie die Seite als Datei direkt im Browser öffnen (ohne die *Live-Vorschau* vom Brackets-Editor), dann werden die Links nicht ganz korrekt funktionieren. Der Grund ist, dass das Dateisystem einfach den Ordnerinhalt anzeigt und nicht automatisch `index.html` öffnet. Sie müssen dann manuell auf `index.html` klicken. 
</p>
<p>
Sobald Sie aber die Seite auf einem Server veröffentlichen, sollten die Links ganz normal funktionieren.
</p>
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

Bei geordneten ist die Reihenfolge entscheidend und wird deshalb nummeriert. In HTML sieht sie sehr ähnlich aus wie die ungeordnete Liste: Anstelle des `<ul>`-Elementes wird ein `<ol>`-Element verwendet (steht für *ordered list*).

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

Die Links unserer Navigation werden also alle verpackt in `<li>`-Elemente, welche innerhalb eines `<ol>`-Elements liegen:

<pre class="prettyprint lang-html">
&lt;ul>
  &lt;li>&lt;a href="./">Home&lt;/a>&lt;/li>
  &lt;li>&lt;a href="blog/">Blog&lt;/a>&lt;/li>
  &lt;li>&lt;a href="projekte/">Projekte&lt;/a>&lt;/li>
  &lt;li>&lt;a href="kontakt/">Kontakt&lt;/a>&lt;/li>
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
  &lt;li>&lt;a href="2014-08-02-erster-eintrag/">Erster Eintrag&lt;/a>&lt;/li>
&lt;/ul>
</pre>

<div class="alert alert-info">
**Hinweis:** *Blogeinträge werden meist in umgekehrter Reihenfolge aufgelistet, so dass der letzte Eintrag zuoberst in der Liste erscheint.*
</div>


***

&rarr; Fahren Sie weiter mit [Teil 6: Bootstrap Framework verwenden](/library/html-css/de/part6/).
