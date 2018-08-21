---
layout: article
title: "JavaFX 8 教程 - 第六部分：统计图"
date: 2014-10-08
updated: 2015-01-16
slug: javafx-8-tutorial/zh-cn/part6
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-zh-cn-part6.md
description: null
image: /assets/library/javafx-8-tutorial/part6/addressapp-part6.png
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
    active: true
  - text: "第七部分：部署"
    link: /library/javafx-8-tutorial/zh-cn/part7/
    paging: 7
- header: "下载源代码"
  body:
  - text: Part 6 as Eclipse Project <em>(requires at least JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-6.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: 语言
  collection: library
  item: javafx-8-tutorial
  part: part6
  active: zh-cn
---

![Screenshot AddressApp Part 6](/assets/library/javafx-8-tutorial/part6/addressapp-part6.png)


## 第6部分的主题

* 创建一个**统计图**显示生日的分布。


*****

## 生日统计

在AddressApp中所有人员都有生日。当我们人员庆祝他们生日的时候，如果有一些生日的统计不是会更好。

我们使用**柱状图**，包含每个月的一个条形。每个条形显示在指定月份中有多少人需要过生日。


## 统计FXML视图

1. 在`ch.makery.address.view`包中我们开始创建一个`BirthdayStatistics.fxml`（*右击包|New|other..|New FXML Document*）
![生日统计FXML](/assets/library/javafx-8-tutorial/part6/birthday-statistics-fxml.png)

2. 在Scene Builder中打开`BirthdayStatistics.fxml`文件。

3. 选择根节点`AnchorPane`。在*Layout*组中设置*Pref Width*为620，*Pref Height*为450。

4. 添加`BarChart`到`AnchorPane`中。

5. 右击`BarChart`并且选择*Fit to Parent*。

6. 保存fxml文件，进入到Eclipse中，F5刷新项目。

在我们返回到Scene Builder之前，我们首先创建控制器，并且在我们的`MainApp`中准备好一切。


## 统计控制器

在view包 `ch.makery.address.view`中创建一个Java类，称为`BirthdayStatisticsController.java`。

在开始解释之前，让我们看下整个控制器类。


##### BirthdayStatisticsController.java

<pre class="prettyprint lang-java">
package ch.makery.address.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import ch.makery.address.model.Person;

/**
 * The controller for the birthday statistics view.
 * 
 * @author Marco Jakob
 */
public class BirthdayStatisticsController {

    @FXML
    private BarChart&lt;String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList&lt;String> monthNames = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
    }

    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    public void setPersonData(List&lt;Person> persons) {
    	// Count the number of people having their birthday in a specific month.
        int[] monthCounter = new int[12];
        for (Person p : persons) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series&lt;String, Integer> series = new XYChart.Series&lt;>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i &lt; monthCounter.length; i++) {
        	series.getData().add(new XYChart.Data&lt;>(monthNames.get(i), monthCounter[i]));
        }
        
        barChart.getData().add(series);
    }
}
</pre>


#### 控制器如何工作

1.  控制器需要从FXML文件中访问两个元素:

   * `barChar`：它有`String`和`Integer`类型。`String`用于x轴上的月份，`Integer`用于指定月份中人员的数量。
   * `xAxis`：我们使用它添加月字符串

2. `initialize()` 方法使用所有月的列表填充`x-axis`。

3. `setPersonData(…)`方法将由`MainApp`访问，设置人员数据。它遍历所有人员，统计出每个月生日的人数。然后它为每个月添加`XYChart.Data`到数据序列中。每个`XYChart.Data`对象在图表中表示一个条形。


*****

## 连接视图和控制器

1. 在Scene Builder中打开`BirthdayStatistics.fxml`。

2. 在**Controller**组中设置`BirthdayStatisticsController`为控制器。

3. 选择`BarChart`，并且选择`barChar`作为fx:id属性（在*Code*组中）

4. 选择`CategoryAxis`，并且选择`xAxis`作为fx:id属性。  
![类别轴](/assets/library/javafx-8-tutorial/part6/category-axis.png)

5. 你可以添加一个标题给`BarChar`（在*Properties*组中）进一步修饰。



*****


## 连接View/Controller和MainApp

我们为*生日统计*使用与*编辑人员对话框*相同的机制，一个简单的弹出对话框。

添加下面的方法到`MainApp`类中


<pre class="prettyprint lang-java">
/**
 * Opens a dialog to show birthday statistics.
 */
public void showBirthdayStatistics() {
    try {
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Birthday Statistics");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller.
        BirthdayStatisticsController controller = loader.getController();
        controller.setPersonData(personData);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>

一切设置完毕，但是我们没有任何东西实际上调用新的`showBirthdayStatistics()`方法。幸运的是我们已经在`RootLayout.fxml`中有一个菜单，它可以用于这个目的。


### 显示生日统计菜单

在`RootLayoutController`中添加下面的方法，它将处理*显示生日统计*菜单项的用户点击。

<pre class="prettyprint lang-java">
/**
 * Opens the birthday statistics.
 */
@FXML
private void handleShowBirthdayStatistics() {
  mainApp.showBirthdayStatistics();
}
</pre>

现在，使用Scene Builder打开`RootLayout.fxml`文件。创建*Staticstic* `菜单`，带有一个*Show Statistcs* `MenuItem`:

![Show Statistics菜单](/assets/library/javafx-8-tutorial/part6/show-statistics-menu.png)

选择*Show Statistics* `MenuItem`，并且选择`handleShowBirthdayStatistics`作为`On Action`（在*Code*组中）。  

![Show Statistics On Action](/assets/library/javafx-8-tutorial/part6/show-statistics-on-action.png)

进入到Eclipse，刷新项目，**测试它**。


*****

## JavaFX Chart的更多信息

更多信息的一个好地方是官方Oracle教程，[使用JavaFX Chart](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/charts.htm).


### 下一步做什么？

在最后的教程[第7部分](/library/javafx-8-tutorial/zh-cn/part7/) 中，我们将最后部署我们的应用（例如：打包并且发布应用到我们的用户）


##### 一些你可能感兴趣的其它文章

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

