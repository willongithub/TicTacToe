/**
 * Created by: Siqi Wu (ID 750892)
 * On: May 14, 2016
 * Comment: This is GameManager Class from Project C - TicTacToe Game System Ver3.0
 */

public class GameManager {		
	// this is a game board of size 3*3
	private char[][] cellsArray = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
	private final int BOARDSIZE = 3;
	private final int NONE = 0;
	private final int PLAYERONEWIN = 1;
	private final int PLAYERTWOWIN = 2;
	private final char X = 'X';
	private final char O = 'O';

	public int playGame(Player playerOne, Player playerTwo) {
		int moveSwap = 0; //0 indicates player One's move while 1 indicates player Two's move
		int roundCount = 0;	//count the round for the game
		final int GAMEDRAW = 9;  //after 9 rounds without winner, there should be a draw
		final int DRAW = 3;
		String playerGivenNameOne = playerOne.getGivenName();
		String playerGivenNameTwo = playerTwo.getGivenName();
		int stateOfGame = getStateOfGame();
		
		//gaming loop. While the state is no winner, game continues
		while (stateOfGame == NONE) {	
			if (roundCount == GAMEDRAW) {
				printGrid();
				stateOfGame = DRAW;
				System.out.println("Game over. It was a draw!");
				System.out.printf("%n");
				System.out.print(">");
				break;
			}
			printGrid();
			
			//read input for each player and get the game state after each move
			if (moveSwap == 0) { //player one's move
				Move tempMove = playerOne.makeMove(cellsArray);
				int moveRow = tempMove.getRow();
				int moveColumn = tempMove.getColumn();
				cellsArray[moveRow][moveColumn] = O;
				moveSwap = 1; //swap to player two after a valid move
				stateOfGame = getStateOfGame(); //test game state after a valid move
				roundCount++;
			}
			else { //player two's move
				Move tempMove = playerTwo.makeMove(cellsArray);
				int moveRow = tempMove.getRow();
				int moveColumn = tempMove.getColumn();
				cellsArray[moveRow][moveColumn] = X;
				moveSwap = 0; //swap to player one after a valid move
				stateOfGame = getStateOfGame(); //test game state after a valid move
				roundCount++;
			}				
		}

		//when there is a winner, while loop ends and it print out the result
		if (stateOfGame == PLAYERONEWIN) {
			printGrid();
			System.out.printf("Game over. %s won!", playerGivenNameOne);
			System.out.println();
			System.out.printf("%n");
			System.out.print(">");
		}
		if (stateOfGame == PLAYERTWOWIN) {
			printGrid();
			System.out.printf("Game over. %s won!", playerGivenNameTwo);
			System.out.println();
			System.out.printf("%n");
			System.out.print(">");
		}
		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				cellsArray[i][j] = ' ';
			}
		}
		return stateOfGame;
	}

	//print out the game grid for current state
	private void printGrid() {
		System.out.printf("%s|%s|%s", cellsArray[0][0], cellsArray[0][1], cellsArray[0][2]);
		System.out.println("\n-----");
		System.out.printf("%s|%s|%s", cellsArray[1][0], cellsArray[1][1], cellsArray[1][2]);
		System.out.println("\n-----");
		System.out.printf("%s|%s|%s", cellsArray[2][0], cellsArray[2][1], cellsArray[2][2]);
		System.out.println();		
	}
	
	//get the state of current game
	private int getStateOfGame() {
		int gameState = NONE;
		int oCount = 0; //count the number of "O"s for evaluation
		int xCount = 0; //count the number of "X"s for evaluation
		final int WIN = 3; //3 consecutive "O" or "X" lead to winning

		//evaluate winning condition horizontally
		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				if (cellsArray[i][j] == O) {
					oCount++;
				}
				if (cellsArray[i][j] == X) {
					xCount++;
				}	
			}
			if (oCount == WIN) {
				gameState = PLAYERONEWIN;
			}
			if (xCount == WIN) {
				gameState = PLAYERTWOWIN;
			}
			oCount = 0;
			xCount = 0;	
		}
		
		//evaluate winning condition vertically
		for (int j = 0; j < BOARDSIZE; j++) {
			for (int i = 0; i < BOARDSIZE; i++) {
				if (cellsArray[i][j] == O) {
					oCount++;
				}
				if (cellsArray[i][j] == X) {
					xCount++;
				}
			}
			if (oCount == WIN) {
				gameState = PLAYERONEWIN;
			}
			if (xCount == WIN) {
				gameState = PLAYERTWOWIN;
			}
			oCount = 0;
			xCount = 0;
		}
		
		//evaluate winning condition diagonally and anti-diagonally
		if (cellsArray[1][1] == O) {
			if (cellsArray[0][0] == cellsArray[2][2] && cellsArray[0][0] == O) {
				gameState = PLAYERONEWIN;
			}
			if (cellsArray[0][2] == cellsArray[2][0] && cellsArray[0][2] == O) {
				gameState = PLAYERONEWIN;
			}
		}
		if (cellsArray[1][1] == X) {
			if (cellsArray[0][0] == cellsArray[2][2] && cellsArray[0][0] == X) {
				gameState = PLAYERTWOWIN;
			}
			if (cellsArray[0][2] == cellsArray[2][0] && cellsArray[0][2] == X) {
				gameState = PLAYERTWOWIN;
			}
		}
		return gameState;
	}
		
}
