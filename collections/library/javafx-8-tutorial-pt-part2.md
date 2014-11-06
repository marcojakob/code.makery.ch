---
layout: article
title: "Tutorial JavaFX 8 - Parte 2: Modelo e TableView"
date: 2014-09-10 00:00
updated: 2014-11-06 00:00
slug: javafx-8-tutorial/pt/part2
canonical: /java/javafx-8-tutorial-part2/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-pt-part2.md
description: "Use uma TableView JavaFX para mostrar uma ObservableList de Persons."
image: /assets/library/javafx-8-tutorial/part2/addressapp-part2.png
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
    active: true
  - text: "Parte 3: Interagindo com o usuário"
    link: /library/javafx-8-tutorial/pt/part3/
    paging: 3
  - text: "Parte 4: Estilos usando CSS"
    link: /library/javafx-8-tutorial/pt/part4/
    paging: 4
  - text: "Parte 5: Salvando dados como XML"
    link: /library/javafx-8-tutorial/pt/part5/
    paging: 5
  - text: "Parte 6: Gráficos de Estatistica"
    link: /library/javafx-8-tutorial/pt/part6/
    paging: 6
  - text: "Parte 7: Implantação"
    link: /library/javafx-8-tutorial/pt/part7/
    paging: 7
- header: "Download Sources"
  body:
  - text: "Parte 2 como um projeto Eclipse <em>(versão mínima requirida: JDK 8u20)</em>"
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-2.zip
    icon-css: fa fa-fw fa-download
- header: Linguagens
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-part2/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/part2/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Español
    link: /library/javafx-8-tutorial/es/part2/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/part2/
    icon-css: fa fa-fw fa-globe
---

![Screenshot AddressApp Part 2](/assets/library/javafx-8-tutorial/part2/addressapp-part2.png)


## Tópicos na Parte 2

* Criar a Classe **Model**
* Usando a classe model em uma **ObservableList**
* Mostrar dados na **TableView** usando **Controllers**


*****

## Criar a Classe Model

Nós precisamos de uma classe model para guardar informação sobre as pessoas em nosso agenda. Adicione uma nova classe ao pacote model (`ch.makery.address.model`) chamado `Person` (pessoa). A  classe `Person` (pessoa) terá um pouco de variáveis de instância para o nome, endereço e aniversário. Adicione o código seguinte à classe. Eu explicarei algumas coisas específicas do JAvaFX depois do código.


##### Person.java

<pre class="prettyprint lang-java">
package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe Model para uma Person (pessoa).
 *
 * @author Marco Jakob
 */
public class Person {

	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty street;
	private final IntegerProperty postalCode;
	private final StringProperty city;
	private final ObjectProperty&lt;LocalDate&gt; birthday;

	/**
	 *  Construtor padrão.
	 */
	public Person() {
		this(null, null);
	}
	
	/**
	 * Construtor com alguns dados iniciais.
	 * 
	 * @param firstName Primeiro nome da Pessoa.
	 * @param lastName Sobrenome da Pessoa.
	 */
	public Person(String firstName, String lastName) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
		// Alguns dados de exemplo, apenas para testes.
		this.street = new SimpleStringProperty("some street");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.city = new SimpleStringProperty("some city");
		this.birthday = new SimpleObjectProperty&lt;LocalDate&gt;(LocalDate.of(1999, 2, 21));
	}
	
	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}
	
	public StringProperty streetProperty() {
		return street;
	}

	public int getPostalCode() {
		return postalCode.get();
	}

	public void setPostalCode(int postalCode) {
		this.postalCode.set(postalCode);
	}
	
	public IntegerProperty postalCodeProperty() {
		return postalCode;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}
	
	public StringProperty cityProperty() {
		return city;
	}

	public LocalDate getBirthday() {
		return birthday.get();
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}
	
	public ObjectProperty&lt;LocalDate&gt; birthdayProperty() {
		return birthday;
	}
}
</pre>


###  Explicações

* Com JavaFX  é comum o uso de [`Properties` (propriedades)](http://docs.oracle.com/javase/8/javafx/api/javafx/beans/property/Property.html) para todos os campos de uma classe model. Uma `Property` (propriedade) nos permite, por exemplo ser notificado automaticamente quando o `lastName` (sobrenome) ou qualquer outra variável seja mudada. Isso nos ajuda a manter a view sincronizada com os dados. PAra aprender mais sobre `Properties` leia [Usando Propriedades e Ligações de Propriedades no JavaFX](http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm).
* [`LocalDate`](http://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html), o tipo que nós estamos usando para `birthday` (aniversário), é parte da nova [API Data e Hora (Date and Time API) para JDK 8](http://docs.oracle.com/javase/tutorial/datetime/iso/).


*****

## Uma Lista de Pessoas

Os principais dados que nossa aplicação gerencia é um monte de pessoas. Vamos criar uma lista para objetos `Person`  dentro da classe `MainApp`. Todos as outras classes controller classes obterão acesso posteriormente à lista central dentro de `MainApp`. 


### ObservableList

Nós estamos trabalhando com classes view JavaFX que precisam de ser informadas sobre quaisquer mudanças feitas à lsita de pessoas. Isto é importante, caso contrário a view não seria sincronizada com os dados. Para este propósito, JavaFX introduz algumas novas [classes de Coleção](http://docs.oracle.com/javase/8/javafx/collections-tutorial/collections.htm). 

Para aquelas coleções, nós precisamos da `ObservableList`. Para criar uma nova `ObservableList`, adicione o código seguinte ao começo da classe `MainApp`. Nós adicionaremos também um construtor que cria alguns dados de exemplo e um método getter público:


##### MainApp.java

<pre class="prettyprint lang-java">

    // ... APÓS AS OUTRAS VARIÁVEIS ...

	/**
	 * Os dados como uma observable list de Persons.
	 */
	private ObservableList&lt;Person&gt; personData = FXCollections.observableArrayList();

	/**
	 * Construtor
	 */
	public MainApp() {
		// Add some sample data
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Mueller"));
	}
  
	/**
	 * Retorna os dados como uma observable list de Persons. 
	 * @return
	 */
	public ObservableList&lt;Person&gt; getPersonData() {
		return personData;
	}
  
    // ... O RESTANTE DA CLASSE ...
</pre>


*****

## O PersonOverviewController ##

Agora vamos finalmente colocar alguns dados em nossa tabela. Nós precisaremos de um controller para nosso `PersonOverview.fxml`.

1. Criar uma classe normal dentro do pacote **view** chamada `PersonOverviewController.java`. (Nós devemos colocá-la no mesmo pacote do que a `PersonOverview.fxml`, entretanto o SceneBuilder não o encontrará pelo menos não na versão atual).
2. Nós adicionaremos algumas variáveis de instância que nos dá acesso à tabela e às labels dentro da view. Os campos e alguns métodos tem uma anotação especial `@FXML`. Isso é enecessário para o arquivo FXML ter acesso aos campos e métodos privados. Após nós termos tudo ajustado no arquivo FXML, a aplicação vai preencher automaticamente as variáveis quando o arquivo FXML é carregado. Então vamos adicionar o código abaixo:

<div class="alert alert-info">
**Nota:** Lembre-se de sempre usar os ** imports javafx**, NÃO USE awt or swing!
</div>


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;

public class PersonOverviewController {
    @FXML
    private TableView&lt;Person&gt; personTable;
    @FXML
    private TableColumn&lt;Person, String&gt; firstNameColumn;
    @FXML
    private TableColumn&lt;Person, String&gt; lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * O construtor.
     * O construtor é chamado antes do método inicialize().
     */
    public PersonOverviewController() {
    }

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    	// Inicializa a tablea de pessoa com duas colunas.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * É chamado pela aplicação principal para dar uma referência de volta a si mesmo.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Adiciona os dados da observable list na tabela
        personTable.setItems(mainApp.getPersonData());
    }
}
</pre>


Agora este código provavelmente vai precisar de explicação.:

* Todos os campos e métodos onde o arquivo fxml precisa de acesso devem ser anotados com `@FXML`. Na verdade, omente se eles forem private,  mas é melhor tê-los private e marcá-los com a anotação!
* O método `initialize()` é chamado automaticamente após o arquivo fxml ter sido carregado. Nessa hora, todos os campos FXML já devem ter sido inicializados.
* O método `setCellValueFactory(...)` que nós definimos nas colunas da tabela são usados para determinar qual campo dentro dos objetos de `Person` devem ser usados para determinda coluna. A seta `->` indica que nós estamos usando um recurso do Java 8 chamado *Lambdas*. (Outra opção seria usar uma [PropertyValueFactory](http://docs.oracle.com/javase/8/javafx/api/), mas esta não é type-safe (segura por tipo)).


### Conectando a MainApp com o PersonOverviewController

O método `setMainApp(...)` deve ser chamado pela classe `MainApp`. Isso nos dá uma maneira de acessar o objeto `MainApp` e obter a lista de `Persons` e outras coisas. Substitua o método `showPersonOverview()` pelo abaixo. Ele contém duas linhas adicionais:


##### MainApp.java - novo método showPersonOverview()

<pre class="prettyprint lang-java">
/**
 * Mostra a person overview dentro do root layout.
 */
public void showPersonOverview() {
    try {
        // Carrega a person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();

        // Define a person overview no centro do root layout.
        rootLayout.setCenter(personOverview);

        // Dá ao controlador acesso à the main app.
        PersonOverviewController controller = loader.getController();
        controller.setMainApp(this);

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>



*****


## Ligar a View ao Controller

Nós estamos quase lá. Mas uma pequena coisa está faltando: Nós não contamos ao nosso arquivo `PersonOverview.fxml` ainda, qual controller usar e qual elemento deve combinar com qual campo dentro do controller.

1. Abra `PersonOverview.fxml` com o *SceneBuilder*.

2. Abra o grupo *Controller* no lado direito e selecione `PersonOverviewController` como **controller class**.   
![Set Controller Class](/assets/library/javafx-8-tutorial/part2/set-controller-class.png)

3. Selecione a `TableView` no grupo *Hierarchy* e escolha no grupo *Code* o campo `personTable` como **fx:id**.   
![Set TableView fx:id](/assets/library/javafx-8-tutorial/part2/set-tableview-fx-id.png)

4. Faça o mesmo para as colunas, selecione `firstNameColumn` e `lastNameColumn` como **fx:id** respectivamente.

5. Para **cada label** nas segunda coluna, escolha o **fx:id** correspondente.   
![Set Label fx:id](/assets/library/javafx-8-tutorial/part2/set-label-fx-id.png)

6. Importante: Volta ao Eclipse e **atualize (refresh) o projeto AddressApp inteiro project** (F5). Isso é necessário porque o Eclipse às vezes não sabe sobre mudanças que foram feitas dentro do Scene Builder.


*****

## Inicie a Aplicação

Quando você iniciar a sua aplicação agora, você deve ver algo como o screenshot no começo deste post do blog.   

Parabéns!


### O Que Vem Depois?

No [Tutorial Parte 3](/library/javafx-8-tutorial/pt/part3/) nós adicionaremos mais funcionalidades como adicionar, deletar e editar Persons.


##### Alguns outros artigos que você deve achar interessante (em inglês)

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Exemplos de Manipulação de Eventos](/blog/javafx-8-event-handling-examples/)
* [JavaFX Filtrar e Ordenar TableView](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX Renderizador de Células TableView](/blog/javafx-8-tableview-cell-renderer/)
