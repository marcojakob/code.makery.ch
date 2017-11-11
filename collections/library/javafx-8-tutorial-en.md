---
layout: article
title: "دروس JavaFX 8"
date: 2014-04-19 00:00
updated: 2015-03-12 00:00
slug: javafx-8-tutorial
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-en.md
description: "تأخذك هذه الاقسام السبعة عبر تصميم وبرمجة وتوزيع تطبيق عناوين باستخدام JavaFX."
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: true
rtl: true
sidebars:
- header: "المقالات بهذه السلسلة"
  body:
  - text: "مقدمة"
    link: /library/javafx-8-tutorial/
    paging: Intro
    active: true
  - text: "القسم 1: باني المشهد"
    link: /library/javafx-8-tutorial/part1/
    paging: 1
  - text: "القسم 2: النموذج وعارض الجدول"
    link: /library/javafx-8-tutorial/part2/
    paging: 2
  - text: "القسم 3: التفاعل مع المستخدم"
    link: /library/javafx-8-tutorial/part3/
    paging: 3
  - text: "القسم 3: انماط CSS"
    link: /library/javafx-8-tutorial/part4/
    paging: 4
  - text: "القسم 5: تخزين البيانات كملفات XML"
    link: /library/javafx-8-tutorial/part5/
    paging: 5
  - text: "القسم 6: المخططات الاحصائية"
    link: /library/javafx-8-tutorial/part6/
    paging: 6
  - text: "القسم 7: التوزيع"
    link: /library/javafx-8-tutorial/part7/
    paging: 7
languages: 
  header: اللغات
  collection: library
  item: javafx-8-tutorial
  part: 
  active: ar
---


بالعودة الي 2012 فقد كتبت [سلسلة تدريبات متكاملة عن جافا اف اكس 2](/library/javafx-2-tutorial/) لطلابي. وقد قراءها العديد من الاشخاص حول العالم وقاموا بالتعليق عليها. لذا قررت اعادة كتابتها **لجافا اف اكس 8**( لمعرفة التعديلات من [التحديث الي JavaFX 8 - ما الجديد](/blog/update-to-javafx-8-whats-new/)).

سيأخذك هذا التمرين عبر عملية تصميم وبرمجة ونشر تطبيق عناوين. وهذا هو الشكل النهائي للتطبيق:

![Screenshot AddressApp](/assets/library/javafx-8-tutorial/addressapp.png)


## ما الذي ستتعلمه

* انشاء وبدء تطبيق جافا اف اكس
* استخدام باني المشهد لتصميم واجهة المستخدم الرسومية
* هيكلة التطبيق بنمط نموذج - عرض - متحكم (MVC)
* استخدام لائحة مراقبة `ObservableLists` للتحديث التلقائي لواجهة المستخدم
* استخدام عارض الجدول `TableView` والاستجابة لتغير التحديد في الجدول
* انشاء صندوق حوار قافز مخصص لتعديل الاشخاص
* التحقق من مدخلات المستخدم
* تنميط تطبيق JavaFX باستخدام CSS
* الحفاظ علي البيانات بصيغة XML
* حفظ مسار اخر ملف مفتوح في تفضيلات المستخدم
* انشاء مخطط جافا اف اكس للاحصاءات
* نشر تطبيق جافا اف اكس كحزمة اصلية native package

**اليس هذا كثير!**  لكن بعد الانتهاء من هذا التمرين ستصبح مستعدا لانشاء تطبيقات جافا اف اكس


## كيف تستخدم هذه الدروس

توجد طريقتين لاستخدام هذه الدروس:

* **تعلم الكثير من المسارات:** انشاء تطبيق جافا اف اكس الخاص بك
* **مسارات سريعة:** تستورد الشفرة المصدرية للدروس في بئية التطوير المتكاملة خاصتك (وهي عبارة عن مشاريع اكلبس لكن يمكنك استخدام بيئة التطوير التي تحبها مثل نت بينز او خلافها). ثم تواصل الدرس لفهم الاكواد.

الان اتمني ان تستمتع بهذه الدروس وننتقل الي [القسم 1: باني المشهد](/library/javafx-8-tutorial/part1/).
