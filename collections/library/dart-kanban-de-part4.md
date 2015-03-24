---
layout: article
title: "Dart Kanban - Teil 4: Todos verschieben"
date: 2015-03-23 00:00
slug: dart-kanban/de/part4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/dart-kanban-de-part4.md
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
  - text: "Teil 4: Todos verschieben"
    link: /library/dart-kanban/de/part4/
    paging: 4
    active: true
  - text: "Teil 5: "
    link: /library/dart-kanban/de/part5/
    paging: 5
---

Im letzten Teil haben wir gelernt, wie wir neue Todos zu unserer Liste hinzufügen können. Damit das Kanban Board auch voll funktioniert, müssen die Todos aus der *Todo*-Spalte in die beiden anderen Spalten *Doing* und *Done* verschoben werden können. 


## HTML-Elemente verschieben und löschen

Zum Verschieben unserer Todo-Elemente werden wir auf die Klick-Events reagieren, ähnlich wie beim Knopf. Zuerst brauchen wir eine weitere Funktion für solche Events.

Fügen Sie folgende Funktion am Schluss der Dart-Datei ein:

<pre class="prettyprint lang-dart">
/// Verschiebt ein Todo-Element zur 'doing' oder 'done' Liste oder löscht es,
/// wenn es in der letzten Liste angeklickt wird.
void moveItem(Event e) {

  // Holt das gecklickte Element aus dem Event.
  Element item = e.target;

  // Verschieben oder löschen, je nach dem in welcher Liste das Element ist.
  if (item.parent == todoList) {
    doingList.children.add(item);
  } else if (item.parent == doingList) {
    doneList.children.add(item);
  } else {
    item.remove();
  }
}
</pre>


#### Erklärungen

Mit `e.target` erhält man das Element, auf welches der Benutzer geklickt hat.

Mit den `if`-Bedingungen prüfen wir, in welcher Liste das geklickte Element sich befindet. Mit `.parent` erhalten wir das übergeordnete Element, welches eines unserer drei Listen ist.

Um ein Element im HTML-Dokument zu verschieben, können wir es einfach an die neue Liste hinzufügen. Ein Element kann immer nur an einer Stelle vorkommen. Deshalb wird es automatisch von der vorherigen Liste gelöscht und in die neue eingefügt.

Falls das Element in der letzten Liste geklickt wurde, entfernen wir es.


## Auf Klick-Events reagieren

Die Funktion haben wir parat. Jetzt müssen wir diese nur noch im richtigen Moment aufrufen.

Fügen Sie in der `addTodoItem()`-Funktion eine Zeile hinzu:

<pre class="prettyprint lang-dart">
/// Fügt ein neues Todo-Element zur Liste hinzu.
void addTodoItem(Event e) {
  AnchorElement newTodo = new AnchorElement();
  newTodo.href = '#';
  newTodo.text = todoInput.value;
  newTodo.classes.add('list-group-item');

  <mark>newTodo.onClick.listen(moveItem);</mark>

  todoList.children.add(newTodo);

  // Löscht das Textfeld.
  todoInput.value = '';
}
</pre>

Mit dieser Zeile wird bei jedem neuen Todo-Element gleich ein Klick-Event-Listener hinzugefügt. Das bedeutet, sobald auf ein Todo-Element geklickt wird, wird die Funktion `moveItem()` aufgerufen.


## Abschliessen und Testen

Nun können Sie das Programm testen indem Sie ein neues Todo hinzufügen und diese dann anklicken. Es sollte jeweils in die nächste Liste verschoben und am Schluss gelöscht werden.

Wahrscheinlich werden Sie feststellen, dass das Element, welches wir als Beispiel direkt in die HTML-Datei geschrieben haben, nicht auf Klicks reagiert. Der Grund ist, dass wir nur bei den neuen Elementen einen Klick-Event-Listener hinzufügen, jedoch nicht bei den bestehenden Elementen.

Öffnen Sie nochmals die `index.html`-Datei und entfernen Sie das `<a>`-Element: 

<pre class="prettyprint lang-html">
<del>&lt;a href="#" class="list-group-item">Mein erstes Todo&lt;/a></del>
</pre>


## Wie weiter?

Im [letzten Teil](/library/dart-kanban/de/part5/) schauen wir an, wie wir unser Kanban Board von Dart nach JavaScript übersetzen können. Dann können wir es schliesslich auf einen Webserver laden und veröffentlichen.




