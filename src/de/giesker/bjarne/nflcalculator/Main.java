package de.giesker.bjarne.nflcalculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import de.giesker.bjarne.gamestuff.Game;
import de.giesker.bjarne.util.MyLinkedList;
import de.giesker.bjarne.window.Window;

public class Main {

	public static MyLinkedList<Game> allGames = new MyLinkedList<Game>();
	public static String favFontName = "Tahoma ";

	public static void main(String[] args) {
		BufferedReader reader = null;

		try {
			// Tries to open the saveFile
			reader = new BufferedReader(new FileReader("cache\\gamecache.txt"));

			// Add all prev saved games to List
			String nextLine = reader.readLine();
			if (nextLine != null) {
				for (short s = 0; s < nextLine.length() / 8; s++) {
					allGames.add(new Game(nextLine.substring(8 * s, 8 * (s + 1))));
				}
			}
			reader.close();

		} catch (FileNotFoundException e) {
			// If File does not exists create a new one

			File newFile = new File("cache\\gamecache.txt");
			try {
				newFile.createNewFile();
			} catch (IOException eFile) {
				System.err.print("Something went wrong createing the File cache\\gamecache.txt");
			}
		} catch (IOException eClose) {
			System.err.print("Something went wrong reading the File cache\\gamecache.txt");
		}

		// Open Window
		new Window();

	}

}
