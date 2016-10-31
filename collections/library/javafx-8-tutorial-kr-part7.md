---
layout: article
title: "JavaFX 8 튜토리얼 - 7부: 배포"
date: 2016-10-30 00:00
slug: javafx-8-tutorial/kr/part7
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-kr-part7.md
description: "JavaFX 애플리케이션을 네이티브 패키지로 배포하는 방법을 배웁니다. Windows, MacOS, 또는 Linux 인스톨러를 만듭니다."
image: /assets/library/javafx-8-tutorial/part7/addressapp-macos.png
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
  - text: "5부: XML로 데이터 저장하기"
    link: /library/javafx-8-tutorial/kr/part5/
    paging: 5
  - text: "6부: 통계 차트"
    link: /library/javafx-8-tutorial/kr/part6/
    paging: 6
  - text: "7부: 배포"
    link: /library/javafx-8-tutorial/kr/part7/
    paging: 7
    active: true
- header: "소스 코드 다운로드"
  body:
  - text: 7부 Eclipse 프로젝트 <em>(JDK 8u40 이상 필요)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-7.zip
    icon-css: fa fa-fw fa-download
languages:
  header: 언어
  collection: library
  item: javafx-8-tutorial
  part: part7
  active: kr
---

![Screenshot AddressApp Part 7](/assets/library/javafx-8-tutorial/part7/addressapp-part7.png)

이 튜토리얼의 마지막 주제로 AddressApp을 배포 (패키지와 퍼블리싱)하는 방법을 보여드리려고 합니다.


*****

## 7부 주제

* JavaFX 애플리케이션을 e(fx)clipse로 **네이티브 패키지**로 배포하기


*****

## 배포란 무엇인가

배포란 소프트웨어를 포장(패키징, packaging)해서 사용자에게 전달(delivering)하는 과정입니다. 이는 사용자가 우리 소프트웨어를 처음으로 소유한 이후로 소프트웨어 개발에서 중요한 부분입니다.

Java는 Java 언어의 강점인 *크로스 플랫폼*을 설명하기 위해 **한 번 작성해서, 어디에서나 실행한다** 라는 모토를 선전합니다.

예전에는 Java 애플리케이션을 설치하는 사용자 경험이 매끄럽지 않았습니다. 만일 필요한 Java 버전이 없었다면 직접 설치해야 했습니다. 이 과정에는 어려움이 따릅니다. 예를 들어 관리자 권한이나 Java 버전 간 호환성 문제 등이 있습니다.

다행히도 JavaFX는 새로운 배포 방법을 제공하는데 이를 **네이티브 패키징(Native Packaging)** (또한 독립적인 애플리케이션 패키지- Self-Contained Application Package -라고 부름) 이라고 부릅니다. 네이티브 패키지란 여러분의 애플리케이션과 (플랫폼 특정- platform-specific -의) Java 런타임(Runtime)을 포함하는 번들(bundle)입니다.

Oracle이 제공하는 공식 JavaFX 문서에 모든 가능한 [JavaFX deployment options](http://docs.oracle.com/javafx/2/deployment/jfxpub-deployment.htm)를 광범위하게 다룹니다.

저는 여기서 Eclipse와 [**e(fx)clipse plugin**](http://www.eclipse.org/efxclipse/)으로 **네이티브 패키지**를 만드는 방법을 보여드릴 겁니다.


*****

## 네이티브 패키지 만들기

사용자 컴퓨터에서 디렉토리 하나에 독립적인 애플리케이션을 만드는 것이 목표입니다. 우리 AddressApp은 다음과 같이 보일 겁니다 (Windows 기준):

![AddressApp Native Package](/assets/library/javafx-8-tutorial/part7/native-package.png)

`app` 디렉토리에는 애플리케이션 데이터가 있고 `runtime`에는 플랫폼 특정 Java 런타임이 있습니다.

사용자가 좀 더 편리하게 사용하기 위해 인스톨러를 제공할 겁니다:

* Windows의 `exe` 파일 인스톨러
* MacOS의 `dmg` (드래그 앤 드롭) 인스톨러

e(fx)clipse 플러그인이 네이티브 패키지와 인스톨러를 만드는 데 도움을 줄 겁니다.


### 1단계 - build.fxbuild 변경하기

`build.fxbuild` 파일은 Ant 빌드 툴이 사용하도록 e(fx)clipse가 파일을 만드는 데 사용됩니다. (만일 `build.fxbuild` 파일이 없으면 Eclipse에서 새로운 *Java FX Project*를 만들어서 생성된 파일을 복사하세요.)

1. 여러분의 상위 프로젝트에서 `build.fxbuild`를 엽니다.

2. 별이 표시된 모든 필드를 채웁니다. *MacOS 사용자는 Application title에 공백을 넣으면 문제가 발생합니다.*    
![fxbuild settings](/assets/library/javafx-8-tutorial/part7/fxbuild-settings.png)

3. **Packaging Format**을 Windows면 `exe`로, MacOS면 `dmg`로, 또는 리눅스면 `rpm`으로 고릅니다.

4. `Generate ant build.xml only` 링크를 클릭합니다 (우측에 있습니다).   
![generate ant build](/assets/library/javafx-8-tutorial/part7/generate-ant-build.png)

5. `build` 디렉토리와 `build.fxml` 파일이 만들어졌는지 확인합니다.


### 2단계 - 인스톨러 아이콘 추가하기

인스톨러에 멋진 아이콘을 넣고 싶습니다:

* 인스톨러 파일 아이콘 [AddressApp.ico](/assets/library/javafx-8-tutorial/part7/AddressApp.ico)
* 인스톨러 시작 화면 아이콘 [AddressApp-setup-icon.bmp](/assets/library/javafx-8-tutorial/part7/AddressApp-setup-icon.bmp)
* Mac 인스톨러 아이콘 [AddressApp.icns](/assets/library/javafx-8-tutorial/part7/AddressApp.icns)
* Mac 데스크탑 아이콘 [AddressApp-volume.icns](/assets/library/javafx-8-tutorial/part7/AddressApp-volume.icns)


1. `build` 디렉토리에 다음 하위 디렉토리를 만듭니다:
  * `build/package/windows` (Windows)
  * `build/package/macosx` (MacOS)
2. 위 하위 디렉토리에 다음처럼 아이콘들을 복사합니다:
![Installer Icons](/assets/library/javafx-8-tutorial/part7/installer-icons.png)
3. **중요**: 아이콘 이름은 반드시 `build.fxbuild`에서 지정한 **Application title**과 정확히 일치해야 합니다:   
  * `YourAppTitle.ico`
  * `YourAppTitle-setup-icon.bmp`
  * `YourAppTitle.icns`
  * `YourAppTitle-volume.icns`


### 3단계 - 리소스 추가하기

`resources` 디렉토리는 자동으로 복사되지 않았습니다. 우리는 반드시 수동으로 빌드 디렉토리에 추가해야 합니다:

1. `build` 디렉토리에 다음처럼 하위 디렉토리를 만듭니다:
  * `build/dist`
2. `resources` 디렉토리를 `build/dist` 디렉토리 (애플리케이션 이미지 포함)에 복사합니다.    
![Build Resources](/assets/library/javafx-8-tutorial/part7/build-resources.png)


### 4단계 - build.xml을 편집해서 아이콘 포함시키기

E(fx)clipse는 **Ant**에 의해 실행되도록 `build/build.xml` 파일을 생성했습니다. 우리 인스톨러 아이콘과 리소스 이미지는 작동하지 않습니다.

e(fx)clipse는 (아직?) `resources` 디렉토리 같은 추가 리소스와 인스톨러 아이콘을 포함하라고 말을 못합니다. 우리가 직접 `build.xml`을 편집해야 합니다.

`build.xml`을 열어서 `fxant` 경로를 찾습니다. `${basedir}` 한 줄을 추가합니다 (인스톨러 아이콘을 사용할 수 있게 만듭니다):


##### build.xml - "basedir" 추가

<pre class="prettyprint lang-xml">
&lt;path id="fxant"&gt;
  &lt;filelist&gt;
    &lt;file name="${java.home}\..\lib\ant-javafx.jar"/&gt;
    &lt;file name="${java.home}\lib\jfxrt.jar"/&gt;
    <mark>&lt;file name="${basedir}"/&gt;</mark>
  &lt;/filelist&gt;
&lt;/path&gt;
</pre>


파일 아래에서 `fx:resources id="appRes"` 블록을 찾아 `resources` 라인을 추가합니다:

##### build.xml - "resources" 추가

<pre class="prettyprint lang-xml">
&lt;fx:resources id="appRes"&gt;
    &lt;fx:fileset dir="dist" includes="AddressApp.jar"/&gt;
    &lt;fx:fileset dir="dist" includes="libs/*"/&gt;
    <mark>&lt;fx:fileset dir="dist" includes="resources/**"/&gt;</mark>
&lt;/fx:resources&gt;
</pre>


왜 그런지 모르겠지만, `fx:application`에서 버전 번호가 더해지지 않고 `1.0` (사람들이 댓글로 지적)으로 고정되어서 이 문제를 고치기 위해 수동으로 버전 숫자를 넣습니다 ([문제](http://code.makery.ch/library/javafx-8-tutorial/part7/#comment-1566725959)를 찾은 Marc에게 감사합니다):

##### build.xml - "version" 추가

<pre class="prettyprint lang-xml">
&lt;fx:application id="fxApplication"
    name="AddressApp"
    mainClass="ch.makery.address.MainApp"
    <mark>version="1.0"</mark>
/>
</pre>

이 시점에서 우리는 Ant 빌드로 `build.xml`을 실행할 수 있었습니다. 그러면 프로젝트를 실행할 수 있는 jar 파일을 만듭니다. 하지만 이 과정을 건너뛰어서 멋진 인스톨러를 만들고 싶습니다.


### 5단계 (WINDOWS) - Windows exe 인스톨러

![AddressApp on Windows](/assets/library/javafx-8-tutorial/part7/addressapp-windows.png)

**Inno Setup**으로 우리 애플리케이션의 Windows 인스톨러를 `.exe` 파일로 만들 수 있습니다. `.exe`는 사용자 수준의 설치 (관리자 권한이 필요 없음)를 수행합니다. 또 단축아이콘도 만들어집니다 (메뉴 또는 바탕화면에).

1. [Inno Setup 5 또는 그 이상 버전](http://www.jrsoftware.org/isdl.php)을 내려받습니다. Inno Setup을 여러분의 컴퓨터에 설치합니다. 우리 Ant 스크립트는 이를 이용해서 인스톨러를 자동으로 만들 겁니다.

2. Windows한테 Inno Setup이 설치되어 있는 경로를 알리기 위해 환경 변수에 Inno setup 경로 (예: `C:\Program Files (x86)\Inno Setup 5`)를 추가합니다. 만일 어디에 있는지 모르겠으면 [How to set the path and environment variables in Windows](http://www.computerhope.com/issues/ch000549.htm)를 읽으세요.

3. Eclipse를 재시작하고 6단계를 진행합니다.


### 5단계 (MAC) - MacOS dmg 인스톨러

![AddressApp on Mac](/assets/library/javafx-8-tutorial/part7/addressapp-macos.png)

Mac OS `dmg` 드래그 앤 드롭 인스톨러를 만드는 데에는 다른 툴이 필요 없습니다.

참고: 인스톨러 이미지를 표시하기 위해서는 반드시 애플리케이션 이름과 동일해야 합니다.


### 5단계 (LINUX 등) - Linux rpm 인스톨러

다른 패키징 옵션으로는 (Windows는 `msi`, Linux는 `rpm`) 네이티브 패키지인 [blog post](https://blogs.oracle.com/talkingjavadeployment/entry/native_packaging_for_javafx)를 읽거나 [oracle documentation](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm#A1324980)을 읽으세요.


### 6단계 - build.xml 실행

마지막 단계에서 우리는 Ant로 `build.xml`을 실행합니다: `build.xml` 파일에서 마우스 오른쪽 클릭 | *Run As* | *Ant Build*.

![Run Ant Build](/assets/library/javafx-8-tutorial/part7/run-ant-build.png)

빌드하는 데에는 **시간이 걸립니다** (제 컴퓨터에서는 1분 정도 걸립니다).

만일 모든 작업이 성공했다면 `build/deploy/bundles` 디렉토리에서 네이티브 번들을 찾아야 합니다. Windows 버전은 다음처럼 보입니다:

![Deployed File](/assets/library/javafx-8-tutorial/part7/deployed-file.png)


`AddressApp-1.0.exe` 파일이 애플리케이션을 설치하는 단일 파일로 사용될 수 있습니다. 이 인스톨러는 번들을 `C:/Users/[yourname]/AppData/Local/AddressApp`에 복사할 겁니다.


### 다음 할 일은?

이 튜토리얼이 여러분에게 JavaFX를 시작하는 데 도움이 되기를 바라며 여러분만의 프로젝트를 만들 수 있기를 바랍니다.

어떤 피드백도 환영합니다. 건의사항이나 틀린 내용이 있으면 언제나 댓글을 달아 주세요.


##### 흥미로운 자료들

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
