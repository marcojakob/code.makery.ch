+++
title = "JavaFX 튜토리얼"
date = 2016-10-30
updated = 2016-11-03
description = "이 튜토리얼은 7부에 걸쳐 JavaFX로 주소록 애플리케이션을 디자인, 프로그래밍, 배포하는 방법을 가르칩니다."
image = "addressapp.png"
published = true
prettify = true
comments = true
commentsIdentifier = "/library/javafx-8-tutorial/kr/"
aliases = [ 
  "/library/javafx-8-tutorial/kr/" 
]
sidebarName = "소개"
pagingName = "Intro"
+++

저는 2012년에 [JavaFX 2 tutorial series](/library/javafx-2-tutorial/)를 완성해서 제 학생들을 가르쳤습니다. 많은 사람들도 이 튜토리얼을 접하고 좋은 반응을 보여줬습니다. 그래서 저는 JavaFX 8로 다시 작성하기로 결심했습니다([Update to JavaFX 8 - What's New](/blog/update-to-javafx-8-whats-new/)).

여러분은 이 튜토리얼을 따라 주소록 애플리케이션을 디자인하고, 프로그래밍하고, 배포하는 방법을 배울 것입니다. 다음 그림은 완성된 형태입니다.

![Screenshot AddressApp](addressapp.png)


## 배우는 내용

* JavaFX 프로젝트 만들기
* Scene Builder로 사용자 인터페이스 디자인하기
* MVC(Model-View-Controller) 패턴으로 구조화하기
* `ObservableList`를 사용해서 사용자 인터페이스를 자동으로 업데이트하기
* `TableView`를 사용해서 테이블 셀 선택에 반응하게 만들기
* 커스텀 팝업 다이얼로그(Custom Popup Dialog)를 만들어서 연락처 바꾸기
* 사용자 입력 검사하기
* CSS로 꾸미기
* 데이터를 XML로 유지하기(Persisting)
* 마지막으로 불러온 파일 경로를 user preferences로 저장하기
* 통계 차트 만들기
* 네이티브 패키지로 배포하기

**적은 분량은 아니지만** 이 여정을 마치고 나면 괜찮은 애플리케이션을 만들 수 있을 것입니다.


## 배우는 방법

튜토리얼은 두 가지 방법으로 배울 수 있습니다.

* **많이 배우기:** 이 프로젝트를 처음부터 만듭니다.
* **빨리 배우기:** 소스 코드를 여러분의 IDE (기본적으로 Eclipse 프로젝트지만 NetBeans 같은 다른 IDE도 사용 가능)에서 불러옵니다. 그 다음에는 튜토리얼을 따라 코드를 이해합니다.

이 시리즈가 여러분의 여정에 도움이 되길 바랍니다. [1부: Scene Builder](/kr/library/javafx-tutorial/part1/)부터 시작하세요.


<div class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> Korean translations have been contributed by
  <ul>
    <li><a href="https://github.com/olgi3rd" class="alert-link">Olgierd Everac</a></li>
  </ul>
  <br>
  <p>
    <strong>기여하기</strong><br>
    한국어 번역판은 여전히 어색한 문장을 많이 내포하고 있습니다.<br>
    더 나은 아이디어가 있으시면 언제든지 직접 개선할 수 있습니다.<br>
    그렇지 않으면 아래 댓글로 제안해 주셔도 도움이 됩니다.<br><br>
    보통 다음과 같이 작업합니다.<br>
  </p>
  <ol>
    <li><a href="https://github.com/marcojakob/code.makery.ch">원본 Repository</a> Fork</li>
    <li>git clone Forked Repository</li>
    <li>번역</li>
    <li>파일 상단 updated 값 중 날짜만 변경</li>
    <li>커밋</li>
    <li>Pull Request</li>
  </ol>
</div>
