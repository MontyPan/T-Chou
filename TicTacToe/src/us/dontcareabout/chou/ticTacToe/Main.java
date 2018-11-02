package us.dontcareabout.chou.ticTacToe;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		char [][] playBoard = {{'-','-','-'},{'-','-','-'},{'-','-','-'}};
		int player = 1;

		printBoard(playBoard);

        placeStone(0,0, player, playBoard);
        printBoard(playBoard);
        player=switchPlayer(player);

        placeStone(0,1, player, playBoard);
        printBoard(playBoard);

        placeStone(1,0, player, playBoard);
        printBoard(playBoard);
	}

	// print TicTacToe board
	private static void printBoard( char [][] playBoard) {
	    int len = playBoard.length;
	    for(int idx=0; idx<len; idx++) {
	        System.out.println(playBoard[idx]);
        }
    }

    // Switch players between 1 and 2
    private static int switchPlayer(int player) {
	    if(player == 1) {
	        player =2;
        } else if(player == 2) {
	        player =1;
        }
        return player;
    }

    // place stones on position i, j.
    // Return true if the position i, j is empty where stones can be placed.
    private static boolean placeStone(int i, int j, int player, char [][] playBoard) {
	    char marker = '-';
	    if(player == 1) {
	        marker = 'O';
        } else {
	        marker ='X';
        }

        if(playBoard[i][j]=='-') {
            playBoard[i][j] = marker;
            return true;
        } else {
            System.out.println("The position i, j is not available.");
            return false;
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
