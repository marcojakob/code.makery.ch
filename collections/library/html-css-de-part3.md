---
layout: article
title: "HTML & CSS Tutorial - Teil 3: Einführung in CSS"
date: 2014-08-13 00:00
slug: html-css/de/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/html-css-de-part3.md
description: "Lernen Sie, wie Sie mit CSS eine Webseite gestalten können."
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
    active: true
  - text: "Teil 4: Entwicklertools im Browser"
    link: /library/html-css/de/part4/
    paging: 4
  - text: "Teil 5: Navigieren zwischen Seiten"
    link: /library/html-css/de/part5/
    paging: 5
  - text: "Teil 6: Bootstrap Framework verwenden"
    link: /library/html-css/de/part6/
    paging: 6
  - text: "Teil 7: Wie weiter?"
    link: /library/html-css/de/part7/
    paging: 7
- header: Downloads
  body:
  - text: Portfolio Teil 3
    link: https://github.com/marcojakob/tutorial-html-css/releases/download/v0.3/portfolio-de-part3.zip
    icon-css: fa fa-fw fa-file-archive-o
---

Vorhin haben Sie eine erste Webseite erstellt und veröffentlicht. Zugegeben, die Webseite hat noch wenig Inhalt und sieht nicht besonders ansprechend aus. Das wollen wir jetzt ändern. In diesem Teil lernen Sie, wie Sie mit CSS die Seite gestalten können. Später, im [Teil 5](/library/html-css/de/part5/) werden wir mit der Hilfe von einem Framework noch mehr Style in die Webseite bringen. 


## Struktur und Style

Nochmals zur Erinnerung: <abbr data-toggle="tooltip" title="Hypertext Markup Language">HTML</abbr> beschreibt die Struktur einer Webseite während <abbr data-toggle="tooltip" title="Cascading Style Sheet">CSS</abbr> die visuelle Erscheinung definiert. 

Die Aufteilung von Struktur und Style hat viele Vorteile, wie wir noch sehen werden. Es ist nicht zwingend, aber meistens schreibt man HTML und CSS auch in separate Dateien.


## Eine CSS-Datei einbinden

Eine CSS-Datei ist eine ganz normale Textdatei mit der Endung `.css`, die wir mit der HTML-Datei verknüpfen werden:

Erstellen Sie also in Ihrem *Portfolio*-Ordner eine **neue Datei**. Wir geben der Datei den Namen `main.css`.

![Neue CSS-Datei](/assets/library/html-css/part3/new-css-file-de.png)

Nun wechseln wir zur `index.html`-Datei und fügen innerhalb des `<head>`-Elements ein `<link>`-Element hinzu. Mit dem `<link>`-Element können wir der HTML-Seite mitteilen, dass sie die CSS-Datei laden soll:


##### Link von HTML zur CSS-Datei

<pre class="prettyprint lang-html">
&lt;link rel="stylesheet" href="main.css">
</pre>

Fügen Sie dieses Element im `<head>`-Bereich ein. Dann sollte Ihr Code etwa so aussehen:

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>

&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    <mark>&lt;link rel="stylesheet" href="main.css"></mark>
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

<div class="alert alert-info">
**Wichtig:** Damit das CSS gefunden wird, muss das `href`-Attribut genau auf die Datei verweisen. Das heisst auch, falls die Datei in einem Unterordner liegen würde, müsste dieser auch angegeben werden, also zum Beispiel `href="unterordner/main.css"`. Mehr dazu siehe unter <a class="alert-link" href="/library/html-css/de/part1/#relative-und-absolute-urls">relative und absolute URLs</a>.
</div>


## Farben

Bis hierhin war die CSS-Datei natürlich noch leer. Nun wollen wir unsere ersten Zeilen CSS schreiben. Im CSS schreibt man sogenannte *CSS-Regeln*. Mit einer *CSS-Regel* können wir angeben, was mit einem bestimmten Teil im HTML geschehen soll.

Als Beispiel werden wir die Hintergrundfarbe und die Textfarbe der zweiten Überschrift verändern. Schreiben Sie folgende Zeilen in Ihre CSS-Datei.

##### main.css

<pre class="prettyprint lang-css">
h2 {
  background-color: #607d8b;
  color: #ffffff;
}
</pre>

Das Resultat sollte etwa so aussehen:

![Geänderte Farben](/assets/library/html-css/part3/changed-color-de.png)


## Farbcodes

Farben werden auf einem Coumputerbilschirm durch eine Mischung verschiedener Anteile von Rot, Grün und Blau (RGB) erzeugt. Die Kombination aus Rot, Grün und Blau kann in CSS auf zwei Arten ausgedrückt werden: Als *RGB-Werte* oder als *Hex-Codes* (Hexadezimalzahlen). Die Notation mit *Hex-Codes* ist die gängiste.

Schauen wir uns das Beispiel mit der blau-grauen Hintergrundfarbe von oben an:

* Als Hex-Code: `#607d8b`
* Als RGB-Werte: `rgb(96, 125, 139)`

Bei den *Hex-Codes* stehen die ersten beiden Ziffern für den Rotanteil, die mittleren für den Grünanteil und die letzten beiden Ziffern für den Blauanteil.


### Mit Farbcodes arbeiten

Das Ausdrücken von Farben mit Hexadezimalzahlen ist sehr beliebt, da man damit sehr viele Farbkombinationen ausdrücken kann (über 16.7 Millionen). Es ist aber etwas schwierig mit ihnen zu arbeiten. Meistens braucht man deshalb einen Farbwähler.

Falls Sie als Editor den [Brackets](http://brackets.io/) verwenden, können Sie den Farbwähler gleich in der CSS-Datei aufrufen. Klicken Sie dazu mit der rechten Maustaste auf den Farbcode und wählen *Schnell bearbeiten*. Oder drücken Sie die Tastenkombination `Ctrl+E`.

![Schnell bearbeiten](/assets/library/html-css/part3/quick-edit-de.png)

Dann erscheint sofort ein Farbwähler, wo man bequem die Farbe auswählen kann. Sie können mit den drei Knöpfen auch zwischen den verschiedenen Notationsformen umschalten.

![Farbwähler](/assets/library/html-css/part3/color-chooser-de.png)


### Online Farbwähler

Falls Sie nicht mit dem Brackets-Editor und keinen Farbwähler integriert haben, können sie auf zahlreiche Online-Farbwähler zurückgreifen:

* [HTML Color Codes](http://html-color-codes.info/)
* [ColorPicker](http://www.colorpicker.com/)
* Und zahlreiche mehr...


### Online Farbpaletten

Meistens braucht man für eine Webseite mehr als nur eine Farbe, eine sogenannte Farbpalette. Nur mit einem einfachen Farbwähler ist es aber recht schwierig, Farben auszuwählen, die gut zueinander passen.

Dazu gibt es verschiedene Hilfen, die das finden einer Farbpalette erleichtern. Hier ein paar Beispiele:


##### Fertige Farbpaletten

* [Colour Lovers](http://www.colourlovers.com/palettes)
* [Design Seeds](http://design-seeds.com/)
* [Google Color Palette](http://www.google.com/design/spec/style/color.html)


##### Generatoren zum Erstellen von Farbpaletten

* [Kuler](https://kuler.adobe.com) - eine Farbregel definieren, am Regler mit dem kleinen Pfeil ziehen und schon hat man fünf Farben, die zusammen passen.
* [Paletton](http://www.paletton.com) - ein ausgeklügleter Regler zum Finden von Farbpaletten.
* [Colourco.de](http://colourco.de/) - ein weiterer, praktischer Paletten-Generator


## CSS-Regeln

Sie haben nun bereits gesehen, wie CSS-Regeln die Farbe eines HTML-Elementes verändern können. Nun schauen wir uns etwas genauer an, wie CSS-Regeln aufgebaut sind werden.

Eine CSS-Regel besteht aus drei Elementen, einem *Selektor*, einer *Eigenschaft* und einem *Wert*:


![CSS-Regel](/assets/library/html-css/part3/css-rule-de.png)

Diese Regel besagt, dass alle `<h2>`-Elemente eine Hintergrundfarbe von `#607d8b` erhalten sollen.


## CSS-Selektoren

Selektoren geben an, auf welches HTML-Element eine CSS-Regel angewandt werden soll. 

<div class="alert alert-info">
**Hinweis:** Wir werden hier nur die wichtigsten Selektoren kennen lernen. Damit kommt man schon sehr weit. Für eine ausführlichere Übersicht suchen Sie im Internet nach "CSS Selektoren".
</div>


### Typselektor

Der Typselektor wird angewandt auf alle Elemente mit dem entsprechenden Tag. 
Wenn wir als Beispiel alle `p`-Elemente ansprechen wollten, würde dies so aussehen:


##### CSS

<pre class="prettyprint lang-css">
p {
  ...
}
</pre>


##### HTML

<pre class="prettyprint lang-html">
&lt;p>...&lt;/p>
&lt;p>...&lt;/p>
</pre>


### Klassenselektor

Klassenselektoren werden sehr häufig verwendet. Dazu fügt man dem HTML-Element ein Attribut `class` hinzu und gibt ihm einen beliebigen Wert. In CSS kann man nun alle Elemente ansprechen, die das gleiche `class`-Attribut haben. Man macht dies mit einem **Punkt**, gefolgt vom `class`-Attribut.

Im folgenden Beispiel gilt der Klassenselektor für alle Elemente, welche ein `class`-Attribut mit dem Wert `highlight` haben:


##### CSS

<pre class="prettyprint lang-css">
.highlight {
  ...
}
</pre>


##### HTML

<pre class="prettyprint lang-html">
&lt;p class="highlight">...&lt;/p>
&lt;p>...&lt;/p>
&lt;p class="highlight new">...&lt;/p>
</pre>

In diesem HTML würde die CSS-Regel auf das erste und das dritte `p`-Element angewandt werden. Wie sie sehen, ist es auch möglich, dass ein Element mehrere Klassen besitzt (`highlight` und `new`).


### ID-Selektor

Der ID-Selektor ist sehr ähnlich wie der Klassenselektor. Er wird auf alle HTML-Elemente angewandt, die ein Attribut `id` mit einem entsprechenden Wert haben. Anstelle des Punktes verwendet man für ID-Selektoren ein **Rautezeichen** (`#`).

Der wichtige Unterschied zum Klassenselektor besteht darin, dass ID-Selektoren **nur einmal** pro HTML-Seite vorkommen sollten, während eine Klasse für beliebig viele Elemente verwendet werden kann.

Im folgenden Beispiel gilt der ID-Selektor für das Element, welches ein `id`-Attribut mit dem Wert `navigation` besitzt:


##### CSS

<pre class="prettyprint lang-css">
#navigation {
  ...
}
</pre>


##### HTML

<pre class="prettyprint lang-html">
&lt;p id="navigation">...&lt;/p>
&lt;p>...&lt;/p>
</pre>


### Nachkommenselektor

Mit einem Nachkommenselektor kann man Elemente noch etwas genauer auswählen, indem man mehrere Typ-, Klassen- oder ID-Selektoren kombiniert.

Schauen wir uns gleich ein Beispiel an, bevor ich etwas näher darauf eingehe:


##### CSS

<pre class="prettyprint lang-css">
p a {
  ...
}
</pre>

##### HTML

<pre class="prettyprint lang-html">
&lt;p>
  &lt;a href="http://code.makery.ch">Meine Webseite&lt;/a>
&lt;/p>
&lt;a href="http://www.example.com">Example Webseite&lt;/a>
</pre>

Der Nachkommenselektor `p a` wählt alle `a`-Elemente aus, die irgendwo innerhalb eines `p`-Elementes vorkommen. In unserem Beispiel würde die CSS-Regel auf *Meine Webseite* zutreffen, jedoch nicht auf *Example Webseite*. 

<div class="alert alert-info">
**Wichtig:** Das `a`-Element muss nicht unbedingt direkt dem `p`-Element untergeordnet sein. Der Nachkommenselektor trifft auf irgend ein *Nachkomme* zu. Für den anderen Fall gibt es den Kindselektor (`>`), auf den ich jedoch hier nicht eingehen werde.
</div>


## CSS-Eigenschaften

Durch CSS-Regeln wird festgelegt, wie HTML-Elemente angezeigt werden sollen. Es gibt zahlreiche Eigenschaften, die in CSS definiert werden können. Zwei davon haben wir bereits kennen gelernt, `background-color` und `color`.

Ich verzichte darauf, hier weitere der vielen CSS-Eigenschaften zu erklären. Aber ich möchte Ihnen aufzeigen, wie Sie sich Schritt für Schritt mehr CSS-Wissen aneignen können. Es macht mehr Sinn, wenn Sie in dem Moment etwas lernen, wenn Sie auch wirklich ein konkretes Bedürfnis haben. Es gibt zwei Szenarien, wie sie später vorgehen können:


#### Wenn Sie eine CSS-Eigenschaft sehen, die Sie nicht kennen...

... dann **suchen Sie im Internet nach der Eigenschaft** und zwar zusammen mit dem Stichwort `CSS`. Versuchen Sie es gleich einmal, indem Sie versuchen herauszufinden, was die folgende CSS-Regel bewirkt:

<pre class="prettyprint lang-css">
padding: 5px;
</pre>

Verwenden Sie als Suchbegriff `CSS padding`. Geben Sie sich nicht mit dem ersten Resultat zufrieden, sondern öffnen Sie ungefähr die ersten 5 oder mehr der gefundenen Seiten. Entscheiden Sie dann, welche Seite Sie genauer lesen wollen.

Versuchen Sie auch mal diese Regel auf das `h2`-Element in Ihrem Portfolio-Projekt anzuwenden.


#### Wenn Sie etwas verändern wollen, aber die CSS-Eigenschaft dazu nicht kennen...

... dann **suchen Sie im Internet nach dem, was Sie tun wollen** zusammen mit dem Stichwort `CSS`. Evtl. werden Sie mehrere Versuche brauchen, bis Sie das gefunden haben, wonach Sie gesucht haben.

Ein Beispiel: Angenommen, Sie wollen die Textgrösse verändern. Geben Sie in diesem Fall `Textgrössse CSS` als Suchbegriffe ein. Auch hier wieder darauf achten, dass Sie nicht nur das erste Suchresultat in Betracht ziehen.


## CSS-Werte

Wir haben bereits ein paar Werte von CSS-Eigenschaften kennen gelernt: Zum Beispiel Hexadezimalwerte wie `#ffffff` oder Grössenangaben wie `5px`. Je nach CSS-Eigenschaft muss man manchmal klar definierte Wörter angeben wie zum Beispiel `left` oder `right` für die Ausrichtung von Text.

Eine der wichtigsten CSS-Werte sind Grössenangaben. Deshalb schauen wir die etwas genauer an:

Die häufigste Form ist die Angabe in **Pixel**. Pixel werden als `px` abgekürzt:

<pre class="prettyprint lang-css">
p {
  font-size: 16px;
}
</pre>

Eine andere Möglichkeit ist die Angabe in **Prozent**. Prozentangaben stehen immer im Verhältnis zu einem übergeordneten Element. Wenn also im folgenden Beispiel das übergeordnete Element von `h2` die ganze Bildschirmbreite umfasst, dann bedeutet das, dass `h2` nun 60% davon breit sein wird.

<pre class="prettyprint lang-css">
h2 {
  width: 60%;
}
</pre>

Eine weitere beliebte Art für die Grössenangabe sind `em`. Ein `em` ist immer relativ zur Schriftgrösse. Das heisst, wenn man die Schriftgrösse verändert, dann verändert sich ein `em` mit.

Als Beispiel: Wenn ein Element eine Schriftgrösse von `20px` hat und eine Breite von `5em`, dann wäre die Breite des Elementes `100px`.

<pre class="prettyprint lang-css">
h2 {
  font-size: 20px;
  width: 5em;
}
</pre>


## Beispiel Klassenselektor im Portfolio

Nehmen wir mal an, wir möchten die Hauptüberschrift "Web Portfolio von Marco" etwas vergrössern. Das wäre natürlich möglich, indem wir eine CSS-Regel für das Element `h1` definieren würden. Etwa so:

<pre class="prettyprint lang-css">
h1 {
  font-size: 65px;
}
</pre>

Das Problem dabei ist, dass nun **alle** `h1`-Elemente eine grössere Schrift haben werden. Wir möchten jedoch nur diese erste Überschrift vergrössern. Dies können wir erreichen mit einem **Klassenselektor**.

Dazu fügen wir zuerst dem `h1`-Element im HTML eine Klasse hinzu. Wir können irgend einen Wert für die Klasse vergeben, ich wähle hier `title`.

<pre class="prettyprint lang-html">
&lt;h1 class="title">Web Portfolio von Marco&lt;/h1>
</pre>

Dann können wir diesen einen Titel im CSS ansprechen mit `.title`. Die CSS-Regel ist jetzt:

<pre class="prettyprint lang-css">
.title {
  font-size: 65px;
}
</pre>

Wenn alles geklappt hat, dann sieht das Portfolio nun etwa so aus: 

![Portfolio](/assets/library/html-css/part3/portfolio-de.jpg)


***

&rarr; Weiter geht's mit [Teil 4: Entwicklertools im Browser](/library/html-css/de/part4/).