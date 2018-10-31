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
		int player =1;
		
		printBoard(playBoard);
		
		
		
		playBoard = placeStone(0,0, player, playBoard);
		printBoard(playBoard);
		
		player = switchPlayer(player);
		playBoard = placeStone(1,0, player, playBoard);
		printBoard(playBoard);
		
		player = switchPlayer(player);
		playBoard = placeStone(1,1, player, playBoard);
		printBoard(playBoard);

	}
	
	// print the Tic Tac Toe game board
	private static void printBoard(char [][] playBoard) {
		int len = playBoard.length;
		System.out.println("Tic Tac Toe");
		for(int line=0; line<len; line++) {
			System.out.println(playBoard[line]);
		}
		System.out.println();
	}

	// switch play 1 and 2
	private static int switchPlayer(int player) {
		switch(player) {
		case(1): {player = 2; break;}
		case(2): {player =1; break;}
		}
		return player;
	}

	// place stone at position x, y. Player 1's mark ='O', Player 2's mark='X'
	private static char [][] placeStone(int x, int y, int player, char [][] playBoard) {
		char mark='-';
		switch(player) {
		case(1): {mark='O';break;}
		case(2): {mark='X';break;}
		}
		playBoard[x][y] = mark;
		return playBoard;
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
