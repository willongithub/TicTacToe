/**
 * Created by: Siqi Wu (ID 750892)
 * On: May 14, 2016
 * Comment: This is InvalidArgumentException Class from Project C - TicTacToe Game System Ver3.0
 */

public class InvalidArgumentException extends Exception{
	
	public InvalidArgumentException() {
		super("Incorrect number of arguments supplied to command.");
	}
	
	public InvalidArgumentException(String message) {
		super(message);
	}
}