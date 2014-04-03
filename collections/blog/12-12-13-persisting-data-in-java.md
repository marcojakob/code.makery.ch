---
layout: post
title: Persisting Data in Java
date: 2012-12-13 13:00
slug: persisting-data-in-java
published: true
prettify: true
comments: true
tags:
- Java
---


Almost every application will need some way to persistently store data. There are many different ways to accomplish that. We'll start with a simple way to save a small amount of data and continue to more advanced techniques. 

![Floppy](/assets/blog/12-12-13-persisting-data-in-java/floppy.png)

Some of the techniques (Preferences, XML and reading/writing Files) described here were already used in the [JavaFX 2 Tutorial Part 5](/java/javafx-2-tutorial-part5/). 


* * *

## Preferences

With the class [`Preferences`](http://docs.oracle.com/javase/7/docs/api/index.html?java/util/prefs/Preferences.html) of the standard Java library we can save some small values. Typically, you'd save some simple user preferences in it like path to the *last opened file*. 

Depending on the operating system, the `Preferences` are saved in different places (e.g. the registry file in Windows).

* `Preferences` example in [JavaFX 2 Tutorial Part 5](/java/javafx-2-tutorial-part5/).
* Instructions in *German* in the book [Java ist auch eine Insel](http://openbook.galileocomputing.de/javainsel/javainsel_11_009.html#dodtp29221705-11a7-4fe0-8f23-bfb46d58ff59)


* * *

## Saving Files

There are various possibilities to load and save a file in Java.

* An example is the `FileUtil` helper class in [JavaFX 2 Tutorial Part 5](/java/javafx-2-tutorial-part5).
* The official Java [File I/O Tutorial](http://docs.oracle.com/javase/tutorial/essential/io/fileio.html).

Simple text files can directly be saved to the disk. You construct a String (from objects) and save it using the means described above. But if you later want to read from the text files and use the information in your application, you might have some difficulties. You would have to manually parse the elements from one big unstructured String. Then, to get objects again, you would have to create new objects and fill them with the information from the String.

That's why it helps to save data in some structured form like XML.


* * *

## XML with XStream

XStream is a simple library to serialize objects to XML and back again. Serializing means that, given an object (which may contain a reference to other objects), XStream will create a single XML-String representing this object(s). The XML-String can then be saved to a file. XStream will later accept this XML-String to reproduce the object(s) again.

* XML example in [JavaFX 2 Tutorial Part 5](/java/javafx-2-tutorial-part5/).
* Official [XStream Website](http://xstream.codehaus.org/) with a good [Two Minute Tutorial](http://xstream.codehaus.org/tutorial.html).

When data gets more complex then a database instead of XML is appropriate.


* * *

## Java Database (also called Derby)

Derby is an Open Source Database completely written in Java. The advantage of a pure Java database is that it can be embedded with the Java program (as opposed to being run as a separate, standalone server). Derby is based on the SQL Standard.

* Official [Apache Derby Website](http://db.apache.org/derby/) containing downloads, manuals, etc.
* [Derby Tutorial](http://db.apache.org/derby/papers/DerbyTut/embedded_intro.html) with an embedded example. **Note:** You will find the example `SimpleApp` inside the Derby zip under `demo/programs/simple/`.


### Eclipse Data Tools Platform

With the Eclipse *Data Tools Platform* plugin you can manage your database. This is very practical, especially to view the data that has been saved to the database.

* [Eclipse Data Tools Platform Tutorial](http://www.vogella.de/articles/EclipseDataToolsPlatform/article.html)


* * *

## Database with a Mapper Tool and MySQL (Advanced)

Tools like [Hibernate](http://www.hibernate.org/) and [EclipseLink](http://www.eclipse.org/eclipselink/) help to fill the gap between the relational data in the database and objects (see [Object-relational impedance mismatch](http://de.wikipedia.org/wiki/Object-relational_impedance_mismatch)).

* [Hibernate Tutorial](http://www.javatips.net/blog/2011/12/hibernate-annotations-tutorial)
* Other [Hibernate Tutorials](http://www.roseindia.net/hibernate/hibernate4/index.shtml) with more information.
* An extensive [Hibernate video tutorial series](http://www.youtube.com/watch?v=Yv2xctJxE-w&list=PL4AFF701184976B25&index=1)
* [EclipseLink Tutorial](http://www.vogella.com/articles/JavaPersistenceAPI/article.html) - also using Derby Embedded Database (could be replaced by MySQL)