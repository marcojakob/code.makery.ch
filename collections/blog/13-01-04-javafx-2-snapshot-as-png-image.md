---
layout: post
title: JavaFX 2 Snapshot as PNG Image
date: 2013-01-04 00:00
slug: javafx-2-snapshot-as-png-image
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2013/01/04/javafx-snapshot-as-png-image/
tags:
- Java
- JavaFX
---

JavaFX 2.2 and above provides a convenient **screenshot** feature. It takes a snapshot of any node or scene.

The following method saves the `barChart` node as a `png` image:

<pre class="prettyprint lang-java">
@FXML
public void saveAsPng() {
	WritableImage image = barChart.snapshot(new SnapshotParameters(), null);
	
	// TODO: probably use a file chooser here
	File file = new File("chart.png");
	
    try {
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
    } catch (IOException e) {
    	// TODO: handle exception here
    }
}
</pre>

Note: You could test this code with our **AddressApp** example (see download in [AddressApp Tutorial Part 7](/java/javafx-2-tutorial-part7/)). Just add the `saveAsPng()` method to the `BirthdayStatisticsController` class and call the method through some action (e.g. a new button).