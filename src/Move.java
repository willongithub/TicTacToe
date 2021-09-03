/**
 * Created by: Siqi Wu (ID 750892)
 * On: May 25, 2016
 * Comment: This is Move Class from Project C - TicTacToe Game System Ver3.0
 */

public class Move {
	private int rowOfMove;
	private int columnOfMove;

	public Move() {
		rowOfMove = 0;
		columnOfMove = 0;
	}

	public Move(int tempRow, int tempColumn) {
		rowOfMove = tempRow;
		columnOfMove = tempColumn;
	}
	
	public void setRow(int tempRow) {
		rowOfMove = tempRow;
	}
	
	public void setColumn(int tempColumn) {
		columnOfMove = tempColumn;
	}
	
	public int getRow() {
		return rowOfMove;
	}
	
	public int getColumn() {
		return columnOfMove;
	}
	
}
