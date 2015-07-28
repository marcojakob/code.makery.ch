---
layout: article
title : "آموزش JavaFX 8 - قسمت دوم: مدل و TableView"
date: 2014-04-19 00:00
updated: 2015-07-28 00:00
slug: javafx-8-tutorial/fa/part2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-fa-part2.md
description: "Use a JavaFX TableView to display an ObservableList of Persons."
image: /assets/library/javafx-8-tutorial/part2/addressapp-part2.png
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
  - text: "قسمت دوم: مدل و TableView"
    link: /library/javafx-8-tutorial/fa/part2/
    paging: 2
    active: true
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
  - text: دانلود قسمت دوم به عنوان پروژه Eclipse <em>(حداقل به نسخه JDK 8u40 نیاز دارد)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-2.zip
    icon-css: fa fa-fw fa-download
languages:
  header: "زبان‌ها"
  collection: library
  item: javafx-8-tutorial
  part: part2
  active: fa
---

![Screenshot AddressApp Part 2](/assets/library/javafx-8-tutorial/part2/addressapp-part2.png)


## مباحث قسمت دوم

* ساختن یک کلاس **الگو** (Model** Classes**)

* استفاده از کلاس مدل در یک **ObservableList**

* نشان دادن داده در **TableView** با کمک **کنترلرها**


*****

## ساختن کلاس الگو

ما به یک کلاس الگو برای نگهداری اطلاعات درباره افراد داخل دفتر تلفن نیاز داریم. یک کلاس جدید به پکیج الگو (`ch.makery.address.model`) به نام `Person` اضافه کنید. کلاس `Person` شامل چندتا متغیر نمونه (instance variable) برای نام, آدرس, تاریخ تولد خواهد بود. قطعه کد زیر رو به کلاس اضافه کنید. بعضی از جزئیات Javafx رو بعد از کد توضیح میدم.



##### Person.java

<pre class="prettyprint lang-java" dir="ltr">
package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty street;
	private final IntegerProperty postalCode;
	private final StringProperty city;
	private final ObjectProperty&lt;LocalDate&gt; birthday;

	/**
	 * Default constructor.
	 */
	public Person() {
		this(null, null);
	}
	
	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
		// Some initial dummy data, just for convenient testing.
		this.street = new SimpleStringProperty("some street");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.city = new SimpleStringProperty("some city");
		this.birthday = new SimpleObjectProperty&lt;LocalDate&gt;(LocalDate.of(1999, 2, 21));
	}
	
	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}
	
	public StringProperty streetProperty() {
		return street;
	}

	public int getPostalCode() {
		return postalCode.get();
	}

	public void setPostalCode(int postalCode) {
		this.postalCode.set(postalCode);
	}
	
	public IntegerProperty postalCodeProperty() {
		return postalCode;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}
	
	public StringProperty cityProperty() {
		return city;
	}

	public LocalDate getBirthday() {
		return birthday.get();
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}
	
	public ObjectProperty&lt;LocalDate&gt; birthdayProperty() {
		return birthday;
	}
}
</pre>


### توضیحات

* در JavaFX معمولا از [`Properties`] (http://docs.oracle.com/javase/8/javafx/api/javafx/beans/property/Property.html) برای همه فیلدهای کلاس الگو استفاده میشه. یک `Property` به ما اجازه میده به طور خودکار از تغییرات متغییرها مثل `LastName` آگاه بشیم. این قابلیت به ما این اجازه رو میده که نمای برنامه همگام با داده باشه. برای اطلاعات بیشتر درمورد `Propertise` به [Using JavaFX Propertise and Binding](http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm) رجوع کنید.
* [`LocalDate`](http://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html) گونه‌ای که برای `birthday` استفاده میکنیم, قسمتی از [API تاریخ و زمان برای JDK8](http://docs.oracle.com/javase/tutorial/datetime/iso/) هست.


*****

## یک لیست از افراد

داده اصلی که برنامه ما اونو مدیریت میکنه, تعدادی آدم هست. پس لازمه که داخل کلاس `MainApp` لیستی برای `Person` بسازیم. بقیه کلاس‌های کنترل گر, بعدا به لیست داخل `MainApp` دسترسی پیدا میکنن.


### ObservableList

ما داریم با کلاس‌های نمای JavaFX کار میکنیم که نیاز دارند از هر تغییری که توی لیست افراد به وجود میاد آگاه بشن. این خیلی مهمه! چون در غیر این صورت نما با داده همگام نمیمونه. برای این منظور, JavaFX چندتا [Collection classes](http://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm) معرفی کرده.

از اون کلکسیون ما به `ObservableList` نیاز داریم, برای ساختن `ObservableList` جدید, قطعه کد زیرو به ابتدای کلاس `MainApp` اضافه کنید. ما همچنین یک سازنده (constructor) برای ساختن چند داده نمونه و متدهای دریافت کننده (getter methods) به برنامه اضافه میکنیم:


##### MainApp.java

<pre class="prettyprint lang-java" dir="ltr">

    // ... AFTER THE OTHER VARIABLES ...

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList&lt;Person&gt; personData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {
		// Add some sample data
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Mueller"));
	}
  
	/**
	 * Returns the data as an observable list of Persons. 
	 * @return
	 */
	public ObservableList&lt;Person&gt; getPersonData() {
		return personData;
	}
  
    // ... THE REST OF THE CLASS ...
</pre>


*****

## PersonOverviewController ##

بلاخره بیاید چندتا داده به جدولمون اضافه کنیم. ما به یک کنترل گر برای `PersonOverview.fxml` نیاز داریم.

1. یک کلاس معمولی داخل پکیج **view** به اسم `PersonOverviewController.java` بسازید. (ما باید این کلاسو توی همون پکیجی قرار بدیم که `PersonOverview.fxml` قرار داره در غیر این صورت SceneBuilder اونو شناسایی نمیکنه.)
2. ما چند تا متغیر نمونه (instance variable) هم اضافه میکنیم تا به ما دسترسی به جدول و برچسب‌های (label) داخل نما رو بده. فیلد ها و برخی متدها دارای تفسیری (annotation) به شکل `@FXML` هستند. این برای فایل fxml ضروریه تا بتونه به فیلدها و متدهای private دسترسی داشته باشه. بعد از اینکه همه چیزو تو فایل fxml تنظیم کردیم, برنامه ما به طور خودکار هنگام بارگذاری فایل fxml متغیرها رو پر میکنه. پس کد قطعه کد زیرو به برنامه اضافه کنید:

<div class="alert alert-info">
نکته: مراقب باشید که هنگام import از کتابخانه‌های awt یا swing به جای **Javafx** استفاده نکنید!
</div>


##### PersonOverviewController.java

<pre class="prettyprint lang-java" dir="ltr">
package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;

public class PersonOverviewController {
    @FXML
    private TableView&lt;Person&gt; personTable;
    @FXML
    private TableColumn&lt;Person, String&gt; firstNameColumn;
    @FXML
    private TableColumn&lt;Person, String&gt; lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
}
</pre>


این کد احتمالا احتیاج به مقداری توضیح داره:

* تمامی فیلدها و متدهایی که فایل fxml باید به آنها دسترسی داشته باشد باید با `FXML@` نشانه گذاری شده باشند. درواقع این کار فقط وقتی لازمه که اونها private باشن اما بهتره که همیشه به صورت private تعریف بشن و نشانه گذاری بشن!
* متد `()initialize` به صورت خودکار بعد از بارگذاری فایل fxml فراخوانی میشه. در این زمان, همه فیلدهای FXML باید مقداردهی اولیه شده باشند.
* متد `(...)setCellValueFactory` که برای ستون‌های جدول استفاده کردیم برای این هست که مشخص کنیم هر فیلد داخل کلاس `Person` باید تو کدوم ستون قرار بگیره. فلشی که به شکل `<-` هست نشون میده که ما از یه قابلیت جاوا 8 به اسم *Lambdas* داریم استفاده میکنیم. (انتخاب دیگه برای ما استفاده از [PropertyValueFactory](http://docs.oracle.com/javase/8/javafx/api/) هست اما یک type-safe نیست یعنی احتمال بروز خطا وجود داره.)


<div class="alert alert-info">
  <p>
    در این مثال, ما فقط داریم از مقدارهای `StringProperty` برای ستون‌های جدول استفاده میکنیم. درصورتی که بخواهید از `IntegerProperty` و یا `DoubleProperty` استفاده کنید, متد `(...)setCellValueFactory` باید یک `()asObject` هم داشته باشد.
  </p>
  <p>
  <pre>myIntegerColumn.setCellValueFactory(cellData -> 
      cellData.getValue().myIntegerProperty().<mark>asObject()</mark>);</pre>
  </p>
  <p>
	این بخاطر تصمیم به طراحی بد مهمه(
    <a href="https://community.oracle.com/thread/2575601">
    اینجا رو ببینید
    </a>
    ).
  </p>
</div>


### متصل کردن MainApp به PersonOverviewController

متد `(...)setMainApp` باید توسط کلاس `MainApp` فراخوانی بشه. این به ما راهی برای دسترسی به اشیا کلاس `MainApp` و گرفن لیست افراد و چیزهای دیگه میده. متد `showPersonOverview` رو با قطعه کد زیر که شامل دو خط اضافیه جایگزین کنید: 


##### MainApp.java - new showPersonOverview() method

<pre class="prettyprint lang-java" dir="ltr">
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

<mark>        // Give the controller access to the main app.
        PersonOverviewController controller = loader.getController();
        controller.setMainApp(this);</mark>

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>



*****


## وصل کردن نما به کنترلر

تا اینجا تقریبا همه کارها رو انجام دادیم فقط مونده یه چیز: ما هنوز به `PersonOverview.fxml` نگفتیم که از کدوم کنترلر استفاده کنه و هر عنصر معادل چه فیلدی تو کلاس کنترلر هست.

1. اول `PersonOverview.fxml` رو با *SceneBuilder* باز کنید. 

2. *Controller* که در سمت چپ هست رو باز کنید و `PersonOverviewController` رو به عنوان **controller class** انتخاب کنبد.   
![Set Controller Class](/assets/library/javafx-8-tutorial/part2/set-controller-class.png)

3. `TableView` رو از قسمت *Hierarchy* انتخاب کنید و تو قسمت *Code* در سمت راست, `personTable` رو به عنوان **fx:id** انتخاب کنید.   
![Set TableView fx:id](/assets/library/javafx-8-tutorial/part2/set-tableview-fx-id.png)

4. همین کارو برای ستون‌ها انجام بدید و `firstNameColumn` و `lastNameColumn` رو به عنوان **fx:id** انتخاب کنید.

5. برای **هر برچسب** روی ستون دوم, **fx:id** متناظرش رو انتخاب کنید. 
![Set Label fx:id](/assets/library/javafx-8-tutorial/part2/set-label-fx-id.png)

6. مهم: به Eclipse برگردید و **با دکمه F5 کل پروژه رو رفرش کنید**. این مهمه چون بعضی مواقع Eclipse متوجه تغییراتی که تو SceneBuilder ایجاد کردیم نمیشه.


*****

## برنامه رو شروع کنید

وقتی برنامه رو اجرا میکنید, باید چیزی شبیه عکسی که در ابتدای این قسمت از آموزش دیدید, ببینید.

تبریک میگم!

*نکته: این برچسب ها در هنگامی که شخصی رو انتخاب میکنید به روز رسانی نمیشن. ما تعاملات با کاربر رو در قسمت بعدی آموزش دنبال میکنیم.*


### بعدش چه اتفاقی میوفته؟

در [آموزش قسمت سوم](/library/javafx-8-tutorial/fa/part3/) ما بعضی قابلیت‌ها مثل اضافه کردن شخص جدید, پاک کردن و به روز رسانی یک شخص رو به برنامه اضافه میکنیم. 


##### بعضی از مطالب که ممکنه در این زمینه مفید باشن

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)