---
layout: post
title: JavaFX 2 Scene Builder and e(fx)clipse Updates
date: 2013-01-03 21:00
slug: javafx-2-scene-builder-and-efxclipse-updates
published: true
prettify: false
comments: false
tags:
- Java
- JavaFX
---

I'm excited about the quick reaction to bug reports by the JavaFX developers at Oracle! I've reported an (annoying) Scene Builder [bug](http://javafx-jira.kenai.com/browse/DTL-5402) at the end of November and it was already fixed in a new Scene Builder version in January.

## Scene Builder Update

The Scene Builder bug forced us to delete and then reselect the controller class every time we reopened a `fxml` file in Scene Builder. This is fixed in `Scene Builder 1.1 beta 17` and above. So **I recommend you update to the newest version**:

* Go to [JavaFX Scene Builder Page](http://www.oracle.com/technetwork/java/javafx/tools/index.html) and download the newest **JavaFX Scene Builder 1.1 Early Access** edition.

I've updated the AddressApp Tutorial [Part III](/java/javafx-2-tutorial-part3), [Part V](/java/javafx-2-tutorial-part5) and [Part VI](/java/javafx-2-tutorial-part6) where I mentioned the bug.


## e(fx)clipse Update

The (very helpful) JavaFX plugin for Eclipse has been updated to [version 0.8.0](http://tomsondev.bestsolution.at/2013/01/06/efxclipse-0-8-0-released/).

As Tom Schindl, the author of e(fx)clipse, mentioned in a comment to [AddressApp Tutorial Part VII](/java/javafx-2-tutorial-part7), we might not need to explicitly start Eclipse with a JDK to do the deployment (Step 1 in the tutorial).