---
layout: post
title: JavaFX 8 TableView Cell Renderer
date: 2014-05-02 00:00
slug: javafx-8-tableview-cell-renderer
description: "In this post I will show how to customize the rendering of a JavaFX TableView."
image: /assets/blog/14-05-02-javafx-8-tableview-cell-renderer/tableview-cell-renderer.png
published: true
prettify: true
comments: true
tags:
- Java
- JavaFX
---

In this post I will show how to **customize the rendering of a JavaFX TableView**. This is an updated version of the [JavaFX 2 TableView Cell Renderer](/blog/javafx-2-tableview-cell-renderer/) article. Compared to the old version, we can now use some Java 8 language features like lambda expressions and the new Date and Time API.  

Following is how the result will look like. 

![TableView Cell Renderer](/assets/blog/14-05-02-javafx-8-tableview-cell-renderer/tableview-cell-renderer.png)

* The *Birthday* column is a formatted `LocalDate` object. 
* All birthdays in March receive a yellow background and a brown text color.


*****

## How a Table Cell is Rendered

Each table cell will receive an object, in our case it is an instance of `Person`. To do the rendering, the cell will need a [Cell Value Factory](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableColumn.html#setCellValueFactory-javafx.util.Callback-) and a [Cell Factory](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableColumn.html#cellFactoryProperty):


### Cell Value Factory

The cell must know which part of `Person` it needs to display. For all cells in the *birthday column* this will be the `Person`s `birthday` value.

This is our birthday column:

<pre class="prettyprint lang-java">
private TableColumn&lt;Person, LocalDate> birthdayColumn;
</pre>

And later during initialization, we'll set the *Cell Value Factory*:

<pre class="prettyprint lang-java">
birthdayColumn.setCellValueFactory(
      cellData -> cellData.getValue().birthdayProperty());
</pre>

So far nothing too fancy.


### Cell Factory

Once the cell has the value, it must know how to display that value. In our case, the birthday's `LocalDate` value must be formatted and colored depending on the month.

<pre class="prettyprint lang-java">
DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

// Custom rendering of the table cell.
birthdayColumn.setCellFactory(column -> {
    return new TableCell&lt;Person, LocalDate>() {
        @Override
        protected void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
                setStyle("");
            } else {
                // Format date.
                setText(myDateFormatter.format(item));

                // Style all dates in March with a different color.
                if (item.getMonth() == Month.MARCH) {
                    setTextFill(Color.CHOCOLATE);
                    setStyle("-fx-background-color: yellow");
                } else {
                    setTextFill(Color.BLACK);
                    setStyle("");
                }
            }
        }
    };
});
</pre>


The *Cell Factory* contains some complicated stuff (*Lambda expression*, *Generics* and *Anonymous Inner Classes*). Don't worry too much about all this. Just focus on the important part which is the `updateItem(...)` method. 

The `updateItem(...)` method gets called whenever the cell should be rendered. We receive the `LocalDate` item that must be rendered. If the item is empty we reset the cell. Otherwise, we format the item and set the text of the cell. Depending on the month, we also set some color.



*****


## Rendering ListView and TreeView Cells

Note that the JavaFX [`ListView`](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/ListView.html) and [`TreeView`](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TreeView.html) are rendered in a very similar way.


* * *

## Download

Download the complete source of the [TableView Cell Renderer Example](/assets/blog/14-05-02-javafx-8-tableview-cell-renderer/tableview-cell-renderer.zip).
