---
layout: article
title: "JavaFX 8 Tutorial - Part 7: Deployment"
date: 2014-05-10 00:00
updated: 2015-02-18 00:00
slug: javafx-8-tutorial/fr/part7
canonical: /library/javafx-8-tutorial/part7/
github: https://github.com/marcojakob/code.makery.ch/edit/master/collections/library/javafx-8-tutorial-fr-part7.md
description: "Comment déployer une application JavaFX avec un package natif. Créez  un fichier d'install pour Windows, MacOS, ou Linux."
image: /assets/library/javafx-8-tutorial/part7/addressapp-macos.png
published: true
prettify: true
comments: true
sidebars:
- header: "Articles in this Series"
  body:
  - text: "Introduction"
    link: /library/javafx-8-tutorial/fr/
    paging: Intro
  - text: "Part 1: Scene Builder"
    link: /library/javafx-8-tutorial/fr/part1/
    paging: 1
  - text: "Part 2: Model and TableView"
    link: /library/javafx-8-tutorial/fr/part2/
    paging: 2
  - text: "Part 3: Interacting with the User"
    link: /library/javafx-8-tutorial/fr/part3/
    paging: 3
  - text: "Part 4: CSS Styling"
    link: /library/javafx-8-tutorial/fr/part4/
    paging: 4
  - text: "Part 5: Storing Data as XML"
    link: /library/javafx-8-tutorial/fr/part5/
    paging: 5
  - text: "Part 6: Statistics Chart"
    link: /library/javafx-8-tutorial/fr/part6/
    paging: 6
  - text: "Part 7: Deployment"
    link: /library/javafx-8-tutorial/fr/part7/
    paging: 7
    active: true
- header: "Download Sources"
  body:
  - text: Part 7 as Eclipse Project <em>(requires at least JDK 8u20)</em>
    link: https://github.com/marcojakob/tutorial-javafx-8/releases/download/v1.0/addressapp-jfx8-part-7.zip
    icon-css: fa fa-fw fa-download
languages: 
  header: Langues
  collection: library
  item: javafx-8-tutorial
  part: part7
  active: fr
---

![Capture AddressApp Partie 7](/assets/library/javafx-8-tutorial/part7/addressapp-part7.png)

J'ai pensé que je devais écrire une dernière partie pour conclure ce tutoriel, partie qui traiterait du déploiement de l'application AddressApp.


*****

## Le sujet de la 7ème partie

* Déployer notre application JavaFX comme **Package natif** avec e(fx)clipse


*****

## Qu'est ce que le déploiement ?

Le déploiement, c'est le fait de packager l'application puis de la proposer à l'utilisateur. Cette partie du développement est cruciale puisqu'il s'agit du premier contact qu'aura l'utilisateur avec votre programme.

Java mettait souvent en avant le slogan **Write Once, Run Anywhere** (écrit une fois, exécuté partout) pour illustrer l'aspect *cross-platform* du langage Java. Idéalement, cela signifie que votre application java peut etre exécuté sur n'importe quel dispositif disposant d'une machine virtuelle Java (JVM).

Par le passé, installer une application Java n'était jamais de tout repos. Si l'utilisateur ne disposait pas de la bonne version de Java sur son système, il devait se voir invité à l'installer préalablement. Cela amenait quelques difficultés comme, par exemple, la nécessité de disposer de droits administrateur, des problèmes de compatibilité entre versions de Java, etc.

Heureusement, JavaFX propose une nouvelle option appelée **Packaging natif ** (aussi appelé Self-Contained Application Package). Un package natif regroupe en fait votre application ainsi que la machine virtuelle Java (qui, elle, est spécifique à une plateforme). 

La documentation JavaFX fournie par Oracle contient un guide assez complet pour toutes les [options de déploiement JavaFX](http://docs.oracle.com/javafx/2/deployment/jfxpub-deployment.htm) (en anglais). 

Dans cette partie, je vais vous montrer comment créer un **Package Natif** avec Eclipse et le [**plugin e(fx)clipse **](http://www.eclipse.org/efxclipse/).


*****

## Créer un Package Natif

Le but est de créer une application exécutable contenue dans un seul et unique répertoire sur l'ordinateur de l'utilisateur. Voilà à quoi doit ressembler notre AddressApp (sous Windows):

![AddressApp Native Package](/assets/library/javafx-8-tutorial/part7/native-package.png)

Le répertoire `app` contient les éléments de notre application et le répertoire `runtime` contient la machine virtuelle Java spécifique à la plateforme.

Pour rendre le tout encore plus confortable à l'utilisateur, nous allons créer un fichier d'installation:

* Un fichier d'installation `exe` pour windows
* Un fichier d'installation `dmg` (drag and drop) pour MacOS.

Le plugin e(fx)clipse nous aidera à générer le package natif ainsi que le programme d'installation.


### Etape 1 - Editer le fichier build.fxbuild

Le fichier `build.fxbuild` est utilisé par e(fx)clipse pour générer un fichier qui sera utilisé par l'outil de génération Ant. (Si vous n'avez pas de fichier `build.fxbuild`, créez un nouveau *Projet Java FX* dans Eclipse et copiez le fichier généré.)

1. Ouvrez le fichier `build.fxbuild` à la racine du projet.

2. Remplissez tous les champs contenant une étoile. *Pour MacOS: N'utilisez pas d'espace dans le titre de l'application puisque cela semble poser un problème.*    
![réglages fxbuild](/assets/library/javafx-8-tutorial/part7/fxbuild-settings.png)

3. Comme **Packaging Format** choisissez `exe` pour Windows, `dmg` pour MacOS, et `rpm` pour Linux.

4. Cliquez sur le lien `Generate ant build.xml only` (sur le côté droit).   
![generate ant build](/assets/library/javafx-8-tutorial/part7/generate-ant-build.png)

5. Verifiez qu'un nouveau répertoire `build` et qu'un fichier `build.xml` sont créés.


### Etape 2 - Ajouter des icônes pour le fichier d'install

Nous souhaitons avoir de belles icônes pour notre install:

* [AddressApp.ico](/assets/library/javafx-8-tutorial/part7/AddressApp.ico) pour l'icône du fichier d'install
* [AddressApp-setup-icon.bmp](/assets/library/javafx-8-tutorial/part7/AddressApp-setup-icon.bmp) pour le splash screen de l'install
* [AddressApp.icns](/assets/library/javafx-8-tutorial/part7/AddressApp.icns) pour l'icône de l'install sous mac


1. Créez les sous-répertoires suivants dans le répertoire `build`:
  * `build/package/windows` (utilisé seulement pour windows)
  * `build/package/macos` (utilisé seulement pour macos)
2. Copiez les icônes correspondantes (celles mentionnées ci-dessus) dans ces sous-répertoires. Cela devrait ressembler à quelque chose comme ça maintenant:   
![Installer Icons](/assets/library/javafx-8-tutorial/part7/installer-icons.png)
3. **Important**: Le nom des icônes doit parfaitement coller à l'**Application title** que vous avez spécifié dans votre `build.fxbuild`:
  * `YourAppTitle.ico`
  * `YourAppTitle-setup-icon.bmp`
  * `YourAppTitle.icns`


### Etape 3 - Ajouter les ressources

Notre répertoire `resources` n'est pas copié automatiquement. Nous devons donc l'ajouter manuellement dans le répertoire de build:

1. Créez le sous-répertoire suivant dans le répertoire `build` :
  * `build/dist`   
2. Copiez le répertoire `resources` (contenant les images de notre application) dans `build/dist`.    
![Build Resources](/assets/library/javafx-8-tutorial/part7/build-resources.png)


### Etape 4 - Editer build.xml pour inclure les icônes

E(fx)clipse a généré un fichier `build/build.xml` qui est prêt à être exécuté par **Ant**. Il manquera toujours nos icônes et nos ressources.

Comme e(fx)clipse ne peut pas (encore) inclure des ressources additionelles comme notre sous-répertoire `resources` et nos icônes, nous allons devoir manuellement éditer le fichier `build.xml`:

Ouvrez `build.xml` et trouvez l'entrée `fxant`. Ajouter une ligne pour le `${basedir}` (cela permettra de rendre accessible nos icônes pour l'install):


##### build.xml - ajouter "basedir"

<pre class="prettyprint lang-xml">
&lt;path id="fxant"&gt;
  &lt;filelist&gt;
    &lt;file name="${java.home}\..\lib\ant-javafx.jar"/&gt;
    &lt;file name="${java.home}\lib\jfxrt.jar"/&gt;
    <mark>&lt;file name="${basedir}"/&gt;</mark>
  &lt;/filelist&gt;
&lt;/path&gt;
</pre>    


Trouvez le bloc `fx:resources id="appRes"` un peu plus bas dans le fichier. Ajoutez une ligne pour notre sous-répertoire `resources`:

##### build.xml - ajouter "resources"

<pre class="prettyprint lang-xml">
&lt;fx:resources id="appRes"&gt;
    &lt;fx:fileset dir="dist" includes="AddressApp.jar"/&gt;
    &lt;fx:fileset dir="dist" includes="libs/*"/&gt;
    <mark>&lt;fx:fileset dir="dist" includes="resources/**"/&gt;</mark>
&lt;/fx:resources&gt; 
</pre>


Le numéro de version n'a pas été ajouté à `fx:application` ce qui implique un numéro de version `1.0` par défaut (comme signalé par quelques personnes dans les commentaires). Pour corriger ça, ajoutez manuellement le numéro de version (Merci à Marc pour [la solution](http://code.makery.ch/library/javafx-8-tutorial/part7/#comment-1566725959)):

##### build.xml - ajouter la "version"

<pre class="prettyprint lang-xml">
&lt;fx:application id="fxApplication"
    name="AddressApp"
    mainClass="ch.makery.address.MainApp"
    <mark>version="1.0"</mark>
/>
</pre>

A ce stade, nous pourrions déjà lancer `build.xml` via Ant. Cela génèrerait un jar exécutable du projet. Mais nous voulons aller un cran plus loin et créer un bel install.


### Etape 5 (WINDOWS) - Install Windows en .exe

![AddressApp on Windows](/assets/library/javafx-8-tutorial/part7/addressapp-windows.png)

Avec **Inno Setup** nous pouvons créer un install pour Windows de notre application, le tout sous la forme d'un unique fichier `.exe`. L'`.exe` généré ne nécessitera que des droits du niveau de l'utilisateur (pas besoin de droits administrateur). Un raccourci sera également créé (dans le menu ou sur le bureau).

1. Téléchargez [Inno Setup 5 ou +](http://www.jrsoftware.org/isdl.php). Installez Inno Setup sur votre ordinateur. Notre script Ant l'utilisera automatiquement pour générer le fichier d'install.

2. Indiquez à Windows le chemin d'installation d'Inno Setup (par exemple `C:\Program Files (x86)\Inno Setup 5`). Pour cela, ajoutez le `Path` d'Inno Setup aux variables d'environnement de windows. Si vous ne savez pas comment faire, lisez ce tutoriel (en anglais) : [How to set the path and environment variables in Windows](http://www.computerhope.com/issues/ch000549.htm).

3. Redémarrez Eclipse et continuez avec l'étape 5.


### Etape 5 (MAC) - Install MacOS en .dmg

![AddressApp on Mac](/assets/library/javafx-8-tutorial/part7/addressapp-macos.png)

Pour créer un install pour Mac OS en `dmg`, aucun outil additionnel n'est nécessaire.

Note: Pour que l'image de l'install fonctionne, elle doit avoir exactement le même nom que l'application.


### Etape 5 (LINUX etc.) - Install Linux en .rpm 

Pour d'autres options (`msi` pour windows, `rpm` pour Linux) consultez ce [post de blog](https://blogs.oracle.com/talkingjavadeployment/entry/native_packaging_for_javafx) (en anglais) ou la [documentation Oracle](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm#A1324980) (en anglais).


### Etape 6 - Exécuter build.xml

L'étape finale consiste à exécuter `build.xml` avec Ant: *clic droit* sur le fichier `build.xml` puis *Run As* et enfin *Ant Build*.

![Run Ant Build](/assets/library/javafx-8-tutorial/part7/run-ant-build.png)

La génération **prendra un peu de temps** (de l'ordre d'une minute sur mon ordinateur).

Si l'opération est un succès, vous devriez trouver votre package complet dans le répertoire `build/deploy/bundles`. Voici à quoi cela ressemble sous windows:

![Deployed File](/assets/library/javafx-8-tutorial/part7/deployed-file.png)


Le fichier `AddressApp-1.0.exe` peut être utilisé comme install de l'application. Cette dernière copiera le tout à l'adresse `C:/Users/[yourname]/AppData/Local/AddressApp`.


### Et ensuite ?

J'espère que ce tutoriel vous a aidé pour être au point avec JavaFX et qu'à partir de là, vous serez capable d'écrire votre propre projet JavaFX.

Tout retour sera le bienvenu. N'hésitez pas à écrire un commentaire si vous avez des suggestions ou si vous avez des questions sur des points qui ne vous paraitraient pas clairs.


##### D'autres articles que vous pourriez trouver intéressants :

* [JavaFX Dialogs](/blog/javafx-8-dialogs/)
* [JavaFX Date Picker](/blog/javafx-8-date-picker/)
* [JavaFX Event Handling Examples](/blog/javafx-8-event-handling-examples/)
* [JavaFX TableView Sorting and Filtering](/blog/javafx-8-tableview-sorting-filtering/)
* [JavaFX TableView Cell Renderer](/blog/javafx-8-tableview-cell-renderer/)


