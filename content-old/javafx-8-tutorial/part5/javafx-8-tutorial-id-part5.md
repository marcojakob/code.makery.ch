---
layout: article
title: "Tutorial JavaFX 8 - Bagian 5: Menyimpan data sebagai XML"
date: 2014-04-25 01:00
updated: 2015-01-05 00:00
slug: javafx-8-tutorial/id/part5
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-id-part5.md
description: "Menyimpan data sebagai XML denan JAXB. Belajar bagaimana menggunakan JavaFX FileChooser dan JavaFX Menu."
image: /assets/library/javafx-8-tutorial/part5/addressapp-part5.png
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
  - text: "Bagian 5: Menyimpan data sebagai XML"
    link: /library/javafx-8-tutorial/id/part5/
    paging: 5
    active: true
  - text: "Bagian 6: Bagan Statistika"
    link: /library/javafx-8-tutorial/id/part6/
    paging: 6
  - text: "Bagian 7: Penyebaran"
    link: /library/javafx-8-tutorial/id/part7/
    paging: 7
- header: "Unduh kode sumber"
  body:
  - text: Bagian 5 - Proyek dari Eclise <em>(Diperlukan setidaknya JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-5.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Bahasa
  collection: library
  item: javafx-8-tutorial
  part: part5
  active: id
---

![Tangkapan layar AddressApp bagian 5](/assets/library/javafx-8-tutorial/part5/addressapp-part5.png)


## Pembahasan di bagian 5

* **Memaksa data sebagai XML**
* Menggunakan JavaFX **FileChooser**
* Menggunakan JavaFX **Menu**
* Menyimpan berkas terbuka paling akhir di preferensi pengguna



*****

Hingga saat ini aplikasi alamat kita hanya menyimpan data di memori. Setiap saat kita menutup aplikasi, data akan hilang. Jadi udah waktunya kita mulai berpikir menyimpan data.


## Menyimpan Preferensi Pengguna

Java mengijinkan kta untuk menyimpan keadaan aplikasi menggunakan kelas bernama `Preferences`. Bergantung pada sistem operasi, `Preferences` disimpan di beberapa tempat( berkas pendaftaran di Window).

Kita tidak akan bisa menyimpan `Preferences` untuk menyimpan keseluruhan data alamat. Tetapi ini mengijinkan kita untuk **menyimpan keadaan aplikasi**.  Salah satunya adalah alamat pada **berkas yang terakhir kali dibuka**. Dengan informasi ini, kita dapat memuat keadaan terakhir aplikasi ketika pengguna menjalankan kembali aplikasi.

Dua metode ini menangani penyimpanan dan menerima preferensi. Tambah kode ini ke akhir kelas `MainApp`:


##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * Returns the person file preference, i.e. the file that was last opened.
 * The preference is read from the OS specific registry. If no such
 * preference can be found, null is returned.
 * 
 * @return
 */
public File getPersonFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    String filePath = prefs.get("filePath", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

/**
 * Sets the file path of the currently loaded file. The path is persisted in
 * the OS specific registry.
 * 
 * @param file the file or null to remove the path
 */
public void setPersonFilePath(File file) {
    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    if (file != null) {
        prefs.put("filePath", file.getPath());

        // Update the stage title.
        primaryStage.setTitle("AddressApp - " + file.getName());
    } else {
        prefs.remove("filePath");

        // Update the stage title.
        primaryStage.setTitle("AddressApp");
    }
}
</pre>


## Memaksa Data sebagai XML

### Kenapa XML?

Salah satu cara paling umum untuk memaksa data menggunakan basis data. Nasis data biasanya berisi beberapa jenis hubungan data (seperti tabel) selama data yang kita perlukan menyimpan objek. Ini di sebut object-relational ketidak cocokan impedansi [(object-relational impedance mismatch)](http://wikipedia.org/wiki/Object-relational_impedance_mismatch).Ini sedikit perlu usaha untuk mencocokan objek ke tabel database relasional. Ada beberapa kerangka kerja yang dapat membantu mencocokan (contoh [Hibernate](http://www.hibernate.org/), the most popular one) tetapi ini tetap memerlukan beberapa usaha.

Untuk model data sederhana kita, akan sangat mudah menggunakan XML. Kita akan menggunakan pustaka bernama [JAXB](https://jaxb.java.net/) (**J**ava **A**rchitecture for **X**ML **B**inding). Dengan beberapa baris kode JAXB akan memungkinkan kita membangkitkan keluaran XML seperti:

##### Example xml output

<pre class="prettyprint lang-xml">
&lt;persons&gt;
    &lt;person&gt;
        &lt;birthday&gt;1999-02-21&lt;/birthday&gt;
        &lt;city&gt;some city&lt;/city&gt;
        &lt;firstName&gt;Hans&lt;/firstName&gt;
        &lt;lastName&gt;Muster&lt;/lastName&gt;
        &lt;postalCode&gt;1234&lt;/postalCode&gt;
        &lt;street&gt;some street&lt;/street&gt;
    &lt;/person&gt;
    &lt;person&gt;
        &lt;birthday&gt;1999-02-21&lt;/birthday&gt;
        &lt;city&gt;some city&lt;/city&gt;
        &lt;firstName&gt;Anna&lt;/firstName&gt;
        &lt;lastName&gt;Best&lt;/lastName&gt;
        &lt;postalCode&gt;1234&lt;/postalCode&gt;
        &lt;street&gt;some street&lt;/street&gt;
    &lt;/person&gt;
&lt;/persons&gt;
</pre>




### Menggunakan JAXB

JAXB telah termasuk dalam JDK. Ini berarti kita tidak perlu menyertakan pustaka tambahan.

JAXB menyedikan 2 fitur utama: Kemampuan untuk **mengumpulkan** java objek ke XML, dan **memecah** data XML ke java objek.

Agar JAXB dapat melakukan perubahan, kita perlu mempersiapkan model.


#### Mempersiapkan Kelas Model Untuk JAXB

Data yang mau disimpan menetap di variabel `personData` didalam kelas `MainApp`. AXB memerlukan kelas paling atas untuk di kasi keterangan `@XmlRootElement`. `personData` adalah kelas `ObservableList` dan kita tidak bisa memberikan keterangan ke `ObservableList`. Jadi kita perlu untuk membuat kelas lain yang hanyak akan digunakan untuk menampung daftar dari `Persons` untuk menyimpan ke XML. 

Kelas baru yang kita buat akan bernama `PersonListWrapper` dan taruh ke paket `ch.makery.address.model`.


##### PersonListWrapper.java

<pre class="prettyprint lang-java">
package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

	private List&lt;Person> persons;

	@XmlElement(name = "person")
	public List&lt;Person> getPersons() {
		return persons;
	}

	public void setPersons(List&lt;Person> persons) {
		this.persons = persons;
	}
}
</pre>

Perhatikan dua penejelasan : 

* `@XmlRootElement` menjelaskan nama dari elemen akar.
* `@XmlElement` adalah pilihan nama yang bisa kita tentukan untuk elemen.


#### Membaca dan Menulis Data dengan JAXB

Kita akan membuat kelas `MainApp` bertanggungjawab untuk membaca dan menulis data Person. Tambah dua metode barikut ke akhir `MainApp.java`:


<pre class="prettyprint lang-java">
/**
 * Loads person data from the specified file. The current person data will
 * be replaced.
 * 
 * @param file
 */
public void loadPersonDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

        personData.clear();
        personData.addAll(wrapper.getPersons());

        // Save the file path to the registry.
        setPersonFilePath(file);

    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not load data");
        alert.setContentText("Could not load data from file:\n" + file.getPath());
        
        alert.showAndWait();
    }
}

/**
 * Saves the current person data to the specified file.
 * 
 * @param file
 */
public void savePersonDataToFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Wrapping our person data.
        PersonListWrapper wrapper = new PersonListWrapper();
        wrapper.setPersons(personData);

        // Marshalling and saving XML to the file.
        m.marshal(wrapper, file);

        // Save the file path to the registry.
        setPersonFilePath(file);
    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could not save data");
        alert.setContentText("Could not save data to file:\n" + file.getPath());
        
        alert.showAndWait();
    }
}
</pre>

Pengumpulan/pemecahan telah siap, mari kita buat menu simpan/muat yang dapat digunakan.


## Menangani Menu Aksi

Di `RootLayout.fxml` telah ada menu, tetap ita belum menggunakanya. Sebelum kita menambah aksi ke menu, kita terlebih dahulu membuat bulir-bulir menu.

Buka berkas `RootLayout.fxml` di Scene Builder dan seret bulir menu yang diperlukan dari kelompok *library* ke `MenuBar` di kelompok *hierarchy*. Buat **New**, **Open...**, **Save**, **Save As...**, dan **Exit** bulir menu.

![Menambah bulir-bulir menu](/assets/library/javafx-8-tutorial/part5/add-menu-items.png)

Petunjuk: Gunakan pengaturan *Accelerator* di kelompok *Properties* anda bisa membuat jalan pintas pada menu.


### RootLayoutController

Untuk menangani aksi menu, kita perlu menambah kelas pengendali. Buat sebuah kelas `RootLayoutController` didalam paket `ch.makery.address.view`. 

Tambahkan kode berikut ke controller:


##### RootLayoutController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

import org.controlsfx.dialog.Dialogs;

import ch.makery.address.MainApp;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.savePersonDataToFile(file);
		}
	}

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("AddressApp");
	        alert.setHeaderText("About");
	        alert.setContentText("Author: Marco Jakob\nWebsite: http://code.makery.ch");
	        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
</pre>


#### FileChooser

Catat bahwa metode yang menggunakan kelas `FileChooser` didalam `RootLayoutController`. ertama, sebuah kelas objek `FileChooser` dibuat. kemudian ekstensi saring di tambah sehingga hanya berkas yang berakhiran `.xml` ditampilkan, dan terakhir fileChooser ditampilkan diatas stage utama

Jika pengguna menutup dialog tanpa memilih sebuah berkas, nilai `null` kan dikembalikan. Jika tidak, kita akan bisa memilih berkas dan melewatkanya ke `loadPersonDataFromFile(...)` atau metode `savePersonDataToFile(...)` dari `MainApp`. 


### Menghubungkan tampilan fxml ke pengendali

1. Buka `RootLayout.fxml` di Scene Builder. Dikelompok *Controller* pilih `RootLayoutController` sebagai Controller class. 

2. Pada kelompok *Hierarchy* dan pilih sebuah bulir menu. Di *Code* dibawah **On Action** anda seharusnya melihat pilihan dari semua metode pengendali yang ada. Pilih metode yang tepat untuk tiap menu. 
![Menu Actions](/assets/library/javafx-8-tutorial/part5/menu-actions.png)

3. Ulangi langkah-langkah ini untuk tiap bulir menu.

4.  Tutup Scenee Builder dan **segarkan (F5)** Ini akan membuat Eclipse tau telah ada perubahan yang dilakukan dari Scene Builder.


### Menghubungkan MainApp dan RootLayoutController

Di beberapa tempat, `RootLayoutController` perlu rujukan balik ke `MainApp`. Kita belum memberikan rujukan ke `RootLayoutController`.

Buka kelas `MainApp` dan ganti metode `initRootLayout()` dengan:

<pre class="prettyprint lang-java">
/**
 * Initializes the root layout and tries to load the last opened
 * person file.
 */
public void initRootLayout() {
    try {
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class
                .getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // Give the controller access to the main app.
        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Try to load last opened person file.
    File file = getPersonFilePath();
    if (file != null) {
        loadPersonDataFromFile(file);
    }
}
</pre>

Perhatikan ada dua perubahan: Baris-baris yang *memberikan akses pengendali ke MainApp* an 3 baris terakhir untuk *memuat berkas person terakhir yang dibuka*.


### Percobaan

Melakukan ujicoba dari aplikasi anda seharusnya dapat menggunakan menu untuk menyimpan data person ke sebuah berkas.

Ketika anda buka berkas `xml` di sebuat editor, anda akan mengetahui birthday tidak tersimpan secara benar, tag `<birthday/>` kosong, dikarenakan JAXB tidak tahu bagaimana merubah `LocalDate` ke XML. Kita harus menyediakan  `LocalDateAdapter` yang telah disesuaikan untuk menjelaskan perubahan ini.

Buat kelas baru didalam `ch.makery.address.util` bernama `LocalDateAdapter` berisi:

##### LocalDateAdapter.java

<pre class="prettyprint lang-java">
package ch.makery.address.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter (for JAXB) to convert between the LocalDate and the ISO 8601 
 * String representation of the date such as '2012-12-03'.
 * 
 * @author Marco Jakob
 */
public class LocalDateAdapter extends XmlAdapter&lt;String, LocalDate> {

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}
}
</pre>

Buka `Person.java` dan tambah keterangan pada metode `getBirthday()`:

<pre class="prettyprint lang-java">
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public LocalDate getBirthday() {
    return birthday.get();
}
</pre>

Sekarang coba lagi, Coba simpan dan muat berkas xml. Setelah memulai ulang, ini seharusnya secara otomatis memuat berkas terakhir digunakan.



## Bagaimana Ini Bekerja


Mari kita lihat bagaimana semua ini berfungsi:

1. Aplikasi telah dijalankan menggunakan metode `main(...)` didalam `MainApp`.
2. Pembangun `public MainApp()` dipanggil dan menambah beberapa contoh data.
3. Metode `MainApp`s `start(...)` dipanggil dan memanggil `initRootLayout()` untuk menginisialisasi tata letak dasar dari `RootLayout.fxml`. Berkas fxml memiliki informasi mengenai pengendali mana yang digunakan dan hubungan tampilan pada `RootLayoutController`. 
4. `MainApp` mendapat `RootLayoutController` dari pemuat fxml, dan melewatkan sebuah referensi ke pengendali itu sendiri. Dengan referensi ni, pengendali dapat mengakses publik metode dari `MainApp`.
5. Pada akhir metode `initRootLayout()`kita akan mencoba untuk mendapatkan berkasperson yang terakhir dibuka dari `Preferences` Jika Preferences telah tau berkas XML, kita akan memuat data dari berkas XML ini. Ini tampaknya akan menulis ulang data contoh dari pembangun.


### Berikutnya

Pada Tutorial [Bagian 6](/library/javafx-8-tutorial/id/part6/) kita akan menambah bagan statistika tanggal lahir.


##### Beberapa artikel menarik lainnya

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
