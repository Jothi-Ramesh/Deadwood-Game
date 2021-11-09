import java.util.Scanner;

public class Gsystem extends LocationManager {


    // Field for keeping track of the current day in the Game
    private int currentDay;
    int endDay =0;
    private String[] playerLocationList;

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
    }
}