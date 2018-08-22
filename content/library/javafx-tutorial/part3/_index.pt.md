---
layout: article
title: "JavaFX 8 Tutorial - Parte 3: Interagindo com o Usuário"
date: 2014-04-24
updated: 2014-11-08
slug: javafx-tutorial/pt/part3
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-pt-part3.md
description: "Reagir às mudanças de seleção na TableView do JavaFX. Adicionar, editar e remover items da  tabela e validar entrada do usuário."
image: /assets/library/javafx-tutorial/part3/addressapp-part3.png
published: true
prettify: true
comments: true
sidebars:
- header: "Artigos nesta serie"
  body:
  - text: "Introdução"
    link: /library/javafx-tutorial/pt/
    paging: Intro
  - text: "Parte 1: Scene Builder"
    link: /library/javafx-tutorial/pt/part1/
    paging: 1
  - text: "Parte 2: Modelo e TableView"
    link: /library/javafx-tutorial/pt/part2/
    paging: 2
  - text: "Parte 3: Interagindo com o usuário"
    link: /library/javafx-tutorial/pt/part3/
    paging: 3
    active: true
  - text: "Parte 4: Estilos usando CSS"
    link: /library/javafx-tutorial/pt/part4/
    paging: 4
  - text: "Parte 5: Salvando dados como XML"
    link: /library/javafx-tutorial/pt/part5/
    paging: 5
  - text: "Parte 6: Gráficos de Estatistica"
    link: /library/javafx-tutorial/pt/part6/
    paging: 6
  - text: "Parte 7: Implantação"
    link: /library/javafx-tutorial/pt/part7/
    paging: 7
- header: "Download de Códigos Fonte"
  body:
  - text: "Parte 3 como um projeto Eclipse <em>(versão mínima requirida: JDK 8u20)</em>"
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-3.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Linguagens
  collection: library
  item: javafx-tutorial
  part: part3
  active: pt
---

![Screenshot AddressApp Part 3](/assets/library/javafx-tutorial/part3/addressapp-part3.png)


## Tópicos na Parte 3

* **Reagir às mudanças de seleção** na tabela.
* Adicionar funcionalidade aos botões de **add (adicionar)**, **edit (editar)**, e **remove (remover)**.
* Criar uma **janela popup** customizada  para editar uma pessoa.
* **Validar entrada do usuário**.


*****


## Reagir às Seleções de Tabela

Obviamente, nós ainda não usamos o lado direito da nossa aplicação. A idéia é mostrar os detalhes sobre uma pessoa no lado direito quando o usuário selecionar uma pessoa na tabela.

Primeiro, vamos adicionar um novo método dentro de `PersonOverviewController` que nos ajuda a preencher as labels com os dados de uma única `Person`.

Crie um método chamado `showPersonDetails(Person person)`. Vá por todas as labels e defina o texto usando `setText(...)` com detalhes da pessoa. Se `null` é passado como parâmetro, todas as labels devem ser limpas.


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * PReenche todos os campos de texto para mostrar detalhes sobre a pessoa.
 * Se a pessoa especificada for null, todos os campos de texto são limpos.
 * 
 * @param person a pessoa ou null
 */
private void showPersonDetails(Person person) {
    if (person != null) {
        // Preenche as labels com informações do objeto person.
        firstNameLabel.setText(person.getFirstName());
        lastNameLabel.setText(person.getLastName());
        streetLabel.setText(person.getStreet());
        postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
        cityLabel.setText(person.getCity());

        // TODO: Nós precisamos de uma maneira de converter o aniversário em um String! 
        // birthdayLabel.setText(...);
    } else {
        // Person é null, remove todo o texto.
        firstNameLabel.setText("");
        lastNameLabel.setText("");
        streetLabel.setText("");
        postalCodeLabel.setText("");
        cityLabel.setText("");
        birthdayLabel.setText("");
    }
}
</pre>


### Converter a Data de Aniversário em um String

Você vai perceber que nós não poderíamos definir o `birthday` em uma `Label` porque ele é do tipo `LocalDate` e não uma `String`. Nós devemos formatar a data primeiro.

Nós usaremos a conversão de `LocalDate` para `String` e vice versa em vários lugares. è uma boa prática criar uma classe helper (auxiliar) com métodos`static` para isso. Nós chamaremos ela de `DateUtil` e colocá-la em um pacote separado chamado `ch.makery.address.util`:


##### DateUtil.java

<pre class="prettyprint lang-java">
package ch.makery.address.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Funções auxiliares para lidar com datas.
 * 
 * @author Marco Jakob
 */
public class DateUtil {
	
	/** O padrão usado para conversão. Mude como quiser. */
	private static final String DATE_PATTERN = "dd.MM.yyyy";
	
	/** O formatador de data. */
	private static final DateTimeFormatter DATE_FORMATTER = 
			DateTimeFormatter.ofPattern(DATE_PATTERN);
	
    /**
     * Retorna os dados como String formatado. O 
     * {@link DateUtil#DATE_PATTERN}  (padrão de data) que é utilizado.
     * 
     * @param date A data a ser retornada como String
     * @return String formadado
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Converte um String no formato definido {@link DateUtil#DATE_PATTERN} 
     * para um objeto {@link LocalDate}.
     * 
     * Retorna null se o String não puder se convertido.
     * 
     * @param dateString a data como String
     * @return o objeto data ou null se não puder ser convertido
     */
    public static LocalDate parse(String dateString) {
        try {
        	return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checa se o String é uma data válida.
     * 
     * @param dateString A data como String
     * @return true se o String é uma data válida
     */
    public static boolean validDate(String dateString) {
    	// Tenta converter o String.
    	return DateUtil.parse(dateString) != null;
    }
}
</pre>

<div class="alert alert-info">
**Dica:** Você pode mudar o formato da data mudando o `DATE_PATTERN`. Para todos os formatos possíveis veja (em inglês) <a class="alert-link" href="http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">DateTimeFormatter</a>.
</div>


#### Usar o DateUtil

Agora nós precisamos usar nosso novo `DateUtil` no método `showPersonDetails` da classe `PersonOverviewController`. Substitua o *TODO* que nós colocamos pela linha seguinte:

<pre class="prettyprint lang-java">
birthdayLabel.setText(DateUtil.format(person.getBirthday()));
</pre>


### Detectar Mundanças na Seleção da Tabela

Para ser informado quando o usuário selecionar uma pessoa na tabela de pessoas, nós devemos *detectar (listen) mudanças*.

Existe uma interface no JavaFX chamada [`ChangeListener`](http://docs.oracle.com/javase/8/javafx/api/) com um método chamado `changed(...)`. O método tem três parâmetros: `observable`, `oldValue`, e `newValue`.

Nós vamos criar um `ChangeListener` usando uma *expressão lambda* do Java 8. Vamos adicionar algumas linhas ao método `initialize()` na classe `PersonOverviewController`. Agora ela está assim:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
@FXML
private void initialize() {
    // Inicializa a tabela de pessoas com duas colunas.
    firstNameColumn.setCellValueFactory(
            cellData -> cellData.getValue().firstNameProperty());
    lastNameColumn.setCellValueFactory(
            cellData -> cellData.getValue().lastNameProperty());

    // Limpa os detalhes da pessoa.
    showPersonDetails(null);

    // Detecta mudanças de seleção e mostra os detalhes da pessoa quando houver mudança.
    personTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showPersonDetails(newValue));
}
</pre>

Com `showPersonDetails(null);` nós resetamos os detalhes da pessoa. 

Com `personTable.getSelectionModel...` nós obtemos a *selectedItemProperty* da tabela de pessoas e adiciona um listener (detector) a ela. Sempre que o usuário selecionar uma pessoa na tabela, nossa *expressão lambda* é executada. Nós obtemos a pessoa selecionada recentemente e passamos para o método `showPersonDetails(...)`.

Tente **rodar sua aplicação** neste ponto. Verifique que quando você seleciona uma pessoa na tabela, detalhes saquela pessoa são mostrados à direita.

Se algo não funcionar, você pode comprar sua classe `PersonOverviewController` com [PersonOverviewController.java](/assets/library/javafx-tutorial/part3/PersonOverviewController.java).


*****

## O Botão Deletar

Nossa interface de usuário já contém um botão de delete, mas sem nenhuma funcionalidade. Nós podemos selecionar a ação para um botão dentro do *Scene Builder*. Qualquer método dentro do nosso que for anotado com `@FXML` (ou for public) é acessível pelo *Scene Builder*. Assim, vamos primeiro adicionar um método delete ao fim de nossa classe `PersonOverviewController`:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Chamado quando o usuário clica no botão delete.
 */
@FXML
private void handleDeletePerson() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    personTable.getItems().remove(selectedIndex);
}
</pre>

Agora, abra o arquivo `PersonOverview.fxml` no *SceneBuilder*. Selecione o botão *Delete*, abra o grupo *Code* e escolha `handleDeletePerson` no dropdown de **On Action**.

![On Action](/assets/library/javafx-tutorial/part3/handle-delete.png)


### Lidando com Erros

Se você rodar a aplicação neste ponto, você poderá deletar a pessoa selecionada da tabela. Mas o que acontece se você **clicar o botão delete enquanto nenhuma pessoa estiver selecionada** na tabela? 

Haverá uma `ArrayIndexOutOfBoundsException` porque ele não poderia remover uma pessoa no index (na posição) `-1`. O index (a posição) `-1` foi retornado pelo método `getSelectedIndex()` - que significa que há nenhuma seleção.

Ignorar tal erro não é muito legal, é claro. Nós deveríamos deixar o usuário saber que ele/ela deve selecionar uma pessoa antes de deletar. (Melhor seria se nós desabilitássemos o  botão, então o usuário não teria chance de fazer algo errado.)

Nós adicionaremos uma janela de popup para informar o usuário. Você precisará **adicionar uma biblioteca** para o [Dialogs](/blog/javafx-8-dialogs/): 

1. Baixe este [controlsfx-8.0.6_20.jar](https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/controlsfx-8.0.6_20.jar) (você poderia também obtê-lo do site[ControlsFX Website](http://fxexperience.com/controlsfx/)).   
**Importante: O ControlsFX deve estar na versão `8.0.6_20` ou maior para trabalhar com o `JDK 8u20`, versões anteriores vão ter problemas de compatibilidade.**
2. Crie uma subpasta **lib** no projeto e adicione o arquivo controlsfx-jar a esta pasta.
3. Adcione a biblioteca ao **classpath** do seu projeto: No Eclipse *clique com o botão direito no arquivo* | *Build Path* | *Add to Build Path*. Agora o Eclipse sabe sobre a biblioteca.
![ControlsFX Libaray](/assets/library/javafx-tutorial/part3/controlsfx-library.png)

Com algumas mudanças feitas no método `handleDeletePerson()`, nós podemos mostrar uma janela simples de popup quando o usuário clicar no botão delete quando não houver uma pessoa selecionada na tabela:


##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Chamado quando o usuário clica no botão delete.
 */
@FXML
private void handleDeletePerson() {
    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        personTable.getItems().remove(selectedIndex);
    } else {
        // Nada selecionado.
      
	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Nenhuma seleção");
        	alert.setHeaderText("Nenhuma Pessoa Selecionada");
        	alert.setContentText("Por favor, selecione uma pessoa na tabela.");

        	alert.showAndWait();
    }
}
</pre>

<div class="alert alert-info">
Para mais exemplos sobre como usar Dialogs leia (em inglês) <a class="alert-link" href="/blog/javafx-8-dialogs/">JavaFX 8 Dialogs</a>.
</div>



*****


## Os Dialogs New (Novo) e Edit (Editar)

As ações new (novo) e edit (editar)  são um pouco mais trabalhosas: Nós precisaremos de uma dialog customizada com um formulário para perguntar o usuário os detalhes da pessoa.


### Desenhando o Dialog

1. Crie um novo arquivo fxml chamado `PersonEditDialog.fxml` dentro do pacote *view*.   
![Create Edit Dialog](/assets/library/javafx-tutorial/part3/person-edit-dialog1.png)

2. Use um `GridPane`, `Label`s, `TextField`s e `Button`s para criar um Dialog como o seguinte:   
![Edit Dialog](/assets/library/javafx-tutorial/part3/person-edit-dialog2.png)   

*Se você não quiser fazer o trabalho, você pode baixar este [PersonEditDialog.fxml](/assets/library/javafx-tutorial/part3/PersonEditDialog.fxml).* 


### Criar o Controller

Crie o controller para o Dialog como `PersonEditDialogController.java`:

##### PersonEditDialogController.java

<pre class="prettyprint lang-java pre-scrollable">
package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

/**
 * Dialog para editar detalhes de uma pessoa.
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
     * Inicializa a classe controlle. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Define o palco deste dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Define a pessoa a ser editada no dialog.
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
     * Retorna true se o usuário clicar OK,caso contrário false.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Chamado quando o usuário clica OK.
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
     * Chamado quando o usuário clica Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Valida a entrada do usuário nos campos de texto.
     * 
     * @return true se a entrada é válida
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Nome inválido!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Sobrenome inválido!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "Rua inválida!\n"; 
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "Código Postal inválido!\n"; 
        } else {
            // tenta converter o código postal em um int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Código Postal inválido (deve ser um inteiro)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "Cidade inválida!\n"; 
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Aniversário inválido!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "Aniversário inválido. Use o formato dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
        	Alert alert = new Alert(AlertType.ERROR);
            	      alert.setTitle("Campos Inválidos");
            	      alert.setHeaderText("Por favor, corrija os campos inválidos");
            	      alert.setContentText(errorMessage);
                alert.showAndWait();
                
            return false;
        }
    }
}
</pre>

Algumas coisas para notar sobre este controller:

* O método `setPerson(...)` pode ser chamado por outra classe para definir a pessoa a ser editada.
* Quando o usuário clica o botão OK, o método `handleOk()` é chamado. Primeiro, alguma validação é feita pela chamada do método `isInputValid()`. Só se a validação tiver sucesso, o objeto pessoa é preenchido com os dados que o usuário inseriu. Aquelas mudanças serão aplicadas diretamente ao objeto da pessoa que foi passado para o método` setPerson(...)`!
* O booleano `okClicked` é usado então o método chamador pode determinar se o usuário clicou no botão OK ou Cancel.


### Ligar View e Controller 

Com a View (FXML) e o controller criado nós precisamos ligá-los:

1. Abra o `PersonEditDialog.fxml`.
2. No grupo *Controller* no lado esquerdo selecione o `PersonEditDialogController` como classe controller.
3. Defina o **fx:id** de todos os `TextField`s para o campo correspondente do controller.
4. Defina o **onAction** dos dois botões ao método handler correspondente.



### Abrindo o Dialog

Adicione um método para carregar e mostrar o `EditPersonDialog` dentro do nosso `MainApp`:   


##### MainApp.java

<pre class="prettyprint lang-java">
/**
 * Abre uma janela para editar detalhes para a pessoa especificada. Se o usuário clicar
 * OK, as mudanças são salvasno objeto pessoa fornecido e retorna true.
 * 
 * @param person O objeto pessoa a ser editado
 * @return true Se o usuário clicou OK,  caso contrário false.
 */
public boolean showPersonEditDialog(Person person) {
    try {
        // Carrega o arquivo fxml e cria um novo stage para a janela popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Cria o palco dialogStage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Define a pessoa no controller.
        PersonEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPerson(person);

        // Mostra a janela e espera até o usuário fechar.
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
</pre>

Adicione os seguitnes métodos ao `PersonOverviewController`. Esses métodos chamarão o `showPersonEditDialog(...)` do `MainApp` quando o usuário clicar os botões *new* ou *edit*.   

##### PersonOverviewController.java

<pre class="prettyprint lang-java">
/**
 * Chamado quando o usuário clica no botão novo. Abre uma janela para editar
 * detalhes da nova pessoa.
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
 * Chamado quando o usuário clica no botão edit. Abre a janela para editar
 * detalhes da pessoa selecionada.
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
        // Nada seleciondo.
        Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("Nenhuma seleção");
        	alert.setHeaderText("Nenhuma Pessoa Selecionada");
        	alert.setContentText("Por favor, selecione uma pessoa na tabela.");
        	alert.showAndWait();
    }
}
</pre>

Abra o arquivo `PersonOverview.fxml` no Scene Builder. Escolha os métodos correspondentes em *On Action* para os botões new e edit.


*****

## Pronto!

Você deve ter uma *Aplicação de Endereços (Agenda)* agora. A aplicação pode adicionar, editar e deletar pessoas. Há também validação para os campos de texto para evitar más entradas do usuário.

Eu espero que os conceitos e estrutura desta aplicação vão levá-los a começar a escrever suas próprias aplicações JavaFX! Divirtam-se.


### O Que Vem Depois?

No [Tutorial Parte 4](/library/javafx-tutorial/pt/part4/) nós adicionaremos alguma estilização CSS.


##### Alguns outros artigos que você deve achar interessante (em inglês)

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Exemplos de Manipulação de Eventos](/blog/javafx-8-event-handling-examples/)
* [JavaFX Filtrar e Ordenar TableView](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX Renderizador de Células TableView](/blog/javafx-8-tableview-cell-renderer/)
