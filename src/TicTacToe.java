/**
 * Created by: Siqi Wu (ID 750892)
 * On: May 14, 2016
 * Comment: This is TicTacToe Class from Project C - TicTacToe Game System Ver3.0
 */

import java.util.Scanner;

public class TicTacToe {	
	public static Scanner keyboardInput = new Scanner(System.in);
	private PlayerManager playerManagerInstance = new PlayerManager();
	private GameManager gameManagerInstance = new GameManager();
	
	//entry method to run the game system
	public static void main(String[] args) {	
		TicTacToe gameSystem = new TicTacToe();
		gameSystem.run();
	}
	
	public void run() {	
		System.out.println("Welcome to Tic Tac Toe!");
		System.out.printf("%n");
		System.out.print(">");
		//read the whole line of input and split into command and argument
		final int PLAYERONEWIN = 1;
		final int PLAYERTWOWIN = 2;
		final int NOBODYWIN = 3;
		final String WIN = "win";
		final String LOSE = "lose";
		final String DRAW = "draw";
		final int HUMAN = 0;
		final int AI = 1;
		final int AAI = 2;
		String[] command; //commands from keyboard
		String[] argument; //arguments from keyboard
		String tempCommand; //get y/n confirmation command
		command = TicTacToe.keyboardInput.nextLine().split(" ");
		
		while (!command[0].equals("exit")) {
			try {				
				if (command[0].equals("addplayer")) {
					final int ARGNUM = 3; //number of arg for addplayer
					argument = command[1].split(",");
					if (argument.length < ARGNUM || command.length < 2) {
						throw new InvalidArgumentException();
					}
					String uName = argument[0];
					String fName = argument[1];
					String gName = argument[2];
					playerManagerInstance.addPlayer(uName, fName, gName, HUMAN);
				} else if (command[0].equals("addaiplayer")) {
					final int ARGNUM = 3; //number of arg for addaiplayer
					argument = command[1].split(",");
					if (argument.length < ARGNUM || command.length < 2) {
						throw new InvalidArgumentException();
					}
					String uName = argument[0];
					String fName = argument[1];
					String gName = argument[2];
					playerManagerInstance.addPlayer(uName, fName, gName, AI);
				} else if (command[0].equals("addadvancedaiplayer")) {
					final int ARGNUM = 3; //number of arg for addadvancedaiplayer
					argument = command[1].split(",");
					if (argument.length < ARGNUM || command.length < 2) {
						throw new InvalidArgumentException();
					}
					String uName = argument[0];
					String fName = argument[1];
					String gName = argument[2];
					playerManagerInstance.addPlayer(uName, fName, gName, AAI);
				} else if (command[0].equals("removeplayer")) {
					if (!(command.length == 1)) {
						String uName = command[1];
						playerManagerInstance.removePlayer(uName);
					} else {
						System.out.print("Are you sure you want to ");
						System.out.println("remove all players? (y/n)");
						tempCommand = keyboardInput.nextLine();
						if (tempCommand.equals("y")) {
							playerManagerInstance.removePlayer();
						}
					}
				} else if (command[0].equals("editplayer")) {
					final int ARGNUM = 3; //number of arg for addplayer
					argument = command[1].split(",");
					if (argument.length < ARGNUM || command.length < 2) {
						throw new InvalidArgumentException();
					}
					String uName = argument[0];
					String fName = argument[1];
					String gName = argument[2];
					playerManagerInstance.editPlayer(uName, fName, gName);
				} else if (command[0].equals("resetstats")) {
					if (!(command.length == 1)) {
						String uName = command[1];
						playerManagerInstance.resetStats(uName);
					} else {
						System.out.print("Are you sure you want to ");
						System.out.println("reset all player statistics? (y/n)");
						tempCommand = keyboardInput.nextLine();
						if (tempCommand.equals("y")) {
							playerManagerInstance.resetStats();
						}
					}
				} else if (command[0].equals("displayplayer")) {
					if (!(command.length == 1)) {
						String uName = command[1];
						playerManagerInstance.displayPlayer(uName);
					} else
						playerManagerInstance.displayPlayer();
				} else if (command[0].equals("rankings")) {
					playerManagerInstance.displayRanking();
				} else if (command[0].equals("playgame")) {
					final int ARGNUM = 2; //number of arg for addplayer
					argument = command[1].split(",");
					if (argument.length < ARGNUM || command.length < 2) {
						throw new InvalidArgumentException();
					}
					String userNameOne = argument[0];
					String userNameTwo = argument[1];
					int resultGame;
					if (playerManagerInstance.validUsername(userNameOne)) {
						if (playerManagerInstance.validUsername(userNameTwo)) {
							Player playerOne = playerManagerInstance.getPlayer(userNameOne);
							Player playerTwo = playerManagerInstance.getPlayer(userNameTwo);
							resultGame = gameManagerInstance.playGame(playerOne, playerTwo);
							if (resultGame == NOBODYWIN) {
								playerManagerInstance.updatePlayer(userNameOne, DRAW);
								playerManagerInstance.updatePlayer(userNameTwo, DRAW);
							}
							if (resultGame == PLAYERONEWIN) {
								playerManagerInstance.updatePlayer(userNameOne, WIN);
								playerManagerInstance.updatePlayer(userNameTwo, LOSE);
							}
							if (resultGame == PLAYERTWOWIN) {
								playerManagerInstance.updatePlayer(userNameOne, LOSE);
								playerManagerInstance.updatePlayer(userNameTwo, WIN);
							}
						}
					}
				}
//				else if (command[0].equals("help")) {
//					help();
//				}
				else {
					throw new InvalidCommandException(command[0]);
				}
			} catch (InvalidArgumentException e) {
				System.out.println(e.getMessage());
				System.out.printf("%n");
				System.out.print(">");
			} catch (InvalidCommandException e) {
				System.out.println("'" + e.getMessage() + "' is not a valid command.");
				System.out.printf("%n");
				System.out.print(">");
			}
			command = keyboardInput.nextLine().split(" ");	//read again and continue the loop
		}
		if (command[0].equals("exit")) {
			playerManagerInstance.exitGame();
			System.out.println();			
			System.exit(0);
		}
	}

}
