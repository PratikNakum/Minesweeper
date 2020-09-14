package com.tag.minesweeper;

import java.util.Scanner;

public class Utility {

	static Scanner scanner = new Scanner(System.in);

	static int getInt() {
		return scanner.nextInt();
	}

	static int randomNumber(int num) {
		return (int) (Math.random() * num);
	}

	static String getString() {
		return scanner.next();
	}

	static void print(String s) {
		System.out.println(s);
	}

	static int getInt(String s) {

		System.out.println(s);
		return scanner.nextInt();

	}

	static char getChar(String string) {
		System.out.println(string);
		return scanner.next().charAt(0);
	}

}
