---
layout: article
title: "Tutorial JavaFX 8 - Parte 5: Salvando dados como XML"
date: 2014-09-10 00:00
updated: 2014-11-12 00:00
slug: javafx-8-tutorial/pt/part5
canonical: /java/javafx-8-tutorial-part5/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-pt-part5.md
description: "Salvar dados como XML com JAXB. Aprende como usar o FileChooser do JavaFX e o Menu do JavaFX."
image: /assets/library/javafx-8-tutorial/part5/addressapp-part5.png
published: true
prettify: true
comments: false
sidebars:
- header: "Artigos nesta serie"
  body:
  - text: "Introdução"
    link: /library/javafx-8-tutorial/pt/
    paging: Intro
  - text: "Parte 1: Scene Builder"
    link: /library/javafx-8-tutorial/pt/part1/
    paging: 1
  - text: "Parte 2: Modelo e TableView"
    link: /library/javafx-8-tutorial/pt/part2/
    paging: 2
  - text: "Parte 3: Interagindo com o usuário"
    link: /library/javafx-8-tutorial/pt/part3/
    paging: 3
  - text: "Parte 4: Estilos usando CSS"
    link: /library/javafx-8-tutorial/pt/part4/
    paging: 4
  - text: "Parte 5: Salvando dados como XML"
    link: /library/javafx-8-tutorial/pt/part5/
    paging: 5
    active: true
  - text: "Parte 6: Gráficos de Estatistica"
    link: /library/javafx-8-tutorial/pt/part6/
    paging: 6
  - text: "Parte 7: Implantação"
    link: /library/javafx-8-tutorial/pt/part7/
    paging: 7
- header: "Download de Códigos Fonte"
  body:
  - text: "Parte 5 como um projeto Eclipse <em>(versão mínima requirida: JDK 8u20)</em>"
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-5.zip
    icon-css: fa fa-fw fa-download
- header: Linguagens
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-part5/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part5/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Español
    link: /library/javafx-8-tutorial/es/part5/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part5/
    icon-css: fa fa-fw fa-globe
---


![Screenshot AddressApp Part 5](/assets/library/javafx-8-tutorial/part5/addressapp-part5.png)


## Tópicos da Parte 5

* **Persistindo dados como XML**
* Usando o **FileChooser** do JavaFX
* Usando o **Menu** do JavaFX
* Salvando o caminho do último arquivo aberto nas **user preferences** (preferências de usuário)



*****

No momento, os dados de nossa aplicação só estão na memória. Toda vez que nós fechamos a aplicação, os dados são perdidos. Então isso é sobre para começar a pensar sobre armazenagem de dados de forma persistente (persistência de dados).


## Salvando Preferências de Usuário

O Java nos permite salvar alguns estados da aplicação usando uma classe chamada `Preferences`. Dependendo do sistema operacional, as `Preferences` (Preferências) são salvas em diferentes lugares (ex.: o arquivo de registro no Windows).

Nós não conseguiremos usar as `Preferences` (Preferências) pra armazenar nossa agenda inteira. Mas isso nos permite **salvar alguns estados simples da aplicação**. Uma dessas coisas é o **arquivo para o último arquivo aberto**. Com esta informação nós poderíamos carregar o último estado da aplicação sempre que o usuário reiniciar a aplicação.

OS dois métodos seguitnes tomam conta de salvar e recuperar as Preferences (Preferências). Adicione-os ao fim da sua classe `MainApp`:


##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * Retorna o arquivo de preferências da pessoa, o último arquivo que foi aberto.
 * As preferências são lidas do registro específico do SO (Sistema Operacional). 
 * Se tais prefêrencias não puderem  ser encontradas, ele retorna null.
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
 * Define o caminho do arquivo do arquivo carregado atual. O caminho é persistido no
 * registro específico do SO (Sistema Operacional).
 * 
 * @param file O arquivo ou null para remover o caminho
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


## Persistindo Dados como XML

### Por que XML?

Uma das maneiras mais comuns de persistir dados é usando um banco de dados. Bancos de Dados geralmente contém algum tipo de dados relacionais (como tabelas) enquanto os dados que nós precisamos salvar são objetos. Isso é chamado de [incompatibilidade impedância objeto relacional](http://wikipedia.org/wiki/Object-relational_impedance_mismatch). Isso é bastante trabalho combinar objetos a tabelas em banco ddos relacionais. Existem alguns frameworks que ajudam com a combinação (ex.: [Hibernate](http://www.hibernate.org/), o mais popular) mas ainda requer algum trabalho para configurar.

PAra nosso modelo de dados simples é muito mais fácil usar XML. Nós usaremos uma biblioteca chamada [JAXB](https://jaxb.java.net/) (**J**ava **A**rchitecture for **X**ML **B**inding / Arquitetura Java para Ligação XML). Com apenas algumas linhas de código, JAXB nos permitirá gerar saída XML como essa:

##### Exemplo de saída XML

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




### Usando JAXB

JAXB já está incluso no JDK. Isso significa que nós não precisamos incluir qualquer biblioteca adicional.

JAXB provê duas funcionalidades principais: a habilidade de **empacotar** objetos Java em XML e **desempacotar** XML devolta em objetos Java.

Para o JAXB poder fazer a conversão, nós precisamos preparar nosso modelo.


#### Preparando a Classe Modelo (Model) para o JAXB 

Os dados que nós queremos salvar estão armazenados na variável `personData` dentro da nossa classe `MainApp`. JAXB querer que nossa classe seja anotada com `@XmlRootElement`. `personData` é da classe `ObservableList` e nós não podemos pôr quaisquer anotações em `ObservableList`. Então nós precisamos criar mais outra classe que só será usada para conter nossa lista de `Persons` para salvar em XML. 

A nova classe que nós criamos é chamada `PersonListWrapper` e é colocada dentro do pacote `ch.makery.address.model`.


##### PersonListWrapper.java

<pre class="prettyprint lang-java">
package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe auxiliar para envolver uma lista de pessoas. Esta é usada para salvar a
 * lista de pessoas em XML.
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

Note as duas anotações: 

* `@XmlRootElement` define o nome do elemento base.
* `@XmlElement` é um nome opcional nós podemos especificar para o elemento.


#### Lendo e Escrevendo Dados com JAXB

Nós faremos nossa classe `MainApp` responsável por ler e escrever os dados da pessoa. Adicione os dois métodos no fim da `MainApp.java`:


<pre class="prettyprint lang-java">
/**
 * Carrega os dados da pessoa do arquivo especificado. A pessoa atual
 * será substituída.
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
                .title("Erro")
                .masthead("Não foi possível carregar dados do arquivo:\n" 
                          + file.getPath()).showException(e);
    }
}

/**
 * Salva os dados da pessoa atual no arquivo especificado.
 * 
 * @param file
 */
public void savePersonDataToFile(File file) {
    try {
        JAXBContext context = JAXBContext
                .newInstance(PersonListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Envolvendo nossos dados da pessoa.
        PersonListWrapper wrapper = new PersonListWrapper();
        wrapper.setPersons(personData);

        // Enpacotando e salvando XML  no arquivo.
        m.marshal(wrapper, file);

        // Saalva o caminho do arquivo no registro.
        setPersonFilePath(file);
    } catch (Exception e) { // catches ANY exception
        Dialogs.create().title("Erro")
                .masthead("Não foi possível salvar os dados do arquivo:\n" 
                          + file.getPath()).showException(e);
    }
}
</pre>

O empacotamento/desempacotamento está pronto. Vamos criar o menu salvar/carregar para realmente poder usá-lo.


## Lidando Com Ações de Menu

No nosso `RootLayout.fxml` já existe um menu, mas nós não usamos ele ainda. Antes de nós adicionarmos ação ao menu nós criaremos todos os itens de menu primeiro.

Abra o arquivo `RootLayout.fxml` no Scene Builder e arraste os itens de menu necessários do grupo *library* para a `MenuBar` (barra de menu) no grupo *hierarchy*. Crie um item de menu **New**, um **Open...**, um **Save**, um **Save As...**, e um **Exit**.

![Add Menu Items](/assets/library/javafx-8-tutorial/part5/add-menu-items.png)

Dica: Usando a configuração *Accelerator* no grupo *Properties* você pode definir teclas de atalho aos itens de menu.


### O RootLayoutController

Para lidar com ações de menu nós precisaremos de uma nova classe controller. Crie uma classe `RootLayoutController` dentro do pacote `ch.makery.address.view`. 

Adicione o conteúdo seguinte ao controller:


##### RootLayoutController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import org.controlsfx.dialog.Dialogs;

import ch.makery.address.MainApp;

/**
 * O controlador para o root layout. O root layout provê um layout básico
 * para a aplicação contendo uma barra de menu e um espaço onde outros elementos
 * JavaFX podem ser colocados.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController {

    // Referência à aplicação principal
    private MainApp mainApp;

    /**
     * É chamado pela aplicação principal para referenciar a si mesma.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Cria uma agenda vazia.
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * Abre o FileChooser para permitir o usuário selecionar uma agenda
     * para carregar.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Define um filtro de extensão
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostra a janela de salvar arquivo
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * Salva o arquivo para o arquivo de pessoa aberto atualmente. Se não houver
     * arquivo aberto, a janela "salvar como" é mostrada.
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
     * Abre um FileChooser para permitir o usuário selecionar um arquivo
     * para salvar.
     */
    @FXML
    private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		// Define o filtro de extensão
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		// Mostra a janela para salvar arquivo
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

		if (file != null) {
			// Certifica de que esta é a extensão correta
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.savePersonDataToFile(file);
		}
	}

    /**
     * Abre uma janela Sobre.
     */
    @FXML
    private void handleAbout() {
		Dialogs.create()
	        .title("AddressApp")
	        .masthead("Sobre")
	        .message("Autor: Marco Jakob\nWebsite: http://code.makery.ch")
	        .showInformation();
    }

    /**
     * Fecha a aplicação.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
</pre>


#### FileChooser (Selecionador de Arquivos)

Note que os métodos que usam a classe `FileChooser` dentro do `RootLayoutController` acima. Primeiro, um novo objeto da classe `FileChooser` é criado. Então, um filtro de extensão é adicionado então somente arquivos terminhando em `.xml` são mostrados. Finalmente, o selecionador de arquivosé mostrado no topo do primary stage.

Se o usuário fecha a janela sem escolher um arquivo, `null`  é retornado. Caso contrário, nós obtemos o arquivo selecionado e podemos passá-lo ao método `loadPersonDataFromFile(...)` ou ao método `savePersonDataToFile(...)` da `MainApp`. 


### Conectando a View FXML ao Controller

1. Abra o `RootLayout.fxml` no Scene Builder. No grupo *Controller* selecione `RootLayoutController` como Controller class (classe Controller). 

2. Volte ao grupo *Hierarchy* e selecione um item de menu. No grupo *Code* em **On Action** você deve ver uma escolha de todos os métodos disponíveis do controlador. Escolha o método correspondente para cada item de menu.   
![Menu Actions](/assets/library/javafx-8-tutorial/part5/menu-actions.png)

3. Repita os passos para cada item de menu.

4. Feche o Scene Builder e pressione **Refresh (F5)** na pasta raiz do seu projeto. Isso fará o Eclipse consciente das mudanças que você vez no Scene Builder.


### Conectando a MainApp e o RootLayoutController

Em vários lugares, o `RootLayoutController` precisa de uma referência devolta à `MainApp`. Nós não passamos a referência ao `RootLayoutController` ainda.

Abra a classe `MainApp` e substitua o método `initRootLayout()` com o código seguinte:

<pre class="prettyprint lang-java">
/**
 * Inicializa o root layout e tenta carregar o último arquivo
 * de pessoa aberto.
 */
public void initRootLayout() {
    try {
        // Carrega o root layout do arquivo fxml.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class
                .getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        // Mostra a scene (cena) contendo o root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // Dá ao controller o acesso ao main app.
        RootLayoutController controller = loader.getController();
        controller.setMainApp(this);

        primaryStage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Tenta carregar o último arquivo de pessoa aberto.
    File file = getPersonFilePath();
    if (file != null) {
        loadPersonDataFromFile(file);
    }
}
</pre>

Note as duas mudanças: As linhas que *Dão ao controller o acesso ao main app* e as últimas três linhas para *carregar o último arquivo de pessoa aberto*.


### Testando

Fazendo um test drive da sua aplicação você deve poder usar os menus para salvar os dados da pessoa em um arquivo. 

Quando você abrir o arquivo `xml` em um editor você notará que o aniversário não está salvo corretamente, é uma tag `<birthday/>` vazia. A razão disso é que o JAXB não sabe converter a `LocalDate` em XML. Nós devemos fornecer um `LocalDateAdapter` customizado para definir esta conversão.

Crie uma nova classe dentro do pacote `ch.makery.address.util` chamada `LocalDateAdapter` com o conteúdo seguinte:

##### LocalDateAdapter.java

<pre class="prettyprint lang-java">
package ch.makery.address.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adaptador (para JAXB) para converter entre LocalDate e representação String 
 * ISO 8601 da data como '2012-12-03'.
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

Então abra a `Person.java` e adicione a seguinte anotação ao método `getBirthday()`:

<pre class="prettyprint lang-java">
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public LocalDate getBirthday() {
    return birthday.get();
}
</pre>

Agora, teste novamente. Tente salvar e carregar o arquivo XML. Após reiniciar, ele deve carregar automaticamente o último arquivo usado.



## Como Funciona


Vamos ver como isso funciona juntos:

1. A aplicação é inicada usando o método `main(...)` dentro da `MainApp`.
2. O construtor `public MainApp()` é chamado e adiciona algusn dados de exemplo.
3. O método `start(...)` da `MainApp` é chamado e chama o método `initRootLayout()` paara inicializar o root layout do `RootLayout.fxml`. O arquivo fxml tem a informação sobre qual controller usar e liga a view a seu `RootLayoutController`. 
4. A `MainApp` pega o `RootLayoutController` do carregador de fxml e passa a referência de si mesmo ao controller. Com esta referência, o controller pode acessar os métodos (public) da `MainApp`.
5. Ao final do método `initRootLayout()` nós tentamos pegar o *último arquivo de pessoa aberto* de `Preferences`. Se as `Preferences` souberem sobre um arquivo XML, nós carregaremos os dados deste arquivo XML. Isso vai aparentemente sobrescrever os dados de exemplo do construtor. 


### O Que Vem Depois?

No Tutorial [Parte 6](/library/javafx-8-tutorial/pt/part6/) nós adicionaremos um gráfico de estatísticas de aniversário.


##### Alguns outros artigos que você deve achar interessante (em inglês)

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Exemplos de Manipulação de Eventos](/blog/javafx-8-event-handling-examples/)
* [JavaFX Filtrar e Ordenar TableView](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX Renderizador de Células TableView](/blog/javafx-8-tableview-cell-renderer/)
