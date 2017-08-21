---
layout: article
title: "Hello Dart: Installation"
date: 2015-01-26 00:00
slug: hello-dart/de/install
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/hello-dart-de-install.md
description: "Dart Editor installieren. Die Hello Dart Szenarien laden und ein erstes Dart Programm starten."
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
    active: true
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
  - text: "Teil 5: Funktionen"
    link: /library/hello-dart/de/part5/
    paging: 5
  - text: "Nächste Schritte"
    link: /library/hello-dart/de/next/
    icon-css: fa fa-fw fa-flag-checkered
    paging: <i class="fa fa-flag-checkered"></i>
- header: Links
  body:
  - text: Dart Editor Download
    link: https://www.dartlang.org/tools/download.html
    icon-css: fa fa-fw fa-download
  - text: Hello Dart Szenarien
    link: https://github.com/marcojakob/hello-dart/releases
    icon-css: fa fa-fw fa-download
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
languages:
  header: Sprachen
  collection: library
  item: hello-dart
  part: install
  active: de
---

Zum Programmieren brauchen wir... 

* den **Dart Editor** und 
* die `Hello Dart`-Szenarien.


## Dart Editor installieren

1. Laden Sie den [Dart Editor hier](https://www.dartlang.org/tools/download.html) herunter.
2. Entpacken Sie das Zip mit dem Dart Editor auf Ihre Festplatte.

<div class="alert alert-info">
  <strong>Hinweis:</strong> Dart Editor benötigt Java in der Version 6 oder höher. Falls nötig, laden Sie die [Java Runtime](http://www.oracle.com/technetwork/java/javase/downloads/) herunter. Bei anderen Problemen mit der Installation, suchen Sie unter <a href="https://www.dartlang.org/tools/editor/troubleshoot.html" class="alert-link">Troubleshoot</a> nach einer Lösung.
</div>


### Was enthält die Installation?

***

<div class="row">
  <div class="col-md-2">
    ![Dart Editor](/assets/library/hello-dart/install/dart-editor-icon.png)
  </div>
  <div class="col-md-7">
    Dart Editor ist ein einfacher aber trotzdem mächtiger Editor. Damit können Dart-Projekte erstellt, editert und verwaltet werden. 
  </div>
</div>

***

<div class="row">
  <div class="col-md-2">
    ![Dartium](/assets/library/hello-dart/install/chromium-icon.png)
  </div>
  <div class="col-md-7">
    Dies ist eine spezielle Version des Chrome Web Browsers, genannt Dartium, welcher die Dart VM (virtual machine) enthält. Dart Programme können direkt in diesem Browser laufen. Der Dart Editor führt Projekte automatisch darin aus.
  </div>
</div>

***

<div class="row">
  <div class="col-md-2">
    ![Dart SDK](/assets/library/hello-dart/install/dart-sdk-icon.png)
  </div>
  <div class="col-md-7">
    Das Verzeichnis <strong>dart-sdk</strong> enthält das Dart Software Development Kit. Hier befinden sich die Dart Standard-Bibliotheken und Tools für die Kommandozeile.
  </div>
</div>

***


## Dart Editor starten

![Dart Editor Executable](/assets/library/hello-dart/install/dart-logo-21.png) Starten Sie den Dart Editor mit einem Doppelklick auf die ausführbare Datei im Installationsordner.

![Dart Editor](/assets/library/hello-dart/install/dart-editor.png)


## `Hello Dart` Szenarien öffnen

1. Laden Sie unter [Hello Dart-Releases](https://github.com/marcojakob/hello-dart/releases) die Zip-Datei `hello_dart_scenarios.zip` herunter (nehmen Sie die neuste Version).  
2. Entpacken Sie die Zip-Datei.
3. Im Dart Editor: Klicken Sie auf *File* | *Open Existing Folder...*. Wählen Sie dort den Ordner `hello_dart_scenarios` aus, den Sie vorher entpackt haben.
4. Öffnen Sie im Unterordner `web/part1/scenario1.01` die Datei `my_player.dart` mit einem Doppelklick.   
<p>
![My Player](/assets/library/hello-dart/install/my-player.png)
</p>
5. Klicken Sie auf das *Run*-Symbol ![Run](/assets/library/hello-dart/install/run.png).

Jetzt sollte sich automatisch der Chromium Browser öffnen mit dem ersten Szenario:

![First Scenario](/assets/library/hello-dart/install/first-scenario.png)


***

### Wie weiter?

Im [Teil 1](/library/hello-dart/de/part1/) machen wir unsere ersten Schritte beim Programmieren.

***

*Quellen*<br>
<em class="small">
[Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) Bilder stammen von Daniel Cook (Lostgarden.com), veröffentlicht unter [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) hat die "Planet Cute" Bilder weiterentwickelt und mir zur Verfügung gestellt. Optimiert wurden sie mit dem grossartigen [TexturePacker](https://www.codeandweb.com/texturepacker).
</em>