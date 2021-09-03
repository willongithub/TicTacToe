/**
 * Created by: Siqi Wu (ID 750892)
 * On: May 25, 2016
 * Comment: This is HumanPlayer Class from Project C - TicTacToe Game System Ver3.0
 */

import java.io.Serializable;

public class HumanPlayer extends Player implements Serializable {	

	public HumanPlayer() {
		super();
	}

	public HumanPlayer(String uName, String fName, String gName) {		
		super(uName, fName, gName);
	}

	@Override
	public Move makeMove(char[][] gameBoard) {
		int moveValid = 0;
		int moveRow = 0;
		int moveColumn = 0;
		final int BOARDSIZE = 3;
		Move humanMove = new Move();
		while (moveValid == 0) {
			System.out.printf("%s's move:", getGivenName());
			System.out.println();
			String[] moveInput = TicTacToe.keyboardInput.nextLine().split(" ");
			moveRow = Integer.valueOf(moveInput[0]);
			moveColumn = Integer.valueOf(moveInput[1]);
			if (moveRow<0 || moveRow>(BOARDSIZE-1) || moveColumn<0 || moveColumn>(BOARDSIZE-1)) {
				System.out.print("Invalid move. ");
				System.out.println("You must place at a cell within {0,1,2} {0,1,2}.");
			}
			else if (gameBoard[moveRow][moveColumn] != ' ') {
				System.out.print("Invalid move. ");
				System.out.println("The cell has been occupied.");
			}
			else
				moveValid = 1;
		}
		humanMove.setRow(moveRow);
		humanMove.setColumn(moveColumn);
		return humanMove;
	}
	
}
