/**
 * Created by: Siqi Wu (ID 750892)
 * On: May 25, 2016
 * Comment: This is AIPlayer Class from Project C - TicTacToe Game System Ver3.0
 */

import java.io.Serializable;

public class AIPlayer extends Player implements Serializable {

	public AIPlayer() {
		super();
	}

	public AIPlayer(String uName, String fName, String gName) {
		super(uName, fName, gName);
	}

	@Override
	public Move makeMove(char[][] gameBoard) {
		final int BOARDSIZE = 3;
		Move aiMove = new Move();
		System.out.printf("%s's move:", getGivenName());
		System.out.println();
		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				if (gameBoard[i][j] == ' ') {
					aiMove.setRow(i);
					aiMove.setColumn(j);
					return aiMove;
				}
			}
		}
		System.out.println("All the cell has been occupied.");
		return null;
	}

}
