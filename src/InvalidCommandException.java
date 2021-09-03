/**
 * Created by: Siqi Wu (ID 750892)
 * On: May 14, 2016
 * Comment: This is InvalidCommandException Class from Project C - TicTacToe Game System Ver3.0
 */

public class InvalidCommandException extends Exception{
	
	public InvalidCommandException() {
		super("This is not a valid command!");
	}
	
	public InvalidCommandException(String message) {
		super(message);
	}
}