---
layout: article
title: "JavaFX 8 教程 - 第三部分：与用户的交互"
date: 2014-10-08 00:00
updated: 2015-01-15 00:00
slug: javafx-8-tutorial/zh-cn/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-zh-cn-part3.md
description: null
image: /assets/library/javafx-8-tutorial/part3/addressapp-part3.png
published: true
prettify: true
comments: true
sidebars:
- header: "系列文章"
  body:
  - text: "简介"
    link: /library/javafx-8-tutorial/zh-cn/
    paging: 简介
  - text: "第一部分：Scene Builder"
    link: /library/javafx-8-tutorial/zh-cn/part1/
    paging: 1
  - text: "第二部分：Model 和 TableView"
    link: /library/javafx-8-tutorial/zh-cn/part2/
    paging: 2
  - text: "第三部分：与用户的交互"
    link: /library/javafx-8-tutorial/zh-cn/part3/
    paging: 3
    active: true
  - text: "第四部分：CSS 样式"
    link: /library/javafx-8-tutorial/zh-cn/part4/
    paging: 4
  - text: "第五部分：将数据用 XML 格式存储"
    link: /library/javafx-8-tutorial/zh-cn/part5/
    paging: 5
  - text: "第六部分：统计图"
    link: /library/javafx-8-tutorial/zh-cn/part6/
    paging: 6
  - text: "第七部分：部署"
    link: /library/javafx-8-tutorial/zh-cn/part7/
    paging: 7
- header: "下载源代码"
  body:
  - text: Part 3 as Eclipse Project <em>(requires at least JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-3.zip
    icon-css: fa fa-fw fa-download
- header: 语言
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
    active: true
  - text: Русский
    link: /library/javafx-8-tutorial/ru/part3/
    icon-css: fa fa-fw fa-globe
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/part3/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/javafx-8-tutorial/fr/part3/
    icon-css: fa fa-fw fa-globe
---

![Screenshot AddressApp Part 3](/assets/library/javafx-8-tutorial/part3/addressapp-part3.png)


## 第3部分的主题：

1. 在表中**反应选择的改变**（TableView中）。
2. 增加**增加**，**编辑**和**删除**按钮的功能。
3. 创建自定义**弹出对话框**编辑人员。
4. **验证用户输入**。


*****


## 响应表的选择

显然，我们还没有使用应用程序的右边。想法是当用户选择表中的人员时，在右边显示人员的详情。

首先，让我们在`PersonOverviewController`添加一个新的方法，帮助我们使用单个人员的数据填写标签。

创建方法`showPersonDetails(Person person)`。遍历所有标签，并且使用`setText(…)`方法设置标签的文本为个人的详情。如果null作为参数传递，所有的标签应该被清空。


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


### 转换生日日期为字符串

你注意到我们没有设置`birthday`到标签中，因为它是`LocalDate`类型，不是`String`。我们首先需要格式化日期。

在几个地方上我们使用`LocalDate`和`String`之间的转换。好的实践是创建一个带有`static`方法的帮助类。我们称它为`DateUtil`，并且把它放到单独的包中，称为`ch.makery.address.util`。


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
**提示**：你能通过改变`DATE_PATTERN`修改日期的格式。所有可能的格式参考 <a class="alert-link" href="http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">DateTimeFormatter</a>.
</div>


#### 使用DateUtil

现在，我们需要在`PersonOverviewController`的`showPersonDetails`方法中使用我们新建的`DateUtil`。使用下面这样替代我们添加的`TODO`。

<pre class="prettyprint lang-java">
birthdayLabel.setText(DateUtil.format(person.getBirthday()));
</pre>


### 监听表选择的改变

为了当用户在人员表中选择一个人时获得通知，我们需要**监听改变**。

在JavaFX中有一个接口称为[`ChangeListener`](http://docs.oracle.com/javase/8/javafx/api/)，带有一个方法`changed()`。该方法有三个参数：`observable`, `oldValue`和`newValue`。

我们使用*Java 8 lambda*表达式创建这样一个`ChangeListener`。让我们添加一些行到`PersonOverviewController`的`initialize()`方法中。现在看起来是这样的。


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

使用`showPersonDetails(null)`，我们重设个人详情。

使用`personTable.getSelectionModel...`，我们获得人员表的`selectedItemProperty`，并且添加监听。不管什么时候用户选择表中的人员，都会执行我们的`lambda`表达式。我们获取新选择的人员，并且把它传递给`showPersonDetails(...)`方法。

现在试着**运行你的应用程序**，验证当你选择表中的人员时，关于该人员的详情是否正确的显示。

如果有些事情不能工作，你可以对比下[PersonOverviewController.java](/assets/library/javafx-8-tutorial/part3/PersonOverviewController.java)中的`PersonOverviewController`类



*****

## 删除按钮

我们的用户接口已经包含一个删除按钮，但是没有任何功能。我们能在*SceneBuilder*中的按钮上选择动作。在我们控制器中的任何使用`@FXML`（或者它是公用的）注释的方法都可以被*Scene Builder*访问。因此，让我们在`PersonOverviewController`类的最后添加一个删除方法。


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

现在，使用*SceneBuilder*打开`PersonOverview.fxml`文件，选择*Delete*按钮，打开*Code*组，在**On Actin**的下拉菜单中选择`handleDeletePerson`。

![On Action](/assets/library/javafx-8-tutorial/part3/handle-delete.png)


### 错误处理

如果你现在运行应用程序，你应该能够从表中删除选择的人员。但是，**当你没有在表中选择人员时点击删除按钮时会发生什么呢**。

这里有一个`ArrayIndexOutOfBoundsException`，因为它不能删除掉索引为-1人员项目。索引-1由`getSelectedIndex()`返回，它意味着你没有选择项目。

当然，忽略这种错误不是非常好。我们应该让用户知道在删除时必须选择一个人员。（更好的是我们应该禁用删除按钮，以便用户没有机会做错误的事情）。

我们添加一个弹出对话框通知用户，你将需要**添加一个库*[Dialogs](/blog/javafx-8-dialogs/)：

1. 下载[controlsfx-8.0.6_20.jar](https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/controlsfx-8.0.6_20.jar) （你也能从[ControlsFX Website](http://fxexperience.com/controlsfx/)中获取）。
**重要：ControlsFX必须是8.0.6_20以上版本才能在`JDK8U20`以上版本工作。**
2. 在项目中创建一个**lib**子目录，添加controlsf jar文件到该目录下。
3. 添加库到你的项目**classpath**中。在Eclipse中*右击jar文件*|选择*Build Path*| *Add to Build Path*。现在Eclipse知道这个库了。

![ControlsFX Libaray](/assets/library/javafx-8-tutorial/part3/controlsfx-library.png)

对`handleDeletePerson()`方法做一些修改后，不管什么时候用户没有选择表中的人员时按下删除按钮，我们能显示一个简单的对话框。


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
更多如何使用Dialog的示例，请阅读<a class="alert-link" href="/blog/javafx-8-dialogs/">JavaFX 8 Dialogs</a>.
</div>



*****


## 新建和编辑对话框

新建和编辑的动作有点工作：我们需要一个自定义带表单的对话框（例如：新的Stage），询问用户关于人员的详情。



### 设计对话框

1. 在*view*包中创建新的fxml文件，称为`PersonEditDialog.fxml`
![Create Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog1.png)

2. 使用`GridPan`，`Label`，`TextField`和`Button`创建一个对话框，如下所示:   
![Edit Dialog](/assets/library/javafx-8-tutorial/part3/person-edit-dialog2.png)   

*如果你不能完成工作，你能下载这个[PersonEditDialog.fxml](/assets/library/javafx-8-tutorial/part3/PersonEditDialog.fxml).* 


### 创建控制器

为对话框创建控制器`PersonEditDialogController.java`:

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

关于该控制器的一些事情应该注意：

1. `setPerson(…)`方法可以从其它类中调用，用来设置编辑的人员。
2. 当用户点击OK按钮时，调用`handleOK()`方法。首先，通过调用`isInputValid()`方法做一些验证。只有验证成功，Person对象使用输入的数据填充。这些修改将直接应用到Person对象上，传递给`setPerson(…)`。
3. 布尔值`okClicked`被使用，以便调用者决定用户是否点击OK或者Cancel按钮。


### 连接视图和控制器

使用已经创建的视图（FXML）和控制器，需要连接到一起。

1. 使用SceneBuilder打开`PersonEditDialog.fxml`文件
2. 在左边的*Controller*组中选择`PersonEditDialogController`作为控制器类
3. 设置所有**TextField**的`fx:id`到相应的控制器字段上。
4. 设置两个按钮的**onAction**到相应的处理方法上。



### 打开对话框

在`MainApp`中添加一个方法加载和显示编辑人员的对话框。


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

添加下面的方法到`PersonOverviewController`中。当用户按下*New*或*Edit*按钮时，这些方法将从`MainApp`中调用`showPersonEditDialog(...)`。

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

在Scene Builder中打开`PersonOverview.fxml`文件，为New和Edit按钮的*On Action*中选择对应的方法。


*****

## 完成!

现在你应该有一个可以工作的*Address应用*。应用能够添加、编辑和删除人员。这里甚至有一些文本字段的验证避免坏的用户输入。

我希望本应用的概念和结构让开始编写自己的JavaFX应用！玩的开心。


### 下一步是什么？

在教程[第4部分](/library/javafx-8-tutorial/zh-cn/part4/)中将添加一些CSS样式表。


##### 你可能有兴趣的一些其他文章

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)

