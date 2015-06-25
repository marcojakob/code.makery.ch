---
layout: article
title: "Підручник з JavaFX 8 - Частина 7: Розгортання"
date: 2014-05-10 00:00
updated: 2014-12-04 00:00
slug: javafx-8-tutorial/uk/part7
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-uk-part7.md
description: "Розгортання JavaFX додатку з упаковки, що залежить від платформи (native package). Створення інсталяційниого пакета для Windows, Mac чи Linux"
image: /assets/library/javafx-8-tutorial/part7/addressapp-macos.png
published: true
prettify: true
comments: true
sidebars:
- header: "Статті цієї серії"
  body:
  - text: "Вступ"
    link: /library/javafx-8-tutorial/uk/
    paging: Intro
    active: true
  - text: "Частина 1: Scene Builder"
    link: /library/javafx-8-tutorial/uk/part1/
    paging: 1
  - text: "Частина 2: Модель та компонент TableView"
    link: /library/javafx-8-tutorial/uk/part2/
    paging: 2
  - text: "Частина 3: Взаємодія з користувачем"
    link: /library/javafx-8-tutorial/uk/part3/
    paging: 3
  - text: "Частина 4: Стилізація за допомогою CSS"
    link: /library/javafx-8-tutorial/uk/part4/
    paging: 4
  - text: "Частина 5: Збереження даних в XML"
    link: /library/javafx-8-tutorial/uk/part5/
    paging: 5
  - text: "Частина 6: Статистична діаграма"
    link: /library/javafx-8-tutorial/uk/part6/
    paging: 6
  - text: "Частина 7: Розгортання"
    link: /library/javafx-8-tutorial/uk/part7/
    paging: 7
    active: true
- header: Скачати вихідний код
  body:
  - text: Частина 7 як проект Eclipse <em>(Необхідно хоча б JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-7.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Мови
  collection: library
  item: javafx-8-tutorial
  part: part7
  active: uk
---

![Screenshot AddressApp Part 7](/assets/library/javafx-8-tutorial/part7/addressapp-part7.png)

Я думаю, що потрібно написати останню частину цього підручника, щоб показати, як розгорнути (запакувати та опублікувати) AddressApp.

*****

## Частина 7: Зміст

* Розгортання нашого JavaFX додатку у вигляді **упаковки, що залежить від платформи** (native package) з використанням плагіна е(fx)clipse.

*****

## Що таке розгортання

Розгортання - це процес упаковки і доставки програмного забезпечення до користувача. Це важлива частина процесу розробки ПЗ, тому що тут відбувається перший контакт користувача з нашим додатком.

Java рекламується під слоганом **"Написано одного разу, працює скрізь"** для того, щоб проілюструвати *кросплатформову перевагу* даної мови програмування. В ідеалі, це означає, що наш Java додаток запуститися на будь-якому пристрої, де встановлена віртуальна машина Java (JVM). 

У минулому, досвід користувача при установці Java додатку не завжди був гладким. Якщо у користувача в системі не була встановлена необхідна версія Java, він перенаправлявся для її інсталяції. Це призводило до деяких труднощів: до необхідності мати права адміністратора, до виникнення питань сумісності версій Java, і т.д.

На щастя, JavaFX надає нову опцію розгортання, яка має назву **упаковка, що залежить від платформи** (native package) або "Автономна упаковка додатку" (Self-Contained Application Package). Нативна упаковка - це пакет, що містить разом Java-додаток і середовище виконання Java для конкретної платформи.

Офіційна документація по JavaFX від Oracle містить вичерпне керівництво для всіх можливих варіантів [розгортання JavaFX додатків](http://docs.oracle.com/javafx/2/deployment/jfxpub-deployment.htm).

В цій статті я покажу, як створити "нативну упаковку" за допомогою додатку Eclipse та [плагіна e(fx)clipse](http://www.eclipse.org/efxclipse/).

*****

## Створення нативної упаковки

Мета полягає у створенні автономно-упакованого додатку, розміщеного в одній директорії на комп'ютері користувача. Як приблизно буде виглядати результат для нашого додатку на Windows, можна подивитися нижче:

![AddressApp Native Package](/assets/library/javafx-8-tutorial/part7/native-package.png)

Папка `app` містить дані нашого додатку, а папка `runtime` - платформо-залежне середовище виконання.

Для того, щоб зробити цей процес більш комфортним для користувача, ми надамо йому програму-інсталятор:

* `exe`-інсталятор для Windows;
* `dmg`-інсталятор (перетягни й відпусти, drag and drop) для MacOS.

Плагін e(fx)clipse допоможе нам згенерувати нативну упаковку та інсталятор.

### Крок 1 - Редагуємо файл build.fxbuild

Файл `build.fxbuild` використовується плагіном e(fx)clipse для генерації файлу, який буде використовуватися інструментом збірки **Ant**. (Якщо у вас немає файлу `build.fxbuild`, створіть *новий JavaFX проект* в додатку Eclipse та скопіюйте згенерований файл.)

1. Відкрийте файл `build.fxbuild` з кореневої папки вашого проекту.

2. Заповніть всі поля, помічені зірочкою. *Для MacOS: не використовуйте пробіли в назві додатку (в полі **Application Title**) оскільки це, здається, приводить до проблем).*
![fxbuild settings](/assets/library/javafx-8-tutorial/part7/fxbuild-settings.png)

3. Виберіть значення **формату упаковки**: для Windows - `exe`, для MacOS - `dmg`, для Linux - `rpm`.

4. Натисніть на посилання `Generate ant build.xml only` праворуч.  
![generate ant build](/assets/library/javafx-8-tutorial/part7/generate-ant-build.png)

5. Перевірте, чи створилась нова папка `build` з файлом `build.xml`.

### Крок 2 - Додамо в інсталятор іконки

Ми можемо додати в наш інсталятор пару гарних зображень:

* [AddressApp.ico](/assets/library/javafx-8-tutorial/part7/AddressApp.ico) для іконки інсталяційного файлу
* [AddressApp-setup-icon.bmp](/assets/library/javafx-8-tutorial/part7/AddressApp-setup-icon.bmp) для екрану привітання інсталятора
* [AddressApp.icns](/assets/library/javafx-8-tutorial/part7/AddressApp.icns) - для інсталятора під MacOS
 
1. Створіть наступні піддиректорії в папці `build`:
  * `build/package/windows` (використовується лише в Windows)
  * `build/package/macos` (використовується лише в MacOS)
  
2. Скопіюйте відповідні іконки з приведених посилань та розмістіть їх в призначені для них папки. В результаті це повинно виглядати так:  
![Installer Icons](/assets/library/javafx-8-tutorial/part7/installer-icons.png)

3. **Важливо**: Імена іконок повинні точно співпадати з **іменем додатку** (Application title), яке ви вказали в `build.fxbuild`:
	* `YourAppTitle.ico`
	* `YourAppTitle-setup-icon.bmp`
	* `YourAppTitle.icns`

### Крок 3 - Додаємо ресурси

Наша папка `resources` не скопіюється автоматично. Ми повинні вручну додати її в папку `build`:

1. Створіть піддиректорію `dist` в папці `build`:
	* `build/dist`
	
2. Скопіюйте папку `resources` (яка містить іконки для нашого додатку) в `build/dist`.  
![Build Resources](/assets/library/javafx-8-tutorial/part7/build-resources.png)

### Крок 4 - Редагуємо build.xml та включаємо в нього іконки

Плагін e(fx)clipse згенерував файл `build/build.xml`, який готовий для виконання за допомогою Ant. Проте, поки що, наші іконки відображатись не будуть.

Оскільки e(fx)clipse не може повідомити (поки що?), що нам необхідно приєднати додаткові ресурси, такі як наша папка `resources`, нам необхідно зробити це вручну, шляхом редагування `build.xml`.

Відкрийте `build.xml` та знайдіть тег `<path id="fxant">`. Додайте однин рядок для `${basedir}` (це зробить наші іконки доступними):

##### build.xml - додаємо "basedir"

<pre class="prettyprint lang-xml">
&lt;path id="fxant"&gt;
  &lt;filelist&gt;
    &lt;file name="${java.home}\..\lib\ant-javafx.jar"/&gt;
    &lt;file name="${java.home}\lib\jfxrt.jar"/&gt;
    <mark>&lt;file name="${basedir}"/&gt;</mark>
  &lt;/filelist&gt;
&lt;/path&gt;
</pre>

Нижче знайдете блок коду `fx:resources id="appRes"`. Додайте однин рядок для наших ресурсів:

##### build.xml - додаємо "resources"

<pre class="prettyprint lang-xml">
&lt;fx:resources id="appRes"&gt;
    &lt;fx:fileset dir="dist" includes="AddressApp.jar"/&gt;
    &lt;fx:fileset dir="dist" includes="libs/*"/&gt;
    <mark>&lt;fx:fileset dir="dist" includes="resources/**"/&gt;</mark>
&lt;/fx:resources&gt; 
</pre>

Чомусь, номер версії не додається в `fx:application`, який завжди встановлює версію нашого інсталятора як `1.0` (як вказали кілька чоловік в коментарях). Для виправлення цього, додайте номер версії вручну (дякую Марку, що [вияснив це](/library/javafx-8-tutorial/part7/#comment-1566725959)):

##### build.xml - додаємо "version"

<pre class="prettyprint lang-xml">
&lt;fx:application id="fxApplication"
    name="AddressApp"
    mainClass="ch.makery.address.MainApp"
    <mark>version="1.0"</mark>
/>
</pre>

Тепер ми можемо запускати `build.xml` через Ant. Це згенерує нам працездатний jar файл проекту. Але ми хочемо піти на крок далі і створити хороший інсталятор.

### Крок 5 - exe інсталятор під Windows

![AddressApp on Windows](/assets/library/javafx-8-tutorial/part7/addressapp-windows.png)

За допомогою **Inno Setup** ми можемо створити для нашого додатку інсталятор під Windows у вигляді єдиного `.exe` файлу. Створений `.exe` інсталятор буде проводити установку програми на рівні користувача (не потрібні права адміністратора). Також буде створено ярлик (в меню або на робочому столі).

1. Завантажте [Inno Setup](http://www.jrsoftware.org/isdl.php) версії 5 чи вище. Встановіть Inno Setup на ваш комп'ютер. Наш Ant скрипт буде використовуватися для автоматичної генерації інсталятора.

2. Вкажіть шлях до Windows, Inno Setup (наприклад, `C:\Program Files (x86)\Inno Setup 5`). Для цього, додайте Inno Setup до змінної `Path` в ваших змінних оточення. Якщо ви не знаєте де їх знайти, почитайте [як встановити шляхи та змінні оточення в Windows](http://www.computerhope.com/issues/ch000549.htm).

3. Перевантажте додаток Eclipse, та продовжуйте з кроку 5.

### Крок 5 - dmg інсталятор для MacOS

![AddressApp on Mac](/assets/library/javafx-8-tutorial/part7/addressapp-macos.png)

Для створення `dmg` інсталятора під MacOS ніяких додаткових інструментів не потрібно.

Примітка: Для того, щоб зображення інсталятора відображалося, ви повинні назвати це зображення так само, як і додаток.

### Крок 5 - rpm інсталятор для Linux

Для інших способів встановлення додатку (`msi` для Windows, `rpm` для Linux) читайте [цю статтю](https://blogs.oracle.com/talkingjavadeployment/entry/native_packaging_for_javafx) чи [документацію від Oracle](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm#A1324980).

### Крок 6 - Запускаємо build.xml

На останньому кроці ми запустимо файл `build.xml` за допомогою Ant: виконайте клік *правою кнопкою мишки* на файлі `build.xml` *| Run As | Ant Build*.

![Run Ant Build](/assets/library/javafx-8-tutorial/part7/run-ant-build.png)

Збірка додатку займе **трохи часу** (близько хвилини на моєму комп'ютері).

Якщо все пройшло вдало, ви повинні знайти нативні збірки в папці `build/deploy/bundles`. Ось так виглядає версія для Windows:

![Deployed File](/assets/library/javafx-8-tutorial/part7/deployed-file.png)

Файл `AddressApp-1.0.exe` може використовуватися як єдиний файл для установки програми. Цей інсталятор скопіює нашу збірку в папку `C:/Users/[yourname]/AppData/Local/AddressApp`.

### Що далі?

Сподіваюся, що цей підручник допоможе вам почати працювати з JavaFX і з цього моменту ви зможете писати свої JavaFX проекти.

Я ціную будь-який зворотній зв'язок. Не соромтеся писати коментарі, якщо у вас є будь-які пропозиції або якщо вам щось не зрозуміло.

##### Вам можуть бути цікаві також деякі інші статті

* [JavaFX Dialogs (official)](blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
