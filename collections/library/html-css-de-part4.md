---
layout: article
title: "Entwicklertools im Browser"
date: 2014-07-22 00:00
slug: html-css/de/part4
github: false
published: true
prettify: true
comments: false
sidebars:
- header: Artikel in dieser Serie
  body:
  - text: "Unsere erste Webseite"
    link: /library/html-css/de/first-web-page/
    paging: 1
- header: Downloads
  body:
  - text: Seite als Word-Datei
    link: /library/convert-web-page-to-word/de/
    icon-css: fa fa-fw fa-file-word-o
---


Im Chrome-Browser ist ein Tool versteckt, welches uns erlaubt, bei jeder Webseite hinter die Kulissen zu schauen. Dies ist extrem hilfreich, um entweder unsere eigene Webseite zu untersuchen oder um von anderen Webseiten etwas abzuschauen. Dieses Tool heisst **Chrome Developer Tools** (abgekürzt **DevTools**).


## Chrome DevTools öffnen

*DevTools* können Sie auf verschiedene Arten öffnen: 

* Mit einem *Rechts-Klick*, ***Element untersuchen*** auf ein beliebiges Element auf der Webseite.

* Mit `Ctrl+Shift+i` oder `F12`.

Versuchen Sie das gleich mal auf Ihrer Portfolio-Webseite. Es sollte sich nun folgendes Fenster öffnen:

![DevTools öffnen](/assets/library/html-css/part4/open-devtools-de.png)

**Hinweis:** *Wenn Sie den Brackets-Editor verwenden, deaktivieren Sie die Live-Vorschau und laden Sie die Seite neu. Ansonsten werden noch zusätzliche "ids" von Brackets angezeigt.*


## Ein Element untersuchen

Mit *DevTools* können wir nun ganz einfach einzelne Elemente auf unserer Webseite untersuchen.

Nehmen wir als Beispiel das `h2`-Element: Klicken Sie mit der rechten Maustaste auf den "Willkommen"-Titel und wählen *Element untersuchen*. Dies öffnet *DevTools* und markiert auf der linken Seite gleich das `h2`-Element.

![DevTools h2-Element](/assets/library/html-css/part4/devtools-h2-de.png)

Auf der rechten Seite sehen Sie, welche *Styles* auf das `h2`-Element angewandt werden. Sie sehen dort zum Beispiel die Farben, welche wir im `main.css` definiert haben. Unter den Regeln aus dem `main.css` wird ein Bereich für das `user agent stylesheet` angezeigt. Dies sind die Regeln, die standardmässig vom Browser auf `h2`-Elemente angewendet werden.

Darunter sehen Sie ein buntes Rechteck. Dieses zeigt die Grösse, Abstände und Rahmen des `h2`-Elementes an. Zum Beispiel sehen Sie dort den Wert `5` für `padding`, wie wir ihn im `main.css` definiert hatten. 


### Styles und HTML verändern

Jetzt kommt aber das Spannende: Wir können live im Browser die Styles verändern oder deaktivieren und auch das HTML editieren! Wenn Sie zum Beispiel auf das Farbquadrat von `background-color` klicken, können Sie eine andere Hintergrundfarbe auswählen oder Sie können einen anderen Wert für `padding` eingeben.  

Auch das HTML lässt sich verändern, indem Sie mit der rechten Maustaste auf ein Element im *DevTools*-Fenster klicken und *Edit...* auswählen.

So können wir sehr einfach experimentieren. Die Änderungen sind jedoch **nur temporär**, das heisst, sobald wir die Seite neu laden, ist der ursprüngliche Zustand wieder hergestellt.


### Von anderen abschauen

Mit *DevTools* kann man jede beliebige Webseite untersuchen und auch temporär verändern. Nutzen Sie diese Möglichkeit! Wenn Sie zum Beispiel auf einer Webseite eine Farbe sehen, die Ihnen gefällt, können Sie mit *DevTools* herausfinden, welchen *Hex-Code* die Farbe hat. 

Bei grösseren Webseiten können die *Styles* recht lang werden. Dort kommt es dann oft vor, dass CSS-Regeln mehrfach definiert werden und somit einander überschreiben. Sie sehen dies zum Beispiel bei unserem `h1`-Element mit dem Titel. Die Schriftgrösse, welche wir definiert haben, überschreibt die standardmässige Schriftgrösse des Browsers. Deshalb ist diese durchgestrichen.

![CSS Überschreiben](/assets/library/html-css/part4/css-overwrite-de.png)


## Weitere Möglichkeiten von DevTools

Wir werden *DevTools* immer mal wieder verwenden. Es gibt noch viele weitere Funktionien von *DevTools*. Sie können 

Es gibt noch viele weiter Möglichkeiten, um mit *DevTools* zu arbeite. Wenn Sie mehr darüber wissen möchten, lesen Sie die [DevTools Dokumentation](https://developer.chrome.com/devtools/index).

Ausserdem gibt es einen sehr guten [DevTools Online-Kurs](http://discover-devtools.codeschool.com/), welcher gratis zur Verfügung steht. 