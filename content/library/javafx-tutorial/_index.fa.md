---
layout: article
title: "آموزش JavaFX 8 (فارسی)"
date: 2014-04-19
updated: 2015-07-15
slug: javafx-tutorial/fa
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-fa.md
description: "یک آموزش 7 قسمتی شامل طراحی, برنامه نویسی و پیاده‌سازی نسخه قابل نصب یک برنامه آدرس به وسیله JavaFX"
image: /assets/library/javafx-tutorial/addressapp.png
published: true
prettify: true
comments: true
rtl: true
sidebars:
- header: "مباحث این سری"
  body:
  - text: "مقدمه"
    link: /library/javafx-tutorial/fa/
    paging: Intro
    active: true
  - text: "قسمت اول: Scene Builder"
    link: /library/javafx-tutorial/fa/part1/
    paging: 1
  - text: "قسمت دوم: مدل و TableView"
    link: /library/javafx-tutorial/fa/part2/
    paging: 2
  - text: "قسمت سوم: تعامل با کاربر"
    link: /library/javafx-tutorial/fa/part3/
    paging: 3
  - text: "قسمت چهارم: سلیقه سازی با CSS"
    link: /library/javafx-tutorial/fa/part4/
    paging: 4
  - text: "قسمت پنجم: ذخیره کردن داده به عنوان XML"
    link: /library/javafx-tutorial/fa/part5/
    paging: 5
  - text: "قسمت ششم: رسم نمودار آماری"
    link: /library/javafx-tutorial/fa/part6/
    paging: 6
  - text: "قسمت هفتم: ساختن نسخه قابل نصب"
    link: /library/javafx-tutorial/fa/part7/
    paging: 7
languages:
  header: "زبان‌ها"
  collection: library
  item: javafx-tutorial
  part:
  active: fa
---

در سال 2012 من یک [آموزش کامل JavaFX 2](/library/javafx-2-tutorial) برای دانشجویانم نوشتم. بسیاری از مردم از سراسر دنیا آموزش رو خونده بودن و بازخورد مثبتی داشتند. بخاطر همین تصمیم گرفتم **آموزش JavaFX 2 رو برای JavaFX 8 بازنویسی کنم**(اطلاعات بیشتر درمورد [تغییرات JavaFX8](/blog/update-to-javafx-8-whats-new/))

این آموزش شامل طراحی, برنامه نویسی, و پیاده‌سازی نسخه قابل نصب یک برنامه آدرس است. برنامه نهایی به شکل زیر خواهد بود:

![Screenshot AddressApp](/assets/library/javafx-tutorial/addressapp.png)

## مباحثی که شما یاد میگیرید

* ساختن یک پروژه JavaFX
* استفاده از Scene Builder برای طراحی رابط کاربری
* ساختار دادن به برنامه با استفاده از الگوی MVC
* استفاده از `ObservableLists` برای بروزرسانی خودکار رابط کاربری
* استفاده از `TableView` و واکنش نشان دادن به تغییر انتخاب در جدول
* ساختن بالابر (popup) برای تغییر در افراد
* اعتبار بخشیدن به داده‌ای که کاربر وارد میکند
* سلیقه سازی کردن یک برنامه JavaFX با استفاده از CSS
* کار با داده به عنوان XML
* ذخیره سازی آخرین شاخه باز شده توسط کاربر 
* ساختن جدول JavaFX برای رسم اطلاعات آماری
* ساخت نسخه قابل نصب مخصوص هر سیستم عامل

** این تقریبا خیلی زیاده!**بنابراین بعد از اینکه شما تمام این آموزش‌ها رو پشت سر گذاشتید شما توانایی ساختن برنامه‌های پیچیده توسط JavaFX رو بدست میارید.

## چطور از این آموزش استفاده کنید

دوراه برای استفاده از این آموزش وجود داره:
* **روشی که در اون خیلی یاد میگیرید:** پروژه JavaFX خودتونو از صفر بسازید.
* **روش سریع:** سورس کد آموزش مربوطه رو دانلود کنید و اونو توی IDE مورد نظر خودتون باز کنید, بعد آموزش‌ رو بخونید تا کد رو متوجه بشید.

امیدوارم بهتون کلی خوش بگذره!از [قسمت اول: Scene Builder](/library/javafx-tutorial/fa/part1/) شروع کنید.

<div dir="ltr" class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> The Persian translation has been contributed by 
  <ul>
    <li><a href="https://github.com/yasharne" class="alert-link">Yashar Nesabian</a></li> 
  </ul>
  Thank you very much!
</div>