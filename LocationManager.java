public class LocationManager {

    protected int playerCount;
    protected Scene[] playerLocationList;
    protected Player[] listOfPlayers;
    protected Scene[] scenes;

    // Create a LocationManager object corresponding to the amount of players
    public LocationManager(int players, Scene[] scenesDict) {
        playerCount = players;
        listOfPlayers = new Player[players];
        scenes = scenesDict;
        for(int i =0; i < playerCount; i++){
            playerLocationList[i] = scenes[10];
        }
        playerLocationList = new Scene[players];


    }

    public LocationManager() {
    }

    // Method to move a specific Player instance to a new location
    public void movePlayer(Player playerToMove, String newPosition) {
        playerToMove.location = newPosition;
        int ind = getInd(newPosition);
        playerLocationList[playerToMove.num-1] = scenes[ind];
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
            if(scenes[i].name == location){
                return i;
            }
        }
        return -1;
    }
}