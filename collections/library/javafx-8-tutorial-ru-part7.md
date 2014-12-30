---
layout: article
title: "Учебник по JavaFX 8 - Часть 7: Развертывание"
date: 2014-05-10 00:00
updated: 2014-12-04 00:00
slug: javafx-8-tutorial/ru/part7
canonical: /java/javafx-8-tutorial-part7/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-ru-part7.md
description: "Развертывание JavaFX приложения с помощью процесса нативной упаковки (native package). Создание инсталляционного пакета для Windows, Mac или Linux."
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
  - text: "Часть 7: Развертывание"
    link: /library/javafx-8-tutorial/ru/part7/
    paging: 7
    active: true
- header: Скачать исходники
  body:
  - text: Часть 7 как проект Eclipse <em>(Требуется хотя бы JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-7.zip
    icon-css: fa fa-fw fa-download
- header: Языки
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-part7/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part7/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part7/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part7/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/part7/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/part7/
    icon-css: fa fa-fw fa-globe
---

![Screenshot AddressApp Part 7](/assets/library/javafx-8-tutorial/part7/addressapp-part7.png)

Я думаю, что написал последнюю часть данного учебника чтобы показать вам как развернуть (т.е. упаковать и опубликовать) наше приложение. 


*****

## Часть 7: Содержание

* Развертывание нашего JavaFX-приложения в виде нативной упаковки (Native Package) с использованием плагина e(fx)clipse.


*****

## Что такое развертывание

Развертывание - это процесс упаковки и доставки програмного обеспечения к пользователю. Это важная часть процесса разработки ПО, т.к. здесь происходит первый контакт пользователя с нашим приложением.

Java рекламируется под слоганом *"Написано однажды, работает везде"* для того, чтобы проиллюстрировать *кроссплатформенное* преимущество данного языка программирования. В идеале, это означает, что наше java-приложение сможет запуститься на любом устройстве, где установлена виртуальная машина Java (JVM). 

В прошлом, пользовательский опыт при установке java-приложения не всегда был гладким. Если у пользователя на системе не была установлена требуемая версия Java, он перенаправлялся для ее инсталляции. Это приводило к некоторым трудностям: к необходимости иметь права администратора, к возникновению вопросов совместимости версий Java, и т.д.

К счастью, JavaFX предоставляет новую опцию развертывания, которая имеет название "Нативная упаковка" (**Native Packaging**) (другое название "Автономная упаковка приложения" (Self-Containde Application Package)). Нативная упаковка - это пакет, содержащий вместе java-приложение и среду выполнения Java для конкретной платформы.

Официальная документация по JavaFX от Oracle содержит обширное руководство для всех возможных вариантов [развертывания JavaFX-приложений](http://docs.oracle.com/javafx/2/deployment/jfxpub-deployment.htm).

В этой статье я покажу как создать "Нативную упаковку" с помощью приложения Eclipse и [плагина e(fx)clipse](http://www.eclipse.org/efxclipse/).


*****

## Создание нативной упаковки

Цель заключается в создании автономно-упакованного приложения, размещенного в одной директории на компьютере пользователя. То, как оно примерно будет выглядеть для нашего приложения на Windows можно посмотреть ниже:

![AddressApp Native Package](/assets/library/javafx-8-tutorial/part7/native-package.png)

Папка `app` содержит данные нашего приложения, а папка `runtime` - платформо-зависимую среду выполнения.

Для того, чтобы сделать этот процесс более комфортным для пользователя, мы предоставим ему инсталлятор:

* `exe`-инсталлятор для Windows
* `dmg`-инсталлятор для MacOS.

Плагин e(fx)clipse поможет нам сгенерировать нативную упаковку и инсталлятор.


### Шаг 1 - Редактируем файл build.fxbuild

Файл `build.fxbuild` используется плагином e(fx)clipse для генерации файла, который будет использоватся инструментом сборки Ant. (Если у вас нет файла `build.fxbuild`, создайте новый JavaFX-проект в приложении Eclipse и скопируйте сгенерированный файл.)

1. Откройте файл `build.fxbuild` из корневой папки вашего проекта.

2. Заполните все поля помеченные звездочкой. Для MacOS: не используйте пробелы в названии приложения (в поле *Application Title*) так как это, кажется, приводит к проблемам).  
![fxbuild settings](/assets/library/javafx-8-tutorial/part7/fxbuild-settings.png)

3. Выберите значение формата упаковки: для Windows - `exe`, для MacOS - `dmg`, для Linux - `rpm`.

4. Нажмите на ссылку `Generate ant build.xml only` справа.  
![generate ant build](/assets/library/javafx-8-tutorial/part7/generate-ant-build.png)

5. Проверьте, создалась ни новая папка `build` с файлом `build.xml`.


### Шаг 2 - Добавляем в инсталлятор иконки

Мы можем добавить в наш инсталлятор пару красивых иконок:

* [AddressApp.ico](/assets/library/javafx-8-tutorial/part7/AddressApp.ico) для иконки инсталляционного файла
* [AddressApp-setup-icon.bmp](/assets/library/javafx-8-tutorial/part7/AddressApp-setup-icon.bmp) для экрана приветствия инсталлятора
* [AddressApp.icns](/assets/library/javafx-8-tutorial/part7/AddressApp.icns) - для инсталлятора под MacOS
 

1. Создайте следующие подпапки в папке `build`:
  * `build/package/windows` (используется только в Windows)
  * `build/package/macos` (используется только в MacOS)
2. Скопируйте соответствующие иконки с приведенных ссылок и поместите их в назначенные для них папки. В результате это должно выглядеть так:  
![Installer Icons](/assets/library/javafx-8-tutorial/part7/installer-icons.png)
3. **Важно**: Имена иконок должны точно совпадать с именем приложения, которое вы указали в `build.fxbuild`:
	* `YourAppTitle.ico`
	* `YourAppTitle-setup-icon.bmp`
	* `YourAppTitle.icns`


### Шаг 3 - Добавляем ресурсы

Наша папка `resources` не скопируется автоматически. Мы должны в ручную добавить ее в папку `build`:

1. Создайте подпапку `dist` в папке `build`:
	* `build/dist`
2. Скопируйте папку `resources` (которая содержит иконки для нашего приложения) в `build/dist`.  
![Build Resources](/assets/library/javafx-8-tutorial/part7/build-resources.png)


### Шаг 4 - Редактируем build.xml и включаем в него иконки

Плагин e(fx)clipse сгенерировал файл `build/build.xml`, который готов для выполнения **Ant**'ом. Пока наши иконки работать не будут.

Так как e(fx)clipse не может сообщить (пока?), что нам необходимо включить дополнительные ресурсы в папку, нам необходимо сделать это вручную путем редактирования `build.xml`:

Откройте `build.xml` и найдите тег `<path id="fxant">`. Добавьте одну строку для `${basedir}` (это сделает наши иконки видимыми):


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


Ниже найдите блок кода `fx:resources id="appRes"`. Добавьте одну строку для наших ресурсов:

##### build.xml - добавляем "resources"

<pre class="prettyprint lang-xml">
&lt;fx:resources id="appRes"&gt;
    &lt;fx:fileset dir="dist" includes="AddressApp.jar"/&gt;
    &lt;fx:fileset dir="dist" includes="libs/*"/&gt;
    <mark>&lt;fx:fileset dir="dist" includes="resources/**"/&gt;</mark>
&lt;/fx:resources&gt; 
</pre>


Почему-то, номер версии не добавляется в `fx:application`, который всегда делает версию нашего установщик `1.0` (как указали несколько человек в комментариях). Для исправления этого, добавьте номер версии вручную (спасибо Марку, что [выяснил это](/java/javafx-8-tutorial-part7/#comment-1566725959)):

##### build.xml - добавляем "version"

<pre class="prettyprint lang-xml">
&lt;fx:application id="fxApplication"
    name="AddressApp"
    mainClass="ch.makery.address.MainApp"
    <mark>version="1.0"</mark>
/>
</pre>

Теперь мы можем запускать `build.xml` через Ant. Это сгенерирует нам работоспособный jar-файл проекта. Но мы хотим пойти на шаг дальше и создать хороший установщик.


### Шаг 5 - exe-установщик под Windows

![AddressApp on Windows](/assets/library/javafx-8-tutorial/part7/addressapp-windows.png)

С помощью **Inno Setup** мы можем создать установщик под Windows для нашего приложения в виде единого `.exe`-файла. Созданный `.exe`-установщик будет проводить установку программы на уровне пользователя (не потребуются права администратора). Также будет создан ярлык (в меню или на рабочем столе).

1. Скачайте [Inno Setup](http://www.jrsoftware.org/isdl.php) версии 5 или выше. Установите Inno Setup на ваш компьютер. Наш Ant-скрипт будет использоваться для автоматической генерации установщика.

2. Укажите Windows путь к Inno Setup (например, `C:\Program Files (x86)\Inno Setup 5`). Для этого добавьте Inno Setup к переменной `Path` в ваших переменных окружения. Если вы не знаете где их найти, почитайте [Как установить пути и переменные окружения в Windows](http://www.computerhope.com/issues/ch000549.htm).

3. Перезапустите приложение Eclipse и продолжите с шага 5.


### Шаг 5 - dmg-установщик для MacOS

![AddressApp on Mac](/assets/library/javafx-8-tutorial/part7/addressapp-macos.png)

Для создания `dmg`-установщика под MacOS никаких дополнительных инструментов не требуется.

Заметка: Для того, чтобы отображалось изображение установщика, вы должны задать этому изображению такое же название, как у вашего приложения.


### Шаг 5 - rpm-установщик для Linux

Для других способов упаковки приложения (`msi` для Windows, `rpm` для Linux) читайте [эту статью](https://blogs.oracle.com/talkingjavadeployment/entry/native_packaging_for_javafx) или [документацию от Oracle](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm#A1324980).


### Шаг 6 - Запускаем build.xml

На последнем шаге мы запустим файл `build.xml` с помощью Ant: кликните правой кнопкой мышки на файл `build.xml` *| Run As | Ant Build*.

![Run Ant Build](/assets/library/javafx-8-tutorial/part7/run-ant-build.png)

Сборка приложения займет немного времени (около 1 минуты на моем компьютере).

Если все прошло удачно, вы должны найти нативные сборки в папке `build/deploy/bundles`. Вот так выглядит версия для Windows:

![Deployed File](/assets/library/javafx-8-tutorial/part7/deployed-file.png)


Файл `AddressApp-1.0.exe` может использоваться как единственный файл для установки приложения. Этот установщик скопирует нашу сборку в папку `C:/Users/[yourname]/AppData/Local/AddressApp`.


### Что дальше?

Надеюсь, что этот учебник поможет вам начать работать с JavaFX и с этого момента вы сможете писать свои JavaFX-проекты.

Я ценю любую обратную связь. Не стесняйтесь писать комментарии если у вас есть какие-либо предложения или если вам что-то не ясно.


##### Вам могут быть интересны также некоторые другие статьи

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
