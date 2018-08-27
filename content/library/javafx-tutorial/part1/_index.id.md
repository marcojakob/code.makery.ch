+++
title = "Bagian 1: Scene Builder"
date = 2014-04-19
updated = 2015-03-12
description = "Belajar untuk mengatur proyek JavaFX. Bagian ini merupakan salah satu dari tujuh tutorial yang membahas tentang mendesain, memprogram dan penyebarang aplikasi alamat dengan JavaFX."
image = "addressapp-part1.png"
prettify = true
comments = true 
commentsIdentifier = "/library/javafx-8-tutorial/id/part1/"
aliases = [ 
  "/library/javafx-8-tutorial/id/part1/"
]
weight = 1

[[sidebars]]
header = "Unduh kode sumber"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Bagian 1 - Proyek dari Eclise <em>(Diperlukan setidaknya JDK 8u40)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-1.zip"
+++

![Tangkapan layar AddressApp Bagian 1](addressapp-part1.png)

### Pembahasan dibagian 1

* Mengenal JavaFX
* Membuat dan memulai proyek JavaFX
* Menggunakan Scene Builder untuk desain antar muka pengguna
* Struktur dasar aplikasi menggunakan pola Model-View-Controller (MVC)


*****


### Prasyarat

* [Java JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) terbaru (termasuk **JavaFX 8**).
* Eclipse 4.4 atau lebih besar dengan e(fx)clipse plugin. Eclipse 4.3 atau lebih besar dengan e(fx)clipse plugin dar [website e(fx)clipse](http://efxclipse.bestsolution.at/install.html#all-in-one). Sebagai alternatif bisa menggunakan [situs pembaharuan](http://www.eclipse.org/efxclipse/install.html) untuk instalasi Eclipse.
* [Scene Builder 8.0](http://gluonhq.com/products/scene-builder/) (provided by Gluon because [Oracle hanya memberikan ini dalam bentuk code](http://www.oracle.com/technetwork/java/javase/downloads/sb2download-2177776.html)).


### Konfigurasi Eclipse 

Kita perlu untuk memberitahu Eclipse untuk menggunakan JDK 8 dan dimana dapat ditemukannya Scene Builder:

1. Buka preferensi Eclipse dan arahkan ke  *Java | Installed JREs*.

2. Klik *Add...*, pilih *Standard VM* dan pilih *Directory* instalasi JDK 8 anda.

3. Hapus JRE atau JDK lainnya, sehingga **JDK 8 menjadi baku**.   
![Preferensi JDK](preferences-jdk.png)

4. Arahkan ke *Java | Compiler*. atur **Compiler compliance level to 1.8**.   
![Preferensi kepatuhan](preferences-compliance.png)

5. Arahkan ke preferensi *JavaFX*. Rincikan alamat pengeksekusi Scene Builder.   
![Preferensi JavaFX](preferences-javafx.png)


### Link-link membantu

Anda mungkin ingin menandai tautan berikut:

* [Java 8 API](http://docs.oracle.com/javase/8/docs/api/) - JavaDoc kelas-kelas baku java
* [JavaFX 8 API](http://docs.oracle.com/javase/8/javafx/api/) - JavaDoc untuk kelas-kelas JavaFX
* [ControlsFX API](https://controlsfx.bitbucket.io/) - JavaDoc untuk kontrol-kontrol tambahan JavaFX [ControlsFX project](http://fxexperience.com/controlsfx/)
* [Oracle's JavaFX Tutorials](http://docs.oracle.com/javase/8/javafx/get-started-tutorial/get_start_apps.htm) - Tutorial resmi JavaFX oleh Oracle

Sekarang, mari kita mulai!


*****


## Buat Sebuah Proyek JavaFX

Di Eclipse (dengan e(fx)clipse telah terpasang) arahakan ke *File | New | Other...* dan pilij *JavaFX Project*.   
tetapkan nama proyek (contoh *AddressApp*) dan klik *Finish*.

Hapus paket *application* berserta isinya jika tela dibuat secara otomatis.


### Membuat Paket-Paket

Tepat dimulai dari awal kita akan mengikuti prinsip-prinsip desain perangkat lunak yang bagus. Satu prinsip yang sangat penting bahwa [**Model-Tampilan-Pengendali** (Model-View-Controller)](http://en.wikipedia.org/wiki/Model_View_Controller). Berdasar pada hal ini kita memisahkan kode kita menjadi tiga bagian dan membuat masing-masing paket (klik kanan pada src-folder, *New... | Package*):

* `ch.makery.address` - berisi *umumnya* kelas-kelas pengendali (=logika bisnis)
* `ch.makery.address.model` - memuat kelas-kelas model
* `ch.makery.address.view` - memuat tampilan-tampilan

**Catatan:** Paket tampilan kita juga akan memuat beberapa kontroller yang secara langsung terhubung pada tampilan. Sebuat saja **view-controllers**.


*****


## Buat berkas Tataletak FXML 

Ada dua cara untuk membuat antarmuka pengguna. Entah menggunakan berkas XML atau langsung memprogram semuanya didalam Java. Carilah di internet, dan anda pasti menemukan 2 cara ini. Kita akan menggunakan XML (berakhiran .fxml) untuk kebanyakan bagian. Saya menemukan ini cara yang lebih bersih untuk menjaga controller dan view terpisah secara mandiri. Selanjutnya, kita dapat menggunakan Scene Builder untuk memperbaharui XML, yang berarti kita tidak akan langsung berhubungan dengan XML.

Klik kanan pada paket ch.makery.address.view dan buat berkas baru bernama `PersonOverview`.

![New FXML Document](new-fxml-document.png)

![New PersonOverview](new-person-overview.png)



*****


##  Mendesain dengan Scene Builder

<div class="alert alert-warning">
  <strong>Note:</strong> Jika anda tidak berhasil mengikuti, unduh kode sumber dari bagian-bagian tutorial ini, dan cobalah dengan fxml yang telah disertakan.
</div>

(jika Scene builder tidak dapat terbuka, pergi ke "Window | Prefrences | JavaFX" dan atur 

Klik kanan pada `PersonOverview.fxml` dan pilih *Open with Scene Builder*. Sekarang anda seharusnya melihat Scene Builder dengan sebuah *AncherPane* (terlihat di bawah Hierarchy pada sisi kiri).

1. Pilih *Anchor Pane* di Hierarchy adan sesuaikan ukuran di bawah pada Layout (pada sisi kanan):   
![Ukuran Anchor Pane](anchor-pane-size.png)

2. Tambah sebuah *Split Pane (Horizontal Flow)* tutorial/part1/anchor-pane-size.png
 2. Tambah sebuah Split Pane (Horizontal Flow) dengan menyeret dari Library kepada area utama. Klik kanan pada *Split Pane* pada tampilan *Hierarchy* dan pilih *Fit to Parent*.   
![Sesuaikan pada induk](fit-to-parent.png)

3. Seret *TableView* (dari *Controls*) ke bagian sisi kiri dari *SplitPane*. Pilih TableView (bukan sebuah kolom) dan atur kendala Tataletak pada TableView. Didalam *AnchorPane* kamu akan bisa selalu mengatur jangkar pada 4 perbatasan ([Informasi lebih lanjut pada tataletak](http://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm)).   
![TableView Anchors](table-view-anchors.png)

4. Pada menu *Preview | Show Preview in Window* untuk melihat apakah memiliki perilaku yang tepat. Coba ubah ukuran jendela. Ukuran TableView seharusnya mengikuti perubahan jendela, sebagaimana telah di kaitkan pada perbatasan.

5. Mengganti teks kolom (pada Properties) ubah ke "First Name" dan "Last Name".   
![Teks kolom](column-texts.png)

6. Pilih*TableView* lalu *constrained-resize* untuk *Column Resize Policy* (pada Properties). Ini memastikan bahwa kolom-kolom akan selalu mengisi ruang kosong yang ada.   
![Kebijakan perubahan ukuran kolom](column-resize-policy.png)

7. Tambahkan *Label* pada sisi kanan dengan teks "Person Details" (petunjuk: gunakan pencarian untuk menemukan *Label*). Sesuaikan tataletak menggunakan jangkar.  
![Detail label Persons](person-details-label.png)

8. Tambahkan *GridPane* pada sisi kanan, pilih dan sesuaikan tataletak menggunakan jangkar (atas, kanan, dan kiri).    
![Tataletak GridPane](grid-pane-layout.png)

9. Tambah label-label berikut ke sel-sel.   
*Catatan: Untuk menambah pada GridPane pilih sebuah baris(akan berwarna kuning), klik kanan dan pilih "Add Row".*   
![Tambah Label-Lbael](add-labels.png)

10. Tambah sebuah *ButtonBar* di bawah, lalu tambah 3 tombol pada bar. Sekarang, atur jangkar (kanan dan bawah) pada *ButtonBar* sehingga itu tetap pada tempat yang tepat.   
![Kelompok Button](button-group.png)

11. Sekarang anda seharusnya melihat sesuatu seperti berikut. Gunakan menu *Preview* untuk mencoba perilaku perubahan ukuran.
![Pratinjau](scene-builder-preview.png)



*****


##  Buat Aplikasi Utama

Kita perlu FXML baru untuk tata letak akar yang akan berisi sebuah palang menu dan membungkus `PersonOverview.fxml` yang baru kita buat.

1.  Buat *dokumen FXML* didalam paket view bernama `RootLayout.fxml`. Kali ini pilih *BorderPane* sebagai elemen akar.
![RootLayout baru](new-root-layout.png)

2. Buka `RootLayout.fxml` didalam Scene Builder.

3. Ubah ukuran *BorderPane* dengan *Pref Width* diatur ke 600 dan *Pref Height* diatur ke 400.   
![Ukuran RootLayout](root-layout-size.png)

4. Tambahkan *MenuBar* ke celah teratas. Kita tidak akan menerapkan fungsi menu saat ini.   
![MenuBar](menu-bar.png)


###  Kelas Utama JavaFX

Sekarang kita perlu membuat **main java class** yang menjalankan aplikasi kita dengan `RootLayout.fxml` dan menambah `PersonOverview.fxml` pada posisi tengah. 

1. Klik kanan pada proyek dan pilih *New | Other...* kemudian *JavaFX Main Class*.   
![Kelas JavaFX Main](new-main-class.png)

2. Kita akan panggil `MainApp` dan menaruh ini didalam paket Controller `ch.makery.address` (Catatan: ini adalah paket induk dari `view` dan anak paket`model`).   
![Kelas JavaFX Main baru](new-main-class2.png)


Kelas `MainApp.java` dihasilkan dari `Application` dan berisi dua metode. Ini adalah struktur dasa yang kita perlukan untuk memulai Aplikasi JavaFX. Bagian terpenting untuk kita adalah metode `start(Stage primaryStage)`.Ini secara otomatis dipanggil dari dalam metode `main`.

Seperti yang anda lihat metode `start(...)` menerima `Stage` sebagai parameter. Grafik berikut menjelaskan struktur dari tiap aplikasi JavaFX:

![Berkas FXML baru](javafx-hierarchy.png)   
*Image Source: http://www.oracle.com*

**Seperti sebuah drama pertunjukan**: `Stage` adalah penampung utama yang biasanya adalah `Window`dengan sebuah batasan, dan beserta tombol-tombol mengecilkan, membesarkan, dan tutup. Didalam `Stage` anda menambah `Scene` yang tentunya dapat diganti oleh `Scene` lainnya. Didalam `Scene` simpul seperti `AnchorPane`, `TextBox`, dan lain-lain ditambahkan.

Untuk informasi lebih lanjut mengenai ini, beralih pada [Working with the JavaFX Scene Graph](http://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm).


*****

Buka `MainApp.java` dan ganti kode yang ada dengan :

<pre class="prettyprint lang-java">
package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}
</pre>

Berbagai macam komentar seharusnya dapat memberikan anda beberapa petunjuk mengenai apa yang dilakukan.

Jika anda menjalankan aplikasi sekarang, anda seharusnya melihat sesuatu seperti tangkapan layar seperti di awal mula artikel.


### Permasalahan yang sering terjadi

Jika JavaFX tidak dapat menemukan berkas `fxml`  yang telah dispesifikasikan, anda mungkin mendapatkan pesan error seperti:

`java.lang.IllegalStateException: Location is not set.`

Untuk mengatasi ini, periksa duakali nama dari berkas fxml tersebut.
Jika masih tidak berfungsi, unduh kode sumber dari tutorial ini, dan coba dengan `fxml` terlampir.

<div class="alert alert-warning">
  Jika masih tidak dapat berfungsi, unduh kode sumber dari tutorial ini dan cobalah dengan fxml yang telah termasuk.
</div>


*****

### Berikutnya ?

Di [Tutorial bagian 2](/id/library/javafx-tutorial/part2/) kita akan menambah beberapa data fungsi pada AddressApp.


##### Beberapa artikel menarik lainnya

* [JavaFX Dialogs (resmi)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
