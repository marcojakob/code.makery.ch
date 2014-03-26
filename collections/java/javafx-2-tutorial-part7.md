---
layout: article
title: "JavaFX 2 Tutorial - Part 7: Deployment with e(fx)clipse"
date: 2012-12-18 01:00
updated: 2013-05-22 00:00
published: true
prettify: true
comments: 
  shortname: edumakery
  identifier: http://edu.makery.ch/blog/2012/12/18/javafx-tutorial-addressapp-7/
sidebars:
- header: "Articles in this Series"
  paging: true
  body:
  - text: "Introduction"
    link: /java/javafx-2-tutorial-intro
    intro: true
  - text: "Part 1: Scene Builder"
    link: /java/javafx-2-tutorial-part1
  - text: "Part 2: Model and TableView"
    link: /java/javafx-2-tutorial-part2
  - text: "Part 3: Interacting with the User"
    link: /java/javafx-2-tutorial-part3
  - text: "Part 4: CSS Styling"
    link: /java/javafx-2-tutorial-part4
  - text: "Part 5: Storing Data as XML"
    link: /java/javafx-2-tutorial-part5
  - text: "Part 6: Statistics Chart"
    link: /java/javafx-2-tutorial-part6
  - text: "Part 7: Deployment with e(fx)clipse"
    link: /java/javafx-2-tutorial-part7
    active: true
- header: "Download Sources"
  body:
  - text: Source of Part 7 (Eclipse Project)
    link: /assets/java/javafx-2-tutorial-part7/addressapp-part-7.zip
    icon-css: fa fa-fw fa-download
---

*Update Feb 11th, 2013: New instructions for Deployment on Mac OS. Thank you Eskil for providing me with this information!*

*Update May 22nd, 2013: Updated step 3 and step 4 for e(fx)clipse plugin version 0.8.1.*

![Screenshot AddressApp Part 7](/assets/java/javafx-2-tutorial-part7/addressapp01.png)

I thought I'd write one last part of this tutorial series to show how to deploy (i.e. package and publish) the AddressApp.

Download example AddressApp as 

* Windows exe installer: [AddressApp-0.7.exe](https://www.dropbox.com/s/jk5ilt3p47c674z/AddressApp-0.7.exe).
* MacOS dmg drag-and-drop installer: [AddressApp.dmg](https://www.dropbox.com/s/cfpr4bh25u8qsmz/AddressApp.dmg) - Thank you Eskil for providing this!

* * *

## Topics in Part 7

* Deploying our JavaFX application as **Native Package** with e(fx)clipse

* * *

## What is Deployment

Deplyoment is the process of packaging and delivering software to the user. This is a crucial part of software development since it's the first contact a user has with our software.

Java advertises with the slogan **Write Once, Run Anywhere** to illustrate the *cross-platform* benefits of the Java language. Ideally, this means that our Java application can be run on any device equipped with a Java virtual machine (JVM).

In the past, the user experience for installing a Java application hasn't always been smooth. If the user didn't have the required Java version on his system, he had to be directed to install it first. This lead to some difficulties, e.g. need for admin rights, compatibility issues between Java versions, etc.

Fortunately, JavaFX 2 provides a new deployment option called **Native Packaging** (also called Self-Contained Application Package). A native package is a bundle containing both your application code and the (platform-specific) Java Runtime. 

The official JavaFX documentation by Oracle contains an extensive guide for all possible [JavaFX deployment options](http://docs.oracle.com/javafx/2/deployment/jfxpub-deployment.htm). 

In this post I will show how to create a **Native Package** with with Eclipse and the [**e(fx)clipse plugin**](http://www.eclipse.org/efxclipse/). My current e(fx)clipse version is **0.1.1**.

* * * 

## Create a Native Package

The goal is to create a self-contained application in a single folder on the user's computer. Here is how it will look like for our AddressApp (on Windows):

![AddressApp Native Package](/assets/java/javafx-2-tutorial-part7/addressapp02.png)

The `app` folder contains our application data and the `runtime` folder contains the platform-specific Java runtime.

To make it even more comfortable for the user, we'll provide an installer:

* A `.exe` file installer on windows
* A `dmg` (drag and drop) installer for MacOS.

The e(fx)clipse plugin will help us generate the native package and installer.


### Step 1 - eclipse.ini

**Note**: Step 1 might not be necessary with e(fx)clipse 0.8.0 and above. 

JavaFX uses a tool called [Ant](http://ant.apache.org/) to build and package the application. This tool is already included in Eclipse. As Ant depends on the JDK we need to make shure Eclipse itself runs with the JDK (not the JRE).

1. Close Eclipse.
2. Find the folder of your Eclipse installation and open the file `eclipse.ini` in a text editor. This file contains Eclipse startup settings. On Mac OS X, eclipse.ini can be found by right-clicking Eclipse.app and selecting "Show package contents". The file is located under Contents/MacOS.
3. After the line `openFile` add `-vm` and then specify your **jdk installation directory**. The end of the file should now look like this:


##### eclipse.ini

<pre class="prettyprint lang-xml">
openFile
-vm
C:\Program Files\Java\jdk1.7.0_09\bin\javaw.exe
-vmargs
-Xms40m
-Xmx512m
</pre>

**Mac OS**: For Mac OS, path could be something like `/Library/Java/JavaVirtualMachines/jdk1.7.0_07.jdk/Contents/Home/bin/java` (In my version the javaw.exe does not exist but I specified "java" instead which seemed no problem)


### Step 2 - Installer Icons

We would like to have some nice icons for our installer:

1. Download [AddressApp.ico](/assets/java/javafx-2-tutorial-part7/AddressApp.ico), [AddressApp-setup-icon.bmp](/assets/java/javafx-2-tutorial-part7/AddressApp-setup-icon.bmp) and [AddressApp.icns](/assets/java/javafx-2-tutorial-part7/AddressApp.icns).
2. Copy the three icons to the project root of your AddressApp project in Eclipse.

![Installer Icons](/assets/java/javafx-2-tutorial-part7/addressapp03.png)


### Step 3 - Edit build.fxbuild

The file `build.fxbuild` is used by e(fx)clipse to generate a file that will be used by the Ant build tool. If you don't have a `build.fxbuild` file, create a new *Java FX Project* in Eclipse and copy the generated file.

1. Open `build.fxbuild` from your project root.
2. Fill out all the fields containing a star. *For MacOS: Do not use spaces in Application title as this seems to cause a problem.*    
![build.fxbuild](/assets/java/javafx-2-tutorial-part7/addressapp04.png)
3. As **Packaging Format** choose `exe` for Windows, `dmg` for MacOS, and `rpm` for Linux.
4. Click on the link `Generate ant build.xml only` (found on the right side).
5. Verify that a new `build` folder and a file `build.xml` is created.


### Step 4 - Edit build.xml to include icons

E(fx)clipse has generated a file `build/build.xml` which is ready to be executed by **Ant**. Our installer icons and resource icons just won't work. If you don't want icons you may skip this step. 

As e(fx)clipse can't be told (yet?) to include additional resources like our `resources` folder and the few installer icons we've added above, we have to manually edit the `build.xml`:

Open `build.xml` and find the path `fxant`. Add one line for the `${basedir}` (will make our installer icons available):


##### build.xml

<pre class="prettyprint lang-xml">
&lt;path id="fxant"&gt;
  &lt;filelist&gt;
    &lt;file name="${java.home}\..\lib\ant-javafx.jar"/&gt;
    &lt;file name="${java.home}\lib\jfxrt.jar"/&gt;
    &lt;file name="${basedir}"/&gt;
  &lt;/filelist&gt;
&lt;/path&gt;
</pre>    

Find the following block further down in the file:

<pre class="prettyprint lang-xml">
&lt;fx:resources id="appRes"&gt;
    &lt;fx:fileset dir="dist" includes="AddressApp.jar"/&gt;
    &lt;fx:fileset dir="dist" includes="libs/*"/&gt;
&lt;/fx:resources&gt; 
</pre>

**Replace** the four lines obove with the following code:

<pre class="prettyprint lang-xml">
&lt;mkdir dir="dist/resources" /&gt;
&lt;copy todir="dist/resources" &gt;
    &lt;fileset dir="../resources" /&gt;
&lt;/copy&gt;

&lt;mkdir dir="package" /&gt;

&lt;!-- Icons only for Windows --&gt;
&lt;mkdir dir="package/windows" /&gt;
&lt;copy todir="package/windows"&gt;
    &lt;fileset dir=".."&gt;
        &lt;include name="AddressApp.ico" /&gt;
        &lt;include name="AddressApp-setup-icon.bmp" /&gt;
    &lt;/fileset&gt;
&lt;/copy&gt;

&lt;!-- Icons only for MacOS --&gt;
&lt;mkdir dir="package/macosx" /&gt;
&lt;copy todir="package/macosx"&gt;
    &lt;fileset dir=".."&gt;
        &lt;include name="AddressApp.icns" /&gt;
    &lt;/fileset&gt;
&lt;/copy&gt;		

&lt;fx:resources id="appRes"&gt;
    &lt;fx:fileset dir="dist" includes="AddressApp.jar"/&gt;
    &lt;fx:fileset dir="dist" includes="libs/*"/&gt;
    &lt;fx:fileset dir="dist" includes="resources/**"/&gt;
&lt;/fx:resources&gt; 
</pre>


### Step 5 (WINDOWS) - Windows exe Installer

![AddressApp on Windows](/assets/java/javafx-2-tutorial-part7/addressapp06.png)

With **Inno Setup** we can create a Windows Installer of our application as a single `.exe` file. The resulting `.exe` will perform a user level installation (no admin permissions required). A shortcut will be created (menu or desktop)

1. Download [Inno Setup 5 or later](http://www.jrsoftware.org/isdl.php). Install Inno Setup on your computer. Our Ant script will use it to automatically generate the installer.
2. Tell Windows about the installation path to Inno Setup (e.g. `C:\Program Files (x86)\Inno Setup 5`): Add it to the `Path` variable in your windows environment variables. If you don't know where to find it, read [How to set the path and environment variables in Windows](http://www.computerhope.com/issues/ch000549.htm).


### Step 5 (MAC) - MacOS dmg Installer

![AddressApp on Mac](/assets/java/javafx-2-tutorial-part7/addressapp07.png)

To create a Mac OS `dmg` drag-and-drop installer, no additional tool is required.

Note: For the installer image to work it must have exactly the same name as the application.


### Step 5 (LINUX etc.) - Linux rpm Installer ###
For other packaging options (`msi` for windows, `rpm` for Linux) see this native packaging [blog post](https://blogs.oracle.com/talkingjavadeployment/entry/native_packaging_for_javafx) or this [oracle documentation](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm#A1324980).


### Step 6 - Run build.xml ###
As a final step, we run the `build.xml` with Ant: *Right-click* on the `build.xml` file | *Run As* | *Ant Build*.

![Run Ant Build](/assets/java/javafx-2-tutorial-part7/addressapp08.png)

The building **will take a while** (about 1 minute on my computer).

If everything was successful, you should find the native bundle in the folder `build/deploy/bundles`:

* Windows: The file `AddressApp-1.0.exe` can be used as a single file to install the application. This installer will copy the bundle to `C:/Users/[yourname]/AppData/Local/AddressApp`.
* Hint: You could also use the subfolder `build/deploy/bundles/AddressApp` to deploy the application as a `.zip` file.



### What's Next?

I hope this tutorial was a help for you to get started with JavaFX and you'll be able to write your own JavaFX project from here. I might add some further JavaFX blog posts outside of this tutorial series, we'll see...

I appreciate any feedback. Feel free to write comments if you have any suggestions or if something was unclear.

