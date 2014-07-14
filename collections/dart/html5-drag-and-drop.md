---
layout: article
title: Dart HTML5 Drag and Drop
date: 2014-03-25 00:00
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/projects/dart-html5-drag-and-drop/index.html
sidebars:
- header: Source Code
  body:
  - text: Dart HTML5 Drag and Drop (GitHub)
    link: https://github.com/marcojakob/dart-html5-dnd
    icon-css: fa fa-fw fa-github-alt
css:
- /assets/dart/html5-drag-and-drop/html5_dnd_example.css
javascript: 
- /assets/dart/html5-drag-and-drop/dart.min.js
- /assets/dart/html5-drag-and-drop/html5_dnd_example.dart.min.js
---

<div class="alert alert-danger">
  <p>
    <strong>Note:</strong> The following examples don't work at the moment. I'm currently redesigning the Drag and Drop library. 
  </p>
  <p>
    But, I've given up on trying to work with the quirks of native HTML5 Drag and Drop (and I recommend you do too! - except for some special cases like dragging files from and to the desktop). 
  </p>
  <p>
    If you want to be informed when the new library is available, tell me your <a href="https://tinyletter.com/code-makery" class="alert-link">email address here</a> and I'll let you know when it's ready.
  </p>
</div>

<p>
  Native HTML5 Drag and Drop is <a href="http://www.quirksmode.org/blog/archives/2009/09/the_html5_drag.html">not easy to work with</a>! The 
  <a href="https://github.com/marcojakob/dart-html5-dnd">Dart HTML5 Drag and Drop</a> library was created to use HTML5 Drag and Drop in a consistend way across all modern browsers. Here are some examples of how to use it.
</p>
  
<h3>Features</h3>
<p>
  <ul>
    <li>Make any HTML Element <code>draggable</code>.</li>
    <li>Create <code>dropzones</code> and connect them with <code>draggables</code>.</li>
    <li>Rearrange elements with <code>sortables</code> (similar to jQuery UI Sortable).</li>
    <li>Support for <code>touch events</code> on touch screen devices.</li>
    <li>Same functionality and API for IE9+, Firefox, Chrome and Safari.</li>
    <li>Uses fast native HTML5 Drag and Drop of the browser whenever possible.</li>
    <li>For browsers that do not support some features, the behaviour is emulated.
        This is the case for IE9 and partly for IE10 (when custom drag images are used).</li>
  </ul>
</p>

<p>
  API and Examples are inspired by <a href="http://farhadi.ir/projects/html5sortable/">HTML5 Sortable</a>, jQuery UI (<a href="http://api.jqueryui.com/draggable/">Draggable</a>, 
    <a href="http://api.jqueryui.com/droppable/">Droppable</a>, <a href="http://api.jqueryui.com/sortable/">Sortable</a>) and <a href="http://touchpunch.furf.com/">Touch Punch</a>.
</p>
  
<h3>Installation Instructions and Source Code</h3>
<p>
  <a href="https://github.com/marcojakob/dart-html5-dnd">Dart HTML5 Drag and Drop</a> on GitHub.
</p>
<p>You are welcome to contribute by reporting an <a href="https://github.com/marcojakob/dart-html5-dnd/issues">Issue</a> or <a href="https://help.github.com/articles/fork-a-repo">forking the repository</a>.
</p>

<p></p>

<h2>Draggable and Dropzone</h2>
<p>
  Drag the documents over the trash, and drop them.
</p>
<div id="draggable-dropzone" class="example-container">
  <div class="trash"></div>
  <img class="document" src="/assets/dart/html5-drag-and-drop/document.png">
  <img class="document" src="/assets/dart/html5-drag-and-drop/document.png">
  <img class="document" src="/assets/dart/html5-drag-and-drop/document.png">
  <img class="document" src="/assets/dart/html5-drag-and-drop/document.png">
</div><div class="example-code"><ul class="menu"><li><a href="#" tab="html" class="active">HTML</a><a href="#" tab="css">CSS</a><a href="#" tab="dart">Dart</a></li></ul><div class="content"><div tab="html" class="active">
<pre class="prettyprint lang-html">
&lt;div class="trash">&lt;/div>
&lt;img class="document" src="icons/document.png">
&lt;img class="document" src="icons/document.png">
&lt;img class="document" src="icons/document.png">
&lt;img class="document" src="icons/document.png">
</pre>
</div><div tab="css">
<pre class="prettyprint lang-css">
#draggable-dropzone .trash {
  background: url(icons/trash.png) top left no-repeat;
  /* ... */
}

#draggable-dropzone .trash.full {
  background: url(icons/trash.png) top right no-repeat;
}

#draggable-dropzone .dnd-over {
  opacity: 1;
}

#draggable-dropzone .dnd-dragging {
  opacity: 0.5;
}
</pre>
</div><div tab="dart">
<pre class="prettyprint lang-dart">
// Install draggables (documents).
DraggableGroup dragGroup = new DraggableGroup()
..installAll(queryAll('#draggable-dropzone .document'));

// Install dropzone (trash).
DropzoneGroup dropGroup = new DropzoneGroup()
..install(query('#draggable-dropzone .trash'))
..accept.add(dragGroup)
..onDrop.listen((DropzoneEvent event) {
  event.draggable.remove();
  event.dropzone.classes.add('full');
});
</pre>
</div></div></div>

<h2>Dragging Divs</h2>
<p>
  All HTML elements with the <em>draggable="true"</em> attribute can be dragged - 
  well...almost. Internet Explorer 9 does not support this attribute. To get the 
  same behaviour for all browsers, drag and drop events are emulated in IE9. 
  Try the following example in IE9.
</p>
<p>
  <strong>Note:</strong> The <em>draggable="true"</em> attribute is automatically
  added to draggables, so there is no need to set it in HTML.
</p>
<div id="dragging-divs" class="example-container">
  <div class="dragme" draggable="true">
    Drag me!  
  </div>
  <div class="dropzone example-box">
    Drag here!
  </div>
</div><div class="example-code"><ul class="menu"><li><a href="#" tab="html" class="active">HTML</a><a href="#" tab="css">CSS</a><a href="#" tab="dart">Dart</a></li></ul><div class="content"><div tab="html" class="active">
<pre class="prettyprint lang-html">
&lt;div class="dragme">
  Drag me!  
&lt;/div>
&lt;div class="dropzone example-box">
  Drag here!
&lt;/div>
</pre>
</div><div tab="css">
<pre class="prettyprint lang-css">
#dragging-divs .dnd-dragging {
  opacity: 0.5;
}

#dragging-divs .dnd-over {
  background: #d387ca;
}
</pre>
</div><div tab="dart">
<pre class="prettyprint lang-dart">
// Install draggable.
DraggableGroup dragGroup = new DraggableGroup()
..installAll(queryAll('#dragging-divs .dragme'));

// Install dropzone.
DropzoneGroup dropGroup = new DropzoneGroup()
..install(query('#dragging-divs .dropzone'))
..accept.add(dragGroup);
</pre>
</div></div></div>

<h2>Drop Effects</h2>
<p>
  The drop effect controls the feedback that is given when hovering over 
  the target element. The browser's cursor can take on one of the following
  forms: 'move', 'copy', 'link', and 'none'. If 'none' is used, dropping 
  on the target is not possible.
</p>
<div id="drop-effects" class="example-container">
  <div class="trash"></div>
  <div class="dragme move">move</div>
  <div class="dragme copy">copy</div>
  <div class="dragme link">link</div>
  <div class="dragme none">none</div>
</div>
<div class="example-code"><ul class="menu"><li><a href="#" tab="html" class="active">HTML</a><a href="#" tab="css">CSS</a><a href="#" tab="dart">Dart</a></li></ul><div class="content"><div tab="html" class="active">

<pre class="prettyprint lang-html">
&lt;div class="trash">&lt;/div>
&lt;div class="dragme move">move&lt;/div>
&lt;div class="dragme copy">copy&lt;/div>
&lt;div class="dragme link">link&lt;/div>
&lt;div class="dragme none">none&lt;/div>
</pre>

</div><div tab="css">
<pre class="prettyprint lang-css">
#drop-effects .trash {
  background: url(icons/trash.png) top left no-repeat;
  opacity: 0.7;
  /* ... */
}

#drop-effects .trash.full {
  background: url(icons/trash.png) top right no-repeat;
}

#drop-effects .dragme {
  background: url(icons/document.png) no-repeat;
  /* ... */
}

#drop-effects .dnd-over {
  opacity: 1;
}

#drop-effects .dnd-dragging {
  opacity: 0.5;
}
</pre>
</div><div tab="dart">
<pre class="prettyprint lang-dart">
// Install draggables.
DraggableGroup dragGroupMove = new DraggableGroup()
..dropEffect = DROP_EFFECT_MOVE
..install(query('#drop-effects .move'));

DraggableGroup dragGroupCopy = new DraggableGroup()
..dropEffect = DROP_EFFECT_COPY
..install(query('#drop-effects .copy'));

DraggableGroup dragGroupLink = new DraggableGroup()
..dropEffect = DROP_EFFECT_LINK
..install(query('#drop-effects .link'));

DraggableGroup dragGroupNone = new DraggableGroup()
..dropEffect = DROP_EFFECT_NONE
..install(query('#drop-effects .none'));

// Install dropzone.
DropzoneGroup dropGroup = new DropzoneGroup()
..install(query('#drop-effects .trash'))
..accept.addAll([dragGroupMove, dragGroupCopy, dragGroupLink, dragGroupNone])
..onDrop.listen((DropzoneEvent event) {
  event.draggable.remove();
  event.dropzone.classes.add('full');
});
</pre>
</div></div></div>

<h2>Custom Drag Images</h2>
<p>
  HTML5 drag and drop supports setting of custom drag images in place of the
  auto-generated drag overlay.
</p>
<p>
  IE9 and IE10 do not support this. To get the same behaviour, a custom drag 
  image is emulated: An image is manually created and moved together with
  the mouse (similar to what jQuery IU does).
</p>  
<div id="drag-images" class="example-container">
  <div class="dropzone example-box">
    Drag here!
  </div>
  <div class="dragme one" draggable="true">
    png at position [40,40]
  </div>
  <div class="dragme two" draggable="true">
    png at position [-20,-20]
  </div>
  <div class="dragme three" draggable="true">
    custom drawn canvas
  </div>
</div><div class="example-code"><ul class="menu"><li><a href="#" tab="html" class="active">HTML</a><a href="#" tab="css">CSS</a><a href="#" tab="dart">Dart</a></li></ul><div class="content"><div tab="html" class="active">
<pre class="prettyprint lang-html">
&lt;div class="dropzone example-box">
  Drag here!
&lt;/div>
&lt;div class="dragme one">
  png at position [40,40]
&lt;/div>
&lt;div class="dragme two">
  png at position [-20,-20]
&lt;/div>
&lt;div class="dragme three">
  custom drawn canvas
&lt;/div>
</pre>
</div><div tab="css">
<pre class="prettyprint lang-css">
#drag-images .dnd-dragging {
  opacity: 0.5;
}

#drag-images .dnd-over {
  background: #d387ca;
}
</pre>
</div><div tab="dart">
<pre class="prettyprint lang-dart">
ImageElement png = new ImageElement(src: 'icons/smiley-happy.png');
CanvasElement canvas = new CanvasElement();
var ctx = canvas.context2D
    ..fillStyle = "rgb(200,0,0)"
    ..fillRect(10, 10, 55, 50);
var dataUrl = canvas.toDataUrl("image/jpeg", 0.95);
//Create a new image element from the data URL.
ImageElement canvasImage = new ImageElement(src: dataUrl);

// Install draggables.
DraggableGroup dragGroupOne = new DraggableGroup(
    dragImageFunction: (Element draggable) => new DragImage(png, 40, 40))
..install(query('#drag-images .one'));

DraggableGroup dragGroupTwo = new DraggableGroup(
    dragImageFunction: (Element draggable) => new DragImage(png, -20, -20))
..install(query('#drag-images .two'));

DraggableGroup dragGroupThree = new DraggableGroup(
    dragImageFunction: (Element draggable) => new DragImage(canvasImage, 20, 20))
..install(query('#drag-images .three'));

// Install dropzone.
DropzoneGroup dropGroup = new DropzoneGroup()
..install(query('#drag-images .dropzone'))
..accept.addAll([dragGroupOne, dragGroupTwo, dragGroupThree]);
</pre>
</div></div></div>

<h2>Dropping on Nested Elements</h2>
<p>
  In its original form, HTML5 dragEnter and dragLeave events of an 
  element also fire when dragging over a child element. This makes it very 
  hard to distinguish important events from unimportant ones. The dart 
  html5_dnd library ensures that only the relevant events (entering or 
  leaving the main element) are fired.
</p>
<div id="nested-elements" class="example-container">
  <div class="dragme" draggable="true">
    Drag me!  
  </div>
  
  <div class="dropzone example-box">
    <div>
      <input value="Drag here!">
      <textarea rows="5">1 drag enter fired
2 drag leave fired
</textarea>
      <button>Button</button>
    </div>
  </div>
</div><div class="example-code"><ul class="menu"><li><a href="#" tab="html" class="active">HTML</a><a href="#" tab="css">CSS</a><a href="#" tab="dart">Dart</a></li></ul><div class="content"><div tab="html" class="active">
<pre class="prettyprint lang-html">
&lt;div class="dragme">
  Drag me!  
&lt;/div>

&lt;div class="dropzone example-box">
  &lt;div>
    &lt;input value="Drag here!">&lt;/input>
    &lt;textarea rows="5">&lt;/textarea>
    &lt;button>Button&lt;/button>
  &lt;/div>
&lt;/div>
</pre>
</div><div tab="css">
<pre class="prettyprint lang-css">
#nested-elements .dnd-dragging {
  opacity: 0.5;
}

#nested-elements .dragme {
  width: 64px;
  height: 64px;
  /* ... */
}
</pre>
</div><div tab="dart">
<pre class="prettyprint lang-dart">
TextAreaElement textarea = query('#nested-elements .dropzone textarea');
InputElement input = query('#nested-elements .dropzone input');
input.value = 'Drag here!';
textarea.text = '';
int enterLeaveCounter = 1;
int overCounter = 1;

// Install draggables.
DraggableGroup dragGroup = new DraggableGroup()
..install(query('#nested-elements .dragme'));

// Install dropzone.
DropzoneGroup dropGroup = new DropzoneGroup()
..install(query('#nested-elements .dropzone'))
..accept.add(dragGroup)
..onDragEnter.listen((DropzoneEvent event) {
  textarea.appendText('${enterLeaveCounter++} drag enter fired
');
  textarea.scrollTop = textarea.scrollHeight;
})
..onDragOver.listen((DropzoneEvent event) {
  input.value = '${overCounter++} drag over fired';
})
..onDragLeave.listen((DropzoneEvent event) {
  textarea.appendText('${enterLeaveCounter++} drag leave fired
');
  textarea.scrollTop = textarea.scrollHeight;
})
..onDrop.listen((DropzoneEvent event) {
  textarea.appendText('${enterLeaveCounter++} drop fired
');
  textarea.scrollTop = textarea.scrollHeight;
});
</pre>
</div></div></div>

<h2>Sortable List</h2>
<p>
  Reorder Elements. Notice the behaviour when dragging a smaller element
  over a bigger element.
</p>
<div id="sortable-list" class="example-container">
  <ul class="example-box">
    <li draggable="true">Item 1</li>
    <li draggable="true">Item 2</li>
    <li draggable="true">Item 3</li>
    <li class="higher" draggable="true">Item 4</li>
    <li draggable="true">Item 5</li>
    <li class="higher" draggable="true">Item 6</li>
  </ul>
</div><div class="example-code"><ul class="menu"><li><a href="#" tab="html" class="active">HTML</a><a href="#" tab="css">CSS</a><a href="#" tab="dart">Dart</a></li></ul><div class="content"><div tab="html" class="active">
<pre class="prettyprint lang-html">
&lt;ul class="example-box">
  &lt;li>Item 1&lt;/li>
  &lt;li>Item 2&lt;/li>
  &lt;li>Item 3&lt;/li>
  &lt;li class="higher">Item 4&lt;/li>
  &lt;li>Item 5&lt;/li>
  &lt;li class="higher">Item 6&lt;/li>
&lt;/ul>
</pre>
</div><div tab="css">
<pre class="prettyprint lang-css">
#sortable-list .dnd-placeholder {
  border: 1px dashed #CCC;
  background: none;  
}

#sortable-list li {
  cursor: move;
}
</pre>
</div><div tab="dart">
<pre class="prettyprint lang-dart">
SortableGroup sortGroup = new SortableGroup()
..installAll(queryAll('#sortable-list li'))
..onSortUpdate.listen((SortableEvent event) {
  // do something when user sorted the elements...
});

// Only accept elements from this section.
sortGroup.accept.add(sortGroup);
</pre>
</div></div></div>

<h2>Sortable Grid</h2>
<p>
  Grid behaviour is applied when elements are floated.
</p>
<div id="sortable-grid" class="example-container">
  <ul class="example-box grid">
    <li draggable="true">Item 1</li>
    <li draggable="true">Item 2</li>
    <li draggable="true">Item 3</li>
    <li draggable="true">Item 4</li>
    <li class="wider" draggable="true">Item 5</li>
    <li draggable="true">Item 6</li>
    <li class="higher" draggable="true">Item 7</li>
    <li draggable="true">Item 8</li>
  </ul>
</div><div class="example-code"><ul class="menu"><li><a href="#" tab="html" class="active">HTML</a><a href="#" tab="css">CSS</a><a href="#" tab="dart">Dart</a></li></ul><div class="content"><div tab="html" class="active">
<pre class="prettyprint lang-html">
&lt;ul class="example-box grid">
  &lt;li>Item 1&lt;/li>
  &lt;li>Item 2&lt;/li>
  &lt;li>Item 3&lt;/li>
  &lt;li>Item 4&lt;/li>
  &lt;li class="wider">Item 5&lt;/li>
  &lt;li>Item 6&lt;/li>
  &lt;li class="higher">Item 7&lt;/li>
  &lt;li>Item 8&lt;/li>
&lt;/ul>
</pre>
</div><div tab="css">
<pre class="prettyprint lang-css">
#sortable-grid .dnd-placeholder {
  border: 1px dashed #CCC;
  background: none;  
}

#sortable-grid li {
  cursor: move;
}
</pre>
</div><div tab="dart">
<pre class="prettyprint lang-dart">
SortableGroup sortGroup = new SortableGroup()
..installAll(queryAll('#sortable-grid li'))
..isGrid = true;

// Only accept elements from this section.
sortGroup.accept.add(sortGroup);
</pre>
</div></div></div>

<h2>Only Allow Drag on Handles</h2>
<p>
  Items can only be dragged with the handle.
</p>
<div id="sortable-list-handles" class="example-container">
  <ul class="example-box">
    <li draggable="true"><span>::</span> Item 1</li>
    <li draggable="true"><span>::</span> Item 2</li>
    <li draggable="true"><span>::</span> Item 3</li>
    <li draggable="true"><span>::</span> Item 4</li>
    <li draggable="true"><span>::</span> Item 5</li>
    <li draggable="true"><span>::</span> Item 6</li>
  </ul>
</div><div class="example-code"><ul class="menu"><li><a href="#" tab="html" class="active">HTML</a><a href="#" tab="css">CSS</a><a href="#" tab="dart">Dart</a></li></ul><div class="content"><div tab="html" class="active">
<pre class="prettyprint lang-html">
&lt;ul class="example-box">
  &lt;li>&lt;span>::&lt;/span> Item 1&lt;/li>
  &lt;li>&lt;span>::&lt;/span> Item 2&lt;/li>
  &lt;li>&lt;span>::&lt;/span> Item 3&lt;/li>
  &lt;li>&lt;span>::&lt;/span> Item 4&lt;/li>
  &lt;li>&lt;span>::&lt;/span> Item 5&lt;/li>
  &lt;li>&lt;span>::&lt;/span> Item 6&lt;/li>
&lt;/ul>
</pre>
</div><div tab="css">
<pre class="prettyprint lang-css">
#sortable-list-handles .dnd-placeholder {
  border: 1px dashed #CCC;
  background: none;  
}

#sortable-list-handles span {
  cursor: move;
}
</pre>
</div><div tab="dart">
<pre class="prettyprint lang-dart">
SortableGroup sortGroup = new SortableGroup(handle: 'span')
..installAll(queryAll('#sortable-list-handles li'));

// Only accept elements from this section.
sortGroup.accept.add(sortGroup);
</pre>
</div></div></div>


<h2>Prevent Drag on Some Elements</h2>
<p>
  Starting a drag can be prevented on specified elements. In this example, 
  drag can only be started on the outer div.
</p>
<p>
  By default, elements of type input, textarea, button, select, or option 
  are not draggable.
</p>
<div id="cancel-drag" class="example-container">
  <div class="dragme example-box">
    Drag me!
    <div class="nodrag">No drag here!</div>
    <textarea>No drag here!</textarea>
    <button>No drag here!</button>
  </div>
</div>
<div class="example-code"><ul class="menu"><li><a href="#" tab="html" class="active">HTML</a><a href="#" tab="css">CSS</a><a href="#" tab="dart">Dart</a></li></ul><div class="content"><div tab="html" class="active">
<pre class="prettyprint lang-html">
&lt;div class="dragme example-box">
  Drag me!
  &lt;div class="nodrag">No drag here!&lt;/div>
  &lt;textarea>No drag here!&lt;/textarea>
  &lt;button>No drag here!&lt;/button>
&lt;/div>
</pre>
</div><div tab="css">
<pre class="prettyprint lang-css">
#cancel-drag .dnd-dragging {
  opacity: 0.5;
}

#cancel-drag .dragme {
  padding: 5px;
  border: 1px solid #aaa;
  background: #acf;
  cursor: move;
}

#cancel-drag .dragme * {
  cursor: default;
}
</pre>
</div><div tab="dart">
<pre class="prettyprint lang-dart">
// Install draggable.
DraggableGroup dragGroup = new DraggableGroup(
    cancel: 'textarea, button, .nodrag')
..install(query('#cancel-drag .dragme'));
</pre>
</div></div></div>


<h2>Two Sortable Groups</h2>
<p>
  Two sortable groups that are connected with each other. 
</p>
<p>
  Elements in the right group display an icon when dragged, elements in the 
  left group display the dragged element. Whenever an element changes group 
  it is uninstalled from the previous group and installed in the new group.
</p>
<div id="sortable-two-groups" class="example-container">
  <ul class="example-box group1">
    <li draggable="true">Item 1</li>
    <li draggable="true">Item 2</li>
    <li draggable="true">Item 3</li>
    <li draggable="true">Item 4</li>
    <li draggable="true">Item 5</li>
    <li draggable="true">Item 6</li>
  </ul>
  <ul class="example-box group2">
    <li class="other" draggable="true">Item 1</li>
    <li class="other" draggable="true">Item 2</li>
    <li class="other" draggable="true">Item 3</li>
    <li class="other" draggable="true">Item 4</li>
    <li class="other" draggable="true">Item 5</li>
    <li class="other" draggable="true">Item 6</li>
  </ul>
</div><div class="example-code"><ul class="menu"><li><a href="#" tab="html" class="active">HTML</a><a href="#" tab="css" class="">CSS</a><a href="#" tab="dart" class="">Dart</a></li></ul><div class="content"><div tab="html" class="active">
<pre class="prettyprint lang-html">
&lt;ul class="example-box list1">
  &lt;li>Item 1&lt;/li>
  &lt;li>Item 2&lt;/li>
  &lt;li>Item 3&lt;/li>
  &lt;li>Item 4&lt;/li>
  &lt;li>Item 5&lt;/li>
  &lt;li>Item 6&lt;/li>
&lt;/ul>
&lt;ul class="example-box list2">
  &lt;li class="other">Item 1&lt;/li>
  &lt;li class="other">Item 2&lt;/li>
  &lt;li class="other">Item 3&lt;/li>
  &lt;li class="other">Item 4&lt;/li>
  &lt;li class="other">Item 5&lt;/li>
  &lt;li class="other">Item 6&lt;/li>
&lt;/ul>
</pre>
</div><div tab="css" class="">
<pre class="prettyprint lang-css">
#sortable-two-groups .dnd-placeholder {
  border: 1px dashed #CCC;
  background: none;  
}

#sortable-two-groups .dnd-dragging {
  opacity: 0.5;
}

#sortable-two-groups li {
  cursor: move;
}

#sortable-two-groups .group1 {
  float: left;
  width: 150px;
}

#sortable-two-groups .group2 {
  float: right;
  width: 150px;
}
</pre>
</div><div tab="dart">
<pre class="prettyprint lang-dart">
ImageElement png = new ImageElement(src: 'icons/smiley-happy.png');
  
SortableGroup sortGroup1 = new SortableGroup()
..installAll(queryAll('#sortable-two-groups .group1 li'))
..onSortUpdate.listen((SortableEvent event) {
  event.originalGroup.uninstall(event.draggable);
  event.newGroup.install(event.draggable);
});

SortableGroup sortGroup2 = new SortableGroup(
    dragImageFunction: (Element draggable) => new DragImage(png, 5, 5))
..installAll(queryAll('#sortable-two-groups .group2 li'))
..onSortUpdate.listen((SortableEvent event) {
  event.originalGroup.uninstall(event.draggable);
  event.newGroup.install(event.draggable);
});

// Only accept elements from this section.
sortGroup1.accept.addAll([sortGroup1, sortGroup2]);
sortGroup2.accept.addAll([sortGroup1, sortGroup2]);
</pre>
</div></div></div>

<div style="clear:both"></div>