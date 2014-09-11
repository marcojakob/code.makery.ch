---
layout: article
title: "JavaFX 8 Tutorial"
date: 2014-09-10 00:00
updated: 2014-09-10 00:00
slug: javafx-8-tutorial/pt
canonical: /java/javafx-8-tutorial-intro/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-pt.md
description: "Este Tutorial em sete partes orienta a criação, programação e a implantação de um aplicativo de endereços usando JavaFX."
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: false
sidebars:
- header: "Artigos nesta serie"
  body:
  - text: "Introfução"
    link: /library/javafx-8-tutorial/pt/
    paging: Intro
    active: true
  - text: "Parte 1: Scene Builder"
    link: /library/javafx-8-tutorial/pt/part1/
    paging: 1
  - text: "Parte 2: Model and TableView"
    link: /library/javafx-8-tutorial/pt/part2/
    paging: 2
  - text: "Parte 3: Interagindo com o usúario"
    link: /library/javafx-8-tutorial/pt/part3/
    paging: 3
  - text: "Parte 4: Estilos usando CSS"
    link: /library/javafx-8-tutorial/pt/part4/
    paging: 4
  - text: "Parte 5: Salvando dados com XML"
    link: /library/javafx-8-tutorial/pt/part5/
    paging: 5
  - text: "Parte 6: Graficos de Estatisticas"
    link: /library/javafx-8-tutorial/pt/part6/
    paging: 6
  - text: "Parte 7: Inplantação"
    link: /library/javafx-8-tutorial/pt/part7/
    paging: 7
- header: Languages
  body:
  - text: English
    link: /java/javafx-8-tutorial-intro/
    icon-css: fa fa-fw fa-globe
  - text: Português
    link: /library/javafx-8-tutorial/pt/
    icon-css: fa fa-fw fa-globe
    active: true
---

<div class="alert alert-warning">
  <i class="fa fa-language"></i> This page is beeing translated to Portuguese. If you'd like to help out please read <a href="/library/how-to-contribute/" class="alert-link">how to contribute</a>.
</div>

Em 2012 eu criei um tutorial muito detalhado [JavaFX 2 tutorial series](/java/javafx-2-tutorial-intro/) para meus alunos. Muitas pessoas ao redor do mundo leram o tutorial e derem um feedback possitivo. Então eu decidi ** rescrever o tutorial de JavaFX 2 para JavaFX 8**(Leia sobre o que mudou em [Atualização para JavaFX 8 - Quais as novidades ](/blog/update-to-javafx-8-whats-new/)).

Este Tutorial em sete partes orienta a criação, programação e a implantação de um aplicativo de endereços. No fim nossa aplicação ficará assim:
![Screenshot AddressApp](/assets/library/javafx-8-tutorial/addressapp.png)


## Oque você aprenderá

* Criar e executar um projeto JavaFX
* Usar o Scene Builder para criar a interface com o usuário
* Estruturar uma aplicação usando o padrão MVC (Modelo Visão Controle)
* Usar `ObservableLists` para atualizar automaticamente a interface do usuário 
* Usar `TableView`e mostar as informaçoes ao selecionar uma linha
* Criar uma caixa de dialogo popup para editar as informaçõs das pessoas
* Validar a entrada do usuário
* Personalizar um aplicativo JavaFX usando CSS
* Persistir dados usando XML
* Armazenar o ultimo arquivo aberto nas preferencias do usuário
* Criar gráfico JavaFX com estatisticas
* Implementar um aplicativo JavaFX em um instalador

** Já é Bastante coisa!** 

**This is quite a lot!** So, after you you've completed this tutorial series you should be ready to build sophisticated applications with JavaFX.


## How to use this Tutorial

There are two ways to use this tutorial:

* **learn-a-lot track:** Create your own JavaFX project from the ground up.
* **fast track:** Import the source code for a tutorial part into your IDE (it's an Eclipse project, but you could use other IDEs like NetBeans with slight modifications). Then go through the tutorial to understand the code.

Now, I hope you'll have fun! Start with [Part 1: Scene Builder](/library/javafx-8-tutorial/pt/part1/).
