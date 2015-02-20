---
layout: article
title: "Hello Dart - Teil 4: Variablen"
date: 2015-01-21 00:00
slug: hello-dart/de/part4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-de-part4.md
description: ""
image: /assets/library/hello-dart/hello-dart.png
published: true
prettify: true
comments: false
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/hello-dart/de/
    paging: Einleitung
  - text: "<em>Installation</em>"
    link: /library/hello-dart/de/install/
    icon-css: fa fa-fw fa-cog
  - text: "<em>Hintergrundinfos</em>"
    link: /library/hello-dart/de/background/
    icon-css: fa fa-fw fa-info
  - text: "Teil 1: Erste Schritte"
    link: /library/hello-dart/de/part1/
    paging: 1
  - text: "Teil 2: Schleifen"
    link: /library/hello-dart/de/part2/
    paging: 2
  - text: "Teil 3: Bedingte Anweisungen"
    link: /library/hello-dart/de/part3/
    paging: 3
  - text: "Teil 4: Variablen"
    link: /library/hello-dart/de/part4/
    paging: 4
    active: true
  - text: "Teil 5: Funktionen"
    link: /library/hello-dart/de/part5/
    paging: 5
- header: Lösungen
  body:
  - text: "Lösungen zu Teil 4"
    link: /library/hello-dart/de/part4-solutions/
    icon-css: fa fa-fw fa-check-square-o
- header: Links
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
---

Mit Schleifen haben wir gelernt, bestimmte Ereignisse zu wiederholen, solange eine Bedingung erfüllt ist. Nun möchten wir aber folgendes tun:

*Der Spieler soll eine Spur von fünf Sternen legen.*

![Fünf Sterne](/assets/library/hello-dart/part4/five-stars.png)

Dies wäre natürlich recht einfach, wenn wir `putStar()` und `move()` fünfmal aufrufen, jedoch wenig elegant. Schöner wäre es, wenn unser Spieler mitzählt, wie viele Sterne er bereits abgelegt hat. Damit der Spieler zählen kann, braucht er ein "Gedächtnis", d.h. konkret einen Speicher. Beim Programmieren sind die Speicherplätze durch Variablen ansprechbar.


## Zählen mit Variablen

<pre class="prettyprint lang-dart">
var i;
i = 0;

while (i &lt; 5) {
  putStar();
  move();

  i = i + 1;
}
</pre>


#### Erklärungen

1. Mit `var i;` wird Speicherplatz für eine Variable mit dem Namen `i` reserviert. Man sagt: Die **Variable i wird deklariert**.
2. Durch `i = 0;` wird der Variable `i` der Wert `0` zugewiesen. Da es die erste Wertzuweisung für die Variable ist, sagt man auch: Die **Variable i wird initialisiert**.
3. Deklaration und Initialisierung werden meistens zusammengefasst wie folgt:   
`var i = 0;`
4. Für die Bedingung `i < 5` wird der Vergleichsoperator `<` benutzt (weitere Vergleichsoperatoren siehe Tabelle weiter unten).
5. Bei der Zuweisung `i = i + 1;` muss man zuerst den rechten Teil anschauen. Es bedeutet: "Nimm den aktuellen Wert von i, addiere 1 dazu und speichere den neuen Wert wieder unter dem Namen i ab."


#### Weitere Hinweise zu Variablen

* Es ist möglich, eine Variable mit einem endgültigen, unveränderlichen Wert zu versehen, d.h. sie zu einer Konstanten zu machen:   
`const anzahl = 5;`   
Dann könnte man im obigen Programmbeispiel `while (i < anzahl)` schreiben.
* Variablen schreibt man mit einem Kleinbuchstaben, wie die Methodennamen.


***

## Datentypen in Dart

In Dart gibt es ein paar vordefinierte Datentypen. Mit Datentypen gibt man an, was in eine Variable gespeichert wird. Diese Angaben sind in Dart freiwillig. Das heisst, man kann alle Variablen einfach mit `var` deklarieren, wie wir es oben gemacht haben. Ich empfehle aber, meistens den Datentyp anzugeben, weil dies hilft, Fehler zu erkennen.

<div class="alert alert-info">
  <strong>Merke:</strong> Alles, was in eine Variable gespeichert werden kann, wird in Dart als **Objekt** bezeichnet. Der Datentyp eines Objektes nennen wir eine **Klasse**. Klassen und Objekte sind die Grundelemente der <a class="alert-link" href="http://de.wikipedia.org/wiki/Objektorientierte_Programmierung">objektorientierten Programmierung</a>.
</div>

Später werden wir unsere eigenen Klassen erstellen. Jetzt schauen wir uns zuerst die Grunddatentypen von Dart an.

*Für weitere Informationen zu den Grunddatentypen siehe [Dart Up and Running - Chapter 2: Built-in types](https://www.dartlang.org/docs/dart-up-and-running/ch02.html#built-in-types).*

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Typ</th>
      <th>Beschreibung</th>
      <th>Beispiel</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`int`</td>
      <td>Ganze Zahlen</td>
      <td>`1`, `2`, `-1233123`</td>
    </tr>
    <tr>
      <td>`double`</td>
      <td>Fliesskommazahlen</td>
      <td>`0.5`, `-333.234`</td>
    </tr>
    <tr>
      <td>`String`</td>
      <td>Text in einer beliebigen Länge. Text wird immer in *zwischen einfache oder doppelte Anführungszeichen* gestellt.</td>.
      <td>`'Ich bin ein Text'` oder `"Ich bin auch ein Text"`</td>
    </tr>
    <tr>
      <td>`bool`</td>
      <td>Ein Boolean ist ein Wahrheitswert, der entweder *wahr* oder *falsch* sein kann.</td>
      <td>`true` oder `false`</td>
    </tr>
    <tr>
      <td>`List`</td>
      <td>Eine Sammlung von Objekten. Wird manchmal auch *Array* genannt.</td>
      <td>`[1, 2, 3]`</td>
    </tr>
    <tr>
      <td>`Map`</td>
      <td>In einer Map kann man Objekte speichern als *Schlüssel* und *Werte*.</td>
      <td>`{ 'schlüssel-1': 'wert-1', 'schlüssel-2': 'wert-2' }`</td>
    </tr>
  </tbody>
</table>


## Vergleichsoperatoren

Folgende Operatoren können in Dart für Vergleiche verwendet werden. Das Ergebnis ist jeweils ein `bool`, das heisst entweder `true` oder `false`.

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Operator</th>
      <th>Beschreibung</th>
      <th>Beispiel</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`==`</td>
      <td>gleich</td>
      <td>`k == 2`</td>
    </tr>
    <tr>
      <td>`!=`</td>
      <td>ungleich</td>
      <td>`k != 12`</td>
    </tr>
    <tr>
      <td>`>`</td>
      <td>grösser als</td>
      <td>`k > 67`</td>
    </tr>
    <tr>
      <td>`<`</td>
      <td>kleiner als</td>
      <td>`k < 12`</td>
    </tr>
    <tr>
      <td>`>=`</td>
      <td>grösser als oder gleich</td>
      <td>`k >= 45`</td>
    </tr>
    <tr>
      <td>`<=`</td>
      <td>kleiner als oder gleich</td>
      <td>`k <= 23`</td>
    </tr>
  </tbody>
</table>

**Achtung:** Der Vergleich auf "Gleichheit" hat immer zwei Gleichheitszeichen `==`. Mit einem einzelnen Gleichheitszeichen `=` geschieht eine Zuweisung!


## Arithmetische Operatoren

Zur Berechnung können folgende arithmetische Operatoren verwendet werden:

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Operator</th>
      <th>Beschreibung</th>
      <th>Beispiel</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`+`</td>
      <td>Addition</td>
      <td>`h = w + 34`</td>
    </tr>
    <tr>
      <td>`-`</td>
      <td>Subtraktion</td>
      <td>`y = 3.4 – t`</td>
    </tr>
    <tr>
      <td>`*`</td>
      <td>Multiplikation</td>
      <td>`z = h * 3.56`</td>
    </tr>
    <tr>
      <td>`/`</td>
      <td>Division</td>
      <td>`d = m / v`</td>
    </tr>
    <tr>
      <td>`%`</td>
      <td>Modulo (liefert den Rest der Division)</td>
      <td>`count = w % 2`</td>
    </tr>
  </tbody>
</table>


***

#### <i class="fa fa-rocket mg-t"></i> AUFGABE 4.01: Counting Stars

![Sternenspur](/assets/library/hello-dart/part4/counting-stars.png)

Der Spieler soll von links nach rechts gehen bis zum Baum und dabei die Sterne zählen.

*Hinweise:*

1. Definieren und initialisieren Sie eine Variable, zum Beispiel mit   
`int count = 0;`
2. Schreiben Sie darunter eine Schleife, in der die Variable immer um eins erhöht wird:   
`count = count + 1;`
3. Die Variable sollte nur erhöht werden, wenn der Spieler auf einem Stern ist.
4. Am Schluss soll der Spieler angeben, wie viele Sterne er gezählt hat. Mit `${count}` können wir die Variable in einen Text einfügen:   
`say('Ich habe ${count} Sterne gefunden.');`


<div class="alert alert-info">
  <strong>Gültigkeitsbereich von Variablen:</strong> Variablen sind immer nur innerhalb des Blockes (zwischen den geschweiften Klammern) gültig, in welchem sie deklariert wurden. Man kann sie auch ausserhalb der Methoden deklarieren, dann gelten sie für die ganze Klasse.
</div>


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 4.02: Invert

![Sternenspur](/assets/library/hello-dart/part4/invert.jpg)

Eine quadratische Fläche ist von Bäumen umrandet. Innerhalb der Fläche ist ein Muster aus Kleeblättern gelegt, das von Kara invertiert (= umgedreht) werden soll. Kara startet links oben in der Ecke mit Blick nach rechts.

*Hinweise:*

* Bei dieser Aufgabe ist es hilfreich, mit booleschen Variablen zu arbeiten, z.B.:
  * Deklaration und Initilalisierung: `boolean goingRight = false;`
  * Aus true wird false und umgekehrt: `goingRight = !goingRight;`
  * Boolesche Variable als Bedingung: `if (goingRight)`



#### <i class="fa fa-rocket"></i> TASK 3.03: Kara in a Box II

![TASK 3.03](/assets/library/greenfoot-kara/chapter3/task03.png) 

Eine quadratische Fläche ist von Bäumen umrandet. Innerhalb der Fläche soll ein schachbrettartiges Muster aus Kleeblättern von Kara gelegt werden. Kara startet links oben in der Ecke mit Blick nach rechts.



#### <i class="fa fa-rocket"></i> TASK 3.04 (schwierig): Die längste Baumreihe

![TASK 3.04](/assets/library/greenfoot-kara/chapter3/task04.png) 

Auf der Wiese hat es verschiedene Baumreihen. Kara soll nun die Länge (in Anzahl Bäumen) der längsten Baumreihe ermitteln und auf die Konsole ausgeben. Zwischen d en Baumreihen ist immer mindestens ein Feld Platz. Auf dem letzten Feld liegt ein Kleeblatt.



#### <i class="fa fa-rocket"></i> TASK 3.05 (sehr schwierig): Push Mushroom Through Tunnel

![TASK 3.05](/assets/library/greenfoot-kara/chapter3/task05.png) 

Die Welt von Kara hat zwei Boxen, welche durch einen Tunnel verbunden sind. In der Box links befindet sich Kara und ein Kleeblatt. In der Box rechts befindet sich ein Pilz. Kara soll nun auf die andere Seite gelangen, den Pilz finden und ihn auf die andere Seite schieben. Auf der anderen Seite angekommen soll der Pilz schliesslich auf das Kleeblatt geschoben werden.

Sie können davon ausgehen, dass Kara immer oben links in der Ecke startet und das Kleeblatt sich unten links befindet. Der Pilz kann jedoch an einer beliebigen Stelle rechts vom Tunnel stehen.

*Hints:*

* Diese Aufgabe kann auch in Zusammenarbeit zu zweit gelöst werden. 
* Dabei können die Teilprobleme untereinander aufgeteilt werden:
  * Tunneleingang finden
  * Pilz finden
  * Pilz vor den Tunneleingang stossen
  * Pilz auf das Kleeblatt stossen



***

## Vertiefung zu Variablen

Wir hatten bereits einen ersten Kontakt zu Variablen. Nun noch ein paar zusätzliche Bemerkungen zu den verschiedenen Typen:


### Elementare Datentypen

![Elementare Datentypen](/assets/library/greenfoot-kara/chapter3/elementary-types.png) 

Elementare Datentypen sind wie Becher (im Speicher), wo der Wert direkt in eine Variable gespeichert wird.


### Referenztypen

![Referenztypen](/assets/library/greenfoot-kara/chapter3/reference-types.png) 

Der Wert in `k` ist eine Referenz auf das Kara-Objekt. Mit dem Punktoperator (`k.`) kann `k` wie eine Fernbedienung auf das Kara-Objekt benutzt werden! 


***

***Credits:*** *[Ideen und Konzepte von Kara](http://www.swisseduc.ch/informatik/karatojava/) wurden entwickelt von Jürg Nievergelt, Werner Hartmann, Raimond Reichert et al. Einige Kara-Übungen basieren auf [Unterlagen von Horst Gierhardt](http://www.swisseduc.ch/informatik/karatojava/javakara/material/).*


***

## Wie weiter?

Fahren Sie weiter mit [Kapitel 4: Sokoban Spiel](/library/greenfoot-kara/de/chapter4/)