---
layout: article
title: "Tutorial JavaFX 8 - Bagian 7: Penyebaran"
date: 2014-05-10
updated: 2015-03-12
slug: javafx-tutorial/id/part7
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-id-part7.md
description: "Bagaimana untuk menyebarkan aplikasi JavaFX sebagai paket asli. Membuat pemasang untuk Windows, MacOS, dan Linux."
image: /assets/library/javafx-tutorial/part7/addressapp-macos.png
published: true
prettify: true
comments: true
sidebars:
- header: "Seri Artikel"
  body:
  - text: "Pengenalan"
    link: /library/javafx-tutorial/id/
    paging: Intro
  - text: "Bagian 1: Scene Builder"
    link: /library/javafx-tutorial/id/part1/
    paging: 1
  - text: "Bagian 2: Model dan TableView"
    link: /library/javafx-tutorial/id/part2/
    paging: 2
  - text: "Bagian 3: Berinteraksi dengan pengguna"
    link: /library/javafx-tutorial/id/part3/
    paging: 3
  - text: "Bagian 4: Memberikan gaya dengan CSS"
    link: /library/javafx-tutorial/id/part4/
    paging: 4
  - text: "Bagian 5: Menyimpan data sebagai XML"
    link: /library/javafx-tutorial/id/part5/
    paging: 5
  - text: "Bagian 6: Bagan Statistika"
    link: /library/javafx-tutorial/id/part6/
    paging: 6
  - text: "Bagian 7: Penyebaran"
    link: /library/javafx-tutorial/id/part7/
    paging: 7
    active: true
- header: "Unduh kode sumber"
  body:
  - text: Bagian 7 - Proyek dari Eclise <em>(Diperlukan setidaknya JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-7.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Bahasa
  collection: library
  item: javafx-tutorial
  part: part7
  active: id
---

![Tangkapan layar AddressApp bagian 7](/assets/library/javafx-tutorial/part7/addressapp-part7.png)

Saya pikir saya akan menulis satu bagian terakhir dari seri tutorial ini untuk menunjukan bagaimana untuk menyebarkan (ini berarti memaket dan publikasi) AddressApp.


*****

## Pembahasan di bagian 7

* Menyebarkan aplikasi JavaFX sebagai paket **paket asli** dengan e(fx)eclipse


*****

## Apa itu Penyebaran

Penyebaran adalah proses memaket dan mengantarkan perangkat lunak ke pengguna. Ini adalah bagian penting dari pengembangan perangkat lunak semenjak hubungan pertama pengguna dengan perangkat lunak kita.

Java beriklan dengan slogan **Tulis sekali, jalankan dimanapun** untuk menggambarkan keuntungan dari bahasa java di gunakan *lintas platform*. Idealnya ini berarti aplikasi Java ktia bisa berjalan di semua perangkat yang dilengkapi dengan Java Virtual Machine (JVM).

Di masa yang lampau, pengalaman pengguna untuk memasang aplikasi java tidak selalu berjalan mulus, jika pengguna tidak memiliki versi java yang diperlukan, dia harus memasang itu terlebih dahulu, ini membuat beberapa kesulitan seperti diperlukan hak admin, isu kecocokan antara versi java, dan lain lain.

Untungnya JavaFX menyediakan pilihan penyebaran baru bernama **Pemaketan asli** (juga disebut paket aplikasi tersendiri). Sebuah pemaketan asli merupakan bungkusan yang berisi kode aplikasi dan java runtime (berdasar platform).

Dokumentasi resmi dari Oracle yang berisi panduan untuk semua kemungkinan [JavaFX deployment options](http://docs.oracle.com/javafx/2/deployment/jfxpub-deployment.htm). 

Pada kirimian ini saya akan menunjukan bagaimana membuat **paket asli** dengan Eclipse dan [**e(fx)clipse plugin**](http://www.eclipse.org/efxclipse/).


*****

## Membuat Paket Alami

Tujuan membuat aplikasi mandiri dalam satu folder di komputer pengguna. Di tangkapan layarn akan menampilkan bagaimana akan terlihat untuk AddressApp (di Windows):

![Paket alami AddressApp](/assets/library/javafx-tutorial/part7/native-package.png)

Folder `app` berisi data aplikasi dan folder `runtime` berisi Java runtime berdasar platform.

Untuk membuat ini lebih nyaman bagi pengguna, kita akan menyediakan sebuah pemasangan (installer):

* Sebuah `exe` file pemasangan untuk Windows
* Sebuah `dmg` file pemasangan untuk MacOs.

e(fx) plugin akan membantu kita menghasilkan paket alami dan pemasangan (installer).


### Langkah 1- Perbaharui buld.fxbuild

Berkas `build.fxbuild` digunakan oleh e(fx)clipse untuk menghasilkan sebuah berkas yang akan digunakan alat pembangun Ant(Ant Build). Jika anda tidak memiliki berkas `build.fxbuild`, buat sebuah proyek JavaFX baru di Eclipse dan gandakan berkas yang dihaslkan.

1. Buka `build.fxbuild`.

2. Isi semua bidang yang berisi bintang. *Untuk MacOS, jangan gunakan spasi di judul aplikasi, karena ini akan menyebabkan masalah.*    
![Pengaturan fxbuild](/assets/library/javafx-tutorial/part7/fxbuild-settings.png)

3. Sebagai **bentuk pemaketan** pilih `exe` untuk Windows, `dmg` untuk MacOS, dan `rpm` untuk Linux.

4. Klik pada tautan `Generate ant build.xml only` ((pada sisi kanan)e).   
![Bangkitkan ant build](/assets/library/javafx-tutorial/part7/generate-ant-build.png)

5. Verifikasi folder `build` baru dan sebuah berkas `build.xml` telah dibuat.


### Langkah 2 - Tambahkan Ikon Pemasangan

Kita ingin memiliki ikon yang menarik untuk pemasang :

* [AddressApp.ico](/assets/library/javafx-tutorial/part7/AddressApp.ico) untuk berkas ikon pemasang.
* [AddressApp-setup-icon.bmp](/assets/library/javafx-tutorial/part7/AddressApp-setup-icon.bmp) untuk pemasang ikon Splash scren.
* [AddressApp.icns](/assets/library/javafx-tutorial/part7/AddressApp.icns) untuk ikon pemasang MacOs.
* [AddressApp-volume.icns](/assets/library/javafx-tutorial/part7/AddressApp-volume.icns) untuk ikon Mac
* 

1. Buat subfolder didalam folder `build`:
  * `build/package/windows` (hanya digunakan untuk windows)
  * `build/package/macosx` (hanya digunakan untuk macos)
2. Salin ikon yang sesuai dari atas ke subfolder ini. Ini seharusnya terlihat seperti : 
![Installer Icons](/assets/library/javafx-tutorial/part7/installer-icons.png)
3. **Penting**: Nama ikon harus sama tepat dengan **judul aplikasi**yang telah di tentukan dalam `build.fxbuild`:
  * `YourAppTitle.ico`
  * `YourAppTitle-setup-icon.bmp`
  * `YourAppTitle.icns`
  * `YourAppTitle-volume.icns`


### Langkah 3 - Menambah sumber-sumber

Folder `resources` idak tersalin secara otomatis, kita harus secara manual menambahkanya ke build direktori:

1. Buat subfolder berikut didalam folder `build`:
  * `build/dist`   
2. Salin folder `resources` (berisi gambar-gambar aplikasi) ke `build/dist`.    
![Build Resources](/assets/library/javafx-tutorial/part7/build-resources.png)


### Langkah 4 - Perbaharui build.xml untuk memasukan ikon-ikon

E(fx)clipse telah menghasilkan berkas `build/build.xml` yang siap di eksekusi oleh **Ant**. Ikon pemasang kita dan sumber gambar tidak hanyak langsung berfungsi.

E(fx)eclipse tidak bisa dikatkan untuk memasukan sumber-sumber tambahan seperti folder `resources` dan beberapa ikon pemasangan yang telah kita tambahkan diatas, kita harus secara manual memperbaharui `build.xml`:

Buka `build.xml` dan temukan jalur `fxant`. Tambahkan satu baru untuk `${basedir}` (ini akan membuat ikon pemasangan tersedia):


##### build.xml - add "basedir"

<pre class="prettyprint lang-xml">
&lt;path id="fxant"&gt;
  &lt;filelist&gt;
    &lt;file name="${java.home}\..\lib\ant-javafx.jar"/&gt;
    &lt;file name="${java.home}\lib\jfxrt.jar"/&gt;
    <mark>&lt;file name="${basedir}"/&gt;</mark>
  &lt;/filelist&gt;
&lt;/path&gt;
</pre>    


Temukan blok `fx:resources id="appRes"` dibagian bawah dari berkas, tambah sebuah baris untuk reources `resources`:

##### build.xml - add "resources"

<pre class="prettyprint lang-xml">
&lt;fx:resources id="appRes"&gt;
    &lt;fx:fileset dir="dist" includes="AddressApp.jar"/&gt;
    &lt;fx:fileset dir="dist" includes="libs/*"/&gt;
    <mark>&lt;fx:fileset dir="dist" includes="resources/**"/&gt;</mark>
&lt;/fx:resources&gt; 
</pre>


Entah bagaimana, nomor versi tidak akan ditambah di `fx:application` yang membuat pemasang selalu baku ke versi `1.0` (seperti yang ditunjukan oleh beberpa orang di komentar). Untuk menyelasaikan ini, secara manual tambah nomor versi (terimakasih kepada Marc karena telah [memberitahukan](http://code.makery.ch/library/javafx-tutorial/part7/#comment-1566725959)):

##### build.xml - add "version"

<pre class="prettyprint lang-xml">
&lt;fx:application id="fxApplication"
    name="AddressApp"
    mainClass="ch.makery.address.MainApp"
    <mark>version="1.0"</mark>
/>
</pre>

Kita sudah bisa menjalankan `build.xml` sebagai Ant build pada saat ini. Ini dapat menghasilkan berkas jar yang dapat dijalankan, tetapi kita ingin melangkah lebih maju dan membuat installer yang bagus.


### Langkah 5 (WINDOWS) - Window pemasang Exe

![AddressApp di Windows](/assets/library/javafx-tutorial/part7/addressapp-windows.png)

Dengan **Inno Setup** kita dapat membuat pemasang aplikasi Window sebagai sebuah berkas `.exe` `.exe` yang dihasilkan akan melakukan pemasangan tingkat pengguna (tidak ada memerlukan ijin admin), juga jalan pintas akan dibuat (menu atau Desktop).

1. Unduh [Inno Setup 5 or later](http://www.jrsoftware.org/isdl.php). Pasang Inno setup pada komputer anda, Ant Skrip kita akan menggunakan ini secara otomatis untuk menghasilkan pemasangan.

2. Beritahu Windows mengenai jalur pemasangan pada Inno Setup (contoh `C:\Program Files (x86)\Inno Setup 5`). Untuk melakukan ini, tambah Inno Setup pada variabel `Path` di Variabel lingkungan Windows (Windows Environtment Variable). Jika anda tidak tau dimana harus menemukanya, baca [Bagaimana cara mengatur jalur dan variabel lingkungan di Windows](http://www.computerhope.com/issues/ch000549.htm).

3. Mulai ulang Eclipse dan lanjut dengan langkah 6.


### Langkah 5 (MAC) - MacOS pemasangan dmg 

![AddressApp di Mac](/assets/library/javafx-tutorial/part7/addressapp-macos.png)

Unutk membuat MacOs `dmg` pemasangan seret dan lepaskan (drag-and-drop) tidak ada alat tambahan yang diperlukan. 

Catatan: Agar gambar pemasang dapat bekerja, harus memiliki nama yang sama dengan nama aplikasi.


### langkah 5 (LINUX) - Linux pemasangan rpm

Untuk pilihan pemaketan lain (`msi` untuk windows, `rpm` untuk Linux) lihat pemaketan alami [blog post](https://blogs.oracle.com/talkingjavadeployment/entry/native_packaging_for_javafx) atau ini [oracle documentation](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm#A1324980).


### Langkah 6 - Run build.xml

Sebagai langkah akhir, kita menjalankan `build.xml` dengan Ant: *klik kanan* pada berkas `build.xml` *Run As* | *Ant Build*.

![Run Ant Build](/assets/library/javafx-tutorial/part7/run-ant-build.png)

Proses ini akan memakan waktu (sekitar 1 menit pada komputer saya).

Jika semua telah berhasil, kamu seharusnya menemukan bundle alami di folder `build/deploy/bundles`. Berikut adalah contoh versi window 

![Deployed File](/assets/library/javafx-tutorial/part7/deployed-file.png)


Berkas `AddressApp-1.0.exe` dapat digunakan sebagai sebuah berkas pemasangan. Pemasangan ini akan menyalin bundle ke `C:/Users/[yourname]/AppData/Local/AddressApp`.


### Berikutnya

Saya harap tutorial ini dapat membantu anda untuk memulai dengan JavaFX dan anda dapat menulis proyek JavaFX anda dari sini.

Saya menghargai berbagai umpan balik. Jangan ragu untuk menulis komentar jika anda memiliki bebrapa saran atau beberapa hal lain yang tidak jelas.



##### Beberapa artikel menarik lainnya

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

