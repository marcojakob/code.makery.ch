+++
title = "Lösungen"
date = 2012-10-03
updated = 2014-03-08
prettify = true
comments = true
commentsIdentifier = "/library/greenfoot-kara/de/chapter4-solutions/"
aliases = [ 
  "/library/greenfoot-kara/de/chapter4-solutions/" 
]

pagingName = "<i class=\"fa fa-fw fa-check-square-o\"></i>"
sidebarName = "<i class=\"fa fa-fw fa-check-square-o\"></i> Lösungen"

[[sidebars]]
header = "Downloads"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-archive-o\"></i> scenarios-chapter-4-solutions.zip"
link = "https://github.com/marcojakob/greenfoot-kara/releases/download/v2.1.1/scenarios-chapter-4-solutions.zip"
[[sidebars.items]]
text = "<i class=\"fa fa-fw fa-file-word-o\"></i> Seite als Word-Datei"
link = "/library/convert-web-page-to-word/"
+++

#### <i class="fa fa-check-square-o"></i> LÖSUNG AUFGABE 4.01 bis 4.09

<pre class="prettyprint lang-java">
public class MyKara extends KaraSokoban {
	
    int counter = 0;

	public void act() {
		String key = getKey();

		if (key.equals("right")) {
			setDirectionRight();
			tryToMove();
		}

		if (key.equals("down")) {
			setDirectionDown();
			tryToMove();
		}

		if (key.equals("left")) {
			setDirectionLeft();
			tryToMove();
		}

		if (key.equals("up")) {
			setDirectionUp();
			tryToMove();
		}
	}

	/**
	 * Kara macht einen Schritt. Diese Methode schaut zuerst, ob sich Kara
	 * bewegen kann oder ob er zuerst noch einen Pilz schieben muss.
	 */
	public void tryToMove() {
		if (!treeFront()) {
			if (mushroomFront()) {
				if (canPushMushroom()) {
					move();
					counter++;
					setNumberOfMoves(counter);
				}
			} else {
				move();
				counter++;
				setNumberOfMoves(counter);
			}

			if (testLevelComplete()) {
				saveHighscore();
				levelComplete();
			}
		}
	}

	/**
	 * Diese Methode behandelt das Speichern der Highscore.
	 */
	public void saveHighscore() {
		// Testen, ob in den Top 3.
		if (isHighscoreTop3(counter)) {
			// Ist in Top 3 --> Hinzufügen zur Highscore.
			addHighscoreEntry(counter);
		}
	}
}
</pre>