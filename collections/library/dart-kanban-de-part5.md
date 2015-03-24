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
  - text: "Teil 1: Webapplikation erstellen"
    link: /library/dart-kanban/de/part1/
    paging: 1
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
    active: true
---

Wenn man eine coole App mit Dart programmiert hat, dann möchte man diese natürlich auch veröffentlichen.


## Dart nach JavaScript übersetzen

Damit alle Browser unser Programm verstehen, müssen wir es nach JavaScript übersetzen lassen. Bevor wir das tun können, nehmen wir ein paar Einstellungen vor.


### pubspec.yaml

In jedem Dart-Projekt hat es eine `pubspec.yaml`-Datei. Öffnen Sie `pubspec.yaml` und wechseln Sie in die **Source**-Ansicht.

![Dart Kommandozeile](/assets/library/dart-kanban/part5/pubspec-source.png)

Das Wichtigste sind die `dependencies`. Diese beschreiben, welche *Packages* (externe Bibliotheken) von unserem Projekt verwendet werden. Mindestens das `browser` *Package* sollte dort vorhanden sein. Diese *Packages* werden dann automatisch in den *packages*-Ordner geladen.

Wir brauchen nun noch ein zusätzliches *package* und zwei Einstellungen. Ersetzen Sie den Inhalt von `pubspec.yaml` mit folgendem Code:

<pre class="prettyprint">
name: dart_kanban
version: 0.0.1
description: Ein Kanban Board programmiert mit Dart
environment:
  sdk: '>=1.0.0 &lt;2.0.0'
dependencies:
  browser: any
  script_inliner: any
transformers:
- script_inliner
- $dart2js:
    minify: true
</pre>


#### Erklärungen

Die Anweisung für `dart2js` bewirkt, dass das JavaScript automatisch "minifiziert", das heisst so klein wie möglich gemacht wird.

Den `script_inliner` werde ich gleich noch beschreiben.


### Scripts vorbereiten

Ganz zu Beginn haben wir in der `index.html` zwei `<script>`-Tags eingefügt. Diese schauen wir uns jetzt etwas genauer an.

Öffnen Sie also die Datei `index.html`. Zuunderst sollten Sie diese zwei Zeilen sehen:

<pre class="prettyprint lang-html">
&lt;script type="application/dart" src="main.dart">&lt;/script>
&lt;script data-pub-inline src="packages/browser/dart.js">&lt;/script>
</pre>

Das erste Script ist klar - es gibt an, wo unser Dart-Programm zu finden ist. 

Das zweite Script, `dart.js`, wird verwendet für Browser, die kein Dart unterstützten. Darin wird `main.dart` mit dem entsprechenden JavaScript ersetzt. `dart.js` ist nur eine ganz kleine JavaScript-Datei und deshalb macht es Sinn, dass diese nicht separat geladen, sondern gleich in die HTML-Datei eingebettet wird. Dafür ist der oben hinzugefügte `script_inliner` verantwortlich. Jedes Script, welches mit `data-pub-inline` markiert ist, wird vom `script_inliner` automatisch ins HTML eingefügt.



## BitBalloon



## Deployment

* BitBalloon