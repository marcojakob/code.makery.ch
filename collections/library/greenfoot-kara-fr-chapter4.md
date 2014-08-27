---
layout: article
title: "GreenfootKara - Chapitre 4: Jeu Sokoban"
date: 2014-07-10 00:00
slug: greenfoot-kara/fr/chapter4
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/greenfoot-kara-fr-chapter4.md
published: true
prettify: true
comments: true
sidebars:
- header: Articles de cette Série
  body:
  - text: "Introduction"
    link: /library/greenfoot-kara/fr/
    paging: Intro
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
    active: true
  - text: "Solutions Chapitre 4"
    link: /library/greenfoot-kara/fr/chapter4-solutions/
    icon-css: fa fa-fw fa-check-square-o
  - text: "Chapitre 5: Méthodes"
    link: /library/greenfoot-kara/fr/chapter5/
    paging: 5
- header: Downloads
  body:
  - text: scenarios-chapter-4.zip
    link: https://github.com/marcojakob/greenfoot-kara/releases/download/2.1.0/scenarios-chapter-4.zip
    icon-css: fa fa-fw fa-file-archive-o
  - text: Page as Word File
    link: /library/convert-web-page-to-word/
    icon-css: fa fa-fw fa-file-word-o
- header: Languages
  body:
  - text: English
    link: /library/greenfoot-kara/chapter4/
    icon-css: fa fa-fw fa-globe
  - text: Deutsch
    link: /library/greenfoot-kara/de/chapter4/
    icon-css: fa fa-fw fa-globe
  - text: Français
    link: /library/greenfoot-kara/fr/chapter4/
    icon-css: fa fa-fw fa-globe
    active: true
---

## Sokoban 

Sokoban (倉庫番, Japanese "Chef d'entrepôt") est un jeu électronique développé par Hiroyuki Imabayashi en 1982 sous le nom "Thinking Rabbit" et qui a été édité sur plusieurs OS. (Source: [Sokoban on Wikipedia](http://fr.wikipedia.org/wiki/Sokoban))


### Principe du jeu

Le principe du jeu est qu'un personne doit déplacer tous (à l'origine des caisses) les  objets vers une destination désignée. Les caisses peuvent être poussées mais ne peuvent pas être tirées par le, et une seule caisse doit être déplacée à la fois. Le but est d'atteindre un niveau supérieur de difficulté. Mais c'est aussi un défi permanent d'essayer de minimiser les pas nécessaires et donc les efforts. 

Dans notre Kara Sokoban les champignons doivent être poussés sur les domaines cibles (leafs).


### Description des Niveaux

Plusieurs jeux de Sokoban games utilisent un simple format  ASCII pour définir les niveaux. Pour créer nos proprpres nieaux n'importe quel éditeur de texte fera l'affaire. Un exemple avec Kara pourrait être tel que :

![Example World](/assets/library/greenfoot-kara/chapter4/example-world.png) 

<pre class="prettyprint">
####
# .#
#  ###
# @  #
#  $ #
#  ###
####
</pre>

Les différents éléments sont représentés par les symboles suivants :

* `#`: Arbre
* `@`: Kara
* `.`: Feuille
* `$`: Champignon
* `*`: Champignon sur une feuille
* `+`: Kara sur une feuille


***

## Programmer Sokoban

Pour pouvoir jouer au "Kara Sokoban", nous devons programmer le comportement de Kara. Le joueur devra pouvoir controler Kara avec les touches flèches du clavier :


### Contrôler Kara avec les touches "flèches de direction" du clavier

Ouvrez le scénario **Kara 401 Sokoban** dans le dossier **scenarios-chapter-4**. 

On peut voir qu'il y a quelques classes complémentaires au monde qui on été ajoutées. Ces nouvelles classes ne doivent pas nous perturber,  nous allons continuer à intervenir principalement sur les classes `Kara` and `MyKara`.

Double-clic pour charger dans l'éditeur la classe (en gris) `KaraSokoban`. Cette classe contient les méthodes qui sont disponibles pour `MyKara`. Les méthodes de `Kara` sont aussi, bien sûr, héritées. Exami-nons la documentation en choisissant cela dans le coin supérieur droit de l'éditeur pour passer de *source code* à *documentation*. 

![Documentation](/assets/library/greenfoot-kara/chapter4/documentation.png) 

Ici on ne voit que les commentaires de la classe et des méthodes. Et ce qui nous intéresse se trouve dans *Method Summary*.

![Method Summary](/assets/library/greenfoot-kara/chapter4/method-summary.png) 

On retrouve quelques méthodes connues de Kara mais aussi quelques nouvelles méthodes. Prenez un peu de temps pour vous familiariser avec ces méthodes, examinez le détail des commentaires en suivant les liens en bleu.

Maintenant retrouvez les méthodes qui permettent de recevoir l'information quand on enfonce une touche du clavier.

La méthode qui fait cela a un type de retour qui n'est pas void mais `String`. Une chaîne de caractères (`String`) est du texte (i.e. un mot ou une phrase) qui est enfermée entre deux guillemets. Les exemples suivants sont tous des exemples de chaînes de caractères (`String`):

* `"I am a string"`
* `"Hello"`
* `"a"`

Dans notre cas, le résultat, de type `String` est le nom de la touche qui a été pressée en dernier et qui est retouné au programme qui fait appel à cette méthode. On peut sauvegarder cette chaîne (`String`) en utilisant cette ligne de code :

<pre class="prettyprint lang-java">
String key = getKey();
</pre>

Saisissez cette ligne de code dans la méthode `act()` de la classe `MyKara` (en rouge)!

Les chaînes (Strings) peuvent être comparées entre elles en utilisant une méthode spéciale appelée `equals()`. Pour pouvoir agir quand une touche a été enfoncée, nous devons ajouter l'instruction suivante :

<pre class="prettyprint lang-java">
if (key.equals("left")) {
	// Left key was pressed -> do something
}
</pre>


#### <i class="fa fa-rocket"></i> TÂCHE 4.01 : Contrôler Kara avec les touches du clavier

Complétez le code dans la méthode `act()` afin que Kara bouge dans la direction désirée quand on appuie sur une des quatre touches de direction. Aidez-vous de la documentation de la classe Kara (en gris). Pour tester appuyer sur le bouton **Run** !


### Mise en place des Obstacles pour Kara

Que se passe-t-il quand vous entrez dans une arbre en voiture ? Et si vous roulez sur un champignon ?


#### <i class="fa fa-rocket"></i> TÂCHE 4.02 : Protéger Kara des arbres

Réparer l'erreur afin que, Kara ne puisse pas se déplacer pour heurter un arbre quand elle en voit un.

*Rappel :* Le code ne doit pas être dupliqué. Il vaut mieux écrire une méthode que l'on puisse appeler autant que de besoin !


#### <i class="fa fa-rocket"></i> TÂCHE 4.03 : Kara pousse les champignons

Evidemment, Kara doit pouvoir déplacer un champignon en le poussant. Programmez Kara afin qu'elle puisse pousser un champignon. Assurez-vou qu'il n'y ait plus de message d'erreur.

*Note : Si vous êtes bloqué quelque part, vous pouvez appuyer sur le bouton "Retry Level"*


#### <i class="fa fa-rocket"></i> TÂCHE 4.04 : Le travail est fait

Après que Kara ait fait son travail, on va lui en confier un autre, i.e. le niveau suivant doit être chargé. Kara a un moyen de savoir si son travail est terminé. Trouvez quelle est cette méthode et appelez-la au bon endroit du code source.

Puis vous devriez être confronté à un niveau supérieur. Pour l'instant il y a 4 niveaux de jeu. On ajoutera des niveaux plus tard.


#### <i class="fa fa-rocket"></i> TÂCHE 4.05 : Kara compte les étapes

En bas de l'écran il y a un affichage prévu pour les mouvement qui, pour l'instant ne marche pas. Pour in-crémenter ce nombre Kara dispose aussi d'une méthode. La commande suivante va portre ce nombre à 3, par exemple : `setNumberOfMoves(3);`

Modifiez votre programme afin qu'il prenne en compte l'inscription du nombre de pas réalisés. Pour ce faire vous devez déclarer une variable en dehors de la méthode act().Variable que l'on appelle un champ ou une *variable d'instance* parce qu'elle sera disponible à n'importe quel endroit de la classe. 


#### <i class="fa fa-rocket"></i> TÂCHE 4.06 : Jouons

Voilà, notre jeu de Sokoban est vraiment jouable. Le jeu dispose déjà d'un menu principas qui doit être dé-bloqué ainsi :

Dans la classe `MyKara` vous allez trouver une constante‚ `DEVELOPER_MODE`.  Il suffit de mettre cette constante à `false`, et de Compiler, le menu principal est lancé. Dans le menu principal vous pouvez saisir un mot de passe pour votre niveau ; ainsi vous pouvez reprendre votre jeu à l'endroit où vous l'avez laissé en dernier.


#### <i class="fa fa-rocket"></i> TÂCHE 4.07 : Définissez votre propres niveaux

Les niveaux de jeu sont lus à partir du fichier **Levels.txt**. Localisez ce fichier dans le scénario et ouvrez le avec un éditeur de texte. Essayez de créer un niveau de jeu supplémentaire.

*Truc 1 :* Vous pouvez créer des niveaux un peu plus facilement dans Greenfoot avec une petite astuce. Assurez-vous que la constante `DEVELOPER_MODE` est positionnée à `true`. Maintenant vous pouvez placer des "actors" dans le monde Greenfoot.   
Quand vous avez fini, cliquez droit avec votre souris à un endroit vide du monde. Selectionnez la generateASCIILevel(). Ceci va générer un niveau au format  ASCII dans la console. Il ne vous reste plus qu'à copier cela dans le fichier *Levels.txt*.

*Truc 2 :* Le fichier qui contient les niveaux peut être aussi échangé avec d'autres. Pour cela on peut définir des niveaux de fichiers, la variable `LEVEL_FILE` peut être modifiée dans la classe `MyKara`.

*Truc 3 :* Pour réaliser des niveaux additionnels vous pouvez vous inspirer des sites suivants :
* http://sneezingtiger.com/sokoban/levels.html (regardez les niveaux Microban, les autres sont vraiment très grands !)
* http://www.sourcecode.se/sokoban/levels.php (si vous cliquez sur le *T*, vous obtenez le niveau désiré level uau format ASCII)


#### <i class="fa fa-rocket"></i> TÂCHE 4.08 (créativité) : My pictures

Les images de Kara, champignon, feuille, arbre et fond d'écran peuvent être. Pour choisir une image diffé-rente, vous pouvez cliquer-droit sur les classes et choisir set image... . La plupart des images ont une taille de 28 x 28 pixels.


#### <i class="fa fa-rocket"></i> TÂCHE 4.09 (prise en compte de la vitesse) : Le meilleur score

Dans la classe `MyKara` si la constante `HIGHSCORE_ENABLED` est positionnée à `true`, alors les scores les plus élevé sont affichés. Dans le menu principal, un bouton complémentaire est activé.

Il y a toujours les trois plus hauts scores par niveau. Kara intègre des méthodes pour modifier le plus haut score.

Essayez de modifier votre programme afin qu'il teste si on a réalisé un score remarquable. Et, si c'est le cas, ce score devra être affiché.


#### <i class="fa fa-rocket"></i> IDÉES POUR ALLER PLUS LOIN : Jouer des Sons

On peut avec Greenfoot ajouter des sons au scénario Kara. Des informations sur la gestion des sons peuvent être trouvées dans la documentation de Greenfoot,  dans la documentation de la classe `GreenfootSound`.


***

J'espère que vous avez eu du plaisir avec Kara.

Si vous souhaitez partager votre scénario avec d'autres, choisissez Export dans le menu Scenario. L'exportation peut être faite en ligne, dans la "Greenfoot Gallery", ou en tant qu'exécutable Java. 


## Etape suivante ?

&rarr; [Chapitre 5: Méthodes](/library/greenfoot-kara/fr/chapter5/)


*** 

***Sources:*** *Les [idées et concepts de Kara](http://www.swisseduc.ch/informatik/karatojava/) ont été developpés par Jürg Nievergelt, Werner Hartmann, Raimond Reichert et al. 

***Traduction française:*** *Christian Malen <i class="fa fa-trophy"></i> - thank you for your contributions!* 

