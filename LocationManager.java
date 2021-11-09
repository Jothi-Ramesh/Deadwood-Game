public class LocationManager {

    protected int playerCount;
    protected String[] playerLocationList;
    protected Player[] listOfPlayers;

    // Create a LocationManager object corresponding to the amount of players
    public LocationManager(int players) {
        playerCount = players;
        listOfPlayers = new Player[players];
        playerLocationList = new String[players];  
    }

    public LocationManager() {
    }

    // Method to move a specific Player instance to a new location
    public void movePlayer(Player playerToMove, String newPosition) {
        playerToMove.setLocation(newPosition);
        int num = playerToMove.getNum();
        playerLocationList[num] = newPosition;
        // add functionality for updating playerLocationList array
    }

    // Method to check if a specific Player instance is currently at trailers
    public boolean checkIfInTrailers(Player playerToCheck) {
        if(playerToCheck.getLocation().equals("Trailers")) {
            return true;
        }
        return false;
    }
}