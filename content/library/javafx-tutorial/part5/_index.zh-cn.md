+++
title = "第五部分：将数据用 XML 格式存储"
date = 2014-10-08
updated = 2015-01-16
description = "使用JAXB将数据另存为XML。 了解如何使用JavaFX FileChooser和JavaFX菜单。"
image = "addressapp-part5.png"
prettify = true
comments = true 
commentsIdentifier = "/library/javafx-8-tutorial/zh-cn/part5/"
aliases = [ 
  "/library/javafx-8-tutorial/zh-cn/part5/"
]
weight = 5

[[sidebars]]
header = "下载源代码"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-download\"></i> Part 5 as Eclipse Project <em>(requires at least JDK 8u40)</em>"
link = "https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-5.zip"
+++

![Screenshot AddressApp Part 5](addressapp-part5.png)


## 第5部分的主题

* **持久化数据为XML**
* 使用JavaFX的**FileChooser**
* 使用JavaFX的**菜单**
* 在**用户设置**中保存最后打开的文件路径。



*****

现在我们的地址应用程序的数据只保存在内存中。每次我们关闭应用程序，数据将丢失，因此是时候开始考虑持久化存储数据了。


## 保存用户设置

Java允许我们使用`Preferences`类保存一些应用状态。依赖于操作系统，`Perferences`保存在不同的地方（例如：Windows中的注册文件）。

我们不能使用`Preferences`来保存全部地址簿。但是它允许我们**保存一些简单的应用状态**。一件这样事情是**最后打开文件的路径**。使用这个信息，我们能加载最后应用的状态，不管用户什么时候重启应用程序。

下面两个方法用于保存和检索Preference。添加它们到你的`MainApp`类的最后：


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


## 持久性数据到XML

### 为什么是XML？

持久性数据的一种最常用的方法是使用数据库。数据库通常包含一些类型的关系数据（例如：表），当我们需要保存的数据是对象时。这称[object-relational impedance mismatch](http://wikipedia.org/wiki/Object-relational_impedance_mismatch)。匹配对象到关系型数据库表有很多工作要做。这里有一些框架帮助我们匹配（例如：[Hibernate](http://www.hibernate.org/)，最流行的一个）。但是它仍然需要相当多的设置工作。

对于简单的数据模型，非常容易使用XML。我们使用称为[JAXB](https://jaxb.java.net/)（**J**ava **A**rchitecture for **X**ML **B**inding）的库。只需要几行代码，JAXB将允许我们生成XML输出，如下所示：

##### 示例XML输出

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




### 使用JAXB

JAXB已经包含在JDK中。这意味着我们不需要包含任何其它的库。

JAXB提供两个主要特征：**编列(marshal)**Java对象到XML的能力，**反编列(unmarshal)**XML到Java对象。

为了让JAXB能够做转换，我们需要准备我们的模型。


#### 准备JAXB的模型类 

我们希望保持的数据位于`MainApp`类的`personData`变量中。JAXB要求使用`@XmlRootElement`注释作为最顶层的类。`personData`是`ObservableList`类，我们不能把任何注释放到`ObservableList`上。因此，我们需要创建另外一个类，它只用于保存`Person`列表，用于存储成XML文件。

创建的新类名为`PersonListWrapper`，把它放入到`ch.makery.address.model`包中。


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

注意两个注释： 

* `@XmlRootElement` 定义根元素的名称。
* `@XmlElement` 一个可选的名称，用来指定元素。


#### 使用JAXB读写数据

我们让`MainApp`类负责读写人员数据。添加下面两个方法到`MainApp.java`的最后：


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
        Dialogs.create()
                .title("Error")
                .masthead("Could not load data from file:\n" + file.getPath())
                .showException(e);
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
        Dialogs.create().title("Error")
                .masthead("Could not save data to file:\n" + file.getPath())
                .showException(e);
    }
}
</pre>

*编组和解组*已经准备好，让我们创建保存和加载的菜单实际的使用它。


## 处理菜单响应

在我们`RootLayout.fxml`中，这里已经有一个菜单，但是我们没有使用它。在我们添加响应到菜单中之前，我们首先创建所有的菜单项。

在Scene Builder中打开`RootLayout.fxml`，从*library*组中拖曳必要的菜单到*Hierarchy*组的`MemuBar`中。创建**New**，**Open…**，**Save**，**Save As…**和**Exit**菜单项。

![添加菜单项](add-menu-items.png)

提示：使用*Properties*组下的*Accelerator*设置，你能设置菜单项的快捷键。


### RootLayoutController

为了处理菜单动作，我们需要创建一个新的控制器类。在控制器包`ch.makery.address.view`中创建一个类`RootLayoutController`。

添加下面的内容到控制器中：


##### RootLayoutController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import java.io.File;

import javafx.fxml.FXML;
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
		Dialogs.create()
	        .title("AddressApp")
	        .masthead("About")
	        .message("Author: Marco Jakob\nWebsite: http://code.makery.ch")
	        .showInformation();
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

注意在上面的`RootLayoutController`中使用`FileCooser`的方法。首先，创建新的`FileChooser`类对象的，然后，添加扩展名过滤器，以至于只显示以`.xml`结尾的文件。最后，文件选择器显示在主Stage的上面。

如果用户没有选择一个文件关闭对话框，返回`null`。否则，我们获得选择的文件，我们能传递它到`MainApp`的`loadPersonDataFromFile(…)`或`savePersonDataToFile()`方法中。

### 连接fxml视图到控制器

1. 在Scene Builder中打开`RootLayout.fxml`。在*Controller*组中选择`RootLayoutController`作为控制器类。

2. 回到*Hierarchy*组中，选择一个菜单项。在*Code*组中**On Action**下，应该看到所有可用控制器方法的选择。为每个菜单项选择响应的方法。  
![菜单动作](menu-actions.png)

3. 为每个菜单项重复第2步。

4. 关闭Scene Builder，并且在项目的根目录上按下**刷新F5**。这让Eclipse知道在Scene Builder中所做的修改。


### 连接MainApp和RootLayoutController

在几个地方，`RootLayoutController`需要引用`MainApp`类。我们也没有传递一个`MainApp`的引用到`RootLayoutController`。

打开`MainApp`类，使用下面的替代`initRootLayout()`方法：

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

注意两个修改：一行*给控制器访问MainApp*和最后三行*加载最新打开的人员文件*。


### 测试

做应用程序的测试驱动，你应该能够使用菜单保存人员数据到文件中。

当你在编辑器中打开一个`xml`文件，你将注意到生日没有正确保存，这是一个空的`<birthday/>`标签。原因是JAXB不只奥如何转换`LocalDate`到XML。我们必须提供一个自定义的`LocalDateAdapter`定义这个转换。

在`ch.makery.address.util`中创建新的类，称为`LocalDateAdapter`，内容如下：

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

然后打开`Person.jar`，添加下面的注释到`getBirthday()`方法上：

<pre class="prettyprint lang-java">
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public LocalDate getBirthday() {
    return birthday.get();
}
</pre>

现在，再次测试。试着保存和加载XML文件。在重启之后，它应该自动加载最后使用的文件。



## 它如何工作


让我们看下它是如何一起工作的：

1. 应用程序使用`MainApp`中的`main(…)`方法启动。
2. 调用`public MainApp()`构造函数添加一些样例数据。
3. 调用`MainApp`的`start(…)`方法，调用`initRootLayout()`从`RootLayout.fxml`中初始化根布局。fxml文件有关于使用控制器的信息，连接视图到`RootLayoutController`。
4. `MainApp`从fxml加载器中获取`RootLayoutController`，传递自己的引用到控制器中。使用这些引用，控制器随后可以访问`MainApp`的公开方法。
5. 在`initRootLayout`方法结束，我们试着从`Perferences`中获取*最后打开的人员文件*。如果`Perferences`知道有这样一个XML文件，我们将从这个XML文件中加载数据。这显然会覆盖掉构造函数中的样例数据。


### 下一步是什么？

在本教程的[第6部分](/zh-cn/library/javafx-tutorial/part6/)，我们添加一个*生日统计图表*。


##### 一些其它你可能感兴趣的文章

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)
