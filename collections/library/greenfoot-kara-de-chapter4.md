---
layout: article
title: "GreenfootKara - Chapter 4: Sokoban Spiel"
date: 2012-10-03 00:00
updated: 2014-03-08 00:00
slug: greenfoot-kara/de/chapter4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-de-chapter4.md
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
    active: true
  - text: "Kapitel 5: Methoden"
    link: /library/greenfoot-kara/de/chapter5/
    paging: 5
- header: Lösungen
  body:
  - text: "Lösungen Kapitel 4"
    link: /library/greenfoot-kara/de/chapter4-solutions/
    icon-css: fa fa-fw fa-check-square-o
- header: Downloads
  body:
  - text: scenarios-chapter-4.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-4.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
languages: 
  header: Sprachen
  collection: library
  item: greenfoot-kara
  part: chapter4
  active: de
---

## Sokoban 

Sokoban (倉庫番, japanisch "Lagerhausverwalter") ist ein Computerspiel, das von Hiroyuki Imabayashi entwickelt und 1982 unter der Firma "Thinking Rabbit" erstmals für verschiedene Computersysteme veröffentlicht wurde. Einige der Original-Levels tauchten später in den beiden boxxle-Versionen für den NINTENDO GameBoy wieder auf. (Source: [Sokoban auf Wikipedia](http://de.wikipedia.org/wiki/Sokoban))


### Spielprinzip

In einem einfachen Spielprinzip gilt es, mit einer Spielfigur alle Objekte – meistens sind es Kisten – nacheinander auf die dafür vorgesehenen Zielfelder zu bewegen, wobei die Kisten von der Spielfigur nur geschoben und nicht gezogen werden können und auch nicht mehrere Kisten zugleich geschoben werden können. Neben dem blossen Bestehen der Levels ist eine weiterführende Herausforderung, die dafür nötigen Schritte zu minimieren.

Bei unserem Kara Sokoban müssen die Pilze auf die Zielfelder (Kleeblätter) geschoben werden.


### Beschreibung der Levels

Viele Sokoban-Spiele verwenden zur Beschreibung der Levels ein einfaches ASCII-Format , welches den Aus-tausch erleichtert. Zur Erstellung von eigenen Levels kann dabei jeder beliebige Texteditor verwendet werden. Das Beispiel mit Kara würde wie folgt aussehen:

![Beispiel Welt](/assets/library/greenfoot-kara/chapter4/example-world.png) 

<pre class="prettyprint">
####
# .#
#  ###
# @  #
#  $ #
#  ###
####
</pre>

Dabei werden die Elemente mit den folgenden Symbolen dargestellt:

* `#`: Baum
* `@`: Kara
* `.`: Blatt
* `$`: Pilz
* `*`: Pilz auf Blatt
* `+`: Kara auf Blatt


***

## Sokoban programmieren

Damit wir mit Kara Sokoban spielen können, müssen wir das Verhalten von Kara programmieren. Der Spieler sollten in der Lage sein, Kara mit den Pfeiltasten zu steuern:


### Control with Arrow Keys

Öffnen Sie das Szenario **Kara 401 Sokoban** aus dem Ordner **scenarios-chapter-4**. 

Nun sehen Sie die gewohnte Kara-Welt mit ein paar zusätzlichen Klassen. Die zusätzlichen Klassen sollen uns nicht stören, wir werden uns weiterhin vor allem mit den Klassen `Kara` und `MyKara` beschäftigen.

Öffnen Sie mit einem Doppelklick den Editor für die (graue) Klasse `KaraSokoban`. Diese Klasse beinhaltet die Methoden, die uns beim Programmieren von `MyKara` zur Verfügung stehen. Auch die Methoden der Klasse `Kara` werden vererbt. Am besten stellen Sie oben rechts im Editor von *Source Code* auf *Dokumentation*. So sehen Sie nur die Kommentare für die Klasse und die Methoden. 

![Documentation](/assets/library/greenfoot-kara/chapter4/documentation.png) 

Das für uns Wichtigste erscheint dann unter dem Titel *Method Summary*. 

![Method Summary](/assets/library/greenfoot-kara/chapter4/method-summary.png) 

Kara besitzt zum Teil die gleichen, zum Teil auch neue Methoden. Machen Sie Sich damit vertraut und klicken Sie für ausführlichere Informationen auf die blauen Links.

Suchen Sie nun die Methode, mit welcher wir **Informationen über gedrückte Tasten** erhalten. 

Die entsprechende Methode hat nicht `void` oder `boolean` als Rückgabetyp, sondern `String`. Ein `String` ist ein Text (z.B. ein Wort oder ein Satz), der in doppelten Anführungszeichen steht. Die folgenden Beispiele sind alles `String`-Beispiele:

* `"Ich bin ein String"`
* `"Hallo"`
* `"a"`

In unserem Fall ist der zurückerhaltene `String` der Name der Taste, die zuletzt gedrückt wurde. Diesen `String` können wir mit folgender Zeile in eine Variable speichern:

<pre class="prettyprint lang-java">
String key = getKey();
</pre>

Schreiben Sie diesen Code in die `act()`-Methode vom (roten) MyKara!

`Strings` können mit einer speziellen Methode namens `equals()` verglichen werden. Um darauf zu reagieren, wenn eine bestimmte Taste gedrückt wurde, müssen wir folgende if-Bedingung hinzufügen:

<pre class="prettyprint lang-java">
if (key.equals("left")) {
	// Taste 'links' wurde gedrückt --> Mache etwas...
}
</pre>


#### <i class="fa fa-rocket"></i> AUFGABE 4.01: Kara mit den Pfeiltasten steuern

Vervollständigen Sie den Code in der `act()`-Methode so, dass Kara auf alle vier Pfeiltasten reagiert und sich in die entsprechende Richtung bewegt. Benutzen Sie dabei die Methoden, welche Sie in der Dokumentation vom (grauen) Kara finden! Zum Testen drücken Sie auf den Run-Knopf!


### Hindernisse für Kara

Was passiert, wenn Sie in einen Baum oder Pilz hineinfahren?


#### <i class="fa fa-rocket"></i> AUFGABE 4.02: Protecting Kara From Trees

Fangen Sie den Fehler so ab, dass sich Kara nicht bewegt, wenn er vor einem Baum steht.

*Zur Erinnerung:* Damit Sie den gleichen Code nicht immer wiederholen müssen, empfiehlt es sich eine Methode dafür zu schreiben!


#### <i class="fa fa-rocket"></i> AUFGABE 4.03: Kara bewegt Pilze

Schliesslich soll ja Kara die Pilze stossen. Programmieren Sie Kara so, dass er einen Pilz stossen kann. Achten Sie darauf, dass am Schluss keine Fehlermeldungen mehr entstehen.

*Hinweis:* Falls Sie irgendwo stecken geblieben sind, können Sie auf den Knopf "Retry Level" drücken!


#### <i class="fa fa-rocket"></i> AUFGABE 4.04: Arbeit erledigt

Nachdem Kara seine Arbeit erledigt hat, müsste er eine neue Aufgabe erhalten, d.h. der nächste Level müsste geladen werden. Kara hat eine Methode, mit der er überprüfen kann, ob ein Level erledigt ist. Finden Sie heraus, welche Methode es ist und rufen Sie sie an der richtigen Stelle in Ihrem Programm auf. 

Anschliessend sollten Sie in den nächsten Level gelangen. Im Moment haben Sie vier Levels, die Sie spielen können. Wie weitere Levels hinzugefügt werden können, erfahren Sie später.


#### <i class="fa fa-rocket"></i> AUFGABE 4.05: Kara zählt die Schritte

Am unteren Bildschirmrand finden Sie eine Anzeige für die ‚Moves‘, die jedoch noch nicht funktioniert. Zum Setzen dieser Zahl hat Kara auch eine Methode. Mit dem folgenden Befehl kann die Anzahl beispielsweise auf 3 gesetzt werden: `setNumberOfMoves(3);`

Erweitern Sie Ihr Programm so, dass es die Anzahl Schritte zählt. (Dafür werden Sie eine Variable ausserhalb der Methode `act()` brauchen. Dies ist dann eine sogenannte *Instanzvariable*, da sie im ganzen Objekt verfügbar ist.)


#### <i class="fa fa-rocket"></i> AUFGABE 4.06: Das Spiel richtig spielen

Nun ist unser Sokoban-Spiel richtig spielbar. Das Spiel besitzt noch ein Hauptmenu, welches erst wie folgt freigeschaltet werden muss:

In der Klasse `MyKara` finden Sie eine Konstante `DEVELOPER_MODE`. Wenn Sie diese Konstante auf `false` setzen, wird bei einem erneuten *Compile* direkt das Hauptmenu gestartet.

Im Hauptmenu können Sie Level Passwörter eingeben. So können Sie Ihr Spiel in dem Level fortsetzen, wo Sie das letzte Mal verblieben sind.


#### <i class="fa fa-rocket"></i> AUFGABE 4.07: Eigene Levels erstellen

Die Levels werden aus der Datei **Levels.txt** gelesen. Suchen Sie diese Datei und öffnen Sie sie in einem beliebigen Texteditor. Versuchen Sie einen zusätzlichen Level zu erstellen.

*Tipps:*

1. Damit das Erstellen von Levels etwas einfacher geht, können Sie es in Greenfoot machen. Dafür stellen Sie am besten wieder den `DEVELOPER_MODE` auf  `true`. Nun können Sie auf der grünen Wiese Kara, die Bäume, die Pilze und die Kleeblätter platzieren.     
Wenn Sie fertig sind, drücken Sie auf einer leeren Stelle in der Welt mit der rechten Maustaste. Wählen Sie den Befehl `generateASCIILevel()`. Nun sollte der gezeichnete Level in der Konsole im ASCII-Format ausgegeben werden. Dies können Sie nun direkt in die Level-Datei hineinkopieren.
2. Die Datei mit den Levels können natürlich auch untereinander ausgetauscht werden. Damit Sie mehrere Level-Dateien haben können, kann in der Klasse `MyKara` die Variabel `LEVEL_FILE` geändert werden.
3. Für zusätzliche Levels können Sie Sich auf folgenden Internetseiten inspirieren lassen:
  * http://users.bentonrea.com/~sasquatch/sokoban/ (unter Microban-Levels schauen, die anderen sind sehr gross!)
  * http://www.sourcecode.se/sokoban/levels.php (wenn man auf das ‚T‘ klickt, erhalten Sie die Levels in dem gewünschten ASCII-Text-Format)



The levels are read from the file **Levels.txt**. Locate the file in the scenario and open it in a text editor. Try to create an additional level.

*Hints:*

1. You can create levels in Greenfoot a bit easier with a little trick. Make sure the `DEVELOPER_MODE` is set to `true`. Now, you can place the actors in the Greenfoot world.   
When you are finished, press on a blank spot in the world with the right mouse button. Select the command `generateASCIILevel()`. This will print a level in ASCII format to the console. This code you can copy directly into the level file.
2. The file with the levels can also be exchanged with others. In order that you can have several different level-files, the variable `LEVEL_FILE` can be modified in the `MyKara` class.
3. For additional levels, you can get inspiration from the following websites:
  * http://sneezingtiger.com/sokoban/levels.html (look at Microban levels - the others are very large! - and open as text)
  * http://www.sourcecode.se/sokoban/levels.php (if you click on the *T*, you get the desired level in ASCII text format)


#### <i class="fa fa-rocket"></i> AUFGABE 4.08 (kreativ): Eigene Bilder

Die Bilder für Kara, Pilze, Hintergrund und co. können ausgewechselt werden. Um ein anderes Bild auszuwählen können Sie auf die Klassen rechts-klicken und `set Image...` drücken. Die Bilder für die Felder sind jeweils 28 x 28 Pixel gross.


#### <i class="fa fa-rocket"></i> AUFGABE 4.09 (fortgeschritten): Die Highscore

Wenn Sie in der Klasse `MyKara` die Konstante `HIGHSCORE_ENABLED` auf `true` stellen, dann können die Highscores geführt werden. Dazu wird im Hauptmenu ein zusätzlicher Knopf eingeschaltet.

Es gibt immer drei Highscores pro Level. Kara besitzt Methoden, um die Highscore zu ändern.

Versuchen Sie Ihr Programm so zu erweitern, dass geprüft wird, ob man eine Highscore erreicht hat. Falls ja, soll die Highscore hinzugefügt werden.


#### <i class="fa fa-rocket"></i> WEITERE IDEEN: Sound 

Sie können mit Greenfoot auch Sound zu Ihrem Spiel hinzufügen. Informationen dazu finden Sie in der Greenfoot Dokumentation unter der Klasse `GreenfootSound`.


***

Ich hoffe, Sie hatten Spass mit dem Käfer.
Falls Sie das Szenario mit anderen teilen möchten, können Sie es unter dem Menu *Scenario | Export ...* entweder auf die Greenfoot Gallery (online) stellen oder es als ausführbares Java-Programm exportieren. 


## Wie weiter?

Fahren Sie weiter mit [Kapitel 5: Methoden](/library/greenfoot-kara/de/chapter5)






