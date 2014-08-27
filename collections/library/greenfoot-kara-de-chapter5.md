---
layout: article
title: "GreenfootKara - Kapitel 5: Methoden"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara/de/chapter5
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-de-chapter5.md
published: true
prettify: true
comments: true
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/greenfoot-kara/de/
    paging: Intro
  - text: "Kapitel 1: Erste Schritte"
    link: /library/greenfoot-kara/de/chapter1/
    paging: 1
  - text: "Kapitel 2: Programmfluss"
    link: /library/greenfoot-kara/de/chapter2/
    paging: 2
  - text: "Kapitel 3: Variablen"
    link: /library/greenfoot-kara/de/chapter3/
    paging: 3
  - text: "Kapitel 4: Sokoban Spiel"
    link: /library/greenfoot-kara/de/chapter4/
    paging: 4
  - text: "Kapitel 5: Methoden"
    link: /library/greenfoot-kara/de/chapter5/
    paging: 5
    active: true
  - text: "Lösungen Kapitel 5"
    link: /library/greenfoot-kara/de/chapter5-solutions/
    icon-css: fa fa-fw fa-check-square-o
- header: Downloads
  body:
  - text: scenarios-chapter-5.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/2.1.0/scenarios-chapter-5.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
- header: Sprachen
  body:
  - text: English
    link: /library/greenfoot-kara/chapter5/
    icon-css: fa fa-fw fa-globe
  - text: Deutsch
    link: /library/greenfoot-kara/de/chapter5/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Français
    link: /library/greenfoot-kara/fr/chapter5/
    icon-css: fa fa-fw fa-globe
---

*Es war einmal ein kleines Mädchen, das immer ein rotes Käppchen trug. Darum hiess es bei allen Leuten nur „Rotkäppchen". Eines Tages sagte die Mutter zu dem Kind: "Rotkäppchen, heute hat Deine Grossmutter Geburtstag. Back ihr doch ihren Lieblingskuchen, nimm eine Flasche vom guten alten Wein aus dem Keller, leg alles in einen Korb und geh sie besuchen."*

![Rotkäppchen](/assets/library/greenfoot-kara/chapter5/red-riding-hood.jpg)   
<small>*Image by [Russ Fagle](http://www.cafepress.com/redridinghood)*</small>

**Helfen Sie Rotkäppchen beim Kuchenbacken! Lesen Sie dafür aber zuerst die Theorie aufmerksam durch.**


***

## Methoden mit Parametern

Über Parameter können einer Methode Werte übergeben werden. So kann man bei folgender Methode angeben, wie viele Schritte Kara gehen soll:

<pre class="prettyprint lang-java">
public void multiMove(int steps) {
    int i = 0;
    
    while (i &lt; steps)     {
        move();
    
        i = i + 1;
    }
}
</pre>

Um z.B. 5 Schritte zu gehen, wird der Wert 5 in der Klammer angegeben:

<pre class="prettyprint lang-java">
multiMove(5);
</pre>

Um eine Methode mit mehreren Parametern aufzurufen, werden die Werte durch Kommas getrennt.

<pre class="prettyprint lang-java">
drawRectangle(21, 4);
</pre>


#### Erklärungen

1. Bei den bisherigen Programmen war es eher lästig, die Methoden immer mit einem Klammerpaar `()` zu versehen. Nun wird klar, dass die bisher eingesetzten Methoden nur Spezialfälle darstellten, bei denen kein Parameter übergeben wird.
2. In der Klammer wird der Parameter (hier `steps`) mit einem Typ angegeben (hier `int`). Mehrere Parameter werden durch Kommas getrennt. Beispiel:    
<pre class="prettyprint lang-java">
public void drawRectangle(int width, int height)
</pre>
3. Beim Aufruf einer Methode wird der Wert in die Variable (hier `width` und `height`) kopiert.


### Kuchen backen

Wir wollen nun mit Kara für die Grossmutter einen Geburtstagskuchen "backen". Der Kuchen wird aus Kleeblättern bestehen.

Öffnen Sie das Szenario **Kara 501...** aus dem Ordner **scenarios-chapter-5**.

Als Vorbereitung für das Backen sollen folgende Methoden zur Verfügung gestellt werden:

1. `public void turnAround()`   
Dreht Kara um 180 Grad.

2. `public void multiMove(int steps)`   
Kara geht die Anzahl (steps) Schritte in die aktuelle Blickrichtung (siehe Beispiel im Theorieteil).

3. `public void putLeafs(int count)`   
Kara legt die Anzahl (count) Kleeblätter. Das erste Blatt legt er an der aktuellen Position, die weiteren fortgesetzt in seine Blickrichtung.


#### <i class="fa fa-rocket"></i> AUFGABE 5.01: Kuchen backen

![Aufgabe 5.01](/assets/library/greenfoot-kara/chapter5/task01.png) 

Kara soll mit Kleeblättern ein Rechteck zeichnen, welches den Kuchen symbolisiert. 

Kara startet in der Ecke unten links und schaut nach rechts. Kara soll nicht nur eine fixe Grösse eines Rechteckes zeichnen können, sondern ein Rechteck mit variabler Breite und Höhe! Der Methodenaufruf `drawRectangle(21, 4)` soll demnach ein Rechteck mit der Breite 21 und der Höhe 4 erzeugen.


#### <i class="fa fa-rocket"></i> AUFGABE 5.02: Kerzen auf Kuchen

![Aufgabe 5.02](/assets/library/greenfoot-kara/chapter5/task02.png) 

Damit unser Kuchen auch wie ein Geburtstagskuchen aussieht, braucht er natürlich noch ein paar Kerzen. Erweitern Sie Ihr Programm um eine zusätzliche Methode `drawCandles(int count)`, welche die angegebene Anzahl Kerzen auf den Kuchen setzt. 


***

## Methoden mit Rückgabewerten

Über den Rückgabewert kann eine Methode einen Wert als Ergebnis zurückliefern. So errechnet die folgende Methode eine zufällige Zahl (zwischen 0 und 9) und gibt diese als Resultat zurück:

<pre class="prettyprint lang-java">
public int randomNumber() {
    int random = Greenfoot.getRandomNumber(10);
    return random;
}
</pre>


Das Resultat kann entweder in eine Variable gespeichert oder direkt wie folgt verwendet werden:

<pre class="prettyprint lang-java">
multiMove(randomNumber());
</pre>


#### Erklärungen

1. Der Typ des Rückgabewerts (hier `int`) wird vor dem Namen der Methode angegeben. Wenn die Methode keinen Rückgabewert enthält, wird das mit `void` ausgedrückt.
2. Die Rückgabe eines Wertes innerhalb einer Methode geschieht mit `return`, gefolgt vom Wert. 


#### <i class="fa fa-rocket"></i> AUFGABE 5.03: Kerzen nach Alter

Wir möchten der Grossmutter **für jedes Jahrzehnt eine Kerze** auf den Kuchen stecken. Da Rotkäppchen nicht genau weiss, wie alt die Grossmutter wird, muss es zuerst ihre Mutter fragen.

Programmieren Sie Kara so, dass er zuerst nach dem Alter der Grossmutter fragt und anschliessend entsprechend der eingegebenen Zahl Kerzen auf dem Kuchen platziert.

**Hinweis:** Zum Erfragen des Alters kann folgende Methode von Kara verwendet werden (ist übrigens eine Methode mit Rückgabewert `int`):

<pre class="prettyprint lang-java">
intInput("Message...")
</pre>


#### <i class="fa fa-rocket"></i> AUFGABE 5.04 (forgeschritten): Torte

![Aufgabe 5.04](/assets/library/greenfoot-kara/chapter5/task04.png) 

"Backen" Sie mit Kara eine Torte mit einer Schicht für jedes Jahrzehnt, das die Grossmutter über 50 ist. Jede Schicht soll zwei Zeilen haben und soll auf beiden Seiten um zwei Blätter nach Innen gerückt sein. 


***

## Ende ... und wie weiter?

Herzliche Gratulation, Sie haben das Ende diesr Serie erreicht!

Hier sind ein paar Möglichkeiten, wie Sie Ihr Programmierarbenteuer fortsetzen könnten:

* *Option 1:* Ein eigenes Projekt mit Greenfoot entwickeln
* *Option 2:* Einführung in eine professionelle Entwicklungsumgebung wie Eclipse, Netbeans etc. (siehe [GameGridKara](/library/gamegrid-kara/de/))
* *Option 3:* Einführung in [GUI-Programmierung mit JavaFX](/java/javafx-2-tutorial-intro/)


### Feedback

Ich freue mich von Ihnen zu hören. Schreiben Sie einen Kommentar, wenn Sie die Serie glücklich beendet haben oder auch, wenn Sie Verbesserungsvorschläge haben. <a href="#disqus_thread"><i class="fa fa-comment-o"></i></a>

<i class="fa fa-beer"></i> Marco





