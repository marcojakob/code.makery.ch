---
layout: article
title: "JavaFX 8 튜토리얼 - 2부: Model 그리고 TableView"
date: 2014-04-23 00:00
updated: 2015-03-12 00:00
slug: javafx-8-tutorial/part2
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-kr-part2.md
description: "JavaFX TableView를 사용해서 연락처의 ObservableList를 보여줍니다."
image: /assets/library/javafx-8-tutorial/part2/addressapp-part2.png
published: true
prettify: true
comments: 
  identifier: /java/javafx-8-tutorial-part2/
sidebars:
- header: "차례"
  body:
  - text: "소개"
    link: /library/javafx-8-tutorial/
    paging: Intro
  - text: "1부: Scene Builder"
    link: /library/javafx-8-tutorial/part1/
    paging: 1
  - text: "2부: Model 그리고 TableView"
    link: /library/javafx-8-tutorial/part2/
    paging: 2
    active: true
  - text: "3부: 사용자 상호작용"
    link: /library/javafx-8-tutorial/part3/
    paging: 3
  - text: "4부: CSS 꾸미기"
    link: /library/javafx-8-tutorial/part4/
    paging: 4
  - text: "5부: XML로 데이터 저장하기"
    link: /library/javafx-8-tutorial/part5/
    paging: 5
  - text: "6부: 통계 차트"
    link: /library/javafx-8-tutorial/part6/
    paging: 6
  - text: "7부: 배포"
    link: /library/javafx-8-tutorial/part7/
    paging: 7
- header: "소스 코드 다운로드"
  body:
  - text: 2부 Eclipse 프로젝트 <em>(JDK 8u40 이상 필요)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-2.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: 언어
  collection: library
  item: javafx-8-tutorial
  part: part2
  active: kr
---

![Screenshot AddressApp Part 2](/assets/library/javafx-8-tutorial/part2/addressapp-part2.png)


## 2부 주제

* **모델** 클래스 만들기
* **ObservableList**로 모델 클래스 이용하기
* **컨트롤러**로 **TableView**에서 데이터 보여주기


*****

## 모델 클래스 만들기

주소록에서 연락처 정보를 유지하려면 모델 클래스가 필요합니다. 모델 패키지 (`ch.makery.address.model`)에 `Person` 이라는 새로운 클래스를 만듭니다. `Person` 클래스는 이름(name), 주소(address) 그리고 생일(birthday) 같은 인스턴스 변수 몇 개를 가질 겁니다. 다음 코드를 클래스에 작성하세요. 자세한 내용은 이후에 설명하겠습니다.


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
 * 연락처 모델 클래스
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
	 * 디폴트 생성자
	 */
	public Person() {
		this(null, null);
	}
	
	/**
	 * 데이터를 초기화하는 생성자
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
		// 테스트를 위해 초기화하는 더미 데이터
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


### 설명

* JavaFX [`Properties`](http://docs.oracle.com/javase/8/javafx/api/javafx/beans/property/Property.html)를 모델 클래스의 모든 필드에 사용하는 것은 흔한 일입니다. `프로퍼티`는 예를 들면 어떤 변수가 바뀌면 자동으로 우리에게 알립니다. 이는 뷰가 데이터와 계속 일치하게 유지시켜 줍니다. `프로퍼티`에 대한 자세한 내용을 알고 싶으면 [Using JavaFX Properties and Binding](http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm)을 읽으세요.
* `생일`에 이용하려는 [`LocalDate`](http://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html)는 [Date and Time API for JDK 8](http://docs.oracle.com/javase/tutorial/datetime/iso/)의 새로운 타입입니다.


*****

## 연락처 리스트

우리 애플리케이션이 관리하는 주요 데이터는 연락처 한두 개가 아닙니다. `MainApp` 클래스에 `Person` 객체를 담는 리스트를 만들어 봅시다. 나중에 이 리스트는 `MainApp`에서 다른 모든 컨트롤러 클래스들이 이용할 겁니다.


### ObservableList

우리가 해야 할 일은 연락처 리스트에 변경이 일어나면 JavaFX 뷰 클래스에 이를 알려야 합니다. 그렇지 않으면 뷰는 데이터와 일치하지 않게 되므로, 이는 중요합니다. 이를 위해 JavaFX는 새로운 [Collection classes](http://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm)를 도입합니다.

컬렉션들 중 `ObservableList`를 사용할 겁니다. 다음 코드를 `MainApp` 클래스의 첫 부분에 추가해서 새로운 `ObservableList`를 만드세요. 또한 생성자도 추가해서 샘플 데이터와 public getter 메서드로 만들 겁니다:


##### MainApp.java

<pre class="prettyprint lang-java">

    // ... 다른 변수들 ...

	/**
	 * 연락처에 대한 observable 리스트
	 */
	private ObservableList&lt;Person&gt; personData = FXCollections.observableArrayList();

	/**
	 * 생성자
	 */
	public MainApp() {
		// 샘플 데이터를 추가한다
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
	 * 연락처에 대한 observable 리스트를 반환한다.
	 * @return
	 */
	public ObservableList&lt;Person&gt; getPersonData() {
		return personData;
	}
  
    // ... 클래스의 나머지 부분 ...
</pre>


*****

## PersonOverviewController ##

이제 마지막으로 테이블에서 데이터를 보여주기 위해 `PersonOverview.fxml`에 대한 컨트롤러가 필요합니다.

1. **뷰** 패키지에 `PersonOverviewController.java` 라는 일반 클래스를 만듭니다. (반드시 `PersonOverview.fxml` 파일과 같은 패키지에 넣어야 하며, 그렇지 않으면 SceneBuilder는 이 파일을 찾을 수 없습니다.)
2. 뷰에 있는 테이블과 라벨에 접근하기 위해 인스턴스 변수 몇 개를 추가할 겁니다. 필드와 메서드 몇 개에는 `@FXML` 이라는 특별한 어노테이션이 있습니다. 이는 fxml 파일이 private 필드와 prviate 메서드를 이용하기 위해 필요합니다. fxml 파일에서 모든 설정을 마치고 나서, 이 파일이 로드될 때 애플리케이션은 변수에 값을 자동으로 채웁니다:

<div class="alert alert-info">
**참고:** awt나 swing이 아니라 항상 **javafx imports** 구문을 사용한다는 것을 명심하세요!
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

    // 메인 애플리케이션 참조
    private MainApp mainApp;

    /**
     * 생성자.
     * initialize() 메서드 이전에 호출된다.
     */
    public PersonOverviewController() {
    }

    /**
     * 컨트롤러 클래스를 초기화한다.
     * fxml 파일이 로드되고 나서 자동으로 호출된다.
     */
    @FXML
    private void initialize() {
    	// 연락처 테이블의 두 열을 초기화한다.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * 참조를 다시 유지하기 위해 메인 애플리케이션이 호출한다.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // 테이블에 observable 리스트 데이터를 추가한다.
        personTable.setItems(mainApp.getPersonData());
    }
}
</pre>


설명을 하자면:

* fxml 파일이 이용하는 모든 필드와 메서드는 반드시 `@FXML` 어노테이션을 사용해야 합니다. 실은 이것들이 private일 경우에만 해당되지만 그래도 이게 더 낫습니다!
* `initialize()` 메서드는 fxml 파일이 로드되고 나서 자동으로 호출됩니다. 이 경우 모든 FXML 필드가 초기화되어 있습니다.
* 우리가 테이블 열에 설정한 `setCellValueFactory(...)`는 `Person` 객체의 어떤 필드를 각 열에 사용할지 결정하는 데 사용됩니다. `->` 화살표는 Java 8 기능인 *람다*를 가리킵니다. (다른 대안으로는 [PropertyValueFactory](http://docs.oracle.com/javase/8/javafx/api/)가 있지만 타입에 안전하지 않습니다).


<div class="alert alert-info">
  <p>
    이 예제에서는 테이블 열에 대해 `StringProperty` 값을 사용하고 있습니다. `IntegerProperty`나 `DoubleProperty`가 필요하다면 `setCellValueFactory(...)`는 반드시 추가로 `asObject()`를 이용해야 합니다:
  </p>
  <p>
  <pre>myIntegerColumn.setCellValueFactory(cellData -> 
      cellData.getValue().myIntegerProperty().<mark>asObject()</mark>);</pre>
  </p>
  <p>
    이는 JavaFX의 잘못된 디자인 결정으로 인해 꼭 필요합니다. (<a href="https://community.oracle.com/thread/2575601">이와 관련된 토론 보기</a>).
  </p>
</div>


### MainApp을 PersonOverviewController와 연결하기

The `setMainApp(...)` method must be called by the `MainApp` class. This gives us a way to access the `MainApp` object and get the list of `Persons` and other things. Replace the `showPersonOverview()` method with the following. It contains two additional lines:

`setMainApp(...)` 메서드는 반드시 `MainApp` 클래스에서 호출돼야 합니다. 이는 우리에게 `MainApp` 객체를 이용해서 `연락처` 리스트 외 다른 것들을 얻을 수 있게 해줍니다.


##### MainApp.java - 새로운 showPersonOverview() 메서드

<pre class="prettyprint lang-java">
/**
 * 상위 레이아웃 안에 연락처 요약(person overview)을 보여준다.
 */
public void showPersonOverview() {
    try {
        // 연락처 요약을 가져온다.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();
        
        // 연락처 요약을 상위 레이아웃 가운데로 설정한다.
        rootLayout.setCenter(personOverview);

<mark>        // 메인 애플리케이션이 컨트롤러를 이용할 수 있게 한다.
        PersonOverviewController controller = loader.getController();
        controller.setMainApp(this);</mark>

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>



*****


## 뷰를 컨트롤러에 걸기(Hook)

거의 다 왔습니다! 하지만 한 가지 빠뜨린 게 있습니다: 아직 `PersonOverview.fxml` 파일한테 어떤 컨트롤러를 이용할지, 어떤 요소가 컨트롤러의 어느 필드와 일치한지 말하지 않았습니다.

1. *SceneBuilder*로 `PersonOverview.fxml`을 엽니다.

2. 왼쪽 패널에서 *Controller* 그룹을 열어서 **controller class**를 `PersonOverviewController`로 선택합니다.   
![Set Controller Class](/assets/library/javafx-8-tutorial/part2/set-controller-class.png)

3. *Hierarchy* 그룹에서 `TableView`를 선택한 다음 *Code* 그룹에서 **fx:id** 값을 `personTable` 필드로 선택합니다.   
![Set TableView fx:id](/assets/library/javafx-8-tutorial/part2/set-tableview-fx-id.png)

4. 테이블뷰의 열도 마찬가지로 **fx:id** 값을 `firstNameColumn`과 `lastNameColumn`으로 각각 선택합니다.

5. 두 번째 열의 **각 라벨**도 마찬가지로 적절한 **fx:id** 값을 선택하세요.   
![Set Label fx:id](/assets/library/javafx-8-tutorial/part2/set-label-fx-id.png)

6. 중요: Eclipse로 돌아가서 **주소록 프로젝트 전체를 새로고침** 하세요 (F5). 왜냐하면 Eclipse는 가끔 Scene Builder에서 일어난 변화를 모를 때가 있습니다.


*****

## 애플리케이션 시작하기

지금 애플리케이션을 실행하면 이 글 맨 위 사진과 같은 화면을 보게 될 겁니다.

축하합니다!

*참고: 연락처를 선택하면 라벨이 업데이트되지 않습니다. 이를 위해 다음 3부에서 사용자 상호작용을 만들어 볼 겁니다.*


### 다음 할 일은?

[튜토리얼 3부](/library/javafx-8-tutorial/part3/)에서는 연락처를 추가, 삭제 그리고 변경하는 기능을 추가할 겁니다.


##### 흥미로운 자료들

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
