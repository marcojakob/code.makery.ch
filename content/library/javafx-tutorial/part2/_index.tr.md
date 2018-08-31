+++
title = "Kısım 2: Model ve TableView"
date = 2014-04-23
updated = 2015-03-12
description = "Kişilerin ObservableList'ini göstermek için JavaFX TableView kullan."
image = "addressapp-part2.png"
prettify = true
comments = true
commentsIdentifier = "/java/javafx-8-tutorial-part2/"
aliases = [ 
  "/library/javafx-8-tutorial/part2/",
	"/library/javafx-2-tutorial/part2/"
]
weight = 2

[[sidebars]]
header = "Download Sources"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Eclipse Projesi olarak Kısım 2 <em>(en az JDK 8u40 gerektirir)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-2.zip"
+++

![Screenshot AddressApp Part 2](addressapp-part2.png)


## Kısım 2 deki Başlıklar
* **model** sınıfı oluşturmak
* Model sınıfını bir **ObservableList** içerisinde kullanmak
* **Controllers** kullanarak veriyi **TableView** içerisinde göstermek



*****

## Model Sınıfını Oluştur

Adres defterindeki kişiler hakkında bilgileri tutabilmek için bir model sınıfına ihtiyacımız var. Model paketine (`ch.makery.address.model`) `Person` isminde yeni bir sınıf ekle. `Person` sınıfının isim, adres ve doğum tarihi için birkaç değişkeni olacak. Aşağıdaki kodu sınıfa ekleyin. Koddan sonra bazı JavaFX özelliklerini açıklayacağım. 


##### Person.java

<pre class="prettyprint lang-java">
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


### Açıklamalar

* JavaFX'te bir model sınıfının tüm alanları için [`Properties`](http://docs.oracle.com/javase/8/javafx/api/javafx/beans/property/Property.html) yaygın olarak kullanılır. Bir `Property` örneğin bize `lastName` ya da herhangi bir başka değişken değiştiğinde otomatik olarak haberdar olmamızı sağlar. Bu görüntüyü veri ile senkronize tutmamıza yardımcı olur. `Properties` hakkında daha fazla bilgi edinmek için [Using JavaFX Properties and Binding](http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm)'i okuyun.   
* [`LocalDate`](http://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html), `birthday` için kullandığımız değişken tipi, yeni [Date and Time API for JDK 8](http://docs.oracle.com/javase/tutorial/datetime/iso/)'in bir parçasıdır.


*****

## Kişiler Listesi

Uygulamamızın yönettiği temel veri birtakım kişilerdir. Şimdi `MainApp` sınıfı içerisinde `Person` nesneleri için bir liste oluşturalım. Diğer tüm controller sınıfları daha sonra `MainApp` içerisindeki merkezi listeye erişim alacaklar.   


### ObservableList

Kişi listesine yapılan değişikliklerden haberdar olması gereken JavaFX görüntü sınıflarıyla çalışıyoruz. Bu önemli çünkü aksi halde görüntümüz ver ile senkronize olmaz. Bu maksatla JavaFX bazı yeni [Collection classes](http://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm) oluşturdu. 

Bu collectionlar için `ObservableList`'e ihtiyacımız var. Yeni bir `ObservableList` oluşturmak için aşağıdaki kodu `MainApp` sınıfının başlangıcına ekleyin. Aynı zamanda bazı örnek veriler oluşturan yapılandırıcı ile public alıcı metodu da ekleyeceğiz. 


##### MainApp.java

<pre class="prettyprint lang-java">

    // ... AFTER THE OTHER VARIABLES ...

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList&lt;Person&gt; personData = FXCollections.observableArrayList();

	/**
	 * Yapılandırıcı
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

Nihayet tablomuza biraz veri alalım. `PersonOverview.fxml` için contoller a ihtiyacımız var.

1. **view** paketi içerisinde `PersonOverviewController.java` isminde normal bir sınıf oluşturun. (Bunu `PersonOverview.fxml` ile aynı paketin içerisine koymalıyız, aksi halde SceneBuilder bulamayacaktır.) 
2. Tabloya ve görüntü içerisindeki etiketlere erişimimizi sağlayacak bazı değişkenler ekleyeceğiz. Değişkenler ve bazı metodların özel `@FXML` notasyonları var. Bu, fxml dosyasının private alanlara ve private metotlara erişebilmesi için gereklidir. fxml disya içerisinde herşeyi ayarladıktan sonra, fxml dosya yüklendiğinde uygulama otomatik olarak değişkenleri dolduracaktır. Öyleyse aşağıdaki kodu ekleyelim: 

<div class="alert alert-info">
<strong>Not:</strong> Her zaman <strong>javafx import</strong> kullanmayı unutmayın, awt ya da swing değil!
</div>


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
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


Muhtemelen bu kodun biraz açıklamaya ihtiyacı var:

* fxml dosyasının erişmesi gereken tüm alanlar ve metodlar `@FXML` açıklamasına sahip olmalı. Aslında eğr sadece private iseler bu gerekli ama bunları private olarak tanımlamak ve notasyon ile işaretlemek daha iyi olur. 
* `initialize()` metodu fxml dosyası yüklendikten sonra otomatik olarak çağırılır. Bu noktada tüm FXML alanlarının zaten başlatılmış olması (initialized) gerekirdi. 
*  tablo sütunlarına ayarladğımız `setCellValueFactory(...)`, `Person` nesnesindeki hangi alanların o sütunda kullanılması gerektiğine karar vermek için kullanılıyor. Ok `->` *Lambdas* adındaki Java 8 özelliğini kullandığımızı gösteriyor. (Diğer bir seçenek [PropertyValueFactory](http://docs.oracle.com/javase/8/javafx/api/) kullanmka olabilirdi, ancak bu tip-güvenli değil).


<div class="alert alert-info">
  <p>
    Bu örnekte tablo sütunlarımız için sadece `StringProperty` kullanıyoruz.  `IntegerProperty` ya da `DoubleProperty` kullanmak istediğinizde, `setCellValueFactory(...)`'nin ek olarak `asObject()`'i olması lazım:
  </p>
  <p>
  <pre>myIntegerColumn.setCellValueFactory(cellData -> 
      cellData.getValue().myIntegerProperty().<mark>asObject()</mark>);</pre>
  </p>
  <p>
    Bu, JavaFX in kötü tasarım tercihinden dolayı gereklidir (bakınız <a href="https://community.oracle.com/thread/2575601">bu tartışma</a>).
  </p>
</div>


### MainApp ile PersonOverviewController'ı bağlamak

`setMainApp(...)` metodu `MainApp` sınıfı tarafından çağırılmalıdır. Bu bize `MainApp` nesnesine erişim ve `Persons` listesini ve diğer şeyleri alma imkanı sağlar.  `showPersonOverview()` metodunu aşağıdakiyle değiştirin. İki ilave satır içeriyor:


##### MainApp.java - yeni showPersonOverview() metodu

<pre class="prettyprint lang-java">
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


## Görüntüyü Controllera bağla

Nerdeyse bitti! Ama küçük birşey eksik: `PersonOverview.fxml` dosyamıza hangi contorller'ı kullanması gerektiğini ve hangi elementin controller içerisindeki hangi alanla eşleştiğini söylemedik.  

1. `PersonOverview.fxml` dosyasını *SceneBuilder* ile açın.

2. Sol taraftaki *Controller* grubunu açın ve **controller class** olarak `PersonOverviewController`'ı seçin.
![Controller Sınıfını Belirle](set-controller-class.png)

3. *Hierarchy* grubundaki `TableView`'i seçin ve *Code* grubu içerisindeki `personTable` alanını **fx:id** olarak seçin.   
![TableView fx:id Belirle](set-tableview-fx-id.png)

4. Aynı şeyi sütunlar için de yapın ve `firstNameColumn` ve `lastNameColumn` sütunlarını **fx:id** olarak seçin. 

5. İkinci sütundaki **her etiket** için karşılık gelen **fx:id** yi seçin.   
![Set Label fx:id](set-label-fx-id.png)

6. Önemli: Eclipse geri gidin ve **tüm projeyi yenileyin** (F5). Bu gerekli çünkü Eclipse bazan SeceneBuilder içerisinde yapılan değişikliklerden haberdar olmuyor.

*****

## Uygulamayı başlat

Şimdi uygulamayı başlattığında bu blog sayfasının başındaki ekran görüntüsüne bnzer birşey görmen lazım.

Tebrikler!

*Not: Bir kişi seçildiğinde etiketler henüz güncellenmeyecek. Bir sonraki kısımda kullanıcı etkileşimini programlayacağız.*


### Sırada Ne Var?

[Tutorial Kısım 3](/library/javafx-tutorial/part3/) Kişilerin eklenmesi, silinmesi ve düzenlenmesi gibi bazı işlevler ekleyeceğiz.


##### İlginç bulabileceğiniz diğer bazı makaleler

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

