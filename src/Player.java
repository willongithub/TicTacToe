/**
 * Created by: Siqi Wu (ID 750892)
 * On: May 25, 2016
 * Comment: This is Player Class from Project C - TicTacToe Game System Ver3.0
 */

import java.io.Serializable;

public abstract class Player implements Serializable {	
	private String userName;
	private String familyName;
	private String givenName;
	private int gamePlayed;
	private int gameWon;
	private int gameDrawn;
	public int validFlag = 0;

	public Player() {
		userName = " ";
		familyName = " ";
		givenName = " ";
		gamePlayed = 0;
		gameWon = 0;
		gameDrawn = 0;
	}

	public Player(String uName, String fName, String gName) {
		if (uName == null) {
			System.out.println("Invalid username!");
			return;
		}
		else
			userName = uName;
		if (fName == null) {
			System.out.println("Invalid family name!");
			return;
		}
		else
			familyName = fName;
		if (gName.equals(null)) {
			System.out.println("Invalid given name!");
			return;
		}
		else
			givenName = gName;
		gamePlayed = 0;
		gameWon = 0;
		gameDrawn = 0;
		validFlag = 1;
	}

	public void deletePlayer() {
		this.validFlag = 0;
	}

	public String getUserName() {
		return userName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void editPlayer(String fName, String gName) {
		this.familyName = fName;
		this.givenName = gName;
	}

	public void copyPlayer(Player tempPlayer) {
		this.userName = tempPlayer.userName;
		this.familyName = tempPlayer.familyName;
		this.givenName = tempPlayer.givenName;
		this.gamePlayed = tempPlayer.gamePlayed;
		this.gameWon = tempPlayer.gameWon;
		this.gameDrawn = tempPlayer.gameDrawn;
		this.validFlag = 1;
	}

	public void resetStats() {
		this.gamePlayed = 0;
		this.gameWon = 0;
		this.gameDrawn = 0;
	}

	public void dispStats() {
		String uName = this.userName;
		String fName = this.familyName;
		String gName = this.givenName;
		int gPlayed = this.gamePlayed;
		int gWon = this.gameWon;
		int gDrawn = this.gameDrawn;
		System.out.print(uName + "," + fName + "," + gName + ",");
		System.out.println(gPlayed + " games," + gWon + " wins," + gDrawn + " draws");
	}

	public double getRatioWinning() {
		double tempRatio = 0;
		double gPlayed = this.gamePlayed;
		double gWon = this.gameWon;
		if (gamePlayed != 0) {
			tempRatio = gWon/gPlayed*100;
			return tempRatio;
		}
		else
			return tempRatio;
	}

	public double getRatioDrawing() {
		double tempRatio = 0;
		double gPlayed = this.gamePlayed;
		double gDrawn = this.gameDrawn;
		if (gamePlayed != 0) {
			tempRatio = gDrawn/gPlayed*100;
			return tempRatio;
		}
		else
			return tempRatio;
	}

	public int getGamePlayed() {
		return gamePlayed;
	}

	public int getGameWon() {
		return gameWon;
	}

	public int getGameDrawn() {
		return gameDrawn;
	}

	public void updateStats(String statsOfGamePlayed) {
		gamePlayed++; //when "lose", gamePlayed increases without other action.
		if (statsOfGamePlayed.equals("win")) {
			gameWon++;
		}
		if (statsOfGamePlayed.equals("draw")) {
			gameDrawn++;
		}
	}
	
	public abstract Move makeMove(char[][] gameBoard);
	
}