package us.dontcareabout.chou.ticTacToe;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		char [][] playBoard = {{'-','-','-'},{'-','-','-'},{'-','-','-'}};

		printBoard(playBoard);
	}

	// print TicTacToe board
	public static void printBoard( char [][] playBoard) {
	    int len = playBoard.length;
	    for(int idx=0; idx<len; idx++) {
	        System.out.println(playBoard[idx]);
        }
    }

	@SuppressWarnings("resource")
	private static int getInputNumber() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}

	private static void callMethod(int value) {
		for (int i = 0; i < value / 2; i++) {
			System.out.print("*");
		}

		System.out.println();
	}
}
