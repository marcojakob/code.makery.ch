+++
title = "Teil 1: Erste Schritte"
date = 2015-01-21
updated = 2020-05-05
description = "Die ersten Schritte im Programmieren mit Dart. Lernen Sie Klassen und Funktionen kennen und verstehen Sie, was die main-Funktion bewirkt."
image = "around-tree.png"
prettify = true
# comments = true
commentsIdentifier = "/library/hello-dart/de/part1/"
aliases = [ 
  "/library/hello-dart/de/part1/" 
]

pagingName = "1"
weight = 3

[[sidebars]]
header = "Lösungen"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-check-square-o\"></i> Lösungen Teil 1"
link = "/de/library/hello-dart/part1/solutions/"

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-external-link\"></i> Hello Dart Szenarien"
link = "https://github.com/marcojakob/hello-dart/releases"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Seite als Word-Datei"
link = "/de/library/convert-web-page-to-word/"
+++

In diesem ersten Teil wagen wir unsere ersten Schritte im Programmieren.  

![Hello Dart](hello.png)


### Voraussetzungen

* Du brauchst keine Vorkenntnisse im Programmieren, um mit `Hello Dart` zu starten!
* Du musst Dart installiert haben. Falls du das noch nicht gemacht hast, findest du hier eine [Installationsanleitung](/de/library/dart-install/).

***

## `Hello Dart` Szenarien öffnen

1. Lade die Zip-Datei von [Hello Dart Releases](https://github.com/marcojakob/hello-dart/releases) herunter (achte darauf, die neuste Version zu nehmen).  
2. Entpacke das Zip auf deinem Computer.
3. In Visual Studio Code: Klicke auf **File | Open Folder...**. Wähle den `hello-dart` Ordner, den du soeben entpackt hast.
4. Rechtsklick auf die Datei `pubspec.yaml` und dann `Get Packages`.   
![Get Packages](get-packages.png)

Dies wird alle Abhängikeiten herunterladen, die unser Programm braucht.

##  Web Server starten

Gib nun `webdev serve` im Terminal ein.

*Hinweis: Wenn du hier eine Fehlermeldung erhältst, dann kann es sein, dass deine Installation noch nicht korrekt ist (siehe [Dart Installation](/de/library/dart-install/)).*

![Serve Webdev](terminal-start-webdev.png)

Dies wird den Web Server lokal starten. Du solltest eine lokale Web-Adresse sehen (beginnt mit http://127....). Nutze Ctrl+click auf diese URL, um den Browser zu öffnen (am besten Chrome verwenden).

Im Browser solltest du eine Liste von Szenarien sehen. Wenn du das erste Szenario öffnest, so sollte so was in dieser Art erscheinen:

![First Scenario](first-scenario.png)

## Die Klasse `MyPlayer`

Öffnen Sie die Datei `my_player.dart` aus dem Ordner `scenario1.01`.

![My Player](my-player.png)

Betrachten wir einmal die **Klasse** `MyPlayer`. Zwischen den geschweiften Klammern `{` und `}` werden die Eigenschaften und das Verhalten unseres Spielers definiert. 

Das Einzige, was im Moment in dieser Klasse steht ist `start()` und drei Anweisungen für die Bewegungen des Spielers. `start()` ist eine Funktion (wird manchmal auch *Methode* genannt). Darin können wir das Verhalten unseres Spieleres verändern.


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 1.01: First Steps

Ändern Sie die `start()`-Funktion so ab, dass der Spieler zuerst einen Schritt macht, dann einen Stern legt und schliesslich wieder einen Schritt macht.

In der [Einleitung](/de/library/hello-dart/) finden Sie alle Befehle, die ein Spieler ausführen kann.

<div class="alert alert-info">
  <strong>Beachten Sie:</strong> Nach jedem Befehl muss ein Strichpunkt <code>;</code> stehen. 
</div>

Klicken Sie auf das *Run*-Symbol ![Run](run.png), um Ihr Programm zu testen.


## `index.html` und die `main()`-Funktion

Da wir unsere Programme in einem Webbrowser laufen lassen, brauchen wir immer eine `html`-Datei. In `index.html` finden Sie jeweils eine Angabe, dass das Script `my_player.dart` geladen werden soll. Damit der Browser weiss, wo das Dart-Programm beginnt, braucht es eine `main()`-Funktion. 

Zuunterst in der Dart-Datei finden Sie die `main()`-Funktion. Jedes Dart-Programm muss genau eine `main()`-Funktion als Einstiegspunkt haben.

In unserer `main()`-Funktion rufen wir die Funktion `createWorld(...)` auf. Diese Funktion ist Teil von `Hello Dart` und zeichnet die ganze Welt mit dem Spieler und den Feldern. Sobald alles parat ist wird automatisch unsere `start()`-Funktion aufgerufen und der Spieler beginnt sich zu bewegen.


## Welt gestalten

Die Szenarien von `Hello Dart` beinhalten zusätzliche Grafiken, die Sie nach Ihren Vorlieben einschalten können.

![Catgirl](catgirl.png)


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 1.02: World Design

Ändern Sie die `main()`-Funktion wie folgt ab:

<pre class="prettyprint lang-dart">
main() {
  character = 'catgirl';
  field = 'stone';
  
  createWorld('scenario.txt', MyPlayer());
}
</pre>

Für **character** können Sie innerhalb der Anführungszeichen die Werte `boy`, `catgirl`, `stargirl` oder `pinkgirl` verwenden.

Für **field** sind die Werte `grass`, `stone`, `wood` oder `dirt` gültig.

<div class="alert alert-info">
  <strong>Tipp:</strong> Um eine Änderung zu testen geht es am schnellsten, wenn man <code>Ctrl+S</code> (oder <code>⌘+S</code>) zum Speichern und anschliessend im Browser <code>F5</code> (oder <code>⌘+R</code>) zum Aktualisieren klickt.
</div>


#### <i class="fa fa-rocket mg-t"></i> AUFGABE 1.03: Around Tree

Öffnen Sie `scenario1.03`. Wenn Sie das Szenario starten, dann sollten Sie eine Welt mit drei Bäumen und einem Stern sehen.

Schreiben Sie ein Programm, welches Ihren Spieler auf dem angegebenen Weg zum Stern führt. Er muss dabei um die Bäume herumlaufen. Beim Stern angekommen, soll er ihn entfernen.

![Around Tree](around-tree.png)


## Neue Funktionen

#### <i class="fa fa-rocket mg-t"></i> AUFGABE 1.04: Around Tree with Function

Wenn Sie Aufgabe 1.03 korrekt gelöst haben, so wird Ihr Programm wahrscheinlich *drei gleiche Teile* enthalten, nämlich für das Herumgehen um jeden Baum. Dies können wir zur besseren Übersicht noch etwas erweitern, indem wir eine neue Funktion einführen. Unterhalb der schliessenden Klammer `}` von der `start()`-Funktion erstellen wir eine neue Funktion:

<pre class="prettyprint lang-dart">
goAroundTree() {

}
</pre>

Schreiben Sie zwischen die geschweiften Klammern der Funktion die Befehle, die es braucht, um um den Baum zu kommen.

Benutzen Sie nun innerhalb der `start()`-Funktion die Funktion `goAroundTree()` für jeden der drei Bäume.


## Wie weiter?

&rarr; Fahren Sie weiter mit [Teil 2: Schleifen](/de/library/hello-dart/part2/).


***

*Quellen*<br>
<em class="small">
[Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) Bilder stammen von Daniel Cook (Lostgarden.com), veröffentlicht unter [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) hat die "Planet Cute" Bilder weiterentwickelt und mir zur Verfügung gestellt. Optimiert wurden sie mit dem grossartigen [TexturePacker](https://www.codeandweb.com/texturepacker).<br>
Einige Übungen in `Hello Dart` sind inspiriert von [Kara](http://www.swisseduc.ch/informatik/karatojava/). Kara wurde entwickelt von Jürg Nievergelt, Werner Hartmann, Raimond Reichert und anderen.
</em>