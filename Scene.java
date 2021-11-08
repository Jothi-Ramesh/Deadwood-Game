public class Scene {
    String name;
    int numRoles;
    int[][] filledRoles;
    String location;
    int shots;
    public Scene(String name, int numRoles, int shots){}
    public int[][] getFilledRoles() {
        return filledRoles;
    }

    public String getName() {
        return name;
    }

    public int getNumRoles() {
        return numRoles;
    }

    public int getShots() {
        return shots;
    }

    public String getLocation() {
        return location;
    }
    public void fillRole(){}
    public void decreaseShot(){}


}
