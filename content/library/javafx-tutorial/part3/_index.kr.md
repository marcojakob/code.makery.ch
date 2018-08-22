---
layout: article
title: "JavaFX 8 튜토리얼 - 3부: 사용자 상호작용"
date: 2016-10-30
slug: javafx-tutorial/kr/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-kr-part3.md
description: "JavaFX TableView 선택에 반응하게 만듭니다. 테이블에 추가, 변경 그리고 삭제하고, 사용자 입력을 검사합니다."
image: /assets/library/javafx-tutorial/part3/addressapp-part3.png
published: true
prettify: true
comments: true
sidebars:
- header: "차례"
  body:
  - text: "소개"
    link: /library/javafx-tutorial/kr/
    paging: Intro
  - text: "1부: Scene Builder"
    link: /library/javafx-tutorial/kr/part1/
    paging: 1
  - text: "2부: Model 그리고 TableView"
    link: /library/javafx-tutorial/kr/part2/
    paging: 2
  - text: "3부: 사용자 상호작용"
    link: /library/javafx-tutorial/kr/part3/
    paging: 3
    active: true
  - text: "4부: CSS 꾸미기"
    link: /library/javafx-tutorial/kr/part4/
    paging: 4
  - text: "5부: XML로 데이터 저장하기"
    link: /library/javafx-tutorial/kr/part5/
    paging: 5
  - text: "6부: 통계 차트"
    link: /library/javafx-tutorial/kr/part6/
    paging: 6
  - text: "7부: 배포"
    link: /library/javafx-tutorial/kr/part7/
    paging: 7
- header: "소스 코드 다운로드"
  body:
  - text: 3부 Eclipse 프로젝트 <em>(JDK 8u40 이상 필요)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-3.zip
    icon-css: fa fa-fw fa-download
languages:
  header: 언어
  collection: library
  item: javafx-tutorial
  part: part3
  active: kr
---

![Screenshot AddressApp Part 3](/assets/library/javafx-tutorial/part3/addressapp-part3.png)


## 3부 주제

* 테이블 **선택에 반응하기**.
* 연락처 **추가**, **변경** 그리고 **삭제** 버튼 구현하기.
* 커스텀 **팝업 다이얼로그**를 만들어서 연락처 변경하기
* **사용자 입력 검사하기**.


*****


## 테이블 선택에 반응하기

알다시피 우리는 아직 애플리케이션의 오른쪽 부분을 이용하지 않았습니다. 우리 계획은 테이블에서 사용자가 연락처를 선택하면 오른쪽에 자세한 정보를 보여주는 겁니다.

먼저 `PersonOverviewController`에 새로운 메서드를 추가해서 `연락처(Person)` 하나로 데이터를 label에 채워 봅시다.

새로운 메서드인 `showPersonDetails(Person person)`를 만드세요. `setText(...)`를 이용해서 person으로 모든 label에 정보를 채웁니다. 만일 파라미터 값이 `null`이면 모든 label 값이 지워집니다.

##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * 연락처의 자세한 정보를 보여주기 위해 모든 텍스트 필드를 채운다.
 * 만일 person이 null이면 모든 텍스트 필드가 지워진다.
 *
 * @param person the person or null
 */
private void showPersonDetails(Person person) {
    if (person != null) {
        // person 객체로 label에 정보를 채운다.
        firstNameLabel.setText(person.getFirstName());
        lastNameLabel.setText(person.getLastName());
        streetLabel.setText(person.getStreet());
        postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
        cityLabel.setText(person.getCity());

        // TODO: 생일을 String으로 변환해야 한다!
        // birthdayLabel.setText(...);
    } else {
        // person이 null이면 모든 텍스트를 지운다.
        firstNameLabel.setText("");
        lastNameLabel.setText("");
        streetLabel.setText("");
        postalCodeLabel.setText("");
        cityLabel.setText("");
        birthdayLabel.setText("");
    }
}
</pre>


### 생일을 String으로 변환하기

우리는 `생일(birthday)`을 `Label`에 설정할 수 없었습니다. 왜냐하면 `String`이 아니라 `LocalDate` 타입이기 때문입니다. 먼저 이 날짜 형식을 바꿔야 합니다.

우리는 `LocalDate`에서 `String`으로, 그리고 그 반대로 변환시키기 위해 `static` 헬퍼 클래스를 만들 것이며 이는 좋은 방법입니다. 이 클래스를 `DateUtil`이라 부를 것이며 별도의 패키지인 `ch.makery.address.util`에 만들겠습니다:


##### DateUtil.java

<pre class="prettyprint lang-java">
package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 날짜를 제어하는 헬퍼 함수들
 *
 * @author Marco Jakob
 */
public class DateUtil {

	/** 변환에 사용되는 날짜 패턴이다. 원하는 대로 바꿔도 좋다. */
	private static final String DATE_PATTERN = "dd.MM.yyyy";

	/** 날짜 변환기 */
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern(DATE_PATTERN);

	/**
     * 주어진 날짜를 String 타입으로 반환한다. 위에서 정의한
     * {@link DateUtil#DATE_PATTERN}이 사용된다.
     *
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * String을 {@link DateUtil#DATE_PATTERN}에 정의한 대로
     * {@link LocalDate} 객체로 변환한다.
     *
     * String이 변환되지 않으면 null을 반환한다.
     *
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String dateString) {
        try {
        	return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * 유효한 날짜인지 검사한다.
     *
     * @param dateString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {
    	// Try to parse the String.
    	return DateUtil.parse(dateString) != null;
    }
}
</pre>

<div class="alert alert-info">
**힌트:** `DATE_PATTERN`을 변경해서 날짜 형식을 바꿀 수 있습니다. 지원하는 형식은 <a class="alert-link" href="http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">DateTimeFormatter</a>를 보세요.
</div>


#### DateUtil 사용하기

이제 `PersonOverviewController`의 `showPersonDetails` 메서드에서 방금 만든 `DateUtil`을 사용해야 합니다. *TODO* 코드를 다음 내용으로 바꾸세요:

<pre class="prettyprint lang-java">
birthdayLabel.setText(DateUtil.format(person.getBirthday()));
</pre>


### 테이블 선택 감지하기(Listen)

사용자가 연락처를 선택할 때 이를 알아내려면 *그 변화를 감지*해야 합니다.

JavaFX에는 [`ChangeListener`](http://docs.oracle.com/javase/8/javafx/api/)라고 하는 인터페이스가 있으며 여기에 `changed(...)` 라는 메서드 하나가 있습니다. 이 메서드는 파라미터 3개를 가집니다: `observable`, `oldValue` 그리고 `newValue`

우리는 Java 8 *람다 표현식(lambda expression)*으로 `ChangeListener`를 만들 겁니다. `PersonOverviewController`의 `initialize()` 메서드에 코드 몇 줄을 추가해 봅시다. 결과는 다음과 같습니다:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
@FXML
private void initialize() {
    // 연락처 테이블의 두 열을 초기화한다.
    firstNameColumn.setCellValueFactory(
            cellData -> cellData.getValue().firstNameProperty());
    lastNameColumn.setCellValueFactory(
            cellData -> cellData.getValue().lastNameProperty());

    // 연락처 정보를 지운다.
    showPersonDetails(null);

    // 선택을 감지하고 그 때마다 연락처의 자세한 정보를 보여준다.
    personTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showPersonDetails(newValue));
}
</pre>

`showPersonDetails(null);`로 연락처 정보를 초기화합니다.

`personTable.getSelectionModel...`로 연락처 테이블의 *selectedItemProperty*를 가져온 다음 여기에 리스너를 추가합니다. 사용자가 테이블에서 연락처를 선택할 때마다 우리가 작성한 *람다 표현식(lambda expression*이 실행됩니다.

**여러분의 애플리케이션을 실행**해 보세요. 그리고 테이블 내 연락처를 선택하면 자세한 정보가 오른쪽에 나타나는지 확인하세요.

만일 잘 되지 않으면 여러분의 `PersonOverviewController` 클래스를 [PersonOverviewController.java](/assets/library/javafx-tutorial/part3/PersonOverviewController.java)와 비교할 수 있습니다.


*****

## 삭제 버튼

우리 사용자 인터페이스는 삭제 버튼이 있지만 아직 작동하지 않습니다. 버튼의 동작은 *Scene Builder*에서 선택할 수 있습니다. 컨트롤러의 어느 메서드라도 `@FXML` 어노테이션 (또는 public)이 붙어 있으면 *Scene Builder*에서 이용할 수 있습니다. 따라서 `PersonOverviewController` 클래스의 맨 아래에 삭제 메서드를 추가해 봅시다:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * 사용자가 삭제 버튼을 클릭하면 호출된다.
 */
@FXML
private void handleDeletePerson() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    personTable.getItems().remove(selectedIndex);
}
</pre>

이제 *SceneBuilder*로 `PersonOverview.fxml` 파일을 엽니다. *Delete* 버튼을 선택한 후 *Code* 그룹에서 **On Action** 값을 `handleDeletePerson`으로 고릅니다.

![On Action](/assets/library/javafx-tutorial/part3/handle-delete.png)

<div class="alert alert-info">
**기억하세요:** Scene Builder로 FXML 파일을 변경한 내용을 적용하기 위해 Eclipse 프로젝트를 새로고침해야 할 수 있습니다.
</div>



### 오류 제어하기

이 시점에서 애플리케이션을 실행하면 테이블에서 선택한 연락처가 삭제될 겁니다. 하지만 만일 여러분이 **아무런 연락처도 선택하지 않고 삭제 버튼을 클릭**하면 어떻게 될까요?

`ArrayIndexOutOfBoundsException`이 발생합니다. 왜냐하면 `-1` 인덱스에 있는 연락처는 삭제할 수 없기 때문입니다. `getSelectedIndex()` 메서드가 반환하는 `-1` 인덱스란 아무것도 선택되지 않았다는 의미입니다.

물론 이런 오류를 그냥 넘어가는 건 썩 좋은 게 아닙니다. 우리는 연락처를 삭제하기 전에 반드시 선택해야 한다는 사실을 사용자가 알게 만들어야 합니다. (아예 삭제 버튼을 비활성화시켜서 사용자에게 잘못된 일을 할 기회조차 주지 않는 게 더 낫습니다.)

`handleDeletePerson` 메서드를 좀 바꿔서, 사용자가 아무 연락처도 선택하지 않고 삭제 버튼을 누르면 간단한 팝업 다이얼로그를 보여줄 수 있습니다.


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * 사용자가 삭제 버튼을 클릭하면 호출된다.
 */
@FXML
private void handleDeletePerson() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        personTable.getItems().remove(selectedIndex);
    } else {
        // 아무것도 선택하지 않았다.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }
}
</pre>

<div class="alert alert-info">
다이얼로그 사용 방법에 대한 다른 예제가 필요하면 제 블로그 포스트인 <a class="alert-link" href="/blog/javafx-dialogs-official/">JavaFX Dialogs</a>를 읽으세요.
</div>


*****


## 추가와 변경 다이얼로그

추가와 변경과 같은 동작은 할 일이 조금 더 있습니다: 연락처 정보를 묻는 양식의 커스텀 다이얼로그 (이 말은 새로운 스테이지)가 필요합니다.


### 다이얼로그 디자인

1. 새로운 fxml 파일인 `PersonEditDialog.fxml`을 *view* 패키지에 만듭니다.   
![Create Edit Dialog](/assets/library/javafx-tutorial/part3/person-edit-dialog1.png)

2. 다음처럼 다이얼로그를 만들기 위해 `GridPane`, `Label`, `TextField` 그리고 `Button`을 사용하세요:   
![Edit Dialog](/assets/library/javafx-tutorial/part3/person-edit-dialog2.png)   


### 컨트롤러 만들기

`PersonEditDialogController.java` 라는 컨트롤러를 만듭니다:

##### PersonEditDialogController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

/**
 * 연락처 정보를 변경하는 다이얼로그
 *
 * @author Marco Jakob
 */
public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * 컨트롤러 클래스를 초기화한다.
     * 이 메서드는 fxml 파일이 로드된 후 자동으로 호출된다.
     */
    @FXML
    private void initialize() {
    }

    /**
     * 이 다이얼로그의 스테이지를 설정한다.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * 다이얼로그에서 변경될 연락처를 설정한다.
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    /**
     * 사용자가 OK를 클릭하면 true를, 그 외에는 false를 반환한다.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * 사용자가 OK를 클릭하면 호출된다.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * 사용자가 cancel을 클릭하면 호출된다.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * 텍스트 필드로 사용자 입력을 검사한다.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // 우편 번호를 int 타입으로 변환한다.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // 오류 메시지를 보여준다.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
</pre>

이 컨트롤러에서 주목할 몇 가지:

* `setPerson(...)` 메서드는 다른 클래스에서도 호출 가능하며 변경될 연락처를 설정합니다.
* 사용자가 OK 버튼을 클릭하면 `handleOk()` 메서드가 호출됩니다. 먼저 `isInputValid()` 메서드가 호출돼서 검사를 마칩니다. 검사 결과 이상이 없을 때에만 person 객체에 값이 채워집니다. 이 변화는 `setPerson(...)`에 전달한 person 객체에 직접 적용됩니다!
* `okClicked` boolean 값은 사용자가 OK나 Cancel 버튼 중 어느 것을 클릭했는지 알기 위해 사용됩니다.


### 뷰와 컨트롤러 연결하기

뷰 (FXML)와 컨트롤러를 만들고 나서 이 둘을 서로 연결해야 합니다:

1. `PersonEditDialog.fxml`을 엽니다.
2. 왼쪽 *Controller* 그룹에서 컨트롤러 클래스를 `PersonEditDialogController`로 선택합니다.
3. 모든 `TextField`의 **fx:id** 값을 컨트롤러의 상응하는 필드로 설정합니다.
4. 버튼 2개의 **onAction** 값을 상응하는 handler 메서드로 설정합니다.



### 다이얼로그 열기

`MainApp`에 메서드를 추가해서 연락처 변경 다이얼로그를 로드하고 보여줍니다.



##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * person의 자세한 정보를 변경하기 위해 다이얼로그를 연다.
 * 만일 사용자가 OK를 클릭하면 주어진 person에 내용을 저장한 후 true를 반환한다.
 *
 * @param person the person object to be edited
 * @return true if the user clicked OK, false otherwise.
 */
public boolean showPersonEditDialog(Person person) {
    try {
        // fxml 파일을 로드하고 나서 새로운 스테이지를 만든다.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // 다이얼로그 스테이지를 만든다.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // person을 컨트롤러에 설정한다.
        PersonEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPerson(person);

        // 다이얼로그를 보여주고 사용자가 닫을 때까지 기다린다.
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
</pre>

`PersonOverviewController`에 다음 메서드들을 추가합니다. 이들은 사용자가 *new*나 *edit* 버튼을 클릭할 때 `MainApp`의 `showPersonEditDialog(...)`를 호출할 겁니다.

##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * 사용자가 new 버튼을 클릭할 때 호출된다.
 * 새로운 연락처 정보를 넣기 위해 다이얼로그를 연다.
 */
@FXML
private void handleNewPerson() {
    Person tempPerson = new Person();
    boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
    if (okClicked) {
        mainApp.getPersonData().add(tempPerson);
    }
}

/**
 * 사용자가 edit 버튼을 클릭할 때 호출된다.
 * 선택한 연락처 정보를 변경하기 위해 다이얼로그를 연다.
 */
@FXML
private void handleEditPerson() {
    Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
    if (selectedPerson != null) {
        boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
        if (okClicked) {
            showPersonDetails(selectedPerson);
        }

    } else {
        // 아무것도 선택하지 않았다.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }
}
</pre>

Scene Builder로 `PersonOverview.fxml` 파일을 열어서 new와 edit 버튼의 *On Action* 값을 각각의 메서드로 고릅니다.


*****

## 끝!

지금쯤이면 *주소록 애플리케이션* 이 잘 동작해야 합니다. 연락처를 추가, 변경 그리고 삭제할 수 있어야 합니다. 게다가 연락처가 잘못되는 경우를 피하기 위해 텍스트 필드를 검사합니다.

이 애플리케이션의 개념과 구조가 여러분만의 JavaFX 애플리케이션 개발을 시작하게 도움이 됐으면 좋겠습니다! 즐기세요.


### 다음 할 일은?

[튜토리얼 4부](/library/javafx-tutorial/kr/part4/)에서는 CSS 스타일을 추가합니다.


##### 흥미로운 자료들

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
