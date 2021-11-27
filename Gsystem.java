import java.util.Random;
import java.util.Scanner;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import javax.swing.*;

public class Gsystem extends LocationManager {


    // Field for keeping track of the current day in the Game
    private int currentDay;
    private static boolean gameEnded = false;
    public Scene[] scenesArr;
    public Card[] cardsArr;

    // Create a System object corresponding to the amount of players in the game
    public Gsystem(int players) throws ParserConfigurationException {

        //time to build board
        Document boardDoc = getDocFromFile("Deadwood-Game/board.xml");
        boardDoc.getDocumentElement().normalize();
        NodeList list = boardDoc.getElementsByTagName("set");
        Scene[] scenes = new Scene[12];
        for (int j = 0; j < list.getLength(); j++) {
            Node curScene = list.item(j);
            if (curScene.getNodeType() == Node.ELEMENT_NODE) {
                Element scene = (Element) curScene;

                String name = scene.getAttribute("name");
                NodeList neighborsList = ((Element) curScene).getElementsByTagName("neighbor");
                String[] neighbors = new String[neighborsList.getLength()];
                for (int o = 0; o < neighborsList.getLength(); o++) {
                    Node neighbor = neighborsList.item(o);
                    Element neighborE = (Element) neighbor;
                    neighbors[o] = neighborE.getAttribute("name");
                }
                NodeList takeList = ((Element) curScene).getElementsByTagName("take");
                int takes = Integer.parseInt(((Element) takeList.item(0)).getAttribute("number"));
                NodeList partList = ((Element) curScene).getElementsByTagName("part");
                int numParts = partList.getLength();
                Part[] parts = new Part[numParts];
                for(int k = 0; k < partList.getLength();k++){
                    Node partN = partList.item(k);
                    Element partE = (Element) partN;
                    String pName = partE.getAttribute("name");
                    int pLevel = Integer.parseInt(partE.getAttribute("level"));
                    String pLine = partE.getElementsByTagName("line").item(0).getTextContent();
                    parts[k] = new Part(pName, pLine, pLevel);
                }
                scenes[j] = new Scene(name, numParts, takes, neighbors, parts);
            }
        }
        String[] oNeighbors = {"Train station", "Ranch", "Secret Hideout"};
        String[] tNeighbors = {"Main Street", "Saloon", "Secret Hideout"};
        scenes[10] = new Scene("trailer", 0, 0, tNeighbors, null);
        scenes[11] = new Scene("office", 0, 0, oNeighbors, null);

        //build cards
        Document cardDoc = getDocFromFile("Deadwood-Game/cards.xml");
        cardDoc.getDocumentElement().normalize();
        NodeList cardList = cardDoc.getElementsByTagName("card");
        int cardLen = cardList.getLength();
        Card[] cards = new Card[cardLen];
        for(int i = 0; i < cardLen; i++){
            Node curCard = cardList.item(i);
            if(curCard.getNodeType() == Node.ELEMENT_NODE) {
                Element card = (Element) curCard;

                String cardName = card.getAttribute("name");
                int cardBudget = Integer.parseInt(card.getAttribute("budget"));
                NodeList sceneList = ((Element) curCard).getElementsByTagName("scene");
                int cardNum = Integer.parseInt(((Element) sceneList.item(0)).getAttribute("number"));
                String line = sceneList.item(0).getTextContent();
                NodeList partList = ((Element) curCard).getElementsByTagName("part");
                int numParts = partList.getLength();
                Part[] parts = new Part[numParts];
                for (int k = 0; k < partList.getLength(); k++) {
                    Node partN = partList.item(k);
                    Element partE = (Element) partN;
                    String pName = partE.getAttribute("name");
                    int pLevel = Integer.parseInt(partE.getAttribute("level"));
                    String pLine = partE.getElementsByTagName("line").item(0).getTextContent();
                    parts[k] = new Part(pName, pLine, pLevel);
                }
                cards[i] = new Card(cardName, line, cardNum, cardBudget, numParts, parts);
            }

        }
        cardsArr = cards;
<<<<<<< HEAD
        setCards(cards, scenes);
=======
>>>>>>> 5e1e192cd1d800de1dc6fe9fe34f80806756cf6d
        scenesArr = scenes;
        setCards();

    }

    public void setCards(Card[] cardsArr, Scene[] scenes) {
        int range = cardsArr.length;
        Random rn = new Random();
        int n = rn.nextInt(40);
        for(int j = 0; j < scenesArr.length; j++){
            n = rn.nextInt(40);
            scenesArr[j].addCard(cardsArr[n]);
        }
    }
    public Document getDocFromFile(String filename)
            throws ParserConfigurationException {
        DocumentBuilderFactory docBuildFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuild = docBuildFac.newDocumentBuilder();
        Document doc = null;
        try {
            doc = docBuild.parse(filename);
        } catch (Exception ex) {
            System.out.println("XML parse failure");
            ex.printStackTrace();
        }
        return doc;
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

    public int awardRank(Player playerToAward, int newRank, String currency) {
        playerToAward.setRank(newRank);
        if(currency.equals("Dollar")) {
            if(newRank == 2 && playerToAward.getMoney()>=4) {
                playerToAward.setMoney(playerToAward.getMoney() - 4);
                playerToAward.setRank(2);
                return 1;
            }
            if(newRank == 3 && playerToAward.getMoney()>=10) {
                playerToAward.setMoney(playerToAward.getMoney() - 10);
                playerToAward.setRank(3);
                return 1;
            }
            if(newRank == 4 && playerToAward.getMoney()>=18) {
                playerToAward.setMoney(playerToAward.getMoney() - 18);
                playerToAward.setRank(4);
                return 1;
            }
            if(newRank == 5 && playerToAward.getMoney()>=28) {
                playerToAward.setMoney(playerToAward.getMoney() - 28);
                playerToAward.setRank(5);
                return 1;
            }
            if(newRank == 6 && playerToAward.getMoney()>=40) {
                playerToAward.setMoney(playerToAward.getMoney() - 40);
                playerToAward.setRank(6);
                return 1;
            }
            else{
                System.out.println("Invalid currency! Please try again");
                return 0;
            }
        }
        else if(currency.equals("Credit")) {
            if(newRank == 2 && playerToAward.getCredits()>=5) {
                playerToAward.setCredits(playerToAward.getCredits() - 5);
                playerToAward.setRank(2);
                return 1;
            }
            if(newRank == 3 && playerToAward.getCredits()>=10) {
                playerToAward.setCredits(playerToAward.getCredits() - 10);
                playerToAward.setRank(3);
                return 1;
            }
            if(newRank == 4 && playerToAward.getCredits()>=15) {
                playerToAward.setCredits(playerToAward.getCredits() - 15);
                playerToAward.setRank(4);
                return 1;
            }
            if(newRank == 5 && playerToAward.getCredits()>=20) {
                playerToAward.setCredits(playerToAward.getCredits() - 20);
                playerToAward.setRank(5);
                return 1;
            }
            if(newRank == 6 && playerToAward.getCredits()>=25) {
                playerToAward.setCredits(playerToAward.getCredits() - 25);
                playerToAward.setRank(6);
                return 1;
            }
            else{
                System.out.println("Invalid currency! Please try again");
                return 0;
            }
        }
        else{
            return 0;
        }
    }

    // Method to move all players in the game to Trailers (when a new game starts or a new day occurs)
    private void moveAllPlayersToTrailers() {

    }
    
    // MAIN METHOD
    public static void main(String[] args) throws ParserConfigurationException {
    	Scanner scan = new Scanner(System.in);
    	
    	System.out.println("Welcome to Deadwood!");
    	System.out.print("Please enter how many people will be playing: ");
    	int people = scan.nextInt();
    	while(people < 2 || people > 8) {
    		System.out.println("Sorry, there can only be 2-8 players!");
    		System.out.print("Please enter how many people will be playing: ");
        	people = scan.nextInt();
    	}


        Player[] Gplayers = new Player[people];
    	// Choose the correct game format based on playercount
    	if(people == 2) {
    		Player pOne = new Player(0, 0, "name", 1, 1);
    		Player pTwo = new Player(0, 0, "name", 2, 1);
            Gplayers = new Player[] {pOne, pTwo};
    	} else if(people == 3) {
    		Player pOne = new Player(0, 0, "name", 1, 1);
    		Player pTwo = new Player(0, 0, "name", 2, 1);
    		Player pThree = new Player(0, 0, "name", 3, 1);
            Gplayers = new Player[] {pOne, pTwo, pThree};
    	} else if(people == 4) {
    		Player pOne = new Player(0, 0, "name", 1, 1);
    		Player pTwo = new Player(0, 0, "name", 2, 1);
    		Player pThree = new Player(0, 0, "name", 3, 1);
    		Player pFour = new Player(0, 0, "name", 4, 1);
            Gplayers = new Player[] {pOne, pTwo, pThree, pFour};
    	} else if(people == 5) {
    		Player pOne = new Player(0, 2, "name", 1, 1);
    		Player pTwo = new Player(0, 2, "name", 2, 1);
    		Player pThree = new Player(0, 2, "name", 3, 1);
    		Player pFour = new Player(0, 2, "name", 4, 1);
    		Player pFive = new Player(0, 2, "name", 5, 1);
            Gplayers = new Player[] {pOne, pTwo, pThree, pFour, pFive};
    	} else if(people == 6 ) {
    		Player pOne = new Player(0, 4, "name", 1, 1);
    		Player pTwo = new Player(0, 4, "name", 2, 1);
    		Player pThree = new Player(0, 4, "name", 3, 1);
    		Player pFour = new Player(0, 4, "name", 4, 1);
    		Player pFive = new Player(0, 4, "name", 5, 1);
    		Player pSix = new Player(0, 4, "name", 6, 1);
            Gplayers = new Player[] {pOne, pTwo, pThree, pFour, pFive, pSix};
    	} else if(people == 7) {
    		Player pOne = new Player(0, 0, "name", 1, 2);
    		Player pTwo = new Player(0, 0, "name", 2, 2);
    		Player pThree = new Player(0, 0, "name", 3, 2);
    		Player pFour = new Player(0, 0, "name", 4, 2);
    		Player pFive = new Player(0, 0, "name", 5, 2);
    		Player pSix = new Player(0, 0, "name", 6, 2);
    		Player pSeven = new Player(0, 0, "name", 7, 2);
            Gplayers = new Player[] {pOne, pTwo, pThree, pFour, pFive, pSix, pSeven};
    	} else {
    		Player pOne = new Player(0, 0, "name", 1, 2);
    		Player pTwo = new Player(0, 0, "name", 2, 2);
    		Player pThree = new Player(0, 0, "name", 3, 2);
    		Player pFour = new Player(0, 0, "name", 4, 2);
    		Player pFive = new Player(0, 0, "name", 5, 2);
    		Player pSix = new Player(0, 0, "name", 6, 2);
    		Player pSeven = new Player(0, 0, "name", 7, 2);
    		Player pEight = new Player(0, 0, "name", 8, 2);
    		Gplayers = new Player[] {pOne, pTwo, pThree, pFour, pFive, pSix, pSeven, pEight};
    	}
    	
    	// create a variable to keep track of the total turns, we will also use this as a mechanism to determine whos turn it is by using modular arithmetic!
    	int turnCounter = 0;
    	String name = scan.nextLine();
        for(int i = 0; i<people; i++) {
            System.out.println("What is this players name?");
            name = scan.nextLine();
            Gplayers[i].name = name;
        }
    	Gsystem gsys = new Gsystem(people);
        LocationManager manager = new LocationManager(people, gsys.scenesArr, Gplayers);
        manager.listOfPlayers = Gplayers;
        manager.playerLocationList=gsys.scenesArr;

    	while(!gameEnded) {  		
	    	int playerTurn = (turnCounter%people)+1;
	    	char playerSelection = 0;
	    	Player curPlayer = Gplayers[playerTurn-1];
	    	if(curPlayer.location == "trailer" || curPlayer.location == "office" || gsys.scenesArr[manager.getInd(curPlayer.location)].numRoles==0){
	    	    if(curPlayer.location== "office"){
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or upgrade \'u\'");
                    playerSelection = scan.nextLine().charAt(0);
                    // prompt again if player selection is not valid
                    while (!(playerSelection == 'm' || playerSelection=='u')) {
                        System.out.println("Invalid selection, please choose again!");
                        System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move or upgrade \'u\'");
                        playerSelection = scan.next().charAt(0);
                    }
                }
	    	    else {
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\'");
                    playerSelection = scan.nextLine().charAt(0);
                    // prompt again if player selection is not valid
                    while (!(playerSelection == 'm')) {
                        System.out.println("Invalid selection, please choose again!");
                        System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move");
                        playerSelection = scan.next().charAt(0);
                    }
                }
            }
	    	else {
                System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                playerSelection = scan.nextLine().charAt(0);
                // prompt again if player selection is not valid
                while (!(playerSelection == 'm' || playerSelection == 'w' || playerSelection == 'a' || playerSelection == 'r')) {
                    System.out.println("Invalid selection, please choose again!");
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                    playerSelection = scan.next().charAt(0);
                }
                // the current player object can be referenced by theLocationManager.listOfPlayers[playerTurn - 1]
                while (playerSelection == 'm' && !manager.listOfPlayers[playerTurn - 1].role.equals("no")) {
                    System.out.println("You cannot move while you currently have a role.");
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                    playerSelection = scan.next().charAt(0);
                }
                while (playerSelection == 'w' && (curPlayer.role.equals("yes")) || curPlayer.location == "trailer" || curPlayer.location == "office") {
                    System.out.println("You cannot work while you already have a role!");
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                    playerSelection = scan.next().charAt(0);
                }
                while (playerSelection == 'a' && manager.listOfPlayers[playerTurn - 1].role.equals("no")) {
                    System.out.println("You need to have a role in order to act");
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                    playerSelection = scan.next().charAt(0);
                }
                while (playerSelection == 'r' && manager.listOfPlayers[playerTurn - 1].role.equals("no")) {
                    System.out.println("You need to have a role in order to rehearse");
                    System.out.println("Player " + playerTurn + " it is your turn.\nPlease select move \'m\' or work \'w\' or rehearse \'r\'");
                    playerSelection = scan.next().charAt(0);
                }
            }
	    	if(playerSelection == 'u'){
                System.out.println("Welcome to the office! we hear you'd like to upgrade your player!");
                System.out.println("|Rank    |Dollars |Credits |");
                System.out.println("+--------+--------+--------+");
                System.out.println("|2       |4       |5       |");
                System.out.println("+--------+--------+--------+");
                System.out.println("|3       |10      |10      |");
                System.out.println("+--------+--------+--------+");
                System.out.println("|4       |18      |15      |");
                System.out.println("+--------+--------+--------+");
                System.out.println("|5       |28      |20      |");
                System.out.println("+--------+--------+--------+");
                System.out.println("|6       |40      |25      |");
                System.out.println("Please choose rank or 10 to cancel:");
                int choiceInt = scan.nextInt();
                System.out.println("Please choose weather to use dollars or credits (c/d)");
                String currChoice = scan.nextLine();
                boolean upgradeDone = false;
                while(!upgradeDone){
                    if(choiceInt == 10){
                        upgradeDone = true;
                    }
                    else{
                        int done = gsys.awardRank(curPlayer, choiceInt, currChoice);
                        if(done == 1){
                            upgradeDone = true;
                        }
                    }
                }

            }
	    	if(playerSelection=='r'){
	    	    if(curPlayer.getRehearseLvl()<6){
	    	        curPlayer.setRehearseLvl(curPlayer.getRehearseLvl()+1);
                }
	    	    else{
                    System.out.println("Already rehearsed to teh maximum");
                }
            }
	    	if(playerSelection == 'm'){
	    	    String location = curPlayer.location;
	    	    int indScene = manager.getInd(location);
	    	    String[] neighbors = gsys.scenesArr[indScene].neighbors;
                System.out.println("You could move to the following locations:");
                for(int j = 0; j < neighbors.length; j++){
                    System.out.print(j+". "+neighbors[j]+"  ");
                    if(gsys.scenesArr[manager.getInd(neighbors[j])].numRoles == 0){
                        System.out.print("(no roles or wrapped)");
                    }
                }
                System.out.println("\n pick a number to make the corisponding selection.");
                int moveInt = Integer.parseInt(scan.nextLine());
                manager.movePlayer(curPlayer, neighbors[moveInt]);
                System.out.println("Pick a role? y/n");
                String confirm = scan.nextLine();
                if(confirm.equals("y")){
                    System.out.println("Pick a role to fill:");
                    int locind = manager.getInd(curPlayer.location);
                    Scene curScene = gsys.scenesArr[locind];
                    Card curCard = curScene.curCard;
                    int numPartCard = curCard.numParts;
                    int numPartScene = curScene.numRoles;
                    int numParts = curScene.numRoles + curCard.numParts;
                    int ind =0;
                    System.out.print("Roles available on the board: ");
                    int i;
                    int k;
                    for(i = 0; i<numPartScene; i++){
                        if(!curScene.parts[i].taken  && curScene.parts[i].level <= curPlayer.getRank()) {
                            System.out.print(ind + ".  " + curScene.parts[i].name + "  ");
                            ind++;
                        }
                    }
                    System.out.print("\nRoles available on the card: ");
                    for(k = 0; k <numPartCard; k++){
                        if(!curCard.parts[k].taken && curCard.parts[k].level <= curPlayer.getRank()) {
                            System.out.print(ind + ".  " + curCard.parts[k].name + "  ");
                            ind++;
                        }
                    }
                    System.out.println("Please make selection by entering number next to part\n");
                    int roleInt = Integer.parseInt(scan.nextLine());
                    int permInt = roleInt;
                    if(roleInt >i){
                        roleInt -= numPartScene;
                        curCard.parts[roleInt].taken = true;
                        curPlayer.role = curCard.parts[roleInt].name;
                        curPlayer.part=curCard.parts[roleInt];
                        curPlayer.setLevel(2);

                    }
                    else {
                        curScene.parts[roleInt].taken = true;
                        curPlayer.role = curScene.parts[roleInt].name;
                        curPlayer.part = curScene.parts[roleInt];
                        curPlayer.setLevel(1);
                    }
                    curScene.filledRoles[roleInt] = curPlayer.getNum();
                }
            }

	    	if(playerSelection == 'w'){
	    	    Part p = curPlayer.part;
	    	    int ind = manager.getInd(curPlayer.location);
	    	    Card card = gsys.scenesArr[ind].curCard;
	    	    int budget = card.budget;
                int range = 6;
                Random rn = new Random();
                int n = range+1;
                int r = rn.nextInt()%n;
                if(r >= budget){
                    if(curPlayer.getLevel() == 2){
                        curPlayer.setCredits(curPlayer.getCredits()+2);
                        gsys.scenesArr[ind].takes-=1;
                    }
                    if(curPlayer.getLevel() == 1){
                        gsys.scenesArr[ind].takes-=1;
                        curPlayer.setCredits(curPlayer.getCredits()+1);
                        curPlayer.setMoney(curPlayer.getMoney()+1);
                    }
                }
                else{
                    if(curPlayer.getLevel()==1){
                        curPlayer.setMoney(curPlayer.getMoney()+1);
                    }
                }
                if(gsys.scenesArr[ind].takes == 0){
                    gsys.scenesArr[ind].wrap();
                    for(int i =0; i < gsys.scenesArr[ind].filledRoles.length; i++){
                        Gplayers[gsys.scenesArr[ind].filledRoles[i+1]].role="no";
                        Gplayers[gsys.scenesArr[ind].filledRoles[i+1]].setLevel(0);
                        Gplayers[gsys.scenesArr[ind].filledRoles[i+1]].setRehearseLvl(0);
                        Gplayers[gsys.scenesArr[ind].filledRoles[i+1]].part = null;
                    }
                }

            }
	    	// code for successful selections
	    	//if(playerSelection = )
    	
	    	turnCounter++;
    	}
    	
    	
    	
    	
    }
}