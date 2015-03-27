---
layout: article
title: "Dart Kanban - Teil 5: Veröffentlichen"
date: 2015-03-23 00:00
slug: dart-kanban/de/part5
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/dart-kanban-de-part5.md
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
  - text: "Teil 1: Webapp erstellen"
    link: /library/dart-kanban/de/part1/
    paging: 1
  - text: "Teil 2: HTML vorbereiten"
    link: /library/dart-kanban/de/part2/
    paging: 2
  - text: "Teil 3: Element hinzufügen"
    link: /library/dart-kanban/de/part3/
    paging: 3
  - text: "Teil 4: Element verschieben"
    link: /library/dart-kanban/de/part4/
    paging: 4
  - text: "Teil 5: Veröffentlichen"
    link: /library/dart-kanban/de/part5/
    paging: 5
    active: true
- header: "Source Code"
  body:
  - text: Beispielcode auf GitHub
    link: https://github.com/marcojakob/tutorial-dart-kanban
    icon-css: fa fa-fw fa-github
---

Wenn man eine coole App mit Dart programmiert hat, dann möchte man diese natürlich auch veröffentlichen.


## Dart nach JavaScript übersetzen

Damit alle Browser unser Programm verstehen, müssen wir es nach JavaScript übersetzen lassen. 


### pubspec.yaml anpassen

In jedem Dart-Projekt hat es eine `pubspec.yaml`-Datei. Darin wird beschrieben, welche externen Bibliotheken (*Packages*) in unserem Projekt verwendet werden. Dazu kann man auch Einstellungen vornehmen, die die Übersetzung in JavaScript beeinflussen.

Öffnen Sie `pubspec.yaml` und wechseln Sie in die **Source**-Ansicht.

![Dart Kommandozeile](/assets/library/dart-kanban/part5/pubspec-source.png)

Wir brauchen nun noch ein zusätzliches *Package* und die Angabe eines *Transformers*. Ersetzen Sie den Inhalt von `pubspec.yaml` mit folgendem Code:

<pre class="prettyprint">
name: dart_kanban
version: 0.0.1
description: Ein Kanban Board programmiert mit Dart
environment:
  sdk: '>=1.0.0 &lt;2.0.0'
dependencies:
  browser: any
  dart_to_js_script_rewriter: any
transformers:
- dart_to_js_script_rewriter
</pre>

Der `dart_to_js_script_rewriter` wird in unserem HTML den Link auf die Dart-Datei erstezen mit einem Link auf die neu generierte JavaScript-Datei.


### Pub Build

Nun sind wir bereit für das Übersetzen in JavaScript: **Rechtsklick** auf `pubspec.yaml` | **Pub Build - Minified**. 

![Build JavaScript](/assets/library/dart-kanban/part5/build-javascript.png)

Dann dauert es ein paar Sekunden und ein neuer Ordner mit Namen `build` wird erstellt mit dem übersetzten JavaScript.


## Der Build Ordner

![Build Ordner](/assets/library/dart-kanban/part5/build-folder.png)

Im generierten `build`-Ordner befinden sich nun all die Dateien, die wir brauchen, um unser Programm auf einen Webserver zu stellen.

Neben den oben abgebildeten Dateien und Ordner gibt es noch einen Ordner `build/web/packages`, welcher im Dart Editor ausgeblendet wird (im Datei-Explorer oder Finder ist dieser sichtbar). Dieser Ordner enthält Dateien von anderen *Packages*, die das Dart-Programm allenfalls benötigt.


## Veröffentlichen (Deployment)

Das Veröffentlichen geht ganz einfach. Wir können den gesamten Ordner `build/web` auf einen Server kopieren, zum Beispiel per FTP. Da unser ganzes Programm im Browser auf dem Client läuft, haben wir keine speziellen Anforderungen an den Server.

<div class="alert alert-info">
  <strong>Tipp:</strong> Der Unterordner `build/web/packages` wird im Dart Editor ausgeblendet. Bei manchen Projekten werden in der Endversion Dateien aus dem *Packages* Ordner verwendet (in unserem Kanban Board noch nicht). Vergessen Sie bei zukünftigen Projekten nicht, diesen Ordner auch auf den Webserver zu kopieren.
</div>


### Hostingmöglichkeiten

Im [HTML & CSS Tutorial Teil 2](/library/html-css/de/part2/) habe ich verschiedene Möglichkeiten beschrieben, wie man eine Website gratis oder sehr günstig hosten lassen kann. All diese Hosting-Anbieter können genau gleich verwendet werden für unsere Dart-Programme.


#### BitBalloon

Speziell empfehlen kann ich [BitBalloon](https://www.bitballoon.com), da es kaum eine einfachere Möglichkeit gibt für Hosting: Einfach die Dateien per Drag-and-Drop in den (Chrome) Browser ziehen und schon ist die Seite live. Dies funktioniert sogar ohne Login!

**Achtung:** Eine kleine Änderung müssen wir allerdings bei unserer HTML-Datei vornehmen, damit Dart-Programme mit BitBalloon funktionieren. BitBalloon optimiert nämlich das Laden von allen Script-Dateien und gibt jeder Datei einen eigenen Speicherort. Das führt dazu, dass unser Dart-Programm die dazugehörige JavaScript-Datei nicht mehr findet.

Mit einem Attribut können wir BitBalloon mitteilen, dass es das Script nicht verschieben soll. Öffnen Sie dazu die `index.html`-Datei und fügen Sie wie folgt ein `data-dont-bundle="true"` zum Dart-Script hinzu. 

<pre class="prettyprint lang-html">
&lt;script type="application/dart" src="main.dart" <mark>data-dont-bundle="true"</mark>>&lt;/script>
</pre>

Dann können Sie wiederum `Pub Build` aufrufen, damit der `build`-Ordner nochmals neu erstellt wird. Jetzt können Sie den ganzen Ordner `build/web` packen und auf BitBalloon kopieren. Damit ist Ihr Dart-Programm schon live.


#### Andere Hostinganbieter

Neben BitBalloon geibt es natürlich zahlreiche andere Hostingmöglichkeiten. Ein paar sind wie gesagt im [HTML & CSS Tutorial](/library/html-css/de/part2/) beschrieben.


## Wie weiter?

Gratuliere! Wenn Sie bis hierhin mitgemacht haben, so haben Sie jetzt Ihr eigenes Dart-Programm geschrieben und es veröffentlicht.


### Weiterentwicklung vom Kanban Board

Es gibt zahlreiche Möglichkeiten, wie das Kanban Board weiterentwickelt werden könnte:

* Speichern der Todos lokal im Browser.
* Speichern der Todos auf einem Server.
* Verhindern, dass leere Todos erstellt werden können.
* Verschieben von Todos mit Drag and Drop (Maus oder Touch).
* Usw.

<div class="alert alert-warning">
  <strong>Bald geht's weiter:</strong> Für ein paar dieser Features werde ich in den nächsten Wochen Anleitungen entwickeln. Wenn Sie erfahren möchten, wenn neues Material zur Verfügung steht, melden Sie sich am besten für den <a href="https://tinyletter.com/codemakery" class="alert-link">Newsletter</a> an oder folgen mir auf <a href="https://twitter.com/codemakery" class="alert-link">Twitter</a> oder <a href="https://plus.google.com/+MarcoJakob7" class="alert-link">Google+</a>.
</div>


### Weitere Informationen

Auf folgenden Internetseiten finden Sie weitere Informationen zu Dart:

* [Dart Language Tour](https://www.dartlang.org/docs/dart-up-and-running/ch03.html#darthtml---browser-based-apps) - Eine ausführliche Anleitung zu den wichtigsten Features von Dart (Online-Buch)
* [Dart Library Tour](https://www.dartlang.org/docs/dart-up-and-running/ch03.html) - Anleitung für die wichtigsten Dart Bibliotheken
* [Avast, Ye Pirates](https://www.dartlang.org/codelabs/darrrt/) - Ein Einführungsbeispiel zum Erstellen einer Dart Web App
* [Write Command-Line Apps](https://www.dartlang.org/docs/tutorials/cmdline/) - Eine Einführung in Kommandozeilen-Apps mit Dart
* [The Dart Tutorials](https://www.dartlang.org/docs/tutorials/cmdline/) - Weiter (offizielle) Dart Tutorials
* [The Dart FAQ](https://www.dartlang.org/support/faq.html) - A lot of good answers for questions about Dart









