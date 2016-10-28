---
layout: article
title: "JavaFX 8 튜토리얼 - 6부: 통계 차트"
date: 2014-05-09 00:00
updated: 2015-03-12 00:00
slug: javafx-8-tutorial/part6
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-kr-part6.md
description: "JavaFX 바 차트를 만드는 방법을 배웁니다."
image: /assets/library/javafx-8-tutorial/part6/addressapp-part6.png
published: true
prettify: true
comments:
  identifier: /java/javafx-8-tutorial-part6/
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
    active: true
  - text: "7부: 배포"
    link: /library/javafx-8-tutorial/part7/
    paging: 7
- header: "소스 코드 다운로드"
  body:
  - text: 6부 Eclipse 프로젝트 <em>(JDK 8u40 이상 필요)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-6.zip
    icon-css: fa fa-fw fa-download
languages:
  header: 언어
  collection: library
  item: javafx-8-tutorial
  part: part6
  active: kr
---

![Screenshot AddressApp Part 6](/assets/library/javafx-8-tutorial/part6/addressapp-part6.png)


## 6부 주제

* **통계 차트**를 만들어 생일 분포 보여주기


*****

## 생일 통계

AddressApp의 모든 연락처에는 생일이 있습니다. 생일을 기념할 때 이에 대한 통계를 보여주면 더 좋을 것 같습니다.

우리는 **바 차트**를 사용할 겁니다. 각 바는 연락처에 있는 생일을 월별로 보여줍니다.

## FXML View 통계

1. `ch.makery.address.view` 패키지 (* 마우스 오른쪽 클릭 | New | other... | New FXML Document*)에 `BirthdayStatistics.fxml` 파일을 만듭니다.   
![Birthday Statistics FXML](/assets/library/javafx-8-tutorial/part6/birthday-statistics-fxml.png)

2. Scene Builder로 `BirthdayStatistics.fxml` 파일을 엽니다.

3. 상위 레이아웃인 `AnchorPane`을 선택합니다. *Layout* 그룹에서 *Pref Width* 값을 620로, *Pref Height* 값을 450으로 설정합니다.

4. `AnchorPane`에 `BarChart*를 추가합니다.`

5. `BarChart`에서 마우스 오른쪽 클릭 후 *Fit to Parents*를 선택합니다.

6. FXML 파일을 저장합니다. Eclipse에서 프로젝트를 새로고침합니다.

Scene Builder로 돌아가기 전에 먼저 컨트롤러를 만들고 `MainApp`에서 모든 것을 연결할 겁니다.


## 통계 컨트롤러

뷰 패키지인 `ch.makery.address.view`에 새로운 Java 클래스 `BirthdayStatisticsController.java`를 만듭니다.

무슨 코드인지 설명하기 전에 컨트롤러 클래스 전체를 보여드리겠습니다:


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
 * 생일 통계 뷰에 대한 컨트롤러
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
     * 컨트롤러 클래스를 초기화한다. 이 메서드는 FXML 파일이 로드된 후 자동으로 호출된다.
     */
    @FXML
    private void initialize() {
        // 영어 월 이름을 배열로 가져온다.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // 리스트로 변환하고 나서 ObservableList에 추가한다.
        monthNames.addAll(Arrays.asList(months));

        // 수평축에 월 이름을 카테고리로 할당한다.
        xAxis.setCategories(monthNames);
    }

    /**
     * 통계에 보여줄 연락처를 설정한다.
     *
     * @param persons
     */
    public void setPersonData(List&lt;Person> persons) {
    	// 연락처 생일의 월 개수를 누적한다.
        int[] monthCounter = new int[12];
        for (Person p : persons) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series&lt;String, Integer> series = new XYChart.Series&lt;>();

        // 월별로 XYChart.Data 객체를 만든다. series에 추가한다.
        for (int i = 0; i &lt; monthCounter.length; i++) {
        	series.getData().add(new XYChart.Data&lt;>(monthNames.get(i), monthCounter[i]));
        }

        barChart.getData().add(series);
    }
}
</pre>


#### 원리

1. 컨트롤러는 FXML 파일에서 엘리먼트 2개에 접근해야 합니다:
   * `barChart`: `String`과 `Integer` 타입을 가집니다. `String`은 xAxis에서 월로 사용되고 `Integer`는 그 달의 연락처 개수에 사용됩니다.
   * `xAxis`: 이걸로 월 String을 추가할 겁니다.

2. `initialize()` 메서드는 모든 월 리스트를 xAxis의 값으로 채웁니다.

3. `setPersonData(...)` 메서드는 `MainApp` 클래스에서 연락처 데이터를 설정하기 위해 이용될 겁니다. 모든 연락처를 순회해서 월별로 생일 개수를 셉니다. 그 후 series 데이터에 모든 달의 `XYChart.Data`를 추가합니다. 각 `XYChart.Data` 객체는 차트에서 바 하나를 나타냅니다.


*****

## 뷰와 컨트롤러 연결하기

1. Scene Builder로 `BirthdayStatistics.fxml` 파일을 엽니다.

2. *Controller* 그룹에서 `BirthdayStatisticsController`를 controller로 설정합니다.

3. `BarChart`를 선택 후 fx:id 프로퍼티로 `barChart`를 고릅니다 (*Code* 그룹에서).

4. `CategoryAxis`를 선택하고 fx:id 프로퍼티로 `xAxis`를 고릅니다.   
![Category Axis](/assets/library/javafx-8-tutorial/part6/category-axis.png)

5. `BarChart`의 타이틀 (*Properties* 그룹에서)을 추가할 수 있습니다.



*****


## 뷰/컨트롤러를 MainApp으로 연결하기

우리는 *생일 통계*의 동일한 메커니즘을 *연락처 변경 다이얼로그*에 사용할 겁니다.

다음 메서드를 `MainApp` 클래스에 추가하세요:


<pre class="prettyprint lang-java">
/**
 * 생일 통계를 보여주기 위해 다이얼로그를 연다.
 */
public void showBirthdayStatistics() {
    try {
        // FXML 파일을 불러와서 팝업의 새로운 Stage를 만든다.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Birthday Statistics");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // 연락처를 컨트롤러에 설정한다.
        BirthdayStatisticsController controller = loader.getController();
        controller.setPersonData(personData);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>

모든 준비가 끝났습니다. 하지만 실제로 새로운 `showBirthdayStatistics()` 메서드를 호출하지 않습니다. 운이 좋게도 `RootLayout.fxml`에는 메뉴가 있습니다.


### 생일 차트 메뉴 보여주기

`RootLayoutController`에 *show birthday statistics* 메뉴 아이템 클릭을 제어하는 다음 메서드를 추가합니다:

<pre class="prettyprint lang-java">
/**
 * 생일 차트를 연다.
 */
@FXML
private void handleShowBirthdayStatistics() {
  mainApp.showBirthdayStatistics();
}
</pre>

이제 Scene Builder로 `RootLayout.fxml` 파일을 엽니다. *Show Statistics* `MenuItem`을 포함한 *Statistics* `Menu`를 만듭니다:

![Show Statistics Menu](/assets/library/javafx-8-tutorial/part6/show-statistics-menu.png)

*Show Statistics* `MenuItem`을 선택하고 `On Action`을 `handleShowBirthdayStatistics`로 고릅니다 (*Code* 그룹에서).

![Show Statistics On Action](/assets/library/javafx-8-tutorial/part6/show-statistics-on-action.png)

Eclipse로 가서 프로젝트를 새로고침한 후 **테스트 하세요**.


*****

## JavaFX 차트에 대한 자세한 정보

공식 Oracle 튜토리얼에 자세한 정보가 나와 있습니다: [Working with JavaFX Charts](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/charts.htm).


### 다음 할 일은?

마지막 [튜토리얼 7부](/library/javafx-8-tutorial/part7/)에서는 마침내 우리 애플리케이션을 배포할 겁니다 (즉 패키징과 전달)


##### 흥미로운 자료들

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
