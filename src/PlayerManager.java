/**
 * Created by: Siqi Wu (ID 750892)
 * On: May 14, 2016
 * Comment: This is PlayerManager Class from Project C - TicTacToe Game System Ver3.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class PlayerManager {
	private final int NUMOFPLAYER = 100; //number of players the system allowed
	private Player[] listPlayer = new Player[NUMOFPLAYER];
	private int count = 0;
	private final String FILENAME = "players.dat";
	
	public PlayerManager() {
		File fileObject;
		ObjectInputStream dataInput;
		try {			
			fileObject = new File(FILENAME);
			if (fileObject.exists()) {
				dataInput = new ObjectInputStream(new FileInputStream(FILENAME));
				listPlayer =  (Player[])dataInput.readObject();
				for (int i = 0; i < NUMOFPLAYER; i++) {
					count = i;
					if (listPlayer[i] == null) { //this is always false, need to be fixed
						break;
					}						
				}
			}		
		} catch (FileNotFoundException e) {
			System.out.println("1Exception thrown operating the file " + FILENAME);
			System.exit(0);
		} catch (ClassNotFoundException e) {
			System.out.println("2Exception thrown operating the file " + FILENAME);
			System.exit(0);
		} catch (EOFException e) {
			System.out.println("3Exception thrown operating the file " + FILENAME);		
			System.exit(0);
		} catch (IOException e) {
			System.out.println("4Exception thrown operating the file " + FILENAME);		
			System.exit(0);
		}
	}
	
	public void addPlayer(String uName, String fName, String gName, int aiFlag) {
		String userName;
		String familyName;
		String givenName;
		userName = uName;
		final int HUMAN = 0;
		final int AI = 1;
		final int AAI = 2;
		for (int i = 0; i < count; i++) {
			if (listPlayer[i].getUserName().equals(userName)) {
				System.out.println("The username has been used already.");
				System.out.printf("%n");
				System.out.print(">");
				return;
			}
		}
		familyName = fName;
		givenName = gName;
		if (aiFlag == HUMAN) {
			listPlayer[count] = new HumanPlayer(userName, familyName, givenName);
		} else if (aiFlag == AI) {
			listPlayer[count] = new AIPlayer(userName, familyName, givenName);
		}
		else
			listPlayer[count] = new AdvancedAIPlayer(userName, familyName, givenName);

		count++;
		System.out.printf("%n");
		System.out.print(">");
		return;
	}
	
	public void removePlayer(String tempUserName) {
		String userName = tempUserName;
		for (int i = 0; i < count; i++) {			
			if (listPlayer[i].getUserName().equals(userName)) {
				listPlayer[i].deletePlayer();
				movePlayer(listPlayer[i]);
				listPlayer[count-1].deletePlayer();
				count--;
				System.out.printf("%n");
				System.out.print(">");
				return;
			}
		}
		System.out.println("The player does not exist.");
		System.out.printf("%n");
		System.out.print(">");
		return;
	}
	
	public void movePlayer(Player tempPlayer) {
		tempPlayer.copyPlayer(tempPlayer);
	}
	
	public void removePlayer() {
		for (int i = 0; i < count; i++) {
			listPlayer[i].deletePlayer();
		}
		count = 0;
		System.out.printf("%n");
		System.out.print(">");
		return;
	}
	
	public void editPlayer(String uName, String fName, String gName) {
		String userName = uName;
		String familyName = fName;
		String givenName = gName;
		for (int i = 0; i < count; i++) {		 
			if (listPlayer[i].getUserName().equals(userName)) {
				listPlayer[i].editPlayer(familyName, givenName);
				System.out.printf("%n");
				System.out.print(">");
				return;
			}			
		}
		System.out.println("The player does not exist.");
		System.out.printf("%n");
		System.out.print(">");
		return;
	}		
	
	public void resetStats(String tempUsername) {
		String userName = tempUsername;
		for (int i = 0; i < count; i++) {		 
			if (listPlayer[i].getUserName().equals(userName)) {
				listPlayer[i].resetStats();
				return;
			}			
		}
		System.out.println("The player does not exist.");
		System.out.printf("%n");
		System.out.print(">");
		return;	
	}
	
	public void resetStats() {
		for (int i = 0; i < count; i++) {
			listPlayer[i].resetStats();
		}
		System.out.printf("%n");
		System.out.print(">");
	}
	
	public void displayPlayer(String tempUsername) {
		String userName = tempUsername;
		for (int i = 0; i < count; i++) {		 
			if (listPlayer[i].getUserName().equals(userName) && listPlayer[i].validFlag==1) {
				listPlayer[i].dispStats();
				System.out.printf("%n");
				System.out.print(">");
				return;
			}			
		}
		System.out.println("The player does not exist.");
		System.out.printf("%n");
		System.out.print(">");
		return;
	}
	
	public void displayPlayer() {
		int LISTROW = 2;
		String[][] listDisp = new String[count][LISTROW]; //temporary array for sorting
		String[][] tempListDisp = new String[1][LISTROW]; //temporary array for swap
		for (int i = 0; i < count; i++) {
			listDisp[i][0] = listPlayer[i].getUserName();
			listDisp[i][1] = String.valueOf(i); //the index of this player in listPlayer array
		}
		// sorting the player list alphabetically
		for (int i = 0; i < (count-1); i++) {
			String tempChar = String.valueOf(listDisp[i][0].charAt(0));
			String tempCharNext = String.valueOf(listDisp[i+1][0].charAt(0));
			if (tempChar.compareToIgnoreCase(tempCharNext) > 0) {
				for (int j = 0; j < LISTROW; j++) {
					tempListDisp[0][j] = listDisp[i][j];
					listDisp[i][j] = listDisp[i+1][j];
					listDisp[i+1][j] = tempListDisp[0][j];
				}
			}
		}
		for (int i = 0; i < count; i++) {
			// output using the sorted list as index to access the listPlayer array
			// which means the listPlayer[] will not change after display command
			if (listPlayer[Integer.valueOf(listDisp[i][1])].validFlag==1) {
				listPlayer[Integer.valueOf(listDisp[i][1])].dispStats();
			}
		}
		System.out.printf("%n");
		System.out.print(">");
	}
	
	//compare the Objects in the list
	public int compr(String tempValueA, String tempValueB) {
		if (Double.valueOf(tempValueA).compareTo(Double.valueOf(tempValueB)) > 0) {
			return 1;
		}
		if (Double.valueOf(tempValueA).compareTo(Double.valueOf(tempValueB)) < 0) {
			return -1;
		}
		else 
			return 0;
	}
	
	public void displayRanking() {
		final int LISTROW = 4; //number of values to be swapped
		final int OUTPUTPLAYER = 10; //number of players displayed in the ranking
		int swapFlag = 1; //flag for swapping
		String[][] listRanking = new String[count][LISTROW]; //temporary array for sorting
		String[][] tempRank = new String[1][LISTROW]; //temporary array for swap
		for (int i = 0; i < count; i++) {
			listRanking[i][0] = listPlayer[i].getUserName();
			listRanking[i][1] = String.valueOf(listPlayer[i].getRatioWinning());
			listRanking[i][2] = String.valueOf(listPlayer[i].getRatioDrawing());
			listRanking[i][3] = String.valueOf(listPlayer[i].getGamePlayed());
		}
		while (swapFlag == 1) {
			swapFlag = 0;
			for (int i = 0; i < (count-1); i++) {
				if (compr(listRanking[i][1], listRanking[i+1][1]) <= 0) {
					if (compr(listRanking[i][1], listRanking[i+1][1]) == 0) {
						if (compr(listRanking[i][2], listRanking[i+1][2]) <= 0) {
							if (compr(listRanking[i][2], listRanking[i+1][2]) == 0) {
								String chr = String.valueOf(listRanking[i][0].charAt(0));
								String chrNxt = String.valueOf(listRanking[i+1][0].charAt(0));
								if (chr.compareToIgnoreCase(chrNxt) > 0) {
									for (int j = 0; j < LISTROW; j++) {
										tempRank[0][j] = listRanking[i][j];
										listRanking[i][j] = listRanking[i+1][j];
										listRanking[i+1][j] = tempRank[0][j];
										swapFlag = 1;
									}
								}
							}
							else {
								for (int j = 0; j < LISTROW; j++) {
									tempRank[0][j] = listRanking[i][j];
									listRanking[i][j] = listRanking[i+1][j];
									listRanking[i+1][j] = tempRank[0][j];
									swapFlag = 1;
								}
							}
						}
					}
					else {
						for (int j = 0; j < LISTROW; j++) {
							tempRank[0][j] = listRanking[i][j];
							listRanking[i][j] = listRanking[i+1][j];
							listRanking[i+1][j] = tempRank[0][j];
							swapFlag = 1;
						}
					}					
				}
			}
		}
		System.out.println(" WIN  | DRAW | GAME | USERNAME");
		int dispCount = OUTPUTPLAYER;
		if (count<=OUTPUTPLAYER) {
			dispCount = count;
		}
		for (int i = 0; i < dispCount; i++) {
			double tempRatioWinning = Double.valueOf(listRanking[i][1]);
			double tempRatioDrawing = Double.valueOf(listRanking[i][2]);
			double tempGamePlayed = Double.valueOf(listRanking[i][3]);
			System.out.printf(" %3.0f", tempRatioWinning);
			System.out.print("% |");
			System.out.printf(" %3.0f", tempRatioDrawing);
			System.out.print("% |");
			System.out.printf(" %2.0f", tempGamePlayed);
			System.out.println("   | " + listRanking[i][0]);
		}
		System.out.printf("%n");
		System.out.print(">");
	}
	
	public boolean validUsername(String tempUserName) {
//		String userName = tempUserName;
		for (int i = 0; i < count; i++) {		 
			if (listPlayer[i].getUserName().equals(tempUserName)) {
				return true;
			}
		}
		System.out.println("Player does not exist.");
		System.out.printf("%n");
		System.out.print(">");
		return false;
	}

	public Player getPlayer(String tempUserName) {
		for (int i = 0; i < count; i++) {
			if (listPlayer[i].getUserName().equals(tempUserName)) {
				return listPlayer[i];
			}
		}
		System.out.println("This player is missing!");
		System.out.printf("%n");
		System.out.print(">");
		return null;
	}
	
	public void updatePlayer(String tempUserName, String tempUserStat) {
		for (int i = 0; i < count; i++) {		 
			if (listPlayer[i].getUserName().equals(tempUserName)) {
				listPlayer[i].updateStats(tempUserStat);
			}
		}
	}
	
	public void exitGame() {
		ObjectOutputStream dataOutput;
//		File fileObject;
		try {
//			fileObject = new File(FILENAME);
//			if (fileObject.exists()) {
//				fileObject.delete();
//			}
//			fileObject.createNewFile();
			dataOutput = new ObjectOutputStream(new FileOutputStream(FILENAME));
			dataOutput.writeObject(listPlayer);
			dataOutput.close();		
		} catch (IOException e) {
			System.out.println("5Exception thrown operating the file " + FILENAME);		
			System.exit(0);
		}
	}
	
}
