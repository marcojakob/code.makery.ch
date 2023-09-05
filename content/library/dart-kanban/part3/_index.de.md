+++
title = "Teil 3: Elemente hinzufügen"
date = 2015-03-23
updated = 2020-05-05
image = "dart-kanban.png"
description = "Mit Dart neue HTML-Elemente hinzufügen. Auf Events vom Benutzer reagieren. Lernen, wie man mit der HTML-Bibliothek von Dart arbeitet."
prettify = true
# comments = true
commentsIdentifier = "/library/dart-kanban/de/part1/"
aliases = [ 
  "/library/dart-kanban/de/part1/" 
]

pagingName = "3"
weight = 4

[[sidebars]]
header = "Source Code"

[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-github-alt\"></i> Beispielcode auf GitHub"
link = "https://github.com/marcojakob/tutorial-dart-kanban"
+++


In diesem Teil programmieren wir die dynamische Funktionalität von unserem Kanban Board mit Dart.


## Elemente vom HTML-Dokument ansprechen

Im ersten Teil haben wir bereits ein kurzes Beispiel gesehen, wie wir mit Dart das Untertitel-Element aus dem HTML-Dokument holen konnten:

<pre class="prettyprint lang-dart">
querySelector('#subtitle');
</pre>

Schaue kurz in der `index.html`-Datei nach. Dort haben wir bereits alle wichtigen Elemente mit einer ID versehen. Über diese IDs können wir nun sehr einfach die Elemente aus dem Dart-Code heraus ansprechen und schliesslich auch verändern.

Wir ergänzen nun unser `main.dart` wie folgt:

<pre class="prettyprint lang-dart">
import 'dart:html';

InputElement todoInput;
ButtonElement todoButton;

DivElement todoList;
DivElement doingList;
DivElement doneList;

void main() {
  // Den Untertitel setzen.
  var subtitle = querySelector('#subtitle');
  subtitle.text = 'by Marco';

  // Die Referenzen auf die HTML-Elemente holen.
  todoInput = querySelector('#todo-input');
  todoButton = querySelector('#todo-button');

  todoList = querySelector('#todo-list');
  doingList = querySelector('#doing-list');
  doneList = querySelector('#done-list');
}
</pre>


#### Erklärungen

Weil wir die verschiedenen Elemente (Textfeld, Knopf und Listen) immer wieder brauchen werden, **speichern** wir diese **in eigene Variablen**. Wir definieren die Variablen ausserhalb der `main()`-Funktion, damit wir später auch aus anderen Funktionen darauf zugreifen können.

Wichtig sind auch die **Typen der Variablen** wie zum Beispiel `InputElement` und `ButtonElement`.

<div class="alert alert-info">
  <strong>Objektorientierte Progammiersprachen:</strong> Typen von Variablen werden in Dart auch <em>Klassen</em> genannt. Klassen und schliesslich auch <em>Objekte</em> sind Begriffe, die in objektorierten Programmiersprachen verwendet werden.</div>

Schauen wir uns mal das **Beispiel vom Button** an: Oberhalb der `main()`-Funktion wird die Variable `todoButton` mit dem Typ `ButtonElement` definiert. Innerhalb der `main()`-Funktion wird dann das entsprechende Element aus dem HTML-Dokument geholt und in die Variable gespeichert.


## Ein Element hinzufügen

Die HTML-Elemente, die wir per Knopfdruck in die Todo-Liste hinzufügen möchten, sehen wie folgt aus:

<pre class="prettyprint lang-html">
&lt;a href="#" class="list-group-item list-group-item-action">Neues Todo&lt;/a>
</pre>

Wir erstellen eine neue Funktion, die solche Elemente erstellt und diese an die richtige Liste anhängt. Füge folgende Funktion ganz am Ende der Dart-Datei hinzu:

<pre class="prettyprint lang-dart">
/// Fügt ein neues Todo-Element zur Liste hinzu.
void addTodoItem(Event e) {
  var newTodo = AnchorElement();
  newTodo.href = '#';
  newTodo.text = todoInput.value;
  newTodo.classes.add('list-group-item');
  newTodo.classes.add('list-group-item-action');

  todoList.children.add(newTodo);

  // Das Textfeld löschen.
  todoInput.value = '';
}
</pre>


#### Erklärungen

Mit `void addTodoItem(Event e)` wird die Funktion definiert. `void` bedeutet, dass diese Funktion keinen Wert zurückliefert. Wir wollen einfach eine Aktion ausführen, nämlich, dass ein neues Todo-Element hinzugefügt wird.

In der nächsten Zeile wird ein neues `<a>`-Element (`AnchorElement`) erstellt.

Jedes `<a>`-Element braucht einen Link. Da wir aber nirgendwo hinspringen wollen bei einem Klick, setzen wir den Link auf ein `#`. 

Mit `todoInput.value` holen wir den Wert aus dem Textfeld und setzen ihn gleich in unser neues Todo-Element.

Damit die Elemente schön dargestellt werden, fügen wir die Bootstrap-Klassen `list-group-item` und `list-group-item-action` hinzu.


##### Kind-Elemente

Jedes HTML-Element hat eine Liste von allen untergeordneten Elementen (genannt Kind-Elemente). Auch unser `todoList`-Element hat eine solche Liste, die wir mit `.children` ansprechen können. Wir können also mit `todoList.children.add(newTodo)` unser neues Todo-Element an diese Liste anfügen. Dies bewirkt, dass der Browser dieses Element anzeigt.  


## Auf Events reagieren

Wenn du jetzt das Programm startest, dann funktioniert das Hinzufügen noch nicht. Was fehlt, ist, dass wir auf den Knopfdruck reagieren.

Bei einem Knopfdruck werden vom Browser Events produziert, die wir abfangen können.

Füge die folgende Code-Zeile am Schluss der `main()`-Funktion ein:

<pre class="prettyprint lang-dart">
void main() {
  ...Bisheriger Code...

  // Auf Knopfdruck-Event reagieren.
  todoButton.onClick.listen(addTodoItem);
}
</pre>


#### Erklärungen

Wir geben an, dass wir beim `todoButton` auf den Click-Event reagieren möchten. Die `listen`-Funktion akzeptiert einen sogenanntent *Event Handler*. Ein *Event Handler* ist eine Funktion, die genau ein Argument, nämlich einen `Event` erwartet. Dies entspricht genau unserer `addTodoItem`-Funktion. Somit können wir diese Funktion dort angeben.

Sobald nun der Knopf gedrückt wird, so wird unserer `addTodoItem()`-Funktion aufgerufen und ein neues Todo-Element wird hinzufgefügt.

Teste das Programm, indem du einen Text in das Feld eingibst und auf den Knopf drückst.


## Weitere Informationen

* Auf der Seite von Mozilla findest du die ganze [Liste von möglichen HTML-Elementen](https://developer.mozilla.org/de/docs/Web/HTML/Element). Viele dieser HTML-Elemente haben in Dart einen entsprechenden Typ (Klasse), wie zum Beispiel `InputElement`, `TextAreaElement`, `SpanElement` und `ListElement`.
* Weitere Informationen über die Verwendung von Dart mit HTML findest du in der [Tour of Dart](https://dart.dev/guides/libraries/library-tour#darthtml) (Englisch). Dort wird die ganze Dart-Sprache und wichtige Bibliotheken und Tools erklärt.


## Wie weiter?

Im [Teil 4](/de/library/dart-kanban/part4/) werden wir unser Kanban Board so erweitern, damit die Todos in die zwei anderen spalten verschoben werden können.
