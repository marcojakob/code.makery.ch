---
layout: article
title: "JavaFX 8 Tutorial - Part 7: Deployment"
date: 2014-05-10
updated: 2016-02-05
slug: javafx-8-tutorial/it/part7
canonical: /library/javafx-8-tutorial/part7/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-it-part7.md
description: "How to deploy a JavaFX application as native package. Create an installer for Windows, MacOS, or Linux."
image: /assets/library/javafx-8-tutorial/part7/addressapp-macos.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articoli in questa serie"
  body:
  - text: "Introduzione"
    link: /library/javafx-8-tutorial/it/
    paging: Intro
  - text: "Parte 1: Scene Builder"
    link: /library/javafx-8-tutorial/it/part1/
    paging: 1
  - text: "Parte 2: Model and TableView"
    link: /library/javafx-8-tutorial/it/part2/
    paging: 2
  - text: "Parte 3: Interazione con l'utente"
    link: /library/javafx-8-tutorial/it/part3/
    paging: 3
  - text: "Parte 4: CSS Styling"
    link: /library/javafx-8-tutorial/it/part4/
    paging: 4
  - text: "Parte 5: Conservazione dati come XML"
    link: /library/javafx-8-tutorial/it/part5/
    paging: 5
  - text: "Parte 6: Grafico delle statistiche"
    link: /library/javafx-8-tutorial/it/part6/
    paging: 6
  - text: "Parte 7: Deployment"
    link: /library/javafx-8-tutorial/it/part7/
    paging: 7
    active: true
- header: "Download Sources"
  body:
  - text: Part 7 as Eclipse Project <em>(requires at least JDK 8u40)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.1/addressapp-jfx8u40-part-7.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Languages
  collection: library
  item: javafx-8-tutorial
  part: part7
  active: it
---

![Screenshot AddressApp Part 7](/assets/library/javafx-8-tutorial/part7/addressapp-part7.png)

Nelle parti fionali di questa serie di tutorial mostreremo come effettuare il deploy (ovver come comporre il package e come distribuirlo) della nostra AddressApp.


*****

## Argomenti nella sezione 7

* Distribuire la nostra applivazione  JavaFX come un **Package Nativo** con e(fx)clipse


*****

## Cos'è il Deployment

Il deplyoment è il processo di impacchettamento e distribuzione verso gli utenti del software che abbiamo sviluppato. Si tratta di una parte cruciale del processo di sviluppo poichè costituisce il primo contatto che gli utenti avranno con il nostro software.

Java viene pubblicizzato con lo slogan **Write Once, Run Anywhere** (scrivi una volta, esegui ovunque) per illustrare i benefici *multi-piattaforma* del linguaggio Java. Idealmente questo significa che la nostra applicazione Java puà essere eseguita su qualsiasi dispositivo equipaggiato con una Java Virtual Machine (JVM).

In passato, la user experience per l'installazione di applicazioni Java non sempre filava liscio. Se l'utente non disponeva della versione richiesta di Java sul proprio sistema, veniva prima indirizzato sull'installazione. Questo conduceva ad alcune difficoltà, ad esempio la necessità di disporre di diritti di amministrazione, problemi di compatibilità tra le versioni di Java ecc.

Fortunatamente, JavaFX fornisce una nuova opzione di deploy chiamata **Package Nativo** (nota anche come Self-Contained Application Package).Un package nativo raggruppa sia il codice dell'applicazione sia l'ambiente di runtime Java specifico per la piattaforma di destinazione. 

La documentazione JavaFX di Oracle contiene una guida esaustiva per tutte le possibili [opzioni di deploy JavaFX](http://docs.oracle.com/javafx/2/deployment/jfxpub-deployment.htm). 

In questo post vi mostrerò come creare un **Package Nativo** con eclipse e il [**plugin e(fx)clipse **](http://www.eclipse.org/efxclipse/).


*****

## Creare un Package Nativo

L'obiettivo è di creare una applicazione auto-consistente in una singola cartella sul computer dell'utente. Ecco come questo apparirà per la nostra appliazione AddressApp (su Windows):

![AddressApp Native Package](/assets/library/javafx-8-tutorial/part7/native-package.png)

La cartella `app` contiene i dati della nostra applicazione mentre la cartella `runtime` contiene lo specifico ambiente Java di runtime.

Per rendere il tutto ancora più semplice per l'utente, forniremo un installer:

* Un installer per windows sotto forma di file `exe`
* Un installer (drag and drop) per MacOS sotto forma di `dmg` (drag and drop)

Il plugin e(fx)clipse ci aiuterà a generare il package nativo e l'installer.


### Passo 1 - Editare build.fxbuild

Il file `build.fxbuild` viene utilizzato da e(fx)clipse per generare un file che verrà utilizzato dal tool Ant. (Se il file non esiste ancora`build.fxbuild` , create un nuovo *Java FX Project* in Eclipse e copiate il file generato.)

1. Aprite il file `build.fxbuild` dalla cartella root del progetto.

2. Compilate tutti i campi contenenti un asterisco. *Per MacOS: Non utilizzare spazi nel titolo dell'applicaione dato che potrebbero causare problemi.*    
![fxbuild settings](/assets/library/javafx-8-tutorial/part7/fxbuild-settings.png)

3. Come **Packaging Format** scegliete `exe` per Windows, `dmg` per MacOS, oppure `rpm` per Linux.

4. Clickate sul link `Generate ant build.xml only` (che so trova sul lato destro). 
![generate ant build](/assets/library/javafx-8-tutorial/part7/generate-ant-build.png)

5. Verificate che la nuova cartella `build` e il file `build.xml` siano stati creati.


### Passo 2 - Aggiungere le incone dell' Installer

Vorremo avere delle icone carine per il nostro installer:

* [AddressApp.ico](/assets/library/javafx-8-tutorial/part7/AddressApp.ico) per l'icona del file dell'installer
* [AddressApp-setup-icon.bmp](/assets/library/javafx-8-tutorial/part7/AddressApp-setup-icon.bmp) per l'icona splash screen dell'installer
* [AddressApp.icns](/assets/library/javafx-8-tutorial/part7/AddressApp.icns) per l'icona dell'installer per Mac
* [AddressApp-volume.icns](/assets/library/javafx-8-tutorial/part7/AddressApp-volume.icns) per l'icona desktop Mac


1. Create le seguenti sotto-cartelle nella cartella  `build`:
  * `build/package/windows` (utilizzata solo per windows)
  * `build/package/macosx` (utilizzata solo per macos)
2. Copiate le icone sopra dentro le sottocartelle corrispondenti. Adesso dovrebbe apparire così:   
![Installer Icons](/assets/library/javafx-8-tutorial/part7/installer-icons.png)
3. **Importante**: Il nome delle icone deve corrispondere esattamente al campo **Application title** che avete specificato nel file `build.fxbuild`:
  * `YourAppTitle.ico`
  * `YourAppTitle-setup-icon.bmp`
  * `YourAppTitle.icns`
  * `YourAppTitle-volume.icns`


### Passo 3 - Aggiungere il contenuto della cartella Resources

La nostra cartella `resources` non viene copiata automaticamnte. Dobbiamo aggiungerla manualmente alla cartella build:

1. Create la seguente sotto-cartella dentro `build`:
  * `build/dist`   
2. Copiate la cartella `resources`(contenente le immagini della vostra applicazione) dentro `build/dist`.    
![Build Resources](/assets/library/javafx-8-tutorial/part7/build-resources.png)


### Passo 4 - Editare build.xml per includere le icone

E(fx)clipse ha generato un  file `build/build.xml` che è pronto per essere dato in pasto al tool **Ant**, ma le icone e le risorse del nostro installer non funzionerebbero. 

Dato che non è possibile (per il momento) istruire e(fx)clipse per includere risorse addizionali alla nostra cartella `resources` e le icone degli installer che abbiamo aggiunto sopra, dobbiamo modificare manualmente il file `build.xml`:

Aprite `build.xml` e cercate l'elemento `fxant`. Aggiungete una riga per la `${basedir}` (ciò renderà disponibili le icone degli installer):


##### build.xml - add "basedir"

<pre class="prettyprint lang-xml">
&lt;path id="fxant"&gt;
  &lt;filelist&gt;
    &lt;file name="${java.home}\..\lib\ant-javafx.jar"/&gt;
    &lt;file name="${java.home}\lib\jfxrt.jar"/&gt;
    <mark>&lt;file name="${basedir}"/&gt;</mark>
  &lt;/filelist&gt;
&lt;/path&gt;
</pre>    


Cercate il blocco `fx:resources id="appRes"` più in basso nel file, e aggiungete la riga per le `resources`:

##### build.xml - add "resources"

<pre class="prettyprint lang-xml">
&lt;fx:resources id="appRes"&gt;
    &lt;fx:fileset dir="dist" includes="AddressApp.jar"/&gt;
    &lt;fx:fileset dir="dist" includes="libs/*"/&gt;
    <mark>&lt;fx:fileset dir="dist" includes="resources/**"/&gt;</mark>
&lt;/fx:resources&gt; 
</pre>


Per qualche motivo il numero di versione non viene aggiunto  in `fx:application`, pertanto l'installer apparirà sempre con la versione di default `1.0` (come puntualizzato da alcune persone nei commenti). Per sistemre la cosa, aggiungete manualmente il numero di versione (grazie a Marc per [la segnalazione](http://code.makery.ch/library/javafx-8-tutorial/part7/#comment-1566725959)):

##### build.xml - add "version"

<pre class="prettyprint lang-xml">
&lt;fx:application id="fxApplication"
    name="AddressApp"
    mainClass="ch.makery.address.MainApp"
    <mark>version="1.0"</mark>
/>
</pre>

A questo punto potremmo già lanciare il  `build.xml` con Ant. Questo genererebbe il jar eseguibile del progetto. Ma vogliamo fare un passo avanti e creare un pratico installer.


### Passo 5 (WINDOWS) - Installer 'exe' per Windows

![AddressApp su Windows](/assets/library/javafx-8-tutorial/part7/addressapp-windows.png)

Con **Inno Setup** possiamo creare un Installer Windows della nostra applicazione come un singolo file`.exe`. Il risultante `.exe` eseguirà una installazione con i permessi dell'utente (non sono richiesti i privilegi di amministratore). Inoltre verrà creato un collegamento (nel menù o sul desktop).

1. Scaricate [Inno Setup 5 o superiore](http://www.jrsoftware.org/isdl.php). Installate Inno Setup sul vostro computer. Il nostro script Ant script lo utilizzerà automaticamente per generare l'installer.

2. Istruite Windows sul path di installazione di Inno Setup (ad es. `C:\Program Files (x86)\Inno Setup 5`). Per farlo, aggiungete Inno Setup alla variabile `Path` tra le variabilidi ambiente di Windows. Se non sapete dove si trova, potete leggere [How to set the path and environment variables in Windows](http://www.computerhope.com/issues/ch000549.htm).

3. Riavviate  Eclipse e continuate con il passo 6.


### Passo 5 (MAC) - MacOS dmg Installer

![AddressApp su Mac](/assets/library/javafx-8-tutorial/part7/addressapp-macos.png)

Per creare un installer drag-and-drop `dmg` per Mac OS, non è richiesto nessun ulteriore tool.

Nota: per fare in modo che l'immagine dell'installer funzioni il nome dell'applicazione e quello dell'immagine devono coincidere.


### Passo 5 (LINUX etc.) - Linux rpm Installer

Par altro opzioni del  package (`msi` per windows, `rpm` per Linux) consultate questo [blog post sui package nativi](https://blogs.oracle.com/talkingjavadeployment/entry/native_packaging_for_javafx) oppure la [documentazione oracle ](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm#A1324980).


### Passo 6 - Lanciare il build.xml

Il passo finale consiste nell'eseguire il `build.xml` con Ant: *Right-click* sul file `build.xml` | *Run As* | *Ant Build*.

![Run Ant Build](/assets/library/javafx-8-tutorial/part7/run-ant-build.png)

Il building **ci impiegherà un pò** (circa un minuto sul mio pc).

Se va tutto a buon fine, dovreste trovare il bundle in formato nativo nella cartella `build/deploy/bundles`. Questo è come dovrebbe apparire nella versione per Windows:

![Deployed File](/assets/library/javafx-8-tutorial/part7/deployed-file.png)


Il file `AddressApp-1.0.exe` può essere utilizzato per installare l'applicazione. L'installer copierà il bundle in `C:/Users/[yourname]/AppData/Local/AddressApp`.


### Cosa c'è dopo?

Spero che questo tutorial vi sia stato d'aiuto per iniziare con JavaFX e che sarete in grado di scrivere il vostro progetto JavaFX a partire da adesso.

Ogni feedback sarà apprezzato. Sentitevi liberi di lasciare un commento se avete dei suggerimenti o se qualcosa non vi è chiaro.


##### Qualche altro articolo che potreste trovare interessante

* [JavaFX Dialogs (official)](/blog/javafx-dialogs-official/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)


