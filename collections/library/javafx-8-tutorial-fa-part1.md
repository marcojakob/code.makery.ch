---
layout: article
title: "آموزش JavaFX 8 - قسمت اول: Scene Builder"
date: 2014-04-19 00:00
updated: 2015-07-15 00:00
slug: javafx-8-tutorial/fa/part1
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-fa-part1.md
description: "Learn how to set up a JavaFX project. This is part one of a seven-part tutorial about designing, programming and deploying an address application with JavaFX."
image: /assets/library/javafx-8-tutorial/part1/addressapp-part1.png
published: true
prettify: true
comments: true
rtl: true
sidebars:
- header: "مباحث این سری"
  body:
  - text: "مقدمه"
    link: /library/javafx-8-tutorial/fa/
    paging: Intro
  - text: "قسمت اول: Scene Builder"
    link: /library/javafx-8-tutorial/fa/part1/
    paging: 1
    active: true
  - text: "قسمت دوم: مدل و TableView"
    link: /library/javafx-8-tutorial/fa/part2/
    paging: 2
  - text: "قسمت سوم: تعامل با کاربر"
    link: /library/javafx-8-tutorial/fa/part3/
    paging: 3
  - text: "قسمت چهارم: سلیقه سازی با CSS"
    link: /library/javafx-8-tutorial/fa/part4/
    paging: 4
  - text: "قسمت پنجم: ذخیره کردن داده به عنوان XML"
    link: /library/javafx-8-tutorial/fa/part5/
    paging: 5
  - text: "قسمت ششم: رسم نمودار آماری"
    link: /library/javafx-8-tutorial/fa/part6/
    paging: 6
  - text: "قسمت هفتم: ساختن نسخه قابل نصب"
    link: /library/javafx-8-tutorial/fa/part7/
    paging: 7
- header: "دانلود سورس"
  body:
  - text: دانلود قسمت اول به عنوان پروژه Eclipse <em>(حداقل به نسخه JDK 8u40 نیاز دارد)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-1.zip
    icon-css: fa fa-fw fa-download
languages:
  header: "زبان‌ها"
  collection: library
  item: javafx-8-tutorial
  part: part1
  active: fa
---

![Screenshot AddressApp Part 1](/assets/library/javafx-8-tutorial/part1/addressapp-part1.png)

###مباحث قسمت اول

* شناختن JavaFX
* ساختن یک پروژه JavaFX
* استفاده از Scene Builder برای طراحی رابط کاربری
ساختار پایه برنامه با استفاده از الگوی MVC


*****


### پیش نیاز ها

* آخرین نسخه [جاوا JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (شامل **JavaFX 8**).
* برنامه Eclipse 4.4 و یا جدیدتر به همراه افزونه e(fx)clipse. ساده ترین راه, دانلود توزیع از پیش تنظیم شده از [وبسایت e(fx)clipse](http://efxclipse.bestsolution.at/install.html#all-in-one) هست. به عنوان جایگذین میتونید نسخه Eclipse از قبل نصب شده خودتون رو از [اینجا](http://www.eclipse.org/efxclipse/install.html) دانلود کنید.
* [Scene Builder 8.0](http://gluonhq.com/products/scene-builder/) (ایجاد شده توسط Gluon چون [اوراکل اونو فقط در قالب سورس کد منتشر میکنه](http://www.oracle.com/technetwork/java/javase/downloads/sb2download-2177776.html)).


### تنظیم کردن Eclipse

ما باید به Eclipse بگیم که از JDK 8 استفاده کنه و اینکه کجا میتونه Scene Builder رو پیدا کنه:

1. منوی Preferences در برنامه Eclipse رو باز کنید, بعد به قسمت Java و بعد Installed JREs برید

2. روی *...Add* کلیک کنید, *Standard VM* رو انتخاب کنید و بعد *مسیری* که JDK 8 رو تو اون نصب کردین انتخاب کنید.

3. بقیه JKD ها و JRE ها رو حذف کنید تا **JDK 8 به طور پیشفرض انتخاب بشه**. 
![Preferences JDK](/assets/library/javafx-8-tutorial/part1/preferences-jdk.png)

4. به *Java|Compiler* برید و مقدار **Compiler compliance level** رو به 1.8 تغییر بدید.
![Preferences Compliance](/assets/library/javafx-8-tutorial/part1/preferences-compliance.png)

5. به قسمت تنظیمات *JavaFX* برید و آدرس برنامه قابل اجرای Scene Builder رو وارد کنید.
![Preferences JavaFX](/assets/library/javafx-8-tutorial/part1/preferences-javafx.png)


### لینک های مفید

لینک های زیر میتونن مفید باشن:

* [Java 8 API](http://docs.oracle.com/javase/8/docs/api/) - JavaDoc برای کلاس‌های استاندارد جاوا
* [JavaFX 8 API](http://docs.oracle.com/javase/8/javafx/api/) - JavaDoc برای کلاس‌های JavaFX
* [ControlsFX API](http://controlsfx.bitbucket.org/) - JavaDoc برای [پروژه ControlsFX](http://fxexperience.com/controlsfx/) برای کنترل های اضافه تر.
* [Oracle's JavaFX Tutorials](http://docs.oracle.com/javase/8/javafx/get-started-tutorial/get_start_apps.htm) - آموزش‌های رسمی توسط Oracle.

حالا بیاین شروع کنیم!


*****


## ساختن یک پروژه JavaFX جدید

در Eclipse (نسخه‌ای که دارای e(fx)clipse است), به *...File | New | Other* رفته و *JavaFX Project* رو انتخاب کنید. 
یه اسم برای پروژه انتخاب کنید ( تو اینجا مثلا *AddressApp* ) و بعد روی *Finish* کلیک کنید.

درصورتی که به صورت خودکار پکیجی به اسم *application* ساخته شده, خودش و محتویاتش رو پاک کنید.


### ساختن پکیج ها

از اول این آموزش ما از اصول طراحی خوبی پیروی میکنیم. یکی از این اصول مهم [**Model-View-Control** (MVC)](http://en.wikipedia.org/wiki/Model_View_Controller) نام داره.براین اساس ما کدمونو به سه واحد مجزا تقسیم میکنیم و برای هرکدوم یک پکیج میسازیم(راست کلیک روی src-folder و بعد New... | Package):

* `ch.makery.address` - شامل بیشتر کلاس‌های کنترلر
* `ch.makery.address.model` - شامل کلاس‌های الگو(مدل)
* `ch.makery.address.view` - شامل نما (view)

**نکته:** پکیج view همچنین شامل کنترلرهایی میشه که مستقیما مرتبطه با یک نما. پس بهتره بهشون بگیم کنترلرهای نما.


*****


## ساختن فایل FXML

دو راه برای ساختن رابط کاربری وجود داره. یا استفاده از فایل XML یا نوشتن همه چیز در جاوا. با گشتن توی اینترنت شما به هردوتاش برمیخورید. ما از XML (فایلی با پسوند fxml.) برای بیشتر قسمت‌ها استفاده میکنیم. از نظر من این راه بهتری برای جدا کردن کنترلر و نما از هم هست. همچنین ما میتونیم از محیط گرافیکی Scene Builder استفاده کنیم تا فایل XML خودمونو دستکاری کنیم. این یعنی ما مجبور نیستیم به طور مستقیم با XML کار کنیم.

روی پکیج view راست کلیک کنید و یک *فایل FXML* به اسم `PersonOverview` بسازید.

![New FXML Document](/assets/library/javafx-8-tutorial/part1/new-fxml-document.png)

![New PersonOverview](/assets/library/javafx-8-tutorial/part1/new-person-overview.png)



*****


## طراحی با Scene Builder

<div class="alert alert-warning">
  **نکته:** اگر کار نکرد, سورس این قسمت رو دانلود کنید و با فایل fxml که درونش قرار داره امتحان کنید.
</div>

روی `PersonOverview.fxml` راست کلیک کنید و *Open with Scene Builder* رو انتخاب کنید. حالا Scene Builder با یک *Anchor Pane* داخلش باز میشه(تو قسمت Hierarchy در سمت چپ پیداست).

(در صورتی که Scene Builder باز نشد به *Window | Preferences | JavaFX* برید و مسیر صحیح نصب Scene Builder رو انتخاب کنید).

1. *Anchor Pane* موجود توی Hierarchy رو انتخاب کنید و اندازشو توی تب Layout در سمت راست تغییر بدید.  
![Anchor Pane Size](/assets/library/javafx-8-tutorial/part1/anchor-pane-size.png)

2. یک *Splitpane( اونی که افقی هست ) با درگ کردن اون از Library به ناحیه اصلی بسازید. روی *Split Pane* در *Hierarchy* راست کلیک کنید و *Fit to Parent* رو انتخاب کنید. 
![Fit to Parent](/assets/library/javafx-8-tutorial/part1/fit-to-parent.png)

3. یک *TableView* به سمت چپ *SplitPane* اضافه کنید. TableView رو انتخاب کنید (مراقب باشید فقط یک ستونشو انتخاب نکنید) و محدودیت طرح بندی (layout) زیرو روش اعمال کنید. داخل یک AnchorPane شما همیشه میتونید هرچهار لنگر (pane) رو نسبت به چهار طرف تنظیم کنید ([اطلاعات بیشتر در مورد Layout ها](http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm]).
![TableView Anchors](/assets/library/javafx-8-tutorial/part1/table-view-anchors.png)

4. به منوی *Preview | Show Preview in window* برید تا ببینید همه چیز اونطور که انتظار دارید هست یا نه. سعی کنید ابعاد پنجره رو تغییر بدید. Table View باید همراه با پنجره تغییر اندازه پیدا کنه چون با لنگر (anchor) به چهار طرف پنجره وصل شده.

5. اسم ستون‌ها رو در در قسمت Propertise به "First Name" و "Last Name" تغییر بدید. 
![Column Texts](/assets/library/javafx-8-tutorial/part1/column-texts.png)

6. *Table View* رو انتخاب کنید و از تب Propertise برای Column Resize Policy عبارت constrained-resize رو انتخاب کنید. این کار برای این هست که مطمعن بشیم ستون‌ها از تمام فضای جدول استفاده میکنن.
![Column Resize Policy](/assets/library/javafx-8-tutorial/part1/column-resize-policy.png)

7. یک *Label* در قسمت راست با عبارت "Person Details" ایجاد کنید. طرح بندی اونو با استفاده از لنگرها تنظیم کنید.
![Person Details Label](/assets/library/javafx-8-tutorial/part1/person-details-label.png)

8. یک *GridPane* به قسمت رای اضافه کنید, اونو انتخاب کنید و طرح بندی اونو با استفاده از لنگرها تغییر بدید ( بالا, چپ, راست ).
![GridPane Layout](/assets/library/javafx-8-tutorial/part1/grid-pane-layout.png)

9. برچسب‌های زیر رو به سلول ها اضافه کنید.
نکته: برای اضافه کردن سطر به GridPane, یک سطر رو انتخاب کنید (رنگش زرد میشه), روی شماره سطر کلیک کنید و *"Add Row"* رو انتخاب کنید.
![Add labels](/assets/library/javafx-8-tutorial/part1/add-labels.png)

10. یک *ButtonBar* به پایین اضافه کنید و بهش سه تا دکمه اضافه کنید. حالا لنگرهای راست و پایین *ButtonBar* رو تنظیم کنید که همیشه تو جای درستش قرار داشته باشه.
![Button Group](/assets/library/javafx-8-tutorial/part1/button-group.png)

11. حالا شما باید چیزی شبیه به این داشته باشید. با کمک منوی *Preview* رفتار برنامه موقع تغییر سایزو تست کنید.
![Preview](/assets/library/javafx-8-tutorial/part1/scene-builder-preview.png)



*****


## ساختن  برنامه اصلی

ما به یک FXML دیگه برای طرح بندی ریشه که شامل نوار منو و بسته بندی (wrap) کردن `PersonOverview` هست نیاز داریم.

1. یک سند *FXML* دیگه داخل پکیج view به اسم `RootLayout.fxml` بسازید. اینبار *BorderPane* رو به عنوان عنصر ریشه انتخاب کنید.  
![New RootLayout](/assets/library/javafx-8-tutorial/part1/new-root-layout.png)

2. `RootLayout.fxml` رو با Scene Builder باز کنید.

3. *BorderPane* رو با قرار  دادن مقدار 600 برای *Pref Width* و 400 برای *Pref Height* تنظیم کنید.
![RootLayout Size](/assets/library/javafx-8-tutorial/part1/root-layout-size.png)

4. یک *MenuBar* به قسمت TOP اضافه کنید. در این بخش ما کاری با کارکرد منوها نداریم. 
![MenuBar](/assets/library/javafx-8-tutorial/part1/menu-bar.png)


### کلاس اصلی JavaFX

حالا ما به یک **کلاس اصلی جاوا (main class)** نیاز داریم که برنامه مارا با `RootLayout.fxml` آغاز کند و `PersonOverview.fxml` رو در وسطش قرار بده.

1. روی پروژه خودتون راست کلیک کنید و از طریق *...New | Other* گزینه *JavaFX Main Class* رو انتخاب کنید.  
![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class.png)

2. ما اسم کلاسو `MainApp` قرار میدیم و اونو توی پکیج کنترلر `ch.makery.address` قرار میدیم(نکته: این یک پکیج والد برای زیر پکیج های `view` و `model` هست).
![New JavaFX Main Class](/assets/library/javafx-8-tutorial/part1/new-main-class2.png)


کلاس `MainApp.java` ساخته شده از `Application` ارث میبرد و شامل دو متد است. این یک ساختار پایه برای شروع یک یک برنامه JavaFX هست. مهمترین قسمت برای ما متد `start(Stage primaryStage)` است. هنگامی که یه اپلیکیشن از طریق متد `launch` ,`main` میشود, این متد به طور خودکار فراخوانی میشود.

همونطور که میبینید, متد `(...)start` یک شی `Stage` به عنوان پارامتر دریافت میکند. نگاره زیر ساختار هر برنامه JavaFX رو نشون میده:

![New FXML Document](/assets/library/javafx-8-tutorial/part1/javafx-hierarchy.png)   
*منبع عکس: http://www.oracle.com*

**این مثل یه نمایش تئاتر میمونه:** `Stage` ظرف اصلی هست که معمولا یه پنجره (`Window`) هست با حاشیه (border) و شامل دکمه های minimize, maximize و close. داخل `Stage` شما `Scene` (صحنه) اضافه میکنید که البته میتونه با یک `Scene` دیگه تعویض بشه. داخل `Scene` گره‌های JavaFX مثل `AnchorPane`, `TextBox` و غیره افزوده میشن. 

برای اطلاعات بیشتر در این مورد به صفحه [کار کردن با JavaFX Scene Graph](http://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm) رجوع کنید.


*****

`MainApp.java` رو باز کنید و کدهای زیرو وارد کنید:

<pre class="prettyprint lang-java" dir="ltr">
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

توضیحات داخل کدها شما رو راهنمایی میکنه که هر قسمت کد چه کاری داره انجام میده.

اگه شما برنامه رو اجرا کنید, شما باید چیزی شبیه عکسی که در اول پست بود ببینید.


### مشکلات متداول

اگه JavaFX نتونه فایل `fxml` شما رو پیدا کنه, شما ممکنه با یک همچین پیغامی روبرو بشید:

`java.lang.IllegalStateException: Location is not set.`

برای حل این مشکل دوباره بررسی کنید که فایل‌های `fxml` غلط املایی نداشته باشن!

<div class="alert alert-warning">
  اگه مشکل شما همچنان پابرجاست, سورس این آموزش رو دانلود کنید و با فایل `fxml` که درونش هست امتحان کنید.
</div>


*****

### بعدش چه اتفاقی میوفته؟

در [آموزش قسمت دوم](will add some data and functional) کمی داده و کاربرد به برنامه اضافه میکنیم.


##### بعضی از مطالب که ممکنه در این زمینه مفید باشن

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)