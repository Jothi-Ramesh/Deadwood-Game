import java.util.Scanner;

public class Gsystem extends LocationManager {


    // Field for keeping track of the current day in the Game
    private int currentDay;
    int endDay =0;
    private String[] playerLocationList;
    private static boolean gameEnded = false;

    // Create a System object corresponding to the amount of players in the game
    public Gsystem(int players) {
        int startCred = 0;
        int startRank =1;
        if(players <4 && players > 1){
            endDay = 3;
        }
        else if(players == 5){
            startCred = 2;
        }
        else if(players == 6){
            startCred = 4;
        }
        else if(players > 5){
            startRank = 2;
        }
        Scanner reader = new Scanner(System.in);
        Player[] Gplayers = new Player[];
        for(int i = 0; i<players; i++) {
            System.out.println("What is this players name?");
            String name = reader.nextLine();
            Gplayers[i] = new Player(0, startCred, name, i, startRank);
        }
        reader.close();
        //time to build board
        String BOARDFILE = "board.xml";
    }
    public Gsystem() {
    }

    // Upates in-game day (and ends gane )
    public void updateDay(int currentDay) {
        this.currentDay = currentDay + 1;
        if(this.currentDay == 4 && playerCount < 4) {
            endGame();
        } else if(this.currentDay == 5) {
            endGame();
        }
    }

    // Ends the game
    private void endGame() {
        // Needs implementation for determining winner of game
    	gameEnded = true;
        System.exit(0);
    }
    // Checks for funds of a player to see if there is sufficient criterion for a upgrade
    public void checkFunds(Player playerToCheck, int desiredRank, String currency) {
        if(currency.equals("Dollar")) {
            if(desiredRank == 2) {
                if(playerToCheck.getMoney() >= 4) {
                    awardRank(playerToCheck, desiredRank, currency);
                } else {
                    System.out.println("Player does not have sufficient funds");
                }
            }
            if(desiredRank == 3) {
                if(playerToCheck.getMoney() >= 10) {
                    awardRank(playerToCheck, desiredRank, currency);
                } else {
                    System.out.println("Player does not have sufficient funds");
                }
            }
            if(desiredRank == 4) {
                if(playerToCheck.getMoney() >= 18) {
                    awardRank(playerToCheck, desiredRank, currency);
                } else {
                    System.out.println("Player does not have sufficient funds");
                }
            }
            if(desiredRank == 5) {
                if(playerToCheck.getMoney() >= 28) {
                    awardRank(playerToCheck, desiredRank, currency);
                } else {
                    System.out.println("Player does not have sufficient funds");
                }
            }
            if(desiredRank == 6) {
                if(playerToCheck.getMoney() >= 40) {
                    awardRank(playerToCheck, desiredRank, currency);
                } else {
                    System.out.println("Player does not have sufficient funds");
                }
            }
        }
        else if(currency.equals("Credit")) {
            if(desiredRank == 2) {
                if(playerToCheck.getCredits() >= 5) {
                    awardRank(playerToCheck, desiredRank, currency);
                } else {
                    System.out.println("Player does not have sufficient funds");
                }
            }
            if(desiredRank == 3) {
                if(playerToCheck.getCredits() >= 10) {
                    awardRank(playerToCheck, desiredRank, currency);
                } else {
                    System.out.println("Player does not have sufficient funds");
                }
            }
            if(desiredRank == 4) {
                if(playerToCheck.getCredits() >= 15) {
                    awardRank(playerToCheck, desiredRank, currency);
                } else {
                    System.out.println("Player does not have sufficient funds");
                }
            }
            if(desiredRank == 5) {
                if(playerToCheck.getCredits() >= 20) {
                    awardRank(playerToCheck, desiredRank, currency);
                } else {
                    System.out.println("Player does not have sufficient funds");
                }
            }
            if(desiredRank == 6) {
                if(playerToCheck.getCredits() >= 25) {
                    awardRank(playerToCheck, desiredRank, currency);
                } else {
                    System.out.println("Player does not have sufficient funds");
                }
            }
        } else {
            System.out.println("Invalid currency for payment");
        }

    }

    public void awardRank(Player playerToAward, int newRank, String currency) {
        playerToAward.setRank(newRank);
        if(currency.equals("Dollar")) {
            if(newRank == 2) {
                playerToAward.setMoney(playerToAward.getMoney() - 4);
                playerToAward.setRank(2);
            }
            if(newRank == 3) {
                playerToAward.setMoney(playerToAward.getMoney() - 10);
                playerToAward.setRank(3);
            }
            if(newRank == 4) {
                playerToAward.setMoney(playerToAward.getMoney() - 18);
                playerToAward.setRank(4);
            }
            if(newRank == 5) {
                playerToAward.setMoney(playerToAward.getMoney() - 28);
                playerToAward.setRank(5);
            }
            if(newRank == 6) {
                playerToAward.setMoney(playerToAward.getMoney() - 40);
                playerToAward.setRank(6);
            }
        }
        else if(currency.equals("Credit")) {
            if(newRank == 2) {
                playerToAward.setCredits(playerToAward.getCredits() - 5);
                playerToAward.setRank(2);
            }
            if(newRank == 3) {
                playerToAward.setCredits(playerToAward.getCredits() - 10);
                playerToAward.setRank(3);
            }
            if(newRank == 4) {
                playerToAward.setCredits(playerToAward.getCredits() - 15);
                playerToAward.setRank(4);
            }
            if(newRank == 5) {
                playerToAward.setCredits(playerToAward.getCredits() - 20);
                playerToAward.setRank(5);
            }
            if(newRank == 6) {
                playerToAward.setCredits(playerToAward.getCredits() - 25);
                playerToAward.setRank(6);
            }
        }
    }

    // Method to move all players in the game to Trailers (when a new game starts or a new day occurs)
    private void moveAllPlayersToTrailers() {

    }
    
    // MAIN METHOD
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.println("Welcome to Deadwood!");
    	System.out.print("Please enter how many people will be playing: ");
    	int people = scan.nextInt();
    	while(people < 2 || people > 8) {
    		System.out.println("Sorry, there can only be 2-8 players!");
    		System.out.print("Please enter how many people will be playing: ");
        	people = scan.nextInt();
    	}
    	
    	// Create a LocationManager corresponding to the amount of players
    	LocationManager theLocationManager = new LocationManager(people);
    	// Choose the correct game format based on playercount
    	if(people == 2) {
    		Player pOne = new Player(0, 0, "name", 1, 1);
    		Player pTwo = new Player(0, 0, "name", 2, 1);
    		Player[] values = {pOne, pTwo};
    		theLocationManager.listOfPlayers = values;
    	} else if(people == 3) {
    		Player pOne = new Player(0, 0, "name", 1, 1);
    		Player pTwo = new Player(0, 0, "name", 2, 1);
    		Player pThree = new Player(0, 0, "name", 3, 1);
    		Player[] values = {pOne, pTwo, pThree};
    		theLocationManager.listOfPlayers = values;
    	} else if(people == 4) {
    		Player pOne = new Player(0, 0, "name", 1, 1);
    		Player pTwo = new Player(0, 0, "name", 2, 1);
    		Player pThree = new Player(0, 0, "name", 3, 1);
    		Player pFour = new Player(0, 0, "name", 4, 1);
    		Player[] values = {pOne, pTwo, pThree, pFour};
    		theLocationManager.listOfPlayers = values;
    	} else if(people == 5) {
    		Player pOne = new Player(0, 2, "name", 1, 1);
    		Player pTwo = new Player(0, 2, "name", 2, 1);
    		Player pThree = new Player(0, 2, "name", 3, 1);
    		Player pFour = new Player(0, 2, "name", 4, 1);
    		Player pFive = new Player(0, 2, "name", 5, 1);
    		Player[] values = {pOne, pTwo, pThree, pFour, pFive};
    		theLocationManager.listOfPlayers = values;
    	} else if(people == 6 ) {
    		Player pOne = new Player(0, 4, "name", 1, 1);
    		Player pTwo = new Player(0, 4, "name", 2, 1);
    		Player pThree = new Player(0, 4, "name", 3, 1);
    		Player pFour = new Player(0, 4, "name", 4, 1);
    		Player pFive = new Player(0, 4, "name", 5, 1);
    		Player pSix = new Player(0, 4, "name", 6, 1);
    		Player[] values = {pOne, pTwo, pThree, pFour, pFive, pSix};
    		theLocationManager.listOfPlayers = values;    		
    	} else if(people == 7) {
    		Player pOne = new Player(0, 0, "name", 1, 2);
    		Player pTwo = new Player(0, 0, "name", 2, 2);
    		Player pThree = new Player(0, 0, "name", 3, 2);
    		Player pFour = new Player(0, 0, "name", 4, 2);
    		Player pFive = new Player(0, 0, "name", 5, 2);
    		Player pSix = new Player(0, 0, "name", 6, 2);
    		Player pSeven = new Player(0, 0, "name", 7, 2);
    		Player[] values = {pOne, pTwo, pThree, pFour, pFive, pSix, pSeven};
    		theLocationManager.listOfPlayers = values;
    	} else {
    		Player pOne = new Player(0, 0, "name", 1, 2);
    		Player pTwo = new Player(0, 0, "name", 2, 2);
    		Player pThree = new Player(0, 0, "name", 3, 2);
    		Player pFour = new Player(0, 0, "name", 4, 2);
    		Player pFive = new Player(0, 0, "name", 5, 2);
    		Player pSix = new Player(0, 0, "name", 6, 2);
    		Player pSeven = new Player(0, 0, "name", 7, 2);
    		Player pEight = new Player(0, 0, "name", 8, 2);
    		Player[] values = {pOne, pTwo, pThree, pFour, pFive, pSix, pSeven, pEight};
    		theLocationManager.listOfPlayers = values;
    	}
    	// moveAllPlayersToTrailers();
    	
    	// create a variable to keep track of the total turns, we will also use this as a mechanism to determine whos turn it is by using modular arithmetic!
    	int turnCounter = 0;
    	while(!gameEnded) {  		
	    	int playerTurn = turnCounter % people + 1;
	    	System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or act \'a\' or rehease \'r\'");
	    	char playerSelection = scan.next().charAt(0);
	    	// prompt again if player selection is not valid
	    	while(!(playerSelection == 'm' || playerSelection == 'w' || playerSelection == 'a' || playerSelection == 'r')) {
	    		System.out.println("Invalid selection, please choose again!");
	    		System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or act \'a\' or rehease \'r\'");
		    	playerSelection = scan.next().charAt(0);
	    	}
	    	// the current player object can be referenced by theLocationManager.listOfPlayers[playerTurn - 1]	    
	    	while(playerSelection == 'm' && !theLocationManager.listOfPlayers[playerTurn - 1].role.equals("no")) {
	    		System.out.println("You cannot move while you currently have a role.");
	    		System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or act \'a\' or rehease \'r\'");
		    	playerSelection = scan.next().charAt(0);
	    	}
	    	while(playerSelection == 'w' && !theLocationManager.listOfPlayers[playerTurn - 1].role.equals("no")) {
	    		System.out.println("You cannot work while you already have a role!");
	    		System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or act \'a\' or rehease \'r\'");
		    	playerSelection = scan.next().charAt(0);
	    	}
	    	while(playerSelection == 'a' && theLocationManager.listOfPlayers[playerTurn - 1].role.equals("no")) {
	    		System.out.println("You need to have a role in order to act");
	    		System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or act \'a\' or rehease \'r\'");
		    	playerSelection = scan.next().charAt(0);
	    	}
	    	while(playerSelection == 'r' && theLocationManager.listOfPlayers[playerTurn - 1].role.equals("no")) {
	    		System.out.println("You need to have a role in order to rehearse");
	    		System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or act \'a\' or rehease \'r\'");
		    	playerSelection = scan.next().charAt(0);
	    	}
	    	
    	
	    	turnCounter++;
    	}
    	
    	
    	
    	
    }
}