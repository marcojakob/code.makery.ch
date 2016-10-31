---
layout: article
title: "JavaFX 8 튜토리얼 - 4부: CSS 꾸미기"
date: 2016-10-30 00:00
slug: javafx-8-tutorial/kr/part4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-kr-part4.md
description: "여러분은 JavaFX에서 CSS로 사용자 인터페이스를 꾸밀 수 있습니다. 우리는 또 튜토리얼 4부에서 애플리케이션 아이콘을 추가해 볼 겁니다."
image: /assets/library/javafx-8-tutorial/part4/addressapp-part4.png
published: true
prettify: true
comments: true
sidebars:
- header: "차례"
  body:
  - text: "소개"
    link: /library/javafx-8-tutorial/kr/
    paging: Intro
  - text: "1부: Scene Builder"
    link: /library/javafx-8-tutorial/kr/part1/
    paging: 1
  - text: "2부: Model 그리고 TableView"
    link: /library/javafx-8-tutorial/kr/part2/
    paging: 2
  - text: "3부: 사용자 상호작용"
    link: /library/javafx-8-tutorial/kr/part3/
    paging: 3
  - text: "4부: CSS 꾸미기"
    link: /library/javafx-8-tutorial/kr/part4/
    paging: 4
    active: true
  - text: "5부: XML로 데이터 저장하기"
    link: /library/javafx-8-tutorial/kr/part5/
    paging: 5
  - text: "6부: 통계 차트"
    link: /library/javafx-8-tutorial/kr/part6/
    paging: 6
  - text: "7부: 배포"
    link: /library/javafx-8-tutorial/kr/part7/
    paging: 7
- header: "소스 코드 다운로드"
  body:
  - text: 4부 Eclipse 프로젝트 <em>(JDK 8u40 이상 필요)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-4.zip
    icon-css: fa fa-fw fa-download
languages:
  header: 언어
  collection: library
  item: javafx-8-tutorial
  part: part4
  active: kr
---

![Screenshot AddressApp Part 4](/assets/library/javafx-8-tutorial/part4/addressapp-part4.png)


## 4부 주제

* **CSS 꾸미기**
* **애플리케이션 아이콘** 추가하기



*****


## CSS 꾸미기

여러분은 JavaFX에서 캐스케이딩 스타일 시트 (CSS)로 사용자 인터페이스를 꾸밀 수 있습니다. 정말 멋집니다! 왜냐하면 Java 애플리케이션의 외관을 입맛대로 바꾸기란 어려웠으니까요.

우리는 이 튜토리얼에서 Windows 8 메트로 디자인에서 영감을 받은 *다크 테마*를 만들 겁니다. 버튼 CSS는 Pedro Duque Vieira의 블로그 포스트인 [JMetro - Windows 8 Metro controls on Java](http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/)에 기반을 둡니다.


### CSS와 친해지기

JavaFX 애플리케이션을 꾸미려면 먼저 CSS가 무엇인지 알아야 합니다. [CSS tutorial](http://www.csstutorial.net/)이 시작하기에 좋습니다.

JavaFX CSS에 관한 정보:

* [Skinning JavaFX Applications with CSS](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/css_tutorial.htm) - Tutorial by Oracle
* [JavaFX CSS Reference](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html) - Official Reference


### 디폴트 JavaFX CSS

JavaFX 8의 디폴트 CSS 스타일은 **`modena.css`**라고 부르는 파일입니다. 이 CSS 파일은 여러분의 Java가 있는 `/jdk1.8.x/jre/lib/ext/jfxrt.jar` 폴더 밑에 `jfxrt.jar` 라는 JavaFX jar 파일에 있습니다.

`jfxrt.jar` 파일의 압축을 풀면 `com/sun/javafx/scene/control/skin/modena/` 밑에 `modena.css`가 있습니다.

이 디폴트 스타일 시트는 JavaFX 애플리케이션에만 적용됩니다. 커스텀 스타일 시트를 추가해서 `modena.css`의 디폴트 스타일을 덮어쓸 수 있습니다.

<div class="alert alert-info">
**힌트:** 디폴트 CSS 파일을 살펴보면 여러분이 어떤 스타일을 덮어쓰고 싶은지 도움이 됩니다.
</div>


### CSS 스타일 시트

다음 내용을 `DarkTheme.css` 라는 CSS 파일에 넣어서 *view* 패키지에 추가하세요.

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

이제 필요한 것은 Scene에 CSS 추가하는 일입니다. 우리는 이를 Java로 프로그래밍할 수 있지만, Scene Builder를 사용해서 fxml 파일에 추가할 겁니다:

#### RootLayout.fxml에 CSS 추가하기

1. Scene Builder로 `RootLayout.fxml` 파일을 엽니다.

2. Hierarchy 그룹에서 상위 레이아웃인 `BorderPane`을 선택합니다. *Properties* 그룹 밑에서 스타일시트를 `DarkTheme.css`로 추가합니다.   
![DarkTheme for RootLayout](/assets/library/javafx-8-tutorial/part4/darktheme-rootlayout.png)


#### PersonEditDialog.fxml에 CSS 추가하기

1. Scene Builder로 `PersonEditDialog.fxml` 파일을 엽니다. 상위 레이아웃인 `AnchorPane`을 선택한 후 *Properties* 그룹의 Stylesheets를 `DarkTheme.css`로 고릅니다.

2. 아직도 배경이 흰색이므로 `AnchorPane`에 Style Class인 `background`를 추가합니다.   
![Add Style Class](/assets/library/javafx-8-tutorial/part4/darktheme-personeditdialog.png)

3. OK 버튼을 선택한 후 Properties 그룹에서 *Default Button*을 고릅니다. 그러면 사용자가 키를 *누를 때* 색깔이 바뀌고 이를 디폴트 버튼으로 만들 겁니다.


#### PersonOverview.fxml에 CSS 추가하기

1. Scene Builder로 `PersonOverview.fxml` 파일을 엽니다. *Hierarchy* 그룹에서 상위 레이아웃인 `AnchorPane`을 선택합니다. Stylesheets에 `DarkTheme.css`를 추가합니다.

2. 이 시점에서 무엇이 바뀌어야 합니다: 테이블과 버튼이 검정색이 되었습니다. `modena.css`의 모든 클래스 스타일인 `.table-view`와 `.button`이 테이블과 버튼에 적용됩니다. 우리가 커스텀 CSS에서 스타일 몇 개를 재정의(오버라이드)했을 때 새로운 스타일들이 자동으로 적용됩니다.

3. 버튼 크기를 조정하면 모든 텍스트가 표시될 겁니다.

4. 오른쪽 `AnchorPane`을 선택합니다. 이는 `SplitPane` 안에 있습니다.   
![Background Style Select](/assets/library/javafx-8-tutorial/part4/background-style-select.png)   

5. *Properties* 그룹에서 Style Class로 `background`를 선택합니다. 이제 배경이 검정색으로 바뀝니다.   
![Background Style](/assets/library/javafx-8-tutorial/part4/background-style.png)


#### 서로 다른 스타일의 라벨들

이제 오른쪽에 있는 모든 라벨은 같은 크기로 되었습니다. CSS 파일에는 `.label-header`와 `.label-bright` 라는 스타일이 미리 정의되어 있는데 곧 이를 사용해 볼 겁니다.

1. *Person Details* 라벨을 선택한 후 Style Class에 `label-header`를 추가합니다.   
![Label Header Style](/assets/library/javafx-8-tutorial/part4/label-header-style.png)

2. 오른쪽 열 (실제로 연락처 정보가 표시되는 열)의 각 라벨의 Style Class에 `label-bright`를 추가합니다.   
![Label Bright Style](/assets/library/javafx-8-tutorial/part4/label-bright-style.png)


*****


## 애플리케이션 아이콘 추가하기

현재 우리 애플리케이션의 타이틀바와 태스크바에는 디폴트 아이콘을 가지고 있습니다:

![Default Icon](/assets/library/javafx-8-tutorial/part4/default-app-icon.png)

하지만 커스텀 아이콘이 더 좋아 보입니다:

![Custom Icon](/assets/library/javafx-8-tutorial/part4/custom-app-icon.png)


### 아이콘 파일

[Icon Finder](http://www.iconfinder.com)는 무료 아이콘을 내려받을 수 있는 곳입니다. 저는 여기서 작은 [address book icon](https://www.iconfinder.com/icons/86957/address_book_icon#size=32)을 내려받았습니다.

여러분의 주소록 프로젝트 밑에 **resources** 디렉토리를 만들고 나서 이 아래에 **images** 라는 하위 디렉토리를 만듭니다 (일반적인 디렉토리). 디렉토리 구조는 다음과 같습니다:

![Custom Icon File](/assets/library/javafx-8-tutorial/part4/custom-icon-file.png)


### Scene에 아이콘 설정하기

Scene에 아이콘을 설정하려면 다음 코드를 `MainApp.java`의 `start(...)` 메서드에 추가하세요.


##### MainApp.java

<pre class="prettyprint lang-java">
this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
</pre>

이제 `start(...)` 메서드의 전체 모습은 다음과 같습니다:

<pre class="prettyprint lang-java">
public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("AddressApp");

    // 애플리케이션 아이콘을 설정한다.
    this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

    initRootLayout();

    showPersonOverview();
}
</pre>

물론 연락처 변경 다이얼로그의 Stage에도 아이콘을 추가할 수 있습니다.


### 다음 할 일은?

[튜토리얼 5부](/library/javafx-8-tutorial/kr/part5/)에서는 데이터를 유지하기 위해 XML 스토리지를 추가할 겁니다.


##### 흥미로운 자료들

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
