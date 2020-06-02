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
		

		Scanner eingabewert = new Scanner(System.in);
		
		System.out.println("Geben sie eine Zeichenkette ein");
		String value= eingabewert.next();
		String großbuchstaben= value.toUpperCase();
		System.out.println(großbuchstaben);

	}

}
