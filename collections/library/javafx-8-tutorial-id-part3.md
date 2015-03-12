---
layout: article
title: "Tutorial JavaFX 8 - Bagian 3: Interaksi pengguna"
date: 2014-04-24 00:00
updated: 2014-01-05 00:00
slug: javafx-8-tutorial/id/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-id-part3.md
description: "Bereaksi terhadap perubahan seleksi pada TableView. Menambah, memperbaharui, dan menghapus bulir-bulir dari table dan juga memvalidasi masukan pengguna."
image: /assets/library/javafx-8-tutorial/part3/addressapp-part3.png
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
    active: true
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
- header: "Unduh kode sumber"
  body:
  - text: Bagian 3 - Proyek dari Eclise <em>(Diperlukan setidaknya JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-3.zip
    icon-css: fa fa-fw fa-download
- header: Bahasa
  languages: true
  body:
  - text: English
    link: /library/javafx-8-tutorial/part3/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part3/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/part3/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part3/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/part3/
    icon-css: fa fa-fw fa-globe
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/part3/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Français
    link: /library/javafx-8-tutorial/fr/part3/
    icon-css: fa fa-fw fa-globe
---

![Tangkapan layar AddressApp bagian 3](/assets/library/javafx-8-tutorial/part3/addressapp-part3.png)


## Pembahasan dibagian 3

* **Beraksi pada perubahan pemilihan** pada table.
* Menambah fungsi tombol **tambah**, **pembaharuan**, dan **hapus**.
* Buat **dialog munculan** untuk memperbaharui person.
* **Validasi masukan pengguna**.


*****


## Beraksi Pada Pemilihan Table

Jelas kita belum menggunakan sisi tepat dari aplikasi. Ide untuk menampilkan detail mengengai person disi kanan ketika pengguuna memilih person di table.

Pertama tambah metode didalam `PersonOverviewController` yang membantu kita untuk mengisi label-label dengan data dari sebuah `Person`.

Buat metode bernama `showPersonDetails(Person person)`. Buat semua label-label dengan mengatur teks menggunakan `setText(...)` dengan detail dari person. Jika parameter `null` dilewatkan, maka semua label harus bersih.


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Fills all text fields to show details about the person.
 * If the specified person is null, all text fields are cleared.
 * 
 * @param person the person or null
 */
private void showPersonDetails(Person person) {
    if (person != null) {
        // Fill the labels with info from the person object.
        firstNameLabel.setText(person.getFirstName());
        lastNameLabel.setText(person.getLastName());
        streetLabel.setText(person.getStreet());
        postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
        cityLabel.setText(person.getCity());

        // TODO: We need a way to convert the birthday into a String! 
        // birthdayLabel.setText(...);
    } else {
        // Person is null, remove all the text.
        firstNameLabel.setText("");
        lastNameLabel.setText("");
        streetLabel.setText("");
        postalCodeLabel.setText("");
        cityLabel.setText("");
        birthdayLabel.setText("");
    }
}
</pre>


### Mengganti Birthday Date ke String

Anda akan melihat bahwa kita tidak dapat mengatur `birthday` ke label `Label` dikarenakan memiliki tipe `LocalDate` dan bukan `String`. Kita perlu membentuk tanggal lebih dahulu.

Kita akan menggunakan perubahan dari `LocalDate` dan `String` di beberapa tempat. Ini praktik yang baik unutk menciptakan kelas pembantu dengan metode `static`. Kita akan memanggil ini `DateUtil` dan menaruh dipaket terpisah  bernama `ch.makery.address.util`:


##### DateUtil.java

<pre class="prettyprint lang-java">
package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling dates.
 * 
 * @author Marco Jakob
 */
public class DateUtil {
	
	/** The date pattern that is used for conversion. Change as you wish. */
	private static final String DATE_PATTERN = "dd.MM.yyyy";
	
	/** The date formatter. */
	private static final DateTimeFormatter DATE_FORMATTER = 
			DateTimeFormatter.ofPattern(DATE_PATTERN);
	
	/**
     * Returns the given date as a well formatted String. The above defined 
     * {@link DateUtil#DATE_PATTERN} is used.
     * 
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Converts a String in the format of the defined {@link DateUtil#DATE_PATTERN} 
     * to a {@link LocalDate} object.
     * 
     * Returns null if the String could not be converted.
     * 
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String dateString) {
        try {
        	return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks the String whether it is a valid date.
     * 
     * @param dateString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {
    	// Try to parse the String.
    	return DateUtil.parse(dateString) != null;
    }
}
</pre>

<div class="alert alert-info">
**Petunjuk:** Kamu bisa merubah bentu dari tanggal dengan mengganti
`DATE_PATTERN`. Untuk semua kemungkinan pola ang ada lihat <a class="alert-link" href="http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">DateTimeFormatter</a>.
</div>


#### Gunakan DateUtil

Sekarang kita perlu menggunakan `DateUtil` yang baru di metod `showPersonDetails` dari `PersonOverviewController`. Ganti *TODO* yang telah ada sebelumnya dengan baris berikut:

<pre class="prettyprint lang-java">
birthdayLabel.setText(DateUtil.format(person.getBirthday()));
</pre>


### Mendengarkan Perubahan Pemilihan

Untuk mendapat informasi ketika pengguna memilih person di tabel person, kita perlu *mendengarkan perubahan-perubahan*.

Ada antarmuka yang di JavaFX bernama [`ChangeListener`](http://docs.oracle.com/javase/8/javafx/api/) dengan sebuah metode bernama `changed(...)`. Metode ini memiliki tiga parameter: `observable`, `oldValue`, and `newValue`.

Kita akan menciptakan sebuah `ChangeListener` menggunakan Java 8 *lambda expression*. Mari tambahkan beberapa baris ke metode `initialize()` didalam`PersonOverviewController`. Sekarang ini terlihat seperti ini:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
@FXML
private void initialize() {
    // Initialize the person table with the two columns.
    firstNameColumn.setCellValueFactory(
            cellData -> cellData.getValue().firstNameProperty());
    lastNameColumn.setCellValueFactory(
            cellData -> cellData.getValue().lastNameProperty());

    // Clear person details.
    showPersonDetails(null);

    // Listen for selection changes and show the person details when changed.
    personTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showPersonDetails(newValue));
}
</pre>

Dengan `showPersonDetails(null);` kita mengatur ulang person detail.

Dengan `personTable.getSelectionModel...` kita mendapatkan *selectedItemProperty* dari tabel person dan menambah pendengar. Kapan saja pengguna memilih person di tabel *Ekspresi lambda* di eksekusi. Kita mengambil person baru terpilih dan melewatkannya ke metode  `showPersonDetails(...)`.

Coba **jalankan aplikasi anda**, pastikan ketika anda memilih person di tabel, detail mengenai person ditampilkan pada sisi kanan.

Jika tidak berfungsi, anda bisa membandingkan kelas `PersonOverviewController` punya anda dengan [PersonOverviewController.java](/assets/library/javafx-8-tutorial/part3/PersonOverviewController.java).


*****

## Tombol Hapus

Antarmuka pengguna telah berisi tombol hapus, tapi tanpa fungsi. Kita kan membuat aksi untuk tombol didalam *Scene Builder*. Metode apapun didalam kontroller kita yang berketerangan `@FXML` (atau publik) adalah dapat diakses oleh  *Scene Builder*. Jadi, mari pertama kita tambahkan metode hapus pada akhir kelas `PersonOverviewController`:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Called when the user clicks on the delete button.
 */
@FXML
private void handleDeletePerson() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    personTable.getItems().remove(selectedIndex);
}
</pre>

Sekarang buka berkas `PersonOverview.fxml` dari *SceneBuilder*. Pilih tombol hapus, buka kelompok *Code* dan pilih `handleDeletePerson` di dropdown of **On Action**.

![Sedang beraksi](/assets/library/javafx-8-tutorial/part3/handle-delete.png)

<div class="alert alert-info">
**Ingat:** Setelah membuat perubahan pada berkas FXML di Scene Builder, mungkin perlu di segarkan (f5) pada Eclipse, agar perubahan diterapkan.
</div>



### Penanganan Kesalahan

Jika anda menjalankan Aplikasi, anda seharusnya dapat menghapus person terpilih dari tabel. Tapi apa yang terjadi jika **tombol delete di klik, tapi tiada person yang terpilih di tabel ?**

Akan ada `ArrayIndexOutOfBoundsException` karena tidak dapat menghapus person pada indeks -1. Indeks -1 dikembalikan oleh `getSelectedIndex()` - yang berarti tidak ada terpilih.

Tentu saja mengabaikan kesalahan seperti ini tidak bagus. Kita harus membiarkan pengguna tahu bahwa dia harus memilih person sebelum melakukan penghapusan. (Bahkan lebih baik jika kita melumpuhkan tombol hapus sehingga pengguna tidak dapat kesempatan untuk melakukan hal yang salah).

Kita akan menambah dialog munculan untuk memberitahu pengguna. Anda akan perlu **menambah pustaka** untuk [Dialogs](/blog/javafx-8-dialogs/): 

1. Unduh [controlsfx-8.0.6_20.jar](https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/controlsfx-8.0.6_20.jar) (anda juga dapat langung mengunduh di [ControlsFX Website](http://fxexperience.com/controlsfx/)).   
**Penting: The ControlsFX harus versi `8.0.6_20` atau lebih tinggi agar dapat bekerja dengan `JDK 8u20`, sebagaimana telah ada perubahan bersar yang telah dikenalkan pada versi tersebut.**
2. Buat subfolder **lib** dalam proyek dan tambahkan berkas controlsfx-jar ke folder ini.
3. Tambah pustaka untuk **classpath**: proyek anda: di Eclipse klik kanan pada berkas  *Build Path* | *Add to Build Path*. Sekarang Eclipse tau dimana letak pustaka ini.

![Pustaka ControlsFX](/assets/library/javafx-8-tutorial/part3/controlsfx-library.png)

Dengan beberapa perubahan pada metode `handleDeletePerson()`, kita bisa menampilkan dialog munculan sederhana ketika pengguna tekan tombol hapus saat tidak ada persoon terpilih pada tabel:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Called when the user clicks on the delete button.
 */
@FXML
private void handleDeletePerson() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        personTable.getItems().remove(selectedIndex);
    } else {
        // Nothing selected.
        Dialogs.create()
            .title("No Selection")
            .masthead("No Person Selected")
            .message("Please select a person in the table.")
            .showWarning();
    }
}
</pre>

<div class="alert alert-info">
Untuk lebih banyak contoh bagaimana menggunakan DIalogs baca <a class="alert-link" href="/blog/javafx-8-dialogs/">JavaFX 8 Dialogs</a>.
</div>



*****


## Dialog Baru dan Pembaharuan

Aksi baru dan pembaharuan perlu sedikit tindakan: Kita akan perlu kustom dialog (stage baru) dengan bentuk meminta pengguna untuk detail tentang person.


### Desain Dialog

1. Buat fxml baru bernama `PersonEditDialog.fxml` didalam paket *view*.   
![Buat Dialog pembaharuan](/assets/library/javafx-8-tutorial/part3/person-edit-dialog1.png)

2. Gunakan `GridPane`, `Label`, `TextField` dan `Button` untuk membuat dalog seperti:   
![Dialog pembaharuan](/assets/library/javafx-8-tutorial/part3/person-edit-dialog2.png)   

*Jika anda tidak ingin repot, bisa mengunduh pada [PersonEditDialog.fxml](/assets/library/javafx-8-tutorial/part3/PersonEditDialog.fxml).* 


### Buat Pengendali

Buat Pengendali untuk Dialog sebagai `PersonEditDialogController.java`:

##### PersonEditDialogController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
        	Dialogs.create()
		        .title("Invalid Fields")
		        .masthead("Please correct invalid fields")
		        .message(errorMessage)
		        .showError();
            return false;
        }
    }
}
</pre>

Beberapa hal mengenai pengendali ini:

* Metode `setPerson(...)` dapat dipanggil dari kelas lain untuk mengatur person yang di perbaharui.
* Ketika pengguna menekan tombol OK, metode `handleOk()` dipanggil. Pertama beberapa validasi dilakukan dengan memanggil metode `isInputValid()`. Hanya jika validasi telah berhasil, objek person di isi dengan data yang telah dimasukan oleh pengguna. Perubahan ini akan secara langsung di terapkan ke objek person yang telah di lewatkan ke `setPerson(...)`!
* boolean `okClicked` digunakan sehinga pemanggil dapat menentukan ketika pengguna menekan tombol Ok atau Cancel.


### Kaitkan Tampilan dan Pengendali

Dengan dibuatnya tampilan (fxml) dan pengendali, kita perlu menghubungkan mereka bersama:

1. Buka `PersonEditDialog.fxml`.
2. Di kelompok *Controller* dibagian kiri, pilih `PersonEditDialogController` sebagai kelas controller class.
3. Atur **fx:id** pada semua `TextField` dengan pengendali yang sesuai.
4. Atur **onAction** dari dua tombol ke metode penanganan yang sesuai.



### Membuka Dialog

Tambah metode untuk memuat dan menampilkan person dialog didalam `MainApp`:   


##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * Opens a dialog to edit details for the specified person. If the user
 * clicks OK, the changes are saved into the provided person object and true
 * is returned.
 * 
 * @param person the person object to be edited
 * @return true if the user clicked OK, false otherwise.
 */
public boolean showPersonEditDialog(Person person) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        PersonEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPerson(person);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
</pre>

Tambahkan metode berikut pada `PersonOverviewController`. Metode-metode ini akan memanggil `showPersonEditDialog(...)` dari`MainApp` ketika pengguna menekan tombol baru atau pembaharuan.   

##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Called when the user clicks the new button. Opens a dialog to edit
 * details for a new person.
 */
@FXML
private void handleNewPerson() {
    Person tempPerson = new Person();
    boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
    if (okClicked) {
        mainApp.getPersonData().add(tempPerson);
    }
}

/**
 * Called when the user clicks the edit button. Opens a dialog to edit
 * details for the selected person.
 */
@FXML
private void handleEditPerson() {
    Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
    if (selectedPerson != null) {
        boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
        if (okClicked) {
            showPersonDetails(selectedPerson);
        }

    } else {
        // Nothing selected.
        Dialogs.create()
            .title("No Selection")
            .masthead("No Person Selected")
            .message("Please select a person in the table.")
            .showWarning();
    }
}
</pre>

Buka berkas `PersonOverview.fxml` di Scene Builder. Pilih metode yang sesuai di *On Action* ntuk tombol baru dan pembaharuan.


*****

## Selesai!

nda seharusnya memiliki *Address Application* yang telah berfungsi. Aplikasi ini telah dapat menambah, pembaharuan, dan menghapus persons. Bahkan ada beberapa pengesahan untuk bidang teks untuk menghindari kesalahan masukan dari pengguna.

Saya harap konsep dan struktur dari aplikasi ini akan dapat membuat anda menulis Aplikasi JavaFX anda sendri.


### Berikutnya?

[Tutorial Bagian 4](/library/javafx-8-tutorial/id/part4/) kita akan menambahkan gaya CSS.


##### Beberapa artikel menarik lainnya

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)