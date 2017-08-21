---
layout: article
title: "GreenfootKara - Hintergrundinfos"
date: 2012-10-03 00:00
slug: greenfoot-kara/de/background
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-de-teachers-info.md
published: true
prettify: true
comments: true
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/greenfoot-kara/de/
    paging: Intro
  - text: "Hintergrundinfos"
    link: /library/greenfoot-kara/de/background/
    icon-css: fa fa-fw fa-info
    active: true
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
- header: Downloads
  body:
  - text: GreenfootKara Quelldateien auf GitHub
    link: https://github.com/marcojakob/greenfoot-kara
    icon-css: fa fa-fw fa-github-alt
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
languages: 
  header: Sprachen
  collection: library
  item: greenfoot-kara
  part: background
  active: de
---

Auf dieser Seite finden Sie Hintergrundinformationen über `GreenfootKara`. Dies wird speziell hilfreich sein für Lehrpersonen.

Wenn `GreenfootKara` in der Schule eingesetzt wird, wird die Bearbeitung des gesamten Materials ca. **16-20 Lektionen** umfassen.


## Behandelte Themen

* *1 Lektion:* Installation von Greenfoot, Projekte starten
* *3 Lektionen:* Kara kennen lernen, mit der Maus Objekte erstellen und Methoden ausführen, Quelltext lesen, erstes Programm schreiben (Kapitel 1)
* *3-4 Lektionen:* Flussdiagramme, Kontrollstrukturen (Bedingungen und Schleifen), Boolean und logische Operatoren (Kapitel 2)
* *3-4 Lektionen:* Variablen, Datentypen und Operatoren, Struktur einer Klasse, verschachtelte Anweisungen (Kapitel 3)
* *3-4 Lektionen:* Spiel programmieren, Keyboard Input, Strings vergleichen, Javadoc lesen, eigene ASCII Levels designen, eigenes Szenario auf Greenfoot Webseite veröffentlichen (Kapitel 4)
* *3 lessons:* Eigene Methoden mit Parametern und Rückgabewerten, Repetition (Kapitel 5)


### Weiterführende Möglichkeiten

* *Option 1:* Ein eigenes Projekt mit Greenfoot entwickeln
* *Option 2:* Einführung in eine professionelle Entwicklungsumgebung wie Eclipse, Netbeans etc. (siehe [GameGridKara](/library/gamegrid-kara/de/))
* *Option 3:* Einführung in [GUI-Programmierung mit JavaFX](/library/javafx-8-tutorial/)


* * *

## Das Kara Scenario

Für die Verwendung des Kara-Szenarios muss die Programmierumgebung [Greenfoot](http://www.greenfoot.org) installiert werden.

Nach der Installation kann das Kara-Szenario mittels der Datei `project.greenfoot` im Szenario-Ordner geöffnet werden. 

Für jede Übungen wird jeweils ein vorbereitetes Szenario zur Verfügung gestellt (siehe Ordner *scenarios-chapter-1*, *scenarios-schapter-1-solutions* etc.). In jedem Szenario ist die Welt von Kara mit Bäumen, Kleeblättern etc. für die jeweilige Übung schon vorbereitet.

Die Möglichkeiten von Kara bleiben, ausser in Kapitel 4 und 5, überall gleich. Im Kapitel 4 kommen ein paar zusätzliche Methoden hinzu, damit das Sokoban-Spiel programmiert werden kann. Im Kapitel Kapitel 5 erhält Kara zu den Grundfunktionen noch die Möglichkeit, Nachrichten anzuzeigen und eine Eingabe des Benutzers zu erhalten.


### Die Klassen Kara und MyKara

Die wichtigsten Klassen sind `Kara` und `MyKara`. Die Klasse `Kara` beinhaltet alle Funktionalität des Käfers Kara. Programmiert wird jedoch immer in der Klasse `MyKara`, wobei durch Vererbung auf die Methoden von `Kara` zugegriffen werden kann. So müssen sich die Lernenden am Anfang nicht unnötig mit der Komplexität dieser Methoden beschäftigen.

Später können die Lernenden dann natürlich nachschauen, wie die Methoden in `Kara` implementiert wurden.
Dazu empfiehlt sich, zuerst die *Documentation-Ansicht* dieser Klasse zu verwenden, welche im Greenfoot-
Editor die Javadoc-Kommentare anzeigt.


### Der Einstieg mit der Maus

Objekte der Klassen können durch *Rechtsklick | new ....()* instanziiert und in der Welt platziert werden. (Tipp: Durch Drücken der Shift-Taste können mehrere Instanzen einer Klasse in die Welt gesetzt werden, ohne jeweils das Kontextmenu auszuwählen).

Beim ersten Kontakt mit `GreenfootKara` kann zuerst nur mit der Maus gearbeitet werden. Wenn man auf ein
Kara-Objekt einen Rechtsklick macht, dann erscheinen alle Methoden, die man zur Verfügung hat. Die kann
man auswählen und dabei beobachten, was Kara macht.


### Programmieren

Die Programme werden in der `act()`-Methode von `MyKara` geschrieben. Diese Methode wird beim Drücken
des Act-Knopfes ausgeführt. Wenn der Run-Knopf gedrückt wird, dann wird die `act()`-Methode wiederholt
aufgerufen.


* * *

## Eigene Szenarien erstellen

Um eigene Szenarien zu erstellen empfiehlt sich, ein existierendes Szenario zu kopieren und in der Datei `WorldSetup.txt` Änderungen vorzunehmen.


*Hinweis: Wenn Sie einen anderen Namen für die Datei verwenden möchten als `WorldSetup.txt`, ändern Sie die Konstante `WORLD_SETUP_FILE` in der `KaraWorld` Klasse.*

Eine World Setup Datei **kann auch mehrere Welten beinhalten**. Jede Welt muss mit den folgenden drei Zeilen beginnen:

<pre class="prettyprint">
World: [Your title]
X: [Width of the world]
Y: [Height of the world]
[Your actors]
</pre>

Die Actors werden wie folgt repräsentiert:

* `#`: Baum
* `@`: Kara
* `.`: Blatt
* `$`: Pilz
* `*`: Pilz auf einem Blatt
* `+`: Kara auf einem Blatt

**Tipp:** Erstellen Sie die Welt im Greenfoot Editor. Mit Rechtsklick auf die Welt | saveWorldSetupToFile() oder printWorldSetupToConsole() kann eine in Greenfoot erstellte Welt gespeichert werden.


* * *

## Scenario Quelldateien auf GitHub

Falls Sie Änderungen an `GreenfootKara` vornoehmen möchten, schauen Sie das [GreenfootKara Repository auf GitHub](https://github.com/marcojakob/greenfoot-kara) an. Dieses Repository enthält ein Eclipse-Projekt mit allen Quelldateien von `GreenfootKara` und allen Szenarien. Dort können Sie auch Fehler in `GreenfootKara` melden. 


* * * 

## Generelle Tipps

### Greenfoot Editor Tipps

* Oft haben Lernende Mühe mit dem sauberen Strukturieren durch Geschweifte Klammern und das Setzen
von Tabulatoren. Der Editor hilft beim Formatieren durch eine Auto-Layout-Funktion (Menu Edit).
* Mit Ctrl-Space öffnet sich eine Kontext-Hilfe für die Autovervollständigung.
* Oben Rechts im Editor kann die Ansicht von Source Code auf Documentation gestellt werden.
* Unter dem Menu *Options | Preferences ...* kann die Schriftgrösse verändert werden (z.B. für eine Präsentation am Beamer).


### Bilschirmausgaben oder Eingaben des Benutzers

Es gibt verschiedene Möglichkeiten in Greenfoot mit dem Benutzer über Input/Output zu interagieren:

* Mit `System.out.println(...)`-Befehlen etwas auf die Konsole schreiben.
* Mit einem Swing-Dialog (z.B. `JOptionPane`): Dies wird in `KaraIO` (Kapitel 5) verwendet.
* Mit gezeichneten Labels: Dies ist die komplexeste Variante ist aber elegant, da die Ein- und Ausgabe direkt auf der Welt erscheint und nicht nur in einem Popup-Dialog. Ein Beispiel dazu kann in `KaraSokoban` (Kapitel 4) gefunden werden.


### Szenarien mit anderen Teilen (Deployment)

Mit Greenfoot können Szenarien einfach exportiert und anderen zur Verfügung gestellt werden. Es gibt
dazu drei Möglichkeiten:

* Das Szenario auf die [Greenfoot Gallery](http://greenfootgallery.org) stellen. Dort kann es direkt online als Applet ausgeführt werden. Auch die Highscore-Liste von Sokoban Kara sollte funktioniern.
* Das Szenario als Runnable-Jar-Applikation exportieren.
* Eine eigene Internetseite mit dem Szenario erstellen.


* * * 

## Bekannte Fehler

### Welt erscheint nicht mehr

Es kann vorkommen, dass Greenfoot blockiert und die Welt von Kara nicht mehr gezeichnet wird. Auch ein
erneutes Kompilieren oder Drücken von Reset hilft nicht mehr.

**Grund:** Oft passiert dies, wenn das Programm zu lange in der Act-Methode bleibt und dann der Benutzer versucht, das Programm zu unterbrechen. Das ist ein Problem, das bei Greenfoot bekannt ist, jedoch schwierig zu beheben ist. Es ist nämlich bei Java kaum möglich, eine laufende Methode von aussen zu unterbrechen.

**Lösung:** Greenfoot beenden und neu starten. Alternativ kann man auch den Debugger öffnen und dort auf
*Terminate* klicken. Dies beendet das Szenario und startet gleich wieder neu.


* * * 

## Buchempfehlung und weiterführende Links

Das Buch von Michael Kölling **Einführung in Java mit Greenfoot** ist sehr zu empfehlen. Es kann entweder als Inspiration für die Lehrperson oder als Lehrbuch für die ganze Klasse verwendet werden.

* **[code.makery.ch Blog](/blog/) (Neue Versionen von GreenfootKara werden hier angekündigt)**

* **Links zu Kara and Greenfoot:**
  * Worksheets and exercises for Kara: http://www.swisseduc.ch/informatik/karatojava/javakara/material/
  * Main Website for Greenfoot: http://www.greenfoot.org
  * Greenfoot forum for teachers: http://greenroom.greenfoot.org





