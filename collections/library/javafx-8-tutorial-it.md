---
layout: article
title: "JavaFX 8 Tutorial (Italiano)"
date: 2014-04-19 00:00
updated: 2015-03-12 00:00
slug: javafx-8-tutorial/it
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-it.md
description: "Questo tutorial in sette parti, guida nella progettazione, nella programmazione e nel deploying di una applicazione rubrica con JavaFX."
image: /assets/library/javafx-8-tutorial/addressapp.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articoli in questa serie"
  body:
  - text: "Introduzione"
    link: /library/javafx-8-tutorial/it/
    paging: Intro
    active: true
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
languages: 
  header: Languages
  collection: library
  item: javafx-8-tutorial
  part: 
  active: it
---

Già nel 2012 avevo scritto una serie di dettagliati tutorial su [JavaFX 2](/java/javafx-2-tutorial-intro/) per i miei studenti. Molte persone in tutto il mondo hanno letto i tutorial dandomi feedback davvero positivi. Così ho deciso di **riscrivere il tutorial su JavaFX 2 per JavaFX 8** (leggi qualcosa sui cambiamenti in [Update to JavaFX 8 - What's New](/blog/update-to-javafx-8-whats-new/)).
 
Questo tutorial ti guida nella progettazione, nella programmazione e nel "deploying" di una rubrica. Così apparirà l'applicazione finita:

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
* "Deployment" di una applicazione JavaFX come pacchetto nativo

**Non è poco** Così, dopo che completerai questa serie di tutorial, dovresti essere pronto per sviluppare applicazioni sofisticate con JavaFX. 


## Come usare questo Tutorial

Ci sono due modi per usare questo tutorial:

* **Metodo approfondito** Creare il proprio pregetto JavaFX partendo da zero
* **Metedo veloce** Importare il codice sorgente per il tutorial nel proprio IDE (è un progetto Eclipse, ma è possibile usare altri IDE come NetBeans con piccole modifiche). Dopodichè usare il tutorial per capire il codice


Adesso spero che vi divertirete! Iniziamo con [Parte 1: Scene Builder](/library/javafx-8-tutorial/it/part1/).

<div class="alert alert-success">
  <strong><i class="fa fa-trophy"></i> Attribution:</strong> The italian translation has been contributed by 
  <ul>
    <li><a href="https://github.com/ksclero" class="alert-link">Ettore Stani</a></li> 
    <li><a href="https://github.com/Mistra" class="alert-link">Giulio Mistrangelo</a></li> 
    <li><a href="https://github.com/Zikoel" class="alert-link">Elia Mazzuoli</a></li> 
    <li><a href="https://github.com/luigimartin" class="alert-link">Luigi Martin Petrella</a></li> 
  </ul>
  Thank you very much!
</div>
