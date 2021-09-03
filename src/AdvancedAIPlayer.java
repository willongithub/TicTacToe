/**
 * Created by: Siqi Wu (ID 750892)
 * On: May 26, 2016
 * Comment: This is AdvancedAIPlayer Class from Project C - TicTacToe Game System Ver3.0
 */

import java.io.Serializable;
import java.util.Random;

public class AdvancedAIPlayer extends Player implements Serializable {
	private final int BOARDSIZE = 3;

	public AdvancedAIPlayer() {
		/* do not change this method, make sure to add
		   public Player() {} to Player class as well
		*/
	}

	public AdvancedAIPlayer(String uName, String fName, String gName) {
		super(uName, fName, gName);
	}
	
	@Override
	public Move makeMove(char[][] gameBoard) {
		Move aaiMove = new Move();
		System.out.printf("%s's move:", getGivenName());
		System.out.println();
		if (!link(gameBoard).equals("0")) {
			String pos = link(gameBoard);
			aaiMove.setRow(Integer.valueOf(pos.charAt(0))-48);
			aaiMove.setColumn(Integer.valueOf(pos.charAt(1))-48);
			return aaiMove;
		} else if (gameBoard[1][1] == ' ') {
			aaiMove.setRow(1);
			aaiMove.setColumn(1);
			return aaiMove;
		} else if (getCellState(gameBoard) == 3) {
			if (isCorner(gameBoard)) {
				aaiMove = sideMove(gameBoard);
				return aaiMove;
			}
		}
		else
			aaiMove = cornerMove(gameBoard);
//		System.out.println("The Dummy AI goes wrong!");
		return aaiMove;
	}

	public int getCellState(char[][] gameBoard) {
		int markCount = 0;
		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				if (gameBoard[i][j] != ' ') {
					markCount++;
				}
			}
		}
		return markCount;
	}

	public String link(char[][] gameBoard) {
		int markCount = 0; //count the number of "O"s or "X"s for evaluation
		final int LINE = 2; //2 consecutive "O"s or "X"s need to be dealed with
		final char X = 'X';
		final char O = 'O';
		char mark;
		String pos = "0";
		int i, j;

		if (getCellState(gameBoard)%2 == 0) {
			mark = X;
		}
		else
			mark = O;

		//evaluate link condition horizontally
		for (i = 0; i < BOARDSIZE; i++) {
			for (j = 0; j < BOARDSIZE; j++) {
				if (gameBoard[i][j] == mark) {
					markCount++;
				}
			}
			if (markCount == LINE) {
				for (j = 0; j < BOARDSIZE; j++) {
					if (gameBoard[i][j] == ' ') {
						pos = String.valueOf(i)+String.valueOf(j);
					}
				}
				if (!pos.equals("0")) {
					return pos;
				}
			}
			else
				markCount = 0;
		}

		//evaluate link condition vertically
		for (j = 0; j < BOARDSIZE; j++) {
			for (i = 0; i < BOARDSIZE; i++) {
				if (gameBoard[i][j] == mark) {
					markCount++;
				}
			}
			if (markCount == LINE) {
				for (i = 0; i < BOARDSIZE; i++) {
					if (gameBoard[i][j] == ' ') {
						pos = String.valueOf(i)+String.valueOf(j);
					}
				}
				if (!pos.equals("0")) {
					return pos;
				}
			}
			else
				markCount = 0;
		}

		//evaluate link condition diagonally and anti-diagonally
		for (i = 0; i < BOARDSIZE; i++) {
			if (gameBoard[i][i] == mark) {
				markCount++;
			}
		}
		if (markCount == LINE) {
			for (i = 0; i < BOARDSIZE; i++) {
				if (gameBoard[i][i] == ' ') {
					pos = String.valueOf(i)+String.valueOf(i);
				}
			}
			if (!pos.equals("0")) {
				return pos;
			}
		}
		else
			markCount = 0;
		for (i = 0; i < BOARDSIZE; i++) {
			if (gameBoard[i][2-i] == mark) {
				markCount++;
			}
		}
		if (markCount == LINE) {
			for (i = 0; i < BOARDSIZE; i++) {
				if (gameBoard[i][2-i] == ' ') {
					pos = String.valueOf(i)+String.valueOf(2-i);
				}
			}
			if (!pos.equals("0")) {
				return pos;
			}
		}
//		else
//			markCount = 0;

		return "0";
	}

	public boolean isCorner(char[][] gameBoard) {
		if ((gameBoard[0][0] != ' ') && (gameBoard[0][0] == gameBoard[2][2])) {
			return true;
		}
		if ((gameBoard[0][2] != ' ') && (gameBoard[0][2] == gameBoard[2][0])) {
			return true;
		}
		return false;
	}

	public Move sideMove(char[][] gameBoard) {
		Move tempMove = new Move();
		Random start = new Random();
		while (true) {
			switch (start.nextInt(4)) {
				case 0:
					for (int i = 0; i < BOARDSIZE; i++) {
						if (gameBoard[i][1] == ' ') {
							tempMove.setRow(i);
							tempMove.setColumn(1);
							return tempMove;
						}
					}
				case 1:
					for (int i = BOARDSIZE; i > 0; i--) {
						if (gameBoard[i - 1][1] == ' ') {
							tempMove.setRow(i - 1);
							tempMove.setColumn(1);
							return tempMove;
						}
					}
				case 2:
					for (int i = 0; i < BOARDSIZE; i++) {
						if (gameBoard[1][i] == ' ') {
							tempMove.setRow(1);
							tempMove.setColumn(i);
							return tempMove;
						}
					}
				case 3:
					for (int i = BOARDSIZE; i > 0; i--) {
						if (gameBoard[1][i] == ' ') {
							tempMove.setRow(1);
							tempMove.setColumn(i);
							return tempMove;
						}
					}
					break;
				default:
					System.out.println("sideMove() error!");
					System.exit(0);
			}
		}
//		System.out.println("sideMove() error!");
//		System.exit(0);
//		return null;
	}

	public Move cornerMove(char[][] gameBoard) {
		Move tempMove = new Move();
		Random start = new Random();
		while (true) {
			switch (start.nextInt(4)) {
				case 0:
					if (gameBoard[0][0] == ' ') {
						tempMove.setRow(0);
						tempMove.setColumn(0);
						return tempMove;
					}
				case 1:
					if (gameBoard[0][2] == ' ') {
						tempMove.setRow(0);
						tempMove.setColumn(2);
						return tempMove;
					}
				case 2:
					if (gameBoard[2][0] == ' ') {
						tempMove.setRow(2);
						tempMove.setColumn(0);
						return tempMove;
					}
				case 3:
					if (gameBoard[2][2] == ' ') {
						tempMove.setRow(2);
						tempMove.setColumn(2);
						return tempMove;
					}
					break;
				default:
					System.out.println("cornerMove() error!");
					System.exit(0);
					break;
			}
			tempMove = sideMove(gameBoard);
			return tempMove;
		}
//		System.out.println("sideMove() error!");
//		System.exit(0);
//		return null;
	}
}
