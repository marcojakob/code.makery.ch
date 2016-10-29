---
layout: article
title: "JavaFX 8 튜토리얼"
date: 2014-04-19 00:00
updated: 2015-03-12 00:00
slug: javafx-8-tutorial/kr
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-kr.md
description: "이 튜토리얼은 7부에 걸쳐 JavaFX로 주소록 애플리케이션을 디자인, 프로그래밍, 배포하는 방법을 차근차근 설명합니다."
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: true
sidebars:
- header: "차례"
  body:
  - text: "소개"
    link: /library/javafx-8-tutorial/kr/
    paging: Intro
    active: true
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
  - text: "5부: XML로 데이터 저장하기"
    link: /library/javafx-8-tutorial/kr/part5/
    paging: 5
  - text: "6부: 통계 차트"
    link: /library/javafx-8-tutorial/kr/part6/
    paging: 6
  - text: "7부: 배포"
    link: /library/javafx-8-tutorial/kr/part7/
    paging: 7
languages:
  header: 언어
  collection: library
  item: javafx-8-tutorial
  part:
  active: kr
---


2012년에 우리 학생들을 위해 [JavaFX 2 tutorial series](/library/javafx-2-tutorial/)를 매우 자세하게 썼습니다. 전 세계 사람들이 이 튜토리얼을 읽고 매우 긍정적인 피드백을 보내왔습니다. 그래서 저는 **JavaFX 2 튜토리얼을 JavaFX 8로 다시 쓰기로** 결정했습니다 (무엇이 다른지 궁금하면 [Update to JavaFX 8 - What's New](/blog/update-to-javafx-8-whats-new/)를 읽으세요).

이 튜토리얼은 여러분에게 주소록 애플리케이션을 디자인, 프로그래밍, 배포하는 방법을 차근차근 설명합니다. 완성된 형태는 다음과 같습니다:

![Screenshot AddressApp](/assets/library/javafx-8-tutorial/addressapp.png)


## 배우는 것들

* JavaFX 프로젝트를 만들고 시작하기
* Scene Builder를 사용해서 사용자 인터페이스를 디자인하기
* 모델-뷰-컨트롤러 (MVC, Model-View-Controller) 패턴으로 애플리케이션을 구조적으로 만들기
* `ObservableLists`를 사용해서 자동으로 사용자 인터페이스를 업데이트하기
* `TableView`를 사용해서 테이블 선택에 반응하기
* 커스텀 팝업 다이얼로그를 만들어서 연락처 변경하기
* 사용자 입력 검사하기
* CSS로 JavaFX 애플리케이션 꾸미기
* XML로 데이터 유지하기(Persisting)
* 마지막으로 연 파일 경로를 preference로 저장하기
* JavaFX 통계 차트 만들기
* JavaFX 애플리케이션을 네이티브 패키지로 배포하기

**굉장히 많습니다!** 이 튜토리얼을 마치고 나면 JavaFX로 세련된 애플리케이션을 만들 준비가 될 겁니다.


## 이 튜토리얼을 보는 방법

이 튜토리얼을 보는 방법은 2가지 방식이 있습니다:

* **많이 배우기 코스:** 프로젝트를 처음부터 끝까지 다시 만듭니다.
* **빠른 코스:** 튜토리얼 소스 코드를 여러분의 IDE (기본적으로 Eclipse 프로젝트지만 NetBeans 같은 다른 IDE도 사용할 수 있습니다)에서 불러옵니다. 그 다음에는 튜토리얼을 따라 코드를 이해합니다.

이제 즐겁게 배우길 바랍니다! [1부: Scene Builder](/library/javafx-8-tutorial/kr/part1/)부터 시작하세요.


<div class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> Korean translations have been contributed by
  <ul>
    <li><a href="https://github.com/olgi3rd" class="alert-link">Olgierd Everac</a></li>
  </ul>
  감사합니다!
</div>
