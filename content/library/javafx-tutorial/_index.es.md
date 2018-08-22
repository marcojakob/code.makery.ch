---
layout: article
title: "Tutorial JavaFX 8 (Español)"
date: 2014-09-17
slug: javafx-tutorial/es
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-tutorial-es.md
description: "Este tutorial en siete partes describe el diseño, programación y publicación de una aplicación de contactos con JavaFX."
image: /assets/library/javafx-tutorial/addressapp.png
published: true
prettify: true
comments: false
sidebars:
- header: "Artículos en esta serie"
  body:
  - text: "Introducción"
    link: /library/javafx-tutorial/es/
    paging: Intro
    active: true
  - text: "Parte 1: Scene Builder"
    link: /library/javafx-tutorial/es/part1/
    paging: 1
  - text: "Parte 2: Modelo y TableView"
    link: /library/javafx-tutorial/es/part2/
    paging: 2
  - text: "Parte 3: Interacción con el usuario"
    link: /library/javafx-tutorial/es/part3/
    paging: 3
  - text: "Parte 4: Hojas de estilo CSS"
    link: /library/javafx-tutorial/es/part4/
    paging: 4
  - text: "Parte 5: Persistencia de datos con XML"
    link: /library/javafx-tutorial/es/part5/
    paging: 5
  - text: "Parte 6: Gráficos estadísticos"
    link: /library/javafx-tutorial/es/part6/
    paging: 6
  - text: "Parte 7: Despliegue"
    link: /library/javafx-tutorial/es/part7/
    paging: 7
languages: 
  header: Lenguajes
  collection: library
  item: javafx-tutorial
  part: 
  active: es
---

JavaFX proporciona a los desarrolladores de Java una nueva plataforma gráfica.  JavaFX 2.0 se publicó en octubre del 2011 con la intención de reemplazar a Swing en la creación de nuevos interfaces gráficos de usario (IGU). Cuando empecé a enseñar JavaFX en 2011 era una tecnología muy incipiente todavía. No había libros sobre JavaFX que fueran **adecuados para estudiantes de programación noveles**, así es que empecé a escribir una serie de tutoriales muy detallados sobre JavaFX.

El tutorial te guía a lo largo del diseño, programación y publicación de una aplicación de contactos (libreta de direcciones) mediante JavaFX. Este es el aspecto que tendrá la aplicación final:

![Screenshot AddressApp](/assets/library/javafx-tutorial/addressapp.png)


## Lo que aprenderás

* Creación de un nuevo projecto JavaFX
* Uso de Scene Builder para diseñar la interfaz de usuario
* Estructuración de una aplicación según el patrón MVC (Modelo, Vista, Controlador)
* Uso de `ObservableList` para la actualización automática de la interfaz de usuario
* Uso de `TableView` y respuesta a cambios de selección en la tabla
* Creación de un diálogo personalizado para editar personas
* Validación de la entrada del usuario
* Aplicación de estilos usando CSS
* Persistencia de datos mediante XML
* Guardado del último archivo abierto en las preferencias de usuario
* Creación de un gráfico JavaFX para mostrar estadísticas
* Despliegue de una aplicación JavaFX nativa

Después de completar esta serie de tutoriales deberías estar preparado para desarrollar aplicaciones sofisticadas con JavaFX.


## Como usar este tutorial

Hay dos formas de utilizarlo

* **máximo-aprendizaje**: Crea tu propio proyecto JavaFX desde cero.
* **máxima-rápidez**: Importa el código fuente de una parte del tutorial en tu entorno de desarrollo favorito (es un proyecto Eclipse, pero puedes usar otros entornos, como Netbeans, con ligeras modificaciones). Después revisa el tutorial para entender el código. Este enfoque también resulta útil si te quedas atascado en la creación de tu propio código.

¡ Espero que te diviertas y aprendas mucho ! Empieza en  [Part 1: Scene Builder](/library/javafx-tutorial/es/part1/).



<div class="alert alert-success">
  <strong>Atribución:</strong> Este tutorial fue traducido a español por <a href="http://about.me/magomar" class="alert-link">Mario Gómez Martínez <i class="fa fa-trophy"></i></a>. ¡ Muchas gracias !
</div>
