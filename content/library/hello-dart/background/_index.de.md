+++
title = "Hintergrundinfos"
date = 2015-01-26
description = "Hintergrundinformationen zu Hello Dart. Warum ich so begeistert bin von der Programmiersprache Dart."
image = "hello-dart.png"
prettify = true
# comments = true
commentsIdentifier = "/library/hello-dart/de/background/"
aliases = [ 
  "/library/hello-dart/de/background/" 
]

sidebarName = "<i class=\"fa fa-fw fa-info\"></i> Hintergrundinfos"
pagingName = "<i class=\"fa fa-fw fa-info\"></i>"
weight = 1
+++

Auf dieser Seite erkläre ich, weshalb ich so begeistert bin von [Dart](https://www.dartlang.org) und ich diese Programmiersprache für Programmieranfänger wie auch für professionelle Programmierer empfehle.

`Hello Dart` bietet eine Einführung in die Programmierung mit Dart. Detaillierte technische Informationen finden Sie auf der Seite von [Hello Dart auf GitHub](https://github.com/marcojakob/hello-dart).


## Weshalb Dart?

Seit langem bin ich auf der Suche nach Programmiersprachen und Programmiertools, die folgende fünf Punkte erfüllen:

<div class="panel panel-info">
  <div class="panel-heading">
    <h3 class="panel-title">Anforderungen an die "optimale" Programmiersprache</h3>
  </div>
  <div class="panel-body">
    <ol style="margin-bottom: 0;">
      <li>Macht <strong>Programmieranfänger glücklich</strong></li>
      <li>Macht <strong>professionelle Programmierer glücklich und produktiv</strong></li>
      <li>Läuft auf <strong>mobilen</strong> Plattformen: Android, iOS, Windows Phone</li>
      <li>Läuft auf dem <strong>Desktop</strong>: Windows, Mac OS, Linux</li>
      <li>Hat <strong>breite Einsatzmöglichkeiten</strong>: Interaktive Spiele, Animationen, Business-Applikationen, Client-Server-Applikationen, etc.</li>
    </ol>
  </div>
</div>

Natürlich gibt es nie die "perfekte" Programmiersprache für alle Anwendungsfälle. Aber eine Sprache, die die oben genannten Anforderungen zufriedenstellend erfüllt, wäre eine **sehr gute Sprache für sehr viele Anwendungen**.

Diesen Mix an Anforderungen kann meiner Meinung nach im Moment nur [Dart](https://www.dartlang.org) erfüllen! Im folgenden Abschnitt versuche ich darzulegen, weshalb praktisch alle anderen Programmiersprachen an mindestens einem dieser fünf Punkte scheitern.


### Mobile Applikationen

<div class="alert alert-warning">
  <strong>Sprachen:</strong> Java (Android), Swift/Objective-C (iOS), C# (Windows Phone)
</div>

Android, iOS und Windows sind die gängisten Plattformen für mobile Anwendungen. Jede der drei Plattformen hat ihre eigene Programmiersprache und Programmierumgebung. Schon das Entwickeln für eine der drei Plattformen ist anspruchsvoll. Aber wenn eine Applikation für zwei oder sogar drei dieser Plattformen programmiert werden soll, wird es für Anfänger viel zu komplex und auch für professionelle Entwickler sehr aufwändig und teuer.

Eine *Native Mobile App* läuft schliesslich auch nur auf Mobiltelefonen oder Tablets, aber nicht auf dem Desktop.


### Desktop Applikationen

<div class="alert alert-warning">
  <strong>Sprachen:</strong> C#, Java, Python, Visual Basic, Pascal usw.
</div>

Diese etablierten und bewährten Programmiersprachen bieten alle recht gute Möglichkeiten, um graphische Benutzeroberflächen für den Desktop zu programmieren. Aber diese bleiben dann meistens auch auf dem Desktop und können kaum oder nur über komplexe Umwege auf mobile Plattformen gebracht werden. Ausserdem benötigen Desktopapplikationen oft eine umständliche Installation, bis sie verwendet werden können.

Daher wurden herkömmliche Desktopapplikationen in den letzten Jahren von den mobilen und webbasierten Applikationen immer mehr verdrängt.


### Webbasierte Applikationen

Webbasierte Applikationen haben den Vorteil, dass sie sowohl auf Desktopbrowsern (Internet Explorer, Firefox, Chrome, Safari) als auch auf mobilen Browsern laufen. Damit wären also unsere Anforderungen Nr. 3 und 4 beide erfüllt.

Es gibt zwei grobe Kategorien von Sprachen für die Webentwicklung: serverseitige und clientseitige.


#### Serverseitige Webapplikationen

<div class="alert alert-warning">
  <strong>Sprachen:</strong> PHP, Ruby, Python, Java, C#, Visual Basic usw.
</div>

Bei diesen Programmiersprachen werden auf dem Webserver HTML-Seiten generiert, welche dann im Browser angezeigt werden können. Dies bedeutet, dass bei jeder Interaktion des Benutzers eine neue Anfrage an den Server geschickt werden muss, damit dieser wieder eine neue HTML-Seite generieren kann. Damit sind interaktive Spiele und Animationen mit serverseitigen Sprachen nicht möglich (Nr. 5).

Viele der serverseitigen Sprachen wurden ursprünglich nicht für die Webprogrammierung entwickelt. Erst später wurden diese Möglichkeiten hinzugefügt. Über die Jahre sind diese Sprache so stark gewachsen, dass sie immer komplexer wurden. Das heisst, es ist schwierig für Programmieranfänger (Nr. 1) und macht oft auch wenig Spass für professionelle Programmierer (Nr. 2).


#### Clientseitige Webapplikationen

<div class="alert alert-warning">
  <strong>Sprachen:</strong> JavaScript, TypeScript, CoffeeScript usw.
</div>

Clientseitige Webapplikationen sind der klare Trend. JavaScript ist die einzige Programmiersprache, welche von allen gängigen Browsern unterstützt wird. Es ist mit JavaScript möglich, ganze Webanwendungen zu schreiben, welche direkt in mobilen Browsern (Nr. 3) und Desktopbrowsern (Nr. 4) laufen können.

Leider ist JavaScript eine Programmiersprache mit schwierigen Konzepten und vielen Ausnahmefällen. Diese Hindernisse sind für Anfänger (Nr. 1) und für professionelle Programmierer (Nr. 2) sehr frustrierend.

Aus diesem Grund gibt es immer mehr Sprachen (z.B. TypeScript und CoffeeScript) und Tools (z.B. jQuery, AngularJS, Ember.js), die versuchen, die Probleme von JavaScript zu korrigieren. 

Auch JavaScript selber wird weiterentwickelt (siehe ECMAScript 6). Viele Fehler der Vergangenheit werden aber nicht korrigiert werden können, da sonst die existierenden Webapplikationen nicht mehr laufen würden. Das heisst es kommt meist einfach mehr Funktionalität hinzu. Dies macht es für Programmieranfänger (Nr. 1) und Profis (Nr. 2) immer komplexer.    


#### Dart

Dart ist für das Web gemacht und lässt sich direkt in JavaScript übersetzen. Somit laufen Dart-Anwendungen ohne zusätzliche Installation in allen modernen Desktopbrowsern (Nr. 4) und mobilen Browsern (Nr. 3).

Neben clientseitigen Webapplikationen (z.B. interaktive Spiele und dymanische Business-Apps) können auch Server-Applikationen mit Dart geschrieben werden. Somit sind die Einsatzmöglichkeiten enorm breit (Nr. 5).

Dart ist stark an die bekanntesten Programmiersprachen wie Java und C# angelehnt, ist aber konsistenter und eleganter. Das macht die Sprache einfach zu lernen für Umsteiger (Nr. 2) und viel einfacher für Programmieranfänger (Nr. 1).

Fast genauso wichtig wie die Programmiersprache selbst ist das, was rund um die Sprache zur Verfügung steht. Auch hier ist Dart enorm durchdacht und kaum eine andere Sprache bietet so einfache Programmiertools: Komfortable aber einfache Entwicklungsumgebung, keine komplizierte Installation, viele nützliche Bibliotheken, die nach bedarf automatisch vom Server heruntergeladen werden, etc.

Ganz einfach: Ich bin begeistert von Dart und ich denke, dass Dart noch viele Programmieranfänger und erfahrene Programmierer glücklich machen wird!


### Fazit

<div class="table-responsive">
<table class="table table-bordered table-striped">
  <thead>
    <tr>
      <th></th>
      <th>Mobile Apps</th>
      <th>Desktop Apps</th>
      <th>Serverseitige Web-Apps</th>
      <th>Clientseitige Web-Apps</th>
      <th>Dart</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">Glückliche Anfänger&nbsp;(1)</th>
      <td><i class="fa fa-star-half-full fa-lg text-warning"></i></td>
      <td><i class="fa fa-star-half-full fa-lg text-warning"></i></td>
      <td><i class="fa fa-times fa-lg text-danger"></i></td>
      <td><i class="fa fa-times fa-lg text-danger"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
    </tr>
    <tr>
      <th scope="row">Glückliche Profis&nbsp;(2)</th>
      <td><i class="fa fa-star-half-full fa-lg text-warning"></i></td>
      <td><i class="fa fa-star-half-full fa-lg text-warning"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
      <td><i class="fa fa-star-half-full fa-lg text-warning"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
    </tr>
    <tr>
      <th scope="row">Läuft auf Mobile&nbsp;(3)</th>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
      <td><i class="fa fa-times fa-lg text-danger"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
    </tr>
    <tr>
      <th scope="row">Läuft auf Desktop&nbsp;(4)</th>
      <td><i class="fa fa-times fa-lg text-danger"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
    </tr>
    <tr>
      <th scope="row">Breite Einsatz&shy;möglichkeiten&nbsp;(5)</th>
      <td><i class="fa fa-star-half-full fa-lg text-warning"></i></td>
      <td><i class="fa fa-star-half-full fa-lg text-warning"></i></td>
      <td><i class="fa fa-star-half-full fa-lg text-warning"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
      <td><i class="fa fa-check fa-lg text-success"></i></td>
    </tr>
  </tbody>
</table>
</div>