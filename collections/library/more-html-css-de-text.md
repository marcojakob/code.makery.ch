---
layout: article
title: "Mehr HTML & CSS: Textformatierung"
date: 2014-08-25 00:00
slug: more-html-css/de/text
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/more-html-css-de-text.md
published: true
prettify: true
comments: true
sidebars:
- header: Artikel dieser Serie
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
  - text: "Textformatierung"
    link: /library/more-html-css/de/text/
    paging: 4
    active: true
---

In diesem Teile lernen Sie Möglichkeiten kennen, wie Sie mit HTML und CSS Texte gestalten können.

Wenn Sie [Bootstrap 3](http://holdirbootstrap.de/) verwenden, dann wird die Textformatierung viel einfacher. Bootstrap kümmert sich darum, dass die Texte und Textabstände typografisch korrekt und einheitlich angezeigt werden.

<div class="alert alert-warning">
Falls Sie Bootstrap in Ihrem Projekt noch nicht integriert haben, lesen Sie zuerst den Teil über <a href="/library/html-css/de/part6/" class="alert-link">Bootstrap Framework verwenden</a> aus dem HTML & CSS Tutorial.
</div>

Das Meiste wird bereits unter [Typografie](http://holdirbootstrap.de/css/#type) in der Bootstrap Dokumentation erklärt. Deshalb werde ich nur auf die wichtigsten Textformatierungen eingehen. Schliesslich zeige ich auf, wie Sie die Standardschrift von Bootstrap verändern können.


## Überschriften

Überschriften werden mit den HTML-Elementen `<h1>` bis `<h6>` bezeichnet:

<pre class="prettyprint lang-html">
<mark>&lt;h1></mark>Überschrift 1<mark>&lt;/h1></mark>
<mark>&lt;h2></mark>Überschrift 2<mark>&lt;/h2></mark>
<mark>&lt;h3></mark>Überschrift 3<mark>&lt;/h3></mark>
<mark>&lt;h4></mark>Überschrift 4<mark>&lt;/h4></mark>
<mark>&lt;h5></mark>Überschrift 5<mark>&lt;/h5></mark>
<mark>&lt;h6></mark>Überschrift 6<mark>&lt;/h6></mark>
</pre>

Mehr dazu im Abschnitt [Überschriften](http://holdirbootstrap.de/css/#type-headings) in der Bootstrap Dokumentation.


## Fett

Um Text **fett** darzustellen, verwenden wir das HTML-Element `<strong>`:

<pre class="prettyprint lang-html">
&lt;p>So kann man das <mark>&lt;strong></mark>Wichtige<mark>&lt;/strong></mark> in einem Satz hervorheben.&lt;/p>
</pre>

Mehr dazu im Abschnitt [Inline-Text-Elemente](http://holdirbootstrap.de/css/#type-inline-text) der Bootstrap Dokumentation.


## Kursiv

Um Text *kursiv* darzustellen, verwenden wir das HTML-Element `<em>` (steht für emphasis):

<pre class="prettyprint lang-html">
&lt;p>So kann man Text <mark>&lt;em></mark>kursiv<mark>&lt;/em></mark> dartsellen.&lt;/p>
</pre>

Mehr dazu im Abschnitt [Inline-Text-Elemente](http://holdirbootstrap.de/css/#type-inline-text) der Bootstrap Dokumentation.


## Schriftart ändern

Mit der CSS-Eigenschaft `font-family` können Schriftarten definiert werden. Meistens werden mehrere Schrifarten angegeben. Wenn nämlich eine Schriftart auf dem System nicht vorhanden ist, dann wird einfach die nächste in der Liste genommen.

Bootstrap verwendet standardmässig die folgenden Schriften:


##### Standard Bootstrap-Schriften

<pre class="prettyprint lang-css">
font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
</pre>

Diese Schriften werden auf dem `<body>`-Element definiert und gelten somit für die gesamte Webseite.

Wenn Sie die Schriftart ändern möchten, dann können Sie die **CSS-Regel von Bootstrap überschreiben**. Als Beispiel könnten wir die Schriftart umstellen auf eine [Serif-Schrift](http://de.wikipedia.org/wiki/Serife):


##### Andere Schriften verwenden

<pre class="prettyprint lang-css">
body {
  font-family: Georgia, "Times New Roman", Times, serif;
}
</pre>


### Verfügbare Schriftarten im Browser

Standardmässig gibt es nur wenige Schriftarten, die auf allen Systemen (Windows, Mac, Linux, Tablets, Smartphones, etc.) verfügbar sind.

Man diese Schriftarten *Web Safe Fonts*. [CSS Font Stack](http://cssfontstack.com/) ist eine der vielen Webseiten, welche diese Schriftarten auflisten. Falls Sie eine solche Schriftart verwenden, denken Sie daran, dass Sie immer mehrere Schriftarten angeben. [CSS Font Stack](http://cssfontstack.com/) bietet für jede Schrift eine empfohlene Liste von ähnlichen Schriftarten, einen sogenannten *Font Stack*.


### Eigene Schriftarten einbetten

Es gibt auch die Möglichkeit, Schriftarten gleich mit der Webseite mitzuliefern. So stehen uns unzählige Schriftarten zur Verfügung. Der einzige Nachteil ist, dass zusätzliche Schriften natürlich die Ladezeit unserer Webseite etwas verlängern.


#### Google Fonts

Die einfachste Art, Schriften einzubetten, ist mit der Hilfe von [Google Fonts](https://www.google.com/fonts). Die Google Fonts sind frei verfügbare Schriften, die nach Bedarf direkt von Google heruntergeladen werden können. So geht es:

1. Gehen Sie auf die Webseite von [Google Fonts](https://www.google.com/fonts) und wählen Sie eine Schriftart. Sobald Sie eine Schriftart ausgewählt haben, klicken Sie auf den *Quick-use*-Knopf.   
![Quick Use](/assets/library/more-html-css/text/google-fonts-quick-use.png)

2. Nun können Sie auswählen, welche Ausführung der Schrift sie möchten (z.B. light, bold, extra-bold, etc.). Kopieren Sie anschliessend den generierten Code in den `<head>`-Bereich Ihrer Webseite. Als Beispiel habe ich mal die Schrift *Open Sans* ausgewählt:   
<pre class="prettyprint lang-html">
&lt;link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
</pre>

3. Google Fonts wird den CSS-Code für die ausgewählte Schriftart automatisch generieren. Nun können wir die Schrift in unserem CSS verwenden:   
<pre class="prettyprint lang-css">
   body {
     font-family: "Open Sans", sans-serif;
   }
</pre>


#### Empfohlene Schriftkombinationen

Eine passende Schriftart auszuwählen ist sehr wichtig und gibt Ihrer Webseite ein persönliches Aussehen. Oft verwendet man zwei Schriftarten in Kombination, eine Schriftart für die Überschriften und eine für den Text.

Schauen Sie sich empfohlene Schriftkombinationen von Google Fonts auf folgenden Seiten an:
* [Theme Armada](http://blog.themearmada.com/8-amazing-google-font-combinations/)
* [Brian Gardner](http://briangardner.com/google-font-combinations/)
* [Caleb McGuire](http://www.mrmcguire.com/10-useful-google-font-combinations-for-your-next-site/)
* [Beautiful Web Type](http://hellohappy.org/beautiful-web-type/)
* und weitere

Als Beispiel wähle ich die Kombination von *Oswald* für Überschriften und *Lato* für den Text:

1. Suchen Sie auf [Google Fonts](https://www.google.com/fonts) die beiden Schriftarten *Oswald* und *Lato*. Achten Sie darauf, dass Sie für fette Überschriften die Schrift als *Bold 700* auswählen!

2. Fügen Sie den Code für *Oswald* und *Lato* im `<head>`-Bereich ein:   
<pre class="prettyprint lang-html">
&lt;link href='http://fonts.googleapis.com/css?family=Oswald:700' rel='stylesheet' type='text/css'>
&lt;link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
</pre>

3. Nun müssen wir im CSS die beiden Schriftarten einfügen:   
<pre class="prettyprint lang-css">
   body {
     font-family: "Lato", sans-serif;
   }
   
   h1, h2, h3, h4, h5, h6 {
     font-family: "Oswald", sans-serif;
     font-weight: 700;
   }
</pre>



#### Weitere Schriftarten

Neben Google Fonts gibt etliche Plattformen, wo Sie Schriftarten entweder kostenlos oder gegen eine Lizenzgebühr herunterladen können:

* [Font Squirrel](http://www.fontsquirrel.com/)
* [dafont](http://www.dafont.com/)
* [Typekit](https://typekit.com/)
* [Font Deck](http://fontdeck.com/)

