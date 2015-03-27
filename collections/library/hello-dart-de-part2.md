---
layout: article
title: "Hello Dart - Teil 2: Schleifen"
date: 2015-01-21 00:00
slug: hello-dart/de/part2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-de-part2.md
description: "Lernen Sie Schleifen programmieren mit Dart. Mit logischen Operatoren steuern wir den Programmablauf."
image: /assets/library/hello-dart/hello-dart.png
published: true
prettify: true
comments: true
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/hello-dart/de/
    paging: Einleitung
  - text: "Hintergrundinfos"
    link: /library/hello-dart/de/background/
    icon-css: fa fa-fw fa-info
    paging: <i class="fa fa-info"></i>
  - text: "Installation"
    link: /library/hello-dart/de/install/
    icon-css: fa fa-fw fa-cog
    paging: <i class="fa fa-cog"></i>
  - text: "Teil 1: Erste Schritte"
    link: /library/hello-dart/de/part1/
    paging: 1
  - text: "Teil 2: Schleifen"
    link: /library/hello-dart/de/part2/
    paging: 2
    active: true
  - text: "Teil 3: Bedingte Anweisungen"
    link: /library/hello-dart/de/part3/
    paging: 3
  - text: "Teil 4: Variablen"
    link: /library/hello-dart/de/part4/
    paging: 4
  - text: "Teil 5: Funktionen"
    link: /library/hello-dart/de/part5/
    paging: 5
  - text: "Nächste Schritte"
    link: /library/hello-dart/de/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Lösungen
  body:
  - text: "Lösungen zu Teil 2"
    link: /library/hello-dart/de/part2-solutions/
    icon-css: fa fa-fw fa-check-square-o
- header: Links
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
---

Im letzten Teil haben wir gelernt, wie wir unserem Spieler eine Reihe von Anweisungen geben können. Anstatt jede Anweisung einzeln aufzuführen, können wir auch Anweisungen wiederholen lassen. Beim Programmieren nennt man solche Wiederholungen **Schleifen**.

Als erstes Beispiel für eine Schleife möchten wir Folgendes tun: 


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 2.01: Loop

![Schleife](/assets/library/hello-dart/part2/loop.png)

Der Spieler soll sich solange vorwärts bewegen, bis er nicht mehr weiter kann.

In Dart können wir das wie folgt ausdrücken:

<pre class="prettyprint lang-dart">
while (canMove()) {
  move();
}
</pre>

Mit `while` wird etwas solange wiederholt, wie die Bedingung in der Klammer zutrifft. Wenn eine Bedingung zutrifft, dann ist sie `true` (=wahr). Sobald der Spieler am Rand ankommt, dann meldet die Sensor-Funktion `canMove()` den Wert `false` (=falsch) zurück und der Spieler hört auf, sich zu bewegen.

Testen Sie dieses Programm, indem Sie die `while`-Schleife in die `start()`-Funktion von `scenario2.01` hinein schreiben.


## Logische Operatoren

Unsere Sensor-Funktionen (siehe [Einleitung](/library/hello-dart/de/#sensoren)) antworten alle entweder mit `true` oder `false`, wenn man diese aufruft. Einen solchen Wert, der entweder wahr oder falsch sein kann, nennt man [Boolean](http://de.wikipedia.org/wiki/Boolesche_Variable).  

Boolesche Werte können mit Hilfe von logischen Operatoren auch kombiniert oder verändert werden. Die folgende Tabelle zeigt die drei wichtigsten logischen Operatoren in Dart:

<table class="table">
  <thead>
    <tr>
      <th>Operator</th>
      <th>Beschreibung</th>
      <th>Beispiel</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>`&&`</td>
      <td>und</td>
      <td>`treeFront() && onStar()`</td>
      <td>Ist erfüllt (true), wenn beide Aussagen erfüllt sind, das heisst wenn der Spieler vor einem Baum **und** auf einem Stern steht.</td>
    </tr>
    <tr>
      <td>`||`</td>
      <td>oder</td>
      <td>`treeFront() || onStar()`</td>
      <td>Ist dann erfüllt (true), wenn entweder die eine **oder** die andere Aussage oder beide erfüllt sind.</td>
    </tr>
    <tr>
      <td>`!`</td>
      <td>nicht</td>
      <td>`!treeFront()`</td>
      <td>Ändert einen Ausdruck von true in false und umgekehrt. Diese Aussage wäre also dann erfüllt (true), wenn der Spieler **nicht** vor einem Baum steht.</td>
    </tr>
  </tbody>
</table>

Ein Beispiel in Dart würde wie folgt aussehen:


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 2.02: Loop Star

![Schleife mit Stern](/assets/library/hello-dart/part2/loop-star.png)

Öffnen Sie das `scenario2.02` und programmieren Sie den Spieler mit einer Schleife so, dass er vorwärts läuft, bis er auf dem Stern steht. 


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 2.03: Leaving the Tunnel

![Tunnel verlassen](/assets/library/hello-dart/part2/leaving-the-tunnel.png)

Unser Spieler ist in einem Tunnel und möchte raus. Schreiben Sie ein Programm für `scenario2.03`, bei dem der Spieler aus dem Tunnel hinaus läuft und stehen bleibt, sobald auf einer der beiden Seiten kein Baum mehr steht.

Am Schluss soll er ausserdem einen Stern ablegen.


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 2.04: Afraid of Tunnel

![Angst vor dem Tunnel](/assets/library/hello-dart/part2/afraid-of-tunnel.png)

Der Spieler hat Angst vor Tunneln. Er soll auf jedem Feld überprüfen, ob es ein Tunneleingang ist (das heisst, ob es auf beiden Seiten Bäumen hat). Ist dies der Fall, so soll er stehen bleiben und in einer Sprechblase sagen, dass er Angst hat.


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 2.05: Climbing Up

![Treppensteigen](/assets/library/hello-dart/part2/climbing-up.png)

Der Spieler soll eine beliebig lange Treppe hochlaufen.

Schreiben Sie eine Funktion `onStepUp()`, wo Sie den Spieler eine einzelne Stufe hochsteigen lassen. Überlegen Sie sich, wie Sie erkennen können, ob der Spieler noch eine Stufe steigen muss oder ob er zuoberst angekommen ist.


## For-Schleifen

Es gibt neben der `while`-Schleife eine weitere, sehr wichtige Art von Schleife: die `for`-Schleife:

<pre class="prettyprint lang-dart">
for (var i = 0; i &lt; 4; i++) {
  move();
}
</pre>

Bei diesem Beispiel wird ein Zähler `i` verwendet. Der Zähler wird zuerst auf `0` gesetzt. Danach wird bei jedem Schleifendurchgang geprüft, ob `i` kleiner ist als 4 und schliesslich wird `i` um eins erhöht. Das bedeutet, dass die `move()`-Anweisung in dieser Schleife vier mal ausgeführt wird.

Sie werden der `for`-Schleife später immer wieder begegnen, auch zum Teil in etwas abgewandelter Form. Für den Moment reicht uns mal das Wissen, dass es sie gibt.


## Wie weiter?

Im [Teil 3](/library/hello-dart/de/part3/) lernen wir mit *bedingten Anweisungen* eine weitere Art kennen, unsere Programme zu steuern.


***

*Quellen*<br>
<em class="small">
[Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) Bilder stammen von Daniel Cook (Lostgarden.com), veröffentlicht unter [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) hat die "Planet Cute" Bilder weiterentwickelt und sie mir zur Verfügung gestellt.<br>
Einige Übungen in `Hello Dart` sind inspiriert von [Kara](http://www.swisseduc.ch/informatik/karatojava/). Kara wurde entwickelt von Jürg Nievergelt, Werner Hartmann, Raimond Reichert und anderen.
</em>