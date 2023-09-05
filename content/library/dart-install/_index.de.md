+++
title = "Installation"
date = 2015-01-26
updated = 2020-05-05
description = "Dart und Visual Studio Code installieren."
image = "dart-sdk-icon.png"
prettify = true
# comments = true
commentsIdentifier = "/library/hello-dart/de/install/"
aliases = [ 
  "/library/hello-dart/de/install/" 
]

sidebarName = "<i class=\"fa fa-fw fa-cog\"></i> Installation"
pagingName = "<i class=\"fa fa-fw fa-cog\"></i>"
weight = 2

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-external-link\"></i> Dart SDK"
link = "https://dart.dev/tools/sdk/archive"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-external-link\"></i> Visual Studio Code"
link = "https://code.visualstudio.com/"
+++

Zum Programmieren brauchen wir... 

- Dart SDK
- Visual Studio Code Editor
- `Hello Dart`-Szenarien


## Dart installieren

![Dart SDK](dart-sdk-icon.png)

1. Gehe zur [Dart SDK Website](https://dart.dev/tools/sdk/archive).
2. Unter `Stable channel` lade das Dart SDK als zip für dein Betriebssystem herunter.
3. Entpacke das heruntergeladene Zip in einen Ordner, z.B. `C:\dart-sdk\` (auf Windows).

### PATH Umgebungsvariable definieren

Um die Dart-Tools von der Kommandozeile aus aufrufen zu können, müssen wir zwei Verzeichnise zur PATH [Umgebungsvariable](https://www.computerhope.com/issues/ch000549.htm) hinzufügen:

- Den `bin` Unterordner aus dem Ort, wo du das Dart SDK gespeichert hast: `C:\dart-sdk\bin` (oder so ähnlich).
- Den [pub cache](https://dart.dev/tools/pub/cmd/pub-global#running-a-script-from-your-path) (Darts package manager): `%APPDATA%\Pub\Cache\bin` (auf Windows) or `$HOME/.pub-cache/bin` (auf macOS)

## Visual Studio Code installieren

<a href="https://code.visualstudio.com/" target="_blank">![VS Code Editor Logo](vs-code-logo.png)
</a>

Gehe zur [Visual Studio Code](https://code.visualstudio.com/) Website und installiere es. Visual Studio Code wird unser Programmiereditor sein.

### Dart Extension installieren

In Visual Studio Code öffne das "extensions" Menu.

![Extensions](extensions.png)

Installiere nun die Dart "extension". Dies macht den Code Editor parat für die Dart Sparche.

![Dart Extension](extensions-dart.png)

## Web Server installieren

Wir brauchen einen Web Server, genannt [webdev](https://dart.dev/tools/webdev), um unsere Dart-Programme zu starten.

Öffne das Terminal unter dem Menu **View | Terminal**.

Gib den Befehl `pub global activate webdev` ein und drücke Enter.

![Install Webdev](terminal-install-webdev.png)

## Projektgenerator installieren (Stagehand)

Um das Grundgerüst von Webapplikation zu erstellen, werden wir [Stagehand](https://pub.dev/packages/stagehand) verwenden.

Öffne das Terminal unter dem Menu **View | Terminal**.

Gib den Befehl `pub global activate stagehand` ein.

![Install Stagehand](terminal-install-stagehand.png)

## Startklar

Nun solltest du parat sein für deine Dart-Abenteuer zum Beispiel mit [Hello Dart](/de/library/hello-dart/) oder [Dart Kanban](/de/library/dart-kanban/).