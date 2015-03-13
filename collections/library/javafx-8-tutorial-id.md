---
layout: article
title: "Tutorial JavaFX 8 (Bahasa Indonesia)"
date: 2014-04-19 00:00
updated: 2015-01-02 00:00
slug: javafx-8-tutorial/id
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-id.md
description: "Tujuh bagian tutorial ini mencakup mendesain, memprogram, dan penyebaran aplikasi alamat dengan JavaFX."
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: true
sidebars:
- header: "Seri artikel"
  body:
  - text: "Pengenalan"
    link: /library/javafx-8-tutorial/id/
    paging: Intro
    active: true
  - text: "Bagian 1: Scene Builder"
    link: /library/javafx-8-tutorial/id/part1/
    paging: 1
  - text: "Bagian 2: Model dan TableView"
    link: /library/javafx-8-tutorial/id/part2/
    paging: 2
  - text: "Bagian 3: Berinteraksi dengan pengguna"
    link: /library/javafx-8-tutorial/id/part3/
    paging: 3
  - text: "Bagian 4: Memberikan gaya dengan CSS"
    link: /library/javafx-8-tutorial/id/part4/
    paging: 4
  - text: "Bagian 5: Menyimpan data sebagai XML"
    link: /library/javafx-8-tutorial/id/part5/
    paging: 5
  - text: "Bagian 6: Bagan Statistika"
    link: /library/javafx-8-tutorial/id/part6/
    paging: 6
  - text: "Bagian 7: Penyebaran"
    link: /library/javafx-8-tutorial/id/part7/
    paging: 7
languages: 
  header: Bahasa
  collection: library
  item: javafx-8-tutorial
  part:
  active: id
---

Pada 2012 saya telah menulis [Seri Tutorial JavaFX 2](/library/javafx-2-tutorial/) yang terperinci untuk murid-muridku. Banyak orang dari penjuru dunia telah membaca tutorial ini dan memberi umpan balik yang positif. Lalu saya memutuskan **menulis ulang Tutorial JavaFX 2 untuk JavaFX 8** (baca perubahan di [Pembaharuan javaFX 8 - Apa yang baru](/blog/update-to-javafx-8-whats-new/)).

Tutorial ini menuntun anda melalui merancang, pemrograman dan menggunakan aplikasi alamat. Berikut adalah bentuk akhir aplikasi yang akan terlihat:

![Tangkapan Layar AddressApp](/assets/library/javafx-8-tutorial/addressapp.png)


## Apa yang akan anda pelajari

* Membuat dan memulai proyek JavaFX
* Menggunakan Scene Builder untuk mendesain antar muka pengguna
* Strukturisasi sebuah aplikasi dengan pola Model-Tampilan-Pengendali (Model-View-Controller)
* Menggunakan `ObservableLists`untuk secara otomatis memperbaharui antarmuka pengguna
* Menggunakan `TableView` dan bereaksi terhadap perubahan seleksi pada tabel
* Membuat dialog ubah sesuai munculan untuk memperbaharui persons
* Validasi masukan pengguna
* Memberi gaya pada aplikasi JavaFX dengan CSS
* Memaksa data sebagai XML
* Menyimpan alamat berkas terakhir dibuka didalam preferensi pengguna
* Membuat bagan JavaFX untuk statistika
* Penyebaran aplikasi JavaFX sebagai paket asli

**Ini cukup banyak!**, jadi setelah kamu telah menyelesaikan seri tutorial-tutorial ini, kamu seharusnya telah siap untuk membangun aplikasi canggih dengan JavaFX.


## Bagaimana menggunakan tutorial ini

Ada dua cara untuk menggunakan tutorial ini:

* **Jalur pelajari lebih banyak:** Buat proyek JavaFX anda sendiri dari awal.
* **Jalur cepat:** Impor source kode dari bagian tutorial ke IDE (ini merupakan proyek yang dibuat dengan Eclipse, tapi anda bisa menggunakan IDE lainnya seperti NetBeans dengan sedikit modifikasi). Kemudian melalui tutorial untuk memahami kode tersebut.

Sekarang, saya harap anda akan bersenang-senang! dimulai dari [Bagian 1 : Scene Builder](/library/javafx-8-tutorial/id/part1/).

<div class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> The Indonesian translation has been contributed by 
  <ul>
    <li><a href="https://plus.google.com/117664039243670214562/" class="alert-link">Tria Warman</a></li> 
  </ul>
  Thank you very much!
</div>
