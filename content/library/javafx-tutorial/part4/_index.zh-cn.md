---
layout: article
title: "JavaFX 8 教程 - 第四部分：CSS 样式"
date: 2014-10-08
updated: 2015-01-17
slug: javafx-tutorial/zh-cn/part4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-zh-cn-part4.md
description: null
image: /assets/library/javafx-tutorial/part4/addressapp-part4.png
published: true
prettify: true
comments: true
sidebars:
- header: "系列文章"
  body:
  - text: "简介"
    link: /library/javafx-tutorial/zh-cn/
    paging: 简介
  - text: "第一部分：Scene Builder"
    link: /library/javafx-tutorial/zh-cn/part1/
    paging: 1
  - text: "第二部分：Model 和 TableView"
    link: /library/javafx-tutorial/zh-cn/part2/
    paging: 2
  - text: "第三部分：与用户的交互"
    link: /library/javafx-tutorial/zh-cn/part3/
    paging: 3
  - text: "第四部分：CSS 样式"
    link: /library/javafx-tutorial/zh-cn/part4/
    paging: 4
    active: true
  - text: "第五部分：将数据用 XML 格式存储"
    link: /library/javafx-tutorial/zh-cn/part5/
    paging: 5
  - text: "第六部分：统计图"
    link: /library/javafx-tutorial/zh-cn/part6/
    paging: 6
  - text: "第七部分：部署"
    link: /library/javafx-tutorial/zh-cn/part7/
    paging: 7
- header: "下载源代码"
  body:
  - text: Part 4 as Eclipse Project <em>(requires at least JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-4.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: 语言
  collection: library
  item: javafx-tutorial
  part: part4
  active: zh-cn
---

![Screenshot AddressApp Part 4](/assets/library/javafx-tutorial/part4/addressapp-part4.png)


## 第4部分主题

* **CSS样式表**
* 添加**应用程序图标**



*****


## CSS样式表 

在JavaFX中，你能使用层叠样式表修饰你的用户接口。这非常好！自定义Java应用界面从来不是件简单的事情。

在本教程中，我们将创建一个*DarkTheme*主题，灵感来自于Windows 8 Metro设计。按钮的CSS来至于Pedro Duque Vieia的博客[Java中JMetro-Windows 8 Metro控件](http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/)。


### 熟悉CSS

如果你希望修饰你的JavaFX应用，通常你应该对CSS有一个基本的了解。一个好的起点是[CSS教程](http://www.csstutorial.net/).

关于CSS更多JavaFX指定信息:

* [使用CSS换肤JavaFX应用](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/css_tutorial.htm) - Oracle教程
* [JavaFX CSS参考](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html) - 官方


### 缺省的JavaFX CSS

在JavaFX 8中缺省的CSS风格源码是一个称为**`modena.css`**文件。该CSS文件可以在JavaFX jar文件`jfxrt.jar`中找到，它位于Java目录`/jdk1.8.x/jre/lib/ext/jfxrt.jar`。

解压`jfxrt.jar`，你应该能在`com/sun/javafx/scene/control/skin/modena/`目录下找到`modena.css`。

缺省的样式表总是应用到JavaFX应用上。通过添加自定义样式表，你能覆盖`modena.css`中缺省的样式。


<div class="alert alert-info">
**提示**：查看缺省的CSS文件能够让你模板你需要覆盖掉那些样式。
</div>


### 添加CSS样式表

添加下面的CSS文件`DarkTheme.css`到`view`包中。


##### DarkTheme.css

<pre class="prettyprint lang-css pre-scrollable">
.background {
    -fx-background-color: #1d1d1d;
}

.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 0.6;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.table-view {
    -fx-base: #1d1d1d;
    -fx-control-inner-background: #1d1d1d;
    -fx-background-color: #1d1d1d;
    -fx-table-cell-border-color: transparent;
    -fx-table-header-border-color: transparent;
    -fx-padding: 5;
}

.table-view .column-header-background {
    -fx-background-color: transparent;
}

.table-view .column-header, .table-view .filler {
    -fx-size: 35;
    -fx-border-width: 0 0 1 0;
    -fx-background-color: transparent;
    -fx-border-color: 
        transparent
        transparent
        derive(-fx-base, 80%) 
        transparent;
    -fx-border-insets: 0 10 1 0;
}

.table-view .column-header .label {
    -fx-font-size: 20pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-alignment: center-left;
    -fx-opacity: 1;
}

.table-view:focused .table-row-cell:filled:focused:selected {
    -fx-background-color: -fx-focus-color;
}

.split-pane:horizontal > .split-pane-divider {
    -fx-border-color: transparent #1d1d1d transparent #1d1d1d;
    -fx-background-color: transparent, derive(#1d1d1d,20%);
}

.split-pane {
    -fx-padding: 1 0 0 0;
}

.menu-bar {
    -fx-background-color: derive(#1d1d1d,20%);
}

.context-menu {
    -fx-background-color: derive(#1d1d1d,50%);
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 0.9;
}

.menu .left-container {
	-fx-background-color: black;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}

/* 
 * Metro style Push Button
 * Author: Pedro Duque Vieira
 * http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/
 */
.button {
    -fx-padding: 5 22 5 22;   
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #1d1d1d;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
  -fx-background-color: white;
  -fx-text-fill: #1d1d1d;
}

.button:focused {
    -fx-border-color: white, white;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #1d1d1d;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color,30%);
}
</pre>

现在我们需要把CSS添加到我们的场景中。我们能在Java代码中编程完成，但是我们将使用SceneBuilder来添加它到fxml文件中。


#### 添加CSS到RootLayout.fxml

1. 在Scene Builder中打开`RootLayout.fxml`

2. 在*Hierarchy*视图中选择根节点`BorderPane`。在*Properties*组中添加`DarkTheme.css`作为样式表。

![DarkTheme for RootLayout](/assets/library/javafx-tutorial/part4/darktheme-rootlayout.png)


#### 添加CSS到PersonEditDialog.fxml

1. 在Scene Builder中打开`PersonEditDialog.fxml`。选择根节点`AnchorPane`，并且在*Properties*组中选择`DarkTheme.css`作为样式表。

2. 背景仍然是白色的，因此添加样式类`background`到根节点`AnchorPane`。

![Add Style Class](/assets/library/javafx-tutorial/part4/darktheme-personeditdialog.png)

3.  选择OK按钮，在*Properties*视图中选择*Default Button*单选框。这将修改它的颜色，当用户*输入*关键词时，使用它作为缺省的按钮。



#### 添加CSS到PersonOverview.fxml

1. 在Scene Builder中打开文件`PersonOverview.fxml`。在*Hierarchy*组中选择根节点`AnchorPane`。在*Properties*下面添加`DarkTheme.css`文件作为样式表。

2. 你现在应该已经看到一些修改，表和按钮是黑色的。来自`modena.css`中所有类样式`.table-view`和`.button`应用到表和按钮。因为我们已经在自定义CSS中重定义（因此覆盖掉）一些样式。新的样式自动应用。

3. 你可能需要调整按钮的大小，以便显示所有的文本。

4. 选择`SplitPane`中右边的`AnchorPane`。
![Background Style Select](/assets/library/javafx-tutorial/part4/background-style-select.png)   

5. 进入到*Properties*组，并且选择`background`作为样式表。背景现在应该变为黑色。
![Background Style](/assets/library/javafx-tutorial/part4/background-style.png)


#### 使用不同样式的标签

现在，在左边的所有的标签都有相同的大小。这里已经有一些样式定义在CSS文件中，称为`.label-header`和`.label-bright`。我们将使用更多样式的标签Label。

1. 选择*Person Detail*标签，添加`label-header`作为样式类。
![Label Header Style](/assets/library/javafx-tutorial/part4/label-header-style.png)

2. 给右边一列的每个Label（显示人员详情的那列）添加CSS样式类`label-bright`。
![Label Bright Style](/assets/library/javafx-tutorial/part4/label-bright-style.png)


*****


## 添加应用图标

现在，在标题栏和任务栏中，我们的应用只有一个缺省图标:

![Default Icon](/assets/library/javafx-tutorial/part4/default-app-icon.png)

使用自定义图标看起来更好了。

![Custom Icon](/assets/library/javafx-tutorial/part4/custom-app-icon.png)


### 图标文件

获取图标的一个可能地方是[Icon Finder](http://www.iconfinder.com)。我下载了一个[地址本的图标](http://www.iconfinder.com/icondetails/86957/32/).

通常在你的AddressApp项目中创建一个目录称为**resources**，在它中子目录称为**images**。把你选择的图标放入到images目录中。现在，你的目录结构应该看上去如下所示：

![Custom Icon File](/assets/library/javafx-tutorial/part4/custom-icon-file.png)


### 设置图标到场景

为了给你场景设置图标，添加下面一行到`MainApp.jar`的`start(…)`方法中。


##### MainApp.java

<pre class="prettyprint lang-java">
this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
</pre>

现在，整个`start(…)`方法看上去应该是这样的。:

<pre class="prettyprint lang-java">
public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("AddressApp");

    // Set the application icon.
    this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

    initRootLayout();

    showPersonOverview();
}
</pre>

当然，你也应该添加图标到人员编辑对话框的Stage中。


### 下一步

在本教程的[第5部分](/library/javafx-tutorial/zh-cn/part5/)将为我们数据添加XML存储。


##### 你可能感兴趣的有些其他文章

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

