---
layout: article
title: "Dart Kanban - Teil 1: Webapplikation erstellen"
date: 2015-03-23 00:00
slug: dart-kanban/de/part1
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/dart-kanban-de-part1.md
description: ""
image: /assets/library/dart-kanban/dart-kanban.png
published: true
prettify: true
comments: false
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/dart-kanban/de/
    paging: Einleitung
  - text: "Dart Editor installieren"
    link: /library/dart-kanban/de/install/
    icon-css: fa fa-fw fa-cog
    paging: <i class="fa fa-cog"></i>
  - text: "Teil 1: Webapplikation erstellen"
    link: /library/dart-kanban/de/part1/
    paging: 1
    active: true
  - text: "Teil 2: "
    link: /library/dart-kanban/de/part2/
    paging: 2
  - text: "Teil 3: "
    link: /library/dart-kanban/de/part3/
    paging: 3
  - text: "Teil 4: "
    link: /library/dart-kanban/de/part4/
    paging: 4
  - text: "Teil 5: "
    link: /library/dart-kanban/de/part5/
    paging: 5
---

Es gibt grundsätzlich zwei Arten von Dart-Programmen:

* Programme, die auf der Konsole laufen (Kommandozeilenprogramme)
* Programme, die in einem Browser laufen (Webapplikationen)


### Kommandozeilenprogramme

Kommandozeilenprogramme brauchen bloss eine `.dart`-Datei mit dem Dart-Programm. Dieses Programm kann dann im Dart Editor mit dem Run-Knopf ![Run](/assets/library/dart-kanban/part1/run.png) gestartet werden. Alternativ kann ein solches Programm auch mit der Dart VM aus dem Ordner `dart-sdk/bin` ausgeführt werden.

![Dart Kommandozeile](/assets/library/dart-kanban/part1/command-line.png)

Wir werden uns an dieser Stelle nicht näher mit Kommandozeilenprogramme beschäftigen. Diese werden für Programme verwendet ohne grafische Benutzeroberfläche (z.B. auf einem Server). Bei Interesse gibt es hier eine [Anleitung für Kommandozeilenprogramme](https://www.dartlang.org/docs/tutorials/cmdline/).


### Webapplikationen

Neben einer Dart-Datei braucht eine Webapplikation eine HTML-Datei, die in einem Browser angezeigt werden kann.

Auch eine Webapplikation kann im Dart Editor mit dem Run-Knopf ![Run](/assets/library/dart-kanban/part1/run.png) gestartet werden. Dies öffnet den Dartium (spezieller Chrome Browser mit integrierter Dart VM), welcher die HTML-Datei öffnet. Die HTML-Datei lädt anschliessend die Dart-Datei mit dem Dart-Programm.

Wenn Sie die Webapplikation in einem anderen Browser laufen lassen möchten, dann kann der Dart-Code nach JavaScript übersetzt werden mittels dem [dart2js](https://www.dartlang.org/tools/dart2js/) Tool. Dazu gibt es im Dart Editor ein praktisches Kontextmenu:

![Run as JavaScript](/assets/library/dart-kanban/part1/run-as-javascript.png)


## Eine Webapplikation erstellen

Um eine Webapplikation zu erstellen öffnen Sie das Menu **File | New Project...**

1. Schreiben Sie `dart_kanban` in das Feld für den *Project name*.
2. Legen Sie im zweiten Feld den Ordner fest, wo Sie das Projekt speichern wollen.
3. Wählen Sie **ubersimplewebapp** aus der Liste der Vorlagen aus.
4. Klicken Sie auf **Finish**.

Der Dart Editor erstellt daraufhin alle Dateien, die es für eine Webapplikation braucht. Wir schauen uns nun die wichtigsten Dateien an.


### Die HTML-Datei

Die Datei `web/index.html` ist die Datei, die vom Browser geladen wird und die Grundstruktur vorgibt. Ersetzen Sie den automatisch erstellen Code mit dem folgenden HTML.

<pre class="prettyprint lang-html">
&lt;!DOCTYPE html>
&lt;html>
  &lt;head>
    &lt;meta charset="utf-8">
    &lt;meta http-equiv="X-UA-Compatible" content="IE=edge">
    &lt;meta name="viewport" content="width=device-width, initial-scale=1.0">
    &lt;link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    &lt;link rel="stylesheet" href="styles/main.css">
    &lt;title>Kanban Board&lt;/title>
  &lt;/head>

  &lt;body>
    &lt;div class="container">
      &lt;div class="page-header">
        &lt;h1>Kanban Board &lt;small id="subtitle">&lt;/small>&lt;/h1>
      &lt;/div>
    &lt;/div>

    &lt;script type="application/dart" src="main.dart">&lt;/script>
    &lt;script data-pub-inline src="packages/browser/dart.js">&lt;/script>
  &lt;/body>
&lt;/html>
</pre>

Speichern Sie die Datei nach den Änderungen mit **File | Save** oder der Tastenkombination **Ctrl+S**.


#### Erklärungen zum HTML-Code

Im `head` werden zwei Stylesheets eingefügt. Ich verwende hier das [Bootstrap](http://holdirbootstrap.de/) CSS. Das zweite ist unser eigenes CSS.

Der `body` enthält zwei Div-Container mit Bootstrap-Klassen und einen Titel und Untertitel. Der Untertitel hat eine ID `subtitle`, die wir später noch verwenden werden.

Am Schluss befinden sich die zwei wichtigen `script`-Tags. Das erste gibt an, dass die `main.dart`-Datei geladen werden soll. Das zweite Script, `dart.js`, wird verwendet für Browser, die kein Dart unterstützten. Darin wird `main.dart` mit dem entsprechenden JavaScript ersetzt.


### Die Dart-Datei

Öffnen Sie die Datei `main.dart` und ändern Sie den Code so ab, dass er wie folgt aussieht.

<pre class="prettyprint lang-dart">
import 'dart:html';

void main() {
  Element subtitle = querySelector('#subtitle');
  subtitle.text = 'von Marco';
}
</pre>


#### Erklärungen zum Dart-Code

In der ersten Zeile wird Darts HTML-Bibliothek importiert. Diese beinhaltet die wichtigsten Klassen und Funktionen, um mit dem HTML-Dokument zu interagieren.


##### Die main()-Funktion

Dann folgt die `main()`-Funktion. Diese Funktion ist der Startpunkt des Dart-Programms. Jede Dart-Applikation muss **genau eine main-Funktion** haben.


##### querySelector() verwenden

Die Funktion `querySelector()` stammt aus der importierten `dart:html` Bibliothek. Damit kann man ein Element aus dem HTML-Dokument holen. In unserem Fall ist dies das Element mit der ID `subtitle`.

Wir speichern das Untertitel-Element in eine Variable. Mit dem **Punkt-Operator** können wir auf Eigenschaften zugreifen und diese verändern. 

Mit dem Gleichheits-Zeichen (`=`) setzen wir den Text auf den String-Wert `'von Marco'`.

<div class="alert alert-info">
    <strong>Alternative:</strong> Wir könnten die zwei Schritte in unserem Programm auch auf einer Zeile zusammenfassen. Damit bräuchten wir keine Variable.
<pre class="prettyprint lang-dart">
querySelector('#subtitle').text = 'von Marco';
</pre>
</div>

## Die Webapplikation starten

Selektieren Sie nun die `index.html` und klicken Sie auf den Run-Knopf ![Run](/assets/library/dart-kanban/part1/run.png). Der Dart Editor öffnet Chromium und lädt die `index.html` darin. Der Dart-Code sorgt dafür, dass der Untertitel gesetzt wird, wie im folgenden Screenshot.

![Erster Start](/assets/library/dart-kanban/part1/first-run-de.png)










