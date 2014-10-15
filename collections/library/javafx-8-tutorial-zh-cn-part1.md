---
layout: article
title: "JavaFX 8 教程 - 第一部分：Scene Builder"
date: 2014-10-08 00:00
updated: 2014-10-08 00:00
slug: javafx-8-tutorial/zh-cn/part1
canonical: /java/javafx-8-tutorial-part1/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-zh-cn-part1.md
description: "这 7 部分组成的教程将介绍一个用 JavaFX 编写的联系人程的设计，开发与部署的过程。"
image: /assets/library/javafx-8-tutorial/part1/addressapp-part1.png
published: true
prettify: true
comments: true
sidebars:
- header: "系列文章"
  body:
  - text: "简介"
    link: /library/javafx-8-tutorial/zh-cn/
    paging: 简介
  - text: "第一部分：Scene Builder"
    link: /library/javafx-8-tutorial/zh-cn/part1/
    paging: 1
    active: true
  - text: "第二部分：Model 和 TableView"
    link: /library/javafx-8-tutorial/zh-cn/part2/
    paging: 2
  - text: "第三部分：与用户的交互"
    link: /library/javafx-8-tutorial/zh-cn/part3/
    paging: 3
  - text: "第四部分：CSS 样式"
    link: /library/javafx-8-tutorial/zh-cn/part4/
    paging: 4
  - text: "第五部分：将数据用 XML 格式存储"
    link: /library/javafx-8-tutorial/zh-cn/part5/
    paging: 5
  - text: "第六部分：统计图"
    link: /library/javafx-8-tutorial/zh-cn/part6/
    paging: 6
  - text: "第七部分：部署"
    link: /library/javafx-8-tutorial/zh-cn/part7/
    paging: 7
- header: "下载源代码"
  body:
  - text: 第一部分为 Eclipse 项目 <em>（要求至少 JDK 8u20）</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-1.zip
    icon-css: fa fa-fw fa-download
- header: 语言
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-part1/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part1/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part1/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part1/
    icon-css: fa fa-fw fa-globe
    active: true
---

<div class="alert alert-warning">
  <i class="fa fa-language"></i> This page needs translation to Chinese (Simplified). If you'd like to help out please read <a href="/library/how-to-contribute/" class="alert-link">how to contribute</a>.
</div>

![Screenshot AddressApp Part 1](/assets/library/javafx-8-tutorial/part1/addressapp-part1.png)

### 第一部分的主题

* 开始了解 JavaFX 。
* 创建并运行一个 JavaFX 项目。
* 使用 Scene Builder 来设计用户界面。
* 使用 模型 - 视图 - 控制器（MVC）模式 构造基础的应用。


*****


### 前提条件

* 最新的 [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) （包含 **JavaFX 8**）。
* Eclipse 4.3 或更高版本与 e(fx)clipse 插件。最简单的方法是从 [e(fx)clipse 网站](http://efxclipse.bestsolution.at/install.html#all-in-one) 下载预先配置的发行版本。作为一种备选你可以使用一个 [update site](http://www.eclipse.org/efxclipse/install.html) 来给您的 Eclipse 安装。
* [Scene Builder 2.0](http://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-info-2157684.html) 或更高。


### Eclipse 配置

我们需要告诉 Eclipse 使用 JDK 8 和在哪里找 Scene Builder：

1. 打开 Eclipse 的设置并导航至 *Java | Installed JREs* 。

2. 点击 *Add...*, 选择 *Standard VM* 并选择你安装 JDK 8 的 *Directory* 。

3. 移除其他的 JREs 或 JDKs 从而使 **JDK 8 成为默认**。
![Preferences JDK](/assets/library/javafx-8-tutorial/part1/preferences-jdk.png)

4. 导航至 *Java | Compiler* 。设置 **Compiler compliance level 到 1.8**。
![Preferences Compliance](/assets/library/javafx-8-tutorial/part1/preferences-compliance.png)

5. 导航至 *JavaFX* 设置。指定你的 Scene Builder 可执行文件的路径。
![Preferences JavaFX](/assets/library/javafx-8-tutorial/part1/preferences-javafx.png)


### 帮助链接

你可能会想收藏下面的链接：

* [Java 8 API](http://docs.oracle.com/javase/8/docs/api/) - Java 标准类的文档。
* [JavaFX 8 API](http://docs.oracle.com/javase/8/javafx/api/) - JavaFX 类的文档。
* [ControlsFX API](http://controlsfx.bitbucket.org/) - [ControlsFX project](http://fxexperience.com/controlsfx/) 额外 JavaFX 控件的文档。
* [Oracle's JavaFX Tutorials](http://docs.oracle.com/javase/8/javafx/get-started-tutorial/get_start_apps.htm) - Oracle 的 JavaFX 官方教程。

现在，让我们开始吧！


*****


## Create a new JavaFX Project

In Eclipse (with e(fx)clipse installed) go to *File | New | Other...* and choose *JavaFX Project*.   
Specify the Name of the project (e.g. *AddressApp*) and click *Finish*.

Remove the *application* package and its content if it was automatically created.


### Create the Packages

Right from the start we will follow good software design principles. One very important principle is that of [**Model-View-Controller** (MVC)](http://en.wikipedia.org/wiki/Model_View_Controller). According to this we divide our code into three units and create a package for each (Right-click on the src-folder, *New... | Package*):

* `ch.makery.address` - contains *most* controller classes (=business logic)
* `ch.makery.address.model` - contains model classes
* `ch.makery.address.view` - contains views 

**Note:** Our view package will also contain some controllers that are directly related to a single view. Let's call them **view-controllers**.


*****


## Create the FXML Layout File

There are two ways to create the user interface. Either using an XML file or programming everything in Java. Looking around the internet you will encounter both. We will use XML (ending in .fxml) for most parts. I find it a cleaner way to keep the controller and view separated from each other. Further, we can use the graphical Scene Builder to edit our XML. That means we will not have to directly work with XML.

Right-click on the view package and create a new *FXML Document* called `PersonOverview`.   

![New FXML Document](/assets/library/javafx-8-tutorial/part1/new-fxml-document.png)

![New PersonOverview](/assets/library/javafx-8-tutorial/part1/new-person-overview.png)



*****


## Design with Scene Builder

<div class="alert alert-warning">
  **Note:** If you can't get it to work, download the source of this tutorial part and try it with the included fxml.
</div>

Right-click on `PersonOverview.fxml` and choose *Open with Scene Builder*. Now you should see the Scene Builder with just an *AncherPane* (visible under Hierarchy on the left).

1. Select the *Anchor Pane* in your Hierarchy and adjust the size under Layout (right side):   
![Anchor Pane Size](/assets/library/javafx-8-tutorial/part1/anchor-pane-size.png)

2. Add a *Split Pane (Horizontal Flow)* by dragging it from the Library into the main area. Right-click the *Split Pane* in the *Hierarchy* view and select *Fit to Parent*.   
![Fit to Parent](/assets/library/javafx-8-tutorial/part1/fit-to-parent.png)

3. Drag a *TableView* (under *Controls*) into the left side of the *SplitPane*. Select the TableView (not a Column) and set the following layout constraints to the TableView. Inside an *AnchorPane* you can always set anchors to the four borders ([more information on Layouts](http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm)).   
![TableView Anchors](/assets/library/javafx-8-tutorial/part1/table-view-anchors.png)

4. Go to the menu *Preview | Show Preview in Window* to see, whether it behaves right. Try resizing the window. The TableView should resize together with the window as it is anchored to the borders.

5. Change the column text (under Properties) to "First Name" and "Last Name".   
![Column Texts](/assets/library/javafx-8-tutorial/part1/column-texts.png)

6. Select the *TableView* choose *constrained-resize* for the *Column Resize Policy* (under Properties). This ensures that the colums will always take up all available space.   
![Column Resize Policy](/assets/library/javafx-8-tutorial/part1/column-resize-policy.png)

7. Add a *Label* on the right side with the text "Person Details" (hint: use the search to find the *Label*). Adjust it's layout using anchors.   
![Person Details Label](/assets/library/javafx-8-tutorial/part1/person-details-label.png)

8. Add a *GridPane* on the right side, select it and adjust its layout using anchors (top, right and left).    
![GridPane Layout](/assets/library/javafx-8-tutorial/part1/grid-pane-layout.png)

9. Add the following labels to the cells.   
*Note: To add a row to the GridPane select an existing row number (will turn yellow), right-click the row number and choose "Add Row".*   
![Add labels](/assets/library/javafx-8-tutorial/part1/add-labels.png)

10. Add the three buttons at the bottom. Tip: Select all of them, right-click and call *Wrap In | HBox*. This groups them together. You might need to specify a *spacing* inside the HBox. Then, also set anchors (right and bottom) so they stay in the right place.   
![Button Group](/assets/library/javafx-8-tutorial/part1/button-group.png)

11. Now you should see something like the following. Use the *Preview* menu to test its resizing behaviour.   
![Preview](/assets/library/javafx-8-tutorial/part1/scene-builder-preview.png)



*****


## Create the Main Application

We need another FXML for our root layout which will contain a menu bar and wraps the just created `PersonOverview.fxml`.

1. Create another *FXML Document* inside the view package called `RootLayout.fxml`. This time, choose *BorderPane* as the root element.   
![New RootLayout](/assets/library/javafx-8-tutorial/part1/new-root-layout.png)

2. Open the `RootLayout.fxml` in Scene Builder.

3. Resize the *BorderPane* with *Pref Width* set to 600 and *Pref Height* set to 400.   
![RootLayout Size](/assets/library/javafx-8-tutorial/part1/root-layout-size.png)

4. Add a *MenuBar* into the TOP Slot. We will not implement the menu functionality at the moment.   
![MenuBar](/assets/library/javafx-8-tutorial/part1/menu-bar.png)


### The JavaFX Main Class 

Now, we need to create the **main java class** that starts up our application with the `RootLayout.fxml` and adds the `PersonOverview.fxml` in the center. 

1. Right-click on your project and choose *New | Other...* and choose *JavaFX Main Class*.   
![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class.png)

2. We'll call the class `MainApp` and put it in the controller package `ch.makery.address` (note: this is the parent package of the `view` and `model` subpackages).   
![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class2.png)


The generated `MainApp.java` class extends from `Application` and contains two methods. This is the basic structure that we need to start a JavaFX Application. The most important part for us is the `start(Stage primaryStage)` method. It is automatically called when the application is `launched` from within the `main` method.

As you see, the `start(...)` method receives a `Stage` as parameter. The following graphic illustrates the structure of every JavaFX application:

![New FXML Document](/assets/library/javafx-8-tutorial/part1/javafx-hierarchy.png)   
*Image Source: http://www.oracle.com*

**It's like a theater play**: The `Stage` is the main container which is usually a `Window` with a border and the typical minimize, maximize and close buttons. Inside the `Stage` you add a `Scene` which can, of course, be switched out by another `Scene`. Inside the `Scene` the actual JavaFX nodes like `AnchorPane`, `TextBox`, etc. are added.

For more information on this turn to [Working with the JavaFX Scene Graph](http://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm).


*****

Open `MainApp.java` and replace the code with the following:

<pre class="prettyprint lang-java">
package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}
</pre>

The various comments should give you some hints about what's going on.

If you run the application now, you should see something like the screenshot at the beginning of this post.


### Frequent Problems

If JavaFX can't find the `fxml` file you speicified, you might get the following error message: 

`java.lang.IllegalStateException: Location is not set.`

To solve this issue double check if you didn't misspell the name of your `fxml` files!

<div class="alert alert-warning">
  If it still doesn't work, download the source of this tutorial part and try it with the included fxml.
</div>


*****

### What's Next?

In [Tutorial Part 2](/library/javafx-8-tutorial/zh-cn/part2/) we will add some data and functionality to our AddressApp.


##### Some other articles you might find interesting

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)