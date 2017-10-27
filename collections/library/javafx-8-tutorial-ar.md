---
layout: article
title: "دورة تعليمية في JavaFX"
date: 2014-04-19 00:00
updated: 2017-10-27 00:00
slug: javafx-8-tutorial/ar/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-en.md
description: "This seven-part tutorial walks through designing, programming and deploying an address application with JavaFX."
description: "الاجزاء السبعة من هذه الدورة تدور حول تصميم، برمجة ونشر تطبيق عناوين باستعمال JavaFX "
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: 
  identifier: /java/javafx-8-tutorial-intro/ar/
sidebars:
- header: "الفهرس"
  body:
  - text: "مقدمة"
    link: /library/javafx-8-tutorial/ar/
    paging: Intro
    active: true
  - text: "الجزء1: Scene Builder"
    link: /library/javafx-8-tutorial/ar/part1/
    paging: 1
  - text: "الجزء2: Model and TableView"
    link: /library/javafx-8-tutorial/ar/part2/
    paging: 2
  - text: "الجزء3: التفاعل مع المستخدم"
    link: /library/javafx-8-tutorial/ar/part3/
    paging: 3
  - text: "الجزء4: المظهر باستعمال CSS"
    link: /library/javafx-8-tutorial/ar/part4/
    paging: 4
  - text: "الجزء5: تخزسن المعلومات في XML"
    link: /library/javafx-8-tutorial/ar/part5/
    paging: 5
  - text: "الجزء6: الاحصائيات"
    link: /library/javafx-8-tutorial/ar/part6/
    paging: 6
  - text: "الجزء7: النشر"
    link: /library/javafx-8-tutorial/ar/part7/
    paging: 7
languages: 
  header: Languages
  collection: library
  item: javafx-8-tutorial
  part: 
  active: ar
---

في 2012 قمت بكتابة [دورة سلستة JavaFX 2](/library/javafx-2-tutorial/) لمتابعي. قراء الكثيرون الدورة في جميع انحاء العالم واعطوا انطباعا ايجابيا. لهذا قررت **اعادة كتابة الدورة من أجل JavaFX 8** (اقرا عن ما تغير [Update to JavaFX 8 - What's New](/blog/update-to-javafx-8-whats-new/)).


This tutorial walks you through designing, programming and deploying an address application. This is how the final application will look like:

الدورة تدور حول تصميم، برمجة ونشر تطبيق عناوين باستعمال JavaFX. النتسجة النهائية ستبدو بهذا الشكل :

![Screenshot AddressApp](/assets/library/javafx-8-tutorial/addressapp.png)


## مالذي ستتعلمه

* انشاء وبدا مشاريعJavaFX
* استعمال Scene Builder لتصميم الواجهة الرسومية
* هسكلة التطبيق باستعمال نمط Model-View-Controller (MVC)
* استعمال `ObservableLists` من اجل تحديث واجة الميتخدم آليا
* استعمال `TableView` و التفاعل عند تحيد العناصر في الجدول
* انشاء نافذة محادثة من أجل تحديث الاشخاص
* التأكد من صحة المعلومات الدخلة منقبل المستخدم
* تغير مظهو تطبيق الـ JavaFX باستعمال CSS
* تخزين النعلومات في XML
* تخزين مسار آخر ملف تم فتحه في اعدادات المستخدم
* انشاء رسومات بيانية بالـ JavaFX من اأجل الاحصاءات statistics
* نشر تطبيق JavaFX كبرنامج للتنصيب

**هذا كثير!** لكن, بعد الانتهاء من هذه الدورة ستكون قادرا على بناء تطبيقات معقدة باستعمال JavaFX.


## كيف تستعمل هذه الدورة

توجد طريقتان للاستفادة من الدورة:

* **الطريقة المفصلة:** قم بانشاء مشروع JavaFX من الصفر.
* **fast track:** قم بنسخ الكود مساشرة الى بيءة التطوسر التي تستعملها (المشاريع هي مشاريع Eclips, لكن يمكن استعمالها في بيئات أخرى مثل NetBeans مع تغييرات بسيطة). ثم عد الى الدورة لتفهم الكود.

الان, أرجوا أن تستمتع! ابدأ ب [الجزء الأول: Scene Builder](/library/javafx-8-tutorial/ar/part1/).

<div class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> The Arabic translation has been contributed by 
  <ul>
    <li><a href="https://github.com/Mohamed-SM" class="alert-link">Mohamed-SM</a></li>  
  </ul>
  Thank you very much!
</div>
