---
layout: article
title: "GreenfootKara - Solutions Chapitre 1"
date: 2014-07-10 00:00
slug: greenfoot-kara/fr/chapter1-solutions
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-fr-chapter1-solutions.md
published: true
prettify: true
comments: true
sidebars:
- header: Articles de cette Série
  body:
  - text: "Introduction"
    link: /library/greenfoot-kara/fr/
    paging: Intro
  - text: "Background Info"
    link: /library/greenfoot-kara/fr/background/
    icon-css: fa fa-fw fa-info
  - text: "Chapitre 1: Premiers Pas"
    link: /library/greenfoot-kara/fr/chapter1/
    paging: 1
  - text: "Chapitre 2: Ordinogramme"
    link: /library/greenfoot-kara/fr/chapter2/
    paging: 2
  - text: "Chapitre 3: Variables"
    link: /library/greenfoot-kara/fr/chapter3/
    paging: 3
  - text: "Chapitre 4: Jeu Sokoban"
    link: /library/greenfoot-kara/fr/chapter4/
    paging: 4
  - text: "Chapitre 5: Méthodes"
    link: /library/greenfoot-kara/fr/chapter5/
    paging: 5
- header: Solutions
  body:
  - text: "Solutions Chapitre 1"
    link: /library/greenfoot-kara/fr/chapter1-solutions/
    icon-css: fa fa-fw fa-check-square-o
    active: true
- header: Downloads
  body:
  - text: scenarios-chapter-1-solutions.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-1-solutions.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Page as Word File
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-word-o
languages: 
  header: Langues
  collection: library
  item: greenfoot-kara
  part: chapter1-solutions
  active: fr
---

<div class="alert alert-warning">
  <i class="fa fa-language"></i> This page contains parts that are not translated yet. If you'd like to help out please read <a href="/library/how-to-contribute/" class="alert-link">how to contribute</a>.
</div>


#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 1.02

<div class="alpha-list hidden"></div>

1. Que fait la méthode `move()` ?   
  **Kara avance d'une cellule.**
2. Placez deux Karas et faites les se faire face. De quelle méthode avez-vous besoin ?   
  **turnLeft() ou turnRight()**
3. Essayez les autres méthodes. Il existe deux types de méthodes. Quelles sont ces 2 types et que font-elles ?   
  **Les méthodes avec void: Réalise une action.**
  **Les méthodes avec un boolean: Ouvre une fenêtre qui contient le résultat attendu.**


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 1.03

<div class="alpha-list hidden"></div>

1. Clic droit sur l'objet Kara et appelez la méthode `onLeaf()`. Est-ce qu'elle retourne toujours `false` ? Ou y a-t-il des cas où elle retourne `true` ?   
  **Si Kara est sur une feuille, cette méthode retourne true.**
2. Ajoutez un arbre au monde. Quelle méthode invoquer pour vérifier si Kara est face à un arbre ?   
  **treeFront()**
3. Que se passe-t-il si vous invoyez la méthode `move()` de Kara method alors qu'elle fait face à un arbre ?   
  **Kara indique qu'elle ne peut pas avancer.**


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 1.04

Quelles sont les valeurs des coordonnées et de la rotation dans les situations suivantes ?

1. **x: 0, y: 0, rotation: 0**

2. **x: 1, y: 2, rotation: 180**


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 1.05

<div class="alpha-list hidden"></div>

1. Placez un objet `MyKara` dans le monde. Quelle nouvelle méthode est  devenue disponible ?   
  **act()**
2. Que fait cette méthode ?   
  **Move, turn right, move**
3. Que se passe-t-il si vous appuyez sur le bouton de contrôle Act de Greenfoot ?   
  **Même choses que ci-desus, la méthode  act() est appelée**
4. Appuyez sur le **bouton Run**. Que se passe-t-il ? Essayez d'utiliser le potentiomètre linéaire.   
act() est appelée encore et encore jusqu'à ce le bouton pause soit cliqué.
5. Quelle méthodes peut-on voir quand on clique droit sur `MyKara` puis "inherited from Kara" ? Peut-on utiliser ces méthodes ?   
  **Toutes les méthodes héritées peuvent être utilisées directement dans MyKara.**


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 1.06

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	
	public void act() {
		move();
        putLeaf();
        move();

		stop();
	}
}
</pre>

***

#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 1.07

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	
	public void act() {
		move();

		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
		turnLeft();

		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
		turnLeft();

		move();

		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
		turnLeft();

		removeLeaf();

		stop();
	}
}
</pre>


#### Notes

1. The **comments** in the code have been omitted (= the text that is in the Greenfoot editor either gray or blue).
Comments in the source code are written as additional information. The comments are for people and are ignored by the computer. There are **three ways** to post comments in the code:
  1. With a double slash `//` (after the double slash the rest of the line is a comment)
  2. Longer comments over several lines are enclosed between `/*` and `*/`.
  3. Comments on methods and classes are written between `/**` and `*/`.
2.	The method `stop()` makes sure the simulation stops after the `act()`-Method even when the Run-button was pressed.


***

#### <i class="fa fa-check-square-o"></i> SOLUTION TÂCHE 1.08

<pre class="prettyprint lang-java">
public class MyKara extends Kara {
	public void act() {
		move();
		goAroundTree();
		goAroundTree();
		move();
		goAroundTree();
		removeLeaf();

		stop();
	}

	public void goAroundTree() {
		turnLeft();
		move();
		turnRight();
		move();
		move();
		turnRight();
		move();
		turnLeft();
	}
}
</pre>

#### Notes

1. For greater clarity and to avoid that we need to write the same code three times, we have developed a new method `goAroundTree()`.
2. The name of the methods here are preceded by two keywords: `public void`.
`public` means that the method can be called from outside.
`void` means that the method returns no value.
3. Behind each method is a pair of parentheses `()` which means that the method gets passed no parameters. Later we will learn how to write methods with parameters.
