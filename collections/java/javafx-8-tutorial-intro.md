---
layout: article
title: "JavaFX 8 Tutorial"
date: 2014-04-19 00:00
updated: 2015-03-12 00:00
slug: javafx-8-tutorial-intro
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/java/javafx-8-tutorial-intro.md
description: "This seven-part tutorial walks through designing, programming and deploying an address application with JavaFX."
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /java/javafx-8-tutorial-intro
    paging: Intro
    active: true
  - text: "Part 1: Scene Builder"
    link: /java/javafx-8-tutorial-part1/
    paging: 1
  - text: "Part 2: Model and TableView"
    link: /java/javafx-8-tutorial-part2/
    paging: 2
  - text: "Part 3: Interacting with the User"
    link: /java/javafx-8-tutorial-part3/
    paging: 3
  - text: "Part 4: CSS Styling"
    link: /java/javafx-8-tutorial-part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /java/javafx-8-tutorial-part5/
    paging: 5
  - text: "Part 6: Statistics Chart"
    link: /java/javafx-8-tutorial-part6/
    paging: 6
  - text: "Part 7: Deployment"
    link: /java/javafx-8-tutorial-part7/
    paging: 7
- header: Languages
  languages: true
  body:
  - text: English
    link: /java/javafx-8-tutorial-intro/
    icon-css: fa fa-fw fa-globe
    active: true
  - text: Português
    link: /library/javafx-8-tutorial/pt/
    icon-css: fa fa-fw fa-globe
  - text: Español
    link: /library/javafx-8-tutorial/es/
    icon-css: fa fa-fw fa-globe
  - text: 中文（简体）
    link: /library/javafx-8-tutorial/zh-cn/
    icon-css: fa fa-fw fa-globe
  - text: Русский
    link: /library/javafx-8-tutorial/ru/
    icon-css: fa fa-fw fa-globe
  - text: Bahasa Indonesia
    link: /library/javafx-8-tutorial/id/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/javafx-8-tutorial/fr/
    icon-css: fa fa-fw fa-globe
---

Già nel 2012 avevo scritto una serie di dettagliati tutorial su [JavaFX 2](/java/javafx-2-tutorial-intro/) per i miei studenti. Molte persone in tutto il mondo hanno letto il tutorial dandomi feedback davvero positivi. Così ho deciso di **riscrivere il tutorial su JavaFX 2 per JavaFX 8** (leggi qualcosa sui cambiamenti in [Update to JavaFX 8 - What's New](/blog/update-to-javafx-8-whats-new/)).

Questo tutorial ti guida nella progettazione, nella programmazione e nalla costruzione di una rubrica. Così apparirà l'applicazione finita:

![Screenshot AddressApp](/assets/library/javafx-8-tutorial/addressapp.png)

##Cosa imparerai

* Creazione e avviamento di un progetto JavaFX
* Uso di Scene Builder per progettare l'interfaccia utente
* Strutturazione di un'applicazione con il patter Model-View-Controller (MVC)
* Uso di `ObservableLists` per l'aggiornamento automatico dell'interfaccia utente
* Uso di `TableView` e reazioni alle variazioni di selezione nella tabella
* Creazione di una finestra di dialogo personalizzata per la modifica dei dati delle persone
* Validazione dell'input dell'utente
* Stili di una applicazione JavaFX con i CSS
* Persistanza dei dati come XML
* Salvataggio dell'ultimo percorso di file aperto nelle preferenze dell'utente
* Creazione di un grafico JavaFX per le statistiche
* Costruzione di una apllicazione JavaFX come pacchetto nativo

**Tutto questo è molto** Quindi, dopo che completerai questa serie di tutorial, dovresti essere pronto per sviluppare applicazioni sofisticate con JavaFX. 


## Come usare questo Tutorial

Ci sono due modi per usare questo tutorial:

* **Metodo lento** Creare il proprio pregetto JavaFX partendo da zero
* **Metedo veloce** Importare il codice sorgente per il tutorial nel tuo IDE (è un progetto Eclipse, ma è possibile usare altri IDE come NetBeans con piccole modifiche). Dopodichè usare il tutorial per capire il codice

Adesso, spero che vi divertirete! Iniziamo con [Parte 1: Scene Builder](/java/javafx-8-tutorial-part1/).
