---
layout: article
title: "Hello Dart - Teil 2: Programmfluss"
date: 2015-01-21 00:00
slug: hello-dart/de/part2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-de-part2.md
description: "Dart Editor installieren. Das Hello Dart Package hinzufügen und ein erstes Dart Programm starten."
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
  - text: "Teil 2: Programmfluss"
    link: /library/hello-dart/de/part2/
    paging: 2
    active: true
  - text: "Teil 3: Variablen"
    link: /library/hello-dart/de/part3/
    paging: 3
  - text: "Teil 4: Funktionen"
    link: /library/hello-dart/de/part4/
    paging: 4
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

Im letzten Teil haben wir gelernt, wie wir unserem Spieler eine Reihe von Anweisungen geben können. Anstatt jede Anweisung einzeln aufzuführen, können wir auch Anweisungen wiederholen lassen. Beim Programmieren nennt man solche Wiederholungen *Schleifen*.

## Schleifen

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

Unsere Sensor-Funktionen (siehe [Einleitung](/library/hello-dart/de/#sensoren)) antworten alle entweder mit `true` oder `false`, wenn man diese aufruft. Solche Werte nennt man einen [booleschen Wert](http://de.wikipedia.org/wiki/Boolesche_Variable).  

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


## Bedingte Anweisungen

Neben den Schleifen gibt es eine zweite Struktur, die sehr wichtig ist, um den Programmablauf zu steuern. Mit bedingten Anweisungen kann man angeben, in wann ein Block von Anweisungen ausgeführt werden soll und wann nicht.


<pre class="prettyprint lang-dart">
if (treeFront()) {    // Bedingung.
  turnLeft();         // Block 1 wird ausgeführt, wenn Bedingung true ist.
} else {
  move();             // Block 2 wird ausgeführt, wenn Bedingung false ist.
}
</pre>

**Hinweis: Der `else`-Teil (Block 2) kann weggelassen werden, wenn er nicht benötigt wird.**


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 2.04: Conditionals

Beschreiben Sie mit Worten, was die folgenden Codebeispiele bewirken. Testen Sie diese anschliessend im `scenario2.04`. Damit die bedingten Anweisungen nicht nur einmal ausgeführt werden, sollten sie diese in die vorbereitete Schleife setzen.


##### a.

<pre class="prettyprint lang-dart">
if (onStar()) {
  removeStar();
}
move();
</pre>


##### b.

<pre class="prettyprint lang-dart">
if (onStar()) {
  removeLeaf();
} else {
  putLeaf();
}
move();
</pre>


##### c.

Bedingte Anweisungen können auch ineinander verschachtelt werden:

<pre class="prettyprint lang-dart">
if (treeLeft()) {
  if (onStar()) {
    removeStar();
  }
}
move();
</pre>


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 2.05: Star at Tree

![Blatt beim Baum](/assets/library/hello-dart/part2/star-at-tree.png)

Der Spieler soll geradeaus gehen und überall dort einen Stern legen, wo entweder links oder rechts ein Baum steht.

Öffnen Sie das `scenario2.05` und schreiben Sie das Programm dazu.


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 2.06: Afraid of Tunnel

![Angst vor dem Tunnel](/assets/library/hello-dart/part2/afraid-of-tunnel.png)

Der Spieler hat Angst vor Tunneln. Er soll auf jedem Feld überprüfen, ob es ein Tunneleingang ist (das heisst, ob es auf beiden Seiten Bäumen hat). Ist dies der Fall, so soll er in einer Sprechblase sagen, dass er Angst hat.


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 2.07: Star Trak

***

*Quellen*<br>
<em class="small">
[Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) Bilder stammen von Daniel Cook (Lostgarden.com), veröffentlicht unter [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) hat die "Planet Cute" Bilder weiterentwickelt und sie mir zur Verfügung gestellt.<br>
Viele Übungen in `Hello Dart` sind inspiriert von [Kara](http://www.swisseduc.ch/informatik/karatojava/). Kara wurde entwickelt von Jürg Nievergelt, Werner Hartmann, Raimond Reichert und anderen.
</em>