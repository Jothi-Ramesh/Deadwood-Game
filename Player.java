public class Player {
    int num;
    String name;
    String role;
    String location;
    String scene;
    int credits;
    int rank;
    int money;
    int rehearseLvl;


    Player(int startMon, int startCred, String name, int num, int rank){}

    public int getNum() {
        return num;
    }

    public int getCredits() {
        return credits;
    }
    public int getRank(){
        return rank;
    }
    public int getMoney(){
        return money;
    }
    public String getLocation(){
        return location;
    }
    public void setCredits(int newCredits) {
        credits = newCredits;
    }
    public void setRank(int newRank){
        rank = newRank;
    }
    public void setMoney(int newMoney){
        money = newMoney;
    }
    public void setLocation(String newLocation) {
        location = newLocation;
    }


    public void work(String Role, String location, String scene){}
    private void move(String location){

    }
    public void rehearse(String scene, int rehearseLvl){}
    private void upgrade(int rank, int credits, int money, String location){}
}