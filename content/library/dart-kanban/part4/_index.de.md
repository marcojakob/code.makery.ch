+++
title = "Teil 4: Elemente verschieben"
date = 2015-03-23
updated = 2020-05-05
image = "dart-kanban.png"
description = "Mit Dart HTML-Elemente verschieben und löschen. Auf Klick-Events reagieren."
prettify = true
comments = true
commentsIdentifier = "/library/dart-kanban/de/part4/"
aliases = [ 
  "/library/dart-kanban/de/part4/" 
]

pagingName = "4"
weight = 5

[[sidebars]]
header = "Source Code"

[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-github-alt\"></i> Beispielcode auf GitHub"
link = "https://github.com/marcojakob/tutorial-dart-kanban"
+++

Im vorherigen Teil haben wir gelernt, wie wir neue Todos zu unserer Liste hinzufügen können. Damit das Kanban Board auch voll funktioniert, müssen die Todos aus der *Todo*-Spalte in die beiden anderen Spalten *Doing* und *Done* verschoben werden können. 


## HTML-Elemente verschieben und löschen

Zum Verschieben unserer Todo-Elemente werden wir auf die Klick-Events reagieren, ähnlich wie beim Knopf. Zuerst brauchen wir eine weitere Funktion für solche Events.

Füge folgende Funktion am Schluss der Dart-Datei ein:

<pre class="prettyprint lang-dart">
/// Verschiebt ein Todo-Element zur 'doing' oder 'done' Liste oder löscht es,
/// wenn es in der letzten Liste angeklickt wird.
void moveItem(Event e) {
  // Das gecklickte Element aus dem Event holen.
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

Füge in der `addTodoItem()`-Funktion eine Zeile hinzu:

<pre class="prettyprint lang-dart">

/// Fügt ein neues Todo-Element zur Liste hinzu.
void addTodoItem(Event e) {
  var newTodo = AnchorElement();
  newTodo.href = '#';
  newTodo.text = todoInput.value;
  newTodo.classes.add('list-group-item');
  newTodo.classes.add('list-group-item-action');

  // Klick-Event-Listener für das Todo-Element.
  <mark>newTodo.onClick.listen(moveItem);</mark>

  todoList.children.add(newTodo);

  // Das Textfeld löschen.
  todoInput.value = '';
}
</pre>

Mit dieser Zeile wird bei jedem neuen Todo-Element gleich ein Klick-Event-Listener hinzugefügt. Das bedeutet, sobald auf ein Todo-Element geklickt wird, wird die Funktion `moveItem()` aufgerufen.


## Abschliessen und Testen

Nun kannst du das Programm testen indem du ein neues Todo hinzufügen und dieses dann anklickst. Es sollte jeweils in die nächste Liste verschoben und am Schluss gelöscht werden.

Wahrscheinlich wirst du feststellen, dass das Element, welches wir als Beispiel direkt in die HTML-Datei geschrieben haben, nicht auf Klicks reagiert. Der Grund ist, dass wir nur bei den neuen Elementen einen Klick-Event-Listener hinzufügen, jedoch nicht beim bestehenden Element.

Öffnen nochmals die `index.html`-Datei und entfernen das `<a>`-Element: 

<pre class="prettyprint lang-html">
<del>&lt;a href="#" class="list-group-item list-group-item-action">Mein erstes Todo&lt;/a></del>
</pre>


## Wie weiter?

Im [Teil 5](/de/library/dart-kanban/part5/) schauen wir an, wie wir unser Kanban Board von Dart nach JavaScript übersetzen können. Dann können wir es schliesslich auf einen Webserver laden und veröffentlichen.




