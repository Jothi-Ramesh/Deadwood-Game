public class Scene {
    String name;
    int numRolesInt;
    int numRoles;
    int[] filledRoles = new int[0];
    String location;
    String[] neighbors;
    Part[] parts;
    Card curCard;
    int takes;
    int takesTot;
    int[] area;
    int[][] takeLoc;

    public Scene(String nameIn, int numRolesIn, int takesIn, String neighborsIn[], Part[] partsIn, int[] areaIn, int[][] takeLocIn){
        name = nameIn;
        numRoles = numRolesIn;
        numRolesInt = numRoles;
        takes = 0;
        takesTot = takesIn;
        neighbors = neighborsIn;
        parts = partsIn;
        filledRoles = new int [numRoles];
        for (int i = 0; i < numRoles; i ++){
            filledRoles[i] = 0;
        }
        area = areaIn;
        takeLoc = takeLocIn;

    }
    public int[] getFilledRoles() {
        return filledRoles;
    }

    public String getName() {
        return name;
    }

    public int getNumRoles() {
        return numRoles;
    }

    public int getTakes() {
        return takes;
    }

    public String getLocation() {
        return location;
    }

    public void fillRole(int roleInd, int playerNum){
        numRoles--;
        filledRoles[roleInd] = playerNum;
        if(roleInd >numRolesInt){
            curCard.parts[roleInd-numRolesInt].taken = true;
        }
        else{
            parts[roleInd].taken = true;
        }
    }
    public void decreaseShot(){}

    public String[] getNeighbors() {
        return neighbors;
    }

    public int[] getArea() {
        return area;
    }

    public void addCard(Card card){
        int[] newRoles= new int[numRolesInt+ card.numParts];
        for(int i =0; i< newRoles.length; i++){
            newRoles[i] = 0;
        }
        filledRoles = newRoles;
        numRoles = newRoles.length;
        for(int j = 0; j < card.numParts; j ++){
            int[] curArea = card.parts[j].getArea();
            curArea[0] = curArea[0] + area[0];
            curArea[1] = curArea[1] + area[1];
            card.parts[j].setArea(curArea);
        }
        curCard = card;
    }
}
