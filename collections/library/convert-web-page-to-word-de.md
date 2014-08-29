---
layout: article
title: "Webseite nach Word umwandeln"
date: 2014-07-07 00:00
slug: convert-web-page-to-word/de
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/convert-web-page-to-word-de.md
published: true
comments: false
sidebars:
- header: Sprachen
  body:
  - text: English
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-globe
  - text: Deutsch
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-globe
    active: true
---

Dies ist die beste Art, die gefunden habe, um eine HTML Seite in eine Word `docx` Datei zu konvertieren. Dies kann überall angewendet werden, wo eine Webseite als Word-Version benötigt wird. 


### 1. Webseite als HTML speichern

Öffnen Sie die Webseite, die Sie konvertieren möchten. Im Browsermenu wählen Sie **Speichern unter...** (oder Ctrl+S) und speichern Sie die Seite irgendwo auf dem Computer.


### 2. Webseite in Word öffnen

Sie sollten nun eine `.htm` oder `.html` Datei haben. Machen Sie einen Rechtsklick auf diese Datei und wählen Sie **Öffnen mit...** | **Microsoft Word**. 


### 3. Speichern als DOCX

Öffnen Sie das Menu **Datei** und wählen Sie **Speichern unter...**. Ändern Sie den Dateityp auf **.docx** und speichern Sie. (Falls Sie einen Info-Dialog sehen, klicken Sie einfach ok.)


### 4. Bilder einbetten

Falls das Dokument Bilder enthält, dann sind diese Bilder wahrscheinlich nur verlinkt. Meistens will man aber, dass die Bilder direkt im Worddokument eingebettet sind.

Öffnen Sie wieder das Menu **Datei** und wählen Sie **Informationen**. Falls es verlinkte Bilder hat, sehen Sie auf der rechten Seite ein Linksymbol. Klicken Sie auf **Verknüfpungen mit Dateien bearbeiten**.

![Links to Files](/assets/library/convert-web-page-to-word/links-to-files-de.png)

Im anschliessenden Dialog müssen Sie **alle Bilder markieren** in der Liste (verwenden Sie die Shift-Taste, um mehrere Bilder zu markieren).  

Klicken Sie auf **Verknüfpung aufheben** und anschliessend auf OK. Alle Links werden entfernt und die Bilder werden in das Dokument eingebettet.


### 5. Aufräumen

Nun können wir ein wenig aufräumen. Löschen Sie alle Elemente im Dokument, welche Sie nicht brauchen, z.B. die Navigation, das Logo, etc.

Word öffnet eine Webseite normalerweise im **Weblayout**. Ändern sie dies zurück zum "normalen" **Seitenlayout**, damit Sie sehen, wie es auf gedruckte Seiten passen würde.

![Print Layout View](/assets/library/convert-web-page-to-word/print-layout-view.png)

Nachdem wir das Layout ein wenig angepasst haben, sollten wir ein ziemlich brauchbares Word Dokument der Webseite haben.

**Hinweis:** Auf Überschriften werden automatisch *Formatvorlagen* angewendet. Dies ermöglicht uns, dass wir sehr einfach alle Überschriften gleichzeitig ändern können.

![Heading Styles](/assets/library/convert-web-page-to-word/heading-styles.png)
