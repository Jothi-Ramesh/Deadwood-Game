import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LocationManager {

    protected int playerCount;
    public int shoots;
    public int days;
    protected Scene[] playerLocationList;
    protected Player[] listOfPlayers;
    protected Scene[] scenes;

    // Create a LocationManager object corresponding to the amount of players
    public LocationManager(int players, Scene[] scenesDict, Player[] playerList) {
        playerCount = players;
        listOfPlayers = playerList;
        scenes = scenesDict;
        playerLocationList = new Scene[playerCount];
        for(int i =0; i < playerCount; i++){
            playerLocationList[i] = scenes[10];
        }
        shoots = 10;


    }
    public void resetManager(Scene[] scenesDict){
        scenes = scenesDict;
        for(int i =0; i < playerCount; i++){
            playerLocationList[i] = scenes[10];
        }
        shoots = 2;
    }

    public LocationManager() {
    }

    // Method to move a specific Player instance to a new location
    public void movePlayer(Player playerToMove, String newPosition) {
        playerToMove.location = newPosition;
        int ind = getInd(newPosition);
        playerLocationList[playerToMove.num-1] = scenes[ind];
        int[] area = scenes[ind].getArea();
        area[2] = 45;
        area[3] = 45;
        playerToMove.setTokenLoc(area);
        // add functionality for updating playerLocationList array
    }

    // Method to check if a specific Player instance is currently at trailers
    public boolean checkIfInTrailers(Player playerToCheck) {
        if(playerToCheck.getLocation().equals("trailers")) {
            return true;
        }
        return false;
    }

    public int getInd(String location){
        for(int i = 0; i < scenes.length; i ++){
            if(scenes[i].name.equals(location)){
                return i;
            }
        }
        return -1;
    }
    public void sceneWrap(Scene curScene){
        boolean someoneOnCard = false;
        int cardCount = 0;
        int boardCount = 0;
        for(int i = 0; i < curScene.numRoles; i++){
            if(curScene.filledRoles[i] != 0){
                Player p = listOfPlayers[curScene.filledRoles[i]-1];
                if(p.role.equals("card")){
                    cardCount++;
                    someoneOnCard = true;
                }
                else{
                    boardCount++;
                }
            }
        }
        Player[] onCard = new Player[cardCount];
        Player[] onBoard = new Player[boardCount];
        int boardInd = 0;
        int cardInd = 0;
        for(int g = 0; g < curScene.numRoles; g++){
            if(curScene.filledRoles[g] != 0){
                Player p = listOfPlayers[curScene.filledRoles[g]-1];
                if(p.role.equals("board")){
                    onBoard[boardInd] = p;
                    boardInd++;
                }
                else{
                    onCard[cardInd] = p;
                    cardInd++;
                }
            }
        }
        int budget = curScene.curCard.budget;
        if(someoneOnCard) {
            int[] rolls = new int[budget];
            for (int g = 0; g < budget; g++) {
                rolls[g] = (int) (Math.random() * 6 + 1);
            }
            int rollInd = 0;
            Arrays.sort(rolls);
            Arrays.sort(onCard, (a, b) -> String.valueOf(a.getRank()).compareTo(String.valueOf(b.getRank())));
            int ind = 0;
            while (rollInd <= budget) {
                onCard[ind].setMoney(onCard[ind].getMoney() + rolls[rollInd]);
                onCard[ind].setRehearseLvl(0);
                onCard[ind].role = "no";
                onCard[ind].part = null;
                ind++;
                rollInd++;
                if (ind >= onCard.length) {
                    ind = 0;
                }
            }
            for (int j = 0; j < onBoard.length; j++) {
                onBoard[j].setMoney(onBoard[j].getMoney() + onBoard[j].getRank());
                onBoard[j].setRehearseLvl(0);
                onBoard[j].role = "no";
                onBoard[j].part = null;
            }
        }
        else {
            for (int j = 0; j < onBoard.length; j++) {
                onBoard[j].setRehearseLvl(0);
                onBoard[j].role = "no";
                onBoard[j].part = null;
            }
        }
        shoots--;
        System.out.println(shoots);
        curScene.numRoles=0;
    }

    /*public void sceneWrap(Scene curScene){

        int onCard = 0;
        Player[] playersInScene = new Player[curScene.numRoles];
        int ind = 0;
        for(int i = 0; i <curScene.filledRoles.length; i++){
            if(curScene.filledRoles[i] != 0){
                int ind2 = curScene.filledRoles[i];
                Player p = listOfPlayers[ind2-1];
                playersInScene[ind] = p;
                if(p.role.equals("card")){
                    onCard = 1;
                }
                ind++;
            }
        }
        if(onCard == 1) {
            for (int j = 0; j < playersInScene.length; j++) {
                int[] rolls = new int[curScene.curCard.budget];
                for (int g = 0; g < ind; g++) {
                    rolls[g] = (int) (Math.random() * 6 + 1);
                }
                int rollInd = 0;
                Arrays.sort(rolls);
                int[][] sortedPlayers = new int[ind][2];
                for (int i = 0; i < ind; i++) {
                    sortedPlayers[i][0] = playersInScene[i].getRank();
                    sortedPlayers[i][1] = playersInScene[i].getNum();
                }
                Arrays.sort(sortedPlayers);
                if (playersInScene[j] != null) {
                    playersInScene[j].role = "no";
                    playersInScene[j].part = null;
                    for (int h = 0; h < rolls.length; h++) {
                        if (rollInd == ind) {
                            rollInd = 0;
                        }
                        if (playersInScene[rollInd].role.equals("card")) {
                            playersInScene[rollInd].setMoney(playersInScene[rollInd].getMoney() + rolls[h]);
                            rollInd++;
                        }
                    }
                }
            }
        }
        else{
            for(int j = 0; j < playersInScene.length; j++){
                if(playersInScene[j] != null) {
                    playersInScene[j].role = "no";
                    playersInScene[j].part = null;
                    //add awarding here tomorrow
                }
            }
        }
        for(int n = 0; n<curScene.filledRoles.length; n++){
            playersInScene[n] = null;
        }
        shoots--;
        curScene.numRoles = 0;
    }

     */
}