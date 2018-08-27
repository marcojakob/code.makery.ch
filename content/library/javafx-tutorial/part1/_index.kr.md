+++
title = "1부: Scene Builder"
date = 2016-10-30
updated = 2016-11-03
description = "JavaFX 프로젝트를 만드는 방법을 배웁니다. 이 문서는 JavaFX로 주소록 애플리케이션을 디자인, 프로그래밍, 배포하는 7부로 구성된 튜토리얼 중 1부입니다."
image = "addressapp-part1.png"
prettify = true
comments = true 
commentsIdentifier = "/library/javafx-8-tutorial/kr/part1/"
aliases = [ 
  "/library/javafx-8-tutorial/kr/part1/"
]
weight = 1

[[sidebars]]
header = "소스 코드 다운로드"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> 1부 Eclipse 프로젝트 <em>(JDK 8u40 이상 필요)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-1.zip"
+++

![Screenshot AddressApp Part 1](addressapp-part1.png)

### 주제

* JavaFX 알아보기
* JavaFX 프로젝트 만들기
* Scene Builder로 사용자 인터페이스 디자인하기
* 모델-뷰-컨트롤러(MVC, Model-View-Controller) 패턴으로 애플리케이션을 구조적으로 만들기


*****


### 필요한 것

* 최신 [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (**JavaFX 8** 포함)
* Eclipse 4.4 또는 e(fx)clipse 플러그인을 포함한 그 이상 버전. 가장 쉬운 방법은 [e(fx)clipse website](http://efxclipse.bestsolution.at/install.html#all-in-one)에서 미리 포함되어 있는 버전을 받습니다. 다른 대안으로는 Eclipse에서 [update site](http://www.eclipse.org/efxclipse/install.html)를 이용할 수 있습니다.
* Gluon이 제공하는 [Scene Builder 8.0](http://gluonhq.com/products/scene-builder/) (왜냐하면 [Oracle은 오직 소스 코드 형태로만 제공](http://www.oracle.com/technetwork/java/javase/downloads/sb2download-2177776.html)하기 때문입니다.)


### Eclipse 설정

Eclipse가 JDK 8과 Scene Builder를 사용할 수 있게 해야 합니다:

1. Eclipse Preferences를 열어 *Java | Installed JREs*를 찾습니다.

2. *Add..*를 클릭한 후 *Standard VM*를 선택, JDK 8이 설치되어 있는 *디렉토리*를 고릅니다.

3. 다른 JRE나 JDK를 삭제합니다. 그러면 **JDK 8 하나만 남습니다**.   
![Preferences JDK](preferences-jdk.png)

4. *Java | Compiler*를 찾아 **Compiler compliance level을 1.8**로 설정합니다.   
![Preferences Compliance](preferences-compliance.png)

5. *JavaFX* 부분을 찾아 Scene Builder 실행 파일이 있는 경로를 지정합니다.   
![Preferences JavaFX](preferences-javafx.png)


### 유용한 정보

필요할 때 찾아 보세요:

* [Java 8 API](http://docs.oracle.com/javase/8/docs/api/) - 표준 Java 클래스의 JavaDoc
* [JavaFX 8 API](http://docs.oracle.com/javase/8/javafx/api/) - JavaFX 클래스의 JavaDoc
* [ControlsFX API](https://controlsfx.bitbucket.io/) - 추가 JavaFX 컨트롤을 제공하는 [ControlsFX project](http://fxexperience.com/controlsfx/)의 JavaDoc
* [Oracle's JavaFX Tutorials](http://docs.oracle.com/javase/8/javafx/get-started-tutorial/get_start_apps.htm) - Oracle 공식 JavaFX 튜토리얼

이제 시작해 봅시다!


*****


## JavaFX 프로젝트 만들기

e(fx)clipse를 설치하고 나서 Eclipse에서 *File | New | Other...*, *JavaFX Project*를 고릅니다.
프로젝트 이름(예: *AddressApp*)을 작성한 다음, *Finish*를 클릭합니다.

자동으로 생성되는 *application* 패키지가 있다면 삭제하세요.


### 패키지 만들기

시작부터 우리는 좋은 소프트웨어 디자인 원칙을 따를 겁니다. 가장 중요한 원칙이  [**모델-뷰-컨트롤러**](https://ko.wikipedia.org/wiki/%EB%AA%A8%EB%8D%B8-%EB%B7%B0-%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC)입니다. 이에 따라 우리의 코드를 세 단위로 나누고 각 패키지를 만듭니다 (src 디렉토리에서 마우스 오른쪽 클릭 후 *New... | Package* 선택):

* `ch.makery.address` - 컨트롤러 클래스의 *대부분* (= 비즈니스 로직)
* `ch.makery.address.model` - 모델 클래스
* `ch.makery.address.view` - 뷰

**참고:** 우리의 뷰 패키지는 뷰와 직접 관련있는 컨트롤러 몇 가지도 포함할 겁니다. 이들을 **뷰-컨트롤러**라고 부르겠습니다.


*****


## FXML 레이아웃 파일 만들기

사용자 인터페이스를 만드는 방법은 2가지로 XML 파일을 이용하거나 모든 것을 Java로 프로그래밍하는 겁니다. 인터넷에서 찾아 보면 2가지 모두 사용하는 걸 발견할 수 있습니다. 우리는 XML (.fxml로 끝나는)을 이용할 겁니다. 저는 컨트롤러와 뷰를 서로 분리하는 것이 깔끔한 방법이라고 생각합니다. 게다가 Scene Builder를 사용하면 XML를 시각적으로 편집할 수 있습니다. 즉 우리는 XML을 직접 편집하지 않겠다는 말입니다.

뷰 패키지에 마우스 오른쪽 버튼을 클릭해서 `PersonOverview` 라는 새로운 *FXML Document*를 만듭니다.   

![New FXML Document](new-fxml-document.png)

![New PersonOverview](new-person-overview.png)



*****


## Scene Builder로 디자인하기

<div class="alert alert-warning">
  <strong>참고:</strong> 여기까지 잘 되지 않으면 소스 코드를 받아서 해 보세요.
</div>

`PersonOverview.fxml` 파일에서 마우스 오른쪽 버튼 클릭 후 *Open with Scene Builder*를 클릭합니다. 그러면 Scene Builder가 열리고 *AnchorPane* 하나만 보입니다 (좌측 Hierarchy 그룹).

(Scene Builder가 열리지 않으면 *Window | Preferences | JavaFX*에서 Scene Builder가 설치된 경로를 정확히 설정합니다).

1. Hierarchy 그룹에서 *Anchor Pane*를 선택하고 우측 Layout 그룹에서 Size를 조정합니다:   
![Anchor Pane Size](anchor-pane-size.png)

2. Library 그룹에서 *Split Pane (Horizontal Flow)*을 드래그해서 화면에 추가합니다. *Hierarchy* 그룹의 *Split Pane*에 마우스 오른쪽 버튼 클릭 후 *Fit to Parent*를 클릭합니다.   
![Fit to Parent](fit-to-parent.png)

3. *TableView*를 드래그해서 (*Controls* 아래) *SplitPane*의 왼쪽에 배치합니다. TableView를 선택해서(열이 아닌 테이블 자체) 다음처럼 Layout 그룹의 Constraints를 설정합니다. 여러분은 *AnchorPane* 안쪽에서 언제나 상하좌우로 앵커를 설정할 수 있습니다 ([more information on Layouts](http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm)).   
![TableView Anchors](table-view-anchors.png)

4. *Preview | Show Preview in Window* 메뉴에서 잘 되는지 확인하세요. 윈도우 크기를 늘리거나 줄여 보세요. 앵커를 설정한 대로 TableView는 윈도우와 함께 크기가 바뀝니다.

5. Properties 그룹에서 각 열의 텍스트를 "First Name"과 "Last Name"으로 바꿉니다.   
![Column Texts](column-texts.png)

6. *TableView*를 선택한 후 *Column Resize Policy* (Properties 그룹에서) 값을 *constrained-resize*로 고릅니다. 이 값은 열이 언제나 빈 공간까지 차지하게 합니다.   
![Column Resize Policy](column-resize-policy.png)

7. 오른쪽에 *Label*을 추가해서 텍스트를 "Person Details"로 합니다 (힌트: *Label*를 쉽게 찾으려면 검색하세요). 앵커로 레이아웃을 조정하세요.   
![Person Details Label](person-details-label.png)

8. 오른쪽에 *GridPane*을 추가한 후 앵커로 레이아웃을 조정합니다 (위쪽, 오른쪽, 왼쪽).    
![GridPane Layout](grid-pane-layout.png)

9. 셀에 Label을 추가합니다. (*참고: GridPane에 행을 추가하려면 존재하는 행 숫자를 선택한 후 (노란색으로 바뀜) 마우스 오른쪽 버튼을 클릭해서 "Add Row"를 클릭하세요.*)   
![Add labels](add-labels.png)

10. 하단에 *ButtonBar*를 추가한 다음 버튼 3개를 넣으세요. *ButtonBar*에 앵커를 설정하면 (오른쪽과 아래쪽) 오른쪽 영역에 배치됩니다.   
![Button Group](button-group.png)

11. 이제 다음 화면처럼 보일 겁니다. *Preview* 메뉴를 이용해서 크기를 늘리거나 줄여 보세요.   
![Preview](scene-builder-preview.png)



*****


## 메인 애플리케이션 만들기

우리는 다른 FXML 파일을 만들어서 이 상위 레이아웃이 메뉴바와 방금 만든 `PersonOverview.fxml`을 포함하게 할 겁니다.

1. 뷰 패키지에 `RootLayout.fxml` 이라는 새로운 *FXML Document*를 만듭니다. 이번에는 *BorderPane*을 상위 레이아웃으로 고릅니다.   
![New RootLayout](new-root-layout.png)

2. `RootLayout.fxml`을 Scene Builder로 엽니다.

3. *BorderPane*의 *Pref Width*를 600으로, *Pref Height*를 400으로 설정하세요.   
![RootLayout Size](root-layout-size.png)

4. 상단에 *MenuBar*를 추가하세요. 지금은 메뉴 기능을 구현하지 않을 겁니다.   
![MenuBar](menu-bar.png)


### JavaFX 메인 클래스

이제 우리는 **메인 Java 클래스**를 만들어서 애플리케이션을 시작하고 `PersonOverview.fxml`을 가운데에 추가할 겁니다.

1. 프로젝트에서 마우스 오른쪽 버튼 클릭 후, *New | Other...*를 선택하고, *JavaFX Main Class*를 선택합니다.   
![New JavaFX Main Class](new-main-class.png)

2. 이 클래스 이름을 `MainApp`으로 하고 컨트롤러 패키지인 `ch.makery.address`에 넣습니다 (참고: 이 패키지는 하위 패키지인 `view`와 `model`의 부모 패키지입니다).   
![New JavaFX Main Class](new-main-class2.png)


생성된 `MainApp.java` 클래스는 `Application`을 확장(상속)하고 메서드 2개를 가집니다. 이게 바로 우리가 JavaFX 애플리케이션을 시작하는 데 필요한 기본적인 구조입니다. 가장 중요한 부분은 `start(Stage primaryStage)` 메서드입니다. 이 메서드는 `main` 메서드에서 애플리케이션이 `실행되면` 자동으로 호출됩니다.

보다시피 `start(...)` 메서드는 파라미터로 `Stage`를 받습니다. 다음 그림은 모든 JavaFX 애플리케이션의 구조입니다:

![New FXML Document](javafx-hierarchy.png)
*그림 출처: http://www.oracle.com*

**연극과 비슷합니다**: `Stage`는 `Window`처럼 경계, 최소화, 최대화, 그리고 닫기 버튼을 가지는 메인 컨테이너입니다. 여러분은 `Stage` 안에 `Scene`을 추가할 수 있는데, 이는 물론 다른 `Scene`으로 바뀔 수 있습니다. `Scene` 안에는 `AnchorPane`, `TextBox` 등의 실제 JavaFX 노드가 추가됩니다.

이에 대한 추가 정보는 [Working with the JavaFX Scene Graph](http://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm)에서 볼 수 있습니다.


*****

`MainApp.java`를 열고 다음 코드로 바꾸세요:

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
     * 상위 레이아웃을 초기화한다.
     */
    public void initRootLayout() {
        try {
            // fxml 파일에서 상위 레이아웃을 가져온다.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // 상위 레이아웃을 포함하는 scene을 보여준다.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * 메인 스테이지를 반환한다.
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

주석은 여러분에게 어떻게 되고 있는지 힌트를 줄 겁니다.

지금 애플리케이션을 실행하면 이 글 맨 위 사진과 같은 화면을 보게 될 겁니다.


### 자주 묻는 질문

만일 여러분이 지정한 `fxml` 파일을 JavaFX가 찾지 못하면 다음 오류 메시지를 볼 수 있을 겁니다:

`java.lang.IllegalStateException: Location is not set.`

이 문제를 해결하려면 `fxml` 파일 이름이 정확한지 두 번 확인하세요!

<div class="alert alert-warning">
  그래도 안 되면 소스 코드를 받아서 해 보세요.
</div>


*****

### 다음 할 일은?

[튜토리얼 2부](/kr/library/javafx-tutorial/part2/)에서는 우리 애플리케이션에 데이터와 기능을 몇 가지 추가해 볼 겁니다.


##### 흥미로울 수 있는 정보

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
