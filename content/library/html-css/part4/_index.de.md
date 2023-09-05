+++
title = "Teil 4: Entwicklertools im Browser"
date = 2018-08-10
description = "Lernen Sie die Entwicklertools im Browser kennen (Chrome Developer Tools)."
image = "portfolio.de.png"
prettify = true
# comments = true
commentsIdentifier = "/library/html-css/de/part4/"
aliases = [
  "/library/html-css/de/part4/"
]
weight = 4

[[sidebars]]
header = "Downloads"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Portfolio Teil 4"
link = "https://github.com/marcojakob/tutorial-html-css/releases/download/v2.0/portfolio-part4.zip"
+++

Im Chrome-Browser ist ein Tool versteckt, welches uns erlaubt, bei jeder Webseite hinter die Kulissen zu schauen. Dies ist extrem hilfreich, um entweder unsere eigene Webseite zu untersuchen oder um von anderen Webseiten etwas abzuschauen. Dieses Tool heisst **Chrome Developer Tools** (abgekürzt **DevTools**).


## Chrome DevTools öffnen

*DevTools* können Sie auf verschiedene Arten öffnen:

* Mit einem *Rechts-Klick*, ***Element untersuchen*** auf ein beliebiges Element auf der Webseite.

* Mit `Ctrl+Shift+i` oder `F12`.

Versuchen Sie das gleich mal auf Ihrer Portfolio-Webseite. Es sollte sich nun folgendes Fenster öffnen:

![DevTools öffnen](open-devtools.de.png)


## Ein Element untersuchen

Mit *DevTools* können wir nun ganz einfach einzelne Elemente auf unserer Webseite untersuchen.

Nehmen wir als Beispiel das `h2`-Element: Klicken Sie mit der rechten Maustaste auf den "Willkommen"-Titel und wählen *Element untersuchen*. Dies öffnet *DevTools* und markiert auf der linken Seite gleich das `h2`-Element.

![DevTools h2-Element](devtools-h2.de.png)

Auf der rechten Seite sehen Sie, welche *Styles* auf das `h2`-Element angewandt werden. Sie sehen dort zum Beispiel die Farben, welche wir im `main.css` definiert haben. Unter den Regeln aus dem `main.css` wird ein Bereich für das `user agent stylesheet` angezeigt. Dies sind die Regeln, die standardmässig vom Browser auf `h2`-Elemente angewendet werden.

Darunter sehen Sie ein buntes Rechteck. Dieses zeigt die Grösse, Abstände und Rahmen des `h2`-Elementes an. Zum Beispiel sehen Sie dort den Wert `5` für `padding`, wie wir ihn im `main.css` definiert hatten.


### Styles und HTML verändern

Jetzt kommt aber das Spannende: Wir können live im Browser die Styles verändern oder deaktivieren und auch das HTML editieren! Wenn Sie zum Beispiel auf das Farbquadrat von `background-color` klicken, können Sie eine andere Hintergrundfarbe auswählen oder Sie können einen anderen Wert für `padding` eingeben.

Das HTML lässt sich verändern, indem Sie mit der rechten Maustaste auf ein Element im *DevTools*-Fenster klicken und *Edit...* auswählen.

So können wir sehr einfach experimentieren. Die Änderungen sind jedoch **nur temporär**, das heisst, sobald wir die Seite neu laden, ist der ursprüngliche Zustand wieder hergestellt.


### Von anderen abschauen

Mit *DevTools* kann man jede beliebige Webseite untersuchen und auch temporär verändern. Nutzen Sie diese Möglichkeit! Wenn Sie zum Beispiel auf einer Webseite eine Farbe sehen, die Ihnen gefällt, können Sie mit *DevTools* herausfinden, welchen *Hex-Code* die Farbe hat.

Bei grösseren Webseiten können die *Styles* recht lang werden. Dort kommt es dann oft vor, dass CSS-Regeln mehrfach definiert werden und somit einander überschreiben. Sie sehen dies zum Beispiel bei unserem `h1`-Element mit dem Titel. Die Schriftgrösse, welche wir definiert haben, überschreibt die standardmässige Schriftgrösse des Browsers. Deshalb ist diese durchgestrichen.

![CSS Überschreiben](css-overwrite.de.png)


## Weitere Möglichkeiten von DevTools

Es gibt noch viele weiter Möglichkeiten, um mit *DevTools* zu arbeiten. Wenn Sie mehr darüber wissen möchten, lesen Sie die [DevTools Dokumentation](https://developers.google.com/web/tools/chrome-devtools/).


***

&rarr; Im nächsten Teil erstellen wir weitere Seiten und eine Navigation: [Teil 5: Navigieren zwischen Seiten](/de/library/html-css/part5/).
