+++
title = "Bagian 6: Bagan Statistika"
date = 2014-05-09
updated = 2015-03-12
description = "Belajar bagaimana membuat bagan palang JavaFX."
image = "addressapp-part6.png"
prettify = true
comments = true 
commentsIdentifier = "/library/javafx-8-tutorial/id/part6/"
aliases = [ 
  "/library/javafx-8-tutorial/id/part6/"
]
weight = 6

[[sidebars]]
header = "Unduh kode sumber"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Bagian 6 - Proyek dari Eclise <em>(Diperlukan setidaknya JDK 8u40)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-6.zip"
+++

![Tangkapan layar AddressApp bagian 6](addressapp-part6.png)


## Pembahasan di bagian 6 6

* Membuat **Bagan Statistika** untuk menampilkan tanggal lahir.


*****

## Statistika Tanggal lahir

Semua orang di AddressApp memiliki tanggal lahir. Bukankan akan lebih menyenangkan jika ada statistika tentang ketika mereka merayakannya.

Kita akan menggunakan **Bagan Palang** berisi sebuah palang untuk tiap bulan. Tiap palang menampilkan berapa banyak orang yang memiliki tanggal lahir di bulan tersebut.


## Tampilan FXML Statistik

1. Kita mulai dengan membuat berkas `BirthdayStatistics.fxml` didalam paket `ch.makery.address.view` (*Klik kanan pada paket | New | other... | New FXML Document*).   
![FXML Statistika tanggal lahir](birthday-statistics-fxml.png)

2. Buka berkas `BirthdayStatistics.fxml` di Scene Builder.

3. Pilih akar `AnchorPane`. Pada kelompok *Layout* atur *Pref Width* ke 620 dan *Pref Height* ke 450.

4. Tambah `BarChart` ke `AnchorPane`.

5. Klik kanan pada`BarChart` dan pilih *Fit to Parent*.

6. Simpan berkas fxml, pada Eclipse segarkan (f5).

Sebelum kita beralih ke Scene Builder, kita akan terlebih dahulu membuat pengendali dan menghubungkan semuanya pada `MainApp`.


## Pengendali Statistika

DI paket tampilan `ch.makery.address.view` buat kelas java bernama `BirthdayStatisticsController.java`.

Mari kita lihat pada keseluruhan kelas pengendali sebelum saya mulai menjelaskan:


##### BirthdayStatisticsController.java

<pre class="prettyprint lang-java">
package ch.makery.address.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import ch.makery.address.model.Person;

/**
 * The controller for the birthday statistics view.
 * 
 * @author Marco Jakob
 */
public class BirthdayStatisticsController {

    @FXML
    private BarChart&lt;String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList&lt;String> monthNames = FXCollections.observableArrayList();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
    }

    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    public void setPersonData(List&lt;Person> persons) {
    	// Count the number of people having their birthday in a specific month.
        int[] monthCounter = new int[12];
        for (Person p : persons) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series&lt;String, Integer> series = new XYChart.Series&lt;>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i &lt; monthCounter.length; i++) {
        	series.getData().add(new XYChart.Data&lt;>(monthNames.get(i), monthCounter[i]));
        }
        
        barChart.getData().add(series);
    }
}
</pre>


#### Bagaimana Pengendali Bekerja

1. Pengendali akan memerlukan akses ke 2 elemn dari berkas FXML :
   * `barChar`: ini memiliki tipe `String` dan `Integer`. `String` digunakan untuk bulan pada x-axis dan `Integer` digunakan pada jumlah orang pada bulan tertentu.
   * `xAxis`: Kita akan menggunakan ini untuk menambah String bulan.

2. Metode `initialize()` mengisi x-axis dengan daftar semua bulan.

3. Metode `setPersonData(...)` akan di akses oleh kelas `MainApp` untuk mengatur data person. Ini akan mengulang melewati semua orang dan menghitung tanggal kelahiran perbulan, kemudian ni menambah `XYChart.Data` .Data untuk tiap bulan ke seri data. Tiap `XYChart.Data` Objek akan mewakilkan satu palang didalam bagan.


*****

## Menghubungkan Tampilan dan Pengendali.

1. Buka `BirthdayStatistics.fxml` di Scene Builder.

2. Pada kelompok *Controller* atur `BirthdayStatisticsController` sebagai pengendali.

3. Pilih `BarChart` kemudian pilih `barChart` sebagai properti fx:id  (didalam kelommpok *Code*).

4. Pilih `CategoryAxis` dan pilih `xAxis` sebagai prperti fx:id.   
![Kategori Axis](category-axis.png)

5. Kamu mungkin menambah judul ke `BarChart` (di kelompok *Properties*) untuk membuat gaya lebih lanjut.



*****


## Menghubungkan Tampilan/Pengendali dengan MainApp

Kita akan menggunakan mekanisme yang sama untuk  *statistika tanggal lahir* yang kita gunakan untuk memperbaharui *memperbaharui dialog person*, dengan sebuah dialog munculan.

Tambahkan metode berikut ke kelas `MainApp`:


<pre class="prettyprint lang-java">
/**
 * Opens a dialog to show birthday statistics.
 */
public void showBirthdayStatistics() {
    try {
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Birthday Statistics");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller.
        BirthdayStatisticsController controller = loader.getController();
        controller.setPersonData(personData);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>

Semuanya telah di atur, tetapi kita tidak meiliki apapun yang sebenarnya memanggil metode `showBirthdayStatistics()`. Untungnya kita telah memiliki menu di `RootLayout.fxml` yang bisa digunakan.


### Tampilkan Menu Statistika Tanggal lahir  

Di `RootLayoutController` tambah metode berikut yang akan menangani klik pengguna untuk menampilkan bulir menu *statistika tanggal lahir*: 

<pre class="prettyprint lang-java">
/**
 * Opens the birthday statistics.
 */
@FXML
private void handleShowBirthdayStatistics() {
  mainApp.showBirthdayStatistics();
}
</pre>

Sekarang buka berkas `RootLayout.fxml` dengan Scene Builder. Buat *Statistics* `Menu` dengan *Show Statistics* `MenuItem`:

![Menu tampilkan statistika](show-statistics-menu.png)

Pilih *Show Statistics* `MenuItem` dan pilih `handleShowBirthdayStatistics` untuk `On Action` (didalam kelompok *Code*)   

![Tampilkan statistika On Action](show-statistics-on-action.png)

Pada Eclipse, segarkan (F5) **dan cobalah**.


*****

## Informasi lebih lanjut pada bagan JavaFX

Sebuah tempat bagus untuk informasi adalah tutorial dari oracle [Working with JavaFX Charts](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/charts.htm).


### Berikutnya

Di tutorial terakhir [Bagian 7](/id/library/javafx-tutorial/part7/) kita akan menyebarkan aplikasi kita (yaitu paket dan memberikan aplikasi kepada pengguna kita)


##### Beberapa artikel menarik lainnya

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
