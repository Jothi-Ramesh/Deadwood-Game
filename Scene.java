public class Scene {
    String name;
    int numRoles;
    int[] filledRoles;
    String location;
    String[] neighbors;
    Part[] parts;
    Card curCard;
    int takes;

    public Scene(String nameIn, int numRolesIn, int takesIn, String neighborsIn[], Part[] partsIn){
        name = nameIn;
        numRoles = numRolesIn;
        takes = takesIn;
        neighbors = neighborsIn;
        parts = partsIn;

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

    public void fillRole(){}
    public void decreaseShot(){}

    public String[] getNeighbors() {
        return neighbors;
    }

    public void addCard(Card card){
        int[] tempRoles = filledRoles;
        int[] newRoles= new int[numRoles+ card.numParts];
        for(int i =0; i< tempRoles.length; i++){
            newRoles[i] = filledRoles[i];
        }
        curCard = card;

    }
    public void wrap(){
        numRoles = 0;
    }
}
