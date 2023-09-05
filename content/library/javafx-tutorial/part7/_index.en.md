+++
title = "Part 7: Deployment"
date = 2014-05-10
updated = 2015-03-12
description = "How to deploy a JavaFX application as native package. Create an installer for Windows, MacOS, or Linux."
image = "addressapp-macos.png"
prettify = true
# comments = true
commentsIdentifier = "/java/javafx-8-tutorial-part7/"
aliases = [
  "/java/javafx-8-tutorial-part7/",
  "/library/javafx-8-tutorial/part7/",
  "/library/javafx-2-tutorial/part7/"
]
weight = 7

[[sidebars]]
header = "Download Sources"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Part 7 as Eclipse Project <em>(requires at least JDK 8u40)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-7.zip"
+++

![Screenshot AddressApp Part 7](addressapp-part7.png)

I thought I'd write one last part of this tutorial series to show how to deploy (that means package and publish) the AddressApp.


*****

## Topics in Part 7

* Deploying our JavaFX application as **Native Package** with e(fx)clipse


*****

## What is Deployment

Deplyoment is the process of packaging and delivering software to the user. This is a crucial part of software development since it's the first contact a user has with our software.

Java advertises with the slogan **Write Once, Run Anywhere** to illustrate the *cross-platform* benefits of the Java language. Ideally, this means that our Java application can be run on any device equipped with a Java virtual machine (JVM).

In the past, the user experience for installing a Java application hasn't always been smooth. If the user didn't have the required Java version on his system, he had to be directed to install it first. This lead to some difficulties, e.g. need for admin rights, compatibility issues between Java versions, etc.

Fortunately, JavaFX provides a new deployment option called **Native Packaging** (also called Self-Contained Application Package). A native package is a bundle containing both your application code and the (platform-specific) Java Runtime. 

The official JavaFX documentation by Oracle contains an extensive guide for all possible [JavaFX deployment options](http://docs.oracle.com/javafx/2/deployment/jfxpub-deployment.htm). 

In this post I will show how to create a **Native Package** with Eclipse and the [**e(fx)clipse plugin**](http://www.eclipse.org/efxclipse/).


*****

## Create a Native Package

The goal is to create a self-contained application in a single folder on the user's computer. Here is how it will look like for our AddressApp (on Windows):

![AddressApp Native Package](native-package.png)

The `app` folder contains our application data and the `runtime` folder contains the platform-specific Java runtime.

To make it even more comfortable for the user, we'll provide an installer:

* A `exe` file installer on windows
* A `dmg` (drag and drop) installer for MacOS.

The e(fx)clipse plugin will help us generate the native package and installer.


### Step 1 - Edit build.fxbuild

The file `build.fxbuild` is used by e(fx)clipse to generate a file that will be used by the Ant build tool. (If you don't have a `build.fxbuild` file, create a new *Java FX Project* in Eclipse and copy the generated file.)

1. Open `build.fxbuild` from your project root.

2. Fill out all the fields containing a star. *For MacOS: Do not use spaces in Application title as this seems to cause a problem.*    
![fxbuild settings](fxbuild-settings.png)

3. As **Packaging Format** choose `exe` for Windows, `dmg` for MacOS, or `rpm` for Linux.

4. Click on the link `Generate ant build.xml only` (found on the right side).   
![generate ant build](generate-ant-build.png)

5. Verify that a new `build` folder and a file `build.xml` is created.


### Step 2 - Add Installer Icons

We would like to have some nice icons for our installer:

* [AddressApp.ico](AddressApp.ico) for the installer file icon
* [AddressApp-setup-icon.bmp](AddressApp-setup-icon.bmp) for the installer splash screen icon
* [AddressApp.icns](AddressApp.icns) for the mac installer icon
* [AddressApp-volume.icns](AddressApp-volume.icns) for the mac desktop icon


1. Create the following subfolders in the `build` folder:
  * `build/package/windows` (only used for windows)
  * `build/package/macosx` (only used for macos)
2. Copy the corresponding icons from above into those subfolders. It should look something like this now:   
![Installer Icons](installer-icons.png)
3. **Important**: The name of the icons must exactly match the **Application title** you specified in `build.fxbuild`:
  * `YourAppTitle.ico`
  * `YourAppTitle-setup-icon.bmp`
  * `YourAppTitle.icns`
  * `YourAppTitle-volume.icns`


### Step 3 - Add Resources

Our `resources` folder isn't copied automatically. We must manually add it to the build directory:

1. Create the following subfolder in the `build` folder:
  * `build/dist`   
2. Copy the `resources` folder (containing our application images) into `build/dist`.    
![Build Resources](build-resources.png)


### Step 4 - Edit build.xml to include icons

E(fx)clipse has generated a file `build/build.xml` which is ready to be executed by **Ant**. Our installer icons and resource images just won't work. 

As e(fx)clipse can't be told (yet?) to include additional resources like our `resources` folder and the few installer icons we've added above, we have to manually edit the `build.xml`:

Open `build.xml` and find the path `fxant`. Add one line for the `${basedir}` (will make our installer icons available):


##### build.xml - add "basedir"

<pre class="prettyprint lang-xml">
&lt;path id="fxant"&gt;
  &lt;filelist&gt;
    &lt;file name="${java.home}\..\lib\ant-javafx.jar"/&gt;
    &lt;file name="${java.home}\lib\jfxrt.jar"/&gt;
    <mark>&lt;file name="${basedir}"/&gt;</mark>
  &lt;/filelist&gt;
&lt;/path&gt;
</pre>    


Find the block `fx:resources id="appRes"` further down in the file. Add one line for our `resources`:

##### build.xml - add "resources"

<pre class="prettyprint lang-xml">
&lt;fx:resources id="appRes"&gt;
    &lt;fx:fileset dir="dist" includes="AddressApp.jar"/&gt;
    &lt;fx:fileset dir="dist" includes="libs/*"/&gt;
    <mark>&lt;fx:fileset dir="dist" includes="resources/**"/&gt;</mark>
&lt;/fx:resources&gt; 
</pre>


Somehow, the version number doesn't get added in the `fx:application` which makes the installer always default to version `1.0` (as pointed out by a few people in the comments). To fix this, manually add the version number (thanks Marc for [finding out](http://code.makery.ch/library/javafx-tutorial/part7/#comment-1566725959)):

##### build.xml - add "version"

<pre class="prettyprint lang-xml">
&lt;fx:application id="fxApplication"
    name="AddressApp"
    mainClass="ch.makery.address.MainApp"
    <mark>version="1.0"</mark>
/>
</pre>

We could already run the `build.xml` as an Ant build at this point. This would generate a runnable jar file of the project. But we want to go a step further and create a nice installer.


### Step 5 (WINDOWS) - Windows exe Installer

![AddressApp on Windows](addressapp-windows.png)

With **Inno Setup** we can create a Windows Installer of our application as a single `.exe` file. The resulting `.exe` will perform a user level installation (no admin permissions required). Also a shortcut will be created (menu or desktop).

1. Download [Inno Setup 5 or later](http://www.jrsoftware.org/isdl.php). Install Inno Setup on your computer. Our Ant script will use it to automatically generate the installer.

2. Tell Windows about the installation path to Inno Setup (e.g. `C:\Program Files (x86)\Inno Setup 5`). To do this add Inno Setup to the `Path` variable in your windows environment variables. If you don't know where to find it, read [How to set the path and environment variables in Windows](http://www.computerhope.com/issues/ch000549.htm).

3. Restart Eclipse and continue with Step 6.


### Step 5 (MAC) - MacOS dmg Installer

![AddressApp on Mac](addressapp-macos.png)

To create a Mac OS `dmg` drag-and-drop installer, no additional tool is required.

Note: For the installer image to work it must have exactly the same name as the application.


### Step 5 (LINUX etc.) - Linux rpm Installer

For other packaging options (`msi` for windows, `rpm` for Linux) see this native packaging [blog post](https://blogs.oracle.com/talkingjavadeployment/entry/native_packaging_for_javafx) or this [oracle documentation](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm#A1324980).


### Step 6 - Run build.xml

As a final step, we run the `build.xml` with Ant: *Right-click* on the `build.xml` file | *Run As* | *Ant Build*.

![Run Ant Build](run-ant-build.png)

The building **will take a while** (about 1 minute on my computer).

If everything was successful, you should find the native bundle in the folder `build/deploy/bundles`. This is how the windows version looks like:

![Deployed File](deployed-file.png)


The file `AddressApp-1.0.exe` can be used as a single file to install the application. This installer will copy the bundle to `C:/Users/[yourname]/AppData/Local/AddressApp`.


### What's Next?

I hope this tutorial was a help for you to get started with JavaFX and you'll be able to write your own JavaFX project from here.

I appreciate any feedback. Feel free to write comments if you have any suggestions or if something was unclear.


##### Some other articles you might find interesting

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)


