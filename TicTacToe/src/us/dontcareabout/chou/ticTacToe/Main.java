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
		
		while(true) {
			System.out.println("Player " + player +" input");
			int value = getInputNumber();
			int [] pos = getPosition(value);
			System.out.println();
//			System.out.println(pos[0] + "," + pos[1]);
			if(placeStone(pos[0],pos[1], player, playBoard)) {
				player=switchPlayer(player);
				printBoard(playBoard);
				
			}
		}
		

	}
	
	// print the Tic Tac Toe game board
	private static void printBoard(char [][] playBoard) {
		int len = playBoard.length;
		System.out.println("Tic Tac Toe");
		for(int line=0; line<len; line++) {
			System.out.println(playBoard[line]);
		}
//		System.out.println();
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
	// return true if the position x, y is empty where the stone can be placed.
	private static boolean placeStone(int x, int y, int player, char [][] playBoard) {
		char mark='-';
		switch(player) {
		case(1): {mark='O';break;}
		case(2): {mark='X';break;}
		}
		
		if(playBoard[x][y] =='-') {
			playBoard[x][y] = mark;
			return true;
		}
		else {
			System.out.println("The position " + x + ", " + y + " is not available");
			return false;
		}
	}
	
	// get position x, y from numpad.
	private static int [] getPosition(int number) {
		int x=10, y=10;
		switch(number) {
			case(1): {x=2; y=0; break;}
			case(2): {x=2; y=1; break;}
			case(3): {x=2; y=2; break;}
			case(4): {x=1; y=0; break;}
			case(5): {x=1; y=1; break;}
			case(6): {x=1; y=2; break;}
			case(7): {x=0; y=0; break;}
			case(8): {x=0; y=1; break;}
			case(9): {x=0; y=2; break;}
		}
		int [] pos= {x, y};
		return pos;
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
