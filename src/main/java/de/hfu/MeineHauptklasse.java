package de.hfu;

import java.util.Scanner;

public class MeineHauptklasse {

	public static void main(String[] args) {
		
		Scanner eingabewert = new Scanner(System.in);
		String value= eingabewert.nextLine();
		System.out.println(value.toUpperCase());

	}

}
