package de.hfu;

import java.util.Scanner;

/** 
 * Das ist meine Mainklasse
 * 
 * @author Philipp Oeschger
 * @version 1.0
 **/

public class MeineHauptklasse {
	
	/**
	 *  Hier ist meine Main-Methode 
	 *  
	 * @param args Kommandozeilenparameter
	 **/
	public static void main(String[] args) {
		
		/**
		 * Hier wird der Scanner initialisiert 
		 **/
		Scanner eingabewert = new Scanner(System.in);
		
		/**
		 *  Auslesen der Scanner Eingabe 
		 **/
		String value= eingabewert.nextLine();
		
		/**
		 *  Ausgabe in Großbuchstaben
		 **/
		System.out.println(value.toUpperCase());

	}

}
