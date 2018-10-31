package us.dontcareabout.chou.ticTacToe;


import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
//		int value = getInputNumber();
//
//		for (int i = 0; i < value; i++) {
//			if (i % 2 != 0) { continue; }
//			callMethod(i);
//		}
		
		// init empty board
		char [][] playBoard = {{'-', '-','-'},{'-', '-','-'},{'-', '-','-'}};
		
		printBoard(playBoard);


	}
	
	private static void printBoard(char [][] playBoard) {
		int len = playBoard.length;
		System.out.println("Tic Tac Toe");
		for(int line=0; line<len; line++) {
			System.out.println(playBoard[line]);
		}
	}

	private static int getInputNumber() {
		Scanner scanner = new Scanner(System.in);
		int result = scanner.nextInt();
		scanner.close();
		return result;
	}
	

	private static void callMethod(int value) {
		for (int i = 0; i < value / 2; i++) {
			System.out.print("*");
		}

		System.out.println();
	}
}
