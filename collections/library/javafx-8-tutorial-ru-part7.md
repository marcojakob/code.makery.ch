---
layout: article
title: "Учебник по JavaFX 8 - Часть 7: Развёртывание"
date: 2014-05-10 00:00
updated: 2016-04-26 00:00
slug: javafx-8-tutorial/ru/part7
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-ru-part7.md
description: "Развёртывание приложения JavaFX как нативного пакета (native package). Создание инсталляторов для Windows, Mac и Linux."
image: /assets/library/javafx-8-tutorial/part7/addressapp-macos.png
published: true
prettify: true
comments: true
sidebars:
- header: "Статьи в этой серии"
  body:
  - text: "Введение"
    link: /library/javafx-8-tutorial/ru/
    paging: Intro
  - text: "Часть 1: Scene Builder"
    link: /library/javafx-8-tutorial/ru/part1/
    paging: 1
  - text: "Часть 2: Модель и компонент TableView"
    link: /library/javafx-8-tutorial/ru/part2/
    paging: 2
  - text: "Часть 3: Взаимодействие с пользователем"
    link: /library/javafx-8-tutorial/ru/part3/
    paging: 3
  - text: "Часть 4: Стилизация с помощью CSS"
    link: /library/javafx-8-tutorial/ru/part4/
    paging: 4
  - text: "Часть 5: Хранение данных в XML"
    link: /library/javafx-8-tutorial/ru/part5/
    paging: 5
  - text: "Часть 6: Статистическая диаграмма"
    link: /library/javafx-8-tutorial/ru/part6/
    paging: 6
  - text: "Часть 7: Развёртывание"
    link: /library/javafx-8-tutorial/ru/part7/
    paging: 7
    active: true
- header: Скачать исходники
  body:
  - text: Часть 7 как проект Eclipse <em>(Требуется хотя бы JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-7.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Языки
  collection: library
  item: javafx-8-tutorial
  part: part7
  active: ru
---

![Screenshot AddressApp Part 7](/assets/library/javafx-8-tutorial/part7/addressapp-part7.png)

Я написал последнюю часть данного учебника для того, чтобы показать вам, как развернуть (то есть упаковать и опубликовать) наше приложение AddressApp. 


*****

## Часть 7: Содержание

* Развёртывание нашего JavaFX-приложения в виде нативного пакета (**Native Package**) с использованием плагина e(fx)clipse.


*****

## Что такое развёртывание

Развёртывание - это процесс упаковки и доставки программного обеспечения пользователю. Это важная часть процесса разработки ПО, так как здесь происходит первый контакт пользователя с нашим приложением.

Для того, чтобы проиллюстрировать *кроссплатформенное* преимущество данного языка программирования, Java рекламируется под слоганом *"Написано однажды, работает везде"*. В идеале это означает, что наше Java-приложение сможет запуститься на любом устройстве, где установлена виртуальная машина Java (JVM). 

В прошлом, пользовательский опыт от установки приложений Java не всегда был радостным. Если у пользователя в системе не была установлена требуемая версия Java, он перенаправлялся для её установки. Это приводило к некоторым трудностям, например, к необходимости обладать правами администратора, к возникновению вопросов совместимости версий Java, и прочему.

К счастью, JavaFX предоставляет новую возможность развёртывания, называемую "нативная упаковка" (**Native Packaging**) (другое название - "Автономная упаковка приложения" (Self-Containde Application Package)). Нативный Пакет - это пакет, который вместе с приложением Java содержит и среду выполнения Java для конкретной платформы.

Официальная документация по JavaFX от Oracle содержит обширное руководство для всех возможных вариантов [развёртывания приложений JavaFX](http://docs.oracle.com/javafx/2/deployment/jfxpub-deployment.htm).

В этой статье я покажу как создать "Нативную Пакет" с помощью приложения Eclipse и [плагина e(fx)clipse](http://www.eclipse.org/efxclipse/).


*****

## Создание нативного пакета

Цель заключается в создании автономно-упакованного приложения, которое будет размещаться на компьютере пользователя в одной директории. То, как наше приложение будет примерно выглядеть в Windows можно посмотреть ниже:

![AddressApp Native Package](/assets/library/javafx-8-tutorial/part7/native-package.png)

В папке `app` лежат данные приложения, а в папке `runtime` - платформ-зависимая среда выполнения.

Для того, чтобы сделать процесс установки более комфортным для пользователя, мы предоставим ему установщик:

* `exe`-установщик для Windows
* `dmg`-установщик для MacOS.

Плагин e(fx)clipse поможет нам сгенерировать нативный пакет и установщик.


### Шаг 1-й. Редактируем файл build.fxbuild

Файл `build.fxbuild` используется плагином e(fx)clipse для генерации файла, который в свою очередь будет использоваться инструментом сборки Ant. (Если у вас нет файла `build.fxbuild`, то создайте в Eclipse новый проект JavaFX и скопируйте оттуда сгенерированный файл).

1. Откройте файл `build.fxbuild` из корневой папки вашего проекта.

2. Заполните все поля, помеченные звёздочками. Для владельцев MacOS: не используйте пробелы в названии приложения (в поле *Application Title*) так как это, похоже, приводит к проблемам).  
![fxbuild settings](/assets/library/javafx-8-tutorial/part7/fxbuild-settings.png)

3. Выберите формат упаковки: для Windows - `exe`, для MacOS - `dmg`, или Linux - `rpm`.

4. Справа нажмите на ссылку `Generate ant build.xml only`.  
![generate ant build](/assets/library/javafx-8-tutorial/part7/generate-ant-build.png)

5. Проверьте, была ли создана папка `build` с файлом `build.xml`.


### Шаг 2. Добавляем в установщик иконки

Мы можем добавить в наш установщик пару красивых иконок:

* [AddressApp.ico](/assets/library/javafx-8-tutorial/part7/AddressApp.ico) для иконки файла установщика
* [AddressApp-setup-icon.bmp](/assets/library/javafx-8-tutorial/part7/AddressApp-setup-icon.bmp) для экрана приветствия установщика
* [AddressApp.icns](/assets/library/javafx-8-tutorial/part7/AddressApp.icns) - для установщика под MacOS
* [AddressApp-volume.icns](/assets/library/javafx-8-tutorial/part7/AddressApp-volume.icns) для рабочего стола Mac

1. Создайте в папке `build` следующие подкаталоги:
  * `build/package/windows` (используется только в Windows)
  * `build/package/macosx` (используется только в MacOS)
2. Из приведенных ссылок загрузите соответствующие иконки и поместите их в назначенные для них папки. В результате это должно выглядеть так:  
![Installer Icons](/assets/library/javafx-8-tutorial/part7/installer-icons.png)
3. **Важно**: Имена иконок должны точно соответствовать имени приложения, которое вы указали в `build.fxbuild`:
  * `НазваниеВашегоПриложения.ico`
  * `НазваниеВашегоПриложения-setup-icon.bmp`
  * `НазваниеВашегоПриложения .icns`
  * `НазваниеВашегоПриложения-volume.icns`


### Шаг 3. Добавляем ресурсы

Наша папка `resources` автоматически не будет скопирована автоматически. Мы должны вручную добавить её в папку `build`:

1. Создайте в папке `build` подкаталог `dist`:
	* `build/dist`
2. Скопируйте папку `resources` (которая содержит иконки нашего приложения) в `build/dist`.  
![Build Resources](/assets/library/javafx-8-tutorial/part7/build-resources.png)


### Шаг 4. Редактируем build.xml и включаем в него иконки

Плагин e(fx)clipse сгенерировал файл `build/build.xml`, который готов для выполнения **Ant**'ом. Но пока наши иконки и изображения ресурсов работать не будут.

Пока (?) плагину e(fx)clipse нельзя указать, что нам необходимо включить дополнительные ресурсы, например папку `resource` и иконки, которые мы добавили ранее. Нам необходимо сделать это вручную путём редактирования `build.xml`:

Откройте `build.xml` и найдите тег `<path id="fxant">`. Добавьте одну строку для `${basedir}` (это сделает наши иконки доступными):


##### build.xml - добавляем "basedir"

<pre class="prettyprint lang-xml">
&lt;path id="fxant"&gt;
  &lt;filelist&gt;
    &lt;file name="${java.home}\..\lib\ant-javafx.jar"/&gt;
    &lt;file name="${java.home}\lib\jfxrt.jar"/&gt;
    <mark>&lt;file name="${basedir}"/&gt;</mark>
  &lt;/filelist&gt;
&lt;/path&gt;
</pre>


Ниже найдите блок кода `fx:resources id="appRes"`. Добавьте одну строку для наших ресурсов `resources`:

##### build.xml - добавляем "resources"

<pre class="prettyprint lang-xml">
&lt;fx:resources id="appRes"&gt;
    &lt;fx:fileset dir="dist" includes="AddressApp.jar"/&gt;
    &lt;fx:fileset dir="dist" includes="libs/*"/&gt;
    <mark>&lt;fx:fileset dir="dist" includes="resources/**"/&gt;</mark>
&lt;/fx:resources&gt; 
</pre>


По какой-то причине номер версии не добавляется в `fx:application`, что всегда делает версию нашего установщика `1.0` (как указали несколько человек в комментариях). Для исправления этого, добавьте номер версии вручную (спасибо Марку, что [выяснил это](http://code.makery.ch/library/javafx-8-tutorial/part7/#comment-1566725959)):

##### build.xml - добавляем "version"

<pre class="prettyprint lang-xml">
&lt;fx:application id="fxApplication"
    name="AddressApp"
    mainClass="ch.makery.address.MainApp"
    <mark>version="1.0"</mark>
/>
</pre>

Теперь мы готовы запускать `build.xml` через Ant. Это сгенерирует нам исполняемый jar-файл проекта. Но мы хотим пойти на шаг дальше и создать хороший установщик.


### Шаг 5. EXE-установщик под Windows

![AddressApp on Windows](/assets/library/javafx-8-tutorial/part7/addressapp-windows.png)

С помощью утилиты **Inno Setup** можно создать установщик нашей адресной книги для Windows в виде единого `.exe`-файла. Созданный `.exe`-установщик будет устанавливать программу на уровне пользователя (не потребуются права администратора). Также он создаст ярлык (в меню или на рабочем столе).

1. Скачайте [Inno Setup](http://www.jrsoftware.org/isdl.php) версии 5 или выше. Установите Inno Setup на ваш компьютер. Наш Ant-скрипт будет использоваться для автоматической генерации установщика.

2. Укажите системе Windows путь к Inno Setup (например, `C:\Program Files (x86)\Inno Setup 5`). Для этого добавьте Inno Setup в переменную `Path` в переменных окружения системы. Если вы не знаете где их найти, почитайте [Как установить пути и переменные окружения в Windows](http://www.computerhope.com/issues/ch000549.htm).

3. Перезапустите приложение Eclipse и продолжите с шага 6.


### Шаг 5. DMG-установщик для MacOS

![AddressApp on Mac](/assets/library/javafx-8-tutorial/part7/addressapp-macos.png)

Для создания `dmg`-установщика под MacOS никаких дополнительных инструментов не потребуется.

Примечание: Для того, чтобы пользователь видел картинку установщика, следует назвать это изображение точно так же, как и наше приложение.


### Шаг 5 (LINUX и другие). RPM-установщик для Linux

Для других способов упаковки приложения (`msi` для Windows, `rpm` для Linux) читайте [эту статью](https://blogs.oracle.com/talkingjavadeployment/entry/native_packaging_for_javafx) или [документацию от Oracle](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm#A1324980).


### Шаг 6 - Запускаем build.xml

На последнем этапе мы запустим файл `build.xml` с помощью Ant: кликните правой кнопкой мышки на файле `build.xml` *| Run As | Ant Build*.

![Run Ant Build](/assets/library/javafx-8-tutorial/part7/run-ant-build.png)

Сборка приложения займёт какое-то время (около 1 минуты на моём компьютере).

Если всё прошло удачно, вы должны найти нативные сборки в папке `build/deploy/bundles`. Вот так выглядит версия для Windows:

![Deployed File](/assets/library/javafx-8-tutorial/part7/deployed-file.png)


Файл `AddressApp-1.0.exe` может использоваться как единственный файл для установки приложения. Этот установщик скопирует нашу сборку в папку `C:/Users/[yourname]/AppData/Local/AddressApp`.


### Что дальше?

Надеюсь, что этот учебник поможет вам начать работать с JavaFX и с этого момента вы сможете писать собственные проекты JavaFX.

Я буду признателен любым отзывам. Если у вас есть какие-либо предложения, или если вам что-то не ясно, то не стесняйтесь писать комментарии.


##### Вам могут быть интересны также некоторые другие статьи

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
