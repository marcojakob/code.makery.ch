---
layout: article
title: "Tutorial JavaFX 8 - Parte 6: Gráficos de Estatistica"
date: 2014-09-10
updated: 2014-11-12
slug: javafx-tutorial/pt/part6
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-pt-part6.md
description: "Aprenda como criar um Gráfico de Barras JavaFX (JavaFX Bar Chart)."
image: /assets/library/javafx-tutorial/part6/addressapp-part6.png
published: true
prettify: true
comments: false
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
  - text: "Parte 4: Estilos usando CSS"
    link: /library/javafx-tutorial/pt/part4/
    paging: 4
  - text: "Parte 5: Salvando dados como XML"
    link: /library/javafx-tutorial/pt/part5/
    paging: 5
  - text: "Parte 6: Gráficos de Estatistica"
    link: /library/javafx-tutorial/pt/part6/
    paging: 6
    active: true
  - text: "Parte 7: Implantação"
    link: /library/javafx-tutorial/pt/part7/
    paging: 7
- header: "Download de Códigos Fonte"
  body:
  - text: "Parte 6 como um projeto Eclipse <em>(versão mínima requirida: JDK 8u20)</em>"
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-6.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Linguagens
  collection: library
  item: javafx-tutorial
  part: part6
  active: pt
---

![Screenshot AddressApp Part 6](/assets/library/javafx-tutorial/part6/addressapp-part6.png)


## Tópicos na Parte 6

* Criando um **Gráfico de Estatísticas** para mostrar a distribuição de aniversários.


*****

## Estatísticas de Aniversários

Todas as nossas pessoas na AddressApp têm um aniversário. Não seria legal ter algumas estatísticas sobre nossas pessoas celebrarem seus aniversários?

Nós usaremos um **Bar Chart (Gráfico de Barras)** contendo uma barra para cada mês. Cada barra mostra quantas pessoas têm seus aniversários naquele mês particular.


## A View FXML de Estatísticas

1. Nós começaremos criando um arquivo `BirthdayStatistics.fxml` dentro do nosso pacote `ch.makery.address.view` (*Clique com o botão direito no pacote | New | other... | New FXML Document*).   
![Birthday Statistics FXML](/assets/library/javafx-tutorial/part6/birthday-statistics-fxml.png)

2. Abra o arquivo `BirthdayStatistics.fxml` no Scene Builder.

3. Selecione o `AnchorPane` raiz. No grupo *Layout* defina a *Pref Width* para 620 e a *Pref Height* para 450.

4. Adicione um `BarChart` no `AnchorPane`.

5. Clique com o botão direito no `BarChart` e selecione *Fit to Parent*.

6. Salve o arquivo fxml, vá ao Eclipse e atualize (refresh) o projeto.

Antes de voltarmos ao Scene Builder nós criaremos primeiro o controller e ligar tudo na nossa `MainApp`.


## O Controller de Estatísticas

No pacote `ch.makery.address.view`, crie uma classe Java chamada `BirthdayStatisticsController.java`.

Vamos primeiro olhar na classe inteira do controller antes de começar a explicar:


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
 * O controller para a view de estatísticas de aniversário.
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
     * Inicializa a classe controller. Eeste método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
        // Obtém an array com nomes dos meses em Inglês.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Converte o array em uma lista e adiciona em nossa ObservableList de meses.
        monthNames.addAll(Arrays.asList(months));
        
        // Associa os nomes de mês como categorias para o eixo horizontal.
        xAxis.setCategories(monthNames);
    }

    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    public void setPersonData(List&lt;Person> persons) {
        // Conta o número de pessoas tendo seus aniversários em um mês específico.
        int[] monthCounter = new int[12];
        for (Person p : persons) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series&lt;String, Integer> series = new XYChart.Series&lt;>();
        
        // Cria um objeto XYChart.Data para cada mês. Adiciona ele às séries.
        for (int i = 0; i &lt; monthCounter.length; i++) {
        	series.getData().add(new XYChart.Data&lt;>(monthNames.get(i), monthCounter[i]));
        }
        
        barChart.getData().add(series);
    }
}
</pre>


#### Como o Controller Funciona

1. O controller precisará de acesso para dois elementos do nosso arquivo FXML:
   * O `barChar`: Ele tem um tipo `String` e `Integer`. O `String` é usado para o mês no eixo x e o `Integer` é usado para o número de pessoas em um mês específico. 
   * O `xAxis (eixo x)`: Nós usaremos este para adicionar os String do mês.   

2. O método `initialize()` preenche o eixo x com uma lista de todos os meses.

3. O método `setPersonData(...)` será acessado pela classe `MainApp` para definir os dados da pessoa. Ele percorre todas as pessoas, conta os aniversários por mês. Então ele adiciona `XYChart.Data` para todo mês na série de dados. Cada objeto `XYChart.Data` representará uma barra no gráfico.


*****

## Conectando View e Controller

1. Abra o `BirthdayStatistics.fxml` no Scene Builder.

2. No grupo *Controller* defina `BirthdayStatisticsController` como controller.

3. Selecione o `BarChart` e escolha `barChart` como propriedade fx:id (no grupo *Code*).

4. Selecione a `CategoryAxis` (eixo de caregoria) e escolha `xAxis` (eixo x) como propriedade fx:id.   
![Category Axis](/assets/library/javafx-tutorial/part6/category-axis.png)

5. Você pode adicionar um título ao `BarChart` (no grupo *Properties*) para mais estilização.



*****


## Conectando a/o View/Controller com a MainApp

Nós usaremos o mesmo mecanismo para nossas *birthday statistics* (estatísticas de aniversário) que nós usamos para a *edit person dialog*, uma janela simples de popup.

Adicione o método seguinte para sua classe `MainApp`:


<pre class="prettyprint lang-java">
/**
 * Abre uma janela para mostrar as estatísticas de aniversário.
 */
public void showBirthdayStatistics() {
    try {
        // Carrega o arquivo fxml e cria um novo palco para o popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Birthday Statistics");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Define a pessoa dentro do controller.
        BirthdayStatisticsController controller = loader.getController();
        controller.setPersonData(personData);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
</pre>

Tudo é ajustado, mas nós não temos qualquer coisa que realmente chama o novo método `showBirthdayStatistics()`. Por sorte, nós já temos um menu no `RootLayout.fxml` que pode ser usado para este propósito.


### Menu para Mostrar Estatísticas de Aniversário

Em nosso `RootLayoutController` adicione o seguinte método que lidará com os cliques do usuário no item de menu *show birthday statistics*: 

<pre class="prettyprint lang-java">
/**
 * Abre as estatísticas de aniversário.
 */
@FXML
private void handleShowBirthdayStatistics() {
  mainApp.showBirthdayStatistics();
}
</pre>

 Agora, abra o arquivo `RootLayout.fxml` com o Scene Builder. Crie o `Menu` *Statistics* com um `MenuItem` (item de menu) *Show Statistics* :

![Show Statistics Menu](/assets/library/javafx-tutorial/part6/show-statistics-menu.png)

Selecione o `MenuItem` *Show Statistics* e escolha `handleShowBirthdayStatistics` para `On Action` (no grupo *Code*)   

![Show Statistics On Action](/assets/library/javafx-tutorial/part6/show-statistics-on-action.png)

Vá ao Eclipse, atualize (refresh) o projeto e **testa ele**.


*****

## Mais Informação do JavaFX Charts

Um bom lugar para obter mais informaçãoé no tutorial official da Oracle no site (em inglês) [Working with JavaFX Charts](http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/charts.htm).


### O Que Vem Depois?

No último tutorial [Parte 7](/library/javafx-tutorial/pt/part7/) nós finalmente implantaremos (deploy)  nossa aplicação (por exemplo empacotar e entregar o aplicativo para nosso usuários).


##### Alguns outros artigos que você deve achar interessante (em inglês)

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Exemplos de Manipulação de Eventos](/blog/javafx-8-event-handling-examples/)
* [JavaFX Filtrar e Ordenar TableView](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX Renderizador de Células TableView](/blog/javafx-8-tableview-cell-renderer/)
