---
layout: article
title: "Tutorial JavaFX 8 - Bagian 4: Memberikan gaya dengan CSS"
date: 2014-04-25 00:00
updated: 2015-01-05 00:00
slug: javafx-8-tutorial/id/part4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-id-part4.md
description: "Di JavaFX anda bisa memberi gaya pada antarmuka pengguna menggunakan CSS. Jika juga akan memberikan ikon aplikasi pada tutorial ini."
image: /assets/library/javafx-8-tutorial/part4/addressapp-part4.png
published: true
prettify: true
comments: true
sidebars:
- header: "Seri Artikel"
  body:
  - text: "Pengenalan"
    link: /library/javafx-8-tutorial/id/
    paging: Intro
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
    active: true
  - text: "Bagian 5: Menyimpan data sebagai XML"
    link: /library/javafx-8-tutorial/id/part5/
    paging: 5
  - text: "Bagian 6: Bagan Statistika"
    link: /library/javafx-8-tutorial/id/part6/
    paging: 6
  - text: "Bagian 7: Penyebaran"
    link: /library/javafx-8-tutorial/id/part7/
    paging: 7
- header: "Unduh kode sumber"
  body:
  - text: Bagian 3 - Proyek dari Eclise <em>(Diperlukan setidaknya JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-4.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Bahasa
  collection: library
  item: javafx-8-tutorial
  part: part4
  active: id
---

![Tangkapan layar AddressApp bagian 4](/assets/library/javafx-8-tutorial/part4/addressapp-part4.png)


## Pembahasan di bagian 4

* **Memberi gaya CSS**
* Menambah **Ikon Aplikasi**



*****


## Memberi gaya CSS 

Di JavaFX anda bisa memberi gaya pada antarmuka pengguna menggunakan Cascading Style Sheet (CSS). Ini adalah hal yang sangat hebat, tidak pernah semudah ini untuk mengubah sesuaikan tampilan JavaFX.

Ditutorial ini kita akan membuat *DarkTheme* terinspirasi oleh desain Windows 8 Metro. Css untuk tombol berdasarkan [JMetro - Windows 8 Metro controls on Java](http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/) oleh Pedro Duque Vieira.


### Membuat Terbiasa dengan CSS

Jika anda ingin memberi gaya pada aplikasi JavaFX anda seharusnya memiliki pengetahuan dasar mengenai CSS secara umum. Tempat yang bagus untuk memulainya adalah [CSS tutorial](http://www.csstutorial.net/).

Untuk lebih banyak informasi terkait CSS di JavaFX :

* [Skinning JavaFX Applications with CSS](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/css_tutorial.htm) - Tutorial oleh Oracle
* [JavaFX CSS Reference](http://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html) - Referensi Resmi


### JavaFX CSS Baku

Sumber CSS baku dalam JavaFX 8 di panggil **`modena.css`**. Berkas css ini dapat di temukan pada berkas `jfxrt.jar` bertempat di folder java yang berlokasi di `/jdk1.8.x/jre/lib/ext/jfxrt.jar`.

Unzip the `jfxrt.jar`. Anda akan menemukan `modena.css` didalam `com/sun/javafx/scene/control/skin/modena/`

Gaya baku ini selalu diterapkan ke aplikasi JavaFX. Dengan menambah CSS yang disesuaikan kita dapat mengesampingkan gaya baku dari `modena.css`.   

<div class="alert alert-info">
**Petunjuk:** Akan membantu untuk melihat CSS baku, untuk melihat gaya mana yang perlu untuk dikesampingkan
</div>


### Melampirkan CSS

Tambahkan CSS berikut yang bernama `DarkTheme.css`  kedalam paket *view*.


##### DarkTheme.css

<pre class="prettyprint lang-css pre-scrollable">
.background {
    -fx-background-color: #1d1d1d;
}

.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 0.6;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.table-view {
    -fx-base: #1d1d1d;
    -fx-control-inner-background: #1d1d1d;
    -fx-background-color: #1d1d1d;
    -fx-table-cell-border-color: transparent;
    -fx-table-header-border-color: transparent;
    -fx-padding: 5;
}

.table-view .column-header-background {
    -fx-background-color: transparent;
}

.table-view .column-header, .table-view .filler {
    -fx-size: 35;
    -fx-border-width: 0 0 1 0;
    -fx-background-color: transparent;
    -fx-border-color: 
        transparent
        transparent
        derive(-fx-base, 80%) 
        transparent;
    -fx-border-insets: 0 10 1 0;
}

.table-view .column-header .label {
    -fx-font-size: 20pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-alignment: center-left;
    -fx-opacity: 1;
}

.table-view:focused .table-row-cell:filled:focused:selected {
    -fx-background-color: -fx-focus-color;
}

.split-pane:horizontal > .split-pane-divider {
    -fx-border-color: transparent #1d1d1d transparent #1d1d1d;
    -fx-background-color: transparent, derive(#1d1d1d,20%);
}

.split-pane {
    -fx-padding: 1 0 0 0;
}

.menu-bar {
    -fx-background-color: derive(#1d1d1d,20%);
}

.context-menu {
    -fx-background-color: derive(#1d1d1d,50%);
}

.menu-bar .label {
    -fx-font-size: 14pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 0.9;
}

.menu .left-container {
	-fx-background-color: black;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}

/* 
 * Metro style Push Button
 * Author: Pedro Duque Vieira
 * http://pixelduke.wordpress.com/2012/10/23/jmetro-windows-8-controls-on-java/
 */
.button {
    -fx-padding: 5 22 5 22;   
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #1d1d1d;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
  -fx-background-color: white;
  -fx-text-fill: #1d1d1d;
}

.button:focused {
    -fx-border-color: white, white;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #1d1d1d;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color,30%);
}
</pre>

Kita sekarang perlu melampirkan CSS ke Scene. Kita dapat melakukan ini melalui kode, tetapi kita akan menggunakan Scene Builder untuk menambah ke berkas fxml: 


#### Melampirkan CSS ke RootLayout.fxml

1. Buka berkas `RootLayout.fxml` di Scene Builder. 

2. Pilih akar `BorderPane` di Hierarchy. Dibawah kelompok *Properties* tambah berkas `DarkTheme.css` file as stylesheet.   
![DarkTheme untuk RootLayout](/assets/library/javafx-8-tutorial/part4/darktheme-rootlayout.png)


#### Lampirkan CSS ke PersonEditDialog.fxml

1. Buka berkas `PersonEditDialog.fxml` di Scene Builder. Pilih akar `AnchorPane` dan pilih `DarkTheme.css` in the kelompok *Properties* sebagai stylesheet.

2. atar belakang masih berwarna putih, jadi tambah kelas gaya `background` ke akar `AnchorPane`.   
![Menambah kelas gaya](/assets/library/javafx-8-tutorial/part4/darktheme-personeditdialog.png)

3. Pilih tombol OK dan pilih *Default Button* di tampilan Properties. Ini akan merubah warnah dan membuat tombol baku ketika tombol *enter* ditekan oleh pengguna.


#### Lampirkan CSS ke PersonOverview.fxml

1. Buka berkas `PersonOverview.fxml` di Scene Builder. Pilih akar `AnchorPane` pada kelompok *Hierarchy*. Pada Properties tambahkan `DarkTheme.css` sebagai stylesheet.

2. Anda seharusnay melihat beberapa perubahan saat ini. Tabel dan tombol berwarna hitam. Semua kelas gaya `.table-view` dan `.button` dari `modena.css` diterapkan ke tabel dan tombol. Karena kita telah mendefinisikan ulang (dan dengan demikian diganti) beberapa dari gaya di penyesuaian CSS kita, gaya baru akan diterapkan secara otomatis.

3. Anda mungkin perlu menyesuaian ukuran dari tombol sehingga semua teks ditampilkan.

4. Pilih `AnchorPane` yang tepat didalam `SplitPane`.   
![Background Style Select](/assets/library/javafx-8-tutorial/part4/background-style-select.png)   

5. Pada keompok *Properties* pilih `background` sebagai kelas gaya. Latarbelakang akan berwarna hitam.   
![Background Style](/assets/library/javafx-8-tutorial/part4/background-style.png)


#### Label dengan Gaya yang berbeda

Saat ini semua label pada sisi kanan memliki ukuran yang sama. Sudah ada beberapa gaya yang didefinisikan dalam berkas CSS bernama`.label-header` dan `.label-bright` kita akan gunakan ini sebagai gaya.

1. Pilih label *Person Details* dan tambahkan `label-header` sebagai kelas gaya.   
![Label Header Style](/assets/library/javafx-8-tutorial/part4/label-header-style.png)

2. Untuk tiap label di sisi kanan kolom (dimana rinncian person ditampilkan) tambah kelas gaya `label-bright`.   
![Label Bright Style](/assets/library/javafx-8-tutorial/part4/label-bright-style.png)


*****


## Menambah Ikon Aplikasi

Saat ini aplikasi kita hanya memiliki ikon baku di palang judul dan palang tugas:

![Default Icon](/assets/library/javafx-8-tutorial/part4/default-app-icon.png)

Akan lebih terlihat lebih baik jika memiliki ikon khusus:

![Custom Icon](/assets/library/javafx-8-tutorial/part4/custom-app-icon.png)


### Berkas Ikon

Tempat yang memungkinkan untuk mendapatkan ikon2 ada di  [Icon Finder](http://www.iconfinder.com). Saya telah mengunduhe [address book icon](http://www.iconfinder.com/icondetails/86957/32/).

Buat sebuah folder didalam proyek AddressApp bernama **resources** dan subfolder bernama **images**.Taruh ikon dalam images folder. Struktur folder anda akan terlihat seperti :

![Custom Icon File](/assets/library/javafx-8-tutorial/part4/custom-icon-file.png)


### Atur Ikon Ke Scene

Untuk mengatur ikon pada scene, tambahkan baris berikut pada metode `start(...)` didalam `MainApp.java`


##### MainApp.java

<pre class="prettyprint lang-java">
this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
</pre>

Keseluruhan metode `start(...)` terlihat seperti ini :

<pre class="prettyprint lang-java">
public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("AddressApp");

    // Set the application icon.
    this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

    initRootLayout();

    showPersonOverview();
}
</pre>

Anda tentunya juga dapat menambah ikon pada stage dari  dialog pembaharuan person:


### Berikutnya?

Didalam [Tutorial Bagian 5](/library/javafx-8-tutorial/id/part5/) kita akan menggunakan XML sebagai penyimpanan data.


##### Beberapa artikel menarik lainnya

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
