---
layout: article
title: "HTML & CSS Tutorial - Teil 2: Webseite veröffentlichen"
date: 2014-08-13 00:00
slug: html-css/de/part2
github: false
published: true
prettify: true
comments: false
sidebars:
- header: Artikel dieser Serie
  body:
  - text: "Einleitung"
    link: /library/html-css/de/
    paging: Intro
  - text: "Teil 1: Unsere erste Webseite"
    link: /library/html-css/de/part1/
    paging: 1
  - text: "Teil 2: Webseite veröffentlichen"
    link: /library/html-css/de/part2/
    paging: 2
    active: true
  - text: "Teil 3: Einführung in CSS"
    link: /library/html-css/de/part3/
    paging: 3
  - text: "Teil 4: Entwicklertools im Browser"
    link: /library/html-css/de/part4/
    paging: 4
  - text: "Teil 5: Navigieren zwischen Seiten"
    link: /library/html-css/de/part5/
    paging: 5
  - text: "Teil 6: Bootstrap Framework verwenden"
    link: /library/html-css/de/part6/
    paging: 6
  - text: "Teil 7: Wie weiter?"
    link: /library/html-css/de/part7/
    paging: 7
---

Im ersten Teil haben wir unsere erste Webseite erstellt. Es wäre ja schade, wenn diese Webseite nur auf unserem Computer liegt und sie gar nie von jemand anderem angeschaut werden kann. Deshalb werden wir jetzt die Webseite gleich live ins Internet stellen. 

<div class="alert alert-info">
**Keine Angst:** Sie können die Webseite später jederzeit ändern oder löschen. Sie müssen auch nicht befürchten, dass sofort viele Besucher Ihre Webseite anschauen werden. Denn solange Sie niemandem einen Link auf die Seite schicken oder den Link irgendwo veröffentlichen, wird wohl kaum jemand einfach auf Ihre Seite stossen.
</div>


## Hosting

Was braucht es eigentlich, damit unsere Webseite im Internet aufgerufen werden kann? Wir brauchen einen **Server**, wo unsere Webseite gespeichert ist und eine **Adresse**, genannt URL, damit unsere Seite auf dem Server gefunden werden kann.

Es gibt zahlreiche Dienste, wo wir Speicherplatz auf einem Server mieten können. Solche Dienste nennt man [Hosting](http://de.wikipedia.org/wiki/Hosting). Wir schauen nun ein paar Möglichkeiten für ein (gratis) *Hosting* an. Falls dies Ihre erste Webseite ist, empfehle ich, dass Sie einmal mit **BitBalloon** beginnen.


### Hosting auf BitBalloon

![BitBalloon Logo](/assets/library/html-css/part2/bitballoon-logo.png)

[BitBalloon](https://www.bitballoon.com/) ist ein wunderbar einfacher Dienst, den ich sehr empfehlen kann. So geht es:

1. Melden Sie sich an auf der [Bitballoon Webseite](https://www.bitballoon.com/). Klicken Sie dazu auf *Sign Up* und anschliessend auf *Sign in Using Persona*. Anschliessen geben Sie Ihre E-Mail-Adresse und ein Passwort ein.

2. Sobald Sie angemeldet sind können Sie einfach Ihren gesamten Webseitenordner (Ihren Portfolio-Ordner) in den dafür vorgesehenen Bereich ziehen:   
![BitBalloon drag and drop](/assets/library/html-css/part2/bitballoon-drag-and-drop.png)

3. Kaum zu glauben, aber das war's schon. Sobald die Seite hochgeladen ist, wurde für Sie eine URL generiert, unter welcher Ihre Seite aufgerufen werden kann. Klicken Sie auf diese URL und überprüfen Sie, ob die Seite korrekt angezeigt wird.

4. Um Ihre URL etwas zu verkürzen können Sie auf ***Edit name*** klicken und einen beliebigen Namen eingeben.   
![BitBalloon veröffentlicht](/assets/library/html-css/part2/bitballoon-published.png)

5. Für Updates: Sobald Sie eine neue Version Ihrer Webseite veröffentlichen möchten, ziehen Sie den Ordner einfach erneut in das Feld mit dem Text ***Drag & Drop to Update your Site***.


### Hosting auf Google Drive

![Google Drive Logo](/assets/library/html-css/part2/google-drive-logo.png)

[Google Drive](https://drive.google.com/) eignet sich auch als *hosting*-Server. Der Nachteil ist, dass Sie eine sehr lange URL erhalten werden. So funktioniert es:

1. Laden Sie Ihren Ordner mit der Webseite auf Google Drive hoch.

2. Selektieren Sie den Ordner in Drive und wählen Sie *Freigeben*. Ändern Sie die Zugriff auf *"Öffentlich im Web"*. 

3. Öffnen Sie den Ordner in Drive und laden Sie alle Webseitendateien (index.html, Bilder, CSS, etc.) in den Ordner hoch.

4. Kopieren Sie die Zeichen nach `#folders` in der Adresszeile Ihres Browsers.   
![Google Drive Zeichen Kopieren](/assets/library/html-css/part2/google-drive-copy-string.png)

5. Geben Sie in der Addresszeile Ihres Browsers `http://googledrive.com/host/` und anschliessend die kopierten Zeichen ein.
![Google Drive Hosting Addresse](/assets/library/html-css/part2/google-drive-host-address.png)

Unter diesem Link kann nun jeder Ihre Webseite aufrufen.


### Hosting auf Dropbox

![Dropbox Logo](/assets/library/html-css/part2/dropbox-logo.png)

Man kann auch [Dropbox](https://www.dropbox.com/) als *hosting*-Server verwenden. Damit dies funktioniert, braucht man jedoch einen zusätzlichen Dienst, welcher die Webseitenanfragen weiter leitet. Zwei mögliche Dienste sind [Pancake.io](https://pancake.io/) oder [DropPages](http://droppages.com/).


### Hosting auf GitHub

![GitHub Logo](/assets/library/html-css/part2/github-logo.jpg)

Wenn Sie schon etwas Erfahrung mit Programmieren haben, dann ist auch [GitHub](https://github.com) eine gute Möglichkeit für Webseiten-Hosting. GitHub ist eine Plattform, um die Zusammenarbeit unter Programmierern zu erleichtern und bietet darüber hinaus auf [GitHub Pages](https://pages.github.com/) Hosting für Webseiten an. GitHub Pages bietet mehr Optionen (z.B. mit eigenem Domainnamen verknüpfen), aber es erfordert Kenntnisse im Arbeiten mit [Git](http://de.wikipedia.org/wiki/Git).


***

## URL verkürzen

Je nach Hosting, das Sie gewählt haben, ist die URL für Ihre Webseite recht lang. Dafür gibt es ganz einfache Dienste, mit welchen Sie eine URL verkürzen können. 

Mit verkürzten URLs wird es auch vorstellbar, dass man diese zum Beispiel auf einem Mobiltelefon eingeben kann. Manche Dienste erstellen auch gleich einen [QR-Code](http://de.wikipedia.org/wiki/QR-Code).

Hier ist eine Liste von ein paar URL-Shortener:

* [Goo.gl](https://goo.gl/) (inkl. QR-Code, wenn man auf *Details* klickt)
* [bit.ly](https://bitly.com/)
* [Tiny.cc](http://tiny.cc/)


## Eigene Domain

Irgendwann kommt der Zeitpunkt, wo Sie einen ganz eigenen Domainnamen, wie zum Beispiel `http://www.mein-super-name.ch`, möchten. Zum Teil können Sie gleich bei Ihrem Hosting-Anbieter einen Domainnamen registrieren lassen (z.B. bei BitBalloon).

Ansonsten gibt es zahlreiche Webhosting- und Registrar-Dienste. Es lohnt sich ein paar Dienste zu vergleichen. Wenn Sie persönlichen Support möchten, können Sie auch mit einem Anbieter in Ihrer Umgebung Kontakt aufnehmen.


***

&rarr; Im nächsten Teil geht es darum, der Webseite etwas Style einzuhauchen. Weiter geht's also mit [Teil 3: Einführung in CSS](/library/html-css/de/part3/).

