---
layout: article
title: "Tutorial JavaFX 8 - Parte 7: Implantação"
date: 2014-09-10
updated: 2014-11-13
slug: javafx-tutorial/pt/part7
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-pt-part7.md
description: "How to deploy a JavaFX application as native package. Create an installer for Windows, MacOS, or Linux."
image: /assets/library/javafx-tutorial/part7/addressapp-macos.png
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
  - text: "Parte 7: Implantação"
    link: /library/javafx-tutorial/pt/part7/
    paging: 7
    active: true
- header: "Download de Códigos Fonte"
  body:
  - text: "Parte 7 como um projeto Eclipse <em>(versão mínima requirida: JDK 8u20)</em>"
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-7.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Linguagens
  collection: library
  item: javafx-tutorial
  part: part7
  active: pt
---

![Screenshot AddressApp Part 7](/assets/library/javafx-tutorial/part7/addressapp-part7.png)

Eu pensei que deveria dedicar a última parte desta série de tutoriais para mostrar como realizar o deploy (i.e package e publicação) do AddressApp.


*****

## Tópicos da Parte 7

* Utilizando o deploy em nossa aplicação JavaFX como um **Package Nativo** com e(fx)clipse


*****

## O que é Deploy

Deploy é o processo de empacotamento (packaging) e entrega do  software ao usuário. Esta é uma parte crucial do desenvolvimento de software, pois é o primeiro contato entre o cliente e nosso software.

Java enfatiza o slogan **Write Once, Run Anywhere** para ilustrar os beneficios da *Multiplataforma* da linguagem Java. Conceitualmente, isso significa que sua aplicação Java pode ser executado em qualquer dipositivo que possua uma Java virtual machine (JVM).

No passado, a experiência do usuário em instalar uma aplicação Java não era tão simplificada . Se o usuário não tinha a versão do Java requirida em seu sistema,  ele primeiramente tinha que instala-la diretamentet. Isso gerava muitas dificuldades, como a necessidade do uso de previlégios de administrador, problemas de compatibilidade entre as versões do java, etc.

Felizmente o JavaFX fornece uma nova opção de deploy, chamada **Native Packaging** (também chamada de Self-Contained Application Package). Um native package é um pacote que contém o código de sua aplicação juntamente com o Java Runtime (Específico para cada plataforma). 

A documentação oficial do JavaFX mantida pela Oracle contém um guia completo para todas as formas de deploy (em inglês) [JavaFX deployment options](http://docs.oracle.com/javafx/2/deployment/jfxpub-deployment.htm). 

Nesta publicação eu irei mostrar como criar um  **Native Package** com o Eclipse e o plugin e(fx)clipse [**e(fx)clipse plugin**](http://www.eclipse.org/efxclipse/).


*****

## Criando um Native Package

O objetivo é criar uma aplicação independente em um único diretório no computador do usuário. Aqui esta como irá se parecer nosso AddressApp (No Windows):

![AddressApp Native Package](/assets/library/javafx-tutorial/part7/native-package.png)

A pasta `app` contém od dados de nossa aplicação e a pasta `runtime`  contém o Java runtime específico de cada plataforma.

A fim de tornar a experiência do usuário ainda mais confortável, nós iremos fornecer um instalador:

* Um instalador `exe` para windows
* Um instalador `dmg` (drag and drop) para MacOS.

O e(fx)clipse plugin irá nos ajudar a gerar o package nativo e o instalador.


### Passo 1 - Editar o build.fxbuild

O arquivo `build.fxbuild` é usado pelo e(fx)clipse para gerar um arquivo que será usado pela Ant build tool. (Se você não tem um arquivo `build.fxbuild`, crie um novo *Projeto Java FX* no Eclipse e copie os arquivos gerados anteriormente.)

1. Abra o `build.fxbuild` a partir da raiz de seu projeto (project root).

2. Preencha todos os campos que contêm uma estrela. *Para MacOS: Não use espaços no título da aplicaçãos, pois isso causará problemas*    
![fxbuild settings](/assets/library/javafx-tutorial/part7/fxbuild-settings.png)

3. Como **Formato de Package ** escolha `exe` para Windows, `dmg` para MacOS e `rpm` para Linux.

4. Clique no link `Generate ant build.xml only` (Encontrado no lado direito).   
![generate ant build](/assets/library/javafx-tutorial/part7/generate-ant-build.png)

5. Perceba que uma nova pasta `build` e um arquivo `build.xml` foram criados.


### Passo 2 - Adicionar icones ao instalador

Gostaríamos de ter alguns icones legais para o nosso instalador:

* [AddressApp.ico](/assets/library/javafx-tutorial/part7/AddressApp.ico) para o arquivo ícone do instalador
* [AddressApp-setup-icon.bmp](/assets/library/javafx-tutorial/part7/AddressApp-setup-icon.bmp) para o ícone da tela inicial.
* [AddressApp.icns](/assets/library/javafx-tutorial/part7/AddressApp.icns) para o ícone do instalador para mac.


1. Crie as seguintes subpastas no diretório `build`:
  * `build/package/windows` (Usado apenas no windows)
  * `build/package/macos` (Usado apenas no  macos)
2. Copie os ícones correspondes acima aos respectivos sub-diretorios. Deve parecer assim agora:
![Installer Icons](/assets/library/javafx-tutorial/part7/installer-icons.png)
3. **Importante**: O nome dos ícones deve corresponder exatamente ao **Título da aplicação** (Application title) que você especificou no `build.fxbuild`:
  * `YourAppTitle.ico`
  * `YourAppTitle-setup-icon.bmp`
  * `YourAppTitle.icns`


### Passo 3 - Adicionar recursos

Nossa pasta `resources` não é copiada automáticamente. Nós precisamos adiciona-la manualmente no diretório de compilação (build):

1. Crie a seguinte subpasta no diretório `build`:
  * `build/dist`   
2. Copie a pasta `resources` (contendo as imagens de nossa aplicação) para `build/dist`.    
![Build Resources](/assets/library/javafx-tutorial/part7/build-resources.png)


### Passo 4 - Edite build.xml para incluir os ícones

O E(fx)clipse gerou um arquivo chamado  `build/build.xml` que está pronto para ser executado pelo **Ant**. Nosso icone de isntalação e nossa fonte de images apenas não funcionarão. 

Como o e(fx)clipse não foi orientado (ainda?) para adicionar as fontes originais como a pasta `resources`, juntamente com  os icones do instalador, que nós adicionamos anteriormente, teremos que editar manualmente o `build.xml`:

Abra o `build.xml` e procure pelo caminho chamado `fxant`. Adicione uma linha com o `${basedir}` (tornará nossos icones do instalador disponíveis):


##### build.xml - adicione "basedir" (Diretório de origem)

<pre class="prettyprint lang-xml">
&lt;path id="fxant"&gt;
  &lt;filelist&gt;
    &lt;file name="${java.home}\..\lib\ant-javafx.jar"/&gt;
    &lt;file name="${java.home}\lib\jfxrt.jar"/&gt;
    <mark>&lt;file name="${basedir}"/&gt;</mark>
  &lt;/filelist&gt;
&lt;/path&gt;
</pre>    


Encontre o campo `fx:resources id="appRes"` mais abaixo no arquivo. Adicione uma linha para nossos `resources`:

##### build.xml - adicionar "resources"

<pre class="prettyprint lang-xml">
&lt;fx:resources id="appRes"&gt;
    &lt;fx:fileset dir="dist" includes="AddressApp.jar"/&gt;
    &lt;fx:fileset dir="dist" includes="libs/*"/&gt;
    <mark>&lt;fx:fileset dir="dist" includes="resources/**"/&gt;</mark>
&lt;/fx:resources&gt; 
</pre>


De alguma maneira, o número da versão não é adicionados ao `fx:application` o que faz o insalador sempre voltar para o padrão, que é `1.0` (como dito por algumas pessoas nos comentários). Para concertar isso devemos adicionar manualmente o número da versão (abrigado por isso Marc [finding out](http://code.makery.ch/library/javafx-tutorial/part7/#comment-1566725959)):

##### build.xml - adicionar "version"

<pre class="prettyprint lang-xml">
&lt;fx:application id="fxApplication"
    name="AddressApp"
    mainClass="ch.makery.address.MainApp"
    <mark>version="1.0"</mark>
/>
</pre>

Agora  nós ja podemos executar o `build.xml` como uma build do Ant. Isto deverá gerar o arquivo executável jar  do projeto. Mas nós queremos ir um passo além e criar um instalador bonito.


### Passo 5 (WINDOWS) - Um instalador exe para Windows

![AddressApp on Windows](/assets/library/javafx-tutorial/part7/addressapp-windows.png)

Com o **Inno Setup** nós podemso criar um instalador para Windows de nossa aplicação, isto como um único arquivo `.exe`. O `.exe` resultante irá realizara uma instalaçaõ a nível de usuário (nenhuma permisão de adminstrador necessária). Um atalho também será criado (no menu ou desktop).

1. Baixe o [Inno Setup 5 ou posterior](http://www.jrsoftware.org/isdl.php). Instale o Inno Setup em seu computador. Nosso Ant script irá utiliza-lo para criar o instalador de forma automática.

2. Informe ao Windows o caminho de instalaçaõ do Inno Setup (ex: `C:\Program Files (x86)\Inno Setup 5`). Para fazer isso, adicione Inno Setup a variavel de ambiente `Path`. Se você não sabe onde encontra-la, leia  (em inglês) [How to set the path and environment variables in Windows](http://www.computerhope.com/issues/ch000549.htm).

3. Reinicie o Eclipse e continue com o Passo 5.


### Passo 5 (MAC) - Instalador dmg para MacOS 

![AddressApp on Mac](/assets/library/javafx-tutorial/part7/addressapp-macos.png)

Para criar um instalador `dmg` (drag-and-drop) para MacOS, nenhuma ferramenta adicionas é necessária.
Nota: Para a imagem do instalador funcionar, ela deve possuir exatamente o mesmo nome da aplicação.

### Passo 5 (LINUX etc.) - Instalador rpm para Linux

Para outras opções de empacotamento (packaging) (`msi` para windows, `rpm` para Linux) veja esse native packaging (em inglês) [blog post](https://blogs.oracle.com/talkingjavadeployment/entry/native_packaging_for_javafx) ou esse (em inglês) [oracle documentation](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm#A1324980).


### Passo 6 - Execute o build.xml

Como passo final, nós executamos o `build.xml` com o Ant: *Clique com o botão direito* no arquivo `build.xml` | *Executar como * | *Ant Build* (depende da linguagem de seu SO).

![Run Ant Build](/assets/library/javafx-tutorial/part7/run-ant-build.png)

A compilação **irá demorar um pouco** (aproximadamente 1 minuto em meu computador).

Se tudo ocorrer corretamente, você deverá encontrar a pasta nativa no diretório `build/deploy/bundles`. É assim que se parece a versão para windows:

![Deployed File](/assets/library/javafx-tutorial/part7/deployed-file.png)


O arquivo `AddressApp-1.0.exe` pode ser usado como arquivo independente para se instalar a aplicação. Este instalador copiará a pasta para `C:/Users/[seuNome]/AppData/Local/AddressApp`.


### E agora?

Espero que este tutorial tenha ajudado você a começar com o javaFX. Você estará apto a escrever o seu próprio projeto JavaFX de agora em diante.

Eu gosto de seu feedback, portanto sinta-se livre para comentar se tiver alguma sugestão ou dúvida.

##### Alguns artigos que você pode achar interessantes (Em inglês)

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Exemplos de Manipulação de Eventos](/blog/javafx-8-event-handling-examples/)
* [JavaFX Filtrar e Ordenar TableView](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX Renderizador de Células TableView](/blog/javafx-8-tableview-cell-renderer/)


