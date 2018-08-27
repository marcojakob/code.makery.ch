+++
title = "5부: XML로 데이터 저장하기"
date = 2016-10-30
description = "JAXB를 이용해서 데이터를 XML로 저장합니다. JavaFX FileChooser와 JavaFX Menu 사용 방법을 배웁니다."
image = "addressapp-part5.png"
prettify = true
comments = true 
commentsIdentifier = "/library/javafx-8-tutorial/kr/part5/"
aliases = [ 
  "/library/javafx-8-tutorial/kr/part5/"
]
weight = 5

[[sidebars]]
header = "소스 코드 다운로드"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> 5부 Eclipse 프로젝트 <em>(JDK 8u40 이상 필요)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-5.zip"
+++

![Screenshot AddressApp Part 5](addressapp-part5.png)


## 5부 주제

* **XML로 데이터 유지하기(Persisting)**
* JavaFX **FileChooser** 사용하기
* JavaFX **Menu** 사용하기
* 마지막으로 불러온 파일의 경로를 **사용자 환경설정**에 저장하기



*****

현재 우리 주소록 애플리케이션의 데이터는 메모리에만 존재합니다. 애플리케이션을 닫을 때마다 데이터는 사라집니다. 지금부터 데이터를 지속적으로 저장하는 방법에 대해 생각해 봅시다.


## 사용자 환경설정 저장하기

Java는 애플리케이션의 상태를 `Preferences` 라고 하는 클래스를 사용해서 저장할 수 있습니다. 운영체제에 따라 이 `Preferences`는 다른 곳에 저장됩니다 (예: Windows의 경우 레지스트리 파일).

우리는 `Preferences`를 사용해서 주소록 전체를 저장할 수 없을 겁니다. 대신 **애플리케이션의 간단한 상태를 저장**할 수 있습니다. 예를 들면 **마지막으로 불러온 파일의 경로**가 있습니다. 이런 식으로 한다면, 사용자가 애플리케이션을 재시작할 때마다 애플리케이션의 마지막 상태를 불러올 수 있을 겁니다.

다음 메서드 2개는 Preferences를 저장하고 불러오는 일을 합니다. 여러분의 `MainApp` 클래스의 맨 끝에 추가하세요:

##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * 연락처 파일 환경설정을 반환한다.
 * 즉 파일은 마지막으로 열린 것이고, 환경설정은 OS 특정 레지스트리로부터 읽는다.
 * 만일 preference를 찾지 못하면 null을 반환한다.
 *
 * @return
 */
public File getPersonFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    String filePath = prefs.get("filePath", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

/**
 * 현재 불러온 파일의 경로를 설정한다. 이 경로는 OS 특정 레지스트리에 저장된다.
 *
 * @param file the file or null to remove the path
 */
public void setPersonFilePath(File file) {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    if (file != null) {
        prefs.put("filePath", file.getPath());

        // Stage 타이틀을 업데이트한다.
        primaryStage.setTitle("AddressApp - " + file.getName());
    } else {
        prefs.remove("filePath");

        // Stage 타이틀을 업데이트한다.
        primaryStage.setTitle("AddressApp");
    }
}
</pre>


##  XML로 데이터 유지하기(Persisting)

### 왜 XML인가?

데이터를 유지하는 가장 흔한 방법들 중 하나는 데이터베이스를 이용하는 겁니다. 데이터베이스에는 대게 관계 있는 데이터 (테이블처럼)가 들어있지만 우리는 이 데이터를 객체로 저장해야 합니다. 이를 [객체-관계 임피던스 불일치](http://wikipedia.org/wiki/Object-relational_impedance_mismatch)라고 합니다. 객체를 관계형 데이터베이스 테이블과 일치시키는 건 꽤 힘든 일입니다. 이를 도와주는 프레임워크 몇 개가 있지만 (예: [Hibernate](http://www.hibernate.org/)), 이마저도 설정하는 게 어렵습니다.

우리 데이터 모델은 간단하기 때문에 XML을 사용하는 건 매우 쉽습니다. [JAXB](https://jaxb.java.net/) (**J**ava **A**rchitecture for **X**ML **B**inding) 라고 하는 라이브러리를 사용할 겁니다. JAXB는 코드 몇 줄로 XML을 다음처럼 만들어 냅니다:

##### XML 출력 예

<pre class="prettyprint lang-xml">
&lt;persons&gt;
    &lt;person&gt;
        &lt;birthday&gt;1999-02-21&lt;/birthday&gt;
        &lt;city&gt;some city&lt;/city&gt;
        &lt;firstName&gt;Hans&lt;/firstName&gt;
        &lt;lastName&gt;Muster&lt;/lastName&gt;
        &lt;postalCode&gt;1234&lt;/postalCode&gt;
        &lt;street&gt;some street&lt;/street&gt;
    &lt;/person&gt;
    &lt;person&gt;
        &lt;birthday&gt;1999-02-21&lt;/birthday&gt;
        &lt;city&gt;some city&lt;/city&gt;
        &lt;firstName&gt;Anna&lt;/firstName&gt;
        &lt;lastName&gt;Best&lt;/lastName&gt;
        &lt;postalCode&gt;1234&lt;/postalCode&gt;
        &lt;street&gt;some street&lt;/street&gt;
    &lt;/person&gt;
&lt;/persons&gt;
</pre>




### JAXB 사용하기

JAXB는 이미 JDK에 포함되어 있습니다. 즉 라이브러리를 추가로 포함시키지 않아도 된다는 의미입니다.

JAXB는 주요 기능 2개를 제공합니다: Java 객체를 XML로 **마샬링(marshal)**하거나 XML을 다시 Java 객체로 되돌리는 **역 마샬링(unmarshal)**입니다.

JAXB로 이런 변환을 하려면 먼저 모델을 준비해야 합니다.

####  모델 준비하기

우리가 저장하고 싶은 데이터는 `MainApp` 클래스의 `personData` 변수에 있습니다. JAXB는 `@XmlRootElement` 어노테이션을 붙여서 최상위 클래스가 되어야 합니다. `personData`는 `ObservableList`이기 때문에 어느 어노테이션도 붙일 수 없습니다. 그러니 다른 클래스를 만들어서 XML로 저장하기 위해 `연락처` 리스트를 담는 데에만 사용할 겁니다.

새로운 클래스 이름은 `PersonListWrapper`이며 `ch.makery.address.model` 패키지에 넣습니다.


##### PersonListWrapper.java

<pre class="prettyprint lang-java">
package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 연락처 리스트를 감싸는 헬퍼 클래스이다.
 * XML로 저장하는 데 사용된다.
 *
 * @author Marco Jakob
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

	private List&lt;Person> persons;

	@XmlElement(name = "person")
	public List&lt;Person> getPersons() {
		return persons;
	}

	public void setPersons(List&lt;Person> persons) {
		this.persons = persons;
	}
}
</pre>

어노테이션 2개에 주목하세요:

* `@XmlRootElement`는 상위 엘리먼트의 이름을 정의합니다.
* `@XmlElement`는 엘리먼트에 선택적인 이름을 정의할 수 있습니다.


#### JAXB로 데이터를 읽고 쓰기

`MainApp` 클래스가 연락처 데이터를 읽고 쓰게 만들 겁니다. `MainApp.java`의 맨 끝에 다음 메서드 2개를 추가합니다:


<pre class="prettyprint lang-java">
/**
 * 지정한 파일로부터 연락처 데이터를 가져온다. 현재 연락처 데이터로 대체된다.
 *
 * @param file
 */
public void loadPersonDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // 파일로부터 XML을 읽은 다음 역 마샬링한다.
        PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

        personData.clear();
        personData.addAll(wrapper.getPersons());

        // 파일 경로를 레지스트리에 저장한다.
        setPersonFilePath(file);

    } catch (Exception e) { // 예외를 잡는다
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());

        alert.showAndWait();
    }
}

/**
 * 현재 연락처 데이터를 지정한 파일에 저장한다.
 *
 * @param file
 */
public void savePersonDataToFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // 연락처 데이터를 감싼다.
        PersonListWrapper wrapper = new PersonListWrapper();
        wrapper.setPersons(personData);

        // 마샬링 후 XML을 파일에 저장한다.
        m.marshal(wrapper, file);

        // 파일 경로를 레지스트리에 저장한다.
        setPersonFilePath(file);
    } catch (Exception e) { // 예외를 잡는다.
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not save data");
        alert.setContentText("Could not save data to file:\n" + file.getPath());

        alert.showAndWait();
    }
}
</pre>

마샬링/역 마샬링이 준비되었습니다. 이제 이 기능을 사용하기 위해 save/load 메뉴를 만듭시다.


## 메뉴 동작 제어하기

`RootLayout.fxml`에는 이미 메뉴가 있지만 아직까지 사용한 적이 없습니다. 메뉴에 동작을 추가하기 전에 먼저 모든 메뉴 항목을 만들겠습니다.

Scene Builder로 `RootLayout.fxml` 파일을 열어서 *library* 그룹의 menu item을 *hierarchy* 그룹의 `MenuBar`에 추가합니다. **New**, **Open...**, **Save**, **Save As...**, 그리고 **Exit** 메뉴를 만드세요.

![Add Menu Items](add-menu-items.png)

힌트: *Properties* 그룹 아래 *Accelerator* 설정을 사용해서 메뉴 항목에 단축키를 설정할 수 있습니다.


### RootLayoutController

메뉴 동작을 제어하기 위해 새로운 컨트롤러 클래스가 필요합니다. 컨트롤러 패키지인 `ch.makery.address.view`에 `RootLayoutController` 클래스를 만듭니다.

다음 내용을 컨트롤러에 추가합니다:


##### RootLayoutController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import ch.makery.address.MainApp;

/**
 * 상위 레이아웃에 대한 컨트롤러이다. 상위 레이아웃은 메뉴바와 JavaFX 엘리먼트가 들어갈 공간을 포함한 기본적인 레이아웃을 제공한다.
 *
 * @author Marco Jakob
 */
public class RootLayoutController {

    // 메인 애플리케이션 참조
    private MainApp mainApp;

    /**
     * 참조를 다시 유지하기 위해 메인 애플리케이션이 호출한다.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * 비어 있는 주소록을 만든다.
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * FileChooser를 열어서 사용자가 가져올 주소록을 선택하게 한다.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // 확장자 필터를 설정한다.
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Save File Dialog를 보여준다.
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * 현재 열려 있는 파일에 저장한다.
     * 만일 열려 있는 파일이 없으면 "save as" 다이얼로그를 보여준다.
     *
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * FileChooser를 열어서 사용자가 저장할 파일을 선택하게 한다.
     */
    @FXML
    private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// 확장자 필터를 설정한다.
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Save File Dialog를 보여준다.
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// 정확한 확장자를 가져야 한다.
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.savePersonDataToFile(file);
		}
	}

    /**
     * About 다이얼로그를 보여준다.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("AddressApp");
    	alert.setHeaderText("About");
    	alert.setContentText("Author: Marco Jakob\nWebsite: http://code.makery.ch");

    	alert.showAndWait();
    }

    /**
     * 애플리케이션을 닫는다.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
</pre>


#### FileChooser

`RootLayoutController`의 메서드에서 사용한 `FileChooser`에 주목하세요. 먼저 `FileChooser` 클래스의 객체가 만들어졌습니다. 그러고 나서 확장자 필터가 추가되어 `.xml`로 끝나는 파일만 보여줍니다. 마지막으로 Primary Stage 위에 표시됩니다.

만일 사용자가 파일을 선택하지 않고 다이얼로그를 닫으면 `null`이 반환됩니다. 그 외에는 우리가 선택한 파일을 가져와서 `MainApp`의 `loadPersonDataFromFile(...)`이나 `savePersonDataToFile(...)`에 전달할 수 있습니다.


### FXML 뷰를 컨트롤러에 연결하기

1. Scene Builder로 `RootLayout.fxml`을 엽니다. *Controller* 그룹에서 Controller 클래스로 `RootLayoutController`를 선택합니다.

2. *Hierarchy* 그룹으로 돌아가서 메뉴 아이템을 선택합니다. 여러분은 *Code* 그룹 아래 **On Action**에서 사용 가능한 모든 컨트롤러 메서드를 보게 될 겁니다. 메뉴 아이템별로 그에 상응하는 메서드를 고릅니다.   
![Menu Actions](menu-actions.png)

3. 모든 메뉴 아이템에 반복합니다.

4. Scene Builder를 닫고 프로젝트에서 **새로고침 (F5)**을 누릅니다. 그러면 Scene Builder에서 했던 작업이 적용될 겁니다.


### MainApp과 RootLayoutController 연결하기

`RootLayoutController`는 `MainApp`의 참조가 필요합니다. 우리는 아직까지 `RootLayoutController`로 참조를 전달하지 않았습니다.

`MainApp` 클래스를 열어서 `initRootLayout()` 메서드를 다음 코드로 바꿉니다.

<pre class="prettyprint lang-java">
/**
 * 상위 레이아웃을 초기화하고 마지막으로 열었던 연락처 파일을 가져온다.
 */
public void initRootLayout() {
    try {
        // fxml 파일에서 상위 레이아웃을 가져온다.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class
                .getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        // 상위 레이아웃을 포함하는 scene을 보여준다.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // 컨트롤러한테 MainApp 접근 권한을 준다.
        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // 마지막으로 열었던 연락처 파일을 가져온다.
    File file = getPersonFilePath();
    if (file != null) {
        loadPersonDataFromFile(file);
    }
}
</pre>

2가지 변화에 주목하세요: *컨트롤러한테 MainApp 접근 권한을 준다* 라인과 마지막 라인 3줄인 *마지막으로 열었던 연락처 파일을 가져온다*


### 테스트

애플리케이션을 시험 운전해 보려면 메뉴로 연락처 데이터를 파일에 저장할 수 있어야 합니다.

에디터로 `xml` 파일을 열 때 생일이 비어 있는 `<birthday/>` 처럼 정확히 저장되지 않는다는 것을 알게 될 겁니다. 왜냐하면 JAXB는 `LocalDate`를 어떻게 XML로 변환해야 하는지 모르기 때문입니다. 이를 위해 커스텀 `LocalDateAdapter`가 필요합니다.

`ch.makery.address.util`에 새로운 클래스인 `LocalDateAdapter`를 만들고 다음 내용을 추가합니다:

##### LocalDateAdapter.java

<pre class="prettyprint lang-java">
package ch.makery.address.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * LocalDate와 ISO 8601 간 변환을 하는 JAXB 어댑터
 * String은 '2012-12-03' 같은 날짜를 나타낸다.
 *
 * @author Marco Jakob
 */
public class LocalDateAdapter extends XmlAdapter&lt;String, LocalDate> {

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}
}
</pre>

`Person.java`를 열어서 `getBirthday()` 메서드에 추가합니다:

<pre class="prettyprint lang-java">
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public LocalDate getBirthday() {
    return birthday.get();
}
</pre>

이제 다시 테스트합니다. XML 파일을 저장하고 열어 보세요. 재시작을 하고 나면 자동으로 마지막에 열었던 파일을 가져오게 될 겁니다.



## 원리


이 모든 과정이 어떻게 되는 건지 살펴보죠:

1. 애플리케이션은 `MainApp`의 `main(...)` 메서드를 이용해서 시작됩니다.
2. 생성자인 `public MainApp()`이 호출되어 샘플 데이터를 넣습니다.
3. `MainApp`의 `start(...)` 메서드가 호출되고 `RootLayout.fxml`의 상위 레이아웃을 초기화하기 위해 `initRootLayout()`을 호출합니다. FXML 파일은 어느 컨트롤러가 뷰에 사용되고 연결되는지 정보를 가집니다.
4. `MainApp`은 FXML Loader로부터 `RootLayoutController`를 가져와서 자기 자신에 대한 참조를 컨트롤러로 전달합니다. 컨트롤러는 이 참조를 가지고 나중에 `MainApp`의 public 메서드를 이용할 수 있습니다.
5. `initRootLayout()` 메서드의 맨 끝에서 우리는 `Preferences`로부터 *마지막으로 열었던 연락처 파일* 을 가져옵니다. 만일 `Preferences`가 XML 파일에 대해 알면 이 파일로부터 데이터를 가져옵니다. 이는 생성자의 샘플 데이터를 덮어쓰게 됩니다.


### 다음 할 일은?

[튜토리얼 6부](/kr/library/javafx-tutorial/part6/)에서는 생일 통계 차트를 추가할 겁니다.


##### 흥미로운 자료들

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
