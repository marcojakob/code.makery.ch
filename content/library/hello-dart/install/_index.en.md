+++
title = "Installation"
date = 2015-05-01
updated = 2020-05-05
description = "Installing Dart and Visual Studio Code. Loading the Hello Dart scenarios and starting a first Dart program."
image = "hello-dart.png"
prettify = true
comments = true

sidebarName = "<i class=\"fa fa-fw fa-cog\"></i> Installation"
pagingName = "<i class=\"fa fa-fw fa-cog\"></i>"
weight = 2

[[sidebars]]
header = "Links"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-external-link\"></i> Dart SDK"
link = "https://dart.dev/tools/sdk/archive"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-external-link\"></i> Visual Studio Code"
link = "https://code.visualstudio.com/"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-external-link\"></i> Hello Dart Scenarios"
link = "https://github.com/marcojakob/hello-dart/releases"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Page as Word File"
link = "/library/convert-web-page-to-word/"
+++

To start programming we need...

- Dart SDK
- Visual Studio Code Editor
- `Hello Dart` scenarios


## Installing Dart SDK

![Dart SDK](dart-sdk-icon.png)

1. Go to [Dart SDK Website](https://dart.dev/tools/sdk/archive).
2. Under `Stable channel` download the Dart SDK as zip file for your operating system.
3. Unzip the downloaded files to a folder like `C:\dart-sdk\` (on Windows).

### Configure PATH Environment Variable

To run the Dart tools from command line we need to add two directories to the PATH [environment variables](https://www.computerhope.com/issues/ch000549.htm).

- The `bin` subfolder where you put your Dart SDK: `C:\dart-sdk\bin` (or similar)
- The [pub cache](https://dart.dev/tools/pub/cmd/pub-global#running-a-script-from-your-path) (Dart's package manager): `%APPDATA%\Pub\Cache\bin` (on Windows) or `$HOME/.pub-cache/bin` (on macOS)


## Installing Visual Studio Code

<a href="https://code.visualstudio.com/" target="_blank">![VS Code Editor Logo](vs-code-logo.png)
</a>

Go to the [Visual Studio Code](https://code.visualstudio.com/) website and install it. This will be our code editor.

### Install Dart Extension

In Visual Studio Code open the extensions menu.

![Extensions](extensions.png)

Then install the Dart extension. This will give us support for the Dart language in the code editor.

![Dart Extension](extensions-dart.png)


## Setup for `Hello Dart` Scenarios

1. Download the source code (zip) from [Hello Dart Releases](https://github.com/marcojakob/hello-dart/releases) (use the latest version).  
2. Unzip the files to your computer.
3. In Visual Studio Code: Click on **File | Open Folder...**. Choose the `hello-dart` folder that you have just unzipped.
4. Right-click the file `pubspec.yaml` and choose `Get Packages`.   
![Get Packages](get-packages.png)

This will download the packages that this program depends on.

## Install Web Server

We need a web server, called [webdev](https://dart.dev/tools/webdev), to be able to start our Dart programs. 

Open the terminal in the menu with **View | Terminal**.

Use the command `pub global activate webdev` and hit enter.

![Install Webdev](terminal-install-webdev.png)


## Start Web Server

Now enter `webdev serve` in the terminal.

*Note: If you get an error message, your PATH variable might not be set up correctly (see above).*

![Serve Webdev](terminal-start-webdev.png)

This will start the web server locally. You will see a local web address (starting with http://127....). Ctrl+click this URL to open it in a browser (preferably Chrome).

In the browser you should see a list of scenarios. If you open the first scenario you should see something like this:

![First Scenario](first-scenario.png)


### What's next?

&rarr; Continue with [Part 1](/library/hello-dart/part1/) to make your first steps in programming.

***

*Credits*<br>
<em class="small">
  [Planet Cute](http://www.lostgarden.com/2007/05/dancs-miraculously-flexible-game.html) images by Daniel Cook (Lostgarden.com), published under [CC BY 3.0](http://creativecommons.org/licenses/by/3.0/us/).<br>
[Oleg Yadrov](https://www.linkedin.com/in/olegyadrov) improved the "Planet Cute" images and was so kind to let me use them. The images were optimized with the great [TexturePacker](https://www.codeandweb.com/texturepacker).
</em>